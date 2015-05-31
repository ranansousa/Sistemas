package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloPagarDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ImpressaoMultiplosRecibosFilter;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.NotaFiscalEntradaModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ParticionadorTituloPagarModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.TituloPagarRateioHistoricoModel;
import br.com.pempec.finance.models.reports.FilterReportFluxoCaixa;
import br.com.pempec.finance.models.reports.FilterReportTituloPagar;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.Repopulador;
import br.com.pempec.finance.utils.Tela;
import java.util.ArrayList;
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

/**
 *
 * @author Pempec
 */
public class TituloPagarDAO implements TituloPagarDAOIf {

    @HibernateTransaction
    public void inserir(Collection<TituloPagarModel> titulos) throws SystemException {

        String registroProvisao = PempecKeyGenerator.generateKey();

        for (TituloPagarModel tituloPagarModel : titulos) {

            HibernateUtil.getCurrentSession().save(tituloPagarModel);

            if (tituloPagarModel.getNotaFiscal() != null) {

                NotaFiscalEntradaModel notaFiscalEntradaModel = tituloPagarModel.getNotaFiscal();
                notaFiscalEntradaModel.getPk().setOrganizacao(tituloPagarModel.getPk().getOrganizacao());

                if (!tituloPagarModel.getNotaFiscal().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                    MovimentoDiarioModel mov = tituloPagarModel.getNotaFiscal().getMovimentoDiarioModel();
                    mov.setObservacao("titulo " + tituloPagarModel.getNumeroDocumento());

                    HibernateUtil.getCurrentSession().save(mov);
                }

                HibernateUtil.getCurrentSession().save(notaFiscalEntradaModel);


            }

            if (tituloPagarModel.getMovimentoDiarioModel() != null) {

                MovimentoDiarioModel mov = tituloPagarModel.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }


            if (tituloPagarModel.getCollCentroCustosRateio() != null && !tituloPagarModel.getCollCentroCustosRateio().isEmpty()) {

                for (TituloPagarRateioCCModel rateio : tituloPagarModel.getCollCentroCustosRateio()) {

                    rateio.setTituloPagarModel(tituloPagarModel);

                    HibernateUtil.getCurrentSession().save(rateio);

                }


            }

            if (tituloPagarModel.getCollHistoricosRateio() != null && !tituloPagarModel.getCollHistoricosRateio().isEmpty()) {

                for (TituloPagarRateioHistoricoModel rateio : tituloPagarModel.getCollHistoricosRateio()) {

                    rateio.setTituloPagarModel(tituloPagarModel);

                    HibernateUtil.getCurrentSession().save(rateio);

                }


            }

            if (tituloPagarModel.isProvisao()) {
                tituloPagarModel.setRegistroProvisao(registroProvisao);
            }

        }

        Repopulador.repopulador(Tela.TELA_PRINCIPAL, null);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void inserir(TituloPagarModel model) throws SystemException {

        HibernateUtil.getCurrentSession().save(model);

        if (model.getNotaFiscal() != null) {

            NotaFiscalEntradaModel notaFiscalEntradaModel = model.getNotaFiscal();
            notaFiscalEntradaModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            if (model.getNotaFiscal().getMovimentoDiarioModel() != null
                    && model.getNotaFiscal().getMovimentoDiarioModel().getCodigo() != null
                    && !model.getNotaFiscal().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getNotaFiscal().getMovimentoDiarioModel();
                mov.setObservacao("titulo " + model.getNumeroDocumento());
                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().save(notaFiscalEntradaModel);

        }

        if (model.getCollCentroCustosRateio() != null && !model.getCollCentroCustosRateio().isEmpty()) {

            for (TituloPagarRateioCCModel rateio : model.getCollCentroCustosRateio()) {

                rateio.setTituloPagarModel(model);

                HibernateUtil.getCurrentSession().save(rateio);

            }

        }

        if (model.getCollHistoricosRateio() != null && !model.getCollHistoricosRateio().isEmpty()) {

            for (TituloPagarRateioHistoricoModel rateio : model.getCollHistoricosRateio()) {

                rateio.setTituloPagarModel(model);

                HibernateUtil.getCurrentSession().save(rateio);

            }

        }

        if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        if (model.isProvisao()) {
            model.setRegistroProvisao(PempecKeyGenerator.generateKey());
        }

        Repopulador.repopulador(Tela.TELA_CONTAS_A_PAGAR_CADASTRO, model);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void alterar(TituloPagarModel model) throws SystemException {

        if (model.getNotaFiscal() != null) {

            NotaFiscalEntradaModel notaFiscalEntradaModel = model.getNotaFiscal();

            notaFiscalEntradaModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            if (notaFiscalEntradaModel.getPk().getId() == null || notaFiscalEntradaModel.getPk().getId().isEmpty()) {

                notaFiscalEntradaModel.getPk().setId(PempecKeyGenerator.generateKey());

                model.getNotaFiscal().getPk().setId(notaFiscalEntradaModel.getPk().getId());

                HibernateUtil.getCurrentSession().save(notaFiscalEntradaModel);

            } else {

                HibernateUtil.getCurrentSession().update(notaFiscalEntradaModel);
            }

            if (!model.getNotaFiscal().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getNotaFiscal().getMovimentoDiarioModel();
                mov.setObservacao(" NF do titulo " + model.getNumeroDocumento());
                HibernateUtil.getCurrentSession().save(mov);

            }

        }

        if (model.getMovimentoDiarioModel() != null && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        if (model.getCollCentroCustosRateio() != null && !model.getCollCentroCustosRateio().isEmpty()) {

            Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloPagarRateioCCModel.deletarCCRateioPorIdTituloPagar");
            query.setParameter("id_titulo_pagar", model.getPk().getId());
            query.executeUpdate();

            for (TituloPagarRateioCCModel rateio : model.getCollCentroCustosRateio()) {
                rateio.setTituloPagarModel(model);
                HibernateUtil.getCurrentSession().save(rateio);
            }

        }

        if (model.getCollHistoricosRateio() != null && !model.getCollHistoricosRateio().isEmpty()) {

            Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloPagarRateioHistoricoModel.deletarHistoricoRateioPorIdTituloPagar");
            query.setParameter("id_titulo_pagar", model.getPk().getId());
            query.executeUpdate();

            for (TituloPagarRateioHistoricoModel rateio : model.getCollHistoricosRateio()) {
                rateio.setTituloPagarModel(model);
                HibernateUtil.getCurrentSession().save(rateio);
            }
        }

        HibernateUtil.getCurrentSession().update(model);

        Repopulador.repopulador(Tela.TELA_CONTAS_A_PAGAR_CADASTRO, model);
        Repopulador.repopulador();

    }

    public List<TituloPagarModel> obterPorFiltro(TituloPagarModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);


        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.like("numeroDocumento", model.getNumeroDocumento(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));


        if (model.getProvisao() != null) {
            criteria.add(Restrictions.eq("provisao", model.getProvisao()));
            criteria.add(Restrictions.isNull("dataPagamento"));
            criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_PAGO));
            criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));
        }



