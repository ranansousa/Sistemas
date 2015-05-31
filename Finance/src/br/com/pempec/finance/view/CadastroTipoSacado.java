package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.TipoSacadoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoSacadoModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.TipoSacadoTextFilterator;
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
public class CadastroTipoSacado extends FinanceInternalFrame implements IRepopulador {

    private TipoSacadoBO tipoSacadoBO = new TipoSacadoBO();
    private long tela = Tela.TELA_PARAMETROS_TIPOS_DE_SACADOS.getTela();

    private String NameObject() {
        return (String) "Tipo Sacado";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos() {

        try {

            OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

            Collection<TipoSacadoModel> lColecao = tipoSacadoBO.obterTodos(organizacaoModel);

            final EventList<TipoSacadoModel> lRegistros = GlazedLists.eventList(lColecao);
            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboTipoSacado, lRegistros, new TipoSacadoTextFilterator());
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

    public CadastroTipoSacado() throws SystemException {

        initComponents();

        //Escodendo os campos ID's
        campoCodigo.setVisible(false);


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoCodigo = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        comboTipoSacado = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro Básico");
        setPreferredSize(new java.awt.Dimension(480, 340));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        campoCodigo.setEditable(false);

        labelDescricao.setText("Descrição:");

        comboTipoSacado.setFont(getFont());
        comboTipoSacado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTipoSacadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboTipoSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelDescricao))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(labelDescricao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipoSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(54, Short.MAX_VALUE))
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
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoExcluir, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(43, 43, 43))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboTipoSacadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTipoSacadoActionPerformed

        String evento = evt.getActionCommand();
        int modifiers = evt.getModifiers();

        boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
        boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

        if (comboTipoSacado.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {


            try {

                TipoSacadoModel tab = new TipoSacadoModel();
                tab.setDescricao(comboTipoSacado.getSelectedItem().toString());
                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(Controller.getOrganizacao());
                tab = tipoSacadoBO.consultarPorTemplate(tab);

                if (tab != null && tab.getPk() != null) {

                    botaoAlterar.setEnabled(true);
                    botaoExcluir.setEnabled(true);
                    botaoIncluir.setEnabled(false);

                    campoCodigo.setText(tab.getPk().getId());


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

}//GEN-LAST:event_comboTipoSacadoActionPerformed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        botaoAlterar.setEnabled(false);
        botaoIncluir.setEnabled(true);
        campoCodigo.setText("");
        comboTipoSacado.setSelectedItem(null);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        String valorCombo = null;

        if (comboTipoSacado.getSelectedItem() != null) {
            valorCombo = comboTipoSacado.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                TipoSacadoModel tab = new TipoSacadoModel();

                tab.setPk(new PKModel());

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab.setDescricao(valorCombo);


                tab.setMovimentoDiarioModel(registroMovimento("Deletar Tipo Sacado", valorCombo, valorCombo + " ->" + tab.getPk().getId(), null, "Deletado"));

                tipoSacadoBO.excluir(tab);

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

        //Levantar com Ranan os obrigatórios.

        if (comboTipoSacado.getSelectedItem() == null) {
            comboTipoSacado.requestFocus();
            return false;
        }

        return true;
    }
    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        String valorCombo = null;

        if (comboTipoSacado.getSelectedItem() != null) {
            valorCombo = comboTipoSacado.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {

                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (validaCampos()) {

                    TipoSacadoModel tab = new TipoSacadoModel();
                    tab.setPk(new PKModel());
                    OrganizacaoModel organizacaoModel = Controller.getOrganizacao();
                    tab.getPk().setOrganizacao(organizacaoModel);
                    tab.getPk().setId(PempecKeyGenerator.generateKey());

                    tab.setDescricao(valorCombo);

                    tab.setMovimentoDiarioModel(registroMovimento("Inserir Tipo Sacado", valorCombo, tab.getDescricao(), null, "Inserido"));
                    tipoSacadoBO.inserir(tab);


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

    if (comboTipoSacado.getSelectedItem() != null) {
        valorCombo = comboTipoSacado.getSelectedItem().toString().toUpperCase();
    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCampos()) {
                TipoSacadoModel tab = new TipoSacadoModel();
                tab.setPk(new PKModel());
                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();
                tab.getPk().setOrganizacao(organizacaoModel);
                tab.getPk().setId(campoCodigo.getText());


                tab.setDescricao(valorCombo);

                tab.setMovimentoDiarioModel(registroMovimento("Alterar Tipo Sacado", valorCombo, tab.getDescricao(), null, "Alterado"));

                tipoSacadoBO.alterar(tab);


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
    private javax.swing.JComboBox comboTipoSacado;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelDescricao;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
}
