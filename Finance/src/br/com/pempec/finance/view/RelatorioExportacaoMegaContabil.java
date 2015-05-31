package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaCreditoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaTransferenciaBO;
import br.com.pempec.finance.businessObjects.FormaPagamentoBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TesourariaCreditoBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.ContaContabilModel;
import br.com.pempec.finance.models.FormaPagamentoModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloPagarBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloPagarBaixaInternetModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloPagarRateioCCModel;
import br.com.pempec.finance.models.TituloPagarRateioHistoricoModel;
import br.com.pempec.finance.models.TituloReceberBaixaAcrescimoModel;
import br.com.pempec.finance.models.TituloReceberBaixaDeducaoModel;
import br.com.pempec.finance.models.TituloReceberBaixaFormaPagamentoModel;
import br.com.pempec.finance.models.TituloReceberBaixaInternetModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.TituloReceberRateioCCModel;
import br.com.pempec.finance.models.TituloReceberRateioHistoricoModel;
import br.com.pempec.finance.models.reports.ReportExportacaoMegaContabil;
import br.com.pempec.finance.models.reports.ReportExportacaoMegaContabilSub;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.RelatorioConstantes;
import br.com.pempec.finance.utils.RelatorioUtil;
import br.com.pempec.finance.utils.Tela;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 *
 * @author Administrador
 */
public class RelatorioExportacaoMegaContabil extends FinanceInternalFrame implements IRepopulador {

    private long tela = Tela.TELA_RELATORIOS_EXPORTACAO_MEGACONTABIL.getTela();
    private Date dataInicial = Controller.getDataServidorBD();
    private Date dataFinal = Controller.getDataServidorBD();
    private List<ReportExportacaoMegaContabil> dadosExportados;
    private Collection<HistoricoModel> collHistorico;
    private Collection<ContaBancariaModel> collContaBancaria;
    private Collection<TituloPagarModel> collTitulosPagar;
    private Collection<TituloPagarBaixaModel> collBaixasTitulosPagar;
    private Collection<TituloReceberModel> collTitulosReceber;
    private Collection<TituloReceberBaixaModel> collBaixasTitulosReceber;
    private Collection<TesourariaDebitoModel> collTesourariaDebito;
    private Collection<TesourariaCreditoModel> collTesourariaCredito;
    private Collection<ContaBancariaDebitoModel> collContaBancariaDebito;
    private Collection<ContaBancariaCreditoModel> collContaBancariaCredito;
    private Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia;
    private HistoricoBO historicoBO;
    private TituloPagarBO tituloPagarBO;
    private TituloPagarBaixaBO tituloPagarBaixaBO;
    private TituloReceberBO tituloReceberBO;
    private TituloReceberBaixaBO tituloReceberBaixaBO;
    private TesourariaDebitoBO tesourariaDebitoBO;
    private TesourariaCreditoBO tesourariaCreditoBO;
    private ContaBancariaCreditoBO contaBancariaCreditoBO;
    private ContaBancariaDebitoBO contaBancariaDebitoBO;
    private ContaBancariaTransferenciaBO contaBancariaTransferenciaBO;
    private FormaPagamentoBO formaPagamentoBO;
    private ContaBancariaBO contaBancariaBO;

    public RelatorioExportacaoMegaContabil() throws SystemException {


        this.setLocation(250, 50);
        Controller.setQtdMovimentosHoje(0);
        
       
        initComponents();

        this.historicoBO = new HistoricoBO();
        this.tituloPagarBO = new TituloPagarBO();
        this.tituloPagarBaixaBO = new TituloPagarBaixaBO();
        this.tituloReceberBO = new TituloReceberBO();
        this.tituloReceberBaixaBO = new TituloReceberBaixaBO();
        this.tesourariaDebitoBO = new TesourariaDebitoBO();
        this.tesourariaCreditoBO = new TesourariaCreditoBO();
        this.contaBancariaCreditoBO = new ContaBancariaCreditoBO();
        this.contaBancariaDebitoBO = new ContaBancariaDebitoBO();
        this.contaBancariaTransferenciaBO = new ContaBancariaTransferenciaBO();
        this.formaPagamentoBO = new FormaPagamentoBO();
        this.contaBancariaBO = new ContaBancariaBO();

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        label2 = new javax.swing.JLabel();
        jFTPeriodoInicial = new org.jdesktop.swingx.JXDatePicker();
        jFTPeriodoFinal = new org.jdesktop.swingx.JXDatePicker();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setMaximizable(true);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance -Relatório Exportação Mega");
        setRequestFocusEnabled(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));
        jPanel1.setPreferredSize(new java.awt.Dimension(939, 314));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Parametros"));
        jPanel6.setPreferredSize(new java.awt.Dimension(1050, 125));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Período"));
        jPanel4.setPreferredSize(new java.awt.Dimension(304, 80));

