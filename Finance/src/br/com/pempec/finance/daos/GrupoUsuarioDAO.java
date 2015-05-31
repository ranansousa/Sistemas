package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.GrupoUsuarioDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.GrupoModel;
import br.com.pempec.finance.models.GrupoUsuarioModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
public class GrupoUsuarioDAO implements GrupoUsuarioDAOIf {

    @HibernateTransaction
    public void inserir(GrupoUsuarioModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(GrupoUsuarioModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public GrupoUsuarioModel consultarPorPk(GrupoUsuarioModel model) throws SystemException {
        return (GrupoUsuarioModel) HibernateUtil.getCurrentSession().get(GrupoUsuarioModel.class, model.getGrupoUsuarioIDModel().getId());
    }

    public List<GrupoUsuarioModel> obterTodos() throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(GrupoUsuarioModel.class).list();
    }

    @HibernateTransaction
    public void excluirTodos(String[] array) throws SystemException {
    }

    public List<GrupoUsuarioModel> obterPorGrupo(GrupoModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(GrupoUsuarioModel.class);

        criteria.add(Restrictions.eq("grupoUsuarioIDModel.grupo.id", model.getId()));

        return criteria.list();

    }

    public List<GrupoUsuarioModel> obterPorUsuario(UsuarioModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(GrupoUsuarioModel.class);

        criteria.add(Restrictions.eq("grupoUsuarioIDModel.usuario.id", model.getId()));

        return criteria.list();

    }

    @HibernateTransaction
    public void excluirTodosPorGrupo(GrupoModel grupo) throws SystemException {
        Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.models.GrupoUsuarioModel.deletarGrupoUsuarioPorIdGrupo");
        query.setParameter("id_grupo", grupo.getId());
        query.executeUpdate();
        Repopulador.repopulador();
    }
}
