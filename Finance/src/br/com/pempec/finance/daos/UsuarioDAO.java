package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.UsuarioDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.GrupoModel;
import br.com.pempec.finance.models.GrupoUsuarioIDModel;
import br.com.pempec.finance.models.GrupoUsuarioModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.UsuarioActionModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.MD5;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
public class UsuarioDAO implements UsuarioDAOIf {

    @HibernateTransaction
    public void salvar(UsuarioModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();
            mov.getPk().setOrganizacao(Controller.getOrganizacao());

            HibernateUtil.getCurrentSession().save(mov);

        }

        if (model.getId() == null) {
            model.setId(PempecParse.stringToLong(PempecKeyGenerator.generateIdUsuario()));
            HibernateUtil.getCurrentSession().save(model);
        } else {
            HibernateUtil.getCurrentSession().merge(model);
        }

        Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.GrupoUsuarioModel.deletarGrupoUsuarioPorIdUsuario");
        query.setParameter("id_usuario", model.getId());
        query.executeUpdate();

        if (model.getListaGrupos() != null) {

            GrupoUsuarioModel grupoUsuarioModel;

            GrupoUsuarioIDModel grupoUsuarioIDModel;

            for (GrupoModel grupoModel : model.getListaGrupos()) {

                grupoUsuarioModel = new GrupoUsuarioModel();

                grupoUsuarioIDModel = new GrupoUsuarioIDModel();

                grupoUsuarioIDModel.setId(PempecKeyGenerator.generateKeyLong());

                grupoUsuarioIDModel.setUsuario(model);

                grupoUsuarioIDModel.setGrupo(grupoModel);

                grupoUsuarioModel.setGrupoUsuarioIDModel(grupoUsuarioIDModel);

                HibernateUtil.getCurrentSession().save(grupoUsuarioModel);

            }

        }

        query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.UsuarioActionModel.deletarUsuarioActionPorIdUsuario");
        query.setParameter("id_usuario", model.getId());
        query.executeUpdate();

        if (model.getListaPermissoes() != null) {

            for (UsuarioActionModel usuarioActionModel : model.getListaPermissoes()) {

                usuarioActionModel.getUsuarioActionIDModel().setId(PempecKeyGenerator.generateKeyLong());

                usuarioActionModel.getUsuarioActionIDModel().setUsuario(model);

                HibernateUtil.getCurrentSession().save(usuarioActionModel);

            }

        }

        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void resetarSenha(UsuarioModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            mov.getPk().setOrganizacao(Controller.getOrganizacao());

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().merge(model);

    }

    @HibernateTransaction
    public void registraAcesso(UsuarioModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            mov.getPk().setOrganizacao(Controller.getOrganizacao());

            mov.setObservacao(model.getMovimentoDiarioModel().getObservacao() + " CHEQUE");

            HibernateUtil.getCurrentSession().save(mov);

        }

//        StringBuilder builder = new StringBuilder();
//
//        builder.append(" INSERT INTO TITULO_PAGAR_HISTORICO (ID_TITULO_PAGAR_HISTORICO, ID_ORGANIZACAO, ");
//        builder.append(" ID_HISTORICO, ID_TITULO_PAGAR, VALOR) ");
//        builder.append(" SELECT TP.ID_TITULO_PAGAR, TP.ID_ORGANIZACAO, TP.ID_HISTORICO, TP.ID_TITULO_PAGAR, TP.VALOR_NOMINAL ");
//        builder.append(" FROM TITULO_PAGAR TP WHERE ID_TITULO_PAGAR NOT IN ( ");
//        builder.append(" SELECT ID_TITULO_PAGAR FROM TITULO_PAGAR_HISTORICO ) ");
//
//        SQLQuery query = HibernateUtil.getCurrentSession().createSQLQuery(builder.toString());
//
//        query.executeUpdate();
//
//        builder = new StringBuilder();
//
//        builder.append(" INSERT INTO TITULO_PAGAR_RATEIO_CC (ID_TITULO_PAGAR_RATEIO_CC, ID_ORGANIZACAO, ");
//        builder.append(" ID_CENTRO_CUSTO, ID_TITULO_PAGAR, VALOR) ");
//        builder.append(" SELECT TR.ID_TITULO_PAGAR, TR.ID_ORGANIZACAO, TR.ID_CENTRO_CUSTO, TR.ID_TITULO_PAGAR, TR.VALOR_NOMINAL ");
//        builder.append(" FROM TITULO_PAGAR TR WHERE ID_TITULO_PAGAR NOT IN ( ");
//        builder.append(" SELECT ID_TITULO_PAGAR FROM TITULO_PAGAR_RATEIO_CC ) ");
//
//        query = HibernateUtil.getCurrentSession().createSQLQuery(builder.toString());
//
//        query.executeUpdate();
//
//        builder = new StringBuilder();
//
//        builder.append(" INSERT INTO TITULO_RECEBER_HISTORICO (ID_TITULO_RECEBER_HISTORICO, ID_ORGANIZACAO, ");
//        builder.append(" ID_HISTORICO, ID_TITULO_RECEBER, VALOR) ");
//        builder.append(" SELECT TR.ID_TITULO_RECEBER, TR.ID_ORGANIZACAO, TR.ID_HISTORICO, TR.ID_TITULO_RECEBER, TR.VALOR_NOMINAL ");
//        builder.append(" FROM TITULO_RECEBER TR WHERE ID_TITULO_RECEBER NOT IN ( ");
//        builder.append(" SELECT ID_TITULO_RECEBER FROM TITULO_RECEBER_HISTORICO ) ");
//
//        query = HibernateUtil.getCurrentSession().createSQLQuery(builder.toString());
//
//        query.executeUpdate();
//
//        builder = new StringBuilder();
//
//        builder.append(" INSERT INTO TITULO_RECEBER_RATEIO_CC (ID_TITULO_RECEBER_RATEIO_CC, ID_ORGANIZACAO, ");
//        builder.append(" ID_CENTRO_CUSTO, ID_TITULO_RECEBER, VALOR) ");
//        builder.append(" SELECT TR.ID_TITULO_RECEBER, TR.ID_ORGANIZACAO, TR.ID_CENTRO_CUSTO, TR.ID_TITULO_RECEBER, TR.VALOR_NOMINAL ");
//        builder.append(" FROM TITULO_RECEBER TR WHERE ID_TITULO_RECEBER NOT IN ( ");
//        builder.append(" SELECT ID_TITULO_RECEBER FROM TITULO_RECEBER_RATEIO_CC ) ");
//
//        query = HibernateUtil.getCurrentSession().createSQLQuery(builder.toString());
//
//        query.executeUpdate(); 19/05/2012

