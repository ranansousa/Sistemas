package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.EnderecoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
public class EnderecoDAO implements EnderecoDAOIf {

    @HibernateTransaction
    public void inserir(EnderecoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(EnderecoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public EnderecoModel consultarPorPk(EnderecoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                EnderecoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("estado", FetchMode.JOIN);
        criteria.setFetchMode("cidade", FetchMode.JOIN);
        criteria.setFetchMode("bairro", FetchMode.JOIN);

        return (EnderecoModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(EnderecoModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public EnderecoModel consultarPorTemplate(EnderecoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                EnderecoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("estado", FetchMode.JOIN);
        criteria.setFetchMode("cidade", FetchMode.JOIN);
        criteria.setFetchMode("bairro", FetchMode.JOIN);

        return (EnderecoModel) criteria.uniqueResult();

    }

    public List<EnderecoModel> obterPorFiltro(EnderecoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                EnderecoModel.class);

        if (model.getLogradouro() != null && !model.getLogradouro().isEmpty()) {
            criteria.add(Restrictions.like("logradouro", model.getLogradouro(),
                    MatchMode.START));
        }

        criteria.setFetchMode("estado", FetchMode.JOIN);
        criteria.setFetchMode("cidade", FetchMode.JOIN);
        criteria.setFetchMode("bairro", FetchMode.JOIN);


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("logradouro"));

        return criteria.list();

    }

    public List<EnderecoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                EnderecoModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<EnderecoModel> coll)
            throws SystemException {

        for (EnderecoModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }
}