        if (model.getProvisao() != null) {
            criteria.add(Restrictions.eq("provisao", model.getProvisao()));
        }

        criteria.addOrder(Order.asc("dataEmissao"));
        criteria.addOrder(Order.asc("numeroDocumento"));

        return criteria.list();

    }

    public List<TituloPagarModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.asc("dataEmissao"));
        criteria.addOrder(Order.asc("status"));
        criteria.addOrder(Order.asc("dataVencimento"));


        return criteria.list();
    }

    public List<TituloPagarModel> obterRelatorio(FilterReportTituloPagar filtro)
            throws SystemException {

        //metodo sendo utilizado tambem na FichaCadastralCedente com o parametro CEDENTE

        //metodo sendo utilizado tambem na telaCartaoCredito com o parametro CEDENTE para total de cartao credito


        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        if (filtro.getCedente() != null && !filtro.getCedente().isEmpty()) {
            criteria.add(Restrictions.eq("cedente.pk.id", filtro.getCedente()));
        }

        if (filtro.getCentroCusto() != null && !filtro.getCentroCusto().isEmpty()) {
            criteria.add(Restrictions.eq("centroCusto.pk.id", filtro.getCentroCusto()));
        }

        if (filtro.getHistorico() != null && !filtro.getHistorico().isEmpty()) {
            criteria.add(Restrictions.eq("historico.pk.id", filtro.getHistorico()));
        }

        if (filtro.getLocalPagamento() != null && !filtro.getLocalPagamento().isEmpty()) {
            criteria.add(Restrictions.eq("localPagamento.pk.id", filtro.getLocalPagamento()));
        }

        if (filtro.getResponsavel() != null && !filtro.getResponsavel().isEmpty()) {
            criteria.add(Restrictions.eq("responsavel.pk.id", filtro.getResponsavel()));
        }

        if (filtro.getStatus() != null && !filtro.getStatus().isEmpty()) {
            criteria.add(Restrictions.eq("status.pk.id", filtro.getStatus()));
        }

        if (filtro.getTipoCobranca() != null && !filtro.getTipoCobranca().isEmpty()) {
            criteria.add(Restrictions.eq("tipoCobranca.pk.id", filtro.getTipoCobranca()));
        }

        criteria.add(Restrictions.between("dataVencimento", filtro.getDataInicial(), filtro.getDataFinal()));

        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);

        
        criteria.addOrder(Order.asc("dataVencimento"));
        criteria.addOrder(Order.desc("valorNominal"));
        criteria.addOrder(Order.asc("dataEmissao"));
        criteria.addOrder(Order.asc("cedente"));
        

        return criteria.list();

    }

    public List<TituloPagarModel> obterImpressaoMultiplosRecibos(ImpressaoMultiplosRecibosFilter filter)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        if (filter.getDataPagamento() != null) {
            criteria.add(Restrictions.eq("dataPagamento", filter.getDataPagamento()));
        }

        if (filter.getNumeroDocumentoInicial() != null && !filter.getNumeroDocumentoInicial().isEmpty()) {

            if (filter.getNumeroDocumentoFinal() != null && !filter.getNumeroDocumentoFinal().isEmpty()) {
                criteria.add(Restrictions.between("numeroDocumento", filter.getNumeroDocumentoInicial(), filter.getNumeroDocumentoFinal()));
            } else {
                criteria.add(Restrictions.like("numeroDocumento", filter.getNumeroDocumentoInicial(), MatchMode.START));
            }

        }

        criteria.add(Restrictions.eq("pk.organizacao.id", filter.getOrganizacao().getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataEmissao"));
        criteria.addOrder(Order.asc("numeroDocumento"));

        return criteria.list();

    }

    public List<TituloPagarModel> obterRelatorioFluxo(FilterReportFluxoCaixa filtro)
            throws SystemException {


        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        if (filtro.getCedente() != null && !filtro.getCedente().isEmpty()) {
            criteria.add(Restrictions.eq("cedente.pk.id", filtro.getCedente()));
        }

        if (filtro.getCentroCusto() != null && !filtro.getCentroCusto().isEmpty()) {
            criteria.add(Restrictions.eq("centroCusto.pk.id", filtro.getCentroCusto()));
        }

        if (filtro.getHistorico() != null && !filtro.getHistorico().isEmpty()) {
            criteria.add(Restrictions.eq("historico.pk.id", filtro.getHistorico()));
        }

        if (filtro.getLocalPagamento() != null && !filtro.getLocalPagamento().isEmpty()) {
            criteria.add(Restrictions.eq("localPagamento.pk.id", filtro.getLocalPagamento()));
        }

        if (filtro.getResponsavel() != null && !filtro.getResponsavel().isEmpty()) {
            criteria.add(Restrictions.eq("responsavel.pk.id", filtro.getResponsavel()));
        }

        if (filtro.getStatus() != null && !filtro.getStatus().isEmpty()) {
            criteria.add(Restrictions.eq("status.pk.id", filtro.getStatus()));
        }

        if (filtro.getTipoCobranca() != null && !filtro.getTipoCobranca().isEmpty()) {
            criteria.add(Restrictions.eq("tipoCobranca.pk.id", filtro.getTipoCobranca()));
        }

        criteria.add(Restrictions.between("dataVencimento", filtro.getDataInicial(), filtro.getDataFinal()));

        criteria.add(Restrictions.isNull("dataPagamento"));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_PAGO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);


        
        criteria.addOrder(Order.asc("dataVencimento"));
        criteria.addOrder(Order.asc("valorNominal"));
        criteria.addOrder(Order.asc("dataEmissao"));

        criteria.addOrder(Order.asc("cedente"));
        

        return criteria.list();

    }

    public List<TituloPagarModel> obterTodosAPagar(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);


        criteria.add(Restrictions.isNull("dataPagamento"));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_PAGO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("lotePagamentoModel", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);


        criteria.addOrder(Order.asc("dataVencimento"));
       criteria.addOrder(Order.asc("valorNominal"));
        //criteria.addOrder(Order.asc("dataEmissao"));
        criteria.addOrder(Order.asc("cedente"));



        return criteria.list();
    }

    public List<TituloPagarModel> obterTodosRecibo(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataPagamento"));

        return criteria.list();
    }

    public List<TituloPagarModel> obterTodosPagos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        criteria.add(Restrictions.isNotNull("dataPagamento"));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_NOVO));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataEmissao"));
        criteria.addOrder(Order.asc("dataVencimento"));
        criteria.addOrder(Order.asc("valorNominal"));
        criteria.addOrder(Order.asc("cedente"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloPagarModel> coll)
            throws SystemException {

        for (TituloPagarModel model : coll) {

            Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloPagarRateioCCModel.deletarCCRateioPorIdTituloPagar");
            query.setParameter("id_titulo_pagar", model.getPk().getId());
            query.executeUpdate();

            Query queryHistorico = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloPagarRateioHistoricoModel.deletarHistoricoRateioPorIdTituloPagar");
            queryHistorico.setParameter("id_titulo_pagar", model.getPk().getId());
            queryHistorico.executeUpdate();

            if (model.getNotaFiscal() != null) {

                NotaFiscalEntradaModel notaFiscalEntradaModel = model.getNotaFiscal();
                notaFiscalEntradaModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
                notaFiscalEntradaModel.setObservacao(model.getNotaFiscal().getObservacao() + " - " + " ## O título foi excluido. ##");

                if (!model.getNotaFiscal().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                    MovimentoDiarioModel mov = model.getNotaFiscal().getMovimentoDiarioModel();
                    mov.setCodigo(model.getNotaFiscal().getNumero());
                    mov.setObservacao("titulo " + model.getNumeroDocumento());
                    HibernateUtil.getCurrentSession().save(mov);

                }

                HibernateUtil.getCurrentSession().update(notaFiscalEntradaModel);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador(Tela.TELA_PRINCIPAL, null);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void excluir(TituloPagarModel model)
            throws SystemException {
        // o titulo nao sera excluido fisicamente. 
        // tera apenas o status de EXCLUIDO
        if (model.getNotaFiscal() != null) {

            NotaFiscalEntradaModel notaFiscalEntradaModel = model.getNotaFiscal();
            notaFiscalEntradaModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
            notaFiscalEntradaModel.setObservacao(model.getNotaFiscal().getObservacao() + " - " + " ## O título foi excluido. ##");

            if (!model.getNotaFiscal().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getNotaFiscal().getMovimentoDiarioModel();
                mov.setCodigo(model.getNotaFiscal().getNumero());
                mov.setObservacao("titulo " + model.getNumeroDocumento());
                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().update(notaFiscalEntradaModel);

        }

        if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloPagarRateioCCModel.deletarCCRateioPorIdTituloPagar");
        query.setParameter("id_titulo_pagar", model.getPk().getId());
        query.executeUpdate();

        Query queryHistorico = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloPagarRateioHistoricoModel.deletarHistoricoRateioPorIdTituloPagar");
        queryHistorico.setParameter("id_titulo_pagar", model.getPk().getId());
        queryHistorico.executeUpdate();

        HibernateUtil.getCurrentSession().update(model);

        Repopulador.repopulador(Tela.TELA_CONTAS_A_PAGAR_CADASTRO, model);
        Repopulador.repopulador();

    }

    public TituloPagarModel consultarPorPk(TituloPagarModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        return (TituloPagarModel) criteria.uniqueResult();

    }

    public List<TituloPagarRateioCCModel> obterRateioPorTituloPagar(TituloPagarModel titulo)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarRateioCCModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", titulo.getPk().getOrganizacao().getId()));

        criteria.add(Restrictions.eq("tituloPagarModel.pk.id", titulo.getPk().getId()));

        criteria.setFetchMode("centroCustoModel", FetchMode.JOIN);

        return criteria.list();

    }

    public List<TituloPagarRateioHistoricoModel> obterHistoricosPorTituloPagar(TituloPagarModel titulo)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarRateioHistoricoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", titulo.getPk().getOrganizacao().getId()));

        criteria.add(Restrictions.eq("tituloPagarModel.pk.id", titulo.getPk().getId()));

        criteria.setFetchMode("historicoModel", FetchMode.JOIN);

        return criteria.list();

    }

    public TituloPagarModel consultarPorTemplate(TituloPagarModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.eq("numeroDocumento", model.getNumeroDocumento()));
        }

        if (model.getChequeVinculado() != null && model.getChequeVinculado().getPk() != null) {
            criteria.add(Restrictions.eq("chequeVinculado.pk.id", model.getChequeVinculado().getPk().getId()));
        }

        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloPagarModel) criteria.uniqueResult();

    }

    public TituloPagarModel consultarPorTemplatePago(TituloPagarModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.eq("numeroDocumento", model.getNumeroDocumento()));
        }

        criteria.add(Restrictions.isNotNull("dataPagamento"));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloPagarModel) criteria.uniqueResult();

    }

    public TituloPagarModel consultarPorTemplateAPagar(TituloPagarModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.eq("numeroDocumento", model.getNumeroDocumento()));
        }

        criteria.add(Restrictions.isNull("dataPagamento"));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_PAGO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);


        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloPagarModel) criteria.uniqueResult();

    }

    /**
     * Método utilizado para exportaÃ§Ã£o de títulos para Contabilidade Sistema:
     * MEGACONTABIL.
     *
     * @param model
     * @param dataInicial
     * @param dataFinal
     * @return
     * @throws br.com.pempec.finance.exceptions.SystemException
     */
    public List<TituloPagarModel> obterTitulosExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.between("dataEmissao", dataInicial, dataFinal));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        criteria.add(Restrictions.isNull("tituloAnterior.pk.id"));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);


        criteria.addOrder(Order.asc("dataEmissao"));
        return criteria.list();

    }

    /**
     * Método utilizado para exportaÃ§Ã£o de títulos para Contabilidade Sistema:
     * MEGACONTABIL.
     *
     * @param model
     * @param dataInicial
     * @param dataFinal
     * @return
     * @throws br.com.pempec.finance.exceptions.SystemException
     */
    public List<TituloPagarModel> obterTitulosExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.between("dataEmissao", dataInicial, dataFinal));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.isNull("tituloAnterior.pk.id"));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataEmissao"));

        return criteria.list();

    }

    /**
     * Este método traz todos os títulos filho do titulo obtido por parametro,
     * sendo que ele engloba o próprio título passado.
     *
     * @param model título base a procurar pelos filhos
     * @return Coleção de titulos da Hierarquia do titulo. Inclui o proprio
     * titulo.
     * @throws br.com.pempec.finance.exceptions.SystemException
     */
    public List<TituloPagarModel> obterTitulosFilhos(TituloPagarModel model) throws SystemException {

        TituloPagarModel aux = model;

        Boolean temFilho = false;

        List<TituloPagarModel> auxColl = new ArrayList<TituloPagarModel>();

        auxColl.add(model);

        do {

            Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                    TituloPagarModel.class);

            criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

            criteria.add(Restrictions.eq("tituloAnterior.pk.id", aux.getPk().getId()));

            criteria.add(Restrictions.eq("pk.organizacao.id", aux.getPk().getOrganizacao().getId()));
            criteria.setMaxResults(1);

            aux = (TituloPagarModel) criteria.uniqueResult();

            if (aux != null && aux.getTituloAnterior() != null) {

                temFilho = true;

                auxColl.add(aux);

            } else {

                temFilho = false;
            }

        } while (temFilho);

        return auxColl;

    }

    /**
     * Este método traz todos os títulos antecessores do titulo obtido por
     * parametro, sendo que ele engloba o próprio título passado.
     *
     * @param model título base a procurar pelos antecessores
     * @return Coleção de títulos da Hierarquia do titulo. Inclui o proprio
     * titulo.
     * @throws br.com.pempec.finance.exceptions.SystemException
     */
    public List<TituloPagarModel> obterTitulosAntecipados(TituloPagarModel model) throws SystemException {

        TituloPagarModel aux = model;

        Boolean temAntecessor = false;

        List<TituloPagarModel> auxColl = new ArrayList<TituloPagarModel>();

        auxColl.add(model);

        if (aux.getTituloAnterior() != null) {

            do {

                Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                        TituloPagarModel.class);

                criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

                criteria.add(Restrictions.eq("pk.id", aux.getTituloAnterior().getPk().getId()));

                criteria.add(Restrictions.eq("pk.organizacao.id", aux.getTituloAnterior().getPk().getOrganizacao().getId()));

                criteria.setMaxResults(1);

                aux = (TituloPagarModel) criteria.uniqueResult();

                if (aux != null && aux.getTituloAnterior() != null) {

                    temAntecessor = true;

                } else {

                    temAntecessor = false;
                }

                auxColl.add(aux);

            } while (temAntecessor);

        }

        return auxColl;

    }

    //pega o titulo existente e particiona ele com o novo valor e nova data de vencimento que o usuario vai informar
    @HibernateTransaction
    public void particionar(ParticionadorTituloPagarModel model) throws SystemException {

        if (model.getTituloAnterior() != null) {
            TituloPagarModel tituloAnterior = model.getTituloAnterior();

            HibernateUtil.getCurrentSession().update(tituloAnterior);

        }

        if (model.getTituloNovo() != null) {

            TituloPagarModel tituloNovo = model.getTituloNovo();

            if (!model.getTituloNovo().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getTituloNovo().getMovimentoDiarioModel();
                HibernateUtil.getCurrentSession().save(mov);
            }

            HibernateUtil.getCurrentSession().save(tituloNovo);

        }

        Repopulador.repopulador(Tela.TELA_CONTAS_A_PAGAR_CADASTRO, null);
        Repopulador.repopulador();


    }

    public List<TituloPagarModel> obterTitulosLotePagamento(LotePagamentoTituloModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);
        criteria.add(Restrictions.eq("lotePagamentoModel.pk.id", model.getPk().getId()));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataEmissao"));

        return criteria.list();
    }

    public List<TituloPagarModel> obterTitulosExportados(LoteContabilModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);
        criteria.add(Restrictions.eq("loteContabil.pk.id", model.getPk().getId()));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);
        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataEmissao"));
        return criteria.list();
    }

    /**
     * Método utilizado para pegar titulos a vencer por periodo Sistema: FINANCE
     *
     * @param model
     * @param dataInicial
     * @param dataFinal
     * @return
     * @throws br.com.pempec.finance.exceptions.SystemException
     */
    public List<TituloPagarModel> obterTodosPorPeriodo(TituloPagarModel model, Date dataInicial, Date dataFinal, String[] ordem)
            throws SystemException {


        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);


        criteria.add(Restrictions.isNull("dataPagamento"));

        criteria.add(Restrictions.between("dataVencimento", dataInicial, dataFinal));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_PAGO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        if (model.getCedente() != null && model.getCedente().getPk() != null) {
            criteria.add(Restrictions.eq("cedente.pk.id", model.getCedente().getPk().getId()));
        }


        criteria.add(Restrictions.isNull("chequeVinculado.pk.id"));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);

        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);

        criteria.setFetchMode("usuario", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

//        criteria.addOrder(Order.asc("dataEmissao"));
//        criteria.addOrder(Order.asc("dataVencimento"));
//        criteria.addOrder(Order.asc("cedente"));
//        criteria.addOrder(Order.asc("cedente"));

        int indice = 0;


        if (ordem[indice].toString() != null && !ordem[indice].toString().isEmpty()) {
            criteria.addOrder(Order.asc(ordem[indice].toString()));
            indice++;
        }

        if (ordem[indice].toString() != null && !ordem[indice].toString().isEmpty()) {
            criteria.addOrder(Order.asc(ordem[indice].toString()));
            indice++;
        }

        if (ordem[indice].toString() != null && !ordem[indice].toString().isEmpty()) {
            criteria.addOrder(Order.asc(ordem[indice].toString()));
            indice++;
        }
        if (ordem[indice].toString() != null && !ordem[indice].toString().isEmpty()) {
            criteria.addOrder(Order.asc(ordem[indice].toString()));
        }



        return criteria.list();

    }

    public List<TituloPagarModel> obterNotaFiscaisPorPeriodo(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        criteria.setFetchMode("chequeVinculado", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        Criteria subCriteria = criteria.createCriteria("cedente", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("cartaoCreditoModel", FetchMode.JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        Criteria subCriteriaNF = criteria.createCriteria("notaFiscal");

        subCriteriaNF.add(Restrictions.between("dataEmissao", dataInicial, dataFinal));

        subCriteriaNF.setFetchMode("tipoDocumento", FetchMode.JOIN);

        subCriteriaNF.addOrder(Order.asc("dataEmissao"));

        criteria.addOrder(Order.asc("dataEmissao"));

        return criteria.list();

    }

    public List<TituloPagarModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarModel.class);

        criteria.add(Restrictions.eq("dataRegistro", dataInicial));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.asc("dataRegistro"));

        return criteria.list();

    }

    @HibernateTransaction
    public void corrigeRegistroProvisao() throws SystemException {

        try {

            Query query = HibernateUtil.getCurrentSession().createSQLQuery("update titulo_pagar t set t.registro_provisao = null where t.is_provisao = 0");

            query.executeUpdate();

            List<TituloPagarModel> titulos;

            Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                    TituloPagarModel.class);

            criteria.add(Restrictions.eq("parcela", "1"));

            criteria.add(Restrictions.eq("provisao", 1));

            titulos = criteria.list();

            for (TituloPagarModel tituloPagarModel : titulos) {
                tituloPagarModel.setRegistroProvisao(PempecKeyGenerator.generateKey());
                HibernateUtil.getCurrentSession().update(tituloPagarModel);
            }

        } catch (Exception e) {
            throw new SystemException(e);
        }

    }
}//fim

