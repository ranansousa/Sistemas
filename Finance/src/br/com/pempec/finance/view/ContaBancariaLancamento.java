package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaCreditoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.iterators.LancamentoContaBancariaCreditoTextFilterator;
import br.com.pempec.finance.utils.iterators.LancamentoContaBancariaDebitoTextFilterator;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
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
public class ContaBancariaLancamento extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaDebitoBO contaBancariaDebitoBO = new ContaBancariaDebitoBO();
    private ContaBancariaCreditoBO contaBancariaCreditoBO = new ContaBancariaCreditoBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private long telaCredito = Tela.TELA_CONCILIACAO_LANCAMENTOS.getTela();
    private long telaDebito = Tela.TELA_CONCILIACAO_LANCAMENTOS.getTela();
    private TipoOperacaoBancariaBO operacaoBancariaBO = new TipoOperacaoBancariaBO();

    private String NameObject() {
        return (String) "Lançamentos em Conta Bancária ";
    }

    public void repopularCombos() {

        try {


            // responsavel
            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboResponsavelCredito.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));
            comboResponsavelDebito.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            //conta Bancaria

            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta(" -> Selecione <- ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancariaCredito.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));
            comboContaBancariaDebito.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            Collection<TipoOperacaoBancariaModel> lTipoOperacao = new ArrayList<TipoOperacaoBancariaModel>();

            TipoOperacaoBancariaModel tipoOPeracao = new TipoOperacaoBancariaModel();

            tipoOPeracao.setDescricao(" -> Selecione <- ");

            lTipoOperacao.add(tipoOPeracao);

            lTipoOperacao.addAll(operacaoBancariaBO.obterTodos(organizacaoModel));

            comboTipoOperacaoBancariaCredito.setModel(new javax.swing.DefaultComboBoxModel(lTipoOperacao.toArray()));
            comboTipoOperacaoBancariaDebito.setModel(new javax.swing.DefaultComboBoxModel(lTipoOperacao.toArray()));

            Collection<ContaBancariaCreditoModel> lColecaoCredito = contaBancariaCreditoBO.obterTodosLancamentosBancariosPorTipo(organizacaoModel, "C");
            Collection<ContaBancariaDebitoModel> lColecaoDebito = contaBancariaDebitoBO.obterTodosLancamentosBancariosPorTipo(organizacaoModel, ("D"));


            final EventList<ContaBancariaCreditoModel> lRegistrosCreditos = GlazedLists.eventList(lColecaoCredito);
            if (supportCredito != null && supportCredito.getItemList() != null && supportCredito.getComboBox() != null) {
                supportCredito.uninstall();
            }
            supportCredito = AutoCompleteSupport.install(comboTituloCredito, lRegistrosCreditos, new LancamentoContaBancariaCreditoTextFilterator());
            supportCredito.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportCredito.setStrict(false);


            final EventList<ContaBancariaDebitoModel> lRegistrosDebitos = GlazedLists.eventList(lColecaoDebito);

            if (supportDebito != null && supportDebito.getItemList() != null && supportDebito.getComboBox() != null) {
                supportDebito.uninstall();
            }
            supportDebito = AutoCompleteSupport.install(comboTituloDebito, lRegistrosDebitos, new LancamentoContaBancariaDebitoTextFilterator());
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

    public ContaBancariaLancamento() throws SystemException {

        initComponents();
        //Escodendo os campos
        campoCodigoCredito.setVisible(false);
        campoCodigoDebito.setVisible(false);

        labelDataRegistroCredito.setText(PempecParse.dateToString(Controller.getDataServidorBD()));
        labelDataRegistroDebito.setText(PempecParse.dateToString(Controller.getDataServidorBD()));
        jFTDataMovimentoCredito.setDate(Controller.getDataServidorBD());
        jFTDataMovimentoDebito.setDate(Controller.getDataServidorBD());
        labelTipoLancamentoCredito.setText("Crédito");
        labelTipoLancamentoDebito.setText("Débito");

        //Aplicando tamanho maximo de caracteres do campo.
        jTDescricaoCredito.setDocument(new Documento(80));
        jTDescricaoDebito.setDocument(new Documento(80));
        jTObservacaoCredito.setDocument(new Documento(60));
        jTObservacaoDebito.setDocument(new Documento(60));

        labelExportCredito.setVisible(false);
        labelExportDebito.setVisible(false);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaCompensarCheque = new javax.swing.JPanel();
        campoCodigoCredito = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboContaBancariaCredito = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        comboResponsavelCredito = new javax.swing.JComboBox();
        jTDescricaoCredito = new javax.swing.JTextField();
        labelDataVencimento = new javax.swing.JLabel();
        botaoGerarNumeroDocumentoCredito = new javax.swing.JButton();
        labelNumeroDocumento = new javax.swing.JLabel();
        labelDescricao1 = new javax.swing.JLabel();
        jTObservacaoCredito = new javax.swing.JTextField();
        comboTituloCredito = new javax.swing.JComboBox();
        jFTValorCredito = new br.com.pempec.componentes.JDoubleField();
        labelDataVencimento2 = new javax.swing.JLabel();
        labelTipoLancamentoCredito = new javax.swing.JLabel();
        comboTipoOperacaoBancariaCredito = new javax.swing.JComboBox();
        jLabel13 = new javax.swing.JLabel();
        labelDataRegistroCredito = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoCreditar = new javax.swing.JButton();
        botaoFecharCredito = new javax.swing.JButton();
        botaoLimparCredito = new javax.swing.JButton();
        botaoAlterarCredito = new javax.swing.JButton();
        botaoEstornarCredito = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jFTDataMovimentoCredito = new org.jdesktop.swingx.JXDatePicker();
        labelExportCredito = new javax.swing.JLabel();
        abaDebito = new javax.swing.JPanel();
        labelDescricao4 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        comboContaBancariaDebito = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        comboResponsavelDebito = new javax.swing.JComboBox();
        jTDescricaoDebito = new javax.swing.JTextField();
        labelDataVencimento4 = new javax.swing.JLabel();
        labelNumeroDocumento2 = new javax.swing.JLabel();
        labelDescricao5 = new javax.swing.JLabel();
        jTObservacaoDebito = new javax.swing.JTextField();
        jFTValorDebito = new br.com.pempec.componentes.JDoubleField();
        labelDataVencimento5 = new javax.swing.JLabel();
        labelTipoLancamentoDebito = new javax.swing.JLabel();
        comboTipoOperacaoBancariaDebito = new javax.swing.JComboBox();
        jLabel15 = new javax.swing.JLabel();
        labelDataRegistroDebito = new javax.swing.JLabel();
        jFTDataMovimentoDebito = new org.jdesktop.swingx.JXDatePicker();
        jPanel3 = new javax.swing.JPanel();
        botaoDebitar = new javax.swing.JButton();
        botaoAlterarDebito = new javax.swing.JButton();
        botaoEstornarDebito = new javax.swing.JButton();
        botaoLimparDebito = new javax.swing.JButton();
        botaoFecharDebito = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        botaoGerarNumeroDocumentoDebito = new javax.swing.JButton();
        campoCodigoDebito = new javax.swing.JTextField();
        comboTituloDebito = new javax.swing.JComboBox();
        labelExportDebito = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Lançamentos na Conta Bancária");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        abaCompensarCheque.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)));
        abaCompensarCheque.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                abaCreditosComponentShown(evt);
            }
        });

        campoCodigoCredito.setEditable(false);

        labelDescricao.setText("Detalhamento");

        jLabel10.setText("Conta Bancária");

        comboContaBancariaCredito.setFont(new java.awt.Font("Arial", 0, 10));

        jLabel7.setText("Responsável");

        comboResponsavelCredito.setFont(new java.awt.Font("Arial", 0, 10));

        labelDataVencimento.setText("Data Movimento");

        botaoGerarNumeroDocumentoCredito.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        botaoGerarNumeroDocumentoCredito.setText("Gerar");
        botaoGerarNumeroDocumentoCredito.setToolTipText("Gerar Número Documento");
        botaoGerarNumeroDocumentoCredito.setActionCommand("botaoGerarNumeroDocumento");
        botaoGerarNumeroDocumentoCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarNumeroDocumentoCreditoActionPerformed(evt);
            }
        });

        labelNumeroDocumento.setText("Número do Documento");

        labelDescricao1.setText("Observação");

        comboTituloCredito.setToolTipText("");
        comboTituloCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloCreditoActionPerformed(evt);
            }
        });

        labelDataVencimento2.setText("Valor");

        labelTipoLancamentoCredito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoLancamentoCredito.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo Lançamento"));
        labelTipoLancamentoCredito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        comboTipoOperacaoBancariaCredito.setFont(new java.awt.Font("Arial", 0, 10));

        jLabel13.setText("Operação Bancária");

        labelDataRegistroCredito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataRegistroCredito.setToolTipText("");
        labelDataRegistroCredito.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Registro"));
        labelDataRegistroCredito.setFocusable(false);
        labelDataRegistroCredito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoCreditar.setMnemonic('I');
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

        botaoAlterarCredito.setMnemonic('I');
        botaoAlterarCredito.setText("Alterar");
        botaoAlterarCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarCreditoActionPerformed(evt);
            }
        });

        botaoEstornarCredito.setMnemonic('I');
        botaoEstornarCredito.setText("Estornar");
        botaoEstornarCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEstornarCreditoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCreditar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterarCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEstornarCredito)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimparCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoCreditar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFecharCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimparCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoEstornarCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoAlterarCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelExportCredito.setBackground(new java.awt.Color(0, 204, 204));
        labelExportCredito.setFont(new java.awt.Font("Arial", 0, 10));
        labelExportCredito.setForeground(new java.awt.Color(255, 204, 153));
        labelExportCredito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)));

        javax.swing.GroupLayout abaCompensarChequeLayout = new javax.swing.GroupLayout(abaCompensarCheque);
        abaCompensarCheque.setLayout(abaCompensarChequeLayout);
        abaCompensarChequeLayout.setHorizontalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDescricao1)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addComponent(labelExportCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(156, Short.MAX_VALUE))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaCompensarChequeLayout.createSequentialGroup()
                                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jTDescricaoCredito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                            .addComponent(labelTipoLancamentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(labelDataRegistroCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labelDataVencimento)
                                                .addComponent(jFTDataMovimentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                            .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                                    .addComponent(comboTituloCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(campoCodigoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(botaoGerarNumeroDocumentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(labelNumeroDocumento))
                                            .addGap(33, 33, 33))
                                        .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                            .addComponent(labelDescricao)
                                            .addGap(266, 266, 266))))
                                .addGap(4, 4, 4)
                                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel10)
                                            .addComponent(comboContaBancariaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(20, 20, 20)
                                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelDataVencimento2)
                                            .addComponent(jFTValorCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel13)
                                    .addComponent(comboTipoOperacaoBancariaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboResponsavelCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addComponent(jTObservacaoCredito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE))
                        .addGap(54, 54, 54))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(54, Short.MAX_VALUE))))
        );
        abaCompensarChequeLayout.setVerticalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addComponent(labelNumeroDocumento)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTituloCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoGerarNumeroDocumentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDescricaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                .addComponent(labelDataVencimento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTDataMovimentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelDataRegistroCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTipoLancamentoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelDescricao1))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarChequeLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboContaBancariaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFTValorCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(labelDataVencimento2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTipoOperacaoBancariaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboResponsavelCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTObservacaoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(labelExportCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jTabbedPane1.addTab("Créditos", abaCompensarCheque);
        abaCompensarCheque.getAccessibleContext().setAccessibleName("abaCreditos");

        abaDebito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 0)));
        abaDebito.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                abaDebitoComponentShown(evt);
            }
        });

        labelDescricao4.setText("Detalhamento");

        jLabel12.setText("Conta Bancária");

        comboContaBancariaDebito.setFont(new java.awt.Font("Arial", 0, 10));

        jLabel9.setText("Responsável");

        comboResponsavelDebito.setFont(new java.awt.Font("Arial", 0, 10));

        labelDataVencimento4.setText("Data Movimento");

        labelNumeroDocumento2.setText("Número do Documento");

        labelDescricao5.setText("Observação");

        labelDataVencimento5.setText("Valor");

        labelTipoLancamentoDebito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoLancamentoDebito.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo Lançamento"));
        labelTipoLancamentoDebito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        comboTipoOperacaoBancariaDebito.setFont(new java.awt.Font("Arial", 0, 10));

        jLabel15.setText("Operação Bancária");

        labelDataRegistroDebito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataRegistroDebito.setToolTipText("");
        labelDataRegistroDebito.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Registro"));
        labelDataRegistroDebito.setFocusable(false);
        labelDataRegistroDebito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoDebitar.setMnemonic('I');
        botaoDebitar.setText("Debitar");
        botaoDebitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDebitarActionPerformed(evt);
            }
        });

        botaoAlterarDebito.setMnemonic('I');
        botaoAlterarDebito.setText("Alterar");
        botaoAlterarDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarDebitoActionPerformed(evt);
            }
        });

        botaoEstornarDebito.setMnemonic('I');
        botaoEstornarDebito.setText("Estornar");
        botaoEstornarDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEstornarDebitoActionPerformed(evt);
            }
        });

        botaoLimparDebito.setMnemonic('L');
        botaoLimparDebito.setText("Limpar");
        botaoLimparDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparDebitoActionPerformed(evt);
            }
        });

        botaoFecharDebito.setMnemonic('F');
        botaoFecharDebito.setText("Fechar");
        botaoFecharDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharDebitoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(botaoDebitar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEstornarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimparDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoAlterarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoDebitar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoEstornarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimparDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFecharDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        botaoGerarNumeroDocumentoDebito.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        botaoGerarNumeroDocumentoDebito.setText("Gerar");
        botaoGerarNumeroDocumentoDebito.setToolTipText("Gerar Número Documento");
        botaoGerarNumeroDocumentoDebito.setActionCommand("botaoGerarNumeroDocumento");
        botaoGerarNumeroDocumentoDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarNumeroDocumentoDebitoActionPerformed(evt);
            }
        });

        campoCodigoDebito.setEditable(false);

        comboTituloDebito.setToolTipText("");
        comboTituloDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloDebitoActionPerformed(evt);
            }
        });

        labelExportDebito.setBackground(new java.awt.Color(0, 204, 204));
        labelExportDebito.setFont(new java.awt.Font("Arial", 0, 10));
        labelExportDebito.setForeground(new java.awt.Color(255, 204, 153));
        labelExportDebito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)));

        javax.swing.GroupLayout abaDebitoLayout = new javax.swing.GroupLayout(abaDebito);
        abaDebito.setLayout(abaDebitoLayout);
        abaDebitoLayout.setHorizontalGroup(
            abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDebitoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescricao5)
                            .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaDebitoLayout.createSequentialGroup()
                                    .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jTDescricaoDebito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                                .addComponent(labelTipoLancamentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelDataRegistroDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(labelDataVencimento4)
                                                    .addComponent(jFTDataMovimentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaDebitoLayout.createSequentialGroup()
                                                .addComponent(comboTituloDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(campoCodigoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(botaoGerarNumeroDocumentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addComponent(labelNumeroDocumento2))
                                        .addComponent(labelDescricao4))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                    .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(abaDebitoLayout.createSequentialGroup()
                                            .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel12)
                                                .addComponent(comboContaBancariaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(20, 20, 20)
                                            .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(labelDataVencimento5)
                                                .addComponent(jFTValorDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jLabel15)
                                        .addComponent(comboTipoOperacaoBancariaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboResponsavelDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)))
                                .addComponent(jTObservacaoDebito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(52, Short.MAX_VALUE))
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addComponent(labelExportDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(156, Short.MAX_VALUE))
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(54, 54, 54))))
        );
        abaDebitoLayout.setVerticalGroup(
            abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDebitoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addComponent(labelNumeroDocumento2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoCodigoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTituloDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoGerarNumeroDocumentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelDescricao4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDescricaoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                .addComponent(labelDataVencimento4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTDataMovimentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelDataRegistroDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTipoLancamentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaDebitoLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboContaBancariaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFTValorDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel12)
                                .addComponent(labelDataVencimento5)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboTipoOperacaoBancariaDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboResponsavelDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDescricao5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTObservacaoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelExportDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jTabbedPane1.addTab("Débitos", abaDebito);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 683, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharCreditoActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharCreditoActionPerformed

    private void botaoLimparCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparCreditoActionPerformed

        //Somente setar null nos combos que possui o auto-complete
        comboContaBancariaCredito.setSelectedIndex(0);
        labelTipoLancamentoCredito.setEnabled(false);
        comboTituloCredito.setSelectedItem(null);
        comboResponsavelCredito.setSelectedIndex(0);
        comboTipoOperacaoBancariaCredito.setSelectedIndex(0);
        labelTipoLancamentoCredito.setText("Crédito");
        labelDataRegistroCredito.setText(PempecParse.dateToString(Controller.getDataServidorBD()));
        jFTDataMovimentoCredito.setDate(Controller.getDataServidorBD());
        jTObservacaoCredito.setText("");
        campoCodigoCredito.setText("");
        jFTValorCredito.setText("0,00");
        jTDescricaoCredito.setText("");
        botaoGerarNumeroDocumentoCredito.setEnabled(true);
        botaoCreditar.setEnabled(true);
}//GEN-LAST:event_botaoLimparCreditoActionPerformed

    private Boolean validaCamposDebitos() {

        if (comboResponsavelDebito.getSelectedIndex() == 0) {
            comboResponsavelDebito.requestFocus();
            return false;
        }


        if (jFTDataMovimentoDebito.getDate() == null) {
            jFTDataMovimentoDebito.requestFocus();
            return false;
        }

        if (jTDescricaoDebito.getText().isEmpty()) {
            jTDescricaoDebito.requestFocus();
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


        return true;
    }

    private Boolean validaCamposCreditos() {


        if (comboResponsavelCredito.getSelectedIndex() == 0) {
            comboResponsavelCredito.requestFocus();
            return false;
        }


        if (jFTDataMovimentoCredito.getDate() == null) {
            jFTDataMovimentoCredito.requestFocus();
            return false;
        }

        if (jTDescricaoCredito.getText().isEmpty()) {
            jTDescricaoCredito.requestFocus();
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





        return true;
    }

private void abaCreditosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_abaCreditosComponentShown
    botaoDebitar.setVisible(false);
    botaoEstornarDebito.setVisible(false);
    botaoAlterarDebito.setVisible(false);

    botaoCreditar.setVisible(true);
    botaoAlterarCredito.setVisible(true);

    botaoEstornarCredito.setVisible(true);

    botaoAlterarCredito.setEnabled(false);
    botaoEstornarCredito.setEnabled(false);


}//GEN-LAST:event_abaCreditosComponentShown

private void botaoGerarNumeroDocumentoCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarNumeroDocumentoCreditoActionPerformed
    String numero = PempecKeyGenerator.generateNumeroDocumentoContaBancariaCredito();
    comboTituloCredito.setSelectedItem(numero);
    comboTituloCredito.requestFocus();
}//GEN-LAST:event_botaoGerarNumeroDocumentoCreditoActionPerformed

private void comboTituloCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTituloCreditoActionPerformed
    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTituloCredito.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            ContaBancariaCreditoModel tab = new ContaBancariaCreditoModel();

            tab.setIdentificacao(comboTituloCredito.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = contaBancariaCreditoBO.consultarPorTemplateLancamentoBancario(tab);

            if (tab != null && tab.getPk() != null) {

                if (tab.getPk() != null) {

                    botaoDebitar.setEnabled(false);
                    botaoEstornarDebito.setEnabled(false);
                    botaoCreditar.setEnabled(false);
                    botaoGerarNumeroDocumentoCredito.setEnabled(false);
                    botaoAlterarCredito.setEnabled(true);
                    botaoEstornarCredito.setEnabled(true);
                } else {
                    botaoDebitar.setEnabled(false);
                    botaoEstornarDebito.setEnabled(false);
                    botaoAlterarCredito.setEnabled(false);
                    botaoCreditar.setEnabled(true);
                    botaoGerarNumeroDocumentoCredito.setEnabled(true);
                }


                if (tab.getLoteContabil() != null && tab.getLoteContabil().getPk() != null) {

                    botaoAlterarCredito.setEnabled(false);
                    botaoEstornarCredito.setEnabled(false);
                    botaoCreditar.setEnabled(false);

                    labelExportCredito.setVisible(true);
                    labelExportCredito.setText("Lançamento exportado sob num. " + tab.getLoteContabil().getLote());

                }

                campoCodigoCredito.setText(tab.getPk().getId());
                Double valor = tab.getValor();
                jFTValorCredito.setText(PempecParse.doubleToZero(valor));

                jTDescricaoCredito.setText(tab.getDescricao());
                jTObservacaoCredito.setText(tab.getObservacao());
                labelTipoLancamentoCredito.setText(tab.getTipoLancamento());
                labelDataRegistroCredito.setText(PempecParse.dateToString(tab.getDataRegistro()));
                jFTDataMovimentoCredito.setDate(tab.getDataMovimento());


                if (tab.getResponsavel() != null) {
                    for (int i = 1; i < comboResponsavelCredito.getItemCount(); i++) {
                        if (tab.getResponsavel().getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboResponsavelCredito.getItemAt(i)).getPk().getId())) {
                            comboResponsavelCredito.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getContaBancaria() != null) {
                    for (int i = 1; i < comboContaBancariaCredito.getItemCount(); i++) {
                        if (tab.getContaBancaria().getPk().getId().equalsIgnoreCase(((ContaBancariaModel) comboContaBancariaCredito.getItemAt(i)).getPk().getId())) {
                            comboContaBancariaCredito.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getTipoOperacaoBancaria() != null) {
                    for (int i = 1; i < comboTipoOperacaoBancariaCredito.getItemCount(); i++) {
                        if (tab.getTipoOperacaoBancaria().getPk().getId().equalsIgnoreCase(((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaCredito.getItemAt(i)).getPk().getId())) {
                            comboTipoOperacaoBancariaCredito.setSelectedIndex(i);
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
}//GEN-LAST:event_comboTituloCreditoActionPerformed

private void botaoDebitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDebitarActionPerformed
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

                ContaBancariaDebitoModel tab = new ContaBancariaDebitoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(PempecKeyGenerator.generateKey());

                tab.setIdentificacao(valorCombo);

                tab.setTipoLancamento("D");

                tab.setDescricao(jTDescricaoDebito.getText().toUpperCase());

                tab.setObservacao(jTObservacaoDebito.getText());

                tab.setDataRegistro(Controller.getDataServidorBD());

                tab.setUsuario(Controller.getUsuarioLogado());

                tab.setValor(jFTValorDebito.getValue());



                if (jFTDataMovimentoDebito.getDate() != null) {
                    tab.setDataMovimento(jFTDataMovimentoDebito.getDate());
                }


                if (comboResponsavelDebito.getSelectedItem() != null && ((FuncionarioModel) comboResponsavelDebito.getSelectedItem()).getPk() != null) {
                    tab.setResponsavel(new FuncionarioModel());
                    tab.getResponsavel().setPk(new PKModel());
                    tab.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavelDebito.getSelectedItem()).getPk().getId());
                }

                if (comboContaBancariaDebito.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancariaDebito.getSelectedItem()).getPk() != null) {
                    tab.setContaBancaria(new ContaBancariaModel());
                    tab.getContaBancaria().setPk(new PKModel());
                    tab.getContaBancaria().getPk().setId(((ContaBancariaModel) comboContaBancariaDebito.getSelectedItem()).getPk().getId());
                }

                //obert a contaBancaria para lancar a conta contabil
                ContaBancariaModel contaBancaria = ((ContaBancariaModel) comboContaBancariaDebito.getSelectedItem());
                contaBancaria = contaBancariaBO.consultarPorPk(contaBancaria);

                if (comboTipoOperacaoBancariaDebito.getSelectedItem() != null && ((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaDebito.getSelectedItem()).getPk() != null) {
                    tab.setTipoOperacaoBancaria(new TipoOperacaoBancariaModel());
                    tab.getTipoOperacaoBancaria().setPk(new PKModel());
                    tab.getTipoOperacaoBancaria().getPk().setId(((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaDebito.getSelectedItem()).getPk().getId());
                }

                TipoOperacaoBancariaModel tipoOperacaoBancariaModel = (TipoOperacaoBancariaModel) comboTipoOperacaoBancariaDebito.getSelectedItem();
                tipoOperacaoBancariaModel = operacaoBancariaBO.consultarPorPk(tipoOperacaoBancariaModel);

                tab.setMovimentoDiarioModel(registroMovimento("Debito", tab.getIdentificacao(), "ID " + tab.getIdentificacao() + " " + tab.getDescricao(), PempecParse.stringToDouble(jFTValorDebito.getText()), "Debitado"));

                contaBancariaDebitoBO.inserir(tab);


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

        exibeMensagemAviso("Campo Numero do Documento obrigatório.", null);
    }

}//GEN-LAST:event_botaoDebitarActionPerformed

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

                ContaBancariaCreditoModel tab = new ContaBancariaCreditoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(PempecKeyGenerator.generateKey());

                tab.setIdentificacao(valorCombo);

                tab.setTipoLancamento("C");

                tab.setDescricao(jTDescricaoCredito.getText().toUpperCase());

                tab.setObservacao(jTObservacaoCredito.getText().toUpperCase());

                tab.setDataRegistro(Controller.getDataServidorBD());

                tab.setUsuario(Controller.getUsuarioLogado());

                tab.setValor(jFTValorCredito.getValue());

                if (jFTDataMovimentoCredito.getDate() != null) {
                    tab.setDataMovimento(jFTDataMovimentoCredito.getDate());
                }


                if (comboResponsavelCredito.getSelectedItem() != null && ((FuncionarioModel) comboResponsavelCredito.getSelectedItem()).getPk() != null) {
                    tab.setResponsavel(new FuncionarioModel());
                    tab.getResponsavel().setPk(new PKModel());
                    tab.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavelCredito.getSelectedItem()).getPk().getId());
                }

                if (comboContaBancariaCredito.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancariaCredito.getSelectedItem()).getPk() != null) {
                    tab.setContaBancaria(new ContaBancariaModel());
                    tab.getContaBancaria().setPk(new PKModel());
                    tab.getContaBancaria().getPk().setId(((ContaBancariaModel) comboContaBancariaCredito.getSelectedItem()).getPk().getId());
                }

                //obter a contaBancaria para lancar a conta contabil
                ContaBancariaModel contaBancaria = ((ContaBancariaModel) comboContaBancariaCredito.getSelectedItem());
                contaBancaria = contaBancariaBO.consultarPorPk(contaBancaria);

                if (comboTipoOperacaoBancariaCredito.getSelectedItem() != null && ((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaCredito.getSelectedItem()).getPk() != null) {
                    tab.setTipoOperacaoBancaria(new TipoOperacaoBancariaModel());
                    tab.getTipoOperacaoBancaria().setPk(new PKModel());
                    tab.getTipoOperacaoBancaria().getPk().setId(((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaCredito.getSelectedItem()).getPk().getId());
                }

                TipoOperacaoBancariaModel tipoOperacaoBancariaModel = (TipoOperacaoBancariaModel) comboTipoOperacaoBancariaCredito.getSelectedItem();
                tipoOperacaoBancariaModel = operacaoBancariaBO.consultarPorPk(tipoOperacaoBancariaModel);

                tab.setMovimentoDiarioModel(registroMovimento("Creditar", tab.getIdentificacao(), "ID " + tab.getIdentificacao() + " " + tab.getDescricao(), PempecParse.stringToDouble(jFTValorCredito.getText()), "Creditado"));

                contaBancariaCreditoBO.inserir(tab);


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
        exibeMensagemAviso("Campo Numero do Documento obrigatório.", null);
    }





}//GEN-LAST:event_botaoCreditarActionPerformed

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

                ContaBancariaCreditoModel tab = new ContaBancariaCreditoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.setIdentificacao(valorCombo);

                tab = contaBancariaCreditoBO.consultarPorTemplateLancamentoBancario(tab);

                tab.setTipoLancamento(labelTipoLancamentoCredito.getText());
                tab.setValor(jFTValorCredito.getValue());
                tab.setDescricao(jTDescricaoCredito.getText().toUpperCase());
                tab.setObservacao(jTObservacaoCredito.getText().toUpperCase());

                if (jFTDataMovimentoCredito.getDate() != null) {
                    tab.setDataMovimento(jFTDataMovimentoCredito.getDate());
                }


                if (comboResponsavelCredito.getSelectedItem() != null && ((FuncionarioModel) comboResponsavelCredito.getSelectedItem()).getPk() != null) {
                    tab.setResponsavel(new FuncionarioModel());
                    tab.getResponsavel().setPk(new PKModel());
                    tab.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavelCredito.getSelectedItem()).getPk().getId());
                }

                if (comboContaBancariaCredito.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancariaCredito.getSelectedItem()).getPk() != null) {
                    tab.setContaBancaria(new ContaBancariaModel());
                    tab.getContaBancaria().setPk(new PKModel());
                    tab.getContaBancaria().getPk().setId(((ContaBancariaModel) comboContaBancariaCredito.getSelectedItem()).getPk().getId());
                }

                //obert a contaBancaria para lancar a conta contabil
                ContaBancariaModel contaBancaria = ((ContaBancariaModel) comboContaBancariaCredito.getSelectedItem());
                contaBancaria = contaBancariaBO.consultarPorPk(contaBancaria);

                if (comboTipoOperacaoBancariaCredito.getSelectedItem() != null && ((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaCredito.getSelectedItem()).getPk() != null) {
                    tab.setTipoOperacaoBancaria(new TipoOperacaoBancariaModel());
                    tab.getTipoOperacaoBancaria().setPk(new PKModel());
                    tab.getTipoOperacaoBancaria().getPk().setId(((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaCredito.getSelectedItem()).getPk().getId());
                }

                TipoOperacaoBancariaModel tipoOperacaoBancariaModel = (TipoOperacaoBancariaModel) comboTipoOperacaoBancariaCredito.getSelectedItem();
                tipoOperacaoBancariaModel = operacaoBancariaBO.consultarPorPk(tipoOperacaoBancariaModel);

                tab.setDataRegistro(Controller.getDataServidorBD());

                tab.setUsuario(Controller.getUsuarioLogado());

                //  tab.setMovimentoDiarioModel(registroMovimento("Alterar", tab.getIdentificacao(), ((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaCredito.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), PempecParse.stringToDouble(jFTValorCredito.getText()), "Alterado"));
                tab.setMovimentoDiarioModel(registroMovimento("Alterar", tab.getIdentificacao(), "ID " + tab.getIdentificacao() + " " + tab.getDescricao(), PempecParse.stringToDouble(jFTValorCredito.getText()), "Alterado"));

                contaBancariaCreditoBO.alterar(tab);

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
        exibeMensagemAviso("Campo Numero do Documento obrigatório.", null);
    }


}//GEN-LAST:event_botaoAlterarCreditoActionPerformed

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

                ContaBancariaDebitoModel tab = new ContaBancariaDebitoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigoDebito.getText());

                tab.setIdentificacao(valorCombo);

                tab = contaBancariaDebitoBO.consultarPorTemplateLancamentoBancario(tab);

                tab.setTipoLancamento(labelTipoLancamentoDebito.getText());

                tab.setValor(jFTValorDebito.getValue());

                tab.setDescricao(jTDescricaoDebito.getText().toUpperCase());

                tab.setObservacao(jTObservacaoDebito.getText().toUpperCase());

                if (jFTDataMovimentoDebito.getDate() != null) {
                    tab.setDataMovimento(jFTDataMovimentoDebito.getDate());
                }

                if (comboResponsavelDebito.getSelectedItem() != null && ((FuncionarioModel) comboResponsavelDebito.getSelectedItem()).getPk() != null) {
                    tab.setResponsavel(new FuncionarioModel());
                    tab.getResponsavel().setPk(new PKModel());
                    tab.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavelDebito.getSelectedItem()).getPk().getId());
                }

                if (comboContaBancariaDebito.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancariaDebito.getSelectedItem()).getPk() != null) {
                    tab.setContaBancaria(new ContaBancariaModel());
                    tab.getContaBancaria().setPk(new PKModel());
                    tab.getContaBancaria().getPk().setId(((ContaBancariaModel) comboContaBancariaDebito.getSelectedItem()).getPk().getId());
                }

                //obert a contaBancaria para lancar a conta contabil
                ContaBancariaModel contaBancaria = ((ContaBancariaModel) comboContaBancariaDebito.getSelectedItem());
                contaBancaria = contaBancariaBO.consultarPorPk(contaBancaria);

                if (comboTipoOperacaoBancariaDebito.getSelectedItem() != null && ((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaDebito.getSelectedItem()).getPk() != null) {
                    tab.setTipoOperacaoBancaria(new TipoOperacaoBancariaModel());
                    tab.getTipoOperacaoBancaria().setPk(new PKModel());
                    tab.getTipoOperacaoBancaria().getPk().setId(((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaDebito.getSelectedItem()).getPk().getId());
                }

                TipoOperacaoBancariaModel tipoOperacaoBancariaModel = (TipoOperacaoBancariaModel) comboTipoOperacaoBancariaDebito.getSelectedItem();
                tipoOperacaoBancariaModel = operacaoBancariaBO.consultarPorPk(tipoOperacaoBancariaModel);

                tab.setDataRegistro(Controller.getDataServidorBD());

                tab.setUsuario(Controller.getUsuarioLogado());

                tab.setMovimentoDiarioModel(registroMovimento("Alterar", tab.getIdentificacao(), "ID " + tab.getIdentificacao() + " " + tab.getDescricao(), PempecParse.stringToDouble(jFTValorDebito.getText()), "Alterado"));

                contaBancariaDebitoBO.alterar(tab);

                this.botaoLimparDebitoActionPerformed(evt);

            } else {

                exibeMensagemAviso("Campo Numero do Documento obrigatório.", null);

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
        exibeMensagemAviso("Campo(s) obrigatório(s).", null);
    }

}//GEN-LAST:event_botaoAlterarDebitoActionPerformed

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

                ContaBancariaDebitoModel tab = new ContaBancariaDebitoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigoDebito.getText());

                tab.setIdentificacao(valorCombo);

                tab = contaBancariaDebitoBO.consultarPorTemplateLancamentoBancario(tab);

                //tab.setMovimentoDiarioModel(registroMovimento("Deletar", tab.getIdentificacao(), ((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaDebito.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), PempecParse.stringToDouble(jFTValorDebito.getText()), "Deletado"));
                tab.setMovimentoDiarioModel(registroMovimento("Deletar", tab.getIdentificacao(), "ID " + tab.getIdentificacao() + " " + tab.getDescricao(), PempecParse.stringToDouble(jFTValorDebito.getText()), "Deletado"));


                contaBancariaDebitoBO.excluir(tab);

                this.botaoLimparDebitoActionPerformed(evt);

            } else {

                exibeMensagemAviso("Campo Numero do Documento obrigatório.", null);

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
        exibeMensagemAviso("Campo(s) obrigatório(s).", null);
    }



}//GEN-LAST:event_botaoEstornarDebitoActionPerformed

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

                ContaBancariaCreditoModel tab = new ContaBancariaCreditoModel();

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.setIdentificacao(valorCombo);

                tab = contaBancariaCreditoBO.consultarPorTemplateLancamentoBancario(tab);


                //tab.setMovimentoDiarioModel(registroMovimento("Deletar", tab.getIdentificacao(), ((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaCredito.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), PempecParse.stringToDouble(jFTValorCredito.getText()), "Deletado"));
                tab.setMovimentoDiarioModel(registroMovimento("Deletar", tab.getIdentificacao(), "ID " + tab.getIdentificacao() + " " + tab.getDescricao(), PempecParse.stringToDouble(jFTValorCredito.getText()), "Deletado"));

                contaBancariaCreditoBO.excluir(tab);

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
        exibeMensagemAviso("Campo Numero do Documento obrigatório.", null);
    }

}//GEN-LAST:event_botaoEstornarCreditoActionPerformed

