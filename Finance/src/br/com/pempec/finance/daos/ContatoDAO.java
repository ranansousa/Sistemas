package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ContatoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContatoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

//Equipe PEMPEC
public class ContatoDAO implements ContatoDAOIf {

    @HibernateTransaction
    public void inserir(ContatoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(ContatoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public ContatoModel consultarPorPk(ContatoModel model)
            throws SystemException {
        return (ContatoModel) HibernateUtil.getCurrentSession().get(
                ContatoModel.class, model.getPk());
    }

    @HibernateTransaction
    public void excluir(ContatoModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public ContatoModel consultarPorTemplate(ContatoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContatoModel.class);

        if (model.getEmail() != null && !model.getEmail().isEmpty()) {
            criteria.add(Restrictions.eq("email", model.getEmail()));
        }

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (ContatoModel) criteria.uniqueResult();

    }

    public List<ContatoModel> obterPorFiltro(ContatoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContatoModel.class);

        if (model.getEmail() != null && !model.getEmail().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getEmail(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("email"));

        return criteria.list();

    }

    public List<ContatoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                ContatoModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<ContatoModel> coll)
            throws SystemException {

        for (ContatoModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }
}
