package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CentroCustoBO;
import br.com.pempec.finance.businessObjects.FormaPagamentoBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.TipoAcrescimoBO;
import br.com.pempec.finance.businessObjects.TipoDeducaoBO;
import br.com.pempec.finance.businessObjects.TipoNotaFiscalBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.NotaFiscalEmitidaModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoAcrescimoModel;
import br.com.pempec.finance.models.TipoDeducaoModel;
import br.com.pempec.finance.models.TipoNotaFiscalModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloReceberBaixaInternetModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroTituloReceberBaixaAC;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroTituloReceberBaixaDE;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroTituloReceberBaixaFP;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopuladorNew;
import br.com.pempec.finance.utils.MaskUtils;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.tables.TableModelCadastroTituloReceberBaixaAC;
import br.com.pempec.finance.utils.tables.TableModelCadastroTituloReceberBaixaDE;
import br.com.pempec.finance.utils.tables.TableModelCadastroTituloReceberBaixaFP;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.TituloReceberTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author EQUIPE PEMPEC
 */
public class CadastroTituloReceberBaixa extends FinanceInternalFrame implements IRepopuladorNew {

    private TituloReceberBaixaBO tituloReceberBaixaBO = new TituloReceberBaixaBO();
    private CentroCustoBO centroCustoBO = new CentroCustoBO();
    private FormaPagamentoBO formaPagamentoBO = new FormaPagamentoBO();
    private TipoDeducaoBO tipoDeducaoBO = new TipoDeducaoBO();
    private TipoAcrescimoBO tipoAcrescimoBO = new TipoAcrescimoBO();
    private TituloReceberBO tituloReceberBO = new TituloReceberBO();
    private TipoNotaFiscalBO tipoNotaFiscalBO = new TipoNotaFiscalBO();
    private FuncionarioBO responsavelBO = new FuncionarioBO();
    private long tela = Tela.TELA_CONTAS_A_RECEBER_BAIXA.getTela();

    private String NameObject() {
        return (String) "Recebimento de Títulos";
    }

