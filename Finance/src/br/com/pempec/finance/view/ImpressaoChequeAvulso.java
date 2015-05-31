package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.FolhaCheque;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Extenso;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopuladorNew;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.ChequeTextFilterator;
import br.com.pempec.finance.utils.iterators.TituloPagarTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class ImpressaoChequeAvulso extends FinanceInternalFrame implements IRepopuladorNew {

    private ContaBancariaChequeBO contaBancariaChequeBO = new ContaBancariaChequeBO();
    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private long tela = Tela.TELA_CONCILIACAO_IMPRESSAO_DE_CHEQUES.getTela();
    private Collection<ContaBancariaModel> lContas;
    private Collection<TituloPagarModel> lColecaoTituloPagar;
    private EventList<TituloPagarModel> lRegistros;

    private String NameObject() {
        return (String) "Impressão de Cheque Avulso";
    }

    public void repopularCombos(Tela tela, Object object) {

        try {

            switch (tela) {

                case TELA_PARAMETROS_CONTAS_BANCARIAS:

                    ContaBancariaModel contaBancariaModel = (ContaBancariaModel) object;

                    if (contaBancariaModel != null) {

                        comboConta.addItem(contaBancariaModel);

                        for (int i = 1; i < comboConta.getItemCount(); i++) {
                            if (contaBancariaModel.getPk().getId().equalsIgnoreCase(((ContaBancariaModel) comboConta.getItemAt(i)).getPk().getId())) {
                                comboConta.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_CONTAS_A_PAGAR_CADASTRO:

                    TituloPagarModel tituloPagarModel = (TituloPagarModel) object;

                    if (tituloPagarModel != null) {

                        lRegistros.add(tituloPagarModel);

                        for (int i = 0; i < comboTitulo.getItemCount(); i++) {
                            if (tituloPagarModel.getPk().getId().equalsIgnoreCase(((TituloPagarModel) comboTitulo.getItemAt(i)).getPk().getId())) {
                                comboTitulo.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                default:

                    ContaBancariaModel contaBancaria = new ContaBancariaModel();

                    contaBancaria.setConta(" -> Selecione <- ");

                    lContas = new ArrayList<ContaBancariaModel>();

                    lContas.add(contaBancaria);

                    lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

                    comboConta.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));


                    TituloPagarModel filtro = new TituloPagarModel();
                    filtro.setPk(new PKModel());
                    filtro.getPk().setOrganizacao(organizacaoModel);
                    filtro.setProvisao(1);

                    lColecaoTituloPagar = tituloPagarBO.obterPorFiltro(filtro);

                    lRegistros = GlazedLists.eventList(lColecaoTituloPagar);
                    if (supportTitulo != null && supportTitulo.getItemList() != null && supportTitulo.getComboBox() != null) {
                        supportTitulo.uninstall();
                    }
                    supportTitulo = AutoCompleteSupport.install(comboTitulo, lRegistros, new TituloPagarTextFilterator());
                    supportTitulo.setFilterMode(TextMatcherEditor.STARTS_WITH);

                    break;

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

    public ImpressaoChequeAvulso() throws SystemException {
        this.setLocation(250, 20);
        Controller.setQtdMovimentosHoje(0);
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        botaoCancelar = new javax.swing.JButton();
        botaoConfirmar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        abaCompensarCheque1 = new javax.swing.JPanel();
        labelCheque1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelPortador = new javax.swing.JLabel();
        comboConta = new javax.swing.JComboBox();
        comboCheque = new javax.swing.JComboBox();
        labelDescricao = new javax.swing.JLabel();
        comboTitulo = new javax.swing.JComboBox();
        labelNumeroDocumento = new javax.swing.JLabel();
        labelValorPagar = new javax.swing.JLabel();
        jFTValorPagar = new br.com.pempec.componentes.JDoubleField();
        labelObs = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTObservacao = new org.jdesktop.swingx.JXEditorPane();
        labelDataEmissao = new javax.swing.JLabel();
        jFTDataEmissao = new org.jdesktop.swingx.JXDatePicker();
        labelDataVencimento = new javax.swing.JLabel();
        jFTDataPrevisaoCompensacao = new org.jdesktop.swingx.JXDatePicker();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Impressão de Cheque Avulso");

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

        botaoImprimir.setMnemonic('L');
        botaoImprimir.setText("Imprimir");
        botaoImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImprimirActionPerformed(evt);
            }
        });

        botaoCancelar.setMnemonic('L');
        botaoCancelar.setText("Cancelar");
        botaoCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCancelarActionPerformed(evt);
            }
        });

        botaoConfirmar.setMnemonic('F');
        botaoConfirmar.setText("Vincular");
        botaoConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoConfirmarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(51, 102, 255))); // NOI18N

        abaCompensarCheque1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 1, true));

        labelCheque1.setText("Cheque");

        jLabel11.setText("Conta Bancária");

        labelPortador.setBackground(new java.awt.Color(222, 218, 210));
        labelPortador.setFont(new java.awt.Font("Arial", 1, 10));
        labelPortador.setForeground(new java.awt.Color(0, 153, 153));
        labelPortador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Portador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        comboConta.setFont(new java.awt.Font("Arial", 0, 10));
        comboConta.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaItemStateChanged(evt);
            }
        });

        comboCheque.setFont(new java.awt.Font("Arial", 0, 10));
        comboCheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboChequeActionPerformed(evt);
            }
        });

        labelDescricao.setBackground(new java.awt.Color(222, 218, 210));
        labelDescricao.setFont(new java.awt.Font("Arial", 1, 10));
        labelDescricao.setForeground(new java.awt.Color(0, 153, 153));
        labelDescricao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descrição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        comboTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloActionPerformed(evt);
            }
        });

        labelNumeroDocumento.setText("Número do Documento");

        labelValorPagar.setText("Valor do Cheque");

        jFTValorPagar.setEditable(false);

        labelObs.setText("Observação");

        jTObservacao.setFont(new java.awt.Font("Arial", 0, 11));
        jScrollPane2.setViewportView(jTObservacao);

        labelDataEmissao.setText("Data Emissão");

        labelDataVencimento.setText("Previsão Compensação");

        javax.swing.GroupLayout abaCompensarCheque1Layout = new javax.swing.GroupLayout(abaCompensarCheque1);
        abaCompensarCheque1.setLayout(abaCompensarCheque1Layout);
        abaCompensarCheque1Layout.setHorizontalGroup(
            abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDescricao, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11)
                                    .addComponent(labelDataEmissao)
                                    .addComponent(jFTDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFTDataPrevisaoCompensacao, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelDataVencimento)
                                    .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCheque1))))
                        .addGap(10, 10, 10)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelNumeroDocumento)
                                    .addComponent(comboTitulo, 0, 254, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelValorPagar)
                                    .addComponent(jFTValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelObs)
                            .addComponent(jScrollPane2, 0, 0, Short.MAX_VALUE))))
                .addContainerGap())
        );
        abaCompensarCheque1Layout.setVerticalGroup(
            abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(labelCheque1)
                            .addComponent(labelNumeroDocumento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelValorPagar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addComponent(labelDataVencimento)
                        .addContainerGap())
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                .addComponent(labelObs)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jFTDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFTDataPrevisaoCompensacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(labelDataEmissao))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                                .addComponent(labelPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(labelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abaCompensarCheque1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(abaCompensarCheque1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        abaCompensarCheque1.getAccessibleContext().setAccessibleName("abaEstorno");

        jTabbedPane1.addTab("Cheque Avulso", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 787, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Estorno de Compensação");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    public void limparCamposCheque() {
        labelPortador.setText("");
        labelDescricao.setText("");
        jFTDataPrevisaoCompensacao.setDate(null);
        botaoConfirmar.setEnabled(true);
        botaoCancelar.setEnabled(false);
        botaoImprimir.setEnabled(false);
    }

    public void limparCamposTitulo() {
        comboTitulo.setEnabled(true);
        comboTitulo.setSelectedItem(null);
        jFTDataEmissao.setDate(null);
        jFTValorPagar.setText("0,00");
        jTObservacao.setText("");

    }

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        comboConta.setSelectedIndex(0);

        botaoImprimir.setEnabled(false);
        botaoCancelar.setEnabled(false);

        if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
            supportCheque.uninstall();
        }

        this.limparCamposCheque();
        this.limparCamposTitulo();

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (comboConta.getSelectedItem() == null) {
            comboConta.requestFocus();
            return false;
        }

        if (comboCheque.getSelectedItem() == null) {
            comboCheque.requestFocus();
            return false;
        }


        return true;
    }

private void comboContaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaItemStateChanged

    if (comboConta.getSelectedItem() != null && comboConta.getSelectedItem() instanceof ContaBancariaModel
            && ((ContaBancariaModel) comboConta.getSelectedItem()).getPk() != null) {

        this.limparCamposCheque();

        try {

            Collection<TipoStatusModel> collStatus = new ArrayList<TipoStatusModel>();

            TipoStatusModel status = new TipoStatusModel();
            status.setPk(new PKModel());
            status.getPk().setOrganizacao(organizacaoModel);
            status.getPk().setId(Constantes.STATUS_CHEQUE_NOVO);

            collStatus.add(status);

            status = new TipoStatusModel();
            status.setPk(new PKModel());
            status.getPk().setOrganizacao(organizacaoModel);
            status.getPk().setId(Constantes.STATUS_CHEQUE_AVULSO);

            collStatus.add(status);

            Collection<ContaBancariaChequeModel> lContaBancariaCheque = contaBancariaChequeBO.obterPorContaBancariaStatus((ContaBancariaModel) comboConta.getSelectedItem(), collStatus);

            final EventList<ContaBancariaChequeModel> lRegistrosCheques = GlazedLists.eventList(lContaBancariaCheque);
            if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
                supportCheque.uninstall();
            }

            supportCheque = AutoCompleteSupport.install(comboCheque, lRegistrosCheques, new ChequeTextFilterator());
            supportCheque.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportCheque.setStrict(false);

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

        this.limparCamposCheque();
        this.limparCamposTitulo();

        if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
            supportCheque.uninstall();
        }

    }
}//GEN-LAST:event_comboContaItemStateChanged

