package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface LoteContabilDAOIf {

    public void inserir(LoteContabilModel model) throws SystemException;

    public void alterar(LoteContabilModel model) throws SystemException;

    public LoteContabilModel consultarPorPk(LoteContabilModel model)
            throws SystemException;

    public List<LoteContabilModel> obterPorFiltro(LoteContabilModel model)
            throws SystemException;

    public List<LoteContabilModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(LoteContabilModel model) throws SystemException;

    public LoteContabilModel consultarPorTemplate(LoteContabilModel model)
            throws SystemException;

    public void excluirTodos(Collection<LoteContabilModel> coll)
            throws SystemException;
}
