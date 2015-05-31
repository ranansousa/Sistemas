package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.TelaDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.TelaModel;
import java.util.List;
import org.hibernate.Criteria;

//EQUIPE PEMPEC
public class TelaDAO implements TelaDAOIf {

    public List<TelaModel> obterTodos() throws SystemException {
        Criteria criteria = HibernateUtil.getCurrentSession().createCriteria(TelaModel.class);
        return criteria.list();
    }
}
