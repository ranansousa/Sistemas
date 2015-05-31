package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloReceberBaixaDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LoteRecebimentoTituloModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloReceberBaixaInternetModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.models.TituloReceberRateioHistoricoModel;
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
public class TituloReceberBaixaDAO implements TituloReceberBaixaDAOIf {

    @HibernateTransaction
    public void salvar(TituloReceberBaixaModel model, TituloReceberModel titulo) throws SystemException {

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

        for (TituloReceberBaixaFormaPagamentoModel forma : model.getFormasPagamento()) {

            forma.setTituloReceberBaixaModel(model);

            forma.getForma().getPk().setOrganizacao(forma.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().save(forma);

            if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {

                TesourariaCreditoModel tesourariaCreditoModel = new TesourariaCreditoModel();

                tesourariaCreditoModel.setPk(new PKModel());
                tesourariaCreditoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
                tesourariaCreditoModel.getPk().setId(PempecKeyGenerator.generateKey());
                tesourariaCreditoModel.setNumeroDocumento(PempecKeyGenerator.generateNumeroDocumentoTesourariaCredito());

                tesourariaCreditoModel.setTituloReceberBaixaModel(model);
                tesourariaCreditoModel.setDataRegistro(Controller.getDataServidorBD());
                tesourariaCreditoModel.setDataMovimento(titulo.getDataPagamento());
                tesourariaCreditoModel.setDataContabil(titulo.getDataPagamento());
                tesourariaCreditoModel.setSacadoModel(model.getTitulo().getSacado());
                tesourariaCreditoModel.setDescricao("TÍT " + model.getTitulo().getNumeroDocumento() + " " + model.getTitulo().getDescricao());
                tesourariaCreditoModel.setValorNominal(forma.getValor());
                tesourariaCreditoModel.setUsuario(Controller.getUsuarioLogado());
                tesourariaCreditoModel.setTipoLancamento("C");
                tesourariaCreditoModel.setResponsavel(model.getResponsavel());
                tesourariaCreditoModel.setMovimentoDiarioModel(registroMovimento("Lanç. Tesouraria.", tesourariaCreditoModel.getNumeroDocumento(), "Valor em espécie como pagto título", forma.getValor(), "Lançado"));

                historicoModel.getPk().setId(Constantes.HISTORICO_TESOURARIA_ESPECIE_RECEBIDO);

                tesourariaCreditoModel.setHistorico(historicoModel);

                if (tesourariaCreditoModel.getMovimentoDiarioModel() != null) {

                    MovimentoDiarioModel mov = tesourariaCreditoModel.getMovimentoDiarioModel();

                    HibernateUtil.getCurrentSession().save(mov);
                }

                HibernateUtil.getCurrentSession().save(tesourariaCreditoModel);
                
                //22/01/14
                titulo.setDescricao(dscOriginal + " FP ESP");
                //Alterando o título
                HibernateUtil.getCurrentSession().merge(titulo);

            }


        }

        for (TituloReceberBaixaDeducaoModel deducao : model.getDeducoes()) {

            deducao.setTituloReceberBaixaModel(model);

            HibernateUtil.getCurrentSession().save(deducao);

            valorPagar -= deducao.getValor();

        }

        for (TituloReceberBaixaAcrescimoModel acrescimo : model.getAcrescimos()) {

            acrescimo.setTituloReceberBaixaModel(model);

            HibernateUtil.getCurrentSession().save(acrescimo);

            valorPagar += acrescimo.getValor();


        }

        for (TituloReceberBaixaChequeModel cheque : model.getTituloReceberBaixaCheque()) {

            cheque.setTituloReceberBaixa(model);
            HibernateUtil.getCurrentSession().save(cheque);

            TesourariaCreditoModel tesourariaCreditoModel = new TesourariaCreditoModel();

            tesourariaCreditoModel.setPk(new PKModel());
            tesourariaCreditoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
            tesourariaCreditoModel.getPk().setId(PempecKeyGenerator.generateKey());
            tesourariaCreditoModel.setNumeroDocumento(PempecKeyGenerator.generateNumeroDocumentoTesourariaCredito());

            tesourariaCreditoModel.setTituloReceberBaixaChequeModel(cheque);
            tesourariaCreditoModel.setDataRegistro(Controller.getDataServidorBD());
            tesourariaCreditoModel.setDataMovimento(titulo.getDataPagamento());
            tesourariaCreditoModel.setDataContabil(titulo.getDataPagamento());
            tesourariaCreditoModel.setSacadoModel(model.getTitulo().getSacado());
            tesourariaCreditoModel.setDescricao("CH Ref ao título : > " + model.getTitulo().getNumeroDocumento() + " " + model.getTitulo().getDescricao());
            tesourariaCreditoModel.setValorNominal(cheque.getValor());
            tesourariaCreditoModel.setUsuario(Controller.getUsuarioLogado());
            tesourariaCreditoModel.setTipoLancamento("C");
            tesourariaCreditoModel.setResponsavel(model.getResponsavel());

            tesourariaCreditoModel.setMovimentoDiarioModel(registroMovimento("Lanç. Tesouraria.", tesourariaCreditoModel.getNumeroDocumento(), " Cheque " + cheque.getNumeroCheque() + " recebido como pagto título " + model.getTitulo().getNumeroDocumento(), cheque.getValor(), "Lançado"));

            historicoModel.getPk().setId(Constantes.HISTORICO_TESOURARIA_CHEQUE_RECEBIDO);

            tesourariaCreditoModel.setHistorico(historicoModel);

            if (tesourariaCreditoModel.getMovimentoDiarioModel() != null) {

                MovimentoDiarioModel mov = tesourariaCreditoModel.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);
            }

            HibernateUtil.getCurrentSession().save(tesourariaCreditoModel);
            //22/01/14 SO PEGA O ULTIMO CHEQUE
                titulo.setDescricao(dscOriginal + " FP CH " + cheque.getNumeroCheque()
                        + " CTA " + cheque.getConta());
                //Alterando o título
                HibernateUtil.getCurrentSession().merge(titulo);

        }

        for (TituloReceberBaixaInternetModel internet : model.getTituloReceberBaixaInternet()) {

            internet.setTituloReceberBaixa(model);

            internet.getContaBancaria().getPk().setOrganizacao(internet.getPk().getOrganizacao());

            internet.getTipoOperacaoBancaria().getPk().setOrganizacao(internet.getPk().getOrganizacao());

            HibernateUtil.getCurrentSession().save(internet);

            ContaBancariaCreditoModel bancariaCreditoModel = new ContaBancariaCreditoModel();

            bancariaCreditoModel.setPk(new PKModel());
            bancariaCreditoModel.getPk().setId(PempecKeyGenerator.generateKey());
            bancariaCreditoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
            bancariaCreditoModel.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaCredito());

            bancariaCreditoModel.setContaBancaria(internet.getContaBancaria());
            bancariaCreditoModel.setDataMovimento(internet.getDataOperacao());
            bancariaCreditoModel.setDataRegistro(Controller.getDataServidorBD());
            bancariaCreditoModel.setDataContabil(internet.getDataOperacao());
            bancariaCreditoModel.setDescricao("TÍT " + model.getTitulo().getNumeroDocumento() + " " + model.getTitulo().getDescricao());
            bancariaCreditoModel.setObservacao("Crédito de Título");
            bancariaCreditoModel.setResponsavel(model.getResponsavel());
            bancariaCreditoModel.setTipoLancamento("C");
            bancariaCreditoModel.setTipoOperacaoBancaria(internet.getTipoOperacaoBancaria());
            bancariaCreditoModel.setUsuario(model.getUsuario());
            bancariaCreditoModel.setValor(internet.getValor());
            bancariaCreditoModel.setTituloReceber(internet.getTituloReceberBaixa().getTitulo());

            bancariaCreditoModel.setMovimentoDiarioModel(registroMovimento("Lanç.Conta Banc.", internet.getTituloReceberBaixa().getTitulo().getNumeroDocumento(), " Conta " + bancariaCreditoModel.getContaBancaria().getConta() + " Titular " + bancariaCreditoModel.getContaBancaria().getTitular(), internet.getValor(), "Lançado"));
            bancariaCreditoModel.getMovimentoDiarioModel().setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

            HibernateUtil.getCurrentSession().save(bancariaCreditoModel);
            
            //22/01/14
                titulo.setDescricao(dscOriginal + " FP INT  CTA " + internet.getContaBancaria().getConta());
                //Alterando o título
                HibernateUtil.getCurrentSession().merge(titulo);

            if (bancariaCreditoModel.getMovimentoDiarioModel() != null) {

                MovimentoDiarioModel mov = bancariaCreditoModel.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);
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

            TituloReceberModel clone = null;

            try {

                clone = (TituloReceberModel) BeanUtils.cloneBean(titulo);

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
                    mov.setObservacao("Título clonado por recebimento parcial");
                    HibernateUtil.getCurrentSession().save(mov);
                }

                //Persistindo novo título
                HibernateUtil.getCurrentSession().save(clone);

                if (titulo.getCollCentroCustosRateio() != null && !titulo.getCollCentroCustosRateio().isEmpty()) {

                    int qtdRateioCC = titulo.getCollCentroCustosRateio().size();

                    for (TituloReceberRateioCCModel tituloReceberRateioCCModel : titulo.getCollCentroCustosRateio()) {

                        TituloReceberRateioCCModel cModel = new TituloReceberRateioCCModel();

                        cModel.setPk(new PKModel());
                        cModel.getPk().setId(PempecKeyGenerator.generateKey());
                        cModel.getPk().setOrganizacao(Controller.getOrganizacao());
                        cModel.setCentroCustoModel(tituloReceberRateioCCModel.getCentroCustoModel());
                        cModel.setTituloReceberModel(clone);
                        cModel.setValor(clone.getValorNominal() / qtdRateioCC);

                        HibernateUtil.getCurrentSession().save(cModel);

                    }

                }

                if (titulo.getCollHistoricosRateio() != null && !titulo.getCollHistoricosRateio().isEmpty()) {

                    int qtdRateioHist = titulo.getCollHistoricosRateio().size();

                    for (TituloReceberRateioHistoricoModel tituloReceberRateioHistoricoModel : titulo.getCollHistoricosRateio()) {

                        TituloReceberRateioHistoricoModel cModel = new TituloReceberRateioHistoricoModel();

                        cModel.setPk(new PKModel());
                        cModel.getPk().setId(PempecKeyGenerator.generateKey());
                        cModel.getPk().setOrganizacao(Controller.getOrganizacao());
                        cModel.setHistoricoModel(tituloReceberRateioHistoricoModel.getHistoricoModel());
                        cModel.setTituloReceberModel(clone);
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

    public TituloReceberBaixaModel consultarPorPk(TituloReceberBaixaModel model)
            throws SystemException {
        return (TituloReceberBaixaModel) HibernateUtil.getCurrentSession().get(
                TituloReceberBaixaModel.class, model.getPk());
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
    public List<TituloReceberBaixaModel> obterBaixasExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaModel.class);

        Criteria subCriteria = criteria.createCriteria("titulo");

        subCriteria.add(Restrictions.between("dataPagamento", dataInicial, dataFinal));
        subCriteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        //comentado por ranan
        // o titulo provisionado pode ter sido exportado, mas, aqui eu quero a baixa dele

        //subCriteria.add(Restrictions.isNull("tituloAnterior.pk.id"));
        //subCriteria.add(Restrictions.isNull("loteContabil.pk.id"));

        subCriteria.setFetchMode("centroCusto", FetchMode.JOIN);
        subCriteria.setFetchMode("sacado", FetchMode.JOIN);
        subCriteria.setFetchMode("historico", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        subCriteria.setFetchMode("collCentroCustosRateio", FetchMode.JOIN);
        subCriteria.setFetchMode("collHistoricosRateio", FetchMode.JOIN);

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        criteria.setFetchMode("centroCusto", FetchMode.JOIN);

        criteria.setFetchMode("formasPagamento", FetchMode.JOIN);

        criteria.setFetchMode("tituloReceberBaixaInternet", FetchMode.JOIN);

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
    public List<TituloReceberBaixaModel> obterBaixasExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaModel.class);

        Criteria subCriteria = criteria.createCriteria("titulo");

        subCriteria.add(Restrictions.between("dataPagamento", dataInicial, dataFinal));
        subCriteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        //comentado por ranan
        // o titulo provisionado pode ter sido exportado, mas, aqui eu quero a baixa dele

        //subCriteria.add(Restrictions.isNull("tituloAnterior.pk.id"));
        //subCriteria.add(Restrictions.isNull("loteContabil.pk.id"));

        subCriteria.setFetchMode("sacado", FetchMode.JOIN);
        subCriteria.setFetchMode("historico", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        subCriteria.setFetchMode("collCentroCustosRateio", FetchMode.JOIN);
        subCriteria.setFetchMode("collHistoricosRateio", FetchMode.JOIN);

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        criteria.setFetchMode("centroCusto", FetchMode.JOIN);

        criteria.setFetchMode("formasPagamento", FetchMode.JOIN);

        criteria.setFetchMode("tituloReceberBaixaInternet", FetchMode.JOIN);

        criteria.setFetchMode("deducoes", FetchMode.JOIN);

        criteria.setFetchMode("acrescimos", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        return criteria.list();


    }

    public List<TituloReceberBaixaModel> obterPorFiltro(TituloReceberBaixaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaModel.class);

        if (model.getTitulo().getDescricao() != null && !model.getTitulo().getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getTitulo().getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<TituloReceberBaixaModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    public List<TituloReceberBaixaModel> obterTitulosExportados(LoteContabilModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaModel.class);

        criteria.add(Restrictions.eq("loteContabil.pk.id", model.getPk().getId()));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("titulo");

        subCriteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));
        subCriteria.add(Restrictions.isNull("tituloAnterior.pk.id"));
        subCriteria.setFetchMode("sacado", FetchMode.JOIN);
        subCriteria.setFetchMode("historico", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        subCriteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);
        subCriteria.setFetchMode("collCentroCustosRateio", FetchMode.JOIN);
        subCriteria.setFetchMode("collHistoricosRateio", FetchMode.JOIN);

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        criteria.setFetchMode("centroCusto", FetchMode.JOIN);

        criteria.setFetchMode("formasPagamento", FetchMode.JOIN);

        criteria.setFetchMode("tituloReceberBaixaInternet", FetchMode.JOIN);

        criteria.setFetchMode("deducoes", FetchMode.JOIN);

        criteria.setFetchMode("acrescimos", FetchMode.JOIN);

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloReceberBaixaModel> coll)
            throws SystemException {

        for (TituloReceberBaixaModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador();

    }

    public TituloReceberBaixaModel consultarPorTemplate(TituloReceberBaixaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaModel.class);

        if (model.getTitulo().getDescricao() != null && !model.getTitulo().getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getTitulo().getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("titulo", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return (TituloReceberBaixaModel) criteria.uniqueResult();

    }

    public TituloReceberBaixaModel consultarPorTitulo(TituloReceberModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("titulo.pk.id", model.getPk().getId()));
        }

        criteria.setFetchMode("formasPagamento", FetchMode.JOIN);
        criteria.setFetchMode("deducoes", FetchMode.JOIN);
        criteria.setFetchMode("acrescimos", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceberBaixaCheque", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceberBaixaInternet", FetchMode.JOIN);
        criteria.setFetchMode("titulo", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloReceberBaixaModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(TituloReceberBaixaModel model, TituloReceberModel titulo)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);

        }

        Query query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.ContaBancariaCreditoModel where ID_TITULO_RECEBER = :titulo and id_organizacao = :organizacao");

        query.setString("titulo", titulo.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TesourariaCreditoModel where ID_TITULO_RECEBER_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TesourariaCreditoModel where tituloReceberBaixaChequeModel.pk.id in ("
                + " select t.pk.id from br.com.pempec.finance.models.TituloReceberBaixaChequeModel t where ID_TITULO_RECEBER_BAIXA = :baixa and ID_ORGANIZACAO = :organizacao "
                + " ) "
                + " and pk.organizacao.id = :organizacao ");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel where ID_TITULO_RECEBER_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel where ID_TITULO_RECEBER_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaChequeModel where ID_TITULO_RECEBER_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaInternetModel where ID_TITULO_RECEBER_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel where ID_TITULO_RECEBER_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaModel where ID_TITULO_RECEBER_BAIXA = :baixa and id_organizacao = :organizacao");

        query.setString("baixa", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.TituloReceberModel set ID_TIPO_STATUS = '" + Constantes.STATUS_TITULO_NOVO + "', DATA_PAGAMENTO = NULL where ID_TITULO_RECEBER = :titulo and id_organizacao = :organizacao");

        query.setString("titulo", titulo.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void salvarLote(LoteRecebimentoTituloModel lote, Collection<TituloReceberBaixaModel> collBaixa, Collection<TituloReceberModel> collTitulo) throws SystemException {
        //Salvando o Lote.
        HibernateUtil.getCurrentSession().save(lote);

        HistoricoModel historicoModel = new HistoricoModel();
        historicoModel.setPk(new PKModel());
        

        //Alterando os títulos
        for (TituloReceberModel tituloReceberModel : collTitulo) {

            tituloReceberModel.setLoteRecebimentoTituloModel(lote);

            HibernateUtil.getCurrentSession().merge(tituloReceberModel);

        }

        for (TituloReceberBaixaModel model : collBaixa) {

            for (TituloReceberBaixaFormaPagamentoModel forma : model.getFormasPagamento()) {

                TituloReceberBaixaFormaPagamentoModel aux = new TituloReceberBaixaFormaPagamentoModel();

                aux.setTituloReceberBaixaModel(model);

                aux.setForma(forma.getForma());

                aux.setPk(new PKModel());

                aux.getPk().setId(PempecKeyGenerator.generateKey());

                aux.getPk().setOrganizacao(Controller.getOrganizacao());

                aux.setValor(model.getTitulo().getValorNominal());

                HibernateUtil.getCurrentSession().save(aux);
                
                
                //setando o ID do Historico 
                
                historicoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

                if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {

                    TesourariaCreditoModel tesourariaCreditoModel = new TesourariaCreditoModel();

                    tesourariaCreditoModel.setPk(new PKModel());
                    tesourariaCreditoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
                    tesourariaCreditoModel.getPk().setId(PempecKeyGenerator.generateKey());
                    tesourariaCreditoModel.setNumeroDocumento(PempecKeyGenerator.generateNumeroDocumentoTesourariaCredito());

                    tesourariaCreditoModel.setTituloReceberBaixaModel(model);
                    tesourariaCreditoModel.setDataRegistro(Controller.getDataServidorBD());
                    tesourariaCreditoModel.setDataMovimento(model.getTitulo().getDataPagamento());
                    tesourariaCreditoModel.setDataContabil(model.getTitulo().getDataPagamento());
                    tesourariaCreditoModel.setSacadoModel(model.getTitulo().getSacado());
                    tesourariaCreditoModel.setDescricao(" TÍTULO Nº " + model.getTitulo().getNumeroDocumento());
                    tesourariaCreditoModel.setValorNominal(model.getTitulo().getValorNominal());
                    tesourariaCreditoModel.setUsuario(Controller.getUsuarioLogado());
                    tesourariaCreditoModel.setTipoLancamento("C");
                    tesourariaCreditoModel.setResponsavel(model.getResponsavel());
                    tesourariaCreditoModel.setObservacao("Titulo recebido atraves do lote " + lote.getLote());
                    tesourariaCreditoModel.setMovimentoDiarioModel(registroMovimento("Lanç. Tesouraria.", tesourariaCreditoModel.getNumeroDocumento(), "Recbto título em espécie", aux.getValor(), "Lançado"));


                    if (model.getMovimentoDiarioModel() != null) {
                        tesourariaCreditoModel.setMovimentoDiarioModel(model.getMovimentoDiarioModel());
                    }

                    

                    historicoModel.getPk().setId(Constantes.HISTORICO_TESOURARIA_ESPECIE_RECEBIDO);

                    tesourariaCreditoModel.setHistorico(historicoModel);

                    HibernateUtil.getCurrentSession().save(tesourariaCreditoModel);

                }//fim do if pagto especie

                
                //cheque
                for (TituloReceberBaixaChequeModel cheque : model.getTituloReceberBaixaCheque()) {

                    cheque.setTituloReceberBaixa(model);
                    HibernateUtil.getCurrentSession().save(cheque);

                    TesourariaCreditoModel tesourariaCreditoModel = new TesourariaCreditoModel();

                    tesourariaCreditoModel.setPk(new PKModel());
                    tesourariaCreditoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
                    tesourariaCreditoModel.getPk().setId(PempecKeyGenerator.generateKey());
                    tesourariaCreditoModel.setNumeroDocumento(PempecKeyGenerator.generateNumeroDocumentoTesourariaCredito());

                    tesourariaCreditoModel.setTituloReceberBaixaChequeModel(cheque);
                    tesourariaCreditoModel.setDataRegistro(Controller.getDataServidorBD());
                    tesourariaCreditoModel.setDataMovimento(model.getTitulo().getDataPagamento());
                    tesourariaCreditoModel.setDataContabil(model.getTitulo().getDataPagamento());
                    tesourariaCreditoModel.setSacadoModel(model.getTitulo().getSacado());
                    tesourariaCreditoModel.setDescricao("CH Ref ao título : > " + model.getTitulo().getNumeroDocumento() + " " + model.getTitulo().getDescricao());
                    tesourariaCreditoModel.setValorNominal(cheque.getValor());
                    tesourariaCreditoModel.setUsuario(Controller.getUsuarioLogado());
                    tesourariaCreditoModel.setTipoLancamento("C");
                    tesourariaCreditoModel.setResponsavel(model.getResponsavel());

                    tesourariaCreditoModel.setMovimentoDiarioModel(registroMovimento("Lanç. Tesouraria.", tesourariaCreditoModel.getNumeroDocumento(), " Cheque " + cheque.getNumeroCheque() + " recebido como pagto título " + model.getTitulo().getNumeroDocumento(), cheque.getValor(), "Lançado"));

                    historicoModel.getPk().setId(Constantes.HISTORICO_TESOURARIA_CHEQUE_RECEBIDO);

                    tesourariaCreditoModel.setHistorico(historicoModel);

                    if (tesourariaCreditoModel.getMovimentoDiarioModel() != null) {

                        MovimentoDiarioModel mov = tesourariaCreditoModel.getMovimentoDiarioModel();

                        HibernateUtil.getCurrentSession().save(mov);
                    }

                    HibernateUtil.getCurrentSession().save(tesourariaCreditoModel);

                }

                for (TituloReceberBaixaInternetModel internet : model.getTituloReceberBaixaInternet()) {

                    internet.setTituloReceberBaixa(model);

                    internet.getContaBancaria().getPk().setOrganizacao(internet.getPk().getOrganizacao());

                    internet.getTipoOperacaoBancaria().getPk().setOrganizacao(internet.getPk().getOrganizacao());

                    HibernateUtil.getCurrentSession().save(internet);

                    ContaBancariaCreditoModel bancariaCreditoModel = new ContaBancariaCreditoModel();

                    bancariaCreditoModel.setPk(new PKModel());
                    bancariaCreditoModel.getPk().setId(PempecKeyGenerator.generateKey());
                    bancariaCreditoModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
                    bancariaCreditoModel.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaCredito());

                    bancariaCreditoModel.setContaBancaria(internet.getContaBancaria());
                    bancariaCreditoModel.setDataMovimento(internet.getDataOperacao());
                    bancariaCreditoModel.setDataRegistro(Controller.getDataServidorBD());
                    bancariaCreditoModel.setDataContabil(internet.getDataOperacao());
                    bancariaCreditoModel.setDescricao("TÍT " + model.getTitulo().getNumeroDocumento() + " " + model.getTitulo().getDescricao());
                    bancariaCreditoModel.setObservacao("Crédito de Título");
                    bancariaCreditoModel.setResponsavel(model.getResponsavel());
                    bancariaCreditoModel.setTipoLancamento("C");
                    bancariaCreditoModel.setTipoOperacaoBancaria(internet.getTipoOperacaoBancaria());
                    bancariaCreditoModel.setUsuario(model.getUsuario());
                    bancariaCreditoModel.setValor(internet.getValor());
                    bancariaCreditoModel.setTituloReceber(internet.getTituloReceberBaixa().getTitulo());

                    bancariaCreditoModel.setMovimentoDiarioModel(registroMovimento("Lanç.Conta Banc.", internet.getTituloReceberBaixa().getTitulo().getNumeroDocumento(), " Conta " + bancariaCreditoModel.getContaBancaria().getConta() + " Titular " + bancariaCreditoModel.getContaBancaria().getTitular(), internet.getValor(), "Lançado"));
                    bancariaCreditoModel.getMovimentoDiarioModel().setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

                    HibernateUtil.getCurrentSession().save(bancariaCreditoModel);
                }


            }//fim do for da baixa

            if (model.getMovimentoDiarioModel() != null) {
                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
                mov.setObservacao("Baixa Titulo Receber Lote");
                HibernateUtil.getCurrentSession().save(mov);

            }

            model.setValorPago(model.getTitulo().getValorNominal());

            // model.setLotePagamentoModel(lote);

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
        movimentoDiarioModel.setObservacao("Lançam. Receb de Titulo.");
        if (movimentoDiarioModel.getNumeroMovimento() == null) {
            movimentoDiarioModel.setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());
        }
        return movimentoDiarioModel;

    }
}
