package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloReceberBaixaDAOIf {

    public void salvar(TituloReceberBaixaModel model, TituloReceberModel titulo) throws SystemException;

    public TituloReceberBaixaModel consultarPorPk(TituloReceberBaixaModel model)
            throws SystemException;

    public List<TituloReceberBaixaModel> obterBaixasExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TituloReceberBaixaModel> obterBaixasExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TituloReceberBaixaModel> obterPorFiltro(TituloReceberBaixaModel model)
            throws SystemException;

    public List<TituloReceberBaixaModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public List<TituloReceberBaixaModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException;

    public void excluir(TituloReceberBaixaModel model, TituloReceberModel titulo) throws SystemException;

    public TituloReceberBaixaModel consultarPorTemplate(TituloReceberBaixaModel model)
            throws SystemException;

    public TituloReceberBaixaModel consultarPorTitulo(TituloReceberModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloReceberBaixaModel> coll)
            throws SystemException;

    public void salvarLote(LoteRecebimentoTituloModel lote, Collection<TituloReceberBaixaModel> collBaixa, Collection<TituloReceberModel> collTitulo) throws SystemException;
}
