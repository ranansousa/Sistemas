package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TipoNotaFiscalBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.TituloPagarRateioHistoricoModel;
import br.com.pempec.finance.models.reports.EspelhoTituloPagar;
import br.com.pempec.finance.models.reports.EspelhoTituloPagarCrossTab;
import br.com.pempec.finance.models.reports.EspelhoTituloPagarSubForma;
import br.com.pempec.finance.models.reports.EspelhoTituloPagarSubHistoricoRateio;
import br.com.pempec.finance.models.reports.EspelhoTituloPagarSubRateio;
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
import java.beans.PropertyVetoException;
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
public class TituloPagarEspelho extends FinanceInternalFrame implements IRepopulador {

    private long tela = Tela.TELA_CONTAS_A_PAGAR_CADASTRO.getTela();
    private TituloPagarModel tituloPagarModel;
    private TituloPagarBaixaBO tituloPagarBaixaBO = new TituloPagarBaixaBO();
    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private TipoNotaFiscalBO tipoNotaFiscalBO = new TipoNotaFiscalBO();

    public TituloPagarEspelho(TituloPagarModel tituloPagar) throws SystemException {

        initComponents();
        

        this.tituloPagarModel = tituloPagar;

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
        setTitle("PEMPEC ENTERPRISE - Finance - Espelho Titulo Pagar");

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
                        .addGap(48, 48, 48)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return "Espelho de TituloPagar";
    }

