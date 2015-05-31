package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaChequeBO;
import br.com.pempec.finance.businessObjects.ContaBancariaDebitoBO;
import br.com.pempec.finance.businessObjects.HistoricoBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.businessObjects.TipoStatusBO;
import br.com.pempec.finance.businessObjects.TituloPagarBaixaChequeBO;
import br.com.pempec.finance.businessObjects.UsuarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.ContaBancariaChequeModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.HistoricoModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.TipoStatusModel;
import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.ChequeTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class CompensadorCheque extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaChequeBO contaBancariaChequeBO = new ContaBancariaChequeBO();
    private TituloPagarBaixaChequeBO tituloPagoChequeBO = new TituloPagarBaixaChequeBO();
    private ContaBancariaDebitoBO contaBancariaDebitoBO = new ContaBancariaDebitoBO();
    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private TipoStatusBO tipoStatusBO = new TipoStatusBO();
    private long tela = Tela.TELA_CONCILIACAO_COMPENSAR_CHEQUE_EMITIDO.getTela();

    private String NameObject() {
        return (String) "Compensação de Cheques";
    }

    public void repopularCombos() {

        try {


            // responsavel
            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboResponsavelCompensa.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            //conta Bancaria

            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta(" -> Selecione <- ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancariaCompensa.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            comboContaBancariaEstorno.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            //Adicionando o status padrão - Novo ou Aberto.
            TipoStatusModel tipoStatusModel = new TipoStatusModel();

            tipoStatusModel.setPk(new PKModel());

            tipoStatusModel.getPk().setOrganizacao(organizacaoModel);

            tipoStatusModel.getPk().setId(Constantes.STATUS_CHEQUE_EMITIDO);


            tipoStatusModel = tipoStatusBO.consultarPorPk(tipoStatusModel);

            labelStatus.setText(tipoStatusModel.getDescricao());


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

    public CompensadorCheque() throws SystemException {

        this.setLocation(250, 50);
        initComponents();
        //Escodendo os campos
        campoCodigoCompensa.setVisible(false);
        campoCodigoChequeEstorno.setVisible(false);

        labelDataGeracao.setVisible(false);
        labelEmissao.setVisible(false);
        labelVencimento.setVisible(false);
        labelPortador.setVisible(false);
        labelStatus.setVisible(false);
        labelValorCheque.setVisible(false);
        labelDataVencimento.setVisible(false);
        jFTDataChequeCompensado.setVisible(false);
        jTObservacaoCompensa.setEnabled(false);
        comboChequeCompensa.setEnabled(false);
        comboResponsavelCompensa.setEnabled(false);

        //formatar datas                
        jFTDataChequeCompensado.setDate(Controller.getDataServidorBD());
        //Aplicando tamanho maximo de caracteres do campo.
        jTObservacaoCompensa.setDocument(new Documento(60));

        botaoCompensar.setEnabled(false);
        botaoEstorno.setEnabled(false);



    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoCompensar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoEstorno = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaCompensarCheque = new javax.swing.JPanel();
        labelCheque = new javax.swing.JLabel();
        campoCodigoCompensa = new javax.swing.JTextField();
        labelValorCheque = new javax.swing.JLabel();
        labelObservacao = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboContaBancariaCompensa = new javax.swing.JComboBox();
        comboChequeCompensa = new javax.swing.JComboBox();
        labelDataGeracao = new javax.swing.JLabel();
        labelEmissao = new javax.swing.JLabel();
        labelStatus = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        comboResponsavelCompensa = new javax.swing.JComboBox();
        labelPortador = new javax.swing.JLabel();
        labelDataVencimento = new javax.swing.JLabel();
        labelVencimento = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTObservacaoCompensa = new org.jdesktop.swingx.JXEditorPane();
        jFTDataChequeCompensado = new org.jdesktop.swingx.JXDatePicker();
        jPanel1 = new javax.swing.JPanel();
        abaCompensarCheque1 = new javax.swing.JPanel();
        labelCheque1 = new javax.swing.JLabel();
        campoCodigoChequeEstorno = new javax.swing.JTextField();
        labelValorChequeEstorno = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelDataGeracaoEstorno = new javax.swing.JLabel();
        labelEmissaoEstorno = new javax.swing.JLabel();
        labelStatusEstorno = new javax.swing.JLabel();
        labelPortadorEstorno = new javax.swing.JLabel();
        labelCompensadoEstorno = new javax.swing.JLabel();
        labelResponsavelEstorno = new javax.swing.JLabel();
        labelDataRegistroEstorno = new javax.swing.JLabel();
        labelTipoOperacaoEstorno = new javax.swing.JLabel();
        labelUsuarioEstorno = new javax.swing.JLabel();
        labelTituloPagarEstorno = new javax.swing.JLabel();
        comboContaBancariaEstorno = new javax.swing.JComboBox();
        comboChequeEstorno = new javax.swing.JComboBox();
        labelDescricaoEstorno = new javax.swing.JLabel();
        labelObservacaoEstorno = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Compensação de Cheques");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoCompensar.setMnemonic('I');
        botaoCompensar.setText("Compensar");
        botaoCompensar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoCompensarActionPerformed(evt);
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

        botaoEstorno.setMnemonic('E');
        botaoEstorno.setText("Estornar");
        botaoEstorno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEstornoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoCompensar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                        .addComponent(botaoCompensar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)), NameObject()));

        abaCompensarCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102))));
        abaCompensarCheque.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                abaCompensarChequeComponentShown(evt);
            }
        });

        labelCheque.setText("Cheque");

        campoCodigoCompensa.setEditable(false);

        labelValorCheque.setBackground(new java.awt.Color(222, 218, 210));
        labelValorCheque.setFont(new java.awt.Font("Arial", 1, 10));
        labelValorCheque.setForeground(new java.awt.Color(0, 153, 153));
        labelValorCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelObservacao.setText("Observação");

        jLabel10.setText("Conta Bancária");

        comboContaBancariaCompensa.setFont(new java.awt.Font("Arial", 0, 10));
        comboContaBancariaCompensa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaBancariaCompensaItemStateChanged(evt);
            }
        });

        comboChequeCompensa.setFont(new java.awt.Font("Arial", 0, 10));
        comboChequeCompensa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboChequeCompensaActionPerformed(evt);
            }
        });

        labelDataGeracao.setBackground(new java.awt.Color(222, 218, 210));
        labelDataGeracao.setFont(new java.awt.Font("Arial", 1, 10));
        labelDataGeracao.setForeground(new java.awt.Color(0, 153, 153));
        labelDataGeracao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gerado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelEmissao.setBackground(new java.awt.Color(222, 218, 210));
        labelEmissao.setFont(new java.awt.Font("Arial", 1, 10));
        labelEmissao.setForeground(new java.awt.Color(0, 153, 153));
        labelEmissao.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Emissão", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelStatus.setBackground(new java.awt.Color(222, 218, 210));
        labelStatus.setFont(new java.awt.Font("Arial", 1, 10));
        labelStatus.setForeground(new java.awt.Color(0, 153, 153));
        labelStatus.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelStatus.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel7.setText("Responsável");

        comboResponsavelCompensa.setFont(new java.awt.Font("Arial", 0, 10));

        labelPortador.setBackground(new java.awt.Color(222, 218, 210));
        labelPortador.setFont(new java.awt.Font("Arial", 1, 10));
        labelPortador.setForeground(new java.awt.Color(0, 153, 153));
        labelPortador.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Portador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelDataVencimento.setText("Data");

        labelVencimento.setBackground(new java.awt.Color(222, 218, 210));
        labelVencimento.setFont(new java.awt.Font("Arial", 1, 10));
        labelVencimento.setForeground(new java.awt.Color(0, 153, 153));
        labelVencimento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vencimento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jTObservacaoCompensa.setFont(new java.awt.Font("Arial", 0, 10));
        jScrollPane1.setViewportView(jTObservacaoCompensa);

        javax.swing.GroupLayout abaCompensarChequeLayout = new javax.swing.GroupLayout(abaCompensarCheque);
        abaCompensarCheque.setLayout(abaCompensarChequeLayout);
        abaCompensarChequeLayout.setHorizontalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(comboContaBancariaCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGap(172, 172, 172)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboChequeCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCheque)))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addComponent(labelObservacao)
                        .addGap(325, 325, 325))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarChequeLayout.createSequentialGroup()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelPortador, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaCompensarChequeLayout.createSequentialGroup()
                                .addGap(172, 172, 172)
                                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarChequeLayout.createSequentialGroup()
                                        .addComponent(jFTDataChequeCompensado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoCodigoCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                        .addComponent(labelDataVencimento)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaCompensarChequeLayout.createSequentialGroup()
                                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7)
                                    .addComponent(comboResponsavelCompensa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(166, 166, 166))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                    .addComponent(labelDataGeracao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                    .addComponent(labelEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(labelValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        abaCompensarChequeLayout.setVerticalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataGeracao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboContaBancariaCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                            .addComponent(labelCheque)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(comboChequeCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboResponsavelCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                .addComponent(labelDataVencimento)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(campoCodigoCompensa, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFTDataChequeCompensado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(13, 13, 13))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelValorCheque, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEmissao, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelPortador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelObservacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        jTabbedPane1.addTab("Compensação de Cheque", abaCompensarCheque);
        abaCompensarCheque.getAccessibleContext().setAccessibleName("abaCompensarCheque");

        abaCompensarCheque1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102))));
        abaCompensarCheque1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                abaEstornoComponentShown(evt);
            }
        });

        labelCheque1.setText("Cheque");

        campoCodigoChequeEstorno.setEditable(false);

        labelValorChequeEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelValorChequeEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelValorChequeEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelValorChequeEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        jLabel11.setText("Conta Bancária");

        labelDataGeracaoEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelDataGeracaoEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelDataGeracaoEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelDataGeracaoEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Gerado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelEmissaoEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelEmissaoEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelEmissaoEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelEmissaoEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Emissão", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelStatusEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelStatusEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelStatusEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelStatusEstorno.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        labelStatusEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelPortadorEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelPortadorEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelPortadorEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelPortadorEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Portador", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelCompensadoEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelCompensadoEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelCompensadoEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelCompensadoEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Compensado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelResponsavelEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelResponsavelEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelResponsavelEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelResponsavelEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Responsável", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelDataRegistroEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelDataRegistroEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelDataRegistroEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelDataRegistroEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelTipoOperacaoEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelTipoOperacaoEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelTipoOperacaoEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelTipoOperacaoEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tipo OPeração", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelUsuarioEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelUsuarioEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelUsuarioEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelUsuarioEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelTituloPagarEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelTituloPagarEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelTituloPagarEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelTituloPagarEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Número Documento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        comboContaBancariaEstorno.setFont(new java.awt.Font("Arial", 0, 10));
        comboContaBancariaEstorno.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaBancariaEstornoItemStateChanged(evt);
            }
        });

        comboChequeEstorno.setFont(new java.awt.Font("Arial", 0, 10));
        comboChequeEstorno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboChequeEstornoActionPerformed(evt);
            }
        });

        labelDescricaoEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelDescricaoEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelDescricaoEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelDescricaoEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descrição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        labelObservacaoEstorno.setBackground(new java.awt.Color(222, 218, 210));
        labelObservacaoEstorno.setFont(new java.awt.Font("Arial", 1, 10));
        labelObservacaoEstorno.setForeground(new java.awt.Color(0, 153, 153));
        labelObservacaoEstorno.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Observação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(153, 153, 153))); // NOI18N

        javax.swing.GroupLayout abaCompensarCheque1Layout = new javax.swing.GroupLayout(abaCompensarCheque1);
        abaCompensarCheque1.setLayout(abaCompensarCheque1Layout);
        abaCompensarCheque1Layout.setHorizontalGroup(
            abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboContaBancariaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))
                                .addGap(28, 28, 28)
                                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCheque1)
                                    .addComponent(comboChequeEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelPortadorEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                        .addComponent(labelTipoOperacaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(labelTituloPagarEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(labelDescricaoEstorno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelObservacaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                    .addComponent(labelCompensadoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelDataRegistroEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                                    .addComponent(labelDataGeracaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelStatusEstorno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarCheque1Layout.createSequentialGroup()
                                    .addComponent(labelEmissaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(labelValorChequeEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(labelResponsavelEstorno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelUsuarioEstorno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(campoCodigoChequeEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        abaCompensarCheque1Layout.setVerticalGroup(
            abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, abaCompensarCheque1Layout.createSequentialGroup()
                .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelDataGeracaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelStatusEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelValorChequeEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEmissaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelCompensadoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataRegistroEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(labelResponsavelEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelUsuarioEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaCompensarCheque1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(labelCheque1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboContaBancariaEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboChequeEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(labelPortadorEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(abaCompensarCheque1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelTipoOperacaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTituloPagarEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(labelDescricaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelObservacaoEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoCodigoChequeEstorno, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(abaCompensarCheque1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 286, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(abaCompensarCheque1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        abaCompensarCheque1.getAccessibleContext().setAccessibleName("abaEstorno");

        jTabbedPane1.addTab("Estorno de Compensação", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 615, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("Estorno de Compensação");
        jTabbedPane1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed

        comboContaBancariaCompensa.setSelectedIndex(0);

        if (supportChequeCompensa != null && supportChequeCompensa.getItemList() != null && supportChequeCompensa.getComboBox() != null) {
            supportChequeCompensa.uninstall();
        }

        comboResponsavelCompensa.setSelectedIndex(0);
        campoCodigoCompensa.setText("");
        //Campo com mascara setar null no value.
        labelDataGeracao.setText("");
        labelVencimento.setText("");
        jTObservacaoCompensa.setText("");
        labelValorCheque.setText("");
        labelStatus.setText("");
        labelPortador.setText("");
        jFTDataChequeCompensado.setDate(Controller.getDataServidorBD());
        //escondendo
        labelDataGeracao.setVisible(false);
        labelEmissao.setVisible(false);
        labelVencimento.setVisible(false);
        labelPortador.setVisible(false);
        labelStatus.setVisible(false);
        labelValorCheque.setVisible(false);
        labelDataVencimento.setVisible(false);
        jFTDataChequeCompensado.setVisible(false);
        jTObservacaoCompensa.setEnabled(false);
        botaoCompensar.setEnabled(false);
        botaoEstorno.setEnabled(false);

        //limpar tela de estorno
        comboContaBancariaEstorno.setSelectedIndex(0);

        if (supportChequeEstorno != null && supportChequeEstorno.getItemList() != null && supportChequeEstorno.getComboBox() != null) {
            supportChequeEstorno.uninstall();
        }
        labelEmissao.setText("");
        labelPortadorEstorno.setText("");
        labelDataGeracaoEstorno.setText("");
        labelEmissaoEstorno.setText("");
        labelCompensadoEstorno.setText("");
        labelDataRegistroEstorno.setText("");
        labelStatusEstorno.setText("");
        labelResponsavelEstorno.setText("");
        labelValorChequeEstorno.setText("");
        labelTipoOperacaoEstorno.setText("");
        labelUsuarioEstorno.setText("");
        labelTituloPagarEstorno.setText("");
        labelDescricaoEstorno.setText("");
        labelObservacaoEstorno.setText("");

    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoEstornoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEstornoActionPerformed

        String valorCombo = null;

        String numDocumento = "0";

        TipoStatusModel tipoStatusModel = new TipoStatusModel();
        tipoStatusModel.setPk(new PKModel());
        tipoStatusModel.getPk().setOrganizacao(organizacaoModel);
        tipoStatusModel.getPk().setId(Constantes.STATUS_CHEQUE_EMITIDO);

        if (comboChequeEstorno.getSelectedItem() != null) {
            valorCombo = comboChequeEstorno.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.OUTROS.getAction();

                if (!Controller.checarPermissao(tela, action)) {
                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;
                }



                ContaBancariaDebitoModel tab = new ContaBancariaDebitoModel();

                ContaBancariaChequeModel cheque = new ContaBancariaChequeModel();
                cheque.setPk(new PKModel());
                cheque.getPk().setOrganizacao(Controller.getOrganizacao());
                cheque.getPk().setId(((ContaBancariaChequeModel) comboChequeEstorno.getSelectedItem()).getPk().getId());
                cheque.setNumeroCheque(valorCombo);

                cheque = contaBancariaChequeBO.consultarPorTemplate(cheque);

                Collection<TituloPagarBaixaChequeModel> collChq = new TituloPagarBaixaChequeBO().obterPorCheque(cheque);

                if (collChq != null && !collChq.isEmpty()) {
                    exibeMensagemAviso("Não é possivel estornar um cheque com título associado. \nUtilize a opção de exclusão da Baixa.", "Validação");
                    return;
                }

                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(Controller.getOrganizacao());
                tab.getPk().setId(campoCodigoChequeEstorno.getText());
                tab.setContaBancariaCheque(cheque);

                tab = contaBancariaDebitoBO.obterPorCheque(tab);

                if (tab.getTituloPagar() != null && !tab.getTituloPagar().getPk().getId().isEmpty()) {
                    numDocumento = tab.getTituloPagar().getNumeroDocumento();
                }

                cheque.setDataCompensado(null);
                cheque.setStatus(tipoStatusModel);

                if (tab.getObservacao() != null && tab.getObservacao().length() < 40) {
                    cheque.setObservacao(tab.getContaBancariaCheque().getObservacao() + " - CH EST COMP - ");
                } else {
                    cheque.setObservacao(tab.getContaBancariaCheque().getObservacao());
                }
                cheque.setDataEstornado(Controller.getDataServidorBD());
                cheque.setMovimentoDiarioModel(registroMovimento("Estornar CH", numDocumento, "CH " + cheque.getNumeroCheque() + " no valor de R$ " + cheque.getValor() + "  utilizado no pagto do título : " + numDocumento, cheque.getValor(), "Alterado"));
                cheque.getMovimentoDiarioModel().setObservacao("Conta " + cheque.getContaBancaria());

                tab.setContaBancariaCheque(cheque);

                contaBancariaDebitoBO.excluir(tab);

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
            exibeMensagemAviso("O cheque é obrigatório.", null);
        }


}//GEN-LAST:event_botaoEstornoActionPerformed

    private Boolean validaCampos() {

        if (comboResponsavelCompensa.getSelectedIndex() == 0) {
            comboResponsavelCompensa.requestFocus();
            return false;
        }

        if (jFTDataChequeCompensado.getDate() == null) {
            jFTDataChequeCompensado.requestFocus();
            return false;
        }

        if (jFTDataChequeCompensado.getDate() != null) {
            Date dataInformada = jFTDataChequeCompensado.getDate();
            if (dataInformada.after(Controller.getDataServidorBD())) {
                exibeMensagemAviso("A Data de Compensação não pode ser superior a HOJE.!", null);
                jFTDataChequeCompensado.requestFocus();
                return false;
            }

        }

        if (jFTDataChequeCompensado.getDate() != null && !(PempecUtil.isDiaUtil(PempecParse.dateToDate(jFTDataChequeCompensado.getDate())))) {

            exibeMensagemAviso("A data de compensação do cheque só pode ser um dia útil.", null);

            return false;
        }




        return true;
    }

private void comboContaBancariaCompensaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaBancariaCompensaItemStateChanged

    botaoCompensar.setEnabled(true);
    botaoEstorno.setEnabled(false);

    if (comboContaBancariaCompensa.getSelectedItem() != null
            && ((ContaBancariaModel) comboContaBancariaCompensa.getSelectedItem()).getPk() != null) {

        try {


            comboChequeCompensa.setEnabled(true);

            TipoStatusModel status = new TipoStatusModel();
            status.setPk(new PKModel());
            status.getPk().setOrganizacao(organizacaoModel);
            status.getPk().setId(Constantes.STATUS_CHEQUE_EMITIDO);

            Collection<ContaBancariaChequeModel> lContaBancariaCheque = contaBancariaChequeBO.obterPorContaBancariaStatus((ContaBancariaModel) comboContaBancariaCompensa.getSelectedItem(), status);

            final EventList<ContaBancariaChequeModel> lRegistrosCheques = GlazedLists.eventList(lContaBancariaCheque);
            if (supportChequeCompensa != null && supportChequeCompensa.getItemList() != null && supportChequeCompensa.getComboBox() != null) {
                supportChequeCompensa.uninstall();
            }
            supportChequeCompensa = AutoCompleteSupport.install(comboChequeCompensa, lRegistrosCheques, new ChequeTextFilterator());
            supportChequeCompensa.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportChequeCompensa.setStrict(false);


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

        if (supportChequeCompensa != null && supportChequeCompensa.getItemList() != null && supportChequeCompensa.getComboBox() != null) {
            supportChequeCompensa.uninstall();
        }

    }

}//GEN-LAST:event_comboContaBancariaCompensaItemStateChanged

private void botaoCompensarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoCompensarActionPerformed


    if (comboChequeCompensa.getSelectedItem() != null && ((ContaBancariaChequeModel) comboChequeCompensa.getSelectedItem()).getPk() != null) {

        ContaBancariaChequeModel chequeEmitido = ((ContaBancariaChequeModel) comboChequeCompensa.getSelectedItem());

        ContaBancariaDebitoModel chequeCompensado = new ContaBancariaDebitoModel();

        UsuarioModel usuarioModel = Controller.getUsuarioLogado();

        TipoStatusModel status = new TipoStatusModel();
        status.setPk(new PKModel());
        status.getPk().setOrganizacao(organizacaoModel);
        status.getPk().setId(Constantes.STATUS_CHEQUE_COMPENSADO);

        try {

            long action = Action.OUTROS.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCampos()) {

                chequeEmitido.setContaBancaria(((ContaBancariaModel) comboContaBancariaCompensa.getSelectedItem()));

                chequeEmitido.setPk(new PKModel());
                chequeEmitido.getPk().setOrganizacao(organizacaoModel);
                chequeEmitido.getPk().setId(((ContaBancariaChequeModel) comboChequeCompensa.getSelectedItem()).getPk().getId());
                chequeEmitido = contaBancariaChequeBO.consultarPorTemplate(chequeEmitido);

                chequeEmitido.setObservacao(jTObservacaoCompensa.getText());

                if (chequeEmitido.getQtdImpressao() == 0) {

                    chequeEmitido.setQtdImpressao(1);

                    chequeEmitido.setObservacao(chequeEmitido.getObservacao() + "\n" + "Compensou ch sem imprimir " + Controller.getUsuarioLogado().getNome());
                }


                List<TituloPagarBaixaChequeModel> listaTitulos = new ArrayList<TituloPagarBaixaChequeModel>();

                listaTitulos = tituloPagoChequeBO.obterPorCheque(chequeEmitido);
                //nao retorna o titulo pago com o cheque

                if (listaTitulos != null && listaTitulos.size() > 0) {

                    TipoOperacaoBancariaModel tipoOperacao = new TipoOperacaoBancariaModel();
                    tipoOperacao.setPk(new PKModel());
                    tipoOperacao.getPk().setOrganizacao(organizacaoModel);
                    tipoOperacao.getPk().setId(listaTitulos.get(0).getTipoOperacaoBancaria().getPk().getId());
                    tipoOperacao = new TipoOperacaoBancariaBO().consultarPorTemplate(tipoOperacao);

                    chequeCompensado.setContaBancariaCheque(chequeEmitido);
                    chequeCompensado.setPk(new PKModel());
                    chequeCompensado.getPk().setOrganizacao(organizacaoModel);
                    chequeCompensado.getPk().setId(PempecKeyGenerator.generateKey());

                    chequeCompensado.setTipoOperacaoBancaria(tipoOperacao);

                    if (listaTitulos.size() >= 1) {

                        TituloPagarModel tituloPago = (TituloPagarModel) listaTitulos.get(0).getTituloPagarBaixa().getTitulo();

                        chequeCompensado.setTituloPagar(tituloPago);

                        chequeCompensado.setDescricao("CH Nº " + chequeEmitido.getNumeroCheque() + " " + tituloPago.getDescricao());

                    } else {
                        chequeCompensado.setTituloPagar(null);
                    }

                    chequeCompensado.setContaBancaria(((ContaBancariaModel) comboContaBancariaCompensa.getSelectedItem()));
                    chequeCompensado.setDataMovimento(jFTDataChequeCompensado.getDate());
                    chequeCompensado.setDataRegistro(Controller.getDataServidorBD());

                    chequeCompensado.setTipoLancamento("D");
                    chequeCompensado.setValor(PempecParse.stringToDouble(labelValorCheque.getText()));
                    chequeCompensado.setUsuario(usuarioModel);
                    chequeCompensado.setIdentificacao(PempecKeyGenerator.generateNumeroDocumentoContaBancariaDebito());



                    if (comboResponsavelCompensa.getSelectedItem() != null && ((FuncionarioModel) comboResponsavelCompensa.getSelectedItem()).getPk() != null) {

                        chequeCompensado.setResponsavel(new FuncionarioModel());
                        chequeCompensado.getResponsavel().setPk(new PKModel());
                        chequeCompensado.getResponsavel().getPk().setId(((FuncionarioModel) comboResponsavelCompensa.getSelectedItem()).getPk().getId());

                    }


                    chequeEmitido.setDataCompensado(jFTDataChequeCompensado.getDate());
                    chequeEmitido.setStatus(status);
                    chequeCompensado.setMovimentoDiarioModel(registroMovimento("Compensar Cheque", "Cheque " + chequeCompensado.getContaBancariaCheque().getNumeroCheque(), "Compensação de Cheque ", PempecParse.stringToDouble(labelValorCheque.getText()), "Compensado"));

                    contaBancariaDebitoBO.inserir(chequeCompensado);

                }//  fim do if da lista Titulos
                else {
                    exibeMensagemAviso("Impossível compensar o cheque. Não existe titulo agregado!", null);
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

    }

}//GEN-LAST:event_botaoCompensarActionPerformed

private void comboContaBancariaEstornoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaBancariaEstornoItemStateChanged

    botaoCompensar.setEnabled(false);
    botaoEstorno.setEnabled(true);

    if (comboContaBancariaEstorno.getSelectedItem() != null
            && ((ContaBancariaModel) comboContaBancariaEstorno.getSelectedItem()).getPk() != null) {

        try {


            comboChequeEstorno.setEnabled(true);

            TipoStatusModel status = new TipoStatusModel();
            status.setPk(new PKModel());
            status.getPk().setOrganizacao(organizacaoModel);
            status.getPk().setId(Constantes.STATUS_CHEQUE_COMPENSADO);

            Collection<ContaBancariaChequeModel> lContaBancariaCheque = contaBancariaChequeBO.obterPorContaBancariaStatus((ContaBancariaModel) comboContaBancariaEstorno.getSelectedItem(), status);

            final EventList<ContaBancariaChequeModel> lRegistrosCheques = GlazedLists.eventList(lContaBancariaCheque);
            if (supportChequeEstorno != null && supportChequeEstorno.getItemList() != null && supportChequeEstorno.getComboBox() != null) {
                supportChequeEstorno.uninstall();
            }
            supportChequeEstorno = AutoCompleteSupport.install(comboChequeEstorno, lRegistrosCheques, new ChequeTextFilterator());
            supportChequeEstorno.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportChequeEstorno.setStrict(false);

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

        if (supportChequeEstorno != null && supportChequeEstorno.getItemList() != null && supportChequeEstorno.getComboBox() != null) {
            supportChequeEstorno.uninstall();
        }

    }
}//GEN-LAST:event_comboContaBancariaEstornoItemStateChanged

private void abaCompensarChequeComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_abaCompensarChequeComponentShown
    botaoCompensar.setEnabled(true);
    botaoEstorno.setEnabled(false);
    comboChequeEstorno.setEnabled(false);
    comboChequeCompensa.setEnabled(true);
}//GEN-LAST:event_abaCompensarChequeComponentShown

private void abaEstornoComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_abaEstornoComponentShown
    botaoCompensar.setEnabled(false);
    botaoEstorno.setEnabled(true);
    comboChequeEstorno.setEnabled(true);
    comboChequeCompensa.setEnabled(false);
}//GEN-LAST:event_abaEstornoComponentShown

private void comboChequeEstornoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboChequeEstornoActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboChequeEstorno.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        botaoCompensar.setEnabled(false);
        botaoEstorno.setEnabled(true);


        String numeroCheque = ((ContaBancariaChequeModel) comboChequeEstorno.getSelectedItem()).getNumeroCheque();

        try {

            ContaBancariaChequeModel chequeModel = new ContaBancariaChequeModel();

            chequeModel.setPk(new PKModel());
            chequeModel.getPk().setOrganizacao(organizacaoModel);
            chequeModel.setNumeroCheque(numeroCheque);

            chequeModel = contaBancariaChequeBO.consultarPorTemplate(chequeModel);

            campoCodigoChequeEstorno.setText(chequeModel.getPk().getId());
            labelEmissao.setText(PempecParse.dateToString(chequeModel.getDataEmissao()));
            labelPortadorEstorno.setText(chequeModel.getPortador());
            labelDataGeracaoEstorno.setText(PempecParse.dateToString(chequeModel.getDataRegistro()));
            labelEmissaoEstorno.setText(PempecParse.dateToString(chequeModel.getDataEmissao()));
            labelCompensadoEstorno.setText(PempecParse.dateToString(chequeModel.getDataCompensado()));
            labelDataRegistroEstorno.setText(PempecParse.dateToString(chequeModel.getDataRegistro()));
            labelStatusEstorno.setText(chequeModel.getStatus().getDescricao());
            labelResponsavelEstorno.setText(chequeModel.getResponsavel().getNome());
            labelValorChequeEstorno.setText(PempecParse.doubleToZero(chequeModel.getValor()));
            labelObservacaoEstorno.setText(chequeModel.getObservacao());

            ContaBancariaDebitoModel contaBancariaDebitoModel = new ContaBancariaDebitoModel();
            contaBancariaDebitoModel.setContaBancariaCheque(chequeModel);
            contaBancariaDebitoModel.setPk(new PKModel());
            contaBancariaDebitoModel.getPk().setOrganizacao(organizacaoModel);

            contaBancariaDebitoModel = contaBancariaDebitoBO.obterPorCheque(contaBancariaDebitoModel);

            UsuarioModel usuario = new UsuarioBO().consultarPorPk(contaBancariaDebitoModel.getUsuario());

            labelUsuarioEstorno.setText(usuario.getNome());

            List<TituloPagarBaixaChequeModel> listaTitulos = new ArrayList<TituloPagarBaixaChequeModel>();

            listaTitulos = tituloPagoChequeBO.obterPorCheque(chequeModel);

            if (listaTitulos != null && !listaTitulos.isEmpty()) {

                if (listaTitulos.get(0).getTipoOperacaoBancaria() != null) {
                    labelTipoOperacaoEstorno.setText(contaBancariaDebitoModel.getTipoOperacaoBancaria().getDescricao());

                    usuario = new UsuarioBO().consultarPorPk(listaTitulos.get(0).getTituloPagarBaixa().getUsuario());

                    labelUsuarioEstorno.setText(usuario.getNome());
                } else {
                    labelTipoOperacaoEstorno.setText("Não Localizado");
                    labelUsuarioEstorno.setText(listaTitulos.get(0).getTituloPagarBaixa().getUsuario().getNome());
                }


                if (listaTitulos.size() == 1) {

                    TituloPagarModel tituloPago = ((TituloPagarModel) listaTitulos.get(0).getTituloPagarBaixa().getTitulo());

                    labelTituloPagarEstorno.setText(tituloPago.getNumeroDocumento());

                    HistoricoModel historico = new HistoricoBO().consultarPorPk(tituloPago.getHistorico());

                    labelDescricaoEstorno.setText(historico.getDescricao() + " " + tituloPago.getDescricao());

                } else {
                    labelTituloPagarEstorno.setText("Lote : " + PempecParse.numberToString(listaTitulos.size()));

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

}//GEN-LAST:event_comboChequeEstornoActionPerformed

private void comboChequeCompensaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboChequeCompensaActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboChequeCompensa.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        labelDataGeracao.setVisible(true);
        labelEmissao.setVisible(true);
        labelVencimento.setVisible(true);
        labelPortador.setVisible(true);
        labelStatus.setVisible(true);
        labelValorCheque.setVisible(true);
        labelDataVencimento.setVisible(true);
        jFTDataChequeCompensado.setVisible(true);
        jTObservacaoCompensa.setEnabled(true);
        comboResponsavelCompensa.setEnabled(true);

        ContaBancariaChequeModel cheque = (ContaBancariaChequeModel) comboChequeCompensa.getSelectedItem();

        if (cheque.getDataRegistro() != null) {
            labelDataGeracao.setText(PempecParse.dateToString(cheque.getDataRegistro()));
        }

        if (cheque.getDataPrevisaoCompensacao() != null) {
            labelVencimento.setText(PempecParse.dateToString(cheque.getDataPrevisaoCompensacao()));
        }

        if (cheque.getDataEmissao() != null) {
            labelEmissao.setText(PempecParse.dateToString(cheque.getDataEmissao()));
        }

        if (cheque.getValor() != null) {
            labelValorCheque.setText(PempecParse.doubleToZero(cheque.getValor()));
        }

        if (cheque.getStatus() != null) {
            labelStatus.setText(cheque.getStatus().getDescricao());
        }

        if (cheque.getPortador() != null) {
            labelPortador.setText(cheque.getPortador().toUpperCase());
        }

        if (cheque.getObservacao() != null) {
            jTObservacaoCompensa.setText(cheque.getObservacao());
        }

    }
}//GEN-LAST:event_comboChequeCompensaActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque;
    private javax.swing.JPanel abaCompensarCheque1;
    private javax.swing.JButton botaoCompensar;
    private javax.swing.JButton botaoEstorno;
    private javax.swing.JButton botaoFechar;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigoChequeEstorno;
    private javax.swing.JTextField campoCodigoCompensa;
    private javax.swing.JComboBox comboChequeCompensa;
    private javax.swing.JComboBox comboChequeEstorno;
    private javax.swing.JComboBox comboContaBancariaCompensa;
    private javax.swing.JComboBox comboContaBancariaEstorno;
    private javax.swing.JComboBox comboResponsavelCompensa;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private org.jdesktop.swingx.JXDatePicker jFTDataChequeCompensado;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXEditorPane jTObservacaoCompensa;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelCheque;
    private javax.swing.JLabel labelCheque1;
    private javax.swing.JLabel labelCompensadoEstorno;
    private javax.swing.JLabel labelDataGeracao;
    private javax.swing.JLabel labelDataGeracaoEstorno;
    private javax.swing.JLabel labelDataRegistroEstorno;
    private javax.swing.JLabel labelDataVencimento;
    private javax.swing.JLabel labelDescricaoEstorno;
    private javax.swing.JLabel labelEmissao;
    private javax.swing.JLabel labelEmissaoEstorno;
    private javax.swing.JLabel labelObservacao;
    private javax.swing.JLabel labelObservacaoEstorno;
    private javax.swing.JLabel labelPortador;
    private javax.swing.JLabel labelPortadorEstorno;
    private javax.swing.JLabel labelResponsavelEstorno;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JLabel labelStatusEstorno;
    private javax.swing.JLabel labelTipoOperacaoEstorno;
    private javax.swing.JLabel labelTituloPagarEstorno;
    private javax.swing.JLabel labelUsuarioEstorno;
    private javax.swing.JLabel labelValorCheque;
    private javax.swing.JLabel labelValorChequeEstorno;
    private javax.swing.JLabel labelVencimento;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport supportChequeCompensa;
    private AutoCompleteSupport supportChequeEstorno;
}
