package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TipoNotaFiscalBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaChequeBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloReceberBaixaChequeModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloReceberBaixaInternetModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.models.TituloReceberRateioHistoricoModel;
import br.com.pempec.finance.models.reports.EspelhoTituloPagarSubForma;
import br.com.pempec.finance.models.reports.EspelhoTituloReceber;
import br.com.pempec.finance.models.reports.EspelhoTituloReceberCrossTab;
import br.com.pempec.finance.models.reports.EspelhoTituloReceberSubHistoricoRateio;
import br.com.pempec.finance.models.reports.EspelhoTitulorReceberSubRateio;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.LazyDynaMap;

/**
 *
 * @author PEMPEC
 */
public class TituloReceberEspelho extends FinanceInternalFrame implements IRepopulador {

    private long tela = Tela.TELA_CONTAS_A_RECEBER_CADASTRO.getTela();
    private TituloReceberModel tituloReceberModel;
    private TituloReceberBaixaBO tituloReceberBaixaBO = new TituloReceberBaixaBO();
    private TituloReceberBO tituloReceberBO = new TituloReceberBO();
    private TipoNotaFiscalBO tipoNotaFiscalBO = new TipoNotaFiscalBO();

    public TituloReceberEspelho(TituloReceberModel tituloReceber) throws SystemException {

        this.tituloReceberModel = tituloReceber;

        this.setLocation(250, 25);

        initComponents();

        this.repopularCombos();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Espelho Titulo Receber");
        setPreferredSize(new java.awt.Dimension(425, 247));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Formato"));

        comboFormato.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboFormatoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, 0, 240, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
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
                .addContainerGap()
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoGerar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return "Espelho de TituloReceber";
    }

