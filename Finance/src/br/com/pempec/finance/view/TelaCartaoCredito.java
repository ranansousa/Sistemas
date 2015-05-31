package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CartaoCreditoBO;
import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CartaoCreditoModel;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.FilterReportTituloPagar;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.CedenteTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class TelaCartaoCredito extends FinanceInternalFrame implements IRepopulador {

    private CartaoCreditoBO cartaoCreditoBO = new CartaoCreditoBO();
    private CedenteBO cedenteBO = new CedenteBO();
    private CedenteModel cedente = new CedenteModel();
    private long tela = Tela.TELA_PARAMETROS_BANCOS.getTela();

    private String NameObject() {
        return (String) "Cartao Credito";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos() {

        try {


            Collection<CedenteModel> lColecao = cedenteBO.obterTodosCartaoCredito(organizacaoModel);


            final EventList<CedenteModel> lRegistros = GlazedLists.eventList(lColecao);

            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboCedente, lRegistros, new CedenteTextFilterator());
            support.setFilterMode(TextMatcherEditor.STARTS_WITH);
            support.setStrict(false);


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

    public TelaCartaoCredito() throws SystemException {

        initComponents();

        //Escodendo os campos ID's
        campoCodigo.setVisible(false);

        labelSituacao.setVisible(false);

        labelConsolidado.setVisible(false);

        labelPagar.setVisible(false);

        labelPago.setVisible(false);



    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoFaturaAnterior = new javax.swing.JButton();
        botaoTodasFaturas1 = new javax.swing.JButton();
        botaoProximaFatura = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        labelCedente = new javax.swing.JLabel();
        comboCedente = new javax.swing.JComboBox();
        campoCodigo = new javax.swing.JTextField();
        labelLimite = new javax.swing.JLabel();
        labelTitular = new javax.swing.JLabel();
        labelNumero = new javax.swing.JLabel();
        labelValidade = new javax.swing.JLabel();
        labelCodigo = new javax.swing.JLabel();
        labelDiaVcto = new javax.swing.JLabel();
        labelDiaCompra = new javax.swing.JLabel();
        labelBandeira = new javax.swing.JLabel();
        labelSituacao = new javax.swing.JLabel();
        labelConsolidado = new javax.swing.JLabel();
        labelPago = new javax.swing.JLabel();
        labelPagar = new javax.swing.JLabel();
        labelProximoVcto = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cartão de Crédito");

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

        botaoFaturaAnterior.setMnemonic('L');
        botaoFaturaAnterior.setText("<<");
        botaoFaturaAnterior.setToolTipText("Fatura Anterior");
        botaoFaturaAnterior.setName(""); // NOI18N
        botaoFaturaAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoFaturaAnteriorActionPerformed(evt);
            }
        });

        botaoTodasFaturas1.setMnemonic('L');
        botaoTodasFaturas1.setText("Faturas");
        botaoTodasFaturas1.setToolTipText("Todas Faturas");
        botaoTodasFaturas1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoTodasFaturas1ActionPerformed(evt);
            }
        });

        botaoProximaFatura.setMnemonic('L');
        botaoProximaFatura.setText(">>");
        botaoProximaFatura.setToolTipText("Próxima Fatura");
        botaoProximaFatura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoProximaFaturaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoTodasFaturas1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFaturaAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoProximaFatura, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoTodasFaturas1, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFaturaAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoProximaFatura, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        botaoFaturaAnterior.getAccessibleContext().setAccessibleDescription("");

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelCedente.setText("Cartão");

        comboCedente.setFont(getFont());
        comboCedente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCedenteActionPerformed(evt);
            }
        });

        campoCodigo.setEditable(false);

        labelLimite.setFont(getFont());
        labelLimite.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelLimite.setBorder(javax.swing.BorderFactory.createTitledBorder("Limite"));

        labelTitular.setFont(getFont());
        labelTitular.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTitular.setBorder(javax.swing.BorderFactory.createTitledBorder("Titular"));

        labelNumero.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelNumero.setBorder(javax.swing.BorderFactory.createTitledBorder("Número"));

        labelValidade.setFont(getFont());
        labelValidade.setBorder(javax.swing.BorderFactory.createTitledBorder("Validade"));

        labelCodigo.setFont(getFont());
        labelCodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCodigo.setBorder(javax.swing.BorderFactory.createTitledBorder("Código"));

        labelDiaVcto.setFont(getFont());
        labelDiaVcto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDiaVcto.setBorder(javax.swing.BorderFactory.createTitledBorder("Dia Vcto"));

        labelDiaCompra.setFont(getFont());
        labelDiaCompra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDiaCompra.setBorder(javax.swing.BorderFactory.createTitledBorder("Dia Cp"));

        labelBandeira.setFont(getFont());
        labelBandeira.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBandeira.setBorder(javax.swing.BorderFactory.createTitledBorder("Bandeira"));

        labelSituacao.setFont(getFont());
        labelSituacao.setForeground(new java.awt.Color(255, 153, 51));
        labelSituacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        labelConsolidado.setFont(getFont());
        labelConsolidado.setForeground(new java.awt.Color(51, 153, 255));
        labelConsolidado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelConsolidado.setBorder(javax.swing.BorderFactory.createTitledBorder("Total de Compras"));

        labelPago.setForeground(new java.awt.Color(0, 153, 153));
        labelPago.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPago.setBorder(javax.swing.BorderFactory.createTitledBorder("Total Pago"));

        labelPagar.setFont(getFont());
        labelPagar.setForeground(new java.awt.Color(255, 102, 102));
        labelPagar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelPagar.setBorder(javax.swing.BorderFactory.createTitledBorder("Saldo Devedor"));

        labelProximoVcto.setForeground(new java.awt.Color(255, 51, 0));
        labelProximoVcto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelProximoVcto.setBorder(javax.swing.BorderFactory.createTitledBorder("Próximo Vencimento"));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCedente)
                    .addComponent(labelTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelValidade, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelDiaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(labelSituacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(52, 52, 52))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelProximoVcto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelConsolidado, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelPago, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelBandeira, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelDiaVcto, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDiaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(labelCedente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(labelTitular, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelValidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelBandeira, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDiaVcto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPago, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelConsolidado, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelProximoVcto, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cartão de Crédito", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        this.botaoLimparActionPerformed(evt);
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        campoCodigo.setText("");

        //Somente setar null nos combos que possui o auto-complete
        comboCedente.setSelectedItem(null);

        labelBandeira.setText("");

        labelSituacao.setText("");

        labelNumero.setText("");

        labelTitular.setText("");

        labelDiaVcto.setText("");

        labelDiaCompra.setText("");

        labelCodigo.setText("");

        labelLimite.setText("");

        labelNumero.setText("");

        labelLimite.setText("");

        labelSituacao.setVisible(false);

        labelValidade.setText("");

        labelPagar.setText("");

        labelPago.setText("");

        labelProximoVcto.setText("");

        labelConsolidado.setVisible(false);

        labelPagar.setVisible(false);

        labelPago.setVisible(false);


        labelConsolidado.setText("");

    }//GEN-LAST:event_botaoLimparActionPerformed

private void comboCedenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCedenteActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboCedente.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {


            cedente.setPk(new PKModel());
            cedente.getPk().setOrganizacao(Controller.getOrganizacao());
            cedente.setNome(comboCedente.getSelectedItem().toString());

            cedente = cedenteBO.consultarPorTemplate(cedente);

            List<TituloPagarModel> collTitulos = new ArrayList<TituloPagarModel>();
            Double valorPago = 0d;
            Double valorPagar = 0d;
            Double total = 0d;

            FilterReportTituloPagar filtro = new FilterReportTituloPagar();
            filtro.setOrganizacao(Controller.getOrganizacao().getId());
            Date dInicio = PempecParse.stringToDate("01/01/1900");
            Date dFinal = PempecParse.stringToDate("01/01/2900");

            filtro.setDataFinal(dFinal);
            filtro.setDataInicial(dInicio);


            if (cedente != null && cedente.getPk().getId() != null) {
                filtro.setCedente(cedente.getPk().getId());

            }

            collTitulos.addAll(new TituloPagarBO().obterRelatorio(filtro));

            for (TituloPagarModel tituloPagarModel : collTitulos) {

                total += tituloPagarModel.getValorNominal();

                if (tituloPagarModel.getDataPagamento() != null) {

                    valorPago += tituloPagarModel.getValorNominal();

                } else {

                    valorPagar += tituloPagarModel.getValorNominal();

                }




            }

            labelPagar.setText(PempecParse.doubleToZero(valorPagar));

            labelPago.setText(PempecParse.doubleToZero(valorPago));

            labelConsolidado.setText(PempecParse.doubleToZero(total));


            CartaoCreditoModel tab = cedente.getCartaoCreditoModel();


            tab = cartaoCreditoBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {

                campoCodigo.setText(tab.getPk().getId());

                labelConsolidado.setVisible(true);

                labelCodigo.setText(tab.getCodigoSeguranca());

                labelDiaVcto.setText(tab.getDiaVencimento());

                if (tab.getDiaVencimento() != null) {

                    labelSituacao.setVisible(true);

                    labelPagar.setVisible(true);

                    labelPago.setVisible(true);


                    if (PempecParse.dateToDate(tab.getValidade()).before(Controller.getDataServidorBD())) {

                        labelSituacao.setText("Cartão Vencido!!!");

                    } else {

                        labelSituacao.setText("Cartão Válido");

                    }
                }

                labelDiaCompra.setText(tab.getDiaCompra());

                labelValidade.setText(PempecParse.dateToString(tab.getValidade()));

                labelLimite.setText(PempecParse.doubleToZero(tab.getLimite()));

                labelNumero.setText(tab.getNumero());

                labelTitular.setText(tab.getTitular());

                labelBandeira.setText(tab.getBandeira());

                int hoje = Integer.parseInt(PempecParse.dateToString(Controller.getDataServidorBD()).substring(0, 2));

                int diaVctoCartao = Integer.parseInt(cedente.getCartaoCreditoModel().getDiaVencimento());

                if (hoje > diaVctoCartao) {

                    String dataVcto = diaVctoCartao + "/" + (Controller.getDataServidorBD().getMonth() + 2) + "/" + "20" + (Controller.getDataServidorBD().getYear() - 100);

                    labelProximoVcto.setText(dataVcto);

                } else {

                    String dataVcto = diaVctoCartao + "/" + (Controller.getDataServidorBD().getMonth() + 1) + "/" + "20" + (Controller.getDataServidorBD().getYear() - 100);

                    labelProximoVcto.setText(dataVcto);

                }




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

}//GEN-LAST:event_comboCedenteActionPerformed

private void botaoFaturaAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFaturaAnteriorActionPerformed
    if (comboCedente.getSelectedItem() != null) {

        try {

            String dataVcto = PempecParse.dateToString(Controller.getDataServidorBD());

            int hoje = Integer.parseInt(PempecParse.dateToString(Controller.getDataServidorBD()).substring(0, 2));

            int diaVctoCartao = Integer.parseInt(cedente.getCartaoCreditoModel().getDiaVencimento());

            if (hoje > diaVctoCartao) {

                dataVcto = diaVctoCartao + "/" + (Controller.getDataServidorBD().getMonth() + 1) + "/" + "20" + (Controller.getDataServidorBD().getYear() - 100);

            } else {

                dataVcto = diaVctoCartao + "/" + (Controller.getDataServidorBD().getMonth()) + "/" + "20" + (Controller.getDataServidorBD().getYear() - 100);

            }


            TipoStatusModel tipoStatusModel = new TipoStatusModel();
            tipoStatusModel.setPk(new PKModel());
            tipoStatusModel.getPk().setOrganizacao(organizacaoModel);
            tipoStatusModel.getPk().setId(Constantes.STATUS_TITULO_PAGO);

            RelatorioCartao relatorioCartao = new RelatorioCartao(cedente, PempecParse.stringToDate(dataVcto), tipoStatusModel);
            relatorioCartao.setTitle("Relatório Cartão");
            relatorioCartao.repopularCombos();
            TelaPrincipal.desktopPane.add(relatorioCartao);
            relatorioCartao.show();


        } //        catch (ApplicationException ex) {
        //
        //            tratamentoExcecao(ex);
        //
        //        }
        catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }



    } else {

        exibeMensagemAviso("Selecione um Cartão", "Cartão");
    }
}//GEN-LAST:event_botaoFaturaAnteriorActionPerformed

private void botaoTodasFaturas1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoTodasFaturas1ActionPerformed
    exibeMensagemAviso("Em Desenvolvimento", "Indisponível");
}//GEN-LAST:event_botaoTodasFaturas1ActionPerformed