    public CadastroTituloReceberBaixa(TituloReceberModel titulo) throws SystemException {

        this.populaTela();

        this.repopularCombos(Tela.TELA_PRINCIPAL, null);

        this.popularCampos(titulo);

        comboTitulo.setSelectedItem(titulo.getNumeroDocumento().toString());

        if (titulo.getCentroCusto() != null) {

            for (int i = 1; i < comboCentroCusto.getItemCount(); i++) {
                if (titulo.getCentroCusto().getPk().getId().equalsIgnoreCase(((CentroCustoModel) comboCentroCusto.getItemAt(i)).getPk().getId())) {
                    comboCentroCusto.setSelectedIndex(i);
                    break;
                }
            }

        }

        if (titulo.getResponsavel() != null) {

            for (int i = 1; i < comboResponsavel.getItemCount(); i++) {
                if (titulo.getResponsavel().getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboResponsavel.getItemAt(i)).getPk().getId())) {
                    comboResponsavel.setSelectedIndex(i);
                    break;
                }
            }

        }


    }

    public CadastroTituloReceberBaixa() throws SystemException {

        this.populaTela();

    }

    private void populaTela() {

        initComponents();

        campoCodigo.setText(PempecKeyGenerator.generateKey());

        comboTipoPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[]{Constantes.TIPO_BAIXA_TITULO_TOTAL, Constantes.TIPO_BAIXA_TITULO_PARCIAL}));

        //Instanciando variaveis auxiliares
        auxFormasPagamento = new ArrayList<TituloReceberBaixaFormaPagamentoModel>();

        auxDeducoes = new ArrayList<TituloReceberBaixaDeducaoModel>();

        auxAcrescimos = new ArrayList<TituloReceberBaixaAcrescimoModel>();

        auxCheques = new ArrayList<TituloReceberBaixaChequeModel>();

        auxInternet = new ArrayList<TituloReceberBaixaInternetModel>();

        //bloqueando os campos para usuario nao editar. Apenas visualizar dados
        jTParcela.setEditable(false);
        jFTValorReceber.setEditable(false);
        jTSacado.setEditable(false);
        jFTDataVencimento.setEditable(false);
        jTDescricao.setEditable(false);
        jTNotaFiscal.setEditable(false);
        jTDescricao.setEditable(false);


        //comboLocalPagamento.setEditable(false);


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


    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panelAcrescimo = new javax.swing.JPanel();
        labelNumeroDocumento = new javax.swing.JLabel();
        comboTitulo = new javax.swing.JComboBox();
        labelDataVencimento = new javax.swing.JLabel();
        jFTDataVencimento = new javax.swing.JFormattedTextField();
        labelNFTitulo = new javax.swing.JLabel();
        jTNotaFiscal = new javax.swing.JTextField();
        labelDescricao = new javax.swing.JLabel();
        jTDescricao = new javax.swing.JTextField();
        labelValorPagar = new javax.swing.JLabel();
        jFTValorReceber = new br.com.pempec.componentes.JDoubleField();
        labelParcela = new javax.swing.JLabel();
        jTParcela = new javax.swing.JTextField();
        campoCodigoTitulo = new javax.swing.JTextField();
        jTSacado = new javax.swing.JTextField();
        jFTValorTotalAdiantado = new br.com.pempec.componentes.JDoubleField();
        labelValorPagar4 = new javax.swing.JLabel();
        labelCpfCnpj = new javax.swing.JTextField();
        labelCga = new javax.swing.JTextField();
        panelDeducoes = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboFormaPagamento = new javax.swing.JComboBox();
        labelValorPagar2 = new javax.swing.JLabel();
        jFTValorFormaPagamento = new br.com.pempec.componentes.JDoubleField();
        btnIncluirFormaPagamento = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableFormaPagamento = new javax.swing.JTable();
        btnRemoverFormaPagamento = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboAcrescimo = new javax.swing.JComboBox();
        labelValorPagar3 = new javax.swing.JLabel();
        jFTValorAcrescimo = new br.com.pempec.componentes.JDoubleField();
        btnIncluirAcrescimo = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableAcrescimo = new javax.swing.JTable();
        btnRemoverAcrescimo = new javax.swing.JButton();
        abaDeducao = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        comboDeducao = new javax.swing.JComboBox();
        labelValorPagar5 = new javax.swing.JLabel();
        jFTValorDeducao = new br.com.pempec.componentes.JDoubleField();
        btnIncluirDeducao = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableDeducao = new javax.swing.JTable();
        btnRemoverDeducao = new javax.swing.JButton();
        abaNF = new javax.swing.JPanel();
        labelNF = new javax.swing.JLabel();
        labelSerieNF = new javax.swing.JLabel();
        labelSubSerieNF = new javax.swing.JLabel();
        labelDataEmissaoNF = new javax.swing.JLabel();
        labelDataRetencaoNF = new javax.swing.JLabel();
        labelValoIssNF = new javax.swing.JLabel();
        labelAliquotaNF = new javax.swing.JLabel();
        labelTipoDocumentoNF = new javax.swing.JLabel();
        labelObservacaoNF = new javax.swing.JLabel();
        labelDescricaoNF = new javax.swing.JLabel();
        labelDataRegistroNF = new javax.swing.JLabel();
        labelDataProtocoloNF = new javax.swing.JLabel();
        labelBaseCalculoNF = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        comboTipoPagamento = new javax.swing.JComboBox();
        labelCentroCusto1 = new javax.swing.JLabel();
        comboResponsavel = new javax.swing.JComboBox();
        labelCentroCusto = new javax.swing.JLabel();
        comboCentroCusto = new javax.swing.JComboBox();
        labelValorPagar1 = new javax.swing.JLabel();
        jFTValorRecebido = new br.com.pempec.componentes.JDoubleField();
        campoCodigo = new javax.swing.JTextField();
        jFTDataPagamento = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        botaoSalvar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - RECEBIMENTO DE TÍTULOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        panelAcrescimo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)), NameObject(), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 153, 255)));
        panelAcrescimo.setForeground(new java.awt.Color(153, 153, 153));

        labelNumeroDocumento.setText("Número do Documento");

        comboTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloActionPerformed(evt);
            }
        });

        labelDataVencimento.setText("Vencimento");

        jFTDataVencimento.setEditable(false);
        jFTDataVencimento.setForeground(new java.awt.Color(255, 255, 255));
        jFTDataVencimento.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        labelNFTitulo.setText("Nota Fiscal");

        jTNotaFiscal.setEditable(false);
        jTNotaFiscal.setHorizontalAlignment(javax.swing.JTextField.TRAILING);

        labelDescricao.setText("Histórico e Descrição do Título");

        jTDescricao.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTDescricao.setForeground(new java.awt.Color(0, 153, 255));

        labelValorPagar.setText("Valor a Receber");

        labelParcela.setText("Parcela");

        jTParcela.setForeground(new java.awt.Color(0, 102, 204));
        jTParcela.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        campoCodigoTitulo.setEditable(false);

        jTSacado.setEditable(false);
        jTSacado.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        jTSacado.setForeground(new java.awt.Color(0, 102, 204));
        jTSacado.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        jTSacado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "SACADO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        jFTValorTotalAdiantado.setEditable(false);
        jFTValorTotalAdiantado.setForeground(new java.awt.Color(0, 102, 204));

        labelValorPagar4.setText("Adiantamento");

        labelCpfCnpj.setEditable(false);
        labelCpfCnpj.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelCpfCnpj.setForeground(new java.awt.Color(0, 102, 204));
        labelCpfCnpj.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        labelCpfCnpj.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CPF/CNPJ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelCga.setEditable(false);
        labelCga.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelCga.setForeground(new java.awt.Color(102, 102, 102));
        labelCga.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        labelCga.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CGA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        javax.swing.GroupLayout panelAcrescimoLayout = new javax.swing.GroupLayout(panelAcrescimo);
        panelAcrescimo.setLayout(panelAcrescimoLayout);
        panelAcrescimoLayout.setHorizontalGroup(
            panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(panelAcrescimoLayout.createSequentialGroup()
                            .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelNumeroDocumento)
                                .addComponent(comboTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addComponent(campoCodigoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelParcela)))
                        .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelAcrescimoLayout.createSequentialGroup()
                            .addComponent(jTSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelDescricao))
                .addGap(27, 27, 27)
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelDataVencimento)
                        .addComponent(labelValorPagar)
                        .addComponent(jFTDataVencimento))
                    .addComponent(jFTValorReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAcrescimoLayout.createSequentialGroup()
                        .addComponent(labelValorPagar4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jFTValorTotalAdiantado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTNotaFiscal, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelNFTitulo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelCga, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        panelAcrescimoLayout.setVerticalGroup(
            panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAcrescimoLayout.createSequentialGroup()
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNFTitulo)
                            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jTNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jFTValorTotalAdiantado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(labelCga, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(labelValorPagar4))))
                    .addGroup(panelAcrescimoLayout.createSequentialGroup()
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDataVencimento)
                                    .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jFTDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(12, 12, 12)
                                .addComponent(labelValorPagar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTValorReceber, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelAcrescimoLayout.createSequentialGroup()
                                        .addComponent(labelNumeroDocumento)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(comboTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoCodigoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(labelParcela))
                                .addGap(12, 12, 12)
                                .addComponent(labelDescricao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(16, 16, 16)
                        .addGroup(panelAcrescimoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTSacado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCpfCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        panelDeducoes.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 255)), "Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(51, 153, 255)));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Forma de Pagamento"));

        jLabel1.setText("Forma");

        comboFormaPagamento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFormaPagamentoItemStateChanged(evt);
            }
        });

        labelValorPagar2.setText("Valor");

        btnIncluirFormaPagamento.setBackground(new java.awt.Color(153, 153, 255));
        btnIncluirFormaPagamento.setText("Incluir");
        btnIncluirFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirFormaPagamentoActionPerformed(evt);
            }
        });

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

        btnRemoverFormaPagamento.setBackground(new java.awt.Color(153, 153, 255));
        btnRemoverFormaPagamento.setText("Remover");
        btnRemoverFormaPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverFormaPagamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnRemoverFormaPagamento)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(comboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(41, 41, 41)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jFTValorFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(77, 77, 77)
                                    .addComponent(btnIncluirFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelValorPagar2)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelValorPagar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTValorFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIncluirFormaPagamento)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRemoverFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pagamento", jPanel4);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Acréscimos"));

        jLabel2.setText("Acréscimo");

        labelValorPagar3.setText("Valor");

        btnIncluirAcrescimo.setBackground(new java.awt.Color(153, 153, 255));
        btnIncluirAcrescimo.setText("Incluir");
        btnIncluirAcrescimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirAcrescimoActionPerformed(evt);
            }
        });

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

        btnRemoverAcrescimo.setBackground(new java.awt.Color(153, 153, 255));
        btnRemoverAcrescimo.setText("Remover");
        btnRemoverAcrescimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverAcrescimoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(490, 490, 490)
                        .addComponent(btnRemoverAcrescimo))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(comboAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(41, 41, 41)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jFTValorAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(155, 155, 155)
                                    .addComponent(btnIncluirAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelValorPagar3)))
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(labelValorPagar3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTValorAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIncluirAcrescimo)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemoverAcrescimo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Acréscimos", jPanel5);

        abaDeducao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Deduções"));

        jLabel6.setText("Dedução");

        labelValorPagar5.setText("Valor");

        btnIncluirDeducao.setBackground(new java.awt.Color(153, 153, 255));
        btnIncluirDeducao.setText("Incluir");
        btnIncluirDeducao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirDeducaoActionPerformed(evt);
            }
        });

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

        btnRemoverDeducao.setBackground(new java.awt.Color(153, 153, 255));
        btnRemoverDeducao.setText("Remover");
        btnRemoverDeducao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverDeducaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout abaDeducaoLayout = new javax.swing.GroupLayout(abaDeducao);
        abaDeducao.setLayout(abaDeducaoLayout);
        abaDeducaoLayout.setHorizontalGroup(
            abaDeducaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDeducaoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaDeducaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaDeducaoLayout.createSequentialGroup()
                        .addGap(490, 490, 490)
                        .addComponent(btnRemoverDeducao))
                    .addGroup(abaDeducaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaDeducaoLayout.createSequentialGroup()
                            .addGroup(abaDeducaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(comboDeducao, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(41, 41, 41)
                            .addGroup(abaDeducaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(abaDeducaoLayout.createSequentialGroup()
                                    .addComponent(jFTValorDeducao, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(155, 155, 155)
                                    .addComponent(btnIncluirDeducao, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(labelValorPagar5)))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        abaDeducaoLayout.setVerticalGroup(
            abaDeducaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaDeducaoLayout.createSequentialGroup()
                .addGroup(abaDeducaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaDeducaoLayout.createSequentialGroup()
                        .addComponent(labelValorPagar5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaDeducaoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTValorDeducao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIncluirDeducao)))
                    .addGroup(abaDeducaoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboDeducao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemoverDeducao, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jTabbedPane1.addTab("Deduções", abaDeducao);

        abaNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102))));

        labelNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelNF.setForeground(new java.awt.Color(0, 153, 153));
        labelNF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Número da Nota Fiscal"));

        labelSerieNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelSerieNF.setForeground(new java.awt.Color(0, 153, 153));
        labelSerieNF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSerieNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Série"));

        labelSubSerieNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelSubSerieNF.setForeground(new java.awt.Color(0, 153, 153));
        labelSubSerieNF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSubSerieNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Sub-Série"));

        labelDataEmissaoNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelDataEmissaoNF.setForeground(new java.awt.Color(0, 153, 153));
        labelDataEmissaoNF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataEmissaoNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Emissão"));

        labelDataRetencaoNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelDataRetencaoNF.setForeground(new java.awt.Color(0, 153, 153));
        labelDataRetencaoNF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataRetencaoNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Retenção"));

        labelValoIssNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelValoIssNF.setForeground(new java.awt.Color(0, 153, 153));
        labelValoIssNF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelValoIssNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor Iss Retido"));

        labelAliquotaNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelAliquotaNF.setForeground(new java.awt.Color(0, 153, 153));
        labelAliquotaNF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAliquotaNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Aliq."));

        labelTipoDocumentoNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelTipoDocumentoNF.setForeground(new java.awt.Color(0, 153, 153));
        labelTipoDocumentoNF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTipoDocumentoNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Espécie do Documento"));

        labelObservacaoNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelObservacaoNF.setForeground(new java.awt.Color(0, 153, 153));
        labelObservacaoNF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelObservacaoNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Observação"));

        labelDescricaoNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelDescricaoNF.setForeground(new java.awt.Color(0, 153, 153));
        labelDescricaoNF.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDescricaoNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Histórico / Descrição"));

        labelDataRegistroNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelDataRegistroNF.setForeground(new java.awt.Color(0, 153, 153));
        labelDataRegistroNF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataRegistroNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Registro"));

        labelDataProtocoloNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelDataProtocoloNF.setForeground(new java.awt.Color(0, 153, 153));
        labelDataProtocoloNF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataProtocoloNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Protocolo"));

        labelBaseCalculoNF.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelBaseCalculoNF.setForeground(new java.awt.Color(0, 153, 153));
        labelBaseCalculoNF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelBaseCalculoNF.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Base Calculo ISS"));

        javax.swing.GroupLayout abaNFLayout = new javax.swing.GroupLayout(abaNF);
        abaNF.setLayout(abaNFLayout);
        abaNFLayout.setHorizontalGroup(
            abaNFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaNFLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(abaNFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaNFLayout.createSequentialGroup()
                        .addComponent(labelNF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSerieNF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelSubSerieNF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelBaseCalculoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelValoIssNF, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelAliquotaNF, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaNFLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(abaNFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelObservacaoNF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaNFLayout.createSequentialGroup()
                                .addComponent(labelDataRegistroNF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDataEmissaoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDataRetencaoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelDataProtocoloNF, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTipoDocumentoNF, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE))
                            .addComponent(labelDescricaoNF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE))))
                .addContainerGap())
        );
        abaNFLayout.setVerticalGroup(
            abaNFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaNFLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(abaNFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSubSerieNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSerieNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelBaseCalculoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValoIssNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelAliquotaNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelDescricaoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaNFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDataEmissaoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataRegistroNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataRetencaoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataProtocoloNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTipoDocumentoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelObservacaoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nota Fiscal", abaNF);

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setText("Recebimento");

        jLabel5.setText("Tipo Recebimento");

        comboTipoPagamento.setForeground(new java.awt.Color(0, 204, 255));
        comboTipoPagamento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Total", "Parcial" }));

        labelCentroCusto1.setText("Responsavel");

        comboResponsavel.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        labelCentroCusto.setText("Centro Custo");

        labelValorPagar1.setText("Valor Recebido");

        jFTValorRecebido.setEditable(false);
        jFTValorRecebido.setForeground(new java.awt.Color(0, 204, 255));

        campoCodigo.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jFTDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(comboTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCentroCusto1))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCentroCusto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jFTValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelValorPagar1))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFTDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(labelCentroCusto1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(labelValorPagar1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(labelCentroCusto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(2, 2, 2))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelDeducoesLayout = new javax.swing.GroupLayout(panelDeducoes);
        panelDeducoes.setLayout(panelDeducoesLayout);
        panelDeducoesLayout.setHorizontalGroup(
            panelDeducoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeducoesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelDeducoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        panelDeducoesLayout.setVerticalGroup(
            panelDeducoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDeducoesLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelDeducoes, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelAcrescimo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(panelAcrescimo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDeducoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jPanel2.setPreferredSize(new java.awt.Dimension(280, 65));

        botaoSalvar.setMnemonic('I');
        botaoSalvar.setText("Receber");
        botaoSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoSalvarActionPerformed(evt);
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
                .addGap(22, 22, 22)
                .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Metodo utilizado para repopular todos os combos deste JinternalFrame.
     */
    public void repopularCombos(Tela tela, Object object) {

        try {


            switch (tela) {

                case TELA_CONTAS_A_RECEBER_CADASTRO:

                    Collection<TituloReceberModel> lColecaoTituloReceber = tituloReceberBO.obterTodosAReceber(organizacaoModel);

                    EventList<TituloReceberModel> lRegistros = GlazedLists.eventList(lColecaoTituloReceber);
                    if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                        support.uninstall();
                    }
                    support = AutoCompleteSupport.install(comboTitulo, lRegistros, new TituloReceberTextFilterator());
                    support.setFilterMode(TextMatcherEditor.STARTS_WITH);


                    break;

                case TELA_PARAMETROS_CENTRO_DE_CUSTOS:

                    Collection<CentroCustoModel> lColecaoCentroCusto = new ArrayList<CentroCustoModel>();

                    CentroCustoModel centroCustoModel = new CentroCustoModel();
                    centroCustoModel.setDescricao(" -> Selecione <- ");
                    lColecaoCentroCusto.add(centroCustoModel);
                    lColecaoCentroCusto.addAll(centroCustoBO.obterTodos(organizacaoModel));
                    comboCentroCusto.setModel(new javax.swing.DefaultComboBoxModel(lColecaoCentroCusto.toArray()));

                    centroCustoModel = (CentroCustoModel) object;

                    if (centroCustoModel != null) {

                        for (int i = 1; i < comboCentroCusto.getItemCount(); i++) {
                            if (centroCustoModel.getPk().getId().equalsIgnoreCase(((CentroCustoModel) comboCentroCusto.getItemAt(i)).getPk().getId())) {
                                comboCentroCusto.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_FORMAS_DE_PAGAMENTOS:

                    Collection<FormaPagamentoModel> lColecaoFormaPagamento = new ArrayList<FormaPagamentoModel>();

                    FormaPagamentoModel formaPagamentoModel = new FormaPagamentoModel();
                    formaPagamentoModel.setDescricao(" -> Selecione <- ");
                    lColecaoFormaPagamento.add(formaPagamentoModel);
                    lColecaoFormaPagamento.addAll(formaPagamentoBO.obterTodos(organizacaoModel));
                    comboFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel(lColecaoFormaPagamento.toArray()));

                    formaPagamentoModel = (FormaPagamentoModel) object;

                    if (formaPagamentoModel != null) {

                        for (int i = 1; i < comboFormaPagamento.getItemCount(); i++) {
                            if (formaPagamentoModel.getPk().getId().equalsIgnoreCase(((FormaPagamentoModel) comboFormaPagamento.getItemAt(i)).getPk().getId())) {
                                comboFormaPagamento.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_TIPOS_DE_DEDUCOES:

                    Collection<TipoDeducaoModel> lColecaoTipoDeducao = new ArrayList<TipoDeducaoModel>();

                    //tipo Deducao
                    TipoDeducaoModel tipoDeducaoModel = new TipoDeducaoModel();
                    tipoDeducaoModel.setDescricao(" -> Selecione <- ");
                    lColecaoTipoDeducao.add(tipoDeducaoModel);
                    lColecaoTipoDeducao.addAll(tipoDeducaoBO.obterTodos(organizacaoModel));
                    comboDeducao.setModel(new javax.swing.DefaultComboBoxModel(lColecaoTipoDeducao.toArray()));

                    tipoDeducaoModel = (TipoDeducaoModel) object;

                    if (tipoDeducaoModel != null) {

                        for (int i = 1; i < comboDeducao.getItemCount(); i++) {
                            if (tipoDeducaoModel.getPk().getId().equalsIgnoreCase(((TipoDeducaoModel) comboDeducao.getItemAt(i)).getPk().getId())) {
                                comboDeducao.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_TIPOS_DE_ACRESCIMOS:

                    Collection<TipoAcrescimoModel> lColecaoTipoAcrescimo = new ArrayList<TipoAcrescimoModel>();

                    TipoAcrescimoModel tipoAcrescimoModel = new TipoAcrescimoModel();
                    tipoAcrescimoModel.setDescricao(" -> Selecione <- ");
                    lColecaoTipoAcrescimo.add(tipoAcrescimoModel);
                    lColecaoTipoAcrescimo.addAll(tipoAcrescimoBO.obterTodos(organizacaoModel));
                    comboAcrescimo.setModel(new javax.swing.DefaultComboBoxModel(lColecaoTipoAcrescimo.toArray()));

                    tipoAcrescimoModel = (TipoAcrescimoModel) object;

                    if (tipoAcrescimoModel != null) {

                        for (int i = 1; i < comboAcrescimo.getItemCount(); i++) {
                            if (tipoAcrescimoModel.getPk().getId().equalsIgnoreCase(((TipoAcrescimoModel) comboAcrescimo.getItemAt(i)).getPk().getId())) {
                                comboAcrescimo.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_FUNCIONARIOS:

                    Collection<FuncionarioModel> lColecaoResponsavel = new ArrayList<FuncionarioModel>();

                    FuncionarioModel funcionarioModel = new FuncionarioModel();
                    funcionarioModel.setNome(" -> Selecione <- ");
                    lColecaoResponsavel.add(funcionarioModel);
                    lColecaoResponsavel.addAll(responsavelBO.obterTodos(organizacaoModel));
                    comboResponsavel.setModel(new javax.swing.DefaultComboBoxModel(lColecaoResponsavel.toArray()));

                    funcionarioModel = (FuncionarioModel) object;

                    if (funcionarioModel != null) {

                        for (int i = 1; i < comboResponsavel.getItemCount(); i++) {
                            if (funcionarioModel.getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboResponsavel.getItemAt(i)).getPk().getId())) {
                                comboResponsavel.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                default:

                    lColecaoCentroCusto = new ArrayList<CentroCustoModel>();
                    lColecaoFormaPagamento = new ArrayList<FormaPagamentoModel>();
                    lColecaoTipoDeducao = new ArrayList<TipoDeducaoModel>();
                    lColecaoTipoAcrescimo = new ArrayList<TipoAcrescimoModel>();
                    lColecaoResponsavel = new ArrayList<FuncionarioModel>();
                    lColecaoTituloReceber = tituloReceberBO.obterTodosAReceber(organizacaoModel);

                    //tipo Deducao
                    tipoDeducaoModel = new TipoDeducaoModel();
                    tipoDeducaoModel.setDescricao(" -> Selecione <- ");
                    lColecaoTipoDeducao.add(tipoDeducaoModel);
                    lColecaoTipoDeducao.addAll(tipoDeducaoBO.obterTodos(organizacaoModel));
                    comboDeducao.setModel(new javax.swing.DefaultComboBoxModel(lColecaoTipoDeducao.toArray()));

                    //tipo Acrescimo
                    tipoAcrescimoModel = new TipoAcrescimoModel();
                    tipoAcrescimoModel.setDescricao(" -> Selecione <- ");
                    lColecaoTipoAcrescimo.add(tipoAcrescimoModel);
                    lColecaoTipoAcrescimo.addAll(tipoAcrescimoBO.obterTodos(organizacaoModel));
                    comboAcrescimo.setModel(new javax.swing.DefaultComboBoxModel(lColecaoTipoAcrescimo.toArray()));

                    //forma pagamento

                    formaPagamentoModel = new FormaPagamentoModel();
                    formaPagamentoModel.setDescricao(" -> Selecione <- ");
                    lColecaoFormaPagamento.add(formaPagamentoModel);
                    lColecaoFormaPagamento.addAll(formaPagamentoBO.obterTodos(organizacaoModel));
                    comboFormaPagamento.setModel(new javax.swing.DefaultComboBoxModel(lColecaoFormaPagamento.toArray()));

                    //Centro Custo

                    centroCustoModel = new CentroCustoModel();
                    centroCustoModel.setDescricao(" -> Selecione <- ");
                    lColecaoCentroCusto.add(centroCustoModel);
                    lColecaoCentroCusto.addAll(centroCustoBO.obterTodos(organizacaoModel));
                    comboCentroCusto.setModel(new javax.swing.DefaultComboBoxModel(lColecaoCentroCusto.toArray()));

                    //Responsavel

                    funcionarioModel = new FuncionarioModel();
                    funcionarioModel.setNome(" -> Selecione <- ");
                    lColecaoResponsavel.add(funcionarioModel);
                    lColecaoResponsavel.addAll(responsavelBO.obterTodos(organizacaoModel));
                    comboResponsavel.setModel(new javax.swing.DefaultComboBoxModel(lColecaoResponsavel.toArray()));

                    lRegistros = GlazedLists.eventList(lColecaoTituloReceber);
                    if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                        support.uninstall();
                    }
                    support = AutoCompleteSupport.install(comboTitulo, lRegistros, new TituloReceberTextFilterator());
                    support.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    support.setStrict(false);

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

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        botaoSalvar.setEnabled(true);
        campoCodigoTitulo.setText("");
        jTDescricao.setText("");
        jTNotaFiscal.setText("");
        jTParcela.setText("");
        labelCpfCnpj.setText("");
        labelCga.setText("");
        jFTDataVencimento.setValue(null);
        jFTValorReceber.setText("0,00");
        jFTValorTotalAdiantado.setText("0,00");
        jTSacado.setText("");
        comboResponsavel.setSelectedIndex(0);
        comboCentroCusto.setSelectedIndex(0);
        comboTipoPagamento.setSelectedIndex(0);
        comboTitulo.setSelectedItem(null);
        jFTDataPagamento.setDate(null);
        labelValorPagar2.setVisible(true);
        jFTValorFormaPagamento.setVisible(true);
        jFTDataVencimento.setForeground(new Color(0, 0, 0));

        //nota fiscal
        //14

        labelNF.setText("");
        labelAliquotaNF.setText("");
        labelBaseCalculoNF.setText("");
        labelDataEmissaoNF.setText("");
        labelDataProtocoloNF.setText("");
        labelDataRegistroNF.setText("");
        labelDataRetencaoNF.setText("");
        labelDescricaoNF.setText("");

        labelObservacaoNF.setText("");
        labelSerieNF.setText("");
        labelSubSerieNF.setText("");
        labelTipoDocumentoNF.setText("");
        labelValoIssNF.setText("0,00");

        jFTDataPagamento.setDate(Controller.getDataServidorBD());
        jFTDataPagamento.setVisible(true);
        comboTipoPagamento.setVisible(true);
        labelDataVencimento.setText("Vencimento");
        
        auxDeducoes = new ArrayList<TituloReceberBaixaDeducaoModel>();
        this.preencheTabelaDeducoes();
        auxAcrescimos = new ArrayList<TituloReceberBaixaAcrescimoModel>();
        this.preencheTabelaAcrescimos();

        this.limparCamposBaixa();

    }//GEN-LAST:event_botaoLimparActionPerformed

    private void limparCamposBaixa() {

        campoCodigo.setText("");

        jFTValorAcrescimo.setText("0,00");
        jFTValorDeducao.setText("0,00");
        jFTValorFormaPagamento.setText("0,00");

        jFTValorRecebido.setText("0,00");

        jFTDataPagamento.setDate(null);

        //Limpando variaveis
        auxFormasPagamento = new ArrayList<TituloReceberBaixaFormaPagamentoModel>();

        auxDeducoes = new ArrayList<TituloReceberBaixaDeducaoModel>();

        auxAcrescimos = new ArrayList<TituloReceberBaixaAcrescimoModel>();

        auxCheques = new ArrayList<TituloReceberBaixaChequeModel>();

        auxInternet = new ArrayList<TituloReceberBaixaInternetModel>();

        this.limparCamposAcrescimos();

        this.limparCamposDeducoes();

        this.limparCamposFormaPagamento();

        this.preencheTabelaAcrescimos();

        this.preencheTabelaDeducoes();

        this.preencheTabelaFormaPagamento();

    }

    private Boolean validaCampos() {

        //Levantar campos Obrigatórios.

        if (jTSacado.getText().isEmpty() || tituloReceberModel == null) {
            exibeMensagemAviso("Selecione um Título.", null);
            return false;
        }

        if (jFTDataPagamento.getDate() == null) {
            jFTDataPagamento.requestFocus();
            return false;
        }


        if (jFTDataPagamento.getDate() != null) {
            Date dataInformada = jFTDataPagamento.getDate();
            if (dataInformada.after(Controller.getDataServidorBD())) {
                exibeMensagemAviso("A Data do Pagamento não pode ser superior a HOJE.!", null);
                jFTDataPagamento.requestFocus();
                return false;
            }
        }

        if (comboTipoPagamento.getSelectedItem().toString().equals(Constantes.TIPO_BAIXA_TITULO_TOTAL) && (this.calculaValorPago() + (this.calculaValorAReceberAcrescimo() - this.calculaValorAReceberDeducoes())) != jFTValorReceber.getValue()) {
            exibeMensagemAviso("Os Valores (RECEBER E PAGO) para pagamento TOTAL devem ser iguais.!", null);
            return false;
        }

        if (comboTipoPagamento.getSelectedItem().toString().equals(Constantes.TIPO_BAIXA_TITULO_PARCIAL) && ((this.calculaValorPago() + (this.calculaValorAReceberAcrescimo() - this.calculaValorAReceberDeducoes())) - jFTValorReceber.getValue()) == 0) {
            exibeMensagemAviso("O tipo de Pagamento deve ser TOTAL para valores iguais!", null);
            return false;
        }

        return true;

    }

    private Boolean validaParametros() {

        if (Controller.verificaParametroAtivo(Parametro.CBR003.getCodigo()) && comboTipoPagamento.getSelectedIndex() == 1) {
            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBR003.getCodigo()), null);
            return false;
        }

        if (Controller.verificaParametroAtivo(Parametro.CBR002.getCodigo())) {
            if (jFTValorRecebido.getValue() > Controller.findByCodigo(Parametro.CBR002.getCodigo()).getValor().doubleValue()) {
                exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBR002.getCodigo()), null);
                return false;
            }
        }

        if (Controller.verificaParametroAtivo(Parametro.CBR004.getCodigo()) && comboTipoPagamento.getSelectedIndex() == 1 && (!auxAcrescimos.isEmpty() || !auxDeducoes.isEmpty())) {
            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBR004.getCodigo()), null);
            return false;
        }

        if (Controller.verificaParametroAtivo(Parametro.CBR006.getCodigo()) && comboTipoPagamento.getSelectedIndex() == 1) {
            if (jFTValorRecebido.getValue() < Controller.findByCodigo(Parametro.CBR006.getCodigo()).getValor().doubleValue()) {
                exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBR006.getCodigo()), null);
                return false;
            }
        }

        if (Controller.verificaParametroAtivo(Parametro.CBR005.getCodigo()) && comboTipoPagamento.getSelectedIndex() == 1) {

            for (TituloReceberBaixaFormaPagamentoModel formaPagamento : auxFormasPagamento) {

                if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)
                        && !Controller.verificaParametroAtivo(Parametro.CBR00501.getCodigo())) {
                    exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBR00501.getCodigo()), null);
                } else {

                    if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE)
                            && !Controller.verificaParametroAtivo(Parametro.CBR00502.getCodigo())) {
                        exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBR00502.getCodigo()), null);
                    } else {

                        if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_INTERNET)
                                && !Controller.verificaParametroAtivo(Parametro.CBR00503.getCodigo())) {
                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBR00503.getCodigo()), null);
                        }

                    }

                }

            }

        }


        return true;
    }

    private void botaoSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoSalvarActionPerformed

        String valorCombo = null;

        if (comboTitulo.getSelectedItem() != null) {
            valorCombo = comboTitulo.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.OUTROS.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (validaCampos()) {

                    if (!validaParametros()) {
                        return;
                    }

                    TituloReceberBaixaModel tab = new TituloReceberBaixaModel();

                    tab.setPk(new PKModel());

                    tab.getPk().setOrganizacao(organizacaoModel);

                    if (campoCodigo.getText() == null || campoCodigo.getText().isEmpty()) {
                        tab.getPk().setId(PempecKeyGenerator.generateKey());
                    } else {
                        tab.getPk().setId(campoCodigo.getText());
                    }

                    tab.setTitulo(tituloReceberModel);

                    //Se o Local de Pagamento são iguais, então é melhor esse campo nao ser editavel
                    tab.setLocalPagamento(tituloReceberModel.getLocalPagamento());

                    tab.setDataRegistro(Controller.getDataServidorBD());

                    if (jFTDataPagamento.getDate() != null) {
                        tituloReceberModel.setDataPagamento(jFTDataPagamento.getDate());
                    } else {
                        tituloReceberModel.setDataPagamento(Controller.getDataServidorBD());
                    }

                    String tipoBaixa = comboTipoPagamento.getSelectedItem().toString();

                    tab.setTipoBaixa(tipoBaixa);

                    tab.setValorPago(this.calculaValorPago());

                    TipoStatusModel status = new TipoStatusModel();

                    status.setPk(new PKModel());

                    status.getPk().setOrganizacao(organizacaoModel);

                    if (tipoBaixa.equals(Constantes.TIPO_BAIXA_TITULO_TOTAL)) {

                        status.getPk().setId(Constantes.STATUS_TITULO_PAGO);

                    } else {

                        String novoVencimento = null;


                        while (novoVencimento == null || novoVencimento.isEmpty() || !PempecUtil.validaData(novoVencimento)) {

                            novoVencimento = getInputMessage("Informar novo Vencimento?", PempecParse.dateToString(tituloReceberModel.getDataVencimento()));

                        }

                        tab.setNovaDataVencimento(PempecParse.stringToDate(novoVencimento));

                        status.getPk().setId(Constantes.STATUS_TITULO_PARCIAL);

                    }

                    tituloReceberModel.setStatus(status);

                    tab.setDataRegistro(Controller.getDataServidorBD());

                    tab.setUsuario(Controller.getUsuarioLogado());

                    //Tratamento de Nulidade.
                    //Tratamento tbm da opção Selecione

                    if (comboCentroCusto.getSelectedItem() != null && ((CentroCustoModel) comboCentroCusto.getSelectedItem()).getPk() != null) {

                        tab.setCentroCusto(new CentroCustoModel());
                        tab.getCentroCusto().setPk(new PKModel());
                        tab.getCentroCusto().getPk().setOrganizacao(organizacaoModel);
                        tab.getCentroCusto().getPk().setId(((CentroCustoModel) comboCentroCusto.getSelectedItem()).getPk().getId());

                    }

                    if (comboResponsavel.getSelectedItem() != null && ((FuncionarioModel) comboResponsavel.getSelectedItem()).getPk() != null) {

                        tab.setResponsavel(new FuncionarioModel());
                        tab.getResponsavel().setPk(new PKModel());
                        tab.getResponsavel().getPk().setOrganizacao(organizacaoModel);
                        tab.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavel.getSelectedItem()).getPk().getId());

                    }

                    //Coletando informações da Tabela para pegar os ID da forma pagamento.
                    Set<TituloReceberBaixaFormaPagamentoModel> formasPagamento = new HashSet<TituloReceberBaixaFormaPagamentoModel>();
                    formasPagamento.addAll(auxFormasPagamento);

                    tab.setFormasPagamento(formasPagamento);

                    Set<TituloReceberBaixaDeducaoModel> deducoes = new HashSet<TituloReceberBaixaDeducaoModel>();
                    deducoes.addAll(auxDeducoes);

                    tab.setDeducoes(deducoes);

                    Set<TituloReceberBaixaAcrescimoModel> acrescimos = new HashSet<TituloReceberBaixaAcrescimoModel>();
                    acrescimos.addAll(auxAcrescimos);

                    tab.setAcrescimos(acrescimos);

                    Set<TituloReceberBaixaChequeModel> cheques = new HashSet<TituloReceberBaixaChequeModel>();

                    cheques.addAll(auxCheques);

                    tab.setTituloReceberBaixaCheque(cheques);

                    Set<TituloReceberBaixaInternetModel> internet = new HashSet<TituloReceberBaixaInternetModel>();

                    internet.addAll(auxInternet);

                    tab.setTituloReceberBaixaInternet(internet);

                    tab.setMovimentoDiarioModel(registroMovimento("BX TIT. RECEBER", valorCombo, tituloReceberModel.getHistorico() + " " + tituloReceberModel.getDescricao(), tab.getValorPago(), "Recebido"));
                    tab.getMovimentoDiarioModel().setObservacao("Numero do Documento " + tituloReceberModel.getNumeroDocumento());

                    tituloReceberBaixaBO.salvar(tab, tituloReceberModel);

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

        } else {
            exibeMensagemAviso("Selecione um Título.", null);

        }


}//GEN-LAST:event_botaoSalvarActionPerformed

