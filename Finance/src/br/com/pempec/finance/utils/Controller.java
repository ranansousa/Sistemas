package br.com.pempec.finance.utils;

import br.com.pempec.finance.businessObjects.ConstantesBO;
import br.com.pempec.finance.businessObjects.FeriadoBO;
import br.com.pempec.finance.businessObjects.GrupoActionBO;
import br.com.pempec.finance.businessObjects.GrupoUsuarioBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.ParametrosBO;
import br.com.pempec.finance.businessObjects.ServidorEmailBO;
import br.com.pempec.finance.businessObjects.UsuarioActionBO;
import br.com.pempec.finance.daos.FormatosRelatoriosDAO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ConstantesModel;
import br.com.pempec.finance.models.FeriadoModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.GrupoActionModel;
import br.com.pempec.finance.models.GrupoModel;
import br.com.pempec.finance.models.GrupoUsuarioModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.ParametrosModel;
import br.com.pempec.finance.models.ServidorEmailModel;
import br.com.pempec.finance.models.UsuarioActionModel;
import br.com.pempec.finance.models.UsuarioModel;
import com.sun.mail.smtp.SMTPTransport;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.*;

/**
 *
 * @author PEMPEC
 */
public final class Controller {

    private static OrganizacaoModel organizacao;
    private static UsuarioModel usuarioLogado;
    private static ServidorEmailModel servidorEmail;
    private static Date dataServidorBD;
    private static String ipServidorBD;
    private static String nameServidorBD;
    private static Integer qtdMovimentosHoje;
    //Armazendo na Cache.
    private static Collection<GrupoUsuarioModel> collGrupoUsuario;
    private static Collection<UsuarioActionModel> collUsuarioAction;
    private static Collection<GrupoActionModel> collGrupoAction;
    private static List<FormatosRelatoriosModel> collFormatos;
    private static List<ParametrosModel> collParametros;
    private static List<FeriadoModel> collFeriados;

    static {

        collFormatos = new FormatosRelatoriosDAO().obterTodos();

        collParametros = new ParametrosBO().obterTodos();

        collFeriados = new FeriadoBO().obterTodos();

    }

    public static void setQtdMovimentosHoje(Integer qtdMovimentosHoje) {
        Controller.qtdMovimentosHoje = qtdMovimentosHoje;
    }

    public static String getMsgCamposObrigatorios() {

        return "Todos os campos da tela bÃ¡sica sÃ£o obrigatÃ³rios.";
    }

    public static String getSO() {

        return System.getProperty("os.name");
    }

    public static String getSerialOrganizacao() {
        String serial = Controller.getOrganizacao().getRazaoSocial();
        String delimitador = "#";

        String razao = Controller.getOrganizacao().getRazaoSocial();
        String fantasia = Controller.getOrganizacao().getFantasia();
        String codinome = Controller.getOrganizacao().getCodinome();
        String licenca = Controller.getOrganizacao().getLicenca();
        String versao = Controller.getOrganizacao().getVersao();
        String host = Controller.getOrganizacao().getHost_name();
        String ip = Controller.getOrganizacao().getHost_ip();
        String cnpj = Controller.getOrganizacao().getCnpj();

        String logradouro = Controller.getOrganizacao().getLogradouro();
        String end = Controller.getOrganizacao().getEndereco();
        String bairro = Controller.getOrganizacao().getBairro().getDescricao();
        String num = Controller.getOrganizacao().getNumero();
        String cep = Controller.getOrganizacao().getCep();
        String cidade = Controller.getOrganizacao().getCidade().getDescricao();
        String estado = Controller.getOrganizacao().getEstado().getSigla();
        String user = Controller.getUsuarioLogado().getNome();


        if (razao != null) {
            serial = "1" + razao + delimitador;
        }

        if (cnpj != null) {
            serial = serial + "2" + cnpj + delimitador;
        }

        if (fantasia != null) {
            serial = serial + "3" + fantasia + delimitador;
        }

        if (codinome != null) {
            serial = serial + "4" + codinome + delimitador;
        }
        if (licenca != null) {
            serial = serial + "5" + licenca + delimitador;
        }

        if (versao != null) {
            serial = serial + "6" + versao + delimitador;
        }

        if (host != null) {
            serial = serial + "7" + host + delimitador;
        }
        if (ip != null) {
            serial = serial + "8" + ip + delimitador;
        }



        if (logradouro != null) {

            serial = serial + "9" + logradouro + delimitador;

        }

        if (end != null) {

            serial = serial + "10" + end + delimitador;

        }


        if (bairro != null) {

            serial = serial + "11" + bairro + delimitador;

        }


        if (num != null) {

            serial = serial + "12" + num + delimitador;

        }
        if (cep != null) {

            serial = serial + "13" + cep + delimitador;

        }
        if (cidade != null) {

            serial = serial + "14" + cidade + delimitador;

        }
        if (estado != null) {

            serial = serial + "15" + estado + delimitador;

        }
        if (user != null) {

            serial = serial + "16" + user + delimitador;

        }

        serial = serial + "17" + PempecParse.dateToString(Controller.getDataServidorBD());

        return serial;
    }

