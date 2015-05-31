package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaCreditoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.ContaBancariaTransferenciaBO;
import br.com.pempec.finance.businessObjects.LoteContabilBO;
import br.com.pempec.finance.businessObjects.MegaContabilBO;
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
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.LoteContabilModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TesourariaCreditoModel;
import br.com.pempec.finance.models.TesourariaDebitoModel;
import br.com.pempec.finance.models.TituloPagarBaixaModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.TituloReceberBaixaModel;
import br.com.pempec.finance.models.TituloReceberModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author Equipe Pempec
 */
public class ExcluirExportacaoMegaContabil extends FinanceInternalFrame implements IRepopulador {

    private LoteContabilBO loteContabilBO = new LoteContabilBO();
    private TituloPagarBO tituloPagarBO = new TituloPagarBO();
    private UsuarioBO usuarioBO = new UsuarioBO();
    private TituloPagarBaixaBO tituloPagarBaixaBO = new TituloPagarBaixaBO();
    private TituloReceberBO tituloReceberBO = new TituloReceberBO();
    private TituloReceberBaixaBO tituloReceberBaixaBO = new TituloReceberBaixaBO();
    private TesourariaCreditoBO tesourariaCreditoBO = new TesourariaCreditoBO();
    private TesourariaDebitoBO tesourariaDebitoBO = new TesourariaDebitoBO();
    private ContaBancariaCreditoBO contaBancariaCreditoBO = new ContaBancariaCreditoBO();
    private ContaBancariaDebitoBO contaBancariaDebitoBO = new ContaBancariaDebitoBO();
    private ContaBancariaTransferenciaBO contaBancariaTransferenciaBO = new ContaBancariaTransferenciaBO();
    private MegaContabilBO megaContabilBO = new MegaContabilBO();
    private Collection<TituloPagarModel> collTitulosPagar;
    private Collection<TituloPagarBaixaModel> collBaixasTitulosPagar;
    private Collection<TituloReceberModel> collTitulosReceber;
    private Collection<TituloReceberBaixaModel> collBaixasTitulosReceber;
    private Collection<TesourariaDebitoModel> collTesourariaDebito;
    private Collection<TesourariaCreditoModel> collTesourariaCredito;
    private Collection<ContaBancariaDebitoModel> collContaBancariaDebito;
    private Collection<ContaBancariaCreditoModel> collContaBancariaCredito;
    private Collection<ContaBancariaTransferenciaModel> collContaBancariaTransferencia;
    private long tela = Tela.TELA_SISTEMA_CANCELAR_EXPORTACAO_MEGACONTABIL.getTela();
    private OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

