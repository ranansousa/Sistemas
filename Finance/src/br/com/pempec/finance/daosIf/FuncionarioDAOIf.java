package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface FuncionarioDAOIf {

    public void inserir(FuncionarioModel model) throws SystemException;

    public void alterar(FuncionarioModel model) throws SystemException;

    public FuncionarioModel consultarPorPk(FuncionarioModel model)
            throws SystemException;

    public List<FuncionarioModel> obterPorFiltro(FuncionarioModel model)
            throws SystemException;

    public List<FuncionarioModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<FuncionarioModel> obterTodosComEmail(OrganizacaoModel model)
            throws SystemException;

    public void excluir(FuncionarioModel model) throws SystemException;

    public FuncionarioModel consultarPorTemplate(FuncionarioModel model)
            throws SystemException;

    public void excluirTodos(Collection<FuncionarioModel> coll)
            throws SystemException;
}
