package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ContaBancariaDebitoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaDebito;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class ContaBancariaDebitoDAO implements ContaBancariaDebitoDAOIf {

    @HibernateTransaction
    public void inserir(ContaBancariaDebitoModel model) throws SystemException {

        TituloPagarBaixaChequeDAO dao = new TituloPagarBaixaChequeDAO();

        if (model.getContaBancariaCheque() != null) {
            ContaBancariaChequeModel cheque = model.getContaBancariaCheque();
            HibernateUtil.getCurrentSession().update(cheque);

            Collection<TituloPagarBaixaChequeModel> auxCollTitBaixCheq = dao.obterPorCheque(model.getContaBancariaCheque());

            for (TituloPagarBaixaChequeModel tituloPagarBaixaChequeModel : auxCollTitBaixCheq) {
                TituloPagarModel titModel = tituloPagarBaixaChequeModel.getTituloPagarBaixa().getTitulo();
                titModel.setDataPagamento(model.getContaBancariaCheque().getDataCompensado());
                HibernateUtil.getCurrentSession().update(titModel);
            }

        }

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void transfereBancoTesouraria(ContaBancariaDebitoModel debito, TesourariaCreditoModel credito) throws SystemException {

        if (debito.getContaBancariaCheque() != null) {

            ContaBancariaChequeModel chequeModel = debito.getContaBancariaCheque();
            chequeModel.setMovimentoDiarioModel(registroMovimento("Alterar CH", debito.getIdentificacao(), "Cheque no valor de R$ " + debito.getValor() + "  utilizado na transf tesour : " + credito.getNumeroDocumento(), debito.getValor(), "Alterado"));
            chequeModel.getMovimentoDiarioModel().setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

            if (chequeModel.getMovimentoDiarioModel() != null) {
                HibernateUtil.getCurrentSession().save(chequeModel.getMovimentoDiarioModel());
            }


            HibernateUtil.getCurrentSession().update(chequeModel);

        }


        if (debito != null) {
            ContaBancariaDebitoModel contaDebito = debito;
            contaDebito.setMovimentoDiarioModel(registroMovimento("Transf P/ Tesour.", debito.getIdentificacao(), "Transferencia de valor para a tesouraria  ", credito.getValorNominal(), "Transferido"));
            contaDebito.getMovimentoDiarioModel().setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

            if (contaDebito.getMovimentoDiarioModel() != null) {
                HibernateUtil.getCurrentSession().save(contaDebito.getMovimentoDiarioModel());
            }

            HibernateUtil.getCurrentSession().save(contaDebito);
        }

        if (credito != null) {

            TesourariaCreditoModel contaCredito = credito;
            
            // mudando em 1607.             
            //se o numero do documento nao foi igual a identificacao é pq ja existia registro de identificacao e entao oco
            if (! credito.getNumeroDocumento().equals(debito.getIdentificacao())){
                            
                   credito.setNumeroDocumento(debito.getIdentificacao());
                   credito.setDescricao("DA CONTA   Nº " + debito.getContaBancaria().getConta() + "  Ident. : " + debito.getIdentificacao());
            }
             // fim mud
            
            contaCredito.setMovimentoDiarioModel(registroMovimento("Transf do Bco.", credito.getNumeroDocumento(), "Credito em especie originado da conta bancaria  " + debito.getContaBancaria().getConta(), credito.getValorNominal(), "Creditado"));
            contaCredito.getMovimentoDiarioModel().setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

            if (contaCredito.getMovimentoDiarioModel() != null) {
                HibernateUtil.getCurrentSession().save(contaCredito.getMovimentoDiarioModel());
            }


            HibernateUtil.getCurrentSession().save(contaCredito);
        }





        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void estornoTransferenciaBancoTesouraria(ContaBancariaDebitoModel debito, TesourariaCreditoModel credito) throws SystemException {

        // deleta o credito na tesouraria

        if (credito != null) {

            TesourariaCreditoModel contaCredito = credito;

            contaCredito.setMovimentoDiarioModel(registroMovimento(" EST Transf do Bco.", credito.getNumeroDocumento(), " EST Credito em especie originado da conta bancaria  " + debito.getContaBancaria().getConta(), credito.getValorNominal(), "ESTORNADO"));

            contaCredito.getMovimentoDiarioModel().setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

            if (contaCredito.getMovimentoDiarioModel() != null) {

                HibernateUtil.getCurrentSession().save(contaCredito.getMovimentoDiarioModel());
            }


            HibernateUtil.getCurrentSession().delete(contaCredito);
        }


        // deleta o debito na conta


        if (debito != null) {

            ContaBancariaDebitoModel contaDebito = debito;

            contaDebito.setMovimentoDiarioModel(registroMovimento("EST Transf P/ Tesour.", debito.getIdentificacao(), " EST Transf de valor para a tesouraria  ", debito.getValor(), "Estornado"));

            contaDebito.getMovimentoDiarioModel().setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

            if (contaDebito.getMovimentoDiarioModel() != null) {

                HibernateUtil.getCurrentSession().save(contaDebito.getMovimentoDiarioModel());

            }

            HibernateUtil.getCurrentSession().delete(contaDebito);
        }


        //altera o cheque utilizado

        if (debito.getContaBancariaCheque() != null) {

            ContaBancariaChequeModel chequeModel = debito.getContaBancariaCheque();
            chequeModel.setMovimentoDiarioModel(registroMovimento(" EST TRANSF C/ CH", debito.getIdentificacao(), "Cheque no valor de R$ " + debito.getValor() + "  utilizado na transf tesour : " + credito.getNumeroDocumento(), debito.getValor(), "ES TRANSF"));
            chequeModel.getMovimentoDiarioModel().setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

            if (chequeModel.getMovimentoDiarioModel() != null) {

                HibernateUtil.getCurrentSession().save(chequeModel.getMovimentoDiarioModel());

            }


            HibernateUtil.getCurrentSession().update(chequeModel);

        }



        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void inserirMultiplos(Collection<ContaBancariaDebitoModel> lista) throws SystemException {

        TituloPagarBaixaChequeDAO dao = new TituloPagarBaixaChequeDAO();

        for (ContaBancariaDebitoModel model : lista) {

            if (model.getContaBancariaCheque() != null) {
                ContaBancariaChequeModel cheque = model.getContaBancariaCheque();
                cheque.setMovimentoDiarioModel(registroMovimento("Alterar CH", model.getTituloPagar().getNumeroDocumento(), "Cheque no valor de R$ " + cheque.getValor() + "  utilizado no pagto do título : " + model.getTituloPagar().getNumeroDocumento(), model.getTituloPagar().getValorNominal(), "Alterado"));
                HibernateUtil.getCurrentSession().update(cheque);

                Collection<TituloPagarBaixaChequeModel> auxCollTitBaixCheq = dao.obterPorCheque(model.getContaBancariaCheque());

                for (TituloPagarBaixaChequeModel tituloPagarBaixaChequeModel : auxCollTitBaixCheq) {
                    TituloPagarModel titModel = tituloPagarBaixaChequeModel.getTituloPagarBaixa().getTitulo();
                    titModel.setDataPagamento(model.getContaBancariaCheque().getDataCompensado());
                    HibernateUtil.getCurrentSession().update(titModel);
                }

            }

            if (model.getMovimentoDiarioModel() != null && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
                HibernateUtil.getCurrentSession().save(mov);
            }

            HibernateUtil.getCurrentSession().save(model);

        }

        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void alterar(ContaBancariaDebitoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public ContaBancariaDebitoModel consultarPorPk(ContaBancariaDebitoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        if (model.getPk().getId() != null) {
            criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));
        }

        criteria.setMaxResults(1);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagar", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (ContaBancariaDebitoModel) criteria.uniqueResult();
    }

    @HibernateTransaction
    public void excluir(ContaBancariaDebitoModel model)
            throws SystemException {

        if (model.getContaBancariaCheque() != null) {

            ContaBancariaChequeModel cheque = model.getContaBancariaCheque();

            HibernateUtil.getCurrentSession().update(cheque);

        }

        if (model.getMovimentoDiarioModel() != null && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();

    }

    public ContaBancariaDebitoModel consultarPorTemplate(ContaBancariaDebitoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        if (model.getPk().getId() != null) {
            criteria.add(Restrictions.eq("identificacao", model.getIdentificacao()));
        }

        criteria.setMaxResults(1);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagar", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (ContaBancariaDebitoModel) criteria.uniqueResult();

    }

    public List<ContaBancariaDebitoModel> obterPorFiltro(ContaBancariaDebitoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        if (model.getContaBancariaCheque().getPk().getId() != null && !model.getContaBancariaCheque().getPk().getId().isEmpty()) {
            criteria.add(Restrictions.like("contaBancariaCheque.pk.id", model.getContaBancariaCheque().getPk().getId(),
                    MatchMode.START));
        }

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));


        return criteria.list();

    }

    public ContaBancariaDebitoModel obterPorCheque(ContaBancariaDebitoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        if (model.getContaBancariaCheque().getPk().getId() != null && !model.getContaBancariaCheque().getPk().getId().isEmpty()) {
            criteria.add(Restrictions.eq("contaBancariaCheque.pk.id", model.getContaBancariaCheque().getPk().getId()));
        }

        criteria.setMaxResults(1);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagar", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (ContaBancariaDebitoModel) criteria.uniqueResult();

    }

    public List<ContaBancariaDebitoModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));



        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaCheque", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.asc("contaBancariaCheque"));

        return criteria.list();

    }

    public List<ContaBancariaDebitoModel> obterPorContaBancariaStatus(ContaBancariaModel model, TipoStatusModel status)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));
        }

        criteria.add(Restrictions.eq("status.pk.id", status.getPk().getId()));


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.desc("dataMovimento"));

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return criteria.list();

    }

    public List<ContaBancariaDebitoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataMovimento"));

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<ContaBancariaDebitoModel> coll)
            throws SystemException {

        for (ContaBancariaDebitoModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    public List<ContaBancariaDebitoModel> obterTodosLancamentosBancariosPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        criteria.add(Restrictions.eq("tipoLancamento", tipo));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.asc("identificacao"));

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagar", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return criteria.list();

    }

    public ContaBancariaDebitoModel consultarPorTemplateLancamentoBancario(ContaBancariaDebitoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        if (model.getIdentificacao() != null) {
            criteria.add(Restrictions.eq("identificacao", model.getIdentificacao()));
        }

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagar", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        return (ContaBancariaDebitoModel) criteria.uniqueResult();

    }

    public List<ContaBancariaDebitoModel> obterTodosLancamentosTesourariaPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);


        criteria.add(Restrictions.eq("tipoLancamento", tipo));


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.asc("identificacao"));

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return criteria.list();

    }

    public List<ContaBancariaDebitoModel> obterContaBancariaDebitoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.isNull("tituloPagar.pk.id"));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        criteria.add(Restrictions.ne("tipoOperacaoBancaria.pk.id", Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_CONTAS));

        criteria.add(Restrictions.between("dataMovimento", dataInicial, dataFinal));

        Criteria subCriteria1 = criteria.createCriteria("tipoOperacaoBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria1.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();


    }

    public List<ContaBancariaDebitoModel> obterContaBancariaDebitoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.isNull("tituloPagar.pk.id"));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        criteria.add(Restrictions.ne("tipoOperacaoBancaria.pk.id", Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_CONTAS));

        criteria.add(Restrictions.between("dataMovimento", dataInicial, dataFinal));

        Criteria subCriteria1 = criteria.createCriteria("tipoOperacaoBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria1.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();


    }

    private MovimentoDiarioModel registroMovimento(String acao, String codigo, String descricao, Double valor, String status) {
        MovimentoDiarioModel movimentoDiarioModel = new MovimentoDiarioModel();
        movimentoDiarioModel.setPk(new PKModel());
        movimentoDiarioModel.getPk().setOrganizacao(Controller.getOrganizacao());
        movimentoDiarioModel.getPk().setId(PempecKeyGenerator.generateKey());
        movimentoDiarioModel.setDataRegistro(PempecParse.dateToDate(new Date()));
        movimentoDiarioModel.setAcao(acao);
        movimentoDiarioModel.setCodigo(codigo);
        movimentoDiarioModel.setDescricaoObjeto(descricao);
        movimentoDiarioModel.setValorOperacao(valor);
        movimentoDiarioModel.setStatusFinalObjeto(status);
        movimentoDiarioModel.setObjeto(this.getClass().getSimpleName());

        return movimentoDiarioModel;

    }

    public List<ContaBancariaDebitoModel> obterTitulosExportados(LoteContabilModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        criteria.add(Restrictions.eq("loteContabil.pk.id", model.getPk().getId()));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria1 = criteria.createCriteria("tipoOperacaoBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria1.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();
    }

    public List<ContaBancariaDebitoModel> obterTodosTitulosPagos(OrganizacaoModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        criteria.add(Restrictions.isNotNull("tituloPagar.pk.id"));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));


        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();
    }

    public List<ContaBancariaDebitoModel> obterRelatorio(FilterReportContaBancariaDebito filtro)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        if (filtro.getContaBancaria() != null && !filtro.getContaBancaria().isEmpty()) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", filtro.getContaBancaria()));
        }

        if (filtro.getTipoOperacaoBancaria() != null && !filtro.getTipoOperacaoBancaria().isEmpty()) {
            criteria.add(Restrictions.eq("tipoOperacaoBancaria.pk.id", filtro.getTipoOperacaoBancaria()));
        }

        if (filtro.getResponsavel() != null && !filtro.getResponsavel().isEmpty()) {
            criteria.add(Restrictions.eq("responsavel.pk.id", filtro.getResponsavel()));
        }

        criteria.add(Restrictions.between("dataMovimento", filtro.getDataInicial(), filtro.getDataFinal()));

        Criteria subCriteria = criteria.createCriteria("tituloPagar", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cedente", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataMovimento"));
        criteria.addOrder(Order.asc("valor"));

        return criteria.list();

    }

    public Double obterSaldoAnterior(FilterReportContaBancariaDebito filter) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("select sum(bd.valor) from br.com.pempec.finance.models.ContaBancariaDebitoModel bd "
                + " WHERE bd.dataMovimento < :data and bd.pk.organizacao.id = :org and bd.contaBancaria.pk.id = :conta ");

        query.setDate("data", filter.getDataInicial());
        query.setParameter("org", filter.getOrganizacao());
        query.setParameter("conta", filter.getContaBancaria());

        return (Double) query.uniqueResult();

    }

    public Double obterTotal(ContaBancariaModel contaBancaria) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("select sum(bd.valor) from br.com.pempec.finance.models.ContaBancariaDebitoModel bd "
                + " WHERE bd.pk.organizacao.id = :org and bd.contaBancaria.pk.id = :conta ");

        query.setParameter("org", contaBancaria.getPk().getOrganizacao().getId());
        query.setParameter("conta", contaBancaria.getPk().getId());

        return (Double) query.uniqueResult();

    }

    public List<ContaBancariaDebitoModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        criteria.add(Restrictions.eq("dataRegistro", dataInicial));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));
        
        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.asc("identificacao"));

        return criteria.list();

    }

    public List<ContaBancariaDebitoModel> obterTodosPorOperacaoBancaria(OrganizacaoModel model, TipoOperacaoBancariaModel operacao)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaDebitoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.eq("tipoOperacaoBancaria.pk.id", operacao.getPk().getId()));

        criteria.add(Restrictions.isNotNull("contaBancariaCheque.pk.id"));

        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.asc("identificacao"));

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return criteria.list();

    }

    

}
