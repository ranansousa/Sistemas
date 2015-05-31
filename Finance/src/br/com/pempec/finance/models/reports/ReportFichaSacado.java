package br.com.pempec.finance.models.reports;

import java.io.Serializable;
import java.util.Date;

// @author PEMPEC
public class ReportFichaSacado implements Serializable {

    //cabecalho
    private String razaoSocialOrganizacao;
    private String cnpjOrganizacao;
    private String nomeUsuario;
    private Date dataEmissaoRelatorio;
    //objeto
    private String tipoSacado;
    private String logradouro;
    private String complemento;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private String bairro;
    private String email;
    private String telefoneFixo;
    private String telefonePrimeiroCelular;
    private String nome;
    private String cpfCnpj;
    private String cga;
    private String personalidade;
    private String banco;
    private String agencia;
    private String conta;
    private String contaContabil;
    private String digitoContaContabil;
    private Double acumuladoRecebido;
    private Double acumuladoReceber;
    private Double total;
    private String situacao;

    public Double getAcumuladoReceber() {
        return acumuladoReceber;
    }

    public void setAcumuladoReceber(Double acumuladoReceber) {
        this.acumuladoReceber = acumuladoReceber;
    }

    public Double getAcumuladoRecebido() {
        return acumuladoRecebido;
    }

    public void setAcumuladoRecebido(Double acumuladoRecebido) {
        this.acumuladoRecebido = acumuladoRecebido;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCga() {
        return cga;
    }

    public void setCga(String cga) {
        this.cga = cga;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCnpjOrganizacao() {
        return cnpjOrganizacao;
    }

    public void setCnpjOrganizacao(String cnpjOrganizacao) {
        this.cnpjOrganizacao = cnpjOrganizacao;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getContaContabil() {
        return contaContabil;
    }

    public void setContaContabil(String contaContabil) {
        this.contaContabil = contaContabil;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public Date getDataEmissaoRelatorio() {
        return dataEmissaoRelatorio;
    }

    public void setDataEmissaoRelatorio(Date dataEmissaoRelatorio) {
        this.dataEmissaoRelatorio = dataEmissaoRelatorio;
    }

    public String getDigitoContaContabil() {
        return digitoContaContabil;
    }

    public void setDigitoContaContabil(String digitoContaContabil) {
        this.digitoContaContabil = digitoContaContabil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getPersonalidade() {
        return personalidade;
    }

    public void setPersonalidade(String personalidade) {
        this.personalidade = personalidade;
    }

    public String getRazaoSocialOrganizacao() {
        return razaoSocialOrganizacao;
    }

    public void setRazaoSocialOrganizacao(String razaoSocialOrganizacao) {
        this.razaoSocialOrganizacao = razaoSocialOrganizacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getTelefoneFixo() {
        return telefoneFixo;
    }

    public void setTelefoneFixo(String telefoneFixo) {
        this.telefoneFixo = telefoneFixo;
    }

    public String getTelefonePrimeiroCelular() {
        return telefonePrimeiroCelular;
    }

    public void setTelefonePrimeiroCelular(String telefonePrimeiroCelular) {
        this.telefonePrimeiroCelular = telefonePrimeiroCelular;
    }

    public String getTipoSacado() {
        return tipoSacado;
    }

    public void setTipoSacado(String tipoSacado) {
        this.tipoSacado = tipoSacado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double valorRecebido, Double valorReceber) {
        this.total = valorRecebido + valorReceber;
    }
}