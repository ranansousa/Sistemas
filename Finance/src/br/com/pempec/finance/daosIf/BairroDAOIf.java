package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.BairroModel;
import br.com.pempec.finance.models.CidadeModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface BairroDAOIf {

    public void inserir(BairroModel model) throws SystemException;

    public void alterar(BairroModel model) throws SystemException;

    public BairroModel consultarPorPk(BairroModel model)
            throws SystemException;

    public List<BairroModel> obterPorFiltro(BairroModel model)
            throws SystemException;

    public List<BairroModel> obterTodos()
            throws SystemException;

    public List<BairroModel> obterPorCidade(CidadeModel model)
            throws SystemException;

    public void excluir(BairroModel model) throws SystemException;

    public BairroModel consultarPorTemplate(BairroModel model)
            throws SystemException;

    public void excluirTodos(Collection<BairroModel> coll)
            throws SystemException;
}
