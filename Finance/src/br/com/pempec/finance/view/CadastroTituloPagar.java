package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.CentroCustoBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.LocalPagamentoBO;
import br.com.pempec.finance.businessObjects.TipoCobrancaBO;
import br.com.pempec.finance.businessObjects.TipoNotaFiscalBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CentroCustoModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.LocalPagamentoModel;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.NotaFiscalEntradaModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoCobrancaModel;
import br.com.pempec.finance.models.TipoNotaFiscalModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.TituloPagarRateioHistoricoModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IPopUpContaContabil;
import br.com.pempec.finance.utils.IRepopuladorNew;
import br.com.pempec.finance.utils.MaskUtils;
import br.com.pempec.finance.utils.Parametro;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.CedenteTextFilterator;
import br.com.pempec.finance.utils.iterators.HistoricoTextFilterator;
import br.com.pempec.finance.utils.iterators.TituloPagarTextFilterator;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroTituloPagarRateioCC;
import br.com.pempec.finance.utils.tables.ColumnModelCadastroTituloPagarRateioHistorico;
import br.com.pempec.finance.utils.tables.TableModelCadastroTituloPagarRateioCC;
import br.com.pempec.finance.utils.tables.TableModelCadastroTituloPagarRateioHistorico;
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
import java.util.List;
import java.util.Set;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroTituloPagar extends FinanceInternalFrame implements IPopUpContaContabil, IRepopuladorNew {

    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private TipoCobrancaBO tipoCobrancaBO = new TipoCobrancaBO();
    private TipoNotaFiscalBO tipoNotaFiscalBO = new TipoNotaFiscalBO();
    private LocalPagamentoBO localPagamentoBO = new LocalPagamentoBO();
    private CedenteBO cedenteBO = new CedenteBO();
    private CentroCustoBO centroCustoBO = new CentroCustoBO();
    private HistoricoBO historicoBO = new HistoricoBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private long tela = Tela.TELA_CONTAS_A_PAGAR_CADASTRO.getTela();

    private String NameObject() {
        return (String) "Título a Pagar";
    }

    public CadastroTituloPagar() throws SystemException {

        initComponents();

        comboMoeda.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"REAL", "DOLAR", "EURO"}));

        comboIss.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Não", "Sim"}));

        jTIdStatus.setText(Constantes.STATUS_TITULO_NOVO);

        jTParcela.setText("1");
        jTSerieNF.setText("A");
        jFTValorISSRetido.setText("0,00");

        //Escodendo os campos ID's
        labelSituacaoVencido.setVisible(false);
        labelExport.setVisible(false);
        // labelCartao.setVisible(false);

        botaoGerar.setVisible(false);
        campoCodigo.setVisible(false);
        jTIdStatus.setVisible(false);
        jTRegistroProvisao.setVisible(false);
        campoIdNF.setVisible(false);
        labelDataPagamento.setVisible(false);
        labelDataPagamento.setText("");
        jFTDataEmissao.setDate(PempecParse.dateToDate(Controller.getDataServidorBD()));

        jFTDataRetencaoISS.setEditable(false);
        jFTValorISSRetido.setEditable(false);

        jFTValorNF.setEditable(true);

        //Aplicando tamanho maximo de caracteres do campo.
        jTDescricao.setDocument(new Documento(80));
        jTObservacao.setDocument(new Documento(200));
        jTObservacaoNF.setDocument(new Documento(200));
        jTNotaFiscal.setDocument(new Documento(20));
        jTSerieNF.setDocument(new Documento(2));
        jTSubSerieNF.setDocument(new Documento(2));

        jTCarteira.setDocument(new Documento(30));
        jTContrato.setDocument(new Documento(20));

        comboIss.setEnabled(true);
        jFTDataRetencaoISS.setEnabled(false);
        jFTValorISSRetido.setEnabled(false);
        //Aplicando mascara em campos.

        labelContaContabilCredito.setText("");
        jTContaContabilCredito.setVisible(false);
        lupaPesquisaContaContabilCredito.setVisible(false);

        botaoIncluir.setEnabled(false);
        jChProvisao.setSelected(false);

        jFTCodigoBarras.setFormatterFactory(MaskUtils.mascaraCodigoBarras());
        jFTotalRateio.setText("0,00");
        jFTotalHistoricosRateio.setText("0,00");
        jFTValorRateio.setText("0,00");
        jFTValorHistoricosRateio.setText("0,00");
        auxCentroCustosRateio = new ArrayList<TituloPagarRateioCCModel>();
        auxHistoricosRateio = new ArrayList<TituloPagarRateioHistoricoModel>();
        this.preencheTabelaCentroCusto();
        this.preencheTabelaHistoricos();

    }

    public void repopularCombos(Tela tela, Object object) {

        try {

            switch (tela) {

                case TELA_CONTAS_A_PAGAR_CADASTRO:

                    Collection<TituloPagarModel> lColecao = tituloPagarBO.obterTodos(organizacaoModel);
                    EventList<TituloPagarModel> lRegistros = GlazedLists.eventList(lColecao);
                    if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                        support.uninstall();
                    }

                    support = AutoCompleteSupport.install(comboTitulo, lRegistros, new TituloPagarTextFilterator());
                    support.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    support.setStrict(false);

                    break;

                case TELA_PARAMETROS_CEDENTES:

                    Collection<CedenteModel> lCedente = cedenteBO.obterTodos(organizacaoModel);

                    EventList<CedenteModel> lRegistrosCedentes = GlazedLists.eventList(lCedente);
                    if (supportCedente != null && supportCedente.getItemList() != null && supportCedente.getComboBox() != null) {
                        supportCedente.uninstall();
                    }
                    supportCedente = AutoCompleteSupport.install(comboCedente, lRegistrosCedentes, new CedenteTextFilterator());
                    supportCedente.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportCedente.setStrict(false);

                    CedenteModel cedenteModel = (CedenteModel) object;

                    if (cedenteModel != null) {

                        for (int i = 1; i < comboCedente.getItemCount(); i++) {
                            if (cedenteModel.getPk().getId().equalsIgnoreCase(((CedenteModel) comboCedente.getItemAt(i)).getPk().getId())) {
                                comboCedente.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_TIPO_DE_COBRANCAS:

                    Collection<TipoCobrancaModel> lTipoCobranca = new ArrayList<TipoCobrancaModel>();

                    TipoCobrancaModel tipoCobrancaModel = new TipoCobrancaModel();

                    tipoCobrancaModel.setDescricao(" -> Selecione <- ");

                    lTipoCobranca.add(tipoCobrancaModel);

                    lTipoCobranca.addAll(tipoCobrancaBO.obterTodos(organizacaoModel));

                    comboTipoCobranca.setModel(new javax.swing.DefaultComboBoxModel(lTipoCobranca.toArray()));

                    tipoCobrancaModel = (TipoCobrancaModel) object;

                    if (tipoCobrancaModel != null) {

                        for (int i = 1; i < comboTipoCobranca.getItemCount(); i++) {
                            if (tipoCobrancaModel.getPk().getId().equalsIgnoreCase(((TipoCobrancaModel) comboTipoCobranca.getItemAt(i)).getPk().getId())) {
                                comboTipoCobranca.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_TIPOS_DE_NOTAS_FISCAIS:

                    Collection<TipoNotaFiscalModel> lTipoNotaFiscal = new ArrayList<TipoNotaFiscalModel>();

                    TipoNotaFiscalModel tipoNotaFiscalModel = new TipoNotaFiscalModel();

                    tipoNotaFiscalModel.setDescricao(" -> Selecione <- ");

                    lTipoNotaFiscal.add(tipoNotaFiscalModel);

                    lTipoNotaFiscal.addAll(tipoNotaFiscalBO.obterTodos(organizacaoModel));

                    comboTipoNotaFiscal.setModel(new javax.swing.DefaultComboBoxModel(lTipoNotaFiscal.toArray()));

                    tipoNotaFiscalModel = (TipoNotaFiscalModel) object;

                    if (tipoNotaFiscalModel != null) {

                        for (int i = 1; i < comboTipoNotaFiscal.getItemCount(); i++) {
                            if (tipoNotaFiscalModel.getPk().getId().equalsIgnoreCase(((TipoNotaFiscalModel) comboTipoNotaFiscal.getItemAt(i)).getPk().getId())) {
                                comboTipoNotaFiscal.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_LOCAL_DE_PAGAMENTO:

                    Collection<LocalPagamentoModel> lLocalPagamento = new ArrayList<LocalPagamentoModel>();

                    LocalPagamentoModel localPagamentoModel = new LocalPagamentoModel();

                    localPagamentoModel.setDescricao(" -> Selecione <- ");

                    lLocalPagamento.add(localPagamentoModel);

                    lLocalPagamento.addAll(localPagamentoBO.obterTodos(organizacaoModel));

                    comboLocalPagamento.setModel(new javax.swing.DefaultComboBoxModel(lLocalPagamento.toArray()));

                    localPagamentoModel = (LocalPagamentoModel) object;

                    if (localPagamentoModel != null) {

                        for (int i = 1; i < comboLocalPagamento.getItemCount(); i++) {
                            if (localPagamentoModel.getPk().getId().equalsIgnoreCase(((LocalPagamentoModel) comboLocalPagamento.getItemAt(i)).getPk().getId())) {
                                comboLocalPagamento.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_CENTRO_DE_CUSTOS:

                    Collection<CentroCustoModel> lCentroCusto = new ArrayList<CentroCustoModel>();

                    CentroCustoModel centroCustoModel = new CentroCustoModel();

                    centroCustoModel.setDescricao(" -> Selecione <- ");

                    lCentroCusto.add(centroCustoModel);

                    lCentroCusto.addAll(centroCustoBO.obterTodos(organizacaoModel));

                    comboCentroCusto.setModel(new javax.swing.DefaultComboBoxModel(lCentroCusto.toArray()));

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

                case TELA_PARAMETROS_HISTORICOS:

                    Collection<HistoricoModel> lHistorico = historicoBO.obterTodosPorTipo(organizacaoModel, "P");

                    EventList<HistoricoModel> lRegistrosHistoricos = GlazedLists.eventList(lHistorico);
                    if (supportHistorico != null && supportHistorico.getItemList() != null && supportHistorico.getComboBox() != null) {
                        supportHistorico.uninstall();
                    }
                    supportHistorico = AutoCompleteSupport.install(comboHistorico, lRegistrosHistoricos, new HistoricoTextFilterator());
                    supportHistorico.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportHistorico.setStrict(false);

                    HistoricoModel historicoModel = (HistoricoModel) object;

                    if (historicoModel != null) {

                        for (int i = 1; i < comboHistorico.getItemCount(); i++) {
                            if (historicoModel.getPk().getId().equalsIgnoreCase(((HistoricoModel) comboHistorico.getItemAt(i)).getPk().getId())) {
                                comboHistorico.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                case TELA_PARAMETROS_FUNCIONARIOS:

                    Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

                    FuncionarioModel funcionarioModel = new FuncionarioModel();

                    funcionarioModel.setNome(" -> Selecione <- ");

                    lFuncionario.add(funcionarioModel);

                    lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

                    comboFuncionario.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

                    funcionarioModel = (FuncionarioModel) object;

                    if (funcionarioModel != null) {

                        for (int i = 1; i < comboFuncionario.getItemCount(); i++) {
                            if (funcionarioModel.getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboFuncionario.getItemAt(i)).getPk().getId())) {
                                comboFuncionario.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    break;

                default:

                    lColecao = tituloPagarBO.obterTodos(organizacaoModel);

                    lCedente = cedenteBO.obterTodos(organizacaoModel);

                    lTipoCobranca = new ArrayList<TipoCobrancaModel>();

                    lTipoNotaFiscal = new ArrayList<TipoNotaFiscalModel>();

                    lLocalPagamento = new ArrayList<LocalPagamentoModel>();

                    lCentroCusto = new ArrayList<CentroCustoModel>();

                    lHistorico = historicoBO.obterTodosPorTipo(organizacaoModel, "P");

                    lFuncionario = new ArrayList<FuncionarioModel>();

                    //Adicionando a opção de selecionar, caso o campo não seja obrigatório!
                    tipoNotaFiscalModel = new TipoNotaFiscalModel();

                    tipoNotaFiscalModel.setDescricao(" -> Selecione <- ");

                    lTipoNotaFiscal.add(tipoNotaFiscalModel);

                    lTipoNotaFiscal.addAll(tipoNotaFiscalBO.obterTodos(organizacaoModel));

                    comboTipoNotaFiscal.setModel(new javax.swing.DefaultComboBoxModel(lTipoNotaFiscal.toArray()));

                    tipoCobrancaModel = new TipoCobrancaModel();

                    tipoCobrancaModel.setDescricao(" -> Selecione <- ");

                    lTipoCobranca.add(tipoCobrancaModel);

                    lTipoCobranca.addAll(tipoCobrancaBO.obterTodos(organizacaoModel));

                    comboTipoCobranca.setModel(new javax.swing.DefaultComboBoxModel(lTipoCobranca.toArray()));

                    localPagamentoModel = new LocalPagamentoModel();

                    localPagamentoModel.setDescricao(" -> Selecione <- ");

                    lLocalPagamento.add(localPagamentoModel);

                    lLocalPagamento.addAll(localPagamentoBO.obterTodos(organizacaoModel));

                    comboLocalPagamento.setModel(new javax.swing.DefaultComboBoxModel(lLocalPagamento.toArray()));

                    centroCustoModel = new CentroCustoModel();

                    centroCustoModel.setDescricao(" -> Selecione <- ");

                    lCentroCusto.add(centroCustoModel);

                    lCentroCusto.addAll(centroCustoBO.obterTodos(organizacaoModel));

                    comboCentroCusto.setModel(new javax.swing.DefaultComboBoxModel(lCentroCusto.toArray()));

                    funcionarioModel = new FuncionarioModel();

                    funcionarioModel.setNome(" -> Selecione <- ");

                    lFuncionario.add(funcionarioModel);

                    lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

                    comboFuncionario.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

                    lRegistros = GlazedLists.eventList(lColecao);
                    if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                        support.uninstall();
                    }
                    support = AutoCompleteSupport.install(comboTitulo, lRegistros, new TituloPagarTextFilterator());
                    support.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    support.setStrict(false);

                    lRegistrosCedentes = GlazedLists.eventList(lCedente);
                    if (supportCedente != null && supportCedente.getItemList() != null && supportCedente.getComboBox() != null) {
                        supportCedente.uninstall();
                    }

                    supportCedente = AutoCompleteSupport.install(comboCedente, lRegistrosCedentes, new CedenteTextFilterator());
                    supportCedente.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportCedente.setStrict(false);

                    lRegistrosHistoricos = GlazedLists.eventList(lHistorico);
                    if (supportHistorico != null && supportHistorico.getItemList() != null && supportHistorico.getComboBox() != null) {
                        supportHistorico.uninstall();
                    }
                    supportHistorico = AutoCompleteSupport.install(comboHistorico, lRegistrosHistoricos, new HistoricoTextFilterator());
                    supportHistorico.setFilterMode(TextMatcherEditor.STARTS_WITH);
                    supportHistorico.setStrict(false);

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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoPagar = new javax.swing.JButton();
        botaoImprimirEspelho = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaBasica = new javax.swing.JPanel();
        campoCodigo = new javax.swing.JTextField();
        labelCedente = new javax.swing.JLabel();
        comboCedente = new javax.swing.JComboBox();
        labelNumeroDocumento = new javax.swing.JLabel();
        labelValorPagar = new javax.swing.JLabel();
        labelTipoCobranca = new javax.swing.JLabel();
        comboTipoCobranca = new javax.swing.JComboBox();
        labelLocalPagamento = new javax.swing.JLabel();
        comboLocalPagamento = new javax.swing.JComboBox();
        labelDataVencimento = new javax.swing.JLabel();
        jTDescricao = new javax.swing.JTextField();
        labelHistorico = new javax.swing.JLabel();
        labelDescricao = new javax.swing.JLabel();
        comboHistorico = new javax.swing.JComboBox();
        jTParcela = new javax.swing.JTextField();
        jFTValorPagar = new br.com.pempec.componentes.JDoubleField();
        comboTitulo = new javax.swing.JComboBox();
        comboFuncionario = new javax.swing.JComboBox();
        labelResponsavel = new javax.swing.JLabel();
        jTIdStatus = new javax.swing.JTextField();
        botaoGerarNumeroDocumento = new javax.swing.JButton();
        labelDataEmissao = new javax.swing.JLabel();
        labelDataProtocolo = new javax.swing.JLabel();
        labelDataPagamento = new javax.swing.JLabel();
        labelContaContabilCredito = new javax.swing.JLabel();
        labelDescricao2 = new javax.swing.JLabel();
        comboCentroCusto = new javax.swing.JComboBox();
        labelCentroCusto = new javax.swing.JLabel();
        jFTDataVencimento = new org.jdesktop.swingx.JXDatePicker();
        jFTDataEmissao = new org.jdesktop.swingx.JXDatePicker();
        jFTDataProtocolo = new org.jdesktop.swingx.JXDatePicker();
        labelSituacaoTitulo = new javax.swing.JLabel();
        labelExport = new javax.swing.JLabel();
        labelSituacaoVencido = new javax.swing.JLabel();
        jChProvisao = new javax.swing.JCheckBox();
        jTContaContabilCredito = new javax.swing.JTextField();
        lupaPesquisaContaContabilCredito = new javax.swing.JButton();
        jTContaContabilDebito = new javax.swing.JTextField();
        lupaPesquisaContaContabilDebito = new javax.swing.JButton();
        jTRegistroProvisao = new javax.swing.JTextField();
        jFTValorAdiantamento = new br.com.pempec.componentes.JDoubleField();
        labelVlrAdiantado = new javax.swing.JLabel();
        labelParcela3 = new javax.swing.JLabel();
        botaoGerar = new javax.swing.JTextField();
        abaComplementar = new javax.swing.JPanel();
        labelMoeda = new javax.swing.JLabel();
        comboMoeda = new javax.swing.JComboBox();
        labelPrevisaoCartorio = new javax.swing.JLabel();
        labelCarteira = new javax.swing.JLabel();
        jTCarteira = new javax.swing.JTextField();
        labelContrato = new javax.swing.JLabel();
        jTContrato = new javax.swing.JTextField();
        labelObs = new javax.swing.JLabel();
        labelCodigoBarras = new javax.swing.JLabel();
        jFTCodigoBarras = new javax.swing.JFormattedTextField();
        jFTPrevisaoCartorio = new org.jdesktop.swingx.JXDatePicker();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTObservacao = new org.jdesktop.swingx.JXEditorPane();
        labelPrevisaoCartorio1 = new javax.swing.JLabel();
        labelAtualizacao = new javax.swing.JLabel();
        abaNf = new javax.swing.JPanel();
        labelDataVencimento3 = new javax.swing.JLabel();
        jFtAliquota = new br.com.pempec.componentes.JDoubleField();
        labelNF1 = new javax.swing.JLabel();
        jTNotaFiscal = new javax.swing.JTextField();
        labelParcela1 = new javax.swing.JLabel();
        jTSerieNF = new javax.swing.JTextField();
        labelParcela2 = new javax.swing.JLabel();
        jTSubSerieNF = new javax.swing.JTextField();
        jFTValorNF = new br.com.pempec.componentes.JDoubleField();
        labelDataVencimento1 = new javax.swing.JLabel();
        jFTDataEmissaoNF = new org.jdesktop.swingx.JXDatePicker();
        labelNF2 = new javax.swing.JLabel();
        comboTipoNotaFiscal = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        comboIss = new javax.swing.JComboBox();
        labelValorPagar7 = new javax.swing.JLabel();
        jFTValorISSRetido = new br.com.pempec.componentes.JDoubleField();
        labelNF3 = new javax.swing.JLabel();
        labelDataVencimento2 = new javax.swing.JLabel();
        jFTDataRetencaoISS = new org.jdesktop.swingx.JXDatePicker();
        campoIdNF = new javax.swing.JTextField();
        labelDataVencimento4 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTObservacaoNF = new org.jdesktop.swingx.JXEditorPane();
        abaHistoricos = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        comboHistoricosRateio = new javax.swing.JComboBox();
        labelValorPagar4 = new javax.swing.JLabel();
        jFTValorHistoricosRateio = new br.com.pempec.componentes.JDoubleField();
        btnIncluirHistoricoRateio = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableHistoricos = new javax.swing.JTable();
        btnRemoverHistoricoRateio = new javax.swing.JButton();
        jFTotalHistoricosRateio = new br.com.pempec.componentes.JDoubleField();
        labelValorPagar5 = new javax.swing.JLabel();
        abaRateio = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboCentroCustoRateio = new javax.swing.JComboBox();
        labelValorPagar2 = new javax.swing.JLabel();
        jFTValorRateio = new br.com.pempec.componentes.JDoubleField();
        btnIncluirCCRateio = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableCentroCusto = new javax.swing.JTable();
        btnRemoverCCRateio = new javax.swing.JButton();
        jFTotalRateio = new br.com.pempec.componentes.JDoubleField();
        labelValorPagar3 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - TÍTULO A PAGAR");
        setMinimumSize(new java.awt.Dimension(890, 533));
        setPreferredSize(new java.awt.Dimension(1040, 550));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoIncluir.setMnemonic('I');
        botaoIncluir.setText("Incluir");
        botaoIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoIncluirActionPerformed(evt);
            }
        });

        botaoAlterar.setMnemonic('A');
        botaoAlterar.setText("Alterar");
        botaoAlterar.setEnabled(false);
        botaoAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoAlterarActionPerformed(evt);
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

        botaoExcluir.setMnemonic('E');
        botaoExcluir.setText("Excluir");
        botaoExcluir.setEnabled(false);
        botaoExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoExcluirActionPerformed(evt);
            }
        });

        botaoPagar.setMnemonic('B');
        botaoPagar.setText("Baixar");
        botaoPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoPagarActionPerformed(evt);
            }
        });

        botaoImprimirEspelho.setMnemonic('A');
        botaoImprimirEspelho.setText("Imprimir");
        botaoImprimirEspelho.setEnabled(false);
        botaoImprimirEspelho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoImprimirEspelhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoImprimirEspelho, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoExcluir, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoImprimirEspelho, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        abaBasica.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));
        abaBasica.setPreferredSize(new java.awt.Dimension(929, 341));

        campoCodigo.setEditable(false);

        labelCedente.setText("Cedente");

        comboCedente.setFont(new java.awt.Font("Arial", 0, 11));
        comboCedente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCedenteItemStateChanged(evt);
            }
        });

        labelNumeroDocumento.setText("Número do Documento");

        labelValorPagar.setText("Valor a Pagar");

        labelTipoCobranca.setText("Tipo Cobrança");

        comboTipoCobranca.setFont(new java.awt.Font("Arial", 0, 11));

        labelLocalPagamento.setText("Local Pagamento");

        comboLocalPagamento.setFont(new java.awt.Font("Arial", 0, 11));

        labelDataVencimento.setText("Data Pagamento");

        labelHistorico.setText("Histórico");

        labelDescricao.setText("Descrição");

        comboHistorico.setFont(new java.awt.Font("Arial", 0, 11));
        comboHistorico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboHistoricoItemStateChanged(evt);
            }
        });
        comboHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboHistoricoActionPerformed(evt);
            }
        });

        jFTValorPagar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTValorPagarFocusLost(evt);
            }
        });

        comboTitulo.setToolTipText("");
        comboTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTituloActionPerformed(evt);
            }
        });

        comboFuncionario.setFont(new java.awt.Font("Arial", 0, 11));

        labelResponsavel.setText("Responsável");

        botaoGerarNumeroDocumento.setFont(new java.awt.Font("Arial", 0, 10));
        botaoGerarNumeroDocumento.setText("Gerar");
        botaoGerarNumeroDocumento.setToolTipText("Gerar Número Documento");
        botaoGerarNumeroDocumento.setActionCommand("botaoGerarNumeroDocumento");
        botaoGerarNumeroDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarNumeroDocumentoActionPerformed(evt);
            }
        });

        labelDataEmissao.setText("Data Emissão");

        labelDataProtocolo.setText("Data Protocolo");

        labelDataPagamento.setFont(new java.awt.Font("Arial", 1, 10));
        labelDataPagamento.setForeground(new java.awt.Color(255, 0, 0));
        labelDataPagamento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 0, 0)), "Data Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

        labelContaContabilCredito.setText("Conta Contabil Crédito");

        labelDescricao2.setText("Conta Contabil Débito");

        comboCentroCusto.setFont(new java.awt.Font("Arial", 0, 11));
        comboCentroCusto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                comboCentroCustoFocusLost(evt);
            }
        });

        labelCentroCusto.setText("Centro Custo");

        jFTDataVencimento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTDataVencimentoFocusLost(evt);
            }
        });

        jFTDataEmissao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTDataEmissaoFocusLost(evt);
            }
        });

        labelSituacaoTitulo.setBackground(new java.awt.Color(222, 218, 210));
        labelSituacaoTitulo.setFont(new java.awt.Font("Arial", 1, 10));
        labelSituacaoTitulo.setForeground(new java.awt.Color(255, 0, 0));
        labelSituacaoTitulo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 0)), "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

        labelExport.setBackground(new java.awt.Color(0, 204, 204));
        labelExport.setFont(new java.awt.Font("Arial", 0, 10));
        labelExport.setForeground(new java.awt.Color(255, 0, 0));
        labelExport.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 0)), "Exportação"));

        labelSituacaoVencido.setBackground(new java.awt.Color(222, 218, 210));
        labelSituacaoVencido.setFont(new java.awt.Font("Arial", 3, 12));
        labelSituacaoVencido.setForeground(new java.awt.Color(255, 0, 0));
        labelSituacaoVencido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSituacaoVencido.setText("Obs");
        labelSituacaoVencido.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jChProvisao.setFont(new java.awt.Font("Tahoma", 1, 12));
        jChProvisao.setForeground(new java.awt.Color(255, 0, 0));
        jChProvisao.setText("Provisão");
        jChProvisao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChProvisaoActionPerformed(evt);
            }
        });

        jTContaContabilCredito.setEditable(false);

        lupaPesquisaContaContabilCredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.jpg"))); // NOI18N
        lupaPesquisaContaContabilCredito.setBorderPainted(false);
        lupaPesquisaContaContabilCredito.setContentAreaFilled(false);
        lupaPesquisaContaContabilCredito.setDefaultCapable(false);
        lupaPesquisaContaContabilCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lupaPesquisaContaContabilCreditoActionPerformed(evt);
            }
        });

        jTContaContabilDebito.setEditable(false);

        lupaPesquisaContaContabilDebito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/lupa.jpg"))); // NOI18N
        lupaPesquisaContaContabilDebito.setBorderPainted(false);
        lupaPesquisaContaContabilDebito.setContentAreaFilled(false);
        lupaPesquisaContaContabilDebito.setDefaultCapable(false);
        lupaPesquisaContaContabilDebito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lupaPesquisaContaContabilDebitoActionPerformed(evt);
            }
        });

        jFTValorAdiantamento.setEditable(false);
        jFTValorAdiantamento.setForeground(new java.awt.Color(0, 153, 153));

        labelVlrAdiantado.setText("Adiantamento");

        labelParcela3.setText("Parcela");

        botaoGerar.setEditable(false);

        javax.swing.GroupLayout abaBasicaLayout = new javax.swing.GroupLayout(abaBasica);
        abaBasica.setLayout(abaBasicaLayout);
        abaBasicaLayout.setHorizontalGroup(
            abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaBasicaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaBasicaLayout.createSequentialGroup()
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCedente)
                            .addComponent(comboCedente, 0, 328, Short.MAX_VALUE)
                            .addComponent(labelHistorico)
                            .addComponent(comboHistorico, 0, 328, Short.MAX_VALUE)
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addComponent(labelDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(labelExport, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                            .addComponent(labelSituacaoTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE))
                        .addGap(24, 24, 24)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDescricao2)
                                    .addComponent(labelContaContabilCredito)
                                    .addGroup(abaBasicaLayout.createSequentialGroup()
                                        .addComponent(jTContaContabilCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lupaPesquisaContaContabilCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(abaBasicaLayout.createSequentialGroup()
                                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTContaContabilDebito, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                                .addComponent(jTIdStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 169, Short.MAX_VALUE)
                                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(15, 15, 15)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lupaPesquisaContaContabilDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(1, 1, 1))
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addComponent(jTRegistroProvisao, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(96, 96, 96)
                                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108))))
                    .addGroup(abaBasicaLayout.createSequentialGroup()
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addComponent(labelNumeroDocumento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelSituacaoVencido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(comboTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botaoGerarNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataVencimento)
                            .addComponent(jFTDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelParcela3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addComponent(jTParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))))
                    .addGroup(abaBasicaLayout.createSequentialGroup()
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDescricao)
                            .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelValorPagar))
                        .addGap(18, 18, 18)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTValorAdiantamento, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelVlrAdiantado))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)))
                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaBasicaLayout.createSequentialGroup()
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(abaBasicaLayout.createSequentialGroup()
                                    .addGap(9, 9, 9)
                                    .addComponent(labelDataEmissao)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE))
                                .addGroup(abaBasicaLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFTDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)))
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelLocalPagamento)
                                    .addComponent(comboLocalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(16, 16, 16)))
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataProtocolo)
                            .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jChProvisao)
                                .addComponent(jFTDataProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelTipoCobranca)
                        .addComponent(comboTipoCobranca, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelResponsavel))
                    .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelCentroCusto)
                        .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(68, 68, 68))
        );
        abaBasicaLayout.setVerticalGroup(
            abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaBasicaLayout.createSequentialGroup()
                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaBasicaLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelNumeroDocumento)
                                    .addComponent(labelSituacaoVencido))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(botaoGerarNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(abaBasicaLayout.createSequentialGroup()
                                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(labelDataVencimento)
                                            .addComponent(labelParcela3))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(abaBasicaLayout.createSequentialGroup()
                                        .addComponent(labelLocalPagamento)
                                        .addGap(6, 6, 6)))
                                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFTDataVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboLocalPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(3, 3, 3)))
                        .addGap(16, 16, 16)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addComponent(labelDescricao)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(abaBasicaLayout.createSequentialGroup()
                                    .addComponent(labelVlrAdiantado)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jFTValorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFTValorAdiantamento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(abaBasicaLayout.createSequentialGroup()
                                    .addComponent(labelValorPagar)
                                    .addGap(31, 31, 31)))))
                    .addGroup(abaBasicaLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jChProvisao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelDataProtocolo)
                            .addComponent(labelDataEmissao))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTDataProtocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTDataEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaBasicaLayout.createSequentialGroup()
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelContaContabilCredito)
                            .addComponent(labelCedente)
                            .addComponent(labelTipoCobranca))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboCedente, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTipoCobranca, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTContaContabilCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addComponent(labelHistorico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTContaContabilDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(abaBasicaLayout.createSequentialGroup()
                                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelDescricao2)
                                    .addComponent(labelResponsavel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(abaBasicaLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lupaPesquisaContaContabilCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lupaPesquisaContaContabilDebito, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaBasicaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(labelCentroCusto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCentroCusto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaBasicaLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTIdStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelSituacaoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaBasicaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelDataPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelExport, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTRegistroProvisao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(botaoGerar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(29, 29, 29))
        );

        jTabbedPane1.addTab("Dados Básicos", abaBasica);

        abaComplementar.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 51)), NameObject()));
        abaComplementar.setPreferredSize(new java.awt.Dimension(929, 341));

        labelMoeda.setText("Moeda");

        comboMoeda.setFont(new java.awt.Font("Arial", 0, 11));

        labelPrevisaoCartorio.setText("Previsão Cartório");

        labelCarteira.setText("Carteira");

        labelContrato.setText("Contrato");

        labelObs.setText("Observação");

        labelCodigoBarras.setText("Código Barras");

        jTObservacao.setFont(new java.awt.Font("Arial", 0, 11));
        jScrollPane2.setViewportView(jTObservacao);

        labelPrevisaoCartorio1.setText("Última Atualização");

        labelAtualizacao.setFont(new java.awt.Font("Arial", 1, 10));
        labelAtualizacao.setForeground(new java.awt.Color(0, 153, 153));
        labelAtualizacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelAtualizacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 11))); // NOI18N

        javax.swing.GroupLayout abaComplementarLayout = new javax.swing.GroupLayout(abaComplementar);
        abaComplementar.setLayout(abaComplementarLayout);
        abaComplementarLayout.setHorizontalGroup(
            abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaComplementarLayout.createSequentialGroup()
                .addGroup(abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaComplementarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelObs)
                            .addGroup(abaComplementarLayout.createSequentialGroup()
                                .addGroup(abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTCarteira, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCarteira)
                                    .addGroup(abaComplementarLayout.createSequentialGroup()
                                        .addGroup(abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelPrevisaoCartorio)
                                            .addComponent(jFTPrevisaoCartorio, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(30, 30, 30)
                                        .addGroup(abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(labelMoeda)
                                            .addComponent(comboMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(18, 18, 18)
                                .addGroup(abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFTCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelContrato)
                                    .addComponent(jTContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(abaComplementarLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(labelPrevisaoCartorio1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(315, Short.MAX_VALUE))
        );
        abaComplementarLayout.setVerticalGroup(
            abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaComplementarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaComplementarLayout.createSequentialGroup()
                        .addGroup(abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaComplementarLayout.createSequentialGroup()
                                .addComponent(labelPrevisaoCartorio)
                                .addGap(4, 4, 4)
                                .addComponent(jFTPrevisaoCartorio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(abaComplementarLayout.createSequentialGroup()
                                .addComponent(labelMoeda)
                                .addGap(6, 6, 6)
                                .addComponent(comboMoeda, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelCarteira)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTCarteira, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaComplementarLayout.createSequentialGroup()
                        .addComponent(labelCodigoBarras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTCodigoBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelContrato)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTContrato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(labelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(abaComplementarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaComplementarLayout.createSequentialGroup()
                        .addComponent(labelAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaComplementarLayout.createSequentialGroup()
                        .addComponent(labelPrevisaoCartorio1)
                        .addGap(34, 34, 34))))
        );

        jTabbedPane1.addTab("Complementar", abaComplementar);

        abaNf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));
        abaNf.setPreferredSize(new java.awt.Dimension(929, 341));

        labelDataVencimento3.setText("Aliq. Iss Retido");

        jFtAliquota.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFtAliquotaFocusLost(evt);
            }
        });

        labelNF1.setText("Nota Fiscal");

        jTNotaFiscal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTNotaFiscalFocusLost(evt);
            }
        });

        labelParcela1.setText("Série");

        jTSerieNF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        labelParcela2.setText("Sub-Série");

        jTSubSerieNF.setForeground(new java.awt.Color(0, 102, 204));
        jTSubSerieNF.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        labelDataVencimento1.setText("Data Emissão");

        jFTDataEmissaoNF.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFTDataEmissaoNFFocusLost(evt);
            }
        });

        labelNF2.setText("Tipo Documento");

        comboTipoNotaFiscal.setFont(new java.awt.Font("Arial", 0, 11));

        jLabel7.setText("ISS Retido");

        comboIss.setForeground(new java.awt.Color(255, 153, 0));
        comboIss.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboIssItemStateChanged(evt);
            }
        });

        labelValorPagar7.setText("Valor Iss");

        labelNF3.setText("Observação");

        labelDataVencimento2.setText("Data Retenção");

        campoIdNF.setEditable(false);

        labelDataVencimento4.setText("Valor NF");

        jScrollPane3.setViewportView(jTObservacaoNF);

        javax.swing.GroupLayout abaNfLayout = new javax.swing.GroupLayout(abaNf);
        abaNf.setLayout(abaNfLayout);
        abaNfLayout.setHorizontalGroup(
            abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaNfLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboTipoNotaFiscal, 0, 433, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addGroup(abaNfLayout.createSequentialGroup()
                        .addComponent(labelNF2)
                        .addGap(322, 322, 322))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaNfLayout.createSequentialGroup()
                        .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNF1)
                            .addComponent(jTNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelParcela1)
                            .addComponent(jTSerieNF, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTSubSerieNF)
                            .addComponent(labelParcela2))
                        .addGap(28, 28, 28)
                        .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelDataVencimento4)
                            .addComponent(jFTValorNF, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(labelNF3))
                .addGap(26, 26, 26)
                .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDataVencimento2)
                    .addGroup(abaNfLayout.createSequentialGroup()
                        .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTDataEmissaoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataVencimento1)
                            .addComponent(comboIss, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(jFTDataRetencaoISS, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTValorISSRetido, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoIdNF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelValorPagar7)
                            .addComponent(jFtAliquota, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataVencimento3))))
                .addGap(268, 268, 268))
        );
        abaNfLayout.setVerticalGroup(
            abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaNfLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaNfLayout.createSequentialGroup()
                        .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaNfLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jFTDataEmissaoNF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFtAliquota, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(labelDataVencimento1)
                                .addComponent(labelDataVencimento3)))
                        .addGap(29, 29, 29)
                        .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaNfLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboIss, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboTipoNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(labelDataVencimento2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTDataRetencaoISS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(abaNfLayout.createSequentialGroup()
                                .addComponent(labelValorPagar7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFTValorISSRetido, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(campoIdNF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(abaNfLayout.createSequentialGroup()
                        .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaNfLayout.createSequentialGroup()
                                .addGroup(abaNfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(abaNfLayout.createSequentialGroup()
                                        .addComponent(labelParcela1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTSerieNF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(abaNfLayout.createSequentialGroup()
                                        .addComponent(labelNF1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(abaNfLayout.createSequentialGroup()
                                        .addComponent(labelDataVencimento4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFTValorNF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addComponent(labelNF2))
                            .addGroup(abaNfLayout.createSequentialGroup()
                                .addComponent(labelParcela2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTSubSerieNF, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addComponent(labelNF3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(128, 128, 128))
        );

        jTabbedPane1.addTab("Nota Fiscal", abaNf);

        abaHistoricos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        abaHistoricos.setPreferredSize(new java.awt.Dimension(929, 341));

        jLabel4.setText("Histórico");

        labelValorPagar4.setText("Valor");

        btnIncluirHistoricoRateio.setBackground(new java.awt.Color(102, 204, 0));
        btnIncluirHistoricoRateio.setText("Incluir");
        btnIncluirHistoricoRateio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirHistoricoRateioActionPerformed(evt);
            }
        });

        tableHistoricos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableHistoricos.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tableHistoricosPropertyChange(evt);
            }
        });
        jScrollPane5.setViewportView(tableHistoricos);

        btnRemoverHistoricoRateio.setBackground(new java.awt.Color(153, 153, 0));
        btnRemoverHistoricoRateio.setText("Remover");
        btnRemoverHistoricoRateio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverHistoricoRateioActionPerformed(evt);
            }
        });

        jFTotalHistoricosRateio.setEditable(false);

        labelValorPagar5.setText("Total");

        javax.swing.GroupLayout abaHistoricosLayout = new javax.swing.GroupLayout(abaHistoricos);
        abaHistoricos.setLayout(abaHistoricosLayout);
        abaHistoricosLayout.setHorizontalGroup(
            abaHistoricosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaHistoricosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaHistoricosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelValorPagar5)
                    .addGroup(abaHistoricosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaHistoricosLayout.createSequentialGroup()
                            .addGroup(abaHistoricosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(comboHistoricosRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(32, 32, 32)
                            .addGroup(abaHistoricosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(abaHistoricosLayout.createSequentialGroup()
                                    .addComponent(jFTValorHistoricosRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                    .addComponent(btnIncluirHistoricoRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(abaHistoricosLayout.createSequentialGroup()
                                    .addComponent(labelValorPagar4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaHistoricosLayout.createSequentialGroup()
                            .addComponent(jFTotalHistoricosRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoverHistoricoRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(278, 278, 278))
        );
        abaHistoricosLayout.setVerticalGroup(
            abaHistoricosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaHistoricosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaHistoricosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaHistoricosLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboHistoricosRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaHistoricosLayout.createSequentialGroup()
                        .addComponent(labelValorPagar4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaHistoricosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTValorHistoricosRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIncluirHistoricoRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(labelValorPagar5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaHistoricosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTotalHistoricosRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoverHistoricoRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rateio Contábil", abaHistoricos);

        abaRateio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        abaRateio.setPreferredSize(new java.awt.Dimension(929, 341));

        jLabel1.setText("Centro de Custos");

        labelValorPagar2.setText("Valor");

        btnIncluirCCRateio.setBackground(new java.awt.Color(102, 204, 0));
        btnIncluirCCRateio.setText("Incluir");
        btnIncluirCCRateio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirCCRateioActionPerformed(evt);
            }
        });

        tableCentroCusto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tableCentroCusto.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tableCentroCustoPropertyChange(evt);
            }
        });
        jScrollPane4.setViewportView(tableCentroCusto);

        btnRemoverCCRateio.setBackground(new java.awt.Color(153, 153, 0));
        btnRemoverCCRateio.setText("Remover");
        btnRemoverCCRateio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverCCRateioActionPerformed(evt);
            }
        });

        jFTotalRateio.setEditable(false);

        labelValorPagar3.setText("Total Rateio");

        javax.swing.GroupLayout abaRateioLayout = new javax.swing.GroupLayout(abaRateio);
        abaRateio.setLayout(abaRateioLayout);
        abaRateioLayout.setHorizontalGroup(
            abaRateioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaRateioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaRateioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelValorPagar3)
                    .addGroup(abaRateioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaRateioLayout.createSequentialGroup()
                            .addGroup(abaRateioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(comboCentroCustoRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(32, 32, 32)
                            .addGroup(abaRateioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(abaRateioLayout.createSequentialGroup()
                                    .addComponent(jFTValorRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 102, Short.MAX_VALUE)
                                    .addComponent(btnIncluirCCRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(abaRateioLayout.createSequentialGroup()
                                    .addComponent(labelValorPagar2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaRateioLayout.createSequentialGroup()
                            .addComponent(jFTotalRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRemoverCCRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 667, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(278, 278, 278))
        );
        abaRateioLayout.setVerticalGroup(
            abaRateioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaRateioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaRateioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaRateioLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCentroCustoRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaRateioLayout.createSequentialGroup()
                        .addComponent(labelValorPagar2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaRateioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTValorRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnIncluirCCRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(labelValorPagar3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaRateioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTotalRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRemoverCCRateio, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rateio de Custos", abaRateio);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 980, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        protegeAba(abaBasica, true);
        protegeAba(abaComplementar, true);
        protegeAba(abaNf, true);
        protegeAba(abaRateio, true);
        protegeAba(abaHistoricos, true);

        labelExport.setVisible(false);
//        labelCartao.setVisible(false);
        labelSituacaoVencido.setVisible(false);
        //campos default
        //sempre deve ser o primeiro registro no banco
        comboCentroCusto.setSelectedIndex(0);

        //nota fiscal
        comboIss.setSelectedIndex(0);
        comboTipoNotaFiscal.setSelectedIndex(0);

        jTContaContabilCredito.setText("");
        jTContaContabilDebito.setText("");
        this.contaContabilDebito = null;
        this.contaContabilCredito = null;

        jChProvisao.setSelected(false);
        botaoAlterar.setEnabled(false);
        botaoIncluir.setEnabled(false);
        botaoPagar.setEnabled(true);
        botaoExcluir.setEnabled(false);

        jTParcela.setEditable(true);

        campoCodigo.setText("");

        comboTitulo.setSelectedItem(null);

        comboCentroCusto.setSelectedIndex(0);
        comboFuncionario.setSelectedIndex(0);
        comboHistorico.setSelectedItem(null);
        comboCedente.setSelectedItem(null);
        comboLocalPagamento.setSelectedIndex(0);
        comboMoeda.setSelectedIndex(0);
        comboTipoCobranca.setSelectedIndex(0);

        jTContaContabilCredito.setVisible(false);
        lupaPesquisaContaContabilCredito.setVisible(false);
        botaoIncluir.setEnabled(false);
        botaoPagar.setEnabled(true);
        labelDataVencimento.setText("Data Pagamento");
        labelContaContabilCredito.setText("");

        jTIdStatus.setText(Constantes.STATUS_TITULO_NOVO);
        campoIdNF.setText("");

        jFTValorPagar.setText("0,00");
        jFTValorAdiantamento.setText("0,00");
        labelVlrAdiantado.setVisible(false);
        jFTValorAdiantamento.setVisible(false);

        jFTDataVencimento.setDate(null);
        labelAtualizacao.setText("");
        jFTDataEmissao.setDate(Controller.getDataServidorBD());
        jFTDataProtocolo.setDate(Controller.getDataServidorBD());
        jTDescricao.setText("");
        jTNotaFiscal.setText("");
        jFTPrevisaoCartorio.setDate(Controller.getDataServidorBD());
        jTObservacao.setText("");
        jFTCodigoBarras.setValue(null);
        jTCarteira.setText("");
        jTContrato.setText("");
        jTParcela.setText("1");
        labelSituacaoTitulo.setText("");
        labelDataPagamento.setText("");
        labelDataPagamento.setVisible(false);

        //nota fiscal
        comboTipoNotaFiscal.setSelectedIndex(0);

        jTNotaFiscal.setText("");
        jTSerieNF.setText("A");
        jTSubSerieNF.setText("");
        jFTValorNF.setText("0,00");
        jFTValorISSRetido.setText("0,00");
        jFTDataEmissaoNF.setDate(null);
        jFTDataRetencaoISS.setDate(null);
        comboIss.setSelectedIndex(0);
        jFtAliquota.setText("");
        jFTValorNF.setEditable(true);
        jTObservacaoNF.setText("");

        //HABILITAR OS CAMPOS
        comboTitulo.setEnabled(true);
        comboCedente.setEnabled(true);
        comboCentroCusto.setEnabled(true);
        comboFuncionario.setEnabled(true);
        comboHistorico.setEnabled(true);
        comboLocalPagamento.setEnabled(true);
        comboMoeda.setEnabled(true);
        comboTipoCobranca.setEnabled(true);
        comboIss.setEnabled(true);
        comboTipoNotaFiscal.setEnabled(true);
        jFTValorPagar.setEditable(true);
        jFTDataVencimento.setEditable(true);
        jFTDataEmissao.setEditable(true);
        jFTDataProtocolo.setEditable(true);
        jTDescricao.setEditable(true);
        jTNotaFiscal.setEditable(true);
        jFTPrevisaoCartorio.setEditable(true);
        //jTObservacao.setEditable(true);
        jFTCodigoBarras.setEditable(true);
        jTCarteira.setEditable(true);
        jTContrato.setEditable(true);
        jTParcela.setEditable(true);

        botaoGerar.setText("0");
        auxCentroCustosRateio = new ArrayList<TituloPagarRateioCCModel>();
        this.preencheTabelaCentroCusto();

        auxHistoricosRateio = new ArrayList<TituloPagarRateioHistoricoModel>();
        this.preencheTabelaHistoricos();

        try {

            Collection<CentroCustoModel> lCentroCusto = new ArrayList<CentroCustoModel>();

            Collection<HistoricoModel> lHistorico = new ArrayList<HistoricoModel>();

            CentroCustoModel centroCustoModel = new CentroCustoModel();

            centroCustoModel.setDescricao(" -> Selecione <- ");

            lCentroCusto.add(centroCustoModel);

            lCentroCusto.addAll(centroCustoBO.obterTodos(organizacaoModel));

            comboCentroCustoRateio.setModel(new javax.swing.DefaultComboBoxModel(lCentroCusto.toArray()));

            HistoricoModel historicoModel = new HistoricoModel();

            historicoModel.setDescricao(" -> Selecione <- ");

            lHistorico.add(historicoModel);

            String tipo = "P";

            lHistorico.addAll(historicoBO.obterTodosPorTipo(organizacaoModel, tipo));

            comboHistoricosRateio.setModel(new javax.swing.DefaultComboBoxModel(lHistorico.toArray()));

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

        this.limparCamposCentroCusto();
        this.limparCamposHistoricos();


    }//GEN-LAST:event_botaoLimparActionPerformed

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

                TituloPagarModel tab = new TituloPagarModel();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.setNumeroDocumento(valorCombo);

                tab = tituloPagarBO.consultarPorTemplate(tab);

                if (tab.getStatus().getPk().getId().equals(Constantes.STATUS_TITULO_PARCIAL)) {
                    exibeMensagemAviso("Título Parcialmente pago! Não pode ser excluído.", null);
                    return;
                }

                //um titulo que tenha cheque vinculado nao deve ser excluido
                if (tab.getChequeVinculado() != null && tab.getChequeVinculado().getPk() != null
                        && !tab.getChequeVinculado().getPk().getId().isEmpty()) {

                    exibeMensagemAviso("Título com cheque vinculado! Não pode ser excluído.\n CHEQUE : " + tab.getChequeVinculado().getNumeroCheque(), null);

                    return;

                }

                tab.getStatus().getPk().setId(Constantes.STATUS_TITULO_EXCLUIDO);

                tab.setDataRegistro(jFTDataEmissao.getDate());

                tab.setDataUltimaAtualizacao(Controller.getDataServidorBD());

                tab.setObservacao(tab.getObservacao() + "- excluído por " + Controller.getUsuarioLogado().getNome());

                tab.setUsuario(Controller.getUsuarioLogado());

                if (tab.getNotaFiscal() != null && !tab.getNotaFiscal().getPk().getId().isEmpty()) {
                    tab.getNotaFiscal().setMovimentoDiarioModel(registroMovimento("Deletar", tab.getNotaFiscal().getNumero(), " Nota excluida", tab.getNotaFiscal().getValor(), "Deletado"));
                }

                tab.setMovimentoDiarioModel(registroMovimento("Deletar", valorCombo, "Titulo " + tab.getNumeroDocumento() + " " + ((HistoricoModel) comboHistorico.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), tab.getValorNominal(), "Deletado"));

                tituloPagarBO.excluir(tab);

                this.botaoLimparActionPerformed(evt);

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
            //Colocar caracteres ASCII para a acentuação.
            exibeMensagemAviso("Selecione um Título!", "Selecione um Título!");

        }

    }//GEN-LAST:event_botaoExcluirActionPerformed

    /**
     * Valida os campos obrigatórios da tela
     *
     * @return
     */
    private Boolean validaCampos() {

        if (jTContaContabilCredito.isVisible() && contaContabilCredito == null) {
            jTContaContabilCredito.requestFocus();
            return false;
        }

        if (contaContabilDebito == null) {
            jTContaContabilDebito.requestFocus();
            return false;
        }

        if (jFTDataVencimento.getDate() == null) {
            jFTDataVencimento.requestFocus();
            return false;
        }

        if (jFTValorPagar.getText().equals("0,00")) {
            jFTValorPagar.requestFocus();
            return false;
        }

        if (comboCedente.getSelectedItem() == null) {
            comboCedente.requestFocus();
            return false;
        }

        if (comboCentroCusto.getSelectedIndex() == 0) {
            comboCentroCusto.requestFocus();
            return false;
        }

        if (comboTipoCobranca.getSelectedIndex() == 0) {
            comboTipoCobranca.requestFocus();
            return false;
        }

        if (comboLocalPagamento.getSelectedIndex() == 0) {
            comboLocalPagamento.requestFocus();
            return false;
        }

        if (jFTDataEmissao.getDate() == null) {
            jFTDataEmissao.requestFocus();
            return false;
        }

        if (jFTDataProtocolo.getDate() == null) {
            jFTDataProtocolo.requestFocus();
            return false;
        }

        if (comboHistorico.getSelectedItem() == null) {
            comboHistorico.requestFocus();
            return false;
        }

        if (jTDescricao.getText().isEmpty()) {
            jTDescricao.requestFocus();
            return false;
        }

        if (jTParcela.getText().isEmpty()) {
            jTParcela.requestFocus();
            return false;
        }

        if (comboFuncionario.getSelectedIndex() == 0) {
            comboFuncionario.requestFocus();
            return false;
        }

        if (!PempecUtil.validaPreenchimentoNumero(comboTitulo.getSelectedItem().toString())) {
            exibeMensagemAviso("O título só pode conter número.", null);
            comboTitulo.requestFocus();
            return false;
        }

        if ((campoCodigo.getText() == null || campoCodigo.getText().isEmpty()) && jChProvisao.isSelected() && !jFTDataVencimento.getDate().after(Controller.getDataServidorBD())) {
            // exibeMensagemAviso("A data de pagamento para título provisionado está incorreta!", null);
            jFTDataVencimento.requestFocus();
            //return false;
            return true;
        }

        return true;

    }

    private Boolean validaCamposNotaFiscal() {

        try {

            if (this.validaPreenchimentoCombo(comboCedente, true)) {

                CedenteModel cedente = (CedenteModel) comboCedente.getSelectedItem();

                cedente = cedenteBO.consultarPorTemplate(cedente);

                if (cedente.getCpfCnpj().equals(Constantes.CPF_PADRAO) || cedente.getCpfCnpj().equals(Constantes.CNPJ_PADRAO)) {

                    exibeMensagemAviso("O Cedente está com CPF/CNPJ incorreto.\n Corrija o cadastro do Cedente.", "ERRO NF");

                    return false;
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

        if (jFTValorNF.getText().equals("0,00")) {
            jFTValorNF.requestFocus();
            return false;
        }

        if (jFTDataEmissaoNF.getDate() == null) {
            jFTDataEmissaoNF.requestFocus();
            return false;
        }

        if (jTNotaFiscal.getText().isEmpty()) {
            jTNotaFiscal.requestFocus();
            return false;
        }

        if (comboTipoNotaFiscal.getSelectedIndex() == 0) {
            comboTipoNotaFiscal.requestFocus();
            return false;
        }

        //Validando Datas.
        if (comboIss.getSelectedIndex() == 1 && jFTDataRetencaoISS.getDate() == null) {
            jFTDataRetencaoISS.requestFocus();
            return false;
        }

        return true;

    }

    private Double calcularIss(String aliq, String baseCalculo) {

        Double imposto = 0d;
        Double alq = 0d;
        Double baseCalc = 0d;

        alq = PempecParse.stringToDouble(aliq);
        baseCalc = PempecParse.stringToDouble(baseCalculo);

        if (alq > 0 && baseCalc > 0) {

            imposto = ((alq / 100) * (baseCalc));
        }

        return imposto;
    }

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        String valorCombo = null;

        if (comboTitulo.getSelectedItem() != null) {
            valorCombo = comboTitulo.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                if (validaCampos()) {

                    if (!validaPreenchimentoTabelaHistoricos() || !validaPreenchimentoTabelaCentroCusto()) {
                        return;
                    }

                    if (auxCentroCustosRateio.size() > 0) {

                        if (!validaRateio(jFTValorPagar.getValue(), this.calculaValorRateio())) {
                            exibeMensagemAviso("RATEIO INCOMPLETO." + "\n Diferença de R$ " + PempecParse.doubleToZero(jFTValorPagar.getValue() - this.calculaValorRateio()) + " reais.", null);
                            return;
                        }
                    }

                    if (auxHistoricosRateio.size() > 0) {

                        if (!validaRateio(jFTValorPagar.getValue(), this.calculaValorHistoricosRateio())) {
                            exibeMensagemAviso("RATEIO CONTÁBIL INCOMPLETO." + "\n Diferença de R$ " + PempecParse.doubleToZero(jFTValorPagar.getValue() - this.calculaValorHistoricosRateio()) + " reais.", null);
                            return;
                        }
                    }

                    TituloPagarModel tab = new TituloPagarModel();

                    if (botaoGerar.getText().equals("1")) {

                        tab.setBotaoGerar(true);
                    } else {

                        tab.setBotaoGerar(false);
                    }

                    tab.setPk(new PKModel());

                    tab.getPk().setOrganizacao(organizacaoModel);

                    tab.getPk().setId(PempecKeyGenerator.generateKey());

                    tab.setNumeroDocumento(comboTitulo.getSelectedItem().toString());
                    tab.setDescricao(jTDescricao.getText().toUpperCase());

                    //verificar se o numero do titulo existe e altera. Ocorre erro de rede.
                    TituloPagarModel tabAux = new TituloPagarModel();
                    tabAux = tab;

                    tabAux = tituloPagarBO.consultarPorTemplate(tab);
                    //verifica se o numero do documento ja existe. devido a uso em rede

                    while (tabAux != null && tabAux.getNumeroDocumento() != null && (!tabAux.getNumeroDocumento().isEmpty())) {

                        String numero = PempecKeyGenerator.generateNumeroDocumentoTituloPagar();

                        List validos = new ArrayList();
                        validos = PempecKeyGenerator.generateNumeroDocumentoTituloPagar(PempecParse.stringToLong(numero), 1);

                        comboTitulo.setSelectedItem(validos.get(0));

                        tab.setNumeroDocumento(comboTitulo.getSelectedItem().toString());
                        valorCombo = tab.getNumeroDocumento();

                        tabAux = tituloPagarBO.consultarPorTemplate(tab);

                    }

                    //rateio do centro de custo
                    Set<TituloPagarRateioCCModel> cRateios = new HashSet<TituloPagarRateioCCModel>();

                    cRateios.addAll(auxCentroCustosRateio);

                    tab.setCollCentroCustosRateio(cRateios);

                    //rateio de historicos
                    Set<TituloPagarRateioHistoricoModel> cHistoricos = new HashSet<TituloPagarRateioHistoricoModel>();

                    cHistoricos.addAll(auxHistoricosRateio);

                    tab.setCollHistoricosRateio(cHistoricos);

                    tab.setCarteira(jTCarteira.getText());

                    tab.setContrato(jTContrato.getText());

                    tab.setCodigoBarras(jFTCodigoBarras.getText());

                    tab.setValorNominal(jFTValorPagar.getValue());

                    tab.setValorAntecipado(jFTValorAdiantamento.getValue());

                    tab.setParcela(jTParcela.getText());

                    tab.setObservacao(jTObservacao.getText());

                    tab.setContaContabilCredito(contaContabilCredito);

                    tab.setContaContabilDebito(contaContabilDebito);

                    tab.setDataRegistro(Controller.getDataServidorBD());

                    tab.setDataUltimaAtualizacao(Controller.getDataServidorBD());

                    if (jFTDataVencimento.getDate() != null) {
                        tab.setDataVencimento(PempecParse.dateToDate(jFTDataVencimento.getDate()));
                    }

                    tab.setMoeda(((String) comboMoeda.getSelectedItem()).toString());

                    TipoStatusModel tipoStatusModel = new TipoStatusModel();

                    tipoStatusModel.setPk(new PKModel());

                    tipoStatusModel.getPk().setId(Constantes.STATUS_TITULO_NOVO);

                    tab.setStatus(tipoStatusModel);

                    tab.setUsuario(Controller.getUsuarioLogado());

                    if (jChProvisao.isSelected()) {
                        tab.setProvisao(1);
                    } else {
                        tab.setProvisao(0);
                    }

                    //a previsao do cartorio default eh sempre 30 apos a data de vencimento.
                    //o campo deve ser preenchido e possivel de edição
                    if (jFTPrevisaoCartorio.getDate() != null) {
                        tab.setPrevisaoCartorio(jFTPrevisaoCartorio.getDate());
                    } else {
                        tab.setPrevisaoCartorio(Controller.getDataServidorBD());
                    }

                    //a data de emissao será preenchida c a data do dia e o usuario pode alterar
                    if (jFTDataEmissao.getDate() != null) {
                        tab.setDataEmissao(jFTDataEmissao.getDate());
                    }

                    //a data de protocolo será preenchida c a data do dia  e o usuario pode alterar
                    if (jFTDataProtocolo.getDate() != null) {
                        tab.setDataProtocolo(jFTDataProtocolo.getDate());
                    }

                    //Tratamento de Nulidade.
                    //Tratamento tbm da opção Selecione
                    if (comboCedente.getSelectedItem() != null && ((CedenteModel) comboCedente.getSelectedItem()).getPk() != null) {

                        tab.setCedente(new CedenteModel());
                        tab.getCedente().setPk(new PKModel());
                        tab.getCedente().getPk().setId(((CedenteModel) comboCedente.getSelectedItem()).getPk().getId());

                    }

                    if (comboTipoCobranca.getSelectedItem() != null && ((TipoCobrancaModel) comboTipoCobranca.getSelectedItem()).getPk() != null) {

                        tab.setTipoCobranca(new TipoCobrancaModel());
                        tab.getTipoCobranca().setPk(new PKModel());
                        tab.getTipoCobranca().getPk().setId(((TipoCobrancaModel) comboTipoCobranca.getSelectedItem()).getPk().getId());

                    }

                    if (comboLocalPagamento.getSelectedItem() != null && ((LocalPagamentoModel) comboLocalPagamento.getSelectedItem()).getPk() != null) {

                        tab.setLocalPagamento(new LocalPagamentoModel());
                        tab.getLocalPagamento().setPk(new PKModel());
                        tab.getLocalPagamento().getPk().setId(((LocalPagamentoModel) comboLocalPagamento.getSelectedItem()).getPk().getId());

                    }

                    if (comboCentroCusto.getSelectedItem() != null && ((CentroCustoModel) comboCentroCusto.getSelectedItem()).getPk() != null) {

                        tab.setCentroCusto(new CentroCustoModel());
                        tab.getCentroCusto().setPk(new PKModel());
                        tab.getCentroCusto().getPk().setId(((CentroCustoModel) comboCentroCusto.getSelectedItem()).getPk().getId());

                    }

                    if (comboHistorico.getSelectedItem() != null && ((HistoricoModel) comboHistorico.getSelectedItem()).getPk() != null) {

                        tab.setHistorico(new HistoricoModel());
                        tab.getHistorico().setPk(new PKModel());
                        tab.getHistorico().getPk().setId(((HistoricoModel) comboHistorico.getSelectedItem()).getPk().getId());
                    }

                    if (comboFuncionario.getSelectedItem() != null && ((FuncionarioModel) comboFuncionario.getSelectedItem()).getPk() != null) {

                        tab.setResponsavel(new FuncionarioModel());
                        tab.getResponsavel().setPk(new PKModel());
                        tab.getResponsavel().getPk().setId(((FuncionarioModel) comboFuncionario.getSelectedItem()).getPk().getId());
                    }

                    //Nota fiscal
                    if (!jTNotaFiscal.getText().isEmpty()) {

                        if (Controller.verificaParametroAtivo(Parametro.CCADP005.getCodigo())) {
                            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCADP005.getCodigo()), null);
                            jTNotaFiscal.setText("");
                            jTNotaFiscal.setEnabled(false);
                            return;
                        }

                        if (validaCamposNotaFiscal()) {

                            tab.setNotaFiscal(new NotaFiscalEntradaModel());

                            tab.getNotaFiscal().setPk(new PKModel());

                            tab.getNotaFiscal().getPk().setId(PempecKeyGenerator.generateKey());

                            if (!jTNotaFiscal.getText().isEmpty()) {
                                tab.getNotaFiscal().setNumero(jTNotaFiscal.getText());
                            }

                            if (!jTSerieNF.getText().isEmpty()) {
                                tab.getNotaFiscal().setSerie(jTSerieNF.getText().toUpperCase());
                            }
                            if (!jTSubSerieNF.getText().isEmpty()) {
                                tab.getNotaFiscal().setSubSerie(jTSubSerieNF.getText().toUpperCase());
                            }

                            if (comboTipoNotaFiscal.getSelectedItem() != null && ((TipoNotaFiscalModel) comboTipoNotaFiscal.getSelectedItem()).getPk() != null) {
                                tab.getNotaFiscal().setTipoDocumento(new TipoNotaFiscalModel());
                                tab.getNotaFiscal().getTipoDocumento().setPk(new PKModel());
                                tab.getNotaFiscal().getTipoDocumento().getPk().setOrganizacao(organizacaoModel);
                                tab.getNotaFiscal().getTipoDocumento().getPk().setId(((TipoNotaFiscalModel) comboTipoNotaFiscal.getSelectedItem()).getPk().getId());
                            }

                            if (jFtAliquota.getValue() > 0) {
                                tab.getNotaFiscal().setAliquota(jFtAliquota.getText());
                            }

                            if (!jFTValorPagar.getText().isEmpty()) {
                                tab.getNotaFiscal().setValor(PempecParse.stringToDouble(jFTValorNF.getText()));
                            }

                            if (!jFTValorISSRetido.getText().isEmpty()) {
                                tab.getNotaFiscal().setValorIss(PempecParse.stringToDouble(jFTValorISSRetido.getText()));
                            }

                            if (!jFTValorNF.getText().isEmpty()) {
                                tab.getNotaFiscal().setBaseCalculo(PempecParse.stringToDouble(jFTValorNF.getText()));
                            }

                            if (!jTObservacaoNF.getText().isEmpty()) {
                                tab.getNotaFiscal().setObservacao(jTObservacaoNF.getText());
                            }

                            tab.getNotaFiscal().setDataRegistro(Controller.getDataServidorBD());
                            tab.getNotaFiscal().setUsuario(Controller.getUsuarioLogado());

                            String descricaoNota = "NI";

                            if (comboHistorico.getSelectedItem() != null) {

                                descricaoNota = comboHistorico.getSelectedItem() + " " + jTDescricao.getText().toUpperCase();

                            } else {
                                descricaoNota = jTDescricao.getText().toUpperCase();

                            }

                            tab.getNotaFiscal().setDescricao(descricaoNota);

                            if (tab.getResponsavel() != null) {

                                tab.getNotaFiscal().setResponsavel(tab.getResponsavel());
                            }

                            if (jFTDataRetencaoISS.getDate() != null) {
                                tab.getNotaFiscal().setDataRetencao(jFTDataRetencaoISS.getDate());
                            }

                            if (jFTDataEmissaoNF.getDate() != null) {
                                tab.getNotaFiscal().setDataEmissao(jFTDataEmissaoNF.getDate());
                            }

                            if (jFTDataProtocolo.getDate() != null) {
                                tab.getNotaFiscal().setDataProtocolo(jFTDataProtocolo.getDate());
                            }

                            tab.getNotaFiscal().setMovimentoDiarioModel(registroMovimento("Inserir NF", tab.getNotaFiscal().getNumero(), descricaoNota, tab.getNotaFiscal().getValor(), "Inserido"));
                            tab.getNotaFiscal().getMovimentoDiarioModel().setObjeto(tab.getNotaFiscal().getClass().getSimpleName());

                        } else { // fim do if na nf

                            exibeMensagemAviso("A nota fiscal não foi preenchida corretamente e não será registrada.", null);

                        }

                    } //fim da nf

                    tab.setMovimentoDiarioModel(registroMovimento("Inserir", valorCombo, "Titulo " + tab.getNumeroDocumento() + " " + ((HistoricoModel) comboHistorico.getSelectedItem()).getDescricao() + " " + tab.getDescricao() + " PARCELA  " + tab.getParcela() + " DE " + tab.getParcela(), tab.getValorNominal(), "Inserido"));

                    if (exibeMensagemConfirmacao("Deseja adicionar novas Parcelas?", "Parcelas")) {

                        try {

                            if (cadastroTituloPagarParcelas == null || cadastroTituloPagarParcelas.isClosed()) {

                                cadastroTituloPagarParcelas = new CadastroTituloPagarParcelas(tab);
                                TelaPrincipal.desktopPane.add(cadastroTituloPagarParcelas);
                                cadastroTituloPagarParcelas.setVisible(true);
                                cadastroTituloPagarParcelas.setPositionCenter();

                                cadastroTituloPagarParcelas.show();

                            } else {
                                cadastroTituloPagarBaixa.setVisible(true);
                                cadastroTituloPagarParcelas.moveToFront();
                                cadastroTituloPagarParcelas.requestFocus();
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

                        tituloPagarBO.inserir(tab);
                        auxHistoricosRateio = new ArrayList<TituloPagarRateioHistoricoModel>();
                        auxCentroCustosRateio = new ArrayList<TituloPagarRateioCCModel>();

                    }

                    this.botaoLimparActionPerformed(evt);

                } else {

                    exibeMensagemAviso(Controller.getMsgCamposObrigatorios(), null);

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

            exibeMensagemAviso("Selecione um título!.", null);

        }


    }//GEN-LAST:event_botaoIncluirActionPerformed

private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

    String valorCombo = null;

    if (comboTitulo.getSelectedItem() != null) {
        valorCombo = comboTitulo.getSelectedItem().toString();
    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCampos()) {

                if (!validaPreenchimentoTabelaHistoricos() || !validaPreenchimentoTabelaCentroCusto()) {
                    return;
                }

                if (auxCentroCustosRateio.size() > 0) {

                    if (!validaRateio(jFTValorPagar.getValue(), this.calculaValorRateio())) {
                        exibeMensagemAviso("RATEIO INCOMPLETO." + "\n Diferença de R$ " + PempecParse.doubleToZero(jFTValorPagar.getValue() - this.calculaValorRateio()) + " reais.", null);
                        return;
                    }
                }

                if (auxHistoricosRateio.size() > 0) {

                    if (!validaRateio(jFTValorPagar.getValue(), this.calculaValorHistoricosRateio())) {
                        exibeMensagemAviso("RATEIO CONTÁBIL INCOMPLETO." + "\n Diferença de R$ " + PempecParse.doubleToZero(jFTValorPagar.getValue() - this.calculaValorHistoricosRateio()) + " reais.", null);
                        return;
                    }
                }

                TituloPagarModel tab = new TituloPagarModel();

                tab.setPk(new PKModel());

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab.setNumeroDocumento(comboTitulo.getSelectedItem().toString());

                tab = tituloPagarBO.consultarPorTemplate(tab);

                Date dataRegistro = tab.getDataRegistro();

                tab.setDataRegistro(PempecParse.dateToDate(dataRegistro));

                tab.setDataUltimaAtualizacao(Controller.getDataServidorBD());

                if (jChProvisao.isSelected()) {
                    tab.setProvisao(1);
                    tab.setRegistroProvisao(jTRegistroProvisao.getText());
                } else {
                    tab.setProvisao(0);
                    tab.setRegistroProvisao(null);
                }

                //rateio do centro de custo
                Set<TituloPagarRateioCCModel> cRateios = new HashSet<TituloPagarRateioCCModel>();

                cRateios.addAll(auxCentroCustosRateio);

                tab.setCollCentroCustosRateio(cRateios);

                //rateio de historicos
                Set<TituloPagarRateioHistoricoModel> cHistoricos = new HashSet<TituloPagarRateioHistoricoModel>();

                cHistoricos.addAll(auxHistoricosRateio);

                tab.setCollHistoricosRateio(cHistoricos);

                //a previsao do cartorio default eh sempre 30 apos a data de vencimento.
                //o campo deve ser preenchido e possivel de edição
                if (jFTPrevisaoCartorio.getDate() != null) {
                    tab.setPrevisaoCartorio(jFTPrevisaoCartorio.getDate());
                } else {
                    tab.setPrevisaoCartorio(PempecParse.dateToDate(PempecUtil.addDayDate(tab.getDataPagamento(), 30)));
                }

                //a data de emissao será preenchida c a data do dia e o usuario pode alterar
                if (jFTDataEmissao.getDate() != null) {
                    tab.setDataEmissao(PempecParse.dateToDate(jFTDataEmissao.getDate()));
                }

                //a data de protocolo será preenchida c a data do dia  e o usuario pode alterar
                if (jFTDataProtocolo.getDate() != null) {
                    tab.setDataProtocolo(PempecParse.dateToDate(jFTDataProtocolo.getDate()));
                }

                if (jFTDataVencimento.getDate() != null) {
                    tab.setDataVencimento(PempecParse.dateToDate(jFTDataVencimento.getDate()));
                }

                tab.setDescricao(jTDescricao.getText().toUpperCase());

                tab.setCarteira(jTCarteira.getText());

                tab.setContrato(jTContrato.getText());

                tab.setCodigoBarras(jFTCodigoBarras.getText());

                tab.setValorNominal(jFTValorPagar.getValue());

                tab.setValorAntecipado(jFTValorAdiantamento.getValue());

                tab.setParcela(jTParcela.getText());

                tab.setObservacao(jTObservacao.getText());

                tab.setContaContabilCredito(contaContabilCredito);

                tab.setContaContabilDebito(contaContabilDebito);

                tab.setMoeda(((String) comboMoeda.getSelectedItem()).toString());

                TipoStatusModel tipoStatusModel = new TipoStatusModel();

                tipoStatusModel.setPk(new PKModel());

                tipoStatusModel.getPk().setId(jTIdStatus.getText());

                tab.setStatus(tipoStatusModel);

                tab.setUsuario(Controller.getUsuarioLogado());

                //Tratamento de Nulidade.
                //Tratamento tbm da opção Selecione
                if (comboCedente.getSelectedItem() != null && ((CedenteModel) comboCedente.getSelectedItem()).getPk() != null) {

                    tab.setCedente(new CedenteModel());
                    tab.getCedente().setPk(new PKModel());
                    tab.getCedente().getPk().setId(((CedenteModel) comboCedente.getSelectedItem()).getPk().getId());

                }

                if (comboTipoCobranca.getSelectedItem() != null && ((TipoCobrancaModel) comboTipoCobranca.getSelectedItem()).getPk() != null) {

                    tab.setTipoCobranca(new TipoCobrancaModel());
                    tab.getTipoCobranca().setPk(new PKModel());
                    tab.getTipoCobranca().getPk().setId(((TipoCobrancaModel) comboTipoCobranca.getSelectedItem()).getPk().getId());

                }

                if (comboLocalPagamento.getSelectedItem() != null && ((LocalPagamentoModel) comboLocalPagamento.getSelectedItem()).getPk() != null) {

                    tab.setLocalPagamento(new LocalPagamentoModel());
                    tab.getLocalPagamento().setPk(new PKModel());
                    tab.getLocalPagamento().getPk().setId(((LocalPagamentoModel) comboLocalPagamento.getSelectedItem()).getPk().getId());

                }

                if (comboCentroCusto.getSelectedItem() != null && ((CentroCustoModel) comboCentroCusto.getSelectedItem()).getPk() != null) {

                    tab.setCentroCusto(new CentroCustoModel());
                    tab.getCentroCusto().setPk(new PKModel());
                    tab.getCentroCusto().getPk().setId(((CentroCustoModel) comboCentroCusto.getSelectedItem()).getPk().getId());

                }

                if (comboHistorico.getSelectedItem() != null && ((HistoricoModel) comboHistorico.getSelectedItem()).getPk() != null) {

                    tab.setHistorico(new HistoricoModel());
                    tab.getHistorico().setPk(new PKModel());
                    tab.getHistorico().getPk().setId(((HistoricoModel) comboHistorico.getSelectedItem()).getPk().getId());
                }

                if (comboFuncionario.getSelectedItem() != null && ((FuncionarioModel) comboFuncionario.getSelectedItem()).getPk() != null) {

                    tab.setResponsavel(new FuncionarioModel());
                    tab.getResponsavel().setPk(new PKModel());
                    tab.getResponsavel().getPk().setId(((FuncionarioModel) comboFuncionario.getSelectedItem()).getPk().getId());
                }

                //Nota fiscal 
                if (!jTNotaFiscal.getText().isEmpty()) {

                    if (Controller.verificaParametroAtivo(Parametro.CCADP003.getCodigo())) {
                        exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCADP003.getCodigo()), null);
                        jTNotaFiscal.setText("");
                        return;
                    }

                    if (validaCamposNotaFiscal()) {

                        tab.setNotaFiscal(new NotaFiscalEntradaModel());

                        tab.getNotaFiscal().setPk(new PKModel());

                        if (campoIdNF.getText() != null && !campoIdNF.getText().isEmpty()) {
                            tab.getNotaFiscal().getPk().setId(campoIdNF.getText());

                        } else {

                            tab.getNotaFiscal().getPk().setId(null);

                        }

                        tab.getNotaFiscal().getPk().setOrganizacao(organizacaoModel);

                        if (!jTNotaFiscal.getText().isEmpty()) {
                            tab.getNotaFiscal().setNumero(jTNotaFiscal.getText());
                        }

                        if (!jTSerieNF.getText().isEmpty()) {
                            tab.getNotaFiscal().setSerie(jTSerieNF.getText().toUpperCase());
                        }
                        if (!jTSubSerieNF.getText().isEmpty()) {
                            tab.getNotaFiscal().setSubSerie(jTSubSerieNF.getText().toUpperCase());
                        }

                        if (comboTipoNotaFiscal.getSelectedItem() != null && ((TipoNotaFiscalModel) comboTipoNotaFiscal.getSelectedItem()).getPk() != null) {

                            tab.getNotaFiscal().setTipoDocumento(new TipoNotaFiscalModel());
                            tab.getNotaFiscal().getTipoDocumento().setPk(new PKModel());
                            tab.getNotaFiscal().getTipoDocumento().getPk().setId(((TipoNotaFiscalModel) comboTipoNotaFiscal.getSelectedItem()).getPk().getId());
                        }

                        if (jFtAliquota.getText() != null && !jFtAliquota.getText().isEmpty()) {
                            tab.getNotaFiscal().setAliquota(PempecParse.doubleToZero(jFtAliquota.getValue()));
                        }

                        if (!jFTValorNF.getText().isEmpty()) {
                            tab.getNotaFiscal().setValor(PempecParse.stringToDouble(jFTValorNF.getText()));
                        }

                        if (!jFTValorISSRetido.getText().isEmpty()) {
                            tab.getNotaFiscal().setValorIss(PempecParse.stringToDouble(jFTValorISSRetido.getText()));
                        }

                        if (!jFTValorNF.getText().isEmpty()) {
                            tab.getNotaFiscal().setBaseCalculo(PempecParse.stringToDouble(jFTValorNF.getText()));
                        }

                        if (!jTObservacaoNF.getText().isEmpty() && jTObservacaoNF.getText() != null) {
                            tab.getNotaFiscal().setObservacao(jTObservacaoNF.getText());
                        }

                        tab.getNotaFiscal().setDataRegistro(Controller.getDataServidorBD());
                        tab.getNotaFiscal().setUsuario(Controller.getUsuarioLogado());

                        String descricaoNota = "NI";

                        if (comboHistorico.getSelectedItem() != null) {

                            descricaoNota = comboHistorico.getSelectedItem() + " " + jTDescricao.getText().toUpperCase();

                        } else {
                            descricaoNota = jTDescricao.getText().toUpperCase();

                        }

                        tab.getNotaFiscal().setDescricao(descricaoNota);

                        if (tab.getResponsavel() != null) {

                            tab.getNotaFiscal().setResponsavel(tab.getResponsavel());
                        }

                        if (jFTDataRetencaoISS.getDate() != null) {
                            tab.getNotaFiscal().setDataRetencao(PempecParse.dateToDate(jFTDataRetencaoISS.getDate()));
                        }

                        if (jFTDataEmissaoNF.getDate() != null) {
                            tab.getNotaFiscal().setDataEmissao(PempecParse.dateToDate(jFTDataEmissaoNF.getDate()));
                        }

                        if (jFTDataProtocolo.getDate() != null) {
                            tab.getNotaFiscal().setDataProtocolo(PempecParse.dateToDate(jFTDataProtocolo.getDate()));
                        }

                        tab.getNotaFiscal().setMovimentoDiarioModel(registroMovimento("Alterar", tab.getNotaFiscal().getNumero(), descricaoNota, tab.getNotaFiscal().getValor(), "Alterada"));

                    } else {

                        exibeMensagemAviso("A nota fiscal não foi preenchida corretamente e não será registrada.", null);

                    }
                }

                tab.setMovimentoDiarioModel(registroMovimento("Alt Tit Pagar", valorCombo, "Titulo " + tab.getNumeroDocumento() + " " + ((HistoricoModel) comboHistorico.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), tab.getValorNominal(), "Alterado"));
                tituloPagarBO.alterar(tab);

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
        exibeMensagemAviso("Selecione um Título!", null);
    }

}//GEN-LAST:event_botaoAlterarActionPerformed

private void botaoPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoPagarActionPerformed
//GEN-LAST:event_botaoPagarActionPerformed
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

                    TituloPagarModel tab = null;

                    if (campoCodigo.getText().isEmpty()) {

                        if (!validaPreenchimentoTabelaHistoricos() || !validaPreenchimentoTabelaCentroCusto()) {
                            return;
                        }

                        if (auxCentroCustosRateio.size() > 0) {
                            if (!validaRateio(jFTValorPagar.getValue(), this.calculaValorRateio())) {
                                exibeMensagemAviso("RATEIO INCOMPLETO." + "\n Diferença de R$ " + PempecParse.doubleToZero(jFTValorPagar.getValue() - this.calculaValorRateio()) + " reais.", null);
                                return;
                            }
                        }

                        if (auxHistoricosRateio.size() > 0) {
                            if (!validaRateio(jFTValorPagar.getValue(), this.calculaValorHistoricosRateio())) {
                                exibeMensagemAviso("RATEIO CONTÁBIL INCOMPLETO." + "\n Diferença de R$ " + PempecParse.doubleToZero(jFTValorPagar.getValue() - this.calculaValorHistoricosRateio()) + " reais.", null);
                                return;
                            }
                        }

                        tab = new TituloPagarModel();

                        tab.setNumeroDocumento(valorCombo);

                        tab.setDescricao(jTDescricao.getText().toUpperCase());

                        tab.setPk(new PKModel());

                        tab.getPk().setOrganizacao(organizacaoModel);

                        tab.getPk().setId(PempecKeyGenerator.generateKey());

                        //verificar se o numero do titulo existe e altera. Ocorre erro de rede.
                        TituloPagarModel tabAux = new TituloPagarModel();
                        tabAux = tab;

                        tabAux = tituloPagarBO.consultarPorTemplate(tab);
                        //verifica se o numero do documento ja existe. devido a uso em rede

                        while (tabAux != null && tabAux.getNumeroDocumento() != null && (!tabAux.getNumeroDocumento().isEmpty())) {

                            String numero = PempecKeyGenerator.generateNumeroDocumentoTituloPagar();

                            List validos = new ArrayList();
                            validos = PempecKeyGenerator.generateNumeroDocumentoTituloPagar(PempecParse.stringToLong(numero), 1);

                            comboTitulo.setSelectedItem(validos.get(0));

                            tab.setNumeroDocumento(comboTitulo.getSelectedItem().toString());
                            valorCombo = tab.getNumeroDocumento();

                            tabAux = tituloPagarBO.consultarPorTemplate(tab);

                        }

                        //rateio do centro de custo
                        Set<TituloPagarRateioCCModel> cRateios = new HashSet<TituloPagarRateioCCModel>();

                        cRateios.addAll(auxCentroCustosRateio);

                        tab.setCollCentroCustosRateio(cRateios);

                        //rateio de historicos
                        Set<TituloPagarRateioHistoricoModel> cHistoricos = new HashSet<TituloPagarRateioHistoricoModel>();

                        cHistoricos.addAll(auxHistoricosRateio);

                        tab.setCollHistoricosRateio(cHistoricos);

                        tab.setCarteira(jTCarteira.getText().trim());

                        tab.setContrato(jTContrato.getText().trim());

                        tab.setCodigoBarras(jFTCodigoBarras.getText().trim());

                        tab.setValorNominal(jFTValorPagar.getValue());

                        tab.setParcela(jTParcela.getText().trim());

                        tab.setObservacao(jTObservacao.getText().trim());

                        if (jChProvisao.isSelected()) {
                            tab.setProvisao(1);
                        } else {
                            tab.setProvisao(0);
                        }

                        tab.setContaContabilCredito(contaContabilCredito);

                        tab.setContaContabilDebito(contaContabilDebito);

                        tab.setDataRegistro(Controller.getDataServidorBD());

                        tab.setDataUltimaAtualizacao(Controller.getDataServidorBD());

                        if (jFTDataVencimento.getDate() != null) {
                            tab.setDataVencimento(jFTDataVencimento.getDate());
                        }

                        tab.setMoeda(((String) comboMoeda.getSelectedItem()).toString());

                        TipoStatusModel tipoStatusModel = new TipoStatusModel();

                        tipoStatusModel.setPk(new PKModel());

                        tipoStatusModel.getPk().setId(Constantes.STATUS_TITULO_NOVO);
                        tipoStatusModel.getPk().setOrganizacao(organizacaoModel);

                        tab.setStatus(tipoStatusModel);

                        tab.setUsuario(Controller.getUsuarioLogado());

                        //a previsao do cartorio default eh sempre 30 apos a data de vencimento.
                        //o campo deve ser preenchido e possivel de edição
                        if (jFTPrevisaoCartorio.getDate() != null) {
                            tab.setPrevisaoCartorio(jFTPrevisaoCartorio.getDate());
                        } else {
                            tab.setPrevisaoCartorio(Controller.getDataServidorBD());
                        }

                        //a data de emissao será preenchida c a data do dia e o usuario pode alterar
                        if (jFTDataEmissao.getDate() != null) {
                            tab.setDataEmissao(jFTDataEmissao.getDate());
                        }

                        //a data de protocolo será preenchida c a data do dia  e o usuario pode alterar
                        if (jFTDataProtocolo.getDate() != null) {
                            tab.setDataProtocolo(jFTDataProtocolo.getDate());
                        }

                        //Tratamento de Nulidade.
                        //Tratamento tbm da opção Selecione
                        if (comboCedente.getSelectedItem() != null && ((CedenteModel) comboCedente.getSelectedItem()).getPk() != null) {

                            tab.setCedente(new CedenteModel());
                            tab.getCedente().setPk(new PKModel());
                            tab.getCedente().getPk().setOrganizacao(organizacaoModel);
                            tab.getCedente().getPk().setId(((CedenteModel) comboCedente.getSelectedItem()).getPk().getId());

                        }

                        if (comboTipoCobranca.getSelectedItem() != null && ((TipoCobrancaModel) comboTipoCobranca.getSelectedItem()).getPk() != null) {

                            tab.setTipoCobranca(new TipoCobrancaModel());
                            tab.getTipoCobranca().setPk(new PKModel());
                            tab.getTipoCobranca().getPk().setOrganizacao(organizacaoModel);
                            tab.getTipoCobranca().getPk().setId(((TipoCobrancaModel) comboTipoCobranca.getSelectedItem()).getPk().getId());

                        }

                        if (comboLocalPagamento.getSelectedItem() != null && ((LocalPagamentoModel) comboLocalPagamento.getSelectedItem()).getPk() != null) {

                            tab.setLocalPagamento(new LocalPagamentoModel());
                            tab.getLocalPagamento().setPk(new PKModel());
                            tab.getLocalPagamento().getPk().setOrganizacao(organizacaoModel);
                            tab.getLocalPagamento().getPk().setId(((LocalPagamentoModel) comboLocalPagamento.getSelectedItem()).getPk().getId());

                        }

                        if (comboCentroCusto.getSelectedItem() != null && ((CentroCustoModel) comboCentroCusto.getSelectedItem()).getPk() != null) {

                            tab.setCentroCusto(new CentroCustoModel());
                            tab.getCentroCusto().setPk(new PKModel());
                            tab.getCentroCusto().getPk().setOrganizacao(organizacaoModel);
                            tab.getCentroCusto().getPk().setId(((CentroCustoModel) comboCentroCusto.getSelectedItem()).getPk().getId());

                        }

                        if (comboHistorico.getSelectedItem() != null && ((HistoricoModel) comboHistorico.getSelectedItem()).getPk() != null) {

                            tab.setHistorico(new HistoricoModel());
                            tab.getHistorico().setPk(new PKModel());
                            tab.getHistorico().getPk().setOrganizacao(organizacaoModel);
                            tab.getHistorico().getPk().setId(((HistoricoModel) comboHistorico.getSelectedItem()).getPk().getId());
                        }

                        if (comboFuncionario.getSelectedItem() != null && ((FuncionarioModel) comboFuncionario.getSelectedItem()).getPk() != null) {

                            tab.setResponsavel(new FuncionarioModel());
                            tab.getResponsavel().setPk(new PKModel());
                            tab.getResponsavel().getPk().setOrganizacao(organizacaoModel);
                            tab.getResponsavel().getPk().setId(((FuncionarioModel) comboFuncionario.getSelectedItem()).getPk().getId());
                        }

                        //Nota fiscal
                        if (!jTNotaFiscal.getText().isEmpty()) {

                            if (Controller.verificaParametroAtivo(Parametro.CCADP003.getCodigo())) {
                                exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCADP003.getCodigo()), null);
                                jTNotaFiscal.setText("");
                                return;
                            }

                            if (validaCamposNotaFiscal()) {

                                tab.setNotaFiscal(new NotaFiscalEntradaModel());

                                tab.getNotaFiscal().setPk(new PKModel());

                                tab.getNotaFiscal().getPk().setId(PempecKeyGenerator.generateKey());

                                tab.getNotaFiscal().getPk().setOrganizacao(organizacaoModel);

                                if (!jTNotaFiscal.getText().isEmpty()) {
                                    tab.getNotaFiscal().setNumero(jTNotaFiscal.getText());
                                }

                                if (!jTSerieNF.getText().isEmpty()) {
                                    tab.getNotaFiscal().setSerie(jTSerieNF.getText().toUpperCase());
                                }

                                if (!jTSubSerieNF.getText().isEmpty()) {
                                    tab.getNotaFiscal().setSubSerie(jTSubSerieNF.getText().toUpperCase());
                                }

                                if (comboTipoNotaFiscal.getSelectedItem() != null && ((TipoNotaFiscalModel) comboTipoNotaFiscal.getSelectedItem()).getPk() != null) {
                                    tab.getNotaFiscal().setTipoDocumento(new TipoNotaFiscalModel());
                                    tab.getNotaFiscal().getTipoDocumento().setPk(new PKModel());
                                    tab.getNotaFiscal().getTipoDocumento().getPk().setId(((TipoNotaFiscalModel) comboTipoNotaFiscal.getSelectedItem()).getPk().getId());
                                }

                                if (jFtAliquota.getText() != null && !jFtAliquota.getText().isEmpty()) {
                                    tab.getNotaFiscal().setAliquota(PempecParse.doubleToZero(jFtAliquota.getValue()));
                                }

                                if (!jFTValorNF.getText().isEmpty()) {
                                    tab.getNotaFiscal().setValor(PempecParse.stringToDouble(jFTValorNF.getText()));
                                }

                                if (!jFTValorISSRetido.getText().isEmpty()) {
                                    tab.getNotaFiscal().setValorIss(PempecParse.stringToDouble(jFTValorISSRetido.getText()));
                                }

                                if (!jFTValorNF.getText().isEmpty()) {
                                    tab.getNotaFiscal().setBaseCalculo(PempecParse.stringToDouble(jFTValorNF.getText()));
                                }

                                if (!jTObservacaoNF.getText().isEmpty() && jTObservacaoNF.getText() != null) {
                                    tab.getNotaFiscal().setObservacao(jTObservacaoNF.getText());
                                }

                                tab.getNotaFiscal().setDataRegistro(Controller.getDataServidorBD());
                                tab.getNotaFiscal().setUsuario(Controller.getUsuarioLogado());

                                String descricaoNota = "NI";

                                if (comboHistorico.getSelectedItem() != null) {

                                    descricaoNota = comboHistorico.getSelectedItem() + " " + jTDescricao.getText().toUpperCase();

                                } else {
                                    descricaoNota = jTDescricao.getText().toUpperCase();

                                }

                                tab.getNotaFiscal().setDescricao(descricaoNota);

                                if (tab.getResponsavel() != null) {

                                    tab.getNotaFiscal().setResponsavel(tab.getResponsavel());
                                }

                                if (jFTDataRetencaoISS.getDate() != null) {
                                    tab.getNotaFiscal().setDataRetencao(PempecParse.dateToDate(jFTDataRetencaoISS.getDate()));
                                }

                                if (jFTDataEmissaoNF.getDate() != null) {
                                    tab.getNotaFiscal().setDataEmissao(PempecParse.dateToDate(jFTDataEmissaoNF.getDate()));
                                }

                                if (jFTDataProtocolo.getDate() != null) {
                                    tab.getNotaFiscal().setDataProtocolo(PempecParse.dateToDate(jFTDataProtocolo.getDate()));
                                }

                                tab.getNotaFiscal().setMovimentoDiarioModel(registroMovimento("Alterar", tab.getNotaFiscal().getNumero(), descricaoNota, tab.getNotaFiscal().getValor(), "Alterada"));

                            } else {

                                exibeMensagemAviso("A nota fiscal não foi preenchida corretamente e não será registrada.", null);

                            }

                        }

                        tab.setMovimentoDiarioModel(registroMovimento("Inserir", valorCombo, "Titulo " + tab.getNumeroDocumento() + " " + ((HistoricoModel) comboHistorico.getSelectedItem()).getDescricao() + " " + tab.getDescricao(), tab.getValorNominal(), "Pago"));
                        tab.getMovimentoDiarioModel().setObservacao("Utilizado botao Pagar");

                        tituloPagarBO.inserir(tab);

                    }

                    tab = new TituloPagarModel();

                    tab.setPk(new PKModel());
                    tab.getPk().setOrganizacao(organizacaoModel);
                    tab.setNumeroDocumento(valorCombo);

                    tab = tituloPagarBO.consultarPorTemplate(tab);

                    if (cadastroTituloPagarBaixa == null || cadastroTituloPagarBaixa.isClosed()) {
                        cadastroTituloPagarBaixa = new CadastroTituloPagarBaixa(tab);
                        TelaPrincipal.desktopPane.add(cadastroTituloPagarBaixa);
                        cadastroTituloPagarBaixa.show();
                    } else {
                        cadastroTituloPagarBaixa.popularCampos(tab);
                        cadastroTituloPagarBaixa.setVisible(true);
                        cadastroTituloPagarBaixa.moveToFront();
                        cadastroTituloPagarBaixa.requestFocus();
                    }

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

            exibeMensagemAviso("Selecione um Título!", null);

        }

    }

private void comboTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTituloActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTitulo.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            TituloPagarModel tab = new TituloPagarModel();

            tab.setNumeroDocumento(comboTitulo.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(organizacaoModel);

            tab = tituloPagarBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {

                labelExport.setVisible(false);
                botaoIncluir.setEnabled(false);
                labelSituacaoVencido.setVisible(false);

                campoCodigo.setText(tab.getPk().getId());

                if (tab.getDataPagamento() != null) {
                    labelDataPagamento.setText(PempecParse.dateToString(tab.getDataPagamento()));
                }

                if (tab.getValorAntecipado() != null) {

                    labelVlrAdiantado.setVisible(true);
                    jFTValorAdiantamento.setVisible(true);

                    jFTValorAdiantamento.setText(PempecParse.doubleToZero(tab.getValorAntecipado()));

                }

                jChProvisao.setSelected(tab.isProvisao());
                jChProvisaoActionPerformed(null);

                if (tab.getStatus().getPk().getId().equalsIgnoreCase(Constantes.STATUS_TITULO_EXCLUIDO)) {
                    protegeAbaExcept(abaBasica, false, comboTitulo);
                    protegeAba(abaComplementar, false);
                    protegeAba(abaNf, false);
                    protegeAba(abaRateio, false);
                    protegeAba(abaHistoricos, false);
                    botaoAlterar.setEnabled(false);
                    botaoExcluir.setEnabled(false);
                    botaoPagar.setEnabled(false);
                    botaoImprimirEspelho.setEnabled(true);
                    labelDataPagamento.setVisible(false);
                    jChProvisao.setEnabled(false);
                }

                if (tab.getStatus().getPk().getId().equalsIgnoreCase(Constantes.STATUS_TITULO_NOVO)) {
                    protegeAbaExcept(abaBasica, true, comboTitulo);
                    protegeAba(abaComplementar, true);
                    protegeAba(abaNf, true);
                    protegeAba(abaRateio, true);
                    protegeAba(abaHistoricos, true);
                    botaoAlterar.setEnabled(true);
                    botaoExcluir.setEnabled(true);
                    botaoImprimirEspelho.setEnabled(true);

                    if (tab.isProvisao()) {
                        botaoPagar.setEnabled(false);
                    } else {
                        botaoPagar.setEnabled(true);
                    }

                    labelDataPagamento.setVisible(false);
                    jChProvisao.setEnabled(true);
                }

                /*
                 if (tab.getStatus().getPk().getId().equalsIgnoreCase(Constantes.STATUS_TITULO_PARCIAL)) {
                 protegeAbaExcept(abaBasica, true, comboTitulo);
                 protegeAba(abaComplementar, true);
                 protegeAba(abaNf, true);
                 protegeAba(abaRateio, true);
                 protegeAba(abaHistoricos, true);
                 botaoAlterar.setEnabled(true);
                 botaoExcluir.setEnabled(false);
                 botaoPagar.setEnabled(true);
                 botaoImprimirEspelho.setEnabled(true);
                 labelDataPagamento.setVisible(true);
                 jChProvisao.setEnabled(false);
                 }


                 if (tab.getStatus().getPk().getId().equalsIgnoreCase(Constantes.STATUS_TITULO_PAGO)) {
                 protegeAbaExcept(abaBasica, true, comboTitulo);
                 protegeAba(abaComplementar, true);
                 protegeAba(abaNf, true);
                 protegeAba(abaRateio, true);
                 protegeAba(abaHistoricos, true);
                 labelDataPagamento.setVisible(true);
                 botaoAlterar.setEnabled(true);
                 botaoExcluir.setEnabled(false);
                 botaoPagar.setEnabled(false);
                 botaoImprimirEspelho.setEnabled(true);
                 jChProvisao.setEnabled(false);
                 }

                 */
                if (tab.getStatus().getPk().getId().equalsIgnoreCase(Constantes.STATUS_TITULO_PARCIAL)) {
                    protegeAbaExcept(abaBasica, false, comboTitulo);
                    protegeAba(abaComplementar, true);
                    protegeAba(abaNf, true);
                    protegeAba(abaRateio, true);
                    protegeAba(abaHistoricos, true);
                    botaoAlterar.setEnabled(true);
                    botaoExcluir.setEnabled(false);
                    if (tab.isProvisao()) {
                        botaoPagar.setEnabled(false);
                    } else {
                        botaoPagar.setEnabled(true);
                    }
                    botaoImprimirEspelho.setEnabled(true);
                    labelDataPagamento.setVisible(true);
                }

                if (tab.getStatus().getPk().getId().equalsIgnoreCase(Constantes.STATUS_TITULO_PAGO)) {
                    protegeAbaExcept(abaBasica, false, comboTitulo);
                    protegeAba(abaComplementar, false);
                    protegeAba(abaNf, false);
                    protegeAba(abaRateio, false);
                    protegeAba(abaHistoricos, false);
                    labelDataPagamento.setVisible(true);
                    botaoAlterar.setEnabled(false);
                    botaoExcluir.setEnabled(false);
                    botaoPagar.setEnabled(false);
                    botaoImprimirEspelho.setEnabled(true);
                }

                if (tab.getLoteContabil() != null && tab.getLoteContabil().getPk() != null) {
                    protegeAbaExcept(abaBasica, false, comboTitulo);
                    protegeAba(abaComplementar, false);
                    protegeAba(abaNf, false);
                    protegeAba(abaRateio, false);
                    protegeAba(abaHistoricos, false);
                    botaoAlterar.setEnabled(false);
                    botaoExcluir.setEnabled(false);
                    botaoPagar.setEnabled(false);
                    labelExport.setVisible(true);
                    labelExport.setText("Titulo exportado sob num. " + tab.getLoteContabil().getLote());
                }

                //Desabilitando a edição da parcela.
                jTParcela.setEditable(false);

                if (tab.getValorNominal() != null) {
                    jFTValorPagar.setText(PempecParse.doubleToZero(tab.getValorNominal()));
                }

                if (tab.getDataVencimento() != null) {

                    jFTDataVencimento.setDate(tab.getDataVencimento());

                }

                if (tab.getDataEmissao() != null) {

                    jFTDataEmissao.setDate(tab.getDataEmissao());

                }

                if (tab.getDataProtocolo() != null) {

                    jFTDataProtocolo.setDate(tab.getDataProtocolo());

                }

                if (tab.getDataUltimaAtualizacao() != null) {

                    labelAtualizacao.setText(PempecParse.dateToString(tab.getDataUltimaAtualizacao()));

                }

                if (tab.getRegistroProvisao() != null) {
                    jTRegistroProvisao.setText(tab.getRegistroProvisao());
                }

                if (tab.getPrevisaoCartorio() != null) {

                    jFTPrevisaoCartorio.setDate(tab.getPrevisaoCartorio());

                } else {
                    jFTPrevisaoCartorio.setDate(Controller.getDataServidorBD());
                }

                jTDescricao.setText(tab.getDescricao());

                if (tab.getStatus() != null) {

                    jTIdStatus.setText(tab.getStatus().getPk().getId());
                    labelSituacaoTitulo.setText(tab.getStatus().getDescricao());
                    if (tab.getDataVencimento() != null && tab.getDataVencimento().before(Controller.getDataServidorBD()) && (labelSituacaoTitulo.getText().equals(Constantes.STATUS_TITULO_NOVO) || labelSituacaoTitulo.getText().equals(Constantes.STATUS_TITULO_PARCIAL))) {
                        labelSituacaoVencido.setVisible(true);
                        labelSituacaoVencido.setText("Título Vencido!");
                    }

                }

                jTCarteira.setText(tab.getCarteira());

                jTParcela.setText(tab.getParcela());

                jTContrato.setText(tab.getContrato());

                jFTCodigoBarras.setText(tab.getCodigoBarras());

                jTObservacao.setText(tab.getObservacao());

                for (int i = 1; i < comboMoeda.getItemCount(); i++) {
                    if (tab.getMoeda().equalsIgnoreCase(((String) comboMoeda.getItemAt(i)).toString())) {
                        comboMoeda.setSelectedIndex(i);
                        break;
                    }
                }

                if (tab.getCedente() != null) {
                    for (int i = 0; i < comboCedente.getItemCount(); i++) {
                        if (tab.getCedente().getPk().getId().equalsIgnoreCase(((CedenteModel) comboCedente.getItemAt(i)).getPk().getId())) {
                            comboCedente.setSelectedIndex(i);
                            break;
                        }
                    }
                }

                if (tab.getContaContabilCredito() != null) {

                    this.contaContabilCredito = tab.getContaContabilCredito();

                    jTContaContabilCredito.setText(this.contaContabilCredito.getConta());

                }

                if (tab.getTipoCobranca() != null) {

                    for (int i = 1; i < comboTipoCobranca.getItemCount(); i++) {
                        if (tab.getTipoCobranca().getPk().getId().equalsIgnoreCase(((TipoCobrancaModel) comboTipoCobranca.getItemAt(i)).getPk().getId())) {
                            comboTipoCobranca.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getLocalPagamento() != null) {

                    for (int i = 1; i < comboLocalPagamento.getItemCount(); i++) {
                        if (tab.getLocalPagamento().getPk().getId().equalsIgnoreCase(((LocalPagamentoModel) comboLocalPagamento.getItemAt(i)).getPk().getId())) {
                            comboLocalPagamento.setSelectedIndex(i);
                            break;
                        }
                    }

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

                    for (int i = 1; i < comboFuncionario.getItemCount(); i++) {
                        if (tab.getResponsavel().getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboFuncionario.getItemAt(i)).getPk().getId())) {
                            comboFuncionario.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getHistorico() != null && tab.getHistorico().getPk() != null && !tab.getHistorico().getPk().getId().isEmpty()) {

                    for (int i = 0; i < comboHistorico.getItemCount(); i++) {
                        if (tab.getHistorico().getPk().getId().equalsIgnoreCase(((HistoricoModel) comboHistorico.getItemAt(i)).getPk().getId())) {
                            comboHistorico.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getContaContabilDebito() != null) {

                    this.contaContabilDebito = tab.getContaContabilDebito();

                    jTContaContabilDebito.setText(this.contaContabilDebito.getConta());

                }

                if (tab.getNotaFiscal() != null) {

                    NotaFiscalEntradaModel nota = tab.getNotaFiscal();

                    campoIdNF.setText(nota.getPk().getId());
                    jTNotaFiscal.setText(nota.getNumero());
                    jTSerieNF.setText(nota.getSerie());
                    jTSubSerieNF.setText(nota.getSubSerie());
                    jFtAliquota.setText(nota.getAliquota());
                    jTObservacaoNF.setText(nota.getObservacao());

                    if (nota.getTipoDocumento() != null) {

                        for (int i = 1; i < comboTipoNotaFiscal.getItemCount(); i++) {
                            if (tab.getNotaFiscal().getTipoDocumento().getPk().getId().equalsIgnoreCase(((TipoNotaFiscalModel) comboTipoNotaFiscal.getItemAt(i)).getPk().getId())) {
                                comboTipoNotaFiscal.setSelectedIndex(i);
                                break;
                            }
                        }
                    }

                    if (nota.getValor() != null) {
                        jFTValorNF.setText(PempecParse.doubleToZero(nota.getValor()));
                    }

                    if (nota.getValorIss() != null && nota.getValorIss() > 0) {

                        comboIss.setSelectedIndex(1);
                        jFTValorISSRetido.setText(PempecParse.doubleToZero(nota.getValorIss()));
                    } else {

                        comboIss.setSelectedIndex(0);
                    }

                    if (nota.getDataEmissao() != null) {
                        jFTDataEmissaoNF.setDate(nota.getDataEmissao());
                    }

                    if (nota.getDataRetencao() != null) {
                        jFTDataRetencaoISS.setDate(nota.getDataRetencao());
                    }

                }

                if (tab.getCollCentroCustosRateio() != null && !tab.getCollCentroCustosRateio().isEmpty()) {
                    auxCentroCustosRateio = new ArrayList<TituloPagarRateioCCModel>();
                    auxCentroCustosRateio.addAll(tab.getCollCentroCustosRateio());
                    this.preencheTabelaCentroCusto();

                    for (TituloPagarRateioCCModel tituloPagarRateioCCModel : auxCentroCustosRateio) {
                        this.repopularComboRateio(tituloPagarRateioCCModel.getCentroCustoModel(), "e");
                    }

                }

                if (tab.getCollHistoricosRateio() != null && !tab.getCollHistoricosRateio().isEmpty()) {
                    auxHistoricosRateio = new ArrayList<TituloPagarRateioHistoricoModel>();
                    auxHistoricosRateio.addAll(tab.getCollHistoricosRateio());
                    this.preencheTabelaHistoricos();

                    for (TituloPagarRateioHistoricoModel pagarRateioHistoricoModel : auxHistoricosRateio) {
                        this.repopularComboHistoricosRateio(pagarRateioHistoricoModel.getHistoricoModel(), "e");
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

    }

}//GEN-LAST:event_comboTituloActionPerformed

private void botaoGerarNumeroDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarNumeroDocumentoActionPerformed
    labelDataPagamento.setVisible(false);
    String numero = PempecKeyGenerator.generateNumeroDocumentoTituloPagar();

    List validos = new ArrayList();
    validos = PempecKeyGenerator.generateNumeroDocumentoTituloPagar(PempecParse.stringToLong(numero), 1);

    comboTitulo.setSelectedItem(validos.get(0));
    comboTitulo.requestFocus();
    botaoGerar.setText("1");
}//GEN-LAST:event_botaoGerarNumeroDocumentoActionPerformed

private void comboCedenteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCedenteItemStateChanged

    if (this.validaPreenchimentoCombo(comboCedente, true)) {

        CedenteModel cedente = (CedenteModel) comboCedente.getSelectedItem();

        try {

            cedente = cedenteBO.consultarPorTemplate(cedente);

            if (cedente.getCartaoCreditoModel() != null) {

                comboLocalPagamento.setSelectedIndex(1);

                comboTipoCobranca.setSelectedIndex(1);

                jChProvisao.setSelected(true);

                if (jChProvisao.isSelected()) {

                    jTContaContabilCredito.setVisible(true);
                    lupaPesquisaContaContabilCredito.setVisible(true);
                    jTContaContabilCredito.setText("");

                    if (labelDataPagamento.getText().isEmpty() && campoCodigo.getText().isEmpty()) {
                        botaoIncluir.setEnabled(true);
                    }

                    botaoPagar.setEnabled(false);
                    labelDataVencimento.setText("Data Vencimento");
                    labelContaContabilCredito.setText("Conta Contabil Crédito");
                }

                if (campoCodigo.getText() == null || campoCodigo.getText().isEmpty()) {

                    if (cedente.getCartaoCreditoModel().getBandeira() != null) {

                        jFTDataVencimento.setDate(null);

                        int diaCompra = PempecParse.stringToInteger(cedente.getCartaoCreditoModel().getDiaCompra());

                        int hoje = Integer.parseInt(PempecParse.dateToString(PempecParse.dateToDate(jFTDataEmissao.getDate())).substring(0, 2));

                        int diaVctoCartao = Integer.parseInt(cedente.getCartaoCreditoModel().getDiaVencimento());

                        if (hoje >= diaCompra || diaVctoCartao < diaCompra) {

                            String dataVcto = cedente.getCartaoCreditoModel().getDiaVencimento() + "/" + (new Date().getMonth() + 2) + "/" + "20" + (new Date().getYear() - 100);

                            jFTDataVencimento.setDate(PempecParse.stringToDate(dataVcto));

                        } else {

                            String dataVcto = cedente.getCartaoCreditoModel().getDiaVencimento() + "/" + (new Date().getMonth() + 1) + "/" + "20" + (new Date().getYear() - 100);

                            jFTDataVencimento.setDate(PempecParse.stringToDate(dataVcto));
                        }

                    } // fim do if da data
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

        if (cedente != null && cedente.getContaContabil() != null && cedente.getContaContabil().getPk() != null && jChProvisao.isSelected()) {

            this.contaContabilCredito = cedente.getContaContabil();

            jTContaContabilCredito.setText(this.contaContabilCredito.getConta());

        } else {

            jTContaContabilCredito.setText("");

            this.contaContabilCredito = null;
        }

    } else {
        jTContaContabilCredito.setText("");
    }
}//GEN-LAST:event_comboCedenteItemStateChanged

private void comboHistoricoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboHistoricoItemStateChanged

    if (this.validaPreenchimentoCombo(comboHistorico, true)) {

        HistoricoModel historico = (HistoricoModel) comboHistorico.getSelectedItem();

        if (historico != null && historico.getContaContabil() != null && historico.getContaContabil().getPk() != null) {
            this.contaContabilDebito = historico.getContaContabil();
            jTContaContabilDebito.setText(this.contaContabilDebito.getConta());
        } else {
            this.contaContabilDebito = null;
            jTContaContabilDebito.setText("");
        }
    }
}//GEN-LAST:event_comboHistoricoItemStateChanged

private void comboIssItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboIssItemStateChanged

    if (jFTValorNF.getValue() > 0) {
        jFTValorISSRetido.setEditable(true);
        jFTDataRetencaoISS.setEditable(true);
        jFTDataRetencaoISS.setDate(jFTDataEmissaoNF.getDate());
        jFTValorISSRetido.setText(PempecParse.doubleToZero(calcularIss(jFtAliquota.getText(), jFTValorNF.getText())));
    }

}//GEN-LAST:event_comboIssItemStateChanged

private void jFTValorPagarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTValorPagarFocusLost
    if (!jFTValorPagar.getText().isEmpty()) {
        Double valorPagar = PempecParse.stringToDouble(jFTValorPagar.getText());

        if (valorPagar > 0) {
            jFTValorNF.setText(PempecParse.doubleToZero(valorPagar));
            jFTValorNF.setForeground(new Color(124, 4, 4));
        } else {
            jFTValorNF.setText("0,00");
            jFTValorNF.setForeground(new Color(0, 0, 0));
        }

    }
}//GEN-LAST:event_jFTValorPagarFocusLost

private void jFtAliquotaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFtAliquotaFocusLost

    if (!jFtAliquota.getText().isEmpty() && jFtAliquota.getText() != null) {
        jFTDataRetencaoISS.setEnabled(true);
        jFTValorISSRetido.setEnabled(true);
        comboIss.setEnabled(true);
        comboIss.setSelectedIndex(1);
    }


}//GEN-LAST:event_jFtAliquotaFocusLost

private void jFTDataEmissaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTDataEmissaoFocusLost
    if (jFTDataEmissao.getDate() != null) {
        jFTDataEmissaoNF.setDate(jFTDataEmissao.getDate());
        jFTDataProtocolo.setDate(PempecParse.dateToDate(Controller.getDataServidorBD()));
    }
}//GEN-LAST:event_jFTDataEmissaoFocusLost

private void jFTDataVencimentoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTDataVencimentoFocusLost
// nao entendi o motivo disso aqui
    //    if (jFTDataVencimento.getDate() != null) {
//        jFTDataProtocolo.setDate(PempecUtil.addDayDate(jFTDataVencimento.getDate(), 30));
//    }
}//GEN-LAST:event_jFTDataVencimentoFocusLost

private void jFTDataEmissaoNFFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFTDataEmissaoNFFocusLost
    if (jFTDataEmissaoNF.getDate() != null) {
        jFTDataRetencaoISS.setDate(jFTDataEmissaoNF.getDate());
    }
}//GEN-LAST:event_jFTDataEmissaoNFFocusLost

private void btnRemoverCCRateioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverCCRateioActionPerformed

    int cont = 0;

    for (int i = 0; i < tableCentroCusto.getRowCount(); i++) {

        if (tableCentroCusto.getValueAt(i, 0).toString().equals("true")) {

            CentroCustoModel custoCustoTitulo = (CentroCustoModel) comboCentroCusto.getSelectedItem();

            if (custoCustoTitulo.getPk().getId().equals(tableCentroCusto.getValueAt(i, 4).toString()) && custoCustoTitulo.getPk().getOrganizacao().getId().equals(organizacaoModel.getId())) {
                exibeMensagemAviso("Não é possível remover esse Centro de Custo. Está definido como centro de custo padrão no título.", null);
                return;
            }

            cont++;

        }

    }

    if (cont == tableCentroCusto.getRowCount()) {

        auxCentroCustosRateio.removeAll(auxCentroCustosRateio);

        try {
            Collection<CentroCustoModel> lCentroCusto = new ArrayList<CentroCustoModel>();

            CentroCustoModel centroCustoModel = new CentroCustoModel();

            centroCustoModel.setDescricao(" -> Selecione <- ");

            lCentroCusto.add(centroCustoModel);

            lCentroCusto.addAll(centroCustoBO.obterTodos(organizacaoModel));

            comboCentroCustoRateio.setModel(new javax.swing.DefaultComboBoxModel(lCentroCusto.toArray()));

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

        this.preencheTabelaCentroCusto();

    } else {

        for (int i = 0; i < tableCentroCusto.getRowCount(); i++) {

            if (tableCentroCusto.getValueAt(i, 0).toString().equals("true")) {

                CentroCustoModel model = new CentroCustoModel();
                model.setPk(new PKModel());
                model.getPk().setOrganizacao(organizacaoModel);
                model.getPk().setId(tableCentroCusto.getValueAt(i, 4).toString());
                model.setCodigo(PempecParse.stringToInteger(tableCentroCusto.getValueAt(i, 1).toString()));
                model.setDescricao(tableCentroCusto.getValueAt(i, 2).toString());

                comboCentroCustoRateio.addItem(model);

                ((TableModelCadastroTituloPagarRateioCC) tableCentroCusto.getModel()).removeByCentroCusto(model);

            }

        }

        this.preencheTabelaCentroCusto();

    }

    this.limparCamposCentroCusto();

}//GEN-LAST:event_btnRemoverCCRateioActionPerformed

private void btnIncluirCCRateioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirCCRateioActionPerformed

    if (Controller.verificaParametroAtivo(Parametro.CCADP002.getCodigo())) {
        exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCADP002.getCodigo()), null);
        return;
    }

    if (validaCampos()) {

        if (this.validaCentroCusto()) {

            if ((calculaValorRateio() + jFTValorRateio.getValue()) > jFTValorPagar.getValue()) {
                exibeMensagemAviso("O valor total do rateio não pode ser maior que o valor do título a pagar.", null);
                return;
            }

            CentroCustoModel centroCusto = ((CentroCustoModel) comboCentroCustoRateio.getSelectedItem());

            TituloPagarRateioCCModel rateio = new TituloPagarRateioCCModel();

            rateio.setPk(new PKModel());

            rateio.getPk().setId(PempecKeyGenerator.generateKey());

            rateio.getPk().setOrganizacao(organizacaoModel);

            rateio.setCentroCustoModel(centroCusto);

            rateio.setValor(jFTValorRateio.getValue());

            auxCentroCustosRateio.add(rateio);

            this.repopularComboRateio(centroCusto, "e");

            this.preencheTabelaCentroCusto();

            this.limparCamposCentroCusto();

        } else {

            exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

        }

    } else {

        exibeMensagemAviso("Dados básicos do título não foram preenchidos.", null);

    }
}//GEN-LAST:event_btnIncluirCCRateioActionPerformed

private void botaoImprimirEspelhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoImprimirEspelhoActionPerformed

    String valorCombo = null;

    if (comboTitulo.getSelectedItem() != null) {
        valorCombo = comboTitulo.getSelectedItem().toString();
    }

    try {

        TituloPagarModel tab = new TituloPagarModel();
        tab.setPk(new PKModel());
        tab.getPk().setOrganizacao(organizacaoModel);
        tab.getPk().setId(campoCodigo.getText());
        tab.setNumeroDocumento(valorCombo);

        tab = tituloPagarBO.consultarPorTemplate(tab);

        if (espelho == null || espelho.isClosed() || !espelho.isVisible()) {
            espelho = new TituloPagarEspelho(tab);
            TelaPrincipal.desktopPane.add(espelho);
            espelho.setVisible(true);
            espelho.setPositionCenter();
            espelho.show();

        } else {

            espelho.setVisible(true);
            espelho.moveToFront();
            espelho.requestFocus();

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


}//GEN-LAST:event_botaoImprimirEspelhoActionPerformed

private void jTNotaFiscalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTNotaFiscalFocusLost

    if (!jTNotaFiscal.getText().isEmpty()) {
        if (Controller.verificaParametroAtivo(Parametro.CCADP005.getCodigo())) { //tava 003
            exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCADP005.getCodigo()), null);
            jTNotaFiscal.setText("");
            return;
        }
    }

}//GEN-LAST:event_jTNotaFiscalFocusLost

private void btnIncluirHistoricoRateioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirHistoricoRateioActionPerformed

    if (Controller.verificaParametroAtivo(Parametro.CCADP004.getCodigo())) {
        exibeMensagemAviso(Controller.sendMessageParametro(Parametro.CCADP004.getCodigo()), null);
        return;
    }

    if (validaCampos()) {

        if (this.validaHistoricos()) {

            if ((calculaValorHistoricosRateio() + jFTValorHistoricosRateio.getValue()) > jFTValorPagar.getValue()) {
                exibeMensagemAviso("O valor total do rateio não pode ser maior que o valor do título a pagar.", null);
                return;
            }

            HistoricoModel historicoModel = ((HistoricoModel) comboHistoricosRateio.getSelectedItem());

            TituloPagarRateioHistoricoModel rateio = new TituloPagarRateioHistoricoModel();

            rateio.setPk(new PKModel());

            rateio.getPk().setId(PempecKeyGenerator.generateKey());

            rateio.getPk().setOrganizacao(organizacaoModel);

            rateio.setHistoricoModel(historicoModel);

            rateio.setValor(jFTValorHistoricosRateio.getValue());

            auxHistoricosRateio.add(rateio);

            this.repopularComboHistoricosRateio(historicoModel, "e");

            this.preencheTabelaHistoricos();

            this.limparCamposHistoricos();

        } else {

            exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

        }

    } else {

        exibeMensagemAviso("Dados básicos do título não foram preenchidos.", null);

    }

}//GEN-LAST:event_btnIncluirHistoricoRateioActionPerformed

private void btnRemoverHistoricoRateioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverHistoricoRateioActionPerformed

    int cont = 0;

    for (int i = 0; i < tableHistoricos.getRowCount(); i++) {

        if (tableHistoricos.getValueAt(i, 0).toString().equals("true")) {

            HistoricoModel historicoTitulo = (HistoricoModel) comboHistorico.getSelectedItem();

            if (historicoTitulo.getPk().getId().equals(tableHistoricos.getValueAt(i, 4).toString()) && historicoTitulo.getPk().getOrganizacao().getId().equals(organizacaoModel.getId())) {
                exibeMensagemAviso("Não é possível remover esse histórico. Está definido como histórico padrão no título.", null);
                return;
            }

            cont++;

        }

    }

    if (cont == tableHistoricos.getRowCount()) {

        auxHistoricosRateio.removeAll(auxHistoricosRateio);

        try {
            Collection<HistoricoModel> collection = new ArrayList<HistoricoModel>();

            HistoricoModel historicoModel = new HistoricoModel();

            historicoModel.setDescricao(" -> Selecione <- ");

            collection.add(historicoModel);

            collection.addAll(historicoBO.obterTodos(organizacaoModel));

            comboHistoricosRateio.setModel(new javax.swing.DefaultComboBoxModel(collection.toArray()));

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

        this.preencheTabelaHistoricos();

    } else {

        for (int i = 1; i < tableHistoricos.getRowCount(); i++) {

            if (tableHistoricos.getValueAt(i, 0).toString().equals("true")) {

                HistoricoModel model = new HistoricoModel();
                model.setPk(new PKModel());
                model.getPk().setOrganizacao(organizacaoModel);
                model.getPk().setId(tableHistoricos.getValueAt(i, 4).toString());
                model.setCodigo(PempecParse.stringToInteger(tableHistoricos.getValueAt(i, 1).toString()));
                model.setDescricao(tableHistoricos.getValueAt(i, 2).toString());

                comboHistoricosRateio.addItem(model);

                ((TableModelCadastroTituloPagarRateioHistorico) tableHistoricos.getModel()).removeByHistorico(model);

            }

        }

        this.preencheTabelaHistoricos();

    }

    this.limparCamposHistoricos();

}//GEN-LAST:event_btnRemoverHistoricoRateioActionPerformed

private void tableHistoricosPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tableHistoricosPropertyChange
    Double total = this.calculaValorHistoricosRateio();
    jFTotalHistoricosRateio.setText(PempecParse.doubleToZero(total));

}//GEN-LAST:event_tableHistoricosPropertyChange

private void tableCentroCustoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tableCentroCustoPropertyChange
    Double total = this.calculaValorRateio();
    jFTotalRateio.setText(PempecParse.doubleToZero(total));
}//GEN-LAST:event_tableCentroCustoPropertyChange

private void comboCentroCustoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_comboCentroCustoFocusLost

    if (!labelSituacaoTitulo.getText().isEmpty() && !labelSituacaoTitulo.getText().trim().equals(Constantes.STATUS_TITULO_NOVO)) {
        return;
    }

    if (jFTValorPagar.getValue() == 0d) {
        exibeMensagemAviso("Favor preencher valor do Título", null);
        jFTValorPagar.requestFocus();
        comboCentroCusto.setSelectedIndex(0);
        this.contaContabilCredito = null;
        jTContaContabilCredito.setText("");
        return;
    }

    if (auxCentroCustosRateio.size() > 1) {
        if (!exibeMensagemConfirmacao("O Rateio Custos será removido! Deseja Continuar?", null)) {
            return;
        }
    }

    auxCentroCustosRateio = new ArrayList<TituloPagarRateioCCModel>();

    CentroCustoModel centroCusto = (CentroCustoModel) comboCentroCusto.getSelectedItem();

    TituloPagarRateioCCModel tituloPagarRateioCCModel = new TituloPagarRateioCCModel();

    tituloPagarRateioCCModel.setPk(new PKModel());

    tituloPagarRateioCCModel.getPk().setOrganizacao(organizacaoModel);

    tituloPagarRateioCCModel.getPk().setId(PempecKeyGenerator.generateKey());

    tituloPagarRateioCCModel.setCentroCustoModel(centroCusto);

    tituloPagarRateioCCModel.setValor(jFTValorPagar.getValue());

    auxCentroCustosRateio.add(tituloPagarRateioCCModel);

    this.repopularComboRateio(centroCusto, "e");

    this.preencheTabelaCentroCusto();

}//GEN-LAST:event_comboCentroCustoFocusLost

