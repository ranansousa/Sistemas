package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IPopUpContaContabil;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.iterators.HistoricoTextFilterator;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroHistorico extends FinanceInternalFrame implements IRepopulador, IPopUpContaContabil {

    private HistoricoBO historicoBO = new HistoricoBO();
    private long tela = Tela.TELA_PARAMETROS_HISTORICOS.getTela();

    public CadastroHistorico() throws SystemException {

        initComponents();
        
    
        //Escodendo os campos ID's
        campoCodigo.setVisible(false);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoCodigo = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        comboHistorico = new javax.swing.JComboBox();
        labelDescricao1 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox();
        labelDescricao2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTCodigo = new br.com.pempec.componentes.JIntegerField();
        jTContaContabil = new javax.swing.JTextField();
        lupaPesquisaContaContabil = new javax.swing.JButton();
        deletaContaContabil = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro Básico");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 102, 255))); // NOI18N

        campoCodigo.setEditable(false);

        labelDescricao.setText("Descrição");

        comboHistorico.setFont(getFont());
        comboHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboHistoricoActionPerformed(evt);
            }
        });

        labelDescricao1.setText("Conta Contabil");

        comboTipo.setFont(getFont());

        labelDescricao2.setText("Tipicidade");

        jLabel1.setText("Código");

        jTContaContabil.setEditable(false);

        lupaPesquisaContaContabil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.jpg"))); // NOI18N
        lupaPesquisaContaContabil.setBorderPainted(false);
        lupaPesquisaContaContabil.setContentAreaFilled(false);
        lupaPesquisaContaContabil.setDefaultCapable(false);
        lupaPesquisaContaContabil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lupaPesquisaContaContabilActionPerformed(evt);
            }
        });

        deletaContaContabil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        deletaContaContabil.setBorderPainted(false);
        deletaContaContabil.setContentAreaFilled(false);
        deletaContaContabil.setDefaultCapable(false);
        deletaContaContabil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletaContaContabilActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lupaPesquisaContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deletaContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(labelDescricao, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelDescricao2)
                                .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(14, 14, 14)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(comboHistorico, javax.swing.GroupLayout.Alignment.LEADING, 0, 340, Short.MAX_VALUE)
                        .addComponent(labelDescricao1, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(deletaContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDescricao)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(labelDescricao2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelDescricao1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lupaPesquisaContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(19, 19, 19))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoIncluir.setMnemonic('I');
        botaoIncluir.setText("Incluir");
        botaoIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirActionPerformed(evt);
            }
        });

        botaoAlterar.setMnemonic('A');
        botaoAlterar.setText("Alterar");
        botaoAlterar.setEnabled(false);
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
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

        botaoExcluir.setMnemonic('E');
        botaoExcluir.setText("Excluir");
        botaoExcluir.setEnabled(false);
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoExcluir, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Histórico";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos() {

        try {

            comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" -> Selecione <- ", "Geral", "Pagamentos", "Recebimentos", "Deduções", "Acréscimos", "Caixa"}));

            Collection<HistoricoModel> lColecao = historicoBO.obterTodos(organizacaoModel);

            final EventList<HistoricoModel> lRegistros = GlazedLists.eventList(lColecao);
            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboHistorico, lRegistros, new HistoricoTextFilterator());
            support.setFilterMode(TextMatcherEditor.STARTS_WITH);
            support.setStrict(false);

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
    private void comboHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboHistoricoActionPerformed

        String evento = evt.getActionCommand();
        int modifiers = evt.getModifiers();

        boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
        boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

        if (comboHistorico.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

            try {

                HistoricoModel tab = historicoBO.consultarPorPk((HistoricoModel) comboHistorico.getSelectedItem());

                if (tab != null && tab.getPk() != null) {

                    botaoAlterar.setEnabled(true);
                    botaoExcluir.setEnabled(true);
                    botaoIncluir.setEnabled(false);

                    campoCodigo.setText(tab.getPk().getId());
                    jTCodigo.setText(String.valueOf(tab.getCodigo()));

                    String tipo = tab.getTipo();

                    if (tipo.equals("G")) {
                        comboTipo.setSelectedIndex(1);
                    } else {
                        if (tipo.equals("P")) {
                            comboTipo.setSelectedIndex(2);
                        } else {
                            if (tipo.equals("R")) {
                                comboTipo.setSelectedIndex(3);
                            } else {
                                if (tipo.equals("D")) {
                                    comboTipo.setSelectedIndex(4);
                                } else {
                                    if (tipo.equals("A")) {
                                        comboTipo.setSelectedIndex(5);
                                    } else {
                                        if (tipo.equals("C")) {
                                            comboTipo.setSelectedIndex(6);
                                        } else {
                                            comboTipo.setSelectedIndex(0);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if (tab.getContaContabil() != null && tab.getContaContabil().getConta() != null && !tab.getContaContabil().getConta().isEmpty()) {

                        this.contaContabil = tab.getContaContabil();

                        jTContaContabil.setText(this.contaContabil.getConta());

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

}//GEN-LAST:event_comboHistoricoActionPerformed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        botaoAlterar.setEnabled(false);
        botaoIncluir.setEnabled(true);
        campoCodigo.setText("");
        comboHistorico.setSelectedItem(null);
        this.contaContabil = null;
        jTContaContabil.setText("");
        jTCodigo.setText("");
        comboTipo.setSelectedIndex(0);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        String valorCombo = null;

//        if (this.validaPreenchimentoCombo(comboHistorico, true)) {
//            valorCombo = comboHistorico.getSelectedItem().toString();
//        }
        if (comboHistorico.getSelectedItem() != null) {
            valorCombo = comboHistorico.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {

                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                HistoricoModel tab = new HistoricoModel();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                if (campoCodigo.getText().equals(Constantes.HISTORICO_TESOURARIA_DEPOSITO) || campoCodigo.getText().equals(Constantes.HISTORICO_TESOURARIA_ESPECIE_RECEBIDO) || campoCodigo.getText().equals(Constantes.HISTORICO_TESOURARIA_ESPECIE_PAGO) || campoCodigo.getText().equals(Constantes.HISTORICO_TESOURARIA_CHEQUE_RECEBIDO) || campoCodigo.getText().equals(Constantes.HISTORICO_TESOURARIA_TRANSFERE_BCO_TESOURARIA)) {
                    exibeMensagemAviso("Não é possível excluir este registro! \n Este histórico é padrão do sistema.", null);
                    return;
                }

                tab.setDescricao(valorCombo);

                tab.setMovimentoDiarioModel(registroMovimento("Deletar Historico", valorCombo, tab.getDescricao(), null, "Deletado"));

                historicoBO.excluir(tab);

                this.botaoLimparActionPerformed(evt);

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

            exibeMensagemAviso("Campo Descrição é obrigatório.", null);
        }

    }//GEN-LAST:event_botaoExcluirActionPerformed
    private Boolean validaCampos() {

//        if (!validaPreenchimentoCombo(comboHistorico, true)) {
//            comboHistorico.requestFocus();
//            return false;
//        }
        if (jTCodigo.getText().isEmpty()) {
            jTCodigo.requestFocus();
            return false;
        }

//        if (contaContabil == null) {
//            jTContaContabil.requestFocus();
//            return false;
//        }
        if (comboTipo.getSelectedItem() == null) {
            comboTipo.requestFocus();
            return false;
        }

        return true;

    }
    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        String valorCombo = null;

//        if (this.validaPreenchimentoCombo(comboHistorico, true)) {
//            valorCombo = comboHistorico.getSelectedItem().toString().toUpperCase();
//        }
        if (comboHistorico.getSelectedItem() != null) {
            valorCombo = comboHistorico.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {

                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                if (validaCampos()) {

                    HistoricoModel tab = new HistoricoModel();
                    tab.setPk(new PKModel());
                    tab.getPk().setOrganizacao(organizacaoModel);
                    tab.getPk().setId(PempecKeyGenerator.generateKey());

                    tab.setDescricao(valorCombo);
                    tab.setCodigo(PempecParse.stringToInteger(jTCodigo.getText()));

                    String tipo = "G";

                    switch (comboTipo.getSelectedIndex()) {

                        case 2:
                            tipo = "P";
                            break;
                        case 3:
                            tipo = "R";
                            break;
                        case 4:
                            tipo = "D";
                            break;
                        case 5:
                            tipo = "A";
                            break;
                        case 6:
                            tipo = "C";
                            break;
                        default:
                            tipo = "G";
                            break;

                    }

                    tab.setTipo(tipo);

                    tab.setContaContabil(this.contaContabil);

                    tab.setMovimentoDiarioModel(registroMovimento("Insere Historico", valorCombo, tab.getDescricao(), null, "Inserido"));

                    historicoBO.inserir(tab);

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
        } else {
            exibeMensagemAviso("Campo Descrição é obrigatório.", null);
        }
    }//GEN-LAST:event_botaoIncluirActionPerformed

private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

    String valorCombo = null;

//    if (this.validaPreenchimentoCombo(comboHistorico, true)) {
//        valorCombo = comboHistorico.getSelectedItem().toString().toUpperCase();
//    }
    if (comboHistorico.getSelectedItem() != null) {
        valorCombo = comboHistorico.getSelectedItem().toString().toUpperCase();
    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            if (validaCampos()) {

                HistoricoModel tab = new HistoricoModel();
                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(organizacaoModel);
                tab.getPk().setId(campoCodigo.getText());

                tab.setDescricao(valorCombo);
                tab.setCodigo(PempecParse.stringToInteger(jTCodigo.getText()));

                String tipo = "G";

                switch (comboTipo.getSelectedIndex()) {

                    case 2:
                        tipo = "P";
                        break;
                    case 3:
                        tipo = "R";
                        break;
                    case 4:
                        tipo = "D";
                        break;
                    case 5:
                        tipo = "A";
                        break;
                    case 6:
                        tipo = "C";
                        break;
                    default:
                        tipo = "G";
                        break;

                }

                tab.setTipo(tipo);

                tab.setContaContabil(this.contaContabil);

                tab.setMovimentoDiarioModel(registroMovimento("Alterar Historico", valorCombo, tab.getDescricao(), null, "Alterado"));

                historicoBO.alterar(tab);

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

    } else {
        exibeMensagemAviso("Campo Descrição é obrigatório.", null);
    }

}//GEN-LAST:event_botaoAlterarActionPerformed

private void lupaPesquisaContaContabilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lupaPesquisaContaContabilActionPerformed

    try {

        if (pesquisaContaContabil == null || pesquisaContaContabil.isClosed()) {
            pesquisaContaContabil = new PesquisaContaContabil(this, null, this.getLocation());
            TelaPrincipal.desktopPane.add(pesquisaContaContabil);
            pesquisaContaContabil.show();
        } else {
            pesquisaContaContabil.setVisible(true);
            pesquisaContaContabil.moveToFront();
            pesquisaContaContabil.requestFocus();
        }

    } catch (final SystemException ex) {

        final File file = PrintScreen.capture();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                tratamentoExcecao(ex, file);

            }
        });

    }


}//GEN-LAST:event_lupaPesquisaContaContabilActionPerformed

private void deletaContaContabilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletaContaContabilActionPerformed

    try {

        if (jTContaContabil != null && !jTContaContabil.getText().isEmpty()) {

            if (exibeMensagemConfirmacao("Você tem certeza que deseja remover o vinculo?", "Remove Vinculo")) {

                jTContaContabil.setText("");
                contaContabil = null;

                this.botaoAlterar.doClick();
            }

        } else {

            exibeMensagemAviso("Não existe registro vinculado", "Conta Contabil");
        }

    } catch (Exception ex) {

        ex.printStackTrace();

    }
}//GEN-LAST:event_deletaContaContabilActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoIncluir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JComboBox comboHistorico;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JButton deletaContaContabil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private br.com.pempec.componentes.JIntegerField jTCodigo;
    private javax.swing.JTextField jTContaContabil;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelDescricao1;
    private javax.swing.JLabel labelDescricao2;
    private javax.swing.JButton lupaPesquisaContaContabil;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private PesquisaContaContabil pesquisaContaContabil;
    private ContaContabilModel contaContabil;

    public void setPesquisaContaContabil(ContaContabilModel contaContabil, String tipo) {
        this.contaContabil = contaContabil;
        jTContaContabil.setText(contaContabil.getConta());
    }

    

}
