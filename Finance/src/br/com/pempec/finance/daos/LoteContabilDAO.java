package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.LoteContabilDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

//EQUIPE PEMPEC
public class LoteContabilDAO implements LoteContabilDAOIf {

    @HibernateTransaction
    public void inserir(LoteContabilModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
    }

    @HibernateTransaction
    public void alterar(LoteContabilModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
    }

    public LoteContabilModel consultarPorPk(LoteContabilModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteContabilModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (LoteContabilModel) criteria.uniqueResult();

    }

    public List<LoteContabilModel> obterPorFiltro(LoteContabilModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteContabilModel.class);

        if (model.getLote() != null && !model.getLote().isEmpty()) {
            criteria.add(Restrictions.eq("lote", model.getLote()));
        }

        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.add(Restrictions.ne("pk.id", "ajuste"));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("lote"));


        return criteria.list();

    }

    public List<LoteContabilModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteContabilModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.ne("pk.id", "ajuste"));

        criteria.addOrder(Order.desc("dataRegistro"));
        criteria.addOrder(Order.desc("lote"));
        criteria.addOrder(Order.asc("status"));


        return criteria.list();


    }

    @HibernateTransaction
    public void excluirTodos(Collection<LoteContabilModel> coll)
            throws SystemException {

        for (LoteContabilModel model : coll) {
            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(LoteContabilModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);

    }

    public LoteContabilModel consultarPorTemplate(LoteContabilModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteContabilModel.class);

        if (model.getLote() != null && !model.getLote().isEmpty()) {
            criteria.add(Restrictions.eq("lote", model.getLote()));
        }

        criteria.setMaxResults(1);
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return (LoteContabilModel) criteria.uniqueResult();

    }
}