private void botaoImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirActionPerformed

    if (comboCheque.getSelectedItem() != null && comboCheque.getSelectedItem() instanceof ContaBancariaChequeModel
            && !comboCheque.getSelectedItem().toString().isEmpty()) {

        try {

            long action = Action.IMPRESSAO.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            if (validaCampos()) {

                ContaBancariaChequeModel cheque = (ContaBancariaChequeModel) comboCheque.getSelectedItem();

                cheque = contaBancariaChequeBO.consultarPorPk(cheque);


                if (cheque.getPk().getId() == null || cheque.getPk().getId().isEmpty()) {
                    exibeMensagemAviso("Problemas com a impressao do cheque", "Erro ao imprimir cheque");
                    return;
                }

                //evitando nulpoint
                if (cheque.getQtdImpressao() == null) {

                    cheque.setQtdImpressao(0);

                }


                Map<String, Object> parametros = new HashMap<String, Object>();

                parametros.put(RelatorioConstantes.PARAMETRO_ID_ORGANIZACAO, organizacaoModel.getId());

                Collection<FolhaCheque> collection = new ArrayList<FolhaCheque>();

                FolhaCheque bean = new FolhaCheque();

                if (Controller.getOrganizacao().getCidade() != null && !Controller.getOrganizacao().getCidade().getDescricao().toString().isEmpty()) {

                    bean.setCidade(Controller.getOrganizacao().getCidade().getDescricao());

                } else {

                    bean.setCidade(".");
                }



                String extenso = "( " + new Extenso(cheque.getValor()).toString();

                if (extenso.length() < 100) {

                    extenso += " ) ";

                    bean.setExtenso(extenso);

                } else {

                    char aux = extenso.charAt(92);

                    if (aux == ' ') {

                        bean.setExtenso(extenso.substring(0, 92));

                        bean.setExtenso2(extenso.substring(93) + " ) ");

                    } else {

                        int pos = extenso.lastIndexOf(" ", 92);

                        bean.setExtenso(extenso.substring(0, pos));

                        bean.setExtenso2(extenso.substring(pos + 1) + " ) ");

                    }

                }

                bean.setDia(PempecParse.dateToString(cheque.getDataEmissao()).substring(0, 2));
                bean.setMes(PempecUtil.getMesExtenso(cheque.getDataEmissao()));
                bean.setAno(PempecParse.dateToString(cheque.getDataEmissao()).substring(6, 10));
                bean.setPortador(cheque.getPortador());
                bean.setValor(cheque.getValor());

                collection.add(bean);

                if (cheque.getContaBancaria().getBanco() != null && cheque.getContaBancaria().getBanco().getNomeBanco() != null && !cheque.getContaBancaria().getBanco().getNomeBanco().isEmpty()) {

                    Integer codigoBanco = Integer.parseInt(cheque.getContaBancaria().getBanco().getCodigoBanco());

                    switch (codigoBanco) {

                        case 237:
                            new RelatorioUtil().imprimirImpressora(RelatorioConstantes.IMPRESSAO_CHEQUE_BRADESCO, parametros, collection);
                            break;

                        case 001:
                            new RelatorioUtil().imprimirImpressora(RelatorioConstantes.IMPRESSAO_CHEQUE_BRASIL, parametros, collection);
                            break;

                        case 104:
                            new RelatorioUtil().imprimirImpressora(RelatorioConstantes.IMPRESSAO_CHEQUE_CEF, parametros, collection);
                            break;

                        default:
                            new RelatorioUtil().imprimirImpressora(RelatorioConstantes.IMPRESSAO_CHEQUE, parametros, collection);
                            break;
                    }

                    //19/05/2012   new MovimentoDiarioBO().inserir(this.registroMovimento("Imp Che AVULSO", cheque.getNumeroCheque(), Controller.getUsuarioLogado().getNome() + " solicitou impressao do cheque : " + cheque.getNumeroCheque() + " da conta : " + cheque.getContaBancaria().getConta(), null, "Impresso CH AVULSO"));

                    Integer qtdImpressao = cheque.getQtdImpressao();

                    cheque.setQtdImpressao(qtdImpressao++);

                    contaBancariaChequeBO.alterar(cheque);



                } else {

                    exibeMensagemAviso("Problemas com o layout do cheque", "Layot de Impressão");
                }

            } else {

                exibeMensagemAviso("Campo(s) obrigatório(s).", null);

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

    } else {

        exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

    }

}//GEN-LAST:event_botaoImprimirActionPerformed

private void comboChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboChequeActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboCheque.getSelectedItem() != null && comboCheque.getSelectedItem() instanceof ContaBancariaChequeModel
            && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        this.limparCamposCheque();
        this.limparCamposTitulo();

        try {

            ContaBancariaChequeModel chequeModel = (ContaBancariaChequeModel) comboCheque.getSelectedItem();

            //Consultar se tem título vinculado.

            jTObservacao.setText("Cheque Vinculado : " + chequeModel.getNumeroCheque());

//            if (chequeModel.getDataPrevisaoCompensacao() != null) {
//                jFTDataPrevisaoCompensacao.setDate(chequeModel.getDataPrevisaoCompensacao());
//            }


            TituloPagarModel filtro = new TituloPagarModel();

            filtro.setPk(new PKModel());
            filtro.getPk().setOrganizacao(organizacaoModel);
            filtro.setChequeVinculado(chequeModel);

            TituloPagarModel titulo = tituloPagarBO.consultarPorTemplate(filtro);

            if (titulo != null && titulo.getPk() != null) {

                for (int i = 0; i < comboTitulo.getItemCount(); i++) {
                    if (titulo.getPk().getId().equalsIgnoreCase(((TituloPagarModel) comboTitulo.getItemAt(i)).getPk().getId())) {
                        comboTitulo.setSelectedIndex(i);
                        break;
                    }
                }

                popularCamposTitulo(titulo);
                comboTitulo.setEnabled(false);
                botaoConfirmar.setEnabled(false);

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

}//GEN-LAST:event_comboChequeActionPerformed

private void comboTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTituloActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTitulo.getSelectedItem() != null && comboTitulo.getSelectedItem() instanceof TituloPagarModel
            && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            TituloPagarModel tab = (TituloPagarModel) comboTitulo.getSelectedItem();

            if (tab != null && tab.getPk() != null) {

                tab = tituloPagarBO.consultarPorPk(tab);

                popularCamposTitulo(tab);
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

}//GEN-LAST:event_comboTituloActionPerformed

private void botaoCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCancelarActionPerformed

    if (comboCheque.getSelectedItem() != null && comboCheque.getSelectedItem() instanceof ContaBancariaChequeModel
            && !comboCheque.getSelectedItem().toString().isEmpty()) {

        try {

            long action = Action.EXCLUIR.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            if (validaCampos()) {

                TituloPagarModel titulo = (TituloPagarModel) comboTitulo.getSelectedItem();

                ContaBancariaChequeModel cheque = (ContaBancariaChequeModel) comboCheque.getSelectedItem();

                titulo.setChequeVinculado(null);

                TipoStatusModel status = new TipoStatusModel();
                status.setPk(new PKModel());
                status.getPk().setOrganizacao(organizacaoModel);

                if (cheque.getQtdImpressao() > 0) {

                    status.getPk().setId(Constantes.STATUS_CHEQUE_EXCLUIDO);

                } else {

                    status.getPk().setId(Constantes.STATUS_CHEQUE_NOVO);
                }

                cheque.setStatus(status);

                contaBancariaChequeBO.cancelarChequeAvulso(cheque, titulo);

                this.botaoLimparActionPerformed(evt);

            } else {

                exibeMensagemAviso("Campo(s) obrigatório(s).", null);

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

}//GEN-LAST:event_botaoCancelarActionPerformed

private void botaoConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoConfirmarActionPerformed

    if (comboCheque.getSelectedItem() != null && comboCheque.getSelectedItem() instanceof ContaBancariaChequeModel
            && !comboCheque.getSelectedItem().toString().isEmpty()) {

        try {

            long action = Action.OUTROS.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;
            }

            TipoStatusModel status = new TipoStatusModel();

            status.setPk(new PKModel());

            status.getPk().setOrganizacao(organizacaoModel);

            status.getPk().setId(Constantes.STATUS_CHEQUE_AVULSO);


            if (validaCampos()) {

                if (comboTitulo.getSelectedItem() == null) {
                    exibeMensagemAviso("Informe um título!", "Validação");
                    comboTitulo.requestFocus();
                    return;
                }

                if (jFTDataEmissao.getDate() == null) {
                    exibeMensagemAviso("Digite uma data de emissão!", "Validação");
                    jFTDataEmissao.requestFocus();
                    return;
                }



                if (jFTDataPrevisaoCompensacao.getDate() == null) {
                    exibeMensagemAviso("Informe uma data de previsão de compensação!", "Validação");
                    jFTDataPrevisaoCompensacao.requestFocus();
                    return;
                }



                if (jFTDataEmissao.getDate() != null && jFTDataEmissao.getDate().before(Controller.getDataServidorBD())) {
                    exibeMensagemAviso("A data de emissão não pode ser anterior a de hoje!", "Validação");
                    jFTDataEmissao.requestFocus();
                    return;
                }



                if (jFTDataPrevisaoCompensacao.getDate() != null && jFTDataEmissao.getDate().before(Controller.getDataServidorBD())) {
                    exibeMensagemAviso("A data de previsão de compensação deve ser maior que a data de emissão!", "Validação");
                    jFTDataPrevisaoCompensacao.requestFocus();
                    return;
                }


                ContaBancariaChequeModel cheque = (ContaBancariaChequeModel) comboCheque.getSelectedItem();

                TituloPagarModel titulo = (TituloPagarModel) comboTitulo.getSelectedItem();

                ContaBancariaModel contaBancaria = (ContaBancariaModel) comboConta.getSelectedItem();

                contaBancaria = contaBancariaBO.consultarPorPk(contaBancaria);

                cheque = contaBancariaChequeBO.consultarPorPk(cheque);

                titulo = tituloPagarBO.consultarPorPk(titulo);

                if (comboTitulo.getSelectedItem() != null && comboTitulo.getSelectedItem() instanceof TituloPagarModel) {

                    if (titulo != null && titulo.getPk() != null && titulo.getChequeVinculado() != null) {
                        exibeMensagemAviso("Existe outro cheque associado a este título Nº " + titulo.getChequeVinculado().getNumeroCheque(), "Validação!");
                        return;
                    }

                    cheque.setContaBancaria(contaBancaria);

                    cheque.setDataEmissao(PempecParse.dateToDate(jFTDataEmissao.getDate()));

                    cheque.setDataPrevisaoCompensacao(PempecParse.dateToDate(jFTDataPrevisaoCompensacao.getDate()));

                    cheque.setValor(titulo.getValorNominal());

                    cheque.setPortador(titulo.getCedente().getNome());

                    cheque.setObservacao("Cheque emitido avulso");

                    cheque.setStatus(status);

                    if (titulo != null && titulo.getPk() != null && !titulo.getPk().getId().isEmpty()) {
                        titulo.setChequeVinculado(cheque);
                    }

                    contaBancariaChequeBO.alterarChequeAvulso(cheque, titulo);

                    botaoConfirmar.setEnabled(false);
                    botaoImprimir.setEnabled(true);

                }

            } else {

                exibeMensagemAviso("Campo(s) obrigatório(s).", null);

            }

        } catch (ApplicationException ex) {

            tratamentoExcecao(ex);

            ex.printStackTrace();

        } catch (final SystemException ex) {

            ex.printStackTrace();

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }
    }

}//GEN-LAST:event_botaoConfirmarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque1;
    private javax.swing.JButton botaoCancelar;
    private javax.swing.JButton botaoConfirmar;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboCheque;
    private javax.swing.JComboBox comboConta;
    private javax.swing.JComboBox comboTitulo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private org.jdesktop.swingx.JXDatePicker jFTDataEmissao;
    private org.jdesktop.swingx.JXDatePicker jFTDataPrevisaoCompensacao;
    protected br.com.pempec.componentes.JDoubleField jFTValorPagar;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXEditorPane jTObservacao;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCheque1;
    private javax.swing.JLabel labelDataEmissao;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelNumeroDocumento;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelPortador;
    private javax.swing.JLabel labelValorPagar;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportCheque;
    private AutoCompleteSupport supportTitulo;

    private void popularCamposTitulo(TituloPagarModel titulo) {

        botaoImprimir.setEnabled(true);
        botaoCancelar.setEnabled(true);


        if (titulo.getValorNominal() != null) {
            jFTValorPagar.setText(PempecParse.doubleToZero(titulo.getValorNominal()));
        }

        if (titulo.getDataVencimento() != null) {

            jFTDataEmissao.setDate(titulo.getDataVencimento());

            jFTDataPrevisaoCompensacao.setDate(PempecUtil.addDayDate(titulo.getDataVencimento(), 2));

        }

        if (titulo.getHistorico() != null) {
            labelDescricao.setText(titulo.getHistorico().getDescricao().toUpperCase() + " " + titulo.getDescricao().toUpperCase());
        }

        if (titulo.getCedente() != null) {
            labelPortador.setText(titulo.getCedente().getNome().toUpperCase() + " " + titulo.getCedente().getCpfCnpj());
        }

    }
}
