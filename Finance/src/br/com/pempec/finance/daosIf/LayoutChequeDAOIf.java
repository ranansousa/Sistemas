package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LayoutChequeModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface LayoutChequeDAOIf {

    public void inserir(LayoutChequeModel model) throws SystemException;

    public void alterar(LayoutChequeModel model) throws SystemException;

    public LayoutChequeModel consultarPorPk(LayoutChequeModel model)
            throws SystemException;

    public List<LayoutChequeModel> obterPorFiltro(LayoutChequeModel model)
            throws SystemException;

    public List<LayoutChequeModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<LayoutChequeModel> coll)
            throws SystemException;

    public void excluir(LayoutChequeModel model) throws SystemException;

    public LayoutChequeModel consultarPorTemplate(LayoutChequeModel model)
            throws SystemException;
}
