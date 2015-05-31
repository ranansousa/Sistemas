package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloPagarBaixaFormaPagamentoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
public class TituloPagarBaixaFormaPagamentoDAO implements TituloPagarBaixaFormaPagamentoDAOIf {

    @HibernateTransaction
    public void inserir(TituloPagarBaixaFormaPagamentoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TituloPagarBaixaFormaPagamentoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TituloPagarBaixaFormaPagamentoModel consultarPorPk(TituloPagarBaixaFormaPagamentoModel model)
            throws SystemException {
        return (TituloPagarBaixaFormaPagamentoModel) HibernateUtil.getCurrentSession().get(
                TituloPagarBaixaFormaPagamentoModel.class, model.getPk());
    }

    public List<TituloPagarBaixaFormaPagamentoModel> obterPorFiltro(TituloPagarBaixaFormaPagamentoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaFormaPagamentoModel.class);

        if (model.getForma().getDescricao() != null && !model.getForma().getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getForma().getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<TituloPagarBaixaFormaPagamentoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaFormaPagamentoModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    public List<TituloPagarBaixaFormaPagamentoModel> obterTodosPorFormaPagto(FormaPagamentoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.add(Restrictions.eq("forma", model));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloPagarBaixaFormaPagamentoModel> coll)
            throws SystemException {

        for (TituloPagarBaixaFormaPagamentoModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(TituloPagarBaixaFormaPagamentoModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloPagarBaixaFormaPagamentoModel consultarPorTemplate(TituloPagarBaixaFormaPagamentoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaFormaPagamentoModel.class);

        if (model.getForma().getDescricao() != null && !model.getForma().getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getForma().getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("descricao"));

        return (TituloPagarBaixaFormaPagamentoModel) criteria.uniqueResult();

    }
}
