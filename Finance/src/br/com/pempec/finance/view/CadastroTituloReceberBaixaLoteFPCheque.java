package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.BancoBO;
import br.com.pempec.finance.businessObjects.SacadoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopuladorNew;
import br.com.pempec.finance.utils.MaskUtils;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import static br.com.pempec.finance.utils.Tela.TELA_PARAMETROS_BANCOS;
import br.com.pempec.finance.utils.iterators.BancoTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroTituloReceberBaixaLoteFPCheque extends FinanceInternalFrame implements IRepopuladorNew {

    private CadastroTituloReceberBaixaLote baixa;
    private TituloReceberBaixaFormaPagamentoModel formaPagamento;
    private BancoBO bancoBO = new BancoBO();
    private AutoCompleteSupport supportBanco;

    private String NameObject() {
        return (String) "Recebimento em Cheque";
    }

    public void repopularCombos(Tela tela, Object object) {

        try {

            switch (tela) {

                case TELA_PARAMETROS_BANCOS:

                    Collection<BancoModel> lBanco = bancoBO.obterTodos();

                    //comboBanco
                    EventList<BancoModel> lRegBancos = GlazedLists.eventList(lBanco);
                    if (supportBanco != null && supportBanco.getItemList() != null && supportBanco.getComboBox() != null) {
                        supportBanco.uninstall();
                    }
                    supportBanco = AutoCompleteSupport.install(comboBanco, lRegBancos, new BancoTextFilterator());
                    supportBanco.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportBanco.setStrict(false);

                    BancoModel bancoModel = (BancoModel) object;

                    if (bancoModel != null) {

                        for (int i = 1; i < comboBanco.getItemCount(); i++) {
                            if (bancoModel.getId().equalsIgnoreCase(((BancoModel) comboBanco.getItemAt(i)).getId())) {
                                comboBanco.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                default:

                    lBanco = bancoBO.obterTodos();

                    //comboBanco
                    lRegBancos = GlazedLists.eventList(lBanco);
                    if (supportBanco != null && supportBanco.getItemList() != null && supportBanco.getComboBox() != null) {
                        supportBanco.uninstall();
                    }
                    supportBanco = AutoCompleteSupport.install(comboBanco, lRegBancos, new BancoTextFilterator());
                    supportBanco.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportBanco.setStrict(false);

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

    private Boolean validaCampos() {

        if (jFTValorCheque.getText().equals("0,00")) {
            jFTValorCheque.requestFocus();
            return false;
        }



        if (comboBanco.getSelectedItem() == null) {
            comboBanco.requestFocus();
            return false;
        }

        if (comboPersonalidade.getSelectedItem() == null) {
            comboPersonalidade.requestFocus();
            return false;
        }

        if (jFTDataProtocolo.getDate() == null) {
            jFTDataProtocolo.requestFocus();
            return false;
        }




        //Validando Datas.


        if (jFTDataProtocolo.getDate() != null) {
            Date dataInformada = jFTDataProtocolo.getDate();
            if (dataInformada.after(Controller.getDataServidorBD())) {
                exibeMensagemAviso("A Data de Protocolo não pode ser superior a data de HOJE.!", null);
                jFTDataProtocolo.requestFocus();
                return false;
            }
        }




        return true;

    }

    public CadastroTituloReceberBaixaLoteFPCheque(CadastroTituloReceberBaixaLote baixa, TituloReceberBaixaFormaPagamentoModel formaPagamento, SacadoModel sacadoModel) throws SystemException {

        this.baixa = baixa;

        this.formaPagamento = formaPagamento;

        initComponents();

        this.repopularCombos(Tela.TELA_PRINCIPAL, null);

        comboPersonalidade.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" -> Selecione <- ",
            "PF", "PJ"
        }));

        //Aplicando tamanho maximo de caracteres do campo.
        jTAgencia.setDocument(new Documento(10));
        jTConta.setDocument(new Documento(20));
        jTNumeroCheque.setDocument(new Documento(10));
        jTTitular.setDocument(new Documento(60));


//preenchendo a tela com dados do cheque
        jTTitular.setText(" ");
        comboPersonalidade.setSelectedIndex(0);
        jFTCpfCpnj.setText(" ");
        jTAgencia.setText(" ");
        jTConta.setText(" ");

        jFTValorCheque.setText(PempecParse.doubleToZero(PempecParse.stringToDouble(baixa.jFTValorTotal.getText())));
        jFTDataProtocolo.setDate(PempecParse.dateToDate(new Date()));
        jTObservacao.setText("Rcbto Lote : " + this.baixa.jTNumeroLote.getText());

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoLimpar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        labelValorPagar = new javax.swing.JLabel();
        jFTValorCheque = new br.com.pempec.componentes.JDoubleField();
        labelDataEmissao = new javax.swing.JLabel();
        labelNomeBanco = new javax.swing.JLabel();
        comboBanco = new javax.swing.JComboBox();
        jTNumeroCheque = new javax.swing.JTextField();
        labelPortador1 = new javax.swing.JLabel();
        labelAgencia = new javax.swing.JLabel();
        jTAgencia = new javax.swing.JTextField();
        labelTitular2 = new javax.swing.JLabel();
        jTTitular = new javax.swing.JTextField();
        labelTipoSacado1 = new javax.swing.JLabel();
        comboPersonalidade = new javax.swing.JComboBox();
        labelCpfCnpj = new javax.swing.JLabel();
        jFTCpfCpnj = new javax.swing.JFormattedTextField();
        labelAgencia1 = new javax.swing.JLabel();
        jTConta = new javax.swing.JTextField();
        jFTDataProtocolo = new org.jdesktop.swingx.JXDatePicker();
        jTObservacao = new javax.swing.JTextField();
        labelTitular3 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setClosable(true);
        setTitle("PEMPEC ENTERPRISE - Finance -  Recebimento em Cheque");
        setMinimumSize(new java.awt.Dimension(641, 427));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoSalvar.setMnemonic('I');
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
            }
        });

        botaoLimpar.setMnemonic('L');
        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoFechar.setMnemonic('F');
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoLimpar, botaoSalvar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)), NameObject()));

        labelValorPagar.setText("Valor");

        jFTValorCheque.setEditable(false);

        labelDataEmissao.setText("Data Protocolo");

        labelNomeBanco.setText("Banco");

        comboBanco.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N

        labelPortador1.setText("Número Cheque");

        labelAgencia.setText("Agência");

        jTAgencia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelTitular2.setText("Titular");

        jTTitular.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelTipoSacado1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelTipoSacado1.setText("Personalidade");

        comboPersonalidade.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        comboPersonalidade.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboPersonalidadeItemStateChanged(evt);
            }
        });

        labelCpfCnpj.setText("CPF");

        labelAgencia1.setText("Conta");

        jTConta.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jTObservacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        labelTitular3.setText("Observação");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNomeBanco)
                                    .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelAgencia)
                                    .addComponent(jTAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelValorPagar)
                                    .addComponent(jFTValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelTitular2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataEmissao)
                            .addComponent(jFTDataProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTipoSacado1)
                                    .addComponent(comboPersonalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTConta, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelAgencia1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelPortador1)
                                    .addComponent(jTNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCpfCnpj)
                                    .addComponent(jFTCpfCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jTObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTitular3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelAgencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelNomeBanco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelAgencia1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelPortador1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTNumeroCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelValorPagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDataEmissao)
                        .addGap(31, 31, 31))
                    .addComponent(jFTDataProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTitular2)
                    .addComponent(labelTipoSacado1)
                    .addComponent(labelCpfCnpj))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPersonalidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTCpfCpnj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(labelTitular3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 712, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("PEMPEC ENTERPRISE - Finance -  Recebimento em Cheque");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed
//GEN-LAST:event_botaoSalvarActionPerformed
        //Faz o processo de salvar e retorna o BEAN populado com os dados do cheque para tela da BAIXA.
        TituloReceberBaixaChequeModel tituloReceberBaixaChequeModel = new TituloReceberBaixaChequeModel();

        // String numDocumento = formaPagamento.getTituloReceberBaixaModel().getTitulo().getNumeroDocumento();
        
        if (validaCampos()) {

            if (validarCombos("comboBanco")) {

                tituloReceberBaixaChequeModel.setPk(new PKModel());
                tituloReceberBaixaChequeModel.getPk().setId(PempecKeyGenerator.generateKey());
                tituloReceberBaixaChequeModel.getPk().setOrganizacao(organizacaoModel);
                tituloReceberBaixaChequeModel.setBanco((BancoModel) comboBanco.getSelectedItem());
                tituloReceberBaixaChequeModel.setNumeroCheque(jTNumeroCheque.getText());
                tituloReceberBaixaChequeModel.setAgencia(jTAgencia.getText());
                tituloReceberBaixaChequeModel.setConta(jTConta.getText());
                tituloReceberBaixaChequeModel.setTitular(jTTitular.getText());
                tituloReceberBaixaChequeModel.setCpfCnpj(jFTCpfCpnj.getText());
                tituloReceberBaixaChequeModel.setDataProtocolo((jFTDataProtocolo.getDate()));
                tituloReceberBaixaChequeModel.setPersonalidade(comboPersonalidade.getSelectedItem().toString());
                tituloReceberBaixaChequeModel.setValor(jFTValorCheque.getValue());
                // tituloReceberBaixaChequeModel.setMovimentoDiarioModel(registroMovimento("Inserir", "Recb Cheque ", "Tit.R : " + numDocumento, jFTValorCheque.getValue(), "Inserido"));

                formaPagamento.setValor(jFTValorCheque.getValue());

                baixa.addCollecaoCheques(tituloReceberBaixaChequeModel, formaPagamento);

                this.botaoLimparActionPerformed(evt);
                this.dispose();

            }

        } else {

            exibeMensagemAviso("Campo(s) Obrigatório(s).", null);

        }



    }

