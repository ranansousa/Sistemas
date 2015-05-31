package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.SacadoBO;
import br.com.pempec.finance.businessObjects.TesourariaCreditoBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.iterators.CedenteTextFilterator;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.iterators.SacadoTextFilterator;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.TesourariaCreditoTextFilterator;
import br.com.pempec.finance.utils.iterators.TesourariaDebitoTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class TesouraiaLancamento extends FinanceInternalFrame implements IRepopulador {

    private TesourariaCreditoBO tesourariaCreditoBO = new TesourariaCreditoBO();
    private TesourariaDebitoBO tesourariaDebitoBO = new TesourariaDebitoBO();
    private CedenteBO cedenteBO = new CedenteBO();
    private SacadoBO sacadoBO = new SacadoBO();
    private HistoricoBO historicoBO = new HistoricoBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private long telaCredito = Tela.TELA_TESOURARIA_LANCAMENTOS.getTela();
    private long telaDebito = Tela.TELA_TESOURARIA_LANCAMENTOS.getTela();

    private String NameObject() {
        return (String) " Lançamentos Tesouraria";
    }

    public void repopularCombos() {

        try {


            Collection<TesourariaCreditoModel> lColecaoCredito = tesourariaCreditoBO.obterTodos(organizacaoModel);

            Collection<TesourariaDebitoModel> lColecaoDebito = tesourariaDebitoBO.obterTodos(organizacaoModel);

            Collection<CedenteModel> lColecaoCedente = cedenteBO.obterTodos(organizacaoModel);

            Collection<SacadoModel> lColecaoSacado = sacadoBO.obterTodos(organizacaoModel);

            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            Collection<HistoricoModel> lHistoricoCredito = new ArrayList<HistoricoModel>();
            Collection<HistoricoModel> lHistoricoDebito = new ArrayList<HistoricoModel>();

            //historicos

            HistoricoModel historicoCredito = new HistoricoModel();

            historicoCredito.setDescricao(" -> Selecione <- ");

            lHistoricoCredito.add(historicoCredito);

            String tipo = "R"; //caixa
            lHistoricoCredito.addAll(historicoBO.obterTodosPorTipo(organizacaoModel, tipo));

            comboHistoricoCredito.setModel(new javax.swing.DefaultComboBoxModel(lHistoricoCredito.toArray()));

            HistoricoModel historicoDebito = new HistoricoModel();

            historicoDebito.setDescricao(" -> Selecione <- ");

            lHistoricoDebito.add(historicoDebito);

            tipo = "p"; // caixa
            lHistoricoDebito.addAll(historicoBO.obterTodosPorTipo(organizacaoModel, tipo));

            comboHistoricoDebito.setModel(new javax.swing.DefaultComboBoxModel(lHistoricoDebito.toArray()));

            // responsavel

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboResponsavelCredito.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            comboResponsavelDebito.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));



            final EventList<CedenteModel> lRegistrosCedentes = GlazedLists.eventList(lColecaoCedente);

            if (supportCedente != null && supportCedente.getItemList() != null && supportCedente.getComboBox() != null) {
                supportCedente.uninstall();
            }
            supportCedente = AutoCompleteSupport.install(comboCedente, lRegistrosCedentes, new CedenteTextFilterator());
            supportCedente.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportCedente.setStrict(false);



            final EventList<SacadoModel> lRegistrosSacados = GlazedLists.eventList(lColecaoSacado);

            if (supportSacado != null && supportSacado.getItemList() != null && supportSacado.getComboBox() != null) {
                supportSacado.uninstall();
            }
            supportSacado = AutoCompleteSupport.install(comboSacado, lRegistrosSacados, new SacadoTextFilterator());
            supportSacado.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportSacado.setStrict(false);


            final EventList<TesourariaCreditoModel> lRegistrosCreditos = GlazedLists.eventList(lColecaoCredito);

            if (supportCredito != null && supportCredito.getItemList() != null && supportCredito.getComboBox() != null) {
                supportCredito.uninstall();
            }
            supportCredito = AutoCompleteSupport.install(comboTituloCredito, lRegistrosCreditos, new TesourariaCreditoTextFilterator());
            supportCredito.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportCredito.setStrict(false);


            final EventList<TesourariaDebitoModel> lRegistrosDebitos = GlazedLists.eventList(lColecaoDebito);

            if (supportDebito != null && supportDebito.getItemList() != null && supportDebito.getComboBox() != null) {
                supportDebito.uninstall();
            }
            supportDebito = AutoCompleteSupport.install(comboTituloDebito, lRegistrosDebitos, new TesourariaDebitoTextFilterator());
            supportDebito.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportDebito.setStrict(false);


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

    public TesouraiaLancamento() throws SystemException {

        initComponents();

        //Escodendo os campos
        campoCodigoCredito.setVisible(false);
        campoCodigoDebito.setVisible(false);



        jFTDataMovimentoCredito.setDate(Controller.getDataServidorBD());
        jFTDataMovimentoDebito.setDate(Controller.getDataServidorBD());

        jFTDataContabilCredito.setDate(Controller.getDataServidorBD());
        jFTDataContabilDebito.setDate(Controller.getDataServidorBD());


        //Aplicando tamanho maximo de caracteres do campo.
        jTDescricaoCredito.setDocument(new Documento(60));
        jTDescricaoDebito.setDocument(new Documento(60));
        jTObservacaoCredito.setDocument(new Documento(200));
        jTObservacaoDebito.setDocument(new Documento(200));

        labelTipoLancamentoCredito.setText("C");
        labelTipoLancamentoDebito.setText("D");
        labelDataRegistroCredito.setText(PempecParse.dateToString(Controller.getDataServidorBD()));
        labelDataRegistroDebito.setText(PempecParse.dateToString(Controller.getDataServidorBD()));

        labelTipoLancamentoCredito.setVisible(false);
        labelDataRegistroCredito.setVisible(false);
        labelTipoLancamentoDebito.setVisible(false);
        labelDataRegistroDebito.setVisible(false);

        labelExportCredito.setVisible(false);
        labelExportDebito.setVisible(false);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaCredito = new javax.swing.JPanel();
        campoCodigoCredito = new javax.swing.JTextField();
        labelDescricao8 = new javax.swing.JLabel();
        labelResponsavel = new javax.swing.JLabel();
        comboResponsavelCredito = new javax.swing.JComboBox();
        labelDataVencimento8 = new javax.swing.JLabel();
        labelNumeroDocumento4 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        comboHistoricoCredito = new javax.swing.JComboBox();
        labelDescricao9 = new javax.swing.JLabel();
        jTObservacaoCredito = new javax.swing.JTextField();
        comboTituloCredito = new javax.swing.JComboBox();
        jFTValorCredito = new br.com.pempec.componentes.JDoubleField();
        labelValor = new javax.swing.JLabel();
        labelDataEmissao4 = new javax.swing.JLabel();
        labelDataRegistroCredito = new javax.swing.JLabel();
        labelTipoLancamentoCredito = new javax.swing.JLabel();
        comboSacado = new javax.swing.JComboBox();
        labelCedente3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        botaoCreditar = new javax.swing.JButton();
        botaoFecharCredito = new javax.swing.JButton();
        botaoLimparCredito = new javax.swing.JButton();
        botaoAlterarCredito = new javax.swing.JButton();
        botaoEstornarCredito = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        botaoGerarNumeroDocumentoCredito = new javax.swing.JButton();
        jTDescricaoCredito = new javax.swing.JTextField();
        jFTDataMovimentoCredito = new org.jdesktop.swingx.JXDatePicker();
        jFTDataContabilCredito = new org.jdesktop.swingx.JXDatePicker();
        labelExportCredito = new javax.swing.JLabel();
        abaDebito = new javax.swing.JPanel();
        campoCodigoDebito = new javax.swing.JTextField();
        labelDescricao2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboResponsavelDebito = new javax.swing.JComboBox();
        jTDescricaoDebito = new javax.swing.JTextField();
        labelDataVencimento1 = new javax.swing.JLabel();
        botaoGerarNumeroDocumentoDebito = new javax.swing.JButton();
        labelNumeroDocumento1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboHistoricoDebito = new javax.swing.JComboBox();
        labelDescricao3 = new javax.swing.JLabel();
        jTObservacaoDebito = new javax.swing.JTextField();
        comboTituloDebito = new javax.swing.JComboBox();
        jFTValorDebito = new br.com.pempec.componentes.JDoubleField();
        labelDataVencimento3 = new javax.swing.JLabel();
        labelDataEmissao1 = new javax.swing.JLabel();
        labelDataRegistroDebito = new javax.swing.JLabel();
        labelTipoLancamentoDebito = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        botaoFecharDebito = new javax.swing.JButton();
        botaoLimparDebito = new javax.swing.JButton();
        botaoDebitar = new javax.swing.JButton();
        botaoEstornarDebito = new javax.swing.JButton();
        botaoAlterarDebito = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        comboCedente = new javax.swing.JComboBox();
        labelCedente = new javax.swing.JLabel();
        jFTDataMovimentoDebito = new org.jdesktop.swingx.JXDatePicker();
        jFTDataContabilDebito = new org.jdesktop.swingx.JXDatePicker();
        labelExportDebito = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Tesouraria Lançamento");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        abaCredito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 204)));
        abaCredito.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                abaCreditoComponentShown(evt);
            }
        });

        campoCodigoCredito.setEditable(false);

        labelDescricao8.setText("Descrição");

        labelResponsavel.setText("Responsável");

        comboResponsavelCredito.setFont(new java.awt.Font("Arial", 0, 10));

        labelDataVencimento8.setText("Data Movimento");

        labelNumeroDocumento4.setText("Número do Documento");

        jLabel16.setText("Histórico");

        comboHistoricoCredito.setFont(new java.awt.Font("Arial", 0, 10));

        labelDescricao9.setText("Observação");

        comboTituloCredito.setToolTipText("");
        comboTituloCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloCreditoActionPerformed(evt);
            }
        });

        labelValor.setText("Valor");

        labelDataEmissao4.setText("Data Contabil");

        labelDataRegistroCredito.setFont(new java.awt.Font("Arial", 0, 10));
        labelDataRegistroCredito.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Registro"));

        labelTipoLancamentoCredito.setFont(new java.awt.Font("Arial", 0, 10));
        labelTipoLancamentoCredito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoLancamentoCredito.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo Lançamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10))); // NOI18N
        labelTipoLancamentoCredito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        comboSacado.setFont(new java.awt.Font("Arial", 0, 11));

        labelCedente3.setText("Sacado");

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoCreditar.setMnemonic('C');
        botaoCreditar.setText("Creditar");
        botaoCreditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCreditarActionPerformed(evt);
            }
        });

        botaoFecharCredito.setMnemonic('F');
        botaoFecharCredito.setText("Fechar");
        botaoFecharCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharCreditoActionPerformed(evt);
            }
        });

        botaoLimparCredito.setMnemonic('L');
        botaoLimparCredito.setText("Limpar");
        botaoLimparCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparCreditoActionPerformed(evt);
            }
        });

        botaoAlterarCredito.setMnemonic('A');
        botaoAlterarCredito.setText("Alterar");
        botaoAlterarCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarCreditoActionPerformed(evt);
            }
        });

        botaoEstornarCredito.setMnemonic('E');
        botaoEstornarCredito.setText("Estornar");
        botaoEstornarCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEstornarCreditoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoCreditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterarCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEstornarCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimparCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoCreditar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoAlterarCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoEstornarCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimparCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFecharCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botaoGerarNumeroDocumentoCredito.setFont(new java.awt.Font("Arial", 0, 10));
        botaoGerarNumeroDocumentoCredito.setText("Gerar");
        botaoGerarNumeroDocumentoCredito.setToolTipText("Gerar Número Documento");
        botaoGerarNumeroDocumentoCredito.setActionCommand("botaoGerarNumeroDocumento");
        botaoGerarNumeroDocumentoCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarNumeroDocumentoCreditoActionPerformed(evt);
            }
        });

        labelExportCredito.setBackground(new java.awt.Color(0, 204, 204));
        labelExportCredito.setFont(new java.awt.Font("Arial", 0, 10));
        labelExportCredito.setForeground(new java.awt.Color(255, 0, 0));
        labelExportCredito.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)), "Exportação"));

        javax.swing.GroupLayout abaCreditoLayout = new javax.swing.GroupLayout(abaCredito);
        abaCredito.setLayout(abaCreditoLayout);
        abaCreditoLayout.setHorizontalGroup(
            abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCreditoLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCreditoLayout.createSequentialGroup()
                        .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaCreditoLayout.createSequentialGroup()
                                .addComponent(comboTituloCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(botaoGerarNumeroDocumentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCodigoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelNumeroDocumento4))
                        .addGap(28, 28, 28)
                        .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaCreditoLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(labelValor))
                            .addComponent(jFTValorCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataVencimento8)
                            .addComponent(jFTDataMovimentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTDataContabilCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataEmissao4))
                        .addContainerGap(43, Short.MAX_VALUE))
                    .addGroup(abaCreditoLayout.createSequentialGroup()
                        .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaCreditoLayout.createSequentialGroup()
                                .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(comboHistoricoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCedente3)
                                    .addComponent(comboSacado, 0, 439, Short.MAX_VALUE)))
                            .addComponent(labelDescricao9, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescricao8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaCreditoLayout.createSequentialGroup()
                                .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboResponsavelCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelResponsavel))
                                .addGap(28, 28, 28)
                                .addComponent(labelExportCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelDataRegistroCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelTipoLancamentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTObservacaoCredito, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTDescricaoCredito, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap())))
        );
        abaCreditoLayout.setVerticalGroup(
            abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCreditoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCreditoLayout.createSequentialGroup()
                        .addComponent(labelNumeroDocumento4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(botaoGerarNumeroDocumentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTituloCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTValorCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelValor)
                    .addGroup(abaCreditoLayout.createSequentialGroup()
                        .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDataVencimento8)
                            .addComponent(labelDataEmissao4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTDataMovimentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTDataContabilCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(labelCedente3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboHistoricoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(labelDescricao8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTDescricaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(labelDescricao9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTObservacaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(abaCreditoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDataRegistroCredito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCreditoLayout.createSequentialGroup()
                        .addComponent(labelResponsavel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboResponsavelCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelExportCredito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipoLancamentoCredito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        jTabbedPane1.addTab("Créditos", abaCredito);

        abaDebito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)));
        abaDebito.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                abaDebitosComponentShown(evt);
            }
        });

        campoCodigoDebito.setEditable(false);

        labelDescricao2.setText("Descrição");

        jLabel9.setText("Responsável");

        comboResponsavelDebito.setFont(new java.awt.Font("Arial", 0, 10));

        labelDataVencimento1.setText("Data Movimento");

        botaoGerarNumeroDocumentoDebito.setFont(new java.awt.Font("Arial", 0, 10));
        botaoGerarNumeroDocumentoDebito.setText("Gerar");
        botaoGerarNumeroDocumentoDebito.setToolTipText("Gerar Número Documento");
        botaoGerarNumeroDocumentoDebito.setActionCommand("botaoGerarNumeroDocumento");
        botaoGerarNumeroDocumentoDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarNumeroDocumentoDebitoActionPerformed(evt);
            }
        });

        labelNumeroDocumento1.setText("Número do Documento");

        jLabel10.setText("Histórico");

        comboHistoricoDebito.setFont(new java.awt.Font("Arial", 0, 10));

        labelDescricao3.setText("Observação");

        comboTituloDebito.setToolTipText("");
        comboTituloDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloDebitoActionPerformed(evt);
            }
        });

        labelDataVencimento3.setText("Valor");

        labelDataEmissao1.setText("Data Contabil");

        labelDataRegistroDebito.setFont(new java.awt.Font("Arial", 0, 10));
        labelDataRegistroDebito.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Registro"));

        labelTipoLancamentoDebito.setFont(new java.awt.Font("Arial", 0, 10));
        labelTipoLancamentoDebito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoLancamentoDebito.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo Lançamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10))); // NOI18N
        labelTipoLancamentoDebito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jPanel3.setPreferredSize(new java.awt.Dimension(734, 65));

        botaoFecharDebito.setMnemonic('F');
        botaoFecharDebito.setText("Fechar");
        botaoFecharDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharDebitoActionPerformed(evt);
            }
        });

        botaoLimparDebito.setMnemonic('L');
        botaoLimparDebito.setText("Limpar");
        botaoLimparDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparDebitoActionPerformed(evt);
            }
        });

        botaoDebitar.setMnemonic('D');
        botaoDebitar.setText("Debitar");
        botaoDebitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDebitarActionPerformed(evt);
            }
        });

        botaoEstornarDebito.setMnemonic('E');
        botaoEstornarDebito.setText("Estornar");
        botaoEstornarDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEstornarDebitoActionPerformed(evt);
            }
        });

        botaoAlterarDebito.setMnemonic('A');
        botaoAlterarDebito.setText("Alterar");
        botaoAlterarDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarDebitoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoDebitar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEstornarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimparDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoDebitar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoAlterarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoEstornarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimparDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFecharDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        comboCedente.setFont(new java.awt.Font("Arial", 0, 11));

        labelCedente.setText("Cedente");

        labelExportDebito.setBackground(new java.awt.Color(0, 204, 204));
        labelExportDebito.setFont(new java.awt.Font("Arial", 0, 10));
        labelExportDebito.setForeground(new java.awt.Color(255, 0, 0));
        labelExportDebito.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)), "Exportação"));

        javax.swing.GroupLayout abaDebitoLayout = new javax.swing.GroupLayout(abaDebito);
        abaDebito.setLayout(abaDebitoLayout);
        abaDebitoLayout.setHorizontalGroup(
            abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDebitoLayout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                .addComponent(labelDescricao3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 676, Short.MAX_VALUE))
                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelNumeroDocumento1)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaDebitoLayout.createSequentialGroup()
                                        .addComponent(comboTituloDebito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(botaoGerarNumeroDocumentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(comboHistoricoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(campoCodigoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCedente)
                                    .addGroup(abaDebitoLayout.createSequentialGroup()
                                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelDataVencimento3)
                                            .addComponent(jFTValorDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(36, 36, 36)
                                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFTDataMovimentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelDataVencimento1))
                                        .addGap(50, 50, 50)
                                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                                .addComponent(labelDataEmissao1)
                                                .addGap(50, 50, 50))
                                            .addComponent(jFTDataContabilDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(comboCedente, 0, 427, Short.MAX_VALUE)))
                            .addComponent(jLabel10)
                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                .addComponent(labelDescricao2)
                                .addGap(198, 198, 198))
                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(abaDebitoLayout.createSequentialGroup()
                                        .addComponent(comboResponsavelDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(37, 37, 37)
                                        .addComponent(labelExportDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                                .addComponent(labelDataRegistroDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelTipoLancamentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addComponent(jTDescricaoDebito, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
                            .addComponent(jTObservacaoDebito, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE))
                        .addGap(45, 45, 45))))
        );
        abaDebitoLayout.setVerticalGroup(
            abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDebitoLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                .addComponent(labelNumeroDocumento1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboTituloDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(labelCedente)))
                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                .addComponent(labelDataVencimento1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTDataMovimentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboHistoricoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(abaDebitoLayout.createSequentialGroup()
                            .addComponent(labelDataVencimento3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(campoCodigoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(botaoGerarNumeroDocumentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFTValorDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(abaDebitoLayout.createSequentialGroup()
                            .addComponent(labelDataEmissao1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFTDataContabilDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(labelDescricao2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTDescricaoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(labelDescricao3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTObservacaoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelExportDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboResponsavelDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelDataRegistroDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipoLancamentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Débitos", abaDebito);
        abaDebito.getAccessibleContext().setAccessibleName("abaDebitos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 861, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private Boolean validaCamposDebitos() {


        if (jFTValorDebito.getValue() < 0.01) {
            exibeMensagemAviso("Valor não pode ser inferior ou igual a R$0,00!", null);
            jFTValorDebito.requestFocus();
            return false;

        }

        if (comboResponsavelDebito.getSelectedIndex() == 0) {
            comboResponsavelDebito.requestFocus();
            return false;
        }

        if (comboHistoricoDebito.getSelectedIndex() == 0) {
            comboHistoricoDebito.requestFocus();
            return false;
        }

        if (comboCedente.getSelectedItem() == null) {
            comboCedente.requestFocus();
            return false;
        }



        if (jTDescricaoDebito.getText().isEmpty()) {
            jTDescricaoDebito.requestFocus();
            return false;
        }

        if (jFTDataContabilDebito.getDate() == null) {
            jFTDataContabilDebito.requestFocus();
            return false;
        }

        if (jFTDataMovimentoDebito.getDate() == null) {
            jFTDataMovimentoDebito.requestFocus();
            return false;
        }


        if (jFTDataMovimentoDebito.getDate() != null) {
            Date dataInformada = jFTDataMovimentoDebito.getDate();
            if (dataInformada.after(Controller.getDataServidorBD())) {
                exibeMensagemAviso("A Data do Movimento não pode ser superior a HOJE.!", null);
                jFTDataMovimentoDebito.requestFocus();
                return false;
            }
        }

        if (jFTDataContabilDebito.getDate() != null) {
            Date dataInformada = jFTDataContabilDebito.getDate();
            if (dataInformada.after(Controller.getDataServidorBD())) {
                exibeMensagemAviso("A Data Contabil não pode ser superior a HOJE.!", null);
                jFTDataContabilDebito.requestFocus();
                return false;
            }
        }



        return true;
    }

    private Boolean validaCamposCreditos() {


        if (jFTValorCredito.getValue() < 0.01) {
            exibeMensagemAviso("Valor não pode ser inferior ou igual a R$0,00!", null);
            jFTValorCredito.requestFocus();
            return false;

        }


        if (comboResponsavelCredito.getSelectedIndex() == 0) {
            comboResponsavelCredito.requestFocus();
            return false;
        }

        if (comboSacado.getSelectedItem() == null) {
            comboSacado.requestFocus();
            return false;
        }

        if (jTDescricaoCredito.getText().isEmpty()) {
            jTDescricaoCredito.requestFocus();
            return false;
        }


        if (jFTDataContabilCredito.getDate() == null) {
            jFTDataContabilCredito.requestFocus();
            return false;
        }

        if (jFTDataMovimentoCredito.getDate() == null) {
            jFTDataMovimentoCredito.requestFocus();
            return false;
        }



        if (jFTDataMovimentoCredito.getDate() != null) {
            Date dataInformada = jFTDataMovimentoCredito.getDate();
            if (dataInformada.after(Controller.getDataServidorBD())) {
                exibeMensagemAviso("A Data do Movimento não pode ser superior a HOJE.!", null);
                jFTDataMovimentoCredito.requestFocus();
                return false;
            }
        }

        if (jFTDataContabilCredito.getDate() != null) {
            Date dataInformada = jFTDataContabilCredito.getDate();
            if (dataInformada.after(Controller.getDataServidorBD())) {
                exibeMensagemAviso("A Data Contabil não pode ser superior a HOJE.!", null);
                jFTDataContabilCredito.requestFocus();
                return false;
            }
        }


        return true;
    }

private void botaoGerarNumeroDocumentoCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarNumeroDocumentoCreditoActionPerformed
    String numero = PempecKeyGenerator.generateNumeroDocumentoTesourariaCredito();
    comboTituloCredito.setSelectedItem(numero);
    comboTituloCredito.requestFocus();
}//GEN-LAST:event_botaoGerarNumeroDocumentoCreditoActionPerformed

private void botaoGerarNumeroDocumentoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarNumeroDocumentoDebitoActionPerformed
    String numero = PempecKeyGenerator.generateNumeroDocumentoTesourariaDebito();
    comboTituloDebito.setSelectedItem(numero);
    comboTituloDebito.requestFocus();
}//GEN-LAST:event_botaoGerarNumeroDocumentoDebitoActionPerformed

private void comboTituloDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTituloDebitoActionPerformed
    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTituloDebito.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            TesourariaDebitoModel tab = new TesourariaDebitoModel();

            tab.setNumeroDocumento(comboTituloDebito.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = tesourariaDebitoBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {


                if (tab.getNumeroDocumento() != null) {
                    botaoAlterarDebito.setEnabled(true);
                    botaoEstornarDebito.setEnabled(true);
                    botaoDebitar.setEnabled(false);
                    botaoGerarNumeroDocumentoDebito.setEnabled(false);
                    labelTipoLancamentoDebito.setVisible(true);
                    labelDataRegistroDebito.setVisible(true);

                    if (tab.getHistorico() != null && tab.getHistorico().getPk().getId().equalsIgnoreCase(Constantes.HISTORICO_TESOURARIA_ESPECIE_PAGO)) {

                        botaoDebitar.setEnabled(false);
                        botaoAlterarDebito.setEnabled(false);
                        botaoEstornarDebito.setEnabled(false);
                        botaoGerarNumeroDocumentoDebito.setEnabled(false);
                    }

                } else {
                    botaoAlterarDebito.setEnabled(false);
                    botaoEstornarDebito.setEnabled(false);
                    botaoDebitar.setEnabled(true);
                    botaoGerarNumeroDocumentoDebito.setEnabled(true);
                }

                if (tab.getLoteContabil() != null && tab.getLoteContabil().getPk() != null) {

                    botaoAlterarDebito.setEnabled(false);
                    botaoEstornarDebito.setEnabled(false);
                    botaoDebitar.setEnabled(false);

                    labelExportDebito.setVisible(true);
                    labelExportDebito.setText("Lanç. exportado sob num. " + tab.getLoteContabil().getLote());

                }

                campoCodigoDebito.setText(tab.getPk().getId());
                jFTDataContabilDebito.setDate(tab.getDataContabil());
                jFTDataMovimentoDebito.setDate(tab.getDataMovimento());
                Double valor = tab.getValorNominal();
                jFTValorDebito.setText(PempecParse.doubleToZero(valor));
                jTDescricaoDebito.setText(tab.getDescricao());
                jTObservacaoDebito.setText(tab.getObservacao());
                labelDataRegistroDebito.setText(PempecParse.dateToString(tab.getDataRegistro()));
                labelTipoLancamentoDebito.setText(tab.getTipoLancamento());



                if (tab.getResponsavel() != null) {

                    for (int i = 1; i < comboResponsavelDebito.getItemCount(); i++) {
                        if (tab.getResponsavel().getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboResponsavelDebito.getItemAt(i)).getPk().getId())) {
                            comboResponsavelDebito.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getHistorico() != null) {

                    for (int i = 1; i < comboHistoricoDebito.getItemCount(); i++) {
                        if (tab.getHistorico().getPk().getId().equalsIgnoreCase(((HistoricoModel) comboHistoricoDebito.getItemAt(i)).getPk().getId())) {
                            comboHistoricoDebito.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getCedenteModel() != null) {

                    for (int i = 1; i < comboCedente.getItemCount(); i++) {
                        if (tab.getCedenteModel().getPk().getId().equalsIgnoreCase(((CedenteModel) comboCedente.getItemAt(i)).getPk().getId())) {
                            comboCedente.setSelectedIndex(i);
                            break;
                        }
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

    }
}//GEN-LAST:event_comboTituloDebitoActionPerformed

private void abaDebitosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_abaDebitosComponentShown
    botaoDebitar.setEnabled(true);
    botaoAlterarDebito.setEnabled(false);
    botaoEstornarDebito.setEnabled(false);

}//GEN-LAST:event_abaDebitosComponentShown

private void botaoFecharDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharDebitoActionPerformed
    this.botaoFecharCreditoActionPerformed(evt);
}//GEN-LAST:event_botaoFecharDebitoActionPerformed

private void botaoLimparDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparDebitoActionPerformed
    comboTituloDebito.setSelectedItem(null);
    comboCedente.setSelectedItem(null);
    comboResponsavelDebito.setSelectedIndex(0);
    comboHistoricoDebito.setSelectedIndex(0);

    labelTipoLancamentoDebito.setVisible(false);
    labelDataRegistroDebito.setVisible(false);

    jTObservacaoDebito.setText("");
    campoCodigoDebito.setText("");
    jFTValorDebito.setText("0,00");
    jTDescricaoDebito.setText("");
    botaoGerarNumeroDocumentoDebito.setEnabled(true);
    botaoAlterarDebito.setEnabled(false);
    botaoEstornarDebito.setEnabled(false);
    botaoDebitar.setEnabled(true);

    labelExportDebito.setVisible(false);


}//GEN-LAST:event_botaoLimparDebitoActionPerformed

private void botaoDebitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDebitarActionPerformed
// contas contabil
    // a conta debito sera a conta vinculada ao cedente
    // a conta credito sera conta vinculada ao historico = caixa

    String valorCombo = null;

    if (comboTituloDebito.getSelectedItem() != null) {
        valorCombo = comboTituloDebito.getSelectedItem().toString();
    }

    if (valorCombo != null) {

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(telaDebito, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCamposDebitos()) {

                TesourariaDebitoModel tab = new TesourariaDebitoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(PempecKeyGenerator.generateKey());

                tab.setNumeroDocumento(valorCombo);

                tab.setDescricao(jTDescricaoDebito.getText().toUpperCase());

                tab.setObservacao(jTObservacaoDebito.getText());

                tab.setTipoLancamento("D");

                tab.setDataRegistro(Controller.getDataServidorBD());

                tab.setUsuario(Controller.getUsuarioLogado());

                tab.setValorNominal(jFTValorDebito.getValue());



                if (jFTDataMovimentoDebito.getDate() != null) {
                    tab.setDataMovimento(jFTDataMovimentoDebito.getDate());
                }

                if (jFTDataContabilDebito.getDate() != null) {
                    tab.setDataContabil(jFTDataContabilDebito.getDate());
                }


                if (comboResponsavelDebito.getSelectedItem() != null && ((FuncionarioModel) comboResponsavelDebito.getSelectedItem()).getPk() != null) {
                    tab.setResponsavel(new FuncionarioModel());
                    tab.getResponsavel().setPk(new PKModel());
                    tab.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavelDebito.getSelectedItem()).getPk().getId());
                }

                if (comboHistoricoDebito.getSelectedItem() != null && ((HistoricoModel) comboHistoricoDebito.getSelectedItem()).getPk() != null) {
                    tab.setHistorico(new HistoricoModel());
                    tab.getHistorico().setPk(new PKModel());
                    tab.getHistorico().getPk().setId(((HistoricoModel) comboHistoricoDebito.getSelectedItem()).getPk().getId());
                }

                if (comboCedente.getSelectedItem() != null && ((CedenteModel) comboCedente.getSelectedItem()).getPk() != null) {
                    tab.setCedenteModel(new CedenteModel());
                    tab.getCedenteModel().setPk(new PKModel());
                    tab.getCedenteModel().getPk().setId(((CedenteModel) comboCedente.getSelectedItem()).getPk().getId());
                }

                // tab.setMovimentoDiarioModel(registroMovimento("Inserir", tab.getNumeroDocumento(), ((HistoricoModel) comboHistoricoDebito.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), tab.getValorNominal(), "Inserido"));
                tab.setMovimentoDiarioModel(registroMovimento("Inserir", tab.getNumeroDocumento(), tab.getNumeroDocumento() + " para " + tab.getDescricao(), tab.getValorNominal(), "Inserido"));
                tesourariaDebitoBO.inserir(tab);

                this.botaoLimparDebitoActionPerformed(evt);


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

    } else {
        exibeMensagemAviso("O Número do documento a debitar é obrigatório.", null);
    }


}//GEN-LAST:event_botaoDebitarActionPerformed

private void botaoEstornarDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEstornarDebitoActionPerformed
    String valorCombo = null;

    if (comboTituloDebito.getSelectedItem() != null) {
        valorCombo = comboTituloDebito.getSelectedItem().toString();
    }

    if (valorCombo != null) {

        try {

            long action = Action.OUTROS.getAction();

            if (!Controller.checarPermissao(telaDebito, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCamposDebitos()) {

                TesourariaDebitoModel tab = new TesourariaDebitoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.setNumeroDocumento(valorCombo);

                tab.getPk().setId(campoCodigoDebito.getText());

                tab = tesourariaDebitoBO.consultarPorTemplate(tab);

                // tab.setMovimentoDiarioModel(registroMovimento("Estornar", tab.getNumeroDocumento(), ((HistoricoModel) comboHistoricoDebito.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), tab.getValorNominal(), "Estornado"));
                tab.setMovimentoDiarioModel(registroMovimento("Estornar", tab.getNumeroDocumento(), tab.getNumeroDocumento() + " para " + tab.getDescricao(), tab.getValorNominal(), "Estornado"));
                tab.getMovimentoDiarioModel().setObservacao("Lanc. Tesour. Debito Estornado ");

                tesourariaDebitoBO.excluir(tab);

                this.botaoLimparDebitoActionPerformed(evt);


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

    } else {
        exibeMensagemAviso("O Número do documento é obrigatório.", null);
    }
}//GEN-LAST:event_botaoEstornarDebitoActionPerformed

private void botaoAlterarDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarDebitoActionPerformed
    String valorCombo = null;

    if (comboTituloDebito.getSelectedItem() != null) {
        valorCombo = comboTituloDebito.getSelectedItem().toString();
    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(telaDebito, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCamposDebitos()) {

                TesourariaDebitoModel tab = new TesourariaDebitoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.setNumeroDocumento(valorCombo);

                tab.getPk().setId(campoCodigoDebito.getText());

                tab = tesourariaDebitoBO.consultarPorTemplate(tab);

                tab.setDescricao(jTDescricaoDebito.getText().toUpperCase());

                tab.setObservacao(jTObservacaoDebito.getText());

                tab.setTipoLancamento("D");

                tab.setDataRegistro(PempecParse.stringToDate(labelDataRegistroDebito.getText()));

                tab.setUsuario(Controller.getUsuarioLogado());

                tab.setValorNominal(jFTValorDebito.getValue());


                if (jFTDataMovimentoDebito.getDate() != null) {
                    tab.setDataMovimento(jFTDataMovimentoDebito.getDate());
                }

                if (jFTDataContabilDebito.getDate() != null) {
                    tab.setDataContabil(jFTDataContabilDebito.getDate());
                }


                if (comboResponsavelDebito.getSelectedItem() != null && ((FuncionarioModel) comboResponsavelDebito.getSelectedItem()).getPk() != null) {
                    tab.setResponsavel(new FuncionarioModel());
                    tab.getResponsavel().setPk(new PKModel());
                    tab.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavelDebito.getSelectedItem()).getPk().getId());
                }

                if (comboHistoricoDebito.getSelectedItem() != null && ((HistoricoModel) comboHistoricoDebito.getSelectedItem()).getPk() != null) {
                    tab.setHistorico(new HistoricoModel());
                    tab.getHistorico().setPk(new PKModel());
                    tab.getHistorico().getPk().setId(((HistoricoModel) comboHistoricoDebito.getSelectedItem()).getPk().getId());
                }

                if (comboCedente.getSelectedItem() != null && ((CedenteModel) comboCedente.getSelectedItem()).getPk() != null) {
                    tab.setCedenteModel(new CedenteModel());
                    tab.getCedenteModel().setPk(new PKModel());
                    tab.getCedenteModel().getPk().setId(((CedenteModel) comboCedente.getSelectedItem()).getPk().getId());
                }

                //tab.setMovimentoDiarioModel(registroMovimento("Alterar", tab.getNumeroDocumento(), ((HistoricoModel) comboHistoricoDebito.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), tab.getValorNominal(), "Alterado"));
                tab.setMovimentoDiarioModel(registroMovimento("Alterar", tab.getNumeroDocumento(), tab.getNumeroDocumento() + " para " + tab.getDescricao(), tab.getValorNominal(), "Alterado"));
                tesourariaDebitoBO.alterar(tab);


                this.botaoLimparDebitoActionPerformed(evt);


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

    } else {
        exibeMensagemAviso("O Número do documento a debitar é obrigatório.", null);
    }

}//GEN-LAST:event_botaoAlterarDebitoActionPerformed

private void comboTituloCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTituloCreditoActionPerformed
    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTituloCredito.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            TesourariaCreditoModel tab = new TesourariaCreditoModel();

            tab.setNumeroDocumento(comboTituloCredito.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = tesourariaCreditoBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {
                if (tab.getNumeroDocumento() != null) {
                    botaoCreditar.setEnabled(false);
                    botaoAlterarCredito.setEnabled(true);
                    botaoEstornarCredito.setEnabled(true);
                    botaoGerarNumeroDocumentoCredito.setEnabled(false);
                    labelTipoLancamentoCredito.setVisible(true);
                    labelDataRegistroCredito.setVisible(true);

                    if (tab.getHistorico() != null && tab.getHistorico().getPk().getId().equalsIgnoreCase(Constantes.HISTORICO_TESOURARIA_ESPECIE_RECEBIDO) || tab.getHistorico().getPk().getId().equalsIgnoreCase(Constantes.HISTORICO_TESOURARIA_CHEQUE_RECEBIDO)) {

                        botaoCreditar.setEnabled(false);
                        botaoAlterarCredito.setEnabled(false);
                        botaoEstornarCredito.setEnabled(false);
                        botaoGerarNumeroDocumentoCredito.setEnabled(false);
                    }

                } else {
                    botaoCreditar.setEnabled(true);
                    botaoAlterarCredito.setEnabled(false);
                    botaoEstornarCredito.setEnabled(false);
                    botaoGerarNumeroDocumentoCredito.setEnabled(true);
                }

                if (tab.getLoteContabil() != null && tab.getLoteContabil().getPk() != null) {

                    botaoAlterarCredito.setEnabled(false);
                    botaoEstornarCredito.setEnabled(false);
                    botaoCreditar.setEnabled(false);

                    labelExportCredito.setVisible(true);
                    labelExportCredito.setText("Lanç. exportado sob num. " + tab.getLoteContabil().getLote());

                }


                campoCodigoCredito.setText(tab.getPk().getId());
                jFTDataContabilCredito.setDate(tab.getDataContabil());
                jFTDataMovimentoCredito.setDate(tab.getDataMovimento());
                Double valor = tab.getValorNominal();
                jFTValorCredito.setText(PempecParse.doubleToZero(valor));
                jTDescricaoCredito.setText(tab.getDescricao());
                jTObservacaoCredito.setText(tab.getObservacao());
                labelDataRegistroCredito.setText(PempecParse.dateToString(tab.getDataRegistro()));
                labelTipoLancamentoCredito.setText(tab.getTipoLancamento());



                if (tab.getResponsavel() != null) {
                    for (int i = 1; i < comboResponsavelCredito.getItemCount(); i++) {
                        if (tab.getResponsavel().getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboResponsavelCredito.getItemAt(i)).getPk().getId())) {
                            comboResponsavelCredito.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getHistorico() != null) {
                    for (int i = 1; i < comboHistoricoCredito.getItemCount(); i++) {
                        if (tab.getHistorico().getPk().getId().equalsIgnoreCase(((HistoricoModel) comboHistoricoCredito.getItemAt(i)).getPk().getId())) {
                            comboHistoricoCredito.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getSacadoModel() != null) {

                    for (int i = 1; i < comboSacado.getItemCount(); i++) {
                        if (tab.getSacadoModel().getPk().getId().equalsIgnoreCase(((SacadoModel) comboSacado.getItemAt(i)).getPk().getId())) {
                            comboSacado.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getHistorico() != null && tab.getHistorico().getPk().getId().equalsIgnoreCase(Constantes.HISTORICO_TESOURARIA_TRANSFERE_BCO_TESOURARIA)) {

                    botaoAlterarCredito.setEnabled(false);

//                    if (exibeMensagemConfirmacao("Lançamento registrado na conta bancária. \n O estorno poderá causar inconsistência no saldo bancário.\n \n Deseja prosseguir? ", "Transferencia do Banco")) {
//
//                        botaoEstornarCredito.setEnabled(true);
//
//                    } else {
//
//                        botaoEstornarCredito.setEnabled(false);
//                    }
//
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

    }
}//GEN-LAST:event_comboTituloCreditoActionPerformed

private void botaoCreditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCreditarActionPerformed
    String valorCombo = null;

    if (comboTituloCredito.getSelectedItem() != null) {
        valorCombo = comboTituloCredito.getSelectedItem().toString();
    }

    if (valorCombo != null) {

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(telaCredito, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }


            if (validaCamposCreditos()) {

                TesourariaCreditoModel tab = new TesourariaCreditoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(PempecKeyGenerator.generateKey());

                tab.setNumeroDocumento(valorCombo);

                tab.setDescricao(jTDescricaoCredito.getText().toUpperCase());

                tab.setObservacao(jTObservacaoCredito.getText());

                tab.setTipoLancamento("C");

                tab.setDataRegistro(Controller.getDataServidorBD());

                tab.setUsuario(Controller.getUsuarioLogado());

                tab.setValorNominal(jFTValorCredito.getValue());



                if (jFTDataMovimentoCredito.getDate() != null) {
                    tab.setDataMovimento(jFTDataMovimentoCredito.getDate());
                }

                if (jFTDataContabilCredito.getDate() != null) {
                    tab.setDataContabil(jFTDataContabilCredito.getDate());
                }


                if (comboResponsavelCredito.getSelectedItem() != null && ((FuncionarioModel) comboResponsavelCredito.getSelectedItem()).getPk() != null) {
                    tab.setResponsavel(new FuncionarioModel());
                    tab.getResponsavel().setPk(new PKModel());
                    tab.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavelCredito.getSelectedItem()).getPk().getId());
                }

                if (comboHistoricoCredito.getSelectedItem() != null && ((HistoricoModel) comboHistoricoCredito.getSelectedItem()).getPk() != null) {
                    tab.setHistorico(new HistoricoModel());
                    tab.getHistorico().setPk(new PKModel());
                    tab.getHistorico().getPk().setId(((HistoricoModel) comboHistoricoCredito.getSelectedItem()).getPk().getId());
                }

                if (comboSacado.getSelectedItem() != null && ((SacadoModel) comboSacado.getSelectedItem()).getPk() != null) {
                    tab.setSacadoModel(new SacadoModel());
                    tab.getSacadoModel().setPk(new PKModel());
                    tab.getSacadoModel().getPk().setId(((SacadoModel) comboSacado.getSelectedItem()).getPk().getId());
                }

                tab.setMovimentoDiarioModel(registroMovimento("Inserir", tab.getNumeroDocumento(), tab.getNumeroDocumento() + " para " + tab.getDescricao(), tab.getValorNominal(), "Inserido"));
                tesourariaCreditoBO.inserir(tab);

                this.botaoLimparCreditoActionPerformed(evt);


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

    } else {
        exibeMensagemAviso("O Número do documento a creditar é obrigatório.", null);
    }

}//GEN-LAST:event_botaoCreditarActionPerformed

private void botaoFecharCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCreditoActionPerformed
    setVisible(false);
}//GEN-LAST:event_botaoFecharCreditoActionPerformed

private void botaoLimparCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparCreditoActionPerformed
    comboTituloCredito.setSelectedItem(null);
    comboSacado.setSelectedItem(null);
    comboResponsavelCredito.setSelectedIndex(0);
    comboHistoricoCredito.setSelectedIndex(0);

    labelTipoLancamentoCredito.setVisible(false);
    labelDataRegistroCredito.setVisible(false);

    jTObservacaoCredito.setText("");
    campoCodigoCredito.setText("");
    jFTValorCredito.setText("0,00");
    jTDescricaoCredito.setText("");
    botaoGerarNumeroDocumentoCredito.setEnabled(true);
    botaoCreditar.setEnabled(true);
    botaoAlterarCredito.setEnabled(false);
    botaoEstornarCredito.setEnabled(false);
    labelExportCredito.setVisible(false);

}//GEN-LAST:event_botaoLimparCreditoActionPerformed

private void botaoAlterarCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarCreditoActionPerformed
    String valorCombo = null;

    if (comboTituloCredito.getSelectedItem() != null) {
        valorCombo = comboTituloCredito.getSelectedItem().toString();
    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(telaCredito, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCamposCreditos()) {

                TesourariaCreditoModel tab = new TesourariaCreditoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.setNumeroDocumento(valorCombo);

                tab.getPk().setId(campoCodigoCredito.getText());

                tab = tesourariaCreditoBO.consultarPorTemplate(tab);

                tab.setDescricao(jTDescricaoCredito.getText().toUpperCase());

                tab.setObservacao(jTObservacaoCredito.getText());

                tab.setTipoLancamento("C");

                tab.setDataRegistro(PempecParse.stringToDate(labelDataRegistroCredito.getText()));

                tab.setUsuario(Controller.getUsuarioLogado());

                tab.setValorNominal(jFTValorCredito.getValue());


                if (jFTDataMovimentoCredito.getDate() != null) {
                    tab.setDataMovimento(jFTDataMovimentoCredito.getDate());
                }

                if (jFTDataContabilCredito.getDate() != null) {
                    tab.setDataContabil(jFTDataContabilCredito.getDate());
                }


                if (comboResponsavelCredito.getSelectedItem() != null && ((FuncionarioModel) comboResponsavelCredito.getSelectedItem()).getPk() != null) {
                    tab.setResponsavel(new FuncionarioModel());
                    tab.getResponsavel().setPk(new PKModel());
                    tab.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavelCredito.getSelectedItem()).getPk().getId());
                }

                if (comboHistoricoCredito.getSelectedItem() != null && ((HistoricoModel) comboHistoricoCredito.getSelectedItem()).getPk() != null) {
                    tab.setHistorico(new HistoricoModel());
                    tab.getHistorico().setPk(new PKModel());
                    tab.getHistorico().getPk().setId(((HistoricoModel) comboHistoricoCredito.getSelectedItem()).getPk().getId());
                }

                if (comboSacado.getSelectedItem() != null && ((SacadoModel) comboSacado.getSelectedItem()).getPk() != null) {
                    tab.setSacadoModel(new SacadoModel());
                    tab.getSacadoModel().setPk(new PKModel());
                    tab.getSacadoModel().getPk().setId(((SacadoModel) comboSacado.getSelectedItem()).getPk().getId());
                }

                tab.setMovimentoDiarioModel(registroMovimento("Alterar", tab.getNumeroDocumento(), tab.getNumeroDocumento() + " para " + tab.getDescricao(), tab.getValorNominal(), "Alterado"));

                tesourariaCreditoBO.alterar(tab);

                this.botaoLimparCreditoActionPerformed(evt);

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

    } else {
        exibeMensagemAviso("O Número do documento a debitar é obrigatório.", null);
    }
}//GEN-LAST:event_botaoAlterarCreditoActionPerformed

private void botaoEstornarCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEstornarCreditoActionPerformed
    String valorCombo = null;

    if (comboTituloCredito.getSelectedItem() != null) {
        valorCombo = comboTituloCredito.getSelectedItem().toString();
    }

    if (valorCombo != null) {

        try {

            long action = Action.OUTROS.getAction();

            if (!Controller.checarPermissao(telaCredito, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }


            if (validaCamposCreditos()) {

                TesourariaCreditoModel tab = new TesourariaCreditoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.setNumeroDocumento(valorCombo);

                tab.getPk().setId(campoCodigoCredito.getText());

                tab = tesourariaCreditoBO.consultarPorTemplate(tab);

                // tab.setMovimentoDiarioModel(registroMovimento("Estornar", tab.getNumeroDocumento(), ((HistoricoModel) comboHistoricoCredito.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), tab.getValorNominal(), "Estornado"));
                tab.setMovimentoDiarioModel(registroMovimento("Estornar", tab.getNumeroDocumento(), tab.getNumeroDocumento() + " para " + tab.getDescricao(), tab.getValorNominal(), "Estornado"));
                tab.getMovimentoDiarioModel().setObservacao("Lanc. Tesour. Credito Estornado ");

                tesourariaCreditoBO.excluir(tab);

                this.botaoLimparCreditoActionPerformed(evt);


            } else {

                exibeMensagemAviso("Campo(s) obrigatório(s)!", null);

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

        exibeMensagemAviso("O Número do documento é obrigatório.", null);

    }
}//GEN-LAST:event_botaoEstornarCreditoActionPerformed

private void abaCreditoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_abaCreditoComponentShown
    botaoCreditar.setEnabled(true);
    botaoAlterarCredito.setEnabled(false);
    botaoEstornarCredito.setEnabled(false);
}//GEN-LAST:event_abaCreditoComponentShown
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCredito;
    private javax.swing.JPanel abaDebito;
    private javax.swing.JButton botaoAlterarCredito;
    private javax.swing.JButton botaoAlterarDebito;
    private javax.swing.JButton botaoCreditar;
    private javax.swing.JButton botaoDebitar;
    private javax.swing.JButton botaoEstornarCredito;
    private javax.swing.JButton botaoEstornarDebito;
    private javax.swing.JButton botaoFecharCredito;
    private javax.swing.JButton botaoFecharDebito;
    private javax.swing.JButton botaoGerarNumeroDocumentoCredito;
    private javax.swing.JButton botaoGerarNumeroDocumentoDebito;
    protected javax.swing.JButton botaoLimparCredito;
    private javax.swing.JButton botaoLimparDebito;
    private javax.swing.JTextField campoCodigoCredito;
    private javax.swing.JTextField campoCodigoDebito;
    private javax.swing.JComboBox comboCedente;
    private javax.swing.JComboBox comboHistoricoCredito;
    private javax.swing.JComboBox comboHistoricoDebito;
    private javax.swing.JComboBox comboResponsavelCredito;
    private javax.swing.JComboBox comboResponsavelDebito;
    private javax.swing.JComboBox comboSacado;
    private javax.swing.JComboBox comboTituloCredito;
    private javax.swing.JComboBox comboTituloDebito;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private org.jdesktop.swingx.JXDatePicker jFTDataContabilCredito;
    private org.jdesktop.swingx.JXDatePicker jFTDataContabilDebito;
    private org.jdesktop.swingx.JXDatePicker jFTDataMovimentoCredito;
    private org.jdesktop.swingx.JXDatePicker jFTDataMovimentoDebito;
    private br.com.pempec.componentes.JDoubleField jFTValorCredito;
    private br.com.pempec.componentes.JDoubleField jFTValorDebito;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JTextField jTDescricaoCredito;
    private javax.swing.JTextField jTDescricaoDebito;
    private javax.swing.JTextField jTObservacaoCredito;
    private javax.swing.JTextField jTObservacaoDebito;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelCedente3;
    private javax.swing.JLabel labelDataEmissao1;
    private javax.swing.JLabel labelDataEmissao4;
    private javax.swing.JLabel labelDataRegistroCredito;
    private javax.swing.JLabel labelDataRegistroDebito;
    private javax.swing.JLabel labelDataVencimento1;
    private javax.swing.JLabel labelDataVencimento3;
    private javax.swing.JLabel labelDataVencimento8;
    private javax.swing.JLabel labelDescricao2;
    private javax.swing.JLabel labelDescricao3;
    private javax.swing.JLabel labelDescricao8;
    private javax.swing.JLabel labelDescricao9;
    private javax.swing.JLabel labelExportCredito;
    private javax.swing.JLabel labelExportDebito;
    private javax.swing.JLabel labelNumeroDocumento1;
    private javax.swing.JLabel labelNumeroDocumento4;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelTipoLancamentoCredito;
    private javax.swing.JLabel labelTipoLancamentoDebito;
    private javax.swing.JLabel labelValor;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportCredito;
    private AutoCompleteSupport supportDebito;
    private AutoCompleteSupport supportCedente;
    private AutoCompleteSupport supportSacado;
}
