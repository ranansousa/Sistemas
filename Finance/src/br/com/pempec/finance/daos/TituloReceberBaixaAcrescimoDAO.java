package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloReceberBaixaAcrescimoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
public class TituloReceberBaixaAcrescimoDAO implements TituloReceberBaixaAcrescimoDAOIf {

    @HibernateTransaction
    public void inserir(TituloReceberBaixaAcrescimoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TituloReceberBaixaAcrescimoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TituloReceberBaixaAcrescimoModel consultarPorPk(TituloReceberBaixaAcrescimoModel model)
            throws SystemException {
        return (TituloReceberBaixaAcrescimoModel) HibernateUtil.getCurrentSession().get(
                TituloReceberBaixaAcrescimoModel.class, model.getPk());
    }

    public List<TituloReceberBaixaAcrescimoModel> obterPorFiltro(TituloReceberBaixaAcrescimoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaAcrescimoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return criteria.list();

    }

    public List<TituloReceberBaixaAcrescimoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaAcrescimoModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloReceberBaixaAcrescimoModel> coll)
            throws SystemException {

        for (TituloReceberBaixaAcrescimoModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(TituloReceberBaixaAcrescimoModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloReceberBaixaAcrescimoModel consultarPorTemplate(TituloReceberBaixaAcrescimoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaAcrescimoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloReceberBaixaAcrescimoModel) criteria.uniqueResult();

    }
}
