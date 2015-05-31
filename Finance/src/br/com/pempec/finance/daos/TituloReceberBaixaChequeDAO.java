package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TituloReceberBaixaChequeDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
@SuppressWarnings("unchecked")
public class TituloReceberBaixaChequeDAO implements TituloReceberBaixaChequeDAOIf {

    @HibernateTransaction
    public void inserir(TituloReceberBaixaChequeModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(TituloReceberBaixaChequeModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(Collection<TituloReceberBaixaChequeModel> titulos) throws SystemException {
        for (TituloReceberBaixaChequeModel tituloReceberBaixaChequeModel : titulos) {
            HibernateUtil.getCurrentSession().update(tituloReceberBaixaChequeModel);
        }

        Repopulador.repopulador();
    }

    public TituloReceberBaixaChequeModel consultarPorPk(TituloReceberBaixaChequeModel model)
            throws SystemException {
        return (TituloReceberBaixaChequeModel) HibernateUtil.getCurrentSession().get(
                TituloReceberBaixaChequeModel.class, model.getPk());
    }

    public List<TituloReceberBaixaChequeModel> obterPorFiltro(TituloReceberBaixaChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaChequeModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("id_tipo_operacao"));

        return criteria.list();

    }

    public List<TituloReceberBaixaChequeModel> obterTodos(OrganizacaoModel model)
            throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaChequeModel.class);

        criteria.setFetchMode("banco", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));
        criteria.addOrder(Order.asc("dataProtocolo"));
        criteria.addOrder(Order.asc("banco"));
        criteria.addOrder(Order.asc("valor"));


        return criteria.list();
    }

    @HibernateTransaction
    public void excluirTodos(Collection<TituloReceberBaixaChequeModel> coll)
            throws SystemException {

        for (TituloReceberBaixaChequeModel model : coll) {
            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(TituloReceberBaixaChequeModel model)
            throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public TituloReceberBaixaChequeModel consultarPorTemplate(TituloReceberBaixaChequeModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaChequeModel.class);

        if (model.getNumeroCheque() != null && !model.getNumeroCheque().isEmpty()) {
            criteria.add(Restrictions.eq("numeroCheque", model.getNumeroCheque()));
        }



        criteria.setMaxResults(1);


        criteria.setFetchMode("banco", FetchMode.JOIN);
        criteria.setFetchMode("tituloReceberBaixa", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));



        return (TituloReceberBaixaChequeModel) criteria.uniqueResult();

    }

    public List<TituloReceberBaixaChequeModel> obterPorTituloReceberBaixa(TituloReceberBaixaModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaChequeModel.class);

        criteria.add(Restrictions.eq("tituloReceberBaixa.pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return criteria.list();

    }

    public List<TituloReceberBaixaChequeModel> obterPorBanco(BancoModel model, OrganizacaoModel organizacao)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaChequeModel.class);

        criteria.add(Restrictions.eq("banco.id", model.getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", organizacao.getId()));

        criteria.addOrder(Order.asc("dataProtocolo"));
        criteria.addOrder(Order.asc("valor"));

        criteria.setFetchMode("banco", FetchMode.JOIN);

        return criteria.list();

    }

    public List<TituloReceberBaixaChequeModel> obterPorLoteDeposito(LoteDepositoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaChequeModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.add(Restrictions.eq("loteDeposito.pk.id", model.getPk().getId()));

        criteria.setFetchMode("banco", FetchMode.JOIN);

        return criteria.list();

    }

    public List<TituloReceberBaixaChequeModel> obterDepositaveis(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                TituloReceberBaixaChequeModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));
        criteria.add(Restrictions.isNull("loteDeposito.pk.id"));
        criteria.add(Restrictions.isNull("dataDeposito"));

        criteria.addOrder(Order.asc("dataProtocolo"));
        criteria.addOrder(Order.asc("banco"));
        criteria.addOrder(Order.asc("valor"));

        criteria.setFetchMode("banco", FetchMode.JOIN);

        return criteria.list();

    }

    public Double obterSaldoCheque(OrganizacaoModel model)
            throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("select sum(td.valor) from br.com.pempec.finance.models.TituloReceberBaixaChequeModel td "
                + " WHERE td.pk.organizacao.id = :org and loteDeposito.pk.id is null and dataDeposito is null");

        query.setParameter("org", model.getId());

        return (Double) query.uniqueResult();

    }

    @HibernateTransaction
    public void depositoTesourariaBanco(Collection<TituloReceberBaixaChequeModel> cheques, ContaBancariaModel conta) throws SystemException {

        TipoOperacaoBancariaModel tipoOperacaoBancariaModel = new TipoOperacaoBancariaModel();
        tipoOperacaoBancariaModel.setPk(new PKModel());
        tipoOperacaoBancariaModel.getPk().setOrganizacao(conta.getPk().getOrganizacao());
        tipoOperacaoBancariaModel.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_DEPOSITO_CHEQUE);

        tipoOperacaoBancariaModel = new TipoOperacaoBancariaDAO().consultarPorTemplate(tipoOperacaoBancariaModel);

        for (TituloReceberBaixaChequeModel cheque : cheques) {

            ContaBancariaCreditoModel contaBancariaCreditoModel = new ContaBancariaCreditoModel();
            contaBancariaCreditoModel.setPk(new PKModel());
            contaBancariaCreditoModel.getPk().setOrganizacao(cheque.getPk().getOrganizacao());
            contaBancariaCreditoModel.getPk().setId(PempecKeyGenerator.generateKey());
            contaBancariaCreditoModel.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaCredito());

            contaBancariaCreditoModel.setContaBancaria(conta);
            contaBancariaCreditoModel.setUsuario(Controller.getUsuarioLogado());
            contaBancariaCreditoModel.setValor(cheque.getValor());

            contaBancariaCreditoModel.setDataContabil(cheque.getDataDeposito());
            contaBancariaCreditoModel.setDataMovimento(cheque.getDataDeposito());
            contaBancariaCreditoModel.setDataRegistro(Controller.getDataServidorBD());
            contaBancariaCreditoModel.setDescricao("Depósito Cheques da Tesouraria");

            //contaBancariaCreditoModel.setMovimentoDiarioModel();
            contaBancariaCreditoModel.setObservacao("Lançamento automático.");
            //contaBancariaCreditoModel.setResponsavel(responsavel);
            contaBancariaCreditoModel.setTipoLancamento("C");

            contaBancariaCreditoModel.setTipoOperacaoBancaria(tipoOperacaoBancariaModel);

            //alterar a data do cheque. campo data do deposito
            cheque.setDataDeposito(Controller.getDataServidorBD());
            HibernateUtil.getCurrentSession().update(cheque);

            //inserir o lancamento na conciliacao bancaria 
            HibernateUtil.getCurrentSession().save(contaBancariaCreditoModel);

        }

        Repopulador.repopulador();
    }
}
