package br.com.pempec.finance.businessObjects;

import br.com.pempec.finance.daos.DAOFactory;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LoteRecebimentoTituloModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public class LoteRecebimentoTituloBO {

    List<LoteRecebimentoTituloModel> lista = new ArrayList<LoteRecebimentoTituloModel>();

    public void inserir(LoteRecebimentoTituloModel model) throws SystemException, ApplicationException {
        DAOFactory.getInstance().getLoteRecebimentoTituloDAO().inserir(model);
    }

    public void alterar(LoteRecebimentoTituloModel model) throws SystemException, ApplicationException {
        //  DAOFactory.getInstance().getLoteRecebimentoTituloDAO().alterar(model);
    }

    public LoteRecebimentoTituloModel consultarPorPk(LoteRecebimentoTituloModel model) throws SystemException, ApplicationException {
        return new LoteRecebimentoTituloModel(); //DAOFactory.getInstance().getLoteRecebimentoTituloDAO().consultarPorPk(model);
    }

    public List<LoteRecebimentoTituloModel> obterPorFiltro(LoteRecebimentoTituloModel model) throws SystemException, ApplicationException {
        //return DAOFactory.getInstance().getLotePagamentoTituloDAO().obterPorFiltro(model);
        return lista;

    }

    public void excluirTodos(Collection<LoteRecebimentoTituloModel> coll) throws SystemException, ApplicationException {
        // DAOFactory.getInstance().getLoteRecebimentoTituloDAO().excluirTodos(coll);
    }

    public List<LoteRecebimentoTituloModel> obterTodos(OrganizacaoModel model) throws SystemException, ApplicationException {
        //  return DAOFactory.getInstance().getLoteRecebimentoTituloDAO().obterTodos(model);
        return lista;
    }

    public void excluir(LoteRecebimentoTituloModel model)
            throws SystemException, ApplicationException {
        //  DAOFactory.getInstance().getLoteRecebimentoTituloDAO().excluir(model);
    }

    public LoteRecebimentoTituloModel consultarPorTemplate(LoteRecebimentoTituloModel model)
            throws SystemException, ApplicationException {
        return DAOFactory.getInstance().getLoteRecebimentoTituloDAO().consultarPorTemplate(model);

    }

    public Boolean validaExclusao(LoteRecebimentoTituloModel model)
            throws SystemException, ApplicationException {
        //  return DAOFactory.getInstance().getLoteRecebimentoTituloDAO().validaExclusao(model);
        return true;
    }
}
