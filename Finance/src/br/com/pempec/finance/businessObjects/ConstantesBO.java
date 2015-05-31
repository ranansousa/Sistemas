package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ConstantesModel;

/**
 * @author PEMPEC
 */
public class ConstantesBO {

    public void alterar(ConstantesModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getConstantesDAO().alterar(model);
    }

    public ConstantesModel consultarPorPk(ConstantesModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getConstantesDAO().consultarPorPk(model);
    }
}