private void botaoProximaFaturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoProximaFaturaActionPerformed
    if (comboCedente.getSelectedItem() != null) {

        try {

            String dataVcto = "";

            int hoje = Integer.parseInt(PempecParse.dateToString(Controller.getDataServidorBD()).substring(0, 2));

            int diaVctoCartao = Integer.parseInt(cedente.getCartaoCreditoModel().getDiaVencimento());

            if (hoje > diaVctoCartao) {

                dataVcto = diaVctoCartao + "/" + (Controller.getDataServidorBD().getMonth() + 2) + "/" + "20" + (Controller.getDataServidorBD().getYear() - 100);

            } else {

                dataVcto = diaVctoCartao + "/" + (Controller.getDataServidorBD().getMonth() + 1) + "/" + "20" + (Controller.getDataServidorBD().getYear() - 100);

            }

            TipoStatusModel tipoStatusModel = new TipoStatusModel();
            tipoStatusModel.setPk(new PKModel());
            tipoStatusModel.getPk().setOrganizacao(organizacaoModel);
            tipoStatusModel.getPk().setId(Constantes.STATUS_TITULO_NOVO);

            RelatorioCartao relatorioCartao = new RelatorioCartao(cedente, PempecParse.stringToDate(dataVcto), tipoStatusModel);
            relatorioCartao.setTitle("Relatório Cartão");
            relatorioCartao.repopularCombos();
            TelaPrincipal.desktopPane.add(relatorioCartao);
            relatorioCartao.show();


        } //        catch (ApplicationException ex) {
        //
        //            tratamentoExcecao(ex);
        //
        //        }
        catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }



    } else {

        exibeMensagemAviso("Selecione um Cartão", "Cartão");
    }
}//GEN-LAST:event_botaoProximaFaturaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton botaoFaturaAnterior;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    protected javax.swing.JButton botaoProximaFatura;
    protected javax.swing.JButton botaoTodasFaturas1;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JComboBox comboCedente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelBandeira;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelCodigo;
    private javax.swing.JLabel labelConsolidado;
    private javax.swing.JLabel labelDiaCompra;
    private javax.swing.JLabel labelDiaVcto;
    private javax.swing.JLabel labelLimite;
    private javax.swing.JLabel labelNumero;
    private javax.swing.JLabel labelPagar;
    private javax.swing.JLabel labelPago;
    private javax.swing.JLabel labelProximoVcto;
    private javax.swing.JLabel labelSituacao;
    private javax.swing.JLabel labelTitular;
    private javax.swing.JLabel labelValidade;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;

    private Boolean validaCampos() throws ApplicationException, SystemException {

        return true;

    }
}
