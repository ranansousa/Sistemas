package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.TelaModel;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TelaBO {

    public List<TelaModel> obterTodos() throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTelaDAO().obterTodos();
    }
}
