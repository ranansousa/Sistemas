package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.CidadeDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.CidadeModel;
import br.com.pempec.finance.models.EstadoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CidadeDAO implements CidadeDAOIf {

    @HibernateTransaction
    public void inserir(CidadeModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(CidadeModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public CidadeModel consultarPorPk(CidadeModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CidadeModel.class);

        criteria.add(Restrictions.eq("id", model.getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("estado", FetchMode.JOIN);

        return (CidadeModel) criteria.uniqueResult();

    }

    public List<CidadeModel> obterPorFiltro(CidadeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CidadeModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(),
                    MatchMode.START));
        }
        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<CidadeModel> obterPorEstado(EstadoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CidadeModel.class);

        if (model.getId() != null && !model.getId().isEmpty()) {
            criteria.add(Restrictions.eq("estado.id", model.getId()));
        }

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();
    }

    public List<CidadeModel> obterTodos()
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                CidadeModel.class).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<CidadeModel> coll)
            throws SystemException {

        for (CidadeModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(CidadeModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public CidadeModel consultarPorTemplate(CidadeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CidadeModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        criteria.setMaxResults(1);

        criteria.setFetchMode("estado", FetchMode.JOIN);

        return (CidadeModel) criteria.uniqueResult();

    }
}
