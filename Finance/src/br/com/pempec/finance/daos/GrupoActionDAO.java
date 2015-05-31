package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.GrupoActionDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ActionModel;
import br.com.pempec.finance.models.GrupoActionIDModel;
import br.com.pempec.finance.models.GrupoActionModel;
import br.com.pempec.finance.models.GrupoModel;
import br.com.pempec.finance.models.TelaModel;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.Repopulador;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

//EQUIPE PEMPEC
public class GrupoActionDAO implements GrupoActionDAOIf {

    @HibernateTransaction
    public void inserir(GrupoActionModel model) throws SystemException {

        HibernateUtil.getCurrentSession().save(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void alterar(GrupoActionModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    public GrupoActionModel consultarPorPk(GrupoActionModel model) throws SystemException {
        return (GrupoActionModel) HibernateUtil.getCurrentSession().get(GrupoActionModel.class, model.getGrupoActionIDModel().getId());
    }

    public List<GrupoActionModel> obterTodos() throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(GrupoActionModel.class).list();
    }

    @HibernateTransaction
    public void excluirTodos(String[] array) throws SystemException {
    }

    public List<GrupoActionModel> obterPorGrupo(GrupoModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(GrupoActionModel.class);

        criteria.add(Restrictions.eq("grupoActionIDModel.grupo.id", model.getId()));

        return criteria.list();

    }

    @HibernateTransaction
    public void excluirTodosPorGrupo(GrupoModel model) throws SystemException {

        Query query = HibernateUtil.getCurrentSession().getNamedQuery("br.com.pempec.models.GrupoActionModel.deletarGrupoActionPorIdGrupo");
        query.setParameter("id_grupo", model.getId());
        query.executeUpdate();
    }

    @HibernateTransaction
    public void inserirPermissaoPorGrupo(GrupoModel model, String[] permissoes) throws SystemException {

        for (String permissao : permissoes) {

            GrupoActionModel grupoActionModel = new GrupoActionModel();

            GrupoActionIDModel grupoActionIDModel = new GrupoActionIDModel();

            grupoActionIDModel.setId(PempecKeyGenerator.generateKeyLong());

            grupoActionIDModel.setGrupo(model);

            String[] aux = permissao.split(":");

            TelaModel tela = new TelaModel();
            tela.setId(PempecParse.stringToLong(aux[0]));

            ActionModel action = new ActionModel();
            action.setId(PempecParse.stringToLong(aux[1]));

            grupoActionIDModel.setTela(tela);
            grupoActionIDModel.setAction(action);

            grupoActionModel.setGrupoActionIDModel(grupoActionIDModel);

            HibernateUtil.getCurrentSession().save(grupoActionModel);
            Repopulador.repopulador();

        }

    }
}
