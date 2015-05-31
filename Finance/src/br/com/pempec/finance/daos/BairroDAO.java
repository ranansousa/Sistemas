package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.BairroDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.BairroModel;
import br.com.pempec.finance.models.CidadeModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class BairroDAO implements BairroDAOIf {

    @HibernateTransaction
    public void inserir(BairroModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(BairroModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public BairroModel consultarPorPk(BairroModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BairroModel.class);

        criteria.add(Restrictions.eq("id", model.getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("descricao", FetchMode.JOIN);

        return (BairroModel) criteria.uniqueResult();

    }

    public List<BairroModel> obterPorFiltro(BairroModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BairroModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(),
                    MatchMode.START));
        }

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<BairroModel> obterPorCidade(CidadeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BairroModel.class);

        criteria.add(Restrictions.eq("cidade.id", model.getId()));

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();
    }

    public List<BairroModel> obterTodos()
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                BairroModel.class).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<BairroModel> coll)
            throws SystemException {

        for (BairroModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(BairroModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public BairroModel consultarPorTemplate(BairroModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BairroModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        criteria.setMaxResults(1);

        criteria.setFetchMode("descricao", FetchMode.JOIN);

        return (BairroModel) criteria.uniqueResult();

    }
}
