package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TipoStatusModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TipoStatusDAOIf {

    public void inserir(TipoStatusModel model) throws SystemException;

    public void alterar(TipoStatusModel model) throws SystemException;

    public TipoStatusModel consultarPorPk(TipoStatusModel model)
            throws SystemException;

    public List<TipoStatusModel> obterPorFiltro(TipoStatusModel model)
            throws SystemException;

    public List<TipoStatusModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TipoStatusModel> coll)
            throws SystemException;

    public TipoStatusModel consultarPorTemplate(TipoStatusModel model)
            throws SystemException;

    public void excluir(TipoStatusModel model) throws SystemException;
}
