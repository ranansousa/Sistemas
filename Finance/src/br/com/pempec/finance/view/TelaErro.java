package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import java.io.File;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author PEMPEC
 */
public class TelaErro extends FinanceInternalFrame implements IRepopulador {

    private String NameObject() {
        return (String) "ERRO DE SISTEMA!";
    }
    private File screen;

    public TelaErro(File screen) {

        initComponents();

        this.screen = screen;

    }

    public void repopularCombos() {
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botaoFechar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        labelDataHora = new javax.swing.JLabel();
        labelTela = new javax.swing.JLabel();
        labelMensagem = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTObservacao = new org.jdesktop.swingx.JXEditorPane();
        botaoNotificar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Erro");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("ERRO DE SISTEMA!");

        jLabel2.setText("Mensagem:");

        jLabel3.setText("Tela:");

        jLabel5.setText("Detalhamento:");

        botaoFechar.setMnemonic('F');
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        jLabel6.setText("Data/Hora:");

        labelDataHora.setPreferredSize(new java.awt.Dimension(54, 14));

        labelTela.setFont(new java.awt.Font("Arial", 0, 10));
        labelTela.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        labelMensagem.setFont(new java.awt.Font("Arial", 0, 10));
        labelMensagem.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        jTObservacao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jTObservacao.setEditable(false);
        jTObservacao.setFont(new java.awt.Font("Arial", 0, 11));
        jScrollPane2.setViewportView(jTObservacao);

        botaoNotificar.setMnemonic('F');
        botaoNotificar.setText("Notificar");
        botaoNotificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNotificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(176, 176, 176)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelDataHora, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                .addContainerGap(457, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelTela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(76, 76, 76))
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(botaoNotificar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 522, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(72, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelDataHora, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTela, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(8, 8, 8)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoNotificar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(122, 122, 122)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(94, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed

        this.dispose();

}//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoNotificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNotificarActionPerformed

        try {

            MovimentoDiarioModel mov = new MovimentoDiarioModel();

            mov.setPk(new PKModel());

            mov.getPk().setOrganizacao(Controller.getOrganizacao());

            mov.getPk().setId(PempecKeyGenerator.generateKey());

            mov.setDescricaoObjeto("USUARIO " + Controller.getUsuarioLogado().toString().toUpperCase() + " ENVIOU E-MAIL ");

            mov.setObjeto(this.getClass().getSimpleName());

            mov.setValorOperacao(0d);

            mov.setStatusFinalObjeto("Enviado");

            mov.setObservacao("ASSUNTO :" + "Notificador");

            mov.setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

            mov.setAcao("ENVIO DE EMAIL");

            mov.setCodigo(PempecKeyGenerator.generateKeyLong().toString());

            mov.setDataRegistro(PempecParse.dateToDate(new Date()));

            String msg = Controller.sendMailErro(screen, labelMensagem.getText() + "\n" + jTObservacao.getText(), mov);

            JOptionPane.showInternalMessageDialog(this, msg);

            new MovimentoDiarioBO().inserir(this.registroMovimento("ERRO", null, Controller.getUsuarioLogado().getNome() + " notificou erro. >" + labelMensagem.getText(), null, "Registro"));

        } catch (ApplicationException ex) {

            exibeMensagemAviso("Erro ao Notificar! Contacte administrador.", "ERRO!");

        } catch (SystemException ex) {

            exibeMensagemAviso("Erro ao Notificar! Contacte administrador.", "ERRO!");

        }

}//GEN-LAST:event_botaoNotificarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoNotificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    public org.jdesktop.swingx.JXEditorPane jTObservacao;
    public javax.swing.JLabel labelDataHora;
    public javax.swing.JLabel labelMensagem;
    public javax.swing.JLabel labelTela;
    // End of variables declaration//GEN-END:variables
}
