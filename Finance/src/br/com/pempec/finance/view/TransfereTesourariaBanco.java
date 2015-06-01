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
import java.awt.Color;
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

    Collection<TesourariaDebitoModel> lColecaoDebito;

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

            //responsavel
            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboFuncionario.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            //Docmentos
            lColecaoDebito = new ArrayList<TesourariaDebitoModel>();

            TesourariaDebitoModel tesourariaDebitoModel = new TesourariaDebitoModel();

            tesourariaDebitoModel.setNumeroDocumento(" -> Selecione <- ");

            lColecaoDebito.add(tesourariaDebitoModel);

            //lColecaoDebito.addAll(tesourariaDebitoBO.obterTodos(organizacaoModel));
            comboTituloDebito.setModel(new javax.swing.DefaultComboBoxModel(lColecaoDebito.toArray()));

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
        labelNumeroDocumento1 = new javax.swing.JLabel();
        comboTituloDebito = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        botaoFecharDebito = new javax.swing.JButton();
        botaoLimparDebito = new javax.swing.JButton();
        botaoEstornarDebito = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jFTDataPesquisarInicial = new org.jdesktop.swingx.JXDatePicker();
        jFTDataPesquisarFinal = new org.jdesktop.swingx.JXDatePicker();
        btnPesquisar = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lblContaCBC = new javax.swing.JLabel();
        lblBancoCBC = new javax.swing.JLabel();
        lblAgenciaCBC = new javax.swing.JLabel();
        lblCllienteCBC = new javax.swing.JLabel();
        lblDescricaoCBC = new javax.swing.JLabel();
        campoIDContaCredito = new javax.swing.JTextField();
        lblDataRegistroCBC = new javax.swing.JLabel();
        lblDataMovimentoCBC = new javax.swing.JLabel();
        lblDataContabilCBC = new javax.swing.JLabel();
        lblTipoOpBancariaCBC = new javax.swing.JLabel();
        lblValorCBC = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblDescricaoTD = new javax.swing.JLabel();
        lblObservacaoTD = new javax.swing.JLabel();
        lblValorTD = new javax.swing.JLabel();
        lblResponsavelTD = new javax.swing.JLabel();
        lblDataMovimentoTD = new javax.swing.JLabel();
        lblDataContabilTD = new javax.swing.JLabel();
        lblDataRegistroTD = new javax.swing.JLabel();
        lblHistoricoTD = new javax.swing.JLabel();
        labelTipoLancamentoDebito = new javax.swing.JLabel();

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
                .addContainerGap(521, Short.MAX_VALUE))
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

        labelNumeroDocumento1.setText("Número do Documento");

        comboTituloDebito.setToolTipText("");
        comboTituloDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloDebitoActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(botaoEstornarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoLimparDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoEstornarDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimparDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFecharDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data a ser Pesquisa"));

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("De :");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("até :");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel17)
                .addGap(42, 42, 42)
                .addComponent(jFTDataPesquisarInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jFTDataPesquisarFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Bancários"));
        jPanel5.setForeground(new java.awt.Color(0, 153, 153));

        lblContaCBC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblContaCBC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Conta"));

        lblBancoCBC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblBancoCBC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Banco"));

        lblAgenciaCBC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAgenciaCBC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Agência"));

        lblCllienteCBC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cliente"));

        lblDescricaoCBC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descrição"));

        campoIDContaCredito.setEditable(false);

        lblDataRegistroCBC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataRegistroCBC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Registro"));

        lblDataMovimentoCBC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataMovimentoCBC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Movimento"));

        lblDataContabilCBC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblDataContabilCBC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Contábil"));

        lblTipoOpBancariaCBC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo Operação Bancária"));

        lblValorCBC.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        lblValorCBC.setForeground(new java.awt.Color(0, 153, 204));
        lblValorCBC.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorCBC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor"));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblDescricaoCBC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblBancoCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblAgenciaCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblContaCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblTipoOpBancariaCBC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(lblDataRegistroCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lblDataMovimentoCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblDataContabilCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblCllienteCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(campoIDContaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblValorCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(237, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(campoIDContaCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblBancoCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAgenciaCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCllienteCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblContaCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricaoCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataRegistroCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblDataMovimentoCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDataContabilCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTipoOpBancariaCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValorCBC, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Dados Tesouraria"));

        lblDescricaoTD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descrição"));

        lblObservacaoTD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Observação"));

        lblValorTD.setForeground(new java.awt.Color(0, 153, 204));
        lblValorTD.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblValorTD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor"));

        lblResponsavelTD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Responsável"));

        lblDataMovimentoTD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Movimento"));

        lblDataContabilTD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Contábil"));

        lblDataRegistroTD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Registro"));

        lblHistoricoTD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Histórico"));

        labelTipoLancamentoDebito.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelTipoLancamentoDebito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoLancamentoDebito.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo Lançamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 10))); // NOI18N
        labelTipoLancamentoDebito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDescricaoTD, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObservacaoTD, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblValorTD, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDataMovimentoTD, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblResponsavelTD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblDataContabilTD, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDataRegistroTD, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelTipoLancamentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblHistoricoTD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblDataRegistroTD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblValorTD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescricaoTD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataMovimentoTD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataContabilTD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipoLancamentoDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblObservacaoTD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblResponsavelTD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHistoricoTD, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout abaDebitoLayout = new javax.swing.GroupLayout(abaDebito);
        abaDebito.setLayout(abaDebitoLayout);
        abaDebitoLayout.setHorizontalGroup(
            abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDebitoLayout.createSequentialGroup()
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaDebitoLayout.createSequentialGroup()
                                .addComponent(comboTituloDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(campoIDTesDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelNumeroDocumento1)))
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addGap(405, 405, 405)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        abaDebitoLayout.setVerticalGroup(
            abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDebitoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(abaDebitoLayout.createSequentialGroup()
                        .addComponent(labelNumeroDocumento1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaDebitoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboTituloDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoIDTesDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panelCheques.addTab("Estornos", abaDebito);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelCheques, javax.swing.GroupLayout.PREFERRED_SIZE, 1170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCheques, javax.swing.GroupLayout.PREFERRED_SIZE, 628, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        if (comboTituloDebito.getSelectedItem() != null) {

            jFTDataPesquisarInicial.setEnabled(false);
            jFTDataPesquisarFinal.setEnabled(false);
            btnPesquisar.setEnabled(false);

            try {

                TesourariaDebitoModel tab = new TesourariaDebitoModel();

                tab.setNumeroDocumento(comboTituloDebito.getSelectedItem().toString());
                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(Controller.getOrganizacao());

                tab = tesourariaDebitoBO.consultarPorTemplate(tab);

                if (tab != null && tab.getPk() != null) {

                    campoIDTesDebito.setText(tab.getPk().getId());
                    lblDescricaoTD.setText(tab.getDescricao());
                    Double valor = tab.getValorNominal();
                    lblValorTD.setText(PempecParse.doubleToZero(valor));

                    lblDataContabilTD.setText(PempecParse.dateToString(tab.getDataContabil()));
                    lblDataMovimentoTD.setText(PempecParse.dateToString(tab.getDataMovimento()));
                    lblDataRegistroTD.setText(PempecParse.dateToString(tab.getDataRegistro()));

                    lblObservacaoTD.setText(tab.getObservacao());

                    if (tab.getHistorico() != null) {
                        lblHistoricoTD.setText(tab.getHistorico().getPk().getId());
                    }

                    labelTipoLancamentoDebito.setText(tab.getTipoLancamento());

                    if (tab.getResponsavel() != null) {
                        lblResponsavelTD.setText(tab.getResponsavel().getNome());
                    }

                    ContaBancariaCreditoModel contaCreditada = new ContaBancariaCreditoModel();
                    contaCreditada.setPk(new PKModel());
                    contaCreditada.getPk().setOrganizacao(Controller.getOrganizacao());
                    contaCreditada.setIdentificacao(tab.getNumeroDocumento());

                    contaCreditada = contaBancariaCreditoBO.consultarPorTemplate(contaCreditada);

                    if (contaCreditada != null) {

                        campoIDContaCredito.setText(contaCreditada.getPk().getId());

                        lblAgenciaCBC.setText(contaCreditada.getContaBancaria().getAgencia());
                        lblCllienteCBC.setText(contaCreditada.getContaBancaria().getTitular());
                        String siglaBanco = "";
                        if (contaCreditada.getContaBancaria().getBanco().getSiglaBanco() != null && !contaCreditada.getContaBancaria().getBanco().getSiglaBanco().isEmpty()) {

                            siglaBanco = " - " +  contaCreditada.getContaBancaria().getBanco().getSiglaBanco();
                        }

                        lblBancoCBC.setText(contaCreditada.getContaBancaria().getBanco().getCodigoBanco() + siglaBanco );
                        lblDescricaoCBC.setText(contaCreditada.getDescricao());
                        lblContaCBC.setText(contaCreditada.getContaBancaria().getConta());
                        lblTipoOpBancariaCBC.setText(contaCreditada.getTipoOperacaoBancaria().getDescricao());
                        valor = contaCreditada.getValor();
                        lblValorCBC.setText(PempecParse.doubleToZero(valor));
                        lblDataContabilCBC.setText(PempecParse.dateToString(contaCreditada.getDataContabil()));
                        lblDataMovimentoCBC.setText(PempecParse.dateToString(contaCreditada.getDataMovimento()));
                        lblDataRegistroCBC.setText(PempecParse.dateToString(contaCreditada.getDataRegistro()));

                        if (contaCreditada != null && contaCreditada.getPk() != null && contaCreditada.getPk().getId() != null
                                && (!contaCreditada.getPk().getId().isEmpty())) {
                            botaoEstornarDebito.setEnabled(true);
                        }

                    } else {

                        this.botaoLimparDebitoActionPerformed(evt);
                        exibeMensagemAviso("Dados inconsistentes. Estorno impossível.", "Estorno DEP BANCO");

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
        comboTituloDebito.setSelectedIndex(0);
        botaoEstornarDebito.setEnabled(false);

        labelTipoLancamentoDebito.setVisible(false);
        campoIDTesDebito.setText("");
        campoIDContaCredito.setText("");
        lblValorTD.setText("0,00");
        lblDescricaoTD.setText("");
        lblDataContabilTD.setText("");
        lblDataMovimentoTD.setText("");
        lblDataRegistroTD.setText("");
        lblResponsavelTD.setText("");
        lblHistoricoTD.setText("");
        lblObservacaoTD.setText("");

        lblDescricaoCBC.setText("");
        lblValorCBC.setText("0,00");

        lblAgenciaCBC.setText("");
        lblBancoCBC.setText("");
        lblCllienteCBC.setText("");
        lblContaCBC.setText("");
        lblDescricaoCBC.setText("");
        lblTipoOpBancariaCBC.setText("");
        lblValorCBC.setText("0.00");
        lblDataContabilCBC.setText("");
        lblDataMovimentoCBC.setText("");
        lblDataRegistroCBC.setText("");

        jFTDataPesquisarInicial.setEnabled(true);
        jFTDataPesquisarFinal.setEnabled(true);
        btnPesquisar.setEnabled(true);

        comboTituloDebito.setEnabled(false);
        lColecaoDebito = null;

        this.repopularCombos();


    }//GEN-LAST:event_botaoLimparDebitoActionPerformed

    private void botaoEstornarDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEstornarDebitoActionPerformed
        String valorCombo = null;

        if (comboTituloDebito.getSelectedItem() != null) {
            valorCombo = comboTituloDebito.getSelectedItem().toString();
        }

        boolean isEstorno = false;

        if (valorCombo != null) {

            try {

                long action = Action.OUTROS.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (validaCamposDebitos()) {

                    TesourariaDebitoModel tab = new TesourariaDebitoModel();
                    ContaBancariaCreditoModel cbc = new ContaBancariaCreditoModel();

                    OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                    tab.setPk(new PKModel());

                    tab.getPk().setOrganizacao(organizacaoModel);

                    tab.setNumeroDocumento(valorCombo);

                    tab.getPk().setId(campoIDTesDebito.getText());

                    tab = tesourariaDebitoBO.consultarPorTemplate(tab);

                    if (tab != null && tab.getPk() != null && tab.getPk().getId() != null) {

                        cbc.setPk(new PKModel());

                        cbc.getPk().setOrganizacao(organizacaoModel);

                        cbc.setIdentificacao(valorCombo);

                        cbc.getPk().setId(campoIDContaCredito.getText());

                        cbc = contaBancariaCreditoBO.consultarPorTemplate(cbc);

                        if (cbc != null && cbc.getPk() != null && cbc.getPk().getId() != null) {

                            if (cbc.getValor() == tab.getValorNominal()) {

                                isEstorno = true;

                            }
                        }

                    } else {

                        exibeMensagemAviso("Impossivel estornar.", "Erro no lançamento TD");
                    }

                    // tab.setMovimentoDiarioModel(registroMovimento("Estornar", tab.getNumeroDocumento(), ((HistoricoModel) comboHistoricoDebito.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), tab.getValorNominal(), "Estornado"));
                    tab.setMovimentoDiarioModel(registroMovimento("Estornar", tab.getNumeroDocumento(), tab.getNumeroDocumento() + " para " + tab.getDescricao(), tab.getValorNominal(), "Estornado"));
                    tab.getMovimentoDiarioModel().setObservacao("Lanc. Tesour. Debito Estornado ");

                    if (isEstorno) {

                        tesourariaDebitoBO.excluirDepositoBanco(tab, cbc);
                    }

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

    private void abaDebitosComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_abaDebitosComponentShown
        //  botaoDebitar.setEnabled(true);
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

                comboTituloDebito.setModel(new javax.swing.DefaultComboBoxModel(lColecaoDebito.toArray()));

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
    private javax.swing.JComboBox comboFuncionario;
    private javax.swing.JComboBox comboTituloDebito;
    private javax.swing.JButton jButton3;
    private org.jdesktop.swingx.JXDatePicker jFTDataMovimento;
    private org.jdesktop.swingx.JXDatePicker jFTDataPesquisarFinal;
    private org.jdesktop.swingx.JXDatePicker jFTDataPesquisarInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private br.com.pempec.componentes.JDoubleField jSaldoTesouraria;
    private br.com.pempec.componentes.JDoubleField jSaldoTesourariaCheque;
    private javax.swing.JSeparator jSeparator2;
    private br.com.pempec.componentes.JDoubleField jValorDeposito;
    private javax.swing.JLabel labelDataProtocolo;
    private javax.swing.JLabel labelNumeroDocumento1;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelTipoLancamentoDebito;
    private javax.swing.JLabel lblAgenciaCBC;
    private javax.swing.JLabel lblBancoCBC;
    private javax.swing.JLabel lblCllienteCBC;
    private javax.swing.JLabel lblContaCBC;
    private javax.swing.JLabel lblDataContabilCBC;
    private javax.swing.JLabel lblDataContabilTD;
    private javax.swing.JLabel lblDataMovimentoCBC;
    private javax.swing.JLabel lblDataMovimentoTD;
    private javax.swing.JLabel lblDataRegistroCBC;
    private javax.swing.JLabel lblDataRegistroTD;
    private javax.swing.JLabel lblDescricaoCBC;
    private javax.swing.JLabel lblDescricaoTD;
    private javax.swing.JLabel lblHistoricoTD;
    private javax.swing.JLabel lblObservacaoTD;
    private javax.swing.JLabel lblResponsavelTD;
    private javax.swing.JLabel lblTipoOpBancariaCBC;
    private javax.swing.JLabel lblValorCBC;
    private javax.swing.JLabel lblValorTD;
    private javax.swing.JTabbedPane panelCheques;
    // End of variables declaration//GEN-END:variables

    private Boolean validaCamposDebitos() {

        return true;
    }

    private void desabilitaCampos() {

        comboTituloDebito.setEnabled(false);

    }

    private void habilitaCampos() {

        comboTituloDebito.setEnabled(true);

    }

}
