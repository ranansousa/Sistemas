package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TipoCedenteDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoCedenteModel;
import br.com.pempec.finance.utils.Repopulador;
import br.com.pempec.finance.utils.Tela;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class TipoCedenteDAO implements TipoCedenteDAOIf {

    @HibernateTransaction
    public void inserir(TipoCedenteModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        //Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPOS_DE_CEDENTES, model);
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void alterar(TipoCedenteModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);

        //Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPOS_DE_CEDENTES, model);
        Repopulador.repopulador();

    }

    public TipoCedenteModel consultarPorPk(TipoCedenteModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TipoCedenteModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TipoCedenteModel) criteria.uniqueResult();


    }

    public List<TipoCedenteModel> obterPorFiltro(TipoCedenteModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TipoCedenteModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<TipoCedenteModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TipoCedenteModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TipoCedenteModel> coll)
            throws SystemException {

        for (TipoCedenteModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);

        }

        //Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPOS_DE_CEDENTES, coll);

    }

    public TipoCedenteModel consultarPorTemplate(TipoCedenteModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TipoCedenteModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TipoCedenteModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(TipoCedenteModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPOS_DE_CEDENTES, model);
        Repopulador.repopulador();

    }
}
