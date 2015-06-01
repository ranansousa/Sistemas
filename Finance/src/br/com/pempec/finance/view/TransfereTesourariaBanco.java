package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaCreditoBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.TesourariaServices;
import br.com.pempec.finance.utils.iterators.TesourariaDebitoTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Color;
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
public class TransfereTesourariaBanco extends FinanceInternalFrame implements IRepopulador {

    private TesourariaDebitoBO tesourariaDebitoBO = new TesourariaDebitoBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private ContaBancariaCreditoBO contaBancariaCreditoBO = new ContaBancariaCreditoBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private CedenteBO cedenteBO = new CedenteBO();
    private HistoricoBO historicoBO = new HistoricoBO();

    Collection<TesourariaDebitoModel> lColecaoDebito = new ArrayList<TesourariaDebitoModel>();

    private long tela = Tela.TELA_TESOURARIA_TRANSFERIR_BANCO.getTela();

    private String NameObject() {
        return "Transferência de Valores para Banco";
    }

    public TransfereTesourariaBanco() throws SystemException {

        initComponents();

        Double saldo = saldoTesouraria();
        Double saldoCheque = saldoTesourariaCheque();

        if (saldo < 0) {
            jSaldoTesouraria.setForeground(new Color(204, 0, 0));
        } else {
            jSaldoTesouraria.setForeground(new Color(0, 51, 204));
        }

        jSaldoTesouraria.setText(PempecParse.doubleToZero(saldo));

        if (saldoCheque < 0) {
            jSaldoTesourariaCheque.setForeground(new Color(204, 0, 0));
        } else {
            jSaldoTesourariaCheque.setForeground(new Color(0, 51, 204));
        }

        jSaldoTesourariaCheque.setText(PempecParse.doubleToZero(saldoCheque));

        jFTDataMovimento.setDate(Controller.getDataServidorBD());

        jFTDataPesquisarInicial.setDate(PempecUtil.addDayDate(Controller.getDataServidorBD(), -1));
        jFTDataPesquisarFinal.setDate(Controller.getDataServidorBD());

        campoIDTesDebito.setVisible(false);

        campoIDContaCredito.setVisible(false);

        desabilitaCampos();

    }

    public void repopularCombos() {

        try {

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            ContaBancariaModel contaBancariaModel = new ContaBancariaModel();

            contaBancariaModel.setConta(" -> Selecione <- ");

            lContas.add(contaBancariaModel);
            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancaria.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));
            comboContaBancariaEstorno.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            //responsavel
            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboFuncionario.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            comboResponsavelDebito.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        panelCheques = new javax.swing.JTabbedPane();
        abaConsulta = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoDepositar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jValorDeposito = new br.com.pempec.componentes.JDoubleField();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboContaBancaria = new javax.swing.JComboBox();
        labelDataProtocolo = new javax.swing.JLabel();
        jFTDataMovimento = new org.jdesktop.swingx.JXDatePicker();
        labelResponsavel = new javax.swing.JLabel();
        comboFuncionario = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSaldoTesouraria = new br.com.pempec.componentes.JDoubleField();
        jSaldoTesourariaCheque = new br.com.pempec.componentes.JDoubleField();
        jLabel3 = new javax.swing.JLabel();
        abaDebito = new javax.swing.JPanel();
        campoIDTesDebito = new javax.swing.JTextField();
        labelDescricao2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboResponsavelDebito = new javax.swing.JComboBox();
        jTDescricaoDebito = new javax.swing.JTextField();
        labelDataVencimento1 = new javax.swing.JLabel();
        labelNumeroDocumento1 = new javax.swing.JLabel();
        labelDescricao3 = new javax.swing.JLabel();
        jTObservacaoDebito = new javax.swing.JTextField();
        comboTituloDebito = new javax.swing.JComboBox();
        jFTValorDebito = new br.com.pempec.componentes.JDoubleField();
        labelDataVencimento3 = new javax.swing.JLabel();
        labelDataEmissao1 = new javax.swing.JLabel();
        labelTipoLancamentoDebito = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        botaoFecharDebito = new javax.swing.JButton();
        botaoLimparDebito = new javax.swing.JButton();
        botaoEstornarDebito = new javax.swing.JButton();
        botaoAlterarDebito = new javax.swing.JButton();
        jFTDataMovimentoDebito = new org.jdesktop.swingx.JXDatePicker();
        jFTDataContabilDebito = new org.jdesktop.swingx.JXDatePicker();
        labelExportDebito = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jFTDataPesquisarInicial = new org.jdesktop.swingx.JXDatePicker();
        jFTDataPesquisarFinal = new org.jdesktop.swingx.JXDatePicker();
        btnPesquisar = new javax.swing.JButton();
        jFTDataRegistro = new org.jdesktop.swingx.JXDatePicker();
        labelDataEmissao2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        comboContaBancariaEstorno = new javax.swing.JComboBox();
        jTDescricao = new javax.swing.JTextField();
        jTBanco = new javax.swing.JTextField();
        jTAgencia = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTCliente = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        campoIDContaCredito = new javax.swing.JTextField();

