/*
 * CadastroCurso.java
 *
 * Created on 8 de Outubro de 2007, 21:58
 */
package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.PeriodicidadeModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.TituloPagarRateioHistoricoModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.SwingUtilities;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author PEMPEC
 */
public class CadastroTituloPagarParcelas extends FinanceInternalFrame implements IRepopulador {

    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private TituloPagarModel tituloPagarModel;

    public CadastroTituloPagarParcelas(TituloPagarModel titulo) throws SystemException {

        this.tituloPagarModel = titulo;

        initComponents();

        //Periodicidade
        Collection<PeriodicidadeModel> lPeriodicidade = new ArrayList<PeriodicidadeModel>();

        lPeriodicidade.add(new PeriodicidadeModel(1, "DIARIA"));
        lPeriodicidade.add(new PeriodicidadeModel(2, "SEMANAL"));
        lPeriodicidade.add(new PeriodicidadeModel(3, "QUINZENAL"));
        lPeriodicidade.add(new PeriodicidadeModel(4, "MENSAL"));
        lPeriodicidade.add(new PeriodicidadeModel(5, "SEMESTRAL"));
        lPeriodicidade.add(new PeriodicidadeModel(6, "ANUAL"));

        comboPeriodicidade.setModel(new javax.swing.DefaultComboBoxModel(lPeriodicidade.toArray()));

    }