    public void repopularCombos() {

        comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));

    }

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
        try {
            setClosed(true);
        } catch (PropertyVetoException ex) {
        }

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

                if (tituloPagarModel != null) {

                    new MovimentoDiarioBO().inserir(this.registroMovimento("Imprimir Espelho", tituloPagarModel.getNumeroDocumento(), Controller.getUsuarioLogado().getNome() + " solicitou impressao do espelho do titulo PG " + tituloPagarModel.getNumeroDocumento(), tituloPagarModel.getValorNominal(), "Impresso"));

                    Map<String, Object> parametros = new HashMap<String, Object>();

                    Collection<EspelhoTituloPagar> coll = new ArrayList<EspelhoTituloPagar>();

                    EspelhoTituloPagar espelho = new EspelhoTituloPagar();

                    espelho.setNomeUsuario(Controller.getUsuarioLogado().getName());
                    espelho.setDataHoraImpresso(PempecParse.dateHourSecondToString(new Date()));

                    espelho.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    espelho.setEndereco(organizacaoModel.getEndereco());
                    espelho.setCnpj(organizacaoModel.getCnpj());

                    espelho.setNumeroDocumento(tituloPagarModel.getNumeroDocumento());

                    CedenteModel cedente = new CedenteModel();
                    String auxCdBancario = "NC";

                    if (tituloPagarModel.getCedente() != null && tituloPagarModel.getCedente().getPk() != null && tituloPagarModel.getCedente().getPk().getId() != null
                            && (!tituloPagarModel.getCedente().getPk().getId().isEmpty())) {

                        cedente = new CedenteBO().consultarPorPk(tituloPagarModel.getCedente());

                        if (cedente != null && cedente.getPk() != null) {

                            espelho.setCedente(cedente.getNome());
                            espelho.setCpfCnpjCedente(cedente.getCpfCnpj());
                            espelho.setCdBanco(cedente.getBanco().getCodigoBanco());
                            espelho.setCdAgencia(cedente.getAgencia());
                            espelho.setCdConta(cedente.getConta());

                        }

                    } else {

                        espelho.setCdBanco(auxCdBancario);
                        espelho.setCdAgencia(auxCdBancario);
                        espelho.setCdConta(auxCdBancario);

                    }

                    espelho.setDataEmissao(tituloPagarModel.getDataEmissao());

                    espelho.setDescricao(tituloPagarModel.getDescricao());

                    espelho.setParcela(tituloPagarModel.getParcela());

                    espelho.setCodigoHistorico(tituloPagarModel.getHistorico().getCodigo().toString());
                    espelho.setHistorico(tituloPagarModel.getHistorico().getDescricao());

                    espelho.setValor(tituloPagarModel.getValorNominal());

                    espelho.setLocalPagamento(tituloPagarModel.getLocalPagamento().getDescricao());

                    espelho.setTipoCobranca(tituloPagarModel.getTipoCobranca().getDescricao());

                    espelho.setResponsavel(tituloPagarModel.getResponsavel().getNome());

                    espelho.setCodigoCentroCusto(tituloPagarModel.getCentroCusto().getCodigo().toString());
                    espelho.setCentroCusto(tituloPagarModel.getCentroCusto().getDescricao());

                    espelho.setSituacao(tituloPagarModel.getStatus().getDescricao());

                    if (tituloPagarModel.getContaContabilCredito() != null) {
                        espelho.setContaContabilCredito(tituloPagarModel.getContaContabilCredito().getConta() + "-" + tituloPagarModel.getContaContabilCredito().getDigitoConta());
                    }

                    if (tituloPagarModel.getContaContabilDebito() != null) {
                        espelho.setContaContabilDebito(tituloPagarModel.getContaContabilDebito().getConta() + "-" + tituloPagarModel.getContaContabilDebito().getDigitoConta());
                    }

                    if (tituloPagarModel.getLoteContabil() != null) {
                        espelho.setExportacao("Título exportado no LOTE nº" + tituloPagarModel.getLoteContabil().getLote());
                    }

                    if (tituloPagarModel.getPrevisaoCartorio() != null
                            || tituloPagarModel.getMoeda() != null
                            || tituloPagarModel.getContrato() != null
                            || tituloPagarModel.getCodigoBarras() != null
                            || tituloPagarModel.getCarteira() != null
                            || tituloPagarModel.getObservacao() != null) {

                        espelho.setPrevisaoCartorio(tituloPagarModel.getPrevisaoCartorio());
                        espelho.setMoeda(tituloPagarModel.getMoeda());
                        espelho.setContrato(tituloPagarModel.getContrato());
                        espelho.setCodigoBarras(tituloPagarModel.getCodigoBarras());
                        espelho.setCarteira(tituloPagarModel.getCarteira());
                        espelho.setObservacaoComplementar(tituloPagarModel.getObservacao());

                        parametros.put("EXISTECOMP", true);

                    }

                    if (tituloPagarModel.getNotaFiscal() != null
                            && tituloPagarModel.getNotaFiscal().getNumero() != null) {

                        espelho.setNumeroNF(tituloPagarModel.getNotaFiscal().getNumero());
                        espelho.setSerie(tituloPagarModel.getNotaFiscal().getSerie());
                        espelho.setSubSerie(tituloPagarModel.getNotaFiscal().getSubSerie());
                        espelho.setValorNF(tituloPagarModel.getNotaFiscal().getValor());
                        espelho.setTipoDocumento(tipoNotaFiscalBO.consultarPorPk(tituloPagarModel.getNotaFiscal().getTipoDocumento()).getDescricao());
                        espelho.setDataEmissaoNF(tituloPagarModel.getNotaFiscal().getDataEmissao());

                        if (tituloPagarModel.getNotaFiscal().getValorIss() != null && tituloPagarModel.getNotaFiscal().getValor() > 0) {
                            espelho.setIssRetido("SIM");
                        } else {
                            espelho.setIssRetido("NÃO");
                        }

                        espelho.setValorISS(tituloPagarModel.getNotaFiscal().getValorIss());
                        espelho.setAliquota(tituloPagarModel.getNotaFiscal().getAliquota());
                        espelho.setDataISS(tituloPagarModel.getNotaFiscal().getDataRetencao());
                        espelho.setObservacaoNF(tituloPagarModel.getNotaFiscal().getObservacao());

                        parametros.put("EXISTENF", true);

                    }

                    List<TituloPagarModel> collTitulos = tituloPagarBO.obterTitulosFilhos(tituloPagarModel);

                    Collection<EspelhoTituloPagarSubRateio> collRateio = new ArrayList<EspelhoTituloPagarSubRateio>();

                    Collection<EspelhoTituloPagarSubHistoricoRateio> collHistoricoRateio = new ArrayList<EspelhoTituloPagarSubHistoricoRateio>();

                    Collection<EspelhoTituloPagarCrossTab> collCrossTab = new ArrayList<EspelhoTituloPagarCrossTab>();

                    Collection<EspelhoTituloPagarSubForma> collFormas = new ArrayList<EspelhoTituloPagarSubForma>();

                    collTitulos.remove(tituloPagarModel);

                    Collection<DynaBean> collection = new ArrayList<DynaBean>();

                    TituloPagarBaixaModel baixaModel = tituloPagarBaixaBO.consultarPorTitulo(tituloPagarModel);

                    if (tituloPagarModel.getCollHistoricosRateio() != null && !tituloPagarModel.getCollHistoricosRateio().isEmpty()) {

                        parametros.put("EXISTERATHIST", true);

                        for (TituloPagarRateioHistoricoModel rateio : tituloPagarModel.getCollHistoricosRateio()) {

                            EspelhoTituloPagarSubHistoricoRateio bean = new EspelhoTituloPagarSubHistoricoRateio();

                            bean.setHistorico(rateio.getHistoricoModel().getDescricao());

                            bean.setCodigoHistorico(rateio.getHistoricoModel().getCodigo().toString());

                            bean.setValor(rateio.getValor());

                            collHistoricoRateio.add(bean);

                        }

                    }

                    if (tituloPagarModel.getCollCentroCustosRateio() != null && !tituloPagarModel.getCollCentroCustosRateio().isEmpty()) {

                        parametros.put("EXISTERAT", true);

                        for (TituloPagarRateioCCModel rateio : tituloPagarModel.getCollCentroCustosRateio()) {

                            EspelhoTituloPagarSubRateio bean = new EspelhoTituloPagarSubRateio();

                            bean.setCentroCusto(rateio.getCentroCustoModel().getDescricao());

                            bean.setCodigoCentroCusto(rateio.getCentroCustoModel().getCodigo().toString());

                            bean.setValor(rateio.getValor());

                            collRateio.add(bean);

                        }

                    }

                    Double valorPago = 0d;

                    if (baixaModel != null) {

                        espelho.setTipoPagamento(baixaModel.getTipoBaixa());

                        parametros.put("EXISTEBAIXA", true);

                        String descricaoFormaInternet = Constantes.FORMA_PAGAMENTO_INTERNET;
                        String descricaoFormaCheque = Constantes.FORMA_PAGAMENTO_CHEQUE;

                        if (baixaModel.getFormasPagamento() != null && !baixaModel.getFormasPagamento().isEmpty()) {

                            for (TituloPagarBaixaFormaPagamentoModel forma : baixaModel.getFormasPagamento()) {

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

                            for (TituloPagarBaixaChequeModel cheque : baixaModel.getTituloPagarBaixaCheque()) {

                                EspelhoTituloPagarSubForma formaCheque = new EspelhoTituloPagarSubForma();

                                formaCheque.setValor(cheque.getValor());

                                formaCheque.setIdForma(Constantes.FORMA_PAGAMENTO_CHEQUE);

                                formaCheque.setForma(descricaoFormaCheque);

                                formaCheque.setNumeroCheque(cheque.getContaBancariaCheque().getNumeroCheque());

                                ContaBancariaModel contaBancaria = contaBancariaBO.consultarPorPk(cheque.getContaBancariaCheque().getContaBancaria());

                                formaCheque.setConta(contaBancaria.getConta());

                                collFormas.add(formaCheque);

                            }

                            for (TituloPagarBaixaInternetModel internet : baixaModel.getTituloPagarBaixaInternet()) {

                                EspelhoTituloPagarSubForma formaInternet = new EspelhoTituloPagarSubForma();

                                formaInternet.setIdForma(Constantes.FORMA_PAGAMENTO_INTERNET);

                                formaInternet.setValor(internet.getValor());

                                formaInternet.setForma(descricaoFormaInternet);

                                formaInternet.setCodigoBanco(internet.getBancoDestino().getCodigoBanco());

                                formaInternet.setNomeBanco(internet.getBancoDestino().getNomeBanco());

                                formaInternet.setAgenciaDestino(formaInternet.getAgenciaDestino());

                                formaInternet.setContaDestino(formaInternet.getContaDestino());

                                formaInternet.setDescricao(internet.getTipoOperacaoBancaria().getDescricao());

                                collFormas.add(formaInternet);

                            }

                        }

                        if (baixaModel.getAcrescimos() != null && !baixaModel.getAcrescimos().isEmpty()) {

                            for (TituloPagarBaixaAcrescimoModel acrescimo : baixaModel.getAcrescimos()) {

                                EspelhoTituloPagarCrossTab crossTab = new EspelhoTituloPagarCrossTab();

                                crossTab.setDescricao(acrescimo.getTipoAcrescimo().getDescricao());

                                crossTab.setTipo("Acréscimos");

                                crossTab.setValor(acrescimo.getValor());

                                collCrossTab.add(crossTab);

                            }

                        }

                        if (baixaModel.getDeducoes() != null && !baixaModel.getDeducoes().isEmpty()) {

                            for (TituloPagarBaixaDeducaoModel deducao : baixaModel.getDeducoes()) {

                                EspelhoTituloPagarCrossTab crossTab = new EspelhoTituloPagarCrossTab();

                                crossTab.setDescricao(deducao.getTipoDeducao().getDescricao());

                                crossTab.setTipo("Deduções");

                                crossTab.setValor(deducao.getValor());

                                collCrossTab.add(crossTab);

                            }

                        }

                    }

                    //  Double valorPagoFilhos = 0d;
                    for (TituloPagarModel titulo : collTitulos) {

                        baixaModel = tituloPagarBaixaBO.consultarPorTitulo(titulo);

                        Double totalAcrescimos = 0d;

                        Double totalDeducoes = 0d;

                        Double valorPagoFilhos = 0d;

                        if (baixaModel != null) {

                            if (baixaModel.getFormasPagamento() != null && !baixaModel.getFormasPagamento().isEmpty()) {

                                for (TituloPagarBaixaFormaPagamentoModel forma : baixaModel.getFormasPagamento()) {

                                    valorPagoFilhos += forma.getValor();

                                }

                            }

                            if (baixaModel.getAcrescimos() != null && !baixaModel.getAcrescimos().isEmpty()) {

                                for (TituloPagarBaixaAcrescimoModel acrescimo : baixaModel.getAcrescimos()) {

                                    totalAcrescimos += acrescimo.getValor();

                                }

                            }

                            if (baixaModel.getDeducoes() != null && !baixaModel.getDeducoes().isEmpty()) {

                                for (TituloPagarBaixaDeducaoModel deducao : baixaModel.getDeducoes()) {

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

                    espelho.setDataPagamento(tituloPagarModel.getDataPagamento());

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION, collection);

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_CROSSTAB, collCrossTab);

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_FORMAS, collFormas);

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_RATEIO, collRateio);

                    parametros.put(RelatorioConstantes.PARAMETRO_COLLECTION_HISTORICO_RATEIO, collHistoricoRateio);

                    coll.add(espelho);

                    switch (comboFormato.getSelectedIndex()) {

                        case 0:
                            new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_CONTAS_PAGAR_ESPELHO, parametros, coll);
                            break;
                        case 1:
                            new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_CONTAS_PAGAR_ESPELHO, parametros, coll);
                            break;
                        case 2:
                            new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_CONTAS_PAGAR_ESPELHO, parametros, coll);
                            break;
                        case 3:

                            //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                            File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_CONTAS_PAGAR_ESPELHO, parametros, coll);

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
