package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaCreditoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaTransferenciaBO;
import br.com.pempec.finance.businessObjects.FormaPagamentoBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.LoteContabilBO;
import br.com.pempec.finance.businessObjects.MovimentoDiarioBO;
import br.com.pempec.finance.businessObjects.TesourariaCreditoBO;
import br.com.pempec.finance.businessObjects.TesourariaDebitoBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaBO;
import br.com.pempec.finance.businessObjects.TituloReceberBO;
import br.com.pempec.finance.businessObjects.TituloReceberBaixaBO;
import br.com.pempec.finance.businessObjects.UsuarioBO;
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
import br.com.pempec.finance.models.LoteContabilModel;
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
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.models.reports.ReportLoteExportacao;
import br.com.pempec.finance.models.reports.ReportLoteExportacaoSub;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

/**
 *
 * @author Administrador
 */
public class RelatorioLoteExportacao extends FinanceInternalFrame implements IRepopulador {

    private long tela = Tela.TELA_RELATORIOS_EXPORTACAO_MEGACONTABIL.getTela();
    private UsuarioBO usuarioBO = new UsuarioBO();
    private HistoricoBO historicoBO = new HistoricoBO();
    private LoteContabilBO loteContabilBO = new LoteContabilBO();
    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private TituloPagarBaixaBO tituloPagarBaixaBO = new TituloPagarBaixaBO();
    private TituloReceberBO tituloReceberBO = new TituloReceberBO();
    private TituloReceberBaixaBO tituloReceberBaixaBO = new TituloReceberBaixaBO();
    private TesourariaCreditoBO tesourariaCreditoBO = new TesourariaCreditoBO();
    private TesourariaDebitoBO tesourariaDebitoBO = new TesourariaDebitoBO();
    private ContaBancariaCreditoBO contaBancariaCreditoBO = new ContaBancariaCreditoBO();
    private ContaBancariaDebitoBO contaBancariaDebitoBO = new ContaBancariaDebitoBO();
    private ContaBancariaTransferenciaBO contaBancariaTransferenciaBO = new ContaBancariaTransferenciaBO();
    private Collection<TituloPagarModel> collTitulosPagar;
    private Collection<TituloPagarBaixaModel> collBaixasTitulosPagar;
    private Collection<TituloReceberModel> collTitulosReceber;
    private Collection<TituloReceberBaixaModel> collBaixasTitulosReceber;
    private Collection<TesourariaDebitoModel> collTesourariaDebito;
    private Collection<TesourariaCreditoModel> collTesourariaCredito;
    private Collection<ContaBancariaDebitoModel> collContaBancariaDebito;
    private Collection<ContaBancariaCreditoModel> collContaBancariaCredito;
    private Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia;
    private List<ReportLoteExportacao> dadosExportados;
    private Collection<ContaBancariaModel> collContaBancaria;
    private Collection<HistoricoModel> collHistorico;
    private FormaPagamentoBO formaPagamentoBO = new FormaPagamentoBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();

