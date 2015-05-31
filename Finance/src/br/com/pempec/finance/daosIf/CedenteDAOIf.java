package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface CedenteDAOIf {

    public void inserir(CedenteModel model) throws SystemException;

    public void alterar(CedenteModel model) throws SystemException;

    public CedenteModel consultarPorPk(CedenteModel model)
            throws SystemException;

    public CedenteModel consultarPorContaContabil(ContaContabilModel model)
            throws SystemException;

    public List<CedenteModel> obterPorFiltro(CedenteModel model)
            throws SystemException;

    public List<CedenteModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<CedenteModel> obterTodosCartaoCredito(OrganizacaoModel model)
            throws SystemException;

    public void excluir(CedenteModel model) throws SystemException;

    public CedenteModel consultarPorTemplate(CedenteModel model)
            throws SystemException;

    public void excluirTodos(Collection<CedenteModel> coll)
            throws SystemException;
}
