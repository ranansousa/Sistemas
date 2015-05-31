package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.List;

/**
 * @author PEMPEC
 */
public class OrganizacaoBO {

    public void inserir(OrganizacaoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getOrganizacaoDAO().inserir(model);
    }

    public void alterar(OrganizacaoModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getOrganizacaoDAO().alterar(model);
    }

    public OrganizacaoModel consultar() throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getOrganizacaoDAO().consultar();
    }

    public OrganizacaoModel consultarPorPk(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getOrganizacaoDAO().consultarPorPk(model);
    }

    public List<OrganizacaoModel> obterPorFiltro(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getOrganizacaoDAO().obterPorFiltro(model);
    }

    public void excluirTodos(String[] array) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getOrganizacaoDAO().excluirTodos(array);
    }

    public List<OrganizacaoModel> obterTodos() throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getOrganizacaoDAO().obterTodos();
    }

    public OrganizacaoModel consultarPorTemplate(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getOrganizacaoDAO().consultarPorTemplate(model);
    }
}
