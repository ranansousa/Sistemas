package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ContaBancariaTransferenciaDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaTransferencia;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
public class ContaBancariaTransferenciaDAO implements ContaBancariaTransferenciaDAOIf {

    @HibernateTransaction
    public void inserir(ContaBancariaTransferenciaModel model) throws SystemException {

        if (model.getContaBancariaCreditoModel().getPk().getId() != null) {
            ContaBancariaCreditoModel contaDestino = model.getContaBancariaCreditoModel();

            HibernateUtil.getCurrentSession().save(contaDestino);

        }

        if (model.getContaBancariaDebitoModel().getPk().getId() != null) {
            ContaBancariaDebitoModel contaOrigem = model.getContaBancariaDebitoModel();

            HibernateUtil.getCurrentSession().save(contaOrigem);

        }


        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(ContaBancariaTransferenciaModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public ContaBancariaTransferenciaModel consultarPorPk(ContaBancariaTransferenciaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteriaCredito = criteria.createCriteria("contaBancariaCreditoModel");
        Criteria subCriteriaDebito = criteria.createCriteria("contaBancariaDebitoModel");

        Criteria subCriteriaContaBancariaCredito = subCriteriaCredito.createCriteria("contaBancaria");
        Criteria subCriteriaContaBancariaDebito = subCriteriaDebito.createCriteria("contaBancaria");

        subCriteriaContaBancariaCredito.createCriteria("banco");
        subCriteriaContaBancariaDebito.createCriteria("banco");

        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancariaModel", FetchMode.JOIN);
        criteria.setFetchMode("usuarioModel", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.setMaxResults(1);

        return (ContaBancariaTransferenciaModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(ContaBancariaTransferenciaModel model)
            throws SystemException {

        if (model.getContaBancariaCreditoModel() != null && model.getContaBancariaDebitoModel() != null) {


            if (model.getContaBancariaCreditoModel().getPk().getId() != null) {

                ContaBancariaCreditoModel contaCredito = model.getContaBancariaCreditoModel();

                HibernateUtil.getCurrentSession().delete(contaCredito);

            }

            if (model.getContaBancariaDebitoModel().getPk().getId() != null) {

                ContaBancariaDebitoModel contaDebito = model.getContaBancariaDebitoModel();

                HibernateUtil.getCurrentSession().delete(contaDebito);

            }


            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }




            HibernateUtil.getCurrentSession().delete(model);
            Repopulador.repopulador();

        }

    }

    public List<ContaBancariaTransferenciaModel> obterPorFiltro(ContaBancariaTransferenciaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);

        if (model.getPk().getId() != null && !model.getPk().getId().isEmpty()) {
            criteria.add(Restrictions.like("pk.id", model.getPk().getId(),
                    MatchMode.START));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("contaBancariaCheque"));

        return criteria.list();

    }

    public List<ContaBancariaTransferenciaModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("contaBancariaCheque"));

        return criteria.list();

    }

    public List<ContaBancariaTransferenciaModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.asc("dataMovimento"));

        criteria.addOrder(Order.asc("identificacao"));