    public ExcluirExportacaoMegaContabil() throws SystemException {

        initComponents();

        campoLoteContabil.setVisible(false);


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        campoLoteContabil = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        comboLoteContabil = new javax.swing.JComboBox();
        labelData = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        labelTP = new javax.swing.JLabel();
        labelTRB = new javax.swing.JLabel();
        labelTPB = new javax.swing.JLabel();
        labelTR = new javax.swing.JLabel();
        labelTC = new javax.swing.JLabel();
        labelTD = new javax.swing.JLabel();
        labelCTAC = new javax.swing.JLabel();
        labelCTAD = new javax.swing.JLabel();
        labelCTAT = new javax.swing.JLabel();
        labelDataUltimaAtualizacao = new javax.swing.JLabel();
        labelSituacao = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoRemover = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Remove Exportação");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        campoLoteContabil.setEditable(false);

        jLabel1.setText("Número do Lote");

        comboLoteContabil.setToolTipText("");
        comboLoteContabil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboLoteContabilActionPerformed(evt);
            }
        });

        labelData.setFont(new java.awt.Font("Arial", 0, 10));
        labelData.setForeground(new java.awt.Color(0, 102, 102));
        labelData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelData.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Data Exportação"));

        labelUsuario.setFont(new java.awt.Font("Arial", 0, 10));
        labelUsuario.setForeground(new java.awt.Color(0, 102, 102));
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Usuário"));

        labelTotal.setForeground(new java.awt.Color(51, 51, 255));
        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Total Documentos"));

        labelTP.setFont(new java.awt.Font("Arial", 0, 10));
        labelTP.setForeground(new java.awt.Color(102, 0, 102));
        labelTP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Tit. Pagar"));

        labelTRB.setFont(new java.awt.Font("Arial", 0, 10));
        labelTRB.setForeground(new java.awt.Color(102, 0, 102));
        labelTRB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTRB.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Recebidos"));

        labelTPB.setFont(new java.awt.Font("Arial", 0, 10));
        labelTPB.setForeground(new java.awt.Color(102, 0, 102));
        labelTPB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTPB.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd  Pagos"));

        labelTR.setFont(new java.awt.Font("Arial", 0, 10));
        labelTR.setForeground(new java.awt.Color(102, 0, 102));
        labelTR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTR.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Tit. Receber"));

        labelTC.setFont(new java.awt.Font("Arial", 0, 10));
        labelTC.setForeground(new java.awt.Color(102, 0, 102));
        labelTC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Tes. Créd."));

        labelTD.setFont(new java.awt.Font("Arial", 0, 10));
        labelTD.setForeground(new java.awt.Color(102, 0, 102));
        labelTD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Qtd Tes. Déb."));

        labelCTAC.setFont(new java.awt.Font("Arial", 0, 10));
        labelCTAC.setForeground(new java.awt.Color(102, 0, 102));
        labelCTAC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCTAC.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Cta. Bco. Créd."));

        labelCTAD.setFont(new java.awt.Font("Arial", 0, 10));
        labelCTAD.setForeground(new java.awt.Color(102, 0, 102));
        labelCTAD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCTAD.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Cta. Bco. Déb."));

        labelCTAT.setFont(new java.awt.Font("Arial", 0, 10));
        labelCTAT.setForeground(new java.awt.Color(102, 0, 102));
        labelCTAT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelCTAT.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Transfer. Cta."));

        labelDataUltimaAtualizacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelDataUltimaAtualizacao.setForeground(new java.awt.Color(0, 102, 102));
        labelDataUltimaAtualizacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataUltimaAtualizacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Data Ult. Atualização"));

        labelSituacao.setFont(new java.awt.Font("Arial", 0, 10));
        labelSituacao.setForeground(new java.awt.Color(0, 102, 102));
        labelSituacao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSituacao.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 0)), "Situação"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
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
                            .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(comboLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(campoLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(280, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelData, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelDataUltimaAtualizacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelSituacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
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
                .addGap(18, 18, 18)
                .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 255), 1, true));

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

        botaoRemover.setMnemonic('L');
        botaoRemover.setText("Remover");
        botaoRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoRemoverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(161, 161, 161)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Remove Exportação";
    }

    public void repopularCombos() {
        try {

            Collection<LoteContabilModel> lLoteContabil = new ArrayList<LoteContabilModel>();

            LoteContabilModel loteContabilModel = new LoteContabilModel();

            loteContabilModel.setLote(" -> Selecione <- ");

            lLoteContabil.add(loteContabilModel);

            lLoteContabil.addAll(loteContabilBO.obterTodos(organizacaoModel));

            comboLoteContabil.setModel(new javax.swing.DefaultComboBoxModel(lLoteContabil.toArray()));

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
        botaoRemover.setEnabled(false);
    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {


        if (comboLoteContabil.getSelectedIndex() == 0) {
            comboLoteContabil.requestFocus();
            return false;
        }

        return true;
    }

    private void botaoRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoRemoverActionPerformed

        try {

            long action = Action.OUTROS.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (comboLoteContabil.getSelectedIndex() != 0) {

                LoteContabilModel loteContabilModel = new LoteContabilModel();

                loteContabilModel.setPk(new PKModel());
                loteContabilModel.getPk().setOrganizacao(Controller.getOrganizacao());
                loteContabilModel.setLote(comboLoteContabil.getSelectedItem().toString());

                loteContabilModel = loteContabilBO.consultarPorTemplate(loteContabilModel);

                // pega as colecoes e manda p o MegaContabil fazer as atualizacoes

                if (loteContabilModel.getQtdTituloPagar() > 0) {
                    collTitulosPagar = tituloPagarBO.obterTitulosExportados(loteContabilModel);
                }

                if (loteContabilModel.getQtdTituloPagarBaixa() > 0) {
                    collBaixasTitulosPagar = tituloPagarBaixaBO.obterTitulosExportados(loteContabilModel);
                }

                if (loteContabilModel.getQtdTituloReceber() > 0) {
                    collTitulosReceber = tituloReceberBO.obterTitulosExportados(loteContabilModel);
                }

                if (loteContabilModel.getQtdTituloReceberBaixa() > 0) {
                    collBaixasTitulosReceber = tituloReceberBaixaBO.obterTitulosExportados(loteContabilModel);
                }

                if (loteContabilModel.getQtdTesourariaCredito() > 0) {
                    collTesourariaCredito = tesourariaCreditoBO.obterTitulosExportados(loteContabilModel);
                }

                if (loteContabilModel.getQtdTesourariaDebito() > 0) {
                    collTesourariaDebito = tesourariaDebitoBO.obterTitulosExportados(loteContabilModel);
                }

                if (loteContabilModel.getQtdContaBancariaCredito() > 0) {
                    collContaBancariaCredito = contaBancariaCreditoBO.obterTitulosExportados(loteContabilModel);
                }

                if (loteContabilModel.getQtdContaBancariaDebito() > 0) {
                    collContaBancariaDebito = contaBancariaDebitoBO.obterTitulosExportados(loteContabilModel);
                }

                if (loteContabilModel.getQtdContaBancariaTransferencia() > 0) {
                    collContaBancariaTransferencia = contaBancariaTransferenciaBO.obterTitulosExportados(loteContabilModel);
                }

                loteContabilModel.setMovimentoDiarioModel(registroMovimento("EXC LOTE C", loteContabilModel.getLote(), "Remocao do lote  " + loteContabilModel.getLote(), 0.d, "Removido"));

                megaContabilBO.removerLote(collTitulosPagar, collBaixasTitulosPagar, collTitulosReceber, collBaixasTitulosReceber,
                        collTesourariaDebito, collTesourariaCredito, collContaBancariaDebito, collContaBancariaCredito, collContaBancariaTransferencia, loteContabilModel);

                exibeMensagemAviso("Lote: " + loteContabilModel.getLote() + " removido com Sucesso!", null);

                this.botaoLimparActionPerformed(evt);

            } else {

                exibeMensagemAviso("Selecione o Lote!", null);
                return;
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
}//GEN-LAST:event_botaoRemoverActionPerformed

    private void comboLoteContabilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboLoteContabilActionPerformed

        if (comboLoteContabil.getSelectedIndex() != 0) {

            try {

                LoteContabilModel lote = new LoteContabilModel();
                lote.setPk(new PKModel());
                lote.getPk().setOrganizacao(organizacaoModel);
                lote.setLote(comboLoteContabil.getSelectedItem().toString());

                lote = loteContabilBO.consultarPorTemplate(lote);

                if (lote != null && lote.getPk().getId() != null) {

                    if (lote.getStatus().equalsIgnoreCase("removido")) {
                        botaoRemover.setEnabled(false);
                    } else {
                        botaoRemover.setEnabled(true);
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
    protected javax.swing.JButton botaoLimpar;
    protected javax.swing.JButton botaoRemover;
    private javax.swing.JTextField campoLoteContabil;
    private javax.swing.JComboBox comboLoteContabil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
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
