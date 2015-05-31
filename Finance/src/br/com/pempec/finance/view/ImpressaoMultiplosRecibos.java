package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.EnderecoBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.SacadoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.ImpressaoMultiplosRecibosFilter;
import br.com.pempec.finance.models.ImpressaoMultiplosRecibosModel;
import br.com.pempec.finance.models.SacadoModel;
import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloReceberBaixaInternetModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.reports.ReciboImpressaoMultiplos;
import br.com.pempec.finance.models.reports.ReciboTituloPagarCrossTab;
import br.com.pempec.finance.models.reports.ReciboTituloPagarSubForma;
import br.com.pempec.finance.models.reports.ReciboTituloReceberCrossTab;
import br.com.pempec.finance.models.reports.ReciboTituloReceberSubForma;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Extenso;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.tables.ColumnModelImpressaoMultiplosRecibos;
import br.com.pempec.finance.utils.tables.TableModelImpressaoMultiplosRecibos;
import java.awt.FontMetrics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaMap;

/**
 * @author PEMPEC
 */
public class ImpressaoMultiplosRecibos extends FinanceInternalFrame implements IRepopulador {

    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private TituloPagarBaixaBO tituloPagarBaixaBO = new TituloPagarBaixaBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private TituloReceberBO tituloReceberBO = new TituloReceberBO();
    private TituloReceberBaixaBO tituloReceberBaixaBO = new TituloReceberBaixaBO();
    private CedenteBO cedenteBO = new CedenteBO();
    private SacadoBO sacadoBO = new SacadoBO();
    private EnderecoBO enderecoBO = new EnderecoBO();
    private static long tela = Tela.TELA_RELATORIOS_IMPRESSAO_MULTIPLOS_RECIBOS.getTela();

    private String NameObject() {
        return (String) "Impressão de Múltiplos Recibos";
    }

    public void repopularCombos() {
    }