private void comboHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboHistoricoActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboHistorico.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        if (!labelSituacaoTitulo.getText().isEmpty() && !labelSituacaoTitulo.getText().trim().equals(Constantes.STATUS_TITULO_NOVO)) {
            return;
        }

        if (jFTValorPagar.getValue() == 0d) {
            exibeMensagemAviso("Favor preencher valor do Título", null);
            jFTValorPagar.requestFocus();
            comboHistorico.setSelectedItem(null);
            this.contaContabilDebito = null;
            jTContaContabilDebito.setText("");
            return;
        }

        if (auxHistoricosRateio.size() > 1) {
            if (!exibeMensagemConfirmacao("O Rateio Contábil será removido! Deseja Continuar?", null)) {
                return;
            }
        }

        auxHistoricosRateio = new ArrayList<TituloPagarRateioHistoricoModel>();

        HistoricoModel historico = (HistoricoModel) comboHistorico.getSelectedItem();

        TituloPagarRateioHistoricoModel tituloPagarRateioHistoricoModel = new TituloPagarRateioHistoricoModel();

        tituloPagarRateioHistoricoModel.setPk(new PKModel());

        tituloPagarRateioHistoricoModel.getPk().setOrganizacao(organizacaoModel);

        tituloPagarRateioHistoricoModel.getPk().setId(PempecKeyGenerator.generateKey());

        tituloPagarRateioHistoricoModel.setHistoricoModel(historico);

        tituloPagarRateioHistoricoModel.setValor(jFTValorPagar.getValue());

        auxHistoricosRateio.add(tituloPagarRateioHistoricoModel);

        this.repopularComboHistoricosRateio(historico, "e");

        this.preencheTabelaHistoricos();

    }

}//GEN-LAST:event_comboHistoricoActionPerformed

