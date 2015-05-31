/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

/**
 *
 * @author PEMPEC
 */
public class ImpressaoMultiplosRecibosModel {

    private Boolean check = false;
    private String documento;
    private String descricao;
    private String tipo;
    private String nome;
    private Double valor;
    private TituloPagarModel tituloPagar;
    private TituloReceberModel tituloReceber;

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public TituloPagarModel getTituloPagar() {
        return tituloPagar;
    }

    public void setTituloPagar(TituloPagarModel tituloPagar) {
        this.tituloPagar = tituloPagar;
    }

    public TituloReceberModel getTituloReceber() {
        return tituloReceber;
    }

    public void setTituloReceber(TituloReceberModel tituloReceber) {
        this.tituloReceber = tituloReceber;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
