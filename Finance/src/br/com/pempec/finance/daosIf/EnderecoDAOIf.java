package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface EnderecoDAOIf {

    public void inserir(EnderecoModel model) throws SystemException;

    public void alterar(EnderecoModel model) throws SystemException;

    public EnderecoModel consultarPorPk(EnderecoModel model)
            throws SystemException;

    public List<EnderecoModel> obterPorFiltro(EnderecoModel model)
            throws SystemException;

    public List<EnderecoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(EnderecoModel model) throws SystemException;

    public EnderecoModel consultarPorTemplate(EnderecoModel model)
            throws SystemException;

    public void excluirTodos(Collection<EnderecoModel> coll)
            throws SystemException;
}