        label2.setText("à");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFTPeriodoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTPeriodoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), "Formato"));
        jPanel5.setPreferredSize(new java.awt.Dimension(304, 80));

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
                .addGap(27, 27, 27)
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 170, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(52, 52, 52))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));
        jPanel2.setPreferredSize(new java.awt.Dimension(939, 65));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoGerar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) " Relatório Exportação Mega ";
    }

    public void repopularCombos() {
        comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));
    }

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        jFTPeriodoInicial.setDate(Controller.getDataServidorBD());
        jFTPeriodoFinal.setDate(Controller.getDataServidorBD());


    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (jFTPeriodoFinal.getDate() == null) {
            jFTPeriodoFinal.requestFocus();
            return false;
        }

        if (jFTPeriodoInicial.getDate() == null) {
            jFTPeriodoInicial.requestFocus();
            return false;
        }

        if (jFTPeriodoInicial.getDate().after(jFTPeriodoFinal.getDate())) {
            exibeMensagemAviso("A Data Inicial não pode ser superior a data final", null);
            jFTPeriodoInicial.requestFocus();
            return false;
        } 
                
        return true;
    }

    private ContaContabilModel getContaContabilByContaBancaria(ContaBancariaModel contaBancaria) {

        for (ContaBancariaModel conta : collContaBancaria) {

            if (conta.getPk().getId().equals(contaBancaria.getPk().getId())) {
                return conta.getContaContabil();
            }

        }

        return null;

    }

    private ContaContabilModel getContaContabilByHistorico(HistoricoModel historico) {

        for (HistoricoModel hist : collHistorico) {

            if (hist.getPk().getId().equals(historico.getPk().getId())) {
                return hist.getContaContabil();
            }

        }

        return null;

    }

    private void botaoGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarActionPerformed

        if (validaCampos()) {

            try {

                long action = Action.IMPRESSAO.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }
                

                new MovimentoDiarioBO().inserir(this.registroMovimento("REL EXP MEGA C", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do relatorio exportação Megacontabil.", null, "Impresso"));

                Map<String, Object> parametros = new HashMap<String, Object>();

                String dataI = PempecParse.dateToString(jFTPeriodoInicial.getDate());
                String dataF = PempecParse.dateToString(jFTPeriodoFinal.getDate());

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL_BARRAS, dataI);
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL_BARRAS, dataF);

                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

                this.dataInicial = PempecParse.dateToDateMinTime(jFTPeriodoInicial.getDate());
                this.dataFinal = PempecParse.dateToDateMaxTime(jFTPeriodoFinal.getDate());
                this.dadosExportados = new ArrayList<ReportExportacaoMegaContabil>();

                this.collHistorico = this.historicoBO.obterTodos(organizacaoModel);

                this.collContaBancaria = this.contaBancariaBO.obterTodos(organizacaoModel);

                FormaPagamentoModel formaPagamentoAux = new FormaPagamentoModel();

                formaPagamentoAux.setPk(new PKModel());

                formaPagamentoAux.getPk().setOrganizacao(organizacaoModel);

                formaPagamentoAux.getPk().setId(Constantes.FORMA_PAGAMENTO_ESPECIE);

                ContaContabilModel contaCaixa = formaPagamentoBO.consultarPorPk(formaPagamentoAux).getContaContabil();

                collContaBancariaTransferencia = contaBancariaTransferenciaBO.obterContaBancariaTransferenciaExportacaoRelatorio(organizacaoModel, dataInicial, dataFinal);

                for (ContaBancariaTransferenciaModel contaBancariaTransferenciaModel : collContaBancariaTransferencia) {

                    ReportExportacaoMegaContabil megaContabil = new ReportExportacaoMegaContabil();

                    megaContabil.setTabela("CBT");
                    megaContabil.setDataLancamento(contaBancariaTransferenciaModel.getDataMovimento());
                    megaContabil.setIdentificacao(contaBancariaTransferenciaModel.getIdentificacao());
                    megaContabil.setHistorico("TRANSF. ENTRE CONTAS DE MESMA TITULARIDADE");

                    Collection<ReportExportacaoMegaContabilSub> item = new ArrayList<ReportExportacaoMegaContabilSub>();

                    ReportExportacaoMegaContabilSub megaContabilD = new ReportExportacaoMegaContabilSub();
                    ReportExportacaoMegaContabilSub megaContabilC = new ReportExportacaoMegaContabilSub();

                    megaContabilD.setTipoLancamento("D");
                    megaContabilC.setTipoLancamento("C");

                    ContaContabilModel contaContabilCreditoAux = this.getContaContabilByContaBancaria(contaBancariaTransferenciaModel.getContaBancariaDebitoModel().getContaBancaria());

                    ContaContabilModel contaContabilDebitoAux = this.getContaContabilByContaBancaria(contaBancariaTransferenciaModel.getContaBancariaCreditoModel().getContaBancaria());

                    try {

                        if (contaContabilDebitoAux != null) {

                            megaContabilD.setConta(contaContabilDebitoAux.getConta());
                            megaContabilD.setContaReduzida(contaContabilDebitoAux.getContaReduzida());
                            megaContabilD.setNomeConta(contaContabilDebitoAux.getDescricao());

                        } else {

                            megaContabilD.setNomeConta("NC");
                            megaContabilD.setConta("NC");
                            megaContabilD.setContaReduzida("NC");

                        }

                    } catch (Exception e) {

                        megaContabilD.setNomeConta("NC");
                        megaContabilD.setConta("NC");
                        megaContabilD.setContaReduzida("NC");

                    }

                    try {

                        if (contaContabilCreditoAux != null) {

                            megaContabilC.setConta(contaContabilCreditoAux.getConta());
                            megaContabilC.setContaReduzida(contaContabilCreditoAux.getContaReduzida());
                            megaContabilC.setNomeConta(contaContabilCreditoAux.getDescricao());

                        } else {

                            megaContabilC.setNomeConta("NC");
                            megaContabilC.setConta("NC");
                            megaContabilC.setContaReduzida("NC");

                        }

                    } catch (Exception e) {

                        megaContabilC.setNomeConta("NC");
                        megaContabilC.setConta("NC");
                        megaContabilC.setContaReduzida("NC");

                    }

                    megaContabilD.setValor(contaBancariaTransferenciaModel.getValor());
                    megaContabilC.setValor(contaBancariaTransferenciaModel.getValor());
                    megaContabil.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    megaContabil.setCnpj(organizacaoModel.getCnpj());

                    item.add(megaContabilD);
                    item.add(megaContabilC);

                    megaContabil.setItem(item);

                    megaContabil.setFlag(true);
                    dadosExportados.add(megaContabil);

                }

                collContaBancariaDebito = contaBancariaDebitoBO.obterContaBancariaDebitoExportacaoRelatorio(organizacaoModel, dataInicial, dataFinal);

                for (ContaBancariaDebitoModel contaBancariaDebitoModel : collContaBancariaDebito) {

                    ReportExportacaoMegaContabil megaContabil = new ReportExportacaoMegaContabil();

                    Collection<ReportExportacaoMegaContabilSub> item = new ArrayList<ReportExportacaoMegaContabilSub>();

                    ContaContabilModel contaContabilDebitoAux = this.getContaContabilByContaBancaria(contaBancariaDebitoModel.getContaBancaria());

                    ReportExportacaoMegaContabilSub megaContabilD = new ReportExportacaoMegaContabilSub();
                    ReportExportacaoMegaContabilSub megaContabilC = new ReportExportacaoMegaContabilSub();

                    megaContabil.setTabela("CBD");
                    megaContabil.setIdentificacao(contaBancariaDebitoModel.getIdentificacao());
                    megaContabil.setDataLancamento(contaBancariaDebitoModel.getDataMovimento());
                    megaContabil.setHistorico(PempecUtil.concatenaComEspaco(contaBancariaDebitoModel.getDescricao()));

                    megaContabilD.setTipoLancamento("D");
                    megaContabilC.setTipoLancamento("C");

                    try {

                        if (contaBancariaDebitoModel.getTipoOperacaoBancaria() != null
                                && contaBancariaDebitoModel.getTipoOperacaoBancaria().getContaContabil() != null) {

                            megaContabilD.setConta(contaBancariaDebitoModel.getTipoOperacaoBancaria().getContaContabil().getConta());
                            megaContabilD.setContaReduzida(contaBancariaDebitoModel.getTipoOperacaoBancaria().getContaContabil().getContaReduzida());
                            megaContabilD.setNomeConta(contaBancariaDebitoModel.getTipoOperacaoBancaria().getContaContabil().getDescricao());

                        } else {

                            megaContabilD.setNomeConta("NC");
                            megaContabilD.setConta("NC");
                            megaContabilD.setContaReduzida("NC");

                        }

                    } catch (Exception e) {

                        megaContabilD.setNomeConta("NC");
                        megaContabilD.setConta("NC");
                        megaContabilD.setContaReduzida("NC");

                    }

                    try {

                        if (contaContabilDebitoAux != null) {

                            megaContabilC.setConta(contaContabilDebitoAux.getConta());
                            megaContabilC.setContaReduzida(contaContabilDebitoAux.getContaReduzida());
                            megaContabilC.setNomeConta(contaContabilDebitoAux.getDescricao());

                        } else {

                            megaContabilC.setNomeConta("NC");
                            megaContabilC.setConta("NC");
                            megaContabilC.setContaReduzida("NC");

                        }

                    } catch (Exception e) {

                        megaContabilC.setNomeConta("NC");
                        megaContabilC.setConta("NC");
                        megaContabilC.setContaReduzida("NC");

                    }

                    megaContabilD.setValor(contaBancariaDebitoModel.getValor());
                    megaContabilC.setValor(contaBancariaDebitoModel.getValor());

                    megaContabil.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    megaContabil.setCnpj(organizacaoModel.getCnpj());

                    item.add(megaContabilD);
                    item.add(megaContabilC);

                    megaContabil.setItem(item);

                    megaContabil.setFlag(true);
                    dadosExportados.add(megaContabil);

                }

                collContaBancariaCredito = contaBancariaCreditoBO.obterContaBancariaCreditoExportacaoRelatorio(organizacaoModel, dataInicial, dataFinal);

                for (ContaBancariaCreditoModel contaBancariaCreditoModel : collContaBancariaCredito) {

                    ReportExportacaoMegaContabil megaContabil = new ReportExportacaoMegaContabil();

                    Collection<ReportExportacaoMegaContabilSub> item = new ArrayList<ReportExportacaoMegaContabilSub>();

                    ContaContabilModel contaContabilCreditoAux = this.getContaContabilByContaBancaria(contaBancariaCreditoModel.getContaBancaria());

                    ReportExportacaoMegaContabilSub megaContabilD = new ReportExportacaoMegaContabilSub();
                    ReportExportacaoMegaContabilSub megaContabilC = new ReportExportacaoMegaContabilSub();

                    megaContabil.setTabela("CBC");
                    megaContabil.setIdentificacao(contaBancariaCreditoModel.getIdentificacao());
                    megaContabil.setDataLancamento(contaBancariaCreditoModel.getDataMovimento());
                    megaContabil.setHistorico(PempecUtil.concatenaComEspaco(contaBancariaCreditoModel.getDescricao()));

                    megaContabilD.setTipoLancamento("D");
                    megaContabilC.setTipoLancamento("C");

                    try {

                        if (contaContabilCreditoAux != null) {

                            megaContabilD.setConta(contaContabilCreditoAux.getConta());
                            megaContabilD.setContaReduzida(contaContabilCreditoAux.getContaReduzida());
                            megaContabilD.setNomeConta(contaContabilCreditoAux.getDescricao());

                        } else {

                            megaContabilD.setNomeConta("NC");
                            megaContabilD.setConta("NC");
                            megaContabilD.setContaReduzida("NC");

                        }

                    } catch (Exception e) {

                        megaContabilD.setNomeConta("NC");
                        megaContabilD.setConta("NC");
                        megaContabilD.setContaReduzida("NC");

                    }

                    try {

                        if (contaBancariaCreditoModel.getTipoOperacaoBancaria() != null
                                && contaBancariaCreditoModel.getTipoOperacaoBancaria().getContaContabil() != null) {

                            megaContabilC.setConta(contaBancariaCreditoModel.getTipoOperacaoBancaria().getContaContabil().getConta());
                            megaContabilC.setContaReduzida(contaBancariaCreditoModel.getTipoOperacaoBancaria().getContaContabil().getContaReduzida());
                            megaContabilC.setNomeConta(contaBancariaCreditoModel.getTipoOperacaoBancaria().getContaContabil().getDescricao());

                        } else {

                            megaContabilC.setNomeConta("NC");
                            megaContabilC.setConta("NC");
                            megaContabilC.setContaReduzida("NC");

                        }

                    } catch (Exception e) {

                        megaContabilC.setNomeConta("NC");
                        megaContabilC.setConta("NC");
                        megaContabilC.setContaReduzida("NC");

                    }

                    megaContabilD.setValor(contaBancariaCreditoModel.getValor());
                    megaContabilC.setValor(contaBancariaCreditoModel.getValor());

                    megaContabil.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    megaContabil.setCnpj(organizacaoModel.getCnpj());

                    item.add(megaContabilD);

                    item.add(megaContabilC);

                    megaContabil.setItem(item);

                    megaContabil.setFlag(true);

                    dadosExportados.add(megaContabil);

                }

                collTesourariaDebito = tesourariaDebitoBO.obterTesourariaDebitoExportacaoRelatorio(organizacaoModel, dataInicial, dataFinal);

                for (TesourariaDebitoModel tesourariaDebitoModel : collTesourariaDebito) {

                    ReportExportacaoMegaContabil megaContabil = new ReportExportacaoMegaContabil();

                    Collection<ReportExportacaoMegaContabilSub> item = new ArrayList<ReportExportacaoMegaContabilSub>();

                    ReportExportacaoMegaContabilSub megaContabilD = new ReportExportacaoMegaContabilSub();
                    ReportExportacaoMegaContabilSub megaContabilC = new ReportExportacaoMegaContabilSub();

                    megaContabil.setTabela("TD");
                    megaContabil.setIdentificacao(tesourariaDebitoModel.getNumeroDocumento());
                    megaContabil.setDataLancamento(tesourariaDebitoModel.getDataMovimento());
                    megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(tesourariaDebitoModel.getHistorico().getDescricao() + " " + tesourariaDebitoModel.getDescricao()));
                    megaContabil.setNome(tesourariaDebitoModel.getCedenteModel().getNome());

                    megaContabilD.setTipoLancamento("D");
                    megaContabilC.setTipoLancamento("C");

                    try {

                        if (tesourariaDebitoModel.getHistorico() != null
                                && tesourariaDebitoModel.getHistorico().getContaContabil() != null) {

                            megaContabilD.setConta(tesourariaDebitoModel.getHistorico().getContaContabil().getConta());
                            megaContabilD.setContaReduzida(tesourariaDebitoModel.getHistorico().getContaContabil().getContaReduzida());
                            megaContabilD.setNomeConta(tesourariaDebitoModel.getHistorico().getContaContabil().getDescricao());

                        } else {

                            megaContabilD.setNomeConta("NC");
                            megaContabilD.setConta("NC");
                            megaContabilD.setContaReduzida("NC");

                        }

                    } catch (Exception e) {

                        megaContabilD.setNomeConta("NC");
                        megaContabilD.setConta("NC");
                        megaContabilD.setContaReduzida("NC");

                    }

                    if (contaCaixa != null) {

                        megaContabilC.setConta(contaCaixa.getConta());
                        megaContabilC.setContaReduzida(contaCaixa.getContaReduzida());
                        megaContabilC.setNomeConta(contaCaixa.getDescricao());

                    } else {

                        megaContabilC.setNomeConta("NC");
                        megaContabilC.setConta("NC");
                        megaContabilC.setContaReduzida("NC");

                    }

                    megaContabilD.setValor(tesourariaDebitoModel.getValorNominal());
                    megaContabilC.setValor(tesourariaDebitoModel.getValorNominal());

                    megaContabil.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    megaContabil.setCnpj(organizacaoModel.getCnpj());

                    item.add(megaContabilD);
                    item.add(megaContabilC);

                    megaContabil.setItem(item);

                    megaContabil.setFlag(true);
                    dadosExportados.add(megaContabil);

                }

                collTesourariaCredito = tesourariaCreditoBO.obterTesourariaCreditoExportacaoRelatorio(organizacaoModel, dataInicial, dataFinal);

                for (TesourariaCreditoModel tesourariaCreditoModel : collTesourariaCredito) {

                    ReportExportacaoMegaContabil megaContabil = new ReportExportacaoMegaContabil();

                    Collection<ReportExportacaoMegaContabilSub> item = new ArrayList<ReportExportacaoMegaContabilSub>();

                    ReportExportacaoMegaContabilSub megaContabilD = new ReportExportacaoMegaContabilSub();
                    ReportExportacaoMegaContabilSub megaContabilC = new ReportExportacaoMegaContabilSub();

                    megaContabil.setTabela("TC");
                    megaContabil.setIdentificacao(tesourariaCreditoModel.getNumeroDocumento());
                    megaContabil.setDataLancamento(tesourariaCreditoModel.getDataMovimento());
                    megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(tesourariaCreditoModel.getHistorico().getDescricao() + " " + tesourariaCreditoModel.getDescricao()));
                    megaContabil.setNome(tesourariaCreditoModel.getSacadoModel().getNome());

                    megaContabilD.setTipoLancamento("D");
                    megaContabilC.setTipoLancamento("C");

                    if (contaCaixa != null) {

                        megaContabilD.setConta(contaCaixa.getConta());
                        megaContabilD.setContaReduzida(contaCaixa.getContaReduzida());
                        megaContabilD.setNomeConta(contaCaixa.getDescricao());

                    } else {

                        megaContabilD.setNomeConta("NC");
                        megaContabilD.setConta("NC");
                        megaContabilD.setContaReduzida("NC");

                    }

                    try {

                        if (tesourariaCreditoModel.getHistorico() != null
                                && tesourariaCreditoModel.getHistorico().getContaContabil() != null) {

                            megaContabilC.setConta(tesourariaCreditoModel.getHistorico().getContaContabil().getConta());
                            megaContabilC.setContaReduzida(tesourariaCreditoModel.getHistorico().getContaContabil().getContaReduzida());
                            megaContabilC.setNomeConta(tesourariaCreditoModel.getHistorico().getContaContabil().getDescricao());

                        } else {

                            megaContabilC.setNomeConta("NC");
                            megaContabilC.setConta("NC");
                            megaContabilC.setContaReduzida("NC");

                        }

                    } catch (Exception e) {

                        megaContabilC.setNomeConta("NC");
                        megaContabilC.setConta("NC");
                        megaContabilC.setContaReduzida("NC");

                    }

                    megaContabilD.setValor(tesourariaCreditoModel.getValorNominal());
                    megaContabilC.setValor(tesourariaCreditoModel.getValorNominal());

                    megaContabil.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    megaContabil.setCnpj(organizacaoModel.getCnpj());

                    item.add(megaContabilD);
                    item.add(megaContabilC);

                    megaContabil.setItem(item);

                    megaContabil.setFlag(true);
                    dadosExportados.add(megaContabil);

                }

                collTitulosPagar = tituloPagarBO.obterTitulosExportacaoRelatorio(organizacaoModel, dataInicial, dataFinal);

                collBaixasTitulosPagar = tituloPagarBaixaBO.obterBaixasExportacaoRelatorio(organizacaoModel, dataInicial, dataFinal);

                Map<String, String> provisionados = new HashMap<String, String>();

                globalTP:
                for (TituloPagarModel tituloPagarModel : collTitulosPagar) {

                    //mudado por ranan em 30/03/10. testando o relatorio de exportacao pq de valor a maior
                    //nao constava a data de pagamento nula

                    //if (tituloPagarModel.isProvisao() && tituloPagarModel.getDataPagamento()==null) {

                    if (tituloPagarModel.isProvisao()) {

                        if (tituloPagarModel.getRegistroProvisao() != null
                                && !tituloPagarModel.getRegistroProvisao().isEmpty()) {

                            if (provisionados.containsKey(tituloPagarModel.getRegistroProvisao())) {
                                continue globalTP;
                            }

                            Double somaProvisionados = 0d;

                            Double rateioHistAux = 0d;

                            Double rateioCCAux = 0d;

                            for (TituloPagarModel titulo : collTitulosPagar) {

                                if (titulo.isProvisao()) {

                                    if (titulo.getRegistroProvisao() != null
                                            && titulo.getRegistroProvisao().equals(tituloPagarModel.getRegistroProvisao())) {
                                        somaProvisionados += titulo.getValorNominal();

                                        for (TituloPagarRateioHistoricoModel rateio : titulo.getCollHistoricosRateio()) {
                                            rateioHistAux += rateio.getValor();
                                        }

                                        for (TituloPagarRateioCCModel rateioCC : titulo.getCollCentroCustosRateio()) {
                                            rateioCCAux += rateioCC.getValor();
                                        }

                                    }
                                }
                            }

                            tituloPagarModel.setValorNominal(somaProvisionados);

                            for (TituloPagarRateioHistoricoModel rateio : tituloPagarModel.getCollHistoricosRateio()) {
                                rateio.setValor(rateioHistAux);
                            }

                            for (TituloPagarRateioCCModel rateioCC : tituloPagarModel.getCollCentroCustosRateio()) {
                                rateioCC.setValor(rateioCCAux);
                            }

                            provisionados.put(tituloPagarModel.getRegistroProvisao(), tituloPagarModel.getRegistroProvisao());

                        }

                        ReportExportacaoMegaContabil megaContabil = new ReportExportacaoMegaContabil();

                        Collection<ReportExportacaoMegaContabilSub> item = new ArrayList<ReportExportacaoMegaContabilSub>();

                        ReportExportacaoMegaContabilSub megaContabilD;
                        ReportExportacaoMegaContabilSub megaContabilC = new ReportExportacaoMegaContabilSub();

                        for (TituloPagarRateioHistoricoModel rateio : tituloPagarModel.getCollHistoricosRateio()) {

                            for (TituloPagarRateioCCModel rateioCC : tituloPagarModel.getCollCentroCustosRateio()) {

                                megaContabilD = new ReportExportacaoMegaContabilSub();

                                ContaContabilModel contaContabil = this.getContaContabilByHistorico(rateio.getHistoricoModel());

                                megaContabilD.setTipoLancamento("D");

                                try {

                                    if (contaContabil != null) {

                                        megaContabilD.setConta(contaContabil.getConta());
                                        megaContabilD.setContaReduzida(contaContabil.getContaReduzida());
                                        megaContabilD.setNomeConta(contaContabil.getDescricao());

                                    } else {

                                        megaContabilD.setNomeConta("NC");
                                        megaContabilD.setConta("NC");
                                        megaContabilD.setContaReduzida("NC");

                                    }

                                } catch (Exception e) {

                                    megaContabilD.setNomeConta("NC");
                                    megaContabilD.setConta("NC");
                                    megaContabilD.setContaReduzida("NC");

                                }

                                megaContabilD.setValor((rateioCC.getValor() / tituloPagarModel.getValorNominal()) * rateio.getValor());

                                item.add(megaContabilD);

                            }

                        }

                        megaContabil.setTabela("TP");
                        megaContabil.setDataLancamento(tituloPagarModel.getDataEmissao());
                        megaContabil.setIdentificacao(tituloPagarModel.getNumeroDocumento());

                        try {

                            megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(tituloPagarModel.getHistorico().getDescricao() + " " + tituloPagarModel.getDescricao()));
                            megaContabil.setNome(tituloPagarModel.getCedente().getNome());

                        } catch (Exception e) {

                            for (HistoricoModel historico : collHistorico) {

                                if (tituloPagarModel.getHistorico().getPk().getId().equals(historico.getPk().getId())) {

                                    megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(historico.getDescricao() + " " + tituloPagarModel.getDescricao()));
                                    megaContabil.setNome(tituloPagarModel.getCedente().getNome());

                                    break;

                                }

                            }

                        }

                        megaContabilC.setTipoLancamento("C");

                        try {

                            if (tituloPagarModel.getContaContabilCredito() != null) {

                                megaContabilC.setConta(tituloPagarModel.getContaContabilCredito().getConta());
                                megaContabilC.setContaReduzida(tituloPagarModel.getContaContabilCredito().getContaReduzida());
                                megaContabilC.setNomeConta(tituloPagarModel.getContaContabilCredito().getDescricao());

                            } else {

                                megaContabilC.setNomeConta("NC");
                                megaContabilC.setConta("NC");
                                megaContabilC.setContaReduzida("NC");

                            }

                        } catch (Exception e) {

                            megaContabilC.setNomeConta("NC");
                            megaContabilC.setConta("NC");
                            megaContabilC.setContaReduzida("NC");

                        }

                        megaContabilC.setValor(tituloPagarModel.getValorNominal());

                        megaContabil.setRazaoSocial(organizacaoModel.getRazaoSocial());
                        megaContabil.setCnpj(organizacaoModel.getCnpj());

                        megaContabil.setFlag(true);

                        item.add(megaContabilC);

                        megaContabil.setItem(item);

                        dadosExportados.add(megaContabil);

                    }//fim do for

                }

