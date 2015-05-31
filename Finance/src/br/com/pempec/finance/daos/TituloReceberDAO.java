package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloReceberDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ImpressaoMultiplosRecibosFilter;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.NotaFiscalEmitidaModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ParticionadorTituloReceberModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.models.TituloReceberRateioHistoricoModel;
import br.com.pempec.finance.models.reports.FilterReportFluxoCaixa;
import br.com.pempec.finance.models.reports.FilterReportTituloReceber;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.Repopulador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pempec
 */
@SuppressWarnings("unchecked")
public class TituloReceberDAO implements TituloReceberDAOIf {

    @HibernateTransaction
    public void inserir(Collection<TituloReceberModel> titulos) throws SystemException {

        String registroProvisao = PempecKeyGenerator.generateKey();

        for (TituloReceberModel tituloReceberModel : titulos) {

            HibernateUtil.getCurrentSession().save(tituloReceberModel);

            if (tituloReceberModel.getNotaFiscal() != null) {

                NotaFiscalEmitidaModel notaFiscalEmitidaModel = tituloReceberModel.getNotaFiscal();
                notaFiscalEmitidaModel.getPk().setOrganizacao(tituloReceberModel.getPk().getOrganizacao());

                if (!tituloReceberModel.getNotaFiscal().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                    MovimentoDiarioModel mov = tituloReceberModel.getNotaFiscal().getMovimentoDiarioModel();
                    mov.setObservacao("Titulo " + tituloReceberModel.getNumeroDocumento());

                    HibernateUtil.getCurrentSession().save(mov);
                }

                HibernateUtil.getCurrentSession().save(notaFiscalEmitidaModel);

            }

            if (tituloReceberModel.getMovimentoDiarioModel() != null) {

                MovimentoDiarioModel mov = tituloReceberModel.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            if (tituloReceberModel.getCollCentroCustosRateio() != null && !tituloReceberModel.getCollCentroCustosRateio().isEmpty()) {

                for (TituloReceberRateioCCModel rateio : tituloReceberModel.getCollCentroCustosRateio()) {

                    rateio.setTituloReceberModel(tituloReceberModel);

                    HibernateUtil.getCurrentSession().save(rateio);

                }

            }

            if (tituloReceberModel.getCollHistoricosRateio() != null && !tituloReceberModel.getCollHistoricosRateio().isEmpty()) {

                for (TituloReceberRateioHistoricoModel rateio : tituloReceberModel.getCollHistoricosRateio()) {

                    rateio.setTituloReceberModel(tituloReceberModel);

                    HibernateUtil.getCurrentSession().save(rateio);

                }

            }

            if (tituloReceberModel.isProvisao()) {
                tituloReceberModel.setRegistroProvisao(registroProvisao);
            }

        }
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void inserir(TituloReceberModel model) throws SystemException {

        HibernateUtil.getCurrentSession().save(model);

        if (model.getNotaFiscal() != null) {

            NotaFiscalEmitidaModel notaFiscalEmitidaModel = model.getNotaFiscal();
            notaFiscalEmitidaModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            if (!model.getNotaFiscal().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getNotaFiscal().getMovimentoDiarioModel();
                mov.setObservacao("titulo " + model.getNumeroDocumento());

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().save(notaFiscalEmitidaModel);

        }

        if (model.getMovimentoDiarioModel() != null) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        if (model.getCollCentroCustosRateio() != null && !model.getCollCentroCustosRateio().isEmpty()) {

            for (TituloReceberRateioCCModel rateio : model.getCollCentroCustosRateio()) {

                rateio.setTituloReceberModel(model);

                HibernateUtil.getCurrentSession().save(rateio);

            }

        }

        if (model.getCollHistoricosRateio() != null && !model.getCollHistoricosRateio().isEmpty()) {

            for (TituloReceberRateioHistoricoModel rateio : model.getCollHistoricosRateio()) {

                rateio.setTituloReceberModel(model);

                HibernateUtil.getCurrentSession().save(rateio);

            }

        }

        if (model.isProvisao()) {
            model.setRegistroProvisao(PempecKeyGenerator.generateKey());
        }

        HibernateUtil.getCurrentSession().save(model);

        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TituloReceberModel model) throws SystemException {

        if (model.getNotaFiscal() != null) {

            NotaFiscalEmitidaModel notaFiscalEmitidaModel = model.getNotaFiscal();

            notaFiscalEmitidaModel.getPk().setOrganizacao(model.getPk().getOrganizacao());

            if (model.getNotaFiscal().getPk().getId().isEmpty()) {

                notaFiscalEmitidaModel.getPk().setId(PempecKeyGenerator.generateKey());

                model.getNotaFiscal().getPk().setId(notaFiscalEmitidaModel.getPk().getId());

                HibernateUtil.getCurrentSession().save(notaFiscalEmitidaModel);

            } else {

                HibernateUtil.getCurrentSession().update(notaFiscalEmitidaModel);
            }

            if (!model.getNotaFiscal().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getNotaFiscal().getMovimentoDiarioModel();
                mov.setObservacao("NF-E do titulo " + model.getNumeroDocumento());
                HibernateUtil.getCurrentSession().save(mov);

            }

        }

        if (model.getMovimentoDiarioModel() != null) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloReceberRateioCCModel.deletarCCRateioPorIdTituloReceber");
        query.setParameter("id_titulo_receber", model.getPk().getId());
        query.executeUpdate();

        if (model.getCollCentroCustosRateio() != null && !model.getCollCentroCustosRateio().isEmpty()) {

            for (TituloReceberRateioCCModel rateio : model.getCollCentroCustosRateio()) {

                rateio.setTituloReceberModel(model);

                HibernateUtil.getCurrentSession().save(rateio);

            }

        }

        query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloReceberRateioHistoricoModel.deletarHistoricoRateioPorIdTituloReceber");
        query.setParameter("id_titulo_receber", model.getPk().getId());
        query.executeUpdate();

        if (model.getCollHistoricosRateio() != null && !model.getCollHistoricosRateio().isEmpty()) {

            for (TituloReceberRateioHistoricoModel rateio : model.getCollHistoricosRateio()) {

                rateio.setTituloReceberModel(model);

                HibernateUtil.getCurrentSession().save(rateio);

            }

        }

        HibernateUtil.getCurrentSession().update(model);

        Repopulador.repopulador();

    }

    public TituloReceberModel consultarPorPk(TituloReceberModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

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
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("sacado", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        return (TituloReceberModel) criteria.uniqueResult();

    }

    public List<TituloReceberModel> obterPorFiltro(TituloReceberModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.like("numeroDocumento", model.getNumeroDocumento(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.addOrder(Order.asc("numeroDocumento"));

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
    public List<TituloReceberModel> obterTitulosExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.between("dataEmissao", dataInicial, dataFinal));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        criteria.add(Restrictions.isNull("tituloAnterior.pk.id"));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("sacado", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);

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
    public List<TituloReceberModel> obterTitulosExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.between("dataEmissao", dataInicial, dataFinal));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        criteria.add(Restrictions.isNull("tituloAnterior.pk.id"));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("sacado", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);

        return criteria.list();

    }

    public List<TituloReceberModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);

        criteria.addOrder(Order.asc("status"));
        criteria.addOrder(Order.asc("dataVencimento"));

        return criteria.list();
    }

    public List<TituloReceberModel> obterTodosAReceber(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.isNull("dataPagamento"));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_PAGO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_FUNDO_PERDIDO));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);

        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.setFetchMode("lotePagamentoModel", FetchMode.JOIN);

        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);

        criteria.setFetchMode("sacado", FetchMode.JOIN);

        //    criteria.addOrder(Order.asc("sacado.nome"));        
        criteria.createAlias("sacado", "sc").addOrder(Order.asc("sc.nome"));
        criteria.addOrder(Order.asc("dataVencimento"));

        

        return criteria.list();
    }

    /**
     * Utilizado para tela de Excluir Baixa. Tenho que obter todos os pagos.
     *
     * @param model
     * @return
     * @throws br.com.pempec.finance.exceptions.SystemException
     */
    public List<TituloReceberModel> obterTodosPagos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.isNotNull("dataPagamento"));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_NOVO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_FUNDO_PERDIDO));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("sacado", FetchMode.JOIN);
        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataVencimento"));

        return criteria.list();
    }

    public List<TituloReceberModel> obterTodosRecibo(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_FUNDO_PERDIDO));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataPagamento"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloReceberModel> coll)
            throws SystemException {

        for (TituloReceberModel model : coll) {

            Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloReceberRateioCCModel.deletarCCRateioPorIdTitulReceber");
            query.setParameter("id_titulo_receber", model.getPk().getId());
            query.executeUpdate();

            Query queryHistorico = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloReceberRateioHistoricoModel.deletarHistoricoRateioPorIdTituloReceber");
            queryHistorico.setParameter("id_titulo_receber", model.getPk().getId());
            queryHistorico.executeUpdate();

            if (model.getMovimentoDiarioModel() != null) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            if (model.getNotaFiscal() != null) {

                NotaFiscalEmitidaModel notaFiscalEmitidaModel = model.getNotaFiscal();
                notaFiscalEmitidaModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
                notaFiscalEmitidaModel.setObservacao(model.getNotaFiscal().getObservacao() + " - " + " ## O título foi excluido. ##");

                if (model.getNotaFiscal().getMovimentoDiarioModel() != null && !model.getNotaFiscal().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                    MovimentoDiarioModel mov = model.getNotaFiscal().getMovimentoDiarioModel();
                    mov.setCodigo(model.getNotaFiscal().getNumero());
                    mov.setObservacao("titulo " + model.getNumeroDocumento());

                    HibernateUtil.getCurrentSession().save(mov);

                }

                HibernateUtil.getCurrentSession().update(notaFiscalEmitidaModel);

            }

            HibernateUtil.getCurrentSession().delete(model);

        }

        Repopulador.repopulador();

    }

    public List<TituloReceberRateioCCModel> obterRateioCustosPorTituloReceber(TituloReceberModel titulo)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberRateioCCModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", titulo.getPk().getOrganizacao().getId()));

        criteria.add(Restrictions.eq("tituloReceberModel.pk.id", titulo.getPk().getId()));

        criteria.setFetchMode("centroCustoModel", FetchMode.JOIN);

        return criteria.list();

    }

    public List<TituloReceberRateioHistoricoModel> obterHistoricosPorTituloReceber(TituloReceberModel titulo)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberRateioHistoricoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", titulo.getPk().getOrganizacao().getId()));

        criteria.add(Restrictions.eq("tituloReceberModel.pk.id", titulo.getPk().getId()));

        criteria.setFetchMode("historicoModel", FetchMode.JOIN);

        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);

        return criteria.list();

    }

    @HibernateTransaction
    public void excluir(TituloReceberModel model)
            throws SystemException {
        // o titulo nao sera excluido fisicamente. 
        // tera apenas o status de EXCLUIDO

        if (model.getMovimentoDiarioModel() != null) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        if (model.getNotaFiscal() != null) {

            NotaFiscalEmitidaModel notaFiscalEmitidaModel = model.getNotaFiscal();
            notaFiscalEmitidaModel.getPk().setOrganizacao(model.getPk().getOrganizacao());
            notaFiscalEmitidaModel.setObservacao(model.getNotaFiscal().getObservacao() + " - " + " ## O título foi excluido. ##");

            if (model.getNotaFiscal().getMovimentoDiarioModel() != null && !model.getNotaFiscal().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getNotaFiscal().getMovimentoDiarioModel();
                mov.setCodigo(model.getNotaFiscal().getNumero());
                mov.setObservacao("titulo " + model.getNumeroDocumento());
                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().update(notaFiscalEmitidaModel);

        }

        Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloReceberRateioCCModel.deletarCCRateioPorIdTituloReceber");
        query.setParameter("id_titulo_receber", model.getPk().getId());
        query.executeUpdate();

        Query queryHistorico = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.TituloReceberRateioHistoricoModel.deletarHistoricoRateioPorIdTituloReceber");
        queryHistorico.setParameter("id_titulo_receber", model.getPk().getId());
        queryHistorico.executeUpdate();

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TituloReceberModel consultarPorTemplate(TituloReceberModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.eq("numeroDocumento", model.getNumeroDocumento()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("sacado", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);

        criteria.setMaxResults(1);

        return (TituloReceberModel) criteria.uniqueResult();

    }

    public TituloReceberModel consultarPorTemplatePago(TituloReceberModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.like("numeroDocumento", model.getNumeroDocumento(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.isNotNull("dataPagamento"));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_FUNDO_PERDIDO));

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("sacado", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        return (TituloReceberModel) criteria.uniqueResult();

    }

    public TituloReceberModel consultarPorTemplateAReceber(TituloReceberModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.like("numeroDocumento", model.getNumeroDocumento(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.isNull("dataPagamento"));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_PAGO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("sacado", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);

        return (TituloReceberModel) criteria.uniqueResult();

    }

    /**
     * Este método traz todos os títulos filho do titulo obtido por parametro,
     * sendo que ele engloba o próprio título passado.
     *
     * @param model título base a procurar pelos filhos
     * @return Coleção de títulos da Hierarquia do titulo. Inclui o proprio
     * titulo.
     * @throws br.com.pempec.finance.exceptions.SystemException
     */
    public List<TituloReceberModel> obterFilhos(TituloReceberModel model) throws SystemException {

        TituloReceberModel aux = model;

        Boolean temFilho = false;

        List<TituloReceberModel> auxColl = new ArrayList<TituloReceberModel>();

        auxColl.add(model);

        do {

            Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                    TituloReceberModel.class);

            criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

            criteria.add(Restrictions.eq("tituloAnterior.pk.id", aux.getPk().getId()));

            criteria.add(Restrictions.eq("pk.organizacao.id", aux.getPk().getOrganizacao().getId()));

            criteria.setMaxResults(1);

            aux = (TituloReceberModel) criteria.uniqueResult();

            if (aux != null && aux.getTituloAnterior() != null) {

                temFilho = true;

                auxColl.add(aux);

            } else {

                temFilho = false;
            }

        } while (temFilho);

        return auxColl;

    }

    public List<TituloReceberModel> obterImpressaoMultiplosRecibos(ImpressaoMultiplosRecibosFilter filter)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

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

        criteria.setFetchMode("sacado", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);

        criteria.addOrder(Order.asc("numeroDocumento"));

        return criteria.list();

    }

    public List<TituloReceberModel> obterTitulosAntecipados(TituloReceberModel model) throws SystemException {

        TituloReceberModel aux = model;

        Boolean temAntecessor = false;

        List<TituloReceberModel> auxColl = new ArrayList<TituloReceberModel>();

        auxColl.add(model);

        if (aux.getTituloAnterior() != null) {

            do {

                Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                        TituloReceberModel.class);

                criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

                criteria.add(Restrictions.eq("pk.id", aux.getTituloAnterior().getPk().getId()));

                criteria.add(Restrictions.eq("pk.organizacao.id", aux.getTituloAnterior().getPk().getOrganizacao().getId()));

                criteria.setMaxResults(1);

                aux = (TituloReceberModel) criteria.uniqueResult();

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

    @HibernateTransaction
    public void particionar(ParticionadorTituloReceberModel model) throws SystemException {

        if (model.getTituloAnterior() != null) {
            TituloReceberModel tituloAnterior = model.getTituloAnterior();

            HibernateUtil.getCurrentSession().update(tituloAnterior);

        }

        if (model.getTituloNovo() != null) {

            TituloReceberModel tituloNovo = model.getTituloNovo();

            if (model.getTituloNovo().getMovimentoDiarioModel() != null
                    && !model.getTituloNovo().getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getTituloNovo().getMovimentoDiarioModel();
                HibernateUtil.getCurrentSession().save(mov);
            }

            HibernateUtil.getCurrentSession().save(tituloNovo);
            Repopulador.repopulador();

        }

    }

    public List<TituloReceberModel> obterTitulosFilhos(TituloReceberModel model) throws SystemException {

        TituloReceberModel aux = model;

        Boolean temFilho = false;

        List<TituloReceberModel> auxColl = new ArrayList<TituloReceberModel>();

        auxColl.add(model);

        do {

            Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                    TituloReceberModel.class);

            criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

            criteria.add(Restrictions.eq("tituloAnterior.pk.id", aux.getPk().getId()));

            criteria.add(Restrictions.eq("pk.organizacao.id", aux.getPk().getOrganizacao().getId()));

            criteria.setMaxResults(1);

            aux = (TituloReceberModel) criteria.uniqueResult();

            if (aux != null && aux.getTituloAnterior() != null) {

                temFilho = true;

                auxColl.add(aux);

            } else {

                temFilho = false;
            }

        } while (temFilho);

        return auxColl;

    }

    public List<TituloReceberModel> obterRelatorio(FilterReportTituloReceber filtro)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        if (filtro.getSacado() != null && !filtro.getSacado().isEmpty()) {
            criteria.add(Restrictions.eq("sacado.pk.id", filtro.getSacado()));
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

        if (filtro.getStatus().equalsIgnoreCase(Constantes.STATUS_TITULO_NOVO)) {
        criteria.add(Restrictions.between("dataVencimento", filtro.getDataInicial(), filtro.getDataFinal()));
        }
        if (filtro.getStatus().equalsIgnoreCase(Constantes.STATUS_TITULO_EXCLUIDO)) {
        criteria.add(Restrictions.between("dataVencimento", filtro.getDataInicial(), filtro.getDataFinal()));
        }
        
        if (filtro.getStatus().equalsIgnoreCase(Constantes.STATUS_TITULO_PAGO)) {
        criteria.add(Restrictions.between("dataPagamento", filtro.getDataInicial(), filtro.getDataFinal()));
        }
        
        if (filtro.getStatus().equalsIgnoreCase(Constantes.STATUS_TITULO_PARCIAL)) {
        criteria.add(Restrictions.between("dataPagamento", filtro.getDataInicial(), filtro.getDataFinal()));
        }
        

        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("sacado", FetchMode.JOIN);
        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataVencimento"));
        criteria.addOrder(Order.asc("sacado"));

        return criteria.list();

    }

    public List<TituloReceberModel> obterRelatorioFluxo(FilterReportFluxoCaixa filtro)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        if (filtro.getSacado() != null && !filtro.getSacado().isEmpty()) {
            criteria.add(Restrictions.eq("sacado.pk.id", filtro.getSacado()));
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

        criteria.add(Restrictions.isNull("dataPagamento"));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_PAGO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_FUNDO_PERDIDO));

        criteria.add(Restrictions.between("dataVencimento", filtro.getDataInicial(), filtro.getDataFinal()));

        criteria.setFetchMode("tipoCobranca", FetchMode.JOIN);
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("sacado", FetchMode.JOIN);
        criteria.setFetchMode("tituloAnterior", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataVencimento"));
        criteria.addOrder(Order.asc("sacado"));

        return criteria.list();

    }

    public List<TituloReceberModel> obterTitulosExportados(LoteContabilModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.eq("loteContabil.pk.id", model.getPk().getId()));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("sacado", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("notaFiscal", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilDebito", FetchMode.JOIN);
        criteria.setFetchMode("contaContabilCredito", FetchMode.JOIN);

        return criteria.list();
    }

    public List<TituloReceberModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.eq("dataRegistro", dataInicial));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        return criteria.list();

    }

    public List<TituloReceberModel> obterTodosPorPeriodo(TituloReceberModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberModel.class);

        criteria.add(Restrictions.isNull("dataPagamento"));

        criteria.add(Restrictions.between("dataVencimento", dataInicial, dataFinal));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_PAGO));

        criteria.add(Restrictions.ne("status.pk.id", Constantes.STATUS_TITULO_EXCLUIDO));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        if (model.getSacado() != null && model.getSacado().getPk() != null) {
            criteria.add(Restrictions.eq("sacado.pk.id", model.getSacado().getPk().getId()));
        }

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("centroCusto", FetchMode.JOIN);

        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("localPagamento", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);

        criteria.setFetchMode("usuario", FetchMode.JOIN);

        Criteria subCriteria = criteria.createCriteria("sacado", CriteriaSpecification.LEFT_JOIN);
        subCriteria.setFetchMode("endereco", FetchMode.JOIN);
        subCriteria.setFetchMode("contato", FetchMode.JOIN);
        subCriteria.setFetchMode("banco", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataEmissao"));
        criteria.addOrder(Order.asc("dataVencimento"));
        criteria.addOrder(Order.asc("sacado"));

        return criteria.list();

    }

    @HibernateTransaction
    public void corrigeRegistroProvisao() throws SystemException {

        try {

            Query query = HibernateUtil.getCurrentSession().createSQLQuery("update titulo_receber t set t.registro_provisao = null where t.is_provisao = 0");

            query.executeUpdate();

            List<TituloReceberModel> titulosReceber;

            Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(TituloReceberModel.class);

            criteria.add(Restrictions.eq("parcela", "1"));

            criteria.add(Restrictions.eq("provisao", 1));

            titulosReceber = criteria.list();

            for (TituloReceberModel receberModel : titulosReceber) {
                receberModel.setRegistroProvisao(PempecKeyGenerator.generateKey());
                HibernateUtil.getCurrentSession().update(receberModel);
            }

        } catch (Exception e) {
            throw new SystemException(e);
        }

    }
}