    public void repopularCombos() {
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelPeriodicidade = new javax.swing.JLabel();
        comboPeriodicidade = new javax.swing.JComboBox();
        labelCodigoBanco = new javax.swing.JLabel();
        jTQtdParcelas = new br.com.pempec.componentes.JIntegerField();
        jTQtdTotalCarne = new br.com.pempec.componentes.JIntegerField();
        labelCodigoBanco1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoConfirmar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro Básico");
        setMinimumSize(new java.awt.Dimension(375, 320));
        setPreferredSize(new java.awt.Dimension(375, 320));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Informações Complementares"));
        jPanel1.setMinimumSize(new java.awt.Dimension(300, 160));
        jPanel1.setPreferredSize(new java.awt.Dimension(300, 160));

        labelPeriodicidade.setText("Periodicidade");

        labelCodigoBanco.setText("Qtd. Parcelas");

        jTQtdParcelas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTQtdParcelasFocusLost(evt);
            }
        });

        jTQtdTotalCarne.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelCodigoBanco1.setText("Parcelas do Carnê");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPeriodicidade, 0, 117, Short.MAX_VALUE)
                    .addComponent(labelCodigoBanco1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTQtdTotalCarne, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTQtdParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTQtdParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboPeriodicidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTQtdTotalCarne, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCodigoBanco1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoConfirmar.setMnemonic('I');
        botaoConfirmar.setText("Confirmar");
        botaoConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmarActionPerformed(evt);
            }
        });

        botaoLimpar.setMnemonic('L');
        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(botaoConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        botaoConfirmar.setEnabled(true);
        comboPeriodicidade.setSelectedIndex(0);
        jTQtdParcelas.setText("");
        jTQtdTotalCarne.setText("");
    }//GEN-LAST:event_botaoLimparActionPerformed

    /**
     * Validação dos campos
     *
     * @return
     */
    private Boolean validaCampos() {

        if (jTQtdTotalCarne.getText().isEmpty()) {
            jTQtdTotalCarne.requestFocus();
            return false;
        }

        if (jTQtdParcelas.getText().isEmpty()) {
            jTQtdParcelas.requestFocus();
            return false;
        }

        if (PempecParse.stringToInteger(jTQtdParcelas.getText()) <= 0) {
            exibeMensagemAviso("A Quantidade de Parcela não pode ser 0 (zero)!", null);
            return false;
        }

        if (PempecParse.stringToInteger(jTQtdTotalCarne.getText()) < 2) {
            exibeMensagemAviso("A Quantidade de Parcela do carnê está incorreta!", null);
            return false;
        }

        return true;

    }

    private void botaoConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmarActionPerformed

        Integer parcelaInicial = 0; //recebe a primeira parcela
        if (validaCampos()) {

            try {

                //A primeira parcela já vem la da tela de titulo a pagar.
                Collection<TituloPagarModel> titulos = new ArrayList<TituloPagarModel>();

                Integer qtdParcelas = PempecParse.stringToInteger(jTQtdParcelas.getText());

                //tituloPagarModel.setParcela("1");
                
                String numeroMudado = tituloPagarModel.getNumeroDocumento() + "0" + tituloPagarModel.getParcela();

                if (qtdParcelas > 1) {

                    parcelaInicial = Integer.parseInt(tituloPagarModel.getParcela());

                    if (tituloPagarModel.getParcela().equalsIgnoreCase("1")) {

                        tituloPagarModel.setParcela(parcelaInicial + " / " + jTQtdTotalCarne.getText());

                    } else {

                        tituloPagarModel.setParcela(parcelaInicial + " / " + jTQtdTotalCarne.getText());

                    }

                    parcelaInicial += 1;

                }

                tituloPagarModel.setNumeroDocumento(numeroMudado);

                tituloPagarModel.setMovimentoDiarioModel(registroMovimento("Inserir", tituloPagarModel.getNumeroDocumento(), "Titulo " + tituloPagarModel.getNumeroDocumento() + " " + tituloPagarModel.getHistorico().getDescricao() + " " + tituloPagarModel.getDescricao() + " PARCELA  " + tituloPagarModel.getParcela(), tituloPagarModel.getValorNominal(), "Inserido"));

                titulos.add(tituloPagarModel);

                TituloPagarModel clone = null;

                int i = 2;

                try {

                    clone = (TituloPagarModel) BeanUtils.cloneBean(tituloPagarModel);

                } catch (final Exception ex) {

                    final File file = PrintScreen.capture();

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {

                            tratamentoExcecao(new SystemException(ex), file);

                        }
                    });

                }

                List numerosValidos = new ArrayList();

                int indiceArray = 0;

                numerosValidos = PempecKeyGenerator.generateNumeroDocumentoTituloPagar(PempecParse.stringToLong(tituloPagarModel.getNumeroDocumento()), qtdParcelas);

                while (i <= qtdParcelas) {

                    try {

                        clone.setPk(new PKModel());

                        clone.getPk().setOrganizacao(tituloPagarModel.getPk().getOrganizacao());

                        clone.setParcela(parcelaInicial + " / " + jTQtdTotalCarne.getText());

                        //  clone.setParcela(i + " / " + qtdParcelas);
                        //                  if (clone.isBotaoGerar()) {
                        //                        clone.setNumeroDocumento(PempecKeyGenerator.generateNumeroDocumentoTituloPagar());
                        //                      } else {
                        clone.setNumeroDocumento(numerosValidos.get(indiceArray).toString());

                        indiceArray++;

//                        }
                        clone.getPk().setId(PempecKeyGenerator.generateKey());

                        

                        if (tituloPagarModel.getCollCentroCustosRateio() != null && !tituloPagarModel.getCollCentroCustosRateio().isEmpty()) {

                            Set<TituloPagarRateioCCModel> rateioCCModels = new HashSet<TituloPagarRateioCCModel>();

                            for (TituloPagarRateioCCModel tituloPagarRateioCCModel : tituloPagarModel.getCollCentroCustosRateio()) {

                                TituloPagarRateioCCModel cModel = new TituloPagarRateioCCModel();

                                cModel.setPk(new PKModel());

                                cModel.getPk().setId(PempecKeyGenerator.generateKey());

                                cModel.getPk().setOrganizacao(organizacaoModel);

                                cModel.setCentroCustoModel(tituloPagarRateioCCModel.getCentroCustoModel());

                                cModel.setTituloPagarModel(clone);

                                cModel.setValor(tituloPagarRateioCCModel.getValor());

                                rateioCCModels.add(cModel);

                            }

                            clone.setCollCentroCustosRateio(rateioCCModels);

                        }

                        if (tituloPagarModel.getCollHistoricosRateio() != null && !tituloPagarModel.getCollHistoricosRateio().isEmpty()) {

                            Set<TituloPagarRateioHistoricoModel> rateioHistoricoModels = new HashSet<TituloPagarRateioHistoricoModel>();

                            for (TituloPagarRateioHistoricoModel tituloPagarRateioHistoricoModel : tituloPagarModel.getCollHistoricosRateio()) {

                                TituloPagarRateioHistoricoModel cModel = new TituloPagarRateioHistoricoModel();

                                cModel.setPk(new PKModel());

                                cModel.getPk().setId(PempecKeyGenerator.generateKey());

                                cModel.getPk().setOrganizacao(organizacaoModel);

                                cModel.setHistoricoModel(tituloPagarRateioHistoricoModel.getHistoricoModel());

                                cModel.setTituloPagarModel(clone);

                                cModel.setValor(tituloPagarRateioHistoricoModel.getValor());

                                rateioHistoricoModels.add(cModel);

                            }

                            clone.setCollHistoricosRateio(rateioHistoricoModels);

                        }

                        clone.setCodigoBarras(null);

                        //a nota ja foi setada para o titulo principal
                        clone.setNotaFiscal(null);

                        HistoricoModel historicoModel = new HistoricoModel();
                        historicoModel.setPk(new PKModel());
                        historicoModel.getPk().setOrganizacao(Controller.getOrganizacao());
                        historicoModel.getPk().setId(clone.getHistorico().getPk().getId());

                        historicoModel = new HistoricoBO().consultarPorPk(historicoModel);

                        if (historicoModel.getPk().getId() != null) {
                            clone.setHistorico(historicoModel);
                        }
                        //o registro ja foi setado para o titulo principal
                        clone.setMovimentoDiarioModel(registroMovimento("Inserir", clone.getNumeroDocumento(), "Titulo " + clone.getNumeroDocumento() + " " + clone.getHistorico().getDescricao() + " " + clone.getDescricao() + " PARCELA  " + clone.getParcela(), clone.getValorNominal(), "Inserido"));

                        //Tratando a periodicidade.
                        switch (((PeriodicidadeModel) comboPeriodicidade.getSelectedItem()).getId()) {

                            case 1:
                                clone.setDataVencimento(PempecUtil.addDayDate(clone.getDataVencimento(), 1));
                                clone.setPrevisaoCartorio(PempecUtil.addDayDate(clone.getPrevisaoCartorio(), 1));
                                break;
                            case 2:
                                clone.setDataVencimento(PempecUtil.addDayDate(clone.getDataVencimento(), 8));
                                clone.setPrevisaoCartorio(PempecUtil.addDayDate(clone.getPrevisaoCartorio(), 8));
                                break;
                            case 3:
                                clone.setDataVencimento(PempecUtil.addDayDate(clone.getDataVencimento(), 15));
                                clone.setPrevisaoCartorio(PempecUtil.addDayDate(clone.getPrevisaoCartorio(), 15));
                                break;
                            case 4:
                                clone.setDataVencimento(PempecUtil.addMonthDate(clone.getDataVencimento(), 1));
                                clone.setPrevisaoCartorio(PempecUtil.addMonthDate(clone.getPrevisaoCartorio(), 1));
                                break;
                            case 5:
                                clone.setDataVencimento(PempecUtil.addMonthDate(clone.getDataVencimento(), 6));
                                clone.setPrevisaoCartorio(PempecUtil.addMonthDate(clone.getPrevisaoCartorio(), 6));
                                break;
                            case 6:
                                clone.setDataVencimento(PempecUtil.addYearDate(clone.getDataVencimento(), 1));
                                clone.setPrevisaoCartorio(PempecUtil.addYearDate(clone.getPrevisaoCartorio(), 1));
                                break;
                        }

                        titulos.add(clone);

                        clone = (TituloPagarModel) BeanUtils.cloneBean(clone);

                    } catch (final Exception ex) {

                        final File file = PrintScreen.capture();

                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {

                                tratamentoExcecao(new SystemException(ex), file);

                            }
                        });

                    }

                    i++;
                    parcelaInicial++;

                }

                tituloPagarBO.inserir(titulos);

                this.botaoLimparActionPerformed(evt);

                this.dispose();

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

        } else {
            exibeMensagemAviso("Campo(s) obrigatório(s).", null);
        }

}//GEN-LAST:event_botaoConfirmarActionPerformed

    private void jTQtdParcelasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTQtdParcelasFocusLost
        if (jTQtdParcelas.getText() != null) {
            Integer parcelaTitulo = Integer.parseInt(tituloPagarModel.getParcela());
            Integer totalCarne = Integer.parseInt(jTQtdParcelas.getText()) + (parcelaTitulo - 1);

            jTQtdTotalCarne.setText(totalCarne.toString());

        }
    }//GEN-LAST:event_jTQtdParcelasFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoConfirmar;
    private javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboPeriodicidade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private br.com.pempec.componentes.JIntegerField jTQtdParcelas;
    private br.com.pempec.componentes.JIntegerField jTQtdTotalCarne;
    private javax.swing.JLabel labelCodigoBanco;
    private javax.swing.JLabel labelCodigoBanco1;
    private javax.swing.JLabel labelPeriodicidade;
    // End of variables declaration//GEN-END:variables
}
