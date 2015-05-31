package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.BancoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.utils.Repopulador;
import br.com.pempec.finance.utils.Tela;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class BancoDAO implements BancoDAOIf {

    @HibernateTransaction
    public void inserir(BancoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador(Tela.TELA_PARAMETROS_BANCOS, model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(BancoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador(Tela.TELA_PARAMETROS_BANCOS, model);
        Repopulador.repopulador();
    }

    public BancoModel consultarPorPk(BancoModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BancoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getId()));

        criteria.setMaxResults(1);

        return (BancoModel) criteria.uniqueResult();
    }

    public List<BancoModel> obterPorFiltro(BancoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BancoModel.class);

        if (model.getNomeBanco() != null && !model.getNomeBanco().isEmpty()) {
            criteria.add(Restrictions.like("nomeBanco", model.getNomeBanco(),
                    MatchMode.START));
        }

        criteria.addOrder(Order.asc("nomeBanco"));

        return criteria.list();

    }

    public List<BancoModel> obterTodos() throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BancoModel.class);

        criteria.addOrder(Order.asc("nomeBanco"));

        return criteria.list();

    }

    @HibernateTransaction
    public void excluirTodos(Collection<BancoModel> coll)
            throws SystemException {

        for (BancoModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(BancoModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador(Tela.TELA_PARAMETROS_BANCOS, model);
        Repopulador.repopulador();
    }

    public BancoModel consultarPorTemplate(BancoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                BancoModel.class);

        if (model.getNomeBanco() != null && !model.getNomeBanco().isEmpty()) {
            criteria.add(Restrictions.eq("nomeBanco", model.getNomeBanco()));
        }

        criteria.setMaxResults(1);

        return (BancoModel) criteria.uniqueResult();

    }
}
