package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloReceberBaixaFormaPagamentoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
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
public class TituloReceberBaixaFormaPagamentoDAO implements TituloReceberBaixaFormaPagamentoDAOIf {

    @HibernateTransaction
    public void inserir(TituloReceberBaixaFormaPagamentoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TituloReceberBaixaFormaPagamentoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TituloReceberBaixaFormaPagamentoModel consultarPorPk(TituloReceberBaixaFormaPagamentoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaFormaPagamentoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getForma().getDescricao()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("tituloReceberBaixaModel", FetchMode.JOIN);
        criteria.setFetchMode("forma", FetchMode.JOIN);

        return (TituloReceberBaixaFormaPagamentoModel) criteria.uniqueResult();
    }

    public List<TituloReceberBaixaFormaPagamentoModel> obterPorFiltro(TituloReceberBaixaFormaPagamentoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaFormaPagamentoModel.class);

        if (model.getForma().getDescricao() != null && !model.getForma().getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getForma().getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<TituloReceberBaixaFormaPagamentoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaFormaPagamentoModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloReceberBaixaFormaPagamentoModel> coll)
            throws SystemException {

        for (TituloReceberBaixaFormaPagamentoModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(TituloReceberBaixaFormaPagamentoModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloReceberBaixaFormaPagamentoModel consultarPorTemplate(TituloReceberBaixaFormaPagamentoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaFormaPagamentoModel.class);

        if (model.getForma().getDescricao() != null && !model.getForma().getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getForma().getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("tituloReceberBaixaModel", FetchMode.JOIN);
        criteria.setFetchMode("forma", FetchMode.JOIN);

        return (TituloReceberBaixaFormaPagamentoModel) criteria.uniqueResult();

    }
}
