package br.com.pempec.finance.models;

/**
 * @author Equipe Pempec
 */
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

public class MovimentoDiarioModel extends FinancePkModel implements Serializable {

    //controle
    private Long numeroMovimento;
    private Date dataRegistro;
    //objeto
    private UsuarioModel usuario;
    private String codigo;
    private String objeto;
    private String descricaoObjeto;
    private String acao;
    private Double valorOperacao;
    private String statusFinalObjeto;
    //rede
    private String nomeEstacao;
    private String nomeServer;
    private String ipEstacao;
    private String observacao;

    public MovimentoDiarioModel() {

        try {

            setIpEstacao(InetAddress.getLocalHost().getHostAddress());

            setNomeEstacao(InetAddress.getLocalHost().getHostName());

            setNomeServer(InetAddress.getLocalHost().getCanonicalHostName());

            setUsuario(Controller.getUsuarioLogado());

            setNumeroMovimento(PempecKeyGenerator.generateKeyLong());

            setDataRegistro(PempecParse.dateToDate(Controller.getDataServidorBD()));

        } catch (UnknownHostException ex) {

            ex.printStackTrace();
        }


    }

    public MovimentoDiarioModel(Long numeroDocumento) {
        this.numeroMovimento = numeroDocumento;
    }

    public String getAcao() {
        return acao;
    }

    public void setAcao(String acao) {
        this.acao = acao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public Long getNumeroMovimento() {
        return numeroMovimento;
    }

    public void setNumeroMovimento(Long numeroMovimento) {
        this.numeroMovimento = numeroMovimento;
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

    public String getStatusFinalObjeto() {
        return statusFinalObjeto;
    }

    public void setStatusFinalObjeto(String statusFinalObjeto) {
        this.statusFinalObjeto = statusFinalObjeto;
    }

    public UsuarioModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }

    public Double getValorOperacao() {
        return valorOperacao;
    }

    public void setValorOperacao(Double valorOperacao) {
        this.valorOperacao = valorOperacao;
    }
}
