package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.BairroBO;
import br.com.pempec.finance.businessObjects.CidadeBO;
import br.com.pempec.finance.businessObjects.EstadoBO;
import br.com.pempec.finance.businessObjects.FuncionarioBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BairroModel;
import br.com.pempec.finance.models.CidadeModel;
import br.com.pempec.finance.models.ContatoModel;
import br.com.pempec.finance.models.EnderecoModel;
import br.com.pempec.finance.models.EstadoModel;
import br.com.pempec.finance.models.FuncionarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.utils.Action;
import br.com.pempec.finance.utils.Constantes;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.Documento;
import br.com.pempec.finance.utils.FinanceInternalFrame;
import br.com.pempec.finance.utils.iterators.FuncionarioTextFilterator;
import br.com.pempec.finance.utils.IRepopulador;
import br.com.pempec.finance.utils.MaskUtils;
import br.com.pempec.finance.utils.PempecKeyGenerator;
import br.com.pempec.finance.utils.PempecUtil;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.iterators.BairroTextFilterator;
import br.com.pempec.finance.utils.iterators.CidadeTextFilterator;
import br.com.pempec.finance.utils.iterators.EstadoTextFilterator;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.io.File;
import java.util.Collection;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class CadastroFuncionario extends FinanceInternalFrame implements IRepopulador {

    private FuncionarioBO funcionarioBO = new FuncionarioBO();
    private EstadoBO estadoBO = new EstadoBO();
    private CidadeBO cidadeBO = new CidadeBO();
    private BairroBO bairroBO = new BairroBO();
    private long tela = Tela.TELA_PARAMETROS_FUNCIONARIOS.getTela();

    public CadastroFuncionario() throws SystemException {

        initComponents();


        //Escodendo os campos ID's
        campoCodigo.setVisible(false);
        campoCodigoContato.setVisible(false);
        campoCodigoEndereco.setVisible(false);

        //Aplicando tamanho maximo de caracteres do campo.
        jTLogradouro.setDocument(new Documento(60));
        jTComplemento.setDocument(new Documento(60));
        jTNumero.setDocument(new Documento(5));
        jFTTelFixo.setDocument(new Documento(10));
        jFTCel.setDocument(new Documento(10));
        jFTEMail.setDocument(new Documento(60));
        jFTMsn.setDocument(new Documento(60));
        jFTEMail.setDocument(new Documento(60));
        jFTMsn.setDocument(new Documento(60));
        jTDDDCel.setDocument(new Documento(2));
        jTDDDTelFixo.setDocument(new Documento(2));

        //Aplicar aos demais.

        //Aplicando mascara em campos.
        //Ver como tratar a questão de CPF ou CNPJ de funcionario
        jFTCep.setFormatterFactory(MaskUtils.mascaraCep());
        jFTCel.setFormatterFactory(MaskUtils.mascaraTelefone());
        jFTTelFixo.setFormatterFactory(MaskUtils.mascaraTelefone());
        jFTCpf.setFormatterFactory(MaskUtils.mascaraCpf());

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        botaoIncluir = new javax.swing.JButton();
        botaoAlterar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();
        botaoExcluir = new javax.swing.JButton();
        botaoImprimirFicha = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        labelSacado = new javax.swing.JLabel();
        comboFuncionario = new javax.swing.JComboBox();
        labelCpf = new javax.swing.JLabel();
        jFTCpf = new javax.swing.JFormattedTextField();
        campoCodigo = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        labelLog = new javax.swing.JLabel();
        jTLogradouro = new javax.swing.JTextField();
        labelCep = new javax.swing.JLabel();
        jFTCep = new javax.swing.JFormattedTextField();
        labelNum = new javax.swing.JLabel();
        jTNumero = new javax.swing.JTextField();
        labelEst = new javax.swing.JLabel();
        comboEstado = new javax.swing.JComboBox();
        labelComp = new javax.swing.JLabel();
        campoCodigoEndereco = new javax.swing.JTextField();
        jTComplemento = new javax.swing.JTextField();
        labelBai = new javax.swing.JLabel();
        comboBairro = new javax.swing.JComboBox();
        labelCid = new javax.swing.JLabel();
        comboCidade = new javax.swing.JComboBox();
        jPanel4 = new javax.swing.JPanel();
        campoCodigoContato = new javax.swing.JTextField();
        labelCpfCnpj1 = new javax.swing.JLabel();
        labelCpfCnpj2 = new javax.swing.JLabel();
        jTDDDTelFixo = new javax.swing.JTextField();
        labelCpfCnpj3 = new javax.swing.JLabel();
        jTDDDCel = new javax.swing.JTextField();
        labelCpfCnpj4 = new javax.swing.JLabel();
        jFTTelFixo = new javax.swing.JFormattedTextField();
        jFTCel = new javax.swing.JFormattedTextField();
        labelCpfCnpj5 = new javax.swing.JLabel();
        jFTEMail = new javax.swing.JFormattedTextField();
        labelCpfCnpj6 = new javax.swing.JLabel();
        jFTMsn = new javax.swing.JFormattedTextField();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Cadastro Básico do Funcionário");

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

        botaoImprimirFicha.setMnemonic('A');
        botaoImprimirFicha.setText("Imprimir");
        botaoImprimirFicha.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoImprimirFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botaoLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botaoFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botaoAlterar, botaoExcluir, botaoFechar, botaoIncluir, botaoLimpar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botaoIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoImprimirFicha, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botaoLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
                    .addComponent(botaoFechar, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelSacado.setText("Funcionario");

        comboFuncionario.setFont(getFont());
        comboFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFuncionarioActionPerformed(evt);
            }
        });

        labelCpf.setText("CPF");

        campoCodigo.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jFTCpf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSacado, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboFuncionario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpf, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoCodigo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(285, 285, 285))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelSacado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(labelCpf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(campoCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );

        jTabbedPane1.addTab("Funcionário", jPanel5);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        labelLog.setText("Logradouro");

        labelCep.setText("Cep");

        labelNum.setText("Número");

        labelEst.setText("Estado");

        comboEstado.setFont(new java.awt.Font("Arial", 0, 10));

        labelComp.setText("Complemento");

        campoCodigoEndereco.setEditable(false);

        labelBai.setText("Bairro");

        comboBairro.setFont(new java.awt.Font("Arial", 0, 10));

        labelCid.setText("Cidade");

        comboCidade.setFont(new java.awt.Font("Arial", 0, 10));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelComp)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jTComplemento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelLog)
                                        .addComponent(jTLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelNum)
                                        .addComponent(jTNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(23, 23, 23)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelBai)
                            .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelEst)
                            .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCid)
                                    .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jFTCep, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(campoCodigoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(labelCep, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(165, 165, 165))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelLog)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(labelComp))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelNum)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelCid)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelEst)
                        .addGap(6, 6, 6)
                        .addComponent(comboEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelBai)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(labelCep)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTCep, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(67, 67, 67))
        );

        jTabbedPane1.addTab("Endereço", jPanel3);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        campoCodigoContato.setEditable(false);

        labelCpfCnpj1.setText("Telefone Fixo");

        labelCpfCnpj2.setText("DDD");

        jTDDDTelFixo.setFont(new java.awt.Font("Arial", 0, 12));

        labelCpfCnpj3.setText("DDD");

        jTDDDCel.setFont(new java.awt.Font("Arial", 0, 12));

        labelCpfCnpj4.setText("Telefone Celular");

        labelCpfCnpj5.setText("E-mail");

        labelCpfCnpj6.setText("Messenger");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTDDDTelFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpfCnpj2)
                    .addComponent(jTDDDCel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelCpfCnpj3))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jFTCel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoCodigoContato, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCpfCnpj6)
                            .addComponent(labelCpfCnpj5)
                            .addComponent(jFTEMail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFTMsn, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCpfCnpj1)
                            .addComponent(labelCpfCnpj4)
                            .addComponent(jFTTelFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(236, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelCpfCnpj2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDDDTelFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCpfCnpj3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTDDDCel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(labelCpfCnpj1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFTTelFixo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCpfCnpj4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFTCel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoCodigoContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addComponent(labelCpfCnpj5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTEMail, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelCpfCnpj6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jFTMsn, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Contato", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return (String) "Funcionarios";
    }

    @Override
    public Font getFont() {
        Font fonte = new Font("Arial", Font.PLAIN, 10);

        return fonte;
    }

    public void repopularCombos() {

        try {

            OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

            Collection<FuncionarioModel> lColecao = funcionarioBO.obterTodos(organizacaoModel);

            Collection<EstadoModel> lEstados = estadoBO.obterTodos();

            Collection<BairroModel> lBairros = bairroBO.obterTodos();

            Collection<CidadeModel> lCidades = cidadeBO.obterTodos();

            //comboBairro
            final EventList<BairroModel> lRegBairros = GlazedLists.eventList(lBairros);
            if (supportBairro != null && supportBairro.getItemList() != null && supportBairro.getComboBox() != null) {
                supportBairro.uninstall();
            }
            supportBairro = AutoCompleteSupport.install(comboBairro, lRegBairros, new BairroTextFilterator());
            supportBairro.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportBairro.setStrict(false);


            //comboCidade
            final EventList<CidadeModel> lRegCidades = GlazedLists.eventList(lCidades);
            if (supportCidade != null && supportCidade.getItemList() != null && supportCidade.getComboBox() != null) {
                supportCidade.uninstall();
            }
            supportCidade = AutoCompleteSupport.install(comboCidade, lRegCidades, new CidadeTextFilterator());
            supportCidade.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportCidade.setStrict(false);

            //comboFuncionario
            final EventList<FuncionarioModel> lRegistros = GlazedLists.eventList(lColecao);
            if (support != null && support.getItemList() != null && support.getComboBox() != null) {
                support.uninstall();
            }
            support = AutoCompleteSupport.install(comboFuncionario, lRegistros, new FuncionarioTextFilterator());
            support.setFilterMode(TextMatcherEditor.STARTS_WITH);
            support.setStrict(false);

            //comboEstado
            final EventList<EstadoModel> lRegEstados = GlazedLists.eventList(lEstados);
            if (supportEstado != null && supportEstado.getItemList() != null && supportEstado.getComboBox() != null) {
                supportEstado.uninstall();
            }
            supportEstado = AutoCompleteSupport.install(comboEstado, lRegEstados, new EstadoTextFilterator());
            supportEstado.setFilterMode(TextMatcherEditor.STARTS_WITH);
            supportEstado.setStrict(false);


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

        botaoAlterar.setEnabled(false);

        botaoIncluir.setEnabled(true);



        campoCodigo.setText("");
        //Somente setar null nos combos que possui o auto-complete
        comboEstado.setSelectedItem(null);
        comboCidade.setSelectedItem(null);
        comboBairro.setSelectedItem(null);
        comboFuncionario.setSelectedItem(null);

        //Campo com mascara setar null no value.
        jFTCpf.setValue(null);

        campoCodigoContato.setText("");
        jTDDDCel.setText("");
        jTDDDTelFixo.setText("");
        jFTCel.setValue(null);
        jFTTelFixo.setValue(null);
        jFTEMail.setText("");
        jFTMsn.setText("");

        campoCodigoEndereco.setText("");
        jTLogradouro.setText("");

        jTNumero.setText("");

        jTComplemento.setText("");
        jFTCep.setValue(null);




    }//GEN-LAST:event_botaoLimparActionPerformed

    private void botaoExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoExcluirActionPerformed

        String valorCombo = null;

        if (comboFuncionario.getSelectedItem() != null) {
            valorCombo = comboFuncionario.getSelectedItem().toString();
        }

        if (valorCombo != null) {

            try {

                long action = Action.EXCLUIR.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                FuncionarioModel tab = new FuncionarioModel();

                tab.setPk(new PKModel());

                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();

                tab.getPk().setOrganizacao(organizacaoModel);

                tab.getPk().setId(campoCodigo.getText());

                tab.setNome(comboFuncionario.getSelectedItem().toString());

                tab = funcionarioBO.consultarPorTemplate(tab);


                if (!campoCodigoEndereco.getText().isEmpty()) {

                    tab.setEndereco(new EnderecoModel());

                    tab.getEndereco().setPk(new PKModel());

                    tab.getEndereco().getPk().setId(campoCodigoEndereco.getText());

                }

                if (!campoCodigoContato.getText().isEmpty()) {

                    tab.setContato(new ContatoModel());
                    tab.getContato().setPk(new PKModel());
                    tab.getContato().getPk().setId(campoCodigoContato.getText());

                }

                tab.setMovimentoDiarioModel(registroMovimento("Deletar Funcionario", valorCombo, tab.getNome() + " ID :" + tab.getPk().getId(), null, "Deletado"));

                funcionarioBO.excluir(tab);

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
            exibeMensagemAviso("Campo Nome é obrigatório.", null);
        }
    }//GEN-LAST:event_botaoExcluirActionPerformed

    private Boolean validaCampos() {


        if (jFTCpf.getText().isEmpty()
                || jFTCpf.getText().equals("   .   .   -  ")) {
            jFTCpf.requestFocus();
            return false;
        }

        return true;
    }

    private void botaoIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoIncluirActionPerformed

        String valorCombo = null;

        if (comboFuncionario.getSelectedItem() != null) {
            valorCombo = comboFuncionario.getSelectedItem().toString().toUpperCase();
        }

        if (valorCombo != null) {

            try {

                long action = Action.CADASTRAR.getAction();

                if (!Controller.checarPermissao(tela, action)) {

                    exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                    return;

                }

                if (validaCampos()) {

                    FuncionarioModel tab = new FuncionarioModel();
                    tab.setPk(new PKModel());
                    OrganizacaoModel organizacaoModel = Controller.getOrganizacao();
                    tab.getPk().setOrganizacao(organizacaoModel);
                    tab.getPk().setId(PempecKeyGenerator.generateKey());


                    tab.setNome(valorCombo);

                    tab.setCpf(jFTCpf.getText());

                    //ATENCAO: o endereco nao é obrigatorio.

                    if (!jTLogradouro.getText().isEmpty()) {

                        tab.setEndereco(new EnderecoModel());
                        tab.getEndereco().setPk(new PKModel());
                        tab.getEndereco().getPk().setId(PempecKeyGenerator.generateKey());
                        tab.getEndereco().setLogradouro(jTLogradouro.getText());
                        tab.getEndereco().setNumero(jTNumero.getText());
                        tab.getEndereco().setCep(jFTCep.getText());
                        tab.getEndereco().setComplemento(jTComplemento.getText());


                        if (comboEstado.getSelectedItem() != null && ((EstadoModel) comboEstado.getSelectedItem()).getId() != null) {

                            tab.getEndereco().setEstado(new EstadoModel());

                            tab.getEndereco().getEstado().setId(((EstadoModel) comboEstado.getSelectedItem()).getId());

                        }


                        if (comboBairro.getSelectedItem() != null && ((BairroModel) comboBairro.getSelectedItem()).getId() != null) {

                            tab.getEndereco().setBairro(new BairroModel());
                            tab.getEndereco().getBairro().setId(((BairroModel) comboBairro.getSelectedItem()).getId());
                        }

                        if (comboCidade.getSelectedItem() != null && ((CidadeModel) comboCidade.getSelectedItem()).getId() != null) {

                            tab.getEndereco().setCidade(new CidadeModel());
                            tab.getEndereco().getCidade().setId(((CidadeModel) comboCidade.getSelectedItem()).getId());
                        }


                    }

                    //Tratando que o contato não é obrigatório
                    //Na validação deve contemplar, caso o usuario digite o ddd, obrigatoriamente deva digitar o numero.
                    if (!jTDDDTelFixo.getText().isEmpty() || !jFTTelFixo.getText().isEmpty() || !jFTEMail.getText().isEmpty()) {

                        tab.setContato(new ContatoModel());
                        tab.getContato().setPk(new PKModel());
                        tab.getContato().getPk().setId(PempecKeyGenerator.generateKey());
                        tab.getContato().setTelefone(jTDDDTelFixo.getText() + jFTTelFixo.getText());

                        if (!PempecUtil.validaPreenchimentoEmail(jFTEMail.getText())) {
                            exibeMensagemAviso("E-mail inválido!", null);
                            jFTEMail.requestFocus();
                            return;
                        }

                        tab.getContato().setEmail(jFTEMail.getText());
                        tab.getContato().setMsn(jFTMsn.getText());
                        tab.getContato().setCelular(jTDDDCel.getText() + jFTCel.getText());

                    }
                    tab.setMovimentoDiarioModel(registroMovimento("Inserir Funcionario", valorCombo, tab.getNome(), null, "Inserido"));
                    funcionarioBO.inserir(tab);

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
            exibeMensagemAviso("Campo Nome é obrigatório.", null);
        }
    }//GEN-LAST:event_botaoIncluirActionPerformed

private void botaoAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botaoAlterarActionPerformed

    String valorCombo = null;

    if (comboFuncionario.getSelectedItem() != null) {
        valorCombo = comboFuncionario.getSelectedItem().toString().toUpperCase();
    }

    if (valorCombo != null) {

        try {

            long action = Action.ALTERAR.getAction();

            if (!Controller.checarPermissao(tela, action)) {

                exibeMensagemAviso(Constantes.MSG_ACESSO_NEGADO, Constantes.MSG_ACESSO_NEGADO);
                return;

            }

            if (validaCampos()) {

                FuncionarioModel tab = new FuncionarioModel();
                tab.setPk(new PKModel());
                OrganizacaoModel organizacaoModel = Controller.getOrganizacao();
                tab.getPk().setOrganizacao(organizacaoModel);
                tab.getPk().setId(campoCodigo.getText());
                tab.setNome(valorCombo);
                tab.setCpf(jFTCpf.getText());

                tab = funcionarioBO.consultarPorTemplate(tab);

                if (!jTLogradouro.getText().isEmpty()) {

                    tab.setEndereco(new EnderecoModel());
                    tab.getEndereco().setPk(new PKModel());
                    tab.getEndereco().getPk().setId(campoCodigoEndereco.getText());
                    tab.getEndereco().setLogradouro(jTLogradouro.getText());
                    tab.getEndereco().setNumero(jTNumero.getText());

                    tab.getEndereco().setCep(jFTCep.getText());

                    tab.getEndereco().setComplemento(jTComplemento.getText());

                    if (comboEstado.getSelectedItem() != null && ((EstadoModel) comboEstado.getSelectedItem()).getId() != null) {

                        tab.getEndereco().setEstado(new EstadoModel());

                        tab.getEndereco().getEstado().setId(((EstadoModel) comboEstado.getSelectedItem()).getId());

                    }

                    if (comboBairro.getSelectedItem() != null && ((BairroModel) comboBairro.getSelectedItem()).getId() != null) {

                        tab.getEndereco().setBairro(new BairroModel());
                        tab.getEndereco().getBairro().setId(((BairroModel) comboBairro.getSelectedItem()).getId());
                    }

                    if (comboCidade.getSelectedItem() != null && ((CidadeModel) comboCidade.getSelectedItem()).getId() != null) {

                        tab.getEndereco().setCidade(new CidadeModel());
                        tab.getEndereco().getCidade().setId(((CidadeModel) comboCidade.getSelectedItem()).getId());
                    }

                }


                if (!jTDDDTelFixo.getText().isEmpty() || !jFTTelFixo.getText().isEmpty() || !jFTEMail.getText().isEmpty()) {

                    tab.setContato(new ContatoModel());
                    tab.getContato().setPk(new PKModel());
                    tab.getContato().getPk().setId(campoCodigoContato.getText());
                    tab.getContato().setTelefone(jTDDDTelFixo.getText() + jFTTelFixo.getText());

                    if (!PempecUtil.validaPreenchimentoEmail(jFTEMail.getText())) {
                        exibeMensagemAviso("E-mail inválido!", null);
                        jFTEMail.requestFocus();
                        return;
                    }

                    tab.getContato().setEmail(jFTEMail.getText());
                    tab.getContato().setMsn(jFTMsn.getText());
                    tab.getContato().setCelular(jTDDDCel.getText() + jFTCel.getText());

                }

                tab.setMovimentoDiarioModel(registroMovimento("Alterar Funcionario", valorCombo, tab.getNome(), null, "Alterado"));
                funcionarioBO.alterar(tab);

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
        exibeMensagemAviso("Campo Nome é obrigatório.", null);
    }

}//GEN-LAST:event_botaoAlterarActionPerformed

private void comboFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFuncionarioActionPerformed
    String evento = evt.getActionCommand();
    int modifiers = evt.getModifiers();

    boolean userEnteredTextAndPressedReturn = "comboBoxEdited".equals(evento);
    boolean userSelectedTextFromList = "comboBoxChanged".equals(evento) && (modifiers & InputEvent.BUTTON1_MASK) != 0;

    if (comboFuncionario.getSelectedItem() != null && (userEnteredTextAndPressedReturn || userSelectedTextFromList)) {

        try {

            FuncionarioModel tab = new FuncionarioModel();
            tab.setNome(comboFuncionario.getSelectedItem().toString());
            tab.setPk(new PKModel());
            tab.getPk().setOrganizacao(Controller.getOrganizacao());

            tab = funcionarioBO.consultarPorTemplate(tab);

            if (tab != null && tab.getPk() != null) {

                botaoAlterar.setEnabled(true);
                botaoExcluir.setEnabled(true);
                botaoIncluir.setEnabled(false);

                campoCodigo.setText(tab.getPk().getId());



                jFTCpf.setText(tab.getCpf());

                if (tab.getEndereco() != null) {

                    campoCodigoEndereco.setText(tab.getEndereco().getPk().getId());
                    jTLogradouro.setText(tab.getEndereco().getLogradouro());
                    jTNumero.setText(tab.getEndereco().getNumero());
                    jFTCep.setText(tab.getEndereco().getCep());
                    jTComplemento.setText(tab.getEndereco().getComplemento());

                    if (tab.getEndereco().getEstado() != null) {

                        for (int i = 1; i < comboEstado.getItemCount(); i++) {
                            if (tab.getEndereco().getEstado().getId().equalsIgnoreCase(((EstadoModel) comboEstado.getItemAt(i)).getId())) {
                                comboEstado.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    if (tab.getEndereco().getCidade() != null) {

                        for (int i = 1; i < comboCidade.getItemCount(); i++) {
                            if (tab.getEndereco().getCidade().getId().equalsIgnoreCase(((CidadeModel) comboCidade.getItemAt(i)).getId())) {
                                comboCidade.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                    if (tab.getEndereco().getBairro() != null) {

                        for (int i = 1; i < comboBairro.getItemCount(); i++) {
                            if (tab.getEndereco().getBairro().getId().equalsIgnoreCase(((BairroModel) comboBairro.getItemAt(i)).getId())) {
                                comboBairro.setSelectedIndex(i);
                                break;
                            }
                        }

                    }

                }

                //Tratando que o contato não é obrigatório
                //Na validação deve contemplar, caso o usuario digite o ddd, obrigatoriamente deva digitar o numero.
                if (tab.getContato() != null) {

                    campoCodigoContato.setText(tab.getContato().getPk().getId());

                    jTDDDTelFixo.setText(tab.getContato().getTelefone().substring(0, 2));
                    jFTTelFixo.setText(tab.getContato().getTelefone().substring(2));
                    jFTEMail.setText(tab.getContato().getEmail());
                    jFTMsn.setText(tab.getContato().getMsn());
                    jTDDDCel.setText(tab.getContato().getCelular().substring(0, 2));
                    jFTCel.setText(tab.getContato().getCelular().substring(2));

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
}//GEN-LAST:event_comboFuncionarioActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterar;
    private javax.swing.JButton botaoExcluir;
    private javax.swing.JButton botaoFechar;
    private javax.swing.JButton botaoImprimirFicha;
    private javax.swing.JButton botaoIncluir;
    protected javax.swing.JButton botaoLimpar;
    private javax.swing.JTextField campoCodigo;
    private javax.swing.JTextField campoCodigoContato;
    private javax.swing.JTextField campoCodigoEndereco;
    private javax.swing.JComboBox comboBairro;
    private javax.swing.JComboBox comboCidade;
    private javax.swing.JComboBox comboEstado;
    private javax.swing.JComboBox comboFuncionario;
    private javax.swing.JFormattedTextField jFTCel;
    private javax.swing.JFormattedTextField jFTCep;
    private javax.swing.JFormattedTextField jFTCpf;
    private javax.swing.JFormattedTextField jFTEMail;
    private javax.swing.JFormattedTextField jFTMsn;
    private javax.swing.JFormattedTextField jFTTelFixo;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTextField jTComplemento;
    private javax.swing.JTextField jTDDDCel;
    private javax.swing.JTextField jTDDDTelFixo;
    private javax.swing.JTextField jTLogradouro;
    private javax.swing.JTextField jTNumero;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelBai;
    private javax.swing.JLabel labelCep;
    private javax.swing.JLabel labelCid;
    private javax.swing.JLabel labelComp;
    private javax.swing.JLabel labelCpf;
    private javax.swing.JLabel labelCpfCnpj1;
    private javax.swing.JLabel labelCpfCnpj2;
    private javax.swing.JLabel labelCpfCnpj3;
    private javax.swing.JLabel labelCpfCnpj4;
    private javax.swing.JLabel labelCpfCnpj5;
    private javax.swing.JLabel labelCpfCnpj6;
    private javax.swing.JLabel labelEst;
    private javax.swing.JLabel labelLog;
    private javax.swing.JLabel labelNum;
    private javax.swing.JLabel labelSacado;
    // End of variables declaration//GEN-END:variables
    private AutoCompleteSupport support;
    private AutoCompleteSupport supportEstado;
    private AutoCompleteSupport supportCidade;
    private AutoCompleteSupport supportBairro;
}
