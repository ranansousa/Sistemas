package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaContabilBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.ContaContabilTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroContaContabil extends FinanceInternalFrame implements IRepopulador {

    private ContaContabilBO contaContabilBO = new ContaContabilBO();
    private long tela = Tela.TELA_PARAMETROS_CONTA_CONTABIL.getTela();

    public CadastroContaContabil() throws SystemException {

        initComponents();

        //Escodendo os campos ID's
        campoCodigo.setVisible(false);
        // campoGrau.setVisible(false);
        campoINSCMF.setVisible(false);
        campoNatureza.setVisible(false);
        campoOrdemDipj.setVisible(false);
        campoRelacionamento.setVisible(false);
        campoGrau.setVisible(false);

        jTConta.setDocument(new Documento(14));
        jTDigitoConta.setDocument(new Documento(1));
        jTContaReduzida.setDocument(new Documento(8));
        jTDigitoContaReduzida.setDocument(new Documento(1));

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoCodigo = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        comboContaContabil = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jTContaReduzida = new br.com.pempec.componentes.JIntegerField();
        jLabel2 = new javax.swing.JLabel();
        jTDigitoContaReduzida = new br.com.pempec.componentes.JIntegerField();
        jLabel3 = new javax.swing.JLabel();
        jTConta = new javax.swing.JFormattedTextField();
        jTDigitoConta = new br.com.pempec.componentes.JIntegerField();
        jLabel4 = new javax.swing.JLabel();
        comboTipo = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        campoINSCMF = new javax.swing.JTextField();
        campoGrau = new javax.swing.JTextField();
        campoOrdemDipj = new javax.swing.JTextField();
        campoRelacionamento = new javax.swing.JTextField();
        campoNatureza = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro Básico");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 153)), NameObject()));

        campoCodigo.setEditable(false);

        labelDescricao.setText("Descrição:");

        comboContaContabil.setFont(getFont());
        comboContaContabil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboContaContabilActionPerformed(evt);
            }
        });

        jLabel1.setText("Conta:");

        jLabel2.setText("Conta Reduzida:");

        jLabel3.setText("Digito:");

        jLabel4.setText("Digito:");

        comboTipo.setFont(new java.awt.Font("Arial", 0, 10));

        jLabel5.setText("Tipo:");

        campoINSCMF.setEditable(false);

        campoGrau.setEditable(false);

        campoOrdemDipj.setEditable(false);

        campoRelacionamento.setEditable(false);

        campoNatureza.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelDescricao, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboContaContabil, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTConta, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addGap(52, 52, 52)
                                        .addComponent(jLabel2)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTDigitoConta, 0, 0, Short.MAX_VALUE)
                                        .addGap(51, 51, 51)
                                        .addComponent(jTContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(11, 11, 11)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTDigitoContaReduzida, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(377, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoINSCMF, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoGrau, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoOrdemDipj, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoRelacionamento, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoNatureza, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(99, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(labelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboContaContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTDigitoContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTContaReduzida, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTDigitoConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoINSCMF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoGrau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoOrdemDipj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoRelacionamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoNatureza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap()
                .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoExcluir, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Conta Contábil";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos() {

        try {

            comboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" -> Selecione <- ",
                "Débito", "Crédito"
            }));

            Collection<ContaContabilModel> lColecao = contaContabilBO.obterTodos(organizacaoModel);

            final EventList<ContaContabilModel> lRegistros = GlazedLists.eventList(lColecao);
            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboContaContabil, lRegistros, new ContaContabilTextFilterator());
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

    private void comboContaContabilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboContaContabilActionPerformed

        String evento = evt.getActionCommand();
        int modifiers = evt.getModifiers();

        boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
        boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

        if (comboContaContabil.getSelectedItem() != null && ((comboContaContabil.getSelectedItem() instanceof ContaContabilModel)) && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

            try {

                ContaContabilModel tab = contaContabilBO.consultarPorPk((ContaContabilModel) comboContaContabil.getSelectedItem());

                if (tab != null && tab.getPk() != null) {

                    botaoAlterar.setEnabled(true);
                    botaoExcluir.setEnabled(true);
                    botaoIncluir.setEnabled(false);

                    campoCodigo.setText(tab.getPk().getId());

                    if (tab.getGrau() != null) {

                        campoGrau.setText(tab.getGrau());

                    } else {

                        campoGrau.setText("5");

                    }

                    if (tab.getInscricaoCMF() != null) {
                        campoINSCMF.setText(tab.getInscricaoCMF().toString());
                    }

                    if (tab.getNatureza() != null) {
                        campoNatureza.setText(tab.getNatureza().toString());
                    }

                    if (tab.getOrdemDIPJ() != null) {
                        campoOrdemDipj.setText(tab.getOrdemDIPJ().toString());
                    }
                    if (tab.getRelacionamento() != null) {
                        campoRelacionamento.setText(tab.getRelacionamento().toString());
                    }

                    jTConta.setText(tab.getConta());

                    jTDigitoConta.setValue(PempecParse.stringToInteger(tab.getDigitoConta()));

                    jTContaReduzida.setValue(PempecParse.stringToInteger(tab.getContaReduzida()));

                    jTDigitoContaReduzida.setValue(PempecParse.stringToInteger(tab.getDigitoContaReduzida()));

                    if (tab.getTipo() != null) {

                        if (tab.getTipo().equals("D")) {
                            comboTipo.setSelectedIndex(1);
                        } else {
                            if (tab.getTipo().equals("C")) {
                                comboTipo.setSelectedIndex(2);
                            } else {
                                comboTipo.setSelectedIndex(0);
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

}//GEN-LAST:event_comboContaContabilActionPerformed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        botaoAlterar.setEnabled(false);
        botaoIncluir.setEnabled(true);
        campoCodigo.setText("");
        campoGrau.setText("5");
        campoINSCMF.setText("");
        campoNatureza.setText("");
        campoOrdemDipj.setText("");
        campoRelacionamento.setText("");
        jTConta.setText("");
        jTContaReduzida.setText("");
        jTDigitoConta.setText("");
        jTDigitoContaReduzida.setText("");
        comboContaContabil.setSelectedItem(null);
        comboTipo.setSelectedIndex(0);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        String valorCombo = null;

        if (comboContaContabil.getSelectedItem() != null) {
            valorCombo = comboContaContabil.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                ContaContabilModel tab = new ContaContabilModel();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab.setDescricao(valorCombo);

                tab.setMovimentoDiarioModel(registroMovimento("Deletar Conta Contabil", valorCombo, tab.getPk().getId(), null, "Deletado"));

                contaContabilBO.excluir(tab);

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

//        if (!this.validaPreenchimentoCombo(comboContaContabil, true)) {
//            comboContaContabil.requestFocus();
//            return false;
//        }

        if (jTConta.getText().isEmpty()) {
            jTConta.requestFocus();
            return false;
        }

        if (jTDigitoConta.getText().isEmpty()) {
            jTDigitoConta.requestFocus();
            return false;
        }

        if (jTContaReduzida.getText().isEmpty()) {
            jTContaReduzida.requestFocus();
            return false;
        }

        if (jTDigitoContaReduzida.getText().isEmpty()) {
            jTDigitoContaReduzida.requestFocus();
            return false;
        }


        if (comboTipo.getSelectedItem() == null) {
            comboTipo.requestFocus();
            return false;
        }

        return true;

    }
    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        String valorCombo = null;

        if (comboContaContabil.getSelectedItem() != null) {
            valorCombo = comboContaContabil.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {

                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                if (validaCampos()) {

                    ContaContabilModel tab = new ContaContabilModel();
                    tab.setPk(new PKModel());
                    tab.getPk().setOrganizacao(organizacaoModel);
                    tab.getPk().setId(PempecKeyGenerator.generateKey());

                    tab.setDescricao(valorCombo);
                    tab.setConta(jTConta.getText());
                    tab.setContaReduzida(PempecUtil.somenteNumero(jTContaReduzida.getText()));
                    tab.setDigitoConta(jTDigitoConta.getText());
                    tab.setDigitoContaReduzida(jTDigitoContaReduzida.getText());
                    tab.setTipo(comboTipo.getSelectedIndex() == 1 ? "D" : "C");

                    if (campoGrau.getText() != null) {

                        tab.setGrau(campoGrau.getText());

                    } else {

                        tab.setGrau("5");

                    }
                    tab.setInscricaoCMF("0");
                    tab.setRelacionamento(0);
                    tab.setOrdemDIPJ(0);
                    tab.setNatureza(PempecParse.stringToInteger(jTConta.getText().substring(0, 1)));

                    tab.setMovimentoDiarioModel(registroMovimento("Inserir Conta Contabil", valorCombo, tab.getDescricao(), null, "Inserido"));

                    contaContabilBO.inserir(tab);

                    this.botaoLimparActionPerformed(evt);

                } else {


                    exibeMensagemAviso(Controller.getMsgCamposObrigatorios(), null);

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

    if (comboContaContabil.getSelectedItem() != null) {
        valorCombo = comboContaContabil.getSelectedItem().toString().toUpperCase();
    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCampos()) {

                ContaContabilModel tab = new ContaContabilModel();
                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(organizacaoModel);
                tab.getPk().setId(campoCodigo.getText());

                tab.setDescricao(valorCombo);

                tab.setConta(jTConta.getText());

                tab.setContaReduzida(PempecUtil.somenteNumero(jTContaReduzida.getText()));

                tab.setDigitoConta(jTDigitoConta.getText());

                tab.setDigitoContaReduzida(jTDigitoContaReduzida.getText());

                tab.setTipo(comboTipo.getSelectedIndex() == 1 ? "D" : "C");

                tab.setGrau(campoGrau.getText());

                tab.setInscricaoCMF(campoINSCMF.getText());

                if (campoNatureza.getText() != null && !campoNatureza.getText().isEmpty()) {

                    tab.setNatureza(PempecParse.stringToInteger(campoNatureza.getText()));

                } else {

                    tab.setNatureza(PempecParse.stringToInteger(tab.getConta().substring(0, 1)));
                }

                tab.setRelacionamento(PempecParse.stringToInteger(campoRelacionamento.getText()));

                tab.setOrdemDIPJ(PempecParse.stringToInteger(campoOrdemDipj.getText()));

                tab.setMovimentoDiarioModel(registroMovimento("Alterar Conta Contabil", valorCombo, tab.getDescricao(), null, "Alterado"));

                contaContabilBO.alterar(tab);

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoIncluir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoGrau;
    private javax.swing.JTextField campoINSCMF;
    private javax.swing.JTextField campoNatureza;
    private javax.swing.JTextField campoOrdemDipj;
    private javax.swing.JTextField campoRelacionamento;
    private javax.swing.JComboBox comboContaContabil;
    private javax.swing.JComboBox comboTipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFormattedTextField jTConta;
    private br.com.pempec.componentes.JIntegerField jTContaReduzida;
    private br.com.pempec.componentes.JIntegerField jTDigitoConta;
    private br.com.pempec.componentes.JIntegerField jTDigitoContaReduzida;
    private javax.swing.JLabel labelDescricao;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
}
