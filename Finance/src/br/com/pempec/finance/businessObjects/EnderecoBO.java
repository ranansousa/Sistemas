package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class EnderecoBO {

    public void inserir(EnderecoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getEnderecoDAO().inserir(model);
    }

    public void alterar(EnderecoModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getEnderecoDAO().alterar(model);
    }

    public EnderecoModel consultarPorPk(EnderecoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getEnderecoDAO().consultarPorPk(
                model);
    }

    public void excluir(EnderecoModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getEnderecoDAO().excluir(model);
    }

    public EnderecoModel consultarPorTemplate(EnderecoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getEnderecoDAO().consultarPorTemplate(model);
    }

    public List<EnderecoModel> obterPorFiltro(EnderecoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getEnderecoDAO().obterPorFiltro(
                model);
    }

    public void excluirTodos(Collection<EnderecoModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getEnderecoDAO().excluirTodos(coll);
    }

    public List<EnderecoModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getEnderecoDAO().obterTodos(model);
    }
}
