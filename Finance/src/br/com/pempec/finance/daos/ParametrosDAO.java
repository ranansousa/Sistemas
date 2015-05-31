package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ParametrosDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.ParametrosModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;

public class ParametrosDAO implements ParametrosDAOIf {

    @HibernateTransaction
    public void salvar(Collection<ParametrosModel> coll, MovimentoDiarioModel movimentoDiarioModel) throws SystemException {

        if (movimentoDiarioModel != null
                && !movimentoDiarioModel.getCodigo().isEmpty()) {

            HibernateUtil.getCurrentSession().save(movimentoDiarioModel);

        }

        for (ParametrosModel parametrosModel : coll) {
            HibernateUtil.getCurrentSession().update(parametrosModel);
        }

        Repopulador.repopulador();

    }

    public List<ParametrosModel> obterTodos() throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                ParametrosModel.class).list();

    }
}
