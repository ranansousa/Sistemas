package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ConstantesModel;

/**
 * @author PEMPEC
 */
public interface ConstantesDAOIf {

    public void alterar(ConstantesModel model) throws SystemException;

    public ConstantesModel consultarPorPk(ConstantesModel model) throws SystemException;
}
