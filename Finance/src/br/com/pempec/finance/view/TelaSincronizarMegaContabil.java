package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaContabilBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.PlanoContasMegaContabil;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.SincronizeMegaContabil;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class TelaSincronizarMegaContabil extends FinanceInternalFrame implements IRepopulador {

    private ContaContabilBO contaContabilBO = new ContaContabilBO();

    private String NameObject() {
        return (String) "Sincronizar Sistema Contábil";
    }

    public TelaSincronizarMegaContabil() throws SystemException {

        this.setLocation(250, 50);
        Controller.setQtdMovimentosHoje(0);
        initComponents();

        if (organizacaoModel != null && organizacaoModel.getSistemaContabil() != null) {
            jTSistema.setText(organizacaoModel.getSistemaContabil());
        }

        jLProcessando.setVisible(false);
        jBProcessandoImg.setVisible(false);

    }

    public void repopularCombos() {
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        botaoSincronizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTSistema = new javax.swing.JTextField();
        jLProcessando = new javax.swing.JLabel();
        jBProcessandoImg = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Sincronizar MegaContábil");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoFechar.setMnemonic('F');
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        botaoSincronizar.setMnemonic('N');
        botaoSincronizar.setText("Sincronizar");
        botaoSincronizar.setToolTipText("Insere novas contas do plano oficial");
        botaoSincronizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSincronizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoSincronizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoSincronizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Sincronizar"));

        jLabel2.setText("Sistema Interligado");

        jTSistema.setEditable(false);
        jTSistema.setForeground(new java.awt.Color(0, 153, 204));

        jLProcessando.setText("Processando...");

        jBProcessandoImg.setForeground(new java.awt.Color(0, 153, 153));
        jBProcessandoImg.setIndeterminate(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLProcessando, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBProcessandoImg, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTSistema, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jBProcessandoImg, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jLProcessando, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        jLabel1.setText("Sugerimos efetuar Backup do Sistema antes desta operação!");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private ContaContabilModel updateForPlanoContas(PlanoContasMegaContabil planoContas, ContaContabilModel contaContabil) {

        contaContabil.setDescricao(planoContas.getDescricao());
        contaContabil.setConta(planoContas.getConta());
        contaContabil.setDigitoConta(planoContas.getDigitoConta());
        contaContabil.setContaReduzida(planoContas.getContaReduzida());
        contaContabil.setDigitoContaReduzida(planoContas.getDigitoContaReduzida());
        contaContabil.setTipo(planoContas.getTipo());
        contaContabil.setGrau("5");
        contaContabil.setInscricaoCMF(planoContas.getInscricaoCMF());
        contaContabil.setNatureza(planoContas.getNatureza());
        contaContabil.setRelacionamento(planoContas.getRelacionamento());
        contaContabil.setOrdemDIPJ(planoContas.getOrdemDIPJ());

        return contaContabil;

    }

    private ContaContabilModel createForPlanoContas(PlanoContasMegaContabil planoContas) {

        ContaContabilModel contaContabil = new ContaContabilModel();

        contaContabil.setPk(new PKModel());
        //contaContabil.getPk().setId(tratamentoIDMega(planoContas.getId()));
        contaContabil.getPk().setId(Controller.tratamentoIDMega(planoContas.getId()));

        contaContabil.getPk().setOrganizacao(organizacaoModel);

        contaContabil.setDescricao(planoContas.getDescricao());
        contaContabil.setConta(planoContas.getConta());
        contaContabil.setDigitoConta(planoContas.getDigitoConta());
        contaContabil.setContaReduzida(planoContas.getContaReduzida());
        contaContabil.setDigitoContaReduzida(planoContas.getDigitoContaReduzida());
        contaContabil.setTipo(planoContas.getTipo());
        contaContabil.setGrau("5");
        contaContabil.setInscricaoCMF(planoContas.getInscricaoCMF());
        contaContabil.setNatureza(planoContas.getNatureza());
        contaContabil.setRelacionamento(planoContas.getRelacionamento());
        contaContabil.setOrdemDIPJ(planoContas.getOrdemDIPJ());

        return contaContabil;

    }

    private void addContaContabil(Collection<ContaContabilModel> collContaContabil, PlanoContasMegaContabil planoContas) {
        collContaContabil.add(this.createForPlanoContas(planoContas));
    }

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoSincronizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSincronizarActionPerformed

        Thread thread = new Thread(new Runnable() {
            public void run() {
                jBProcessandoImg.setVisible(true);
                jLProcessando.setVisible(true);
            }
        });

        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                try {

                    Collection<PlanoContasMegaContabil> collPlanos = SincronizeMegaContabil.getInstance().getPlanosContas();

                    Collection<ContaContabilModel> collContaContabil = contaContabilBO.obterTodos(organizacaoModel);

                    Collection<ContaContabilModel> collContaContabilInsert = new ArrayList<ContaContabilModel>();

                    Collection<ContaContabilModel> collContaContabilUpdate = new ArrayList<ContaContabilModel>();

                    boolean existe = false;

                    ContaContabilModel aux = null;

                    for (PlanoContasMegaContabil planoContasMegaContabil : collPlanos) {

                        //atualizar os campos na base de dados

                        planoContasMegaContabil.setGrau("5");

                        planoContasMegaContabil.setInscricaoCMF("0");

                        planoContasMegaContabil.setNatureza(PempecParse.stringToInteger(planoContasMegaContabil.getConta().substring(0, 1)));

                        planoContasMegaContabil.setOrdemDIPJ(0);

                        planoContasMegaContabil.setRelacionamento(0);

                        String conta = PempecUtil.somenteNumero(planoContasMegaContabil.getConta().trim());
                        String cReduzida = PempecUtil.somenteNumero(planoContasMegaContabil.getContaReduzida().trim());

                        existe = false;

                        for (ContaContabilModel contaContabil : collContaContabil) {

                            if ((cReduzida != null
                                    && contaContabil.getContaReduzida() != null
                                    && cReduzida.equals(PempecUtil.somenteNumero(contaContabil.getContaReduzida().trim())))
                                    || (conta != null
                                    && contaContabil.getConta() != null
                                    && conta.equals(PempecUtil.somenteNumero(contaContabil.getConta().trim())))) {

                                existe = true;

                                aux = contaContabil;

                                break;
                            }
                        }

                        if (existe) {
                            collContaContabilUpdate.add(updateForPlanoContas(planoContasMegaContabil, aux));
                        } else {
                            System.out.println("'" + planoContasMegaContabil.getId() + "',");
                            addContaContabil(collContaContabilInsert, planoContasMegaContabil);
                        }


                    }

                    new MovimentoDiarioBO().inserir(registroMovimento("Sincronize MegaContabil", "Sincronizar MegaContabil", "Sincronizar MegaContabil - INSERT", null, "Sincronizado"));

                    contaContabilBO.sincronizeMegaContabil(collContaContabilInsert, collContaContabilUpdate);

                    exibeMensagemAviso("Sincronização Finalizada.", "Sincronize Mega");


                } catch (ApplicationException ex) {

                    exibeMensagemAviso("O plano de contas do sistema não está completamente coerente com o plano de contas contábil oficial.\nAlterações no plano de contas do sistema causaram incoerências.\nNão será possível sincronizar os sistemas.\nEntre em contato com o suporte.", "Sincronizar!");

                } catch (final SystemException ex) {

                    final File file = PrintScreen.capture();

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {

                            tratamentoExcecao(ex, file);

                        }
                    });

                } finally {
                    jBProcessandoImg.setVisible(false);
                    jLProcessando.setVisible(false);
                }
            }
        });

    }//GEN-LAST:event_botaoSincronizarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoSincronizar;
    private javax.swing.JProgressBar jBProcessandoImg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLProcessando;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTSistema;
    // End of variables declaration//GEN-END:variables
}