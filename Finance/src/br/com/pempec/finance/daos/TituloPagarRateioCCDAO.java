package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloPagarRateioCCDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.reports.FilterReportTituloPagar;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pempec
 */
public class TituloPagarRateioCCDAO implements TituloPagarRateioCCDAOIf {

    @HibernateTransaction
    public void inserir(Collection<TituloPagarRateioCCModel> rateios) throws SystemException {

        for (TituloPagarRateioCCModel TituloPagarRateioCCModel : rateios) {

            HibernateUtil.getCurrentSession().save(TituloPagarRateioCCModel);
        }


    }

    @HibernateTransaction
    public void inserir(TituloPagarRateioCCModel model) throws SystemException {

        HibernateUtil.getCurrentSession().save(model);

    }

    @HibernateTransaction
    public void alterar(TituloPagarRateioCCModel model) throws SystemException {

        HibernateUtil.getCurrentSession().update(model);


    }

    public List<TituloPagarRateioCCModel> obterPorFiltro(TituloPagarRateioCCModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarRateioCCModel.class);

        if (model.getPk() != null && !model.getPk().getId().isEmpty()) {
            criteria.add(Restrictions.like("pk.model.id", model.getPk().getId(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return criteria.list();

    }

    public List<TituloPagarRateioCCModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarRateioCCModel.class);

        return criteria.list();
    }

    public List<TituloPagarRateioCCModel> obterRelatorio(FilterReportTituloPagar filtro)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarRateioCCModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        return criteria.list();

    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloPagarRateioCCModel> coll)
            throws SystemException {

        for (TituloPagarRateioCCModel model : coll) {

            HibernateUtil.getCurrentSession().delete(model);
        }


    }

    @HibernateTransaction
    public void excluir(TituloPagarRateioCCModel model)
            throws SystemException {

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloPagarRateioCCModel consultarPorPk(TituloPagarRateioCCModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarRateioCCModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("centroCustoModel", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloPagarRateioCCModel) criteria.uniqueResult();

    }

    public TituloPagarRateioCCModel consultarPorTemplate(TituloPagarRateioCCModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarRateioCCModel.class);

        if (model.getPk() != null && !model.getPk().getId().isEmpty()) {
            criteria.add(Restrictions.like("pk.model.id", model.getPk().getId(),
                    MatchMode.START));
        }

        criteria.setFetchMode("centroCustoModel", FetchMode.JOIN);
        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloPagarRateioCCModel) criteria.uniqueResult();

    }

    public List<TituloPagarRateioCCModel> obterTodosPorCentroCusto(CentroCustoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarRateioCCModel.class);


        criteria.setFetchMode("centroCustoModel", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return criteria.list();

    }
}//fim