private void jChProvisaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChProvisaoActionPerformed

    if (jChProvisao.isSelected()) {
        jTContaContabilCredito.setVisible(true);
        lupaPesquisaContaContabilCredito.setVisible(true);
        jTContaContabilCredito.setText("");

        if (labelDataPagamento.getText().isEmpty() && campoCodigo.getText().isEmpty()) {
            botaoIncluir.setEnabled(true);
        }

        botaoPagar.setEnabled(false);
        labelDataVencimento.setText("Data Vencimento");
        labelContaContabilCredito.setText("Conta Contabil Crédito");
    } else {
        jTContaContabilCredito.setVisible(false);
        lupaPesquisaContaContabilCredito.setVisible(false);
        botaoIncluir.setEnabled(false);
        botaoPagar.setEnabled(true);
        labelDataVencimento.setText("Data Pagamento");
        labelContaContabilCredito.setText("");
    }

    comboCedenteItemStateChanged(null);


}//GEN-LAST:event_jChProvisaoActionPerformed

private void lupaPesquisaContaContabilDebitoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lupaPesquisaContaContabilDebitoActionPerformed

    try {

        if (pesquisaContaContabilDebito == null || pesquisaContaContabilDebito.isClosed()) {
            pesquisaContaContabilDebito = new PesquisaContaContabil(this, "D", this.getLocation());
            TelaPrincipal.desktopPane.add(pesquisaContaContabilDebito);
            pesquisaContaContabilDebito.show();
        } else {
            pesquisaContaContabilDebito.setVisible(true);
            pesquisaContaContabilDebito.moveToFront();
            pesquisaContaContabilDebito.requestFocus();
        }

    } catch (final SystemException ex) {

        final File file = PrintScreen.capture();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                tratamentoExcecao(ex, file);

            }
        });

    }

}//GEN-LAST:event_lupaPesquisaContaContabilDebitoActionPerformed

private void lupaPesquisaContaContabilCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lupaPesquisaContaContabilCreditoActionPerformed

    try {

        if (pesquisaContaContabilCredito == null || pesquisaContaContabilCredito.isClosed()) {
            pesquisaContaContabilCredito = new PesquisaContaContabil(this, "C", this.getLocation());
            TelaPrincipal.desktopPane.add(pesquisaContaContabilCredito);
            pesquisaContaContabilCredito.show();
        } else {
            pesquisaContaContabilCredito.setVisible(true);
            pesquisaContaContabilCredito.moveToFront();
            pesquisaContaContabilCredito.requestFocus();
        }

    } catch (final SystemException ex) {

        final File file = PrintScreen.capture();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                tratamentoExcecao(ex, file);

            }
        });

    }


}//GEN-LAST:event_lupaPesquisaContaContabilCreditoActionPerformed

    private void repopularComboRateio(CentroCustoModel model, String acao) {

        if (acao.equals("e")) {

            for (int i = 1; i < comboCentroCustoRateio.getItemCount(); i++) {

                CentroCustoModel centroCusto = (CentroCustoModel) comboCentroCustoRateio.getItemAt(i);

                if (model != null && model.getPk() != null && model.getPk().getId() != null && centroCusto.getPk() != null && centroCusto.getPk().getId() != null && !centroCusto.getPk().getId().isEmpty() && centroCusto.getPk().getId().equals(model.getPk().getId())) {
                    comboCentroCustoRateio.removeItemAt(i);
                    break;
                }
            }

        } else {

            comboCentroCustoRateio.addItem(model);
        }

    }

    private void repopularComboHistoricosRateio(HistoricoModel model, String acao) {

        if (acao.equals("e")) {

            for (int i = 1; i < comboHistoricosRateio.getItemCount(); i++) {
                HistoricoModel historico = (HistoricoModel) comboHistoricosRateio.getItemAt(i);
                if (model != null && model.getPk() != null && model.getPk().getId() != null && historico.getPk() != null && historico.getPk().getId() != null && !historico.getPk().getId().isEmpty() && historico.getPk().getId().equals(model.getPk().getId())) {
                    comboHistoricosRateio.removeItemAt(i);
                    break;
                }
            }

        } else {

            comboHistoricosRateio.addItem(model);
        }

    }

    private Boolean validaRateio(Double valorTitulo, Double valorRateio) {

        Boolean aux = false;

        if (PempecParse.doubleToZero(valorTitulo).toString().equals(PempecParse.doubleToZero(valorRateio).toString())) {
            aux = true;
        }

        return aux;
    }

    private void limparCamposCentroCusto() {
        comboCentroCustoRateio.setSelectedIndex(0);
        jFTValorRateio.setText("0,00");
    }

    private void limparCamposHistoricos() {
        comboHistoricosRateio.setSelectedIndex(0);
        jFTValorHistoricosRateio.setText("0,00");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaBasica;
    private javax.swing.JPanel abaComplementar;
    private javax.swing.JPanel abaHistoricos;
    private javax.swing.JPanel abaNf;
    private javax.swing.JPanel abaRateio;
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JTextField botaoGerar;
    private javax.swing.JButton botaoGerarNumeroDocumento;
    private javax.swing.JButton botaoImprimirEspelho;
    private javax.swing.JButton botaoIncluir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoPagar;
    private javax.swing.JButton btnIncluirCCRateio;
    private javax.swing.JButton btnIncluirHistoricoRateio;
    private javax.swing.JButton btnRemoverCCRateio;
    private javax.swing.JButton btnRemoverHistoricoRateio;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoIdNF;
    private javax.swing.JComboBox comboCedente;
    private javax.swing.JComboBox comboCentroCusto;
    private javax.swing.JComboBox comboCentroCustoRateio;
    private javax.swing.JComboBox comboFuncionario;
    private javax.swing.JComboBox comboHistorico;
    private javax.swing.JComboBox comboHistoricosRateio;
    private javax.swing.JComboBox comboIss;
    private javax.swing.JComboBox comboLocalPagamento;
    private javax.swing.JComboBox comboMoeda;
    private javax.swing.JComboBox comboTipoCobranca;
    private javax.swing.JComboBox comboTipoNotaFiscal;
    private javax.swing.JComboBox comboTitulo;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jChProvisao;
    private javax.swing.JFormattedTextField jFTCodigoBarras;
    private org.jdesktop.swingx.JXDatePicker jFTDataEmissao;
    private org.jdesktop.swingx.JXDatePicker jFTDataEmissaoNF;
    private org.jdesktop.swingx.JXDatePicker jFTDataProtocolo;
    private org.jdesktop.swingx.JXDatePicker jFTDataRetencaoISS;
    private org.jdesktop.swingx.JXDatePicker jFTDataVencimento;
    private org.jdesktop.swingx.JXDatePicker jFTPrevisaoCartorio;
    private br.com.pempec.componentes.JDoubleField jFTValorAdiantamento;
    private br.com.pempec.componentes.JDoubleField jFTValorHistoricosRateio;
    private br.com.pempec.componentes.JDoubleField jFTValorISSRetido;
    private br.com.pempec.componentes.JDoubleField jFTValorNF;
    private br.com.pempec.componentes.JDoubleField jFTValorPagar;
    private br.com.pempec.componentes.JDoubleField jFTValorRateio;
    private br.com.pempec.componentes.JDoubleField jFTotalHistoricosRateio;
    private br.com.pempec.componentes.JDoubleField jFTotalRateio;
    private br.com.pempec.componentes.JDoubleField jFtAliquota;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField jTCarteira;
    private javax.swing.JTextField jTContaContabilCredito;
    private javax.swing.JTextField jTContaContabilDebito;
    private javax.swing.JTextField jTContrato;
    private javax.swing.JTextField jTDescricao;
    private javax.swing.JTextField jTIdStatus;
    private javax.swing.JTextField jTNotaFiscal;
    private org.jdesktop.swingx.JXEditorPane jTObservacao;
    private org.jdesktop.swingx.JXEditorPane jTObservacaoNF;
    private javax.swing.JTextField jTParcela;
    private javax.swing.JTextField jTRegistroProvisao;
    private javax.swing.JTextField jTSerieNF;
    private javax.swing.JTextField jTSubSerieNF;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelAtualizacao;
    private javax.swing.JLabel labelCarteira;
    private javax.swing.JLabel labelCedente;
    private javax.swing.JLabel labelCentroCusto;
    private javax.swing.JLabel labelCodigoBarras;
    private javax.swing.JLabel labelContaContabilCredito;
    private javax.swing.JLabel labelContrato;
    private javax.swing.JLabel labelDataEmissao;
    private javax.swing.JLabel labelDataPagamento;
    private javax.swing.JLabel labelDataProtocolo;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDataVencimento1;
    private javax.swing.JLabel labelDataVencimento2;
    private javax.swing.JLabel labelDataVencimento3;
    private javax.swing.JLabel labelDataVencimento4;
    private javax.swing.JLabel labelDescricao;
    private javax.swing.JLabel labelDescricao2;
    private javax.swing.JLabel labelExport;
    private javax.swing.JLabel labelHistorico;
    private javax.swing.JLabel labelLocalPagamento;
    private javax.swing.JLabel labelMoeda;
    private javax.swing.JLabel labelNF1;
    private javax.swing.JLabel labelNF2;
    private javax.swing.JLabel labelNF3;
    private javax.swing.JLabel labelNumeroDocumento;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelParcela1;
    private javax.swing.JLabel labelParcela2;
    private javax.swing.JLabel labelParcela3;
    private javax.swing.JLabel labelPrevisaoCartorio;
    private javax.swing.JLabel labelPrevisaoCartorio1;
    private javax.swing.JLabel labelResponsavel;
    private javax.swing.JLabel labelSituacaoTitulo;
    private javax.swing.JLabel labelSituacaoVencido;
    private javax.swing.JLabel labelTipoCobranca;
    private javax.swing.JLabel labelValorPagar;
    private javax.swing.JLabel labelValorPagar2;
    private javax.swing.JLabel labelValorPagar3;
    private javax.swing.JLabel labelValorPagar4;
    private javax.swing.JLabel labelValorPagar5;
    private javax.swing.JLabel labelValorPagar7;
    private javax.swing.JLabel labelVlrAdiantado;
    private javax.swing.JButton lupaPesquisaContaContabilCredito;
    private javax.swing.JButton lupaPesquisaContaContabilDebito;
    private javax.swing.JTable tableCentroCusto;
    private javax.swing.JTable tableHistoricos;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private AutoCompleteSupport supportCedente;
    private AutoCompleteSupport supportHistorico;
    private ArrayList<TituloPagarRateioCCModel> auxCentroCustosRateio;
    private ArrayList<TituloPagarRateioHistoricoModel> auxHistoricosRateio;
    private CadastroTituloPagarBaixa cadastroTituloPagarBaixa;
    private CadastroTituloPagarParcelas cadastroTituloPagarParcelas;
    private TituloPagarEspelho espelho;
    private PesquisaContaContabil pesquisaContaContabilDebito;
    private ContaContabilModel contaContabilDebito;
    private PesquisaContaContabil pesquisaContaContabilCredito;
    private ContaContabilModel contaContabilCredito;

    private Double calculaValorHistoricosRateio() {

        Double retorno = 0d;

        for (TituloPagarRateioHistoricoModel bean : auxHistoricosRateio) {
            retorno += bean.getValor();
        }

        return retorno;
    }

    private Double calculaValorRateio() {

        Double retorno = 0d;

        for (TituloPagarRateioCCModel bean : auxCentroCustosRateio) {
            retorno += bean.getValor();
        }

        return retorno;
    }

    private Boolean validaCentroCusto() {

        //Levantar as validações
        if (comboCentroCustoRateio.getSelectedIndex() == 0) {
            comboCentroCustoRateio.requestFocus();
            return false;
        }

        if (jFTValorRateio.isVisible() && jFTValorRateio.getText().equals("0,00")) {
            jFTValorRateio.requestFocus();
            return false;
        }

        return true;
    }

    private Boolean validaPreenchimentoTabelaHistoricos() {

        for (int i = 0; i < tableHistoricos.getRowCount(); i++) {

            if (!tableHistoricos.getValueAt(i, 3).toString().equals("")) {

                //Flag de teste de validação de caracteres
                boolean flagValida = false;

                //Verifica se o valor digitado é um número válido
                String aux = tableHistoricos.getValueAt(i, 3).toString();
                for (int j = 0; j < aux.length(); j++) {

                    char teste = aux.charAt(j);

                    switch (teste) {
                        case '0':
                            break;
                        case '1':
                            break;
                        case '2':
                            break;
                        case '3':
                            break;
                        case '4':
                            break;
                        case '5':
                            break;
                        case '6':
                            break;
                        case '7':
                            break;
                        case '8':
                            break;
                        case '9':
                            break;
                        case '.':
                            break;
                        case ',':
                            break;
                        default:
                            j = aux.length();
                            flagValida = true;
                            break;
                    }

                }

                if (flagValida) {
                    exibeMensagemAviso("Valor inválido!", null);
                    tableHistoricos.editCellAt(i, 3);
                    return false;

                } else {

                    //Pega o valor digitado para comissão
                    Double valorFornecido = PempecParse.stringToDouble(tableHistoricos.getValueAt(i, 3).toString());

                    if (valorFornecido <= 0.0) {
                        exibeMensagemAviso("Valor Inválido! Valor tem que ser superior a ZERO.", null);
                        tableHistoricos.editCellAt(i, 3);
                        return false;
                    }

                }

            }

        }

        return true;

    }

    private Boolean validaPreenchimentoTabelaCentroCusto() {

        for (int i = 0; i < tableCentroCusto.getRowCount(); i++) {

            if (!tableCentroCusto.getValueAt(i, 3).toString().equals("")) {

                //Flag de teste de validação de caracteres
                boolean flagValida = false;

                //Verifica se o valor digitado é um número válido
                String aux = tableCentroCusto.getValueAt(i, 3).toString();
                for (int j = 0; j < aux.length(); j++) {

                    char teste = aux.charAt(j);

                    switch (teste) {
                        case '0':
                            break;
                        case '1':
                            break;
                        case '2':
                            break;
                        case '3':
                            break;
                        case '4':
                            break;
                        case '5':
                            break;
                        case '6':
                            break;
                        case '7':
                            break;
                        case '8':
                            break;
                        case '9':
                            break;
                        case '.':
                            break;
                        case ',':
                            break;
                        default:
                            j = aux.length();
                            flagValida = true;
                            break;
                    }

                }

                if (flagValida) {

                    exibeMensagemAviso("Valor inválido!", null);

                    tableCentroCusto.editCellAt(i, 3);

                    return false;

                } else {

                    //Pega o valor digitado para comissão
                    Double valorFornecido = PempecParse.stringToDouble(tableCentroCusto.getValueAt(i, 3).toString());

                    if (valorFornecido <= 0.0) {

                        exibeMensagemAviso("Valor Inválido! Valor tem que ser superior a ZERO.", null);

                        tableCentroCusto.editCellAt(i, 3);

                        return false;

                    }

                }

            }

        }

        return true;

    }

    private Boolean validaHistoricos() {

        //Levantar as validações
        if (comboHistoricosRateio.getSelectedIndex() == 0) {
            comboHistoricosRateio.requestFocus();
            return false;
        }

        if (jFTValorHistoricosRateio.isVisible() && jFTValorHistoricosRateio.getText().equals("0,00")) {
            jFTValorHistoricosRateio.requestFocus();
            return false;
        }

        return true;
    }

    private void preencheTabelaCentroCusto() {

        tableCentroCusto.setAutoCreateColumnsFromModel(false);
        tableCentroCusto.setModel(new TableModelCadastroTituloPagarRateioCC(auxCentroCustosRateio));
        FontMetrics fm = tableCentroCusto.getFontMetrics(tableCentroCusto.getFont());
        tableCentroCusto.setColumnModel(new ColumnModelCadastroTituloPagarRateioCC(fm));
        tableCentroCusto.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableCentroCusto.getTableHeader().setReorderingAllowed(false);
        jFTotalRateio.setText(PempecParse.doubleToZero(this.calculaValorRateio()));

    }

    private void preencheTabelaHistoricos() {

        tableHistoricos.setAutoCreateColumnsFromModel(false);
        tableHistoricos.setModel(new TableModelCadastroTituloPagarRateioHistorico(auxHistoricosRateio));
        FontMetrics fm = tableHistoricos.getFontMetrics(tableHistoricos.getFont());
        tableHistoricos.setColumnModel(new ColumnModelCadastroTituloPagarRateioHistorico(fm));
        tableHistoricos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHistoricos.getTableHeader().setReorderingAllowed(false);

        jFTotalHistoricosRateio.setText(PempecParse.doubleToZero(this.calculaValorHistoricosRateio()));

    }

    public void setPesquisaContaContabil(ContaContabilModel contaContabil, String tipo) {

        if (tipo.equals("C")) {
            this.contaContabilCredito = contaContabil;
            jTContaContabilCredito.setText(contaContabil.getConta());
        } else {
            if (tipo.equals("D")) {
                this.contaContabilDebito = contaContabil;
                jTContaContabilDebito.setText(contaContabil.getConta());
            }
        }

    }
}
