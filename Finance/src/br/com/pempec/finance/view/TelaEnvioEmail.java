package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContatoModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import org.jdesktop.swingx.combobox.ListComboBoxModel;

/**
 *
 * @author PEMPEC
 */
public class TelaEnvioEmail extends FinanceInternalFrame implements IRepopulador {

    private File anexo;
    private String[] destinatarios;
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private long tela = Tela.TELA_SISTEMA_ENVIAR_EMAIL.getTela();
    List<FuncionarioModel> lFuncionario;
    int selecionados = 0;

    private String NameObject() {
        return (String) "Envio de E-Mail";
    }

    public void repopularCombos() {

        try {

            lFuncionario = funcionarioBO.obterTodosComEmail(organizacaoModel);

            ListModel model = new ListComboBoxModel(lFuncionario);

            jList.setModel(model);

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

    public TelaEnvioEmail() throws SystemException {
        initComponents();
        Controller.setQtdMovimentosHoje(0);
        this.setLocation(250, 50);
    }

    public TelaEnvioEmail(File file) throws SystemException {

        initComponents();
        Controller.setQtdMovimentosHoje(0);

        this.anexo = file;
        botaoPesquisar.setEnabled(false);
        jTAnexo.setText(this.anexo.getName());

        this.repopularCombos();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelCedente = new javax.swing.JLabel();
        labelCNPJ1 = new javax.swing.JLabel();
        labelCedente1 = new javax.swing.JLabel();
        jTAssunto = new javax.swing.JFormattedTextField();
        labelInscM = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoEnviar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        botaoNovoUser = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTMensagem = new org.jdesktop.swingx.JXEditorPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList = new javax.swing.JList();
        jTAnexo = new javax.swing.JTextField();
        botaoPesquisar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Tela Envio de E-mail");
        setPreferredSize(new java.awt.Dimension(600, 480));

        labelCedente.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCedente.setText("Para:");
        labelCedente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        labelCNPJ1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCNPJ1.setText("Assunto:");
        labelCNPJ1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        labelCedente1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCedente1.setText("Anexo:");
        labelCedente1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        jTAssunto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        labelInscM.setText("Mensagem:");
        labelInscM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoEnviar.setForeground(new java.awt.Color(0, 153, 153));
        botaoEnviar.setMnemonic('I');
        botaoEnviar.setText("Enviar");
        botaoEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEnviarActionPerformed(evt);
            }
        });

        botaoFechar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        botaoFechar.setMnemonic('F');
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        botaoLimpar.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        botaoLimpar.setMnemonic('L');
        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoNovoUser.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        botaoNovoUser.setText("E-Mail");
        botaoNovoUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoNovoUserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoNovoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoEnviar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoNovoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTMensagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jScrollPane1.setViewportView(jTMensagem);

        jList.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jScrollPane2.setViewportView(jList);

