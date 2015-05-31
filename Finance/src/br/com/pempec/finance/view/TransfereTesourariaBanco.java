package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.HistoricoModel;
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
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.TesourariaServices;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class TransfereTesourariaBanco extends FinanceInternalFrame implements IRepopulador {

    private TesourariaDebitoBO tesourariaDebitoBO = new TesourariaDebitoBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private CedenteBO cedenteBO = new CedenteBO();

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

    }

    public void repopularCombos() {

        try {

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            ContaBancariaModel contaBancariaModel = new ContaBancariaModel();

            contaBancariaModel.setConta(" -> Selecione <- ");

            lContas.add(contaBancariaModel);
            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancaria.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboFuncionario.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

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

        comboContaBancaria.setFont(new java.awt.Font("Arial", 0, 10));

        labelDataProtocolo.setText("Data Movimento");

        labelResponsavel.setText("Responsável");

        comboFuncionario.setFont(new java.awt.Font("Arial", 0, 11));

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
                .addContainerGap(12, Short.MAX_VALUE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(panelCheques, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCheques, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
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

                    ctbCr.setDataContabil(Controller.getDataServidorBD());
                    ctbCr.setDataRegistro(Controller.getDataServidorBD());
                    ctbCr.setDataMovimento(PempecParse.dateToDate(jFTDataMovimento.getDate()));

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
    protected javax.swing.JButton botaoDepositar;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboContaBancaria;
    private javax.swing.JComboBox comboFuncionario;
    private javax.swing.JButton jButton3;
    private org.jdesktop.swingx.JXDatePicker jFTDataMovimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private br.com.pempec.componentes.JDoubleField jSaldoTesouraria;
    private br.com.pempec.componentes.JDoubleField jSaldoTesourariaCheque;
    private javax.swing.JSeparator jSeparator2;
    private br.com.pempec.componentes.JDoubleField jValorDeposito;
    private javax.swing.JLabel labelDataProtocolo;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JTabbedPane panelCheques;
    // End of variables declaration//GEN-END:variables
}