    public ImpressaoMultiplosRecibos() throws SystemException {

        initComponents();
        Controller.setQtdMovimentosHoje(0);

        //formatar datas                
        jFTDataPagamento.setDate(Controller.getDataServidorBD());


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        botaoImprimir = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaCompensarCheque = new javax.swing.JPanel();
        labelDataVencimento = new javax.swing.JLabel();
        jFTDataPagamento = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableTitulos = new javax.swing.JTable();
        jTNumeroInicial = new javax.swing.JTextField();
        jTNumeroFinal = new javax.swing.JTextField();
        labelDataVencimento1 = new javax.swing.JLabel();
        labelDataVencimento2 = new javax.swing.JLabel();
        botaoPesquisar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jRTodos = new javax.swing.JRadioButton();
        jRTP = new javax.swing.JRadioButton();
        jRTR = new javax.swing.JRadioButton();
        jRNenhum = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Impressão Múltiplos Recibos");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoImprimir.setMnemonic('I');
        botaoImprimir.setText("Imprimir");
        botaoImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImprimirActionPerformed(evt);
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
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        abaCompensarCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102))));

        labelDataVencimento.setText("Data do Pagamento");

        tableTitulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Título 1", "Título 2", "Título 3", "Título 4", "Título 5"
            }
        ));
        jScrollPane2.setViewportView(tableTitulos);

        labelDataVencimento1.setText("a");

        labelDataVencimento2.setText("Número Documento");

        botaoPesquisar.setText("Pesquisar");
        botaoPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPesquisarActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleção"));

        buttonGroup1.add(jRTodos);
        jRTodos.setText("Todos");
        jRTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRTodosActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRTP);
        jRTP.setText("TP");
        jRTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRTPActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRTR);
        jRTR.setText("TR");
        jRTR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRTRActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRNenhum);
        jRNenhum.setSelected(true);
        jRNenhum.setText("Nenhum");
        jRNenhum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRNenhumActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRTP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRTR)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRNenhum)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jRTodos)
                .addComponent(jRTP)
                .addComponent(jRTR)
                .addComponent(jRNenhum))
        );

        javax.swing.GroupLayout abaCompensarChequeLayout = new javax.swing.GroupLayout(abaCompensarCheque);
        abaCompensarCheque.setLayout(abaCompensarChequeLayout);
        abaCompensarChequeLayout.setHorizontalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataVencimento))
                        .addGap(28, 28, 28)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                .addComponent(jTNumeroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(labelDataVencimento1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTNumeroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelDataVencimento2))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        abaCompensarChequeLayout.setVerticalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDataVencimento)
                            .addComponent(labelDataVencimento2))
                        .addGap(7, 7, 7)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTNumeroInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTNumeroFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataVencimento1)))
                    .addComponent(botaoPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Impressão Múltiplos Recibos", abaCompensarCheque);
        abaCompensarCheque.getAccessibleContext().setAccessibleName("abaCompensarCheque");

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 12));
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Legenda:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 12));
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("TP = Título a Pagar");

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 12));
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("TR = Título a Receber");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Estorno de Compensação");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        //Somente setar null nos combos que possui o auto-complete
        jFTDataPagamento.setDate(Controller.getDataServidorBD());

        jRNenhum.setSelected(true);

        this.jRNenhumActionPerformed(evt);

        tableTitulos.setAutoCreateColumnsFromModel(false);
        tableTitulos.setModel(new TableModelImpressaoMultiplosRecibos(new ArrayList<ImpressaoMultiplosRecibosModel>()));
        FontMetrics fm = tableTitulos.getFontMetrics(tableTitulos.getFont());
        tableTitulos.setColumnModel(new ColumnModelImpressaoMultiplosRecibos(fm));
        tableTitulos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableTitulos.getTableHeader().setReorderingAllowed(false);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        boolean validaPreenchimento = false;

        for (int i = 0; i < tableTitulos.getRowCount(); i++) {
            if (tableTitulos.getValueAt(i, 0).toString().equals("true")) {
                validaPreenchimento = true;
            }
        }

        if (!validaPreenchimento) {
            exibeMensagemAviso("Selecione um Item!", null);
            return false;
        }

        return true;
    }

