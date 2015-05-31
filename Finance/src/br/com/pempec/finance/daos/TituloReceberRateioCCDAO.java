package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloReceberRateioCCDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.models.reports.FilterReportTituloReceber;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pempec
 */
public class TituloReceberRateioCCDAO implements TituloReceberRateioCCDAOIf {

    @HibernateTransaction
    public void inserir(Collection<TituloReceberRateioCCModel> rateios) throws SystemException {

        for (TituloReceberRateioCCModel TituloReceberRateioCCModel : rateios) {

            HibernateUtil.getCurrentSession().save(TituloReceberRateioCCModel);
        }


    }

    @HibernateTransaction
    public void inserir(TituloReceberRateioCCModel model) throws SystemException {

        HibernateUtil.getCurrentSession().save(model);

    }

    @HibernateTransaction
    public void alterar(TituloReceberRateioCCModel model) throws SystemException {

        HibernateUtil.getCurrentSession().update(model);


    }

    public List<TituloReceberRateioCCModel> obterPorFiltro(TituloReceberRateioCCModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberRateioCCModel.class);

        if (model.getPk() != null && !model.getPk().getId().isEmpty()) {
            criteria.add(Restrictions.like("pk.model.id", model.getPk().getId(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return criteria.list();

    }

    public List<TituloReceberRateioCCModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberRateioCCModel.class);

        return criteria.list();
    }

    public List<TituloReceberRateioCCModel> obterRelatorio(FilterReportTituloReceber filtro)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberRateioCCModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        return criteria.list();

    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloReceberRateioCCModel> coll)
            throws SystemException {

        for (TituloReceberRateioCCModel model : coll) {

            HibernateUtil.getCurrentSession().delete(model);
        }


    }

    @HibernateTransaction
    public void excluir(TituloReceberRateioCCModel model)
            throws SystemException {

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloReceberRateioCCModel consultarPorPk(TituloReceberRateioCCModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberRateioCCModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("centroCustoModel", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloReceberRateioCCModel) criteria.uniqueResult();

    }

    public TituloReceberRateioCCModel consultarPorTemplate(TituloReceberRateioCCModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberRateioCCModel.class);

        if (model.getPk() != null && !model.getPk().getId().isEmpty()) {
            criteria.add(Restrictions.like("pk.model.id", model.getPk().getId(),
                    MatchMode.START));
        }

        criteria.setFetchMode("centroCustoModel", FetchMode.JOIN);
        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloReceberRateioCCModel) criteria.uniqueResult();

    }

    public List<TituloReceberRateioCCModel> obterTodosPorCentroCusto(CentroCustoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberRateioCCModel.class);


        criteria.setFetchMode("centroCustoModel", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));




        return criteria.list();

    }
}//fim

