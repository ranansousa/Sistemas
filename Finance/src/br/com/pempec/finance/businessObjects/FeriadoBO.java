package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FeriadoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class FeriadoBO {

    public void inserir(Collection<FeriadoModel> model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getFeriadoDAO().inserir(model);
    }

    public void alterar(FeriadoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getFeriadoDAO().alterar(model);
    }

    public void excluir(FeriadoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getFeriadoDAO().excluir(model);
    }

    public List<FeriadoModel> obterTodos() {
        try {
            return DAOFactory.getInstance().getFeriadoDAO().obterTodos();
        } catch (SystemException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public List<FeriadoModel> obterPorFiltro(FeriadoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getFeriadoDAO().obterPorFiltro(model);
    }
}
