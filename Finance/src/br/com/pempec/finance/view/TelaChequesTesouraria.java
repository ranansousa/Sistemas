package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.BancoBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaChequeBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BancoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class TelaChequesTesouraria extends FinanceInternalFrame implements IRepopulador {

    private BancoBO bancoBO = new BancoBO();
    private TituloReceberBaixaChequeBO tituloReceberBaixaChequeBO = new TituloReceberBaixaChequeBO();
    private long tela = Tela.TELA_TESOURARIA_CHEQUES_RECEBIDOS.getTela();

    private String NameObject() {
        return "Cheques Recebidos";
    }

    public TelaChequesTesouraria() throws SystemException {

        initComponents();
        comboCheque.setEnabled(false);
        labelDataDeposito.setVisible(false);

    }

    public void repopularCombos() {

        try {

            Collection<TituloReceberBaixaChequeModel> lCheques = new ArrayList<TituloReceberBaixaChequeModel>();

            TituloReceberBaixaChequeModel tituloReceberBaixaChequeModel = new TituloReceberBaixaChequeModel();

            tituloReceberBaixaChequeModel.setNumeroCheque(" -> Selecione <- ");

            lCheques.add(tituloReceberBaixaChequeModel);

            lCheques.addAll(tituloReceberBaixaChequeBO.obterTodos(organizacaoModel));

            comboCheque.setModel(new javax.swing.DefaultComboBoxModel(lCheques.toArray()));


            Collection<BancoModel> lBancos = new ArrayList<BancoModel>();

            //bancos

            BancoModel bancoModel = new BancoModel();

            bancoModel.setNomeBanco(" -> Selecione <- ");

            lBancos.add(bancoModel);

            lBancos.addAll(bancoBO.obterTodos());

            comboBanco.setModel(new javax.swing.DefaultComboBoxModel(lBancos.toArray()));


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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        panelCheques = new javax.swing.JTabbedPane();
        abaConsulta = new javax.swing.JPanel();
        labelNomeBanco = new javax.swing.JLabel();
        comboBanco = new javax.swing.JComboBox();
        labelCheque = new javax.swing.JLabel();
        comboCheque = new javax.swing.JComboBox();
        labelTitular = new javax.swing.JLabel();
        labelAgencia = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();
        labelConta = new javax.swing.JLabel();
        labelDataProtocolo = new javax.swing.JLabel();
        labelValor = new javax.swing.JLabel();
        labelDataDeposito = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimparConsulta = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jButton3.setText("jButton3");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cheques Recebidos");

        abaConsulta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        labelNomeBanco.setText("Banco");

        comboBanco.setFont(new java.awt.Font("Arial", 0, 11));
        comboBanco.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBancoItemStateChanged(evt);
            }
        });

        labelCheque.setText("Cheque");

        comboCheque.setFont(new java.awt.Font("Arial", 0, 10));
        comboCheque.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboChequeItemStateChanged(evt);
            }
        });

        labelTitular.setBackground(new java.awt.Color(222, 218, 210));
        labelTitular.setFont(new java.awt.Font("Arial", 1, 10));
        labelTitular.setForeground(new java.awt.Color(0, 153, 153));
        labelTitular.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTitular.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Titular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelAgencia.setBackground(new java.awt.Color(222, 218, 210));
        labelAgencia.setFont(new java.awt.Font("Arial", 1, 10));
        labelAgencia.setForeground(new java.awt.Color(0, 153, 153));
        labelAgencia.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelAgencia.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelAgencia.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Agência", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelStatus.setBackground(new java.awt.Color(222, 218, 210));
        labelStatus.setFont(new java.awt.Font("Arial", 1, 10));
        labelStatus.setForeground(new java.awt.Color(0, 153, 153));
        labelStatus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelStatus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelConta.setBackground(new java.awt.Color(222, 218, 210));
        labelConta.setFont(new java.awt.Font("Arial", 1, 10));
        labelConta.setForeground(new java.awt.Color(0, 153, 153));
        labelConta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelConta.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelConta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Conta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelDataProtocolo.setBackground(new java.awt.Color(222, 218, 210));
        labelDataProtocolo.setFont(new java.awt.Font("Arial", 1, 10));
        labelDataProtocolo.setForeground(new java.awt.Color(0, 153, 153));
        labelDataProtocolo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDataProtocolo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelDataProtocolo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Recebido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelValor.setBackground(new java.awt.Color(222, 218, 210));
        labelValor.setFont(new java.awt.Font("Arial", 1, 10));
        labelValor.setForeground(new java.awt.Color(0, 153, 153));
        labelValor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelValor.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelDataDeposito.setBackground(new java.awt.Color(222, 218, 210));
        labelDataDeposito.setFont(new java.awt.Font("Arial", 1, 10));
        labelDataDeposito.setForeground(new java.awt.Color(0, 153, 153));
        labelDataDeposito.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDataDeposito.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelDataDeposito.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Depósito", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        javax.swing.GroupLayout abaConsultaLayout = new javax.swing.GroupLayout(abaConsulta);
        abaConsulta.setLayout(abaConsultaLayout);
        abaConsultaLayout.setHorizontalGroup(
            abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaConsultaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaConsultaLayout.createSequentialGroup()
                        .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelConta, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataDeposito, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelTitular, javax.swing.GroupLayout.DEFAULT_SIZE, 389, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaConsultaLayout.createSequentialGroup()
                        .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNomeBanco)
                            .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 55, Short.MAX_VALUE)
                        .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCheque)
                            .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(41, 41, 41))
        );
        abaConsultaLayout.setVerticalGroup(
            abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaConsultaLayout.createSequentialGroup()
                        .addComponent(labelNomeBanco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelCheque))
                .addGap(18, 18, 18)
                .addComponent(labelTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(abaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaConsultaLayout.createSequentialGroup()
                        .addComponent(labelAgencia, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaConsultaLayout.createSequentialGroup()
                        .addComponent(labelConta, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDataProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaConsultaLayout.createSequentialGroup()
                        .addComponent(labelValor, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDataDeposito, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        panelCheques.addTab("Consultar", abaConsulta);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoFechar.setMnemonic('F');
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        botaoLimparConsulta.setMnemonic('L');
        botaoLimparConsulta.setText("Limpar");
        botaoLimparConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparConsultaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimparConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoLimparConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelCheques, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelCheques, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboChequeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboChequeItemStateChanged

        if (comboCheque.getSelectedItem() != null
                && ((TituloReceberBaixaChequeModel) comboCheque.getSelectedItem()).getPk() != null) {

            TituloReceberBaixaChequeModel cheque = (TituloReceberBaixaChequeModel) comboCheque.getSelectedItem();


            if (cheque.getTitular() != null) {
                labelTitular.setText(cheque.getTitular());
            }

            if (cheque.getDataProtocolo() != null) {
                labelDataProtocolo.setText(PempecParse.dateToString(cheque.getDataProtocolo()));
            }

            if (cheque.getAgencia() != null) {
                labelAgencia.setText(cheque.getAgencia());
            }

            if (cheque.getConta() != null) {
                labelConta.setText(cheque.getConta());
            }

            if (cheque.getDataDeposito() != null) {
                labelDataDeposito.setVisible(true);
                labelDataDeposito.setText(PempecParse.dateToString(cheque.getDataDeposito()));
                labelStatus.setText("Depositado");
            } else {
                labelStatus.setText("Em caixa");
            }


            if (cheque.getValor() != null) {
                labelValor.setText(PempecParse.doubleToZero(cheque.getValor()));
            }

        }

}//GEN-LAST:event_comboChequeItemStateChanged

    private void comboBancoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBancoItemStateChanged
        comboCheque.setEnabled(true);

        try {

            new MovimentoDiarioBO().inserir(this.registroMovimento("Consulta CH", null, Controller.getUsuarioLogado().getNome() + " consultou cheque na tesouraria.", null, "Consultado"));

            if (comboBanco.getSelectedItem() != null) {

                BancoModel bancoModel = ((BancoModel) comboBanco.getSelectedItem());

                if (bancoModel.getId() != null) {
                    bancoModel = bancoBO.consultarPorTemplate(bancoModel);
                }

                Collection<TituloReceberBaixaChequeModel> lCheques = new ArrayList<TituloReceberBaixaChequeModel>();

                TituloReceberBaixaChequeModel tituloReceberBaixaChequeModel = new TituloReceberBaixaChequeModel();

                tituloReceberBaixaChequeModel.setNumeroCheque(" -> Selecione <- ");

                lCheques.add(tituloReceberBaixaChequeModel);

                lCheques.addAll(tituloReceberBaixaChequeBO.obterPorBanco(bancoModel, organizacaoModel));

                comboCheque.setModel(new javax.swing.DefaultComboBoxModel(lCheques.toArray()));

            } else {

                Collection<TituloReceberBaixaChequeModel> lCheques = new ArrayList<TituloReceberBaixaChequeModel>();

                TituloReceberBaixaChequeModel tituloReceberBaixaChequeModel = new TituloReceberBaixaChequeModel();

                tituloReceberBaixaChequeModel.setNumeroCheque(" -> Selecione <- ");

                lCheques.add(tituloReceberBaixaChequeModel);

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



    }//GEN-LAST:event_comboBancoItemStateChanged

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparConsultaActionPerformed
        comboCheque.setSelectedIndex(0);
        comboBanco.setSelectedIndex(0);
        labelAgencia.setText("");
        labelConta.setText("");
        labelValor.setText("");
        labelTitular.setText("");
        labelStatus.setText("");
        labelDataDeposito.setText("");
        labelDataProtocolo.setText("");
        labelDataDeposito.setVisible(false);
        comboCheque.setEnabled(false);

}//GEN-LAST:event_botaoLimparConsultaActionPerformed
    private Boolean validaCampos() {

        return true;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaConsulta;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimparConsulta;
    private javax.swing.JComboBox comboBanco;
    private javax.swing.JComboBox comboCheque;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelAgencia;
    private javax.swing.JLabel labelCheque;
    private javax.swing.JLabel labelConta;
    private javax.swing.JLabel labelDataDeposito;
    private javax.swing.JLabel labelDataProtocolo;
    private javax.swing.JLabel labelNomeBanco;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelTitular;
    private javax.swing.JLabel labelValor;
    private javax.swing.JTabbedPane panelCheques;
    // End of variables declaration//GEN-END:variables
}
