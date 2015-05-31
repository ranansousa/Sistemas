package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.ParametrosModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface ParametrosDAOIf {

    public void salvar(Collection<ParametrosModel> coll, MovimentoDiarioModel movimentoDiarioModel) throws SystemException;

    public List<ParametrosModel> obterTodos() throws SystemException;
;
}