    public void repopularCombos() {

        comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));

    }

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        comboFormato.setSelectedIndex(0);

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        return true;
    }

    private void botaoGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarActionPerformed

        if (validaCampos()) {

            try {

                long action = Action.IMPRESSAO.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }

                if (tituloReceberModel != null) {

                    new MovimentoDiarioBO().inserir(this.registroMovimento("Imprimir Espelho", tituloReceberModel.getNumeroDocumento(), Controller.getUsuarioLogado().getNome() + " solicitou impressao do espelho do titulo REC " + tituloReceberModel.getNumeroDocumento(), tituloReceberModel.getValorNominal(), "Impresso"));

                    Map<String, Object> parametros = new HashMap<String, Object>();

                    Collection<EspelhoTituloReceber> coll = new ArrayList<EspelhoTituloReceber>();

                    EspelhoTituloReceber espelho = new EspelhoTituloReceber();

                    espelho.setNomeUsuario(Controller.getUsuarioLogado().getName());
                    espelho.setDataHoraImpresso(PempecParse.dateHourSecondToString(new Date()));

                    espelho.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    espelho.setEndereco(organizacaoModel.getEndereco());
                    espelho.setCnpj(organizacaoModel.getCnpj());

                    espelho.setNumeroDocumento(tituloReceberModel.getNumeroDocumento());

                    espelho.setSacado(tituloReceberModel.getSacado().getNome());
                    espelho.setCpfCnpjSacado(tituloReceberModel.getSacado().getCpfCnpj());

                    espelho.setDataEmissao(tituloReceberModel.getDataEmissao());

                    espelho.setDescricao(tituloReceberModel.getDescricao());

                    espelho.setParcela(tituloReceberModel.getParcela());

                    espelho.setCodigoHistorico(tituloReceberModel.getHistorico().getCodigo().toString());
                    espelho.setHistorico(tituloReceberModel.getHistorico().getDescricao());

                    espelho.setValor(tituloReceberModel.getValorNominal());

                    espelho.setLocalPagamento(tituloReceberModel.getLocalPagamento().getDescricao());

                    espelho.setTipoCobranca(tituloReceberModel.getTipoCobranca().getDescricao());

                    espelho.setResponsavel(tituloReceberModel.getResponsavel().getNome());

                    espelho.setCodigoCentroCusto(tituloReceberModel.getCentroCusto().getCodigo().toString());
                    espelho.setCentroCusto(tituloReceberModel.getCentroCusto().getDescricao());

                    espelho.setSituacao(tituloReceberModel.getStatus().getDescricao());

                    if (tituloReceberModel.getContaContabilCredito() != null) {
                        espelho.setContaContabilCredito(tituloReceberModel.getContaContabilCredito().getConta() + "-" + tituloReceberModel.getContaContabilCredito().getDigitoConta());
                    }

                    if (tituloReceberModel.getContaContabilDebito() != null) {
                        espelho.setContaContabilDebito(tituloReceberModel.getContaContabilDebito().getConta() + "-" + tituloReceberModel.getContaContabilDebito().getDigitoConta());
                    }

                    if (tituloReceberModel.getLoteContabil() != null) {

                        espelho.setExportacao("Título exportado no LOTE nº" + tituloReceberModel.getLoteContabil().getLote());

                    }

                    if (tituloReceberModel.getPrevisaoCartorio() != null
                            || tituloReceberModel.getMoeda() != null
                            || tituloReceberModel.getContrato() != null
                            || tituloReceberModel.getCodigoBarras() != null
                            || tituloReceberModel.getCarteira() != null
                            || tituloReceberModel.getObservacao() != null) {

                        espelho.setPrevisaoCartorio(tituloReceberModel.getPrevisaoCartorio());
                        espelho.setMoeda(tituloReceberModel.getMoeda());
                        espelho.setContrato(tituloReceberModel.getContrato());
                        espelho.setCodigoBarras(tituloReceberModel.getCodigoBarras());
                        espelho.setCarteira(tituloReceberModel.getCarteira());
                        espelho.setObservacaoComplementar(tituloReceberModel.getObservacao());

                        parametros.put("EXISTECOMP", true);


                    }

                    if (tituloReceberModel.getNotaFiscal() != null
                            && tituloReceberModel.getNotaFiscal().getNumero() != null) {

                        espelho.setNumeroNF(tituloReceberModel.getNotaFiscal().getNumero());
                        espelho.setSerie(tituloReceberModel.getNotaFiscal().getSerie());
                        espelho.setSubSerie(tituloReceberModel.getNotaFiscal().getSubSerie());
                        espelho.setValorNF(tituloReceberModel.getNotaFiscal().getValor());
                        espelho.setTipoDocumento(tipoNotaFiscalBO.consultarPorPk(tituloReceberModel.getNotaFiscal().getTipoDocumento()).getDescricao());
                        espelho.setDataEmissaoNF(tituloReceberModel.getNotaFiscal().getDataEmissao());

                        if (tituloReceberModel.getNotaFiscal().getValorIss() != null && tituloReceberModel.getNotaFiscal().getValor() > 0) {
                            espelho.setIssRetido("SIM");
                        } else {
                            espelho.setIssRetido("NÃO");
                        }

                        espelho.setValorISS(tituloReceberModel.getNotaFiscal().getValorIss());
                        espelho.setAliquota(tituloReceberModel.getNotaFiscal().getAliquota());
                        espelho.setDataISS(tituloReceberModel.getNotaFiscal().getDataRetencao());
                        espelho.setObservacaoNF(tituloReceberModel.getNotaFiscal().getObservacao());

                        parametros.put("EXISTENF", true);

                    }

                    Collection<EspelhoTituloReceberSubHistoricoRateio> collHistoricoRateio = new ArrayList<EspelhoTituloReceberSubHistoricoRateio>();

                    if (tituloReceberModel.getCollHistoricosRateio() != null && !tituloReceberModel.getCollHistoricosRateio().isEmpty()) {

                        parametros.put("EXISTERATHIS", true);

                        for (TituloReceberRateioHistoricoModel rateio : tituloReceberModel.getCollHistoricosRateio()) {

                            EspelhoTituloReceberSubHistoricoRateio bean = new EspelhoTituloReceberSubHistoricoRateio();

                            bean.setHistorico(rateio.getHistoricoModel().getDescricao());

                            bean.setCodigoHistorico(rateio.getHistoricoModel().getCodigo().toString());

                            bean.setValor(rateio.getValor());

                            collHistoricoRateio.add(bean);

                        }

                    }

                    Collection<EspelhoTitulorReceberSubRateio> collRateio = new ArrayList<EspelhoTitulorReceberSubRateio>();

                    if (tituloReceberModel.getCollCentroCustosRateio() != null && !tituloReceberModel.getCollCentroCustosRateio().isEmpty()) {

                        parametros.put("EXISTERAT", true);

                        for (TituloReceberRateioCCModel rateio : tituloReceberModel.getCollCentroCustosRateio()) {

                            EspelhoTitulorReceberSubRateio bean = new EspelhoTitulorReceberSubRateio();

                            bean.setCentroCusto(rateio.getCentroCustoModel().getDescricao());

                            bean.setCodigoCentroCusto(rateio.getCentroCustoModel().getCodigo().toString());

                            bean.setValor(rateio.getValor());

                            collRateio.add(bean);

                        }



                    }

                    List<TituloReceberModel> collTitulos = tituloReceberBO.obterTitulosFilhos(tituloReceberModel);

                    Collection<EspelhoTituloReceberCrossTab> collCrossTab = new ArrayList<EspelhoTituloReceberCrossTab>();

                    Collection<EspelhoTituloPagarSubForma> collFormas = new ArrayList<EspelhoTituloPagarSubForma>();

                    collTitulos.remove(tituloReceberModel);

                    Collection<DynaBean> collection = new ArrayList<DynaBean>();

                    TituloReceberBaixaModel baixaModel = tituloReceberBaixaBO.consultarPorTitulo(tituloReceberModel);

                    Double valorPago = 0d;

                    if (baixaModel != null) {

                        espelho.setTipoPagamento(baixaModel.getTipoBaixa());

                        parametros.put("EXISTEBAIXA", true);

                        String descricaoFormaInternet = Constantes.FORMA_PAGAMENTO_INTERNET;
                        String descricaoFormaCheque = Constantes.FORMA_PAGAMENTO_CHEQUE;

                        if (baixaModel.getFormasPagamento() != null && !baixaModel.getFormasPagamento().isEmpty()) {

                            for (TituloReceberBaixaFormaPagamentoModel forma : baixaModel.getFormasPagamento()) {

                                if (forma.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {

                                    EspelhoTituloPagarSubForma formaEspecie = new EspelhoTituloPagarSubForma();

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

                            for (TituloReceberBaixaChequeModel cheque : baixaModel.getTituloReceberBaixaCheque()) {

                                cheque = new TituloReceberBaixaChequeBO().consultarPorPk(cheque);

                                EspelhoTituloPagarSubForma formaCheque = new EspelhoTituloPagarSubForma();

                                formaCheque.setValor(cheque.getValor());

                                formaCheque.setIdForma(Constantes.FORMA_PAGAMENTO_CHEQUE);

                                formaCheque.setForma(descricaoFormaCheque);

                                formaCheque.setNumeroCheque(cheque.getNumeroCheque());

                                formaCheque.setConta(cheque.getAgencia() + "/" + cheque.getConta());

                                collFormas.add(formaCheque);

                            }

                            for (TituloReceberBaixaInternetModel internet : baixaModel.getTituloReceberBaixaInternet()) {


                                EspelhoTituloPagarSubForma formaInternet = new EspelhoTituloPagarSubForma();

                                formaInternet.setValor(internet.getValor());

                                formaInternet.setIdForma(Constantes.FORMA_PAGAMENTO_INTERNET);

                                formaInternet.setForma(descricaoFormaInternet);

                                formaInternet.setContaDestino(internet.getContaBancaria().getConta());

                                formaInternet.setDescricao(internet.getTipoOperacaoBancaria().getDescricao());


                                collFormas.add(formaInternet);

                            }

                        }

                        if (baixaModel.getAcrescimos() != null && !baixaModel.getAcrescimos().isEmpty()) {

                            for (TituloReceberBaixaAcrescimoModel acrescimo : baixaModel.getAcrescimos()) {

                                EspelhoTituloReceberCrossTab crossTab = new EspelhoTituloReceberCrossTab();

                                crossTab.setDescricao(acrescimo.getTipoAcrescimo().getDescricao());

                                crossTab.setTipo("Acréscimos");

                                crossTab.setValor(acrescimo.getValor());

                                collCrossTab.add(crossTab);

                            }

                        }

                        if (baixaModel.getDeducoes() != null && !baixaModel.getDeducoes().isEmpty()) {

                            for (TituloReceberBaixaDeducaoModel deducao : baixaModel.getDeducoes()) {

                                EspelhoTituloReceberCrossTab crossTab = new EspelhoTituloReceberCrossTab();

                                crossTab.setDescricao(deducao.getTipoDeducao().getDescricao());

                                crossTab.setTipo("Deduções");

                                crossTab.setValor(deducao.getValor());

                                collCrossTab.add(crossTab);

                            }

                        }

                    }

                    Double valorPagoFilhos = 0d;

                    for (TituloReceberModel titulo : collTitulos) {

                        baixaModel = tituloReceberBaixaBO.consultarPorTitulo(titulo);

                        Double totalAcrescimos = 0d;

                        Double totalDeducoes = 0d;

                        if (baixaModel != null) {

                            if (baixaModel.getFormasPagamento() != null && !baixaModel.getFormasPagamento().isEmpty()) {

                                for (TituloReceberBaixaFormaPagamentoModel forma : baixaModel.getFormasPagamento()) {

                                    valorPagoFilhos += forma.getValor();

                                }

                            }

                            if (baixaModel.getAcrescimos() != null && !baixaModel.getAcrescimos().isEmpty()) {

                                for (TituloReceberBaixaAcrescimoModel acrescimo : baixaModel.getAcrescimos()) {

                                    totalAcrescimos += acrescimo.getValor();

                                }

                            }

                            if (baixaModel.getDeducoes() != null && !baixaModel.getDeducoes().isEmpty()) {

                                for (TituloReceberBaixaDeducaoModel deducao : baixaModel.getDeducoes()) {

                                    totalDeducoes += deducao.getValor();

                                }

                            }

                            DynaBean lazy = new LazyDynaMap();
                            lazy.set(RelatorioConstantes.PARAMETRO_NUMERO_DOCUMENTO, titulo.getNumeroDocumento());

                            lazy.set(RelatorioConstantes.PARAMETRO_VALOR, valorPagoFilhos);

                            lazy.set(RelatorioConstantes.PARAMETRO_DATA_PAGAMENTO, titulo.getDataPagamento());
                            lazy.set(RelatorioConstantes.PARAMETRO_TOTAL_ACRESCIMOS, totalAcrescimos);
                            lazy.set(RelatorioConstantes.PARAMETRO_TOTAL_DEDUCOES, totalDeducoes);

                            collection.add(lazy);

                        }

                    }

                    espelho.setValorPago(valorPago);

                    espelho.setDataPagamento(tituloReceberModel.getDataPagamento());

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION, collection);

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_CROSSTAB, collCrossTab);

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_FORMAS, collFormas);

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_RATEIO, collRateio);

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_HISTORICO_RATEIO, collHistoricoRateio);

                    coll.add(espelho);

                    switch (comboFormato.getSelectedIndex()) {

                        case 0:
                            new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_CONTAS_RECEBER_ESPELHO, parametros, coll);
                            break;
                        case 1:
                            new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_CONTAS_RECEBER_ESPELHO, parametros, coll);
                            break;
                        case 2:
                            new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_CONTAS_RECEBER_ESPELHO, parametros, coll);
                            break;
                        case 3:

                            //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                            File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_CONTAS_RECEBER_ESPELHO, parametros, coll);

                            try {

                                TelaEnvioEmail envioEmail = new TelaEnvioEmail(anexo);
                                TelaPrincipal.desktopPane.add(envioEmail);
                                envioEmail.show();

                            } catch (final SystemException ex) {

                                final File file = PrintScreen.capture();

                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {

                                        tratamentoExcecao(ex, file);

                                    }
                                });

                            }
                            break;

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

        } else {

            exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

        }




}//GEN-LAST:event_botaoGerarActionPerformed

    private void comboFormatoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboFormatoItemStateChanged
        if (comboFormato.getSelectedItem() != null && ((FormatosRelatoriosModel) comboFormato.getSelectedItem()).getDescricao().equalsIgnoreCase(Constantes.ENVIAR_POR_EMAIL)) {
            botaoGerar.setText("Enviar");
        } else {
            botaoGerar.setText("Gerar");
        }
    }//GEN-LAST:event_comboFormatoItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
