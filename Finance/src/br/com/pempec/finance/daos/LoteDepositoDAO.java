package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.LoteDepositoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

//EQUIPE PEMPEC
public class LoteDepositoDAO implements LoteDepositoDAOIf {

    @HibernateTransaction
    public void inserir(LoteDepositoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
    }

    @HibernateTransaction
    public void alterar(LoteDepositoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
    }

    public LoteDepositoModel consultarPorPk(LoteDepositoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteDepositoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (LoteDepositoModel) criteria.uniqueResult();

    }

    public List<LoteDepositoModel> obterPorFiltro(LoteDepositoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteDepositoModel.class);

        if (model.getLote() != null && !model.getLote().isEmpty()) {
            criteria.add(Restrictions.eq("lote", model.getLote()));
        }
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("lote"));


        return criteria.list();

    }

    public List<LoteDepositoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteDepositoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataRegistro"));
        criteria.addOrder(Order.desc("lote"));
        criteria.addOrder(Order.asc("status"));


        return criteria.list();


    }

    @HibernateTransaction
    public void excluirTodos(Collection<LoteDepositoModel> coll)
            throws SystemException {

        for (LoteDepositoModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(LoteDepositoModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);

    }

    public LoteDepositoModel consultarPorTemplate(LoteDepositoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteDepositoModel.class);


        if (model.getLote() != null && !model.getLote().isEmpty()) {
            criteria.add(Restrictions.eq("lote", model.getLote()));
        }

        criteria.setMaxResults(1);
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return (LoteDepositoModel) criteria.uniqueResult();

    }
}
