package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.LayoutChequeDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.LayoutChequeModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class LayoutChequeDAO implements LayoutChequeDAOIf {

    @HibernateTransaction
    public void inserir(LayoutChequeModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(LayoutChequeModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public LayoutChequeModel consultarPorPk(LayoutChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LayoutChequeModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("conta", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (LayoutChequeModel) criteria.uniqueResult();

    }

    public List<LayoutChequeModel> obterPorFiltro(LayoutChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LayoutChequeModel.class);

        if (model.getConta().getPk().getId() != null && !model.getConta().getPk().getId().isEmpty()) {
            criteria.add(Restrictions.eq("conta.pk.id", model.getConta().getPk().getId()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("conta"));

        return criteria.list();

    }

    public List<LayoutChequeModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                LayoutChequeModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<LayoutChequeModel> coll)
            throws SystemException {

        for (LayoutChequeModel model : coll) {
            if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(LayoutChequeModel model)
            throws SystemException {
        if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public LayoutChequeModel consultarPorTemplate(LayoutChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LayoutChequeModel.class);

        if (model.getConta().getPk().getId() != null && !model.getConta().getPk().getId().isEmpty()) {
            criteria.add(Restrictions.eq("conta.pk.id", model.getConta().getPk().getId()));
        }

        criteria.setMaxResults(1);

        criteria.setFetchMode("conta", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (LayoutChequeModel) criteria.uniqueResult();

    }
}
