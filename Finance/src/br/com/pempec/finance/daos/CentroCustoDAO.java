package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.CentroCustoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.CentroCustoModel;
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

public class CentroCustoDAO implements CentroCustoDAOIf {

    @HibernateTransaction
    public void inserir(CentroCustoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CENTRO_DE_CUSTOS, model);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void alterar(CentroCustoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CENTRO_DE_CUSTOS, model);
        Repopulador.repopulador();

    }

    public CentroCustoModel consultarPorPk(CentroCustoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CentroCustoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (CentroCustoModel) criteria.uniqueResult();

    }

    public List<CentroCustoModel> obterPorFiltro(CentroCustoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CentroCustoModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<CentroCustoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CentroCustoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<CentroCustoModel> coll)
            throws SystemException {

        for (CentroCustoModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CENTRO_DE_CUSTOS, null);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void excluir(CentroCustoModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_CENTRO_DE_CUSTOS, null);
        Repopulador.repopulador();

    }

    public CentroCustoModel consultarPorTemplate(CentroCustoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                CentroCustoModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (CentroCustoModel) criteria.uniqueResult();

    }
}
