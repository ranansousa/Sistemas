package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.LotePagamentoTituloBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.LotePagamentoTituloModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author Equipe Pempec
 */
public class ExcluirLotePagamento extends FinanceInternalFrame implements IRepopulador {

    private LotePagamentoTituloBO lotePagamentoTituloBO = new LotePagamentoTituloBO();
    private long tela = Tela.TELA_CONTAS_A_PAGAR_CANCELAMENTO_DE_LOTE.getTela();
    private OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

    public ExcluirLotePagamento() throws SystemException {

        initComponents();

        campoLotePagamento.setVisible(false);


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoLotePagamento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboLotePagamento = new javax.swing.JComboBox();
        labelData = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        labelFormaPagamento = new javax.swing.JLabel();
        labelTipoOperacao = new javax.swing.JLabel();
        labelCheque = new javax.swing.JLabel();
        labelResponsavel = new javax.swing.JLabel();
        labelContabancaria = new javax.swing.JLabel();
        labelSituacao = new javax.swing.JLabel();
        labelValorTotal = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Remove Exportação");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        campoLotePagamento.setEditable(false);

        jLabel1.setText("Número do Lote");

        comboLotePagamento.setToolTipText("");
        comboLotePagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLotePagamentoActionPerformed(evt);
            }
        });

        labelData.setFont(new java.awt.Font("Arial", 0, 10));
        labelData.setForeground(new java.awt.Color(0, 102, 102));
        labelData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelData.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Data Pagamento"));

        labelUsuario.setFont(new java.awt.Font("Arial", 0, 10));
        labelUsuario.setForeground(new java.awt.Color(0, 102, 102));
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Usuário"));

        labelTotal.setForeground(new java.awt.Color(51, 51, 255));
        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Total Documentos"));

        labelFormaPagamento.setFont(new java.awt.Font("Arial", 0, 10));
        labelFormaPagamento.setForeground(new java.awt.Color(102, 0, 102));
        labelFormaPagamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelFormaPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Forma de Pagamento"));

        labelTipoOperacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelTipoOperacao.setForeground(new java.awt.Color(102, 0, 102));
        labelTipoOperacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTipoOperacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Tipo Operação Bancária"));

        labelCheque.setFont(new java.awt.Font("Arial", 0, 10));
        labelCheque.setForeground(new java.awt.Color(102, 0, 102));
        labelCheque.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Cheque"));

        labelResponsavel.setFont(new java.awt.Font("Arial", 0, 10));
        labelResponsavel.setForeground(new java.awt.Color(102, 0, 102));
        labelResponsavel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelResponsavel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Responsável"));

        labelContabancaria.setFont(new java.awt.Font("Arial", 0, 10));
        labelContabancaria.setForeground(new java.awt.Color(102, 0, 102));
        labelContabancaria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelContabancaria.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Conta Bancária"));

        labelSituacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelSituacao.setForeground(new java.awt.Color(0, 102, 102));
        labelSituacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSituacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Situação"));

        labelValorTotal.setForeground(new java.awt.Color(51, 51, 255));
        labelValorTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelValorTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Valor Total"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboLotePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoLotePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelTipoOperacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
                        .addComponent(labelFormaPagamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(labelContabancaria, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(labelCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(50, 50, 50))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboLotePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoLotePagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelTipoOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelContabancaria, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCheque, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoFechar.setMnemonic('F');
        botaoFechar.setText("Fechar");
        botaoFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFecharActionPerformed(evt);
            }
        });

        botaoLimpar.setMnemonic('L');
        botaoLimpar.setText("Limpar");
        botaoLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoLimparActionPerformed(evt);
            }
        });

        botaoRemover.setMnemonic('L');
        botaoRemover.setText("Remover");
        botaoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(botaoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(228, 228, 228)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Remove Lote de Pagamento";
    }

    public void repopularCombos() {
        try {

            Collection<LotePagamentoTituloModel> lLote = new ArrayList<LotePagamentoTituloModel>();

            LotePagamentoTituloModel lotePagamentoTituloModel = new LotePagamentoTituloModel();

            lotePagamentoTituloModel.setLote(" -> Selecione <- ");

            lLote.add(lotePagamentoTituloModel);

            lLote.addAll(lotePagamentoTituloBO.obterTodos(organizacaoModel));

            comboLotePagamento.setModel(new javax.swing.DefaultComboBoxModel(lLote.toArray()));

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

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        comboLotePagamento.setSelectedIndex(0);
        campoLotePagamento.setText("");
        labelData.setText("");
        labelUsuario.setText("");
        labelFormaPagamento.setText("");
        labelResponsavel.setText("");
        labelCheque.setText("");
        labelTipoOperacao.setText("");
        labelTotal.setText("");
        labelSituacao.setText("");
        labelContabancaria.setText("");
        labelValorTotal.setText("");
        botaoRemover.setEnabled(false);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (comboLotePagamento.getSelectedIndex() == 0) {
            comboLotePagamento.requestFocus();
            return false;
        }

        return true;
    }

    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed

        try {

            long action = Action.OUTROS.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (comboLotePagamento.getSelectedIndex() != 0) {

                if (Controller.verificaParametroAtivo(Parametro.CCANP003.getCodigo())) {
                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANP003.getCodigo()), null);
                    return;
                }

                LotePagamentoTituloModel lotePagamentoTituloModel = new LotePagamentoTituloModel();

                lotePagamentoTituloModel.setPk(new PKModel());
                lotePagamentoTituloModel.getPk().setOrganizacao(organizacaoModel);
                lotePagamentoTituloModel.getPk().setId(campoLotePagamento.getText());
                lotePagamentoTituloModel.setLote(comboLotePagamento.getSelectedItem().toString());

                lotePagamentoTituloModel = lotePagamentoTituloBO.consultarPorTemplate(lotePagamentoTituloModel);

                if (Controller.verificaParametroAtivo(Parametro.CCANP008.getCodigo())) {
                    if (lotePagamentoTituloModel.getValor().doubleValue() > Controller.findByCodigo(Parametro.CCANP008.getCodigo()).getValor().doubleValue()) {
                        exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANP008.getCodigo()), null);
                        return;
                    }
                }

                if (lotePagamentoTituloBO.validaExclusao(lotePagamentoTituloModel)) {

                    lotePagamentoTituloBO.excluir(lotePagamentoTituloModel);

                    exibeMensagemAviso("Lote: " + lotePagamentoTituloModel.getLote() + " removido com Sucesso!", null);

                    this.botaoLimparActionPerformed(evt);

                }

            } else {

                exibeMensagemAviso("Selecione o Lote!", null);
                return;
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
}//GEN-LAST:event_botaoRemoverActionPerformed

    private void comboLotePagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLotePagamentoActionPerformed

        if (comboLotePagamento.getSelectedIndex() != 0) {

            try {

                LotePagamentoTituloModel lotePagamentoTituloModel = new LotePagamentoTituloModel();
                lotePagamentoTituloModel.setPk(new PKModel());
                lotePagamentoTituloModel.getPk().setOrganizacao(organizacaoModel);
                lotePagamentoTituloModel.setLote(comboLotePagamento.getSelectedItem().toString());

                lotePagamentoTituloModel = lotePagamentoTituloBO.consultarPorTemplate(lotePagamentoTituloModel);

                if (lotePagamentoTituloModel != null && lotePagamentoTituloModel.getPk() != null && lotePagamentoTituloModel.getPk().getId() != null) {

                    if (lotePagamentoTituloModel.getStatus().equals(Constantes.LOTE_PAGAMENTO_REMOVIDO)) {
                        botaoRemover.setEnabled(false);
                    } else {
                        botaoRemover.setEnabled(true);
                    }

                    labelTotal.setText(String.valueOf(lotePagamentoTituloModel.getQtdTituloPagar()));

                    if (lotePagamentoTituloModel.getUsuario() != null) {
                        labelUsuario.setText(lotePagamentoTituloModel.getUsuario().getNome());
                    }

                    campoLotePagamento.setText(lotePagamentoTituloModel.getPk().getId());
                    labelData.setText(PempecParse.dateToString(lotePagamentoTituloModel.getDataPagamento()));
                    labelSituacao.setText(lotePagamentoTituloModel.getStatus());

                    if (lotePagamentoTituloModel.getContaBancariaModel() != null) {
                        labelContabancaria.setText(lotePagamentoTituloModel.getContaBancariaModel().getConta());
                    }

                    if (lotePagamentoTituloModel.getTipoOperacaoBancariaModel() != null) {
                        labelTipoOperacao.setText(lotePagamentoTituloModel.getTipoOperacaoBancariaModel().getDescricao());
                    }

                    if (lotePagamentoTituloModel.getFormaPagamentoModel() != null) {
                        labelFormaPagamento.setText(lotePagamentoTituloModel.getFormaPagamentoModel().getDescricao());
                    }

                    if (lotePagamentoTituloModel.getResponsavel() != null) {
                        labelResponsavel.setText(lotePagamentoTituloModel.getResponsavel().getNome());
                    }

                    if (lotePagamentoTituloModel.getCheque() != null) {
                        labelCheque.setText(lotePagamentoTituloModel.getCheque().getNumeroCheque());
                    }

                    labelValorTotal.setText(PempecParse.doubleToZero(lotePagamentoTituloModel.getValor()));

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


        }

}//GEN-LAST:event_comboLotePagamentoActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    protected javax.swing.JButton botaoRemover;
    private javax.swing.JTextField campoLotePagamento;
    private javax.swing.JComboBox comboLotePagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelCheque;
    private javax.swing.JLabel labelContabancaria;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelFormaPagamento;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelSituacao;
    private javax.swing.JLabel labelTipoOperacao;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelValorTotal;
    // End of variables declaration//GEN-END:variables
}
