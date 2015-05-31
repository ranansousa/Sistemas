package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface CentroCustoDAOIf {

    public void inserir(CentroCustoModel model) throws SystemException;

    public void alterar(CentroCustoModel model) throws SystemException;

    public CentroCustoModel consultarPorPk(CentroCustoModel model)
            throws SystemException;

    public List<CentroCustoModel> obterPorFiltro(CentroCustoModel model)
            throws SystemException;

    public List<CentroCustoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<CentroCustoModel> coll)
            throws SystemException;

    public void excluir(CentroCustoModel model) throws SystemException;

    public CentroCustoModel consultarPorTemplate(CentroCustoModel model)
            throws SystemException;
}
