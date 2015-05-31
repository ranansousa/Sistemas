package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.BancoBO;
import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.ContaBancariaCreditoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaTransferenciaBO;
import br.com.pempec.finance.businessObjects.ContaContabilBO;
import br.com.pempec.finance.businessObjects.LoteContabilBO;
import br.com.pempec.finance.businessObjects.LotePagamentoTituloBO;
import br.com.pempec.finance.businessObjects.MegaContabilBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.SacadoBO;
import br.com.pempec.finance.businessObjects.TesourariaCreditoBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaAcrescimoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaChequeBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaDeducaoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaFormaPagamentoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaInternetBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;

public class ManutencaoBancoDados extends FinanceInternalFrame implements IRepopulador {

    private SacadoBO sacadoBO = new SacadoBO();
    private CedenteBO cedenteBO = new CedenteBO();
    private BancoBO bancoBO = new BancoBO();
    private ContaContabilBO contaContabilBO = new ContaContabilBO();
    
    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private TituloPagarBaixaBO tituloPagarBaixaBO = new TituloPagarBaixaBO();
    private TituloReceberBO tituloReceberBO = new TituloReceberBO();
    //colecao titulos a pagar
    private List<TituloPagarModel> lTitulosPagar = new ArrayList<TituloPagarModel>();
    private List<TituloPagarBaixaModel> lPagos = new ArrayList<TituloPagarBaixaModel>();
    private List<TituloPagarBaixaFormaPagamentoModel> lForma = new ArrayList<TituloPagarBaixaFormaPagamentoModel>();
    private List<TituloPagarBaixaChequeModel> lCheques = new ArrayList<TituloPagarBaixaChequeModel>();
    private List<TituloPagarBaixaDeducaoModel> lDeducao = new ArrayList<TituloPagarBaixaDeducaoModel>();
    private List<TituloPagarBaixaAcrescimoModel> lAcrescimo = new ArrayList<TituloPagarBaixaAcrescimoModel>();
    private List<TituloPagarBaixaInternetModel> lInternet = new ArrayList<TituloPagarBaixaInternetModel>();
    private List<ContaBancariaDebitoModel> lContaBancoDebito = new ArrayList<ContaBancariaDebitoModel>();
    private List<TesourariaDebitoModel> lTesourariaDebito = new ArrayList<TesourariaDebitoModel>();
    private List<LotePagamentoTituloModel> lLote = new ArrayList<LotePagamentoTituloModel>();
    private List<SacadoModel> lSacados = new ArrayList<SacadoModel>();
    private List<CedenteModel> lCedentes = new ArrayList<CedenteModel>();
    private List<BancoModel> lBancos = new ArrayList<BancoModel>();
    private List<ContaContabilModel> lContaContabeis = new ArrayList<ContaContabilModel>();
    
    

    String data = PempecParse.dateToString(Controller.getDataServidorBD());

