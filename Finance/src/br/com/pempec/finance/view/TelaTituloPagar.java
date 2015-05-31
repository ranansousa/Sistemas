package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.CedenteTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 * * @author PEMPEC
 */
public class TelaTituloPagar extends FinanceInternalFrame implements IRepopulador {

    private long tela = Tela.TELA_CONTAS_A_PAGAR_BAIXA_EM_LOTE.getTela();
    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private CedenteBO cedenteBO = new CedenteBO();
//Ordem padrão
    String[] ordem = new String[4];
    String ordem1 = Constantes.CONSTANTES_ORDEM_TITULOS_1;
    String ordem2 = Constantes.CONSTANTES_ORDEM_TITULOS_2;
    String ordem3 = Constantes.CONSTANTES_ORDEM_TITULOS_3;
    String ordem4 = Constantes.CONSTANTES_ORDEM_TITULOS_4;

    public TelaTituloPagar() throws SystemException {

        initComponents();

        jFTPeriodoFinal.setDate(Controller.getDataServidorBD());
        jFTPeriodoInicial.setDate(Controller.getDataServidorBD());

        comboOrdem2.setEnabled(false);
        comboOrdem3.setEnabled(false);
        comboOrdem4.setEnabled(false);



    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jComboBox3 = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        jFTPeriodoInicial = new org.jdesktop.swingx.JXDatePicker();
        jFTPeriodoFinal = new org.jdesktop.swingx.JXDatePicker();
        jPanel6 = new javax.swing.JPanel();
        comboOrdem1 = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        comboOrdem2 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        comboOrdem3 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        comboOrdem4 = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        comboCedente = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Valor", "Cedente", "Responsável", "Data Vcto" }));

        jLabel3.setText("Primeira");

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Listagem Titulos Pagar");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Período"));

        label2.setText("à");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label2)
                .addGap(18, 18, 18)
                .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Ordem"));

        comboOrdem1.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        comboOrdem1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboOrdem1ItemStateChanged(evt);
            }
        });

        jLabel1.setText("Primeira");

        comboOrdem2.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        comboOrdem2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboOrdem2ItemStateChanged(evt);
            }
        });

        jLabel2.setText("Segunda");

        comboOrdem3.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        comboOrdem3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboOrdem3ItemStateChanged(evt);
            }
        });

        jLabel4.setText("Terceira");

        comboOrdem4.setFont(new java.awt.Font("Arial", 1, 10)); // NOI18N
        comboOrdem4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboOrdem4ItemStateChanged(evt);
            }
        });

        jLabel5.setText("Quarta");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboOrdem1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboOrdem2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboOrdem3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboOrdem4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboOrdem4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboOrdem3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboOrdem2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboOrdem1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Cedente"));

        comboCedente.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        comboCedente.setToolTipText("");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(comboCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel5Layout.createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(comboCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoGerar.setMnemonic('I');
        botaoGerar.setText("Gerar");
        botaoGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarActionPerformed(evt);
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
                .addGap(77, 77, 77)
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoGerar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Listagem Títulos";
    }

    public void repopularCombos() {

        try {

            Collection<CedenteModel> lCedente = cedenteBO.obterTodos(organizacaoModel);

            final EventList<CedenteModel> lRegistrosCedentes = GlazedLists.eventList(lCedente);
            if (supportCedente != null && supportCedente.getItemList() != null && supportCedente.getComboBox() != null) {
                supportCedente.uninstall();
            }
            supportCedente = AutoCompleteSupport.install(comboCedente, lRegistrosCedentes, new CedenteTextFilterator());
            supportCedente.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportCedente.setStrict(false);


            comboOrdem1.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" Selecione",
                ordem1, ordem2, ordem3, ordem4
            }));
            comboOrdem2.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" Selecione",
                ordem1, ordem2, ordem3, ordem4
            }));
            comboOrdem3.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" Selecione",
                ordem1, ordem2, ordem3, ordem4
            }));
            comboOrdem4.setModel(new javax.swing.DefaultComboBoxModel(new String[]{" Selecione",
                ordem1, ordem2, ordem3, ordem4
            }));

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
        jFTPeriodoFinal.setDate(Controller.getDataServidorBD());
        jFTPeriodoInicial.setDate(Controller.getDataServidorBD());
        comboCedente.setSelectedItem(null);

        comboOrdem1.setSelectedIndex(0);
        comboOrdem2.setSelectedIndex(0);
        comboOrdem3.setSelectedIndex(0);
        comboOrdem4.setSelectedIndex(0);

        comboOrdem2.setEnabled(false);
        comboOrdem3.setEnabled(false);
        comboOrdem4.setEnabled(false);

        comboOrdem1.setForeground(Color.BLACK);
        comboOrdem2.setForeground(Color.BLACK);
        comboOrdem3.setForeground(Color.BLACK);
        comboOrdem4.setForeground(Color.BLACK);

        comboOrdem1.setBackground(Color.WHITE);
        comboOrdem2.setBackground(Color.white);
        comboOrdem3.setBackground(Color.white);
        comboOrdem4.setBackground(Color.white);


    }//GEN-LAST:event_botaoLimparActionPerformed

    @SuppressWarnings("deprecation")
    private Boolean validaCampos() {

        if (jFTPeriodoInicial.getDate() == null) {
            jFTPeriodoInicial.requestFocus();
            return false;
        }

        if (jFTPeriodoFinal.getDate() == null) {
            jFTPeriodoFinal.requestFocus();
            return false;
        }

        if (jFTPeriodoInicial.getDate().after(jFTPeriodoFinal.getDate())) {
            exibeMensagemAviso("Data Inicial não pode ser superior a data final", null);
            jFTPeriodoInicial.requestFocus();
            return false;
        }

        if (comboOrdem1 != null && comboOrdem1.getSelectedIndex() == 0) {
            exibeMensagemAviso("Defina, ao menos, a  primeira ordem! ", null);
            comboOrdem1.requestFocus();
            return false;
        }




        return true;
    }

    private void botaoGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarActionPerformed

        if (validaCampos()) {

            try {

                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                Collection<TituloPagarModel> collTitulos = new ArrayList<TituloPagarModel>();

                TituloPagarModel model = new TituloPagarModel();

                model.setPk(new PKModel());

                model.getPk().setOrganizacao(organizacaoModel);

                CedenteModel cedente = new CedenteModel();

                if (this.validaPreenchimentoCombo(comboCedente, true)) {
                    model.setCedente((CedenteModel) comboCedente.getSelectedItem());
                    cedente = model.getCedente();

                }


                //aqui pega a ordem que da tela


                if (comboOrdem2 != null && comboOrdem2.getSelectedIndex() == 0) {
                    if (comboOrdem3 != null && comboOrdem3.getSelectedIndex() == 0) {
                        if (comboOrdem4 != null && comboOrdem4.getSelectedIndex() == 0) {
                            ordem[1] = "cedente";
                            ordem[2] = "responsavel";
                            ordem[3] = "dataVencimento";
                        }
                    }
                }


                collTitulos = tituloPagarBO.obterTodosPorPeriodo(model, jFTPeriodoInicial.getDate(), jFTPeriodoFinal.getDate(), ordem);

                if (collTitulos.isEmpty()) {
                    exibeMensagemAviso("Não foram encontrados títulos para o período informado.", null);
                    return;
                }


                ListagemTitulosPagar listagem = new ListagemTitulosPagar(collTitulos, cedente);


                TelaPrincipal.desktopPane.add(listagem);
                listagem.show();

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

            exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);
        }

}//GEN-LAST:event_botaoGerarActionPerformed

    private void comboOrdem1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboOrdem1ItemStateChanged

        //array de string (ordem) de 4 posicoes
        //o comboordem1 representa a posicao 0 desse array
        if (comboOrdem1 != null && comboOrdem1.getSelectedIndex() > 0) {

            if (comboOrdem1.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_1)) {
                ordem[0] = "valorNominal";
            }

            if (comboOrdem1.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_2)) {
                ordem[0] = "cedente";
            }

            if (comboOrdem1.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_3)) {
                ordem[0] = "responsavel";
            }

            if (comboOrdem1.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_4)) {
                ordem[0] = "dataVencimento";
            }

            comboOrdem1.setForeground(Color.blue);

        }


        comboOrdem2.setEnabled(true);

    }//GEN-LAST:event_comboOrdem1ItemStateChanged

    private void comboOrdem2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboOrdem2ItemStateChanged
        // TODO add your handling code here:


        //array de string (ordem) de 4 posicoes
        //o comboordem1 representa a posicao 0 desse array
        if (comboOrdem2 != null && comboOrdem2.getSelectedIndex() > 0) {

            if (comboOrdem2.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_1)) {
                ordem[1] = "valorNominal";
            }

            if (comboOrdem2.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_2)) {
                ordem[1] = "cedente";
            }

            if (comboOrdem2.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_3)) {
                ordem[1] = "responsavel";
            }

            if (comboOrdem2.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_4)) {
                ordem[1] = "dataVencimento";
            }
            comboOrdem2.setForeground(Color.CYAN);
            comboOrdem3.setEnabled(true);

        } else {

            comboOrdem2.setSelectedIndex(1);

        }
    }//GEN-LAST:event_comboOrdem2ItemStateChanged

    private void comboOrdem3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboOrdem3ItemStateChanged


        //array de string (ordem) de 4 posicoes
        //o comboordem1 representa a posicao 0 desse array
        if (comboOrdem3 != null && comboOrdem3.getSelectedIndex() > 0) {

            if (comboOrdem3.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_1)) {
                ordem[2] = "valorNominal";
            }

            if (comboOrdem3.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_2)) {
                ordem[2] = "cedente";
            }

            if (comboOrdem3.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_3)) {
                ordem[2] = "responsavel";
            }

            if (comboOrdem3.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_4)) {
                ordem[2] = "dataVencimento";
            }
            comboOrdem3.setForeground(Color.GRAY);
            comboOrdem4.setEnabled(true);

        } else {

            comboOrdem3.setSelectedIndex(1);

        }      // TODO a// TODO add your handling code here:
    }//GEN-LAST:event_comboOrdem3ItemStateChanged

    private void comboOrdem4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboOrdem4ItemStateChanged

        //array de string (ordem) de 4 posicoes
        //o comboordem1 representa a posicao 0 desse array
        if (comboOrdem4 != null && comboOrdem4.getSelectedIndex() > 0) {

            if (comboOrdem4.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_1)) {
                ordem[3] = "valorNominal";
            }

            if (comboOrdem4.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_2)) {
                ordem[3] = "cedente";
            }

            if (comboOrdem4.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_3)) {
                ordem[3] = "responsavel";
            }

            if (comboOrdem4.getSelectedItem().equals(Constantes.CONSTANTES_ORDEM_TITULOS_4)) {
                ordem[3] = "dataVencimento";
            }

            comboOrdem4.setForeground(Color.GREEN);

        } else {

            comboOrdem3.setSelectedIndex(1);

        }
    }//GEN-LAST:event_comboOrdem4ItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboCedente;
    private javax.swing.JComboBox comboOrdem1;
    private javax.swing.JComboBox comboOrdem2;
    private javax.swing.JComboBox comboOrdem3;
    private javax.swing.JComboBox comboOrdem4;
    private javax.swing.JComboBox jComboBox3;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoFinal;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel label2;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportCedente;
}
