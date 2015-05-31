package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.LoteRecebimentoTituloDAOIf;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.*;
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
public class LoteRecebimentoTituloDAO implements LoteRecebimentoTituloDAOIf {

    @HibernateTransaction
    public void inserir(LoteRecebimentoTituloModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }



        HibernateUtil.getCurrentSession().save(model);
    }

    @HibernateTransaction
    public void alterar(LoteRecebimentoTituloModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
    }

    public LoteRecebimentoTituloModel consultarPorPk(LoteRecebimentoTituloModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteRecebimentoTituloModel.class);

        criteria.add(Restrictions.eq("pk.id", model.getPk().getId()));

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));

        return (LoteRecebimentoTituloModel) criteria.uniqueResult();

    }

    public List<LoteRecebimentoTituloModel> obterPorFiltro(LoteRecebimentoTituloModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteRecebimentoTituloModel.class);

        if (model.getLote() != null && !model.getLote().isEmpty()) {
            criteria.add(Restrictions.eq("lote", model.getLote()));
        }
        criteria.setFetchMode("usuario", FetchMode.JOIN);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        criteria.addOrder(Order.asc("lote"));


        return criteria.list();

    }

    public List<LoteRecebimentoTituloModel> obterTodos(OrganizacaoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteRecebimentoTituloModel.class);

        criteria.add(Restrictions.eq("pk.organizacao.id", model.getId()));

        criteria.addOrder(Order.desc("dataRegistro"));
        criteria.addOrder(Order.asc("status"));


        return criteria.list();


    }

    @HibernateTransaction
    public void excluirTodos(Collection<LoteRecebimentoTituloModel> coll)
            throws SystemException {

        for (LoteRecebimentoTituloModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    public Boolean validaExclusao(LoteRecebimentoTituloModel model)
            throws SystemException, ApplicationException {

        Query query;

        if (!Controller.verificaParametroAtivo(Parametro.CCANP006.getCodigo())) {

            //Validando se algum dos registros foram exportados.
            query = HibernateUtil.getCurrentSession().createQuery(" select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao AND ID_LOTE_CONTABIL IS NOT NULL ");

            query.setString("lote", model.getPk().getId());
            query.setString("organizacao", model.getPk().getOrganizacao().getId());

            if (query.list() != null && !query.list().isEmpty()) {
                if (!Controller.verificaParametroAtivo(Parametro.CCANP006.getCodigo())) {
                    throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP006.getCodigo()).getMensagem());
                }
            }

        }

        if (Controller.verificaParametroAtivo(Parametro.CCANP007.getCodigo())) {



            query = HibernateUtil.getCurrentSession().createQuery("select fp.pk.id from br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel fp where ID_TITULO_RECEBER_BAIXA IN ( "
                    + " select tb.pk.id from br.com.pempec.finance.models.TituloReceberBaixaModel tb where ID_TITULO_RECEBER IN ( "
                    + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                    + " ) and id_organizacao = :organizacao "
                    + " and id_organizacao = :organizacao AND ID_FORMA_PAGAMENTO IN ('" + Constantes.FORMA_PAGAMENTO_ESPECIE + "') ");

            query.setString("lote", model.getPk().getId());
            query.setString("organizacao", model.getPk().getOrganizacao().getId());

            if (query.list() != null && !query.list().isEmpty()) {
                if (!Controller.verificaParametroAtivo(Parametro.CCANP00701.getCodigo())) {
                    throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP00701.getCodigo()).getMensagem());
                }
            }

            query = HibernateUtil.getCurrentSession().createQuery("select fp.pk.id from br.com.pempec.finance.models.TituloReceberBaixaFormaRecebimentoModel fp where ID_TITULO_RECEBER_BAIXA IN ( "
                    + " select tb.pk.id from br.com.pempec.finance.models.TituloReceberBaixaModel tb where ID_TITULO_RECEBER IN ( "
                    + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                    + " ) and id_organizacao = :organizacao "
                    + " and id_organizacao = :organizacao AND ID_FORMA_PAGAMENTO IN ('" + Constantes.FORMA_PAGAMENTO_CHEQUE + "') ");

            query.setString("lote", model.getPk().getId());
            query.setString("organizacao", model.getPk().getOrganizacao().getId());

            if (query.list() != null && !query.list().isEmpty()) {
                if (!Controller.verificaParametroAtivo(Parametro.CCANP00702.getCodigo())) {
                    throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP00702.getCodigo()).getMensagem());
                }
            }

            query = HibernateUtil.getCurrentSession().createQuery("select fp.pk.id from br.com.pempec.finance.models.TituloReceberBaixaFormaRecebimentoModel fp where ID_TITULO_RECEBER_BAIXA IN ( "
                    + " select tb.pk.id from br.com.pempec.finance.models.TituloReceberBaixaModel tb where ID_TITULO_RECEBER IN ( "
                    + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
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

        query = HibernateUtil.getCurrentSession().createQuery(" select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        Collection<TituloReceberModel> coll = new ArrayList<TituloReceberModel>();

        if (query.list() != null && !query.list().isEmpty()) {

            for (Object object : query.list()) {
                TituloReceberModel titulo = new TituloReceberModel();
                titulo.setPk(new PKModel());
                titulo.getPk().setId((String) object);
                titulo.getPk().setOrganizacao(model.getPk().getOrganizacao());
                coll.add(titulo);
            }

        }

        if (!coll.isEmpty()) {

            for (TituloReceberModel tituloReceberModel : coll) {

                //Validando os títulos filhos.
                List<TituloReceberModel> titulos = new TituloReceberDAO().obterTitulosFilhos(tituloReceberModel);

                if (titulos.size() > 1) {

                    TituloReceberBaixaDAO tituloReceberBaixaDAO = new TituloReceberBaixaDAO();

                    for (TituloReceberModel titulo : titulos) {

                        if (titulo.getLoteContabil() != null && titulo.getLoteContabil().getPk() != null) {
                            if (!Controller.verificaParametroAtivo(Parametro.CCANP006.getCodigo())) {
                                throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP006.getCodigo()).getMensagem());
                            }
                        }

                        //Obtendo sua Baixa
                        TituloReceberBaixaModel tituloReceberBaixaModel = tituloReceberBaixaDAO.consultarPorTitulo(titulo);

                        if (tituloReceberBaixaModel != null && tituloReceberBaixaModel.getPk() != null) {

                            if (tituloReceberBaixaModel.getFormasPagamento() != null) {

                                for (TituloReceberBaixaFormaPagamentoModel tituloReceberBaixaFormaPagamentoModel : tituloReceberBaixaModel.getFormasPagamento()) {

                                    if (Controller.verificaParametroAtivo(Parametro.CCANP007.getCodigo())) {
                                        if (tituloReceberBaixaFormaPagamentoModel.getForma() != null && tituloReceberBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {
                                            if (!Controller.verificaParametroAtivo(Parametro.CCANP00701.getCodigo())) {
                                                throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP00701.getCodigo()).getMensagem());
                                            }
                                        }

                                        if (tituloReceberBaixaFormaPagamentoModel.getForma() != null && tituloReceberBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE) && tituloReceberBaixaModel.getTituloReceberBaixaCheque() != null) {
                                            if (!Controller.verificaParametroAtivo(Parametro.CCANP00702.getCodigo())) {
                                                throw new ApplicationException(Controller.findByCodigo(Parametro.CCANP00702.getCodigo()).getMensagem());
                                            }

                                        }

                                        if (tituloReceberBaixaFormaPagamentoModel.getForma() != null && tituloReceberBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_INTERNET)) {
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
    public void excluir(LoteRecebimentoTituloModel model)
            throws SystemException {

        //precisa refazer


        Query query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.ContaBancariaDebitoModel where ID_TITULO_RECEBER IN ("
                + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TesourariaDebitoModel where ID_TITULO_RECEBER_BAIXA IN ("
                + " select tb.pk.id from br.com.pempec.finance.models.TituloReceberBaixaModel tb where ID_TITULO_RECEBER IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + " and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.ContaBancariaChequeModel set ID_TIPO_STATUS = '" + Constantes.STATUS_CHEQUE_EXCLUIDO + "' where pk.id in ( "
                + " select t.contaBancariaCheque.pk.id from br.com.pempec.finance.models.TituloReceberBaixaChequeModel t where ID_TITULO_RECEBER_BAIXA IN ( "
                + " select tb.pk.id from br.com.pempec.finance.models.TituloReceberBaixaModel tb where ID_TITULO_RECEBER IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + " and ID_ORGANIZACAO = :organizacao "
                + " ) "
                + " and pk.organizacao.id = :organizacao ");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel where ID_TITULO_RECEBER_BAIXA IN ("
                + " select tb.pk.id from br.com.pempec.finance.models.TituloReceberBaixaModel tb where ID_TITULO_RECEBER IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + "and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel where ID_TITULO_RECEBER_BAIXA IN ( "
                + " select tb.pk.id from br.com.pempec.finance.models.TituloReceberBaixaModel tb where ID_TITULO_RECEBER IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + "and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaChequeModel where ID_TITULO_RECEBER_BAIXA IN ("
                + " select tb.pk.id from br.com.pempec.finance.models.TituloReceberBaixaModel tb where ID_TITULO_RECEBER IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + "and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaInternetModel where ID_TITULO_RECEBER_BAIXA IN ("
                + " select tb.pk.id from br.com.pempec.finance.models.TituloReceberBaixaModel tb where ID_TITULO_RECEBER IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + " and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaFormaRecebimentoModel where ID_TITULO_RECEBER_BAIXA IN ( "
                + " select tb.pk.id from br.com.pempec.finance.models.TituloReceberBaixaModel tb where ID_TITULO_RECEBER IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao ) "
                + " ) and id_organizacao = :organizacao "
                + " and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("delete br.com.pempec.finance.models.TituloReceberBaixaModel where ID_TITULO_RECEBER IN ( "
                + " select t.pk.id from br.com.pempec.finance.models.TituloReceberModel t where ID_LOTE_PAGAMENTO = :lote and ID_ORGANIZACAO = :organizacao "
                + " ) "
                + " and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.TituloReceberModel set ID_TIPO_STATUS = '" + Constantes.STATUS_TITULO_NOVO + "', DATA_PAGAMENTO = NULL, ID_LOTE_PAGAMENTO = NULL where ID_LOTE_PAGAMENTO = :lote and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().createQuery("update br.com.pempec.finance.models.LoteRecebimentoTituloModel set STATUS = '" + Constantes.LOTE_PAGAMENTO_REMOVIDO + "' where pk.id = :lote and id_organizacao = :organizacao");

        query.setString("lote", model.getPk().getId());
        query.setString("organizacao", model.getPk().getOrganizacao().getId());

        query.executeUpdate();

        Repopulador.repopulador();

    }

    public LoteRecebimentoTituloModel consultarPorTemplate(LoteRecebimentoTituloModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                LoteRecebimentoTituloModel.class);


        if (model.getLote() != null && !model.getLote().isEmpty()) {
            criteria.add(Restrictions.eq("lote", model.getLote()));
        }

        
        criteria.add(Restrictions.eq("pk.organizacao.id", model.getPk().getOrganizacao().getId()));
        //criteria.setFetchMode("usuario", FetchMode.JOIN);
        criteria.setFetchMode("contaBancariaModel", FetchMode.JOIN);
       // criteria.setFetchMode("responsavel", FetchMode.JOIN);
        //criteria.setFetchMode("tipoOperacaoBancariaModel", FetchMode.JOIN);
        //criteria.setFetchMode("formaRecebimentoModel", FetchMode.JOIN);

        criteria.setMaxResults(1);
        
        return (LoteRecebimentoTituloModel) criteria.uniqueResult();

    }
}
