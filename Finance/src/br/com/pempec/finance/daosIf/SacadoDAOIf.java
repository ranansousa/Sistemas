package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.SacadoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface SacadoDAOIf {

    public void inserir(SacadoModel model) throws SystemException;

    public void alterar(SacadoModel model) throws SystemException;

    public SacadoModel consultarPorPk(SacadoModel model)
            throws SystemException;

    public SacadoModel consultarPorContaContabil(ContaContabilModel model)
            throws SystemException;

    public List<SacadoModel> obterPorFiltro(SacadoModel model)
            throws SystemException;

    public List<SacadoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(SacadoModel model) throws SystemException;

    public SacadoModel consultarPorTemplate(SacadoModel model)
            throws SystemException;

    public void excluirTodos(Collection<SacadoModel> coll)
            throws SystemException;
}
