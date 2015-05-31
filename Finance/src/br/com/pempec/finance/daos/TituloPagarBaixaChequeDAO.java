package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloPagarBaixaChequeDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class TituloPagarBaixaChequeDAO implements TituloPagarBaixaChequeDAOIf {

    @HibernateTransaction
    public void inserir(TituloPagarBaixaChequeModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TituloPagarBaixaChequeModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TituloPagarBaixaChequeModel consultarPorPk(TituloPagarBaixaChequeModel model)
            throws SystemException {
        return (TituloPagarBaixaChequeModel) HibernateUtil.getCurrentSession().get(
                TituloPagarBaixaChequeModel.class, model.getPk());
    }

    public List<TituloPagarBaixaChequeModel> obterPorFiltro(TituloPagarBaixaChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaChequeModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return criteria.list();

    }

    public List<TituloPagarBaixaChequeModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaChequeModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloPagarBaixaChequeModel> coll)
            throws SystemException {

        for (TituloPagarBaixaChequeModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(TituloPagarBaixaChequeModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloPagarBaixaChequeModel consultarPorTemplate(TituloPagarBaixaChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaChequeModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (TituloPagarBaixaChequeModel) criteria.uniqueResult();

    }

    public List<TituloPagarBaixaChequeModel> obterPorTituloPagarBaixa(TituloPagarBaixaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaChequeModel.class);

        criteria.add(Restrictions.eq("tituloPagarBaixa.pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagarBaixa", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);

        return criteria.list();

    }

    public List<TituloPagarBaixaChequeModel> obterPorCheque(ContaBancariaChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaChequeModel.class);

        criteria.add(Restrictions.eq("contaBancariaCheque.pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.createCriteria("tituloPagarBaixa").setFetchMode("titulo", FetchMode.JOIN);

        criteria.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);

        return criteria.list();

    }

    public List<TituloPagarBaixaChequeModel> obterPorChequesEmitidos(ContaBancariaModel model, OrganizacaoModel organizacaoModel)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaChequeModel.class);

        Criteria subCriteria = criteria.createCriteria("contaBancariaCheque");
        subCriteria.add(Restrictions.eq("status.pk.id", Constantes.STATUS_CHEQUE_EMITIDO));

        if (model != null && model.getPk() != null) {
            Criteria subCriteria2 = subCriteria.createCriteria("contaBancaria");
            subCriteria2.add(Restrictions.eq("pk.id", model.getPk().getId()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", organizacaoModel.getId()));

        subCriteria.addOrder(Order.asc("dataPrevisaoCompensacao"));

        criteria.setFetchMode("contaBancariaCheque", FetchMode.JOIN);
        criteria.setFetchMode("tituloPagarBaixa", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);

        return criteria.list();

    }
}
