package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BancoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface BancoDAOIf {

    public void inserir(BancoModel model) throws SystemException;

    public void alterar(BancoModel model) throws SystemException;

    public BancoModel consultarPorPk(BancoModel model) throws SystemException;

    public List<BancoModel> obterPorFiltro(BancoModel model)
            throws SystemException;

    public List<BancoModel> obterTodos() throws SystemException;

    public void excluirTodos(Collection<BancoModel> coll)
            throws SystemException;

    public BancoModel consultarPorTemplate(BancoModel model)
            throws SystemException;

    public void excluir(BancoModel model) throws SystemException;
}
