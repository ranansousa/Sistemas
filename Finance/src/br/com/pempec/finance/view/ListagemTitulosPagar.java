package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.tables.ColumnModelListagemTitulosPagar;
import br.com.pempec.finance.utils.tables.TableModelListagemTituloPagar;
import java.awt.FontMetrics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class ListagemTitulosPagar extends FinanceInternalFrame implements IRepopulador {

    //Variavel que ira guardar os titulos do periodo selecionado.
    private Collection<TituloPagarModel> lTitulosPeriodo;
    CedenteModel cedente = new CedenteModel();

    private String NameObject() {
        return (String) "Listagem de Títulos";
    }

    public void repopularCombos() {

        tableTitulos.setAutoCreateColumnsFromModel(false);
        tableTitulos.setModel(new TableModelListagemTituloPagar((ArrayList<TituloPagarModel>) lTitulosPeriodo));
        FontMetrics fm = tableTitulos.getFontMetrics(tableTitulos.getFont());
        tableTitulos.setColumnModel(new ColumnModelListagemTitulosPagar(fm));
        tableTitulos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableTitulos.getTableHeader().setReorderingAllowed(false);

    }

    public ListagemTitulosPagar(Collection<TituloPagarModel> collTitulos, CedenteModel cedenteModel) throws SystemException {

        initComponents();

        lTitulosPeriodo = collTitulos;
        cedente = cedenteModel;
        tableTitulos.setEnabled(true);
        jCSelTodos.setEnabled(true);

        Double totalPagar = 0d;

        for (TituloPagarModel tituloPagarModel : collTitulos) {
            totalPagar += tituloPagarModel.getValorNominal();
        }

        jFTValorTotal.setText(PempecParse.doubleToZero(totalPagar));
        jFTQtd.setText(String.valueOf(collTitulos.size()));

        this.repopularCombos();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoBaixar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaTitulos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTitulos = new javax.swing.JTable();
        jCSelTodos = new javax.swing.JCheckBox();
        jFTValorTotal = new br.com.pempec.componentes.JDoubleField();
        labelValorPagar1 = new javax.swing.JLabel();
        jFTQtd = new javax.swing.JTextField();
        labelValorPagar2 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Pagar Lote");
        setPreferredSize(new java.awt.Dimension(990, 680));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoBaixar.setMnemonic('I');
        botaoBaixar.setText("Baixar");
        botaoBaixar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoBaixarActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoBaixar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoBaixar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        abaTitulos.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102))));

        tableTitulos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153)));
        tableTitulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5"
            }
        ));
        jScrollPane2.setViewportView(tableTitulos);

        jCSelTodos.setText("Selecionar Todos");
        jCSelTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCSelTodosActionPerformed(evt);
            }
        });

        jFTValorTotal.setEditable(false);

        labelValorPagar1.setText("Total a Pagar:");

        jFTQtd.setEditable(false);
        jFTQtd.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jFTQtd.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        labelValorPagar2.setText("Quantidade:");

        javax.swing.GroupLayout abaTitulosLayout = new javax.swing.GroupLayout(abaTitulos);
        abaTitulos.setLayout(abaTitulosLayout);
        abaTitulosLayout.setHorizontalGroup(
            abaTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaTitulosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1211, Short.MAX_VALUE)
                    .addGroup(abaTitulosLayout.createSequentialGroup()
                        .addComponent(jCSelTodos)
                        .addGap(40, 40, 40)
                        .addComponent(labelValorPagar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(labelValorPagar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        abaTitulosLayout.setVerticalGroup(
            abaTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaTitulosLayout.createSequentialGroup()
                .addGroup(abaTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaTitulosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelValorPagar1)
                        .addComponent(jFTValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelValorPagar2)
                        .addComponent(jFTQtd, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jCSelTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Baixar Títulos ", abaTitulos);
        abaTitulos.getAccessibleContext().setAccessibleName("abaCompensarCheque");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Estorno de Compensação");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        tableTitulos.setEnabled(true);
        jCSelTodos.setEnabled(true);
        jCSelTodos.setSelected(false);
        this.jCSelTodosActionPerformed(evt);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        boolean validaPreenchimento = false;

        for (int i = 0; i < tableTitulos.getRowCount(); i++) {
            if (tableTitulos.getValueAt(i, 0).toString().equals("true")) {
                validaPreenchimento = true;
                break;
            }
        }

        if (!validaPreenchimento) {
            exibeMensagemAviso("Selecione um Item!", null);
            return false;
        }

        return true;
    }

    private void botaoBaixarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoBaixarActionPerformed

        if (validaCampos()) {

            Collection<TituloPagarModel> coll = new ArrayList<TituloPagarModel>();

            for (TituloPagarModel tituloPagarModel : lTitulosPeriodo) {
                if (tituloPagarModel.getCheck()) {
                    coll.add(tituloPagarModel);
                }
            }

            try {

                botaoBaixar.setEnabled(false);
                tableTitulos.setEnabled(false);
                jCSelTodos.setEnabled(false);

                new MovimentoDiarioBO().inserir(this.registroMovimento("Listagem", "0", Controller.getUsuarioLogado().getNome() + " solicitou listagem de titulos a pagar  ", null, "Listado"));

                CadastroTituloPagarBaixaLote lote = new CadastroTituloPagarBaixaLote(coll, cedente);
                TelaPrincipal.desktopPane.add(lote);
                lote.show();

            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            } catch (ApplicationException ex) {

                tratamentoExcecao(ex);

            }
        } else {

            exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

        }

}//GEN-LAST:event_botaoBaixarActionPerformed

private void jCSelTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCSelTodosActionPerformed
    for (int i = 0; i < tableTitulos.getRowCount(); i++) {
        tableTitulos.setValueAt(jCSelTodos.isSelected(), i, 0);
    }
}//GEN-LAST:event_jCSelTodosActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaTitulos;
    private javax.swing.JButton botaoBaixar;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JCheckBox jCSelTodos;
    private javax.swing.JTextField jFTQtd;
    private br.com.pempec.componentes.JDoubleField jFTValorTotal;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelValorPagar1;
    private javax.swing.JLabel labelValorPagar2;
    private javax.swing.JTable tableTitulos;
    // End of variables declaration//GEN-END:variables
}
