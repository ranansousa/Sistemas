package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface ContaBancariaDAOIf {

    public void inserir(ContaBancariaModel model) throws SystemException;

    public void alterar(ContaBancariaModel model) throws SystemException;

    public ContaBancariaModel consultarPorPk(ContaBancariaModel model)
            throws SystemException;

    public List<ContaBancariaModel> obterPorFiltro(ContaBancariaModel model)
            throws SystemException;

    public List<ContaBancariaModel> montarComboDestino(ContaBancariaModel model)
            throws SystemException;

    public List<ContaBancariaModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    /*public List<ContaBancariaModel> obterPorTitular(String titular)
     throws SystemException;*/
    public void excluirTodos(Collection<ContaBancariaModel> coll)
            throws SystemException;

    public void excluir(ContaBancariaModel model) throws SystemException;

    public ContaBancariaModel consultarPorTemplate(ContaBancariaModel model)
            throws SystemException;
}
