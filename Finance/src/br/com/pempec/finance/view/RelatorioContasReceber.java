package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CentroCustoBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.LocalPagamentoBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.SacadoBO;
import br.com.pempec.finance.businessObjects.TipoCobrancaBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.LocalPagamentoModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.TipoCobrancaModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.reports.FilterReportTituloReceber;
import br.com.pempec.finance.models.reports.ReportTituloReceber;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopuladorNew;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.SacadoTextFilterator;
import br.com.pempec.finance.utils.iterators.HistoricoTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class RelatorioContasReceber extends FinanceInternalFrame implements IRepopuladorNew {

    private TipoCobrancaBO tipoCobrancaBO = new TipoCobrancaBO();
    private LocalPagamentoBO localPagamentoBO = new LocalPagamentoBO();
    private SacadoBO sacadoBO = new SacadoBO();
    private CentroCustoBO centroCustoBO = new CentroCustoBO();
    private HistoricoBO historicoBO = new HistoricoBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private long tela = Tela.TELA_RELATORIOS_CONTA_A_RECEBER.getTela();
    private TituloReceberBaixaBO tituloReceberBaixaBO = new TituloReceberBaixaBO();

    public RelatorioContasReceber() throws SystemException {
        Controller.setQtdMovimentosHoje(0);
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        labelLocalPagamento = new javax.swing.JLabel();
        comboLocalPagamento = new javax.swing.JComboBox();
        labelResponsavel = new javax.swing.JLabel();
        comboFuncionario = new javax.swing.JComboBox();
        labelCedente = new javax.swing.JLabel();
        comboSacado = new javax.swing.JComboBox();
        labelCentroCusto = new javax.swing.JLabel();
        comboCentroCusto = new javax.swing.JComboBox();
        labelHistorico = new javax.swing.JLabel();
        comboHistorico = new javax.swing.JComboBox();
        labelPersonalidade = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox();
        jPanel7 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        jFTPeriodoInicial = new org.jdesktop.swingx.JXDatePicker();
        jFTPeriodoFinal = new org.jdesktop.swingx.JXDatePicker();
        jPanel8 = new javax.swing.JPanel();
        jRSintetico = new javax.swing.JRadioButton();
        jRAnalitico = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        button1 = new java.awt.Button();
        button2 = new java.awt.Button();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Relatório Contas Receber");
        setPreferredSize(new java.awt.Dimension(822, 500));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));
        jPanel1.setMinimumSize(new java.awt.Dimension(1080, 380));
        jPanel1.setPreferredSize(new java.awt.Dimension(1080, 380));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Filtros"));
        jPanel6.setPreferredSize(new java.awt.Dimension(1050, 170));

        labelLocalPagamento.setText("Local Recebimento");

        comboLocalPagamento.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        labelResponsavel.setText("Responsável");

        comboFuncionario.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        labelCedente.setText("Sacado");

        comboSacado.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        labelCentroCusto.setText("Centro Custo");

        comboCentroCusto.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        labelHistorico.setText("Histórico");

        comboHistorico.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        labelPersonalidade.setText("Status");

        comboStatus.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        comboStatus.setForeground(new java.awt.Color(0, 153, 204));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCedente)
                    .addComponent(comboSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCentroCusto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelHistorico)
                    .addComponent(comboHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelResponsavel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboLocalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelLocalPagamento))
                    .addComponent(labelPersonalidade)
                    .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboLocalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(labelLocalPagamento)
                                .addGap(31, 31, 31)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(labelPersonalidade)
                                .addGap(31, 31, 31))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(labelHistorico)
                                .addGap(31, 31, 31)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelResponsavel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(comboSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(labelCedente)
                                .addGap(32, 32, 32)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCentroCusto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Parametros"));
        jPanel7.setPreferredSize(new java.awt.Dimension(1050, 125));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 102), 1, true), "Período"));
        jPanel4.setPreferredSize(new java.awt.Dimension(304, 80));

        label2.setText("à");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Tipo"));
        jPanel8.setPreferredSize(new java.awt.Dimension(304, 80));

        buttonGroup1.add(jRSintetico);
        jRSintetico.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRSintetico.setSelected(true);
        jRSintetico.setText("Sintético");

        buttonGroup1.add(jRAnalitico);
        jRAnalitico.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jRAnalitico.setText("Analítico");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRSintetico, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jRAnalitico)
                .addGap(83, 83, 83))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRSintetico)
                    .addComponent(jRAnalitico))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Formato"));
        jPanel5.setPreferredSize(new java.awt.Dimension(304, 80));

        comboFormato.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFormatoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 775, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jPanel2.setPreferredSize(new java.awt.Dimension(1117, 65));

        botaoGerar.setMnemonic('G');
        botaoGerar.setText("Gerar");
        botaoGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoGerar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 804, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Relatório Contas a Receber";
    }
    /*
     public void repopularCombos() {

     try {

     Collection<TipoCobrancaModel> lTipoCobranca = new ArrayList<TipoCobrancaModel>();

     Collection<LocalPagamentoModel> lLocalPagamento = new ArrayList<LocalPagamentoModel>();

     Collection<SacadoModel> lSacado = new ArrayList<SacadoModel>();

     Collection<CentroCustoModel> lCentroCusto = new ArrayList<CentroCustoModel>();

     Collection<HistoricoModel> lHistorico = new ArrayList<HistoricoModel>();

     Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

     TipoCobrancaModel tipoCobrancaModel = new TipoCobrancaModel();

     tipoCobrancaModel.setDescricao(" -> Todos <- ");

     lTipoCobranca.add(tipoCobrancaModel);

     lTipoCobranca.addAll(tipoCobrancaBO.obterTodos(organizacaoModel));

     comboTipoCobranca.setModel(new javax.swing.DefaultComboBoxModel(lTipoCobranca.toArray()));

     LocalPagamentoModel localPagamentoModel = new LocalPagamentoModel();

     localPagamentoModel.setDescricao(" -> Todos <- ");

     lLocalPagamento.add(localPagamentoModel);

     lLocalPagamento.addAll(localPagamentoBO.obterTodos(organizacaoModel));

     comboLocalPagamento.setModel(new javax.swing.DefaultComboBoxModel(lLocalPagamento.toArray()));

     CentroCustoModel centroCustoModel = new CentroCustoModel();

     centroCustoModel.setDescricao(" -> Todos <- ");

     lCentroCusto.add(centroCustoModel);

     lCentroCusto.addAll(centroCustoBO.obterTodos(organizacaoModel));

     comboCentroCusto.setModel(new javax.swing.DefaultComboBoxModel(lCentroCusto.toArray()));

     HistoricoModel historicoModel = new HistoricoModel();

     historicoModel.setDescricao(" -> Todos <- ");

     lHistorico.add(historicoModel);
     String tipo = "R";

     lHistorico.addAll(historicoBO.obterTodosPorTipo(organizacaoModel, tipo));

     comboHistorico.setModel(new javax.swing.DefaultComboBoxModel(lHistorico.toArray()));

     FuncionarioModel funcionarioModel = new FuncionarioModel();

     funcionarioModel.setNome(" -> Todos <- ");

     lFuncionario.add(funcionarioModel);

     lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

     comboFuncionario.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

     comboStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" -> Selecione <- ",
     Constantes.STATUS_TITULO_PAGO, Constantes.STATUS_TITULO_PARCIAL, Constantes.STATUS_TITULO_FUNDO_PERDIDO, Constantes.STATUS_TITULO_EXCLUIDO, Constantes.STATUS_TITULO_NOVO
     }));


     SacadoModel SacadoModel = new SacadoModel();

     SacadoModel.setNome(" -> Todos <- ");

     lSacado.add(SacadoModel);

     lSacado.addAll(sacadoBO.obterTodos(organizacaoModel));

     comboSacado.setModel(new javax.swing.DefaultComboBoxModel(lSacado.toArray()));

     comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));


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
     */

    public void repopularCombos(Tela tela, Object object) {
        try {

            switch (tela) {

                case TELA_PARAMETROS_SACADOS:

                    Collection<SacadoModel> lSacado = sacadoBO.obterTodos(organizacaoModel);

                    EventList<SacadoModel> lRegistrosSacados = GlazedLists.eventList(lSacado);
                    if (supportSacado != null && supportSacado.getItemList() != null && supportSacado.getComboBox() != null) {
                        supportSacado.uninstall();
                    }
                    supportSacado = AutoCompleteSupport.install(comboSacado, lRegistrosSacados, new SacadoTextFilterator());
                    supportSacado.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportSacado.setStrict(false);

                    SacadoModel SacadoModel = (SacadoModel) object;

                    if (SacadoModel != null) {

                        for (int i = 1; i < comboSacado.getItemCount(); i++) {
                            if (SacadoModel.getPk().getId().equalsIgnoreCase(((SacadoModel) comboSacado.getItemAt(i)).getPk().getId())) {
                                comboSacado.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_TIPO_DE_COBRANCAS:

                    Collection<TipoCobrancaModel> lTipoCobranca = new ArrayList<TipoCobrancaModel>();

                    TipoCobrancaModel tipoCobrancaModel = new TipoCobrancaModel();

                    tipoCobrancaModel.setDescricao(" -> Selecione <- ");

                    lTipoCobranca.add(tipoCobrancaModel);

                    lTipoCobranca.addAll(tipoCobrancaBO.obterTodos(organizacaoModel));

//                    comboTipoCobranca.setModel(new javax.swing.DefaultComboBoxModel(lTipoCobranca.toArray()));
//
//                    tipoCobrancaModel = (TipoCobrancaModel) object;
//
//                    if (tipoCobrancaModel != null) {
//
//                        for (int i = 1; i < comboTipoCobranca.getItemCount(); i++) {
//                            if (tipoCobrancaModel.getPk().getId().equalsIgnoreCase(((TipoCobrancaModel) comboTipoCobranca.getItemAt(i)).getPk().getId())) {
//                                comboTipoCobranca.setSelectedIndex(i);
//                                break;
//                            }
//                        }
//
//                    }
//
                    break;

                case TELA_PARAMETROS_LOCAL_DE_PAGAMENTO:

                    Collection<LocalPagamentoModel> lLocalPagamento = new ArrayList<LocalPagamentoModel>();

                    LocalPagamentoModel localPagamentoModel = new LocalPagamentoModel();

                    localPagamentoModel.setDescricao(" -> Selecione <- ");

                    lLocalPagamento.add(localPagamentoModel);

                    lLocalPagamento.addAll(localPagamentoBO.obterTodos(organizacaoModel));

                    comboLocalPagamento.setModel(new javax.swing.DefaultComboBoxModel(lLocalPagamento.toArray()));

                    localPagamentoModel = (LocalPagamentoModel) object;

                    if (localPagamentoModel != null) {

                        for (int i = 1; i < comboLocalPagamento.getItemCount(); i++) {
                            if (localPagamentoModel.getPk().getId().equalsIgnoreCase(((LocalPagamentoModel) comboLocalPagamento.getItemAt(i)).getPk().getId())) {
                                comboLocalPagamento.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_CENTRO_DE_CUSTOS:

                    Collection<CentroCustoModel> lCentroCusto = new ArrayList<CentroCustoModel>();

                    CentroCustoModel centroCustoModel = new CentroCustoModel();

                    centroCustoModel.setDescricao(" -> Selecione <- ");

                    lCentroCusto.add(centroCustoModel);

                    lCentroCusto.addAll(centroCustoBO.obterTodos(organizacaoModel));

                    comboCentroCusto.setModel(new javax.swing.DefaultComboBoxModel(lCentroCusto.toArray()));

                    centroCustoModel = (CentroCustoModel) object;

                    if (centroCustoModel != null) {

                        for (int i = 1; i < comboCentroCusto.getItemCount(); i++) {
                            if (centroCustoModel.getPk().getId().equalsIgnoreCase(((CentroCustoModel) comboCentroCusto.getItemAt(i)).getPk().getId())) {
                                comboCentroCusto.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_HISTORICOS:

                    Collection<HistoricoModel> lHistorico = historicoBO.obterTodosPorTipo(organizacaoModel, "R");

                    EventList<HistoricoModel> lRegistrosHistoricos = GlazedLists.eventList(lHistorico);
                    if (supportHistorico != null && supportHistorico.getItemList() != null && supportHistorico.getComboBox() != null) {
                        supportHistorico.uninstall();
                    }
                    supportHistorico = AutoCompleteSupport.install(comboHistorico, lRegistrosHistoricos, new HistoricoTextFilterator());
                    supportHistorico.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportHistorico.setStrict(false);

                    HistoricoModel historicoModel = (HistoricoModel) object;

                    if (historicoModel != null) {

                        for (int i = 1; i < comboHistorico.getItemCount(); i++) {
                            if (historicoModel.getPk().getId().equalsIgnoreCase(((HistoricoModel) comboHistorico.getItemAt(i)).getPk().getId())) {
                                comboHistorico.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_FUNCIONARIOS:

                    Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

                    FuncionarioModel funcionarioModel = new FuncionarioModel();

                    funcionarioModel.setNome(" -> Selecione <- ");

                    lFuncionario.add(funcionarioModel);

                    lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

                    comboFuncionario.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

                    funcionarioModel = (FuncionarioModel) object;

                    if (funcionarioModel != null) {

                        for (int i = 1; i < comboFuncionario.getItemCount(); i++) {
                            if (funcionarioModel.getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboFuncionario.getItemAt(i)).getPk().getId())) {
                                comboFuncionario.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                default:

                    lSacado = sacadoBO.obterTodos(organizacaoModel);

                    lTipoCobranca = new ArrayList<TipoCobrancaModel>();

                    lLocalPagamento = new ArrayList<LocalPagamentoModel>();

                    lCentroCusto = new ArrayList<CentroCustoModel>();

                    lHistorico = historicoBO.obterTodosPorTipo(organizacaoModel, "R");

                    lFuncionario = new ArrayList<FuncionarioModel>();

                    //Adicionando a opção de selecionar, caso o campo não seja obrigatório!

                    tipoCobrancaModel = new TipoCobrancaModel();

                    tipoCobrancaModel.setDescricao(" -> Selecione <- ");

                    lTipoCobranca.add(tipoCobrancaModel);

                    lTipoCobranca.addAll(tipoCobrancaBO.obterTodos(organizacaoModel));

                    //  comboTipoCobranca.setModel(new javax.swing.DefaultComboBoxModel(lTipoCobranca.toArray()));

                    localPagamentoModel = new LocalPagamentoModel();

                    localPagamentoModel.setDescricao(" -> Selecione <- ");

                    lLocalPagamento.add(localPagamentoModel);

                    lLocalPagamento.addAll(localPagamentoBO.obterTodos(organizacaoModel));

                    comboLocalPagamento.setModel(new javax.swing.DefaultComboBoxModel(lLocalPagamento.toArray()));

                    centroCustoModel = new CentroCustoModel();

                    centroCustoModel.setDescricao(" -> Selecione <- ");

                    lCentroCusto.add(centroCustoModel);

                    lCentroCusto.addAll(centroCustoBO.obterTodos(organizacaoModel));

                    comboCentroCusto.setModel(new javax.swing.DefaultComboBoxModel(lCentroCusto.toArray()));

                    funcionarioModel = new FuncionarioModel();

                    funcionarioModel.setNome(" -> Selecione <- ");

                    lFuncionario.add(funcionarioModel);

                    lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

                    comboFuncionario.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

                    lRegistrosSacados = GlazedLists.eventList(lSacado);
                    if (supportSacado != null && supportSacado.getItemList() != null && supportSacado.getComboBox() != null) {
                        supportSacado.uninstall();
                    }

                    supportSacado = AutoCompleteSupport.install(comboSacado, lRegistrosSacados, new SacadoTextFilterator());
                    supportSacado.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportSacado.setStrict(false);

                    lRegistrosHistoricos = GlazedLists.eventList(lHistorico);
                    if (supportHistorico != null && supportHistorico.getItemList() != null && supportHistorico.getComboBox() != null) {
                        supportHistorico.uninstall();
                    }
                    supportHistorico = AutoCompleteSupport.install(comboHistorico, lRegistrosHistoricos, new HistoricoTextFilterator());
                    supportHistorico.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportHistorico.setStrict(false);

                    break;

            }

            comboStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[]{Constantes.STATUS_TITULO_NOVO,
                Constantes.STATUS_TITULO_PAGO, Constantes.STATUS_TITULO_PARCIAL, Constantes.STATUS_TITULO_EXCLUIDO}));

//            comboStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" -> Selecione <- ",
//                        Constantes.STATUS_TITULO_PAGO, Constantes.STATUS_TITULO_PARCIAL, Constantes.STATUS_TITULO_EXCLUIDO, Constantes.STATUS_TITULO_NOVO
//                    }));

            comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));

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
        jFTPeriodoFinal.setDate(Controller.getDataServidorBD());
        jFTPeriodoInicial.setDate(Controller.getDataServidorBD());

        comboHistorico.setSelectedItem(null);
        comboSacado.setSelectedItem(null);

        comboFormato.setSelectedIndex(0);
        comboCentroCusto.setSelectedIndex(0);
        comboFuncionario.setSelectedIndex(0);

        comboLocalPagamento.setSelectedIndex(0);
        comboStatus.setSelectedIndex(0);
        // comboTipoCobranca.setSelectedIndex(0);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (jFTPeriodoInicial.getDate() == null) {
            jFTPeriodoInicial.requestFocus();
            return false;
        }

        if (jFTPeriodoFinal.getDate() == null) {
            jFTPeriodoFinal.requestFocus();
            return false;
        }

//        if (comboStatus.getSelectedIndex() == 0) {
//            comboStatus.requestFocus();
//            return false;
//
//        }

        if (jFTPeriodoInicial.getDate().after(jFTPeriodoFinal.getDate())) {
            exibeMensagemAviso("Data Inicial não pode ser superior a data final", null);
            jFTPeriodoInicial.requestFocus();
            return false;
        }

        return true;
    }

    private void botaoGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarActionPerformed


        if (validaCampos()) {

            try {

                long action = Action.IMPRESSAO.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                new MovimentoDiarioBO().inserir(this.registroMovimento("REL CTA RCB", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do relatorio contas a receber.", null, "Impresso"));

                FilterReportTituloReceber filtros = new FilterReportTituloReceber();

                Map<String, Object> parametros = new HashMap<String, Object>();

                String dataInicial = PempecParse.dateToString(jFTPeriodoInicial.getDate());
                String dataFinal = PempecParse.dateToString(jFTPeriodoFinal.getDate());

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL_BARRAS, dataInicial);
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL_BARRAS, dataFinal);

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL, PempecUtil.converteDataYYYYMMDDTracosHorasMinima(dataInicial));
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL, PempecUtil.converteDataYYYYMMDDTracosHorasMaxima(dataFinal));

                parametros.put(RelatorioConstantes.PARAMETRO_ID_ORGANIZACAO, organizacaoModel.getId());

                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

                filtros.setDataInicial(PempecParse.dateToDateMinTime(jFTPeriodoInicial.getDate()));
                filtros.setDataFinal(PempecParse.dateToDateMaxTime(jFTPeriodoFinal.getDate()));

                filtros.setOrganizacao(organizacaoModel.getId());

                String complemento = " ";

                //Pegada dos Filtros - Combos.
                if (comboSacado.getSelectedItem() != null && ((SacadoModel) comboSacado.getSelectedItem()).getPk() != null) {
                    filtros.setSacado(((SacadoModel) comboSacado.getSelectedItem()).getPk().getId());
                    complemento = " PARA " + ((SacadoModel) comboSacado.getSelectedItem()).getNome();
                }

//                if (comboTipoCobranca.getSelectedItem() != null && ((TipoCobrancaModel) comboTipoCobranca.getSelectedItem()).getPk() != null) {
//                    filtros.setTipoCobranca(((TipoCobrancaModel) comboTipoCobranca.getSelectedItem()).getPk().getId());
//                }

                if (comboLocalPagamento.getSelectedItem() != null && ((LocalPagamentoModel) comboLocalPagamento.getSelectedItem()).getPk() != null) {
                    filtros.setLocalPagamento(((LocalPagamentoModel) comboLocalPagamento.getSelectedItem()).getPk().getId());
                }

                if (comboHistorico.getSelectedItem() != null && ((HistoricoModel) comboHistorico.getSelectedItem()).getPk() != null) {
                    filtros.setHistorico(((HistoricoModel) comboHistorico.getSelectedItem()).getPk().getId());
                    complemento = " COM HIST.: " + ((HistoricoModel) comboHistorico.getSelectedItem()).getDescricao();
                }

                if (comboCentroCusto.getSelectedItem() != null && ((CentroCustoModel) comboCentroCusto.getSelectedItem()).getPk() != null) {
                    filtros.setCentroCusto(((CentroCustoModel) comboCentroCusto.getSelectedItem()).getPk().getId());
                }

                if (comboFuncionario.getSelectedItem() != null && ((FuncionarioModel) comboFuncionario.getSelectedItem()).getPk() != null) {
                    filtros.setResponsavel(((FuncionarioModel) comboFuncionario.getSelectedItem()).getPk().getId());
                }



                //if (comboStatus.getSelectedItem() != null && comboStatus.getSelectedIndex() != 0) {
                if (comboStatus.getSelectedItem() != null) {
                    filtros.setStatus((String) comboStatus.getSelectedItem());

                    String tipoRel = "STATUS DOS TÍTULOS :> " + " " + filtros.getStatus() + complemento;

                    parametros.put(RelatorioConstantes.PARAMETRO_ID_TIPO_STATUS, tipoRel);

                } else {

                    String tipoRel = "STATUS DOS TÍTULOS :> TODOS STATUS CONSOLIDADOS.";
                    parametros.put(RelatorioConstantes.PARAMETRO_ID_TIPO_STATUS, tipoRel);
                }


                List<TituloReceberModel> coll = new TituloReceberBO().obterRelatorio(filtros);

                Collection<ReportTituloReceber> collection = new ArrayList<ReportTituloReceber>();

                for (TituloReceberModel tituloReceberModel : coll) {

                    ReportTituloReceber bean = new ReportTituloReceber();

                    bean.setSacado(tituloReceberModel.getSacado().getNome());

                    bean.setCentroCusto(tituloReceberModel.getCentroCusto().getDescricao());

                    bean.setCnpj(organizacaoModel.getCnpj());

                    bean.setDataVencimento(tituloReceberModel.getDataVencimento());

                    bean.setDescricao(tituloReceberModel.getDescricao());

                    bean.setHistorico(tituloReceberModel.getHistorico().getDescricao());

                    bean.setNumeroDocumento(tituloReceberModel.getNumeroDocumento());

                    bean.setParcela(tituloReceberModel.getParcela());

                    bean.setRazaoSocial(organizacaoModel.getRazaoSocial());

                    if (tituloReceberModel.getStatus().getPk().getId().equals(Constantes.STATUS_TITULO_PARCIAL) || tituloReceberModel.getStatus().getPk().getId().equals(Constantes.STATUS_TITULO_PAGO)) {
                        // procurar esse titulo pago e exibir o valor do pagto

                        TituloReceberBaixaModel baixa = new TituloReceberBaixaModel();

                        baixa = tituloReceberBaixaBO.consultarPorTitulo(tituloReceberModel);

                        if (baixa.getPk() != null && !baixa.getPk().getId().isEmpty()) {

                            bean.setValorNominal(baixa.getValorPago());
                        }

                    } else {

                        bean.setValorNominal(tituloReceberModel.getValorNominal());
                    }

                    collection.add(bean);

                }




                if (collection != null && collection.size() > 0) {
                    if (jRSintetico.isSelected()) {

                        switch (comboFormato.getSelectedIndex()) {

                            case 0:
                                new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_CONTAS_RECEBER, parametros, collection);
                                break;
                            case 1:
                                new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_CONTAS_RECEBER, parametros, collection);
                                break;
                            case 2:
                                new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_CONTAS_RECEBER, parametros, collection);
                                break;

                            case 3:

                                //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                                File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_CONTAS_RECEBER, parametros, collection);

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

                    } else {

                        switch (comboFormato.getSelectedIndex()) {

                            case 0:
                                new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_CONTAS_RECEBER_ANALITICO, parametros, collection);
                                break;
                            case 1:
                                new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_CONTAS_RECEBER_ANALITICO, parametros, collection);
                                break;
                            case 2:
                                new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_CONTAS_RECEBER_ANALITICO, parametros, collection);
                                break;

                            case 3:

                                //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                                File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_CONTAS_RECEBER_ANALITICO, parametros, collection);

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
                    }
                } else {


                    String msg = "Não existem titulos para o periodo.\n Verifique o periodo digitado.";

                    exibeMensagemAviso(msg, "Relatório Inexistente.");
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
        } else {

            exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

        }




}//GEN-LAST:event_botaoGerarActionPerformed

    private void comboFormatoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormatoItemStateChanged
        if (comboFormato.getSelectedItem() != null && ((FormatosRelatoriosModel) comboFormato.getSelectedItem()).getDescricao().equalsIgnoreCase(Constantes.ENVIAR_POR_EMAIL)) {
            botaoGerar.setText("Enviar");
        } else {
            botaoGerar.setText("Gerar");
        }
}//GEN-LAST:event_comboFormatoItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private java.awt.Button button1;
    private java.awt.Button button2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboCentroCusto;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JComboBox comboFuncionario;
    private javax.swing.JComboBox comboHistorico;
    private javax.swing.JComboBox comboLocalPagamento;
    private javax.swing.JComboBox comboSacado;
    private javax.swing.JComboBox comboStatus;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoFinal;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoInicial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JRadioButton jRAnalitico;
    private javax.swing.JRadioButton jRSintetico;
    private javax.swing.JLabel label2;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelCentroCusto;
    private javax.swing.JLabel labelHistorico;
    private javax.swing.JLabel labelLocalPagamento;
    private javax.swing.JLabel labelPersonalidade;
    private javax.swing.JLabel labelResponsavel;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportSacado;
    private AutoCompleteSupport supportHistorico;
}
