package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloPagarBaixaDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.TituloPagarRateioHistoricoModel;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

//EQUIPE PEMPEC
public class TituloPagarBaixaDAO implements TituloPagarBaixaDAOIf {

    @HibernateTransaction
    public void salvar(TituloPagarBaixaModel model, TituloPagarModel titulo) throws SystemException {

        String dscOriginal = titulo.getDescricao();

        Double valorPagar = titulo.getValorNominal();
        HistoricoModel historicoModel = new HistoricoModel();
        historicoModel.setPk(new PKModel());
        historicoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

        if (model.getTipoBaixa().equals(Constantes.TIPO_BAIXA_TITULO_PARCIAL)) {

            //Reajustando o adiantamento
            Double valorAntecipado = 0d;

            if (titulo.getValorAntecipado() != null && titulo.getValorAntecipado() > 0) {

                valorAntecipado += titulo.getValorAntecipado() + model.getValorPago();

            } else {

                valorAntecipado += model.getValorPago();

            }

            titulo.setValorAntecipado(valorAntecipado);

        }

        //Alterando o título
        HibernateUtil.getCurrentSession().merge(titulo);

        for (TituloPagarBaixaFormaPagamentoModel forma : model.getFormasPagamento()) {

            forma.setTituloPagarBaixaModel(model);

            forma.getForma().getPk().setOrganizacao(forma.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().save(forma);

            if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {

                

                TesourariaDebitoModel tesourariaDebitoModel = new TesourariaDebitoModel();

                tesourariaDebitoModel.setPk(new PKModel());
                tesourariaDebitoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
                tesourariaDebitoModel.getPk().setId(PempecKeyGenerator.generateKey());
                tesourariaDebitoModel.setNumeroDocumento(PempecKeyGenerator.generateNumeroDocumentoTesourariaDebito());

                tesourariaDebitoModel.setTituloPagarBaixaModel(model);
                tesourariaDebitoModel.setDataRegistro(Controller.getDataServidorBD());
                tesourariaDebitoModel.setDataMovimento(titulo.getDataPagamento());
                tesourariaDebitoModel.setDataContabil(titulo.getDataPagamento());
                tesourariaDebitoModel.setCedenteModel(model.getTitulo().getCedente());
                tesourariaDebitoModel.setDescricao("TÍT " + model.getTitulo().getNumeroDocumento() + " " + model.getTitulo().getDescricao());
                tesourariaDebitoModel.setValorNominal(forma.getValor());
                tesourariaDebitoModel.setUsuario(Controller.getUsuarioLogado());
                tesourariaDebitoModel.setTipoLancamento("D");
                tesourariaDebitoModel.setResponsavel(model.getResponsavel());
                tesourariaDebitoModel.setMovimentoDiarioModel(registroMovimento("Lanç. Tesouraria.", tesourariaDebitoModel.getNumeroDocumento(), "Pagto título em espécie", forma.getValor(), "Lançado"));

                if (tesourariaDebitoModel.getMovimentoDiarioModel() != null) {

                    MovimentoDiarioModel mov = tesourariaDebitoModel.getMovimentoDiarioModel();

                    HibernateUtil.getCurrentSession().save(mov);

                }

                historicoModel.getPk().setId(Constantes.HISTORICO_TESOURARIA_ESPECIE_PAGO);

                tesourariaDebitoModel.setHistorico(historicoModel);

                HibernateUtil.getCurrentSession().save(tesourariaDebitoModel);
                
                //22/01/14
                titulo.setDescricao(dscOriginal + " FP ESP");
                //Alterando o título
                HibernateUtil.getCurrentSession().merge(titulo);

            }

        }

        if (model.getDeducoes() != null) {

            for (TituloPagarBaixaDeducaoModel deducao : model.getDeducoes()) {

                deducao.setTituloPagarBaixaModel(model);

                HibernateUtil.getCurrentSession().save(deducao);

                valorPagar -= deducao.getValor();

            }

        }

        if (model.getAcrescimos() != null) {

            for (TituloPagarBaixaAcrescimoModel acrescimo : model.getAcrescimos()) {

                acrescimo.setTituloPagarBaixaModel(model);

                HibernateUtil.getCurrentSession().save(acrescimo);

                valorPagar += acrescimo.getValor();

            }

        }

        if (model.getTituloPagarBaixaCheque() != null) {

            for (TituloPagarBaixaChequeModel cheque : model.getTituloPagarBaixaCheque()) {

                cheque.setTituloPagarBaixa(model);

                cheque.getTipoOperacaoBancaria().getPk().setOrganizacao(cheque.getPk().getOrganizacao());

                cheque.getContaBancariaCheque().getPk().setOrganizacao(cheque.getPk().getOrganizacao());

                cheque.setMovimentoDiarioModel(registroMovimento("Lanç.Cheque.", model.getTitulo().getNumeroDocumento(), " Cheque " + cheque.getContaBancariaCheque().getNumeroCheque() + " utilizado para pagto título " + model.getTitulo().getNumeroDocumento(), cheque.getValor(), "Lançado"));

                

                if (cheque.getMovimentoDiarioModel() != null) {

                    MovimentoDiarioModel mov = cheque.getMovimentoDiarioModel();
                    HibernateUtil.getCurrentSession().save(mov);
                }

                HibernateUtil.getCurrentSession().update(cheque.getContaBancariaCheque());
                HibernateUtil.getCurrentSession().save(cheque);
                //22/01/14 SO PEGA O ULTIMO CHEQUE
                titulo.setDescricao(dscOriginal + " FP CH " + cheque.getContaBancariaCheque().getNumeroCheque()
                        + " CTA " + cheque.getContaBancariaCheque().getContaBancaria().getConta());
                //Alterando o título
                HibernateUtil.getCurrentSession().merge(titulo);
                
            }
            
            

        }

        if (model.getTituloPagarBaixaInternet() != null) {

            for (TituloPagarBaixaInternetModel internet : model.getTituloPagarBaixaInternet()) {

                internet.setTituloPagarBaixa(model);

                internet.getContaBancaria().getPk().setOrganizacao(internet.getPk().getOrganizacao());

                internet.getTipoOperacaoBancaria().getPk().setOrganizacao(internet.getPk().getOrganizacao());

                HibernateUtil.getCurrentSession().save(internet);

                // TODO Adicionando na tabela conta_bancaria_debito
                ContaBancariaDebitoModel bancariaDebitoModel = new ContaBancariaDebitoModel();

                bancariaDebitoModel.setPk(new PKModel());
                bancariaDebitoModel.getPk().setId(PempecKeyGenerator.generateKey());
                bancariaDebitoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
                bancariaDebitoModel.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaDebito());

                bancariaDebitoModel.setContaBancaria(internet.getContaBancaria());
                bancariaDebitoModel.setContaBancariaCheque(null);
                bancariaDebitoModel.setDataMovimento(internet.getDataOperacao());
                bancariaDebitoModel.setDataRegistro(model.getDataRegistro());
                bancariaDebitoModel.setDescricao(" TÍTULO Nº " + model.getTitulo().getNumeroDocumento() + " " + model.getTitulo().getDescricao());
                bancariaDebitoModel.setObservacao(internet.getDetalhamento());
                bancariaDebitoModel.setResponsavel(model.getResponsavel());
                bancariaDebitoModel.setTipoLancamento("D");
                bancariaDebitoModel.setTipoOperacaoBancaria(internet.getTipoOperacaoBancaria());
                bancariaDebitoModel.setTituloPagar(titulo);
                bancariaDebitoModel.setUsuario(model.getUsuario());
                bancariaDebitoModel.setValor(internet.getValor());

                bancariaDebitoModel.setMovimentoDiarioModel(registroMovimento("Lanç.Conta Banc.", internet.getTituloPagarBaixa().getTitulo().getNumeroDocumento(), " Conta " + bancariaDebitoModel.getContaBancaria().getConta() + " Titular " + bancariaDebitoModel.getContaBancaria().getTitular(), internet.getValor(), "Lançado"));

                HibernateUtil.getCurrentSession().save(bancariaDebitoModel);

                //22/01/14
                titulo.setDescricao(dscOriginal + " FP INT  CTA " + internet.getContaBancaria().getConta());
                //Alterando o título
                HibernateUtil.getCurrentSession().merge(titulo);
                
                if (bancariaDebitoModel.getMovimentoDiarioModel() != null) {

                    MovimentoDiarioModel mov = bancariaDebitoModel.getMovimentoDiarioModel();
                    HibernateUtil.getCurrentSession().save(mov);
                }

            }

        }

        if (model.getMovimentoDiarioModel() != null) {
            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        HibernateUtil.getCurrentSession().save(model);

        //Tratamento para quando o titulo for pago Parcial
        if (model.getTipoBaixa().equals(Constantes.TIPO_BAIXA_TITULO_PARCIAL)) {

            //Gerar um novo título.
            //Resolver depois a questão do campo adiantamento. Criar coluna.
            TituloPagarModel clone = null;

            try {

                clone = (TituloPagarModel) BeanUtils.cloneBean(titulo);

                clone.setPk(new PKModel());

                clone.getPk().setOrganizacao(titulo.getPk().getOrganizacao());

                clone.getPk().setId(PempecKeyGenerator.generateKey());

                //Setando o Titulo Pai
                clone.setTituloAnterior(titulo);

                //Re-fracionando a Parcela.
                if (clone.getParcela().contains("/")) {

                    String[] auxParcela = clone.getParcela().split("/");

                    if (auxParcela.length > 2) {

                        Integer novoNumero = PempecParse.stringToInteger(auxParcela[0]);

                        clone.setParcela(++novoNumero + " / " + auxParcela[1].trim() + " / " + auxParcela[2].trim());

                    } else {

                        clone.setParcela("1 / " + clone.getParcela());

                    }

                } else {

                    clone.setParcela("1 / " + clone.getParcela());

                }

                //Reajustando o Novo numero do Documento
                if (clone.getParcela().contains("/")) {

                    StringTokenizer tokenizer = new StringTokenizer(clone.getParcela(), "/");

                    StringBuilder numeroDoc = new StringBuilder();

                    numeroDoc.append(clone.getNumeroDocumento());
                    numeroDoc.append("0");

                    while (tokenizer.hasMoreTokens()) {
                        numeroDoc.append(tokenizer.nextToken().trim());
                    }

                    clone.setNumeroDocumento(numeroDoc.toString().trim());

                } else {

                    clone.setNumeroDocumento(clone.getNumeroDocumento() + "0" + clone.getParcela());

                }

                //Reajustando o novo valor do título - Considerando Acrescimos e Deducoes
                clone.setValorNominal(valorPagar - model.getValorPago());

                clone.setUsuario(model.getUsuario());

                clone.setDataRegistro(model.getDataRegistro());

                //Ajustando o novo status do título - Ver se vai ficar como titulo novo.                
                //No qual acredito que este título nao possa ser excluído.
                clone.getStatus().getPk().setId(Constantes.STATUS_TITULO_NOVO);

                clone.setDataPagamento(null);

                //Reajustando a data de previsão cartorio e novo vencimento.
                if (model.getNovaDataVencimento() != null) {

                    clone.setDataVencimento(model.getNovaDataVencimento());

                    clone.setPrevisaoCartorio(PempecUtil.addMonthDate(model.getNovaDataVencimento(), 1));

                }

                if (clone.getMovimentoDiarioModel() != null && clone.getMovimentoDiarioModel().getCodigo() != null) {

                    MovimentoDiarioModel mov = clone.getMovimentoDiarioModel();
                    mov.setObservacao("Titulo Clonado por Pagto parcial");
                    HibernateUtil.getCurrentSession().save(mov);

                }

                //Persistindo novo título
                HibernateUtil.getCurrentSession().save(clone);

                if (titulo.getCollCentroCustosRateio() != null && !titulo.getCollCentroCustosRateio().isEmpty()) {

                    int qtdRateioCC = titulo.getCollCentroCustosRateio().size();

                    for (TituloPagarRateioCCModel tituloPagarRateioCCModel : titulo.getCollCentroCustosRateio()) {

                        TituloPagarRateioCCModel cModel = new TituloPagarRateioCCModel();

                        cModel.setPk(new PKModel());
                        cModel.getPk().setId(PempecKeyGenerator.generateKey());
                        cModel.getPk().setOrganizacao(Controller.getOrganizacao());
                        cModel.setCentroCustoModel(tituloPagarRateioCCModel.getCentroCustoModel());
                        cModel.setTituloPagarModel(clone);
                        cModel.setValor(clone.getValorNominal() / qtdRateioCC);

                        HibernateUtil.getCurrentSession().save(cModel);

                    }

                }

                if (titulo.getCollHistoricosRateio() != null && !titulo.getCollHistoricosRateio().isEmpty()) {

                    int qtdRateioHist = titulo.getCollHistoricosRateio().size();

                    for (TituloPagarRateioHistoricoModel tituloPagarRateioHistoricoModel : titulo.getCollHistoricosRateio()) {

                        TituloPagarRateioHistoricoModel cModel = new TituloPagarRateioHistoricoModel();

                        cModel.setPk(new PKModel());
                        cModel.getPk().setId(PempecKeyGenerator.generateKey());
                        cModel.getPk().setOrganizacao(Controller.getOrganizacao());
                        cModel.setHistoricoModel(tituloPagarRateioHistoricoModel.getHistoricoModel());
                        cModel.setTituloPagarModel(clone);
                        cModel.setValor(clone.getValorNominal() / qtdRateioHist);

                        HibernateUtil.getCurrentSession().save(cModel);

                    }

                }

            } catch (Exception ex) {
                throw new SystemException(ex.getMessage());
            }

        }

        Repopulador.repopulador();

    }

    public TituloPagarBaixaModel consultarPorPk(TituloPagarBaixaModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("titulo", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("lotePagamentoModel", FetchMode.JOIN);

        criteria.setFetchMode("formasPagamento", FetchMode.JOIN);
        criteria.setFetchMode("deducoes", FetchMode.JOIN);
        criteria.setFetchMode("acrescimos", FetchMode.JOIN);

        criteria.setFetchMode("tituloPagarBaixaCheque", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagarBaixaInternet", FetchMode.JOIN);

//        Criteria subCriteriaCH = criteria.createCriteria("tituloPagarBaixaCheque");
//        subCriteriaCH.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
//        subCriteriaCH.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
//
//        Criteria subCriteriaWWW = criteria.createCriteria("tituloPagarBaixaInternet");
//        subCriteriaWWW.setFetchMode("contaBancaria", FetchMode.JOIN);
//        subCriteriaWWW.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
//        subCriteriaWWW.setFetchMode("bancoDestino", FetchMode.JOIN);
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloPagarBaixaModel) criteria.uniqueResult();

    }

    public List<TituloPagarBaixaModel> obterPorFiltro(TituloPagarBaixaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaModel.class);

        if (model.getTitulo().getDescricao() != null && !model.getTitulo().getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getTitulo().getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("descricao"));

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
    public List<TituloPagarBaixaModel> obterBaixasExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaModel.class);

        Criteria subCriteria = criteria.createCriteria("titulo");

        subCriteria.add(Restrictions.between("dataPagamento", dataInicial, dataFinal));
        subCriteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        //comentado por ranan
        // o titulo provisionado pode ter sido exportado, mas, aqui eu quero a baixa dele
        // nao importa tb o titulo anterior
        //subCriteria.add(Restrictions.isNull("tituloAnterior.pk.id"));
        //subCriteria.add(Restrictions.isNull("loteContabil.pk.id"));
        subCriteria.setFetchMode("centroCusto", FetchMode.JOIN);
        subCriteria.setFetchMode("cedente", FetchMode.JOIN);
        subCriteria.setFetchMode("historico", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        subCriteria.setFetchMode("collCentroCustosRateio", FetchMode.JOIN);
        subCriteria.setFetchMode("collHistoricosRateio", FetchMode.JOIN);

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        criteria.setFetchMode("centroCusto", FetchMode.JOIN);

        criteria.setFetchMode("formasPagamento", FetchMode.JOIN);

        criteria.setFetchMode("tituloPagarBaixaCheque", FetchMode.JOIN);

        criteria.setFetchMode("tituloPagarBaixaInternet", FetchMode.JOIN);

        criteria.setFetchMode("deducoes", FetchMode.JOIN);

        criteria.setFetchMode("acrescimos", FetchMode.JOIN);

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

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
    public List<TituloPagarBaixaModel> obterBaixasExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaModel.class);

        Criteria subCriteria = criteria.createCriteria("titulo");

        subCriteria.add(Restrictions.between("dataPagamento", dataInicial, dataFinal));
        subCriteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        //comentado por ranan
        // o titulo provisionado pode ter sido exportado, mas, aqui eu quero a baixa dele
        //subCriteria.add(Restrictions.isNull("tituloAnterior.pk.id"));
        //subCriteria.add(Restrictions.isNull("loteContabil.pk.id"));
        subCriteria.setFetchMode("cedente", FetchMode.JOIN);
        subCriteria.setFetchMode("historico", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        subCriteria.setFetchMode("collCentroCustosRateio", FetchMode.JOIN);
        subCriteria.setFetchMode("collHistoricosRateio", FetchMode.JOIN);

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        criteria.setFetchMode("centroCusto", FetchMode.JOIN);

        criteria.setFetchMode("formasPagamento", FetchMode.JOIN);

        criteria.setFetchMode("tituloPagarBaixaCheque", FetchMode.JOIN);

        criteria.setFetchMode("tituloPagarBaixaInternet", FetchMode.JOIN);

        criteria.setFetchMode("deducoes", FetchMode.JOIN);

        criteria.setFetchMode("acrescimos", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        return criteria.list();

    }

    public List<TituloPagarBaixaModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaModel.class).add(
                        Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloPagarBaixaModel> coll)
            throws SystemException {

        for (TituloPagarBaixaModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador();

    }

    public TituloPagarBaixaModel consultarPorTemplate(TituloPagarBaixaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaModel.class);

        if (model.getTitulo() != null && model.getTitulo().getPk() != null && !model.getTitulo().getPk().getId().isEmpty()) {

            criteria.add(Restrictions.eq("titulo.pk.id", model.getTitulo().getPk().getId()));

        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("titulo", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("lotePagamentoModel", FetchMode.JOIN);

        criteria.setFetchMode("formasPagamento", FetchMode.JOIN);
        criteria.setFetchMode("deducoes", FetchMode.JOIN);
        criteria.setFetchMode("acrescimos", FetchMode.JOIN);

        criteria.setFetchMode("tituloPagarBaixaCheque", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagarBaixaInternet", FetchMode.JOIN);

//        Criteria subCriteriaCH = criteria.createCriteria("tituloPagarBaixaCheque");
//        subCriteriaCH.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
//        subCriteriaCH.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
//
//        Criteria subCriteriaWWW = criteria.createCriteria("tituloPagarBaixaInternet");
//        subCriteriaWWW.setFetchMode("contaBancaria", FetchMode.JOIN);
//        subCriteriaWWW.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
//        subCriteriaWWW.setFetchMode("bancoDestino", FetchMode.JOIN);
        return (TituloPagarBaixaModel) criteria.uniqueResult();

    }

    public TituloPagarBaixaModel consultarPorTitulo(TituloPagarModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("titulo.pk.id", model.getPk().getId()));
        }

        criteria.setFetchMode("formasPagamento", FetchMode.JOIN);
        criteria.setFetchMode("deducoes", FetchMode.JOIN);
        criteria.setFetchMode("acrescimos", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.setFetchMode("tituloPagarBaixaCheque", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagarBaixaInternet", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloPagarBaixaModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(TituloPagarBaixaModel model, TituloPagarModel titulo)
            throws SystemException {

        if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        Query query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.ContaBancariaDebitoModel where ID_TITULO_PAGAR = :titulo and id_organizacao = :organizacao");

        query.setString("titulo", titulo.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TesourariaDebitoModel where ID_TITULO_PAGAR_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.ContaBancariaChequeModel set valor = 0.0, "
                + " ID_TIPO_STATUS = (select case when (c.qtdImpressao > 0) then '" + Constantes.STATUS_CHEQUE_NOVO + "' else '" + Constantes.STATUS_CHEQUE_NOVO + "' end from br.com.pempec.finance.models.ContaBancariaChequeModel c where c.pk.id = (select t.contaBancariaCheque.pk.id from br.com.pempec.finance.models.TituloPagarBaixaChequeModel t where ID_TITULO_PAGAR_BAIXA = :baixa and ID_ORGANIZACAO = :organizacao) and ID_ORGANIZACAO = :organizacao) "
                + "where pk.id in ( "
                + " select t.contaBancariaCheque.pk.id from br.com.pempec.finance.models.TituloPagarBaixaChequeModel t where ID_TITULO_PAGAR_BAIXA = :baixa and ID_ORGANIZACAO = :organizacao "
                + " ) "
                + " and pk.organizacao.id = :organizacao ");
        
       /* query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.ContaBancariaChequeModel set valor = 0.0, "
                + " ID_TIPO_STATUS = (select case when (c.qtdImpressao > 0) then '" + Constantes.STATUS_CHEQUE_EXCLUIDO + "' else '" + Constantes.STATUS_CHEQUE_NOVO + "' end from br.com.pempec.finance.models.ContaBancariaChequeModel c where c.pk.id = (select t.contaBancariaCheque.pk.id from br.com.pempec.finance.models.TituloPagarBaixaChequeModel t where ID_TITULO_PAGAR_BAIXA = :baixa and ID_ORGANIZACAO = :organizacao) and ID_ORGANIZACAO = :organizacao) "
                + "where pk.id in ( "
                + " select t.contaBancariaCheque.pk.id from br.com.pempec.finance.models.TituloPagarBaixaChequeModel t where ID_TITULO_PAGAR_BAIXA = :baixa and ID_ORGANIZACAO = :organizacao "
                + " ) "
                + " and pk.organizacao.id = :organizacao ");*/
        
        

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel where ID_TITULO_PAGAR_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel where ID_TITULO_PAGAR_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaChequeModel where ID_TITULO_PAGAR_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaInternetModel where ID_TITULO_PAGAR_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel where ID_TITULO_PAGAR_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaModel where ID_TITULO_PAGAR_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.TituloPagarModel set ID_TIPO_STATUS = '" + Constantes.STATUS_TITULO_NOVO + "', DATA_PAGAMENTO = NULL where ID_TITULO_PAGAR = :titulo and id_organizacao = :organizacao");

        query.setString("titulo", titulo.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        Repopulador.repopulador();

    }

    public List<TituloPagarBaixaModel> obterTitulosExportados(LoteContabilModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaModel.class);

        criteria.add(Restrictions.eq("loteContabil.pk.id", model.getPk().getId()));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("titulo");

        subCriteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        subCriteria.add(Restrictions.isNull("tituloAnterior.pk.id"));

        subCriteria.setFetchMode("cedente", FetchMode.JOIN);
        subCriteria.setFetchMode("historico", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        subCriteria.setFetchMode("collCentroCustosRateio", FetchMode.JOIN);
        subCriteria.setFetchMode("collHistoricosRateio", FetchMode.JOIN);

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        criteria.setFetchMode("centroCusto", FetchMode.JOIN);

        criteria.setFetchMode("formasPagamento", FetchMode.JOIN);

        criteria.setFetchMode("deducoes", FetchMode.JOIN);

        criteria.setFetchMode("acrescimos", FetchMode.JOIN);

        Criteria subCriteriaCH = criteria.createCriteria("tituloPagarBaixaCheque");
        subCriteriaCH.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
        subCriteriaCH.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);

        Criteria subCriteriaWWW = criteria.createCriteria("tituloPagarBaixaInternet");
        subCriteriaWWW.setFetchMode("contaBancaria", FetchMode.JOIN);
        subCriteriaWWW.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        subCriteriaWWW.setFetchMode("bancoDestino", FetchMode.JOIN);

        return criteria.list();
    }

    @HibernateTransaction
    public void salvarLote(LotePagamentoTituloModel lote, Collection<TituloPagarBaixaModel> collBaixa, Collection<TituloPagarModel> collTitulo) throws SystemException {

        //Salvando o Lote.
        HibernateUtil.getCurrentSession().save(lote);

        //Alterando os títulos
        for (TituloPagarModel tituloPagarModel : collTitulo) {

            tituloPagarModel.setLotePagamentoModel(lote);

            HibernateUtil.getCurrentSession().merge(tituloPagarModel);

        }

        for (TituloPagarBaixaModel model : collBaixa) {

            for (TituloPagarBaixaFormaPagamentoModel forma : model.getFormasPagamento()) {

                TituloPagarBaixaFormaPagamentoModel aux = new TituloPagarBaixaFormaPagamentoModel();

                aux.setTituloPagarBaixaModel(model);

                aux.setForma(forma.getForma());

                aux.setPk(new PKModel());

                aux.getPk().setId(PempecKeyGenerator.generateKey());

                aux.getPk().setOrganizacao(Controller.getOrganizacao());

                aux.setValor(model.getTitulo().getValorNominal());

                HibernateUtil.getCurrentSession().save(aux);

                if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {

                    TesourariaDebitoModel tesourariaDebitoModel = new TesourariaDebitoModel();

                    tesourariaDebitoModel.setPk(new PKModel());
                    tesourariaDebitoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
                    tesourariaDebitoModel.getPk().setId(PempecKeyGenerator.generateKey());
                    tesourariaDebitoModel.setNumeroDocumento(PempecKeyGenerator.generateNumeroDocumentoTesourariaDebito());

                    tesourariaDebitoModel.setTituloPagarBaixaModel(model);
                    tesourariaDebitoModel.setDataRegistro(Controller.getDataServidorBD());
                    tesourariaDebitoModel.setDataMovimento(model.getTitulo().getDataPagamento());
                    tesourariaDebitoModel.setDataContabil(model.getTitulo().getDataPagamento());
                    tesourariaDebitoModel.setCedenteModel(model.getTitulo().getCedente());
                    tesourariaDebitoModel.setDescricao(" TÍTULO Nº " + model.getTitulo().getNumeroDocumento());
                    tesourariaDebitoModel.setValorNominal(model.getTitulo().getValorNominal());
                    tesourariaDebitoModel.setUsuario(Controller.getUsuarioLogado());
                    tesourariaDebitoModel.setTipoLancamento("D");
                    tesourariaDebitoModel.setResponsavel(model.getResponsavel());
                    tesourariaDebitoModel.setObservacao("Titulo pago atraves do lote " + lote.getLote());
                    tesourariaDebitoModel.setMovimentoDiarioModel(registroMovimento("Lanç. Tesouraria.", tesourariaDebitoModel.getNumeroDocumento(), "Pagto título em espécie", aux.getValor(), "Lançado"));

                    if (model.getMovimentoDiarioModel() != null) {
                        tesourariaDebitoModel.setMovimentoDiarioModel(model.getMovimentoDiarioModel());
                    }

                    HistoricoModel historicoModel = new HistoricoModel();
                    historicoModel.setPk(new PKModel());
                    historicoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

                    historicoModel.getPk().setId(Constantes.HISTORICO_TESOURARIA_ESPECIE_PAGO);

                    tesourariaDebitoModel.setHistorico(historicoModel);

                    HibernateUtil.getCurrentSession().save(tesourariaDebitoModel);

                }

            }

            if (model.getTituloPagarBaixaCheque() != null) {

                for (TituloPagarBaixaChequeModel cheque : model.getTituloPagarBaixaCheque()) {

                    TituloPagarBaixaChequeModel aux = new TituloPagarBaixaChequeModel();

                    aux.setPk(new PKModel());

                    aux.getPk().setId(PempecKeyGenerator.generateKey());

                    aux.getPk().setOrganizacao(Controller.getOrganizacao());

                    aux.setTituloPagarBaixa(model);

                    aux.setValor(model.getTitulo().getValorNominal());

                    aux.setMovimentoDiarioModel(registroMovimento("Lanç.Cheque.", model.getTitulo().getNumeroDocumento(), " Cheque " + cheque.getContaBancariaCheque().getNumeroCheque() + " utilizado para pagto título " + model.getTitulo().getNumeroDocumento(), cheque.getValor(), "Lançado"));

                    aux.setObservacao("Titulo pago atraves do lote " + lote.getLote());

                    aux.setContaBancariaCheque(cheque.getContaBancariaCheque());

                    aux.setTipoOperacaoBancaria(cheque.getTipoOperacaoBancaria());

                    if (aux.getMovimentoDiarioModel() != null) {

                        MovimentoDiarioModel mov = aux.getMovimentoDiarioModel();
                        HibernateUtil.getCurrentSession().save(mov);
                    }

                    HibernateUtil.getCurrentSession().update(aux.getContaBancariaCheque());
                    HibernateUtil.getCurrentSession().save(aux);

                }

            }

            if (model.getTituloPagarBaixaInternet() != null) {

                for (TituloPagarBaixaInternetModel internet : model.getTituloPagarBaixaInternet()) {

                    TituloPagarBaixaInternetModel aux = new TituloPagarBaixaInternetModel();

                    aux.setPk(new PKModel());

                    aux.getPk().setId(PempecKeyGenerator.generateKey());

                    aux.getPk().setOrganizacao(Controller.getOrganizacao());

                    aux.setTituloPagarBaixa(model);

                    aux.setValor(model.getTitulo().getValorNominal());

                    aux.setAgenciaDestino(internet.getAgenciaDestino());

                    aux.setBancoDestino(internet.getBancoDestino());

                    aux.setContaBancaria(internet.getContaBancaria());

                    aux.setContaDestino(internet.getContaDestino());

                    aux.setCpfCpnjCorrentista(internet.getCpfCpnjCorrentista());

                    aux.setDataOperacao(internet.getDataOperacao());

                    aux.setDetalhamento(internet.getDetalhamento());

                    aux.setNomeCorrentista(internet.getNomeCorrentista());

                    aux.setPersonalidadeCorrentista(internet.getPersonalidadeCorrentista());

                    aux.setTipoOperacaoBancaria(internet.getTipoOperacaoBancaria());

                    HibernateUtil.getCurrentSession().save(aux);

                    // TODO Adicionando na tabela conta_bancaria_debito
                    ContaBancariaDebitoModel bancariaDebitoModel = new ContaBancariaDebitoModel();

                    bancariaDebitoModel.setPk(new PKModel());
                    bancariaDebitoModel.getPk().setId(PempecKeyGenerator.generateKey());
                    bancariaDebitoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
                    bancariaDebitoModel.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaDebito());

                    bancariaDebitoModel.setContaBancaria(aux.getContaBancaria());
                    bancariaDebitoModel.setContaBancariaCheque(null);
                    bancariaDebitoModel.setDataMovimento(aux.getDataOperacao());
                    bancariaDebitoModel.setDataRegistro(model.getDataRegistro());
                    bancariaDebitoModel.setDescricao("PGTO TIT. " + model.getTitulo().getNumeroDocumento() + " - LOTE: " + lote.getLote());
                    bancariaDebitoModel.setObservacao(aux.getDetalhamento());
                    bancariaDebitoModel.setResponsavel(model.getResponsavel());
                    bancariaDebitoModel.setTipoLancamento("D");
                    bancariaDebitoModel.setTipoOperacaoBancaria(aux.getTipoOperacaoBancaria());
                    bancariaDebitoModel.setTituloPagar(model.getTitulo());
                    bancariaDebitoModel.setUsuario(model.getUsuario());
                    bancariaDebitoModel.setValor(aux.getValor());

                    bancariaDebitoModel.setObservacao("Titulo pago atraves do lote " + lote.getLote());
                    bancariaDebitoModel.setMovimentoDiarioModel(registroMovimento("Lanç.Conta Banc.", model.getTitulo().getNumeroDocumento(), " Conta " + bancariaDebitoModel.getContaBancaria().getConta() + " Titular " + bancariaDebitoModel.getContaBancaria().getTitular(), aux.getValor(), "Lançado"));

                    HibernateUtil.getCurrentSession().save(bancariaDebitoModel);

                    if (bancariaDebitoModel.getMovimentoDiarioModel() != null) {

                        MovimentoDiarioModel mov = bancariaDebitoModel.getMovimentoDiarioModel();
                        HibernateUtil.getCurrentSession().save(mov);
                    }

                }

            }

            if (model.getMovimentoDiarioModel() != null) {
                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
                mov.setObservacao("Baixa Titulo Pagar Lote");
                HibernateUtil.getCurrentSession().save(mov);

            }

            model.setValorPago(model.getTitulo().getValorNominal());

            model.setLotePagamentoModel(lote);

            HibernateUtil.getCurrentSession().save(model);

        }

        Repopulador.repopulador();

    }

    private MovimentoDiarioModel registroMovimento(
            String acao, String codigo, String descricao, Double valor, String status) {
        MovimentoDiarioModel movimentoDiarioModel = new MovimentoDiarioModel();
        movimentoDiarioModel.setPk(new PKModel());
        movimentoDiarioModel.getPk().setOrganizacao(Controller.getOrganizacao());
        movimentoDiarioModel.getPk().setId(PempecKeyGenerator.generateKey());
        movimentoDiarioModel.setDataRegistro(Controller.getDataServidorBD());
        movimentoDiarioModel.setAcao(acao);
        movimentoDiarioModel.setCodigo(codigo);
        movimentoDiarioModel.setObjeto(this.getClass().getSimpleName());
        movimentoDiarioModel.setDescricaoObjeto(descricao);
        movimentoDiarioModel.setValorOperacao(valor);
        movimentoDiarioModel.setStatusFinalObjeto(status);
        movimentoDiarioModel.setObservacao("Lançam. Pagto de Titulo.");
        if (movimentoDiarioModel.getNumeroMovimento() == null) {
            movimentoDiarioModel.setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());
        }
        return movimentoDiarioModel;

    }
}
