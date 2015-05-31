package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.ContaBancariaBO;
import br.com.pempec.finance.businessObjects.ContaBancariaTransferenciaBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.businessObjects.TipoOperacaoBancariaBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.ContaBancariaCreditoModel;
import br.com.pempec.finance.models.ContaBancariaDebitoModel;
import br.com.pempec.finance.models.ContaBancariaModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.models.TipoOperacaoBancariaModel;
import br.com.pempec.finance.models.ContaBancariaTransferenciaModel;
import br.com.pempec.finance.models.UsuarioModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.iterators.ContaBancariaTransferenciaTextFilterator;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecParse;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.swing.SwingUtilities;

/**
 * @author PEMPEC
 */
public class TransferenciaEntreContas extends FinanceInternalFrame implements IRepopulador {

    private ContaBancariaBO contaBancariaBO = new ContaBancariaBO();
    private ContaBancariaTransferenciaBO contaBancariaTransferenciaBO = new ContaBancariaTransferenciaBO();
    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private TipoOperacaoBancariaBO tipoOperacaoBancariaBO = new TipoOperacaoBancariaBO();
    private long tela = Tela.TELA_CONCILIACAO_TRANSFERENCIAS_BANCARIAS.getTela();

    private String NameObject() {
        return (String) "Transferência Bancária";
    }

    public void repopularCombos() {

        try {


            // responsavel
            Collection<FuncionarioModel> lFuncionario = new ArrayList<FuncionarioModel>();
            Collection<ContaBancariaTransferenciaModel> lColecao = contaBancariaTransferenciaBO.obterTodos(organizacaoModel);

            FuncionarioModel funcionarioModel = new FuncionarioModel();

            funcionarioModel.setNome(" -> Selecione <- ");

            lFuncionario.add(funcionarioModel);

            lFuncionario.addAll(funcionarioBO.obterTodos(organizacaoModel));

            comboResponsavel.setModel(new javax.swing.DefaultComboBoxModel(lFuncionario.toArray()));

            //conta Bancaria

            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta(" -> Selecione <- ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.obterTodos(organizacaoModel));

            comboContaBancariaOrigem.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

            //Tipo Operacao

            Collection<TipoOperacaoBancariaModel> listaOperacoes = new ArrayList<TipoOperacaoBancariaModel>();

            TipoOperacaoBancariaModel tipoOperacaoBancariaModel = new TipoOperacaoBancariaModel();

            tipoOperacaoBancariaModel.setDescricao(" -> Selecione <- ");

            listaOperacoes.add(tipoOperacaoBancariaModel);

            int tipo = 2; //tipo internet

            listaOperacoes.addAll(tipoOperacaoBancariaBO.obterTodosPorTipo(organizacaoModel, tipo));

            comboTipoOperacao.setModel(new javax.swing.DefaultComboBoxModel(listaOperacoes.toArray()));

            final EventList<ContaBancariaTransferenciaModel> lRegistros = GlazedLists.eventList(lColecao);

            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboTransferencia, lRegistros, new ContaBancariaTransferenciaTextFilterator());
            support.setFilterMode(TextMatcherEditor.STARTS_WITH);
            support.setStrict(false);


            Collection<ContaBancariaTransferenciaModel> lTransferencias = contaBancariaTransferenciaBO.obterTodos(organizacaoModel);

            final EventList<ContaBancariaTransferenciaModel> lRegistrosTransferencias = GlazedLists.eventList(lTransferencias);
            if (supportTransferencia != null && supportTransferencia.getItemList() != null && supportTransferencia.getComboBox() != null) {
                supportTransferencia.uninstall();
            }

            supportTransferencia = AutoCompleteSupport.install(comboEstornar, lRegistrosTransferencias, new ContaBancariaTransferenciaTextFilterator());
            supportTransferencia.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportTransferencia.setStrict(false);


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

    public TransferenciaEntreContas() throws SystemException {

        initComponents();

        campoCodigo.setVisible(false);
        labelDataRegistro.setVisible(false);
        labelLoteContabil.setVisible(false);
        labelUsuario.setVisible(false);
        botaoEstornarTransf.setEnabled(false);



        //formatar datas                
        jFTDataTransferencia.setDate(Controller.getDataServidorBD());

        //Aplicando tamanho maximo de caracteres do campo.
        jTObservacao.setDocument(new Documento(60));

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoTransferir = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoEstornarTransf = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        abaCompensarCheque = new javax.swing.JPanel();
        labelObservacao = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        comboContaBancariaOrigem = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        comboResponsavel = new javax.swing.JComboBox();
        jTObservacao = new javax.swing.JTextField();
        comboContaBancariaDestino = new javax.swing.JComboBox();
        jLabel11 = new javax.swing.JLabel();
        labelValorPagar = new javax.swing.JLabel();
        jFTValor = new br.com.pempec.componentes.JDoubleField();
        jLabel12 = new javax.swing.JLabel();
        comboTipoOperacao = new javax.swing.JComboBox();
        labelNumeroDocumento = new javax.swing.JLabel();
        comboTransferencia = new javax.swing.JComboBox();
        campoCodigo = new javax.swing.JTextField();
        botaoGerarNumeroDocumento = new javax.swing.JButton();
        labelDataRegistro = new javax.swing.JLabel();
        labelDataVencimento1 = new javax.swing.JLabel();
        labelLoteContabil = new javax.swing.JLabel();
        labelUsuario = new javax.swing.JLabel();
        jFTDataTransferencia = new org.jdesktop.swingx.JXDatePicker();
        jPanel1 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        comboEstornar = new javax.swing.JComboBox();
        labelValorTransf = new javax.swing.JLabel();
        labelContaOrigemTransf = new javax.swing.JLabel();
        labelContaDestinoTransf = new javax.swing.JLabel();
        labelDataTransf = new javax.swing.JLabel();
        labelResponsavelTransf = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setResizable(true);
        setTitle("PEMPEC ENTERPRISE - Finance - Transferência Entre Contas");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 102, 255)));

