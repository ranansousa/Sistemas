package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.EstadoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.EstadoModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

//EQUIPE PEMPEC
public class EstadoDAO implements EstadoDAOIf {

    @HibernateTransaction
    public void inserir(EstadoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(EstadoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public EstadoModel consultarPorPk(EstadoModel model)
            throws SystemException {
        return (EstadoModel) HibernateUtil.getCurrentSession().get(
                EstadoModel.class, model.getId());
    }

    @HibernateTransaction
    public void excluir(EstadoModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public EstadoModel consultarPorTemplate(EstadoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                EstadoModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        criteria.setMaxResults(1);

        return (EstadoModel) criteria.uniqueResult();

    }

    public List<EstadoModel> obterPorFiltro(EstadoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                EstadoModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(),
                    MatchMode.START));
        }

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<EstadoModel> obterTodos()
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                EstadoModel.class).list();
    }

    @HibernateTransaction
    public void excluirTodos(String[] array) throws SystemException {

        EstadoModel model = null;

        for (String id : array) {
            model = new EstadoModel();
            model.setId(id);
            HibernateUtil.getCurrentSession().delete(model);
        }

    }
}
