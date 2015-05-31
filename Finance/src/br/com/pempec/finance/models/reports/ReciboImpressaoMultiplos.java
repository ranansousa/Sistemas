/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models.reports;

import java.util.Collection;

/**
 *
 * @author PEMPEC
 */
public class ReciboImpressaoMultiplos {

    private String razaoSocial;
    private String endereco;
    private String cnpj;
    private String numeroDocumento;
    private String descricao;
    private String favorecido;
    private String enderecoFavorecido;
    private String contatoFavorecido;
    private String cidadeEstadoFavorecido;
    private String cidade;
    private String cnpjCpfFavorecido;
    private String valorExtenso;
    private Double valor;
    private Double valorPago;
    private Boolean existeBaixa = false;
    private Boolean existeDed = false;
    private Boolean existeAcres = false;
    private Double totalDeducoes;
    private Double totalAcrescimos;
    private Collection collFormas;
    private Collection collCrossTab;
    private Collection collection;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidadeEstadoFavorecido() {
        return cidadeEstadoFavorecido;
    }

    public void setCidadeEstadoFavorecido(String cidadeEstadoFavorecido) {
        this.cidadeEstadoFavorecido = cidadeEstadoFavorecido;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpjCpfFavorecido() {
        return cnpjCpfFavorecido;
    }

    public void setCnpjCpfFavorecido(String cnpjCpfFavorecido) {
        this.cnpjCpfFavorecido = cnpjCpfFavorecido;
    }

    public Collection getCollCrossTab() {
        return collCrossTab;
    }

    public void setCollCrossTab(Collection collCrossTab) {
        this.collCrossTab = collCrossTab;
    }

    public Collection getCollFormas() {
        return collFormas;
    }

    public void setCollFormas(Collection collFormas) {
        this.collFormas = collFormas;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public String getContatoFavorecido() {
        return contatoFavorecido;
    }

    public void setContatoFavorecido(String contatoFavorecido) {
        this.contatoFavorecido = contatoFavorecido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEnderecoFavorecido() {
        return enderecoFavorecido;
    }

    public void setEnderecoFavorecido(String enderecoFavorecido) {
        this.enderecoFavorecido = enderecoFavorecido;
    }

    public Boolean getExisteAcres() {
        return existeAcres;
    }

    public void setExisteAcres(Boolean existeAcres) {
        this.existeAcres = existeAcres;
    }

    public Boolean getExisteBaixa() {
        return existeBaixa;
    }

    public void setExisteBaixa(Boolean existeBaixa) {
        this.existeBaixa = existeBaixa;
    }

    public Boolean getExisteDed() {
        return existeDed;
    }

    public void setExisteDed(Boolean existeDed) {
        this.existeDed = existeDed;
    }

    public String getFavorecido() {
        return favorecido;
    }

    public void setFavorecido(String favorecido) {
        this.favorecido = favorecido;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Double getTotalAcrescimos() {
        return totalAcrescimos;
    }

    public void setTotalAcrescimos(Double totalAcrescimos) {
        this.totalAcrescimos = totalAcrescimos;
    }

    public Double getTotalDeducoes() {
        return totalDeducoes;
    }

    public void setTotalDeducoes(Double totalDeducoes) {
        this.totalDeducoes = totalDeducoes;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getValorExtenso() {
        return valorExtenso;
    }

    public void setValorExtenso(String valorExtenso) {
        this.valorExtenso = valorExtenso;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }
}
