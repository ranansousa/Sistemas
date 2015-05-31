package br.com.pempec.finance.daos;

import br.com.pempec.finance.daosIf.ParametrosDAOIf;
import br.com.pempec.finance.daosIf.ServidorEmailDAOIf;
import br.com.pempec.finance.hibernate.TransactionClass;
import br.com.pempec.finance.daosIf.BairroDAOIf;
import br.com.pempec.finance.daosIf.BancoDAOIf;
import br.com.pempec.finance.daosIf.BackupDAOIf;
import br.com.pempec.finance.daosIf.CartaoCreditoDAOIf;
import br.com.pempec.finance.daosIf.CedenteDAOIf;
import br.com.pempec.finance.daosIf.CentroCustoDAOIf;
import br.com.pempec.finance.daosIf.CidadeDAOIf;
import br.com.pempec.finance.daosIf.ConstantesDAOIf;
import br.com.pempec.finance.daosIf.ContaBancariaChequeDAOIf;
import br.com.pempec.finance.daosIf.ContaBancariaCreditoDAOIf;
import br.com.pempec.finance.daosIf.ContaBancariaDAOIf;
import br.com.pempec.finance.daosIf.ContaBancariaDebitoDAOIf;
import br.com.pempec.finance.daosIf.ContaBancariaTransferenciaDAOIf;
import br.com.pempec.finance.daosIf.ContaContabilDAOIf;
import br.com.pempec.finance.daosIf.ContatoDAOIf;
import br.com.pempec.finance.daosIf.DAOFactoryIf;
import br.com.pempec.finance.daosIf.EnderecoDAOIf;
import br.com.pempec.finance.daosIf.EstadoDAOIf;
import br.com.pempec.finance.daosIf.FeriadoDAOIf;
import br.com.pempec.finance.daosIf.FormaPagamentoDAOIf;
import br.com.pempec.finance.daosIf.FuncionarioDAOIf;
import br.com.pempec.finance.daosIf.GrupoActionDAOIf;
import br.com.pempec.finance.daosIf.GrupoDAOIf;
import br.com.pempec.finance.daosIf.GrupoUsuarioDAOIf;
import br.com.pempec.finance.daosIf.HistoricoDAOIf;
import br.com.pempec.finance.daosIf.LayoutChequeDAOIf;
import br.com.pempec.finance.daosIf.LocalPagamentoDAOIf;
import br.com.pempec.finance.daosIf.LoteContabilDAOIf;
import br.com.pempec.finance.daosIf.LoteDepositoDAOIf;
import br.com.pempec.finance.daosIf.LotePagamentoTituloDAOIf;
import br.com.pempec.finance.daosIf.LoteRecebimentoTituloDAOIf;
import br.com.pempec.finance.daosIf.MegaContabilDAOIf;
import br.com.pempec.finance.daosIf.MovimentoDiarioDAOIf;
import br.com.pempec.finance.daosIf.OrganizacaoDAOIf;
import br.com.pempec.finance.daosIf.SacadoDAOIf;
import br.com.pempec.finance.daosIf.TelaDAOIf;
import br.com.pempec.finance.daosIf.TesourariaCreditoDAOIf;
import br.com.pempec.finance.daosIf.TesourariaDebitoDAOIf;
import br.com.pempec.finance.daosIf.TipoAcrescimoDAOIf;
import br.com.pempec.finance.daosIf.TipoCedenteDAOIf;
import br.com.pempec.finance.daosIf.TipoCobrancaDAOIf;
import br.com.pempec.finance.daosIf.TipoDeducaoDAOIf;
import br.com.pempec.finance.daosIf.TipoNotaFiscalDAOIf;
import br.com.pempec.finance.daosIf.TipoOperacaoBancariaDAOIf;
import br.com.pempec.finance.daosIf.TipoSacadoDAOIf;
import br.com.pempec.finance.daosIf.TipoStatusDAOIf;
import br.com.pempec.finance.daosIf.TituloPagarBaixaAcrescimoDAOIf;
import br.com.pempec.finance.daosIf.TituloPagarBaixaChequeDAOIf;
import br.com.pempec.finance.daosIf.TituloPagarBaixaDAOIf;
import br.com.pempec.finance.daosIf.TituloPagarBaixaDeducaoDAOIf;
import br.com.pempec.finance.daosIf.TituloPagarBaixaFormaPagamentoDAOIf;
import br.com.pempec.finance.daosIf.TituloPagarBaixaInternetDAOIf;
import br.com.pempec.finance.daosIf.TituloPagarDAOIf;
import br.com.pempec.finance.daosIf.TituloPagarRateioCCDAOIf;
import br.com.pempec.finance.daosIf.TituloReceberBaixaAcrescimoDAOIf;
import br.com.pempec.finance.daosIf.TituloReceberBaixaChequeDAOIf;
import br.com.pempec.finance.daosIf.TituloReceberBaixaDAOIf;
import br.com.pempec.finance.daosIf.TituloReceberBaixaDeducaoDAOIf;
import br.com.pempec.finance.daosIf.TituloReceberBaixaFormaPagamentoDAOIf;
import br.com.pempec.finance.daosIf.TituloReceberDAOIf;
import br.com.pempec.finance.daosIf.TituloReceberRateioCCDAOIf;
import br.com.pempec.finance.daosIf.UsuarioActionDAOIf;
import br.com.pempec.finance.daosIf.UsuarioDAOIf;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.hibernate.HibernateInterceptorAnnotation;
import br.com.pempec.finance.models.LoteRecebimentoTituloModel;

