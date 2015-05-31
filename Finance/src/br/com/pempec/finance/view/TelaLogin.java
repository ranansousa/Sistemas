/*
 * CadastroCurso.java
 *
 * Created on 8 de Outubro de 2007, 21:58
 */
package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.OrganizacaoBO;
import br.com.pempec.finance.businessObjects.UsuarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.ResourcePropertiesLocator;
import br.com.pempec.finance.utils.UIManagerPut;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Collection;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author PEMPEC
 */
public class TelaLogin extends javax.swing.JFrame {

    private static UsuarioBO usuarioBO = new UsuarioBO();
    private static OrganizacaoBO organizacaoBO = new OrganizacaoBO();

    private String getAbout() {

        String version = ResourcePropertiesLocator.getMessageAbout("labelVersion");

        try {

            version = "PEMPEC ENTERPRISE - Finance  " + version + "   # " + InetAddress.getLocalHost().getHostName().toUpperCase();

        } catch (UnknownHostException ex) {

            ex.printStackTrace();
        }

        return version;

    }

    public TelaLogin() {
        
       
        try {
            
            
            

            // coloca uma figura na barra de título da janela
            URL url = this.getClass().getResource("/images/financeIcon.gif");
            Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
            this.setIconImage(imagemTitulo);

            //UIManager.setLookAndFeel ("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
            NimRODTheme nt = new NimRODTheme();

            //  nt.setPrimary1(new Color(103, 204, 255));
            nt.setPrimary1(new Color(193, 255, 193));

            nt.setPrimary2(new Color(193, 255, 193));
            nt.setPrimary3(new Color(193, 255, 193));

            nt.setSecondary1(new Color(193, 193, 193)); //era 240
            nt.setSecondary2(new Color(193, 193, 193));//era 241
            nt.setSecondary3(new Color(193, 193, 193));//era 242

            nt.setFrameOpacity(155);//era 180
            nt.setMenuOpacity(155);//era 195
            nt.setBlack(new Color(0, 0, 0));
            nt.setWhite(new Color(255, 255, 255));

            NimRODLookAndFeel nimRODLF = new NimRODLookAndFeel();

            nimRODLF.setCurrentTheme(nt);

            UIManager.setLookAndFeel(nimRODLF);

            initComponents();

            UIManagerPut.modifyUIManagerPut();

            setLocationRelativeTo(null);

            setResizable(false);
           
            
            
            
            
            

        } catch (Exception ex) {

            new FinanceInternalFrame().exibeMensagemAviso("Ocorreu um erro Inesperado! Favor informar adiministrador. >> ", "Erro");
            //JOptionPane.showMessageDialog(null, "Ocorreu um erro Inesperado! Favor informar adiministrador. >> ");

        }

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelLogin = new javax.swing.JLabel();
        labelSenha = new javax.swing.JLabel();
        jTLogin = new javax.swing.JTextField();
        jTSenha = new javax.swing.JPasswordField();
        labelAlterarSenha = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoLogar = new javax.swing.JButton();
        botaoSair = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(getAbout());
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 102, 255), new java.awt.Color(51, 102, 255), new java.awt.Color(51, 102, 255), new java.awt.Color(51, 102, 255)), "Acesso restrito"));

        labelLogin.setText("Login:");

        labelSenha.setText("Senha:");

        jTSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTSenhaKeyPressed(evt);
            }
        });

        labelAlterarSenha.setBackground(new java.awt.Color(0, 0, 0));
        labelAlterarSenha.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        labelAlterarSenha.setForeground(new java.awt.Color(255, 0, 0));
        labelAlterarSenha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAlterarSenha.setText("Alterar Senha");
        labelAlterarSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAlterarSenhaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelLogin)
                            .addComponent(labelSenha))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTLogin)
                            .addComponent(jTSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelAlterarSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSenha)
                    .addComponent(jTSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelAlterarSenha)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoLogar.setMnemonic('I');
        botaoLogar.setText("Logar");
        botaoLogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLogarActionPerformed(evt);
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
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoLogar, botaoSair});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoLogar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoSair, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSairActionPerformed
        System.exit(0);
}//GEN-LAST:event_botaoSairActionPerformed

    private Boolean validaCampos() {

        if (jTLogin.getText().isEmpty()) {
            jTLogin.requestFocus();
            return false;
        }

        if (new String(jTSenha.getPassword()).isEmpty()) {
            jTSenha.requestFocus();
            return false;
        }

        return true;
    }

    private void botaoLogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLogarActionPerformed

        if (validaCampos()) {

            try {

                UsuarioModel usuarioModel = new UsuarioModel();

                usuarioModel.setLogin(jTLogin.getText().toLowerCase());
                usuarioModel.setSenha(new String(jTSenha.getPassword()).toLowerCase());

                usuarioModel = usuarioBO.validarUsuario(usuarioModel);

                if (usuarioModel == null) {

                    JOptionPane.showMessageDialog(null, "Login não registrado ou Senha inválida.");
                    jTSenha.setText("");
                    return;

                }

                //Verificando se o usuário está ativo
                if (usuarioModel.getAtivo() == 0) {

                    JOptionPane.showMessageDialog(null, "Usuário INATIVO!");
                    jTSenha.setText("");
                    return;

                }

                //Verifica senha padrão
                if (usuarioModel.getSenha().equalsIgnoreCase(Constantes.SENHA_PADRAO_CRIPTOGRAFADA)) {

                    JOptionPane.showMessageDialog(null, "SENHA Padrão! Favor alterá-la");

                    //Direcionando para tela de alterar senha
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {

                            TelaAlterarSenha telaAlterarSenha = new TelaAlterarSenha();
                            telaAlterarSenha.jTLogin.setText(jTLogin.getText());
                            telaAlterarSenha.jTSenha.setText(Constantes.SENHA_PADRAO);
                            telaAlterarSenha.jTNovaSenha.requestFocus();
                            telaAlterarSenha.setVisible(true);

                        }
                    });
                    jTSenha.setText("");
                    return;

                }

                Controller.setUsuarioLogado(usuarioModel);
                //mudancas em 19/05/2012

                Controller.setDataServidorBD(usuarioBO.obterDataServidorBD());

                //Verificando se o usuario possui organização associada.
                if (usuarioModel.getOrganizacao() != null && usuarioModel.getOrganizacao().getId() != null && !usuarioModel.getOrganizacao().getId().isEmpty()) {

                    //Seta a organização que o usuario poderá utilizar
                    OrganizacaoModel organizacaoModel = organizacaoBO.consultarPorTemplate(usuarioModel.getOrganizacao());

                    Controller.setOrganizacao(organizacaoModel);

                    //  Controller.checarLicenca();
                } else {

                    String estacao = "LocalHost";

                    try {

                        estacao = InetAddress.getLocalHost().getHostName().toString().toUpperCase();

                    } catch (UnknownHostException ex) {

                        ex.printStackTrace();

                    }

                    //Aplicando as opções para o usuario escolher a organização.
                    Collection<OrganizacaoModel> organizacoes = organizacaoBO.obterTodos();

                    OrganizacaoModel organizacao = null;

                    while (organizacao == null) {

                        organizacao = (OrganizacaoModel) JOptionPane.showInputDialog(null, "           Escolha a Organização      ", " Seleção            " + "      " + estacao, JOptionPane.INFORMATION_MESSAGE,
                                null, organizacoes.toArray(), organizacoes.toArray()[0]);

                    }

                    Controller.setOrganizacao(organizacao);

                }

                if (Controller.getOrganizacao() != null) {

                    Controller.setIpServidorBD(Controller.getOrganizacao().getIpServidor());

                    Controller.setNameServidorBD(Controller.getOrganizacao().getNomeServidor());

                }

                usuarioModel.setUltimoAcesso(Controller.getDataServidorBD());
                usuarioModel.setMovimentoDiarioModel(registroMovimento("Acesso ao sistema", PempecKeyGenerator.generateKeyLong().toString(), "o usuario " + usuarioModel.getNome() + " logou no sistema.", null, "Logado"));
                usuarioBO.registraAcesso(usuarioModel); //19/05/2012

                //Ocorrendo sucesso, direcionando para tela principal
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {

                        new TelaPrincipal().setVisible(true);
                    }
                });

                this.dispose();

            } catch (ApplicationException ex) {

                JOptionPane.showMessageDialog(null, "Erro -> " + ex.getLocalizedMessage());

            } catch (SystemException ex) {

                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erro -> " + ex.getLocalizedMessage());

            }

        } else {

            JOptionPane.showMessageDialog(null, "Campo(s) Obrigatório(s)");

        }

}//GEN-LAST:event_botaoLogarActionPerformed

