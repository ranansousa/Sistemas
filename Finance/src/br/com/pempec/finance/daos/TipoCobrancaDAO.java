package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TipoCobrancaDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoCobrancaModel;
import br.com.pempec.finance.utils.Repopulador;
import br.com.pempec.finance.utils.Tela;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class TipoCobrancaDAO implements TipoCobrancaDAOIf {

    @HibernateTransaction
    public void inserir(TipoCobrancaModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPO_DE_COBRANCAS, model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TipoCobrancaModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPO_DE_COBRANCAS, model);
        Repopulador.repopulador();
    }

    public TipoCobrancaModel consultarPorPk(TipoCobrancaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TipoCobrancaModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TipoCobrancaModel) criteria.uniqueResult();

    }

    public List<TipoCobrancaModel> obterPorFiltro(TipoCobrancaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TipoCobrancaModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<TipoCobrancaModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TipoCobrancaModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TipoCobrancaModel> coll)
            throws SystemException {

        for (TipoCobrancaModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPO_DE_COBRANCAS, null);
        Repopulador.repopulador();

    }

    public TipoCobrancaModel consultarPorTemplate(TipoCobrancaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TipoCobrancaModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TipoCobrancaModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(TipoCobrancaModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPO_DE_COBRANCAS, null);
        Repopulador.repopulador();
    }
}
