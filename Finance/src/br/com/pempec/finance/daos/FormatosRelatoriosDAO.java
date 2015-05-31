package br.com.pempec.finance.daos;

import br.com.pempec.finance.hibernate.HibernateUtil;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import java.util.List;

public class FormatosRelatoriosDAO {

    public List<FormatosRelatoriosModel> obterTodos() {
        return HibernateUtil.getCurrentSession().createCriteria(
                FormatosRelatoriosModel.class).list();

    }
}