private void labelAlterarSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAlterarSenhaMouseClicked

    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new TelaAlterarSenha().setVisible(true);
        }
    });

}//GEN-LAST:event_labelAlterarSenhaMouseClicked

private void jTSenhaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTSenhaKeyPressed

    if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
        botaoLogar.doClick();
    }


}//GEN-LAST:event_jTSenhaKeyPressed

    /**
     * @param args the command line arguments public static void main(String[]
     * args){ String nome = args[0];//Primeiro parametro int codigo =
     * Integer.parseInt(args[1]);//Segundo parametro }
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoLogar;
    private javax.swing.JButton botaoSair;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTLogin;
    private javax.swing.JPasswordField jTSenha;
    private javax.swing.JLabel labelAlterarSenha;
    private javax.swing.JLabel labelLogin;
    private javax.swing.JLabel labelSenha;
    // End of variables declaration//GEN-END:variables

    private MovimentoDiarioModel registroMovimento(String acao, String codigo, String descricao, Double valor, String status) {
        MovimentoDiarioModel movimentoDiarioModel = new MovimentoDiarioModel();
        movimentoDiarioModel.setPk(new PKModel());
        movimentoDiarioModel.getPk().setOrganizacao(Controller.getOrganizacao());
        movimentoDiarioModel.getPk().setId(PempecKeyGenerator.generateKey());
        movimentoDiarioModel.setDataRegistro(PempecParse.dateToDate(Controller.getDataServidorBD()));
        movimentoDiarioModel.setAcao(acao.toUpperCase());
        movimentoDiarioModel.setCodigo(codigo);
        movimentoDiarioModel.setDescricaoObjeto(descricao.toUpperCase());
        movimentoDiarioModel.setValorOperacao(valor);
        movimentoDiarioModel.setStatusFinalObjeto(status.toUpperCase());
        movimentoDiarioModel.setObjeto(this.getClass().getSimpleName().toUpperCase());
        movimentoDiarioModel.setUsuario(Controller.getUsuarioLogado());
        movimentoDiarioModel.setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

        return movimentoDiarioModel;

    }
}
