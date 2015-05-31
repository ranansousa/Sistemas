package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloReceberBaixaDeducaoDAOIf {

    public void inserir(TituloReceberBaixaDeducaoModel model) throws SystemException;

    public void alterar(TituloReceberBaixaDeducaoModel model) throws SystemException;

    public TituloReceberBaixaDeducaoModel consultarPorPk(TituloReceberBaixaDeducaoModel model) throws SystemException;

    public List<TituloReceberBaixaDeducaoModel> obterPorFiltro(TituloReceberBaixaDeducaoModel model)
            throws SystemException;

    public List<TituloReceberBaixaDeducaoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloReceberBaixaDeducaoModel> coll)
            throws SystemException;

    public TituloReceberBaixaDeducaoModel consultarPorTemplate(TituloReceberBaixaDeducaoModel model)
            throws SystemException;

    public void excluir(TituloReceberBaixaDeducaoModel model) throws SystemException;
}