        jButton3.setText("jButton3");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Transfere da Tesouraria para o Banco");

        abaConsulta.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(), javax.swing.BorderFactory.createCompoundBorder()));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

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

        botaoDepositar.setMnemonic('L');
        botaoDepositar.setText("Depositar");
        botaoDepositar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoDepositarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoDepositar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoDepositar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSeparator2.setForeground(new java.awt.Color(51, 51, 0));
        jSeparator2.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.blue, null));

        jLabel2.setText("Valor Do Depósito");

        jLabel10.setText("Conta Bancária");

        comboContaBancaria.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        labelDataProtocolo.setText("Data Movimento");

        labelResponsavel.setText("Responsável");

        comboFuncionario.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), "Saldo da Tesouraria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

        jLabel1.setText("Espécie");

        jSaldoTesouraria.setEditable(false);
        jSaldoTesouraria.setForeground(new java.awt.Color(0, 51, 204));

        jSaldoTesourariaCheque.setEditable(false);
        jSaldoTesourariaCheque.setForeground(new java.awt.Color(0, 51, 204));

        jLabel3.setText("Cheque");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSaldoTesouraria, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSaldoTesourariaCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSaldoTesourariaCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSaldoTesouraria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout abaConsultaLayout = new javax.swing.GroupLayout(abaConsulta);
        abaConsulta.setLayout(abaConsultaLayout);
        abaConsultaLayout.setHorizontalGroup(
            abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaConsultaLayout.createSequentialGroup()
                .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaConsultaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(abaConsultaLayout.createSequentialGroup()
                                .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jValorDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDataProtocolo)
                                    .addComponent(jFTDataMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelResponsavel)
                            .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaConsultaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 634, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaConsultaLayout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(569, Short.MAX_VALUE))
        );
        abaConsultaLayout.setVerticalGroup(
            abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaConsultaLayout.createSequentialGroup()
                .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaConsultaLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaConsultaLayout.createSequentialGroup()
                                .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(labelDataProtocolo))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboContaBancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFTDataMovimento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(abaConsultaLayout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jValorDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(abaConsultaLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(labelResponsavel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        panelCheques.addTab("Tesouraria", abaConsulta);

        abaDebito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)));
        abaDebito.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                abaDebitosComponentShown(evt);
            }
        });

        campoIDTesDebito.setEditable(false);

        labelDescricao2.setText("Descrição");

        jLabel9.setText("Responsável");

        comboResponsavelDebito.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        labelDataVencimento1.setText("Data Movimento");

        labelNumeroDocumento1.setText("Número do Documento");

        labelDescricao3.setText("Observação");

        comboTituloDebito.setToolTipText("");
        comboTituloDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloDebitoActionPerformed(evt);
            }
        });

        labelDataVencimento3.setText("Valor");

        labelDataEmissao1.setText("Data Contabil");

        labelTipoLancamentoDebito.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelTipoLancamentoDebito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoLancamentoDebito.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo Lançamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10))); // NOI18N
        labelTipoLancamentoDebito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jPanel4.setPreferredSize(new java.awt.Dimension(734, 65));

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(252, 252, 252)
                .addComponent(botaoAlterarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEstornarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimparDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAlterarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEstornarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimparDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFecharDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelExportDebito.setBackground(new java.awt.Color(0, 204, 204));
        labelExportDebito.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelExportDebito.setForeground(new java.awt.Color(255, 0, 0));
        labelExportDebito.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)), "Exportação"));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data a ser Pesquisa"));

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFTDataPesquisarInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(jFTDataPesquisarFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTDataPesquisarInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTDataPesquisarFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        labelDataEmissao2.setText("Data Registro");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Bancários"));

        jLabel12.setText("Conta Bancária");

        comboContaBancariaEstorno.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel13.setText("Banco");

        jLabel14.setText("Agência");

        jLabel15.setText("Cliente");

        jLabel16.setText("Descrição");

        campoIDContaCredito.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboContaBancariaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addGap(22, 22, 22)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel13))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addGap(0, 194, Short.MAX_VALUE))
                                    .addComponent(jTCliente, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoIDContaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboContaBancariaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoIDContaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout abaDebitoLayout = new javax.swing.GroupLayout(abaDebito);
        abaDebito.setLayout(abaDebitoLayout);
        abaDebitoLayout.setHorizontalGroup(
            abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDebitoLayout.createSequentialGroup()
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                .addGap(691, 691, 691)
                                .addComponent(labelDataVencimento3)
                                .addGap(97, 97, 97)
                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFTDataMovimentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDataVencimento1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(abaDebitoLayout.createSequentialGroup()
                                        .addComponent(labelDataEmissao1)
                                        .addGap(50, 50, 50))
                                    .addComponent(jFTDataContabilDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDataEmissao2)
                                    .addComponent(jFTDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDescricao2)
                                    .addComponent(jTDescricaoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(41, 41, 41)
                                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(abaDebitoLayout.createSequentialGroup()
                                        .addComponent(jTObservacaoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboResponsavelDebito, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(abaDebitoLayout.createSequentialGroup()
                                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                                .addComponent(labelDescricao3)
                                                .addGap(337, 337, 337)
                                                .addComponent(jLabel9))
                                            .addComponent(labelNumeroDocumento1)
                                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                                .addComponent(comboTituloDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(campoIDTesDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(15, 15, 15)
                                                .addComponent(jFTValorDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 266, Short.MAX_VALUE))))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaDebitoLayout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 703, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelExportDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTipoLancamentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)))
                .addContainerGap())
        );
        abaDebitoLayout.setVerticalGroup(
            abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDebitoLayout.createSequentialGroup()
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(abaDebitoLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaDebitoLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaDebitoLayout.createSequentialGroup()
                                    .addComponent(labelDataVencimento3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jFTValorDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaDebitoLayout.createSequentialGroup()
                                    .addComponent(labelDataVencimento1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFTDataMovimentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaDebitoLayout.createSequentialGroup()
                                    .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelDataEmissao1)
                                        .addComponent(labelDataEmissao2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jFTDataContabilDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFTDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelNumeroDocumento1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTituloDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoIDTesDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(labelDescricao2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDescricaoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDescricao3)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTObservacaoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboResponsavelDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)))
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTipoLancamentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelExportDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(58, 58, 58)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        panelCheques.addTab("Estornos", abaDebito);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCheques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCheques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        jFTDataMovimento.setDate(Controller.getDataServidorBD());
        comboFuncionario.setSelectedIndex(0);
        comboContaBancaria.setSelectedIndex(0);
        jSaldoTesouraria.setText(PempecParse.doubleToZero(saldoTesouraria()));
        jSaldoTesourariaCheque.setText(PempecParse.doubleToZero(saldoTesourariaCheque()));

        jValorDeposito.setText("0,00");


}//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoDepositarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoDepositarActionPerformed

        if (validaCampos()) {

            try {

                long action = Action.OUTROS.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (jValorDeposito.getValue() > 0) {

                    Double valor = jValorDeposito.getValue();

                    if (valor > saldoTesouraria()) {
                        exibeMensagemAviso("Saldo insuficiente!", null);
                        this.botaoLimparActionPerformed(evt);
                        return;
                    }

                    //creditar no banco
                    ContaBancariaCreditoModel ctbCr = new ContaBancariaCreditoModel();

                    ctbCr.setPk(new PKModel());

                    ctbCr.getPk().setOrganizacao(organizacaoModel);
                    ctbCr.getPk().setId(PempecKeyGenerator.generateKey());
                    ctbCr.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaCredito());

                    if (comboContaBancaria.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancaria.getSelectedItem()).getPk().getId() != null) {
                        ContaBancariaModel contaBancariaModel = (ContaBancariaModel) comboContaBancaria.getSelectedItem();
                        ctbCr.setContaBancaria(contaBancariaModel);
                    }

                    if (comboFuncionario.getSelectedItem() != null && ((FuncionarioModel) comboFuncionario.getSelectedItem()).getPk().getId() != null) {
                        FuncionarioModel funcionarioModel = (FuncionarioModel) comboFuncionario.getSelectedItem();
                        ctbCr.setResponsavel(funcionarioModel);
                    }

                    ctbCr.setDataRegistro(Controller.getDataServidorBD());
                    ctbCr.setDataMovimento(PempecParse.dateToDate(jFTDataMovimento.getDate()));
                    ctbCr.setDataContabil(PempecParse.dateToDate(jFTDataMovimento.getDate()));

                    ctbCr.setDescricao(" NA CONTA : " + ctbCr.getContaBancaria().getConta());
                    ctbCr.setTipoLancamento("C");

                    ctbCr.setValor(valor);

                    TipoOperacaoBancariaModel tipoOPeracao = new TipoOperacaoBancariaModel();
                    tipoOPeracao.setPk(new PKModel());
                    tipoOPeracao.getPk().setOrganizacao(organizacaoModel);
                    tipoOPeracao.getPk().setId(Constantes.TIPO_OPERACAO_BANCARIA_TESOURARIA_DEPOSITO);

                    tipoOPeracao = new TipoOperacaoBancariaBO().consultarPorPk(tipoOPeracao);

                    ctbCr.setTipoOperacaoBancaria(tipoOPeracao);

                    ctbCr.setUsuario(Controller.getUsuarioLogado());

                    ctbCr.setMovimentoDiarioModel(registroMovimento("DEP ESP T/B", ctbCr.getIdentificacao().toString(), "NUM DOC. : " + ctbCr.getIdentificacao().toString() + " - DEP TES P/ BANCO", valor, "Depositado"));

                    //debitar na tesouraria
                    TesourariaDebitoModel tDebito = new TesourariaDebitoModel();
                    tDebito.setPk(new PKModel());
                    tDebito.getPk().setOrganizacao(Controller.getOrganizacao());
                    tDebito.getPk().setId(PempecKeyGenerator.generateKey());
                    tDebito.setDataContabil(ctbCr.getDataContabil());
                    tDebito.setDataRegistro(ctbCr.getDataRegistro());
                    tDebito.setDataMovimento(ctbCr.getDataMovimento());
                    tDebito.setDescricao(ctbCr.getDescricao());
                    tDebito.setTipoLancamento("D");
                    tDebito.setResponsavel(ctbCr.getResponsavel());
                    tDebito.setValorNominal(ctbCr.getValor());
                    tDebito.setUsuario(Controller.getUsuarioLogado());
                    tDebito.setNumeroDocumento(ctbCr.getIdentificacao());

                    //tem que garantir que exista o cedente com o id da organizacao
                    //este cedente é a propria organizacao
                    CedenteModel cedente = new CedenteModel();
                    cedente.setPk(new PKModel());
                    cedente.getPk().setOrganizacao(organizacaoModel);
                    cedente.getPk().setId(Controller.getOrganizacao().getId());

                    cedente = cedenteBO.consultarPorPk(cedente);

                    if (cedente == null || cedente.getPk().getId().isEmpty()) {
                        if (exibeMensagemConfirmacao("Cedente não localizado. \nDeseja que o sistema insira automaticamente ?", "Erro de Cedente")) {

                            cedente = inserirCedenteOrganizacao();

                        } else {

                            cedente = null;
                        }
                    }

                    if (cedente != null && !cedente.getPk().getId().isEmpty()) {

                        if (!Controller.getOrganizacao().getId().equalsIgnoreCase(cedente.getPk().getId())) {
                            exibeMensagemAviso("Problemas com o cedente. \nErro do Cedente igual a Organizacao", "Erro de Cedente");
                            return;
                        }
                    } else {

                        tDebito.setCedenteModel(cedente);
                    }

                    HistoricoModel hst = new HistoricoModel();
                    hst.setPk(new PKModel());
                    hst.getPk().setOrganizacao(organizacaoModel);
                    hst.getPk().setId(Constantes.HISTORICO_TESOURARIA_DEPOSITO);

                    tDebito.setHistorico(hst);

                    tesourariaDebitoBO.depositarBanco(tDebito, ctbCr);
                    this.botaoLimparActionPerformed(evt);

                } else {

                    exibeMensagemAviso("Valor incorreto!", null);

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
            exibeMensagemAviso("Campo(s) obrigatório(s)!", null);
        }

    }//GEN-LAST:event_botaoDepositarActionPerformed

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
                    
                    
                    HistoricoModel hst = new HistoricoModel();
                    hst.setPk(new PKModel());
                    hst.getPk().setOrganizacao(organizacaoModel);
                    hst.getPk().setId(Constantes.HISTORICO_TESOURARIA_DEPOSITO);
                    tab.setHistorico(hst);

                    CedenteModel cedente = new CedenteModel();
                    cedente.setPk(new PKModel());
                    cedente.getPk().setOrganizacao(organizacaoModel);
                    cedente.getPk().setId(Controller.getOrganizacao().getId());

                    if (cedente != null && !cedente.getPk().getId().isEmpty()) {
                        if (!Controller.getOrganizacao().getId().equalsIgnoreCase(cedente.getPk().getId())) {
                            exibeMensagemAviso("Problemas com o cedente. \nErro do Cedente igual a Organizacao", "Erro de Cedente");
                            return;
                        }
                    } else {

                        tab.setCedenteModel(cedente);
                    }

                    campoIDTesDebito.setText(tab.getPk().getId());
                    jFTDataContabilDebito.setDate(tab.getDataContabil());
                    jFTDataMovimentoDebito.setDate(tab.getDataMovimento());
                    jFTDataRegistro.setDate(tab.getDataRegistro());

                    Double valor = tab.getValorNominal();
                    jFTValorDebito.setText(PempecParse.doubleToZero(valor));
                    jTDescricaoDebito.setText(tab.getDescricao());
                    jTObservacaoDebito.setText(tab.getObservacao());
                    labelTipoLancamentoDebito.setText(tab.getTipoLancamento());

                    if (tab.getResponsavel() != null) {

                        for (int i = 1; i < comboResponsavelDebito.getItemCount(); i++) {
                            if (tab.getResponsavel().getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboResponsavelDebito.getItemAt(i)).getPk().getId())) {
                                comboResponsavelDebito.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    ContaBancariaCreditoModel contaCreditada = new ContaBancariaCreditoModel();
                    contaCreditada.setPk(new PKModel());
                    contaCreditada.getPk().setOrganizacao(Controller.getOrganizacao());
                    contaCreditada.setIdentificacao(tab.getNumeroDocumento());

                    contaCreditada = contaBancariaCreditoBO.consultarPorTemplate(contaCreditada);

                    if (contaCreditada != null) {

                        if (contaCreditada.getContaBancaria() != null) {
                            for (int i = 1; i < comboContaBancariaEstorno.getItemCount(); i++) {
                                if (contaCreditada.getContaBancaria().getPk().getId().equalsIgnoreCase(((ContaBancariaModel) comboContaBancariaEstorno.getItemAt(i)).getPk().getId())) {
                                    comboContaBancariaEstorno.setSelectedIndex(i);
                                    break;
                                }
                            }
                        }

                        jTAgencia.setText(contaCreditada.getContaBancaria().getAgencia());
                        jTCliente.setText(contaCreditada.getContaBancaria().getTitular());
                        jTBanco.setText(contaCreditada.getContaBancaria().getBanco().getCodigoBanco());
                        jTDescricao.setText(contaCreditada.getDescricao());

                    } else {
                        
                        
                       comboContaBancariaEstorno.setSelectedIndex(0);
                        jTAgencia.setText("");
                        jTCliente.setText("");
                        jTBanco.setText("");
                        jTDescricao.setText("");
                        
                        
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

    private void botaoFecharDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharDebitoActionPerformed
        //this.botaoFecharCreditoActionPerformed(evt);
        this.botaoLimparDebitoActionPerformed(evt);
        setVisible(false);
    }//GEN-LAST:event_botaoFecharDebitoActionPerformed

    private void botaoLimparDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparDebitoActionPerformed
        comboTituloDebito.setSelectedItem(null);
        comboContaBancariaEstorno.setSelectedIndex(0);
        comboResponsavelDebito.setSelectedIndex(0);

        labelTipoLancamentoDebito.setVisible(false);
        jFTDataRegistro.setVisible(false);

        jTObservacaoDebito.setText("");
        campoIDTesDebito.setText("");
        jFTValorDebito.setText("0,00");
        jTDescricaoDebito.setText("");
        botaoAlterarDebito.setEnabled(false);
        botaoEstornarDebito.setEnabled(false);
//        botaoDebitar.setEnabled(true);

        labelExportDebito.setVisible(false);

        
        jTAgencia.setText("");
        jTBanco.setText("");
        jTCliente.setText("");
        jTDescricao.setText("");


    }//GEN-LAST:event_botaoLimparDebitoActionPerformed

    private void botaoEstornarDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEstornarDebitoActionPerformed
        String valorCombo = null;

        if (comboTituloDebito.getSelectedItem() != null) {
            valorCombo = comboTituloDebito.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.OUTROS.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (validaCamposDebitos()) {

                    TesourariaDebitoModel tab = new TesourariaDebitoModel();

                    OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                    tab.setPk(new PKModel());

                    tab.getPk().setOrganizacao(organizacaoModel);

                    tab.setNumeroDocumento(valorCombo);

                    tab.getPk().setId(campoIDTesDebito.getText());

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

                if (validaCamposDebitos()) {

                    TesourariaDebitoModel tab = new TesourariaDebitoModel();

                    OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                    tab.setPk(new PKModel());

                    tab.getPk().setOrganizacao(organizacaoModel);

                    tab.setNumeroDocumento(valorCombo);

                    tab.getPk().setId(campoIDTesDebito.getText());

                    tab = tesourariaDebitoBO.consultarPorTemplate(tab);

                    tab.setDescricao(jTDescricaoDebito.getText().toUpperCase());

                    tab.setObservacao(jTObservacaoDebito.getText());

                    tab.setTipoLancamento("D");

                    tab.setUsuario(Controller.getUsuarioLogado());

                    tab.setValorNominal(jFTValorDebito.getValue());

                    if (jFTDataRegistro.getDate() != null) {
                        tab.setDataRegistro(jFTDataRegistro.getDate());
                    }

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

    private void abaDebitosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_abaDebitosComponentShown
        //  botaoDebitar.setEnabled(true);
        botaoAlterarDebito.setEnabled(false);
        botaoEstornarDebito.setEnabled(false);
    }//GEN-LAST:event_abaDebitosComponentShown

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        try {

            Date dataInicial = new Date();
            Date dataFinal = new Date();

            if (jFTDataPesquisarInicial.getDate() != null && jFTDataPesquisarFinal.getDate() != null) {
                dataInicial = PempecParse.dateToDate(jFTDataPesquisarInicial.getDate());
                dataFinal = PempecParse.dateToDate(jFTDataPesquisarFinal.getDate());
            }

            lColecaoDebito = tesourariaDebitoBO.obterDepositoBanco(organizacaoModel, dataInicial, dataFinal);

            if (lColecaoDebito.size() > 0) {

                habilitaCampos();

                final EventList<TesourariaDebitoModel> lRegistrosDebitos = GlazedLists.eventList(lColecaoDebito);

                if (supportDebito != null && supportDebito.getItemList() != null && supportDebito.getComboBox() != null) {
                    supportDebito.uninstall();
                }
                supportDebito = AutoCompleteSupport.install(comboTituloDebito, lRegistrosDebitos, new TesourariaDebitoTextFilterator());
                supportDebito.setFilterMode(TextMatcherEditor.STARTS_WITH);
                supportDebito.setStrict(false);
            } else {

                exibeMensagemAviso("Lista vazia. Reveja o periodo.", null);
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
    }//GEN-LAST:event_btnPesquisarActionPerformed
    private Boolean validaCampos() {

        if (jValorDeposito.getText().equals("0,00")) {
            jValorDeposito.requestFocus();
            return false;
        }

        if (comboContaBancaria.getSelectedIndex() == 0) {
            comboContaBancaria.requestFocus();
            return false;
        }

        if (comboFuncionario.getSelectedIndex() == 0) {
            comboFuncionario.requestFocus();
            return false;
        }

        if (jFTDataMovimento.getDate() == null) {
            jFTDataMovimento.requestFocus();
            return false;
        }

        //validar data
        if (jFTDataMovimento.getDate().after(Controller.getDataServidorBD())) {
            exibeMensagemAviso("Data de pagamento não pode ser superior a Atual!", null);
            jFTDataMovimento.requestFocus();
            return false;
        }

        return true;

    }

    private Double saldoTesouraria() {

        Double saldo = 0d;

        try {

            saldo = new TesourariaServices(organizacaoModel).obterSaldoTesouraria().doubleValue();

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
        return saldo;
    }

    private Double saldoTesourariaCheque() {

        Double saldo = 0d;

        try {

            saldo = new TesourariaServices(organizacaoModel).obterSaldoCheque(organizacaoModel);

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
        return saldo;
    }

    private CedenteModel inserirCedenteOrganizacao() {
        CedenteModel cedente = new CedenteModel();
        try {

            cedente.setPk(new PKModel());
            cedente.getPk().setOrganizacao(organizacaoModel);
            cedente.getPk().setId(Controller.getOrganizacao().getId());
            cedente.setNome(Controller.getOrganizacao().getRazaoSocial());
            cedente.setCpfCnpj(Controller.getOrganizacao().getCnpj());

            cedenteBO.inserir(cedente);

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
        return cedente;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaConsulta;
    private javax.swing.JPanel abaDebito;
    private javax.swing.JButton botaoAlterarDebito;
    protected javax.swing.JButton botaoDepositar;
    private javax.swing.JButton botaoEstornarDebito;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoFecharDebito;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoLimparDebito;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JTextField campoIDContaCredito;
    private javax.swing.JTextField campoIDTesDebito;
    private javax.swing.JComboBox comboContaBancaria;
    private javax.swing.JComboBox comboContaBancariaEstorno;
    private javax.swing.JComboBox comboFuncionario;
    private javax.swing.JComboBox comboResponsavelDebito;
    private javax.swing.JComboBox comboTituloDebito;
    private javax.swing.JButton jButton3;
    private org.jdesktop.swingx.JXDatePicker jFTDataContabilDebito;
    private org.jdesktop.swingx.JXDatePicker jFTDataMovimento;
    private org.jdesktop.swingx.JXDatePicker jFTDataMovimentoDebito;
    private org.jdesktop.swingx.JXDatePicker jFTDataPesquisarFinal;
    private org.jdesktop.swingx.JXDatePicker jFTDataPesquisarInicial;
    private org.jdesktop.swingx.JXDatePicker jFTDataRegistro;
    private br.com.pempec.componentes.JDoubleField jFTValorDebito;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private br.com.pempec.componentes.JDoubleField jSaldoTesouraria;
    private br.com.pempec.componentes.JDoubleField jSaldoTesourariaCheque;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTAgencia;
    private javax.swing.JTextField jTBanco;
    private javax.swing.JTextField jTCliente;
    private javax.swing.JTextField jTDescricao;
    private javax.swing.JTextField jTDescricaoDebito;
    private javax.swing.JTextField jTObservacaoDebito;
    private br.com.pempec.componentes.JDoubleField jValorDeposito;
    private javax.swing.JLabel labelDataEmissao1;
    private javax.swing.JLabel labelDataEmissao2;
    private javax.swing.JLabel labelDataProtocolo;
    private javax.swing.JLabel labelDataVencimento1;
    private javax.swing.JLabel labelDataVencimento3;
    private javax.swing.JLabel labelDescricao2;
    private javax.swing.JLabel labelDescricao3;
    private javax.swing.JLabel labelExportDebito;
    private javax.swing.JLabel labelNumeroDocumento1;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelTipoLancamentoDebito;
    private javax.swing.JTabbedPane panelCheques;
    // End of variables declaration//GEN-END:variables

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

    private void desabilitaCampos() {

        comboTituloDebito.setEnabled(false);

    }

    private void habilitaCampos() {

        comboTituloDebito.setEnabled(true);

    }

    private AutoCompleteSupport supportDebito;
    private AutoCompleteSupport supportCedente;

}
