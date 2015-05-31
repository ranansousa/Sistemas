package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TesourariaCreditoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.reports.FilterReportExtratoTesouraria;
import br.com.pempec.finance.utils.Constantes;
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
public class TesourariaCreditoDAO implements TesourariaCreditoDAOIf {

    @HibernateTransaction
    public void inserir(TesourariaCreditoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TesourariaCreditoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TesourariaCreditoModel consultarPorPk(TesourariaCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaCreditoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("sacadoModel", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceberBaixaChequeModel", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceberBaixaModel", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return (TesourariaCreditoModel) criteria.uniqueResult();

    }

    public List<TesourariaCreditoModel> obterPorFiltro(TesourariaCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaCreditoModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.like("numeroDocumento", model.getNumeroDocumento(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.asc("numeroDocumento"));

        return criteria.list();

    }

    public List<TesourariaCreditoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaCreditoModel.class);


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TesourariaCreditoModel> coll)
            throws SystemException {

        for (TesourariaCreditoModel model : coll) {

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
    public void excluir(TesourariaCreditoModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            HibernateUtil.getCurrentSession().save(mov);
        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TesourariaCreditoModel consultarPorTemplate(TesourariaCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaCreditoModel.class);

        if (model.getNumeroDocumento() != null && !model.getNumeroDocumento().isEmpty()) {
            criteria.add(Restrictions.eq("numeroDocumento", model.getNumeroDocumento()));
        }

        if (model.getContaBancariaDebitoModel() != null && model.getContaBancariaDebitoModel().getPk() != null) {
            criteria.add(Restrictions.eq("contaBancariaDebitoModel.pk.id", model.getContaBancariaDebitoModel().getPk().getId()));
        }

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("sacadoModel", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceberBaixaChequeModel", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceberBaixaModel", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaDebitoModel", FetchMode.JOIN);

        return (TesourariaCreditoModel) criteria.uniqueResult();

    }

    public List<TesourariaCreditoModel> obterTodosPorTituloRecebido(TituloReceberBaixaModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaCreditoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        Criteria subCriteria = criteria.createCriteria("tituloReceberBaixaModel");

        subCriteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("sacadoModel", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceberBaixaChequeModel", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceberBaixaModel", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.asc("tituloReceberBaixaModel"));
        return criteria.list();
    }

    public List<TesourariaCreditoModel> obterTesourariaCreditoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaCreditoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.between("dataMovimento", dataInicial, dataFinal));

        criteria.add(Restrictions.isNull("tituloReceberBaixaModel.pk.id"));

        criteria.add(Restrictions.isNull("tituloReceberBaixaChequeModel.pk.id"));

        criteria.add(Restrictions.ne("historico.pk.id", Constantes.HISTORICO_TESOURARIA_TRANSFERE_BCO_TESOURARIA));

        Criteria subCriteria = criteria.createCriteria("historico", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("sacadoModel", FetchMode.JOIN);

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();
    }

    public List<TesourariaCreditoModel> obterTesourariaCreditoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaCreditoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.between("dataMovimento", dataInicial, dataFinal));

        criteria.add(Restrictions.isNull("tituloReceberBaixaModel.pk.id"));

        criteria.add(Restrictions.isNull("tituloReceberBaixaChequeModel.pk.id"));

        criteria.add(Restrictions.ne("historico.pk.id", Constantes.HISTORICO_TESOURARIA_TRANSFERE_BCO_TESOURARIA));

        Criteria subCriteria = criteria.createCriteria("historico", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("sacadoModel", FetchMode.JOIN);

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();
    }

    public List<TesourariaCreditoModel> obterTitulosExportados(LoteContabilModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaCreditoModel.class);

        criteria.add(Restrictions.eq("loteContabil.pk.id", model.getPk().getId()));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("historico", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("sacadoModel", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();
    }

    public List<TesourariaCreditoModel> obterRelatorioExtratoTesouria(FilterReportExtratoTesouraria filter) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaCreditoModel.class);

        criteria.setFetchMode("historico", FetchMode.JOIN);
        criteria.setFetchMode("sacadoModel", FetchMode.JOIN);

        criteria.add(Restrictions.between("dataMovimento", filter.getDataInicial(), filter.getDataFinal()));

        criteria.add(Restrictions.eq("pk.organizacao.id", filter.getOrganizacao()));

        criteria.addOrder(Order.asc("dataMovimento"));
        criteria.addOrder(Order.asc("valorNominal"));

        return criteria.list();

    }

    public Double obterSaldoAnterior(FilterReportExtratoTesouraria filter) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("select sum(tc.valorNominal) from br.com.pempec.finance.models.TesourariaCreditoModel tc "
                + " WHERE tc.dataMovimento < :data and tc.pk.organizacao.id = :org ");

        query.setDate("data", filter.getDataInicial());
        query.setParameter("org", filter.getOrganizacao());

        return (Double) query.uniqueResult();

    }

    public Double obterTotal(OrganizacaoModel organizacaoModel) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("select sum(tc.valorNominal) from br.com.pempec.finance.models.TesourariaCreditoModel tc "
                + " WHERE tc.pk.organizacao.id = :org ");

        query.setParameter("org", organizacaoModel.getId());

        return (Double) query.uniqueResult();

    }

    public List<TesourariaCreditoModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TesourariaCreditoModel.class);

        criteria.add(Restrictions.eq("dataRegistro", dataInicial));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        return criteria.list();

    }
}