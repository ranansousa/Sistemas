package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaChequeBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.FolhaCheque;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Extenso;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.ChequeTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class ImpressaoCheque extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaChequeBO contaBancariaChequeBO = new ContaBancariaChequeBO();
    private TituloPagarBaixaChequeBO tituloPagoChequeBO = new TituloPagarBaixaChequeBO();
    private ContaBancariaDebitoBO contaBancariaDebitoBO = new ContaBancariaDebitoBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private HistoricoBO historicoBO = new HistoricoBO();
    private long tela = Tela.TELA_CONCILIACAO_IMPRESSAO_DE_CHEQUES.getTela();

    private String NameObject() {
        return (String) "Impressão de Cheques";
    }

    public void repopularCombos() {

        try {
            //conta Bancaria

            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta(" -> Selecione <- ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboConta.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

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

    public ImpressaoCheque() throws SystemException {


        initComponents();
        Controller.setQtdMovimentosHoje(0);

        campoCodigoCheque.setVisible(false);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoImprimir = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        abaCompensarCheque1 = new javax.swing.JPanel();
        labelCheque1 = new javax.swing.JLabel();
        campoCodigoCheque = new javax.swing.JTextField();
        labelValorCheque = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelDataGeracao = new javax.swing.JLabel();
        labelEmissao = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();
        labelPortador = new javax.swing.JLabel();
        labelCompensado = new javax.swing.JLabel();
        labelDataRegistro = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelTituloPagar = new javax.swing.JLabel();
        comboConta = new javax.swing.JComboBox();
        comboCheque = new javax.swing.JComboBox();
        labelDescricao = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Impressão de Cheques");

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoImprimir, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(NameObject()));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        abaCompensarCheque1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)));

        labelCheque1.setText("Cheque");

        campoCodigoCheque.setEditable(false);

        labelValorCheque.setBackground(new java.awt.Color(222, 218, 210));
        labelValorCheque.setFont(new java.awt.Font("Arial", 1, 10));
        labelValorCheque.setForeground(new java.awt.Color(0, 153, 153));
        labelValorCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel11.setText("Conta Bancária");

        labelDataGeracao.setBackground(new java.awt.Color(222, 218, 210));
        labelDataGeracao.setFont(new java.awt.Font("Arial", 1, 10));
        labelDataGeracao.setForeground(new java.awt.Color(0, 153, 153));
        labelDataGeracao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gerado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelEmissao.setBackground(new java.awt.Color(222, 218, 210));
        labelEmissao.setFont(new java.awt.Font("Arial", 1, 10));
        labelEmissao.setForeground(new java.awt.Color(0, 153, 153));
        labelEmissao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Emissão", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelStatus.setBackground(new java.awt.Color(222, 218, 210));
        labelStatus.setFont(new java.awt.Font("Arial", 1, 10));
        labelStatus.setForeground(new java.awt.Color(0, 153, 153));
        labelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelStatus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelPortador.setBackground(new java.awt.Color(222, 218, 210));
        labelPortador.setFont(new java.awt.Font("Arial", 1, 10));
        labelPortador.setForeground(new java.awt.Color(0, 153, 153));
        labelPortador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Portador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelCompensado.setBackground(new java.awt.Color(222, 218, 210));
        labelCompensado.setFont(new java.awt.Font("Arial", 1, 10));
        labelCompensado.setForeground(new java.awt.Color(0, 153, 153));
        labelCompensado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Compensado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelDataRegistro.setBackground(new java.awt.Color(222, 218, 210));
        labelDataRegistro.setFont(new java.awt.Font("Arial", 1, 10));
        labelDataRegistro.setForeground(new java.awt.Color(0, 153, 153));
        labelDataRegistro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelUsuario.setBackground(new java.awt.Color(222, 218, 210));
        labelUsuario.setFont(new java.awt.Font("Arial", 1, 10));
        labelUsuario.setForeground(new java.awt.Color(0, 153, 153));
        labelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelTituloPagar.setBackground(new java.awt.Color(222, 218, 210));
        labelTituloPagar.setFont(new java.awt.Font("Arial", 1, 10));
        labelTituloPagar.setForeground(new java.awt.Color(0, 153, 153));
        labelTituloPagar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número Documento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

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

        javax.swing.GroupLayout abaCompensarCheque1Layout = new javax.swing.GroupLayout(abaCompensarCheque1);
        abaCompensarCheque1.setLayout(abaCompensarCheque1Layout);
        abaCompensarCheque1Layout.setHorizontalGroup(
            abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelDescricao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaCompensarCheque1Layout.createSequentialGroup()
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(28, 28, 28)
                                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCheque1)))
                            .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(labelPortador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaCompensarCheque1Layout.createSequentialGroup()
                                    .addComponent(labelTituloPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoCodigoCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                .addComponent(labelCompensado, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                .addComponent(labelDataGeracao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarCheque1Layout.createSequentialGroup()
                                .addComponent(labelEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        abaCompensarCheque1Layout.setVerticalGroup(
            abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoCodigoCheque, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarCheque1Layout.createSequentialGroup()
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(labelCheque1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboConta, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(labelPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarCheque1Layout.createSequentialGroup()
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataGeracao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCompensado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTituloPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 658, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(11, 11, 11)
                    .addComponent(abaCompensarCheque1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 237, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(abaCompensarCheque1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(12, Short.MAX_VALUE)))
        );

        abaCompensarCheque1.getAccessibleContext().setAccessibleName("abaEstorno");

        jTabbedPane1.addTab("Impressão de Cheques", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
        labelDataGeracao.setText("");
        labelEmissao.setText("");
        labelCompensado.setText("");
        labelDataRegistro.setText("");
        labelStatus.setText("");
        labelValorCheque.setText("");
        labelUsuario.setText("");
        labelTituloPagar.setText("");
        labelDescricao.setText("");


    }

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        //limpar tela de estorno

        comboConta.setSelectedIndex(0);
        if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
            supportCheque.uninstall();
        }
        this.limparCamposCheque();

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (comboCheque.getSelectedItem() == null) {
            comboCheque.requestFocus();
            return false;
        }

        return true;
    }

private void comboContaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaItemStateChanged

    if (comboConta.getSelectedItem() != null
            && ((ContaBancariaModel) comboConta.getSelectedItem()).getPk() != null) {

        this.limparCamposCheque();

        try {

            Collection<ContaBancariaChequeModel> lContaBancariaCheque = contaBancariaChequeBO.obterPorContaBancariaStatusEmitidoECompensado((ContaBancariaModel) comboConta.getSelectedItem());
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

        if (supportCheque != null && supportCheque.getItemList() != null && supportCheque.getComboBox() != null) {
            supportCheque.uninstall();
        }

    }
}//GEN-LAST:event_comboContaItemStateChanged

private void botaoImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirActionPerformed

    if (comboCheque.getSelectedItem() != null && !comboCheque.getSelectedItem().toString().isEmpty()) {

        try {

//            long action = Action.IMPRESSAO.getAction();
//
//            if (!Controller.checarPermissao(tela, action)) {
//                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
//                return;
//            } 19/05/2012

            ContaBancariaChequeModel cheque = new ContaBancariaChequeModel();

            cheque.setPk(new PKModel());
            cheque.getPk().setOrganizacao(organizacaoModel);
            cheque.setNumeroCheque(comboCheque.getSelectedItem().toString());

            ContaBancariaModel contaBancaria = new ContaBancariaModel();
            contaBancaria.setPk(new PKModel());
            contaBancaria.getPk().setOrganizacao(organizacaoModel);

            if (comboConta.getSelectedItem() != null) {
                contaBancaria.setConta(comboConta.getSelectedItem().toString());
            }

            contaBancaria = contaBancariaBO.consultarPorTemplate(contaBancaria);

            cheque.setContaBancaria(contaBancaria);

            cheque = contaBancariaChequeBO.consultarPorTemplate(cheque);

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

            if (Controller.getOrganizacao().getCidade() != null) {
                bean.setCidade(Controller.getOrganizacao().getCidade().getDescricao());
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

            if (!cheque.getContaBancaria().getBanco().getNomeBanco().isEmpty() && cheque.getContaBancaria().getBanco().getNomeBanco() != null) {

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

                //19/05/2012       //     new MovimentoDiarioBO().inserir(this.registroMovimento("Impressao Cheque", cheque.getNumeroCheque(), Controller.getUsuarioLogado().getNome() + " solicitou impressao do cheque : " + cheque.getNumeroCheque() + " da conta : " + cheque.getContaBancaria().getConta(), null, "Impresso"));

                Integer qtdImpressao = cheque.getQtdImpressao();

                cheque.setQtdImpressao(qtdImpressao++);

                contaBancariaChequeBO.alterar(cheque);




            } else {

                exibeMensagemAviso("Problemas com o layout do cheque", "Layot de Impressão");
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

        this.botaoLimparActionPerformed(evt); //19/05/2012


    } else {

        exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

    }

}//GEN-LAST:event_botaoImprimirActionPerformed

private void comboChequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboChequeActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboCheque.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        this.limparCamposCheque();

        String numeroCheque = ((ContaBancariaChequeModel) comboCheque.getSelectedItem()).getNumeroCheque();

        try {

            ContaBancariaChequeModel chequeModel = new ContaBancariaChequeModel();

            chequeModel.setPk(new PKModel());
            chequeModel.getPk().setOrganizacao(organizacaoModel);
            chequeModel.setNumeroCheque(numeroCheque);

            chequeModel = contaBancariaChequeBO.consultarPorTemplate(chequeModel);



            campoCodigoCheque.setText(chequeModel.getPk().getId());
            labelEmissao.setText(PempecParse.dateToString(chequeModel.getDataEmissao()));
            labelDataGeracao.setText(PempecParse.dateToString(chequeModel.getDataRegistro()));
            labelEmissao.setText(PempecParse.dateToString(chequeModel.getDataEmissao()));
            labelCompensado.setText(PempecParse.dateToString(chequeModel.getDataCompensado()));
            labelDataRegistro.setText(PempecParse.dateToString(chequeModel.getDataRegistro()));
            labelPortador.setText(chequeModel.getPortador());
            labelStatus.setText(chequeModel.getStatus().toString());
            labelValorCheque.setText(PempecParse.doubleToZero(chequeModel.getValor()));

            ContaBancariaDebitoModel contaBancariaDebitoModel = new ContaBancariaDebitoModel();
            contaBancariaDebitoModel.setContaBancariaCheque(chequeModel);
            contaBancariaDebitoModel.setPk(new PKModel());
            contaBancariaDebitoModel.getPk().setOrganizacao(organizacaoModel);

            contaBancariaDebitoModel = contaBancariaDebitoBO.obterPorCheque(contaBancariaDebitoModel);

            List<TituloPagarBaixaChequeModel> listaTitulos = new ArrayList<TituloPagarBaixaChequeModel>();

            listaTitulos = tituloPagoChequeBO.obterPorCheque(chequeModel);

            if (listaTitulos.size() >= 1) {

                TituloPagarModel tituloPago = ((TituloPagarModel) listaTitulos.get(0).getTituloPagarBaixa().getTitulo());

                labelTituloPagar.setText(tituloPago.getNumeroDocumento());

                HistoricoModel historico = historicoBO.consultarPorPk(tituloPago.getHistorico());

                labelDescricao.setText(historico.getDescricao() + " " + tituloPago.getDescricao());

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque1;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigoCheque;
    private javax.swing.JComboBox comboCheque;
    private javax.swing.JComboBox comboConta;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCheque1;
    private javax.swing.JLabel labelCompensado;
    private javax.swing.JLabel labelDataGeracao;
    private javax.swing.JLabel labelDataRegistro;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelEmissao;
    private javax.swing.JLabel labelPortador;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelTituloPagar;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelValorCheque;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportCheque;
}