private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

    jFTDataProtocolo.setDate(baixa.jFTDataPagamento.getDate());
    comboBanco.setSelectedItem(null);
    comboPersonalidade.setSelectedIndex(0);
    jFTValorCheque.setText("0,00");
    jTAgencia.setText("");
    jTConta.setText("");
    jTTitular.setText("");
    jFTDataProtocolo.setDate(Controller.getDataServidorBD());
    jFTCpfCpnj.setValue(null);

    jTObservacao.setText("");

    if (supportBanco != null && supportBanco.getItemList() != null && supportBanco.getComboBox() != null) {
        supportBanco.uninstall();
    }

}//GEN-LAST:event_botaoLimparActionPerformed

private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
    try {
        this.botaoLimparActionPerformed(evt);
        setClosed(true);

    } catch (final Exception ex) {

        final File file = PrintScreen.capture();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                tratamentoExcecao(new SystemException(ex), file);

            }
        });

    }
}//GEN-LAST:event_botaoFecharActionPerformed

    private void comboPersonalidadeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboPersonalidadeItemStateChanged
        if (((String) comboPersonalidade.getSelectedItem()).toString().equalsIgnoreCase("PF")) {
            jFTCpfCpnj.setVisible(true);
            labelCpfCnpj.setVisible(true);
            labelCpfCnpj.setText("CPF");
            jFTCpfCpnj.setFormatterFactory(MaskUtils.mascaraCpf());
            jFTCpfCpnj.setValue(null);
        } else {
            if (((String) comboPersonalidade.getSelectedItem()).toString().equalsIgnoreCase("PJ")) {
                jFTCpfCpnj.setVisible(true);
                labelCpfCnpj.setVisible(true);
                labelCpfCnpj.setText("CNPJ");
                jFTCpfCpnj.setValue(null);
                jFTCpfCpnj.setFormatterFactory(MaskUtils.mascaraCnpj());
            } else {
                jFTCpfCpnj.setVisible(false);
                labelCpfCnpj.setVisible(false);
                jFTCpfCpnj.setFormatterFactory(null);
                jFTCpfCpnj.setValue(null);
            }
        }
    }//GEN-LAST:event_comboPersonalidadeItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    protected final javax.swing.JButton botaoSalvar = new javax.swing.JButton();
    private javax.swing.JComboBox comboBanco;
    private javax.swing.JComboBox comboPersonalidade;
    private javax.swing.JFormattedTextField jFTCpfCpnj;
    private org.jdesktop.swingx.JXDatePicker jFTDataProtocolo;
    private br.com.pempec.componentes.JDoubleField jFTValorCheque;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTAgencia;
    private javax.swing.JTextField jTConta;
    private javax.swing.JTextField jTNumeroCheque;
    private javax.swing.JTextField jTObservacao;
    private javax.swing.JTextField jTTitular;
    private javax.swing.JLabel labelAgencia;
    private javax.swing.JLabel labelAgencia1;
    private javax.swing.JLabel labelCpfCnpj;
    private javax.swing.JLabel labelDataEmissao;
    private javax.swing.JLabel labelNomeBanco;
    private javax.swing.JLabel labelPortador1;
    private javax.swing.JLabel labelTipoSacado1;
    private javax.swing.JLabel labelTitular2;
    private javax.swing.JLabel labelTitular3;
    private javax.swing.JLabel labelValorPagar;
    // End of variables declaration//GEN-END:variables

    private boolean validarCombos(String combo) {
        boolean valida = false;

        if (combo.equalsIgnoreCase("comboBanco")) {
            if (comboBanco.getSelectedItem() != null) {
                if (((BancoModel) comboBanco.getSelectedItem()).getId() != null) {
                    if (!((BancoModel) comboBanco.getSelectedItem()).getId().isEmpty()) {
                        valida = true;
                    }

                }
            }
        }


        return valida;

    }
} 
