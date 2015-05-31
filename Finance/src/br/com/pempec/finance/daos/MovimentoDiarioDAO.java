package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.MovimentoDiarioDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.reports.FilterReportMovimentoDiario;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * @author Pempec
 */
public class MovimentoDiarioDAO implements MovimentoDiarioDAOIf {

    @HibernateTransaction
    public void inserir(Collection<MovimentoDiarioModel> coll) throws SystemException {

        for (MovimentoDiarioModel movimentoDiarioModel : coll) {

            HibernateUtil.getCurrentSession().save(movimentoDiarioModel);
        }

    }

    @HibernateTransaction
    public void inserir(MovimentoDiarioModel model) throws SystemException {


        if (model.getNumeroMovimento() == null || model.getNumeroMovimento() == 0) {

            model.setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());
        }



        HibernateUtil.getCurrentSession().save(model);
    }

    @HibernateTransaction
    public void alterar(MovimentoDiarioModel model) throws SystemException {

        HibernateUtil.getCurrentSession().update(model);
    }

    public List<MovimentoDiarioModel> obterPorFiltro(MovimentoDiarioModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                MovimentoDiarioModel.class);

        if (model.getCodigo() != null && !model.getCodigo().isEmpty()) {
            criteria.add(Restrictions.like("codigo", model.getCodigo(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("dataRegistro"));
        criteria.addOrder(Order.asc("numeroMovimento"));
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return criteria.list();

    }

    public List<MovimentoDiarioModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                MovimentoDiarioModel.class);


        criteria.addOrder(Order.asc("dataRegistro"));
        criteria.addOrder(Order.asc("numeroMovimento"));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        return criteria.list();
    }

    public List<MovimentoDiarioModel> obterRelatorio(FilterReportMovimentoDiario filtro)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                MovimentoDiarioModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        if (filtro.getUsuario() != null) {
            criteria.add(Restrictions.eq("usuario.id", filtro.getUsuario()));
        }


        criteria.add(Restrictions.between("dataRegistro", filtro.getDataInicial(), filtro.getDataFinal()));

        criteria.setFetchMode("usuario", FetchMode.JOIN);


        criteria.addOrder(Order.asc("dataRegistro"));
        criteria.addOrder(Order.desc("numeroMovimento"));

        return criteria.list();

    }

    @HibernateTransaction
    public void excluirTodos(Collection<MovimentoDiarioModel> coll)
            throws SystemException {

        for (MovimentoDiarioModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }


    }

    @HibernateTransaction
    public void excluir(MovimentoDiarioModel model)
            throws SystemException {

        HibernateUtil.getCurrentSession().delete(model);
    }

    public MovimentoDiarioModel consultarPorPk(MovimentoDiarioModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                MovimentoDiarioModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (MovimentoDiarioModel) criteria.uniqueResult();

    }

    public MovimentoDiarioModel consultarPorTemplate(MovimentoDiarioModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                MovimentoDiarioModel.class);

        if (model.getCodigo() != null && !model.getCodigo().isEmpty()) {
            criteria.add(Restrictions.like("codigo", model.getCodigo(),
                    MatchMode.START));
        }

        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("usuario", FetchMode.JOIN);

        return (MovimentoDiarioModel) criteria.uniqueResult();

    }

    public List<MovimentoDiarioModel> obterTodosPorPeriodo(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                MovimentoDiarioModel.class);

        criteria.add(Restrictions.between("dataRegistro", dataInicial, dataFinal));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataRegistro"));


        return criteria.list();

    }

    public List<MovimentoDiarioModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                MovimentoDiarioModel.class);

        criteria.add(Restrictions.eq("dataRegistro", dataInicial));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.eq("usuario.id", Controller.getUsuarioLogado().getId()));

        return criteria.list();

    }
}//fim

