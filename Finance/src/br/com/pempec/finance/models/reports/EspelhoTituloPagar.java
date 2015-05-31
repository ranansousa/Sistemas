package br.com.pempec.finance.models.reports;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author PEMPEC
 */
public class EspelhoTituloPagar implements Serializable {

    //Informacoes da Organizacao
    private String razaoSocial;
    private String endereco;
    private String cnpj;
    //Informacoes do titulo
    private String numeroDocumento;
    private String situacao;
    private Double valor;
    private String parcela;
    private Date dataEmissao;
    private String responsavel;
    private String localPagamento;
    private String tipoCobranca;
    private String descricao;
    private String codigoHistorico;
    private String historico;
    private String cedente;
    private String cpfCnpjCedente;
    private String cdBanco;
    private String cdAgencia;
    private String cdConta;
    private String contaContabilCredito;
    private String contaContabilDebito;
    private String codigoCentroCusto;
    private String centroCusto;
    private String exportacao;
    //Informacoes complementares
    private Date previsaoCartorio;
    private String moeda;
    private String contrato;
    private String codigoBarras;
    private String carteira;
    private String observacaoComplementar;
    //Informacoes da NF Entrada
    private String numeroNF;
    private String serie;
    private String subSerie;
    private Double valorNF;
    private String tipoDocumento;
    private Date dataEmissaoNF;
    private String issRetido;
    private String aliquota;
    private Double valorISS;
    private Date dataISS;
    private String observacaoNF;
    //Informacoes do Pagamento
    private Date dataPagamento;
    private Double valorPago;
    private String tipoPagamento;
    //Informacoes do Usuario
    private String nomeUsuario;
    private String dataHoraImpresso;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getAliquota() {
        return aliquota;
    }

    public void setAliquota(String aliquota) {
        this.aliquota = aliquota;
    }

    public String getCarteira() {
        return carteira;
    }

    public void setCarteira(String carteira) {
        this.carteira = carteira;
    }

    public String getCedente() {
        return cedente;
    }

    public void setCedente(String cedente) {
        this.cedente = cedente;
    }

    public String getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(String centroCusto) {
        this.centroCusto = centroCusto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoCentroCusto() {
        return codigoCentroCusto;
    }

    public void setCodigoCentroCusto(String codigoCentroCusto) {
        this.codigoCentroCusto = codigoCentroCusto;
    }

    public String getCodigoHistorico() {
        return codigoHistorico;
    }

    public void setCodigoHistorico(String codigoHistorico) {
        this.codigoHistorico = codigoHistorico;
    }

    public String getContaContabilCredito() {
        return contaContabilCredito;
    }

    public void setContaContabilCredito(String contaContabilCredito) {
        this.contaContabilCredito = contaContabilCredito;
    }

    public String getContaContabilDebito() {
        return contaContabilDebito;
    }

    public void setContaContabilDebito(String contaContabilDebito) {
        this.contaContabilDebito = contaContabilDebito;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getCpfCnpjCedente() {
        return cpfCnpjCedente;
    }

    public void setCpfCnpjCedente(String cpfCnpjCedente) {
        this.cpfCnpjCedente = cpfCnpjCedente;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    public Date getDataEmissaoNF() {
        return dataEmissaoNF;
    }

    public void setDataEmissaoNF(Date dataEmissaoNF) {
        this.dataEmissaoNF = dataEmissaoNF;
    }

    public Date getDataISS() {
        return dataISS;
    }

    public void setDataISS(Date dataISS) {
        this.dataISS = dataISS;
    }

    public String getObservacaoNF() {
        return observacaoNF;
    }

    public void setObservacaoNF(String observacaoNF) {
        this.observacaoNF = observacaoNF;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getExportacao() {
        return exportacao;
    }

    public void setExportacao(String exportacao) {
        this.exportacao = exportacao;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getIssRetido() {
        return issRetido;
    }

    public void setIssRetido(String issRetido) {
        this.issRetido = issRetido;
    }

    public String getLocalPagamento() {
        return localPagamento;
    }

    public void setLocalPagamento(String localPagamento) {
        this.localPagamento = localPagamento;
    }

    public String getMoeda() {
        return moeda;
    }

    public void setMoeda(String moeda) {
        this.moeda = moeda;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getNumeroNF() {
        return numeroNF;
    }

    public void setNumeroNF(String numeroNF) {
        this.numeroNF = numeroNF;
    }

    public String getObservacaoComplementar() {
        return observacaoComplementar;
    }

    public void setObservacaoComplementar(String observacaoComplementar) {
        this.observacaoComplementar = observacaoComplementar;
    }

    public String getParcela() {
        return parcela;
    }

    public void setParcela(String parcela) {
        this.parcela = parcela;
    }

    public Date getPrevisaoCartorio() {
        return previsaoCartorio;
    }

    public void setPrevisaoCartorio(Date previsaoCartorio) {
        this.previsaoCartorio = previsaoCartorio;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getSubSerie() {
        return subSerie;
    }

    public void setSubSerie(String subSerie) {
        this.subSerie = subSerie;
    }

    public String getTipoCobranca() {
        return tipoCobranca;
    }

    public void setTipoCobranca(String tipoCobranca) {
        this.tipoCobranca = tipoCobranca;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorISS() {
        return valorISS;
    }

    public void setValorISS(Double valorISS) {
        this.valorISS = valorISS;
    }

    public Double getValorNF() {
        return valorNF;
    }

    public void setValorNF(Double valorNF) {
        this.valorNF = valorNF;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public String getDataHoraImpresso() {
        return dataHoraImpresso;
    }

    public void setDataHoraImpresso(String dataHoraImpresso) {
        this.dataHoraImpresso = dataHoraImpresso;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getCdBanco() {
        return cdBanco;
    }

    public void setCdBanco(String cdBanco) {
        this.cdBanco = cdBanco;
    }

    public String getCdAgencia() {
        return cdAgencia;
    }

    public void setCdAgencia(String cdAgencia) {
        this.cdAgencia = cdAgencia;
    }

    public String getCdConta() {
        return cdConta;
    }

    public void setCdConta(String cdConta) {
        this.cdConta = cdConta;
    }
    
    
}