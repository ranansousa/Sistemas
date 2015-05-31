package br.com.pempec.finance.view;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.ResourcePropertiesLocator;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author PEMPEC
 */
public class AboutFinance extends FinanceInternalFrame implements IRepopulador {

    private String NameObject() {
        return (String) "Sobre";
    }

    public AboutFinance() throws SystemException {

        initComponents();
        this.setLocation(250, 50);

        labelAbout.setText(ResourcePropertiesLocator.getMessageAbout("labelAbout"));
        labelVersion.setText(ResourcePropertiesLocator.getMessageAbout("labelVersion"));
        labelContato.setText(ResourcePropertiesLocator.getMessageAbout("labelContato"));
        labelLicenciado.setText(Controller.getOrganizacao().getRazaoSocial());

        try {

            labelEstacao.setText(InetAddress.getLocalHost().getHostName() + " # " + Controller.getIpEstacao());

            labelServer.setText(Controller.getNameServidorBD() + " # " + Controller.getIpServidorBD());

        } catch (UnknownHostException ex) {

            ex.printStackTrace();
        }


    }

    public void repopularCombos() {
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botaoFechar2 = new javax.swing.JButton();
        labelAbout = new java.awt.Label();
        labelVersion = new java.awt.Label();
        labelLicenciado = new java.awt.Label();
        labelLic = new java.awt.Label();
        botaoLogo = new javax.swing.JButton();
        labelLic2 = new java.awt.Label();
        labelContato = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        labelEstacao = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        labelServer = new javax.swing.JLabel();
        JBSite = new javax.swing.JButton();
        JBSite1 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance ");

        botaoFechar2.setMnemonic('F');
        botaoFechar2.setText("Ok");
        botaoFechar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFechar2ActionPerformed(evt);
            }
        });

        labelAbout.setAlignment(java.awt.Label.CENTER);
        labelAbout.setFont(new java.awt.Font("Arial", 0, 14));
        labelAbout.setForeground(new java.awt.Color(0, 153, 255));
        labelAbout.setText("label1");

        labelVersion.setAlignment(java.awt.Label.CENTER);
        labelVersion.setFont(new java.awt.Font("Arial", 0, 14));
        labelVersion.setForeground(new java.awt.Color(0, 153, 255));
        labelVersion.setText("label1");

        labelLicenciado.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        labelLicenciado.setForeground(new java.awt.Color(0, 153, 255));

        labelLic.setText("Licenciado para:");

        botaoLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo-112x78.jpg"))); // NOI18N
        botaoLogo.setMnemonic('F');

        labelLic2.setText("Contatos:");

        labelContato.setFont(new java.awt.Font("Arial", 0, 10));
        labelContato.setForeground(new java.awt.Color(0, 153, 255));

        jLabel1.setText("Estação:");

        labelEstacao.setText("jLabel2");

        jLabel2.setText("Servidor:");

        labelServer.setText("jLabel2");

        JBSite.setText("Acessar site");
        JBSite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSiteActionPerformed(evt);
            }
        });

        JBSite1.setText("Download Finance");
        JBSite1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSite1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botaoLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelAbout, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelVersion, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(207, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labelLic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labelLicenciado, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(labelLic2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(labelContato, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(botaoFechar2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(JBSite, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(JBSite1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelEstacao, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 2, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelServer, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(336, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(labelAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botaoLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelLic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLicenciado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelLic2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelEstacao))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelServer))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoFechar2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBSite, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBSite1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void botaoFechar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFechar2ActionPerformed
    setVisible(false);
}//GEN-LAST:event_botaoFechar2ActionPerformed

private void JBSiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSiteActionPerformed

    try {
        String url = "www.pempec.com.br/finance";

        if (Controller.getSO().startsWith("Windows")) {

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);

        } else {

            exibeMensagemAviso("Impossível executar operação. Sistema Operacional " + Controller.getSO() + " não responde.", "Acesso Web");

        }
        // String urlConnection = " explorer http://www.pempec.com.br/finance";
        //  Runtime.getRuntime().exec(urlConnection.toString());

    } catch (Exception e) {

        exibeMensagemAviso("Nao foi possivel acessar o site. Tente utilizando o seu browser preferido.\n" + e.getMessage(), "Acesso Web");
    }



}//GEN-LAST:event_JBSiteActionPerformed

private void JBSite1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSite1ActionPerformed



    try {
//        String urlConnection = " explorer http://www.pempec.com.br/finance/FinanceUpdate.exe";
//
//        Runtime.getRuntime().exec(urlConnection.toString());

        String url = "www.pempec.com.br/finance/FinanceUpdate.exe";

        if (Controller.getSO().startsWith("Windows")) {

            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);

        } else {

            exibeMensagemAviso("Impossível executar operação. Sistema Operacional " + Controller.getSO() + " não responde.", "Acesso Web");

        }

    } catch (Exception e) {

        exibeMensagemAviso("Nao foi possivel acessar o site. Tente utilizando o seu browser preferido.\n" + e.getMessage(), "Acesso Web");
    }


}//GEN-LAST:event_JBSite1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBSite;
    private javax.swing.JButton JBSite1;
    private javax.swing.JButton botaoFechar2;
    private javax.swing.JButton botaoLogo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private java.awt.Label labelAbout;
    private java.awt.Label labelContato;
    private javax.swing.JLabel labelEstacao;
    private java.awt.Label labelLic;
    private java.awt.Label labelLic2;
    private java.awt.Label labelLicenciado;
    private javax.swing.JLabel labelServer;
    private java.awt.Label labelVersion;
    // End of variables declaration//GEN-END:variables
}
