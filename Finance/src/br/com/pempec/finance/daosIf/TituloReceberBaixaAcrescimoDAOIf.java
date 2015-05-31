package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import java.util.Collection;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloReceberBaixaAcrescimoDAOIf {

    public void inserir(TituloReceberBaixaAcrescimoModel model) throws SystemException;

    public void alterar(TituloReceberBaixaAcrescimoModel model) throws SystemException;

    public TituloReceberBaixaAcrescimoModel consultarPorPk(TituloReceberBaixaAcrescimoModel model) throws SystemException;

    public List<TituloReceberBaixaAcrescimoModel> obterPorFiltro(TituloReceberBaixaAcrescimoModel model)
            throws SystemException;

    public List<TituloReceberBaixaAcrescimoModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloReceberBaixaAcrescimoModel> coll)
            throws SystemException;

    public TituloReceberBaixaAcrescimoModel consultarPorTemplate(TituloReceberBaixaAcrescimoModel model)
            throws SystemException;

    public void excluir(TituloReceberBaixaAcrescimoModel model) throws SystemException;
}
