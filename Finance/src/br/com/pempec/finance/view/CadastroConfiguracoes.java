package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ServidorEmailBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ServidorEmailModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import java.io.File;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroConfiguracoes extends FinanceInternalFrame implements IRepopulador {

    private ServidorEmailBO servidorEmailBO = new ServidorEmailBO();
    private long tela = Tela.TELA_SISTEMA_CONFIGURACOES.getTela();

    private String NameObject() {
        return (String) "Configurações";
    }

    public void repopularCombos() {

        try {

            ServidorEmailModel model = servidorEmailBO.consultar();

            if (model != null) {

                campoCodigo.setText(model.getId().toString());
                jTHost.setText(model.getHost());
                jTRemetente.setText(model.getRemetente());
                if (model.getRequerAutenticacaoBoolean()) {
                    jChAuth.setSelected(true);
                    jTAuthLogin.setEditable(true);
                    jTAuthSenha.setEditable(true);
                    jTAuthLogin.setText(model.getLogin());
                    jTAuthSenha.setText(model.getSenha());
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

    public CadastroConfiguracoes() throws SystemException {

        initComponents();

        campoCodigo.setVisible(false);

        campoCodigo.setText("1010");

        jChAuth.setSelected(false);
        jChAuthActionPerformed(null);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        labelCedente = new javax.swing.JLabel();
        labelCedente1 = new javax.swing.JLabel();
        jTHost = new javax.swing.JFormattedTextField();
        labelCNPJ1 = new javax.swing.JLabel();
        labelInscM = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoSalvar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jChAuth = new javax.swing.JCheckBox();
        jTAuthLogin = new javax.swing.JFormattedTextField();
        jTAuthSenha = new javax.swing.JPasswordField();
        campoCodigo = new javax.swing.JTextField();
        labelCedente2 = new javax.swing.JLabel();
        jTRemetente = new javax.swing.JFormattedTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Configurações");
        setPreferredSize(new java.awt.Dimension(510, 440));

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 102, 51))));

        labelCedente.setText("Configurar Servidor E-mail SMTP:");

        labelCedente1.setText("Host:");

        labelCNPJ1.setText("Login:");

        labelInscM.setText("Senha:");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 65));

        botaoSalvar.setMnemonic('I');
        botaoSalvar.setText("Salvar");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoSalvar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jChAuth.setText("Requer Autenticação?");
        jChAuth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChAuthActionPerformed(evt);
            }
        });

        campoCodigo.setEditable(false);

        labelCedente2.setText("Remetente:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCedente)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelInscM)
                            .addComponent(labelCNPJ1))
                        .addGap(63, 63, 63)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jTHost, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jChAuth, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTRemetente, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                            .addComponent(jTAuthLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addComponent(jTAuthSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelCedente2)
                    .addComponent(labelCedente1))
                .addGap(178, 178, 178))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelCedente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCedente1)
                    .addComponent(jTHost, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCedente2)
                    .addComponent(jTRemetente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jChAuth)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCNPJ1)
                    .addComponent(jTAuthLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelInscM)
                    .addComponent(jTAuthSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        jTabbedPane1.addTab("Servidor E-mail", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            if (validaCampos()) {

                ServidorEmailModel tab = new ServidorEmailModel();

                tab.setId(PempecParse.stringToLong(campoCodigo.getText()));
                tab.setHost(jTHost.getText());
                tab.setRemetente(jTRemetente.getText());

                if (jChAuth.isSelected()) {

                    tab.setRequerAutenticacao(1);
                    tab.setLogin(jTAuthLogin.getText());
                    tab.setSenha(String.valueOf(jTAuthSenha.getPassword()));

                } else {

                    tab.setRequerAutenticacao(0);

                }

                tab.setMovimentoDiario(this.registroMovimento("Conf. Servidor E-mail", jTHost.getText(), "Configuração Atualizada do Servidor de E-mail", null, "Atualizado"));

                servidorEmailBO.salvar(tab);

                Controller.setServidorEmail(tab);

                exibeMensagemAviso("Salvo com sucesso!", null);

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
    }//GEN-LAST:event_botaoSalvarActionPerformed

    private void jChAuthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChAuthActionPerformed

        if (jChAuth.isSelected()) {

            jTAuthLogin.setEditable(true);
            jTAuthSenha.setEditable(true);

        } else {

            jTAuthLogin.setText("");
            jTAuthSenha.setText("");
            jTAuthLogin.setEditable(false);
            jTAuthSenha.setEditable(false);

        }


}//GEN-LAST:event_jChAuthActionPerformed

    private Boolean validaCampos() {

        if (jTHost.getText() == null || jTHost.getText().isEmpty()) {
            jTHost.requestFocus();
            return false;
        }

        if (jTRemetente.getText() == null || jTRemetente.getText().isEmpty()) {
            jTRemetente.requestFocus();
            return false;
        }

        if (!PempecUtil.validaPreenchimentoEmail(jTRemetente.getText())) {
            exibeMensagemAviso("E-mail Remetente inválido!", null);
            jTRemetente.requestFocus();
            return false;
        }

        if (jChAuth.isSelected()) {

            if (jTAuthLogin.getText() == null || jTAuthLogin.getText().isEmpty()) {
                jTAuthLogin.requestFocus();
                return false;
            }

            if (jTAuthSenha.getPassword() == null || String.valueOf(jTAuthSenha.getPassword()).isEmpty()) {
                jTAuthSenha.requestFocus();
                return false;
            }

        }

        return true;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JCheckBox jChAuth;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JFormattedTextField jTAuthLogin;
    private javax.swing.JPasswordField jTAuthSenha;
    private javax.swing.JFormattedTextField jTHost;
    private javax.swing.JFormattedTextField jTRemetente;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCNPJ1;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelCedente1;
    private javax.swing.JLabel labelCedente2;
    private javax.swing.JLabel labelInscM;
    // End of variables declaration//GEN-END:variables
}