        jTAnexo.setEditable(false);
        jTAnexo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, java.awt.Color.white, null));
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCedente, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(labelCedente1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(labelCNPJ1, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                            .addComponent(labelInscM, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jTAnexo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jTAssunto, javax.swing.GroupLayout.Alignment.LEADING))))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCedente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCedente1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCNPJ1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTAssunto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelInscM, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        jList.clearSelection();

        if (this.anexo != null) {
            botaoPesquisar.setEnabled(false);
        } else {
            botaoPesquisar.setEnabled(true);
            jTAnexo.setText("");
        }

        jTAssunto.setText("");
        jTMensagem.setText("");


}//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEnviarActionPerformed

        try {

            long action = Action.CADASTRAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCampos()) {

                if (destinatarios == null || destinatarios.length == 0) {
                    exibeMensagemAviso("Obrigatório selecionar pelo menos um destinatário!", null);
                    return;
                }

                File aux = null;

                if (this.anexo != null) {
                    aux = this.anexo;
                } else {

                    if (!jTAnexo.getText().isEmpty()) {
                        aux = new File(jTAnexo.getText());
                    }

                }


                MovimentoDiarioModel mov = new MovimentoDiarioModel();

                mov.setPk(new PKModel());

                mov.getPk().setOrganizacao(organizacaoModel);

                mov.getPk().setId(PempecKeyGenerator.generateKey());

                mov.setDescricaoObjeto("USUARIO " + Controller.getUsuarioLogado().toString().toUpperCase() + " ENVIOU E-MAIL ");

                mov.setObjeto(this.getClass().getSimpleName());

                mov.setValorOperacao(0d);

                mov.setStatusFinalObjeto("Enviado");

                mov.setObservacao("ASSUNTO :" + jTAssunto.getText());

                mov.setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

                mov.setAcao("ENVIO DE EMAIL");

                mov.setCodigo(PempecKeyGenerator.generateKeyLong().toString());

                mov.setDataRegistro(PempecParse.dateToDate(new Date()));


                if (mov.getNumeroMovimento() != null) {

                    Controller.sendMail(destinatarios, jTAssunto.getText(), jTMensagem.getText(), aux, mov);

                } else {

                    Controller.sendMail(destinatarios, jTAssunto.getText(), jTMensagem.getText(), aux, null);
                }

                exibeMensagemAviso("E-mail enviado com Sucesso!", null);

                this.botaoLimparActionPerformed(evt);

            } else {

                exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

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
}//GEN-LAST:event_botaoEnviarActionPerformed

    private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed

        try {

            JFileChooser jfc = new JFileChooser();

            jfc.setMultiSelectionEnabled(false);

            jfc.setAcceptAllFileFilterUsed(false);

            FileFilter flf = new FileFilter() {
                public boolean accept(File f) {
                    if (f.isDirectory() || f.getName().endsWith("pdf") || f.getName().endsWith("xls")) {
                        return true;
                    }
                    return false;
                }

                public String getDescription() {
                    return "Arquivos PDF|EXCEL";
                }
            };

            jfc.addChoosableFileFilter(flf);

            if (jfc.showOpenDialog(this) == JFileChooser.CANCEL_OPTION) {
                return;
            }

            File arquivo = jfc.getSelectedFile();

            jTAnexo.setText(arquivo.getPath());

        } catch (final Exception ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(new SystemException(ex), file);

                }
            });

        }


}//GEN-LAST:event_botaoPesquisarActionPerformed

    private void botaoNovoUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoNovoUserActionPerformed
        // TODO add your handling code here:
        int aux = 0;

        ContatoModel ctt = new ContatoModel();
        ctt.setPk(new PKModel());
       
        FuncionarioModel temp = new FuncionarioModel();
        temp.setPk(new PKModel());

        String mail = JOptionPane.showInputDialog("Digite o Endereço de E-mail");
        String nome = JOptionPane.showInputDialog("Digite o Nome da Pessoa");

        while (aux == 0) {

            if (!PempecUtil.validaPreenchimentoEmail(mail)) {
                
                exibeMensagemAviso("Email incorreto", "E-mail");
                mail = JOptionPane.showInputDialog("Digite o Endereço de E-mail");

            } else {
                aux = 1;
            }
        }

        temp.setNome(nome.toUpperCase());
        ctt.setEmail(mail);
        temp.setContato(ctt);

        lFuncionario.add(temp);

        ListModel model = new ListComboBoxModel(lFuncionario);
        jList.setModel(model);
        jList.setSelectedIndex((lFuncionario.size() - 1) - selecionados);
        selecionados++;

    }//GEN-LAST:event_botaoNovoUserActionPerformed

    private Boolean validaCampos() {

        //Tratamento dos destinatários.
        Object[] objects = jList.getSelectedValues();
        destinatarios = new String[objects.length];

        int cont = 0;

        for (int i = 0; i < objects.length; i++) {
            FuncionarioModel func = (FuncionarioModel) objects[i];
            if (func.getContato() != null && func.getContato().getEmail() != null && !func.getContato().getEmail().isEmpty()) {
                destinatarios[cont] = func.getContato().getEmail();
                cont++;
            }
        }

        if (jTAssunto.getText().isEmpty()) {
            jTAssunto.requestFocus();
            return false;
        }

        if (jTMensagem.getText().isEmpty()) {
            jTMensagem.requestFocus();
            return false;
        }


        return true;

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoEnviar;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoNovoUser;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList jList;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTAnexo;
    private javax.swing.JFormattedTextField jTAssunto;
    private org.jdesktop.swingx.JXEditorPane jTMensagem;
    private javax.swing.JLabel labelCNPJ1;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelCedente1;
    private javax.swing.JLabel labelInscM;
    // End of variables declaration//GEN-END:variables
}
