package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.FormaPagamentoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.utils.Repopulador;
import br.com.pempec.finance.utils.Tela;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class FormaPagamentoDAO implements FormaPagamentoDAOIf {

    @HibernateTransaction
    public void inserir(FormaPagamentoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_FORMAS_DE_PAGAMENTOS, model);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void alterar(FormaPagamentoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador(Tela.TELA_PARAMETROS_FORMAS_DE_PAGAMENTOS, model);
        Repopulador.repopulador();
    }

    public FormaPagamentoModel consultarPorPk(FormaPagamentoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                FormaPagamentoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        return (FormaPagamentoModel) criteria.uniqueResult();

    }

    public List<FormaPagamentoModel> obterPorFiltro(FormaPagamentoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                FormaPagamentoModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<FormaPagamentoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                FormaPagamentoModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<FormaPagamentoModel> coll)
            throws SystemException {

        for (FormaPagamentoModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador(Tela.TELA_PARAMETROS_FORMAS_DE_PAGAMENTOS, null);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void excluir(FormaPagamentoModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador(Tela.TELA_PARAMETROS_FORMAS_DE_PAGAMENTOS, null);
        Repopulador.repopulador();
    }

    public FormaPagamentoModel consultarPorTemplate(FormaPagamentoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                FormaPagamentoModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        return (FormaPagamentoModel) criteria.uniqueResult();

    }
}
