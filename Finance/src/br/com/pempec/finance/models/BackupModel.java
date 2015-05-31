/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.models;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author PEMPEC
 */
public class BackupModel extends FinancePkModel implements Serializable {

    private String path;
    private String data;
    private String servidor;
    private String estacao;
    private String nomeFile;
    private String localAppBackup;
    private Long tamanhoFile;
    private UsuarioModel usuarioModel;
    private Date dataBackup;
    private long qtdRegistros;
    private MovimentoDiarioModel movimentoDiarioModel;

//    public BackupModel() {
//
//        this.path = "\"" + ResourcePropertiesLocator.getMessage("path_database");
//        this.localAppBackup = ResourcePropertiesLocator.getMessage("path_firebird");
//        this.servidor = Controller.getOrganizacao().getNomeServidor();
//        this.estacao = Controller.getNomeEstacao();
//        this.usuarioModel = Controller.getUsuarioLogado();
//        this.dataBackup = Controller.getDataServidorBD();
//
//
//    }
    public BackupModel() {
    }

    public long getQtdRegistros() {
        return qtdRegistros;
    }

    public void setQtdRegistros(long qtdRegistros) {
        this.qtdRegistros = qtdRegistros;
    }

    public void setDataBackup(Date dataBackup) {
        this.dataBackup = dataBackup;
    }

    public void setNomeFile(String nomeFile) {
        this.nomeFile = nomeFile;
    }

    public Long getTamanhoFile() {
        return tamanhoFile;
    }

    public void setTamanhoFile(Long tamanhoFile) {
        this.tamanhoFile = tamanhoFile;
    }

    public String getEstacao() {
        return estacao;
    }

    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    public String getNomeFile() {
        return nomeFile;
    }

//    public void setNomeFile(String nome) {
//
//        String nameFile = nome;
//
//        Date dataServidor = new Date();
//
//        if(nome == null || nome.isEmpty()){
//
//        String data = PempecUtil.converteDataDDMMYYYY(PempecParse.dateToString(dataServidor));
//        String hora = PempecParse.getHourFromDate(dataServidor);
//        String minutos = PempecParse.getMinuteFromDate(dataServidor);
//
//        nameFile = "\\FIN_" + data + "_" + hora + "_" + minutos + "_BAK.FBK\"";
//
//        }
//
//
//        this.nomeFile = nameFile;
//
//    }
    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public MovimentoDiarioModel getMovimentoDiarioModel() {
        return movimentoDiarioModel;
    }

    public void setMovimentoDiarioModel(MovimentoDiarioModel movimentoDiarioModel) {
        this.movimentoDiarioModel = movimentoDiarioModel;
    }

    public Date getDataBackup() {

        return dataBackup;
    }

//    public void setDataBackup(Date dataBackup) {
//
//        if (dataBackup != null) {
//
//            this.dataBackup = dataBackup;
//
//        } else {
//
//            this.dataBackup = Controller.getDataServidorBD();
//        }
//    }
    public UsuarioModel getUsuarioModel() {
        return usuarioModel;
    }

    public void setUsuarioModel(UsuarioModel usuarioModel) {
        this.usuarioModel = usuarioModel;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLocalAppBackup() {
        return localAppBackup;
    }

    public void setLocalAppBackup(String localAppBackup) {
        this.localAppBackup = localAppBackup;
    }
}