private void botaoImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirActionPerformed


    try {

        long action = Action.IMPRESSAO.getAction();

        if (!Controller.checarPermissao(tela, action)) {
            exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
            return;
        }

        if (validaCampos()) {

            Collection<ReciboImpressaoMultiplos> lCollection = new ArrayList<ReciboImpressaoMultiplos>();

            for (int i = 0; i < tableTitulos.getRowCount(); i++) {

                if ((Boolean) tableTitulos.getValueAt(i, 0)) {

                    ReciboImpressaoMultiplos item = new ReciboImpressaoMultiplos();

                    if (tableTitulos.getValueAt(i, 1).toString().equals("TP")) {

                        TituloPagarModel tituloPagarModel = (TituloPagarModel) tableTitulos.getValueAt(i, 6);

                        TituloPagarBaixaModel baixa = tituloPagarBaixaBO.consultarPorTitulo(tituloPagarModel);

                        item.setRazaoSocial(organizacaoModel.getRazaoSocial());
                        item.setEndereco(organizacaoModel.getEndereco());
                        item.setCnpj(organizacaoModel.getCnpj());
                        item.setCidade(organizacaoModel.getCidade() + ", " + PempecParse.dateToString(new Date()));
                        item.setNumeroDocumento(tituloPagarModel.getNumeroDocumento());

                        CedenteModel cedente = cedenteBO.consultarPorTemplate(tituloPagarModel.getCedente());

                        item.setFavorecido(cedente.getNome());

                        if (cedente.getEndereco() != null && cedente.getEndereco().getCidade() != null && cedente.getEndereco().getBairro() != null && cedente.getEndereco().getEstado() != null) {

                            EnderecoModel endereco = enderecoBO.consultarPorPk(cedente.getEndereco());

                            item.setEnderecoFavorecido(endereco.getLogradouro().toUpperCase() + ", Nº " + endereco.getNumero() + " - " + endereco.getBairro().getDescricao().toUpperCase() + " - CEP : " + endereco.getCep());

                            if (endereco.getComplemento() != null && !endereco.getComplemento().isEmpty()) {

                                item.setCidadeEstadoFavorecido(endereco.getComplemento() + " - " + endereco.getCidade().getDescricao().toUpperCase() + " - " + endereco.getEstado().getSigla().toUpperCase());

                            } else {

                                item.setCidadeEstadoFavorecido(endereco.getCidade().getDescricao().toUpperCase() + " - " + endereco.getEstado().getSigla().toUpperCase());

                            }

                        } else {

                            item.setEnderecoFavorecido(" Cadastro incompleto. Falta endereço. ");
                            item.setCidadeEstadoFavorecido(" Complete o cadastro.");

                        }

                        item.setCnpjCpfFavorecido(cedente.getPersonalidade().contains("CPF") ? "CPF: " : "CNPJ: " + "              " + cedente.getCpfCnpj());

                        item.setDescricao(tituloPagarModel.getHistorico().getDescricao() + " " + tituloPagarModel.getDescricao());

                        if (cedente.getContato() != null) {
                            item.setContatoFavorecido(cedente.getContato().getTelefone().substring(0, 2) + " - " + cedente.getContato().getTelefone().substring(2));
                        }

                        List<TituloPagarModel> collTitulos = tituloPagarBO.obterTitulosFilhos(tituloPagarModel);

                        Collection<ReciboTituloPagarCrossTab> collCrossTab = new ArrayList<ReciboTituloPagarCrossTab>();

                        Collection<ReciboTituloPagarSubForma> collFormas = new ArrayList<ReciboTituloPagarSubForma>();

                        collTitulos.remove(tituloPagarModel);

                        Collection<DynaBean> collection = new ArrayList<DynaBean>();

                        Double valorPago = 0d;

                        Double totalAcrescimos = 0d;

                        Double totalDeducoes = 0d;

                        if (baixa != null) {

                            String descricaoFormaInternet = Constantes.FORMA_PAGAMENTO_INTERNET;
                            String descricaoFormaCheque = Constantes.FORMA_PAGAMENTO_CHEQUE;

                            if (baixa.getFormasPagamento() != null && !baixa.getFormasPagamento().isEmpty()) {

                                for (TituloPagarBaixaFormaPagamentoModel forma : baixa.getFormasPagamento()) {

                                    if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {

                                        ReciboTituloPagarSubForma formaEspecie = new ReciboTituloPagarSubForma();

                                        formaEspecie.setValor(forma.getValor());

                                        formaEspecie.setIdForma(Constantes.FORMA_PAGAMENTO_ESPECIE);

                                        formaEspecie.setForma(forma.getForma().getDescricao());

                                        collFormas.add(formaEspecie);

                                    } else {

                                        if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE)) {
                                            descricaoFormaCheque = forma.getForma().getDescricao();
                                        } else {
                                            descricaoFormaInternet = forma.getForma().getDescricao();
                                        }

                                    }

                                    valorPago += forma.getValor();

                                }

                                for (TituloPagarBaixaChequeModel cheque : baixa.getTituloPagarBaixaCheque()) {

                                    ReciboTituloPagarSubForma formaCheque = new ReciboTituloPagarSubForma();

                                    formaCheque.setValor(cheque.getValor());

                                    formaCheque.setIdForma(Constantes.FORMA_PAGAMENTO_CHEQUE);

                                    formaCheque.setForma(descricaoFormaCheque);

                                    formaCheque.setNumeroCheque(cheque.getContaBancariaCheque().getNumeroCheque());

                                    ContaBancariaModel contaBancaria = contaBancariaBO.consultarPorPk(cheque.getContaBancariaCheque().getContaBancaria());

                                    formaCheque.setConta(contaBancaria.getConta());

                                    collFormas.add(formaCheque);

                                }

                                for (TituloPagarBaixaInternetModel internet : baixa.getTituloPagarBaixaInternet()) {

                                    ReciboTituloPagarSubForma formaInternet = new ReciboTituloPagarSubForma();

                                    formaInternet.setValor(internet.getValor());

                                    formaInternet.setIdForma(Constantes.FORMA_PAGAMENTO_INTERNET);

                                    formaInternet.setForma(descricaoFormaInternet);

                                    formaInternet.setCodigoBanco(internet.getBancoDestino().getCodigoBanco());

                                    formaInternet.setNomeBanco(internet.getBancoDestino().getNomeBanco());

                                    formaInternet.setAgenciaDestino(internet.getAgenciaDestino());

                                    formaInternet.setContaDestino(internet.getContaDestino());

                                    formaInternet.setDescricao(internet.getTipoOperacaoBancaria().getDescricao());

                                    collFormas.add(formaInternet);


                                }

                            }

                            if (baixa.getAcrescimos() != null && !baixa.getAcrescimos().isEmpty()) {

                                for (TituloPagarBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                                    ReciboTituloPagarCrossTab crossTab = new ReciboTituloPagarCrossTab();

                                    crossTab.setDescricao(acrescimo.getTipoAcrescimo().getDescricao());

                                    crossTab.setTipo("Acréscimos");

                                    crossTab.setValor(acrescimo.getValor());

                                    totalAcrescimos += acrescimo.getValor();

                                    collCrossTab.add(crossTab);

                                }

                            }

                            if (baixa.getDeducoes() != null && !baixa.getDeducoes().isEmpty()) {

                                for (TituloPagarBaixaDeducaoModel deducao : baixa.getDeducoes()) {

                                    ReciboTituloPagarCrossTab crossTab = new ReciboTituloPagarCrossTab();

                                    crossTab.setDescricao(deducao.getTipoDeducao().getDescricao());

                                    crossTab.setTipo("Deduções");

                                    crossTab.setValor(deducao.getValor());

                                    totalDeducoes += deducao.getValor();

                                    collCrossTab.add(crossTab);

                                }

                            }

                        }

                        item.setValor(tituloPagarModel.getValorNominal());

                        if (baixa != null) {

                            item.setExisteBaixa(true);

                            item.setValorPago(valorPago);

                            if (totalAcrescimos != 0d) {
                                item.setExisteAcres(true);
                            }

                            item.setTotalAcrescimos(totalAcrescimos);

                            if (totalDeducoes != 0d) {
                                item.setExisteDed(true);
                            }

                            item.setTotalDeducoes(totalDeducoes);

                            item.setValorExtenso(new Extenso(valorPago).toString().toUpperCase());

                        } else {

                            item.setValorExtenso(new Extenso(tituloPagarModel.getValorNominal()).toString().toUpperCase());

                        }

                        Double valorPagoFilhos = 0d;

                        for (TituloPagarModel titulo : collTitulos) {

                            baixa = tituloPagarBaixaBO.consultarPorTitulo(titulo);

                            Double totalAcrescimosFilhos = 0d;

                            Double totalDeducoesFilhos = 0d;

                            if (baixa != null) {

                                if (baixa.getFormasPagamento() != null && !baixa.getFormasPagamento().isEmpty()) {

                                    for (TituloPagarBaixaFormaPagamentoModel forma : baixa.getFormasPagamento()) {

                                        valorPagoFilhos += forma.getValor();

                                    }

                                }

                                if (baixa.getAcrescimos() != null && !baixa.getAcrescimos().isEmpty()) {

                                    for (TituloPagarBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                                        totalAcrescimosFilhos += acrescimo.getValor();

                                    }

                                }

                                if (baixa.getDeducoes() != null && !baixa.getDeducoes().isEmpty()) {

                                    for (TituloPagarBaixaDeducaoModel deducao : baixa.getDeducoes()) {

                                        totalDeducoesFilhos += deducao.getValor();

                                    }

                                }

                                DynaBean lazy = new LazyDynaMap();
                                lazy.set(RelatorioConstantes.PARAMETRO_NUMERO_DOCUMENTO, titulo.getNumeroDocumento());

                                lazy.set(RelatorioConstantes.PARAMETRO_VALOR, valorPagoFilhos);

                                lazy.set(RelatorioConstantes.PARAMETRO_DATA_PAGAMENTO, titulo.getDataPagamento());
                                lazy.set(RelatorioConstantes.PARAMETRO_TOTAL_ACRESCIMOS, totalAcrescimosFilhos);
                                lazy.set(RelatorioConstantes.PARAMETRO_TOTAL_DEDUCOES, totalDeducoesFilhos);

                                collection.add(lazy);

                            }

                        }

                        item.setCollection(collection);

                        if (collCrossTab.isEmpty()) {
                            collCrossTab.add(new ReciboTituloPagarCrossTab());
                        }

                        item.setCollCrossTab(collCrossTab);

                        item.setCollFormas(collFormas);

                        lCollection.add(item);

                    } else {

                        if (tableTitulos.getValueAt(i, 1).toString().equals("TR")) {

                            TituloReceberModel tituloReceberModel = (TituloReceberModel) tableTitulos.getValueAt(i, 7);

                            TituloReceberBaixaModel baixa = tituloReceberBaixaBO.consultarPorTitulo(tituloReceberModel);

                            item.setRazaoSocial(organizacaoModel.getRazaoSocial());
                            item.setEndereco(organizacaoModel.getEndereco());
                            item.setCnpj(organizacaoModel.getCnpj());
                            item.setCidade(organizacaoModel.getCidade() + ", " + PempecParse.dateToString(new Date()));
                            item.setNumeroDocumento(tituloReceberModel.getNumeroDocumento());

                            SacadoModel sacado = sacadoBO.consultarPorTemplate(tituloReceberModel.getSacado());

                            item.setFavorecido(sacado.getNome());

                            if (sacado.getEndereco() != null && sacado.getEndereco().getCidade() != null && sacado.getEndereco().getBairro() != null && sacado.getEndereco().getEstado() != null) {

                                EnderecoModel endereco = enderecoBO.consultarPorPk(sacado.getEndereco());

                                item.setEnderecoFavorecido(endereco.getLogradouro().toUpperCase() + ", Nº " + endereco.getNumero() + " - " + endereco.getBairro().getDescricao().toUpperCase() + " - CEP : " + endereco.getCep());

                                if (endereco.getComplemento() != null && !endereco.getComplemento().isEmpty()) {

                                    item.setCidadeEstadoFavorecido(endereco.getComplemento() + " - " + endereco.getCidade().getDescricao().toUpperCase() + " - " + endereco.getEstado().getSigla().toUpperCase());

                                } else {

                                    item.setCidadeEstadoFavorecido(endereco.getCidade().getDescricao().toUpperCase() + " - " + endereco.getEstado().getSigla().toUpperCase());

                                }

                            } else {

                                item.setEnderecoFavorecido(" Cadastro incompleto. Falta endereço. ");

                                item.setCidadeEstadoFavorecido(" Complete o cadastro.");

                            }

                            item.setCnpjCpfFavorecido(sacado.getPersonalidade().contains("CPF") ? "CPF: " : "CNPJ: " + sacado.getCpfCnpj());

                            item.setDescricao(tituloReceberModel.getHistorico().getDescricao() + " " + tituloReceberModel.getDescricao());

                            if (sacado.getContato() != null) {
                                item.setContatoFavorecido(sacado.getContato().getTelefone());
                            }

                            List<TituloReceberModel> collTitulos = tituloReceberBO.obterTitulosFilhos(tituloReceberModel);

                            collTitulos.remove(tituloReceberModel);

                            Collection<DynaBean> collection = new ArrayList<DynaBean>();

                            Collection<ReciboTituloReceberCrossTab> collCrossTab = new ArrayList<ReciboTituloReceberCrossTab>();

                            Collection<ReciboTituloReceberSubForma> collFormas = new ArrayList<ReciboTituloReceberSubForma>();

                            Double valorPago = 0d;

                            Double totalAcrescimos = 0d;

                            Double totalDeducoes = 0d;

                            if (baixa != null) {

                                String descricaoFormaInternet = Constantes.FORMA_PAGAMENTO_INTERNET;
                                String descricaoFormaCheque = Constantes.FORMA_PAGAMENTO_CHEQUE;

                                if (baixa.getFormasPagamento() != null && !baixa.getFormasPagamento().isEmpty()) {

                                    for (TituloReceberBaixaFormaPagamentoModel forma : baixa.getFormasPagamento()) {

                                        if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {

                                            ReciboTituloReceberSubForma formaEspecie = new ReciboTituloReceberSubForma();

                                            formaEspecie.setValor(forma.getValor());

                                            formaEspecie.setIdForma(Constantes.FORMA_PAGAMENTO_ESPECIE);

                                            formaEspecie.setForma(forma.getForma().getDescricao());

                                            collFormas.add(formaEspecie);

                                        } else {

                                            if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE)) {
                                                descricaoFormaCheque = forma.getForma().getDescricao();
                                            } else {
                                                descricaoFormaInternet = forma.getForma().getDescricao();
                                            }

                                        }

                                        valorPago += forma.getValor();

                                    }

                                    for (TituloReceberBaixaChequeModel cheque : baixa.getTituloReceberBaixaCheque()) {

                                        ReciboTituloReceberSubForma formaCheque = new ReciboTituloReceberSubForma();

                                        formaCheque.setValor(cheque.getValor());

                                        formaCheque.setIdForma(Constantes.FORMA_PAGAMENTO_CHEQUE);

                                        formaCheque.setForma(descricaoFormaCheque);

                                        formaCheque.setNumeroCheque(cheque.getNumeroCheque());

                                        formaCheque.setConta(cheque.getAgencia() + "/" + cheque.getConta());

                                        collFormas.add(formaCheque);

                                    }

                                    for (TituloReceberBaixaInternetModel internet : baixa.getTituloReceberBaixaInternet()) {

                                        ReciboTituloReceberSubForma formaInternet = new ReciboTituloReceberSubForma();

                                        formaInternet.setValor(internet.getValor());

                                        formaInternet.setIdForma(Constantes.FORMA_PAGAMENTO_INTERNET);

                                        formaInternet.setForma(descricaoFormaInternet);

                                        formaInternet.setContaDestino(internet.getContaBancaria().getConta());

                                        formaInternet.setDescricao(internet.getTipoOperacaoBancaria().getDescricao());

                                        collFormas.add(formaInternet);

                                    }

                                }

                                if (baixa.getAcrescimos() != null && !baixa.getAcrescimos().isEmpty()) {

                                    for (TituloReceberBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                                        ReciboTituloReceberCrossTab crossTab = new ReciboTituloReceberCrossTab();

                                        crossTab.setTipo("Acréscimos");

                                        crossTab.setDescricao(acrescimo.getTipoAcrescimo().getDescricao());

                                        crossTab.setValor(acrescimo.getValor());

                                        collCrossTab.add(crossTab);

                                        totalAcrescimos += acrescimo.getValor();

                                    }

                                }

                                if (baixa.getDeducoes() != null && !baixa.getDeducoes().isEmpty()) {

                                    for (TituloReceberBaixaDeducaoModel deducao : baixa.getDeducoes()) {

                                        ReciboTituloReceberCrossTab crossTab = new ReciboTituloReceberCrossTab();

                                        crossTab.setTipo("Deduções");

                                        crossTab.setDescricao(deducao.getTipoDeducao().getDescricao());

                                        crossTab.setValor(deducao.getValor());

                                        collCrossTab.add(crossTab);

                                        totalDeducoes += deducao.getValor();

                                    }

                                }

                            }

                            item.setValor(tituloReceberModel.getValorNominal());

                            if (baixa != null) {

                                item.setExisteBaixa(true);

                                item.setValorPago(valorPago);

                                if (totalAcrescimos != 0d) {
                                    item.setExisteAcres(true);
                                }

                                item.setTotalAcrescimos(totalAcrescimos);

                                if (totalDeducoes != 0d) {
                                    item.setExisteDed(true);
                                }

                                item.setTotalDeducoes(totalDeducoes);

                                item.setValorExtenso(new Extenso(valorPago).toString().toUpperCase());

                            } else {

                                item.setValorExtenso(new Extenso(tituloReceberModel.getValorNominal()).toString().toUpperCase());

                            }

                            Double valorPagoFilhos = 0d;

                            for (TituloReceberModel titulo : collTitulos) {

                                baixa = tituloReceberBaixaBO.consultarPorTitulo(titulo);

                                Double totalAcrescimosFilhos = 0d;

                                Double totalDeducoesFilhos = 0d;

                                if (baixa != null) {

                                    if (baixa.getFormasPagamento() != null && !baixa.getFormasPagamento().isEmpty()) {

                                        for (TituloReceberBaixaFormaPagamentoModel forma : baixa.getFormasPagamento()) {

                                            valorPagoFilhos += forma.getValor();

                                        }

                                    }

                                    if (baixa.getAcrescimos() != null && !baixa.getAcrescimos().isEmpty()) {

                                        for (TituloReceberBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                                            totalAcrescimosFilhos += acrescimo.getValor();

                                        }

                                    }

                                    if (baixa.getDeducoes() != null && !baixa.getDeducoes().isEmpty()) {

                                        for (TituloReceberBaixaDeducaoModel deducao : baixa.getDeducoes()) {

                                            totalDeducoesFilhos += deducao.getValor();

                                        }

                                    }

                                    DynaBean lazy = new LazyDynaMap();
                                    lazy.set(RelatorioConstantes.PARAMETRO_NUMERO_DOCUMENTO, titulo.getNumeroDocumento());
                                    lazy.set(RelatorioConstantes.PARAMETRO_VALOR, valorPagoFilhos);
                                    lazy.set(RelatorioConstantes.PARAMETRO_DATA_PAGAMENTO, titulo.getDataPagamento());
                                    lazy.set(RelatorioConstantes.PARAMETRO_TOTAL_ACRESCIMOS, totalAcrescimosFilhos);
                                    lazy.set(RelatorioConstantes.PARAMETRO_TOTAL_DEDUCOES, totalDeducoesFilhos);

                                    collection.add(lazy);

                                }

                            }

                            item.setCollection(collection);

                            if (collCrossTab.isEmpty()) {
                                collCrossTab.add(new ReciboTituloReceberCrossTab());
                            }

                            item.setCollCrossTab(collCrossTab);

                            item.setCollFormas(collFormas);

                            lCollection.add(item);

                        }

                    }

                }

            }

            new RelatorioUtil().imprimirImpressoraCollection(RelatorioConstantes.RECIBOS_MULTIPLOS, new HashMap<String, Object>(), lCollection);

            new MovimentoDiarioBO().inserir(this.registroMovimento("Imprimir Mult.Recibo", "Multiplos Recibos", Controller.getUsuarioLogado().getNome() + " solicitou impressao de multiplos recibos de titulos", null, "Impresso"));

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



}//GEN-LAST:event_botaoImprimirActionPerformed

