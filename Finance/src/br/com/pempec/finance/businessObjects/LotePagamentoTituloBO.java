package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class LotePagamentoTituloBO {

    public void inserir(LotePagamentoTituloModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLotePagamentoTituloDAO().inserir(model);
    }

    public void alterar(LotePagamentoTituloModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLotePagamentoTituloDAO().alterar(model);
    }

    public LotePagamentoTituloModel consultarPorPk(LotePagamentoTituloModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLotePagamentoTituloDAO().consultarPorPk(model);
    }

    public List<LotePagamentoTituloModel> obterPorFiltro(LotePagamentoTituloModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLotePagamentoTituloDAO().obterPorFiltro(model);
    }

    public void excluirTodos(Collection<LotePagamentoTituloModel> coll) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLotePagamentoTituloDAO().excluirTodos(coll);
    }

    public List<LotePagamentoTituloModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLotePagamentoTituloDAO().obterTodos(model);
    }

    public void excluir(LotePagamentoTituloModel model)
            throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLotePagamentoTituloDAO().excluir(model);
    }

    public LotePagamentoTituloModel consultarPorTemplate(LotePagamentoTituloModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLotePagamentoTituloDAO().consultarPorTemplate(model);
    }

    public Boolean validaExclusao(LotePagamentoTituloModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLotePagamentoTituloDAO().validaExclusao(model);
    }
}
