/*
 * CadastroCurso.java
 *
 * Created on 8 de Outubro de 2007, 21:58
 */
package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.UsuarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.MD5;
import br.com.pempec.finance.utils.UIManagerPut;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author PEMPEC
 */
public class TelaAlterarSenha extends javax.swing.JFrame {

    private static UsuarioBO usuarioBO = new UsuarioBO();

    public TelaAlterarSenha() {

        try {

            //UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 

            NimRODTheme nt = new NimRODTheme();
            nt.setPrimary1(new Color(103, 204, 255));
            nt.setPrimary2(new Color(103, 204, 255));
            nt.setPrimary3(new Color(103, 204, 255));
            nt.setSecondary1(new Color(240, 240, 240));
            nt.setSecondary2(new Color(241, 241, 241));
            nt.setSecondary3(new Color(242, 242, 242));
            nt.setFrameOpacity(180);
            nt.setMenuOpacity(195);
            nt.setBlack(new Color(0, 0, 0));
            nt.setWhite(new Color(255, 255, 255));


            NimRODLookAndFeel nimRODLF = new NimRODLookAndFeel();
            nimRODLF.setCurrentTheme(nt);
            UIManager.setLookAndFeel(nimRODLF);

            initComponents();

            UIManagerPut.modifyUIManagerPut();

            setResizable(false);

            setLocationRelativeTo(null);

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Ocorreu um erro Inesperado! Favor informar adiministrador. >> ");

        }

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelLogin = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        jTLogin = new javax.swing.JTextField();
        jTSenha = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTNovaSenha = new javax.swing.JPasswordField();
        jTConfNovaSenha = new javax.swing.JPasswordField();
        jPanel2 = new javax.swing.JPanel();
        botaoAlterar = new javax.swing.JButton();
        botaoSair = new javax.swing.JButton();

        setTitle("PEMPEC ENTERPRISE - Finance - Acesso Restrito");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Alterar Senha"));

        labelLogin.setText("Login:");

        labelSenha.setText("Senha:");

        jLabel1.setText("Nova Senha:");

        jLabel2.setText("Conf. Nova Senha:");

        jTConfNovaSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTConfNovaSenhaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelLogin)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(labelSenha))
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTConfNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
                        .addContainerGap(23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSenha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jTNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTConfNovaSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        botaoAlterar.setMnemonic('I');
        botaoAlterar.setText("Alterar");
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
            }
        });

        botaoSair.setMnemonic('L');
        botaoSair.setText("Sair");
        botaoSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoSair});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSair, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed

        this.dispose();

}//GEN-LAST:event_botaoSairActionPerformed

    /**
     * Valida campos obrigatorios
     *
     * @return
     */
    private Boolean validaCampos() {

        if (jTLogin.getText().isEmpty()) {
            jTLogin.requestFocus();
            return false;
        }

        if (new String(jTSenha.getPassword()).isEmpty()) {
            jTSenha.requestFocus();
            return false;
        }

        if (new String(jTNovaSenha.getPassword()).isEmpty()) {
            jTNovaSenha.requestFocus();
            return false;
        }

        if (new String(jTConfNovaSenha.getPassword()).isEmpty()) {
            jTConfNovaSenha.requestFocus();
            return false;
        }

        if (!new String(jTNovaSenha.getPassword()).equalsIgnoreCase(new String(jTConfNovaSenha.getPassword()))) {
            jTNovaSenha.requestFocus();
            JOptionPane.showMessageDialog(null, "As senhas devem ser iguais!");
        }

        return true;
    }

    private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

        if (validaCampos()) {

            try {

                UsuarioModel usuarioModel = new UsuarioModel();

                usuarioModel.setLogin(jTLogin.getText().toLowerCase());

                UsuarioModel aux = usuarioBO.consultarPorLogin(usuarioModel);

                if (aux == null) {
                    JOptionPane.showMessageDialog(null, "Login Inválido!");
                    return;
                }

                aux.setSenha(MD5.criptografar(new String(jTNovaSenha.getPassword())));

                if (aux.getSenha().equalsIgnoreCase(Constantes.SENHA_PADRAO_CRIPTOGRAFADA)) {
                    JOptionPane.showMessageDialog(null, "Senha Padrão do Sistema! Favor escolher outra!");
                    return;
                }

                usuarioBO.resetarSenha(aux);

                JOptionPane.showMessageDialog(null, "Alteração efetuada com sucesso!");

                this.dispose();

            } catch (ApplicationException ex) {

                JOptionPane.showMessageDialog(null, "Erro -> " + ex.getLocalizedMessage());

            } catch (SystemException ex) {

                JOptionPane.showMessageDialog(null, "Erro -> " + ex.getLocalizedMessage());

            }

        } else {

            JOptionPane.showMessageDialog(null, "Campo(s) Obrigatório(s)");

        }


}//GEN-LAST:event_botaoAlterarActionPerformed

    private void jTConfNovaSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTConfNovaSenhaKeyPressed

        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
            botaoAlterar.doClick();
        }

    }//GEN-LAST:event_jTConfNovaSenhaKeyPressed

    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaAlterarSenha().setVisible(true);
            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoSair;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jTConfNovaSenha;
    protected javax.swing.JTextField jTLogin;
    protected javax.swing.JPasswordField jTNovaSenha;
    protected javax.swing.JPasswordField jTSenha;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelSenha;
    // End of variables declaration//GEN-END:variables
}
