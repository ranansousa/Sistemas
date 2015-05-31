package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ServidorEmailDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateTransaction;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.ServidorEmailModel;
import org.hibernate.Criteria;

public class ServidorEmailDAO implements ServidorEmailDAOIf {

    @HibernateTransaction
    public void salvar(ServidorEmailModel model) throws SystemException {

        if (model.getMovimentoDiario() != null
                && !model.getMovimentoDiario().getCodigo().isEmpty()) {

            MovimentoDiarioModel mov = model.getMovimentoDiario();

            HibernateUtil.getCurrentSession().save(mov);

        }

        HibernateUtil.getCurrentSession().saveOrUpdate(model);

    }

    public ServidorEmailModel consultar() throws SystemException {

        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(
                ServidorEmailModel.class);

        criteria.setMaxResults(1);

        return (ServidorEmailModel) criteria.uniqueResult();

    }
}
