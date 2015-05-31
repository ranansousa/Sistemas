package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ConstantesDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.ConstantesModel;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class ConstantesDAO implements ConstantesDAOIf {

    @HibernateTransaction
    public void alterar(ConstantesModel model) throws SystemException {
        HibernateUtil.getCurrentSession().update(model);
    }

    public ConstantesModel consultarPorPk(ConstantesModel model) throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ConstantesModel.class);

        criteria.add(Restrictions.eq("id", model.getId()));

        criteria.setMaxResults(1);

        return (ConstantesModel) criteria.uniqueResult();
    }
}