    public static Integer getQtdMovimentosHoje() {

        return qtdMovimentosHoje;

    }

    public static boolean getVersao() {

        boolean versao = true;
        Integer labelVersion = 2;
        Integer versaoSistemaBD = 2;

        labelVersion = Integer.parseInt(PempecUtil.somenteNumero(ResourcePropertiesLocator.getMessageAbout("labelVersion")));
        versaoSistemaBD = Integer.parseInt(PempecUtil.somenteNumero(Controller.getOrganizacao().getVersao()));

        if (labelVersion == null || versaoSistemaBD == null) {

            labelVersion = 1;
            versaoSistemaBD = 0;

        }

//        if (labelVersion < versaoSistemaBD) {  19/05/2012
//            versao = false;
//        }

        return versao;
    }

    public static String getNomeEstacao() {

        String estacao = "Local";

        try {

            estacao = InetAddress.getLocalHost().getHostName().toString().toUpperCase();

        } catch (UnknownHostException ex) {

            ex.printStackTrace();

        }

        return estacao;
    }

    public static String getIpEstacao() {

        String estacao = "127.0.0.1";

        try {

            estacao = InetAddress.getLocalHost().getHostAddress().toString();


        } catch (UnknownHostException ex) {

            ex.printStackTrace();

        }

        return estacao;
    }

    public static Boolean verificaParametroAtivo(String codigo) {

        try {

            ConstantesBO constantesBO = new ConstantesBO();

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            if (constantesModel != null && constantesModel.getCodigo().equals("1")) {
                collParametros = null;
                constantesModel.setCodigo("0");
                constantesBO.alterar(constantesModel);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        if (collParametros == null || collParametros.isEmpty()) {
            return false;
        }

        for (ParametrosModel parametro : collParametros) {
            if (parametro.getCodigo().equalsIgnoreCase(codigo) && parametro.isAtivo()) {
                return true;
            }
        }

        return false;

    }

    public static ParametrosModel findByCodigo(String codigo) {

        if (collParametros == null || collParametros.isEmpty()) {
            return null;
        }

        for (ParametrosModel param : collParametros) {
            if (codigo.equals(param.getCodigo())) {
                return param;
            }
        }

        return null;

    }

    public static String sendMessageParametro(String codigo) {

        ParametrosModel parametro = findByCodigo(codigo);

        if (parametro == null) {
            return "OperaÃ§Ã£o nÃ£o permitida! Contactar administrador -> Mensagem de parametrizaÃ§Ã£o nÃ£o definida.\n"
                    + "ConfiguraÃ§Ã£o Gerais (ParametrizaÃ§Ã£o do Sistema).";
        }

        String mensagem = parametro.getMensagem().toUpperCase();

        String mensagemOriginal = parametro.getMensagem();

        StringBuilder builder = new StringBuilder();

        if (mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_DATA)) {

            do {

                int pos = 0;

                builder.append(mensagemOriginal.substring(pos, mensagem.indexOf(Constantes.PARAMETRIZACAO_COLUNA_DATA)));

                builder.append(PempecParse.dateToString(parametro.getData()));

                pos = mensagem.indexOf(Constantes.PARAMETRIZACAO_COLUNA_DATA) + Constantes.PARAMETRIZACAO_COLUNA_DATA.length();

                mensagem = mensagemOriginal.substring(pos).toUpperCase();

                if (!mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_DATA)) {
                    builder.append(mensagemOriginal.substring(pos));
                } else {
                    mensagemOriginal = mensagemOriginal.substring(pos);
                }

            } while (mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_DATA));

        }

