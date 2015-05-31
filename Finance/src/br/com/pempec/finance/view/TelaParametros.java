package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ConstantesBO;
import br.com.pempec.finance.businessObjects.ParametrosBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ConstantesModel;
import br.com.pempec.finance.models.ParametrosModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import java.awt.Component;
import java.io.File;
import java.util.Collection;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class TelaParametros extends FinanceInternalFrame implements IRepopulador {

    private ParametrosBO parametrosBO = new ParametrosBO();
    private ConstantesBO constantesBO = new ConstantesBO();
    private Collection<ParametrosModel> parametros;
    private long tela = Tela.TELA_SISTEMA_CONFIGURACOES_GERAIS.getTela();

    private String NameObject() {
        return (String) "Tela de Configurações Gerais";
    }

    public TelaParametros() throws SystemException {
        initComponents();

    }

    public void repopularCombos() {

        parametros = Controller.getCollParametros();

        //Populando Aba Geral.
        Component[] components = abaGeral.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

        //Populando Aba Tela de Avisos.
        components = abaTelaAvisos.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                if (codigo.equals(Parametro.TA008.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    TA008DT.setDate(parametro != null ? parametro.getData() : null);
                }

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

        //Populando Aba Cadastro Titulo Pagar.
        components = abaCADP.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

        //Populando Aba de Baixa Simples Titulo a Pagar
        components = abaBAIXAPS.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                if (codigo.equals(Parametro.CBPS002.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBPS002DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBPS006.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBPS006DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBPSFPE002.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBPSFPE002DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBPSFPC002.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBPSFPC002DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBPSFPI002.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBPSFPI002DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

        components = abaBAIXAPL.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                if (codigo.equals(Parametro.CBPL002.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBPL002DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBPL005.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBPL005DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBPLFPE002.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBPLFPE002DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBPLFPC002.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBPLFPC002DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBPLFPI002.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBPLFPI002DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

        components = abaCANP.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                if (codigo.equals(Parametro.CCANP005.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CCANP005DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CCANP008.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CCANP008DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CCANP009.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CCANP009DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

        components = abaRECP.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                if (codigo.equals(Parametro.CRP003.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CRP003DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

        components = abaCADR.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

        components = abaBAIXAR.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                if (codigo.equals(Parametro.CBR002.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBR002DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBR006.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBR006DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBRFPE001.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBRFPE001DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBRFPC001.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBRFPC001DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                if (codigo.equals(Parametro.CBRFPI001.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CBRFPI001DB.setText(PempecParse.doubleToZero(parametro.getValor()));
                }

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

        components = abaCANR.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                if (codigo.equals(Parametro.CCANR004.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CCANR004DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

        components = abaRECR.getComponents();

        for (Component component : components) {

            if (component instanceof JToggleButton) {

                String codigo = ((JToggleButton) component).getActionCommand();

                ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                if (codigo.equals(Parametro.CRR003.getCodigo()) && (parametro != null ? parametro.isAtivo() : false)) {
                    CRR003DB.setText(PempecParse.doubleToZero(parametro != null ? parametro.getValor() : 0d));
                }

                ((JToggleButton) component).setSelected(parametro != null ? parametro.isAtivo() : false);

            }

        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        telaAvisosGroup1 = new javax.swing.ButtonGroup();
        telaAvisosGroup2 = new javax.swing.ButtonGroup();
        cancelamentoPagarGroup1 = new javax.swing.ButtonGroup();
        baixaTodosGroup1 = new javax.swing.ButtonGroup();
        baixaPagarGroup1 = new javax.swing.ButtonGroup();
        baixaPagarGroup2 = new javax.swing.ButtonGroup();
        baixaPagarGroup3 = new javax.swing.ButtonGroup();
        baixaPagarGroup4 = new javax.swing.ButtonGroup();
        baixaPagarGroup5 = new javax.swing.ButtonGroup();
        baixaReceberGroup1 = new javax.swing.ButtonGroup();
        baixaReceberGroup2 = new javax.swing.ButtonGroup();
        baixaReceberGroup3 = new javax.swing.ButtonGroup();
        baixaReceberGroup4 = new javax.swing.ButtonGroup();
        recibosPagarGroup1 = new javax.swing.ButtonGroup();
        recibosReceberGroup1 = new javax.swing.ButtonGroup();
        cancelamentoPagarGroup2 = new javax.swing.ButtonGroup();
        cancelamentoReceberGroup1 = new javax.swing.ButtonGroup();
        geralGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaGeral = new javax.swing.JPanel();
        G001 = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        botaoFecharG = new javax.swing.JButton();
        botaoSalvarTudoG = new javax.swing.JButton();
        botaoSalvarG = new javax.swing.JButton();
        G002 = new javax.swing.JRadioButton();
        G003 = new javax.swing.JRadioButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jRBTipoPapel = new javax.swing.JRadioButton();
        abaTelaAvisos = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        TA001 = new javax.swing.JRadioButton();
        TA002 = new javax.swing.JRadioButton();
        TA003 = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        TA004 = new javax.swing.JRadioButton();
        TA005 = new javax.swing.JRadioButton();
        TA006 = new javax.swing.JRadioButton();
        TA007 = new javax.swing.JRadioButton();
        TA008 = new javax.swing.JRadioButton();
        TA009 = new javax.swing.JRadioButton();
        TA010 = new javax.swing.JRadioButton();
        TA008DT = new org.jdesktop.swingx.JXDatePicker();
        jPanel26 = new javax.swing.JPanel();
        botaoFecharTA = new javax.swing.JButton();
        botaoSalvarTudoTA = new javax.swing.JButton();
        botaoSalvarTA = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTabbedPane8 = new javax.swing.JTabbedPane();
        abaCADP = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jCheckBox52 = new javax.swing.JCheckBox();
        jCheckBox53 = new javax.swing.JCheckBox();
        jCheckBox54 = new javax.swing.JCheckBox();
        jPanel30 = new javax.swing.JPanel();
        botaoFecharCCADRP = new javax.swing.JButton();
        botaoSalvarTudoCCADP = new javax.swing.JButton();
        botaoSalvarCCADP = new javax.swing.JButton();
        jCheckBox57 = new javax.swing.JCheckBox();
        jCheckBoxProvisaoPagar = new javax.swing.JCheckBox();
        jPanel28 = new javax.swing.JPanel();
        jTabbedPane11 = new javax.swing.JTabbedPane();
        abaBAIXAPS = new javax.swing.JPanel();
        jCheckBox95 = new javax.swing.JCheckBox();
        CBPS002DB = new br.com.pempec.componentes.JDoubleField();
        jLabel48 = new javax.swing.JLabel();
        CBPS006DB = new br.com.pempec.componentes.JDoubleField();
        jRadioButton63 = new javax.swing.JRadioButton();
        jCheckBox97 = new javax.swing.JCheckBox();
        jCheckBox98 = new javax.swing.JCheckBox();
        jCheckBox99 = new javax.swing.JCheckBox();
        jRadioButton64 = new javax.swing.JRadioButton();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jRadioButton65 = new javax.swing.JRadioButton();
        CBPSFPE002DB = new br.com.pempec.componentes.JDoubleField();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jRadioButton67 = new javax.swing.JRadioButton();
        jRadioButton69 = new javax.swing.JRadioButton();
        jRadioButton70 = new javax.swing.JRadioButton();
        CBPSFPC002DB = new br.com.pempec.componentes.JDoubleField();
        CBPSFPI002DB = new br.com.pempec.componentes.JDoubleField();
        jRadioButton72 = new javax.swing.JRadioButton();
        jRadioButton73 = new javax.swing.JRadioButton();
        jPanel33 = new javax.swing.JPanel();
        botaoFecharCBS = new javax.swing.JButton();
        botaoSalvarTudoCBS = new javax.swing.JButton();
        botaoSalvarCBS = new javax.swing.JButton();
        jRadioButton86 = new javax.swing.JRadioButton();
        jRadioButton88 = new javax.swing.JRadioButton();
        abaBAIXAPL = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        jCheckBox101 = new javax.swing.JCheckBox();
        CBPL002DB = new br.com.pempec.componentes.JDoubleField();
        jCheckBox102 = new javax.swing.JCheckBox();
        jRadioButton74 = new javax.swing.JRadioButton();
        jCheckBox103 = new javax.swing.JCheckBox();
        jCheckBox104 = new javax.swing.JCheckBox();
        jCheckBox105 = new javax.swing.JCheckBox();
        jRadioButton75 = new javax.swing.JRadioButton();
        CBPL005DB = new br.com.pempec.componentes.JDoubleField();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jRadioButton77 = new javax.swing.JRadioButton();
        jRadioButton78 = new javax.swing.JRadioButton();
        CBPLFPE002DB = new br.com.pempec.componentes.JDoubleField();
        jLabel62 = new javax.swing.JLabel();
        jRadioButton80 = new javax.swing.JRadioButton();
        jRadioButton81 = new javax.swing.JRadioButton();
        jLabel63 = new javax.swing.JLabel();
        jRadioButton83 = new javax.swing.JRadioButton();
        jRadioButton84 = new javax.swing.JRadioButton();
        CBPLFPI002DB = new br.com.pempec.componentes.JDoubleField();
        CBPLFPC002DB = new br.com.pempec.componentes.JDoubleField();
        jPanel35 = new javax.swing.JPanel();
        botaoFecharCBL = new javax.swing.JButton();
        botaoSalvarTudoCBL = new javax.swing.JButton();
        botaoSalvarCBL = new javax.swing.JButton();
        abaRECP = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jRadioButton101 = new javax.swing.JRadioButton();
        jCheckBox132 = new javax.swing.JCheckBox();
        jCheckBox133 = new javax.swing.JCheckBox();
        jCheckBox134 = new javax.swing.JCheckBox();
        jRadioButton102 = new javax.swing.JRadioButton();
        CRP003DB = new br.com.pempec.componentes.JDoubleField();
        jPanel37 = new javax.swing.JPanel();
        botaoFecharCRP = new javax.swing.JButton();
        botaoSalvarTudoCRP = new javax.swing.JButton();
        botaoSalvarCRP = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        abaCANP = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        jCheckBox110 = new javax.swing.JCheckBox();
        jCheckBox111 = new javax.swing.JCheckBox();
        jRadioButton89 = new javax.swing.JRadioButton();
        jCheckBox112 = new javax.swing.JCheckBox();
        jCheckBox113 = new javax.swing.JCheckBox();
        jCheckBox115 = new javax.swing.JCheckBox();
        jRadioButton90 = new javax.swing.JRadioButton();
        CCANP005DB = new br.com.pempec.componentes.JDoubleField();
        jCheckBox116 = new javax.swing.JCheckBox();
        jCheckBox117 = new javax.swing.JCheckBox();
        jCheckBox119 = new javax.swing.JCheckBox();
        jCheckBox120 = new javax.swing.JCheckBox();
        jRadioButton92 = new javax.swing.JRadioButton();
        CCANP008DB = new br.com.pempec.componentes.JDoubleField();
        jRadioButton94 = new javax.swing.JRadioButton();
        jPanel36 = new javax.swing.JPanel();
        botaoFecharCCANP = new javax.swing.JButton();
        botaoSalvarTudoCCANP = new javax.swing.JButton();
        botaoSalvarCCANP = new javax.swing.JButton();
        jCheckBox106 = new javax.swing.JCheckBox();
        jRadioButton91 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        CCANP009DB = new javax.swing.JFormattedTextField();
        jPanel3 = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        abaCADR = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jCheckBox55 = new javax.swing.JCheckBox();
        jCheckBox56 = new javax.swing.JCheckBox();
        jPanel29 = new javax.swing.JPanel();
        botaoFecharCCADR = new javax.swing.JButton();
        botaoSalvarTudoCCADR = new javax.swing.JButton();
        botaoSalvarCCADR = new javax.swing.JButton();
        jCheckBox58 = new javax.swing.JCheckBox();
        jCheckBox59 = new javax.swing.JCheckBox();
        jCheckBoxProvisaoReceber = new javax.swing.JCheckBox();
        abaBAIXAR = new javax.swing.JPanel();
        jLabel27 = new javax.swing.JLabel();
        jCheckBox66 = new javax.swing.JCheckBox();
        jRadioButton38 = new javax.swing.JRadioButton();
        jCheckBox67 = new javax.swing.JCheckBox();
        jCheckBox80 = new javax.swing.JCheckBox();
        jCheckBox81 = new javax.swing.JCheckBox();
        jRadioButton39 = new javax.swing.JRadioButton();
        CBR006DB = new br.com.pempec.componentes.JDoubleField();
        jCheckBox11 = new javax.swing.JCheckBox();
        jCheckBox12 = new javax.swing.JCheckBox();
        CBR002DB = new br.com.pempec.componentes.JDoubleField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jRadioButton56 = new javax.swing.JRadioButton();
        CBRFPE001DB = new br.com.pempec.componentes.JDoubleField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jRadioButton58 = new javax.swing.JRadioButton();
        CBRFPC001DB = new br.com.pempec.componentes.JDoubleField();
        jRadioButton60 = new javax.swing.JRadioButton();
        CBRFPI001DB = new br.com.pempec.componentes.JDoubleField();
        jPanel39 = new javax.swing.JPanel();
        botaoFecharCBR = new javax.swing.JButton();
        botaoSalvarTudoCBR = new javax.swing.JButton();
        botaoSalvarCBR = new javax.swing.JButton();
        abaCANR = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jCheckBox21 = new javax.swing.JCheckBox();
        jCheckBox22 = new javax.swing.JCheckBox();
        jRadioButton17 = new javax.swing.JRadioButton();
        jCheckBox24 = new javax.swing.JCheckBox();
        jCheckBox23 = new javax.swing.JCheckBox();
        jCheckBox26 = new javax.swing.JCheckBox();
        jRadioButton18 = new javax.swing.JRadioButton();
        CCANR004DB = new br.com.pempec.componentes.JDoubleField();
        jPanel40 = new javax.swing.JPanel();
        botaoFecharCCANR = new javax.swing.JButton();
        botaoSalvarTudoCCANR = new javax.swing.JButton();
        botaoSalvarCCANR = new javax.swing.JButton();
        jCheckBox72 = new javax.swing.JCheckBox();
        abaRECR = new javax.swing.JPanel();
        jRadioButton42 = new javax.swing.JRadioButton();
        jCheckBox70 = new javax.swing.JCheckBox();
        jRadioButton41 = new javax.swing.JRadioButton();
        jCheckBox69 = new javax.swing.JCheckBox();
        CRR003DB = new br.com.pempec.componentes.JDoubleField();
        jCheckBox71 = new javax.swing.JCheckBox();
        jLabel31 = new javax.swing.JLabel();
        jPanel41 = new javax.swing.JPanel();
        botaoFecharCCR = new javax.swing.JButton();
        botaoSalvarTudoCCR = new javax.swing.JButton();
        botaoSalvarCCR = new javax.swing.JButton();
        jCheckBox2 = new javax.swing.JCheckBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Configurações do Sistema");

        G001.setText("Alertar para cheques a compensar?");
        G001.setActionCommand("G001");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Avisos:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Permissões");

        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoFecharG.setMnemonic('F');
        botaoFecharG.setText("Fechar");
        botaoFecharG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharGActionPerformed(evt);
            }
        });

        botaoSalvarTudoG.setMnemonic('F');
        botaoSalvarTudoG.setText("Salvar Tudo");
        botaoSalvarTudoG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoGActionPerformed(evt);
            }
        });

        botaoSalvarG.setMnemonic('F');
        botaoSalvarG.setText("Salvar");
        botaoSalvarG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarGActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarG, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoG)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharG, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoG, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarG, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharG, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        geralGroup1.add(G002);
        G002.setText("Não permitir envio de e-mail?");
        G002.setActionCommand("G003");

        geralGroup1.add(G003);
        G003.setText("Não permitir envio de relatório por e-mail?");
        G003.setActionCommand("G004");

        geralGroup1.add(jRadioButton1);
        jRadioButton1.setText("Permitir envio de e-mail?");
        jRadioButton1.setActionCommand("G002");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Impressão");

        jRBTipoPapel.setText("Formulário padrão A4 com Logomarca");
        jRBTipoPapel.setActionCommand("G002");

        javax.swing.GroupLayout abaGeralLayout = new javax.swing.GroupLayout(abaGeral);
        abaGeral.setLayout(abaGeralLayout);
        abaGeralLayout.setHorizontalGroup(
            abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaGeralLayout.createSequentialGroup()
                .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaGeralLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(G001)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(G002)
                            .addComponent(G003)
                            .addComponent(jRadioButton1)))
                    .addGroup(abaGeralLayout.createSequentialGroup()
                        .addGap(401, 401, 401)
                        .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaGeralLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRBTipoPapel)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(332, Short.MAX_VALUE))
        );
        abaGeralLayout.setVerticalGroup(
            abaGeralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaGeralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(G001)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton1)
                .addGap(4, 4, 4)
                .addComponent(G002)
                .addGap(5, 5, 5)
                .addComponent(G003)
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRBTipoPapel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Geral", abaGeral);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Exibição dos títulos:");

        telaAvisosGroup1.add(TA001);
        TA001.setText("Mostrar todos os títulos a pagar e receber?");
        TA001.setActionCommand("TA001");

        telaAvisosGroup1.add(TA002);
        TA002.setText("Mostrar somente títulos a pagar?");
        TA002.setActionCommand("TA002");

        telaAvisosGroup1.add(TA003);
        TA003.setText("Mostrar somente títulos a receber?");
        TA003.setActionCommand("TA003");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Vencimento dos títulos:");

        telaAvisosGroup2.add(TA004);
        TA004.setText("Mostrar todos vencidos e a vencer?");
        TA004.setActionCommand("TA004");

        telaAvisosGroup2.add(TA005);
        TA005.setText("Mostrar todos a vencer?");
        TA005.setActionCommand("TA005");

        telaAvisosGroup2.add(TA006);
        TA006.setText("Mostrar todos vencidos?");
        TA006.setActionCommand("TA006");

        telaAvisosGroup2.add(TA007);
        TA007.setText("Mostrar vencidos ou vencendo até a data atual?");
        TA007.setActionCommand("TA007");

        telaAvisosGroup2.add(TA008);
        TA008.setText("Mostrar vencidos ou vencendo até a data: ");
        TA008.setActionCommand("TA008");

        telaAvisosGroup2.add(TA009);
        TA009.setText("Mostrar somente do mês vigente?");
        TA009.setActionCommand("TA009");

        telaAvisosGroup2.add(TA010);
        TA010.setText("Mostrar somente do ano vigente?");
        TA010.setActionCommand("TA010");

        jPanel26.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        botaoFecharTA.setMnemonic('F');
        botaoFecharTA.setText("Fechar");
        botaoFecharTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharTAActionPerformed(evt);
            }
        });

        botaoSalvarTudoTA.setMnemonic('F');
        botaoSalvarTudoTA.setText("Salvar Tudo");
        botaoSalvarTudoTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoTAActionPerformed(evt);
            }
        });

        botaoSalvarTA.setMnemonic('F');
        botaoSalvarTA.setText("Salvar");
        botaoSalvarTA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarTA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoTA)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharTA, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoTA, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarTA, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharTA, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout abaTelaAvisosLayout = new javax.swing.GroupLayout(abaTelaAvisos);
        abaTelaAvisos.setLayout(abaTelaAvisosLayout);
        abaTelaAvisosLayout.setHorizontalGroup(
            abaTelaAvisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaTelaAvisosLayout.createSequentialGroup()
                .addGroup(abaTelaAvisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaTelaAvisosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(abaTelaAvisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TA010)
                            .addComponent(TA009)
                            .addGroup(abaTelaAvisosLayout.createSequentialGroup()
                                .addComponent(TA008)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TA008DT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TA007)
                            .addComponent(TA006)
                            .addComponent(TA005)
                            .addComponent(TA004)
                            .addComponent(TA003)
                            .addComponent(TA002)
                            .addComponent(TA001)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaTelaAvisosLayout.createSequentialGroup()
                        .addGap(411, 411, 411)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(322, Short.MAX_VALUE))
        );
        abaTelaAvisosLayout.setVerticalGroup(
            abaTelaAvisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaTelaAvisosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(TA001)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TA002)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TA003)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TA004)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TA005)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TA006)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TA007)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaTelaAvisosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TA008)
                    .addComponent(TA008DT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TA009)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(TA010)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 77, Short.MAX_VALUE)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Tela de Avisos", abaTelaAvisos);

        jTabbedPane8.setPreferredSize(new java.awt.Dimension(1000, 864));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel44.setText("Títulos a Pagar:");

        jCheckBox52.setText("Não permitir particionamento de títulos?");
        jCheckBox52.setActionCommand("CCADP001");

        jCheckBox53.setText("Não permitir rateio por centro de custo?");
        jCheckBox53.setActionCommand("CCADP002");

        jCheckBox54.setText("Não permitir cadastro Nota Fiscal de Entrada?");
        jCheckBox54.setActionCommand("CCADP003");

        jPanel30.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        botaoFecharCCADRP.setMnemonic('F');
        botaoFecharCCADRP.setText("Fechar");
        botaoFecharCCADRP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharCCADRPActionPerformed(evt);
            }
        });

        botaoSalvarTudoCCADP.setMnemonic('F');
        botaoSalvarTudoCCADP.setText("Salvar Tudo");
        botaoSalvarTudoCCADP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoCCADPActionPerformed(evt);
            }
        });

        botaoSalvarCCADP.setMnemonic('F');
        botaoSalvarCCADP.setText("Salvar");
        botaoSalvarCCADP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarCCADPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarCCADP, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoCCADP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCCADRP, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoCCADP, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarCCADP, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharCCADRP, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jCheckBox57.setText("Não permitir rateio contábil?");
        jCheckBox57.setActionCommand("CCADP004");

        jCheckBoxProvisaoPagar.setText("Não permitir provisionamento?");
        jCheckBoxProvisaoPagar.setActionCommand("CCADP006");

        javax.swing.GroupLayout abaCADPLayout = new javax.swing.GroupLayout(abaCADP);
        abaCADP.setLayout(abaCADPLayout);
        abaCADPLayout.setHorizontalGroup(
            abaCADPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCADPLayout.createSequentialGroup()
                .addContainerGap(391, Short.MAX_VALUE)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(339, 339, 339))
            .addGroup(abaCADPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCADPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxProvisaoPagar)
                    .addComponent(jCheckBox57)
                    .addComponent(jCheckBox54)
                    .addComponent(jCheckBox53)
                    .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox52))
                .addContainerGap(749, Short.MAX_VALUE))
        );
        abaCADPLayout.setVerticalGroup(
            abaCADPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCADPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox52)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox57, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxProvisaoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane8.addTab("Cadastro", abaCADP);

        jCheckBox95.setText("Não permitir baixa com valor superior a R$ ");
        jCheckBox95.setActionCommand("CBPS002");

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setText("Baixa Simples:");

        baixaPagarGroup1.add(jRadioButton63);
        jRadioButton63.setText("Não permitir baixa PARCIAL com valor inferior a R$");
        jRadioButton63.setActionCommand("CBPS006");

        jCheckBox97.setText("Internet");
        jCheckBox97.setActionCommand("CBPS00503");

        jCheckBox98.setText("Cheque");
        jCheckBox98.setActionCommand("CBPS00502");

        jCheckBox99.setText("Espécie");
        jCheckBox99.setActionCommand("CBPS00501");

        baixaPagarGroup1.add(jRadioButton64);
        jRadioButton64.setText("Permitir baixa PARCIAL para as formas de pagamento?");
        jRadioButton64.setActionCommand("CBPS005");

        jLabel50.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel50.setText("Formas de Pagamento");

        jLabel51.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel51.setText("+ Espécie:");

        baixaPagarGroup2.add(jRadioButton65);
        jRadioButton65.setText("Permitir baixa sem saldo, excedendo até R$");
        jRadioButton65.setActionCommand("CBPSFPE002");

        jLabel53.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel53.setText("+ Cheque:");

        jLabel54.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel54.setText("+ Internet:");

        baixaPagarGroup2.add(jRadioButton67);
        jRadioButton67.setText("Não permitir baixa sem saldo suficiente em caixa?");
        jRadioButton67.setActionCommand("CBPSFPE001");

        baixaPagarGroup3.add(jRadioButton69);
        jRadioButton69.setText("Permitir baixa sem saldo, excedendo até R$");
        jRadioButton69.setActionCommand("CBPSFPC002");

        baixaPagarGroup3.add(jRadioButton70);
        jRadioButton70.setText("Não permitir baixa sem saldo suficiente em conta?");
        jRadioButton70.setActionCommand("CBPSFPC001");

        baixaPagarGroup4.add(jRadioButton72);
        jRadioButton72.setText("Permitir baixa sem saldo, excedendo até R$");
        jRadioButton72.setActionCommand("CBPSFPI002");

        baixaPagarGroup4.add(jRadioButton73);
        jRadioButton73.setText("Não permitir baixa sem saldo suficiente em conta?");
        jRadioButton73.setActionCommand("CBPSFPI001");

        jPanel33.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        botaoFecharCBS.setMnemonic('F');
        botaoFecharCBS.setText("Fechar");
        botaoFecharCBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharCBSActionPerformed(evt);
            }
        });

        botaoSalvarTudoCBS.setMnemonic('F');
        botaoSalvarTudoCBS.setText("Salvar Tudo");
        botaoSalvarTudoCBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoCBSActionPerformed(evt);
            }
        });

        botaoSalvarCBS.setMnemonic('F');
        botaoSalvarCBS.setText("Salvar");
        botaoSalvarCBS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarCBSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarCBS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoCBS)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCBS, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoCBS, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarCBS, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharCBS, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        baixaPagarGroup1.add(jRadioButton86);
        jRadioButton86.setText("Não permitir baixa PARCIAL para títulos com acrescimos e/ou deduções?");
        jRadioButton86.setActionCommand("CBPS004");

        baixaPagarGroup1.add(jRadioButton88);
        jRadioButton88.setText("Não permitir baixa PARCIAL?");
        jRadioButton88.setActionCommand("CBPS003");

        javax.swing.GroupLayout abaBAIXAPSLayout = new javax.swing.GroupLayout(abaBAIXAPS);
        abaBAIXAPS.setLayout(abaBAIXAPSLayout);
        abaBAIXAPSLayout.setHorizontalGroup(
            abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                        .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton63)
                            .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jCheckBox97, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CBPS006DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton64)
                    .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox98, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox99, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                        .addComponent(jCheckBox95)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CBPS002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jRadioButton88)
                    .addComponent(jRadioButton86))
                .addGap(18, 18, 18)
                .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51)
                            .addComponent(jRadioButton67)
                            .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                                .addComponent(jRadioButton65)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBPSFPE002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel53)
                            .addComponent(jRadioButton70)
                            .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton73)
                                    .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                                        .addComponent(jRadioButton72)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBPSFPI002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel54))
                                .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                                    .addComponent(jRadioButton69)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(CBPSFPC002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel50))
                .addContainerGap(252, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaBAIXAPSLayout.createSequentialGroup()
                .addContainerGap(388, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(336, 336, 336))
        );
        abaBAIXAPSLayout.setVerticalGroup(
            abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CBPS002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox95))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton88, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton86, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jRadioButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox99)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox98)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                                .addComponent(jCheckBox97)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton63))
                            .addComponent(CBPS006DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaBAIXAPSLayout.createSequentialGroup()
                        .addComponent(jLabel50)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel51)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton65)
                            .addComponent(CBPSFPE002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel53)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton70)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton69)
                            .addComponent(CBPSFPC002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel54)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton73)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBAIXAPSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton72)
                            .addComponent(CBPSFPI002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane11.addTab("Simples", abaBAIXAPS);

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setText("Lote de Pagamento:");

        jCheckBox101.setText("Não permitir baixa com valor superior a R$ ");
        jCheckBox101.setActionCommand("CBPL002");

        jCheckBox102.setText("Não permitir baixa por LOTE?");
        jCheckBox102.setActionCommand("CBPL003");

        baixaPagarGroup5.add(jRadioButton74);
        jRadioButton74.setText("Permitir baixa por LOTE para as formas de pagamento?");
        jRadioButton74.setActionCommand("CBPL004");

        jCheckBox103.setText("Espécie");
        jCheckBox103.setActionCommand("CBPL00401");

        jCheckBox104.setText("Cheque");
        jCheckBox104.setActionCommand("CBPL00402");

        jCheckBox105.setText("Internet");
        jCheckBox105.setActionCommand("CBPL00403");

        baixaPagarGroup5.add(jRadioButton75);
        jRadioButton75.setText("Não permitir baixa LOTE com valor inferior a R$");
        jRadioButton75.setActionCommand("CBPL005");

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel59.setText("Formas de Pagamento");

        jLabel60.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel60.setText("+ Espécie:");

        baixaPagarGroup2.add(jRadioButton77);
        jRadioButton77.setText("Não permitir baixa sem saldo suficiente em caixa?");
        jRadioButton77.setActionCommand("CBPLFPE001");

        baixaPagarGroup2.add(jRadioButton78);
        jRadioButton78.setText("Permitir baixa sem saldo, excedendo até R$");
        jRadioButton78.setActionCommand("CBPLFPE002");

        jLabel62.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel62.setText("+ Cheque:");

        baixaPagarGroup3.add(jRadioButton80);
        jRadioButton80.setText("Não permitir baixa sem saldo suficiente em conta?");
        jRadioButton80.setActionCommand("CBPLFPC001");

        baixaPagarGroup3.add(jRadioButton81);
        jRadioButton81.setText("Permitir baixa sem saldo, excedendo até R$");
        jRadioButton81.setActionCommand("CBPLFPC002");

        jLabel63.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel63.setText("+ Internet:");

        baixaPagarGroup4.add(jRadioButton83);
        jRadioButton83.setText("Não permitir baixa sem saldo suficiente em conta?");
        jRadioButton83.setActionCommand("CBPLFPI001");

        baixaPagarGroup4.add(jRadioButton84);
        jRadioButton84.setText("Permitir baixa sem saldo, excedendo até R$");
        jRadioButton84.setActionCommand("CBPLFPI002");

        jPanel35.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        botaoFecharCBL.setMnemonic('F');
        botaoFecharCBL.setText("Fechar");
        botaoFecharCBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharCBLActionPerformed(evt);
            }
        });

        botaoSalvarTudoCBL.setMnemonic('F');
        botaoSalvarTudoCBL.setText("Salvar Tudo");
        botaoSalvarTudoCBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoCBLActionPerformed(evt);
            }
        });

        botaoSalvarCBL.setMnemonic('F');
        botaoSalvarCBL.setText("Salvar");
        botaoSalvarCBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarCBLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarCBL, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoCBL)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCBL, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoCBL, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarCBL, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharCBL, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout abaBAIXAPLLayout = new javax.swing.GroupLayout(abaBAIXAPL);
        abaBAIXAPL.setLayout(abaBAIXAPLLayout);
        abaBAIXAPLLayout.setHorizontalGroup(
            abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaBAIXAPLLayout.createSequentialGroup()
                        .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton74)
                            .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                                .addComponent(jRadioButton75)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBPL005DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jCheckBox102)
                            .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                                .addComponent(jCheckBox101)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBPL002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox105, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox104, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox103, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel60)
                                    .addComponent(jRadioButton77)
                                    .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                                        .addComponent(jRadioButton78)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBPLFPE002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel62)
                                    .addComponent(jRadioButton80)
                                    .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                                        .addComponent(jRadioButton81)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBPLFPC002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel63)
                                    .addComponent(jRadioButton83)
                                    .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                                        .addComponent(jRadioButton84)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CBPLFPI002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel59))
                        .addGap(929, 929, 929))
                    .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                .addGap(385, 385, 385)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        abaBAIXAPLLayout.setVerticalGroup(
            abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                        .addComponent(jLabel59)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel60)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton77)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton78)
                            .addComponent(CBPLFPE002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel62)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton81)
                            .addComponent(CBPLFPC002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel63)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton83))
                    .addGroup(abaBAIXAPLLayout.createSequentialGroup()
                        .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox101)
                            .addComponent(CBPL002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox102)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton74, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox103)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox104)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox105)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jRadioButton75)
                            .addComponent(CBPL005DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaBAIXAPLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton84)
                    .addComponent(CBPLFPI002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane11.addTab("Lote", abaBAIXAPL);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jTabbedPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 1003, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane11)
        );

        jTabbedPane8.addTab("Baixa", jPanel28);

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel77.setText("Títulos a Pagar:");

        recibosPagarGroup1.add(jRadioButton101);
        jRadioButton101.setText("Permitir emissão de recibos para as formas de pagamento?");
        jRadioButton101.setActionCommand("CRP002");

        jCheckBox132.setText("Espécie");
        jCheckBox132.setActionCommand("CRP00201");

        jCheckBox133.setText("Cheque");
        jCheckBox133.setActionCommand("CRP00202");

        jCheckBox134.setText("Internet");
        jCheckBox134.setActionCommand("CRP00203");

        recibosPagarGroup1.add(jRadioButton102);
        jRadioButton102.setText("Permitir emissão de recibos para titulos com valor até R$:");
        jRadioButton102.setActionCommand("CRP003");

        jPanel37.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        botaoFecharCRP.setMnemonic('F');
        botaoFecharCRP.setText("Fechar");
        botaoFecharCRP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharCRPActionPerformed(evt);
            }
        });

        botaoSalvarTudoCRP.setMnemonic('F');
        botaoSalvarTudoCRP.setText("Salvar Tudo");
        botaoSalvarTudoCRP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoCRPActionPerformed(evt);
            }
        });

        botaoSalvarCRP.setMnemonic('F');
        botaoSalvarCRP.setText("Salvar");
        botaoSalvarCRP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarCRPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarCRP, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoCRP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCRP, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoCRP, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarCRP, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharCRP, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jCheckBox1.setText("Não permitir emissão de recibos sem BAIXA?");
        jCheckBox1.setActionCommand("CRP001");

        javax.swing.GroupLayout abaRECPLayout = new javax.swing.GroupLayout(abaRECP);
        abaRECP.setLayout(abaRECPLayout);
        abaRECPLayout.setHorizontalGroup(
            abaRECPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaRECPLayout.createSequentialGroup()
                .addContainerGap(387, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(347, 347, 347))
            .addGroup(abaRECPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaRECPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton101)
                    .addGroup(abaRECPLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(abaRECPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox133, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox132, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaRECPLayout.createSequentialGroup()
                        .addGroup(abaRECPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton102)
                            .addGroup(abaRECPLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jCheckBox134, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CRP003DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        abaRECPLayout.setVerticalGroup(
            abaRECPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaRECPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton101, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox132)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox133)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaRECPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaRECPLayout.createSequentialGroup()
                        .addComponent(jCheckBox134)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton102))
                    .addComponent(CRP003DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane8.addTab("Recibos", abaRECP);

        jLabel70.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel70.setText("Títulos a Pagar:");

        jCheckBox110.setText("Permitir cancelamento de baixa de títulos?");
        jCheckBox110.setActionCommand("CCANP001");

        jCheckBox111.setText("Não permitir cancelamento de baixa de títulos parciais?");
        jCheckBox111.setActionCommand("CCANP002");

        cancelamentoPagarGroup1.add(jRadioButton89);
        jRadioButton89.setText("Permitir cancelamento de baixa de títulos para as formas de pagamento?");
        jRadioButton89.setActionCommand("CCANP004");

        jCheckBox112.setText("Espécie");
        jCheckBox112.setActionCommand("CCANP00401");

        jCheckBox113.setText("Cheque");
        jCheckBox113.setActionCommand("CCANP00402");

        jCheckBox115.setText("Internet");
        jCheckBox115.setActionCommand("CCANP00403");

        cancelamentoPagarGroup1.add(jRadioButton90);
        jRadioButton90.setText("Permitir cancelamento de baixa para titulos com valor até R$:");
        jRadioButton90.setActionCommand("CCANP005");

        jCheckBox116.setText("Não permitir cancelamento de baixa para títulos em LOTE?");
        jCheckBox116.setActionCommand("CCANP003");

        jCheckBox117.setText("Internet");
        jCheckBox117.setActionCommand("CCANP00703");

        jCheckBox119.setText("Cheque");
        jCheckBox119.setActionCommand("CCANP00702");

        jCheckBox120.setText("Espécie");
        jCheckBox120.setActionCommand("CCANP00701");

        cancelamentoPagarGroup2.add(jRadioButton92);
        jRadioButton92.setText("Permitir cancelamento de baixa de títulos em LOTE para as formas de pagamento?");
        jRadioButton92.setActionCommand("CCANP007");

        cancelamentoPagarGroup2.add(jRadioButton94);
        jRadioButton94.setText("Permitir cancelamento de baixa para titulos em LOTE com valor até R$:");
        jRadioButton94.setActionCommand("CCANP008");

        jPanel36.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        botaoFecharCCANP.setMnemonic('F');
        botaoFecharCCANP.setText("Fechar");
        botaoFecharCCANP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharCCANPActionPerformed(evt);
            }
        });

        botaoSalvarTudoCCANP.setMnemonic('F');
        botaoSalvarTudoCCANP.setText("Salvar Tudo");
        botaoSalvarTudoCCANP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoCCANPActionPerformed(evt);
            }
        });

        botaoSalvarCCANP.setMnemonic('F');
        botaoSalvarCCANP.setText("Salvar");
        botaoSalvarCCANP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarCCANPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarCCANP, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoCCANP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCCANP, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel36Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoCCANP, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarCCANP, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharCCANP, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jCheckBox106.setText("Permitir cancelamento de títulos exportados?");
        jCheckBox106.setActionCommand("CCANP006");
        jCheckBox106.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox106ActionPerformed(evt);
            }
        });

        jRadioButton91.setText("Não Permitir cancelar baixa de titulos pagos a mais de");
        jRadioButton91.setActionCommand("CCANP009");

        jLabel6.setText("dias.");

        CCANP009DB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        CCANP009DB.setText("30");

        javax.swing.GroupLayout abaCANPLayout = new javax.swing.GroupLayout(abaCANP);
        abaCANP.setLayout(abaCANPLayout);
        abaCANPLayout.setHorizontalGroup(
            abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCANPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCANPLayout.createSequentialGroup()
                        .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton89)
                            .addComponent(jCheckBox116)
                            .addComponent(jCheckBox111)
                            .addComponent(jCheckBox110)
                            .addComponent(jCheckBox106))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                        .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton92)
                            .addGroup(abaCANPLayout.createSequentialGroup()
                                .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton94)
                                    .addGroup(abaCANPLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jCheckBox119, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCheckBox120, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jCheckBox117, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CCANP008DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(96, 96, 96))
                    .addGroup(abaCANPLayout.createSequentialGroup()
                        .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaCANPLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox113, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox112, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox115, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(abaCANPLayout.createSequentialGroup()
                                .addComponent(jRadioButton91)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CCANP009DB, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel6))
                            .addGroup(abaCANPLayout.createSequentialGroup()
                                .addComponent(jRadioButton90)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CCANP005DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(569, Short.MAX_VALUE))))
            .addGroup(abaCANPLayout.createSequentialGroup()
                .addGap(378, 378, 378)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        abaCANPLayout.setVerticalGroup(
            abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCANPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaCANPLayout.createSequentialGroup()
                        .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox110)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox111)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox116)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox106)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton89, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaCANPLayout.createSequentialGroup()
                        .addComponent(jRadioButton92, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox120)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox119)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox117)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton94)
                            .addComponent(CCANP008DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox112)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox113)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox115)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton90)
                    .addComponent(CCANP005DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(abaCANPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton91)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CCANP009DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane8.addTab("Cancelamento", abaCANP);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Contas a Pagar", jPanel1);

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setText("Títulos a Receber:");

        jCheckBox55.setText("Não permitir particionamento de títulos?");
        jCheckBox55.setActionCommand("CCADR001");

        jCheckBox56.setText("Não permitir cadastro Nota Fiscal de Saída?");
        jCheckBox56.setActionCommand("CCADR002");

        jPanel29.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        botaoFecharCCADR.setMnemonic('F');
        botaoFecharCCADR.setText("Fechar");
        botaoFecharCCADR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharCCADRActionPerformed(evt);
            }
        });

        botaoSalvarTudoCCADR.setMnemonic('F');
        botaoSalvarTudoCCADR.setText("Salvar Tudo");
        botaoSalvarTudoCCADR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoCCADRActionPerformed(evt);
            }
        });

        botaoSalvarCCADR.setMnemonic('F');
        botaoSalvarCCADR.setText("Salvar");
        botaoSalvarCCADR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarCCADRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarCCADR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoCCADR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCCADR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoCCADR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarCCADR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharCCADR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jCheckBox58.setText("Não permitir rateio por centro de custo?");
        jCheckBox58.setActionCommand("CCADR003");

        jCheckBox59.setText("Não permitir rateio contábil?");
        jCheckBox59.setActionCommand("CCADR004");

        jCheckBoxProvisaoReceber.setText("Não permitir provisionamento?");
        jCheckBoxProvisaoReceber.setActionCommand("CCADR006");

        javax.swing.GroupLayout abaCADRLayout = new javax.swing.GroupLayout(abaCADR);
        abaCADR.setLayout(abaCADRLayout);
        abaCADRLayout.setHorizontalGroup(
            abaCADRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCADRLayout.createSequentialGroup()
                .addContainerGap(381, Short.MAX_VALUE)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(345, 345, 345))
            .addGroup(abaCADRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCADRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxProvisaoReceber)
                    .addComponent(jCheckBox56)
                    .addComponent(jCheckBox59)
                    .addComponent(jCheckBox58)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox55))
                .addContainerGap(757, Short.MAX_VALUE))
        );
        abaCADRLayout.setVerticalGroup(
            abaCADRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCADRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox59, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxProvisaoReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(244, 244, 244)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTabbedPane2.addTab("Cadastro", abaCADR);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setText("Títulos a Receber:");

        jCheckBox66.setText("Não permitir baixa PARCIAL?");
        jCheckBox66.setActionCommand("CBR003");

        baixaReceberGroup1.add(jRadioButton38);
        jRadioButton38.setText("Permitir baixa PARCIAL para as formas de pagamento?");
        jRadioButton38.setActionCommand("CBR005");

        jCheckBox67.setText("Espécie");
        jCheckBox67.setActionCommand("CBR00501");

        jCheckBox80.setText("Cheque");
        jCheckBox80.setActionCommand("CBR00502");

        jCheckBox81.setText("Internet");
        jCheckBox81.setActionCommand("CBR00503");

        baixaReceberGroup1.add(jRadioButton39);
        jRadioButton39.setText("Não permitir baixa PARCIAL com valor inferior a R$");
        jRadioButton39.setActionCommand("CBR006");

        jCheckBox11.setText("Não permitir baixa PARCIAL para títulos com acrescimos e/ou deduções?");
        jCheckBox11.setActionCommand("CBR004");

        jCheckBox12.setText("Não permitir baixa com valor superior a R$ ");
        jCheckBox12.setActionCommand("CBR002");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Formas de Pagamento");

        jLabel16.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel16.setText("+ Espécie:");

        baixaReceberGroup2.add(jRadioButton56);
        jRadioButton56.setText("Permitir baixa para titulos com valor até R$");
        jRadioButton56.setActionCommand("CBRFPE001");

        jLabel17.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel17.setText("+ Cheque:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel18.setText("+ Internet:");

        baixaReceberGroup3.add(jRadioButton58);
        jRadioButton58.setText("Permitir baixa para titulos com valor até R$");
        jRadioButton58.setActionCommand("CBRFPC001");

        baixaReceberGroup4.add(jRadioButton60);
        jRadioButton60.setText("Permitir baixa para titulos com valor até R$");
        jRadioButton60.setActionCommand("CBRFPI001");

        jPanel39.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        botaoFecharCBR.setMnemonic('F');
        botaoFecharCBR.setText("Fechar");
        botaoFecharCBR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharCBRActionPerformed(evt);
            }
        });

        botaoSalvarTudoCBR.setMnemonic('F');
        botaoSalvarTudoCBR.setText("Salvar Tudo");
        botaoSalvarTudoCBR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoCBRActionPerformed(evt);
            }
        });

        botaoSalvarCBR.setMnemonic('F');
        botaoSalvarCBR.setText("Salvar");
        botaoSalvarCBR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarCBRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarCBR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoCBR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCBR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoCBR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarCBR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharCBR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout abaBAIXARLayout = new javax.swing.GroupLayout(abaBAIXAR);
        abaBAIXAR.setLayout(abaBAIXARLayout);
        abaBAIXARLayout.setHorizontalGroup(
            abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaBAIXARLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaBAIXARLayout.createSequentialGroup()
                        .addGap(353, 353, 353)
                        .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(363, Short.MAX_VALUE))
                    .addGroup(abaBAIXARLayout.createSequentialGroup()
                        .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(abaBAIXARLayout.createSequentialGroup()
                                .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton39)
                                    .addGroup(abaBAIXARLayout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jCheckBox81, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBR006DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jRadioButton38)
                            .addGroup(abaBAIXARLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox80, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox67, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jCheckBox11)
                            .addGroup(abaBAIXARLayout.createSequentialGroup()
                                .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox66)
                                    .addComponent(jCheckBox12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CBR002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                        .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(abaBAIXARLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel17)
                                            .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel18)
                                                    .addGroup(abaBAIXARLayout.createSequentialGroup()
                                                        .addGap(10, 10, 10)
                                                        .addComponent(jRadioButton60)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(CBRFPI001DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGroup(abaBAIXARLayout.createSequentialGroup()
                                                    .addComponent(jRadioButton58)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(CBRFPC001DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(abaBAIXARLayout.createSequentialGroup()
                                            .addComponent(jRadioButton56)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(CBRFPE001DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(197, 197, 197))))
        );
        abaBAIXARLayout.setVerticalGroup(
            abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaBAIXARLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaBAIXARLayout.createSequentialGroup()
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox12)
                            .addComponent(CBR002DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox66)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton38, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox80)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaBAIXARLayout.createSequentialGroup()
                                .addComponent(jCheckBox81)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jRadioButton39))
                            .addComponent(CBR006DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaBAIXARLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton56)
                            .addComponent(CBRFPE001DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton58, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(CBRFPC001DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(abaBAIXARLayout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(32, 32, 32)))
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBAIXARLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton60)
                            .addComponent(CBRFPI001DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Baixa", abaBAIXAR);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Títulos a Receber:");

        jCheckBox21.setText("Não permitir cancelamento de baixa de títulos?");
        jCheckBox21.setActionCommand("CCANR001");

        jCheckBox22.setText("Não permitir cancelamento de baixa de títulos parciais?");
        jCheckBox22.setActionCommand("CCANR002");

        cancelamentoReceberGroup1.add(jRadioButton17);
        jRadioButton17.setText("Permitir cancelamento de baixa de títulos para as formas de pagamento?");
        jRadioButton17.setActionCommand("CCANR003");

        jCheckBox24.setText("Cheque");
        jCheckBox24.setActionCommand("CCANR00302");

        jCheckBox23.setText("Espécie");
        jCheckBox23.setActionCommand("CCANR00301");

        jCheckBox26.setText("Internet");
        jCheckBox26.setActionCommand("CCANR00303");

        cancelamentoReceberGroup1.add(jRadioButton18);
        jRadioButton18.setText("Permitir cancelamento de baixa para titulos com valor até R$:");
        jRadioButton18.setActionCommand("CCANR004");

        jPanel40.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        botaoFecharCCANR.setMnemonic('F');
        botaoFecharCCANR.setText("Fechar");
        botaoFecharCCANR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharCCANRActionPerformed(evt);
            }
        });

        botaoSalvarTudoCCANR.setMnemonic('F');
        botaoSalvarTudoCCANR.setText("Salvar Tudo");
        botaoSalvarTudoCCANR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoCCANRActionPerformed(evt);
            }
        });

        botaoSalvarCCANR.setMnemonic('F');
        botaoSalvarCCANR.setText("Salvar");
        botaoSalvarCCANR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarCCANRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarCCANR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoCCANR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCCANR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoCCANR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarCCANR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharCCANR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jCheckBox72.setText("Permitir cancelamento de títulos exportados?");
        jCheckBox72.setActionCommand("CCANR005");
        jCheckBox72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox72ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaCANRLayout = new javax.swing.GroupLayout(abaCANR);
        abaCANR.setLayout(abaCANRLayout);
        abaCANRLayout.setHorizontalGroup(
            abaCANRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCANRLayout.createSequentialGroup()
                .addGroup(abaCANRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCANRLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaCANRLayout.createSequentialGroup()
                        .addGap(365, 365, 365)
                        .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaCANRLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(abaCANRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton17)
                            .addComponent(jCheckBox22)
                            .addComponent(jCheckBox21)
                            .addGroup(abaCANRLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(abaCANRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBox24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(abaCANRLayout.createSequentialGroup()
                                .addComponent(jRadioButton18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CCANR004DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(abaCANRLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jCheckBox72)))
                .addContainerGap(361, Short.MAX_VALUE))
        );
        abaCANRLayout.setVerticalGroup(
            abaCANRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCANRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaCANRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jRadioButton18)
                    .addComponent(CCANR004DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 179, Short.MAX_VALUE)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Cancelamento", abaCANR);

        recibosReceberGroup1.add(jRadioButton42);
        jRadioButton42.setText("Permitir emissão de recibos para titulos com valor até R$:");
        jRadioButton42.setActionCommand("CRR003");

        jCheckBox70.setText("Cheque");
        jCheckBox70.setActionCommand("CRR00202");

        recibosReceberGroup1.add(jRadioButton41);
        jRadioButton41.setText("Permitir emissão de recibos para as formas de pagamento?");
        jRadioButton41.setActionCommand("CRR002");

        jCheckBox69.setText("Espécie");
        jCheckBox69.setActionCommand("CRR00201");

        jCheckBox71.setText("Internet");
        jCheckBox71.setActionCommand("CRR00203");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setText("Títulos a Receber:");

        jPanel41.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        botaoFecharCCR.setMnemonic('F');
        botaoFecharCCR.setText("Fechar");
        botaoFecharCCR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharCCRActionPerformed(evt);
            }
        });

        botaoSalvarTudoCCR.setMnemonic('F');
        botaoSalvarTudoCCR.setText("Salvar Tudo");
        botaoSalvarTudoCCR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarTudoCCRActionPerformed(evt);
            }
        });

        botaoSalvarCCR.setMnemonic('F');
        botaoSalvarCCR.setText("Salvar");
        botaoSalvarCCR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarCCRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarCCR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvarTudoCCR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCCR, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarTudoCCR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoSalvarCCR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharCCR, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jCheckBox2.setText("Não permitir emissão de recibos sem BAIXA?");
        jCheckBox2.setActionCommand("CRR001");

        javax.swing.GroupLayout abaRECRLayout = new javax.swing.GroupLayout(abaRECR);
        abaRECR.setLayout(abaRECRLayout);
        abaRECRLayout.setHorizontalGroup(
            abaRECRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaRECRLayout.createSequentialGroup()
                .addContainerGap(370, Short.MAX_VALUE)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(356, 356, 356))
            .addGroup(abaRECRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaRECRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton41)
                    .addGroup(abaRECRLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(abaRECRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBox70, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox69, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaRECRLayout.createSequentialGroup()
                        .addGroup(abaRECRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton42)
                            .addGroup(abaRECRLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jCheckBox71, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CRR003DB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(589, Short.MAX_VALUE))
        );
        abaRECRLayout.setVerticalGroup(
            abaRECRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaRECRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox2)
                .addGap(5, 5, 5)
                .addComponent(jRadioButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaRECRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaRECRLayout.createSequentialGroup()
                        .addComponent(jCheckBox71)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton42))
                    .addComponent(CRR003DB, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Recibos", abaRECR);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Contas a Receber", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1014, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharTAActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharTAActionPerformed

    private void botaoSalvarTudoTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoTAActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoTAActionPerformed

    private void botaoSalvarTAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTAActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaTelaAvisos.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.TA008.getCodigo())) {
                                if (TA008DT.getDate() == null) {
                                    exibeMensagemAviso("Selecione uma Data!", null);
                                    return;
                                }
                                parametro.setData(PempecParse.dateToDate(TA008DT.getDate()));
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

}//GEN-LAST:event_botaoSalvarTAActionPerformed

    private void botaoFecharGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharGActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharGActionPerformed

    private void botaoSalvarTudoGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoGActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoGActionPerformed

    private void botaoSalvarGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarGActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaGeral.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

}//GEN-LAST:event_botaoSalvarGActionPerformed

    private void botaoFecharCCADRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCCADRActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharCCADRActionPerformed

    private void botaoSalvarTudoCCADRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoCCADRActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoCCADRActionPerformed

    private void botaoSalvarCCADRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarCCADRActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaCADR.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }


}//GEN-LAST:event_botaoSalvarCCADRActionPerformed

    private void botaoFecharCCADRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCCADRPActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharCCADRPActionPerformed

    private void botaoSalvarTudoCCADPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoCCADPActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoCCADPActionPerformed

    private void botaoSalvarCCADPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarCCADPActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaCADP.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

}//GEN-LAST:event_botaoSalvarCCADPActionPerformed

    private void botaoFecharCBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCBSActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharCBSActionPerformed

    private void botaoSalvarTudoCBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoCBSActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoCBSActionPerformed

    private void botaoSalvarCBSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarCBSActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaBAIXAPS.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CBPS002.getCodigo())) {
                                if (CBPS002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBPS002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPS006.getCodigo())) {
                                if (CBPS006DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBPS006DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPSFPE002.getCodigo())) {
                                if (CBPSFPE002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBPSFPE002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPSFPC002.getCodigo())) {
                                if (CBPSFPC002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBPSFPC002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPSFPI002.getCodigo())) {
                                if (CBPSFPI002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBPSFPI002DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

}//GEN-LAST:event_botaoSalvarCBSActionPerformed

    private void botaoFecharCBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCBLActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharCBLActionPerformed

    private void botaoSalvarTudoCBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoCBLActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoCBLActionPerformed

    private void botaoSalvarCBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarCBLActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaBAIXAPL.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CBPL002.getCodigo())) {
                                if (CBPL002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBPL002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPL005.getCodigo())) {
                                if (CBPL005DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBPL005DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPLFPE002.getCodigo())) {
                                if (CBPLFPE002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBPLFPE002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPLFPC002.getCodigo())) {
                                if (CBPLFPC002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBPLFPC002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPLFPI002.getCodigo())) {
                                if (CBPLFPI002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBPLFPI002DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

}//GEN-LAST:event_botaoSalvarCBLActionPerformed

    private void botaoFecharCCANPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCCANPActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharCCANPActionPerformed

    private void botaoSalvarTudoCCANPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoCCANPActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoCCANPActionPerformed

    private void botaoSalvarCCANPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarCCANPActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaCANP.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CCANP005.getCodigo())) {
                                if (CCANP005DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CCANP005DB.getValue());
                            }

                            if (codigo.equals(Parametro.CCANP008.getCodigo())) {
                                if (CCANP008DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CCANP008DB.getValue());
                            }

                            if (codigo.equals(Parametro.CCANP009.getCodigo())) {

                                Double ccanp09 = 30d;
                                if (CCANP009DB.getText() != null) {
                                    ccanp09 = Double.valueOf(PempecUtil.somenteNumero(CCANP009DB.getText()));
                                }

                                if (ccanp09 == 0) {
                                    exibeMensagemAviso("Informe um Periodo permitido para o cancelamento após baixa!", null);
                                    return;
                                }
                                parametro.setValor(ccanp09);
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

}//GEN-LAST:event_botaoSalvarCCANPActionPerformed

    private void botaoFecharCBRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCBRActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharCBRActionPerformed

    private void botaoSalvarTudoCBRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoCBRActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoCBRActionPerformed

    private void botaoSalvarCBRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarCBRActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaBAIXAR.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CBR002.getCodigo())) {
                                if (CBR002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBR002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBR006.getCodigo())) {
                                if (CBR006DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBR006DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBRFPE001.getCodigo())) {
                                if (CBRFPE001DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBRFPE001DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBRFPC001.getCodigo())) {
                                if (CBRFPC001DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBRFPC001DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBRFPI001.getCodigo())) {
                                if (CBRFPI001DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CBRFPI001DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

}//GEN-LAST:event_botaoSalvarCBRActionPerformed

    private void botaoFecharCCANRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCCANRActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharCCANRActionPerformed

    private void botaoSalvarTudoCCANRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoCCANRActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoCCANRActionPerformed

    private void botaoSalvarCCANRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarCCANRActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaCANR.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CCANR004.getCodigo())) {
                                if (CCANR004DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CCANR004DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

}//GEN-LAST:event_botaoSalvarCCANRActionPerformed

    private void botaoFecharCCRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCCRActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharCCRActionPerformed

    private void botaoSalvarTudoCCRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoCCRActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoCCRActionPerformed

    private void botaoSalvarCCRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarCCRActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaRECR.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CRR003.getCodigo())) {
                                if (CRR003DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CRR003DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

}//GEN-LAST:event_botaoSalvarCCRActionPerformed

    private void jCheckBox106ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox106ActionPerformed
    }//GEN-LAST:event_jCheckBox106ActionPerformed

    private void jCheckBox72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox72ActionPerformed
    }//GEN-LAST:event_jCheckBox72ActionPerformed

    private void botaoFecharCRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCRPActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharCRPActionPerformed

    private void botaoSalvarTudoCRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarTudoCRPActionPerformed
        this.salvarTudo();
}//GEN-LAST:event_botaoSalvarTudoCRPActionPerformed

    private void botaoSalvarCRPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarCRPActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            Component[] components = abaRECP.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CRP003.getCodigo())) {
                                if (CRP003DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor!", null);
                                    return;
                                }
                                parametro.setValor(CRP003DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }
}//GEN-LAST:event_botaoSalvarCRPActionPerformed

    private ParametrosModel findByCodigo(Collection<ParametrosModel> coll, String codigo) {

        for (ParametrosModel param : coll) {

            if (codigo.equals(param.getCodigo())) {
                return param;
            }

        }

        return null;

    }

    private void salvarTudo() {

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            Component[] components = abaGeral.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            components = abaTelaAvisos.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.TA008.getCodigo())) {

                                if (TA008DT.getDate() == null) {
                                    exibeMensagemAviso("Selecione uma Data! \nObs.: Tela de Avisos", null);
                                    return;
                                }

                                parametro.setData(PempecParse.dateToDate(TA008DT.getDate()));
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            components = abaCADP.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            components = abaBAIXAPS.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CBPS002.getCodigo())) {
                                if (CBPS002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Baixa Simples", null);
                                    return;
                                }
                                parametro.setValor(CBPS002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPS006.getCodigo())) {
                                if (CBPS006DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Baixa Simples", null);
                                    return;
                                }
                                parametro.setValor(CBPS006DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPSFPE002.getCodigo())) {
                                if (CBPSFPE002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Baixa Simples", null);
                                    return;
                                }
                                parametro.setValor(CBPSFPE002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPSFPC002.getCodigo())) {
                                if (CBPSFPC002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Baixa Simples", null);
                                    return;
                                }
                                parametro.setValor(CBPSFPC002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPSFPI002.getCodigo())) {
                                if (CBPSFPI002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Baixa Simples", null);
                                    return;
                                }
                                parametro.setValor(CBPSFPI002DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            components = abaBAIXAPL.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CBPL002.getCodigo())) {
                                if (CBPL002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Baixa Lote", null);
                                    return;
                                }
                                parametro.setValor(CBPL002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPL005.getCodigo())) {
                                if (CBPL005DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Baixa Lote", null);
                                    return;
                                }
                                parametro.setValor(CBPL005DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPLFPE002.getCodigo())) {
                                if (CBPLFPE002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Baixa Lote", null);
                                    return;
                                }
                                parametro.setValor(CBPLFPE002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPLFPC002.getCodigo())) {
                                if (CBPLFPC002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Baixa Lote", null);
                                    return;
                                }
                                parametro.setValor(CBPLFPC002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBPLFPI002.getCodigo())) {
                                if (CBPLFPI002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Baixa Lote", null);
                                    return;
                                }
                                parametro.setValor(CBPLFPI002DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            components = abaCANP.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CCANP005.getCodigo())) {
                                if (CCANP005DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Cancelamento", null);
                                    return;
                                }
                                parametro.setValor(CCANP005DB.getValue());
                            }

                            if (codigo.equals(Parametro.CCANP008.getCodigo())) {
                                if (CCANP008DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Cancelamento", null);
                                    return;
                                }
                                parametro.setValor(CCANP008DB.getValue());
                            }

                            if (codigo.equals(Parametro.CCANP009.getCodigo())) {

                                Double ccanp09 = 30d;
                                if (CCANP009DB.getText() != null) {
                                    ccanp09 = Double.valueOf(PempecUtil.somenteNumero(CCANP009DB.getText()));
                                }

                                if (ccanp09 == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Cancelamento", null);
                                    return;
                                }
                                parametro.setValor(ccanp09);

                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            components = abaRECP.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CRP003.getCodigo())) {
                                if (CRP003DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Pagar - Recibo", null);
                                    return;
                                }
                                parametro.setValor(CRP003DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            components = abaCADR.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            components = abaBAIXAR.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CBR002.getCodigo())) {
                                if (CBR002DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Receber - Baixa", null);
                                    return;
                                }
                                parametro.setValor(CBR002DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBR006.getCodigo())) {
                                if (CBR006DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Receber - Baixa", null);
                                    return;
                                }
                                parametro.setValor(CBR006DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBRFPE001.getCodigo())) {
                                if (CBRFPE001DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Receber - Baixa", null);
                                    return;
                                }
                                parametro.setValor(CBRFPE001DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBRFPC001.getCodigo())) {
                                if (CBRFPC001DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Receber - Baixa", null);
                                    return;
                                }
                                parametro.setValor(CBRFPC001DB.getValue());
                            }

                            if (codigo.equals(Parametro.CBRFPI001.getCodigo())) {
                                if (CBRFPI001DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Receber - Baixa", null);
                                    return;
                                }
                                parametro.setValor(CBRFPI001DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            components = abaCANR.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CCANR004.getCodigo())) {
                                if (CCANR004DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Receber - Cancelamento", null);
                                    return;
                                }
                                parametro.setValor(CCANR004DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            components = abaRECR.getComponents();

            for (Component component : components) {

                if (component instanceof JToggleButton) {

                    String codigo = ((JToggleButton) component).getActionCommand();

                    ParametrosModel parametro = this.findByCodigo(parametros, codigo);

                    if (parametro != null) {

                        if (((JToggleButton) component).isSelected()) {

                            if (codigo.equals(Parametro.CRR003.getCodigo())) {
                                if (CRR003DB.getValue() == 0) {
                                    exibeMensagemAviso("Selecione um Valor! \nObs.: Contas a Receber - Recibo", null);
                                    return;
                                }
                                parametro.setValor(CRR003DB.getValue());
                            }

                            parametro.setStatus(1);

                        } else {

                            parametro.setStatus(0);

                        }

                    }

                }

            }

            parametrosBO.salvar(parametros, registroMovimento("Salvar configurações Sistema", "PARAMETROS", "Configurações Sistema", 0d, "ALTERADO"));

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_PARAMETROS_MODIFICADO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            constantesModel.setCodigo("1");

            constantesBO.alterar(constantesModel);

            exibeMensagemAviso("Salvo com sucesso!", null);

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private br.com.pempec.componentes.JDoubleField CBPL002DB;
    private br.com.pempec.componentes.JDoubleField CBPL005DB;
    private br.com.pempec.componentes.JDoubleField CBPLFPC002DB;
    private br.com.pempec.componentes.JDoubleField CBPLFPE002DB;
    private br.com.pempec.componentes.JDoubleField CBPLFPI002DB;
    private br.com.pempec.componentes.JDoubleField CBPS002DB;
    private br.com.pempec.componentes.JDoubleField CBPS006DB;
    private br.com.pempec.componentes.JDoubleField CBPSFPC002DB;
    private br.com.pempec.componentes.JDoubleField CBPSFPE002DB;
    private br.com.pempec.componentes.JDoubleField CBPSFPI002DB;
    private br.com.pempec.componentes.JDoubleField CBR002DB;
    private br.com.pempec.componentes.JDoubleField CBR006DB;
    private br.com.pempec.componentes.JDoubleField CBRFPC001DB;
    private br.com.pempec.componentes.JDoubleField CBRFPE001DB;
    private br.com.pempec.componentes.JDoubleField CBRFPI001DB;
    private br.com.pempec.componentes.JDoubleField CCANP005DB;
    private br.com.pempec.componentes.JDoubleField CCANP008DB;
    private javax.swing.JFormattedTextField CCANP009DB;
    private br.com.pempec.componentes.JDoubleField CCANR004DB;
    private br.com.pempec.componentes.JDoubleField CRP003DB;
    private br.com.pempec.componentes.JDoubleField CRR003DB;
    private javax.swing.JCheckBox G001;
    private javax.swing.JRadioButton G002;
    private javax.swing.JRadioButton G003;
    private javax.swing.JRadioButton TA001;
    private javax.swing.JRadioButton TA002;
    private javax.swing.JRadioButton TA003;
    private javax.swing.JRadioButton TA004;
    private javax.swing.JRadioButton TA005;
    private javax.swing.JRadioButton TA006;
    private javax.swing.JRadioButton TA007;
    private javax.swing.JRadioButton TA008;
    private org.jdesktop.swingx.JXDatePicker TA008DT;
    private javax.swing.JRadioButton TA009;
    private javax.swing.JRadioButton TA010;
    private javax.swing.JPanel abaBAIXAPL;
    private javax.swing.JPanel abaBAIXAPS;
    private javax.swing.JPanel abaBAIXAR;
    private javax.swing.JPanel abaCADP;
    private javax.swing.JPanel abaCADR;
    private javax.swing.JPanel abaCANP;
    private javax.swing.JPanel abaCANR;
    private javax.swing.JPanel abaGeral;
    private javax.swing.JPanel abaRECP;
    private javax.swing.JPanel abaRECR;
    private javax.swing.JPanel abaTelaAvisos;
    private javax.swing.ButtonGroup baixaPagarGroup1;
    private javax.swing.ButtonGroup baixaPagarGroup2;
    private javax.swing.ButtonGroup baixaPagarGroup3;
    private javax.swing.ButtonGroup baixaPagarGroup4;
    private javax.swing.ButtonGroup baixaPagarGroup5;
    private javax.swing.ButtonGroup baixaReceberGroup1;
    private javax.swing.ButtonGroup baixaReceberGroup2;
    private javax.swing.ButtonGroup baixaReceberGroup3;
    private javax.swing.ButtonGroup baixaReceberGroup4;
    private javax.swing.ButtonGroup baixaTodosGroup1;
    private javax.swing.JButton botaoFecharCBL;
    private javax.swing.JButton botaoFecharCBR;
    private javax.swing.JButton botaoFecharCBS;
    private javax.swing.JButton botaoFecharCCADR;
    private javax.swing.JButton botaoFecharCCADRP;
    private javax.swing.JButton botaoFecharCCANP;
    private javax.swing.JButton botaoFecharCCANR;
    private javax.swing.JButton botaoFecharCCR;
    private javax.swing.JButton botaoFecharCRP;
    private javax.swing.JButton botaoFecharG;
    private javax.swing.JButton botaoFecharTA;
    private javax.swing.JButton botaoSalvarCBL;
    private javax.swing.JButton botaoSalvarCBR;
    private javax.swing.JButton botaoSalvarCBS;
    private javax.swing.JButton botaoSalvarCCADP;
    private javax.swing.JButton botaoSalvarCCADR;
    private javax.swing.JButton botaoSalvarCCANP;
    private javax.swing.JButton botaoSalvarCCANR;
    private javax.swing.JButton botaoSalvarCCR;
    private javax.swing.JButton botaoSalvarCRP;
    private javax.swing.JButton botaoSalvarG;
    private javax.swing.JButton botaoSalvarTA;
    private javax.swing.JButton botaoSalvarTudoCBL;
    private javax.swing.JButton botaoSalvarTudoCBR;
    private javax.swing.JButton botaoSalvarTudoCBS;
    private javax.swing.JButton botaoSalvarTudoCCADP;
    private javax.swing.JButton botaoSalvarTudoCCADR;
    private javax.swing.JButton botaoSalvarTudoCCANP;
    private javax.swing.JButton botaoSalvarTudoCCANR;
    private javax.swing.JButton botaoSalvarTudoCCR;
    private javax.swing.JButton botaoSalvarTudoCRP;
    private javax.swing.JButton botaoSalvarTudoG;
    private javax.swing.JButton botaoSalvarTudoTA;
    private javax.swing.ButtonGroup cancelamentoPagarGroup1;
    private javax.swing.ButtonGroup cancelamentoPagarGroup2;
    private javax.swing.ButtonGroup cancelamentoReceberGroup1;
    private javax.swing.ButtonGroup geralGroup1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox101;
    private javax.swing.JCheckBox jCheckBox102;
    private javax.swing.JCheckBox jCheckBox103;
    private javax.swing.JCheckBox jCheckBox104;
    private javax.swing.JCheckBox jCheckBox105;
    private javax.swing.JCheckBox jCheckBox106;
    private javax.swing.JCheckBox jCheckBox11;
    private javax.swing.JCheckBox jCheckBox110;
    private javax.swing.JCheckBox jCheckBox111;
    private javax.swing.JCheckBox jCheckBox112;
    private javax.swing.JCheckBox jCheckBox113;
    private javax.swing.JCheckBox jCheckBox115;
    private javax.swing.JCheckBox jCheckBox116;
    private javax.swing.JCheckBox jCheckBox117;
    private javax.swing.JCheckBox jCheckBox119;
    private javax.swing.JCheckBox jCheckBox12;
    private javax.swing.JCheckBox jCheckBox120;
    private javax.swing.JCheckBox jCheckBox132;
    private javax.swing.JCheckBox jCheckBox133;
    private javax.swing.JCheckBox jCheckBox134;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox21;
    private javax.swing.JCheckBox jCheckBox22;
    private javax.swing.JCheckBox jCheckBox23;
    private javax.swing.JCheckBox jCheckBox24;
    private javax.swing.JCheckBox jCheckBox26;
    private javax.swing.JCheckBox jCheckBox52;
    private javax.swing.JCheckBox jCheckBox53;
    private javax.swing.JCheckBox jCheckBox54;
    private javax.swing.JCheckBox jCheckBox55;
    private javax.swing.JCheckBox jCheckBox56;
    private javax.swing.JCheckBox jCheckBox57;
    private javax.swing.JCheckBox jCheckBox58;
    private javax.swing.JCheckBox jCheckBox59;
    private javax.swing.JCheckBox jCheckBox66;
    private javax.swing.JCheckBox jCheckBox67;
    private javax.swing.JCheckBox jCheckBox69;
    private javax.swing.JCheckBox jCheckBox70;
    private javax.swing.JCheckBox jCheckBox71;
    private javax.swing.JCheckBox jCheckBox72;
    private javax.swing.JCheckBox jCheckBox80;
    private javax.swing.JCheckBox jCheckBox81;
    private javax.swing.JCheckBox jCheckBox95;
    private javax.swing.JCheckBox jCheckBox97;
    private javax.swing.JCheckBox jCheckBox98;
    private javax.swing.JCheckBox jCheckBox99;
    private javax.swing.JCheckBox jCheckBoxProvisaoPagar;
    private javax.swing.JCheckBox jCheckBoxProvisaoReceber;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JRadioButton jRBTipoPapel;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton101;
    private javax.swing.JRadioButton jRadioButton102;
    private javax.swing.JRadioButton jRadioButton17;
    private javax.swing.JRadioButton jRadioButton18;
    private javax.swing.JRadioButton jRadioButton38;
    private javax.swing.JRadioButton jRadioButton39;
    private javax.swing.JRadioButton jRadioButton41;
    private javax.swing.JRadioButton jRadioButton42;
    private javax.swing.JRadioButton jRadioButton56;
    private javax.swing.JRadioButton jRadioButton58;
    private javax.swing.JRadioButton jRadioButton60;
    private javax.swing.JRadioButton jRadioButton63;
    private javax.swing.JRadioButton jRadioButton64;
    private javax.swing.JRadioButton jRadioButton65;
    private javax.swing.JRadioButton jRadioButton67;
    private javax.swing.JRadioButton jRadioButton69;
    private javax.swing.JRadioButton jRadioButton70;
    private javax.swing.JRadioButton jRadioButton72;
    private javax.swing.JRadioButton jRadioButton73;
    private javax.swing.JRadioButton jRadioButton74;
    private javax.swing.JRadioButton jRadioButton75;
    private javax.swing.JRadioButton jRadioButton77;
    private javax.swing.JRadioButton jRadioButton78;
    private javax.swing.JRadioButton jRadioButton80;
    private javax.swing.JRadioButton jRadioButton81;
    private javax.swing.JRadioButton jRadioButton83;
    private javax.swing.JRadioButton jRadioButton84;
    private javax.swing.JRadioButton jRadioButton86;
    private javax.swing.JRadioButton jRadioButton88;
    private javax.swing.JRadioButton jRadioButton89;
    private javax.swing.JRadioButton jRadioButton90;
    private javax.swing.JRadioButton jRadioButton91;
    private javax.swing.JRadioButton jRadioButton92;
    private javax.swing.JRadioButton jRadioButton94;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane11;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane8;
    private javax.swing.ButtonGroup recibosPagarGroup1;
    private javax.swing.ButtonGroup recibosReceberGroup1;
    private javax.swing.ButtonGroup telaAvisosGroup1;
    private javax.swing.ButtonGroup telaAvisosGroup2;
    // End of variables declaration//GEN-END:variables
}
