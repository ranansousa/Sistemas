package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ContaBancariaDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.Repopulador;
import br.com.pempec.finance.utils.Tela;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class ContaBancariaDAO implements ContaBancariaDAOIf {

    @HibernateTransaction
    public void inserir(ContaBancariaModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
        Repopulador.repopulador(Tela.TELA_PARAMETROS_CONTAS_BANCARIAS, model);

    }

    @HibernateTransaction
    public void alterar(ContaBancariaModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
        Repopulador.repopulador(Tela.TELA_PARAMETROS_CONTAS_BANCARIAS, model);
    }

    public ContaBancariaModel consultarPorPk(ContaBancariaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("banco", FetchMode.JOIN);
        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        return (ContaBancariaModel) criteria.uniqueResult();

    }

    public List<ContaBancariaModel> obterPorFiltro(ContaBancariaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaModel.class);

        if (model.getConta() != null && !model.getConta().isEmpty()) {
            criteria.add(Restrictions.like("conta", model.getConta(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("conta"));

        return criteria.list();

    }

    public List<ContaBancariaModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).setFetchMode("contaContabil", FetchMode.JOIN).list();
    }

    public List<ContaBancariaModel> montarComboDestino(ContaBancariaModel contaOrigem)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaModel.class);

        if (contaOrigem != null && !contaOrigem.getPk().getId().isEmpty()) {
            criteria.add(Restrictions.ne("conta", contaOrigem.getConta()));
        }

        criteria.addOrder(Order.asc("conta"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<ContaBancariaModel> coll)
            throws SystemException {

        for (ContaBancariaModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(ContaBancariaModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
        Repopulador.repopulador(Tela.TELA_PARAMETROS_CONTAS_BANCARIAS, model);
    }

    public ContaBancariaModel consultarPorTemplate(ContaBancariaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaModel.class);

        if (model.getConta() != null && !model.getConta().isEmpty()) {
            criteria.add(Restrictions.eq("conta", model.getConta()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("banco", FetchMode.JOIN);
        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        return (ContaBancariaModel) criteria.uniqueResult();

    }
}
