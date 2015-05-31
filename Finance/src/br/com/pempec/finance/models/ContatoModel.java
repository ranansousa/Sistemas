package br.com.pempec.finance.models;

import java.io.Serializable;

public class ContatoModel extends FinancePkModel implements Serializable {

    private String telefone;
    private String celular;
    private String email;
    private String msn;

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    @Override
    public String toString() {
        return this.getEmail();
    }
}