private void comboTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTituloActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTitulo.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            TituloReceberModel tab = new TituloReceberModel();
            //o valor do combo nao eh descricao.
            tab.setPk(new PKModel());
            tab.setNumeroDocumento(comboTitulo.getSelectedItem().toString());
            tab.getPk().setOrganizacao(organizacaoModel);

            tab = tituloReceberBO.consultarPorTemplateAReceber(tab);

            if (tab != null && tab.getPk() != null) {

                this.popularCampos(tab);

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

    protected void popularCampos(TituloReceberModel tab) {

        try {

            if (tab != null && tab.getPk() != null) {

                botaoSalvar.setEnabled(true);

                tituloReceberModel = tab;

                campoCodigoTitulo.setText(tab.getPk().getId());

                comboTitulo.setSelectedItem(tab.getNumeroDocumento());

                jFTValorReceber.setText(PempecParse.doubleToZero(tab.getValorNominal()));

                if (tab.getDataVencimento() != null) {

                    if (tab.getDataVencimento().before(Controller.getDataServidorBD())) {

                        jFTDataVencimento.setForeground(new Color(0, 100, 40));

                    } else {

                        jFTDataVencimento.setForeground(new Color(140, 100, 40));
                    }

                    jFTDataVencimento.setText(PempecParse.dateToString(tab.getDataVencimento()));

                }

                if (tab.getHistorico() != null) {

                    jTDescricao.setText(tab.getHistorico().getDescricao() + " " + tab.getDescricao());

                }

                jFTValorTotalAdiantado.setText(PempecParse.doubleToZero(tab.getValorAntecipado()));


                jTParcela.setText(tab.getParcela());



                if (tab.getSacado() != null) {

                    jTSacado.setText(tab.getSacado().getNome());

                    labelCpfCnpj.setText(tab.getSacado().getCpfCnpj());


                }



                if (tab.getCentroCusto() != null) {

                    for (int i = 1; i < comboCentroCusto.getItemCount(); i++) {
                        if (tab.getCentroCusto().getPk().getId().equalsIgnoreCase(((CentroCustoModel) comboCentroCusto.getItemAt(i)).getPk().getId())) {
                            comboCentroCusto.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getResponsavel() != null) {

                    for (int i = 1; i < comboResponsavel.getItemCount(); i++) {
                        if (tab.getResponsavel().getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboResponsavel.getItemAt(i)).getPk().getId())) {
                            comboResponsavel.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                //Verificando Campos da Baixa.

                TituloReceberBaixaModel baixa = tituloReceberBaixaBO.consultarPorTitulo(tituloReceberModel);

                if (baixa != null && baixa.getPk() != null) {

                    campoCodigo.setText(baixa.getPk().getId());

                    if (tab.getDataPagamento() != null) {

                        jFTDataPagamento.setDate(tab.getDataPagamento());

                    }

                    if (baixa.getTipoBaixa() != null) {

                        for (int i = 0; i < comboTipoPagamento.getItemCount(); i++) {
                            if (baixa.getTipoBaixa().equalsIgnoreCase(((String) comboTipoPagamento.getItemAt(i)).toString())) {
                                comboTipoPagamento.setSelectedIndex(i);
                                break;
                            }
                        }

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

                    jFTValorRecebido.setText(PempecParse.doubleToZero(this.calculaValorPago()));

                    //Limpando 
                    this.limparCamposBaixa();


                }

                if (tab.getNotaFiscal() != null && tab.getNotaFiscal().getPk() != null && !tab.getNotaFiscal().getPk().getId().isEmpty()) {

                    if (!tab.getNotaFiscal().getNumero().isEmpty()) {

                        NotaFiscalEmitidaModel nota = tab.getNotaFiscal();

                        labelAliquotaNF.setText(nota.getAliquota());
                        labelBaseCalculoNF.setText(PempecParse.doubleToZero(nota.getBaseCalculo()));
                        labelDataEmissaoNF.setText(PempecParse.dateToString(nota.getDataEmissao()));
                        labelDataProtocoloNF.setText(PempecParse.dateToString(nota.getDataProtocolo()));
                        labelDataRegistroNF.setText(PempecParse.dateToString(nota.getDataRegistro()));
                        labelDataRetencaoNF.setText(PempecParse.dateToString(nota.getDataRetencao()));
                        labelDescricaoNF.setText(nota.getDescricao());
                        labelObservacaoNF.setText(nota.getObservacao());
                        labelSerieNF.setText(nota.getSerie());
                        labelSubSerieNF.setText(nota.getSubSerie());
                        labelValoIssNF.setText(PempecParse.doubleToZero(nota.getValorIss()));
                        labelNF.setText(nota.getNumero());
                        jTNotaFiscal.setText(nota.getNumero());

                        if (nota.getTipoDocumento() != null) {
                            String tipo = "";
                            TipoNotaFiscalModel tipoNota = nota.getTipoDocumento();
                            tipoNota = tipoNotaFiscalBO.consultarPorPk(tipoNota);
                            tipo = tipoNota.getDescricao();
                            labelTipoDocumentoNF.setText(tipo);

                        }

                    }

                }

                if (tab.isProvisao()) {
                    jFTDataPagamento.setDate(Controller.getDataServidorBD());
                    jFTDataPagamento.setVisible(true);
                    comboTipoPagamento.setVisible(true);
                    labelDataVencimento.setText("Vencimento");
                } else {
                    jFTDataPagamento.setDate(tab.getDataVencimento());
                    jFTDataPagamento.setVisible(false);
                    comboTipoPagamento.setVisible(false);
                    labelDataVencimento.setText("Data Recebimento");
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

private void btnRemoverAcrescimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverAcrescimoActionPerformed

    int cont = 0;

    for (int i = 0; i < tableAcrescimo.getRowCount(); i++) {

        if (tableAcrescimo.getValueAt(i, 0).toString().equals("true")) {

            cont++;

        }

    }

    if (cont == tableAcrescimo.getRowCount()) {

        for (int i = 0; i < tableAcrescimo.getRowCount(); i++) {

            jFTValorReceber.setText(PempecParse.doubleToZero(jFTValorReceber.getValue() - PempecParse.stringToDouble(tableAcrescimo.getValueAt(i, 2).toString().replace('.', ','))));

        }

        auxAcrescimos.removeAll(auxAcrescimos);

        this.preencheTabelaAcrescimos();

    } else {

        for (int i = 0; i < tableAcrescimo.getRowCount(); i++) {

            if (tableAcrescimo.getValueAt(i, 0).toString().equals("true")) {

                jFTValorReceber.setText(PempecParse.doubleToZero(jFTValorReceber.getValue() - PempecParse.stringToDouble(tableAcrescimo.getValueAt(i, 2).toString().replace('.', ','))));

                ((TableModelCadastroTituloReceberBaixaAC) tableAcrescimo.getModel()).removeByID(tableAcrescimo.getValueAt(i, 3).toString());

            }

        }

        this.preencheTabelaAcrescimos();

    }

}//GEN-LAST:event_btnRemoverAcrescimoActionPerformed

private void btnIncluirAcrescimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirAcrescimoActionPerformed

    if (this.validaAcrescimos()) {

        try {

            TituloReceberBaixaAcrescimoModel tituloReceberBaixaAcrescimoModel = new TituloReceberBaixaAcrescimoModel();

            tituloReceberBaixaAcrescimoModel.setPk(new PKModel());

            tituloReceberBaixaAcrescimoModel.getPk().setOrganizacao(organizacaoModel);

            tituloReceberBaixaAcrescimoModel.getPk().setId(PempecKeyGenerator.generateKey());

            tituloReceberBaixaAcrescimoModel.setValor(jFTValorAcrescimo.getValue());

            //Tratamento de Nulidade.
            //Tratamento tbm da opção Selecione
            if (comboAcrescimo.getSelectedItem() != null && ((TipoAcrescimoModel) comboAcrescimo.getSelectedItem()).getPk() != null) {

                TipoAcrescimoModel tipoAcrescimoModel = new TipoAcrescimoModel();
                tipoAcrescimoModel.setPk(new PKModel());
                tipoAcrescimoModel.getPk().setOrganizacao(organizacaoModel);
                tipoAcrescimoModel.getPk().setId(((TipoAcrescimoModel) comboAcrescimo.getSelectedItem()).getPk().getId());
                tipoAcrescimoModel.setDescricao(((TipoAcrescimoModel) comboAcrescimo.getSelectedItem()).getDescricao());

                tipoAcrescimoModel = tipoAcrescimoBO.consultarPorTemplate(tipoAcrescimoModel);
                tituloReceberBaixaAcrescimoModel.setTipoAcrescimo(tipoAcrescimoModel);

            }

            auxAcrescimos.add(tituloReceberBaixaAcrescimoModel);

            this.preencheTabelaAcrescimos();

            this.limparCamposAcrescimos();

            jFTValorReceber.setText(PempecParse.doubleToZero(this.calculaValorAReceberAcrescimo()));

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

}//GEN-LAST:event_btnIncluirAcrescimoActionPerformed

private void btnRemoverDeducaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverDeducaoActionPerformed

    int cont = 0;

    for (int i = 0; i < tableDeducao.getRowCount(); i++) {

        if (tableDeducao.getValueAt(i, 0).toString().equals("true")) {

            cont++;

        }

    }

    if (cont == tableDeducao.getRowCount()) {

        for (int i = 0; i < tableDeducao.getRowCount(); i++) {

            jFTValorReceber.setText(PempecParse.doubleToZero(jFTValorReceber.getValue() + PempecParse.stringToDouble(tableDeducao.getValueAt(i, 2).toString().replace('.', ','))));

        }

        auxDeducoes.removeAll(auxDeducoes);

        this.preencheTabelaDeducoes();

    } else {

        for (int i = 0; i < tableDeducao.getRowCount(); i++) {

            if (tableDeducao.getValueAt(i, 0).toString().equals("true")) {

                jFTValorReceber.setText(PempecParse.doubleToZero(jFTValorReceber.getValue() + PempecParse.stringToDouble(tableDeducao.getValueAt(i, 2).toString().replace('.', ','))));

                ((TableModelCadastroTituloReceberBaixaDE) tableDeducao.getModel()).removeByID(tableDeducao.getValueAt(i, 3).toString());

            }

        }

        this.preencheTabelaDeducoes();

    }

    jFTValorReceber.setText(PempecParse.doubleToZero(this.calculaValorAReceberDeducoes()));

}//GEN-LAST:event_btnRemoverDeducaoActionPerformed

private void btnIncluirDeducaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirDeducaoActionPerformed

    if (this.validaDeducoes()) {

        try {

            TituloReceberBaixaDeducaoModel tituloReceberBaixaDeducaoModel = new TituloReceberBaixaDeducaoModel();

            tituloReceberBaixaDeducaoModel.setPk(new PKModel());

            tituloReceberBaixaDeducaoModel.getPk().setOrganizacao(organizacaoModel);

            tituloReceberBaixaDeducaoModel.getPk().setId(PempecKeyGenerator.generateKey());

            tituloReceberBaixaDeducaoModel.setValor(jFTValorDeducao.getValue());

            //Tratamento de Nulidade.
            //Tratamento tbm da opção Selecione
            if (comboDeducao.getSelectedItem() != null && ((TipoDeducaoModel) comboDeducao.getSelectedItem()).getPk() != null) {

                TipoDeducaoModel tipoDeducaoModel = new TipoDeducaoModel();
                tipoDeducaoModel.setPk(new PKModel());
                tipoDeducaoModel.getPk().setOrganizacao(organizacaoModel);
                tipoDeducaoModel.getPk().setId(((TipoDeducaoModel) comboDeducao.getSelectedItem()).getPk().getId());
                tipoDeducaoModel.setDescricao(((TipoDeducaoModel) comboDeducao.getSelectedItem()).getDescricao());

                tipoDeducaoModel = tipoDeducaoBO.consultarPorTemplate(tipoDeducaoModel);
                tituloReceberBaixaDeducaoModel.setTipoDeducao(tipoDeducaoModel);

            }

            auxDeducoes.add(tituloReceberBaixaDeducaoModel);

            this.preencheTabelaDeducoes();

            this.limparCamposDeducoes();

            jFTValorReceber.setText(PempecParse.doubleToZero(this.calculaValorAReceberDeducoes()));

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


}//GEN-LAST:event_btnIncluirDeducaoActionPerformed

private void btnRemoverFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverFormaPagamentoActionPerformed

    int cont = 0;

    for (int i = 0; i < tableFormaPagamento.getRowCount(); i++) {

        if (tableFormaPagamento.getValueAt(i, 0).toString().equals("true")) {

            cont++;

        }

    }

    if (cont == tableFormaPagamento.getRowCount()) {

        auxCheques.removeAll(auxCheques);

        auxInternet.removeAll(auxInternet);

        auxFormasPagamento.removeAll(auxFormasPagamento);

        this.preencheTabelaFormaPagamento();

    } else {

        for (int i = 0; i < tableFormaPagamento.getRowCount(); i++) {

            if (tableFormaPagamento.getValueAt(i, 0).toString().equals("true")) {

                ((TableModelCadastroTituloReceberBaixaFP) tableFormaPagamento.getModel()).removeByID(tableFormaPagamento.getValueAt(i, 3).toString());

            }

        }

        this.preencheTabelaFormaPagamento();

    }

    jFTValorRecebido.setText(PempecParse.doubleToZero(this.calculaValorPago()));

}//GEN-LAST:event_btnRemoverFormaPagamentoActionPerformed

private void btnIncluirFormaPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirFormaPagamentoActionPerformed

    if (this.validaFormaPagamento()) {

        String formaPagamento = ((FormaPagamentoModel) comboFormaPagamento.getSelectedItem()).getPk().getId();

        String tipoPagamento = comboTipoPagamento.getSelectedItem().toString();

        TituloReceberBaixaFormaPagamentoModel tituloReceberBaixaFormaPagamentoModel = new TituloReceberBaixaFormaPagamentoModel();

        tituloReceberBaixaFormaPagamentoModel.setPk(new PKModel());

        tituloReceberBaixaFormaPagamentoModel.getPk().setOrganizacao(organizacaoModel);

        tituloReceberBaixaFormaPagamentoModel.getPk().setId(PempecKeyGenerator.generateKey());

        //Tratamento de Nulidade.
        //Tratamento tbm da opção Selecione
        if (comboFormaPagamento.getSelectedItem() != null && ((FormaPagamentoModel) comboFormaPagamento.getSelectedItem()).getPk() != null) {

            try {

                FormaPagamentoModel fpgto = new FormaPagamentoModel();
                fpgto.setPk(new PKModel());
                fpgto.getPk().setOrganizacao(organizacaoModel);
                fpgto.getPk().setId(((FormaPagamentoModel) comboFormaPagamento.getSelectedItem()).getPk().getId());
                fpgto.setDescricao(comboFormaPagamento.getSelectedItem().toString());

                fpgto = formaPagamentoBO.consultarPorTemplate(fpgto);

                if (fpgto.getPk() != null && !(fpgto.getPk().getId().isEmpty())) {
                    tituloReceberBaixaFormaPagamentoModel.setForma(fpgto);
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

        if (formaPagamento.equals(Constantes.FORMA_PAGAMENTO_CHEQUE)) {

            try {

                if (baixaFPCheque == null || baixaFPCheque.isClosed()) {
                    baixaFPCheque = new CadastroTituloReceberBaixaFPCheque(this, tituloReceberModel, tituloReceberBaixaFormaPagamentoModel, tipoPagamento);
                    TelaPrincipal.desktopPane.add(baixaFPCheque);
                    baixaFPCheque.show();
                } else {
                    baixaFPCheque.setVisible(true);
                    baixaFPCheque.moveToFront();
                    baixaFPCheque.requestFocus();
                }


            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            }

        } else {

            if (formaPagamento.equals(Constantes.FORMA_PAGAMENTO_INTERNET)) {

                try {

                    if (baixaFPInternet == null || baixaFPInternet.isClosed()) {
                        baixaFPInternet = new CadastroTituloReceberBaixaFPInternet(this, tituloReceberModel, tituloReceberBaixaFormaPagamentoModel, tipoPagamento);
                        TelaPrincipal.desktopPane.add(baixaFPInternet);
                        baixaFPInternet.show();
                    } else {
                        baixaFPInternet.setVisible(true);
                        baixaFPInternet.moveToFront();
                        baixaFPInternet.requestFocus();
                    }


                } catch (final SystemException ex) {

                    final File file = PrintScreen.capture();

                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {

                            tratamentoExcecao(ex, file);

                        }
                    });

                }

            } else {

                if (Controller.verificaParametroAtivo(Parametro.CBRFPE001.getCodigo())) {
                    if (jFTValorFormaPagamento.getValue() > Controller.findByCodigo(Parametro.CBRFPE001.getCodigo()).getValor().doubleValue()) {
                        exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CBRFPE001.getCodigo()), null);
                        return;
                    }
                }

                tituloReceberBaixaFormaPagamentoModel.setValor(jFTValorFormaPagamento.getValue());

                auxFormasPagamento.add(tituloReceberBaixaFormaPagamentoModel);

                this.preencheTabelaFormaPagamento();

                this.limparCamposFormaPagamento();

            }

        }

        jFTValorRecebido.setText(PempecParse.doubleToZero(this.calculaValorPago()));

    } else {

        exibeMensagemAviso("Campo(s) obrigatório(s).", null);

    }
}//GEN-LAST:event_btnIncluirFormaPagamentoActionPerformed

private void comboFormaPagamentoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormaPagamentoItemStateChanged

    if (comboFormaPagamento.getSelectedItem() != null && ((FormaPagamentoModel) comboFormaPagamento.getSelectedItem()).getPk() != null) {

        String formaPagamento = ((FormaPagamentoModel) comboFormaPagamento.getSelectedItem()).getPk().getId();

        if (formaPagamento.equals(Constantes.FORMA_PAGAMENTO_CHEQUE) || formaPagamento.equals(Constantes.FORMA_PAGAMENTO_INTERNET)) {

            labelValorPagar2.setVisible(false);
            jFTValorFormaPagamento.setVisible(false);

        } else {

            labelValorPagar2.setVisible(true);
            jFTValorFormaPagamento.setVisible(true);

            if (comboTipoPagamento.getSelectedIndex() == 0) {
                Double valorEsp = (PempecParse.stringToDouble(jFTValorReceber.getText()) - PempecParse.stringToDouble(jFTValorRecebido.getText()));

                jFTValorFormaPagamento.setText(PempecParse.doubleToZero(valorEsp));

            } else {

                jFTValorFormaPagamento.setText("0,00");
            }

        }

    }
}//GEN-LAST:event_comboFormaPagamentoItemStateChanged

    private Boolean validaFormaPagamento() {

        if (jFTDataPagamento.getDate() != null && jFTDataPagamento.getDate().after(Controller.getDataServidorBD())) {
            exibeMensagemAviso("Data de recebimento não pode ser superior a HOJE!", null);
            jFTDataPagamento.requestFocus();
            return false;
        }
        //Levantar as validações
        if (comboFormaPagamento.getSelectedIndex() == 0) {
            comboFormaPagamento.requestFocus();
            return false;
        }

        if (jFTValorFormaPagamento.isVisible() && jFTValorFormaPagamento.getText().equals("0,00")) {
            jFTValorFormaPagamento.requestFocus();
            return false;
        }

        return true;
    }

    private void limparCamposFormaPagamento() {

        comboFormaPagamento.setSelectedIndex(0);
        jFTValorFormaPagamento.setText("0,00");

    }

    private Boolean validaDeducoes() {

        //Levantar as validações

        if (comboDeducao.getSelectedIndex() == 0) {
            comboDeducao.requestFocus();
            return false;
        }

        if (jFTValorDeducao.getText().equals("0,00")) {
            jFTValorDeducao.requestFocus();
            return false;
        }

        return true;
    }

    private void limparCamposDeducoes() {

        comboDeducao.setSelectedIndex(0);
        jFTValorDeducao.setText("0,00");

    }

    private Boolean validaAcrescimos() {

        //Levantar as validações

        if (comboAcrescimo.getSelectedIndex() == 0) {
            comboAcrescimo.requestFocus();
            return false;
        }

        if (jFTValorAcrescimo.getText().equals("0,00")) {
            jFTValorAcrescimo.requestFocus();
            return false;
        }

        return true;
    }

    private void limparCamposAcrescimos() {

        comboAcrescimo.setSelectedIndex(0);
        jFTValorAcrescimo.setText("0,00");

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaDeducao;
    private javax.swing.JPanel abaNF;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoSalvar;
    private javax.swing.JButton btnIncluirAcrescimo;
    private javax.swing.JButton btnIncluirDeducao;
    private javax.swing.JButton btnIncluirFormaPagamento;
    private javax.swing.JButton btnRemoverAcrescimo;
    private javax.swing.JButton btnRemoverDeducao;
    private javax.swing.JButton btnRemoverFormaPagamento;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoCodigoTitulo;
    private javax.swing.JComboBox comboAcrescimo;
    private javax.swing.JComboBox comboCentroCusto;
    private javax.swing.JComboBox comboDeducao;
    private javax.swing.JComboBox comboFormaPagamento;
    private javax.swing.JComboBox comboResponsavel;
    private javax.swing.JComboBox comboTipoPagamento;
    private javax.swing.JComboBox comboTitulo;
    protected org.jdesktop.swingx.JXDatePicker jFTDataPagamento;
    private javax.swing.JFormattedTextField jFTDataVencimento;
    private br.com.pempec.componentes.JDoubleField jFTValorAcrescimo;
    private br.com.pempec.componentes.JDoubleField jFTValorDeducao;
    private br.com.pempec.componentes.JDoubleField jFTValorFormaPagamento;
    protected br.com.pempec.componentes.JDoubleField jFTValorReceber;
    protected br.com.pempec.componentes.JDoubleField jFTValorRecebido;
    private br.com.pempec.componentes.JDoubleField jFTValorTotalAdiantado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTDescricao;
    private javax.swing.JTextField jTNotaFiscal;
    private javax.swing.JTextField jTParcela;
    private javax.swing.JTextField jTSacado;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAliquotaNF;
    private javax.swing.JLabel labelBaseCalculoNF;
    private javax.swing.JLabel labelCentroCusto;
    private javax.swing.JLabel labelCentroCusto1;
    private javax.swing.JTextField labelCga;
    private javax.swing.JTextField labelCpfCnpj;
    private javax.swing.JLabel labelDataEmissaoNF;
    private javax.swing.JLabel labelDataProtocoloNF;
    private javax.swing.JLabel labelDataRegistroNF;
    private javax.swing.JLabel labelDataRetencaoNF;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelDescricaoNF;
    private javax.swing.JLabel labelNF;
    private javax.swing.JLabel labelNFTitulo;
    private javax.swing.JLabel labelNumeroDocumento;
    private javax.swing.JLabel labelObservacaoNF;
    private javax.swing.JLabel labelParcela;
    private javax.swing.JLabel labelSerieNF;
    private javax.swing.JLabel labelSubSerieNF;
    private javax.swing.JLabel labelTipoDocumentoNF;
    private javax.swing.JLabel labelValoIssNF;
    private javax.swing.JLabel labelValorPagar;
    private javax.swing.JLabel labelValorPagar1;
    private javax.swing.JLabel labelValorPagar2;
    private javax.swing.JLabel labelValorPagar3;
    private javax.swing.JLabel labelValorPagar4;
    private javax.swing.JLabel labelValorPagar5;
    private javax.swing.JPanel panelAcrescimo;
    private javax.swing.JPanel panelDeducoes;
    private javax.swing.JTable tableAcrescimo;
    private javax.swing.JTable tableDeducao;
    private javax.swing.JTable tableFormaPagamento;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private TituloReceberModel tituloReceberModel = null;
    private ArrayList<TituloReceberBaixaFormaPagamentoModel> auxFormasPagamento;
    private ArrayList<TituloReceberBaixaDeducaoModel> auxDeducoes;
    private ArrayList<TituloReceberBaixaAcrescimoModel> auxAcrescimos;
    private Collection<TituloReceberBaixaChequeModel> auxCheques;
    private Collection<TituloReceberBaixaInternetModel> auxInternet;
    private CadastroTituloReceberBaixaFPCheque baixaFPCheque = null;
    private CadastroTituloReceberBaixaFPInternet baixaFPInternet = null;

    //Funções Auxiliares
    private void preencheTabelaFormaPagamento() {

        tableFormaPagamento.setAutoCreateColumnsFromModel(false);
        tableFormaPagamento.setModel(new TableModelCadastroTituloReceberBaixaFP(auxFormasPagamento));
        FontMetrics fm = tableFormaPagamento.getFontMetrics(tableFormaPagamento.getFont());
        tableFormaPagamento.setColumnModel(new ColumnModelCadastroTituloReceberBaixaFP(fm));
        tableFormaPagamento.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableFormaPagamento.getTableHeader().setReorderingAllowed(false);

    }

    private void preencheTabelaDeducoes() {

        tableDeducao.setAutoCreateColumnsFromModel(false);
        tableDeducao.setModel(new TableModelCadastroTituloReceberBaixaDE(auxDeducoes));
        FontMetrics fm = tableDeducao.getFontMetrics(tableDeducao.getFont());
        tableDeducao.setColumnModel(new ColumnModelCadastroTituloReceberBaixaDE(fm));
        tableDeducao.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableDeducao.getTableHeader().setReorderingAllowed(false);

    }

    private void preencheTabelaAcrescimos() {

        tableAcrescimo.setAutoCreateColumnsFromModel(false);
        tableAcrescimo.setModel(new TableModelCadastroTituloReceberBaixaAC(auxAcrescimos));
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

    private Double calculaValorAReceberAcrescimo() {

        Double retorno = 0d;

        for (TituloReceberBaixaAcrescimoModel tituloReceberBaixaAcrescimoModel : auxAcrescimos) {

            retorno += tituloReceberBaixaAcrescimoModel.getValor();

        }

        for (TituloReceberBaixaDeducaoModel tituloReceberBaixaDeducaoModel : auxDeducoes) {

            retorno -= tituloReceberBaixaDeducaoModel.getValor();

        }

        return retorno + tituloReceberModel.getValorNominal();

    }

    private Double calculaValorAReceberDeducoes() {

        Double retorno = 0d;

        for (TituloReceberBaixaDeducaoModel tituloReceberBaixaDeducaoModel : auxDeducoes) {

            retorno -= tituloReceberBaixaDeducaoModel.getValor();

        }

        for (TituloReceberBaixaAcrescimoModel tituloReceberBaixaAcrescimoModel : auxAcrescimos) {

            retorno += tituloReceberBaixaAcrescimoModel.getValor();

        }

        return retorno + tituloReceberModel.getValorNominal();

    }

    public void addCollecaoCheques(TituloReceberBaixaChequeModel baixaChequeModel, TituloReceberBaixaFormaPagamentoModel tituloReceberBaixaFormaPagamentoModel) {

        auxFormasPagamento.add(tituloReceberBaixaFormaPagamentoModel);

        auxCheques.add(baixaChequeModel);

        this.preencheTabelaFormaPagamento();

        this.limparCamposFormaPagamento();

        jFTValorRecebido.setText(PempecParse.doubleToZero(this.calculaValorPago()));
    }

    public void addCollecaoInternet(TituloReceberBaixaInternetModel internetModel, TituloReceberBaixaFormaPagamentoModel tituloReceberBaixaFormaPagamentoModel) {

        auxFormasPagamento.add(tituloReceberBaixaFormaPagamentoModel);

        auxInternet.add(internetModel);

        this.preencheTabelaFormaPagamento();

        this.limparCamposFormaPagamento();

        jFTValorRecebido.setText(PempecParse.doubleToZero(this.calculaValorPago()));
    }
}
