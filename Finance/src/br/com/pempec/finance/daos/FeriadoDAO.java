package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.FeriadoDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.FeriadoModel;
import br.com.pempec.finance.utils.Repopulador;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;

public class FeriadoDAO implements FeriadoDAOIf {

    @HibernateTransaction
    public void inserir(Collection<FeriadoModel> model) throws SystemException {
        for (FeriadoModel feriadoModel : model) {
            HibernateUtil.getCurrentSession().save(feriadoModel);
        }

        Repopulador.repopulador();

    }

    @HibernateTransaction
    public void alterar(FeriadoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
        Repopulador.repopulador();
    }

    @HibernateTransaction
    public void excluir(FeriadoModel model) throws SystemException {
        HibernateUtil.getCurrentSession().delete(model);
        Repopulador.repopulador();
    }

    public List<FeriadoModel> obterTodos() throws SystemException {
        return HibernateUtil.getCurrentSession().createCriteria(
                FeriadoModel.class).list();
    }

    public List<FeriadoModel> obterPorFiltro(FeriadoModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                FeriadoModel.class);

        return criteria.list();

    }
}
