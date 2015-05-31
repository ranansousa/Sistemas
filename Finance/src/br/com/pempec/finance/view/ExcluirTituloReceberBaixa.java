package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaChequeBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroTituloReceberBaixaAC;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroTituloReceberBaixaDE;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroTituloReceberBaixaFP;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.MaskUtils;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.tables.TableModelExcluirTituloReceberBaixaAC;
import br.com.pempec.finance.utils.tables.TableModelExcluirTituloReceberBaixaDE;
import br.com.pempec.finance.utils.tables.TableModelExcluirTituloReceberBaixaFP;
import br.com.pempec.finance.utils.iterators.TituloReceberTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.FontMetrics;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author EQUIPE PEMPEC
 */
public class ExcluirTituloReceberBaixa extends FinanceInternalFrame implements IRepopulador {

    private TituloReceberBaixaBO tituloReceberBaixaBO = new TituloReceberBaixaBO();
    private TituloReceberBaixaChequeBO tituloReceberBaixaChequeBO = new TituloReceberBaixaChequeBO();
    private TituloReceberBO tituloReceberBO = new TituloReceberBO();
    private long tela = Tela.TELA_CONTAS_A_RECEBER_CANCELAR_BAIXA.getTela();
    private OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

    private String NameObject() {

        return "Exclusão de Recebimento de Títulos";

    }

    public ExcluirTituloReceberBaixa() throws SystemException {

        this.populaTela();

    }