//                globalTP: for (TituloPagarBaixaModel baixa : collBaixasTitulosPagar) {

                for (TituloPagarBaixaModel baixa : collBaixasTitulosPagar) {

                    //Validação para exportação de titulo pago em cheque, somente se o cheque for compensado.
//                    if (baixa.getTituloPagarBaixaCheque() != null
//                            && !baixa.getTituloPagarBaixaCheque().isEmpty()) {
//                        for (TituloPagarBaixaChequeModel cheque : baixa.getTituloPagarBaixaCheque()) {
//                            if (!cheque.getContaBancariaCheque().getStatus().getPk().getId().equals(Constantes.STATUS_CHEQUE_COMPENSADO)) {
//                                continue globalTP;
//                            }
//                        }
//                    }

                    ReportExportacaoMegaContabil megaContabil = new ReportExportacaoMegaContabil();

                    Collection<ReportExportacaoMegaContabilSub> item = new ArrayList<ReportExportacaoMegaContabilSub>();

                    ReportExportacaoMegaContabilSub megaContabilC = null;
                    ReportExportacaoMegaContabilSub megaContabilD = null;
                    ReportExportacaoMegaContabilSub megaContabilAC = null;
                    ReportExportacaoMegaContabilSub megaContabilDE = null;

                    /*for (TituloPagarModel titulo : collTitulosPagar) {
                     if (titulo.getPk().getId().equals(baixa.getTitulo().getPk().getId())
                     && titulo.getPk().getOrganizacao().getId().equals(baixa.getTitulo().getPk().getOrganizacao().getId())) {
                     baixa.setTitulo(titulo);
                     break;
                     }
                     }*/

                    for (TituloPagarRateioHistoricoModel rateio : baixa.getTitulo().getCollHistoricosRateio()) {

                        for (TituloPagarRateioCCModel rateioCC : baixa.getTitulo().getCollCentroCustosRateio()) {

                            megaContabilD = new ReportExportacaoMegaContabilSub();

                            megaContabilD.setTipoLancamento("D");

                            if (baixa.getTitulo().isProvisao()) {

                                try {

                                    if (baixa.getTitulo().getContaContabilCredito() != null) {

                                        megaContabilD.setConta(baixa.getTitulo().getContaContabilCredito().getConta());
                                        megaContabilD.setContaReduzida(baixa.getTitulo().getContaContabilCredito().getContaReduzida());
                                        megaContabilD.setNomeConta(baixa.getTitulo().getContaContabilCredito().getDescricao());

                                    } else {

                                        megaContabilD.setNomeConta("NC");
                                        megaContabilD.setConta("NC");
                                        megaContabilD.setContaReduzida("NC");

                                    }

                                } catch (Exception e) {

                                    megaContabilD.setNomeConta("NC");
                                    megaContabilD.setConta("NC");
                                    megaContabilD.setContaReduzida("NC");

                                }

                            } else {

                                ContaContabilModel contaContabil = this.getContaContabilByHistorico(rateio.getHistoricoModel());

                                try {

                                    if (contaContabil != null) {

                                        megaContabilD.setConta(contaContabil.getConta());
                                        megaContabilD.setContaReduzida(contaContabil.getContaReduzida());
                                        megaContabilD.setNomeConta(contaContabil.getDescricao());

                                    } else {

                                        megaContabilD.setNomeConta("NC");
                                        megaContabilD.setConta("NC");
                                        megaContabilD.setContaReduzida("NC");

                                    }

                                } catch (Exception e) {

                                    megaContabilD.setNomeConta("NC");
                                    megaContabilD.setConta("NC");
                                    megaContabilD.setContaReduzida("NC");

                                }

                            }

                            megaContabilD.setValor((rateioCC.getValor() / baixa.getTitulo().getValorNominal()) * rateio.getValor());

                            item.add(megaContabilD);

                        }

                    }

                    //Exportando os acrescimos

                    for (TituloPagarBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                        megaContabilAC = new ReportExportacaoMegaContabilSub();

                        megaContabilAC.setTipoLancamento("D");

                        for (HistoricoModel historico : collHistorico) {

                            try {

                                if (acrescimo.getTipoAcrescimo().getHistorico().getPk().getId().equals(historico.getPk().getId())) {

                                    if (historico.getContaContabil() != null) {

                                        megaContabilAC.setConta(historico.getContaContabil().getConta());
                                        megaContabilAC.setContaReduzida(historico.getContaContabil().getContaReduzida());
                                        megaContabilAC.setNomeConta(historico.getContaContabil().getDescricao());

                                    } else {

                                        megaContabilAC.setNomeConta("NC");
                                        megaContabilAC.setConta("NC");
                                        megaContabilAC.setContaReduzida("NC");

                                    }

                                    break;

                                }

                            } catch (Exception e) {

                                megaContabilAC.setNomeConta("NC");
                                megaContabilAC.setConta("NC");
                                megaContabilAC.setContaReduzida("NC");

                            }

                        }

                        megaContabilAC.setValor(acrescimo.getValor());

                        item.add(megaContabilAC);

                    }

                    //Exportando as deducoes

                    for (TituloPagarBaixaDeducaoModel deducao : baixa.getDeducoes()) {

                        megaContabilDE = new ReportExportacaoMegaContabilSub();

                        megaContabilDE.setTipoLancamento("C");

                        for (HistoricoModel historico : collHistorico) {

                            try {

                                if (historico.getPk().getId().equals(deducao.getTipoDeducao().getHistorico().getPk().getId())) {

                                    if (historico.getContaContabil() != null) {

                                        megaContabilDE.setConta(historico.getContaContabil().getConta());
                                        megaContabilDE.setContaReduzida(historico.getContaContabil().getContaReduzida());
                                        megaContabilDE.setNomeConta(historico.getContaContabil().getDescricao());

                                    } else {

                                        megaContabilDE.setNomeConta("NC");
                                        megaContabilDE.setConta("NC");
                                        megaContabilDE.setContaReduzida("NC");

                                    }

                                    break;

                                }


                            } catch (Exception e) {

                                megaContabilDE.setNomeConta("NC");
                                megaContabilDE.setConta("NC");
                                megaContabilDE.setContaReduzida("NC");

                            }

                        }

                        megaContabilDE.setValor(deducao.getValor());

                        item.add(megaContabilDE);

                    }

                    for (TituloPagarBaixaFormaPagamentoModel formaPagamento : baixa.getFormasPagamento()) {

                        if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)) {

                            megaContabilC = new ReportExportacaoMegaContabilSub();

                            megaContabilC.setTipoLancamento("C");

                            try {

                                if (formaPagamento.getForma().getContaContabil() != null) {

                                    megaContabilC.setConta(contaCaixa.getConta());
                                    megaContabilC.setContaReduzida(contaCaixa.getContaReduzida());
                                    megaContabilC.setNomeConta(contaCaixa.getDescricao());

                                } else {

                                    megaContabilC.setNomeConta("NC");
                                    megaContabilC.setConta("NC");
                                    megaContabilC.setContaReduzida("NC");

                                }

                            } catch (Exception e) {

                                megaContabilC.setNomeConta("NC");
                                megaContabilC.setConta("NC");
                                megaContabilC.setContaReduzida("NC");

                            }

                            megaContabilC.setValor(formaPagamento.getValor());

                            item.add(megaContabilC);

                        }

                    }

                    for (TituloPagarBaixaChequeModel cheque : baixa.getTituloPagarBaixaCheque()) {

                        megaContabilC = new ReportExportacaoMegaContabilSub();

                        megaContabilC.setTipoLancamento("C");

                        ContaContabilModel contaContabilAux = this.getContaContabilByContaBancaria(cheque.getContaBancariaCheque().getContaBancaria());

                        try {

                            if (contaContabilAux != null) {

                                megaContabilC.setConta(contaContabilAux.getConta());
                                megaContabilC.setContaReduzida(contaContabilAux.getContaReduzida());
                                megaContabilC.setNomeConta(contaContabilAux.getDescricao());

                            } else {

                                megaContabilC.setNomeConta("NC");
                                megaContabilC.setConta("NC");
                                megaContabilC.setContaReduzida("NC");

                            }

                        } catch (Exception e) {

                            megaContabilC.setNomeConta("NC");
                            megaContabilC.setConta("NC");
                            megaContabilC.setContaReduzida("NC");

                        }

                        megaContabilC.setValor(cheque.getValor());

                        item.add(megaContabilC);

                    }

                    for (TituloPagarBaixaInternetModel internet : baixa.getTituloPagarBaixaInternet()) {

                        megaContabilC = new ReportExportacaoMegaContabilSub();

                        megaContabilC.setTipoLancamento("C");

                        try {

                            ContaContabilModel contaContabilAux = this.getContaContabilByContaBancaria(internet.getContaBancaria());

                            if (contaContabilAux != null) {

                                megaContabilC.setConta(contaContabilAux.getConta());
                                megaContabilC.setContaReduzida(contaContabilAux.getContaReduzida());
                                megaContabilC.setNomeConta(contaContabilAux.getDescricao());

                            } else {

                                megaContabilC.setNomeConta("NC");
                                megaContabilC.setConta("NC");
                                megaContabilC.setContaReduzida("NC");

                            }

                        } catch (Exception e) {

                            megaContabilC.setNomeConta("NC");
                            megaContabilC.setConta("NC");
                            megaContabilC.setContaReduzida("NC");

                        }

                        megaContabilC.setValor(internet.getValor());

                        item.add(megaContabilC);

                    }

                    megaContabil.setTabela("TPB");
                    megaContabil.setDataLancamento(baixa.getTitulo().getDataPagamento());
                    megaContabil.setIdentificacao(baixa.getTitulo().getNumeroDocumento());

                    megaContabil.setCnpj(organizacaoModel.getCnpj());
                    megaContabil.setRazaoSocial(organizacaoModel.getRazaoSocial());

                    try {

                        megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(baixa.getTitulo().getHistorico().getDescricao() + " " + baixa.getTitulo().getDescricao()));
                        megaContabil.setNome(baixa.getTitulo().getCedente().getNome());

                    } catch (Exception e) {

                        for (HistoricoModel historico : collHistorico) {

                            if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId())) {

                                megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));
                                megaContabil.setNome(baixa.getTitulo().getCedente().getNome());

                                break;

                            }

                        }

                    }

                    megaContabil.setItem(item);

                    megaContabil.setFlag(true);

                    dadosExportados.add(megaContabil);

                }

                collTitulosReceber = tituloReceberBO.obterTitulosExportacaoRelatorio(organizacaoModel, dataInicial, dataFinal);

                collBaixasTitulosReceber = tituloReceberBaixaBO.obterBaixasExportacaoRelatorio(organizacaoModel, dataInicial, dataFinal);
                

                provisionados = new HashMap<String, String>();

                globalTR:
                for (TituloReceberModel tituloReceberModel : collTitulosReceber) {

                    if (tituloReceberModel.isProvisao()) {

                        if (tituloReceberModel.getRegistroProvisao() != null
                                && !tituloReceberModel.getRegistroProvisao().isEmpty()) {

                            if (provisionados.containsKey(tituloReceberModel.getRegistroProvisao())) {
                                continue globalTR;
                            }

                            Double somaProvisionados = 0d;

                            Double rateioHistAux = 0d;
                            Double rateioCCAux = 0d;

                            for (TituloReceberModel titulo : collTitulosReceber) {

                                if (titulo.isProvisao()) {
                                    if (titulo.getRegistroProvisao() != null
                                            && titulo.getRegistroProvisao().equals(tituloReceberModel.getRegistroProvisao())) {
                                        somaProvisionados += titulo.getValorNominal();
                                        for (TituloReceberRateioHistoricoModel rateio : titulo.getCollHistoricosRateio()) {
                                            rateioHistAux += rateio.getValor();
                                        }
                                        for (TituloReceberRateioCCModel rateioCC : titulo.getCollCentroCustosRateio()) {
                                            rateioCCAux += rateioCC.getValor();
                                        }
                                    }
                                }
                            }

                            tituloReceberModel.setValorNominal(somaProvisionados);

                            for (TituloReceberRateioHistoricoModel rateio : tituloReceberModel.getCollHistoricosRateio()) {
                                rateio.setValor(rateioHistAux);
                            }

                            for (TituloReceberRateioCCModel rateioCC : tituloReceberModel.getCollCentroCustosRateio()) {
                                rateioCC.setValor(rateioCCAux);
                            }

                            provisionados.put(tituloReceberModel.getRegistroProvisao(), tituloReceberModel.getRegistroProvisao());

                        }

                        ReportExportacaoMegaContabil megaContabil = new ReportExportacaoMegaContabil();

                        Collection<ReportExportacaoMegaContabilSub> item = new ArrayList<ReportExportacaoMegaContabilSub>();

                        ReportExportacaoMegaContabilSub megaContabilD = new ReportExportacaoMegaContabilSub();
                        ReportExportacaoMegaContabilSub megaContabilC;

                        megaContabilD.setTipoLancamento("D");

                        try {

                            if (tituloReceberModel.getContaContabilDebito() != null) {

                                megaContabilD.setConta(tituloReceberModel.getContaContabilDebito().getConta());
                                megaContabilD.setContaReduzida(tituloReceberModel.getContaContabilDebito().getContaReduzida());
                                megaContabilD.setNomeConta(tituloReceberModel.getContaContabilDebito().getDescricao());

                            } else {

                                megaContabilD.setNomeConta("NC");
                                megaContabilD.setConta("NC");
                                megaContabilD.setContaReduzida("NC");

                            }

                        } catch (Exception e) {

                            megaContabilD.setNomeConta("NC");
                            megaContabilD.setConta("NC");
                            megaContabilD.setContaReduzida("NC");

                        }

                        megaContabilD.setValor(tituloReceberModel.getValorNominal());

                        item.add(megaContabilD);

                        for (TituloReceberRateioHistoricoModel rateio : tituloReceberModel.getCollHistoricosRateio()) {

                            for (TituloReceberRateioCCModel rateioCC : tituloReceberModel.getCollCentroCustosRateio()) {

                                megaContabilC = new ReportExportacaoMegaContabilSub();

                                ContaContabilModel contaContabil = this.getContaContabilByHistorico(rateio.getHistoricoModel());

                                megaContabilC.setTipoLancamento("C");

                                try {

                                    if (contaContabil != null) {

                                        megaContabilC.setConta(contaContabil.getConta());
                                        megaContabilC.setContaReduzida(contaContabil.getContaReduzida());
                                        megaContabilC.setNomeConta(contaContabil.getDescricao());

                                    } else {

                                        megaContabilC.setNomeConta("NC");
                                        megaContabilC.setConta("NC");
                                        megaContabilC.setContaReduzida("NC");

                                    }

                                } catch (Exception e) {

                                    megaContabilC.setNomeConta("NC");
                                    megaContabilC.setConta("NC");
                                    megaContabilC.setContaReduzida("NC");

                                }

                                megaContabilC.setValor((rateioCC.getValor() / tituloReceberModel.getValorNominal()) * rateio.getValor());

                                item.add(megaContabilC);

                            }

                        }

                        megaContabil.setTabela("TR");

                        megaContabil.setDataLancamento(tituloReceberModel.getDataEmissao());

                        megaContabil.setIdentificacao(tituloReceberModel.getNumeroDocumento());

                        try {

                            megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(tituloReceberModel.getHistorico().getDescricao() + " " + tituloReceberModel.getDescricao()));

                            megaContabil.setNome(tituloReceberModel.getSacado().getNome());

                        } catch (Exception e) {

                            for (HistoricoModel historico : collHistorico) {

                                if (tituloReceberModel.getHistorico().getPk().getId().equals(historico.getPk().getId())) {

                                    megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(historico.getDescricao() + " " + tituloReceberModel.getDescricao()));

                                    megaContabil.setNome(tituloReceberModel.getSacado().getNome());

                                    break;

                                }

                            }

                        }

                        megaContabil.setRazaoSocial(organizacaoModel.getRazaoSocial());

                        megaContabil.setCnpj(organizacaoModel.getCnpj());

                        megaContabil.setFlag(true);

                        megaContabil.setItem(item);

                        dadosExportados.add(megaContabil);

                    }

                }

                for (TituloReceberBaixaModel baixa : collBaixasTitulosReceber) {

                    ReportExportacaoMegaContabil megaContabil = new ReportExportacaoMegaContabil();

                    Collection<ReportExportacaoMegaContabilSub> item = new ArrayList<ReportExportacaoMegaContabilSub>();

                    ReportExportacaoMegaContabilSub megaContabilC = null;

                    ReportExportacaoMegaContabilSub megaContabilD = null;

                    ReportExportacaoMegaContabilSub megaContabilAC = null;

                    ReportExportacaoMegaContabilSub megaContabilDE = null;

                    /*
                     for (TituloReceberModel titulo : collTitulosReceber) {
                     if (titulo.getPk().getId().equals(baixa.getTitulo().getPk().getId())
                     && titulo.getPk().getOrganizacao().getId().equals(baixa.getTitulo().getPk().getOrganizacao().getId())) {
                     baixa.setTitulo(titulo);
                     break;
                     }
                     }
                     */

                    for (TituloReceberBaixaFormaPagamentoModel formaPagamento : baixa.getFormasPagamento()) {

                        if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)
                                || formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE)) {

                            megaContabilD = new ReportExportacaoMegaContabilSub();

                            megaContabilD.setTipoLancamento("D");

                            try {

                                if (contaCaixa != null) {

                                    megaContabilD.setConta(contaCaixa.getConta());

                                    megaContabilD.setContaReduzida(contaCaixa.getContaReduzida());

                                    megaContabilD.setNomeConta(contaCaixa.getDescricao());

                                } else {

                                    megaContabilD.setNomeConta("NC");

                                    megaContabilD.setConta("NC");

                                    megaContabilD.setContaReduzida("NC");

                                }

                            } catch (Exception e) {

                                megaContabilD.setNomeConta("NC");

                                megaContabilD.setConta("NC");

                                megaContabilD.setContaReduzida("NC");

                            }

                            megaContabilD.setValor(formaPagamento.getValor());

                            item.add(megaContabilD);

                        }

                    }

                    for (TituloReceberBaixaInternetModel internet : baixa.getTituloReceberBaixaInternet()) {

                        megaContabilD = new ReportExportacaoMegaContabilSub();

                        megaContabilD.setTipoLancamento("D");

                        ContaContabilModel contaContabilAux = this.getContaContabilByContaBancaria(internet.getContaBancaria());

                        try {

                            if (contaContabilAux != null) {

                                megaContabilD.setConta(contaContabilAux.getConta());

                                megaContabilD.setContaReduzida(contaContabilAux.getContaReduzida());

                                megaContabilD.setNomeConta(contaContabilAux.getDescricao());

                            } else {

                                megaContabilD.setNomeConta("NC");

                                megaContabilD.setConta("NC");

                                megaContabilD.setContaReduzida("NC");

                            }

                        } catch (Exception e) {

                            megaContabilD.setNomeConta("NC");

                            megaContabilD.setConta("NC");

                            megaContabilD.setContaReduzida("NC");

                        }

                        megaContabilD.setValor(internet.getValor());

                        item.add(megaContabilD);

                    }

                    //Exportando os acrescimos
                    for (TituloReceberBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                        megaContabilAC = new ReportExportacaoMegaContabilSub();

                        megaContabilAC.setTipoLancamento("C");

                        for (HistoricoModel historico : collHistorico) {

                            try {

                                if (historico.getPk().getId().equals(acrescimo.getTipoAcrescimo().getHistorico().getPk().getId())) {

                                    if (historico.getContaContabil() != null) {

                                        megaContabilAC.setConta(historico.getContaContabil().getConta());

                                        megaContabilAC.setContaReduzida(historico.getContaContabil().getContaReduzida());

                                        megaContabilAC.setNomeConta(historico.getContaContabil().getDescricao());

                                    } else {

                                        megaContabilAC.setNomeConta("NC");

                                        megaContabilAC.setConta("NC");

                                        megaContabilAC.setContaReduzida("NC");

                                    }

                                    break;

                                }

                            } catch (Exception e) {

                                megaContabilAC.setNomeConta("NC");

                                megaContabilAC.setConta("NC");

                                megaContabilAC.setContaReduzida("NC");

                            }

                        }

                        megaContabilAC.setValor(acrescimo.getValor());

                        item.add(megaContabilAC);

                    }

                    //Exportando as deducoes
                    for (TituloReceberBaixaDeducaoModel deducao : baixa.getDeducoes()) {

                        megaContabilDE = new ReportExportacaoMegaContabilSub();

                        megaContabilDE.setTipoLancamento("D");

                        for (HistoricoModel historico : collHistorico) {

                            try {

                                if (historico.getPk().getId().equals(deducao.getTipoDeducao().getHistorico().getPk().getId())) {

                                    if (historico.getContaContabil() != null) {

                                        megaContabilDE.setConta(historico.getContaContabil().getConta());

                                        megaContabilDE.setContaReduzida(historico.getContaContabil().getContaReduzida());

                                        megaContabilDE.setNomeConta(historico.getContaContabil().getDescricao());

                                    } else {

                                        megaContabilDE.setNomeConta("NC");

                                        megaContabilDE.setConta("NC");

                                        megaContabilDE.setContaReduzida("NC");

                                    }

                                    break;

                                }

                            } catch (Exception e) {

                                megaContabilDE.setNomeConta("NC");

                                megaContabilDE.setConta("NC");

                                megaContabilDE.setContaReduzida("NC");

                            }

                        }

                        megaContabilDE.setValor(deducao.getValor());

                        item.add(megaContabilDE);

                    }

                    for (TituloReceberRateioHistoricoModel rateio : baixa.getTitulo().getCollHistoricosRateio()) {

                        for (TituloReceberRateioCCModel rateioCC : baixa.getTitulo().getCollCentroCustosRateio()) {

                            megaContabilC = new ReportExportacaoMegaContabilSub();

                            megaContabilC.setTipoLancamento("C");

                            if (baixa.getTitulo().isProvisao()) {

                                try {

                                    if (baixa.getTitulo().getContaContabilDebito() != null) {

                                        megaContabilC.setConta(baixa.getTitulo().getContaContabilDebito().getConta());

                                        megaContabilC.setContaReduzida(baixa.getTitulo().getContaContabilDebito().getContaReduzida());

                                        megaContabilC.setNomeConta(baixa.getTitulo().getContaContabilDebito().getDescricao());

                                    } else {

                                        megaContabilC.setNomeConta("NC");

                                        megaContabilC.setConta("NC");

                                        megaContabilC.setContaReduzida("NC");

                                    }

                                } catch (Exception e) {

                                    megaContabilC.setNomeConta("NC");

                                    megaContabilC.setConta("NC");

                                    megaContabilC.setContaReduzida("NC");

                                }

                            } else {

                                ContaContabilModel contaContabil = this.getContaContabilByHistorico(rateio.getHistoricoModel());

                                try {

                                    if (contaContabil != null) {

                                        megaContabilC.setConta(contaContabil.getConta());

                                        megaContabilC.setContaReduzida(contaContabil.getContaReduzida());

                                        megaContabilC.setNomeConta(contaContabil.getDescricao());

                                    } else {

                                        megaContabilC.setNomeConta("NC");

                                        megaContabilC.setConta("NC");

                                        megaContabilC.setContaReduzida("NC");

                                    }

                                } catch (Exception e) {

                                    megaContabilC.setNomeConta("NC");

                                    megaContabilC.setConta("NC");

                                    megaContabilC.setContaReduzida("NC");

                                }

                            }

                            megaContabilC.setValor((rateioCC.getValor() / baixa.getTitulo().getValorNominal()) * rateio.getValor());

                            item.add(megaContabilC);

                        }

                    }

                    megaContabil.setTabela("TRB");

                    megaContabil.setDataLancamento(baixa.getTitulo().getDataPagamento());

                    megaContabil.setIdentificacao(baixa.getTitulo().getNumeroDocumento());

                    megaContabil.setCnpj(organizacaoModel.getCnpj());

                    megaContabil.setRazaoSocial(organizacaoModel.getRazaoSocial());

                    try {

                        megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(baixa.getTitulo().getHistorico().getDescricao() + " " + baixa.getTitulo().getDescricao()));

                        megaContabil.setNome(baixa.getTitulo().getSacado().getNome());

                    } catch (Exception e) {

                        for (HistoricoModel historico : collHistorico) {

                            if (baixa.getTitulo().getHistorico().getPk().getId().equals(historico.getPk().getId())) {

                                megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(historico.getDescricao() + " " + baixa.getTitulo().getDescricao()));

                                megaContabil.setNome(baixa.getTitulo().getSacado().getNome());

                                break;

                            }

                        }

                    }

                    megaContabil.setItem(item);

                    megaContabil.setFlag(true);

                    dadosExportados.add(megaContabil);

                }

                Collections.sort(dadosExportados);

                switch (comboFormato.getSelectedIndex()) {

                    case 0:
                        new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_EXPORTACAO_MEGACONTABIL, parametros, dadosExportados);
                        break;
                    case 1:
                        new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_EXPORTACAO_MEGACONTABIL, parametros, dadosExportados);
                        break;
                    case 2:
                        new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_EXPORTACAO_MEGACONTABIL, parametros, dadosExportados);
                        break;

                    case 3:
                        //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                        File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_EXPORTACAO_MEGACONTABIL, parametros, dadosExportados);

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
        }        // TODO add your handling code here:
}//GEN-LAST:event_comboFormatoItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoFinal;
    private org.jdesktop.swingx.JXDatePicker jFTPeriodoInicial;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel label2;
    // End of variables declaration//GEN-END:variables
}
