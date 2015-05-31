package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.utils.Constantes;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class TipoStatusBO {

    public void inserir(TipoStatusModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoStatusDAO().inserir(model);
    }

    public void alterar(TipoStatusModel model) throws SystemException,
            ApplicationException {
        DAOFactory.getInstance().getTipoStatusDAO().alterar(model);
    }

    public TipoStatusModel consultarPorPk(TipoStatusModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoStatusDAO().consultarPorPk(model);
    }

    public List<TipoStatusModel> obterPorFiltro(TipoStatusModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoStatusDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<TipoStatusModel> coll)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getTipoStatusDAO().excluirTodos(coll);
    }

    public List<TipoStatusModel> obterTodos(OrganizacaoModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoStatusDAO().obterTodos(model);
    }

    public TipoStatusModel consultarPorTemplate(TipoStatusModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getTipoStatusDAO().consultarPorTemplate(model);
    }

    public void excluir(TipoStatusModel model)
            throws SystemException, ApplicationException {

        //Não permitindo excluir.

        String id = model.getPk().getId();

        boolean ok = id.equals(Constantes.STATUS_TITULO_NOVO)
                || id.equals(Constantes.STATUS_TITULO_PAGO)
                || id.equals(Constantes.STATUS_TITULO_PARCIAL);

        if (!ok) {

            DAOFactory.getInstance().getTipoStatusDAO().excluir(model);

        } else {

            throw new ApplicationException("Não é possivel excluir este Registro.\nAssociações Existentes!");
        }


    }
}
