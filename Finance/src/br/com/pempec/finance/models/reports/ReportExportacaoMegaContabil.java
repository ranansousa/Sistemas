/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models.reports;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 * Esta classe e utilizada para guardar os filtros do relatorio.
 *
 * @author PEMPEC
 */
public class ReportExportacaoMegaContabil implements Serializable, Comparable<ReportExportacaoMegaContabil> {

    private String razaoSocial;
    private String cnpj;
    private String tabela;
    private String identificacao;
    private String historico;
    private String nome;
    private Date dataLancamento;
    private Collection<ReportExportacaoMegaContabilSub> item;
    //Flag para amostrar linha no relatorio
    private Boolean flag = false;

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Collection<ReportExportacaoMegaContabilSub> getItem() {
        return item;
    }

    public void setItem(Collection<ReportExportacaoMegaContabilSub> item) {
        this.item = item;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getHistorico() {
        return historico;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public String getTabela() {
        return tabela;
    }

    public void setTabela(String tabela) {
        this.tabela = tabela;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public int compareTo(ReportExportacaoMegaContabil o) {
        return this.dataLancamento.compareTo(o.getDataLancamento());
    }
}