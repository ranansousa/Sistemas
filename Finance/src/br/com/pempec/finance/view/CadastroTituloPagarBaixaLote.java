package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.FormaPagamentoBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.LotePagamentoTituloBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.ReportPagamentoLote;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroTituloPagarBaixaFP;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Extenso;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopuladorNew;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.TesourariaServices;
import br.com.pempec.finance.utils.tables.TableModelCadastroTituloPagarBaixaFP;
import java.awt.FontMetrics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author EQUIPE PEMPEC
 */
public class CadastroTituloPagarBaixaLote extends FinanceInternalFrame implements IRepopuladorNew {

    private TituloPagarBaixaBO tituloPagarBaixaBO = new TituloPagarBaixaBO();
    private FormaPagamentoBO formaPagamentoBO = new FormaPagamentoBO();
    private FuncionarioBO responsavelBO = new FuncionarioBO();
    CedenteModel cedente = new CedenteModel();
    //Variavel que ira guardar os titulos do periodo selecionado.
    private Collection<TituloPagarModel> lTitulosPeriodo;
    private Double valorTotal = 0d;

    private String NameObject() {

        return (String) "Pagamento de Títulos";

    }

    public CadastroTituloPagarBaixaLote(Collection<TituloPagarModel> collTitulos, CedenteModel cedenteModel) throws SystemException {

        initComponents();

        this.populaTela();

        this.repopularCombos(Tela.TELA_PRINCIPAL, null);

        comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));

        lTitulosPeriodo = collTitulos;

        cedente = cedenteModel;

        jFTDataPagamento.setDate(Controller.getDataServidorBD());

        for (TituloPagarModel titulo : lTitulosPeriodo) {
            valorTotal += titulo.getValorNominal();
        }

        jFTValorTotal.setText(PempecParse.doubleToZero(valorTotal));
        jFTValorFormaPagamento.setText(PempecParse.doubleToZero(valorTotal));

        jTNumeroLote.setText(PempecUtil.geraNumeroLote());



    }

    private void populaTela() {

        initComponents();

        //Instanciando variaveis auxiliares
        auxFormasPagamento = new ArrayList<TituloPagarBaixaFormaPagamentoModel>();

        auxCheques = new ArrayList<TituloPagarBaixaChequeModel>();

        auxInternet = new ArrayList<TituloPagarBaixaInternetModel>();

        panelFormato.setVisible(false);
        botaoImprimir.setEnabled(false);

        //Iniciando as tabelas.
        this.preencheTabelaFormaPagamento();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelAcrescimo = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jFTDataPagamento = new org.jdesktop.swingx.JXDatePicker();
        labelCentroCusto1 = new javax.swing.JLabel();
        comboResponsavel = new javax.swing.JComboBox();
        labelValorPagar1 = new javax.swing.JLabel();
        jFTValorTotal = new br.com.pempec.componentes.JDoubleField();
        jTNumeroLote = new javax.swing.JFormattedTextField();
        labelValorPagar3 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboFormaPagamento = new javax.swing.JComboBox();
        labelValorPagar2 = new javax.swing.JLabel();
        jFTValorFormaPagamento = new br.com.pempec.componentes.JDoubleField();
        btnIncluirFormaPagamento = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableFormaPagamento = new javax.swing.JTable();
        btnRemoverFormaPagamento = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        botaoSalvar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        panelFormato = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - PAGAMENTO DE TÍTULOS - LOTE");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        panelAcrescimo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153)), NameObject(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 153, 255)));
        panelAcrescimo.setForeground(new java.awt.Color(153, 153, 153));

        jLabel4.setText("Data Pagamento");

        labelCentroCusto1.setText("Responsável");

        labelValorPagar1.setText("Valor a Pagar");

        jFTValorTotal.setEditable(false);

        jTNumeroLote.setBackground(new java.awt.Color(204, 204, 204));
        jTNumeroLote.setEditable(false);
        jTNumeroLote.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTNumeroLote.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTNumeroLote.setSelectionColor(new java.awt.Color(255, 153, 51));

        labelValorPagar3.setText("Lote Pagamento");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Forma de Pagamento"));

        jLabel1.setText("Forma");

        comboFormaPagamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFormaPagamentoItemStateChanged(evt);
            }
        });

        labelValorPagar2.setText("Valor");

        jFTValorFormaPagamento.setEditable(false);
        jFTValorFormaPagamento.setForeground(new java.awt.Color(0, 102, 255));

        btnIncluirFormaPagamento.setBackground(new java.awt.Color(153, 153, 255));
        btnIncluirFormaPagamento.setText("Incluir");
        btnIncluirFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirFormaPagamentoActionPerformed(evt);
            }
        });

        tableFormaPagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableFormaPagamento);

        btnRemoverFormaPagamento.setBackground(new java.awt.Color(153, 153, 255));
        btnRemoverFormaPagamento.setText("Remover");
        btnRemoverFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverFormaPagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(comboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jFTValorFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(102, 102, 102)
                                .addComponent(btnIncluirFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelValorPagar2)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(btnRemoverFormaPagamento)))
                .addContainerGap(97, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 14, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelValorPagar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTValorFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIncluirFormaPagamento)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(btnRemoverFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Pagamento", jPanel4);

        javax.swing.GroupLayout panelAcrescimoLayout = new javax.swing.GroupLayout(panelAcrescimo);
        panelAcrescimo.setLayout(panelAcrescimoLayout);
        panelAcrescimoLayout.setHorizontalGroup(
            panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAcrescimoLayout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 689, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelAcrescimoLayout.createSequentialGroup()
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jFTDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCentroCusto1)
                            .addComponent(comboResponsavel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelValorPagar1))
                        .addGap(18, 18, 18)
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelValorPagar3)
                            .addComponent(jTNumeroLote, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))))
        );
        panelAcrescimoLayout.setVerticalGroup(
            panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelAcrescimoLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(31, 31, 31))
                    .addComponent(jFTDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAcrescimoLayout.createSequentialGroup()
                        .addComponent(labelCentroCusto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAcrescimoLayout.createSequentialGroup()
                            .addComponent(labelValorPagar1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFTValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(panelAcrescimoLayout.createSequentialGroup()
                            .addComponent(labelValorPagar3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jTNumeroLote, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoSalvar.setMnemonic('P');
        botaoSalvar.setText("Pagar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoFechar.setMnemonic('F');
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        botaoLimpar.setMnemonic('L');
        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoImprimir.setMnemonic('I');
        botaoImprimir.setText("Imprimir");
        botaoImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar, botaoSalvar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelFormato.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Formato"));

        javax.swing.GroupLayout panelFormatoLayout = new javax.swing.GroupLayout(panelFormato);
        panelFormato.setLayout(panelFormatoLayout);
        panelFormatoLayout.setHorizontalGroup(
            panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormatoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, 0, 284, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelFormatoLayout.setVerticalGroup(
            panelFormatoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFormatoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(panelFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelFormato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo utilizado para repopular todos os combos deste JinternalFrame.
     */
    public void repopularCombos(Tela tela, Object object) {

        try {


            switch (tela) {

                case TELA_PARAMETROS_FORMAS_DE_PAGAMENTOS:

                    Collection<FormaPagamentoModel> lColecaoFormaPagamento = new ArrayList<FormaPagamentoModel>();

                    FormaPagamentoModel formaPagamentoModel = new FormaPagamentoModel();
                    formaPagamentoModel.setDescricao(" -> Selecione <- ");
                    lColecaoFormaPagamento.add(formaPagamentoModel);
                    lColecaoFormaPagamento.addAll(formaPagamentoBO.obterTodos(organizacaoModel));
                    comboFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel(lColecaoFormaPagamento.toArray()));

                    formaPagamentoModel = (FormaPagamentoModel) object;

                    if (formaPagamentoModel != null) {

                        for (int i = 1; i < comboFormaPagamento.getItemCount(); i++) {
                            if (formaPagamentoModel.getPk().getId().equalsIgnoreCase(((FormaPagamentoModel) comboFormaPagamento.getItemAt(i)).getPk().getId())) {
                                comboFormaPagamento.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_FUNCIONARIOS:

                    Collection<FuncionarioModel> lColecaoResponsavel = new ArrayList<FuncionarioModel>();

                    FuncionarioModel funcionarioModel = new FuncionarioModel();
                    funcionarioModel.setNome(" -> Selecione <- ");
                    lColecaoResponsavel.add(funcionarioModel);
                    lColecaoResponsavel.addAll(responsavelBO.obterTodos(organizacaoModel));
                    comboResponsavel.setModel(new javax.swing.DefaultComboBoxModel(lColecaoResponsavel.toArray()));

                    funcionarioModel = (FuncionarioModel) object;

                    if (funcionarioModel != null) {

                        for (int i = 1; i < comboResponsavel.getItemCount(); i++) {
                            if (funcionarioModel.getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboResponsavel.getItemAt(i)).getPk().getId())) {
                                comboResponsavel.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                default:

                    lColecaoFormaPagamento = new ArrayList<FormaPagamentoModel>();
                    lColecaoResponsavel = new ArrayList<FuncionarioModel>();

                    //forma pagamento

                    formaPagamentoModel = new FormaPagamentoModel();
                    formaPagamentoModel.setDescricao(" -> Selecione <- ");
                    lColecaoFormaPagamento.add(formaPagamentoModel);
                    lColecaoFormaPagamento.addAll(formaPagamentoBO.obterTodos(organizacaoModel));
                    comboFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel(lColecaoFormaPagamento.toArray()));

                    //Responsavel

                    funcionarioModel = new FuncionarioModel();
                    funcionarioModel.setNome(" -> Selecione <- ");
                    lColecaoResponsavel.add(funcionarioModel);
                    lColecaoResponsavel.addAll(responsavelBO.obterTodos(organizacaoModel));
                    comboResponsavel.setModel(new javax.swing.DefaultComboBoxModel(lColecaoResponsavel.toArray()));

                    break;

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


    }

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        tableFormaPagamento.setEnabled(true);
        comboFormaPagamento.setEnabled(true);
        btnIncluirFormaPagamento.setEnabled(true);
        btnRemoverFormaPagamento.setEnabled(true);
        jFTDataPagamento.setEnabled(true);
        comboResponsavel.setEnabled(true);
        botaoSalvar.setEnabled(false);
        comboResponsavel.setSelectedIndex(0);
        jFTValorFormaPagamento.setVisible(true);
        panelFormato.setVisible(false);
        botaoImprimir.setEnabled(false);

        this.limparCamposBaixa();

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaParametros() {

        if (Controller.verificaParametroAtivo(Parametro.CBPL003.getCodigo())) {
            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPL003.getCodigo()), null);
            return false;
        }

        if (Controller.verificaParametroAtivo(Parametro.CBPL002.getCodigo())) {
            if (jFTValorTotal.getValue() > Controller.findByCodigo(Parametro.CBPL002.getCodigo()).getValor().doubleValue()) {
                exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPL002.getCodigo()), null);
                return false;
            }
        }

        if (Controller.verificaParametroAtivo(Parametro.CBPL005.getCodigo())) {
            if (jFTValorTotal.getValue() < Controller.findByCodigo(Parametro.CBPL005.getCodigo()).getValor().doubleValue()) {
                exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPL005.getCodigo()), null);
                return false;
            }
        }

        if (Controller.verificaParametroAtivo(Parametro.CBPL004.getCodigo())) {

            for (TituloPagarBaixaFormaPagamentoModel formaPagamento : auxFormasPagamento) {

                if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)
                        && !Controller.verificaParametroAtivo(Parametro.CBPL00401.getCodigo())) {
                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPL00401.getCodigo()), null);
                } else {

                    if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE)
                            && !Controller.verificaParametroAtivo(Parametro.CBPL00402.getCodigo())) {
                        exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPL00402.getCodigo()), null);
                    } else {

                        if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_INTERNET)
                                && !Controller.verificaParametroAtivo(Parametro.CBPL00403.getCodigo())) {
                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPL00403.getCodigo()), null);
                        }

                    }

                }

            }

        }


        return true;
    }

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed

        try {

            if (validaCampos()) {

                LotePagamentoTituloModel lote = new LotePagamentoTituloModel();
                LotePagamentoTituloBO lotePagamentoTituloBO = new LotePagamentoTituloBO();
                boolean aux = true;

                if (jTNumeroLote.getText() != null && !jTNumeroLote.getText().isEmpty()) {

                    String numeroLote = jTNumeroLote.getText();
                    lote.setPk(new PKModel());
                    lote.getPk().setOrganizacao(Controller.getOrganizacao());
                    lote.setLote(numeroLote);

                    int loteAux = 1;

                    while (aux) {

                        lote = lotePagamentoTituloBO.consultarPorTemplate(lote);

                        if (lote != null && lote.getLote().equalsIgnoreCase(jTNumeroLote.getText())) {
                            jTNumeroLote.setText(numeroLote + "0" + loteAux);
                            lote.setLote(jTNumeroLote.getText());
                            loteAux++;
                        } else {
                            aux = false;
                        }
                    }


                    lote = new LotePagamentoTituloModel();
                    lote.setPk(new PKModel());
                    lote.getPk().setOrganizacao(Controller.getOrganizacao());
                    lote.getPk().setId(PempecKeyGenerator.generateKey());
                    lote.setDataRegistro(Controller.getDataServidorBD());
                    lote.setUsuario(Controller.getUsuarioLogado());
                    lote.setStatus(Constantes.LOTE_PAGAMENTO_PAGO);
                    lote.setQtdTituloPagar(lTitulosPeriodo.size());
                    lote.setLote(jTNumeroLote.getText());
                    lote.setValor(valorTotal);


                    if (auxFormasPagamento != null && !auxFormasPagamento.isEmpty()) {
                        lote.setFormaPagamentoModel(auxFormasPagamento.get(0).getForma());
                    }

                    if (jFTDataPagamento.getDate() != null) {
                        lote.setDataPagamento(jFTDataPagamento.getDate());
                    }

                    if (comboResponsavel.getSelectedItem() != null) {
                        lote.setResponsavel(new FuncionarioModel());
                        lote.getResponsavel().setPk(new PKModel());
                        lote.getResponsavel().getPk().setOrganizacao(organizacaoModel);
                        lote.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavel.getSelectedItem()).getPk().getId());

                    }


                }

                Collection<TituloPagarModel> collPagar = new ArrayList<TituloPagarModel>();
                Collection<TituloPagarBaixaModel> collPagarBaixa = new ArrayList<TituloPagarBaixaModel>();


                TipoStatusModel status = new TipoStatusModel();
                status.setPk(new PKModel());
                status.getPk().setOrganizacao(organizacaoModel);
                status.getPk().setId(Constantes.STATUS_TITULO_PAGO);

                for (TituloPagarModel tituloOrigem : lTitulosPeriodo) {

                    tituloOrigem.setDataPagamento(jFTDataPagamento.getDate());
                    tituloOrigem.setDataUltimaAtualizacao(Controller.getDataServidorBD());
                    tituloOrigem.setObservacao("Pagto atraves do lote número : " + lote.getLote());
                    tituloOrigem.setLotePagamentoModel(lote);
                    tituloOrigem.setStatus(status);

                    TituloPagarBaixaModel tab = new TituloPagarBaixaModel();

                    tab.setPk(new PKModel());

                    tab.getPk().setOrganizacao(organizacaoModel);
                    tab.getPk().setId(PempecKeyGenerator.generateKey());

                    tab.setTitulo(tituloOrigem);

                    tab.setLocalPagamento(tituloOrigem.getLocalPagamento());

                    tab.setDataRegistro(Controller.getDataServidorBD());

                    tab.setTipoBaixa(Constantes.TIPO_BAIXA_TITULO_TOTAL);

                    tab.setValorPago(valorTotal);

                    tab.setUsuario(Controller.getUsuarioLogado());

                    tab.setCentroCusto(tituloOrigem.getCentroCusto());

                    tab.setResponsavel(tituloOrigem.getResponsavel());

                    //  Coletando informações da Tabela para pegar os ID da forma pagamento.
                    Set<TituloPagarBaixaFormaPagamentoModel> formasPagamento = new HashSet<TituloPagarBaixaFormaPagamentoModel>();

                    formasPagamento.addAll(auxFormasPagamento);

                    tab.setFormasPagamento(formasPagamento);

                    Set<TituloPagarBaixaChequeModel> cheques = new HashSet<TituloPagarBaixaChequeModel>();

                    cheques.addAll(auxCheques);

                    tab.setTituloPagarBaixaCheque(cheques);

                    Set<TituloPagarBaixaInternetModel> internet = new HashSet<TituloPagarBaixaInternetModel>();

                    internet.addAll(auxInternet);

                    tab.setTituloPagarBaixaInternet(internet);

                    tab.setMovimentoDiarioModel(registroMovimento("BX TIT. PAGAR LOTE ", lote.getLote(), " TíTULO. " + tab.getTitulo().getNumeroDocumento() + "  - Lote: " + lote.getLote(), tab.getValorPago(), "Pago"));
                    tab.getMovimentoDiarioModel().setObservacao("Pago pelo lote num: " + lote.getLote() + "em " + lote.getDataPagamento());

                    // completando os campos de LOTE_PAGAMENTO
                    if (tab.getTituloPagarBaixaCheque() != null) {

                        for (TituloPagarBaixaChequeModel chequePago : tab.getTituloPagarBaixaCheque()) {

                            if (chequePago.getContaBancariaCheque() != null) {
                                lote.setCheque(chequePago.getContaBancariaCheque());
                                lote.setContaBancariaModel(chequePago.getContaBancariaCheque().getContaBancaria());
                                lote.setTipoOperacaoBancariaModel(chequePago.getTipoOperacaoBancaria());
                            }
                        }

                    }

                    if (tab.getTituloPagarBaixaInternet() != null) {

                        for (TituloPagarBaixaInternetModel internetPaga : tab.getTituloPagarBaixaInternet()) {

                            if (internetPaga.getContaBancaria() != null) {
                                lote.setTipoOperacaoBancariaModel(internetPaga.getTipoOperacaoBancaria());
                                lote.setContaBancariaModel(internetPaga.getContaBancaria());
                            }
                        }

                    }

                    collPagar.add(tituloOrigem);
                    collPagarBaixa.add(tab);

                }

                tituloPagarBaixaBO.salvarLote(lote, collPagarBaixa, collPagar);
                panelFormato.setVisible(true);
                botaoImprimir.setEnabled(true);
                botaoSalvar.setEnabled(false);
                tableFormaPagamento.setEnabled(false);
                comboFormaPagamento.setEnabled(false);
                btnIncluirFormaPagamento.setEnabled(false);
                btnRemoverFormaPagamento.setEnabled(false);
                jFTDataPagamento.setEnabled(false);
                comboResponsavel.setEnabled(false);

            } else {

                exibeMensagemAviso("Campo(s) obrigatório(s).", null);
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

}//GEN-LAST:event_botaoSalvarActionPerformed

    private void btnRemoverFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverFormaPagamentoActionPerformed

        int cont = 0;

        for (int i = 0; i < tableFormaPagamento.getRowCount(); i++) {

            if (tableFormaPagamento.getValueAt(i, 0).toString().equals("true")) {

                cont++;

            }

        }

        if (cont == tableFormaPagamento.getRowCount()) {

            auxCheques.removeAll(auxCheques);

            auxInternet.removeAll(auxInternet);

            auxFormasPagamento.removeAll(auxFormasPagamento);

            this.preencheTabelaFormaPagamento();

        } else {

            for (int i = 0; i < tableFormaPagamento.getRowCount(); i++) {

                if (tableFormaPagamento.getValueAt(i, 0).toString().equals("true")) {

                    ((TableModelCadastroTituloPagarBaixaFP) tableFormaPagamento.getModel()).removeByID(tableFormaPagamento.getValueAt(i, 3).toString());

                }

            }

            this.preencheTabelaFormaPagamento();

        }

    }//GEN-LAST:event_btnRemoverFormaPagamentoActionPerformed

    private void btnIncluirFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirFormaPagamentoActionPerformed

        if (this.validaFormaPagamento()) {

            String formaPagamento = ((FormaPagamentoModel) comboFormaPagamento.getSelectedItem()).getPk().getId();

            TituloPagarBaixaFormaPagamentoModel tituloPagarBaixaFormaPagamentoModel = new TituloPagarBaixaFormaPagamentoModel();

            tituloPagarBaixaFormaPagamentoModel.setPk(new PKModel());

            tituloPagarBaixaFormaPagamentoModel.getPk().setOrganizacao(organizacaoModel);

            tituloPagarBaixaFormaPagamentoModel.getPk().setId(PempecKeyGenerator.generateKey());

            //Tratamento de Nulidade.
            //Tratamento tbm da opção Selecione
            if (validarCombos("formaPagamento")) {
                tituloPagarBaixaFormaPagamentoModel.setForma((FormaPagamentoModel) comboFormaPagamento.getSelectedItem());
            }

            tituloPagarBaixaFormaPagamentoModel.setValor(jFTValorFormaPagamento.getValue());

            if (formaPagamento.equals(Constantes.FORMA_PAGAMENTO_CHEQUE)) {

                try {

                    if (baixaFPCheque == null || baixaFPCheque.isClosed()) {
                        baixaFPCheque = new CadastroTituloPagarBaixaLoteFPCheque(this, tituloPagarBaixaFormaPagamentoModel, cedente);
                        TelaPrincipal.desktopPane.add(baixaFPCheque);
                        baixaFPCheque.show();
                    } else {
                        baixaFPCheque.setVisible(true);
                        baixaFPCheque.moveToFront();
                        baixaFPCheque.requestFocus();
                    }


                } catch (final SystemException ex) {

                    final File file = PrintScreen.capture();

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {

                            tratamentoExcecao(ex, file);

                        }
                    });

                }

            } else {

                if (formaPagamento.equals(Constantes.FORMA_PAGAMENTO_INTERNET)) {

                    try {

                        if (baixaFPInternet == null || baixaFPInternet.isClosed()) {
                            baixaFPInternet = new CadastroTituloPagarBaixaLoteFPInternet(this, tituloPagarBaixaFormaPagamentoModel, cedente);
                            TelaPrincipal.desktopPane.add(baixaFPInternet);
                            baixaFPInternet.show();
                        } else {
                            baixaFPInternet.setVisible(true);
                            baixaFPInternet.moveToFront();
                            baixaFPInternet.requestFocus();
                        }


                    } catch (final SystemException ex) {

                        final File file = PrintScreen.capture();

                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {

                                tratamentoExcecao(ex, file);

                            }
                        });

                    }

                } else {


                    if (auxFormasPagamento != null && auxFormasPagamento.isEmpty()) {

                        tituloPagarBaixaFormaPagamentoModel.setValor(jFTValorFormaPagamento.getValue());

                        try {

                            Double saldoTesouraria = new TesourariaServices(organizacaoModel).obterSaldoTesouraria();

                            if (saldoTesouraria < jFTValorFormaPagamento.getValue()) {

                                if (!exibeMensagemConfirmacao("Tesouraria não possui saldo suficiente. Desejar continuar? \n\nSaldo: R$ " + PempecParse.doubleToZero(saldoTesouraria), "Saldo Insuficiente")) {
                                    return;
                                }

                                if (Controller.verificaParametroAtivo(Parametro.CBPLFPE001.getCodigo())) {
                                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPLFPE001.getCodigo()), null);
                                    return;
                                }

                                if (Controller.verificaParametroAtivo(Parametro.CBPLFPE002.getCodigo())) {
                                    if (saldoTesouraria < Controller.findByCodigo(Parametro.CBPLFPE002.getCodigo()).getValor().doubleValue()) {
                                        exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBPLFPE002.getCodigo()), null);
                                        return;
                                    }
                                }

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

                        auxFormasPagamento.add(tituloPagarBaixaFormaPagamentoModel);

                        this.preencheTabelaFormaPagamento();

                    } else {

                        exibeMensagemAviso("Só é permitido uma Forma de Pagamento!", null);

                    }

                    this.limparCamposFormaPagamento();

                }

            }

        } else {

            exibeMensagemAviso("Campo(s) obrigatório(s).", null);

        }
}//GEN-LAST:event_btnIncluirFormaPagamentoActionPerformed

    private void comboFormaPagamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormaPagamentoItemStateChanged

        if (validarCombos("formaPagamento")) {

            String formaPagamento = ((FormaPagamentoModel) comboFormaPagamento.getSelectedItem()).getPk().getId();

            if (formaPagamento.equals(Constantes.FORMA_PAGAMENTO_CHEQUE) || formaPagamento.equals(Constantes.FORMA_PAGAMENTO_INTERNET)) {

                labelValorPagar2.setVisible(false);
                jFTValorFormaPagamento.setVisible(false);

            } else {

                labelValorPagar2.setVisible(true);
                jFTValorFormaPagamento.setVisible(true);
                jFTValorFormaPagamento.setText(this.jFTValorTotal.getText());

            }

        }
}//GEN-LAST:event_comboFormaPagamentoItemStateChanged

    private void botaoImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirActionPerformed

        Collection<ReportPagamentoLote> collRel = new ArrayList<ReportPagamentoLote>();

        Map<String, Object> parametros = new HashMap<String, Object>();

        for (TituloPagarModel tituloPagar : lTitulosPeriodo) {

            if (tituloPagar.getCheck()) {

                ReportPagamentoLote bean = new ReportPagamentoLote();

                bean.setRazaoSocial(organizacaoModel.getRazaoSocial());
                bean.setCnpj(organizacaoModel.getCnpj());
                bean.setLote(jTNumeroLote.getText());
                bean.setDataPagamento(jFTDataPagamento.getDate());
                bean.setNumeroDocumento(tituloPagar.getNumeroDocumento());
                bean.setCedente(tituloPagar.getCedente().getNome());
                bean.setDataVencimento(tituloPagar.getDataVencimento());
                bean.setHistorico(tituloPagar.getHistorico().getDescricao() + " " + tituloPagar.getDescricao());
                bean.setValor(tituloPagar.getValorNominal());

                if (auxFormasPagamento != null && !auxFormasPagamento.isEmpty()) {

                    String forma = auxFormasPagamento.get(0).getForma().getDescricao();

                    String aux = forma;

                    if (Constantes.FORMA_PAGAMENTO_ESPECIE.equals(forma)) {

                        bean.setFormaPagamento(forma);

                        bean.setTipoPagamento("CARTEIRA");

                    } else {

                        bean.setTipoPagamento("BANCO");

                        if (auxCheques != null && auxCheques.get(0).getContaBancariaCheque() != null) {

                            if (auxCheques.get(0).getContaBancariaCheque().getContaBancaria() != null) {

                                if (Constantes.FORMA_PAGAMENTO_CHEQUE.equals(forma)) {

                                    aux += " Nº " + auxCheques.get(0).getContaBancariaCheque().getNumeroCheque();

                                }

                                aux += " CONTA: " + auxCheques.get(0).getContaBancariaCheque().getContaBancaria().getConta();
                            }
                        }

                        bean.setFormaPagamento(aux);

                    }

                }

                collRel.add(bean);

            }

            String texto = "Autorizamos a V.Sas. resgatarem o(s) título(s) acima relacionado(s), " + collRel.size()
                    + " BOLETO(S) BANCÁRIO(S) de nossa responsabilidade levando a débito de nossa conta corrente, o valor de R$ " + PempecParse.doubleToZero(jFTValorTotal.getValue())
                    + "( " + new Extenso(jFTValorTotal.getValue())
                    + " ) na respectiva data de vencimento sob aviso.";

            parametros.put("texto", texto);

        }

        try {

            switch (comboFormato.getSelectedIndex()) {

                case 0:
                    new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_PAGAMENTO_LOTE, parametros, collRel);
                    break;
                case 1:
                    new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_PAGAMENTO_LOTE, parametros, collRel);
                    break;
                case 2:
                    new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_PAGAMENTO_LOTE, parametros, collRel);
                    break;
                case 3:

                    //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                    File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_PAGAMENTO_LOTE, parametros, collRel);

                    try {

                        TelaEnvioEmail envioEmail = new TelaEnvioEmail(anexo);
                        TelaPrincipal.desktopPane.add(envioEmail);
                        envioEmail.show();

                    } catch (final SystemException ex) {

                        final File file = PrintScreen.capture();

                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {

                                tratamentoExcecao(ex, file);

                            }
                        });

                    }
                    break;

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
}//GEN-LAST:event_botaoImprimirActionPerformed

    private Boolean validaFormaPagamento() {

        if (jFTDataPagamento.getDate().after(Controller.getDataServidorBD())) {
            exibeMensagemAviso("Data de pagamento não pode ser superior a HOJE!", null);
            jFTDataPagamento.requestFocus();
            return false;
        }


        //Levantar as validações
        if (comboFormaPagamento.getSelectedIndex() == 0) {
            comboFormaPagamento.requestFocus();
            return false;
        }

        if (jFTValorFormaPagamento.isVisible() && jFTValorFormaPagamento.getText().equals("0,00")) {
            jFTValorFormaPagamento.requestFocus();
            return false;
        }

        return true;
    }

    private void limparCamposFormaPagamento() {
        comboFormaPagamento.setSelectedIndex(0);
        jFTValorFormaPagamento.setText("0,00");

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton btnIncluirFormaPagamento;
    private javax.swing.JButton btnRemoverFormaPagamento;
    private javax.swing.JComboBox comboFormaPagamento;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JComboBox comboResponsavel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    protected org.jdesktop.swingx.JXDatePicker jFTDataPagamento;
    private br.com.pempec.componentes.JDoubleField jFTValorFormaPagamento;
    protected br.com.pempec.componentes.JDoubleField jFTValorTotal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JFormattedTextField jTNumeroLote;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCentroCusto1;
    private javax.swing.JLabel labelValorPagar1;
    private javax.swing.JLabel labelValorPagar2;
    private javax.swing.JLabel labelValorPagar3;
    private javax.swing.JPanel panelAcrescimo;
    private javax.swing.JPanel panelFormato;
    private javax.swing.JTable tableFormaPagamento;
    // End of variables declaration//GEN-END:variables
    private ArrayList<TituloPagarBaixaFormaPagamentoModel> auxFormasPagamento;
    private ArrayList<TituloPagarBaixaChequeModel> auxCheques;
    private ArrayList<TituloPagarBaixaInternetModel> auxInternet;
    private CadastroTituloPagarBaixaLoteFPCheque baixaFPCheque = null;
    private CadastroTituloPagarBaixaLoteFPInternet baixaFPInternet = null;

    //Funções Auxiliares
    private void preencheTabelaFormaPagamento() {

        tableFormaPagamento.setAutoCreateColumnsFromModel(false);
        tableFormaPagamento.setModel(new TableModelCadastroTituloPagarBaixaFP(auxFormasPagamento));
        FontMetrics fm = tableFormaPagamento.getFontMetrics(tableFormaPagamento.getFont());
        tableFormaPagamento.setColumnModel(new ColumnModelCadastroTituloPagarBaixaFP(fm));
        tableFormaPagamento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableFormaPagamento.getTableHeader().setReorderingAllowed(false);

    }

    public void addCollecaoCheques(TituloPagarBaixaChequeModel baixaChequeModel, TituloPagarBaixaFormaPagamentoModel tituloPagarBaixaFormaPagamentoModel) {

        if (auxFormasPagamento != null && auxFormasPagamento.isEmpty()) {

            auxFormasPagamento.add(tituloPagarBaixaFormaPagamentoModel);

            auxCheques.add(baixaChequeModel);

            this.preencheTabelaFormaPagamento();

            this.limparCamposFormaPagamento();

        }



    }

    public void addCollecaoInternet(TituloPagarBaixaInternetModel internetModel, TituloPagarBaixaFormaPagamentoModel tituloPagarBaixaFormaPagamentoModel) {

        if (auxFormasPagamento != null && auxFormasPagamento.isEmpty()) {

            auxFormasPagamento.add(tituloPagarBaixaFormaPagamentoModel);

            auxInternet.add(internetModel);

            this.preencheTabelaFormaPagamento();

            this.limparCamposFormaPagamento();

        }

    }

    private boolean validarCombos(String combo) {

        boolean valida = false;

        if (combo.equalsIgnoreCase("formaPagamento")) {
            if (comboFormaPagamento.getSelectedItem() != null) {
                if (((FormaPagamentoModel) comboFormaPagamento.getSelectedItem()).getPk() != null) {
                    if (!((FormaPagamentoModel) comboFormaPagamento.getSelectedItem()).getPk().getId().isEmpty()) {
                        valida = true;
                    }

                }
            }
        }

        if (combo.equalsIgnoreCase("responsavel")) {
            if (comboResponsavel.getSelectedItem() != null) {
                if (((FuncionarioModel) comboResponsavel.getSelectedItem()).getPk() != null) {
                    if (!((FuncionarioModel) comboResponsavel.getSelectedItem()).getPk().getId().isEmpty()) {
                        valida = true;
                    }

                }
            }
        }

        return valida;

    }

    private void limparCamposBaixa() {

        jFTDataPagamento.setDate(Controller.getDataServidorBD());

        //Limpando as Listas.
        auxFormasPagamento = new ArrayList<TituloPagarBaixaFormaPagamentoModel>();

        auxCheques = new ArrayList<TituloPagarBaixaChequeModel>();

        auxInternet = new ArrayList<TituloPagarBaixaInternetModel>();

        this.limparCamposFormaPagamento();

        this.preencheTabelaFormaPagamento();

    }

    private Boolean validaCampos() {

        //Levantar campos Obrigatórios.
        if (jFTDataPagamento.getDate() == null) {
            jFTDataPagamento.requestFocus();
            return false;
        }

        if (comboResponsavel.getSelectedIndex() == 0) {
            comboResponsavel.requestFocus();
            return false;

        }

        if (auxFormasPagamento.isEmpty()) {
            exibeMensagemAviso("Forma de Pagamento Obrigatória!", null);
            return false;
        }

        return true;

    }
}