public final class DAOFactory implements DAOFactoryIf {

    private static DAOFactory factory;

    private DAOFactory() {
    }

    public static synchronized DAOFactory getInstance() {

        if (factory == null) {
            factory = new DAOFactory();
        }

        return factory;
    }

    public ContaBancariaDAOIf getContaBancariaDAO() throws SystemException {
        return (ContaBancariaDAO) TransactionClass.create(
                ContaBancariaDAO.class, HibernateInterceptorAnnotation.class);
    }

    public BackupDAOIf getBackupDAO() throws SystemException {
        return (BackupDAO) TransactionClass.create(
                BackupDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TipoNotaFiscalDAOIf getTipoNotaFiscalDAO() throws SystemException {
        return (TipoNotaFiscalDAO) TransactionClass.create(
                TipoNotaFiscalDAO.class, HibernateInterceptorAnnotation.class);
    }

    public LayoutChequeDAOIf getLayoutChequeDAO() throws SystemException {
        return (LayoutChequeDAO) TransactionClass.create(
                LayoutChequeDAO.class, HibernateInterceptorAnnotation.class);
    }

    public ContaBancariaChequeDAOIf getContaBancariaChequeDAO() throws SystemException {
        return (ContaBancariaChequeDAO) TransactionClass.create(
                ContaBancariaChequeDAO.class, HibernateInterceptorAnnotation.class);
    }

    public ContaBancariaDebitoDAOIf getContaBancariaDebitoDAO() throws SystemException {
        return (ContaBancariaDebitoDAO) TransactionClass.create(
                ContaBancariaDebitoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public ContaBancariaCreditoDAOIf getContaBancariaCreditoDAO() throws SystemException {
        return (ContaBancariaCreditoDAO) TransactionClass.create(
                ContaBancariaCreditoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public ContaBancariaTransferenciaDAOIf getContaBancariaTransferenciaDAO() throws SystemException {
        return (ContaBancariaTransferenciaDAO) TransactionClass.create(
                ContaBancariaTransferenciaDAO.class, HibernateInterceptorAnnotation.class);
    }

    public CedenteDAOIf getCedenteDAO() throws SystemException {
        return (CedenteDAO) TransactionClass.create(CedenteDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public SacadoDAOIf getSacadoDAO() throws SystemException {
        return (SacadoDAO) TransactionClass.create(SacadoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public ContatoDAOIf getContatoDAO() throws SystemException {
        return (ContatoDAO) TransactionClass.create(ContatoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public LocalPagamentoDAOIf getLocalPagamentoDAO() throws SystemException {
        return (LocalPagamentoDAO) TransactionClass.create(
                LocalPagamentoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public FormaPagamentoDAOIf getFormaPagamentoDAO() throws SystemException {
        return (FormaPagamentoDAO) TransactionClass.create(
                FormaPagamentoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TipoCedenteDAOIf getTipoCedenteDAO() throws SystemException {
        return (TipoCedenteDAO) TransactionClass.create(TipoCedenteDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TipoSacadoDAOIf getTipoSacadoDAO() throws SystemException {
        return (TipoSacadoDAO) TransactionClass.create(TipoSacadoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TipoStatusDAOIf getTipoStatusDAO() throws SystemException {
        return (TipoStatusDAO) TransactionClass.create(TipoStatusDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TipoCobrancaDAOIf getTipoCobrancaDAO() throws SystemException {
        return (TipoCobrancaDAO) TransactionClass.create(TipoCobrancaDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TipoOperacaoBancariaDAOIf getTipoOperacaoBancariaDAO() throws SystemException {
        return (TipoOperacaoBancariaDAO) TransactionClass.create(TipoOperacaoBancariaDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public BancoDAOIf getBancoDAO() throws SystemException {
        return (BancoDAO) TransactionClass.create(BancoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public ConstantesDAOIf getConstantesDAO() throws SystemException {
        return (ConstantesDAO) TransactionClass.create(ConstantesDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public CentroCustoDAOIf getCentroCustoDAO() throws SystemException {
        return (CentroCustoDAO) TransactionClass.create(CentroCustoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public EstadoDAOIf getEstadoDAO() throws SystemException {
        return (EstadoDAO) TransactionClass.create(EstadoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public FuncionarioDAOIf getFuncionarioDAO() throws SystemException {
        return (FuncionarioDAO) TransactionClass.create(FuncionarioDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public GrupoActionDAOIf getGrupoActionDAO() throws SystemException {
        return (GrupoActionDAO) TransactionClass.create(GrupoActionDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public GrupoDAOIf getGrupoDAO() throws SystemException {
        return (GrupoDAO) TransactionClass.create(GrupoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public GrupoUsuarioDAOIf getGrupoUsuarioDAO() throws SystemException {
        return (GrupoUsuarioDAO) TransactionClass.create(GrupoUsuarioDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public OrganizacaoDAOIf getOrganizacaoDAO() throws SystemException {
        return (OrganizacaoDAO) TransactionClass.create(OrganizacaoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public UsuarioActionDAOIf getUsuarioActionDAO() throws SystemException {
        return (UsuarioActionDAO) TransactionClass.create(
                UsuarioActionDAO.class, HibernateInterceptorAnnotation.class);
    }

    public UsuarioDAOIf getUsuarioDAO() throws SystemException {
        return (UsuarioDAO) TransactionClass.create(UsuarioDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TelaDAOIf getTelaDAO() throws SystemException {
        return (TelaDAO) TransactionClass.create(TelaDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public HistoricoDAOIf getHistoricoDAO() throws SystemException {
        return (HistoricoDAO) TransactionClass.create(HistoricoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public EnderecoDAOIf getEnderecoDAO() throws SystemException {
        return (EnderecoDAO) TransactionClass.create(EnderecoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TipoDeducaoDAOIf getTipoDeducaoDAO() throws SystemException {
        return (TipoDeducaoDAO) TransactionClass.create(TipoDeducaoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TipoAcrescimoDAOIf getTipoAcrescimoDAO() throws SystemException {
        return (TipoAcrescimoDAO) TransactionClass.create(TipoAcrescimoDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public BairroDAOIf getBairroDAO() throws SystemException {
        return (BairroDAO) TransactionClass.create(
                BairroDAO.class, HibernateInterceptorAnnotation.class);
    }

    public CidadeDAOIf getCidadeDAO() throws SystemException {
        return (CidadeDAO) TransactionClass.create(
                CidadeDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TesourariaDebitoDAOIf getTesourariaDebitoDAO() throws SystemException {
        return (TesourariaDebitoDAO) TransactionClass.create(
                TesourariaDebitoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TesourariaCreditoDAOIf getTesourariaCreditoDAO() throws SystemException {
        return (TesourariaCreditoDAO) TransactionClass.create(
                TesourariaCreditoDAO.class, HibernateInterceptorAnnotation.class);
    }

    // titulo a pagar
    public TituloPagarBaixaChequeDAOIf getTituloPagarBaixaChequeDAO() throws SystemException {
        return (TituloPagarBaixaChequeDAO) TransactionClass.create(
                TituloPagarBaixaChequeDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TituloPagarBaixaInternetDAOIf getTituloPagarBaixaInternetDAO() throws SystemException {
        return (TituloPagarBaixaInternetDAO) TransactionClass.create(
                TituloPagarBaixaInternetDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TituloPagarBaixaDAOIf getTituloPagarBaixaDAO() throws SystemException {
        return (TituloPagarBaixaDAO) TransactionClass.create(
                TituloPagarBaixaDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TituloPagarBaixaFormaPagamentoDAOIf getTituloPagarBaixaFormaPagamentoDAO() throws SystemException {
        return (TituloPagarBaixaFormaPagamentoDAO) TransactionClass.create(
                TituloPagarBaixaFormaPagamentoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TituloPagarBaixaDeducaoDAOIf getTituloPagarBaixaDeducaoDAO() throws SystemException {
        return (TituloPagarBaixaDeducaoDAO) TransactionClass.create(
                TituloPagarBaixaDeducaoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TituloPagarBaixaAcrescimoDAOIf getTituloPagarBaixaAcrescimoDAO() throws SystemException {
        return (TituloPagarBaixaAcrescimoDAO) TransactionClass.create(
                TituloPagarBaixaAcrescimoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TituloPagarDAOIf getTituloPagarDAO() throws SystemException {
        return (TituloPagarDAO) TransactionClass.create(TituloPagarDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TituloPagarRateioCCDAOIf getTituloPagarRateioCCDAO() throws SystemException {
        return (TituloPagarRateioCCDAO) TransactionClass.create(TituloPagarRateioCCDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public ContaContabilDAOIf getContaContabilDAO() throws SystemException {
        return (ContaContabilDAO) TransactionClass.create(ContaContabilDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TituloReceberDAOIf getTituloReceberDAO() throws SystemException {
        return (TituloReceberDAO) TransactionClass.create(TituloReceberDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TituloReceberRateioCCDAOIf getTituloReceberRateioCCDAO() throws SystemException {
        return (TituloReceberRateioCCDAO) TransactionClass.create(TituloReceberRateioCCDAO.class,
                HibernateInterceptorAnnotation.class);
    }

    public TituloReceberBaixaChequeDAOIf getTituloReceberBaixaChequeDAO() throws SystemException {
        return (TituloReceberBaixaChequeDAO) TransactionClass.create(
                TituloReceberBaixaChequeDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TituloReceberBaixaDAOIf getTituloReceberBaixaDAO() throws SystemException {
        return (TituloReceberBaixaDAO) TransactionClass.create(
                TituloReceberBaixaDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TituloReceberBaixaFormaPagamentoDAOIf getTituloReceberBaixaFormaPagamentoDAO() throws SystemException {
        return (TituloReceberBaixaFormaPagamentoDAO) TransactionClass.create(
                TituloReceberBaixaFormaPagamentoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TituloReceberBaixaDeducaoDAOIf getTituloReceberBaixaDeducaoDAO() throws SystemException {
        return (TituloReceberBaixaDeducaoDAO) TransactionClass.create(
                TituloReceberBaixaDeducaoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public TituloReceberBaixaAcrescimoDAOIf getTituloReceberBaixaAcrescimoDAO() throws SystemException {
        return (TituloReceberBaixaAcrescimoDAO) TransactionClass.create(
                TituloReceberBaixaAcrescimoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public LoteContabilDAOIf getLoteContabilDAO() throws SystemException {
        return (LoteContabilDAO) TransactionClass.create(
                LoteContabilDAO.class, HibernateInterceptorAnnotation.class);
    }

    public LoteDepositoDAOIf getLoteDepositoDAO() throws SystemException {
        return (LoteDepositoDAO) TransactionClass.create(
                LoteDepositoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public MegaContabilDAOIf getMegaContabilDAO() throws SystemException {
        return (MegaContabilDAO) TransactionClass.create(
                MegaContabilDAO.class, HibernateInterceptorAnnotation.class);
    }

    public LotePagamentoTituloDAOIf getLotePagamentoTituloDAO() throws SystemException {
        return (LotePagamentoTituloDAO) TransactionClass.create(
                LotePagamentoTituloDAO.class, HibernateInterceptorAnnotation.class);
    }

    public MovimentoDiarioDAOIf getMovimentoDiarioDAO() throws SystemException {
        return (MovimentoDiarioDAO) TransactionClass.create(
                MovimentoDiarioDAO.class, HibernateInterceptorAnnotation.class);
    }

    public ServidorEmailDAOIf getServidorEmailDAO() throws SystemException {
        return (ServidorEmailDAO) TransactionClass.create(
                ServidorEmailDAO.class, HibernateInterceptorAnnotation.class);
    }

    public ParametrosDAOIf getParametrosDAO() throws SystemException {
        return (ParametrosDAO) TransactionClass.create(
                ParametrosDAO.class, HibernateInterceptorAnnotation.class);
    }

    public FeriadoDAOIf getFeriadoDAO() throws SystemException {
        return (FeriadoDAO) TransactionClass.create(
                FeriadoDAO.class, HibernateInterceptorAnnotation.class);
    }

    public CartaoCreditoDAOIf getCartaoCreditoDAO() throws SystemException {
        return (CartaoCreditoDAO) TransactionClass.create(
                CartaoCreditoDAO.class, HibernateInterceptorAnnotation.class);
    }
    
    public LoteRecebimentoTituloDAOIf getLoteRecebimentoTituloDAO() throws SystemException {
        return (LoteRecebimentoTituloDAO) TransactionClass.create(
                LoteRecebimentoTituloDAO.class, HibernateInterceptorAnnotation.class);
    }
}
