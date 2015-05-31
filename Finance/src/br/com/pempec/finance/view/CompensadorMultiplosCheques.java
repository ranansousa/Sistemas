package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaChequeBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.UsuarioModel;
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
import br.com.pempec.finance.utils.tables.ColumnModelCompensadorMultiplosCheques;
import br.com.pempec.finance.utils.tables.TableModelCompensadorMultiplosCheques;
import java.awt.FontMetrics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class CompensadorMultiplosCheques extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaDebitoBO contaBancariaDebitoBO = new ContaBancariaDebitoBO();
    private TituloPagarBaixaChequeBO tituloPagoChequeBO = new TituloPagarBaixaChequeBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private static long tela = Tela.TELA_CONCILIACAO_COMPENSAR_MULTIPLOS_CHEQUES.getTela();

    private String NameObject() {
        return (String) "Compensação de Múltiplos Cheques";
    }

    public void repopularCombos() {

        try {


            // responsavel
            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboResponsavelCompensa.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta(" -> Todas <- ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancariaCompensa.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            List<TituloPagarBaixaChequeModel> lCheques = tituloPagoChequeBO.obterPorChequesEmitidos(null, organizacaoModel);

            List<TituloPagarBaixaChequeModel> lChequesAux = new ArrayList<TituloPagarBaixaChequeModel>();

            TituloPagarBaixaChequeModel aux = null;

            //Removendo os itens com mesmo numero de cheque.
            for (TituloPagarBaixaChequeModel cheque : lCheques) {

                if (aux == null) {
                    aux = cheque;
                    lChequesAux.add(aux);
                    continue;
                }

                if (cheque.getContaBancariaCheque().getNumeroCheque().equals(aux.getContaBancariaCheque().getNumeroCheque())) {
                    continue;
                } else {
                    aux = cheque;
                }

                lChequesAux.add(aux);

            }

            tableCheques.setAutoCreateColumnsFromModel(false);
            tableCheques.setModel(new TableModelCompensadorMultiplosCheques(lChequesAux));
            FontMetrics fm = tableCheques.getFontMetrics(tableCheques.getFont());
            tableCheques.setColumnModel(new ColumnModelCompensadorMultiplosCheques(fm));
            tableCheques.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableCheques.getTableHeader().setReorderingAllowed(false);

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

    public CompensadorMultiplosCheques() throws SystemException {

        initComponents();
        Controller.setQtdMovimentosHoje(0);


        //formatar datas                
        jFTDataChequeCompensado.setDate(Controller.getDataServidorBD());


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoCompensar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaCompensarCheque = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        comboContaBancariaCompensa = new javax.swing.JComboBox();
        labelDataVencimento = new javax.swing.JLabel();
        jFTDataChequeCompensado = new org.jdesktop.swingx.JXDatePicker();
        comboResponsavelCompensa = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCheques = new javax.swing.JTable();
        jCSelTodos = new javax.swing.JCheckBox();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Compensação de Cheques");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoCompensar.setMnemonic('I');
        botaoCompensar.setText("Compensar");
        botaoCompensar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCompensarActionPerformed(evt);
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
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCompensar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(346, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(561, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoCompensar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        abaCompensarCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102))));
        abaCompensarCheque.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                abaCompensarChequeComponentShown(evt);
            }
        });

        jLabel10.setText("Conta Bancária");

        comboContaBancariaCompensa.setFont(new java.awt.Font("Arial", 0, 10));
        comboContaBancariaCompensa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaBancariaCompensaItemStateChanged(evt);
            }
        });

        labelDataVencimento.setText("Data");

        comboResponsavelCompensa.setFont(new java.awt.Font("Arial", 0, 10));

        jLabel7.setText("Responsável");

        tableCheques.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5"
            }
        ));
        jScrollPane2.setViewportView(tableCheques);

        jCSelTodos.setText("Selecionar Todos");
        jCSelTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCSelTodosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaCompensarChequeLayout = new javax.swing.GroupLayout(abaCompensarCheque);
        abaCompensarCheque.setLayout(abaCompensarChequeLayout);
        abaCompensarChequeLayout.setHorizontalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(comboContaBancariaCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTDataChequeCompensado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataVencimento))
                        .addGap(29, 29, 29)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                .addComponent(comboResponsavelCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jCSelTodos)))))
                .addContainerGap())
        );
        abaCompensarChequeLayout.setVerticalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboContaBancariaCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDataVencimento)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTDataChequeCompensado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboResponsavelCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCSelTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Compensação de Cheque", abaCompensarCheque);
        abaCompensarCheque.getAccessibleContext().setAccessibleName("abaCompensarCheque");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 901, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Estorno de Compensação");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        //Somente setar null nos combos que possui o auto-complete

        comboResponsavelCompensa.setSelectedIndex(0);
        comboContaBancariaCompensa.setSelectedIndex(0);
        jFTDataChequeCompensado.setDate(Controller.getDataServidorBD());

        jCSelTodos.setSelected(false);

        this.jCSelTodosActionPerformed(evt);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (jFTDataChequeCompensado.getDate() == null) {
            jFTDataChequeCompensado.requestFocus();
            return false;
        }

        if (comboResponsavelCompensa.getSelectedIndex() == 0) {
            comboResponsavelCompensa.requestFocus();
            return false;
        }

        if (jFTDataChequeCompensado.getDate() != null) {
            Date dataInformada = jFTDataChequeCompensado.getDate();
            if (dataInformada.after(Controller.getDataServidorBD())) {
                exibeMensagemAviso("A Data de Compensação não pode ser superior a HOJE.!", null);
                jFTDataChequeCompensado.requestFocus();
                return false;
            }
        }

        boolean validaPreenchimento = false;

        for (int i = 0; i < tableCheques.getRowCount(); i++) {
            if (tableCheques.getValueAt(i, 0).toString().equals("true")) {
                validaPreenchimento = true;
            }
        }

        if (!validaPreenchimento) {
            exibeMensagemAviso("Selecione um Item!", null);
            return false;
        }

        if (jFTDataChequeCompensado.getDate() != null && !(PempecUtil.isDiaUtil(PempecParse.dateToDate(jFTDataChequeCompensado.getDate())))) {
            exibeMensagemAviso("A data de compensação do cheque só pode ser um dia útil.", null);
            return false;
        }

        return true;
    }

