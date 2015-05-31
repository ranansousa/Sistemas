package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.ParametrosModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class ParametrosBO {

    public void salvar(Collection<ParametrosModel> coll, MovimentoDiarioModel movimentoDiarioModel) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getParametrosDAO().salvar(coll, movimentoDiarioModel);
    }

    public List<ParametrosModel> obterTodos() {
        try {
            return DAOFactory.getInstance().getParametrosDAO().obterTodos();
        } catch (SystemException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
