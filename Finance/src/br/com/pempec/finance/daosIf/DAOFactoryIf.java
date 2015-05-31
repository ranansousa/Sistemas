package br.com.pempec.finance.daosIf;

import br.com.pempec.finance.exceptions.SystemException;

/**
 * Interface para Definir todos os objetos de persist�ncia da aplica��o.
 * Objetivo encapsular a persitencia.
 *
 * @author PEMPEC
 *
 */
public interface DAOFactoryIf {

    public EstadoDAOIf getEstadoDAO() throws SystemException;

    public BackupDAOIf getBackupDAO() throws SystemException;

    public CartaoCreditoDAOIf getCartaoCreditoDAO() throws SystemException;

    public TipoNotaFiscalDAOIf getTipoNotaFiscalDAO() throws SystemException;

    public ContaBancariaDAOIf getContaBancariaDAO() throws SystemException;

    public ContaBancariaCreditoDAOIf getContaBancariaCreditoDAO() throws SystemException;

    public ContaBancariaDebitoDAOIf getContaBancariaDebitoDAO() throws SystemException;

    public ContaBancariaTransferenciaDAOIf getContaBancariaTransferenciaDAO() throws SystemException;

    public ContaBancariaChequeDAOIf getContaBancariaChequeDAO() throws SystemException;

    public ConstantesDAOIf getConstantesDAO() throws SystemException;

    public LayoutChequeDAOIf getLayoutChequeDAO() throws SystemException;

    public FuncionarioDAOIf getFuncionarioDAO() throws SystemException;

    public GrupoActionDAOIf getGrupoActionDAO() throws SystemException;

    public GrupoDAOIf getGrupoDAO() throws SystemException;

    public GrupoUsuarioDAOIf getGrupoUsuarioDAO() throws SystemException;

    public OrganizacaoDAOIf getOrganizacaoDAO() throws SystemException;

    public UsuarioActionDAOIf getUsuarioActionDAO() throws SystemException;

    public UsuarioDAOIf getUsuarioDAO() throws SystemException;

    public TelaDAOIf getTelaDAO() throws SystemException;

    public TipoCedenteDAOIf getTipoCedenteDAO() throws SystemException;

    public TipoSacadoDAOIf getTipoSacadoDAO() throws SystemException;

    public TipoStatusDAOIf getTipoStatusDAO() throws SystemException;

    public TipoOperacaoBancariaDAOIf getTipoOperacaoBancariaDAO() throws SystemException;

    public TipoDeducaoDAOIf getTipoDeducaoDAO() throws SystemException;

    public TipoAcrescimoDAOIf getTipoAcrescimoDAO() throws SystemException;

    public TipoCobrancaDAOIf getTipoCobrancaDAO() throws SystemException;

    public HistoricoDAOIf getHistoricoDAO() throws SystemException;

    public CentroCustoDAOIf getCentroCustoDAO() throws SystemException;

    public ContatoDAOIf getContatoDAO() throws SystemException;

    public ContaContabilDAOIf getContaContabilDAO() throws SystemException;

    public CidadeDAOIf getCidadeDAO() throws SystemException;

    public BairroDAOIf getBairroDAO() throws SystemException;

    public EnderecoDAOIf getEnderecoDAO() throws SystemException;

    public CedenteDAOIf getCedenteDAO() throws SystemException;

    public SacadoDAOIf getSacadoDAO() throws SystemException;

    public LocalPagamentoDAOIf getLocalPagamentoDAO() throws SystemException;

    public FormaPagamentoDAOIf getFormaPagamentoDAO() throws SystemException;

    public BancoDAOIf getBancoDAO() throws SystemException;

    public TituloPagarDAOIf getTituloPagarDAO() throws SystemException;

    public TituloPagarBaixaDAOIf getTituloPagarBaixaDAO() throws SystemException;

    public TituloPagarBaixaFormaPagamentoDAOIf getTituloPagarBaixaFormaPagamentoDAO() throws SystemException;

    public TituloPagarBaixaChequeDAOIf getTituloPagarBaixaChequeDAO() throws SystemException;

    public TituloPagarBaixaAcrescimoDAOIf getTituloPagarBaixaAcrescimoDAO() throws SystemException;

    public TituloPagarBaixaDeducaoDAOIf getTituloPagarBaixaDeducaoDAO() throws SystemException;

    public TituloPagarRateioCCDAOIf getTituloPagarRateioCCDAO() throws SystemException;

    // titulo a receber
    public TituloReceberRateioCCDAOIf getTituloReceberRateioCCDAO() throws SystemException;

    public TituloReceberDAOIf getTituloReceberDAO() throws SystemException;

    public TituloReceberBaixaDAOIf getTituloReceberBaixaDAO() throws SystemException;

    public TituloReceberBaixaFormaPagamentoDAOIf getTituloReceberBaixaFormaPagamentoDAO() throws SystemException;

    public TituloReceberBaixaChequeDAOIf getTituloReceberBaixaChequeDAO() throws SystemException;

    public TituloReceberBaixaAcrescimoDAOIf getTituloReceberBaixaAcrescimoDAO() throws SystemException;

    public TituloReceberBaixaDeducaoDAOIf getTituloReceberBaixaDeducaoDAO() throws SystemException;

    public LoteContabilDAOIf getLoteContabilDAO() throws SystemException;

    public LoteDepositoDAOIf getLoteDepositoDAO() throws SystemException;

    public MegaContabilDAOIf getMegaContabilDAO() throws SystemException;

    public LotePagamentoTituloDAOIf getLotePagamentoTituloDAO() throws SystemException;

    public ServidorEmailDAOIf getServidorEmailDAO() throws SystemException;

    public ParametrosDAOIf getParametrosDAO() throws SystemException;

    public FeriadoDAOIf getFeriadoDAO() throws SystemException;
}
