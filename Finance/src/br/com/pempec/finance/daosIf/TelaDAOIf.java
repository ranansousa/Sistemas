package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.TelaModel;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TelaDAOIf {

    public List<TelaModel> obterTodos() throws SystemException;
}
