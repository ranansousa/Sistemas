package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.HistoricoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.HistoricoModel;
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
public class HistoricoDAO implements HistoricoDAOIf {

    @HibernateTransaction
    public void inserir(HistoricoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador(Tela.TELA_PARAMETROS_HISTORICOS, model);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void alterar(HistoricoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_HISTORICOS, model);
        Repopulador.repopulador();
    }

    public HistoricoModel consultarPorPk(HistoricoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                HistoricoModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setMaxResults(1);

        return (HistoricoModel) criteria.uniqueResult();

    }

    public List<HistoricoModel> obterPorFiltro(HistoricoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                HistoricoModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<HistoricoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                HistoricoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.asc("descricao"));

        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        return criteria.list();


    }

    public List<HistoricoModel> obterTodosPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                HistoricoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        String[] tipos = {"G", tipo.toUpperCase()};

        criteria.add(Restrictions.in("tipo", tipos));

        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<HistoricoModel> coll)
            throws SystemException {

        for (HistoricoModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador(Tela.TELA_PARAMETROS_HISTORICOS, null);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void excluir(HistoricoModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_HISTORICOS, null);
        Repopulador.repopulador();

    }

    public HistoricoModel consultarPorTemplate(HistoricoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                HistoricoModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("contaContabil", FetchMode.JOIN);

        return (HistoricoModel) criteria.uniqueResult();

    }
}
