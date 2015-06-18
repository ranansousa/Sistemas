package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ContaBancariaChequeDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.FilterReportContaBancariaCheque;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Repopulador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC

public class ContaBancariaChequeDAO implements ContaBancariaChequeDAOIf {

    @HibernateTransaction
    public void gerarCheques(Collection<ContaBancariaChequeModel> coll) throws SystemException {

        boolean gerouMovimento = false;

        for (ContaBancariaChequeModel contaBancariaChequeModel : coll) {

            if (contaBancariaChequeModel.getMovimentoDiarioModel() != null
                    && !contaBancariaChequeModel.getMovimentoDiarioModel().getCodigo().isEmpty() && !gerouMovimento) {

                MovimentoDiarioModel mov = contaBancariaChequeModel.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

                gerouMovimento = true;

            }

            HibernateUtil.getCurrentSession().save(contaBancariaChequeModel);
        }
        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void inserir(ContaBancariaChequeModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(ContaBancariaChequeModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }


        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();


    }

    @HibernateTransaction
    public void alterar(Collection<ContaBancariaChequeModel> coll) throws SystemException {

        for (ContaBancariaChequeModel model : coll) {

            if (model.getMovimentoDiarioModel() != null && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().update(model);

        }

        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void alterarChequeAvulso(ContaBancariaChequeModel model, TituloPagarModel titulo) throws SystemException {


        if (model != null && titulo != null) {

            if (model.getValor() != null && model.getPortador() != null) {

                ContaBancariaChequeModel cheque = model;

                HibernateUtil.getCurrentSession().merge(cheque);

            }

            if (titulo.getChequeVinculado() != null) {

                TituloPagarModel tituloPagarModel = titulo;

                HibernateUtil.getCurrentSession().merge(tituloPagarModel);

            }

        }


        Repopulador.repopulador();


    }

    @HibernateTransaction
    public void cancelarChequeAvulso(ContaBancariaChequeModel model, TituloPagarModel titulo) throws SystemException {


        if (model != null && titulo != null) {

            if (model.getPk() != null && model.getPk().getId() != null) {

                ContaBancariaChequeModel cheque = model;

                cheque.setValor(0d);
                cheque.setPortador("");
                cheque.setDataCompensado(null);
                cheque.setDataEstornado(Controller.getDataServidorBD());
                cheque.setObservacao("Estornado por cheque avulso");

                HibernateUtil.getCurrentSession().merge(cheque);

            }

            if (titulo.getPk() != null && titulo.getPk().getId() != null) {

                TituloPagarModel tituloPagarModel = titulo;

                titulo.setChequeVinculado(null);

                titulo.setDataUltimaAtualizacao(Controller.getDataServidorBD());


                HibernateUtil.getCurrentSession().merge(tituloPagarModel);

            }

        }


        Repopulador.repopulador();


    }

    public ContaBancariaChequeModel consultarPorPk(ContaBancariaChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.setMaxResults(1);

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);

        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (ContaBancariaChequeModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluir(ContaBancariaChequeModel model)
            throws SystemException {
        //nao deleta exatamente.. troca o status
        if (model.getMovimentoDiarioModel() != null && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();

    }

    public ContaBancariaChequeModel consultarPorTemplate(ContaBancariaChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);

        if (model.getContaBancaria() != null 
                && model.getContaBancaria().getPk() !=null 
                && model.getContaBancaria().getPk().getId() !=null 
                && !model.getContaBancaria().getPk().getId().isEmpty() ) {
            
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getContaBancaria().getPk().getId()));
        }
        
        
        if (model.getNumeroCheque() != null && !model.getNumeroCheque().isEmpty()) {
            criteria.add(Restrictions.eq("numeroCheque", model.getNumeroCheque()));
        }
        

        criteria.setMaxResults(1);

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);

        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (ContaBancariaChequeModel) criteria.uniqueResult();

    }

    public List<ContaBancariaChequeModel> obterPorFiltro(ContaBancariaChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);

        if (model.getNumeroCheque() != null && !model.getNumeroCheque().isEmpty()) {
            criteria.add(Restrictions.like("numeroCheque", model.getNumeroCheque(),
                    MatchMode.START));
        }
        
        if (model.getContaBancaria() != null 
                && model.getContaBancaria().getPk() !=null 
                && model.getContaBancaria().getPk().getId() !=null 
                && !model.getContaBancaria().getPk().getId().isEmpty() ) {
            
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getContaBancaria().getPk().getId()));
        }
        

        criteria.setMaxResults(1);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.addOrder(Order.asc("numeroCheque"));

        return criteria.list();

    }

    public List<ContaBancariaChequeModel> obterPorContaBancaria(ContaBancariaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));
        }

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.addOrder(Order.asc("numeroCheque"));

        return criteria.list();

    }

    public List<ContaBancariaChequeModel> obterPorContaBancariaTodosNumeroCheque(ContaBancariaModel model, String numeroChequeInicial, String numeroChequeFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);

        if (model.getPk() != null) {

            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));

            criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));


            if (numeroChequeInicial != null && numeroChequeFinal != null) {

                criteria.add(Restrictions.between("numeroCheque", numeroChequeInicial, numeroChequeFinal));

            } else {

                criteria.add(Restrictions.like("numeroCheque", numeroChequeInicial, MatchMode.START));
            }



            Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

            subCriteria.setFetchMode("banco", FetchMode.JOIN);

        }


        criteria.addOrder(Order.asc("numeroCheque"));

        return criteria.list();

    }

    public List<ContaBancariaChequeModel> obterPorContaBancariaNumeroCheque(ContaBancariaModel model, String numeroChequeInicial, String numeroChequeFinal)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));
        }

        criteria.add(Restrictions.eq("status.pk.id", Constantes.STATUS_CHEQUE_EMITIDO));

        criteria.add(Restrictions.between("numeroCheque", numeroChequeInicial, numeroChequeFinal));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);


        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.addOrder(Order.asc("numeroCheque"));

        return criteria.list();

    }

    public List<ContaBancariaChequeModel> obterPorContaBancariaStatusEmitidoECompensado(ContaBancariaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));
        }

        Collection<String> status = new ArrayList<String>();

        status.add(Constantes.STATUS_CHEQUE_EMITIDO);

        status.add(Constantes.STATUS_CHEQUE_COMPENSADO);

        criteria.add(Restrictions.in("status.pk.id", status));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);


        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.addOrder(Order.asc("numeroCheque"));


        return criteria.list();

    }

    public List<ContaBancariaChequeModel> obterPorContaBancariaStatus(ContaBancariaModel model, Collection<TipoStatusModel> collStatus)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));
        }

        Collection<String> status = new ArrayList<String>();

        for (TipoStatusModel state : collStatus) {
            status.add(state.getPk().getId());
        }

        criteria.add(Restrictions.in("status.pk.id", status));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);


        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.addOrder(Order.asc("numeroCheque"));

        return criteria.list();

    }

    public List<ContaBancariaChequeModel> obterPorContaBancariaStatus(ContaBancariaModel model, TipoStatusModel status)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);

        if (model.getPk() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", model.getPk().getId()));
        }

        criteria.add(Restrictions.eq("status.pk.id", status.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);


        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.addOrder(Order.asc("numeroCheque"));

        return criteria.list();

    }

    public List<ContaBancariaChequeModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);


        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        Criteria subCriteria = criteria.createCriteria("contaBancaria", CriteriaSpecification.LEFT_JOIN);

        subCriteria.setFetchMode("banco", FetchMode.JOIN);


        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("status", FetchMode.JOIN);
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.addOrder(Order.asc("dataEmissao"));

        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<ContaBancariaChequeModel> coll)
            throws SystemException {

        for (ContaBancariaChequeModel model : coll) {

            if (!model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    public List<ContaBancariaChequeModel> obterRelatorio(FilterReportContaBancariaCheque filtro)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ContaBancariaChequeModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", filtro.getOrganizacao()));

        if (filtro.getContaBancaria() != null) {
            criteria.add(Restrictions.eq("contaBancaria.pk.id", filtro.getContaBancaria()));
        }

        criteria.add(Restrictions.eq("status.pk.id", filtro.getStatus()));

        criteria.addOrder(Order.asc("dataPrevisaoCompensacao"));

        return criteria.list();

    }

    public Double obterTotalChequesACompensar(ContaBancariaModel contaBancaria) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("select sum(bd.valor) from br.com.pempec.finance.models.ContaBancariaChequeModel bd "
                + " WHERE bd.pk.organizacao.id = :org and bd.status.pk.id = :status  and  bd.contaBancaria.pk.id = :conta ");

        query.setParameter("org", contaBancaria.getPk().getOrganizacao().getId());
        query.setParameter("conta", contaBancaria.getPk().getId());
        query.setParameter("status", Constantes.STATUS_CHEQUE_EMITIDO);

        return (Double) query.uniqueResult();

    }

    @HibernateTransaction
    public void zeraQtdImpressaoCheque() throws SystemException {

        try {

            Query query = HibernateUtil.getCurrentSession().createSQLQuery("update conta_bancaria_cheque c set c.qtd_impressao=0 where c.qtd_impressao is null");

            query.executeUpdate();


        } catch (Exception e) {
            throw new SystemException(e);
        }

    }
}
