package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.CartaoCreditoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.CartaoCreditoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.Repopulador;
import br.com.pempec.finance.utils.Tela;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class CartaoCreditoDAO implements CartaoCreditoDAOIf {

    @HibernateTransaction
    public void inserir(CartaoCreditoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }


        HibernateUtil.getCurrentSession().save(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CARTAO_CREDITO, model);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void alterar(CartaoCreditoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CARTAO_CREDITO, model);
        Repopulador.repopulador();

    }

    public CartaoCreditoModel consultarPorPk(CartaoCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CartaoCreditoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setMaxResults(1);

        return (CartaoCreditoModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(CartaoCreditoModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_BANCOS, null);
        Repopulador.repopulador();
    }

    public CartaoCreditoModel consultarPorTemplate(CartaoCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CartaoCreditoModel.class);

        if (model.getCartao() != null && !model.getCartao().isEmpty()) {
            criteria.add(Restrictions.like("cartao", model.getCartao(),
                    MatchMode.START));
        }

        criteria.setMaxResults(1);


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("cartao"));

        return (CartaoCreditoModel) criteria.uniqueResult();

    }

    public List<CartaoCreditoModel> obterPorFiltro(CartaoCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CartaoCreditoModel.class);


        if (model.getCartao() != null && !model.getCartao().isEmpty()) {
            criteria.add(Restrictions.like("cartao", model.getCartao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("cartao"));

        return criteria.list();

    }

    public List<CartaoCreditoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CartaoCreditoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.asc("cartao"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<CartaoCreditoModel> coll)
            throws SystemException {

        for (CartaoCreditoModel model : coll) {
            if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CARTAO_CREDITO, null);
        Repopulador.repopulador();

    }
}
