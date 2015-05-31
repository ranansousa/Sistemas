package br.com.pempec.finance.models.reports;

import java.io.Serializable;
import java.util.Date;

/**
 * Esta classe e utilizada para guardar os filtros do relatorio.
 *
 * @author PEMPEC
 */
public class ReportMovimentoDiario implements Serializable {

    private String razaoSocial;
    private String cnpj;
    private String usuario;
    private Long numeroMovimento;
    private Date dataEmissaoRelatorio;
    private Date dataRegistro;
    private String codigo;
    private String objeto;
    private String descricaoObjeto;
    private String acao;
    private Double valorOperacao;
    private String statusFinalObjeto;
    private String nomeEstacao;
    private String nomeServer;
    private String ipEstacao;
    private String observacao;

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getDataEmissaoRelatorio() {
        return dataEmissaoRelatorio;
    }

    public void setDataEmissaoRelatorio(Date dataEmissaoRelatorio) {
        this.dataEmissaoRelatorio = dataEmissaoRelatorio;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public String getDescricaoObjeto() {
        return descricaoObjeto;
    }

    public void setDescricaoObjeto(String descricaoObjeto) {
        this.descricaoObjeto = descricaoObjeto;
    }

    public String getIpEstacao() {
        return ipEstacao;
    }

    public void setIpEstacao(String ipEstacao) {
        this.ipEstacao = ipEstacao;
    }

    public String getNomeEstacao() {
        return nomeEstacao;
    }

    public void setNomeEstacao(String nomeEstacao) {
        this.nomeEstacao = nomeEstacao;
    }

    public String getNomeServer() {
        return nomeServer;
    }

    public void setNomeServer(String nomeServer) {
        this.nomeServer = nomeServer;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getStatusFinalObjeto() {
        return statusFinalObjeto;
    }

    public void setStatusFinalObjeto(String statusFinalObjeto) {
        this.statusFinalObjeto = statusFinalObjeto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Double getValorOperacao() {
        return valorOperacao;
    }

    public void setValorOperacao(Double valorOperacao) {
        this.valorOperacao = valorOperacao;
    }

    public void setNumeroMovimento(Long numeroMovimento) {
        this.numeroMovimento = numeroMovimento;
    }

    public Long getNumeroMovimento() {
        return numeroMovimento;
    }
}