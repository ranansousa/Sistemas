package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ServidorEmailModel;

/**
 * @author PEMPEC
 */
public interface ServidorEmailDAOIf {

    public void salvar(ServidorEmailModel model) throws SystemException;

    public ServidorEmailModel consultar() throws SystemException;
}
