package br.com.pempec.finance.view;

import br.com.pempec.finance.businessObjects.CedenteBO;
import br.com.pempec.finance.businessObjects.TituloPagarBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.CedenteModel;
import br.com.pempec.finance.models.FormatosRelatoriosModel;
import br.com.pempec.finance.models.TituloPagarModel;
import br.com.pempec.finance.models.reports.FilterReportTituloPagar;
import br.com.pempec.finance.models.reports.ReportFichaCedente;
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

/**
 *
 * @author PEMPEC
 */
public class FichaCadastralCedente extends FinanceInternalFrame implements IRepopulador {

    private long tela = Tela.TELA_PARAMETROS_CEDENTES.getTela();
    private CedenteModel cedenteModel = new CedenteModel();

    public FichaCadastralCedente(CedenteModel cedente) throws SystemException {

        this.cedenteModel = cedente;
        initComponents();


    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        comboFormato = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        botaoGerar = new javax.swing.JButton();
        botaoFechar = new javax.swing.JButton();
        botaoLimpar = new javax.swing.JButton();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        setTitle("PEMPEC ENTERPRISE - Finance - Ficha Cadastral Cedente");

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
                .addGap(21, 21, 21)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String NameObject() {
        return "Ficha Cadastral Cedente";
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


                Map<String, Object> parametros = new HashMap<String, Object>();

                parametros.put(RelatorioConstantes.PARAMETRO_ID_ORGANIZACAO, Controller.getOrganizacao().getId());

                Collection<ReportFichaCedente> collection = new ArrayList<ReportFichaCedente>();

                ReportFichaCedente bean = new ReportFichaCedente();

                if (cedenteModel != null && cedenteModel.getNome() != null) {

                    CedenteModel tab = new CedenteBO().consultarPorTemplate(cedenteModel);


                    bean.setAcumuladoPagar(0d);
                    bean.setAcumuladoPago(0d);

                    if (tab.getBanco() != null) {

                        bean.setAgencia(tab.getAgencia());
                        bean.setConta(tab.getConta());
                        bean.setBanco(tab.getBanco().getNomeBanco());


                    }


                    if (tab.getEndereco() != null) {


                        if (tab.getEndereco().getCep() != null) {
                            bean.setCep(tab.getEndereco().getCep());
                        }

                        if (tab.getEndereco().getBairro() != null && tab.getEndereco().getBairro().getDescricao() != null && !tab.getEndereco().getBairro().getDescricao().isEmpty()) {
                            bean.setBairro(tab.getEndereco().getBairro().getDescricao());
                        }

                        if (tab.getEndereco().getCidade() != null && tab.getEndereco().getCidade().getDescricao() != null && !tab.getEndereco().getCidade().getDescricao().isEmpty()) {
                            bean.setCidade(tab.getEndereco().getCidade().getDescricao());
                        }

                        if (tab.getEndereco().getComplemento() != null) {
                            bean.setComplemento(tab.getEndereco().getComplemento());
                        }

                        if (tab.getEndereco().getLogradouro() != null) {
                            bean.setLogradouro(tab.getEndereco().getLogradouro());
                        }

                        if (tab.getEndereco().getNumero() != null) {
                            bean.setNumero(tab.getEndereco().getNumero());
                        }

                        if (tab.getEndereco().getEstado() != null && tab.getEndereco().getEstado().getSigla() != null) {
                            bean.setEstado(tab.getEndereco().getEstado().getSigla());
                        }

                    }

                    bean.setCnpjOrganizacao(organizacaoModel.getCnpj());
                    bean.setRazaoSocialOrganizacao(organizacaoModel.getRazaoSocial());

                    if (tab.getContaContabil() != null) {
                        bean.setContaContabil(tab.getContaContabil().getContaReduzida());
                        bean.setDigitoContaContabil(tab.getContaContabil().getDigitoContaReduzida());

                    }

                    bean.setCpfCnpj(tab.getCpfCnpj());

                    bean.setDataEmissaoRelatorio(Controller.getDataServidorBD());

                    bean.setNome(tab.getNome());
                    bean.setNomeUsuario(Controller.getUsuarioLogado().getNome());
                    bean.setPersonalidade(tab.getPersonalidade());

                    if (tab.getContato() != null) {

                        bean.setEmail(tab.getContato().getEmail());
                        bean.setTelefoneFixo(tab.getContato().getTelefone());
                        bean.setTelefonePrimeiroCelular(tab.getContato().getCelular());

                    }


                    bean.setTipoCedente(tab.getTipoCedente().getDescricao());
                    bean.setCga(tab.getCga());
                    bean.setInscEstadual(tab.getInscricaoEstadual());


                    if (tab.getContato() != null) {

                        bean.setEmail(tab.getContato().getEmail());

                        bean.setTelefoneFixo(tab.getContato().getTelefone());

                        bean.setTelefonePrimeiroCelular(tab.getContato().getCelular());

                    }

                    if (tab.getTipoCedente() != null) {

                        bean.setTipoCedente(tab.getTipoCedente().getDescricao());

                    }

                    bean.setContaContabil(tab.getContaContabil().getContaReduzida());
                    bean.setCpfCnpj(tab.getCpfCnpj());
                    bean.setDataEmissaoRelatorio(Controller.getDataServidorBD());
                    bean.setDigitoContaContabil(tab.getContaContabil().getDigitoContaReduzida());
                    bean.setNome(tab.getNome());
                    bean.setNomeUsuario(Controller.getUsuarioLogado().getNome());
                    bean.setPersonalidade(tab.getPersonalidade());

                    List<TituloPagarModel> collTitulos = new ArrayList<TituloPagarModel>();
                    Double valorPago = 0d;
                    Double valorPagar = 0d;

                    FilterReportTituloPagar filtro = new FilterReportTituloPagar();
                    filtro.setOrganizacao(Controller.getOrganizacao().getId());
                    Date dInicio = PempecParse.stringToDate("01/01/1900");
                    Date dFinal = PempecParse.stringToDate("01/01/2900");

                    filtro.setDataFinal(dFinal);
                    filtro.setDataInicial(dInicio);



                    if (tab != null && tab.getPk().getId() != null) {
                        filtro.setCedente(tab.getPk().getId());
                        parametros.put(RelatorioConstantes.PARAMETRO_ID_CEDENTE, tab.getPk().getId());

                    }


                    collTitulos.addAll(new TituloPagarBO().obterRelatorio(filtro));
                    Long auxQtd = 0L;

                    for (TituloPagarModel tituloPagarModel : collTitulos) {
                        if (tituloPagarModel.getDataPagamento() != null) {
                            valorPago += tituloPagarModel.getValorNominal();

                        } else {

                            valorPagar += tituloPagarModel.getValorNominal();
                            if (tituloPagarModel.getDataVencimento().before(PempecParse.dateToDate(new Date()))) {
                                auxQtd++;
                            }

                        }


                    }


                    if (auxQtd == 0) {

                        bean.setSituacao("EM DIA");
                    } else {

                        bean.setSituacao("EM DÉBITO. =>" + " Existe (em) " + auxQtd + " titulo (s) vencido (s).");
                    }

                    bean.setAcumuladoPagar(valorPagar);

                    bean.setAcumuladoPago(valorPago);

                    bean.setTotal(valorPago, valorPagar);

                    collection.add(bean);

                    switch (comboFormato.getSelectedIndex()) {

                        case 0:
                            new RelatorioUtil().visualizar(RelatorioConstantes.FICHA_CADASTRAL_CEDENTE, parametros, collection);
                            break;
                        case 1:
                            new RelatorioUtil().exportarPDF(RelatorioConstantes.FICHA_CADASTRAL_CEDENTE, parametros, collection);
                            break;
                        case 2:
                            new RelatorioUtil().exportarXLS(RelatorioConstantes.FICHA_CADASTRAL_CEDENTE, parametros, collection);
                            break;
                        case 3:

                            //Fazer tela de selecionar os destinatários. Pode ser múltiplos
                            File anexo = new RelatorioUtil().exportarPDFtoFile(RelatorioConstantes.FICHA_CADASTRAL_CEDENTE, parametros, collection);

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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox comboFormato;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    // End of variables declaration//GEN-END:variables
}