    public RelatorioLoteExportacao() throws SystemException {
        initComponents();
        Controller.setQtdMovimentosHoje(0);
        campoLoteContabil.setVisible(false);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        comboLoteContabil = new javax.swing.JComboBox();
        campoLoteContabil = new javax.swing.JTextField();
        labelData = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelDataUltimaAtualizacao = new javax.swing.JLabel();
        labelSituacao = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        labelTD = new javax.swing.JLabel();
        labelCTAT = new javax.swing.JLabel();
        labelCTAD = new javax.swing.JLabel();
        labelTRB = new javax.swing.JLabel();
        labelTR = new javax.swing.JLabel();
        labelCTAC = new javax.swing.JLabel();
        labelTP = new javax.swing.JLabel();
        labelTPB = new javax.swing.JLabel();
        labelTC = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Exportação Mega Contábil");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), "Formato"));

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
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(comboFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Número do Lote");

        comboLoteContabil.setToolTipText("");
        comboLoteContabil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLoteContabilActionPerformed(evt);
            }
        });

        campoLoteContabil.setEditable(false);

        labelData.setFont(new java.awt.Font("Arial", 0, 10));
        labelData.setForeground(new java.awt.Color(0, 102, 102));
        labelData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelData.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Data Exportação"));

        labelUsuario.setFont(new java.awt.Font("Arial", 0, 10));
        labelUsuario.setForeground(new java.awt.Color(0, 102, 102));
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Usuário"));

        labelDataUltimaAtualizacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelDataUltimaAtualizacao.setForeground(new java.awt.Color(0, 102, 102));
        labelDataUltimaAtualizacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataUltimaAtualizacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Data Ult. Atualização"));

        labelSituacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelSituacao.setForeground(new java.awt.Color(0, 102, 102));
        labelSituacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSituacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Situação"));

        labelTotal.setForeground(new java.awt.Color(51, 51, 255));
        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Total Documentos"));

        labelTD.setFont(new java.awt.Font("Arial", 0, 10));
        labelTD.setForeground(new java.awt.Color(102, 0, 102));
        labelTD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Tes. Déb."));

        labelCTAT.setFont(new java.awt.Font("Arial", 0, 10));
        labelCTAT.setForeground(new java.awt.Color(102, 0, 102));
        labelCTAT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCTAT.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Transfer. Cta."));

        labelCTAD.setFont(new java.awt.Font("Arial", 0, 10));
        labelCTAD.setForeground(new java.awt.Color(102, 0, 102));
        labelCTAD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCTAD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Cta. Bco. Déb."));

        labelTRB.setFont(new java.awt.Font("Arial", 0, 10));
        labelTRB.setForeground(new java.awt.Color(102, 0, 102));
        labelTRB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTRB.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Recebidos"));

        labelTR.setFont(new java.awt.Font("Arial", 0, 10));
        labelTR.setForeground(new java.awt.Color(102, 0, 102));
        labelTR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTR.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Tit. Receber"));

        labelCTAC.setFont(new java.awt.Font("Arial", 0, 10));
        labelCTAC.setForeground(new java.awt.Color(102, 0, 102));
        labelCTAC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCTAC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Cta. Bco. Créd."));

        labelTP.setFont(new java.awt.Font("Arial", 0, 10));
        labelTP.setForeground(new java.awt.Color(102, 0, 102));
        labelTP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Tit. Pagar"));

        labelTPB.setFont(new java.awt.Font("Arial", 0, 10));
        labelTPB.setForeground(new java.awt.Color(102, 0, 102));
        labelTPB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTPB.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd  Pagos"));

        labelTC.setFont(new java.awt.Font("Arial", 0, 10));
        labelTC.setForeground(new java.awt.Color(102, 0, 102));
        labelTC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Tes. Créd."));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(labelTC, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(labelTD, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(labelCTAT, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(labelTPB, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(labelTRB, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(labelTP, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(labelTR, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelCTAC, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(labelCTAD, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelDataUltimaAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(comboLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(campoLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 236, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDataUltimaAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelTP, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTR, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelTPB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTRB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelCTAD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelCTAC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCTAT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTC, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addComponent(botaoGerar, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
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
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
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
        return (String) "Exportação Mega Contábil";
    }

    public void repopularCombos() {

        try {

            Collection<LoteContabilModel> lLoteContabil = new ArrayList<LoteContabilModel>();

            LoteContabilModel loteContabilModel = new LoteContabilModel();

            loteContabilModel.setLote(" -> Selecione <- ");

            lLoteContabil.add(loteContabilModel);

            lLoteContabil.addAll(loteContabilBO.obterTodos(organizacaoModel));

            comboLoteContabil.setModel(new javax.swing.DefaultComboBoxModel(lLoteContabil.toArray()));

            comboFormato.setModel(new javax.swing.DefaultComboBoxModel(Controller.getCollFormatos().toArray()));

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

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        campoLoteContabil.setVisible(false);
        comboLoteContabil.setSelectedIndex(0);
        campoLoteContabil.setText("");
        labelData.setText("");
        labelDataUltimaAtualizacao.setText("");
        labelUsuario.setText("");
        labelTC.setText("");
        labelTD.setText("");
        labelTP.setText("");
        labelTR.setText("");
        labelTPB.setText("");
        labelTRB.setText("");
        labelTotal.setText("");
        labelSituacao.setText("");
        labelCTAC.setText("");
        labelCTAD.setText("");
        labelCTAT.setText("");

        botaoGerar.setEnabled(false);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (comboLoteContabil.getSelectedIndex() == 0) {
            comboLoteContabil.requestFocus();
            return false;
        }

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

                new MovimentoDiarioBO().inserir(this.registroMovimento("REL EXP MEGA LOTE", null, Controller.getUsuarioLogado().getNome() + " solicitou impressao do relatorio exportação Megacontabil.", null, "Impresso"));

                Map<String, Object> parametros = new HashMap<String, Object>();

                this.dadosExportados = new ArrayList<ReportLoteExportacao>();

                LoteContabilModel loteContabilModel = new LoteContabilModel();

                loteContabilModel.setPk(new PKModel());
                loteContabilModel.getPk().setOrganizacao(Controller.getOrganizacao());
                loteContabilModel.setLote(comboLoteContabil.getSelectedItem().toString());

                loteContabilModel = loteContabilBO.consultarPorTemplate(loteContabilModel);

                parametros.put(RelatorioConstantes.PARAMETRO_ENDERECO, organizacaoModel.getEndereco());

                parametros.put(RelatorioConstantes.PARAMETRO_DATA_INICIAL_BARRAS, PempecParse.dateToString(loteContabilModel.getPeriodoInicial()));
                parametros.put(RelatorioConstantes.PARAMETRO_DATA_FINAL_BARRAS, PempecParse.dateToString(loteContabilModel.getPeriodoFinal()));

                this.collHistorico = this.historicoBO.obterTodos(organizacaoModel);

                this.collContaBancaria = this.contaBancariaBO.obterTodos(organizacaoModel);

                FormaPagamentoModel formaPagamentoAux = new FormaPagamentoModel();

                formaPagamentoAux.setPk(new PKModel());

                formaPagamentoAux.getPk().setOrganizacao(organizacaoModel);

                formaPagamentoAux.getPk().setId(Constantes.FORMA_PAGAMENTO_ESPECIE);

                ContaContabilModel contaCaixa = formaPagamentoBO.consultarPorPk(formaPagamentoAux).getContaContabil();

                collContaBancariaTransferencia = contaBancariaTransferenciaBO.obterTitulosExportados(loteContabilModel);

                for (ContaBancariaTransferenciaModel contaBancariaTransferenciaModel : collContaBancariaTransferencia) {

                    ReportLoteExportacao megaContabil = new ReportLoteExportacao();

                    megaContabil.setTabela("CBT");
                    megaContabil.setDataLancamento(contaBancariaTransferenciaModel.getDataMovimento());
                    megaContabil.setIdentificacao(contaBancariaTransferenciaModel.getIdentificacao());
                    megaContabil.setHistorico("TRANSF. ENTRE CONTAS DE MESMA TITULARIDADE");
                    megaContabil.setLote(loteContabilModel.getLote());
                    megaContabil.setData(loteContabilModel.getDataRegistro());

                    Collection<ReportLoteExportacaoSub> item = new ArrayList<ReportLoteExportacaoSub>();

                    ReportLoteExportacaoSub megaContabilD = new ReportLoteExportacaoSub();
                    ReportLoteExportacaoSub megaContabilC = new ReportLoteExportacaoSub();

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

                collContaBancariaDebito = contaBancariaDebitoBO.obterTitulosExportados(loteContabilModel);

                for (ContaBancariaDebitoModel contaBancariaDebitoModel : collContaBancariaDebito) {

                    ReportLoteExportacao megaContabil = new ReportLoteExportacao();

                    Collection<ReportLoteExportacaoSub> item = new ArrayList<ReportLoteExportacaoSub>();

                    ContaContabilModel contaContabilDebitoAux = this.getContaContabilByContaBancaria(contaBancariaDebitoModel.getContaBancaria());

                    ReportLoteExportacaoSub megaContabilD = new ReportLoteExportacaoSub();
                    ReportLoteExportacaoSub megaContabilC = new ReportLoteExportacaoSub();

                    megaContabil.setTabela("CBD");
                    megaContabil.setIdentificacao(contaBancariaDebitoModel.getIdentificacao());
                    megaContabil.setDataLancamento(contaBancariaDebitoModel.getDataMovimento());
                    megaContabil.setHistorico(PempecUtil.concatenaComEspaco(contaBancariaDebitoModel.getDescricao()));
                    megaContabil.setLote(loteContabilModel.getLote());
                    megaContabil.setData(loteContabilModel.getDataRegistro());

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

                collContaBancariaCredito = contaBancariaCreditoBO.obterTitulosExportados(loteContabilModel);

                for (ContaBancariaCreditoModel contaBancariaCreditoModel : collContaBancariaCredito) {

                    ReportLoteExportacao megaContabil = new ReportLoteExportacao();

                    Collection<ReportLoteExportacaoSub> item = new ArrayList<ReportLoteExportacaoSub>();

                    ContaContabilModel contaContabilCreditoAux = this.getContaContabilByContaBancaria(contaBancariaCreditoModel.getContaBancaria());

                    ReportLoteExportacaoSub megaContabilD = new ReportLoteExportacaoSub();
                    ReportLoteExportacaoSub megaContabilC = new ReportLoteExportacaoSub();

                    megaContabil.setTabela("CBC");
                    megaContabil.setIdentificacao(contaBancariaCreditoModel.getIdentificacao());
                    megaContabil.setDataLancamento(contaBancariaCreditoModel.getDataMovimento());
                    megaContabil.setHistorico(PempecUtil.concatenaComEspaco(contaBancariaCreditoModel.getDescricao()));
                    megaContabil.setLote(loteContabilModel.getLote());
                    megaContabil.setData(loteContabilModel.getDataRegistro());

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

                collTesourariaDebito = tesourariaDebitoBO.obterTitulosExportados(loteContabilModel);

                for (TesourariaDebitoModel tesourariaDebitoModel : collTesourariaDebito) {

                    ReportLoteExportacao megaContabil = new ReportLoteExportacao();

                    Collection<ReportLoteExportacaoSub> item = new ArrayList<ReportLoteExportacaoSub>();

                    ReportLoteExportacaoSub megaContabilD = new ReportLoteExportacaoSub();
                    ReportLoteExportacaoSub megaContabilC = new ReportLoteExportacaoSub();

                    megaContabil.setTabela("TD");
                    megaContabil.setIdentificacao(tesourariaDebitoModel.getNumeroDocumento());
                    megaContabil.setDataLancamento(tesourariaDebitoModel.getDataMovimento());
                    megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(tesourariaDebitoModel.getHistorico().getDescricao() + " " + tesourariaDebitoModel.getDescricao()));
                    megaContabil.setNome(tesourariaDebitoModel.getCedenteModel().getNome());
                    megaContabil.setLote(loteContabilModel.getLote());
                    megaContabil.setData(loteContabilModel.getDataRegistro());

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

                collTesourariaCredito = tesourariaCreditoBO.obterTitulosExportados(loteContabilModel);

                for (TesourariaCreditoModel tesourariaCreditoModel : collTesourariaCredito) {

                    ReportLoteExportacao megaContabil = new ReportLoteExportacao();

                    Collection<ReportLoteExportacaoSub> item = new ArrayList<ReportLoteExportacaoSub>();

                    ReportLoteExportacaoSub megaContabilD = new ReportLoteExportacaoSub();
                    ReportLoteExportacaoSub megaContabilC = new ReportLoteExportacaoSub();

                    megaContabil.setTabela("TC");
                    megaContabil.setIdentificacao(tesourariaCreditoModel.getNumeroDocumento());
                    megaContabil.setDataLancamento(tesourariaCreditoModel.getDataMovimento());
                    megaContabil.setHistorico(PempecUtil.concatenaComQuebraLinha(tesourariaCreditoModel.getHistorico().getDescricao() + " " + tesourariaCreditoModel.getDescricao()));
                    megaContabil.setNome(tesourariaCreditoModel.getSacadoModel().getNome());
                    megaContabil.setLote(loteContabilModel.getLote());
                    megaContabil.setData(loteContabilModel.getDataRegistro());

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

                collTitulosPagar = tituloPagarBO.obterTitulosExportados(loteContabilModel);

                collBaixasTitulosPagar = tituloPagarBaixaBO.obterTitulosExportados(loteContabilModel);

                Map<String, String> provisionados = new HashMap<String, String>();

                globalTP:
                for (TituloPagarModel tituloPagarModel : collTitulosPagar) {

                    if (tituloPagarModel.isProvisao()) {

                        if (tituloPagarModel.getRegistroProvisao() != null
                                && !tituloPagarModel.getRegistroProvisao().isEmpty()) {

                            if (provisionados.containsKey(tituloPagarModel.getRegistroProvisao())) {
                                continue globalTP;
                            }

                            Double somaProvisionados = 0d;
                            Integer qtdTitulos = 0;

                            for (TituloPagarModel titulo : collTitulosPagar) {

                                if (titulo.isProvisao()) {
                                    if (titulo.getRegistroProvisao() != null
                                            && titulo.getRegistroProvisao().equals(tituloPagarModel.getRegistroProvisao())) {
                                        somaProvisionados += titulo.getValorNominal();
                                        qtdTitulos++;
                                    }
                                }
                            }

                            tituloPagarModel.setValorNominal(somaProvisionados);

                            for (TituloPagarRateioHistoricoModel rateio : tituloPagarModel.getCollHistoricosRateio()) {
                                rateio.setValor(rateio.getValor() * qtdTitulos);
                            }

                            for (TituloPagarRateioCCModel rateioCC : tituloPagarModel.getCollCentroCustosRateio()) {
                                rateioCC.setValor(rateioCC.getValor() * qtdTitulos);
                            }

                            provisionados.put(tituloPagarModel.getRegistroProvisao(), tituloPagarModel.getRegistroProvisao());

                        }

                        ReportLoteExportacao megaContabil = new ReportLoteExportacao();

                        Collection<ReportLoteExportacaoSub> item = new ArrayList<ReportLoteExportacaoSub>();

                        ReportLoteExportacaoSub megaContabilD;
                        ReportLoteExportacaoSub megaContabilC = new ReportLoteExportacaoSub();

                        for (TituloPagarRateioHistoricoModel rateio : tituloPagarModel.getCollHistoricosRateio()) {

                            for (TituloPagarRateioCCModel rateioCC : tituloPagarModel.getCollCentroCustosRateio()) {

                                megaContabilD = new ReportLoteExportacaoSub();

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
                        megaContabil.setLote(loteContabilModel.getLote());
                        megaContabil.setData(loteContabilModel.getDataRegistro());

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

                    }

                }

                for (TituloPagarBaixaModel baixa : collBaixasTitulosPagar) {

                    ReportLoteExportacao megaContabil = new ReportLoteExportacao();

                    Collection<ReportLoteExportacaoSub> item = new ArrayList<ReportLoteExportacaoSub>();

                    ReportLoteExportacaoSub megaContabilC = null;
                    ReportLoteExportacaoSub megaContabilD = null;
                    ReportLoteExportacaoSub megaContabilAC = null;
                    ReportLoteExportacaoSub megaContabilDE = null;

                    for (TituloPagarModel titulo : collTitulosPagar) {
                        if (titulo.getPk().getId().equals(baixa.getTitulo().getPk().getId())
                                && titulo.getPk().getOrganizacao().getId().equals(baixa.getTitulo().getPk().getOrganizacao().getId())) {
                            baixa.setTitulo(titulo);
                            break;
                        }
                    }

                    if (baixa.getTitulo().isProvisao()) {

                        megaContabilD = new ReportLoteExportacaoSub();

                        megaContabilD.setTipoLancamento("D");

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

                        megaContabilD.setValor(baixa.getTitulo().getValorNominal());

                        item.add(megaContabilD);


                    } else {

                        for (TituloPagarRateioHistoricoModel rateio : baixa.getTitulo().getCollHistoricosRateio()) {

                            for (TituloPagarRateioCCModel rateioCC : baixa.getTitulo().getCollCentroCustosRateio()) {

                                megaContabilD = new ReportLoteExportacaoSub();

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

                                megaContabilD.setValor((rateioCC.getValor() / baixa.getTitulo().getValorNominal()) * rateio.getValor());

                                item.add(megaContabilD);

                            }

                        }

                    }

                    //Exportando os acrescimos

                    for (TituloPagarBaixaAcrescimoModel acrescimo : baixa.getAcrescimos()) {

                        megaContabilAC = new ReportLoteExportacaoSub();

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

                        megaContabilDE = new ReportLoteExportacaoSub();

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

                            megaContabilC = new ReportLoteExportacaoSub();

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

                        megaContabilC = new ReportLoteExportacaoSub();

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

                        megaContabilC = new ReportLoteExportacaoSub();

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
                    megaContabil.setLote(loteContabilModel.getLote());
                    megaContabil.setData(loteContabilModel.getDataRegistro());

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

                collTitulosReceber = tituloReceberBO.obterTitulosExportados(loteContabilModel);

                collBaixasTitulosReceber = tituloReceberBaixaBO.obterTitulosExportados(loteContabilModel);

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
                            Integer qtdTitulos = 0;

                            for (TituloReceberModel titulo : collTitulosReceber) {

                                if (titulo.isProvisao()) {
                                    if (titulo.getRegistroProvisao() != null
                                            && titulo.getRegistroProvisao().equals(tituloReceberModel.getRegistroProvisao())) {
                                        somaProvisionados += titulo.getValorNominal();
                                        qtdTitulos++;
                                    }
                                }
                            }

                            tituloReceberModel.setValorNominal(somaProvisionados);

                            for (TituloReceberRateioHistoricoModel rateio : tituloReceberModel.getCollHistoricosRateio()) {
                                rateio.setValor(rateio.getValor() * qtdTitulos);
                            }

                            for (TituloReceberRateioCCModel rateioCC : tituloReceberModel.getCollCentroCustosRateio()) {
                                rateioCC.setValor(rateioCC.getValor() * qtdTitulos);
                            }

                            provisionados.put(tituloReceberModel.getRegistroProvisao(), tituloReceberModel.getRegistroProvisao());

                        }

                        ReportLoteExportacao megaContabil = new ReportLoteExportacao();

                        Collection<ReportLoteExportacaoSub> item = new ArrayList<ReportLoteExportacaoSub>();

                        ReportLoteExportacaoSub megaContabilD = new ReportLoteExportacaoSub();
                        ReportLoteExportacaoSub megaContabilC;

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

                                megaContabilC = new ReportLoteExportacaoSub();

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
                        megaContabil.setLote(loteContabilModel.getLote());
                        megaContabil.setData(loteContabilModel.getDataRegistro());

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

                    ReportLoteExportacao megaContabil = new ReportLoteExportacao();

                    Collection<ReportLoteExportacaoSub> item = new ArrayList<ReportLoteExportacaoSub>();

                    ReportLoteExportacaoSub megaContabilC = null;
                    ReportLoteExportacaoSub megaContabilD = null;
                    ReportLoteExportacaoSub megaContabilAC = null;
                    ReportLoteExportacaoSub megaContabilDE = null;

                    for (TituloReceberModel titulo : collTitulosReceber) {

                        if (titulo.getPk().getId().equals(baixa.getTitulo().getPk().getId())
                                && titulo.getPk().getOrganizacao().getId().equals(baixa.getTitulo().getPk().getOrganizacao().getId())) {
                            baixa.setTitulo(titulo);
                            break;
                        }

                    }

                    for (TituloReceberBaixaFormaPagamentoModel formaPagamento : baixa.getFormasPagamento()) {

                        if (formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_ESPECIE)
                                || formaPagamento.getForma().getPk().getId().equals(Constantes.FORMA_PAGAMENTO_CHEQUE)) {

                            megaContabilD = new ReportLoteExportacaoSub();

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

                        megaContabilD = new ReportLoteExportacaoSub();

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

                        megaContabilAC = new ReportLoteExportacaoSub();

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

                        megaContabilDE = new ReportLoteExportacaoSub();

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

                    if (baixa.getTitulo().isProvisao()) {

                        megaContabilC = new ReportLoteExportacaoSub();

                        megaContabilC.setTipoLancamento("C");

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

                        megaContabilC.setValor(baixa.getTitulo().getValorNominal());

                        item.add(megaContabilC);

                    } else {


                        for (TituloReceberRateioHistoricoModel rateio : baixa.getTitulo().getCollHistoricosRateio()) {

                            for (TituloReceberRateioCCModel rateioCC : baixa.getTitulo().getCollCentroCustosRateio()) {

                                megaContabilC = new ReportLoteExportacaoSub();

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

                                megaContabilC.setValor((rateioCC.getValor() / baixa.getTitulo().getValorNominal()) * rateio.getValor());

                                item.add(megaContabilC);

                            }

                        }

                    }

                    megaContabil.setTabela("TRB");
                    megaContabil.setDataLancamento(baixa.getTitulo().getDataPagamento());
                    megaContabil.setIdentificacao(baixa.getTitulo().getNumeroDocumento());

                    megaContabil.setCnpj(organizacaoModel.getCnpj());
                    megaContabil.setRazaoSocial(organizacaoModel.getRazaoSocial());
                    megaContabil.setLote(loteContabilModel.getLote());
                    megaContabil.setData(loteContabilModel.getDataRegistro());

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
                        new RelatorioUtil().visualizar(RelatorioConstantes.RELATORIO_EXPORTACAO_MEGACONTABIL_LOTE, parametros, dadosExportados);
                        break;
                    case 1:
                        new RelatorioUtil().exportarPDF(RelatorioConstantes.RELATORIO_EXPORTACAO_MEGACONTABIL_LOTE, parametros, dadosExportados);
                        break;
                    case 2:
                        new RelatorioUtil().exportarXLS(RelatorioConstantes.RELATORIO_EXPORTACAO_MEGACONTABIL_LOTE, parametros, dadosExportados);
                        break;

                    case 3:

                        //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                        File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.RELATORIO_EXPORTACAO_MEGACONTABIL_LOTE, parametros, dadosExportados);

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
        }
    }//GEN-LAST:event_comboFormatoItemStateChanged

    private void comboLoteContabilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLoteContabilActionPerformed

        if (comboLoteContabil.getSelectedIndex() != 0) {

            try {

                LoteContabilModel lote = new LoteContabilModel();
                lote.setPk(new PKModel());
                lote.getPk().setOrganizacao(organizacaoModel);
                lote.setLote(comboLoteContabil.getSelectedItem().toString());

                lote = loteContabilBO.consultarPorTemplate(lote);

                if (lote != null && lote.getPk() != null && lote.getPk().getId() != null) {

                    if (lote.getStatus().equalsIgnoreCase("removido")) {

                        botaoGerar.setEnabled(false);

                    } else {

                        botaoGerar.setEnabled(true);
                    }

                    if (lote.getUsuario() != null) {
                        UsuarioModel usuarioModel = lote.getUsuario();
                        usuarioModel = usuarioBO.consultarPorTemplate(usuarioModel);
                        labelUsuario.setText(usuarioModel.getNome());
                    }

                    campoLoteContabil.setText(lote.getPk().getId());
                    labelData.setText(PempecParse.dateToString(lote.getDataRegistro()));
                    labelDataUltimaAtualizacao.setText(PempecParse.dateToString(lote.getDataAtualizacao()));
                    long qtdTotal = 0;
                    long qtdCTAC = lote.getQtdContaBancariaCredito();
                    labelCTAC.setText(String.valueOf(qtdCTAC));
                    qtdTotal += qtdCTAC;

                    long qtdCTAD = lote.getQtdContaBancariaDebito();
                    labelCTAD.setText(String.valueOf(qtdCTAD));
                    qtdTotal += qtdCTAD;

                    long qtdCTAT = lote.getQtdContaBancariaTransferencia();
                    labelCTAT.setText(String.valueOf(qtdCTAT));
                    qtdTotal += qtdCTAT;

                    long qtdTC = lote.getQtdTesourariaCredito();
                    labelTC.setText(String.valueOf(qtdTC));
                    qtdTotal += qtdTC;

                    long qtdTD = lote.getQtdTesourariaDebito();
                    labelTD.setText(String.valueOf(qtdTD));
                    qtdTotal += qtdTD;

                    long qtdTP = lote.getQtdTituloPagar();
                    labelTP.setText(String.valueOf(qtdTP));
                    qtdTotal += qtdTP;

                    long qtdTR = lote.getQtdTituloReceber();
                    labelTR.setText(String.valueOf(qtdTR));
                    qtdTotal += qtdTR;

                    long qtdTRB = lote.getQtdTituloReceberBaixa();
                    labelTRB.setText(String.valueOf(qtdTRB));
                    qtdTotal += qtdTRB;

                    long qtdTPB = lote.getQtdTituloPagarBaixa();
                    labelTPB.setText(String.valueOf(qtdTPB));
                    qtdTotal += qtdTPB;

                    labelTotal.setText(String.valueOf(qtdTotal));

                    labelSituacao.setText(lote.getStatus());

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
    }//GEN-LAST:event_comboLoteContabilActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoLoteContabil;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JComboBox comboLoteContabil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel labelCTAC;
    private javax.swing.JLabel labelCTAD;
    private javax.swing.JLabel labelCTAT;
    private javax.swing.JLabel labelData;
    private javax.swing.JLabel labelDataUltimaAtualizacao;
    private javax.swing.JLabel labelSituacao;
    private javax.swing.JLabel labelTC;
    private javax.swing.JLabel labelTD;
    private javax.swing.JLabel labelTP;
    private javax.swing.JLabel labelTPB;
    private javax.swing.JLabel labelTR;
    private javax.swing.JLabel labelTRB;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelUsuario;
    // End of variables declaration//GEN-END:variables
}
