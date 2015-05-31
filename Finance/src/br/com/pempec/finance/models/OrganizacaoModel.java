package br.com.pempec.finance.models;

import br.com.pempec.finance.utils.Controller;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class OrganizacaoModel extends FinanceIdModel implements Serializable {

    private static final long serialVersionUID = 4591409347103621949L;
    private String sistema = "PEMPECFINANCE";
    private String razaoSocial;
    private String fantasia;
    private String cnpj;
    private String inscricaoEstadual;
    private String inscricaoMunicipal;
    private String sigla;
    private String logradouro;
    private String complemento;
    private String numero;
    private String cep;
    private String ipServidor;
    private String nomeServidor;
    private String sistemaContabil;
    private EstadoModel estado;
    private CidadeModel cidade;
    private BairroModel bairro;
    private UsuarioModel usuarioPadrao; //nome fantasia, ativo, senha 123456, vinculado a esta org
    //security
    private String serial_hd;
    private String serial_cliente;
    private String licenca;
    private String socket_web;
    private String codigo_web;
    private String codinome;
    private String host_name;
    private String host_ip;
    private String versao;
    private Date dataAtualizacao;
    //exportaÃ§Ãµes
    private transient Collection<TipoOperacaoBancariaModel> lOperacaoBancaria = new ArrayList<TipoOperacaoBancariaModel>();
    private transient Collection<FormaPagamentoModel> lFormaPG = new ArrayList<FormaPagamentoModel>();
    private transient Collection<LocalPagamentoModel> lLocalPG = new ArrayList<LocalPagamentoModel>();
    private transient Collection<TipoCedenteModel> lTipoCedente = new ArrayList<TipoCedenteModel>();
    private transient Collection<CentroCustoModel> lCentroCusto = new ArrayList<CentroCustoModel>();
    private transient Collection<TipoCobrancaModel> lCobranca = new ArrayList<TipoCobrancaModel>();
    private transient Collection<TipoSacadoModel> lTipoSacado = new ArrayList<TipoSacadoModel>();
    private transient Collection<TipoStatusModel> lStatus = new ArrayList<TipoStatusModel>();
    private transient Collection<BancoModel> lBanco = new ArrayList<BancoModel>();
    private transient Collection<HistoricoModel> lHistorico = new ArrayList<HistoricoModel>();
    private transient Collection<TipoNotaFiscalModel> lTipoNotaFiscal = new ArrayList<TipoNotaFiscalModel>();
    private transient Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();
    private transient Collection<TipoAcrescimoModel> lTipoAcrecimos = new ArrayList<TipoAcrescimoModel>();
    private transient Collection<TipoDeducaoModel> lTipoDeducao = new ArrayList<TipoDeducaoModel>();
    private transient Collection<CedenteModel> lCedente = new ArrayList<CedenteModel>();
    private transient Collection<SacadoModel> lSacado = new ArrayList<SacadoModel>();
    private transient MovimentoDiarioModel movimentoDiarioModel;

    public String getSistemaContabil() {
        return sistemaContabil;
    }

    public void setSistemaContabil(String sistemaContabil) {
        this.sistemaContabil = sistemaContabil;
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public void setIpServidor(String ipServidor) {
        this.ipServidor = ipServidor;
    }

    public String getNomeServidor() {
        return nomeServidor;
    }

    public void setNomeServidor(String nomeServidor) {
        this.nomeServidor = nomeServidor;
    }

    public Collection<CedenteModel> getLCedente() {
        return lCedente;
    }

    public void setLCedente(Collection<CedenteModel> lCedente) {
        this.lCedente = lCedente;
    }

    public Collection<FuncionarioModel> getLFuncionario() {
        return lFuncionario;
    }

    public void setLFuncionario(Collection<FuncionarioModel> lFuncionario) {
        this.lFuncionario = lFuncionario;
    }

    public Collection<SacadoModel> getLSacado() {
        return lSacado;
    }

    public void setLSacado(Collection<SacadoModel> lSacado) {
        this.lSacado = lSacado;
    }

    public Collection<TipoAcrescimoModel> getLTipoAcrecimos() {
        return lTipoAcrecimos;
    }

    public void setLTipoAcrecimos(Collection<TipoAcrescimoModel> lTipoAcrecimos) {
        this.lTipoAcrecimos = lTipoAcrecimos;
    }

    public Collection<TipoDeducaoModel> getLTipoDeducao() {
        return lTipoDeducao;
    }

    public void setLTipoDeducao(Collection<TipoDeducaoModel> lTipoDeducao) {
        this.lTipoDeducao = lTipoDeducao;
    }

    public Collection<TipoNotaFiscalModel> getLTipoNotaFiscal() {
        return lTipoNotaFiscal;
    }

    public void setLTipoNotaFiscal(Collection<TipoNotaFiscalModel> lTipoNotaFiscal) {
        this.lTipoNotaFiscal = lTipoNotaFiscal;
    }

    public Collection<HistoricoModel> getLHistorico() {
        return lHistorico;
    }

    public void setLHistorico(Collection<HistoricoModel> lHistorico) {
        this.lHistorico = lHistorico;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return inscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public UsuarioModel getUsuarioPadrao() {
        return usuarioPadrao;
    }

    public void setUsuarioPadrao(UsuarioModel usuarioPadrao) {
        this.usuarioPadrao = usuarioPadrao;
    }

    public Collection<CentroCustoModel> getLCentroCusto() {
        return lCentroCusto;
    }

    public void setLCentroCusto(Collection<CentroCustoModel> lCentroCusto) {
        this.lCentroCusto = lCentroCusto;
    }

    public Collection<TipoCobrancaModel> getLCobranca() {
        return lCobranca;
    }

    public void setLCobranca(Collection<TipoCobrancaModel> lCobranca) {
        this.lCobranca = lCobranca;
    }

    public Collection<FormaPagamentoModel> getLFormaPG() {
        return lFormaPG;
    }

    public void setLFormaPG(Collection<FormaPagamentoModel> lFormaPG) {
        this.lFormaPG = lFormaPG;
    }

    public Collection<LocalPagamentoModel> getLLocalPG() {
        return lLocalPG;
    }

    public void setLLocalPG(Collection<LocalPagamentoModel> lLocalPG) {
        this.lLocalPG = lLocalPG;
    }

    public Collection<TipoOperacaoBancariaModel> getLOperacaoBancaria() {
        return lOperacaoBancaria;
    }

    public void setLOperacaoBancaria(Collection<TipoOperacaoBancariaModel> lOperacaoBancaria) {
        this.lOperacaoBancaria = lOperacaoBancaria;
    }

    public Collection<TipoStatusModel> getLStatus() {
        return lStatus;
    }

    public void setLStatus(Collection<TipoStatusModel> lStatus) {
        this.lStatus = lStatus;
    }

    public Collection<TipoCedenteModel> getLTipoCedente() {
        return lTipoCedente;
    }

    public void setLTipoCedente(Collection<TipoCedenteModel> lTipoCedente) {
        this.lTipoCedente = lTipoCedente;
    }

    public Collection<TipoSacadoModel> getLTipoSacado() {
        return lTipoSacado;
    }

    public void setLTipoSacado(Collection<TipoSacadoModel> lTipoSacado) {
        this.lTipoSacado = lTipoSacado;
    }

    public Collection<BancoModel> getLBanco() {
        return lBanco;
    }

    public void setLBanco(Collection<BancoModel> lBanco) {
        this.lBanco = lBanco;
    }

    public String getEndereco() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.logradouro);
        builder.append(", NÂº ");
        builder.append(this.numero).append("  ");
        builder.append(" BAIRRO: ");
        builder.append(this.bairro != null ? this.bairro.getDescricao() : "").append("  ");
        builder.append(" CEP: ");
        builder.append(this.cep).append("  ");
        builder.append(" CIDADE: ");
        builder.append(this.cidade != null ? this.cidade.getDescricao() : "").append("  ");
        builder.append(" UF: ");
        builder.append(this.estado != null ? this.estado.getSigla() : "");

        return builder.toString();
    }

    public BairroModel getBairro() {
        return bairro;
    }

    public void setBairro(BairroModel bairro) {
        this.bairro = bairro;
    }

    public CidadeModel getCidade() {
        return cidade;
    }

    public void setCidade(CidadeModel cidade) {
        this.cidade = cidade;
    }

    public EstadoModel getEstado() {
        return estado;
    }

    public void setEstado(EstadoModel estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.getRazaoSocial();
    }

    public String getCodigo_web() {
        return codigo_web;
    }

    public void setCodigo_web(String codigo_web) {
        this.codigo_web = codigo_web;
    }

    public String getCodinome() {
        return codinome;
    }

    public void setCodinome(String codinome) {
        this.codinome = codinome;
    }

    public String getLicenca() {
        return licenca;
    }

    public void setLicenca(String licenca) {
        this.licenca = licenca;
    }

    public String getSerial_cliente() {
        return serial_cliente;
    }

    public void setSerial_cliente(String serial_cliente) {
        this.serial_cliente = serial_cliente;
    }

    public String getSerial_hd() {
        return serial_hd;
    }

    public void setSerial_hd(String serial_hd) {
        this.serial_hd = serial_hd;
    }

    public String getSocket_web() {
        return socket_web;
    }

    public void setSocket_web(String socket_web) {

        this.socket_web = socket_web;
    }

    public String getHost_ip() {
        return host_ip;
    }

    public void setHost_ip(String host_ip) {

        if (host_ip == null) {

            this.host_ip = Controller.getIpEstacao();

        } else {

            this.host_ip = host_ip;
        }
    }

    public String getHost_name() {
        return host_name;
    }

    public void setHost_name(String host_name) {

        if (host_name == null) {

            this.host_name = Controller.getNomeEstacao();
        } else {

            this.host_name = host_name;
        }
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getSistema() {
        return sistema;
    }

    public void setSistema(String sistema) {
        this.sistema = sistema;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}// fim da class

