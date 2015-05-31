package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloReceberBaixaDeducaoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
public class TituloReceberBaixaDeducaoDAO implements TituloReceberBaixaDeducaoDAOIf {

    @HibernateTransaction
    public void inserir(TituloReceberBaixaDeducaoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TituloReceberBaixaDeducaoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TituloReceberBaixaDeducaoModel consultarPorPk(TituloReceberBaixaDeducaoModel model)
            throws SystemException {
        return (TituloReceberBaixaDeducaoModel) HibernateUtil.getCurrentSession().get(
                TituloReceberBaixaDeducaoModel.class, model.getPk());
    }

    public List<TituloReceberBaixaDeducaoModel> obterPorFiltro(TituloReceberBaixaDeducaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaDeducaoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return criteria.list();

    }

    public List<TituloReceberBaixaDeducaoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaDeducaoModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloReceberBaixaDeducaoModel> coll)
            throws SystemException {

        for (TituloReceberBaixaDeducaoModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(TituloReceberBaixaDeducaoModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloReceberBaixaDeducaoModel consultarPorTemplate(TituloReceberBaixaDeducaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaDeducaoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloReceberBaixaDeducaoModel) criteria.uniqueResult();

    }
}