    private void populaTela() {

        initComponents();

        //Instanciando variaveis auxiliares
        auxFormasPagamento = new ArrayList<TituloReceberBaixaFormaPagamentoModel>();

        auxDeducoes = new ArrayList<TituloReceberBaixaDeducaoModel>();

        auxAcrescimos = new ArrayList<TituloReceberBaixaAcrescimoModel>();

        //Escodendo os campo ID do titulo
        campoCodigoTitulo.setVisible(false);
        campoCodigo.setVisible(false);

        //Aplicando tamanho maximo de caracteres do campo.
        //jTDescricao.setDocument(new Documento(80));

        //Iniciando as tabelas.
        this.preencheTabelaFormaPagamento();
        this.preencheTabelaDeducoes();
        this.preencheTabelaAcrescimos();

        //Aplicando mascara em campos.        
        jFTDataVencimento.setFormatterFactory(MaskUtils.mascaraData());
        jFTDataPagamento.setFormatterFactory(MaskUtils.mascaraData());

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelAcrescimo = new javax.swing.JPanel();
        labelNumeroDocumento = new javax.swing.JLabel();
        comboTitulo = new javax.swing.JComboBox();
        labelDataVencimento = new javax.swing.JLabel();
        jFTDataVencimento = new javax.swing.JFormattedTextField();
        labelNF = new javax.swing.JLabel();
        jTNotaFiscal = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        jTDescricao = new javax.swing.JTextField();
        labelValorPagar = new javax.swing.JLabel();
        jFTValorReceber = new br.com.pempec.componentes.JDoubleField();
        labelParcela = new javax.swing.JLabel();
        jTParcela = new javax.swing.JTextField();
        campoCodigoTitulo = new javax.swing.JTextField();
        jFTCpfCnpj = new javax.swing.JFormattedTextField();
        labelCedente1 = new javax.swing.JLabel();
        jTSacado = new javax.swing.JTextField();
        jFTValorTotalAdiantado = new br.com.pempec.componentes.JDoubleField();
        labelValorPagar4 = new javax.swing.JLabel();
        jTLocalPagamento = new javax.swing.JTextField();
        labelDataVencimento1 = new javax.swing.JLabel();
        panelDeducoes = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableFormaPagamento = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAcrescimo = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableDeducao = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jFTDataPagamento = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        labelCentroCusto1 = new javax.swing.JLabel();
        labelCentroCusto = new javax.swing.JLabel();
        labelValorPagar1 = new javax.swing.JLabel();
        jFTValorPago = new br.com.pempec.componentes.JDoubleField();
        campoCodigo = new javax.swing.JTextField();
        jTTipoPagamento = new javax.swing.JTextField();
        jTResponsavel = new javax.swing.JTextField();
        jTCentroCusto = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        botaoExcluir = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - EXCLUIR RECEBIMENTO");
        setPreferredSize(new java.awt.Dimension(834, 686));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        panelAcrescimo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51)), NameObject(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 153, 255)));
        panelAcrescimo.setForeground(new java.awt.Color(153, 153, 153));

        labelNumeroDocumento.setText("Número do Documento");

        comboTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloActionPerformed(evt);
            }
        });

        labelDataVencimento.setText("Data Vencimento");

        jFTDataVencimento.setEditable(false);

        labelNF.setText("Nota Fiscal");

        jTNotaFiscal.setEditable(false);

        labelDescricao.setText("Descrição");

        jTDescricao.setEditable(false);

        labelValorPagar.setText("Valor Receber");

        jFTValorReceber.setEditable(false);

        labelParcela.setText("Parcela");

        jTParcela.setEditable(false);
        jTParcela.setForeground(new java.awt.Color(0, 204, 255));

        campoCodigoTitulo.setEditable(false);

        jFTCpfCnpj.setEditable(false);

        labelCedente1.setText("CPF/CNPJ");

        jTSacado.setEditable(false);
        jTSacado.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        jTSacado.setForeground(new java.awt.Color(0, 204, 255));
        jTSacado.setBorder(javax.swing.BorderFactory.createTitledBorder("Sacado"));

        jFTValorTotalAdiantado.setEditable(false);
        jFTValorTotalAdiantado.setForeground(new java.awt.Color(153, 0, 0));

        labelValorPagar4.setText("Adiantamento");

        jTLocalPagamento.setEditable(false);
        jTLocalPagamento.setForeground(new java.awt.Color(0, 204, 255));

        labelDataVencimento1.setText("Local Pagamento");

        javax.swing.GroupLayout panelAcrescimoLayout = new javax.swing.GroupLayout(panelAcrescimo);
        panelAcrescimo.setLayout(panelAcrescimoLayout);
        panelAcrescimoLayout.setHorizontalGroup(
            panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jTDescricao, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTSacado, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAcrescimoLayout.createSequentialGroup()
                            .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                    .addComponent(comboTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(campoCodigoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelNumeroDocumento))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                    .addComponent(labelDataVencimento1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(4, 4, 4))
                                .addComponent(jTLocalPagamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(labelDescricao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jFTCpfCnpj, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelDataVencimento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelValorPagar, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jFTValorReceber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFTDataVencimento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelCedente1))
                .addGap(47, 47, 47)
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNF)
                    .addComponent(jTNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValorPagar4)
                    .addComponent(jFTValorTotalAdiantado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelParcela)
                    .addComponent(jTParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );
        panelAcrescimoLayout.setVerticalGroup(
            panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAcrescimoLayout.createSequentialGroup()
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                .addComponent(labelNumeroDocumento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(campoCodigoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelDataVencimento)
                                    .addComponent(labelParcela)
                                    .addComponent(labelDataVencimento1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jFTDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTLocalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                .addComponent(labelDescricao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                .addComponent(labelValorPagar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTValorReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAcrescimoLayout.createSequentialGroup()
                        .addComponent(labelValorPagar4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTValorTotalAdiantado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTSacado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAcrescimoLayout.createSequentialGroup()
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelCedente1)
                            .addComponent(labelNF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );

        panelDeducoes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 51)), "Recebimento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 153, 255)));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Forma de Pagamento"));

        tableFormaPagamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tableFormaPagamento);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pagamento", jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 0)), "Acréscimos"));

        tableAcrescimo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableAcrescimo);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Acréscimos", jPanel5);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 51)), "Deduções"));

        tableDeducao.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tableDeducao);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Deduções", jPanel7);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Data Pagamento");

        jFTDataPagamento.setEditable(false);

        jLabel5.setText("Tipo Pagamento");

        labelCentroCusto1.setText("Responsavel");

        labelCentroCusto.setText("Centro Custo");

        labelValorPagar1.setText("Valor Pago");

        jFTValorPago.setEditable(false);
        jFTValorPago.setForeground(new java.awt.Color(153, 0, 153));

        campoCodigo.setEditable(false);

        jTTipoPagamento.setEditable(false);

        jTResponsavel.setEditable(false);

        jTCentroCusto.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jFTDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCentroCusto1))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCentroCusto))
                .addGap(33, 33, 33)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jFTValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelValorPagar1))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelCentroCusto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelCentroCusto1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelValorPagar1)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jFTValorPago, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jFTDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDeducoesLayout = new javax.swing.GroupLayout(panelDeducoes);
        panelDeducoes.setLayout(panelDeducoesLayout);
        panelDeducoesLayout.setHorizontalGroup(
            panelDeducoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDeducoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDeducoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelDeducoesLayout.setVerticalGroup(
            panelDeducoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeducoesLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelDeducoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAcrescimo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDeducoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoExcluir.setMnemonic('E');
        botaoExcluir.setText("Excluir");
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
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
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoExcluir, botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo utilizado para repopular todos os combos deste JinternalFrame.
     */
    public void repopularCombos() {

        try {

            //colecoes
            Collection<TituloReceberModel> lColecaoTituloReceber = tituloReceberBO.obterTodosPagos(organizacaoModel);

            final EventList<TituloReceberModel> lRegistros = GlazedLists.eventList(lColecaoTituloReceber);
            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboTitulo, lRegistros, new TituloReceberTextFilterator());
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

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        botaoExcluir.setEnabled(true);
        campoCodigoTitulo.setText("");
        jTDescricao.setText("");
        jTNotaFiscal.setText("");
        jTParcela.setText("");
        jFTCpfCnpj.setValue(null);
        jFTDataVencimento.setValue(null);
        jFTValorReceber.setText("0,00");
        jFTValorTotalAdiantado.setText("0,00");
        jTSacado.setText("");
        jTResponsavel.setText("");
        jTCentroCusto.setText("");
        jTLocalPagamento.setText("");
        jTTipoPagamento.setText("");
        comboTitulo.setSelectedItem(null);
        jFTDataPagamento.setValue(null);

        this.limparCamposBaixa();

    }//GEN-LAST:event_botaoLimparActionPerformed

    private void limparCamposBaixa() {

        campoCodigo.setText("");

        jFTValorPago.setText("0,00");

        jFTDataPagamento.setValue(null);

        auxFormasPagamento = new ArrayList<TituloReceberBaixaFormaPagamentoModel>();

        auxDeducoes = new ArrayList<TituloReceberBaixaDeducaoModel>();

        auxAcrescimos = new ArrayList<TituloReceberBaixaAcrescimoModel>();

        this.preencheTabelaAcrescimos();

        this.preencheTabelaDeducoes();

        this.preencheTabelaFormaPagamento();

    }

    private Boolean validaParametros() {

        try {

            if (Controller.verificaParametroAtivo(Parametro.CCANR001.getCodigo())) {
                exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR001.getCodigo()), null);
                return false;
            }

            if (Controller.verificaParametroAtivo(Parametro.CCANR004.getCodigo())) {
                if (tituloReceberModel.getValorNominal().doubleValue() > Controller.findByCodigo(Parametro.CCANR004.getCodigo()).getValor().doubleValue()) {
                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR004.getCodigo()), null);
                    return false;
                }
            }

            titulos = tituloReceberBO.obterTitulosFilhos(tituloReceberModel);

            if (titulos != null && titulos.size() > 1) {

                if (Controller.verificaParametroAtivo(Parametro.CCANR002.getCodigo())) {
                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR002.getCodigo()), null);
                    return false;
                }

            }

            //O título possui filhos, verificar se algum dos filhos foram pagos nas condições pré-determinadas.
            if (titulos != null && titulos.size() > 1) {

                for (TituloReceberModel titulo : titulos) {

                    if (titulo.getLoteContabil() != null && titulo.getLoteContabil().getPk() != null) {
                        if (!Controller.verificaParametroAtivo(Parametro.CCANR005.getCodigo())) {
                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR005.getCodigo()), null);
                            return false;
                        }
                    }

                    //Obtendo sua Baixa
                    tituloReceberBaixaModel = tituloReceberBaixaBO.consultarPorTitulo(titulo);

                    if (tituloReceberBaixaModel != null && tituloReceberBaixaModel.getPk() != null) {

                        if (tituloReceberBaixaModel.getFormasPagamento() != null) {

                            for (TituloReceberBaixaFormaPagamentoModel tituloReceberBaixaFormaPagamentoModel : tituloReceberBaixaModel.getFormasPagamento()) {

                                if (Controller.verificaParametroAtivo(Parametro.CCANR003.getCodigo())) {
                                    if (tituloReceberBaixaFormaPagamentoModel.getForma() != null && tituloReceberBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {
                                        if (!Controller.verificaParametroAtivo(Parametro.CCANR00301.getCodigo())) {
                                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR00301.getCodigo()), null);
                                            return false;
                                        }
                                    }

                                    if (tituloReceberBaixaFormaPagamentoModel.getForma() != null && tituloReceberBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE) && tituloReceberBaixaModel.getTituloReceberBaixaCheque() != null) {

                                        if (!Controller.verificaParametroAtivo(Parametro.CCANR00302.getCodigo())) {
                                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR00302.getCodigo()), null);
                                            return false;
                                        }

                                    }

                                    if (tituloReceberBaixaFormaPagamentoModel.getForma() != null && tituloReceberBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_INTERNET)) {
                                        if (!Controller.verificaParametroAtivo(Parametro.CCANR00303.getCodigo())) {
                                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR00303.getCodigo()), null);
                                            return false;
                                        }
                                    }

                                }

                            }

                        }

                    }

                }

            } else {

                if (tituloReceberModel.getLoteContabil() != null && tituloReceberModel.getLoteContabil().getPk() != null) {
                    if (!Controller.verificaParametroAtivo(Parametro.CCANR005.getCodigo())) {
                        exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR005.getCodigo()), null);
                        return false;
                    }
                }

                if (tituloReceberBaixaModel.getFormasPagamento() != null) {

                    for (TituloReceberBaixaFormaPagamentoModel tituloReceberBaixaFormaPagamentoModel : tituloReceberBaixaModel.getFormasPagamento()) {

                        if (Controller.verificaParametroAtivo(Parametro.CCANR003.getCodigo())) {
                            if (tituloReceberBaixaFormaPagamentoModel.getForma() != null && tituloReceberBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {
                                if (!Controller.verificaParametroAtivo(Parametro.CCANR00301.getCodigo())) {
                                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR00301.getCodigo()), null);
                                    return false;
                                }
                            }

                            if (tituloReceberBaixaFormaPagamentoModel.getForma() != null && tituloReceberBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE) && tituloReceberBaixaModel.getTituloReceberBaixaCheque() != null) {

                                if (!Controller.verificaParametroAtivo(Parametro.CCANR00302.getCodigo())) {
                                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR00302.getCodigo()), null);
                                    return false;
                                }

                            }

                            if (tituloReceberBaixaFormaPagamentoModel.getForma() != null && tituloReceberBaixaFormaPagamentoModel.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_INTERNET)) {
                                if (!Controller.verificaParametroAtivo(Parametro.CCANR00303.getCodigo())) {
                                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCANR00303.getCodigo()), null);
                                    return false;
                                }
                            }

                        }

                    }

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

        return true;

    }

    private Boolean validaCampos() {

        if (jTSacado.getText().isEmpty() || tituloReceberModel == null) {
            exibeMensagemAviso("Selecione um título.!", null);
            return false;
        }

        return true;

    }

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        String valorCombo = null;

        if (comboTitulo.getSelectedItem() != null) {
            valorCombo = comboTitulo.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (validaCampos()) {

                    if (!validaParametros()) {
                        return;
                    }

                    if (titulos != null && titulos.size() > 1) {

                        for (TituloReceberModel titulo : titulos) {

                            //Obtendo sua Baixa
                            tituloReceberBaixaModel = tituloReceberBaixaBO.consultarPorTitulo(titulo);

                            if (tituloReceberBaixaModel != null && tituloReceberBaixaModel.getPk() != null) {

                                tituloReceberBaixaModel.setMovimentoDiarioModel(registroMovimento("excluirPagamento", valorCombo, tituloReceberModel.getHistorico() + " " + tituloReceberModel.getDescricao(), tituloReceberBaixaModel.getValorPago(), "pagamento excluído"));

                                tituloReceberBaixaBO.excluir(tituloReceberBaixaModel, titulo);

                            }

                        }

                    } else {

                        tituloReceberBaixaModel.setMovimentoDiarioModel(registroMovimento("ExcluirRecebimento", valorCombo, tituloReceberModel.getHistorico() + " " + tituloReceberModel.getDescricao(), tituloReceberBaixaModel.getValorPago(), "Recebimento excluído"));
                        tituloReceberBaixaBO.excluir(tituloReceberBaixaModel, tituloReceberModel);

                    }

                    this.botaoLimparActionPerformed(evt);

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

            exibeMensagemAviso("Selecione um Título.", null);

        }


}//GEN-LAST:event_botaoExcluirActionPerformed

