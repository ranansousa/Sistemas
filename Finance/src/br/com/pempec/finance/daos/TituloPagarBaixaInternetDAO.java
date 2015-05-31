package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloPagarBaixaInternetDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class TituloPagarBaixaInternetDAO implements TituloPagarBaixaInternetDAOIf {

    @HibernateTransaction
    public void inserir(TituloPagarBaixaInternetModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TituloPagarBaixaInternetModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public TituloPagarBaixaInternetModel consultarPorPk(TituloPagarBaixaInternetModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaInternetModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);

        criteria.setFetchMode("tituloPagarBaixa", FetchMode.JOIN);
        criteria.setFetchMode("bancoDestino", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);

        criteria.setMaxResults(1);

        return (TituloPagarBaixaInternetModel) criteria.uniqueResult();

    }

    public List<TituloPagarBaixaInternetModel> obterPorFiltro(TituloPagarBaixaInternetModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaInternetModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return criteria.list();

    }

    public List<TituloPagarBaixaInternetModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaInternetModel.class).add(
                Restrictions.eq("pk.organizacao.id", model.getId())).list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloPagarBaixaInternetModel> coll)
            throws SystemException {

        for (TituloPagarBaixaInternetModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(TituloPagarBaixaInternetModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloPagarBaixaInternetModel consultarPorTemplate(TituloPagarBaixaInternetModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaInternetModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);

        criteria.setFetchMode("tituloPagarBaixa", FetchMode.JOIN);
        criteria.setFetchMode("bancoDestino", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);

        return (TituloPagarBaixaInternetModel) criteria.uniqueResult();

    }

    public List<TituloPagarBaixaInternetModel> obterPorTituloPagarBaixa(TituloPagarBaixaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloPagarBaixaInternetModel.class);

        criteria.add(Restrictions.eq("tituloPagarBaixa.pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);

        criteria.setFetchMode("tituloPagarBaixa", FetchMode.JOIN);
        criteria.setFetchMode("bancoDestino", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);

        return criteria.list();

    }
}