        botaoTransferir.setMnemonic('I');
        botaoTransferir.setText("Transferir");
        botaoTransferir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoTransferirActionPerformed(evt);
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

        botaoEstornarTransf.setMnemonic('L');
        botaoEstornarTransf.setText("Estornar");
        botaoEstornarTransf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoEstornarTransfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botaoTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoEstornarTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoFechar, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoTransferir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoEstornarTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)), NameObject()));

        abaCompensarCheque.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153))));

        labelObservacao.setText("Observação");

        jLabel10.setText("Conta Bancária Origem");

        comboContaBancariaOrigem.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        comboContaBancariaOrigem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboContaBancariaOrigemItemStateChanged(evt);
            }
        });

        jLabel7.setText("Responsável");

        comboResponsavel.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        comboContaBancariaDestino.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        jLabel11.setText("Conta Bancária Destino");

        labelValorPagar.setText("Valor");

        jLabel12.setText("Tipo Operação Bancária");

        comboTipoOperacao.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N

        labelNumeroDocumento.setText("Número do Documento");

        comboTransferencia.setToolTipText("");
        comboTransferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboTransferenciaActionPerformed(evt);
            }
        });

        campoCodigo.setEditable(false);

        botaoGerarNumeroDocumento.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        botaoGerarNumeroDocumento.setText("Gerar");
        botaoGerarNumeroDocumento.setToolTipText("Gerar Número Documento");
        botaoGerarNumeroDocumento.setActionCommand("botaoGerarNumeroDocumento");
        botaoGerarNumeroDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botaoGerarNumeroDocumentoActionPerformed(evt);
            }
        });

        labelDataRegistro.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelDataRegistro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelDataRegistro.setToolTipText("");
        labelDataRegistro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 102)));
        labelDataRegistro.setFocusable(false);
        labelDataRegistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelDataVencimento1.setText("Data Movimento");

        labelLoteContabil.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelLoteContabil.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelLoteContabil.setToolTipText("");
        labelLoteContabil.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lote Contábil", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 102)));
        labelLoteContabil.setFocusable(false);
        labelLoteContabil.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelUsuario.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelUsuario.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelUsuario.setToolTipText("");
        labelUsuario.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Usuário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, new java.awt.Color(0, 102, 102)));
        labelUsuario.setFocusable(false);
        labelUsuario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout abaCompensarChequeLayout = new javax.swing.GroupLayout(abaCompensarCheque);
        abaCompensarCheque.setLayout(abaCompensarChequeLayout);
        abaCompensarChequeLayout.setHorizontalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelObservacao)
                            .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTObservacao, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, abaCompensarChequeLayout.createSequentialGroup()
                                    .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(comboContaBancariaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addGap(31, 31, 31)
                                    .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(comboContaBancariaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(labelDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel12)
                                    .addComponent(comboTipoOperacao, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboResponsavel, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(229, 229, 229))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(comboTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNumeroDocumento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(botaoGerarNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFTDataTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelDataVencimento1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelValorPagar)
                            .addComponent(jFTValor, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(46, 46, 46))
        );
        abaCompensarChequeLayout.setVerticalGroup(
            abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumeroDocumento)
                    .addComponent(labelDataVencimento1)
                    .addComponent(labelValorPagar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTDataTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFTValor, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoGerarNumeroDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboContaBancariaOrigem, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(abaCompensarChequeLayout.createSequentialGroup()
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboContaBancariaDestino, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboTipoOperacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelObservacao)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboResponsavel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(abaCompensarChequeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelLoteContabil, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Transferência de Fundos", abaCompensarCheque);

        jLabel13.setText("Transferência");

        comboEstornar.setToolTipText("");
        comboEstornar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboEstornarActionPerformed(evt);
            }
        });

        labelValorTransf.setForeground(new java.awt.Color(204, 0, 0));
        labelValorTransf.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelValorTransf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor"));

        labelContaOrigemTransf.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelContaOrigemTransf.setForeground(new java.awt.Color(0, 102, 102));
        labelContaOrigemTransf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Conta Bancária Origem", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelContaDestinoTransf.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelContaDestinoTransf.setForeground(new java.awt.Color(0, 102, 102));
        labelContaDestinoTransf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Conta Bancária Destino", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        labelDataTransf.setForeground(new java.awt.Color(204, 0, 0));
        labelDataTransf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelDataTransf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data Movimento"));

        labelResponsavelTransf.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        labelResponsavelTransf.setForeground(new java.awt.Color(0, 102, 102));
        labelResponsavelTransf.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Responsável", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 10))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboEstornar, javax.swing.GroupLayout.Alignment.LEADING, 0, 280, Short.MAX_VALUE))
                    .addComponent(labelContaOrigemTransf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelContaDestinoTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelResponsavelTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelDataTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelValorTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboEstornar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelValorTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelContaOrigemTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelContaDestinoTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(labelDataTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelResponsavelTransf, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Estornar Transferências", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botaoFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoFecharActionPerformed
        setVisible(false);
    }//GEN-LAST:event_botaoFecharActionPerformed

    private void botaoLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoLimparActionPerformed
        botaoTransferir.setEnabled(true);
        botaoEstornarTransf.setEnabled(false);
        botaoGerarNumeroDocumento.setEnabled(true);

        comboContaBancariaOrigem.setSelectedIndex(0);
        comboContaBancariaDestino.setSelectedItem(null);
        comboResponsavel.setSelectedIndex(0);
        comboTipoOperacao.setSelectedIndex(0);
        comboTransferencia.setSelectedItem(null);
        comboEstornar.setSelectedItem(null);

        campoCodigo.setText("");
        jFTValor.setText("0,00");
        //Campo com mascara setar null no value.
        jFTDataTransferencia.setDate(Controller.getDataServidorBD());
        labelLoteContabil.setVisible(false);
        labelUsuario.setText("");
        labelDataRegistro.setText("");
        jTObservacao.setText("");

        labelDataTransf.setText("");
        labelContaDestinoTransf.setText("");
        labelContaOrigemTransf.setText("");
        labelResponsavelTransf.setText("");
        labelValorTransf.setText("");

    }//GEN-LAST:event_botaoLimparActionPerformed

    private Boolean validaCampos() {

        if (jFTValor.getText().isEmpty() || jFTValor.getValue() == 0 || jFTValor.getValue() < 0) {
            jFTValor.requestFocus();
            return false;
        }

        if (comboTipoOperacao.getSelectedIndex() == 0) {
            comboTipoOperacao.requestFocus();
            return false;
        }


        if (comboContaBancariaOrigem.getSelectedIndex() == 0) {
            comboContaBancariaOrigem.requestFocus();
            return false;
        }

        if (comboContaBancariaDestino.getSelectedIndex() == 0) {
            comboContaBancariaDestino.requestFocus();
            return false;
        }


        if (comboResponsavel.getSelectedIndex() == 0) {
            comboResponsavel.requestFocus();
            return false;
        }

        if (jFTDataTransferencia.getDate() == null) {
            jFTDataTransferencia.requestFocus();
            return false;
        }


        if (jFTDataTransferencia.getDate() != null) {
            Date dataInformada = PempecParse.dateToDate(jFTDataTransferencia.getDate());
            if (dataInformada.after(Controller.getDataServidorBD())) {
                exibeMensagemAviso("A Data da transferência não pode ser superior a HOJE.!", null);
                jFTDataTransferencia.requestFocus();
                return false;
            }
        }

        return true;
    }

private void botaoTransferirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoTransferirActionPerformed

    if (comboTransferencia.getSelectedItem() != null) {

        String valorCombo = comboTransferencia.getSelectedItem().toString();


        try {

            OrganizacaoModel organizacaoModel = Controller.getOrganizacao();
            UsuarioModel usuarioModel = Controller.getUsuarioLogado();

            long action = Action.OUTROS.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCampos()) {

                ContaBancariaTransferenciaModel tab = new ContaBancariaTransferenciaModel();

                tab.setPk(new PKModel());
                tab.getPk().setOrganizacao(organizacaoModel);
                tab.getPk().setId(PempecKeyGenerator.generateKey());
                tab.setIdentificacao(valorCombo);
                tab.setUsuarioModel(usuarioModel);
                tab.setDataMovimento(PempecParse.dateToDate(jFTDataTransferencia.getDate()));
                tab.setDataRegistro(PempecParse.dateToDate(jFTDataTransferencia.getDate()));
                tab.setObservacao(jTObservacao.getText());
                tab.setValor(jFTValor.getValue());

                if (comboContaBancariaOrigem.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancariaOrigem.getSelectedItem()).getPk() != null) {

                    ContaBancariaModel contaOrigem = new ContaBancariaModel();
                    contaOrigem.setPk(new PKModel());
                    contaOrigem.getPk().setOrganizacao(organizacaoModel);
                    contaOrigem.getPk().setId(((ContaBancariaModel) comboContaBancariaOrigem.getSelectedItem()).getPk().getId());

                    tab.setContaBancariaDebitoModel(new ContaBancariaDebitoModel());
                    tab.getContaBancariaDebitoModel().setPk(new PKModel());
                    tab.getContaBancariaDebitoModel().getPk().setOrganizacao(organizacaoModel);
                    tab.getContaBancariaDebitoModel().getPk().setId(PempecKeyGenerator.generateKey());

                    tab.getContaBancariaDebitoModel().setContaBancaria(contaOrigem);

                    tab.getContaBancariaDebitoModel().setDataContabil(PempecParse.dateToDate(jFTDataTransferencia.getDate()));
                    tab.getContaBancariaDebitoModel().setDataMovimento(PempecParse.dateToDate(jFTDataTransferencia.getDate()));
                    tab.getContaBancariaDebitoModel().setDataRegistro(Controller.getDataServidorBD());
                    tab.getContaBancariaDebitoModel().setIdentificacao("9" + PempecKeyGenerator.generateNumeroDocumentoContaBancariaDebito());
                    tab.getContaBancariaDebitoModel().setDescricao("Débito ref transf. p/ outra conta. Id. " + tab.getContaBancariaDebitoModel().getIdentificacao());
                    tab.getContaBancariaDebitoModel().setMovimentoDiarioModel(registroMovimento("Debitar", tab.getIdentificacao(), "Débito por transferência de valores entre contas", jFTValor.getValue(), "Debitado"));
                    tab.getContaBancariaDebitoModel().setResponsavel(((FuncionarioModel) comboResponsavel.getSelectedItem()));
                    tab.getContaBancariaDebitoModel().setTipoLancamento("D");
                    tab.getContaBancariaDebitoModel().setTipoOperacaoBancaria(((TipoOperacaoBancariaModel) comboTipoOperacao.getSelectedItem()));
                    tab.getContaBancariaDebitoModel().setTituloPagar(null);
                    tab.getContaBancariaDebitoModel().setValor(jFTValor.getValue());
                    tab.getContaBancariaDebitoModel().setUsuario(usuarioModel);
                }


                if (comboContaBancariaDestino.getSelectedItem() != null && ((ContaBancariaModel) comboContaBancariaDestino.getSelectedItem()).getPk() != null) {

                    ContaBancariaModel contaDestino = new ContaBancariaModel();
                    contaDestino.setPk(new PKModel());
                    contaDestino.getPk().setOrganizacao(organizacaoModel);
                    contaDestino.getPk().setId(((ContaBancariaModel) comboContaBancariaDestino.getSelectedItem()).getPk().getId());

                    tab.setContaBancariaCreditoModel(new ContaBancariaCreditoModel());
                    tab.getContaBancariaCreditoModel().setPk(new PKModel());
                    tab.getContaBancariaCreditoModel().getPk().setOrganizacao(organizacaoModel);
                    tab.getContaBancariaCreditoModel().getPk().setId(PempecKeyGenerator.generateKey());

                    tab.getContaBancariaCreditoModel().setContaBancaria(contaDestino);

                    tab.getContaBancariaCreditoModel().setDataContabil(PempecParse.dateToDate(jFTDataTransferencia.getDate()));
                    tab.getContaBancariaCreditoModel().setDataMovimento(PempecParse.dateToDate(jFTDataTransferencia.getDate()));
                    tab.getContaBancariaCreditoModel().setDataRegistro(Controller.getDataServidorBD());

                    tab.getContaBancariaCreditoModel().setIdentificacao("9" + PempecKeyGenerator.generateNumeroDocumentoContaBancariaCredito());
                    tab.getContaBancariaCreditoModel().setDescricao("Crédito ref transf. de outra conta. Id. :" + tab.getContaBancariaCreditoModel().getIdentificacao());
                    tab.getContaBancariaCreditoModel().setMovimentoDiarioModel(registroMovimento("Creditar", tab.getIdentificacao(), "Credito por transferência de valores entre contas", jFTValor.getValue(), "Creditado"));
                    tab.getContaBancariaCreditoModel().setResponsavel(((FuncionarioModel) comboResponsavel.getSelectedItem()));
                    tab.getContaBancariaCreditoModel().setTipoLancamento("C");
                    tab.getContaBancariaCreditoModel().setTipoOperacaoBancaria(((TipoOperacaoBancariaModel) comboTipoOperacao.getSelectedItem()));
                    tab.getContaBancariaCreditoModel().setTituloReceber(null);
                    tab.getContaBancariaCreditoModel().setValor(jFTValor.getValue());
                    tab.getContaBancariaCreditoModel().setUsuario(usuarioModel);
                }

                tab.setMovimentoDiarioModel(registroMovimento("Transferencia", tab.getIdentificacao(), "Transferencia da Conta " + comboContaBancariaOrigem.getSelectedItem().toString() + " para a conta " + comboContaBancariaDestino.getSelectedItem().toString(), jFTValor.getValue(), "Transferido"));
                tab.setResponsavel(tab.getContaBancariaDebitoModel().getResponsavel());
                tab.setTipoOperacaoBancariaModel(tab.getContaBancariaDebitoModel().getTipoOperacaoBancaria());

                contaBancariaTransferenciaBO.inserir(tab);

                this.botaoLimparActionPerformed(evt);

            } else {

                exibeMensagemAviso("Campo(s) Obrigatório(s)!", null);

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


}//GEN-LAST:event_botaoTransferirActionPerformed

private void comboTransferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboTransferenciaActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboTransferencia.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            ContaBancariaTransferenciaModel tab = new ContaBancariaTransferenciaModel();

            tab.setIdentificacao(comboTransferencia.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = contaBancariaTransferenciaBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {

                if (tab.getPk() != null) {

                    botaoTransferir.setEnabled(false);

                    botaoEstornarTransf.setEnabled(false);

                    if (labelUsuario.getText() != null) {
                        labelUsuario.setVisible(true);
                    }

                    if (tab.getLoteContabil() != null) {
                        labelLoteContabil.setVisible(true);
                    }

                    if (labelDataRegistro.getText() != null) {
                        labelDataRegistro.setVisible(true);
                    }
                    botaoGerarNumeroDocumento.setEnabled(false);

                } else {
                    botaoTransferir.setEnabled(true);
                    labelUsuario.setVisible(false);
                    labelLoteContabil.setVisible(false);
                    labelDataRegistro.setVisible(false);
                    botaoGerarNumeroDocumento.setEnabled(true);
                }

                if (tab.getLoteContabil() != null) {
                    labelLoteContabil.setVisible(true);
                    labelLoteContabil.setText(tab.getLoteContabil().getLote());

                } else {

                    labelLoteContabil.setVisible(false);
                }


                if (tab.getUsuarioModel().getNome() != null) {
                    labelUsuario.setText(tab.getUsuarioModel().getNome());
                }

                if (tab.getDataRegistro() != null) {
                    labelDataRegistro.setText(PempecParse.dateToString(tab.getDataRegistro()));
                }

                campoCodigo.setText(tab.getPk().getId());

                if (tab.getValor() != null) {
                    jFTValor.setText(tab.getValor().toString());
                }

                jTObservacao.setText(tab.getObservacao());

                labelDataRegistro.setText(PempecParse.dateToString(tab.getDataRegistro()));

                if (tab.getDataMovimento() != null) {
                    jFTDataTransferencia.setDate(PempecParse.dateToDate(tab.getDataMovimento()));
                }


                if (tab.getResponsavel() != null) {
                    for (int i = 1; i < comboResponsavel.getItemCount(); i++) {
                        if (tab.getResponsavel().getPk().getId().equalsIgnoreCase(((FuncionarioModel) comboResponsavel.getItemAt(i)).getPk().getId())) {
                            comboResponsavel.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getContaBancariaDebitoModel().getContaBancaria() != null) {
                    for (int i = 1; i < comboContaBancariaOrigem.getItemCount(); i++) {
                        if (tab.getContaBancariaDebitoModel().getContaBancaria().getPk().getId().equalsIgnoreCase(((ContaBancariaModel) comboContaBancariaOrigem.getItemAt(i)).getPk().getId())) {
                            comboContaBancariaOrigem.setSelectedIndex(i);
                            break;
                        }
                    }

                }

                if (tab.getContaBancariaCreditoModel().getContaBancaria() != null) {
                    for (int i = 1; i < comboContaBancariaDestino.getItemCount(); i++) {
                        if (tab.getContaBancariaCreditoModel().getContaBancaria().getPk().getId().equalsIgnoreCase(((ContaBancariaModel) comboContaBancariaDestino.getItemAt(i)).getPk().getId())) {
                            comboContaBancariaDestino.setSelectedIndex(i);
                            break;
                        }
                    }

                }



                if (tab.getTipoOperacaoBancariaModel() != null) {
                    for (int i = 1; i < comboTipoOperacao.getItemCount(); i++) {
                        if (tab.getTipoOperacaoBancariaModel().getPk().getId().equalsIgnoreCase(((TipoOperacaoBancariaModel) comboTipoOperacao.getItemAt(i)).getPk().getId())) {
                            comboTipoOperacao.setSelectedIndex(i);
                            break;
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
    }
}//GEN-LAST:event_comboTransferenciaActionPerformed

private void botaoGerarNumeroDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoGerarNumeroDocumentoActionPerformed
    String numero = PempecKeyGenerator.generateNumeroDocumentoTransferencia();
    comboTransferencia.setSelectedItem(numero);
    comboTransferencia.requestFocus();
}//GEN-LAST:event_botaoGerarNumeroDocumentoActionPerformed

private void comboContaBancariaOrigemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboContaBancariaOrigemItemStateChanged
    if (comboContaBancariaOrigem.getSelectedIndex() != 0 && ((ContaBancariaModel) comboContaBancariaOrigem.getSelectedItem()).getPk().getId() != null) {

        try {
            ContaBancariaModel contaBancaria = new ContaBancariaModel();

            contaBancaria.setConta(" -> Selecione <- ");

            Collection<ContaBancariaModel> lContas = new ArrayList<ContaBancariaModel>();

            lContas.add(contaBancaria);

            lContas.addAll(contaBancariaBO.montarComboDestino((ContaBancariaModel) comboContaBancariaOrigem.getSelectedItem()));

            comboContaBancariaDestino.setModel(new javax.swing.DefaultComboBoxModel(lContas.toArray()));

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

}//GEN-LAST:event_comboContaBancariaOrigemItemStateChanged

private void comboEstornarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboEstornarActionPerformed

    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboEstornar.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            ContaBancariaTransferenciaModel tab = new ContaBancariaTransferenciaModel();

            tab.setIdentificacao(comboEstornar.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = contaBancariaTransferenciaBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {

                botaoEstornarTransf.setEnabled(true);

                botaoTransferir.setEnabled(false);

                labelValorTransf.setText(PempecParse.doubleToZero(tab.getValor()));

                labelDataTransf.setText(PempecParse.dateToString(tab.getDataMovimento()));

                labelContaOrigemTransf.setText(tab.getContaBancariaDebitoModel().getContaBancaria().getConta() + " " + tab.getContaBancariaDebitoModel().getContaBancaria().getBanco().getNomeBanco());

                labelContaDestinoTransf.setText(tab.getContaBancariaCreditoModel().getContaBancaria().getConta() + " " + tab.getContaBancariaCreditoModel().getContaBancaria().getBanco().getNomeBanco());

                labelResponsavelTransf.setText(tab.getResponsavel().getNome().toUpperCase());

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
}//GEN-LAST:event_comboEstornarActionPerformed

private void botaoEstornarTransfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoEstornarTransfActionPerformed
    if (comboEstornar.getSelectedItem() != null) {

        String valorCombo = comboEstornar.getSelectedItem().toString();

        try {

            OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

            ContaBancariaTransferenciaModel tab = new ContaBancariaTransferenciaModel();

            long action = Action.OUTROS.getAction();

            if (!Controller.checarPermissao(tela, action)) {
                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(organizacaoModel);
            tab.getPk().setId(PempecKeyGenerator.generateKey());
            tab.setIdentificacao(valorCombo);

            tab = contaBancariaTransferenciaBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null && !tab.getPk().getId().isEmpty()) {

                tab.setMovimentoDiarioModel(registroMovimento("Estorno Trans", tab.getIdentificacao(), "Estorno da transf " + tab.getIdentificacao() + "Cta C" + tab.getContaBancariaCreditoModel().getContaBancaria().getConta() + "Cta Deb" + tab.getContaBancariaDebitoModel().getContaBancaria().getConta(), tab.getValor(), "Estornado"));

                contaBancariaTransferenciaBO.excluir(tab);
            }

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

    }
}//GEN-LAST:event_botaoEstornarTransfActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel abaCompensarCheque;
    protected javax.swing.JButton botaoEstornarTransf;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoGerarNumeroDocumento;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JButton botaoTransferir;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JComboBox comboContaBancariaDestino;
    private javax.swing.JComboBox comboContaBancariaOrigem;
    private javax.swing.JComboBox comboEstornar;
    private javax.swing.JComboBox comboResponsavel;
    private javax.swing.JComboBox comboTipoOperacao;
    private javax.swing.JComboBox comboTransferencia;
    private org.jdesktop.swingx.JXDatePicker jFTDataTransferencia;
    private br.com.pempec.componentes.JDoubleField jFTValor;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTObservacao;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelContaDestinoTransf;
    private javax.swing.JLabel labelContaOrigemTransf;
    private javax.swing.JLabel labelDataRegistro;
    private javax.swing.JLabel labelDataTransf;
    private javax.swing.JLabel labelDataVencimento1;
    private javax.swing.JLabel labelLoteContabil;
    private javax.swing.JLabel labelNumeroDocumento;
    private javax.swing.JLabel labelObservacao;
    private javax.swing.JLabel labelResponsavelTransf;
    private javax.swing.JLabel labelUsuario;
    private javax.swing.JLabel labelValorPagar;
    private javax.swing.JLabel labelValorTransf;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private AutoCompleteSupport supportTransferencia;
}
