package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.LotePagamentoTituloDAOIf;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.Repopulador;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

//EQUIPE PEMPEC
public class LotePagamentoTituloDAO implements LotePagamentoTituloDAOIf {

    @HibernateTransaction
    public void inserir(LotePagamentoTituloModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }



        HibernateUtil.getCurrentSession().save(model);
    }

    @HibernateTransaction
    public void alterar(LotePagamentoTituloModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
    }

    public LotePagamentoTituloModel consultarPorPk(LotePagamentoTituloModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LotePagamentoTituloModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (LotePagamentoTituloModel) criteria.uniqueResult();

    }

    public List<LotePagamentoTituloModel> obterPorFiltro(LotePagamentoTituloModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LotePagamentoTituloModel.class);

        if (model.getLote() != null && !model.getLote().isEmpty()) {
            criteria.add(Restrictions.eq("lote", model.getLote()));
        }
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("lote"));


        return criteria.list();

    }

    public List<LotePagamentoTituloModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LotePagamentoTituloModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataRegistro"));
        criteria.addOrder(Order.asc("status"));


        return criteria.list();


    }

    @HibernateTransaction
    public void excluirTodos(Collection<LotePagamentoTituloModel> coll)
            throws SystemException {

        for (LotePagamentoTituloModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    public Boolean validaExclusao(LotePagamentoTituloModel model)
            throws SystemException, ApplicationException {

        Query query;

        if (!Controller.verificaParametroAtivo(Parametro.CCANP006.getCodigo())) {

            //Validando se algum dos registros foram exportados.
            query = HibernateUtil.getCurrentSession().createQuery(" select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao AND ID_LOTE_CONTABIL IS NOT NULL ");

            query.setString("lote", model.getPk().getId());
            query.setString("organizacao", model.getPk().getOrganizacao().getId());

            if (query.list() != null && !query.list().isEmpty()) {
                if (!Controller.verificaParametroAtivo(Parametro.CCANP006.getCodigo())) {
                    throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP006.getCodigo()).getMensagem());
                }
            }

        }

        if (Controller.verificaParametroAtivo(Parametro.CCANP007.getCodigo())) {

            query = HibernateUtil.getCurrentSession().createQuery("select fp.pk.id from br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel fp where ID_TITULO_PAGAR_BAIXA IN ( "
                    + " select tb.pk.id from br.com.pempec.finance.models.TituloPagarBaixaModel tb where ID_TITULO_PAGAR IN ( "
                    + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                    + " ) and id_organizacao = :organizacao "
                    + " and id_organizacao = :organizacao AND ID_FORMA_PAGAMENTO IN ('" + Constantes.FORMA_PAGAMENTO_ESPECIE + "') ");

            query.setString("lote", model.getPk().getId());
            query.setString("organizacao", model.getPk().getOrganizacao().getId());

            if (query.list() != null && !query.list().isEmpty()) {
                if (!Controller.verificaParametroAtivo(Parametro.CCANP00701.getCodigo())) {
                    throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP00701.getCodigo()).getMensagem());
                }
            }

            query = HibernateUtil.getCurrentSession().createQuery("select fp.pk.id from br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel fp where ID_TITULO_PAGAR_BAIXA IN ( "
                    + " select tb.pk.id from br.com.pempec.finance.models.TituloPagarBaixaModel tb where ID_TITULO_PAGAR IN ( "
                    + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                    + " ) and id_organizacao = :organizacao "
                    + " and id_organizacao = :organizacao AND ID_FORMA_PAGAMENTO IN ('" + Constantes.FORMA_PAGAMENTO_CHEQUE + "') ");

            query.setString("lote", model.getPk().getId());
            query.setString("organizacao", model.getPk().getOrganizacao().getId());

            if (query.list() != null && !query.list().isEmpty()) {
                if (!Controller.verificaParametroAtivo(Parametro.CCANP00702.getCodigo())) {
                    throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP00702.getCodigo()).getMensagem());
                }
            }

            query = HibernateUtil.getCurrentSession().createQuery("select fp.pk.id from br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel fp where ID_TITULO_PAGAR_BAIXA IN ( "
                    + " select tb.pk.id from br.com.pempec.finance.models.TituloPagarBaixaModel tb where ID_TITULO_PAGAR IN ( "
                    + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                    + " ) and id_organizacao = :organizacao "
                    + " and id_organizacao = :organizacao AND ID_FORMA_PAGAMENTO IN ('" + Constantes.FORMA_PAGAMENTO_INTERNET + "') ");

            query.setString("lote", model.getPk().getId());
            query.setString("organizacao", model.getPk().getOrganizacao().getId());

            if (query.list() != null && !query.list().isEmpty()) {
                if (!Controller.verificaParametroAtivo(Parametro.CCANP00703.getCodigo())) {
                    throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP00703.getCodigo()).getMensagem());
                }
            }


        }

        query = HibernateUtil.getCurrentSession().createQuery(" select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        Collection<TituloPagarModel> coll = new ArrayList<TituloPagarModel>();

        if (query.list() != null && !query.list().isEmpty()) {

            for (Object object : query.list()) {
                TituloPagarModel titulo = new TituloPagarModel();
                titulo.setPk(new PKModel());
                titulo.getPk().setId((String) object);
                titulo.getPk().setOrganizacao(model.getPk().getOrganizacao());
                coll.add(titulo);
            }

        }

        if (!coll.isEmpty()) {

            for (TituloPagarModel tituloPagarModel : coll) {

                //Validando os títulos filhos.
                List<TituloPagarModel> titulos = new TituloPagarDAO().obterTitulosFilhos(tituloPagarModel);

                if (titulos.size() > 1) {

                    TituloPagarBaixaDAO tituloPagarBaixaDAO = new TituloPagarBaixaDAO();

                    for (TituloPagarModel titulo : titulos) {

                        if (titulo.getLoteContabil() != null && titulo.getLoteContabil().getPk() != null) {
                            if (!Controller.verificaParametroAtivo(Parametro.CCANP006.getCodigo())) {
                                throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP006.getCodigo()).getMensagem());
                            }
                        }

                        //Obtendo sua Baixa
                        TituloPagarBaixaModel tituloPagarBaixaModel = tituloPagarBaixaDAO.consultarPorTitulo(titulo);

                        if (tituloPagarBaixaModel != null && tituloPagarBaixaModel.getPk() != null) {

                            if (tituloPagarBaixaModel.getFormasPagamento() != null) {

                                for (TituloPagarBaixaFormaPagamentoModel tituloPagarBaixaFormaPagamentoModel : tituloPagarBaixaModel.getFormasPagamento()) {

                                    if (Controller.verificaParametroAtivo(Parametro.CCANP007.getCodigo())) {
                                        if (tituloPagarBaixaFormaPagamentoModel.getForma() != null && tituloPagarBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {
                                            if (!Controller.verificaParametroAtivo(Parametro.CCANP00701.getCodigo())) {
                                                throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP00701.getCodigo()).getMensagem());
                                            }
                                        }

                                        if (tituloPagarBaixaFormaPagamentoModel.getForma() != null && tituloPagarBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE) && tituloPagarBaixaModel.getTituloPagarBaixaCheque() != null) {
                                            if (!Controller.verificaParametroAtivo(Parametro.CCANP00702.getCodigo())) {
                                                throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP00702.getCodigo()).getMensagem());
                                            }

                                        }

                                        if (tituloPagarBaixaFormaPagamentoModel.getForma() != null && tituloPagarBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_INTERNET)) {
                                            if (!Controller.verificaParametroAtivo(Parametro.CCANP00703.getCodigo())) {
                                                throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP00703.getCodigo()).getMensagem());
                                            }
                                        }

                                    }


                                }

                            }

                        }

                    }

                }

            }

        }

        return true;

    }

    @HibernateTransaction
    public void excluir(LotePagamentoTituloModel model)
            throws SystemException {

        Query query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.ContaBancariaDebitoModel where ID_TITULO_PAGAR IN ("
                + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TesourariaDebitoModel where ID_TITULO_PAGAR_BAIXA IN ("
                + " select tb.pk.id from br.com.pempec.finance.models.TituloPagarBaixaModel tb where ID_TITULO_PAGAR IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + " and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.ContaBancariaChequeModel set ID_TIPO_STATUS = '" + Constantes.STATUS_CHEQUE_EXCLUIDO + "' where pk.id in ( "
                + " select t.contaBancariaCheque.pk.id from br.com.pempec.finance.models.TituloPagarBaixaChequeModel t where ID_TITULO_PAGAR_BAIXA IN ( "
                + " select tb.pk.id from br.com.pempec.finance.models.TituloPagarBaixaModel tb where ID_TITULO_PAGAR IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + " and ID_ORGANIZACAO = :organizacao "
                + " ) "
                + " and pk.organizacao.id = :organizacao ");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel where ID_TITULO_PAGAR_BAIXA IN ("
                + " select tb.pk.id from br.com.pempec.finance.models.TituloPagarBaixaModel tb where ID_TITULO_PAGAR IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + "and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel where ID_TITULO_PAGAR_BAIXA IN ( "
                + " select tb.pk.id from br.com.pempec.finance.models.TituloPagarBaixaModel tb where ID_TITULO_PAGAR IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + "and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaChequeModel where ID_TITULO_PAGAR_BAIXA IN ("
                + " select tb.pk.id from br.com.pempec.finance.models.TituloPagarBaixaModel tb where ID_TITULO_PAGAR IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + "and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaInternetModel where ID_TITULO_PAGAR_BAIXA IN ("
                + " select tb.pk.id from br.com.pempec.finance.models.TituloPagarBaixaModel tb where ID_TITULO_PAGAR IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + " and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel where ID_TITULO_PAGAR_BAIXA IN ( "
                + " select tb.pk.id from br.com.pempec.finance.models.TituloPagarBaixaModel tb where ID_TITULO_PAGAR IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + " and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloPagarBaixaModel where ID_TITULO_PAGAR IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloPagarModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao "
                + " ) "
                + " and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.TituloPagarModel set ID_TIPO_STATUS = '" + Constantes.STATUS_TITULO_NOVO + "', DATA_PAGAMENTO = NULL, ID_LOTE_PAGAMENTO = NULL where ID_LOTE_PAGAMENTO = :lote and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.LotePagamentoTituloModel set STATUS = '" + Constantes.LOTE_PAGAMENTO_REMOVIDO + "' where pk.id = :lote and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        Repopulador.repopulador();

    }

    public LotePagamentoTituloModel consultarPorTemplate(LotePagamentoTituloModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LotePagamentoTituloModel.class);


        if (model.getLote() != null && !model.getLote().isEmpty()) {
            criteria.add(Restrictions.eq("lote", model.getLote()));
        }

        criteria.setMaxResults(1);
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaModel", FetchMode.JOIN);
        criteria.setFetchMode("responsavel", FetchMode.JOIN);
        criteria.setFetchMode("cheque", FetchMode.JOIN);
        criteria.setFetchMode("tipoOperacaoBancariaModel", FetchMode.JOIN);
        criteria.setFetchMode("formaPagamentoModel", FetchMode.JOIN);

        return (LotePagamentoTituloModel) criteria.uniqueResult();

    }
}