        mensagem = builder.toString().isEmpty() ? mensagem : builder.toString().toUpperCase();

        mensagemOriginal = builder.toString().isEmpty() ? mensagemOriginal : builder.toString();

        if (mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_VALOR)) {

            builder = new StringBuilder();

            do {

                int pos = 0;

                builder.append(mensagemOriginal.substring(pos, mensagem.indexOf(Constantes.PARAMETRIZACAO_COLUNA_VALOR)));

                builder.append(PempecParse.doubleToZero(parametro.getValor()));

                pos = mensagem.indexOf(Constantes.PARAMETRIZACAO_COLUNA_VALOR) + Constantes.PARAMETRIZACAO_COLUNA_VALOR.length();

                mensagem = mensagemOriginal.substring(pos).toUpperCase();

                if (!mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_VALOR)) {
                    builder.append(mensagemOriginal.substring(pos));
                } else {
                    mensagemOriginal = mensagemOriginal.substring(pos);
                }

            } while (mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_VALOR));

        }

        mensagem = builder.toString().isEmpty() ? mensagem : builder.toString().toUpperCase();

        mensagemOriginal = builder.toString().isEmpty() ? mensagemOriginal : builder.toString();

        if (mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_PORCENTAGEM)) {

            builder = new StringBuilder();

            do {

                int pos = 0;

                builder.append(mensagemOriginal.substring(pos, mensagem.indexOf(Constantes.PARAMETRIZACAO_COLUNA_PORCENTAGEM)));

                builder.append(PempecParse.doubleToZero(parametro.getPorcentagem()));

                pos = mensagem.indexOf(Constantes.PARAMETRIZACAO_COLUNA_PORCENTAGEM) + Constantes.PARAMETRIZACAO_COLUNA_PORCENTAGEM.length();

                mensagem = mensagemOriginal.substring(pos).toUpperCase();

                if (!mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_PORCENTAGEM)) {
                    builder.append(mensagemOriginal.substring(pos));
                } else {
                    mensagemOriginal = mensagemOriginal.substring(pos);
                }

            } while (mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_PORCENTAGEM));

        }

        mensagem = builder.toString().isEmpty() ? mensagem : builder.toString().toUpperCase();

        mensagemOriginal = builder.toString().isEmpty() ? mensagemOriginal : builder.toString();

        if (mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_STATUS)) {

            builder = new StringBuilder();

            do {

                int pos = 0;

                builder.append(mensagemOriginal.substring(pos, mensagem.indexOf(Constantes.PARAMETRIZACAO_COLUNA_STATUS)));

                builder.append(parametro.isAtivo() ? "SIM" : "NÃƒO");

                pos = mensagem.indexOf(Constantes.PARAMETRIZACAO_COLUNA_STATUS) + Constantes.PARAMETRIZACAO_COLUNA_STATUS.length();

                mensagem = mensagemOriginal.substring(pos).toUpperCase();

                if (!mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_STATUS)) {
                    builder.append(mensagemOriginal.substring(pos));
                } else {
                    mensagemOriginal = mensagemOriginal.substring(pos);
                }

            } while (mensagem.contains(Constantes.PARAMETRIZACAO_COLUNA_STATUS));

        }

        return builder.toString().isEmpty() ? mensagemOriginal : builder.toString();

    }

    public static Boolean verificaFeriado(Date data) {

        Boolean retorno = false;

        try {

            ConstantesBO constantesBO = new ConstantesBO();

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_FERIADO_CALENDARIO_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            if (constantesModel != null && constantesModel.getCodigo().equals("1")) {
                collFeriados = null;
                constantesModel.setCodigo("0");
                constantesBO.alterar(constantesModel);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (collFeriados == null || collFeriados.isEmpty()) {
            return retorno;
        }

        data = PempecParse.dateToDate(data);

        for (FeriadoModel feriado : collFeriados) {
            if (PempecParse.dateToDate(feriado.getData()).compareTo(data) == 0) {
                return true;
            }
        }

        return retorno;

    }

    public static String getIpServidorBD() {
        return ipServidorBD;
    }

    public static void setIpServidorBD(String ip) {

        ipServidorBD = ip;

    }

    public static String getNameServidorBD() {

        return nameServidorBD;

    }

    public static void setNameServidorBD(String nome) {

        nameServidorBD = nome;
    }

    public static void setDataServidorBD(Date data) {
        dataServidorBD = PempecParse.dateToDate(data);
    }

    public static Date getDataServidorBD() {
        return PempecParse.dateToDate(dataServidorBD);
    }

    public static List<FeriadoModel> getCollFeriados() {
        return collFeriados;
    }

    public static void setCollFeriados(List<FeriadoModel> collFeriados) {
        Controller.collFeriados = collFeriados;
    }

    public static List<ParametrosModel> getCollParametros() {
        return collParametros;
    }

    public static void setCollParametros(List<ParametrosModel> collParametros) {
        Controller.collParametros = collParametros;
    }

    public static void setOrganizacao(OrganizacaoModel organizacaoModel) {
        organizacao = organizacaoModel;
    }

    public static OrganizacaoModel getOrganizacao() {
        return organizacao;
    }

    public static List<FormatosRelatoriosModel> getCollFormatos() {
        return collFormatos;
    }

    public static UsuarioModel getUsuarioLogado() {
        return usuarioLogado;
    }

    public static void setUsuarioLogado(UsuarioModel usuarioModel) {
        usuarioLogado = usuarioModel;
    }

    public static Collection<GrupoActionModel> getCollGrupoAction() {
        return collGrupoAction;
    }

    public static void setCollGrupoAction(Collection<GrupoActionModel> collGrupoAction) {
        Controller.collGrupoAction = collGrupoAction;
    }

    public static Collection<GrupoUsuarioModel> getCollGrupoUsuario() {
        return collGrupoUsuario;
    }

    public static void setCollGrupoUsuario(Collection<GrupoUsuarioModel> collGrupoUsuario) {
        Controller.collGrupoUsuario = collGrupoUsuario;
    }

    public static Collection<UsuarioActionModel> getCollUsuarioAction() {
        return collUsuarioAction;
    }

    public static void setCollUsuarioAction(Collection<UsuarioActionModel> collUsuarioAction) {
        Controller.collUsuarioAction = collUsuarioAction;
    }

    public static ServidorEmailModel getServidorEmail() {
        return servidorEmail;
    }

    public static void setServidorEmail(ServidorEmailModel servidorEmail) {
        Controller.servidorEmail = servidorEmail;
    }

    public static boolean checarPermissao(Long tela, Long action) throws SystemException, ApplicationException {

        if (usuarioLogado.getEhAdministrador() != null && usuarioLogado.getEhAdministrador().intValue() == 1) {
            return true;
        }

        ConstantesBO constantesBO = new ConstantesBO();

        ConstantesModel constantesModel = new ConstantesModel();

        constantesModel.setId(Constantes.CONSTANTES_PERMISSOES_MODIFICADA);

        constantesModel = constantesBO.consultarPorPk(constantesModel);

        if (constantesModel != null && constantesModel.getCodigo().equals("1")) {
            collGrupoUsuario = null;
            collUsuarioAction = null;
            collGrupoAction = null;

            constantesModel.setCodigo("0");
            constantesBO.alterar(constantesModel);

        }

        if (checarPermissaoUsuarioAction(usuarioLogado, tela, action)) {
            return true;
        } else {
            GrupoUsuarioBO grupoUsuarioBO = new GrupoUsuarioBO();

            if (collGrupoUsuario == null || collGrupoUsuario.isEmpty()) {
                collGrupoUsuario = grupoUsuarioBO.obterPorUsuario(usuarioLogado);
            }

            for (GrupoUsuarioModel grupoUsuarioModel : collGrupoUsuario) {
                if (checarPermissaoGrupoAction(grupoUsuarioModel.getGrupoUsuarioIDModel().getGrupo(), tela, action)) {
                    return true;
                }
            }

        }

        return false;
    }

    /**
     * MÃ©todo que verifica se o usuario possui permissÃ£o a tela para
     * determinada action.
     *
     * @param usuario Usuario
     * @param action AcÃ£o
     * @param tela Tela
     * @return se tem ou nÃ£o
     * @throws Exception
     */
    public static boolean checarPermissaoUsuarioAction(UsuarioModel usuario, Long tela, Long action) throws SystemException, ApplicationException {

        boolean retorno = false;

        UsuarioActionBO usuarioActionBO = new UsuarioActionBO();

        if (collUsuarioAction == null || collUsuarioAction.isEmpty()) {
            collUsuarioAction = usuarioActionBO.obterPorUsuario(usuario);
        }

        if (collUsuarioAction == null || collUsuarioAction.isEmpty()) {
            return retorno;
        }

        for (UsuarioActionModel usuarioActionModel : collUsuarioAction) {
            if (usuarioActionModel.getUsuarioActionIDModel().getTela().getId().toString().equals(tela.toString()) && usuarioActionModel.getUsuarioActionIDModel().getAction().getId().toString().equals(action.toString())) {
                return retorno = true;
            }
        }

        return retorno;
    }

    /**
     * MÃ©todo que verifica se o grupo tem acesso a tela para determinada aÃ§Ã£o
     *
     * @param usuario Usuario
     * @param action AcÃ£o
     * @param tela Tela
     * @return se tem ou nÃ£o
     * @throws Exception
     */
    public static boolean checarPermissaoGrupoAction(GrupoModel grupo, Long tela, Long action) throws SystemException, ApplicationException {

        boolean retorno = false;

        GrupoActionBO grupoActionBO = new GrupoActionBO();

        if (collGrupoAction == null || collGrupoAction.isEmpty()) {
            collGrupoAction = grupoActionBO.obterPorGrupo(grupo);
        }

        if (collGrupoAction == null || collGrupoAction.isEmpty()) {
            return retorno;
        }

        for (GrupoActionModel grupoActionModel : collGrupoAction) {
            if (grupoActionModel.getGrupoActionIDModel().getTela().getId().toString().equals(tela.toString()) && grupoActionModel.getGrupoActionIDModel().getAction().getId().toString().equals(action.toString())) {
                return retorno = true;
            }
        }

        return retorno;
    }

    /**
     * MÃ©todo utilizado para enviar e-mail. Para enviar para mais de um
     * destinatÃ¡rio, deve-se concatenar com ponto-e-virgula (;)
     *
     * @param remetente Quem irÃ¡ enviar o e-mail
     * @param destinatario Vetor de destinatÃ¡rios
     * @param assunto do E-mail
     * @param mensagem do E-mail
     * @param anexo Arquivo a ser enviado.
     * @return Mensagem de sucesso ou erro ao enviar o e-mail.
     * @throws br.com.pempec.finance.exceptions.SystemException
     * @throws br.com.pempec.finance.exceptions.ApplicationException
     */
    public static String sendMail(String[] destinatarios, String assunto, String mensagem, File anexo, MovimentoDiarioModel mov) throws SystemException, ApplicationException {

        if (verificaParametroAtivo(Parametro.G003.getCodigo())) {
            throw new ApplicationException(sendMessageParametro(Parametro.G003.getCodigo()));
        }

        if (servidorEmail == null) {
            servidorEmail = new ServidorEmailBO().consultar();
        }

        if (servidorEmail == null) {
            throw new ApplicationException("Necessário configurar Servidor e E-mail");
        }

        if (destinatarios == null || destinatarios[0] == null) {
            throw new ApplicationException("Obrigatório preenchimento do destinatÃ¡rio!");
        }

        if (assunto == null || assunto.trim().isEmpty()) {
            throw new ApplicationException("Obrigatório preenchimento do assunto!");
        }

        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new ApplicationException("Obrigatório preenchimento da mensagem!");
        }

        try {

            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", servidorEmail.getHost());
            properties.put("mail.smtp.port", "587");

            if (servidorEmail.getRequerAutenticacaoBoolean()) {
                properties.put("mail.smtp.auth", "true");
            }

            Session session = Session.getDefaultInstance(properties);

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(servidorEmail.getRemetente()));

            message.setRecipients(Message.RecipientType.TO, getDestinatarios(destinatarios));

            message.setSubject(assunto);

            message.setSentDate(Controller.getDataServidorBD());

            if (anexo != null) {
                MimeBodyPart mbp1 = new MimeBodyPart();
                mbp1.setText(mensagem);

                MimeBodyPart mbp2 = new MimeBodyPart();

                // anexa o arquivo na mensagem
                FileDataSource fds = new FileDataSource(anexo);
                mbp2.setDataHandler(new DataHandler(fds));
                mbp2.setFileName(fds.getName());

                MimeMultipart mp = new MimeMultipart();
                mp.addBodyPart(mbp1);
                mp.addBodyPart(mbp2);
                message.setContent(mp);
            } else {

                message.setText(mensagem);

            }

            message.setHeader("Enterprise-Finance", "FinanceMail");

            SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");

            try {

                if (servidorEmail.getRequerAutenticacaoBoolean()) {

                    transport.connect(servidorEmail.getHost(), servidorEmail.getLogin(), servidorEmail.getSenha());

                } else {
                    transport.connect();
                }

                transport.sendMessage(message, message.getAllRecipients());

            } finally {

                transport.close();

            }

            if (mov != null) {
                new MovimentoDiarioBO().inserir(mov);
            }

            return "E-mail enviado com Sucesso!";

        } catch (Exception e) {

            throw new SystemException("Erro ao enviar E-mail: " + e.getLocalizedMessage());

        }
    }

    private static Address[] getDestinatarios(String[] membros) throws Exception {

        Address[] destinatarios = new InternetAddress[membros.length];

        for (int i = 0; i < membros.length; i++) {
            if (membros[i] != null && !membros[i].isEmpty()) {
                InternetAddress destinatario = new InternetAddress(membros[i]);
                destinatarios[i] = destinatario;
            }
        }

        return destinatarios;

    }

    /**
     * MÃ©todo utilizado para enviar e-mail ERRO.
     *
     * @param anexo Arquivo a ser enviado.
     * @return Mensagem de sucesso ou erro ao enviar o e-mail.
     * @throws br.com.pempec.finance.exceptions.SystemException
     * @throws br.com.pempec.finance.exceptions.ApplicationException
     */
    public static String sendMailErro(File anexo, String mensagem, MovimentoDiarioModel mov) throws SystemException, ApplicationException {

        if (verificaParametroAtivo(Parametro.G003.getCodigo())) {
            throw new ApplicationException(sendMessageParametro(Parametro.G003.getCodigo()));
        }

        if (servidorEmail == null) {
            servidorEmail = new ServidorEmailBO().consultar();
        }

        if (servidorEmail == null) {
            throw new ApplicationException("Nenhum servidor de e-mail encontrado!\n SerÃ¡ necessÃ¡rio configurar o servidor de e-mail");
        }

        try {

            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", servidorEmail.getHost());

            if (servidorEmail.getRequerAutenticacaoBoolean()) {
                properties.put("mail.smtp.auth", "true");
            }

            Session session = Session.getDefaultInstance(properties);

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(servidorEmail.getRemetente()));

            message.setRecipients(Message.RecipientType.TO, new Address[]{new InternetAddress("suporte@pempec.com.br")});

            message.setRecipients(Message.RecipientType.BCC, new Address[]{new InternetAddress("ranan@pempec.com.br"), new InternetAddress("alexandre@pempec.com.br")});

            message.setSubject("ERRO DE SISTEMA - FINANCE");

            message.setSentDate(new Date());

            if (anexo != null) {
                MimeBodyPart mbp1 = new MimeBodyPart();
                mbp1.setText(mensagem);

                MimeBodyPart mbp2 = new MimeBodyPart();

                // anexa o arquivo na mensagem
                FileDataSource fds = new FileDataSource(anexo);
                mbp2.setDataHandler(new DataHandler(fds));
                mbp2.setFileName(fds.getName());

                MimeMultipart mp = new MimeMultipart();
                mp.addBodyPart(mbp1);
                mp.addBodyPart(mbp2);
                message.setContent(mp);
            } else {

                message.setText(mensagem);

            }

            message.setHeader("Enterprise-Finance", "FinanceMail");

            SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");

            try {

                if (servidorEmail.getRequerAutenticacaoBoolean()) {
                    transport.connect(servidorEmail.getHost(), servidorEmail.getLogin(), servidorEmail.getSenha());
                } else {
                    transport.connect();
                }

                transport.sendMessage(message, message.getAllRecipients());

            } finally {

                transport.close();

            }

            if (mov != null) {
                new MovimentoDiarioBO().inserir(mov);
            }

            return "Notificação enviada com sucesso!";

        } catch (Exception e) {

            e.printStackTrace();

            throw new SystemException("Erro ao notificar administrador: " + e.getLocalizedMessage() + "\n Favor enviar manualmente!");

        }
    }

    public static void sendMailInterno(String origem, String mensagem, MovimentoDiarioModel movimentoDiarioModel) throws SystemException, ApplicationException {

        MovimentoDiarioModel mov = movimentoDiarioModel;

        if (servidorEmail == null) {
            servidorEmail = new ServidorEmailBO().consultar();
        }

        if (servidorEmail == null) {
            //colocar um MOV p gravar informando o ocorrido
        }

        try {

            Properties properties = System.getProperties();
            properties.put("mail.smtp.host", servidorEmail.getHost());

            if (servidorEmail.getRequerAutenticacaoBoolean()) {
                properties.put("mail.smtp.auth", "true");
            }

            Session session = Session.getDefaultInstance(properties);

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(servidorEmail.getRemetente()));

            message.setRecipients(Message.RecipientType.TO, new Address[]{new InternetAddress("suporte@pempec.com.br")});

            //message.setRecipients(Message.RecipientType.BCC, new Address[]{new InternetAddress("ranan@pempec.com.br"), new InternetAddress("alexandre@pempec.com.br")});

            if (origem == null) {
                origem = "Controller ";
            }

            message.setSubject(" FINANCE " + origem.toUpperCase() + "  " + Controller.getOrganizacao().getSigla());

            message.setSentDate(new Date());

            message.setText(mensagem);

            message.setHeader("Enterprise Finance", "FinanceMail");

            SMTPTransport transport = (SMTPTransport) session.getTransport("smtp");

            try {

                if (servidorEmail.getRequerAutenticacaoBoolean()) {
                    transport.connect(servidorEmail.getHost(), servidorEmail.getLogin(), servidorEmail.getSenha());
                } else {
                    transport.connect();
                }

                transport.sendMessage(message, message.getAllRecipients());

            } finally {

                transport.close();

            }

            if (mov != null) {

                new MovimentoDiarioBO().inserir(mov);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }

    public static boolean getConfigProperties() {

        //precisa melhorar.
        //tem que pegar onde o sistema esta instalado
        boolean existFile = false;
        
        String  pathFileConfig = Constantes.CONSTANTES_PATH_FILE_CONFIG;
        String  nameFileConfig = Constantes.CONSTANTES_NAME_FILE_CONFIG;
        String fileConfig = pathFileConfig + nameFileConfig;

        File file = new File(fileConfig);

        if (file != null) {

            existFile = true;
        }

        return existFile;

    }

    public static Object sendToServer(Object object) {

        Object retorno = "8";


        try {
            URL urlPrincipal = new URL(ResourcePropertiesSecurity.getMessage("socket_web"));
            URLConnection conn = urlPrincipal.openConnection();

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setDefaultUseCaches(false);
            conn.setRequestProperty("Content-Type", "java-internal/" + Controller.class.getName());

            conn.setAllowUserInteraction(true);

            // ESCRITA DO OBJETO PARA O SERVIDOR
            ObjectOutputStream out = new ObjectOutputStream(conn.getOutputStream());
            out.writeObject(object);
            out.flush();
            out.close();

            ObjectInputStream input = new ObjectInputStream(conn.getInputStream());

            retorno = input.readObject();



            return retorno;

        } catch (Exception e) {

            try {


                URL urlSecundaria = new URL(ResourcePropertiesSecurity.getMessage("socket_web_seg"));
                URLConnection conn = urlSecundaria.openConnection();

                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setUseCaches(false);
                conn.setDefaultUseCaches(false);
                conn.setRequestProperty("Content-Type", "java-internal/" + Controller.class.getName());

                conn.setAllowUserInteraction(true);

                // ESCRITA DO OBJETO PARA O SERVIDOR
                ObjectOutputStream out = new ObjectOutputStream(conn.getOutputStream());
                out.writeObject(object);
                out.flush();
                out.close();

                ObjectInputStream input = new ObjectInputStream(conn.getInputStream());

                retorno = input.readObject();

            } catch (Exception ex) {
                ex.printStackTrace();

            }



            return retorno;
        }

    }

    public static void checarLicenca() {


        if (getOrganizacao().getDataAtualizacao() == null) {

            getOrganizacao().setDataAtualizacao(PempecUtil.addDayDate(dataServidorBD, -22));

        }


        if (!getDataServidorBD().before(getOrganizacao().getDataAtualizacao())) {

            //new ValidaLicencaFinance().genuineFinance();
            
             getOrganizacao().setDataAtualizacao(PempecUtil.addDayDate(dataServidorBD, 422));

        }

    }

    public static String tratamentoIDMega(String id) {

        if (id.charAt(0) == '{') {
            id = id.substring(1);
        }

        if (id.charAt(id.length() - 1) == '}') {
            id = id.substring(0, id.length() - 2);
        }

        return id;
    }
    
    
}
