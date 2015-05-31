package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author PEMPEC
 */
public interface TituloPagarBaixaDAOIf {

    public void salvar(TituloPagarBaixaModel model, TituloPagarModel titulo) throws SystemException;

    public TituloPagarBaixaModel consultarPorPk(TituloPagarBaixaModel model)
            throws SystemException;

    public List<TituloPagarBaixaModel> obterBaixasExportacao(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TituloPagarBaixaModel> obterBaixasExportacaoRelatorio(OrganizacaoModel model, Date dataInicial, Date dataFinal)
            throws SystemException;

    public List<TituloPagarBaixaModel> obterTitulosExportados(LoteContabilModel model)
            throws SystemException;

    public List<TituloPagarBaixaModel> obterPorFiltro(TituloPagarBaixaModel model)
            throws SystemException;

    public List<TituloPagarBaixaModel> obterTodos(OrganizacaoModel model)
            throws SystemException;

    public void excluir(TituloPagarBaixaModel model, TituloPagarModel titulo) throws SystemException;

    public TituloPagarBaixaModel consultarPorTemplate(TituloPagarBaixaModel model)
            throws SystemException;

    public TituloPagarBaixaModel consultarPorTitulo(TituloPagarModel model)
            throws SystemException;

    public void excluirTodos(Collection<TituloPagarBaixaModel> coll)
            throws SystemException;

    public void salvarLote(LotePagamentoTituloModel lote, Collection<TituloPagarBaixaModel> collBaixa, Collection<TituloPagarModel> collTitulo) throws SystemException;
}
