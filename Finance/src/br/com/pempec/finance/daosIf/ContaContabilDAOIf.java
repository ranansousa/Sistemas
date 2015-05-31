package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface ContaContabilDAOIf {

    public void inserir(ContaContabilModel model) throws SystemException;

    public void alterar(ContaContabilModel model) throws SystemException;

    public ContaContabilModel consultarPorPk(ContaContabilModel model)
            throws SystemException;

    public List<ContaContabilModel> obterPorFiltro(ContaContabilModel model)
            throws SystemException;

    public List<ContaContabilModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<ContaContabilModel> coll)
            throws SystemException;

    public void excluir(ContaContabilModel model) throws SystemException;

    public ContaContabilModel consultarPorTemplate(ContaContabilModel model)
            throws SystemException;

    public void sincronizeMegaContabil(Collection<ContaContabilModel> collContaContabilInsert, Collection<ContaContabilModel> collContaContabilUpdate) throws SystemException;
}