private void botaoPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPesquisarActionPerformed

    try {

        ArrayList<ImpressaoMultiplosRecibosModel> lCollection = new ArrayList<ImpressaoMultiplosRecibosModel>();

        ImpressaoMultiplosRecibosFilter filter = new ImpressaoMultiplosRecibosFilter();

        filter.setOrganizacao(organizacaoModel);

        filter.setDataPagamento(jFTDataPagamento.getDate());

        filter.setNumeroDocumentoInicial(jTNumeroInicial.getText());

        filter.setNumeroDocumentoFinal(jTNumeroFinal.getText());

        List<TituloPagarModel> lTitulosPagar = tituloPagarBO.obterImpressaoMultiplosRecibos(filter);

        for (TituloPagarModel tituloPagarModel : lTitulosPagar) {

            ImpressaoMultiplosRecibosModel item = new ImpressaoMultiplosRecibosModel();

            item.setTipo("TP");

            item.setTituloPagar(tituloPagarModel);

            item.setDocumento(tituloPagarModel.getNumeroDocumento());

            item.setNome(tituloPagarModel.getCedente().getNome());

            item.setDescricao(tituloPagarModel.getHistorico().getDescricao() + " " + tituloPagarModel.getDescricao());

            item.setValor(tituloPagarModel.getValorNominal());

            lCollection.add(item);

        }

        List<TituloReceberModel> lTitulosReceber = tituloReceberBO.obterImpressaoMultiplosRecibos(filter);

        for (TituloReceberModel tituloReceberModel : lTitulosReceber) {

            ImpressaoMultiplosRecibosModel item = new ImpressaoMultiplosRecibosModel();

            item.setTipo("TR");

            item.setTituloReceber(tituloReceberModel);

            item.setDocumento(tituloReceberModel.getNumeroDocumento());

            item.setNome(tituloReceberModel.getSacado().getNome());

            item.setDescricao(tituloReceberModel.getHistorico().getDescricao() + " " + tituloReceberModel.getDescricao());

            item.setValor(tituloReceberModel.getValorNominal());

            lCollection.add(item);

        }

        tableTitulos.setAutoCreateColumnsFromModel(false);
        tableTitulos.setModel(new TableModelImpressaoMultiplosRecibos(lCollection));
        FontMetrics fm = tableTitulos.getFontMetrics(tableTitulos.getFont());
        tableTitulos.setColumnModel(new ColumnModelImpressaoMultiplosRecibos(fm));
        tableTitulos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableTitulos.getTableHeader().setReorderingAllowed(false);

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


}//GEN-LAST:event_botaoPesquisarActionPerformed

