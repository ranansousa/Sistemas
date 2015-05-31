package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class ContaBancariaBO {

    public void inserir(ContaBancariaModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaDAO().inserir(model);
    }

    public void alterar(ContaBancariaModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getContaBancariaDAO().alterar(model);
    }

    public ContaBancariaModel consultarPorPk(ContaBancariaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDAO().consultarPorPk(
                model);
    }

    public List<ContaBancariaModel> obterPorFiltro(ContaBancariaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<ContaBancariaModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaBancariaDAO().excluirTodos(coll);
    }

    /*public List<ContaBancariaModel> obterPorTitular(String titular)
     throws SystemException, ApplicationException {
     return DAOFactory.getInstance().getContaBancariaDAO().obterPorTitular(
     titular);
     }*/
    public List<ContaBancariaModel> montarComboDestino(ContaBancariaModel conta)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDAO().montarComboDestino(
                conta);
    }

    public List<ContaBancariaModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDAO().obterTodos(model);
    }

    public void excluir(ContaBancariaModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getContaBancariaDAO().excluir(model);
    }

    public ContaBancariaModel consultarPorTemplate(ContaBancariaModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getContaBancariaDAO().consultarPorTemplate(model);
    }
}