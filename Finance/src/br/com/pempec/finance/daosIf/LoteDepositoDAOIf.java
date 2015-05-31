package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LoteDepositoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface LoteDepositoDAOIf {

    public void inserir(LoteDepositoModel model) throws SystemException;

    public void alterar(LoteDepositoModel model) throws SystemException;

    public LoteDepositoModel consultarPorPk(LoteDepositoModel model)
            throws SystemException;

    public List<LoteDepositoModel> obterPorFiltro(LoteDepositoModel model)
            throws SystemException;

    public List<LoteDepositoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(LoteDepositoModel model) throws SystemException;

    public LoteDepositoModel consultarPorTemplate(LoteDepositoModel model)
            throws SystemException;

    public void excluirTodos(Collection<LoteDepositoModel> coll)
            throws SystemException;
}
