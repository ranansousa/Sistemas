package br.com.pempec.finance.models;

import java.io.Serializable;

public class EnderecoModel extends FinancePkModel implements Serializable {

    private String logradouro;
    private String complemento;
    private String numero;
    private String cep;
    private BairroModel bairro;
    private CidadeModel cidade;
    private EstadoModel estado;

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

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
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

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public String toString() {
        return this.getLogradouro();
    }
}
