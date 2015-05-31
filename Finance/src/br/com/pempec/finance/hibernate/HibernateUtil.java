package br.com.pempec.finance.hibernate;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.ResourcePropertiesLocator;
import br.com.pempec.finance.view.TelaErroBancoDados;
import br.com.pempec.finance.view.TelaPrincipal;
import java.awt.Font;
import java.io.File;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.SwingUtilities;
import org.firebirdsql.gds.GDSException;
import org.firebirdsql.jdbc.FBSQLException;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ThreadLocal<Session> sessionThread = new ThreadLocal<Session>();
    private static final ThreadLocal<Transaction> transactionThread = new ThreadLocal<Transaction>();

    private HibernateUtil() {

    }

    static {

        try {

            Configuration configuration = new Configuration();

            configuration.addResource("br/com/pempec/finance/hbms/Backup.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Constantes.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Feriado.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Action.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Funcionario.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Grupo.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/GrupoAction.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/GrupoUsuario.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Organizacao.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Tela.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Usuario.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/UsuarioAction.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Banco.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/CentroCusto.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/ContaBancaria.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TipoCedente.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TipoSacado.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TipoStatus.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TipoCobranca.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TipoNotaFiscal.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TipoDeducao.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TipoAcrescimo.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/FormaPagamento.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/LocalPagamento.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Historico.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Estado.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Bairro.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Cidade.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Contato.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Endereco.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Sacado.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Cedente.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloPagar.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloPagarBaixa.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloPagarBaixaAC.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloPagarBaixaDE.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloPagarBaixaFP.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloPagarBaixaCheque.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloPagarBaixaInternet.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TipoOperacaoBancaria.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/ContaBancariaCheque.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/ContaBancariaDebito.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/ContaBancariaCredito.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/ContaBancariaTransferencia.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TesourariaCredito.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TesourariaDebito.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/NotaFiscalEntrada.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/NotaFiscalEmitida.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/MovimentoDiario.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/LayoutCheque.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloReceber.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloReceberBaixa.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloReceberBaixaAC.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloReceberBaixaDE.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloReceberBaixaFP.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloReceberBaixaCheque.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloReceberBaixaInternet.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/LoteContabil.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/LoteDeposito.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/LotePagamentoTitulo.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/LoteRecebimentoTitulo.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloPagarRateioCC.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloReceberRateioCC.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloPagarRateioHistorico.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/TituloReceberRateioHistorico.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/ServidorEmail.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/FormatosRelatorios.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/Parametros.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/ContaContabil.hbm.xml");
            configuration.addResource("br/com/pempec/finance/hbms/CartaoCredito.hbm.xml");

            configuration.setProperty("hibernate.dialect", ResourcePropertiesLocator.getMessage("dialect"));
            configuration.setProperty("hibernate.connection.url", ResourcePropertiesLocator.getMessage("url"));
            configuration.setProperty("hibernate.connection.lc_ctype", "ISO8859_1");
            configuration.setProperty("hibernate.connection.username", ResourcePropertiesLocator.getMessage("username"));
            configuration.setProperty("hibernate.connection.password", ResourcePropertiesLocator.getMessage("password"));
            configuration.setProperty("hibernate.connection.driver_class", ResourcePropertiesLocator.getMessage("driver_class"));
            configuration.setProperty("hibernate.transaction.factory_class", ResourcePropertiesLocator.getMessage("factory_class"));
            configuration.setProperty("hibernate.current_session_context_class", ResourcePropertiesLocator.getMessage("current_session_context_class"));
            configuration.setProperty("hibernate.show_sql", ResourcePropertiesLocator.getMessage("show_sql"));

            sessionFactory = configuration.buildSessionFactory();

            System.out.println("URL " + ResourcePropertiesLocator.getMessage("url"));
//           

        } catch (final RuntimeException ex) {
            String message = "";
            if (ex.getCause() instanceof GDSException) {
                switch (((GDSException) ex.getCause()).getFbErrorCode() ) {
                    case 335544344:
                        message = "Base de dados não localizada!";

                        System.out.println("ERRO " + message);
                }
            }

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);
                }
            });

            throw ex;

        }
    }

    public static Session getCurrentSession() {

        try {

            if (sessionThread.get() == null) {
                Session session = sessionFactory.openSession();
                sessionThread.set(session);
            }

        } catch (final Throwable ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);
                }
            });
        }

        return (Session) sessionThread.get();

    }

    private static void closeSession() {
        Session session = (Session) sessionThread.get();
        if (session != null) {
            session.close();
        }
        sessionThread.set(null);
    }

    static void beginTransaction() {
        if (transactionThread.get() == null) {
            Transaction transaction = getCurrentSession().beginTransaction();
            transactionThread.set(transaction);
        }
    }

    static void commitTransaction() {
        Transaction transaction = (Transaction) transactionThread.get();
        if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
            transaction.commit();
            transactionThread.set(null);
        }
        closeSession();
    }

    static void rollbackTransaction() {
        Transaction transaction = (Transaction) transactionThread.get();
        if (transaction != null && !transaction.wasCommitted() && !transaction.wasRolledBack()) {
            transaction.rollback();
            transactionThread.set(null);
        }
        closeSession();
    }

    static void tratamentoExcecao(RuntimeException exception, File file) {

        Throwable raiz = exception;

        exception.printStackTrace();

        //Pegando Exception que causou o erro.
        while (raiz.getCause() != null) {
            raiz = raiz.getCause();
        }

        TelaErroBancoDados telaErro = new TelaErroBancoDados(file);
        TelaPrincipal.desktopPane.add(telaErro);
        telaErro.labelDataHora.setText(PempecParse.dateHourSecondToString(new Date()));
        telaErro.labelMensagem.setText(raiz.getMessage());
        telaErro.labelTela.setText("Acesso ao banco pelo Hibernate.");

        StringBuilder builder = new StringBuilder();

        for (StackTraceElement stack : raiz.getStackTrace()) {
            builder.append(stack.toString());
            builder.append("\n");
        }

        telaErro.jTObservacao.setText(builder.toString());

        telaErro.setFont(new Font("Arial", Font.PLAIN, 8));

        telaErro.show();

    }

    static void tratamentoExcecao(Throwable exception, File file) {

        Throwable raiz = exception;

        exception.printStackTrace();

        //Pegando Exception que causou o erro.
        while (raiz.getCause() != null) {
            raiz = raiz.getCause();
        }

        TelaErroBancoDados telaErro = new TelaErroBancoDados(file);
        TelaPrincipal.desktopPane.add(telaErro);
        telaErro.labelDataHora.setText(PempecParse.dateHourSecondToString(new Date()));
        telaErro.labelMensagem.setText(raiz.getMessage());
        telaErro.labelTela.setText("Acesso ao banco pelo Hibernate.");

        StringBuilder builder = new StringBuilder();

        for (StackTraceElement stack : raiz.getStackTrace()) {
            builder.append(stack.toString());
            builder.append("\n");
        }

        telaErro.jTObservacao.setText(builder.toString());

        telaErro.setFont(new Font("Arial", Font.PLAIN, 8));

        telaErro.show();

    }

}
