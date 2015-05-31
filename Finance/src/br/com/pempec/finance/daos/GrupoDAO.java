package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.GrupoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.GrupoActionModel;
import br.com.pempec.finance.models.GrupoModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

//EQUIPE PEMPEC
public class GrupoDAO implements GrupoDAOIf {

    @HibernateTransaction
    public void inserir(GrupoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().save(model);

        if (model.getListaPermissoes() != null) {

            for (GrupoActionModel grupoActionModel : model.getListaPermissoes()) {

                grupoActionModel.getGrupoActionIDModel().setGrupo(model);

                HibernateUtil.getCurrentSession().save(grupoActionModel);

            }


        }

        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(GrupoModel model) throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().update(model);

        Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.GrupoActionModel.deletarGrupoActionPorIdGrupo");
        query.setParameter("id_grupo", model.getId());
        query.executeUpdate();

        if (model.getListaPermissoes() != null) {

            for (GrupoActionModel grupoActionModel : model.getListaPermissoes()) {

                grupoActionModel.getGrupoActionIDModel().setGrupo(model);

                HibernateUtil.getCurrentSession().save(grupoActionModel);

            }


        }

        Repopulador.repopulador();
    }

    public GrupoModel consultarPorPk(GrupoModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                GrupoModel.class);

        criteria.add(Restrictions.eq("id", model.getId()));

        criteria.setMaxResults(1);

        return (GrupoModel) criteria.uniqueResult();

    }

    public List<GrupoModel> obterPorFiltro(GrupoModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(GrupoModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.like("descricao", model.getDescricao(), MatchMode.START));
        }

        criteria.addOrder(Order.asc("descricao"));

        return criteria.list();

    }

    public List<GrupoModel> obterTodos() throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(GrupoModel.class).list();
    }

    @HibernateTransaction
    public void excluirTodos(String[] array) throws SystemException {

        GrupoModel model = null;

        for (String id : array) {
            model = new GrupoModel();
            model.setId(PempecParse.stringToLong(id));

            Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.GrupoActionModel.deletarGrupoActionPorIdGrupo");
            query.setParameter("id_grupo", model.getId());
            query.executeUpdate();

            query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.GrupoUsuarioModel.deletarGrupoUsuarioPorIdGrupo");
            query.setParameter("id_grupo", model.getId());
            query.executeUpdate();

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    public GrupoModel consultarPorTemplate(GrupoModel model)
            throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                GrupoModel.class);

        if (model.getDescricao() != null && !model.getDescricao().isEmpty()) {
            criteria.add(Restrictions.eq("descricao", model.getDescricao()));
        }

        criteria.setMaxResults(1);

        return (GrupoModel) criteria.uniqueResult();

    }

    @HibernateTransaction
    public void excluirTodos(Collection<GrupoModel> coll)
            throws SystemException {

        for (GrupoModel model : coll) {

            if (model.getMovimentoDiarioModel() != null
                    && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

                MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

                HibernateUtil.getCurrentSession().save(mov);

            }

            Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.GrupoActionModel.deletarGrupoActionPorIdGrupo");
            query.setParameter("id_grupo", model.getId());
            query.executeUpdate();

            query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.GrupoUsuarioModel.deletarGrupoUsuarioPorIdGrupo");
            query.setParameter("id_grupo", model.getId());
            query.executeUpdate();

            HibernateUtil.getCurrentSession().delete(model);
        }

    }

    @HibernateTransaction
    public void excluir(GrupoModel model)
            throws SystemException {

        if (model.getMovimentoDiarioModel() != null
                && !model.getMovimentoDiarioModel().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiarioModel();

            HibernateUtil.getCurrentSession().save(mov);

        }

        Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.GrupoActionModel.deletarGrupoActionPorIdGrupo");
        query.setParameter("id_grupo", model.getId());
        query.executeUpdate();

        query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.finance.models.GrupoUsuarioModel.deletarGrupoUsuarioPorIdGrupo");
        query.setParameter("id_grupo", model.getId());
        query.executeUpdate();

        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }
}