        return criteria.list();



    }

    @HibernateTransaction
    public void excluirTodos(Collection<ContaBancariaTransferenciaModel> coll)
            throws SystemException {

        for (ContaBancariaTransferenciaModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    public ContaBancariaTransferenciaModel consultarPorTemplate(ContaBancariaTransferenciaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);

        if (model.getIdentificacao() != null) {
            criteria.add(Restrictions.eq("identificacao", model.getIdentificacao()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteriaCredito = criteria.createCriteria("contaBancariaCreditoModel");
        Criteria subCriteriaDebito = criteria.createCriteria("contaBancariaDebitoModel");

        Criteria subCriteriaContaBancariaCredito = subCriteriaCredito.createCriteria("contaBancaria");
        Criteria subCriteriaContaBancariaDebito = subCriteriaDebito.createCriteria("contaBancaria");

        subCriteriaContaBancariaCredito.createCriteria("banco");
        subCriteriaContaBancariaDebito.createCriteria("banco");

        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancariaModel", FetchMode.JOIN);
        criteria.setFetchMode("usuarioModel", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.setMaxResults(1);

        return (ContaBancariaTransferenciaModel) criteria.uniqueResult();

    }

    public List<ContaBancariaTransferenciaModel> obterTodosLancamentosTesourariaPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);

        criteria.add(Restrictions.eq("tipoLancamento", tipo));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.asc("identificacao"));

        return criteria.list();

    }

    public List<ContaBancariaTransferenciaModel> obterContaBancariaTransferenciaExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.between("dataMovimento", dataInicial, dataFinal));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        Criteria subCriteria1 = criteria.createCriteria("contaBancariaCreditoModel", CriteriaSpecification.LEFT_JOIN);

        Criteria subCriteria2 = criteria.createCriteria("contaBancariaDebitoModel", CriteriaSpecification.LEFT_JOIN);

        Criteria subCriteria3 = criteria.createCriteria("tipoOperacaoBancariaModel", CriteriaSpecification.LEFT_JOIN);

        subCriteria1.setFetchMode("contaBancaria", FetchMode.JOIN);

        subCriteria2.setFetchMode("contaBancaria", FetchMode.JOIN);

        subCriteria3.setFetchMode("contaContabil", FetchMode.JOIN);

        return criteria.list();


    }

    public List<ContaBancariaTransferenciaModel> obterContaBancariaTransferenciaExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.between("dataMovimento", dataInicial, dataFinal));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        Criteria subCriteria1 = criteria.createCriteria("contaBancariaCreditoModel", CriteriaSpecification.LEFT_JOIN);

        Criteria subCriteria2 = criteria.createCriteria("contaBancariaDebitoModel", CriteriaSpecification.LEFT_JOIN);

        Criteria subCriteria3 = criteria.createCriteria("tipoOperacaoBancariaModel", CriteriaSpecification.LEFT_JOIN);

        subCriteria1.setFetchMode("contaBancaria", FetchMode.JOIN);

        subCriteria2.setFetchMode("contaBancaria", FetchMode.JOIN);

        subCriteria3.setFetchMode("contaContabil", FetchMode.JOIN);

        return criteria.list();

    }

    public List<ContaBancariaTransferenciaModel> obterTitulosExportados(LoteContabilModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);

        criteria.add(Restrictions.eq("loteContabil.pk.id", model.getPk().getId()));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria1 = criteria.createCriteria("contaBancariaCreditoModel", CriteriaSpecification.LEFT_JOIN);

        Criteria subCriteria2 = criteria.createCriteria("contaBancariaDebitoModel", CriteriaSpecification.LEFT_JOIN);

        Criteria subCriteria3 = criteria.createCriteria("tipoOperacaoBancariaModel", CriteriaSpecification.LEFT_JOIN);

        subCriteria1.setFetchMode("contaBancaria", FetchMode.JOIN);

        subCriteria2.setFetchMode("contaBancaria", FetchMode.JOIN);

        subCriteria3.setFetchMode("contaContabil", FetchMode.JOIN);

        return criteria.list();
    }

    public List<ContaBancariaTransferenciaModel> obterRelatorio(FilterReportContaBancariaTransferencia filtro)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        if (filtro.getContaBancariaCreditoModel() != null && !filtro.getContaBancariaCreditoModel().isEmpty()) {
            criteria.add(Restrictions.eq("contaBancariaCreditoModel.pk.id", filtro.getContaBancariaCreditoModel()));
        }

        if (filtro.getContaBancariaDebitoModel() != null && !filtro.getContaBancariaDebitoModel().isEmpty()) {
            criteria.add(Restrictions.eq("contaBancariaDebitoModel.pk.id", filtro.getContaBancariaDebitoModel()));
        }

        if (filtro.getLoteContabil() != null && !filtro.getLoteContabil().isEmpty()) {
            criteria.add(Restrictions.eq("loteContabil.pk.id", filtro.getLoteContabil()));
        }

        if (filtro.getTipoOperacaoBancariaModel() != null && !filtro.getTipoOperacaoBancariaModel().isEmpty()) {
            criteria.add(Restrictions.eq("tipoOperacaoBancariaModel.pk.id", filtro.getTipoOperacaoBancariaModel()));
        }

        if (filtro.getResponsavel() != null && !filtro.getResponsavel().isEmpty()) {
            criteria.add(Restrictions.eq("responsavel.pk.id", filtro.getResponsavel()));
        }

        if (filtro.getUsuario() != null && !filtro.getUsuario().isEmpty()) {
            criteria.add(Restrictions.eq("usuario.pk.id", filtro.getUsuario()));
        }

        criteria.add(Restrictions.between("dataMovimento", filtro.getDataInicial(), filtro.getDataFinal()));

        criteria.setFetchMode("contaBancariaCreditoModel", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaDebitoModel", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancariaModel", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataMovimento"));
        criteria.addOrder(Order.asc("valor"));

        return criteria.list();

    }

    public List<ContaBancariaTransferenciaModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaTransferenciaModel.class);

        criteria.add(Restrictions.eq("dataRegistro", dataInicial));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        return criteria.list();

    }
}
