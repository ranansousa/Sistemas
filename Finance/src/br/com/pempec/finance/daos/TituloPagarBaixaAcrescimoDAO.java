package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloPagarBaixaAcrescimoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
public class TituloPagarBaixaAcrescimoDAO implements TituloPagarBaixaAcrescimoDAOIf {

    @HibernateTransaction
    public void inserir(TituloPagarBaixaAcrescimoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TituloPagarBaixaAcrescimoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TituloPagarBaixaAcrescimoModel consultarPorPk(TituloPagarBaixaAcrescimoModel model)
            throws SystemException {
        return (TituloPagarBaixaAcrescimoModel) HibernateUtil.getCurrentSession().get(
                TituloPagarBaixaAcrescimoModel.class, model.getPk());
    }

    public List<TituloPagarBaixaAcrescimoModel> obterPorFiltro(TituloPagarBaixaAcrescimoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaAcrescimoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return criteria.list();

    }

    public List<TituloPagarBaixaAcrescimoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaAcrescimoModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloPagarBaixaAcrescimoModel> coll)
            throws SystemException {

        for (TituloPagarBaixaAcrescimoModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(TituloPagarBaixaAcrescimoModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloPagarBaixaAcrescimoModel consultarPorTemplate(TituloPagarBaixaAcrescimoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaAcrescimoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloPagarBaixaAcrescimoModel) criteria.uniqueResult();

    }
}
