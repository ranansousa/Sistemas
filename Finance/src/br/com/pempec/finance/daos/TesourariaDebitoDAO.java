package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TesourariaDebitoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.reports.FilterReportExtratoTesouraria;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
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

/**
 *
 * @author Pempec
 */
public class TesourariaDebitoDAO implements TesourariaDebitoDAOIf {

    @HibernateTransaction
    public void inserir(TesourariaDebitoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TesourariaDebitoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TesourariaDebitoModel consultarPorPk(TesourariaDebitoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("cedenteModel", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagarBaixaModel", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);


        return (TesourariaDebitoModel) criteria.uniqueResult();

    }

    public List<TesourariaDebitoModel> obterPorFiltro(TesourariaDebitoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.like("numeroDocumento", model.getNumeroDocumento(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("numeroDocumento"));
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        return criteria.list();

    }

    public List<TesourariaDebitoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.desc("historico"));



        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TesourariaDebitoModel> coll)
            throws SystemException {

        for (TesourariaDebitoModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
                HibernateUtil.getCurrentSession().save(mov);
            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void excluir(TesourariaDebitoModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TesourariaDebitoModel consultarPorTemplate(TesourariaDebitoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.eq("numeroDocumento", model.getNumeroDocumento()));
        }

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("cedenteModel", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagarBaixaModel", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        return (TesourariaDebitoModel) criteria.uniqueResult();

    }

    public List<TesourariaDebitoModel> obterTodosPorTituloPago(TituloPagarBaixaModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        Criteria subCriteria = criteria.createCriteria("tituloPagarBaixaModel");

        subCriteria.add(Restrictions.eq("pk.id", model.getPk().getId()));
        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.desc("numeroDocumento"));
        criteria.addOrder(Order.desc("tituloPagarBaixaModel"));

        return criteria.list();
    }

    public List<TesourariaDebitoModel> obterTesourariaDebitoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.between("dataMovimento", dataInicial, dataFinal));

        criteria.add(Restrictions.isNull("tituloPagarBaixaModel.pk.id"));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        //evita que seja repetida a exportacao
        criteria.add(Restrictions.ne("historico.pk.id", Constantes.HISTORICO_TESOURARIA_DEPOSITO));

        Criteria subCriteria = criteria.createCriteria("historico", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("cedenteModel", FetchMode.JOIN);

        return criteria.list();
    }

    public List<TesourariaDebitoModel> obterTesourariaDebitoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);

        //evita que seja repetida a exportacao
        criteria.add(Restrictions.ne("historico.pk.id", Constantes.HISTORICO_TESOURARIA_DEPOSITO));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.between("dataMovimento", dataInicial, dataFinal));

        criteria.add(Restrictions.isNull("tituloPagarBaixaModel.pk.id"));

        Criteria subCriteria = criteria.createCriteria("historico", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("cedenteModel", FetchMode.JOIN);

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        return criteria.list();
    }

    public List<TesourariaDebitoModel> obterTitulosExportados(LoteContabilModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);

        criteria.add(Restrictions.eq("loteContabil.pk.id", model.getPk().getId()));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("historico", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("cedenteModel", FetchMode.JOIN);

        return criteria.list();
    }

    @HibernateTransaction
    public void depositarBanco(TesourariaDebitoModel tesourariaModel, ContaBancariaCreditoModel ctbCrModel)
            throws SystemException {

        if (tesourariaModel.getMovimentoDiarioModel() != null
                && !tesourariaModel.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = tesourariaModel.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }


        if (ctbCrModel.getMovimentoDiarioModel() != null
                && !ctbCrModel.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = ctbCrModel.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }



        if (tesourariaModel != null) {

            HibernateUtil.getCurrentSession().save(tesourariaModel);
        }


        if (ctbCrModel != null) {

            HibernateUtil.getCurrentSession().save(ctbCrModel);
        }


        Repopulador.repopulador();
    }

    
    @HibernateTransaction
    public void excluirDepositoBanco(TesourariaDebitoModel tesourariaModel, ContaBancariaCreditoModel ctbCrModel)
            throws SystemException {

        if (tesourariaModel.getMovimentoDiarioModel() != null
                && !tesourariaModel.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = tesourariaModel.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }


        if (ctbCrModel.getMovimentoDiarioModel() != null
                && !ctbCrModel.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = ctbCrModel.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }



        if (tesourariaModel != null) {

            HibernateUtil.getCurrentSession().delete(tesourariaModel);
        }


        if (ctbCrModel != null) {

            HibernateUtil.getCurrentSession().delete(ctbCrModel);
        }


        Repopulador.repopulador();
    }


    
    
    @HibernateTransaction
    public void depositarChequesBanco(Collection<TituloReceberBaixaChequeModel> collCheques, ContaBancariaCreditoModel ctbCrModel, LoteDepositoModel lote)
            throws SystemException {

        // caso de uso:  Depositar cheques selecionados
        // pega a colecao de cheques e faz update no banco
        // faz o lancamento em conta bancaria


        if (ctbCrModel.getMovimentoDiarioModel() != null
                && !ctbCrModel.getMovimentoDiarioModel().getCodigo().isEmpty()) {
            MovimentoDiarioModel mov = ctbCrModel.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }


//alterando os cheques selecionado com o lote deposito e data do deposito
        if (collCheques != null && collCheques.size() > 0) {

            for (TituloReceberBaixaChequeModel tituloReceberBaixaChequeModel : collCheques) {

                HibernateUtil.getCurrentSession().update(tituloReceberBaixaChequeModel);

            }

        }

        if (lote != null) {

            HibernateUtil.getCurrentSession().save(lote);

        }

        if (ctbCrModel != null) {

            HibernateUtil.getCurrentSession().save(ctbCrModel);
        }


        Repopulador.repopulador();
    }

    public List<TesourariaDebitoModel> obterTodosTitulosPagos(OrganizacaoModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);

        criteria.add(Restrictions.isNotNull("tituloPagarBaixaModel.pk.id"));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        return criteria.list();
    }

    public List<TesourariaDebitoModel> obterRelatorioExtratoTesouria(FilterReportExtratoTesouraria filter) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);
        
        criteria.add(Restrictions.between("dataMovimento",  (filter.getDataInicial()),(filter.getDataFinal())));
        criteria.add(Restrictions.eq("pk.organizacao.id", filter.getOrganizacao()));        
        
        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("cedenteModel", FetchMode.JOIN);       

        
        criteria.addOrder(Order.asc("dataMovimento"));
        

        return criteria.list();
    }

    public Double obterSaldoAnterior(FilterReportExtratoTesouraria filter) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("select sum(td.valorNominal) from br.com.pempec.finance.models.TesourariaDebitoModel td "
                + " WHERE td.dataMovimento < :data and td.pk.organizacao.id = :org ");

        query.setDate("data", filter.getDataInicial());
        query.setParameter("org", filter.getOrganizacao());

        return (Double) query.uniqueResult();

    }

    public Double obterTotal(OrganizacaoModel organizacaoModel) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("select sum(td.valorNominal) from br.com.pempec.finance.models.TesourariaDebitoModel td "
                + " WHERE td.pk.organizacao.id = :org ");

        query.setParameter("org", organizacaoModel.getId());

        return (Double) query.uniqueResult();

    }

    public List<TesourariaDebitoModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaDebitoModel.class);

        criteria.add(Restrictions.eq("dataRegistro", dataInicial));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        return criteria.list();

    }
}