private void jRTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRTodosActionPerformed
    for (int i = 0; i < tableTitulos.getRowCount(); i++) {
        tableTitulos.setValueAt(true, i, 0);
    }
}//GEN-LAST:event_jRTodosActionPerformed

private void jRNenhumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRNenhumActionPerformed
    for (int i = 0; i < tableTitulos.getRowCount(); i++) {
        tableTitulos.setValueAt(false, i, 0);
    }
}//GEN-LAST:event_jRNenhumActionPerformed

private void jRTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRTPActionPerformed
    for (int i = 0; i < tableTitulos.getRowCount(); i++) {
        if (tableTitulos.getValueAt(i, 1).toString().equals("TP")) {
            tableTitulos.setValueAt(true, i, 0);
        } else {
            tableTitulos.setValueAt(false, i, 0);
        }
    }
}//GEN-LAST:event_jRTPActionPerformed

private void jRTRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRTRActionPerformed
    for (int i = 0; i < tableTitulos.getRowCount(); i++) {
        if (tableTitulos.getValueAt(i, 1).toString().equals("TR")) {
            tableTitulos.setValueAt(true, i, 0);
        } else {
            tableTitulos.setValueAt(false, i, 0);
        }
    }
}//GEN-LAST:event_jRTRActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoPesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private org.jdesktop.swingx.JXDatePicker jFTDataPagamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton jRNenhum;
    private javax.swing.JRadioButton jRTP;
    private javax.swing.JRadioButton jRTR;
    private javax.swing.JRadioButton jRTodos;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTNumeroFinal;
    private javax.swing.JTextField jTNumeroInicial;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDataVencimento1;
    private javax.swing.JLabel labelDataVencimento2;
    private javax.swing.JTable tableTitulos;
    // End of variables declaration//GEN-END:variables
}
