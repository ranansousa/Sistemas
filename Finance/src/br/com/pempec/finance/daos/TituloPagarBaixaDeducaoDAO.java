package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloPagarBaixaDeducaoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
public class TituloPagarBaixaDeducaoDAO implements TituloPagarBaixaDeducaoDAOIf {

    @HibernateTransaction
    public void inserir(TituloPagarBaixaDeducaoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TituloPagarBaixaDeducaoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TituloPagarBaixaDeducaoModel consultarPorPk(TituloPagarBaixaDeducaoModel model)
            throws SystemException {
        return (TituloPagarBaixaDeducaoModel) HibernateUtil.getCurrentSession().get(
                TituloPagarBaixaDeducaoModel.class, model.getPk());
    }

    public List<TituloPagarBaixaDeducaoModel> obterPorFiltro(TituloPagarBaixaDeducaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaDeducaoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return criteria.list();

    }

    public List<TituloPagarBaixaDeducaoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaDeducaoModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloPagarBaixaDeducaoModel> coll)
            throws SystemException {

        for (TituloPagarBaixaDeducaoModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(TituloPagarBaixaDeducaoModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloPagarBaixaDeducaoModel consultarPorTemplate(TituloPagarBaixaDeducaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaDeducaoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloPagarBaixaDeducaoModel) criteria.uniqueResult();

    }
}