    public ManutencaoBancoDados() throws SystemException {

        this.setLocation(250, 50);
        Controller.setQtdMovimentosHoje(0);
        initComponents();

        jLabeldata.setText(data);
        panelMovDiario.setVisible(false);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abaContaContabil = new javax.swing.JTabbedPane();
        abaBasica = new javax.swing.JPanel();
        jPSenha = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jFSenha = new javax.swing.JPasswordField();
        jLabeldata = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        botaoAutenticar = new javax.swing.JButton();
        botaoFechar1 = new javax.swing.JButton();
        jPLiberada = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        abaDelContaBancaria = new javax.swing.JPanel();
        btnDelCtaBcoCredito = new javax.swing.JButton();
        btnDelCtaBcoDebito = new javax.swing.JButton();
        btnDelCtaBcoTransferencia = new javax.swing.JButton();
        btnDelContaBancaria = new javax.swing.JButton();
        btnZeraQtdImpCheque = new javax.swing.JButton();
        abaDelLoteContabil = new javax.swing.JPanel();
        btnDelLoteContabil = new javax.swing.JButton();
        abaDelMovDiario = new javax.swing.JPanel();
        btnDelMovDiario = new javax.swing.JButton();
        btnDelMovDiarioPeriodo = new javax.swing.JButton();
        panelMovDiario = new javax.swing.JPanel();
        jFtDataInicio = new org.jdesktop.swingx.JXDatePicker();
        jFtDataFinal = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        btnDeletarMovDiario = new javax.swing.JButton();
        abaDelTitReceber = new javax.swing.JPanel();
        btnDelTitRecebidos = new javax.swing.JButton();
        btnDelTitReceber = new javax.swing.JButton();
        jFCorrigeProvisaoTR = new javax.swing.JButton();
        abaDelTituPagar = new javax.swing.JPanel();
        btnDelTitPagar = new javax.swing.JButton();
        btnDelTitPagos = new javax.swing.JButton();
        jFCorrigeProvisaoTP = new javax.swing.JButton();
        abaTabelas = new javax.swing.JPanel();
        btnCaixaAltaSacados = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnCaixaAltaCedentes = new javax.swing.JButton();
        btnCaixaAltaBancos = new javax.swing.JButton();
        btnCaixaAltaContaContabil = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Manutenção do Banco de Dados");

        abaContaContabil.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)), NameObject()));

        abaBasica.setBackground(new java.awt.Color(153, 153, 153));
        abaBasica.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Autenticação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(102, 204, 255)));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setText("Digite a senha para função especial");

        jFSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFSenhaKeyPressed(evt);
            }
        });

        jLabeldata.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabeldata.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoAutenticar.setMnemonic('I');
        botaoAutenticar.setText("Autenticar");
        botaoAutenticar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAutenticarActionPerformed(evt);
            }
        });

        botaoFechar1.setMnemonic('F');
        botaoFechar1.setText("Fechar");
        botaoFechar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFechar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoAutenticar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAutenticar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPSenhaLayout = new javax.swing.GroupLayout(jPSenha);
        jPSenha.setLayout(jPSenhaLayout);
        jPSenhaLayout.setHorizontalGroup(
            jPSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPSenhaLayout.createSequentialGroup()
                .addContainerGap(122, Short.MAX_VALUE)
                .addGroup(jPSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabeldata, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(92, 92, 92))
        );
        jPSenhaLayout.setVerticalGroup(
            jPSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPSenhaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(28, 28, 28)
                .addComponent(jFSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabeldata, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Funções Liberadas!");

        javax.swing.GroupLayout jPLiberadaLayout = new javax.swing.GroupLayout(jPLiberada);
        jPLiberada.setLayout(jPLiberadaLayout);
        jPLiberadaLayout.setHorizontalGroup(
            jPLiberadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPLiberadaLayout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        jPLiberadaLayout.setVerticalGroup(
            jPLiberadaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPLiberadaLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout abaBasicaLayout = new javax.swing.GroupLayout(abaBasica);
        abaBasica.setLayout(abaBasicaLayout);
        abaBasicaLayout.setHorizontalGroup(
            abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaBasicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPLiberada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        abaBasicaLayout.setVerticalGroup(
            abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaBasicaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPLiberada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        abaContaContabil.addTab("Autenticação", abaBasica);

        abaDelContaBancaria.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 51, 0))));
        abaDelContaBancaria.setPreferredSize(new java.awt.Dimension(620, 460));

        btnDelCtaBcoCredito.setText("Deletar Conta Bancaria Credito");
        btnDelCtaBcoCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelCtaBcoCreditoActionPerformed(evt);
            }
        });

        btnDelCtaBcoDebito.setText("Deletar Conta Bancaria Debito");
        btnDelCtaBcoDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelCtaBcoDebitoActionPerformed(evt);
            }
        });

        btnDelCtaBcoTransferencia.setText("Deletar Conta Bancaria Transferencia");
        btnDelCtaBcoTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelCtaBcoTransferenciaActionPerformed(evt);
            }
        });

        btnDelContaBancaria.setText("Deletar Conta Bancaria");
        btnDelContaBancaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelContaBancariaActionPerformed(evt);
            }
        });

        btnZeraQtdImpCheque.setText("Zerar Impressão de Cheques");
        btnZeraQtdImpCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnZeraQtdImpChequeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaDelContaBancariaLayout = new javax.swing.GroupLayout(abaDelContaBancaria);
        abaDelContaBancaria.setLayout(abaDelContaBancariaLayout);
        abaDelContaBancariaLayout.setHorizontalGroup(
            abaDelContaBancariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDelContaBancariaLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(abaDelContaBancariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnZeraQtdImpCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelCtaBcoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelCtaBcoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelCtaBcoTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        abaDelContaBancariaLayout.setVerticalGroup(
            abaDelContaBancariaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDelContaBancariaLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnDelCtaBcoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDelCtaBcoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelCtaBcoTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnZeraQtdImpCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        abaContaContabil.addTab("Conta Bancaria", abaDelContaBancaria);

        abaDelLoteContabil.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 51, 0))));
        abaDelLoteContabil.setPreferredSize(new java.awt.Dimension(620, 460));

        btnDelLoteContabil.setText("Deletar Lote Contabil");
        btnDelLoteContabil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelLoteContabilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaDelLoteContabilLayout = new javax.swing.GroupLayout(abaDelLoteContabil);
        abaDelLoteContabil.setLayout(abaDelLoteContabilLayout);
        abaDelLoteContabilLayout.setHorizontalGroup(
            abaDelLoteContabilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDelLoteContabilLayout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(btnDelLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        abaDelLoteContabilLayout.setVerticalGroup(
            abaDelLoteContabilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDelLoteContabilLayout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(btnDelLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
        );

        abaContaContabil.addTab("Lote Contabil", abaDelLoteContabil);

        abaDelMovDiario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 51, 0))));
        abaDelMovDiario.setPreferredSize(new java.awt.Dimension(620, 460));

        btnDelMovDiario.setText("Deletar Todos Registros");
        btnDelMovDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelMovDiarioActionPerformed(evt);
            }
        });

        btnDelMovDiarioPeriodo.setText("Deletar Registros Por Periodo");
        btnDelMovDiarioPeriodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelMovDiarioPeriodoActionPerformed(evt);
            }
        });

        panelMovDiario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Período"));

        jLabel1.setText("Inicio");

        jLabel2.setText("Final");

        javax.swing.GroupLayout panelMovDiarioLayout = new javax.swing.GroupLayout(panelMovDiario);
        panelMovDiario.setLayout(panelMovDiarioLayout);
        panelMovDiarioLayout.setHorizontalGroup(
            panelMovDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovDiarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMovDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMovDiarioLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(panelMovDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFtDataFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelMovDiarioLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel2)
                        .addGap(46, 46, 46)))
                .addContainerGap())
        );
        panelMovDiarioLayout.setVerticalGroup(
            panelMovDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMovDiarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMovDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelMovDiarioLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFtDataFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMovDiarioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(28, 28, 28))
                    .addComponent(jFtDataInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoFechar.setMnemonic('F');
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        btnDeletarMovDiario.setMnemonic('D');
        btnDeletarMovDiario.setText("Deletar");
        btnDeletarMovDiario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarMovDiarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnDeletarMovDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeletarMovDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout abaDelMovDiarioLayout = new javax.swing.GroupLayout(abaDelMovDiario);
        abaDelMovDiario.setLayout(abaDelMovDiarioLayout);
        abaDelMovDiarioLayout.setHorizontalGroup(
            abaDelMovDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDelMovDiarioLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(abaDelMovDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaDelMovDiarioLayout.createSequentialGroup()
                        .addComponent(panelMovDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(abaDelMovDiarioLayout.createSequentialGroup()
                        .addGroup(abaDelMovDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnDelMovDiarioPeriodo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                            .addComponent(btnDelMovDiario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                        .addGap(78, 78, 78))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaDelMovDiarioLayout.createSequentialGroup()
                .addContainerGap(135, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        abaDelMovDiarioLayout.setVerticalGroup(
            abaDelMovDiarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDelMovDiarioLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btnDelMovDiario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDelMovDiarioPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(panelMovDiario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        abaContaContabil.addTab("Movimento Diario", abaDelMovDiario);

        abaDelTitReceber.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 51, 0))));
        abaDelTitReceber.setPreferredSize(new java.awt.Dimension(620, 460));

        btnDelTitRecebidos.setText("Deletar Todos Titulos Recebidos");
        btnDelTitRecebidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelTitRecebidosActionPerformed(evt);
            }
        });

        btnDelTitReceber.setText("Deletar Todos Titulos a Receber");
        btnDelTitReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelTitReceberActionPerformed(evt);
            }
        });

        jFCorrigeProvisaoTR.setText("Corrgir Registro Provisão");
        jFCorrigeProvisaoTR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFCorrigeProvisaoTRActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaDelTitReceberLayout = new javax.swing.GroupLayout(abaDelTitReceber);
        abaDelTitReceber.setLayout(abaDelTitReceberLayout);
        abaDelTitReceberLayout.setHorizontalGroup(
            abaDelTitReceberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDelTitReceberLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addGroup(abaDelTitReceberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFCorrigeProvisaoTR, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelTitReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelTitRecebidos, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        abaDelTitReceberLayout.setVerticalGroup(
            abaDelTitReceberLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDelTitReceberLayout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(btnDelTitRecebidos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52)
                .addComponent(btnDelTitReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jFCorrigeProvisaoTR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        abaContaContabil.addTab("Títulos a Receber", abaDelTitReceber);

        abaDelTituPagar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 0))));
        abaDelTituPagar.setPreferredSize(new java.awt.Dimension(620, 460));

        btnDelTitPagar.setText("Deletar Todos Titulos a Pagar");
        btnDelTitPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelTitPagarActionPerformed(evt);
            }
        });

        btnDelTitPagos.setText("Deletar Todos Titulos Pagos");
        btnDelTitPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelTitPagosActionPerformed(evt);
            }
        });

        jFCorrigeProvisaoTP.setText("Corrgir Registro Provisão");
        jFCorrigeProvisaoTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFCorrigeProvisaoTPActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaDelTituPagarLayout = new javax.swing.GroupLayout(abaDelTituPagar);
        abaDelTituPagar.setLayout(abaDelTituPagarLayout);
        abaDelTituPagarLayout.setHorizontalGroup(
            abaDelTituPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDelTituPagarLayout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addGroup(abaDelTituPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDelTitPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelTitPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFCorrigeProvisaoTP, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        abaDelTituPagarLayout.setVerticalGroup(
            abaDelTituPagarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDelTituPagarLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(btnDelTitPagos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(btnDelTitPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jFCorrigeProvisaoTP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(109, Short.MAX_VALUE))
        );

        abaContaContabil.addTab("Títulos a Pagar", abaDelTituPagar);

        btnCaixaAltaSacados.setText("Sacados");
        btnCaixaAltaSacados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaixaAltaSacadosActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 102, 102));
        jLabel5.setText("Transformar em caixa alta.");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnCaixaAltaCedentes.setText("Cedentes");
        btnCaixaAltaCedentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaixaAltaCedentesActionPerformed(evt);
            }
        });

        btnCaixaAltaBancos.setText("Bancos");
        btnCaixaAltaBancos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaixaAltaBancosActionPerformed(evt);
            }
        });

        btnCaixaAltaContaContabil.setText("Contas Contabeis");
        btnCaixaAltaContaContabil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCaixaAltaContaContabilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaTabelasLayout = new javax.swing.GroupLayout(abaTabelas);
        abaTabelas.setLayout(abaTabelasLayout);
        abaTabelasLayout.setHorizontalGroup(
            abaTabelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaTabelasLayout.createSequentialGroup()
                .addGroup(abaTabelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaTabelasLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(abaTabelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCaixaAltaBancos, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCaixaAltaSacados, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCaixaAltaContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaTabelasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(abaTabelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(abaTabelasLayout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(btnCaixaAltaCedentes, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                    .addGap(164, 164, 164)))
        );
        abaTabelasLayout.setVerticalGroup(
            abaTabelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaTabelasLayout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel5)
                .addGap(70, 70, 70)
                .addComponent(btnCaixaAltaSacados, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCaixaAltaBancos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCaixaAltaContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(abaTabelasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(abaTabelasLayout.createSequentialGroup()
                    .addGap(56, 56, 56)
                    .addComponent(btnCaixaAltaCedentes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(299, Short.MAX_VALUE)))
        );

        abaContaContabil.addTab("Manutenção Tabelas", abaTabelas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(abaContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(abaContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Banco de Dados";
    }

    public void repopularCombos() {

        protegeAba(abaBasica, true);
        protegeAba(abaDelContaBancaria, false);
        protegeAba(abaDelLoteContabil, false);
        protegeAba(abaDelMovDiario, false);
        protegeAba(abaDelTitReceber, false);
        protegeAba(abaDelTituPagar, false);
        protegeAba(abaTabelas, false);

        jPLiberada.setVisible(false);
        botaoAutenticar.setVisible(true);
        jPSenha.setVisible(true);

        try {
            Runtime.getRuntime().exec("osk");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

        private void btnDelTitRecebidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelTitRecebidosActionPerformed
            exibeMensagemAviso("Em desenvolvimento. Aguarde!", null);
        }//GEN-LAST:event_btnDelTitRecebidosActionPerformed

        private void btnDelTitPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelTitPagosActionPerformed
            try {
                lTitulosPagar = tituloPagarBO.obterTodosPagos(organizacaoModel);
                lPagos = tituloPagarBaixaBO.obterTodos(organizacaoModel);
                lForma = new TituloPagarBaixaFormaPagamentoBO().obterTodos(organizacaoModel);
                lCheques = new TituloPagarBaixaChequeBO().obterTodos(organizacaoModel);
                lDeducao = new TituloPagarBaixaDeducaoBO().obterTodos(organizacaoModel);
                lAcrescimo = new TituloPagarBaixaAcrescimoBO().obterTodos(organizacaoModel);
                lInternet = new TituloPagarBaixaInternetBO().obterTodos(organizacaoModel);
                lContaBancoDebito = new ContaBancariaDebitoBO().obterTodosTitulosPagos(organizacaoModel);
                lTesourariaDebito = new TesourariaDebitoBO().obterTodosTitulosPagos(organizacaoModel);
                lLote = new LotePagamentoTituloBO().obterTodos(organizacaoModel);

                if (lInternet.size() > 0) {
                    new TituloPagarBaixaInternetBO().excluirTodos(lInternet);
                    exibeMensagemAviso("Foram excluidos " + lInternet.size() + "lançamentos internet.", null);
                }

                if (lAcrescimo.size() > 0) {
                    new TituloPagarBaixaAcrescimoBO().excluirTodos(lAcrescimo);
                    exibeMensagemAviso("Foram excluidos " + lAcrescimo.size() + " lançamentos acrescimos.", null);
                }

                if (lDeducao.size() > 0) {
                    new TituloPagarBaixaDeducaoBO().excluirTodos(lDeducao);
                    exibeMensagemAviso("Foram excluidos " + lDeducao.size() + " lançamentos deduções.", null);
                }

                if (lCheques.size() > 0) {
                    new TituloPagarBaixaChequeBO().excluirTodos(lCheques);
                    exibeMensagemAviso("Foram excluidos " + lCheques.size() + " lançamentos cheques.", null);
                }

                if (lForma.size() > 0) {
                    new TituloPagarBaixaFormaPagamentoBO().excluirTodos(lForma);
                    exibeMensagemAviso("Foram excluidos " + lForma.size() + " lançamentos formas.", null);
                }

                if (lPagos.size() > 0) {
                    tituloPagarBaixaBO.excluirTodos(lPagos);
                    exibeMensagemAviso("Foram excluidos " + lPagos.size() + " lançamentos pagos.", null);
                }

                if (lContaBancoDebito.size() > 0) {
                    new ContaBancariaDebitoBO().excluirTodos(lContaBancoDebito);
                    exibeMensagemAviso("Foram excluidos " + lContaBancoDebito.size() + " lançamentos conta.", null);
                }

                if (lTesourariaDebito.size() > 0) {
                    new TesourariaDebitoBO().excluirTodos(lTesourariaDebito);
                    exibeMensagemAviso("Foram excluidos " + lTesourariaDebito.size() + " lançamentos tesouraria.", null);
                }

                if (lTitulosPagar.size() > 0) {
                    tituloPagarBO.excluirTodos(lTitulosPagar);
                    exibeMensagemAviso("Foram excluidos " + lTitulosPagar.size() + " lançamentos titulos.", null);
                }

                if (lLote.size() > 0) {
                    new LotePagamentoTituloBO().excluirTodos(lLote);
                    exibeMensagemAviso("Foram excluidos " + lLote.size() + " lançamentos lote.", null);
                }

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
        }//GEN-LAST:event_btnDelTitPagosActionPerformed

        private void btnDelTitReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelTitReceberActionPerformed
            //obter todos titulos a receber e apagar tudo.
            try {
                List<TituloReceberModel> listaTitulos = new ArrayList<TituloReceberModel>();

                listaTitulos.addAll(tituloReceberBO.obterTodosAReceber(organizacaoModel));

                if (listaTitulos.size() == 0) {
                    exibeMensagemAviso("Lista vazia.", null);
                }

                if (listaTitulos.size() > 0) {
                    tituloReceberBO.excluirTodos(listaTitulos);
                    exibeMensagemAviso("Foram excluídos " + listaTitulos.size() + " títulos a receber.", null);
                }

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
        }//GEN-LAST:event_btnDelTitReceberActionPerformed

        private void btnDelTitPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelTitPagarActionPerformed
            try {
                lTitulosPagar.addAll(tituloPagarBO.obterTodosAPagar(organizacaoModel));

                if (lTitulosPagar.size() == 0) {
                    exibeMensagemAviso("Lista vazia.", null);
                }

                if (lTitulosPagar.size() > 0) {

                    tituloPagarBO.excluirTodos(lTitulosPagar);
                    exibeMensagemAviso("Foram excluídos " + lTitulosPagar.size() + " titulos a pagar.\nTodos os rateios foram  excluidos.", null);
                }

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
        }//GEN-LAST:event_btnDelTitPagarActionPerformed

        private void btnDelMovDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelMovDiarioActionPerformed

            abaDelMovDiario.setEnabled(false);
            try {
                List<MovimentoDiarioModel> listaMov = new ArrayList<MovimentoDiarioModel>();

                listaMov = new MovimentoDiarioBO().obterTodos(organizacaoModel);

                if (listaMov.size() > 0) {

                    new MovimentoDiarioBO().excluirTodos(listaMov);
                    exibeMensagemAviso("Foram excluídos " + listaMov.size() + " registros diários.", null);
                    abaDelMovDiario.setEnabled(true);
                } else {
                    exibeMensagemAviso("Não existem registros diários a serem excluidos.", null);
                    abaDelMovDiario.setEnabled(true);
                }

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

        }//GEN-LAST:event_btnDelMovDiarioActionPerformed

        private void btnDelMovDiarioPeriodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelMovDiarioPeriodoActionPerformed
            panelMovDiario.setVisible(true);
            btnDelMovDiario.setEnabled(false);
            btnDelMovDiarioPeriodo.setEnabled(false);
            btnDeletarMovDiario.setEnabled(true);


        }//GEN-LAST:event_btnDelMovDiarioPeriodoActionPerformed

        private void btnDeletarMovDiarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarMovDiarioActionPerformed

            btnDeletarMovDiario.setEnabled(false);

            try {
                List<MovimentoDiarioModel> listaMov = new ArrayList<MovimentoDiarioModel>();

                if (jFtDataInicio.getDate() == null || jFtDataFinal.getDate() == null) {
                    exibeMensagemAviso("É preciso digitar o período inicial e final.", null);
                    return;
                }

                listaMov = new MovimentoDiarioBO().obterTodosPorPeriodo(organizacaoModel, jFtDataInicio.getDate(), jFtDataFinal.getDate());

                if (listaMov.size() > 0) {

                    new MovimentoDiarioBO().excluirTodos(listaMov);
                    exibeMensagemAviso("Foram excluídos " + listaMov.size() + " registros diários.", null);
                    btnDeletarMovDiario.setEnabled(true);

                } else {
                    exibeMensagemAviso("Não existem registros diários a serem excluidos.", null);
                    btnDeletarMovDiario.setEnabled(true);
                }

                panelMovDiario.setVisible(false);

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
        }//GEN-LAST:event_btnDeletarMovDiarioActionPerformed

        private void btnDelCtaBcoCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelCtaBcoCreditoActionPerformed
            try {
                List<ContaBancariaCreditoModel> lista = new ArrayList<ContaBancariaCreditoModel>();

                lista = new ContaBancariaCreditoBO().obterTodos(organizacaoModel);

                if (lista.size() > 0) {

                    new ContaBancariaCreditoBO().excluirTodos(lista);

                    exibeMensagemAviso("Foram excluídos " + lista.size() + " lançamentos.", null);

                } else {
                    exibeMensagemAviso("Não existem lançamentos a serem excluidos.", null);
                }

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
        }//GEN-LAST:event_btnDelCtaBcoCreditoActionPerformed

        private void btnDelCtaBcoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelCtaBcoDebitoActionPerformed
            try {
                List<ContaBancariaDebitoModel> lista = new ArrayList<ContaBancariaDebitoModel>();

                lista = new ContaBancariaDebitoBO().obterTodos(organizacaoModel);

                if (lista.size() > 0) {

                    new ContaBancariaDebitoBO().excluirTodos(lista);

                    exibeMensagemAviso("Foram excluídos " + lista.size() + " lançamentos.", null);
                } else {
                    exibeMensagemAviso("Não existem lançamentos a serem excluidos.", null);
                }

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
        }//GEN-LAST:event_btnDelCtaBcoDebitoActionPerformed

        private void btnDelCtaBcoTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelCtaBcoTransferenciaActionPerformed
            try {
                List<ContaBancariaTransferenciaModel> lista = new ArrayList<ContaBancariaTransferenciaModel>();

                lista = new ContaBancariaTransferenciaBO().obterTodos(organizacaoModel);

                if (lista.size() > 0) {

                    new ContaBancariaTransferenciaBO().excluirTodos(lista);

                    exibeMensagemAviso("Foram excluídos " + lista.size() + " lançamentos.", null);
                } else {
                    exibeMensagemAviso("Não existem lançamentos a serem excluidos.", null);
                }

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
        }//GEN-LAST:event_btnDelCtaBcoTransferenciaActionPerformed

        private void btnDelContaBancariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelContaBancariaActionPerformed
            try {
                List<ContaBancariaModel> lista = new ArrayList<ContaBancariaModel>();

                lista = new ContaBancariaBO().obterTodos(organizacaoModel);

                if (lista.size() > 0) {

                    new ContaBancariaBO().excluirTodos(lista);

                    exibeMensagemAviso("Foram excluídas " + lista.size() + " contas.", null);
                } else {
                    exibeMensagemAviso("Não existem lançamentos a serem excluidos.", null);
                }

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
        }//GEN-LAST:event_btnDelContaBancariaActionPerformed

        private void btnDelLoteContabilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelLoteContabilActionPerformed
            try {
                List<LoteContabilModel> lista = new ArrayList<LoteContabilModel>();
                List<TituloPagarModel> listaTitulo = new ArrayList<TituloPagarModel>();

                lTitulosPagar = tituloPagarBO.obterTodos(organizacaoModel);

                for (TituloPagarModel tituloPagarModel : lTitulosPagar) {

                    tituloPagarModel.setLoteContabil(null);
                    listaTitulo.add(tituloPagarModel);

                }

                new MegaContabilBO().removerLote(lTitulosPagar, new TituloPagarBaixaBO().obterTodos(organizacaoModel), new TituloReceberBO().obterTodos(organizacaoModel), new TituloReceberBaixaBO().obterTodos(organizacaoModel), new TesourariaDebitoBO().obterTodos(organizacaoModel), new TesourariaCreditoBO().obterTodos(organizacaoModel), new ContaBancariaDebitoBO().obterTodos(organizacaoModel), new ContaBancariaCreditoBO().obterTodos(organizacaoModel), new ContaBancariaTransferenciaBO().obterTodos(organizacaoModel), null);

                lista = new LoteContabilBO().obterTodos(organizacaoModel);

                if (lista.size() > 0) {

                    new LoteContabilBO().excluirTodos(lista);

                    exibeMensagemAviso("Foram excluídos " + lista.size() + " lançamentos.", null);
                } else {
                    exibeMensagemAviso("Não existem lançamentos a serem excluidos.", null);
                }

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
        }//GEN-LAST:event_btnDelLoteContabilActionPerformed

        private void jFCorrigeProvisaoTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFCorrigeProvisaoTPActionPerformed

            try {

                tituloPagarBO.corrigeRegistroProvisao();

                exibeMensagemAviso("Corrigido com Sucesso!", "Manutenção BD");

            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            }

        }//GEN-LAST:event_jFCorrigeProvisaoTPActionPerformed

        private void jFCorrigeProvisaoTRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFCorrigeProvisaoTRActionPerformed

            try {

                tituloReceberBO.corrigeRegistroProvisao();

                exibeMensagemAviso("Corrigido com Sucesso!", "Manutenção BD");

            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            }

        }//GEN-LAST:event_jFCorrigeProvisaoTRActionPerformed

        private void btnZeraQtdImpChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnZeraQtdImpChequeActionPerformed
            try {

                new ContaBancariaChequeBO().zeraQtdImpressaoCheque();

                exibeMensagemAviso("Corrigido com Sucesso!\n Qtd zerada para os nulos", "Cheque");

            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            }


        }//GEN-LAST:event_btnZeraQtdImpChequeActionPerformed

        private void botaoAutenticarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAutenticarActionPerformed

            if (jFSenha.getPassword() != null) {

                String senha = new String(jFSenha.getPassword()).toLowerCase();

                String[] dataAux = data.split("/");

                if (senha.equalsIgnoreCase(dataAux[0] + dataAux[1])) {

                    protegeAba(abaBasica, false);
                    jFSenha.setVisible(false);
                    botaoAutenticar.setVisible(false);
                    jPLiberada.setVisible(true);
                    jPSenha.setVisible(false);

                    protegeAba(abaDelContaBancaria, true);
                    protegeAba(abaDelLoteContabil, true);
                    protegeAba(abaDelMovDiario, true);
                    protegeAba(abaDelTitReceber, true);
                    protegeAba(abaDelTituPagar, true);
                    protegeAba(abaTabelas, true);
                    

                } else {

                    exibeMensagemAviso("Senha Incorreta!", "Senha");

                    jFSenha.setText("");

                }
            } else {

                exibeMensagemAviso("Digita a senha para função especial", "Senha");
            }


        }//GEN-LAST:event_botaoAutenticarActionPerformed

        private void jFSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFSenhaKeyPressed

            if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
                botaoAutenticar.doClick();
            }

        }//GEN-LAST:event_jFSenhaKeyPressed

        private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
            setVisible(false);
}//GEN-LAST:event_botaoFecharActionPerformed

        private void botaoFechar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFechar1ActionPerformed
            setVisible(false);
        }//GEN-LAST:event_botaoFechar1ActionPerformed

    private void btnCaixaAltaSacadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaixaAltaSacadosActionPerformed
        
        try {
        
            lSacados = sacadoBO.obterTodos(organizacaoModel);
            
            if(lSacados.size() >0){
                
                jLabel5.setText("Transformando em caixa alta. Aguarde...");
                
                for (SacadoModel sacadoModel : lSacados) {
                    
                    String nome = sacadoModel.getNome().toUpperCase();
                    
                    SacadoModel tab = sacadoBO.consultarPorTemplate(sacadoModel);
                    
                    if(tab.getPk() != null ){
                        
                        tab.setNome(nome);
                        
                        sacadoBO.alterar(tab);                    
                        
                    }
                                       
                }
                
                exibeMensagemAviso("Processo Concluido Foram alterados " + lSacados.size() + " registros.", "Sacado");
                jLabel5.setText("Transforma em caixa alta. ");
                
            } else {
                jLabel5.setText("Transforma em caixa alta. ");
                exibeMensagemAviso("Lista Vazia", "Sacado");
            }
            
            
        
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
    
// TODO add your handling code here:
    }//GEN-LAST:event_btnCaixaAltaSacadosActionPerformed

    private void btnCaixaAltaCedentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaixaAltaCedentesActionPerformed
       
 
        try {
        
            lCedentes = cedenteBO.obterTodos(organizacaoModel);
            
            if(lCedentes.size() >0){
                
                jLabel5.setText("Transformando em caixa alta. Aguarde...");
                
                for (CedenteModel cedenteModel : lCedentes) {
                    
                    String nome = cedenteModel.getNome().toUpperCase();
                    
                    CedenteModel tab = cedenteBO.consultarPorTemplate(cedenteModel);
                    
                    if(tab.getPk() != null ){
                        
                        tab.setNome(nome);
                        
                        cedenteBO.alterar(tab);                    
                        
                    }
                                       
                }
                
                exibeMensagemAviso("Processo Concluido. Foram alterados " + lCedentes.size() + " registros.", "Cedente");
                jLabel5.setText("Transforma em caixa alta. ");
                
            } else {
                jLabel5.setText("Transforma em caixa alta. ");
                exibeMensagemAviso("Lista Vazia", "Cedente");
            }
            
            
        
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

// TODO add your handling code here:
    }//GEN-LAST:event_btnCaixaAltaCedentesActionPerformed

    private void btnCaixaAltaBancosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaixaAltaBancosActionPerformed
       

        try {
        
            lBancos = bancoBO.obterTodos();
            
            if(lBancos.size() >0){
                
                jLabel5.setText("Transformando em caixa alta. Aguarde...");
                
                for (BancoModel bancoModel : lBancos) {
                    
                    String nome = bancoModel.getNomeBanco().toUpperCase();
                    
                    BancoModel tab = bancoBO.consultarPorTemplate(bancoModel);
                    
                    if(tab.getId() != null ){
                        
                        tab.setNomeBanco(nome);
                        
                        bancoBO.alterar(tab);                    
                        
                    }
                                       
                }
                
                exibeMensagemAviso("Processo Concluido. Foram alterados " + lBancos.size() + " registros.", "Banco");
                jLabel5.setText("Transforma em caixa alta. ");
                
            } else {
                jLabel5.setText("Transforma em caixa alta. ");
                exibeMensagemAviso("Lista Vazia", "Banco");
            }
            
            
        
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

// TODO add your handling code here:
    }//GEN-LAST:event_btnCaixaAltaBancosActionPerformed

    private void btnCaixaAltaContaContabilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCaixaAltaContaContabilActionPerformed

        
        try {
        
            lContaContabeis = contaContabilBO.obterTodos(organizacaoModel);
            
            if(lContaContabeis.size() >0){
                
                jLabel5.setText("Transformando em caixa alta. Aguarde...");
                
                for (ContaContabilModel contaContabilModel : lContaContabeis) {
                    
                    String nome = contaContabilModel.getDescricao().toUpperCase();
                    
                    ContaContabilModel tab = contaContabilBO.consultarPorTemplate(contaContabilModel);
                    
                    if(tab.getPk() != null ){
                        
                        tab.setDescricao(nome);
                        
                        contaContabilBO.alterar(tab);                    
                        
                    }
                                       
                }
                
                exibeMensagemAviso("Processo Concluido. Foram alterados " + lContaContabeis.size() + " registros.", "Contas Contabeis");
                jLabel5.setText("Transforma em caixa alta. ");
                
            } else {
                jLabel5.setText("Transforma em caixa alta. ");
                exibeMensagemAviso("Lista Vazia", "Contas Contabeis");
            }
            
            
        
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
// TODO add your handling code here:
    }//GEN-LAST:event_btnCaixaAltaContaContabilActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaBasica;
    private javax.swing.JTabbedPane abaContaContabil;
    private javax.swing.JPanel abaDelContaBancaria;
    private javax.swing.JPanel abaDelLoteContabil;
    private javax.swing.JPanel abaDelMovDiario;
    private javax.swing.JPanel abaDelTitReceber;
    private javax.swing.JPanel abaDelTituPagar;
    private javax.swing.JPanel abaTabelas;
    private javax.swing.JButton botaoAutenticar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoFechar1;
    private javax.swing.JButton btnCaixaAltaBancos;
    private javax.swing.JButton btnCaixaAltaCedentes;
    private javax.swing.JButton btnCaixaAltaContaContabil;
    private javax.swing.JButton btnCaixaAltaSacados;
    private javax.swing.JButton btnDelContaBancaria;
    private javax.swing.JButton btnDelCtaBcoCredito;
    private javax.swing.JButton btnDelCtaBcoDebito;
    private javax.swing.JButton btnDelCtaBcoTransferencia;
    private javax.swing.JButton btnDelLoteContabil;
    private javax.swing.JButton btnDelMovDiario;
    private javax.swing.JButton btnDelMovDiarioPeriodo;
    private javax.swing.JButton btnDelTitPagar;
    private javax.swing.JButton btnDelTitPagos;
    private javax.swing.JButton btnDelTitReceber;
    private javax.swing.JButton btnDelTitRecebidos;
    private javax.swing.JButton btnDeletarMovDiario;
    private javax.swing.JButton btnZeraQtdImpCheque;
    private javax.swing.JButton jFCorrigeProvisaoTP;
    private javax.swing.JButton jFCorrigeProvisaoTR;
    private javax.swing.JPasswordField jFSenha;
    private org.jdesktop.swingx.JXDatePicker jFtDataFinal;
    private org.jdesktop.swingx.JXDatePicker jFtDataInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabeldata;
    private javax.swing.JPanel jPLiberada;
    private javax.swing.JPanel jPSenha;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panelMovDiario;
    // End of variables declaration//GEN-END:variables
}
