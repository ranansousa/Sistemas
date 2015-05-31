package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TipoNotaFiscalDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoNotaFiscalModel;
import br.com.pempec.finance.utils.Repopulador;
import br.com.pempec.finance.utils.Tela;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class TipoNotaFiscalDAO implements TipoNotaFiscalDAOIf {

    @HibernateTransaction
    public void inserir(TipoNotaFiscalModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);

        Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPOS_DE_NOTAS_FISCAIS, model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TipoNotaFiscalModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPOS_DE_NOTAS_FISCAIS, model);
        Repopulador.repopulador();
    }

    public TipoNotaFiscalModel consultarPorPk(TipoNotaFiscalModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TipoNotaFiscalModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TipoNotaFiscalModel) criteria.uniqueResult();

    }

    public List<TipoNotaFiscalModel> obterPorFiltro(TipoNotaFiscalModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TipoNotaFiscalModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<TipoNotaFiscalModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TipoNotaFiscalModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TipoNotaFiscalModel> coll)
            throws SystemException {

        for (TipoNotaFiscalModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

        Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPOS_DE_NOTAS_FISCAIS, null);
        Repopulador.repopulador();

    }

    public TipoNotaFiscalModel consultarPorTemplate(TipoNotaFiscalModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TipoNotaFiscalModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TipoNotaFiscalModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(TipoNotaFiscalModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador(Tela.TELA_PARAMETROS_TIPOS_DE_NOTAS_FISCAIS, null);
        Repopulador.repopulador();
    }
}