private void botaoLimparDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparDebitoActionPerformed
    comboContaBancariaDebito.setSelectedIndex(0);
    labelTipoLancamentoDebito.setEnabled(false);
    comboTituloDebito.setSelectedItem(null);
    comboResponsavelDebito.setSelectedIndex(0);
    comboTipoOperacaoBancariaDebito.setSelectedIndex(0);
    labelTipoLancamentoDebito.setText("Débito");
    labelDataRegistroDebito.setText(PempecParse.dateToString(Controller.getDataServidorBD()));
    jFTDataMovimentoDebito.setDate(Controller.getDataServidorBD());
    jTObservacaoDebito.setText("");
    campoCodigoDebito.setText("");
    jFTValorDebito.setText("0,00");
    jTDescricaoDebito.setText("");
    botaoGerarNumeroDocumentoDebito.setEnabled(true);
    botaoCreditar.setEnabled(true);
}//GEN-LAST:event_botaoLimparDebitoActionPerformed

private void botaoFecharDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharDebitoActionPerformed
    this.botaoFecharCreditoActionPerformed(evt);
}//GEN-LAST:event_botaoFecharDebitoActionPerformed

private void botaoGerarNumeroDocumentoDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarNumeroDocumentoDebitoActionPerformed
    String numero = PempecKeyGenerator.generateNumeroDocumentoContaBancariaDebito();
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

            ContaBancariaDebitoModel tab = new ContaBancariaDebitoModel();

            tab.setIdentificacao(comboTituloDebito.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = contaBancariaDebitoBO.consultarPorTemplateLancamentoBancario(tab);

            if (tab != null && tab.getPk() != null) {

                if (tab.getPk() != null) {
                    botaoAlterarCredito.setEnabled(false);
                    botaoCreditar.setEnabled(false);
                    botaoEstornarDebito.setEnabled(false);
                    botaoDebitar.setEnabled(false);
                    botaoGerarNumeroDocumentoDebito.setEnabled(false);
                    botaoAlterarDebito.setEnabled(true);
                    botaoEstornarDebito.setEnabled(true);

                } else {
                    botaoAlterarCredito.setEnabled(false);
                    botaoEstornarDebito.setEnabled(false);
                    botaoCreditar.setEnabled(false);
                    botaoEstornarDebito.setEnabled(false);
                    botaoAlterarDebito.setEnabled(false);
                    botaoDebitar.setEnabled(true);
                    botaoGerarNumeroDocumentoDebito.setEnabled(true);
                }

                if (tab.getLoteContabil() != null && tab.getLoteContabil().getPk() != null) {

                    botaoAlterarDebito.setEnabled(false);
                    botaoEstornarDebito.setEnabled(false);
                    botaoDebitar.setEnabled(false);

                    labelExportDebito.setVisible(true);
                    labelExportDebito.setText("Lançamento exportado sob num. " + tab.getLoteContabil().getLote());

                }

                campoCodigoDebito.setText(tab.getPk().getId());
                Double valor = tab.getValor();
                jFTValorDebito.setText(PempecParse.doubleToZero(valor));
                jTDescricaoDebito.setText(tab.getDescricao());
                jTObservacaoDebito.setText(tab.getObservacao());
                labelTipoLancamentoDebito.setText(tab.getTipoLancamento());
                labelDataRegistroDebito.setText(PempecParse.dateToString(tab.getDataRegistro()));
                jFTDataMovimentoDebito.setDate(tab.getDataMovimento());


                if (tab.getResponsavel() != null) {
                    for (int i = 1; i < comboResponsavelDebito.getItemCount(); i++) {
                        if (tab.getResponsavel().getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboResponsavelDebito.getItemAt(i)).getPk().getId())) {
                            comboResponsavelDebito.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getContaBancaria() != null) {
                    for (int i = 1; i < comboContaBancariaDebito.getItemCount(); i++) {
                        if (tab.getContaBancaria().getPk().getId().equalsIgnoreCase(((ContaBancariaModel) comboContaBancariaDebito.getItemAt(i)).getPk().getId())) {
                            comboContaBancariaDebito.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getTipoOperacaoBancaria() != null) {
                    for (int i = 1; i < comboTipoOperacaoBancariaDebito.getItemCount(); i++) {
                        if (tab.getTipoOperacaoBancaria().getPk().getId().equalsIgnoreCase(((TipoOperacaoBancariaModel) comboTipoOperacaoBancariaDebito.getItemAt(i)).getPk().getId())) {
                            comboTipoOperacaoBancariaDebito.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getTipoOperacaoBancaria() != null && tab.getTipoOperacaoBancaria().getPk().getId().equalsIgnoreCase(Constantes.TIPO_OPERACAO_BANCARIA_TRANSFERENCIA_TESOURARIA)) {

                    botaoAlterarDebito.setEnabled(false);
                    botaoEstornarDebito.setEnabled(false);

//                    if (tab.getContaBancariaCheque() != null && tab.getContaBancariaCheque().getDataCompensado() != null) {
//
//                        exibeMensagemAviso("Operação Impossível.\n Faça estorno do cheque primeiro. \n CHEQUE " + tab.getContaBancariaCheque().getNumeroCheque(), "Cheque Compensado");
//
//                    } else {
//
//                        if (exibeMensagemConfirmacao("Lançamento registrado na tesouraria. \n O estorno poderá causar inconsistência no saldo do caixa.\n \n Deseja prosseguir? ", "Transferencia em especie")) {
//
//                            botaoEstornarDebito.setEnabled(true);
//                        }
//
//                    }


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

private void abaDebitoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_abaDebitoComponentShown
    botaoDebitar.setVisible(true);
    botaoEstornarDebito.setVisible(true);
    botaoAlterarDebito.setVisible(true);

    botaoAlterarDebito.setEnabled(false);
    botaoCreditar.setVisible(false);
    botaoAlterarCredito.setVisible(false);
    botaoEstornarCredito.setVisible(false);

    botaoEstornarCredito.setEnabled(false);
    botaoEstornarDebito.setEnabled(false);
}//GEN-LAST:event_abaDebitoComponentShown
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque;
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
    private javax.swing.JComboBox comboContaBancariaCredito;
    private javax.swing.JComboBox comboContaBancariaDebito;
    private javax.swing.JComboBox comboResponsavelCredito;
    private javax.swing.JComboBox comboResponsavelDebito;
    private javax.swing.JComboBox comboTipoOperacaoBancariaCredito;
    private javax.swing.JComboBox comboTipoOperacaoBancariaDebito;
    private javax.swing.JComboBox comboTituloCredito;
    private javax.swing.JComboBox comboTituloDebito;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private org.jdesktop.swingx.JXDatePicker jFTDataMovimentoCredito;
    private org.jdesktop.swingx.JXDatePicker jFTDataMovimentoDebito;
    private br.com.pempec.componentes.JDoubleField jFTValorCredito;
    private br.com.pempec.componentes.JDoubleField jFTValorDebito;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTDescricaoCredito;
    private javax.swing.JTextField jTDescricaoDebito;
    private javax.swing.JTextField jTObservacaoCredito;
    private javax.swing.JTextField jTObservacaoDebito;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelDataRegistroCredito;
    private javax.swing.JLabel labelDataRegistroDebito;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDataVencimento2;
    private javax.swing.JLabel labelDataVencimento4;
    private javax.swing.JLabel labelDataVencimento5;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelDescricao1;
    private javax.swing.JLabel labelDescricao4;
    private javax.swing.JLabel labelDescricao5;
    private javax.swing.JLabel labelExportCredito;
    private javax.swing.JLabel labelExportDebito;
    private javax.swing.JLabel labelNumeroDocumento;
    private javax.swing.JLabel labelNumeroDocumento2;
    private javax.swing.JLabel labelTipoLancamentoCredito;
    private javax.swing.JLabel labelTipoLancamentoDebito;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportCredito;
    private AutoCompleteSupport supportDebito;
}
