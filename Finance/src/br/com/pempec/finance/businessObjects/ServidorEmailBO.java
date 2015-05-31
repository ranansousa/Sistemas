package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ServidorEmailModel;

/**
 * @author PEMPEC
 */
public class ServidorEmailBO {

    public void salvar(ServidorEmailModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getServidorEmailDAO().salvar(model);
    }

    public ServidorEmailModel consultar()
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getServidorEmailDAO().consultar();
    }
}
