/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models.reports;

import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.PempecParse;
import java.io.Serializable;
import java.util.Date;

/**
 * Esta classe e utilizada para guardar os filtros do relatorio.
 *
 * @author PEMPEC
 */
public class ReportFluxoCaixa implements Serializable {

    private String razaoSocial;
    private String cnpj;
    private String nomeUsuario;
    private Date dataEmissaoRelatorio;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {

        this.cnpj = cnpj;
    }

    public Date getDataEmissaoRelatorio() {
        return dataEmissaoRelatorio;
    }

    public void setDataEmissaoRelatorio(Date dataEmissaoRelatorio) {

        if (dataEmissaoRelatorio != null) {

            this.dataEmissaoRelatorio = dataEmissaoRelatorio;

        } else {

            this.dataEmissaoRelatorio = PempecParse.dateToDate(new Date());
        }
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        if (nomeUsuario != null || !nomeUsuario.isEmpty()) {

            this.nomeUsuario = nomeUsuario;

        } else {

            this.nomeUsuario = Controller.getUsuarioLogado().getNome();
        }
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
}