package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ConstantesBO;
import br.com.pempec.finance.businessObjects.FeriadoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ConstantesModel;
import br.com.pempec.finance.models.FeriadoModel;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import javax.swing.SwingUtilities;
import org.jdesktop.swingx.calendar.DateSelectionModel.SelectionMode;
import org.jdesktop.swingx.event.DateSelectionEvent;
import org.jdesktop.swingx.event.DateSelectionListener;

/**
 *
 * @author PEMPEC
 */
public class TelaFeriados extends FinanceInternalFrame implements IRepopulador {

    private ConstantesBO constantesBO = new ConstantesBO();
    private FeriadoBO feriadoBO = new FeriadoBO();
    private Collection<FeriadoModel> collFeriados = Controller.getCollFeriados();

    private String NameObject() {
        return (String) "Cadastro de Feriados";
    }

    public TelaFeriados() throws SystemException {

        this.setLocation(250, 50);
        initComponents();

        jXFeriados.setFirstDayOfWeek(Calendar.SUNDAY);

        try {

            ConstantesModel constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_FERIADO_INICIO_CALENDARIO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            jXFeriados.setLowerBound(PempecParse.stringToDate(constantesModel.getCodigo()));

            constantesModel = new ConstantesModel();

            constantesModel.setId(Constantes.CONSTANTES_FERIADO_FIM_CALENDARIO);

            constantesModel = constantesBO.consultarPorPk(constantesModel);

            jXFeriados.setUpperBound(PempecParse.stringToDate(constantesModel.getCodigo()));

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

        jXFeriados.setSelectionMode(SelectionMode.MULTIPLE_INTERVAL_SELECTION);

        jXFeriados.getSelectionModel().addDateSelectionListener(new DateSelectionListener() {
            public void valueChanged(DateSelectionEvent e) {

                for (Date data : e.getSelection()) {
                    if (data != null && jXFeriados.isFlaggedDate(data)) {
                        if (exibeMensagemConfirmacao("Deseja remover este Feriado: " + PempecParse.dateToString(data) + "?. ", "Remoção Feriado")) {

                            try {

                                FeriadoModel feriadoDelete = null;

                                for (FeriadoModel feriado : collFeriados) {
                                    if (data.compareTo(PempecParse.dateToDate(feriado.getData())) == 0) {
                                        feriadoDelete = feriado;
                                        collFeriados.remove(feriado);
                                        break;
                                    }
                                }

                                if (feriadoDelete != null) {

                                    ConstantesModel constantesModel = new ConstantesModel();

                                    constantesModel.setId(Constantes.CONSTANTES_FERIADO_CALENDARIO_MODIFICADO);

                                    constantesModel = constantesBO.consultarPorPk(constantesModel);

                                    constantesModel.setCodigo("1");

                                    constantesBO.alterar(constantesModel);

                                    feriadoBO.excluir(feriadoDelete);

                                    exibeMensagemAviso("Feriado removido com sucesso!", null);

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

                            jXFeriados.clearSelection();

                        }
                    }
                }
            }
        });

    }

    public void repopularCombos() {

        jXFeriados.clearSelection();

        jXFeriados.clearFlaggedDates();

        for (FeriadoModel feriadoModel : collFeriados) {
            jXFeriados.addFlaggedDates(feriadoModel.getData());
        }

        jXFeriados.repaint();

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jXFeriados = new org.jdesktop.swingx.JXMonthView();
        jPanel27 = new javax.swing.JPanel();
        botaoFecharG = new javax.swing.JButton();
        botaoSalvarG = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro de Feriados");

        jXFeriados.setShowingLeadingDays(true);
        jXFeriados.setShowingTrailingDays(true);
        jXFeriados.setTraversable(true);

        jPanel27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoFecharG.setMnemonic('F');
        botaoFecharG.setText("Fechar");
        botaoFecharG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharGActionPerformed(evt);
            }
        });

        botaoSalvarG.setMnemonic('F');
        botaoSalvarG.setText("Salvar");
        botaoSalvarG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarGActionPerformed(evt);
            }
        });

        botaoLimpar.setMnemonic('F');
        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoSalvarG, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFecharG, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoSalvarG, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFecharG, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXFeriados, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXFeriados, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
                .addGap(34, 34, 34)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharGActionPerformed
        setVisible(false);
}//GEN-LAST:event_botaoFecharGActionPerformed

    private void botaoSalvarGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarGActionPerformed

        try {

            Collection<FeriadoModel> collFeriadoNew = new ArrayList<FeriadoModel>();

            for (Date data : jXFeriados.getSelection()) {
                if (data != null) {
                    FeriadoModel feriado = new FeriadoModel();
                    feriado.setId(PempecKeyGenerator.generateKey());
                    feriado.setData(data);
                    collFeriadoNew.add(feriado);
                }
            }

            if (collFeriadoNew != null && !collFeriadoNew.isEmpty()) {

                ConstantesModel constantesModel = new ConstantesModel();

                constantesModel.setId(Constantes.CONSTANTES_FERIADO_CALENDARIO_MODIFICADO);

                constantesModel = constantesBO.consultarPorPk(constantesModel);

                constantesModel.setCodigo("1");

                constantesBO.alterar(constantesModel);

                feriadoBO.inserir(collFeriadoNew);

                collFeriados.addAll(collFeriadoNew);

                for (FeriadoModel feriado : collFeriadoNew) {
                    jXFeriados.addFlaggedDates(feriado.getData());
                }

                exibeMensagemAviso("Feriado(s) cadastrados com sucesso!", null);

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

    }//GEN-LAST:event_botaoSalvarGActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        jXFeriados.clearSelection();
}//GEN-LAST:event_botaoLimparActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFecharG;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoSalvarG;
    private javax.swing.JPanel jPanel27;
    private org.jdesktop.swingx.JXMonthView jXFeriados;
    // End of variables declaration//GEN-END:variables
}
