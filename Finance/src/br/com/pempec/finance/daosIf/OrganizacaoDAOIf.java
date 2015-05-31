package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.List;

/**
 *
 * @author PEMPEC
 *
 */
public interface OrganizacaoDAOIf {

    public void inserir(OrganizacaoModel model) throws SystemException;

    public void alterar(OrganizacaoModel model) throws SystemException;

    public OrganizacaoModel consultar() throws SystemException;

    public OrganizacaoModel consultarPorTemplate(OrganizacaoModel model)
            throws SystemException;

    public OrganizacaoModel consultarPorPk(OrganizacaoModel model) throws SystemException;

    public List<OrganizacaoModel> obterTodos() throws SystemException;

    public List<OrganizacaoModel> obterPorFiltro(OrganizacaoModel model) throws SystemException;

    public void excluirTodos(String[] array) throws SystemException;
}
