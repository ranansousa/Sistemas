package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.UsuarioActionDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ActionModel;
import br.com.pempec.finance.models.TelaModel;
import br.com.pempec.finance.models.UsuarioActionIDModel;
import br.com.pempec.finance.models.UsuarioActionModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.Repopulador;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
//EQUIPE PEMPEC
public class UsuarioActionDAO implements UsuarioActionDAOIf {

    @HibernateTransaction
    public void inserir(UsuarioActionModel model) throws SystemException {
        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(UsuarioActionModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public UsuarioActionModel consultarPorPk(UsuarioActionModel model) throws SystemException {
        return (UsuarioActionModel) HibernateUtil.getCurrentSession().get(UsuarioActionModel.class, model.getUsuarioActionIDModel().getId());
    }

    public List<UsuarioActionModel> obterTodos() throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(UsuarioActionModel.class).list();
    }

    @HibernateTransaction
    public void excluirTodos(String[] array) throws SystemException {
    }

    public List<UsuarioActionModel> obterPorUsuario(UsuarioModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(UsuarioActionModel.class);

        criteria.add(Restrictions.eq("usuarioActionIDModel.usuario.id", model.getId()));

        return criteria.list();

    }

    @HibernateTransaction
    public void excluirTodosPorUsuario(UsuarioModel model) throws SystemException {
        Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.models.UsuarioActionModel.deletarUsuarioActionPorIdUsuario");
        query.setParameter("id_usuario", model.getId());
        query.executeUpdate();
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void inserirPermissaoPorUsuario(UsuarioModel model, String[] permissoes) throws SystemException {

        for (String permissao : permissoes) {

            UsuarioActionModel usuarioActionModel = new UsuarioActionModel();

            UsuarioActionIDModel usuarioActionIDModel = new UsuarioActionIDModel();

            usuarioActionIDModel.setId(PempecKeyGenerator.generateKeyLong());

            usuarioActionIDModel.setUsuario(model);

            String[] aux = permissao.split(":");

            TelaModel tela = new TelaModel();
            tela.setId(PempecParse.stringToLong(aux[0]));

            ActionModel action = new ActionModel();
            action.setId(PempecParse.stringToLong(aux[1]));

            usuarioActionIDModel.setTela(tela);
            usuarioActionIDModel.setAction(action);

            usuarioActionModel.setUsuarioActionIDModel(usuarioActionIDModel);

            HibernateUtil.getCurrentSession().save(usuarioActionModel);
            Repopulador.repopulador();

        }

    }
}
