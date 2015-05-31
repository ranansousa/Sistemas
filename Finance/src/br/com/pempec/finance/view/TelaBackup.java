package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.BackupBO;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BackupModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.utils.ByteFormat;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.ResourcePropertiesLocator;
import java.io.File;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class TelaBackup extends FinanceInternalFrame implements IRepopulador {

    private BackupBO backupBO = new BackupBO();
    long qtd = Controller.getQtdMovimentosHoje();

    private String NameObject() {
        return (String) "Backup";
    }

    public TelaBackup() throws Exception {

        initComponents();

        Controller.setQtdMovimentosHoje(0);

        this.setLocation(250, 50);
        jBProcessandoImg.setVisible(false);
        jLProcessando.setVisible(false);
        botaoConfirmar.setEnabled(true);
    }

    public void repopularCombos() {

        jLabeTotalEspace.setText(totalEspaco);

        jLabeFreeEspace.setText(freeEspaco);

        if (Controller.getOrganizacao().getRazaoSocial() != null) {

            jLabelOrganizacao.setText(Controller.getOrganizacao().getRazaoSocial().toUpperCase());
        }


        jLabelServer.setText(Controller.getNameServidorBD().toUpperCase());

        jLabelHost.setText(Controller.getNomeEstacao().toUpperCase());

        jLabelData.setText(PempecParse.dateToString(Controller.getDataServidorBD()));

        jLabelNameFile.setText("");

        jLabelNomeUser.setText(Controller.getUsuarioLogado().getNome());



    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelOrganizacao = new javax.swing.JLabel();
        jLabelHost = new javax.swing.JLabel();
        jLabelServer = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jLabelNameFile = new javax.swing.JLabel();
        jLabelNomeUser = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLProcessando = new javax.swing.JLabel();
        jBProcessandoImg = new javax.swing.JProgressBar();
        jPanel2 = new javax.swing.JPanel();
        botaoConfirmar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        jLabeFreeEspace = new javax.swing.JLabel();
        jLabeTotalEspace = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Backup");

        jLabelOrganizacao.setFont(new java.awt.Font("Arial", 0, 12));
        jLabelOrganizacao.setForeground(new java.awt.Color(0, 102, 102));
        jLabelOrganizacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOrganizacao.setText("jLabel1");
        jLabelOrganizacao.setBorder(javax.swing.BorderFactory.createTitledBorder("Empresa"));

        jLabelHost.setFont(new java.awt.Font("Arial", 0, 12));
        jLabelHost.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelHost.setText("jLabel2");
        jLabelHost.setBorder(javax.swing.BorderFactory.createTitledBorder("Host"));

        jLabelServer.setFont(new java.awt.Font("Arial", 0, 12));
        jLabelServer.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelServer.setText("jLabel2");
        jLabelServer.setBorder(javax.swing.BorderFactory.createTitledBorder("Server"));

        jLabelData.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelData.setText("jLabel1");
        jLabelData.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Backup"));

        jLabelNameFile.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNameFile.setText("jLabel1");
        jLabelNameFile.setBorder(javax.swing.BorderFactory.createTitledBorder("Arquivo Backup"));

        jLabelNomeUser.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelNomeUser.setText("jLabel1");
        jLabelNomeUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuário"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12));
        jLabel1.setForeground(new java.awt.Color(102, 0, 0));
        jLabel1.setText("O sistema será fechado após a conclusão do backup.");

        jLProcessando.setText("Processando...");

        jBProcessandoImg.setIndeterminate(true);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoConfirmar.setMnemonic('B');
        botaoConfirmar.setText("Backup");
        botaoConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmarActionPerformed(evt);
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
                .addGap(18, 18, 18)
                .addComponent(botaoConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabeFreeEspace.setForeground(new java.awt.Color(0, 102, 255));
        jLabeFreeEspace.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabeFreeEspace.setText("jLabel1");
        jLabeFreeEspace.setBorder(javax.swing.BorderFactory.createTitledBorder("Espaço Livre"));

        jLabeTotalEspace.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabeTotalEspace.setText("jLabel1");
        jLabeTotalEspace.setBorder(javax.swing.BorderFactory.createTitledBorder("Espaço Total"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelServer, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(jLabelHost, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelData, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                .addGap(49, 49, 49)
                                .addComponent(jLProcessando)
                                .addGap(65, 65, 65))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addComponent(jBProcessandoImg, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelNomeUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabeTotalEspace, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))
                    .addComponent(jLabelNameFile, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addComponent(jLabelOrganizacao, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabeFreeEspace, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(287, 287, 287))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelOrganizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelServer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelHost, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelNameFile, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabeFreeEspace, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabeTotalEspace, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLProcessando)
                        .addGap(18, 18, 18)
                        .addComponent(jBProcessandoImg, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmarActionPerformed

        Long tam = tamUnidade.getTotalSpace();

        if (tam > (1024 ^ 2)) {

            if (Controller.getNomeEstacao().equalsIgnoreCase(Controller.getNameServidorBD())) {

                if (Controller.getIpServidorBD().equalsIgnoreCase(Controller.getIpEstacao())) {

                    Thread threadBarra = new Thread(new Runnable() {
                        public void run() {

                            jBProcessandoImg.setVisible(true);
                            jLProcessando.setVisible(true);
                            botaoConfirmar.setEnabled(false);
                            botaoFechar.setEnabled(false);

                        }
                    });

                    threadBarra.setPriority(Thread.MAX_PRIORITY);

                    threadBarra.start();


                    new Thread(new Runnable() {
                        public void run() {

                            try {

                                String path = "\"" + ResourcePropertiesLocator.getMessage("path_database");
                                String localApp = ResourcePropertiesLocator.getMessage("path_firebird");

                                Date dataServidor = new Date();

                                String data = PempecUtil.converteDataDDMMYYYY(PempecParse.dateToString(dataServidor));
                                String hora = PempecParse.getHourFromDate(dataServidor);
                                String minutos = PempecParse.getMinuteFromDate(dataServidor);

                                String nameFile = "\\FIN_" + data + "_" + hora + "_" + minutos + ".FBK\"";

                                jLabelNameFile.setText(nameFile.substring(1));

                                BackupModel backupModel = new BackupModel();

                                backupModel.setPk(new PKModel());

                                backupModel.getPk().setOrganizacao(organizacaoModel);

                                backupModel.getPk().setId(PempecKeyGenerator.generateKey());

                                backupModel.setDataBackup(Controller.getDataServidorBD());

                                backupModel.setNomeFile(nameFile.substring(1));

                                backupModel.setUsuarioModel(Controller.getUsuarioLogado());

                                backupModel.setQtdRegistros(qtd);

                                backupModel.setMovimentoDiarioModel(registroMovimento("Backup", PempecKeyGenerator.generateKeyLong().toString(), Controller.getUsuarioLogado().getNome() + " realizou backup" + " ARQ: " + jLabelNameFile.getText(), 0.d, "Sucesso"));


                                backupBO.inserir(backupModel);

                                //movimentoDiarioBO.inserir(registroMovimento("Backup", PempecKeyGenerator.generateKeyLong().toString(), Controller.getUsuarioLogado().getNome() + " realizou backup" + " ARQ: " + jLabelNameFile.getText(), 0.d, "Sucesso"));


                                String urlBackup = "\"" + localApp + "\\gbak\" -t -user SYSDBA -password \"masterkey\" " + path + "\\FINANCE.FDB\" " + path + nameFile;

                                Process processo = Runtime.getRuntime().exec(urlBackup);

                                processo.waitFor();


                                System.exit(0);


                            } catch (SystemException ex) {

                                ex.printStackTrace();

                            } catch (final Exception ex) {

                                final File file = PrintScreen.capture();

                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {

                                        tratamentoExcecao(new SystemException(ex), file);


                                    }
                                });

                            }

                        }
                    }).start();




                } else {

                    JOptionPane.showMessageDialog(null, "Essa função só pode ser executada no SERVIDOR\n" + "Incoerencia com o endereo IP.");
                }


            } else {

                JOptionPane.showMessageDialog(null, "Essa rotina só pode ser executada no SERVIDOR\n" + "Incoerencia com o nome do computador");
            }
        } else {

            JOptionPane.showMessageDialog(null, "Sem espaço em disco para o backup");

        }


}//GEN-LAST:event_botaoConfirmarActionPerformed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoConfirmar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JProgressBar jBProcessandoImg;
    private javax.swing.JLabel jLProcessando;
    private javax.swing.JLabel jLabeFreeEspace;
    private javax.swing.JLabel jLabeTotalEspace;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelHost;
    private javax.swing.JLabel jLabelNameFile;
    private javax.swing.JLabel jLabelNomeUser;
    private javax.swing.JLabel jLabelOrganizacao;
    private javax.swing.JLabel jLabelServer;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
    String local = ResourcePropertiesLocator.getMessage("path_database");
    File tamUnidade = new File(local);
    Long espacoTotal = (Long) tamUnidade.getTotalSpace();
    Long espacoLivre = (Long) tamUnidade.getFreeSpace();
    String totalEspaco = new ByteFormat().formatInGigaBytes(espacoTotal);
    String freeEspaco = new ByteFormat().formatInGigaBytes(espacoLivre);

    /*
     private void botaoDeletarActionPerformed(java.awt.event.ActionEvent evt) {
     try {

     String path = "\"" + ResourcePropertiesLocator.getMessage("path_database");
     String cmd =path.substring(1) + "\\*.fbk";


     if(exibeMensagemConfirmacao("Tem certeza que deseja apagar os arquivos de Backup?", "Deleta")){

     //                    Process processo = Runtime.getRuntime().exec(cmd);
     Runtime.getRuntime().exec("del " + cmd);

     exibeMensagemAviso("Arquivos deletados", "Sucesso!");

     }else{

     exibeMensagemAviso("Operação abortada", "Cancel!");

     this.botaoFecharActionPerformed(evt);


     }


     } catch (final Exception ex) {

     final File file = PrintScreen.capture();

     SwingUtilities.invokeLater(new Runnable() {

     public void run() {

     tratamentoExcecao(new SystemException(ex), file);


     }
     });

     }

     }
     */
}