private void comboContaBancariaCompensaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaBancariaCompensaItemStateChanged

    botaoCompensar.setEnabled(true);
    jFTDataChequeCompensado.setEnabled(true);
    comboResponsavelCompensa.setEnabled(true);

    if (comboContaBancariaCompensa.getSelectedItem() != null
            && ((ContaBancariaModel) comboContaBancariaCompensa.getSelectedItem()).getPk() != null) {

        try {


            List<TituloPagarBaixaChequeModel> lCheques = tituloPagoChequeBO.obterPorChequesEmitidos((ContaBancariaModel) comboContaBancariaCompensa.getSelectedItem(), organizacaoModel);

            List<TituloPagarBaixaChequeModel> lChequesAux = new ArrayList<TituloPagarBaixaChequeModel>();

            TituloPagarBaixaChequeModel aux = null;

            //Removendo os itens com mesmo numero de cheque.
            for (TituloPagarBaixaChequeModel cheque : lCheques) {

                if (aux == null) {
                    aux = cheque;
                    lChequesAux.add(aux);
                    continue;
                }

                if (cheque.getContaBancariaCheque().getNumeroCheque().equals(aux.getContaBancariaCheque().getNumeroCheque())) {
                    continue;
                } else {
                    aux = cheque;
                }

                lChequesAux.add(aux);

            }

            tableCheques.setAutoCreateColumnsFromModel(false);
            tableCheques.setModel(new TableModelCompensadorMultiplosCheques(lChequesAux));
            FontMetrics fm = tableCheques.getFontMetrics(tableCheques.getFont());
            tableCheques.setColumnModel(new ColumnModelCompensadorMultiplosCheques(fm));
            tableCheques.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableCheques.getTableHeader().setReorderingAllowed(false);

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

        try {

            List<TituloPagarBaixaChequeModel> lCheques = tituloPagoChequeBO.obterPorChequesEmitidos(null, organizacaoModel);

            List<TituloPagarBaixaChequeModel> lChequesAux = new ArrayList<TituloPagarBaixaChequeModel>();

            TituloPagarBaixaChequeModel aux = null;

            //Removendo os itens com mesmo numero de cheque.
            for (TituloPagarBaixaChequeModel cheque : lCheques) {

                if (aux == null) {
                    aux = cheque;
                    lChequesAux.add(aux);
                    continue;
                }

                if (cheque.getContaBancariaCheque().getNumeroCheque().equals(aux.getContaBancariaCheque().getNumeroCheque())) {
                    continue;
                } else {
                    aux = cheque;
                }

                lChequesAux.add(aux);

            }

            tableCheques.setAutoCreateColumnsFromModel(false);
            tableCheques.setModel(new TableModelCompensadorMultiplosCheques(lChequesAux));
            FontMetrics fm = tableCheques.getFontMetrics(tableCheques.getFont());
            tableCheques.setColumnModel(new ColumnModelCompensadorMultiplosCheques(fm));
            tableCheques.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tableCheques.getTableHeader().setReorderingAllowed(false);

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

}//GEN-LAST:event_comboContaBancariaCompensaItemStateChanged

private void botaoCompensarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCompensarActionPerformed


    try {

        long action = Action.OUTROS.getAction();

        if (!Controller.checarPermissao(tela, action)) {
            exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
            return;

        }

        if (validaCampos()) {

            Collection<ContaBancariaDebitoModel> lista = new ArrayList<ContaBancariaDebitoModel>();

            ContaBancariaChequeModel chequeEmitido;

            ContaBancariaDebitoModel chequeCompensado;

            UsuarioModel usuarioModel = Controller.getUsuarioLogado();

            TipoStatusModel status = new TipoStatusModel();
            status.setPk(new PKModel());
            status.getPk().setOrganizacao(organizacaoModel);
            status.getPk().setId(Constantes.STATUS_CHEQUE_COMPENSADO);

            for (int i = 0; i < tableCheques.getRowCount(); i++) {

                if ((Boolean) tableCheques.getValueAt(i, 0)) {

                    chequeEmitido = (ContaBancariaChequeModel) tableCheques.getValueAt(i, 6);

                    chequeCompensado = new ContaBancariaDebitoModel();

                    chequeEmitido.setContaBancaria(((ContaBancariaChequeModel) tableCheques.getValueAt(i, 6)).getContaBancaria());

                    if (chequeEmitido.getQtdImpressao() == 0) {

                        chequeEmitido.setQtdImpressao(1);

                        chequeEmitido.setObservacao(chequeEmitido.getObservacao() + "\n" + "Compensou ch sem imprimir " + Controller.getUsuarioLogado().getNome());
                    }

                    chequeCompensado.setContaBancariaCheque(chequeEmitido);
                    chequeCompensado.setPk(new PKModel());
                    chequeCompensado.getPk().setOrganizacao(organizacaoModel);
                    chequeCompensado.getPk().setId(PempecKeyGenerator.generateKey());
                    chequeCompensado.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaDebito());

                    chequeCompensado.setTipoOperacaoBancaria((TipoOperacaoBancariaModel) tableCheques.getValueAt(i, 7));

                    chequeCompensado.setTituloPagar((TituloPagarModel) tableCheques.getValueAt(i, 8));

                    chequeCompensado.setDescricao("Cheque  " + chequeEmitido.getNumeroCheque() + (String) tableCheques.getValueAt(i, 4));

                    chequeCompensado.setContaBancaria(((ContaBancariaChequeModel) tableCheques.getValueAt(i, 6)).getContaBancaria());

                    chequeCompensado.setDataMovimento(PempecParse.dateToDate(jFTDataChequeCompensado.getDate()));

                    chequeCompensado.setDataRegistro(Controller.getDataServidorBD());
                    chequeCompensado.setTipoLancamento("D");
                    chequeCompensado.setValor(((ContaBancariaChequeModel) tableCheques.getValueAt(i, 6)).getValor());
                    chequeCompensado.setUsuario(usuarioModel);

                    if (comboResponsavelCompensa.getSelectedItem() != null && ((FuncionarioModel) comboResponsavelCompensa.getSelectedItem()).getPk() != null) {
                        chequeCompensado.setResponsavel(new FuncionarioModel());
                        chequeCompensado.getResponsavel().setPk(new PKModel());
                        chequeCompensado.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavelCompensa.getSelectedItem()).getPk().getId());
                        chequeCompensado.getResponsavel().getPk().setOrganizacao(organizacaoModel);
                    }

                    chequeEmitido.setDataCompensado(PempecParse.dateToDate(jFTDataChequeCompensado.getDate()));
                    chequeEmitido.setStatus(status);
                    chequeCompensado.setMovimentoDiarioModel(registroMovimento("Compensar Mult. CH", "Cheque ", "Compensação de Cheque num. " + chequeCompensado.getContaBancariaCheque().getNumeroCheque(), chequeCompensado.getValor(), "Compensado"));

                    lista.add(chequeCompensado);

                }

            }

            contaBancariaDebitoBO.inserirMultiplos(lista);

            this.botaoLimparActionPerformed(evt);

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



}//GEN-LAST:event_botaoCompensarActionPerformed

private void abaCompensarChequeComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_abaCompensarChequeComponentShown
    botaoCompensar.setEnabled(true);
}//GEN-LAST:event_abaCompensarChequeComponentShown

private void jCSelTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCSelTodosActionPerformed

    for (int i = 0; i < tableCheques.getRowCount(); i++) {
        tableCheques.setValueAt(jCSelTodos.isSelected(), i, 0);
    }

}//GEN-LAST:event_jCSelTodosActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque;
    private javax.swing.JButton botaoCompensar;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboContaBancariaCompensa;
    private javax.swing.JComboBox comboResponsavelCompensa;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jCSelTodos;
    private org.jdesktop.swingx.JXDatePicker jFTDataChequeCompensado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JTable tableCheques;
    // End of variables declaration//GEN-END:variables
}
