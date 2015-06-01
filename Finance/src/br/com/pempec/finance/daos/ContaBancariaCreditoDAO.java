package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ContaBancariaCreditoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaCredito;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class ContaBancariaCreditoDAO implements ContaBancariaCreditoDAOIf {

    @HibernateTransaction
    public void inserir(ContaBancariaCreditoModel model) throws SystemException {

        if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(ContaBancariaCreditoModel model) throws SystemException {

        if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public ContaBancariaCreditoModel consultarPorPk(ContaBancariaCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        if (model.getPk().getId() != null) {
            criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));
        }

        criteria.setMaxResults(1);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceber", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (ContaBancariaCreditoModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(ContaBancariaCreditoModel model)
            throws SystemException {
        if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();

    }

    public ContaBancariaCreditoModel consultarPorTemplate(ContaBancariaCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        if (model.getIdentificacao() != null) {
            criteria.add(Restrictions.eq("identificacao", model.getIdentificacao()));
        } else {
            if (model.getPk().getId() != null) {
                criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));
            }

        }

        criteria.setMaxResults(1);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceber", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria1 = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria1.setFetchMode("banco", FetchMode.JOIN);

        return (ContaBancariaCreditoModel) criteria.uniqueResult();

    }

    public List<ContaBancariaCreditoModel> obterPorFiltro(ContaBancariaCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        if (model.getPk().getId() != null && !model.getPk().getId().isEmpty()) {
            criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));
        }

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();

    }

    public ContaBancariaCreditoModel obterPorCheque(ContaBancariaCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        if (model.getPk().getId() != null && !model.getPk().getId().isEmpty()) {
            criteria.add(Restrictions.like("pk.id", model.getPk().getId(),
                    MatchMode.START));
        }

        criteria.setMaxResults(1);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceber", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (ContaBancariaCreditoModel) criteria.uniqueResult();

    }

    public List<ContaBancariaCreditoModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));
        }

        //Colocar depois
//        criteria.add(Restrictions.isNotNull("contaBancariaCheque"));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceber", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();

    }

    public List<ContaBancariaCreditoModel> obterPorContaBancariaStatus(ContaBancariaModel model, TipoStatusModel status)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));
        }

        criteria.add(Restrictions.eq("status.pk.id", status.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceber", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);

        criteria.addOrder(Order.asc("numeroCheque"));
        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();

    }

    public List<ContaBancariaCreditoModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<ContaBancariaCreditoModel> coll)
            throws SystemException {

        for (ContaBancariaCreditoModel model : coll) {
            if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    public List<ContaBancariaCreditoModel> obterTodosLancamentosBancariosPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        criteria.add(Restrictions.eq("tipoLancamento", tipo));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();

    }

    public ContaBancariaCreditoModel consultarPorTemplateLancamentoBancario(ContaBancariaCreditoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        if (model.getIdentificacao() != null) {
            criteria.add(Restrictions.eq("identificacao", model.getIdentificacao()));
        }

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceber", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("loteContabil", FetchMode.JOIN);

        return (ContaBancariaCreditoModel) criteria.uniqueResult();

    }

    public List<ContaBancariaCreditoModel> obterTodosLancamentosTesourariaPorTipo(OrganizacaoModel model, String tipo)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        criteria.add(Restrictions.eq("tipoLancamento", tipo));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancaria", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();

    }

    public List<ContaBancariaCreditoModel> obterContaBancariaCreditoExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.isNull("tituloReceber.pk.id"));

        criteria.add(Restrictions.ne("tipoOperacaoBancaria.pk.id", Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_CONTAS));

        criteria.add(Restrictions.between("dataMovimento", dataInicial, dataFinal));

        Criteria subCriteria1 = criteria.createCriteria("tipoOperacaoBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria1.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();

    }

    public List<ContaBancariaCreditoModel> obterContaBancariaCreditoExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.add(Restrictions.isNull("tituloReceber.pk.id"));

        criteria.add(Restrictions.isNull("loteContabil.pk.id"));

        criteria.add(Restrictions.ne("tipoOperacaoBancaria.pk.id", Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_CONTAS));

        criteria.add(Restrictions.between("dataMovimento", dataInicial, dataFinal));

        Criteria subCriteria1 = criteria.createCriteria("tipoOperacaoBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria1.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();

    }

    public List<ContaBancariaCreditoModel> obterTitulosExportados(LoteContabilModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        criteria.add(Restrictions.eq("loteContabil.pk.id", model.getPk().getId()));
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria1 = criteria.createCriteria("tipoOperacaoBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria1.setFetchMode("contaContabil", FetchMode.JOIN);

        criteria.setFetchMode("contaBancaria", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));

        return criteria.list();
    }

    public List<ContaBancariaCreditoModel> obterRelatorio(FilterReportContaBancariaCredito filtro)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        if (filtro.getContaBancaria() != null && !filtro.getContaBancaria().isEmpty()) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", filtro.getContaBancaria()));
        }

        if (filtro.getTipoOperacaoBancaria() != null && !filtro.getTipoOperacaoBancaria().isEmpty()) {
            criteria.add(Restrictions.eq("tipoOperacaoBancaria.pk.id", filtro.getTipoOperacaoBancaria()));
        }

        if (filtro.getResponsavel() != null && !filtro.getResponsavel().isEmpty()) {
            criteria.add(Restrictions.eq("responsavel.pk.id", filtro.getResponsavel()));
        }

        criteria.add(Restrictions.between("dataMovimento", filtro.getDataInicial(), filtro.getDataFinal()));

        Criteria subCriteria = criteria.createCriteria("tituloReceber", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("sacado", FetchMode.JOIN);

        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.asc("valor"));

        return criteria.list();

    }

    public Double obterSaldoAnterior(FilterReportContaBancariaCredito filter) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("select sum(bc.valor) from br.com.pempec.finance.models.ContaBancariaCreditoModel bc "
                + " WHERE bc.dataMovimento < :data and bc.pk.organizacao.id = :org and bc.contaBancaria.pk.id = :conta ");

        query.setDate("data", filter.getDataInicial());
        query.setParameter("org", filter.getOrganizacao());
        query.setParameter("conta", filter.getContaBancaria());

        return (Double) query.uniqueResult();

    }

    public Double obterTotal(ContaBancariaModel contaBancaria) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("select sum(bc.valor) from br.com.pempec.finance.models.ContaBancariaCreditoModel bc "
                + " WHERE bc.pk.organizacao.id = :org and bc.contaBancaria.pk.id = :conta ");

        query.setParameter("org", contaBancaria.getPk().getOrganizacao().getId());
        query.setParameter("conta", contaBancaria.getPk().getId());

        return (Double) query.uniqueResult();

    }

    public List<ContaBancariaCreditoModel> obterTodosPorData(OrganizacaoModel model, Date dataInicial)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaCreditoModel.class);

        criteria.add(Restrictions.eq("dataRegistro", dataInicial));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataMovimento"));
        criteria.addOrder(Order.asc("identificacao"));

        return criteria.list();

    }
}