        HibernateUtil.getCurrentSession().merge(model);

    }

    public UsuarioModel consultarPorPk(
            UsuarioModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                UsuarioModel.class);

        criteria.add(Restrictions.eq("id", model.getId()));

        criteria.setMaxResults(1);

        criteria.setFetchMode("organizacao", FetchMode.JOIN);

        return (UsuarioModel) criteria.uniqueResult();

    }

    public List<UsuarioModel> obterPorFiltro(UsuarioModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(UsuarioModel.class);

        if (model.getLogin() != null && !model.getLogin().isEmpty()) {
            criteria.add(Restrictions.like("login", model.getLogin(), MatchMode.START));
        }

        criteria.addOrder(Order.asc("login"));

        return criteria.list();

    }

    public List<UsuarioModel> obterTodos() throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(UsuarioModel.class);

        Criterion isNull = Restrictions.isNull("organizacao.id");

        Criterion isEq = Restrictions.eq("organizacao.id", Controller.getOrganizacao().getId());

        LogicalExpression orExp = Restrictions.or(isNull, isEq);

        criteria.add(orExp);

        criteria.addOrder(Order.asc("nome"));

        return criteria.list();

    }

    @HibernateTransaction
    public void excluir(UsuarioModel model) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.GrupoUsuarioModel.deletarGrupoUsuarioPorIdUsuario");
        query.setParameter("id_usuario", model.getId());
        query.executeUpdate();

        query =
                HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.UsuarioActionModel.deletarUsuarioActionPorIdUsuario");
        query.setParameter("id_usuario", model.getId());
        query.executeUpdate();

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            mov.getPk().setOrganizacao(Controller.getOrganizacao());

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().delete(model);
    }

    @HibernateTransaction
    public void excluirTodos(String[] array) throws SystemException {

        UsuarioModel model = null;

        for (String id : array) {
            model = new UsuarioModel();
            model.setId(PempecParse.stringToLong(id));

            Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.GrupoUsuarioModel.deletarGrupoUsuarioPorIdUsuario");
            query.setParameter("id_usuario", model.getId());
            query.executeUpdate();

            query =
                    HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.UsuarioActionModel.deletarUsuarioActionPorIdUsuario");
            query.setParameter("id_usuario", model.getId());
            query.executeUpdate();

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                mov.getPk().setOrganizacao(Controller.getOrganizacao());

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public UsuarioModel validarUsuario(
            UsuarioModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(UsuarioModel.class);

        criteria.add(Restrictions.eq("login", model.getLogin()));
        criteria.add(Restrictions.eq("senha", MD5.criptografar(model.getSenha())));

        criteria.setFetchMode("organizacao", FetchMode.JOIN);

        return (UsuarioModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public UsuarioModel consultarPorLogin(
            UsuarioModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(UsuarioModel.class);

        criteria.setFetchMode("organizacao", FetchMode.JOIN);

        return (UsuarioModel) criteria.add(Restrictions.eq("login", model.getLogin())).uniqueResult();

    }

    public UsuarioModel consultarPorTemplate(
            UsuarioModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                UsuarioModel.class);

        if (model.getNome() != null && !model.getNome().isEmpty()) {
            criteria.add(Restrictions.eq("nome", model.getNome()));
        }

        criteria.setMaxResults(1);

        criteria.setFetchMode("organizacao", FetchMode.JOIN);

        return (UsuarioModel) criteria.uniqueResult();

    }

    public Date obterDataServidorBD() {

        Date data = PempecParse.dateToDate(new Date());

//        SQLQuery query = HibernateUtil.getCurrentSession().createSQLQuery("SELECT CURRENT_DATE FROM RDB$DATABASE");
//
//        java.sql.Date dataBanco = (java.sql.Date) query.uniqueResult();
//
//        data = PempecParse.dateToDate(new Date(dataBanco.getTime()));


        return data;

    }
}