private void comboTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTituloActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTitulo.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            TituloReceberModel tab = new TituloReceberModel();

            tab.setNumeroDocumento(comboTitulo.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = tituloReceberBO.consultarPorTemplatePago(tab);

            if (tab != null && tab.getPk() != null) {

                if (tab.getLoteContabil() == null) {
                    this.popularCampos(tab);
                } else {
                    exibeMensagemAviso("O título foi exportado. Favor remover o lote sob num." + tab.getLoteContabil().getLote(), null);
                    return;
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


}//GEN-LAST:event_comboTituloActionPerformed

    private void popularCampos(TituloReceberModel tab) {

        try {

            if (tab != null && tab.getPk() != null) {

                botaoExcluir.setEnabled(true);

                tituloReceberModel = tab;

                campoCodigoTitulo.setText(tab.getPk().getId());

                comboTitulo.setSelectedItem(tab.getNumeroDocumento());

                if (tab.getDataVencimento() != null) {

                    jFTDataVencimento.setText(PempecParse.dateToString(tab.getDataVencimento()));

                }

                if (tab.getHistorico() != null) {

                    jTDescricao.setText(tab.getHistorico().getDescricao() + " " + tab.getDescricao());

                }

                jFTValorTotalAdiantado.setText(PempecParse.doubleToZero(tab.getValorAntecipado()));


                jTParcela.setText(tab.getParcela());

                if (tab.getSacado() != null) {

                    jTSacado.setText(tab.getSacado().getNome());

                    jFTCpfCnpj.setValue(null);

                    if (tab.getSacado().getPersonalidade().equals("PF")) {

                        jFTCpfCnpj.setFormatterFactory(MaskUtils.mascaraCpf());

                    } else {

                        jFTCpfCnpj.setFormatterFactory(MaskUtils.mascaraCnpj());

                    }

                    jFTCpfCnpj.setText(tab.getSacado().getCpfCnpj());

                }

                if (tab.getLocalPagamento() != null) {
                    jTLocalPagamento.setText(tab.getLocalPagamento().getDescricao());
                }

                if (tab.getCentroCusto() != null) {
                    jTCentroCusto.setText(tab.getCentroCusto().getDescricao());
                }

                if (tab.getResponsavel() != null) {
                    jTResponsavel.setText(tab.getResponsavel().getNome());
                }

                //Limpando 
                this.limparCamposBaixa();

                //Verificando Campos da Baixa.

                TituloReceberBaixaModel baixa = tituloReceberBaixaBO.consultarPorTitulo(tituloReceberModel);

                if (baixa != null && baixa.getPk() != null) {

                    //Selecionar os campos.

                    tituloReceberBaixaModel = baixa;

                    Collection<TituloReceberBaixaChequeModel> auxBaixaCheques = tituloReceberBaixaChequeBO.obterPorTituloReceberBaixa(baixa);

                    Set<TituloReceberBaixaChequeModel> baixaCheques = new HashSet<TituloReceberBaixaChequeModel>();

                    baixaCheques.addAll(auxBaixaCheques);

                    baixa.setTituloReceberBaixaCheque(baixaCheques);

                    campoCodigo.setText(baixa.getPk().getId());

                    if (tab.getDataPagamento() != null) {

                        jFTDataPagamento.setText(PempecParse.dateToString(tab.getDataPagamento()));

                    }

                    if (tab.getStatus().getPk().getId().equals(Constantes.STATUS_TITULO_PAGO)) {
                        jTTipoPagamento.setText(Constantes.TIPO_BAIXA_TITULO_TOTAL);
                    } else {
                        jTTipoPagamento.setText(Constantes.TIPO_BAIXA_TITULO_PARCIAL);
                    }

                    //Preenchendo as tabelas
                    if (baixa.getFormasPagamento() != null && !baixa.getFormasPagamento().isEmpty()) {
                        

                        auxFormasPagamento.addAll(baixa.getFormasPagamento());
                        this.preencheTabelaFormaPagamento();

                    }

                    if (baixa.getDeducoes() != null && !baixa.getDeducoes().isEmpty()) {

                        auxDeducoes.addAll(baixa.getDeducoes());
                        this.preencheTabelaDeducoes();

                    }

                    if (baixa.getAcrescimos() != null && !baixa.getAcrescimos().isEmpty()) {

                        auxAcrescimos.addAll(baixa.getAcrescimos());
                        this.preencheTabelaAcrescimos();

                    }

                    jFTValorPago.setText(PempecParse.doubleToZero(this.calculaValorPago()));

                    jFTValorReceber.setText(PempecParse.doubleToZero(tab.getValorNominal() + this.calculaValorAReceber()));

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoCodigoTitulo;
    private javax.swing.JComboBox comboTitulo;
    private javax.swing.JFormattedTextField jFTCpfCnpj;
    private javax.swing.JFormattedTextField jFTDataPagamento;
    private javax.swing.JFormattedTextField jFTDataVencimento;
    private br.com.pempec.componentes.JDoubleField jFTValorPago;
    private br.com.pempec.componentes.JDoubleField jFTValorReceber;
    private br.com.pempec.componentes.JDoubleField jFTValorTotalAdiantado;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTCentroCusto;
    private javax.swing.JTextField jTDescricao;
    private javax.swing.JTextField jTLocalPagamento;
    private javax.swing.JTextField jTNotaFiscal;
    private javax.swing.JTextField jTParcela;
    private javax.swing.JTextField jTResponsavel;
    private javax.swing.JTextField jTSacado;
    private javax.swing.JTextField jTTipoPagamento;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCedente1;
    private javax.swing.JLabel labelCentroCusto;
    private javax.swing.JLabel labelCentroCusto1;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDataVencimento1;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelNF;
    private javax.swing.JLabel labelNumeroDocumento;
    private javax.swing.JLabel labelParcela;
    private javax.swing.JLabel labelValorPagar;
    private javax.swing.JLabel labelValorPagar1;
    private javax.swing.JLabel labelValorPagar4;
    private javax.swing.JPanel panelAcrescimo;
    private javax.swing.JPanel panelDeducoes;
    private javax.swing.JTable tableAcrescimo;
    private javax.swing.JTable tableDeducao;
    private javax.swing.JTable tableFormaPagamento;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private TituloReceberModel tituloReceberModel = null;
    private TituloReceberBaixaModel tituloReceberBaixaModel;
    private ArrayList<TituloReceberBaixaFormaPagamentoModel> auxFormasPagamento;
    private ArrayList<TituloReceberBaixaDeducaoModel> auxDeducoes;
    private ArrayList<TituloReceberBaixaAcrescimoModel> auxAcrescimos;
    private List<TituloReceberModel> titulos;

    //Funções Auxiliares
    private void preencheTabelaFormaPagamento() {

        tableFormaPagamento.setAutoCreateColumnsFromModel(false);
        tableFormaPagamento.setModel(new TableModelExcluirTituloReceberBaixaFP(auxFormasPagamento));
        FontMetrics fm = tableFormaPagamento.getFontMetrics(tableFormaPagamento.getFont());
        tableFormaPagamento.setColumnModel(new ColumnModelCadastroTituloReceberBaixaFP(fm));
        tableFormaPagamento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableFormaPagamento.getTableHeader().setReorderingAllowed(false);

    }

    private void preencheTabelaDeducoes() {

        tableDeducao.setAutoCreateColumnsFromModel(false);
        tableDeducao.setModel(new TableModelExcluirTituloReceberBaixaDE(auxDeducoes));
        FontMetrics fm = tableDeducao.getFontMetrics(tableDeducao.getFont());
        tableDeducao.setColumnModel(new ColumnModelCadastroTituloReceberBaixaDE(fm));
        tableDeducao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableDeducao.getTableHeader().setReorderingAllowed(false);

    }

    private void preencheTabelaAcrescimos() {

        tableAcrescimo.setAutoCreateColumnsFromModel(false);
        tableAcrescimo.setModel(new TableModelExcluirTituloReceberBaixaAC(auxAcrescimos));
        FontMetrics fm = tableAcrescimo.getFontMetrics(tableAcrescimo.getFont());
        tableAcrescimo.setColumnModel(new ColumnModelCadastroTituloReceberBaixaAC(fm));
        tableAcrescimo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableAcrescimo.getTableHeader().setReorderingAllowed(false);

    }

    private Double calculaValorPago() {

        Double retorno = 0d;

        for (TituloReceberBaixaFormaPagamentoModel tituloReceberBaixaFormaPagamentoModel : auxFormasPagamento) {

            retorno += tituloReceberBaixaFormaPagamentoModel.getValor();

        }

        return retorno;

    }

    private Double calculaValorAReceber() {

        Double retorno = 0d;

        for (TituloReceberBaixaAcrescimoModel tituloReceberBaixaAcrescimoModel : auxAcrescimos) {

            retorno += tituloReceberBaixaAcrescimoModel.getValor();

        }

        for (TituloReceberBaixaDeducaoModel tituloReceberBaixaDeducaoModel : auxDeducoes) {

            retorno -= tituloReceberBaixaDeducaoModel.getValor();

        }

        return retorno;

    }
}
