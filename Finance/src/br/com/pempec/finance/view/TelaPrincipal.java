package br.com.pempec.finance.view;

import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.utils.Controller;
import br.com.pempec.finance.utils.FinanceFrame;
import br.com.pempec.finance.utils.PrintScreen;
import br.com.pempec.finance.utils.ResourcePropertiesLocator;
import br.com.pempec.finance.utils.Tela;
import br.com.pempec.finance.utils.UIManagerPut;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import org.jdesktop.swingx.plaf.LookAndFeelAddons;
import org.jdesktop.swingx.plaf.metal.MetalLookAndFeelAddons;

/**
 *
 * @author Pempec
 */
public class TelaPrincipal extends FinanceFrame {

    /**
     * Creates new form TelaPrincipalMdi
     */
    public TelaPrincipal() {

        try {

            // coloca uma figura na barra de título da janela
            URL url = this.getClass().getResource("/images/financeIcon.gif");
            Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
            this.setIconImage(imagemTitulo);

            NimRODTheme nt = new NimRODTheme();
            // nt.setPrimary1(new Color(193, 255, 193)); 

            nt.setPrimary1(new Color(103, 204, 255));  //essa eh a cor correta e padrao

            nt.setPrimary2(new Color(193, 255, 193));
            nt.setPrimary3(new Color(193, 255, 193));
            nt.setSecondary1(new Color(240, 240, 240));
            nt.setSecondary2(new Color(241, 241, 241));
            nt.setSecondary3(new Color(242, 242, 242));
            nt.setFrameOpacity(180);
            nt.setMenuOpacity(195);
            nt.setBlack(new Color(0, 0, 0));
            nt.setWhite(new Color(255, 255, 255));

            NimRODLookAndFeel nimRODLF = new NimRODLookAndFeel();
            nimRODLF.setCurrentTheme(nt);
            UIManager.setLookAndFeel(nimRODLF);

            initComponents();

            setExtendedState(MAXIMIZED_BOTH);

            LookAndFeelAddons.setAddon(MetalLookAndFeelAddons.class);

            UIManagerPut.modifyUIManagerPut();

            labelTPVersao.setText("Cliente");

            labelTPVersao.setText(ResourcePropertiesLocator.getMessageAbout("labelVersion"));

//19/05/2012
//            TelaAvisosTitulos avisosTitulos = new TelaAvisosTitulos();
//            avisosTitulos.setTitle("Títulos");
//            desktopPane.add(avisosTitulos);
//
//            avisosTitulos.show();
            // add em 19/05/2012            
//            impressaoCheque = new ImpressaoCheque();
//            TelaPrincipal.desktopPane.add(impressaoCheque);
//                                    impressaoCheque.repopularCombos();
//                                    impressaoCheque.botaoLimpar.doClick();
//                                    impressaoCheque.show();
//                                    
//            if (Controller.verificaParametroAtivo(Parametro.G001.getCodigo())) {
//
//                SwingUtilities.invokeLater(new Runnable() {
//
//                    public void run() {
//
//                        FilterReportContaBancariaCheque filterCheque = new FilterReportContaBancariaCheque();
//
//                        filterCheque.setOrganizacao(Controller.getOrganizacao().getId());
//
//                        filterCheque.setStatus(Constantes.STATUS_CHEQUE_EMITIDO);
//                        Collection<ContaBancariaChequeModel> collCheques = null;
//
//                        try {
//
//                            collCheques = new ContaBancariaChequeBO().obterRelatorio(filterCheque);
//
//                        } catch (ApplicationException ex) {
//
//                            tratamentoExcecao(ex);
//
//                        } catch (final SystemException ex) {
//
//                            final File file = PrintScreen.capture();
//
//                            SwingUtilities.invokeLater(new Runnable() {
//
//                                public void run() {
//
//                                    tratamentoExcecao(ex, file);
//
//                                }
//                            });
//
//                        }
//
//                        if (collCheques != null && !collCheques.isEmpty()) {
//
//                            if (JOptionPane.showConfirmDialog(null, "Existem cheques a compensar! Ir para tela de compensação?", "Cheques a compensar!", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//
//                                try {
//                                    compensadorMultiplosCheques = new CompensadorMultiplosCheques();
//                                    TelaPrincipal.desktopPane.add(compensadorMultiplosCheques);
//                                    compensadorMultiplosCheques.repopularCombos();
//                                    compensadorMultiplosCheques.botaoLimpar.doClick();
//                                    compensadorMultiplosCheques.show();
//                                } catch (final SystemException ex) {
//
//                                    final File file = PrintScreen.capture();
//
//                                    SwingUtilities.invokeLater(new Runnable() {
//
//                                        public void run() {
//
//                                            tratamentoExcecao(ex, file);
//
//                                        }
//                                    });
//
//                                }
//
//
//                            }
//
//                        }
//
//
//                    }
//                });
//
//            }//aqui cheques
        } catch (final Exception ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(new SystemException(ex), file);

                }
            });

        }//aqui

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        labelTPVersao = new javax.swing.JLabel();
        barraMenuPrincipal = new javax.swing.JMenuBar();
        contaPagarMenu = new javax.swing.JMenu();
        tituloPagarMenuItem = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JSeparator();
        particionadorTituloPagarMenuItem = new javax.swing.JMenuItem();
        jSeparator14 = new javax.swing.JSeparator();
        lancamentoTituloPagarMenuItem = new javax.swing.JMenuItem();
        jSeparator40 = new javax.swing.JSeparator();
        baixaTituloLoteMenuItem = new javax.swing.JMenuItem();
        jSeparator39 = new javax.swing.JSeparator();
        relatorioLotePagamentoMenuItem = new javax.swing.JMenuItem();
        jSeparator37 = new javax.swing.JSeparator();
        cancelamentoLoteMenuItem = new javax.swing.JMenuItem();
        jSeparator15 = new javax.swing.JSeparator();
        cancelarBaixaTituloPagarMenuItem = new javax.swing.JMenuItem();
        jSeparator16 = new javax.swing.JSeparator();
        emitirReciboContaPagarMenuItem = new javax.swing.JMenuItem();
        jSeparator38 = new javax.swing.JSeparator();
        contaReceberMenu = new javax.swing.JMenu();
        tituloReceberMenuItem = new javax.swing.JMenuItem();
        jSeparator27 = new javax.swing.JSeparator();
        particionadorTituloReceberMenuItem = new javax.swing.JMenuItem();
        jSeparator26 = new javax.swing.JSeparator();
        lancamentoTituloReceberMenuItem = new javax.swing.JMenuItem();
        baixaTituloReceberMenuItem = new javax.swing.JMenuItem();
        jSeparator25 = new javax.swing.JSeparator();
        cancelaBaixaTituloReceberMenuItem = new javax.swing.JMenuItem();
        jSeparator29 = new javax.swing.JSeparator();
        emitirReciboContaReceberMenuItem = new javax.swing.JMenuItem();
        tesourariaMenu = new javax.swing.JMenu();
        tesourariaLancamentosMenuItem = new javax.swing.JMenuItem();
        jSeparator30 = new javax.swing.JSeparator();
        transfereBancoMenuItem = new javax.swing.JMenuItem();
        jSeparator36 = new javax.swing.JSeparator();
        TelaChequesMenuItem = new javax.swing.JMenuItem();
        jSeparator34 = new javax.swing.JSeparator();
        ListagemChequesMenuItem = new javax.swing.JMenuItem();
        jMenuItemRLoteDeposito = new javax.swing.JMenuItem();
        jSeparator35 = new javax.swing.JSeparator();
        extratoTesourariaMenuItem = new javax.swing.JMenuItem();
        jSeparator17 = new javax.swing.JSeparator();
        emitirReciboTesourariaDebitoMenuItem = new javax.swing.JMenuItem();
        jSeparator20 = new javax.swing.JSeparator();
        emitirReciboTesourariaCreditoMenuItem = new javax.swing.JMenuItem();
        conciliacaoMenu = new javax.swing.JMenu();
        contaBancariaLancamentoMenuItem = new javax.swing.JMenuItem();
        jMenuItemTransfereBancoTesouraria = new javax.swing.JMenuItem();
        jSeparator18 = new javax.swing.JSeparator();
        transferenciaEntreContasMenuItem = new javax.swing.JMenuItem();
        reciboTransferenciaMenuItem = new javax.swing.JMenuItem();
        jSeparator41 = new javax.swing.JSeparator();
        saldoBancarioMenuItem = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JSeparator();
        gerarChequesMenuItem = new javax.swing.JMenuItem();
        emitirChequeAvulsoMenuItem = new javax.swing.JMenuItem();
        jSeparator31 = new javax.swing.JSeparator();
        compensarChequeMenuItem = new javax.swing.JMenuItem();
        compensarMultiChequeMenuItem = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JSeparator();
        extratoBancarioMenuItem = new javax.swing.JMenuItem();
        emitirReciboCopiaChequeMenuItem = new javax.swing.JMenuItem();
        impressaoChequeMenuItem = new javax.swing.JMenuItem();
        impressaoMultiplosChequeMenuItem = new javax.swing.JMenuItem();
        relatorioMenu = new javax.swing.JMenu();
        relFluxoMenuItem = new javax.swing.JMenuItem();
        telaCartaoCreditoMenuItem = new javax.swing.JMenuItem();
        jSeparator21 = new javax.swing.JSeparator();
        reCPMenuItem = new javax.swing.JMenuItem();
        jSeparator28 = new javax.swing.JSeparator();
        relCRMenuItem = new javax.swing.JMenuItem();
        jSeparator22 = new javax.swing.JSeparator();
        relatorioDmsMenuItem = new javax.swing.JMenuItem();
        jSeparator24 = new javax.swing.JSeparator();
        relatorioMovimentoDiarioMenuItem = new javax.swing.JMenuItem();
        jSeparator32 = new javax.swing.JSeparator();
        relatorioExportacaoMegaContabilMenuItem = new javax.swing.JMenuItem();
        jSeparator43 = new javax.swing.JSeparator();
        impressaoMultiplosRecibosMenuItem = new javax.swing.JMenuItem();
        jSeparator33 = new javax.swing.JSeparator();
        telaAvisosMenuItem = new javax.swing.JMenuItem();
        parametrosMenu = new javax.swing.JMenu();
        organizacaoMenuItem = new javax.swing.JMenuItem();
        funcionarioMenuItem = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        bancoMenuItem1 = new javax.swing.JMenuItem();
        contaBancariaMenuItem = new javax.swing.JMenuItem();
        layoutChequeMenuItem = new javax.swing.JMenuItem();
        cartaoCreditoMenuItem = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        tipoOperacaoBancariaMenuItem = new javax.swing.JMenuItem();
        tipoCobrancaMenuItem1 = new javax.swing.JMenuItem();
        tipoStatusMenuItem1 = new javax.swing.JMenuItem();
        tipoSacadoMenuItem1 = new javax.swing.JMenuItem();
        tipoCedenteMenuItem1 = new javax.swing.JMenuItem();
        tipoDeducaoMenuItem = new javax.swing.JMenuItem();
        tipoAcrescimoMenuItem = new javax.swing.JMenuItem();
        tipoNotaFiscalMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        cidadeMenuItem = new javax.swing.JMenuItem();
        bairroMenuItem = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JSeparator();
        formaPagamentoMenuItem1 = new javax.swing.JMenuItem();
        localPagamentoMenuItem1 = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JSeparator();
        historicoMenuItem1 = new javax.swing.JMenuItem();
        contaContabilMenuItem = new javax.swing.JMenuItem();
        centroCustoMenuItem1 = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JSeparator();
        sacadoMenuItem = new javax.swing.JMenuItem();
        jrMenuItemRelacaoSacados = new javax.swing.JMenuItem();
        jSeparator44 = new javax.swing.JSeparator();
        cedenteMenuItem = new javax.swing.JMenuItem();
        jrMenuItemRelacaoCedentes = new javax.swing.JMenuItem();
        jSeparator45 = new javax.swing.JSeparator();
        segurancaMenu = new javax.swing.JMenu();
        grupoMenuItem = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        usuarioMenuItem = new javax.swing.JMenuItem();
        utilMenu = new javax.swing.JMenu();
        calculadorajMenuItem = new javax.swing.JMenuItem();
        backupjMenuItem = new javax.swing.JMenuItem();
        tecladoVirtualjMenuItem = new javax.swing.JMenuItem();
        exportMegaContabilMenu = new javax.swing.JMenu();
        emailMenuItem = new javax.swing.JMenuItem();
        feriadosMenuItem = new javax.swing.JMenuItem();
        configuracoesMenuItem = new javax.swing.JMenuItem();
        parametroslMenuItem = new javax.swing.JMenuItem();
        jSeparator42 = new javax.swing.JSeparator();
        sincronizeMenuItem = new javax.swing.JMenuItem();
        exportMegaMenuItem = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        removExportMegaMenuItem = new javax.swing.JMenuItem();
        jSeparator23 = new javax.swing.JSeparator();
        manutencaoBancoDadosMenu = new javax.swing.JMenuItem();
        jMIverificarDados = new javax.swing.JMenuItem();
        sobreMenuItem1 = new javax.swing.JMenuItem();
        jSeparator19 = new javax.swing.JSeparator();
        sairMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(estacao());

        labelTPVersao.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        labelTPVersao.setForeground(new java.awt.Color(255, 255, 255));
        labelTPVersao.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTPVersao.setText("jLabel1");
        labelTPVersao.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        desktopPane.add(labelTPVersao);
        labelTPVersao.setBounds(170, 400, 500, 100);

        contaPagarMenu.setText("Contas a Pagar");

        tituloPagarMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        tituloPagarMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tituloPagar.png"))); // NOI18N
        tituloPagarMenuItem.setText("Cadastro");
        tituloPagarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tituloPagarMenuItemActionPerformed(evt);
            }
        });
        contaPagarMenu.add(tituloPagarMenuItem);
        contaPagarMenu.add(jSeparator13);

        particionadorTituloPagarMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/particionar.png"))); // NOI18N
        particionadorTituloPagarMenuItem.setText("Particionamento");
        particionadorTituloPagarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                particionadorTituloPagarMenuItemActionPerformed(evt);
            }
        });
        contaPagarMenu.add(particionadorTituloPagarMenuItem);
        contaPagarMenu.add(jSeparator14);

        lancamentoTituloPagarMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, java.awt.event.InputEvent.CTRL_MASK));
        lancamentoTituloPagarMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tab_delete.png"))); // NOI18N
        lancamentoTituloPagarMenuItem.setText("Baixa");
        lancamentoTituloPagarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lancamentoTituloPagarMenuItemActionPerformed(evt);
            }
        });
        contaPagarMenu.add(lancamentoTituloPagarMenuItem);
        contaPagarMenu.add(jSeparator40);

        baixaTituloLoteMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tab_delete.png"))); // NOI18N
        baixaTituloLoteMenuItem.setText("Baixa em Lote");
        baixaTituloLoteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baixaTituloLoteMenuItemActionPerformed(evt);
            }
        });
        contaPagarMenu.add(baixaTituloLoteMenuItem);
        contaPagarMenu.add(jSeparator39);

        relatorioLotePagamentoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        relatorioLotePagamentoMenuItem.setText("Relatório Lote");
        relatorioLotePagamentoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioLotePagamentoMenuItemActionPerformed(evt);
            }
        });
        contaPagarMenu.add(relatorioLotePagamentoMenuItem);
        contaPagarMenu.add(jSeparator37);

        cancelamentoLoteMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancelamentoLoteMenuItem.setText("Cancelamento de Lote");
        cancelamentoLoteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelamentoLoteMenuItemActionPerformed(evt);
            }
        });
        contaPagarMenu.add(cancelamentoLoteMenuItem);
        contaPagarMenu.add(jSeparator15);

        cancelarBaixaTituloPagarMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancelarBaixaTituloPagarMenuItem.setText("Cancelar Baixa");
        cancelarBaixaTituloPagarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBaixaTituloPagarMenuItemActionPerformed(evt);
            }
        });
        contaPagarMenu.add(cancelarBaixaTituloPagarMenuItem);
        contaPagarMenu.add(jSeparator16);

        emitirReciboContaPagarMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        emitirReciboContaPagarMenuItem.setText("Emitir Recibo");
        emitirReciboContaPagarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emitirReciboContaPagarMenuItemActionPerformed(evt);
            }
        });
        contaPagarMenu.add(emitirReciboContaPagarMenuItem);
        contaPagarMenu.add(jSeparator38);

        barraMenuPrincipal.add(contaPagarMenu);

        contaReceberMenu.setText("Contas a Receber");

        tituloReceberMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        tituloReceberMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tituloReceber.png"))); // NOI18N
        tituloReceberMenuItem.setText("Cadastro");
        tituloReceberMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tituloReceberMenuItemActionPerformed(evt);
            }
        });
        contaReceberMenu.add(tituloReceberMenuItem);
        contaReceberMenu.add(jSeparator27);

        particionadorTituloReceberMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/particionar.png"))); // NOI18N
        particionadorTituloReceberMenuItem.setText("Particionamento");
        particionadorTituloReceberMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                particionadorTituloReceberMenuItemActionPerformed(evt);
            }
        });
        contaReceberMenu.add(particionadorTituloReceberMenuItem);
        contaReceberMenu.add(jSeparator26);

        lancamentoTituloReceberMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, java.awt.event.InputEvent.CTRL_MASK));
        lancamentoTituloReceberMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tab_add.png"))); // NOI18N
        lancamentoTituloReceberMenuItem.setText("Baixa");
        lancamentoTituloReceberMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lancamentoTituloReceberMenuItemActionPerformed(evt);
            }
        });
        contaReceberMenu.add(lancamentoTituloReceberMenuItem);

        baixaTituloReceberMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tab_add.png"))); // NOI18N
        baixaTituloReceberMenuItem.setText("Baixa em Lote");
        baixaTituloReceberMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baixaTituloReceberMenuItemActionPerformed(evt);
            }
        });
        contaReceberMenu.add(baixaTituloReceberMenuItem);
        contaReceberMenu.add(jSeparator25);

        cancelaBaixaTituloReceberMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        cancelaBaixaTituloReceberMenuItem.setText("Cancela Baixa");
        cancelaBaixaTituloReceberMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelaBaixaTituloReceberMenuItemActionPerformed(evt);
            }
        });
        contaReceberMenu.add(cancelaBaixaTituloReceberMenuItem);
        contaReceberMenu.add(jSeparator29);

        emitirReciboContaReceberMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        emitirReciboContaReceberMenuItem.setText("Emitir Recibo");
        emitirReciboContaReceberMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emitirReciboContaReceberMenuItemActionPerformed(evt);
            }
        });
        contaReceberMenu.add(emitirReciboContaReceberMenuItem);

        barraMenuPrincipal.add(contaReceberMenu);

        tesourariaMenu.setText("Tesouraria");

        tesourariaLancamentosMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tab_go.png"))); // NOI18N
        tesourariaLancamentosMenuItem.setText("Lançamentos");
        tesourariaLancamentosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lancamentoTesourariaMenuItem(evt);
            }
        });
        tesourariaMenu.add(tesourariaLancamentosMenuItem);
        tesourariaMenu.add(jSeparator30);

        transfereBancoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_refresh.png"))); // NOI18N
        transfereBancoMenuItem.setText("Transferir p/ Banco");
        transfereBancoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transfereBancoMenuItemActionPerformed(evt);
            }
        });
        tesourariaMenu.add(transfereBancoMenuItem);
        tesourariaMenu.add(jSeparator36);

        TelaChequesMenuItem.setText("Cheques Recebidos");
        TelaChequesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelaChequesMenuItemActionPerformed(evt);
            }
        });
        tesourariaMenu.add(TelaChequesMenuItem);
        tesourariaMenu.add(jSeparator34);

        ListagemChequesMenuItem.setText("Depósito de Cheques");
        ListagemChequesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListagemChequesMenuItemActionPerformed(evt);
            }
        });
        tesourariaMenu.add(ListagemChequesMenuItem);

        jMenuItemRLoteDeposito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        jMenuItemRLoteDeposito.setText("Relatório Lote Depósito");
        jMenuItemRLoteDeposito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRLoteDepositoActionPerformed(evt);
            }
        });
        tesourariaMenu.add(jMenuItemRLoteDeposito);
        tesourariaMenu.add(jSeparator35);

        extratoTesourariaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        extratoTesourariaMenuItem.setText("Extrato Tesouraria");
        extratoTesourariaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                extratoTesourariaMenuItemActionPerformed(evt);
            }
        });
        tesourariaMenu.add(extratoTesourariaMenuItem);
        tesourariaMenu.add(jSeparator17);

        emitirReciboTesourariaDebitoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        emitirReciboTesourariaDebitoMenuItem.setText("Emitir Recibo Despesa");
        emitirReciboTesourariaDebitoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emitirReciboTesourariaDebitoMenuItemActionPerformed(evt);
            }
        });
        tesourariaMenu.add(emitirReciboTesourariaDebitoMenuItem);
        tesourariaMenu.add(jSeparator20);

        emitirReciboTesourariaCreditoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        emitirReciboTesourariaCreditoMenuItem.setText("Emitir Recibo Receita");
        emitirReciboTesourariaCreditoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emitirReciboTesourariaCreditoMenuItemActionPerformed(evt);
            }
        });
        tesourariaMenu.add(emitirReciboTesourariaCreditoMenuItem);

        barraMenuPrincipal.add(tesourariaMenu);

        conciliacaoMenu.setText("Conciliação");
        conciliacaoMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conciliacaoLancamentoMenuItem(evt);
            }
        });

        contaBancariaLancamentoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tab_go.png"))); // NOI18N
        contaBancariaLancamentoMenuItem.setText("Lançamentos");
        contaBancariaLancamentoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contaBancariaLancamentoMenuItemActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(contaBancariaLancamentoMenuItem);

        jMenuItemTransfereBancoTesouraria.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jMenuItemTransfereBancoTesouraria.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_refresh.png"))); // NOI18N
        jMenuItemTransfereBancoTesouraria.setText("Transfere Banco/Tesouraria");
        jMenuItemTransfereBancoTesouraria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemTransfereBancoTesourariaActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(jMenuItemTransfereBancoTesouraria);
        conciliacaoMenu.add(jSeparator18);

        transferenciaEntreContasMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_refresh.png"))); // NOI18N
        transferenciaEntreContasMenuItem.setText("Transferências Bancárias");
        transferenciaEntreContasMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferenciaEntreContasMenuItemActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(transferenciaEntreContasMenuItem);

        reciboTransferenciaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        reciboTransferenciaMenuItem.setText("Recibo Transferências Entre Contas");
        reciboTransferenciaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reciboTransferenciaMenuItemrelatorioExtratoBancarioMenuItem(evt);
            }
        });
        conciliacaoMenu.add(reciboTransferenciaMenuItem);
        conciliacaoMenu.add(jSeparator41);

        saldoBancarioMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        saldoBancarioMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/saldo_contas.png"))); // NOI18N
        saldoBancarioMenuItem.setText("Saldo das Contas");
        saldoBancarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saldoBancarioMenuItemActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(saldoBancarioMenuItem);
        conciliacaoMenu.add(jSeparator11);

        gerarChequesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        gerarChequesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/asterisk_orange.png"))); // NOI18N
        gerarChequesMenuItem.setText("Manutenção de Cheques");
        gerarChequesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerarChequesMenuItemActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(gerarChequesMenuItem);

        emitirChequeAvulsoMenuItem.setText("Impressão Cheque Avulso");
        emitirChequeAvulsoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emitirChequeAvulsoMenuItemActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(emitirChequeAvulsoMenuItem);
        conciliacaoMenu.add(jSeparator31);

        compensarChequeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money_delete.png"))); // NOI18N
        compensarChequeMenuItem.setText("Compensar Cheque Emitido");
        compensarChequeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compensarChequeMenuItemActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(compensarChequeMenuItem);

        compensarMultiChequeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money.png"))); // NOI18N
        compensarMultiChequeMenuItem.setText("Compensar Múltiplos Cheques");
        compensarMultiChequeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compensarMultiChequeMenuItemActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(compensarMultiChequeMenuItem);
        conciliacaoMenu.add(jSeparator12);

        extratoBancarioMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        extratoBancarioMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        extratoBancarioMenuItem.setText("Extrato Bancário");
        extratoBancarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioExtratoBancarioMenuItem(evt);
            }
        });
        conciliacaoMenu.add(extratoBancarioMenuItem);

        emitirReciboCopiaChequeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        emitirReciboCopiaChequeMenuItem.setText("Cópia de Cheques");
        emitirReciboCopiaChequeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emitirReciboCopiaChequeMenuItemActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(emitirReciboCopiaChequeMenuItem);

        impressaoChequeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        impressaoChequeMenuItem.setText("Impressão de Cheques");
        impressaoChequeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impressaoChequeMenuItemActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(impressaoChequeMenuItem);

        impressaoMultiplosChequeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        impressaoMultiplosChequeMenuItem.setText("Impressão de Múltiplos Cheques");
        impressaoMultiplosChequeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impressaoMultiplosChequeMenuItemActionPerformed(evt);
            }
        });
        conciliacaoMenu.add(impressaoMultiplosChequeMenuItem);

        barraMenuPrincipal.add(conciliacaoMenu);

        relatorioMenu.setText("Relatórios");

        relFluxoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        relFluxoMenuItem.setText("Fluxo de Caixa");
        relFluxoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioFluxoCaixaMenuItem(evt);
            }
        });
        relatorioMenu.add(relFluxoMenuItem);

        telaCartaoCreditoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        telaCartaoCreditoMenuItem.setText("Cartão Crédito");
        telaCartaoCreditoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telaCartaoCreditoMenuItemActionPerformed(evt);
            }
        });
        relatorioMenu.add(telaCartaoCreditoMenuItem);
        relatorioMenu.add(jSeparator21);

        reCPMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        reCPMenuItem.setText("Contas à Pagar");
        reCPMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioContasPagarMenuItem(evt);
            }
        });
        relatorioMenu.add(reCPMenuItem);
        relatorioMenu.add(jSeparator28);

        relCRMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        relCRMenuItem.setText("Contas à Receber");
        relCRMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioContasReceberMenuItem(evt);
            }
        });
        relatorioMenu.add(relCRMenuItem);
        relatorioMenu.add(jSeparator22);

        relatorioDmsMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        relatorioDmsMenuItem.setText("Declaração Mensal de Serviços");
        relatorioDmsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioDmsMenuItemActionPerformed(evt);
            }
        });
        relatorioMenu.add(relatorioDmsMenuItem);
        relatorioMenu.add(jSeparator24);

        relatorioMovimentoDiarioMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        relatorioMovimentoDiarioMenuItem.setText("Movimento Diário");
        relatorioMovimentoDiarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioMovimentoDiarioMenuItemActionPerformed(evt);
            }
        });
        relatorioMenu.add(relatorioMovimentoDiarioMenuItem);
        relatorioMenu.add(jSeparator32);

        relatorioExportacaoMegaContabilMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        relatorioExportacaoMegaContabilMenuItem.setText("Exportação MegaContábil");
        relatorioExportacaoMegaContabilMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                relatorioExportacaoMegaContabilMenuItemActionPerformed(evt);
            }
        });
        relatorioMenu.add(relatorioExportacaoMegaContabilMenuItem);
        relatorioMenu.add(jSeparator43);

        impressaoMultiplosRecibosMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/printer.png"))); // NOI18N
        impressaoMultiplosRecibosMenuItem.setText("Impressão Múltiplos Recibos");
        impressaoMultiplosRecibosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                impressaoMultiplosRecibosMenuItemActionPerformed(evt);
            }
        });
        relatorioMenu.add(impressaoMultiplosRecibosMenuItem);
        relatorioMenu.add(jSeparator33);

        telaAvisosMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tab.png"))); // NOI18N
        telaAvisosMenuItem.setText("Tela de Avisos");
        telaAvisosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telaAvisosMenuItemActionPerformed(evt);
            }
        });
        relatorioMenu.add(telaAvisosMenuItem);

        barraMenuPrincipal.add(relatorioMenu);

        parametrosMenu.setText("Parâmetros");

        organizacaoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/organizacao.png"))); // NOI18N
        organizacaoMenuItem.setText("Organizações");
        organizacaoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                organizacaoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(organizacaoMenuItem);

        funcionarioMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user.png"))); // NOI18N
        funcionarioMenuItem.setText("Funcionários");
        funcionarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                funcionarioMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(funcionarioMenuItem);
        parametrosMenu.add(jSeparator6);

        bancoMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/banco.png"))); // NOI18N
        bancoMenuItem1.setText("Bancos");
        bancoMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bancoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(bancoMenuItem1);

        contaBancariaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money_add.png"))); // NOI18N
        contaBancariaMenuItem.setText("Contas Bancárias");
        contaBancariaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contaBancariaMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(contaBancariaMenuItem);

        layoutChequeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_refresh.png"))); // NOI18N
        layoutChequeMenuItem.setText("Layout de Cheques");
        layoutChequeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                layoutChequeMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(layoutChequeMenuItem);

        cartaoCreditoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/table_refresh.png"))); // NOI18N
        cartaoCreditoMenuItem.setText("Cartões de Créditos");
        cartaoCreditoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cartaoCreditoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(cartaoCreditoMenuItem);
        parametrosMenu.add(jSeparator7);

        tipoOperacaoBancariaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tipo.png"))); // NOI18N
        tipoOperacaoBancariaMenuItem.setText("Tipos de Operações Bancária");
        tipoOperacaoBancariaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoOperacaoBancariaMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(tipoOperacaoBancariaMenuItem);

        tipoCobrancaMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tipo.png"))); // NOI18N
        tipoCobrancaMenuItem1.setText("Tipos de Cobranças");
        tipoCobrancaMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoCobrancaMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(tipoCobrancaMenuItem1);

        tipoStatusMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tipo.png"))); // NOI18N
        tipoStatusMenuItem1.setText("Tipos de Status");
        tipoStatusMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoStatusMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(tipoStatusMenuItem1);

        tipoSacadoMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tipo.png"))); // NOI18N
        tipoSacadoMenuItem1.setText("Tipos de Sacados");
        tipoSacadoMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoSacadoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(tipoSacadoMenuItem1);

        tipoCedenteMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tipo.png"))); // NOI18N
        tipoCedenteMenuItem1.setText("Tipos de Cedentes");
        tipoCedenteMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoCedenteMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(tipoCedenteMenuItem1);

        tipoDeducaoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tipo.png"))); // NOI18N
        tipoDeducaoMenuItem.setText("Tipos de Deduções");
        tipoDeducaoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoDeducaoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(tipoDeducaoMenuItem);

        tipoAcrescimoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tipo.png"))); // NOI18N
        tipoAcrescimoMenuItem.setText("Tipos de  Acréscimos");
        tipoAcrescimoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoAcrescimoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(tipoAcrescimoMenuItem);

        tipoNotaFiscalMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tipo.png"))); // NOI18N
        tipoNotaFiscalMenuItem.setText("Tipos de Notas Fiscais");
        tipoNotaFiscalMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoNotaFiscalMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(tipoNotaFiscalMenuItem);
        parametrosMenu.add(jSeparator1);

        cidadeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cidade.png"))); // NOI18N
        cidadeMenuItem.setText("Cidades");
        cidadeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cidadeMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(cidadeMenuItem);

        bairroMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bairro.png"))); // NOI18N
        bairroMenuItem.setText("Bairros");
        bairroMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bairroMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(bairroMenuItem);
        parametrosMenu.add(jSeparator10);

        formaPagamentoMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/money_dollar.png"))); // NOI18N
        formaPagamentoMenuItem1.setText("Formas de Pagamentos");
        formaPagamentoMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                formaPagamentoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(formaPagamentoMenuItem1);

        localPagamentoMenuItem1.setText("Local de Pagamento");
        localPagamentoMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                localPagamentoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(localPagamentoMenuItem1);
        parametrosMenu.add(jSeparator8);

        historicoMenuItem1.setText("Históricos");
        historicoMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historicoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(historicoMenuItem1);

        contaContabilMenuItem.setText("Conta Contábil");
        contaContabilMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contaContabilMenuItemhistoricoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(contaContabilMenuItem);

        centroCustoMenuItem1.setText("Centro Custos");
        centroCustoMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centroCustoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(centroCustoMenuItem1);
        parametrosMenu.add(jSeparator9);

        sacadoMenuItem.setText("Sacados");
        sacadoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sacadoMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(sacadoMenuItem);

        jrMenuItemRelacaoSacados.setText("Relação de Sacados");
        jrMenuItemRelacaoSacados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrMenuItemRelacaoSacadosActionPerformed(evt);
            }
        });
        parametrosMenu.add(jrMenuItemRelacaoSacados);
        parametrosMenu.add(jSeparator44);

        cedenteMenuItem.setText("Cedentes");
        cedenteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cedenteMenuItemActionPerformed(evt);
            }
        });
        parametrosMenu.add(cedenteMenuItem);

        jrMenuItemRelacaoCedentes.setText("Relação de Cedentes");
        jrMenuItemRelacaoCedentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrMenuItemRelacaoCedentesActionPerformed(evt);
            }
        });
        parametrosMenu.add(jrMenuItemRelacaoCedentes);
        parametrosMenu.add(jSeparator45);

        barraMenuPrincipal.add(parametrosMenu);

        segurancaMenu.setText("Segurança");

        grupoMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/group_add.png"))); // NOI18N
        grupoMenuItem.setText("Grupos");
        grupoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grupoMenuItemActionPerformed(evt);
            }
        });
        segurancaMenu.add(grupoMenuItem);
        segurancaMenu.add(jSeparator5);

        usuarioMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user_add.png"))); // NOI18N
        usuarioMenuItem.setText("Usuarios");
        usuarioMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioMenuItemActionPerformed(evt);
            }
        });
        segurancaMenu.add(usuarioMenuItem);

        barraMenuPrincipal.add(segurancaMenu);

        utilMenu.setText("Utilitários");

        calculadorajMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F8, 0));
        calculadorajMenuItem.setText("Calculadora");
        calculadorajMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculadorajMenuItemActionPerformed(evt);
            }
        });
        utilMenu.add(calculadorajMenuItem);

        backupjMenuItem.setText("Backup");
        backupjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupjMenuItemActionPerformed(evt);
            }
        });
        utilMenu.add(backupjMenuItem);

        tecladoVirtualjMenuItem.setText("Teclado Virtual");
        tecladoVirtualjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tecladoVirtualjMenuItemActionPerformed(evt);
            }
        });
        utilMenu.add(tecladoVirtualjMenuItem);

        barraMenuPrincipal.add(utilMenu);

        exportMegaContabilMenu.setText("Sistema");

        emailMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/network.gif"))); // NOI18N
        emailMenuItem.setText("Enviar E-Mail");
        emailMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailMenuItemActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(emailMenuItem);

        feriadosMenuItem.setText("Feriados");
        feriadosMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feriadosMenuItemActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(feriadosMenuItem);

        configuracoesMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tipo.png"))); // NOI18N
        configuracoesMenuItem.setText("Configurações");
        configuracoesMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracoesMenuItemActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(configuracoesMenuItem);

        parametroslMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tipo.png"))); // NOI18N
        parametroslMenuItem.setText("Configurações Gerais");
        parametroslMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                parametroslMenuItemActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(parametroslMenuItem);
        exportMegaContabilMenu.add(jSeparator42);

        sincronizeMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MegaContabil.png"))); // NOI18N
        sincronizeMenuItem.setText("Sincronizar MegaContábil");
        sincronizeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sincronizeMenuItemActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(sincronizeMenuItem);

        exportMegaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MegaContabil.png"))); // NOI18N
        exportMegaMenuItem.setText("Exportação MegaContábil");
        exportMegaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportMegaMenuItemActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(exportMegaMenuItem);

        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MegaContabil.png"))); // NOI18N
        jMenuItem1.setText("Relatório Lote Exportação");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(jMenuItem1);

        removExportMegaMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel.png"))); // NOI18N
        removExportMegaMenuItem.setText("Cancelar Exportação MegaContábil");
        removExportMegaMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removExportMegaMenuItemActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(removExportMegaMenuItem);
        exportMegaContabilMenu.add(jSeparator23);

        manutencaoBancoDadosMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/database_go.png"))); // NOI18N
        manutencaoBancoDadosMenu.setText("Manutenção Banco Dados");
        manutencaoBancoDadosMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manutencaoBancoDadosMenuActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(manutencaoBancoDadosMenu);

        jMIverificarDados.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMIverificarDados.setText("Verificar Integridade dos Dados");
        jMIverificarDados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMIverificarDadosActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(jMIverificarDados);

        sobreMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/pempecIcone.png"))); // NOI18N
        sobreMenuItem1.setText("Sobre");
        sobreMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sobreMenuItem1ActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(sobreMenuItem1);
        exportMegaContabilMenu.add(jSeparator19);

        sairMenuItem1.setText("Sair");
        sairMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sairMenuItemActionPerformed(evt);
            }
        });
        exportMegaContabilMenu.add(sairMenuItem1);

        barraMenuPrincipal.add(exportMegaContabilMenu);

        setJMenuBar(barraMenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 891, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tipoCobrancaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoCobrancaMenuItemActionPerformed

        if (cadastroTipoCobranca == null) {

            try {
                cadastroTipoCobranca = new CadastroTipoCobranca();
                cadastroTipoCobranca.setTitle("Manutenção de Tipo Cobrança");
                desktopPane.add(cadastroTipoCobranca);
                cadastroTipoCobranca.setVisible(true);
        cadastroTipoCobranca.setPositionCenter();
            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            }

        }

        cadastroTipoCobranca.repopularCombos();
        cadastroTipoCobranca.botaoLimpar.doClick();
        cadastroTipoCobranca.show();
}//GEN-LAST:event_tipoCobrancaMenuItemActionPerformed

    private String estacao() {

        return (String) " PEMPEC ENTERPRISE   -  Finance     # " + Controller.getIpEstacao() + " " + Controller.getNomeEstacao();

    }

    private void contaBancariaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contaBancariaMenuItemActionPerformed

        if (cadastroContaBancaria == null) {

            try {
                cadastroContaBancaria = new CadastroContaBancaria();
                cadastroContaBancaria.setTitle("Manutenção de Conta Bancária");
                desktopPane.add(cadastroContaBancaria);
                cadastroContaBancaria.setVisible(true);
                cadastroContaBancaria.setPositionCenter();
            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            }

        }

        cadastroContaBancaria.repopularCombos();
        cadastroContaBancaria.botaoLimpar.doClick();
        cadastroContaBancaria.show();

}//GEN-LAST:event_contaBancariaMenuItemActionPerformed

    private void sairMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sairMenuItemActionPerformed

        if (JOptionPane.showConfirmDialog(this, "Confirma sair do sistema?") == JOptionPane.OK_OPTION) {

            System.exit(0);
        }

    }//GEN-LAST:event_sairMenuItemActionPerformed

private void bancoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bancoMenuItemActionPerformed

    if (cadastroBanco == null) {

        try {
            cadastroBanco = new CadastroBanco();
            cadastroBanco.setTitle("Manutenção de Banco");
            desktopPane.add(cadastroBanco);
             cadastroBanco.setVisible(true);
        cadastroBanco.setPositionCenter();

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroBanco.repopularCombos();
    cadastroBanco.botaoLimpar.doClick();
    cadastroBanco.show();

}//GEN-LAST:event_bancoMenuItemActionPerformed

private void tipoStatusMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoStatusMenuItemActionPerformed

    if (cadastroTipoStatus == null) {

        try {
            cadastroTipoStatus = new CadastroTipoStatus();
            cadastroTipoStatus.setTitle("Manutenção de Status");
            desktopPane.add(cadastroTipoStatus);
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTipoStatus.repopularCombos();
    cadastroTipoStatus.botaoLimpar.doClick();
    cadastroTipoStatus.show();

}//GEN-LAST:event_tipoStatusMenuItemActionPerformed

private void tipoSacadoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoSacadoMenuItemActionPerformed

    if (cadastroTipoSacado == null) {

        try {
            cadastroTipoSacado = new CadastroTipoSacado();
            cadastroTipoSacado.setTitle("Manutenção de Tipo Sacado");
            desktopPane.add(cadastroTipoSacado);
            cadastroTipoSacado.setVisible(true);
        cadastroTipoSacado.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTipoSacado.repopularCombos();
    cadastroTipoSacado.botaoLimpar.doClick();
    cadastroTipoSacado.show();

}//GEN-LAST:event_tipoSacadoMenuItemActionPerformed

private void tipoCedenteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoCedenteMenuItemActionPerformed

    if (cadastroTipoCedente == null) {

        try {
            cadastroTipoCedente = new CadastroTipoCedente();
            cadastroTipoCedente.setTitle("Manutenção de Tipo Cedente");
            desktopPane.add(cadastroTipoCedente);
            cadastroTipoCedente.setVisible(true);
        cadastroTipoCedente.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTipoCedente.repopularCombos();
    cadastroTipoCedente.botaoLimpar.doClick();
    cadastroTipoCedente.show();

}//GEN-LAST:event_tipoCedenteMenuItemActionPerformed

private void formaPagamentoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formaPagamentoMenuItemActionPerformed

    if (cadastroFormaPagamento == null) {

        try {
            cadastroFormaPagamento = new CadastroFormaPagamento();
            cadastroFormaPagamento.setTitle("Manutenção de Forma Pagamento");
            desktopPane.add(cadastroFormaPagamento);
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }
    }

    cadastroFormaPagamento.repopularCombos();
    cadastroFormaPagamento.botaoLimpar.doClick();
    cadastroFormaPagamento.show();

}//GEN-LAST:event_formaPagamentoMenuItemActionPerformed

private void localPagamentoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_localPagamentoMenuItemActionPerformed

    if (cadastroLocalPagamento == null) {

        try {
            cadastroLocalPagamento = new CadastroLocalPagamento();
            cadastroLocalPagamento.setTitle("Manutenção de Local Pagamento");
            desktopPane.add(cadastroLocalPagamento);
            cadastroLocalPagamento.setVisible(true);
            cadastroLocalPagamento.setPositionCenter();

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroLocalPagamento.repopularCombos();
    cadastroLocalPagamento.botaoLimpar.doClick();
    cadastroLocalPagamento.show();

}//GEN-LAST:event_localPagamentoMenuItemActionPerformed

private void centroCustoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centroCustoMenuItemActionPerformed

    if (cadastroCentroCusto == null) {

        try {
            cadastroCentroCusto = new CadastroCentroCusto();
            cadastroCentroCusto.setTitle("Manutenção de Centro de Custos");
            desktopPane.add(cadastroCentroCusto);
             cadastroCentroCusto.setVisible(true);
        cadastroCentroCusto.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroCentroCusto.repopularCombos();
    cadastroCentroCusto.botaoLimpar.doClick();
    cadastroCentroCusto.show();

}//GEN-LAST:event_centroCustoMenuItemActionPerformed

private void historicoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historicoMenuItemActionPerformed

    if (cadastroHistorico == null) {

        try {
            cadastroHistorico = new CadastroHistorico();
            cadastroHistorico.setTitle("Manutenção de Históricos");
            desktopPane.add(cadastroHistorico);
            cadastroHistorico.setVisible(true);
            cadastroHistorico.setPositionCenter();

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroHistorico.repopularCombos();
    cadastroHistorico.botaoLimpar.doClick();
    cadastroHistorico.show();
}//GEN-LAST:event_historicoMenuItemActionPerformed

private void tituloPagarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tituloPagarMenuItemActionPerformed

    if (cadastroTituloPagar == null) {

        try {
            cadastroTituloPagar = new CadastroTituloPagar();
            cadastroTituloPagar.setTitle("Manutenção de Título a Pagar");
            desktopPane.add(cadastroTituloPagar);
            cadastroTituloPagar.setVisible(true);
            cadastroTituloPagar.setPositionCenter();

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTituloPagar.repopularCombos(Tela.TELA_PRINCIPAL, null);
    cadastroTituloPagar.botaoLimpar.doClick();
    cadastroTituloPagar.show();

}//GEN-LAST:event_tituloPagarMenuItemActionPerformed

private void lancamentoTituloPagarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lancamentoTituloPagarMenuItemActionPerformed

    if (cadastroTituloPagarBaixa == null) {

        try {
            cadastroTituloPagarBaixa = new CadastroTituloPagarBaixa();
            cadastroTituloPagarBaixa.setTitle("Manutenção de Baixa de Título a Pagar");
            desktopPane.add(cadastroTituloPagarBaixa);
            cadastroTituloPagarBaixa.setVisible(true);
            cadastroTituloPagarBaixa.setPositionCenter();

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTituloPagarBaixa.repopularCombos(Tela.TELA_PRINCIPAL, null);
    cadastroTituloPagarBaixa.botaoLimpar.doClick();
    cadastroTituloPagarBaixa.show();

}//GEN-LAST:event_lancamentoTituloPagarMenuItemActionPerformed

private void tituloReceberMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tituloReceberMenuItemActionPerformed

    if (cadastroTituloReceber == null) {

        try {
            cadastroTituloReceber = new CadastroTituloReceber();
            cadastroTituloReceber.setTitle("Manutenção de Título a Receber");
            desktopPane.add(cadastroTituloReceber);
            cadastroTituloReceber.setVisible(true);
            cadastroTituloReceber.setPositionCenter();

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTituloReceber.repopularCombos(Tela.TELA_PRINCIPAL, null);
    cadastroTituloReceber.botaoLimpar.doClick();
    cadastroTituloReceber.show();

}//GEN-LAST:event_tituloReceberMenuItemActionPerformed

private void lancamentoTituloReceberMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lancamentoTituloReceberMenuItemActionPerformed

    if (cadastroTituloReceberBaixa == null) {

        try {
            cadastroTituloReceberBaixa = new CadastroTituloReceberBaixa();
            cadastroTituloReceberBaixa.setTitle("Manutenção de Baixa de Título a Receber");
            desktopPane.add(cadastroTituloReceberBaixa);
            cadastroTituloReceberBaixa.setVisible(true);
            cadastroTituloReceberBaixa.setPositionCenter();

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTituloReceberBaixa.repopularCombos(Tela.TELA_PRINCIPAL, null);
    cadastroTituloReceberBaixa.botaoLimpar.doClick();
    cadastroTituloReceberBaixa.show();

}//GEN-LAST:event_lancamentoTituloReceberMenuItemActionPerformed

private void sacadoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sacadoMenuItemActionPerformed

    if (cadastroSacado == null) {

        try {
            cadastroSacado = new CadastroSacado();
            cadastroSacado.setTitle("Manutenção de Sacados");
            desktopPane.add(cadastroSacado);
            cadastroSacado.setVisible(true);
            cadastroSacado.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroSacado.repopularCombos(Tela.TELA_PRINCIPAL, null);
    cadastroSacado.botaoLimpar.doClick();
    cadastroSacado.show();

}//GEN-LAST:event_sacadoMenuItemActionPerformed

private void cedenteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cedenteMenuItemActionPerformed

    if (cadastroCedente == null) {

        try {
            cadastroCedente = new CadastroCedente();
            cadastroCedente.setTitle("Manutenção de Cedentes");
            desktopPane.add(cadastroCedente);
            cadastroCedente.setVisible(true);
            cadastroCedente.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroCedente.repopularCombos(Tela.TELA_PRINCIPAL, null);
    cadastroCedente.botaoLimpar.doClick();
    cadastroCedente.show();

}//GEN-LAST:event_cedenteMenuItemActionPerformed

private void tipoAcrescimoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAcrescimoMenuItemActionPerformed

    if (cadastroTipoAcrescimo == null) {

        try {
            cadastroTipoAcrescimo = new CadastroTipoAcrescimo();
            cadastroTipoAcrescimo.setTitle("Manutenção de Acréscimos");
            desktopPane.add(cadastroTipoAcrescimo);
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTipoAcrescimo.repopularCombos();
    cadastroTipoAcrescimo.botaoLimpar.doClick();
    cadastroTipoAcrescimo.show();

}//GEN-LAST:event_tipoAcrescimoMenuItemActionPerformed

private void tipoDeducaoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoDeducaoMenuItemActionPerformed

    if (cadastroTipoDeducao == null) {

        try {
            cadastroTipoDeducao = new CadastroTipoDeducao();
            cadastroTipoDeducao.setTitle("Manutenção de Deduções");
            desktopPane.add(cadastroTipoDeducao);
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTipoDeducao.repopularCombos();
    cadastroTipoDeducao.botaoLimpar.doClick();
    cadastroTipoDeducao.show();
}//GEN-LAST:event_tipoDeducaoMenuItemActionPerformed

private void funcionarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_funcionarioMenuItemActionPerformed

    if (cadastroFuncionario == null) {

        try {
            cadastroFuncionario = new CadastroFuncionario();
            cadastroFuncionario.setTitle("Manutenção de Colaboradores");
            desktopPane.add(cadastroFuncionario);
            cadastroFuncionario.setVisible(true);
        cadastroFuncionario.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroFuncionario.repopularCombos();
    cadastroFuncionario.botaoLimpar.doClick();
    cadastroFuncionario.show();

}//GEN-LAST:event_funcionarioMenuItemActionPerformed

private void tipoOperacaoBancariaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoOperacaoBancariaMenuItemActionPerformed

    if (cadastroTipoOperacaoBancaria == null) {

        try {
            cadastroTipoOperacaoBancaria = new CadastroTipoOperacaoBancaria();
            cadastroTipoOperacaoBancaria.setTitle("Manutenção de Operações Bancárias");
            desktopPane.add(cadastroTipoOperacaoBancaria);
            cadastroTipoOperacaoBancaria.setVisible(true);
        cadastroTipoOperacaoBancaria.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTipoOperacaoBancaria.repopularCombos();
    cadastroTipoOperacaoBancaria.botaoLimpar.doClick();
    cadastroTipoOperacaoBancaria.show();

}//GEN-LAST:event_tipoOperacaoBancariaMenuItemActionPerformed

    public void MessageUtil() {
        JOptionPane.showMessageDialog(null, "Lamentamos, a funcionalidade não está disponível. \n Módulo em desenvolvimento!");
    }
private void conciliacaoLancamentoMenuItem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conciliacaoLancamentoMenuItem
    MessageUtil();

}//GEN-LAST:event_conciliacaoLancamentoMenuItem

private void relatorioFluxoCaixaMenuItem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioFluxoCaixaMenuItem

    if (relatorioFluxoCaixa == null) {

        try {
            relatorioFluxoCaixa = new RelatorioFluxoCaixa();
            relatorioFluxoCaixa.setTitle("Relatório Fluxo de Caixa");
            desktopPane.add(relatorioFluxoCaixa);
             relatorioFluxoCaixa.setVisible(true);
        relatorioFluxoCaixa.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    relatorioFluxoCaixa.repopularCombos();
    relatorioFluxoCaixa.botaoLimpar.doClick();
    relatorioFluxoCaixa.show();

}//GEN-LAST:event_relatorioFluxoCaixaMenuItem

private void relatorioContasPagarMenuItem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioContasPagarMenuItem

    if (relatorioContasPagar == null) {

        try {
            relatorioContasPagar = new RelatorioContasPagar();
            relatorioContasPagar.setTitle("Relatório Contas a Pagar");
            desktopPane.add(relatorioContasPagar);
            relatorioContasPagar.setVisible(true);
        relatorioContasPagar.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    //relatorioContasPagar.repopularCombos();
    relatorioContasPagar.repopularCombos(Tela.TELA_PRINCIPAL, null);
    relatorioContasPagar.botaoLimpar.doClick();
    relatorioContasPagar.show();

}//GEN-LAST:event_relatorioContasPagarMenuItem

private void relatorioContasReceberMenuItem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioContasReceberMenuItem

    if (relatorioContasReceber == null) {

        try {
            relatorioContasReceber = new RelatorioContasReceber();
            relatorioContasReceber.setTitle("Relatório Contas a Receber");
            desktopPane.add(relatorioContasReceber);
            relatorioContasReceber.setVisible(true);
        relatorioContasReceber.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    relatorioContasReceber.repopularCombos(Tela.TELA_PRINCIPAL, null);
    relatorioContasReceber.botaoLimpar.doClick();
    relatorioContasReceber.show();

}//GEN-LAST:event_relatorioContasReceberMenuItem

private void relatorioExtratoBancarioMenuItem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioExtratoBancarioMenuItem

    if (relatorioExtratoBancario == null) {

        try {
            relatorioExtratoBancario = new RelatorioExtratoBancario();
            relatorioExtratoBancario.setTitle("Relatório Extrato Bancário");
            desktopPane.add(relatorioExtratoBancario);
            relatorioExtratoBancario.setVisible(true);
        relatorioExtratoBancario.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    relatorioExtratoBancario.repopularCombos();
    relatorioExtratoBancario.botaoLimpar.doClick();
    relatorioExtratoBancario.show();

}//GEN-LAST:event_relatorioExtratoBancarioMenuItem

private void sobreMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sobreMenuItem1ActionPerformed

    if (aboutFinance == null) {

        try {
            aboutFinance = new AboutFinance();
            aboutFinance.setTitle("Sobre o Finance");
            desktopPane.add(aboutFinance);
            aboutFinance.setVisible(true);
        aboutFinance.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    aboutFinance.show();

}//GEN-LAST:event_sobreMenuItem1ActionPerformed

private void bairroMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bairroMenuItemActionPerformed

    if (cadastroBairro == null) {

        try {
            cadastroBairro = new CadastroBairro();
            cadastroBairro.setTitle("Manutenção de Bairros");
            desktopPane.add(cadastroBairro);
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroBairro.repopularCombos();
    cadastroBairro.botaoLimpar.doClick();
    cadastroBairro.show();

}//GEN-LAST:event_bairroMenuItemActionPerformed

private void cidadeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cidadeMenuItemActionPerformed

    if (cadastroCidade == null) {

        try {
            cadastroCidade = new CadastroCidade();
            cadastroCidade.setTitle("Manutenção de Cidades");
            desktopPane.add(cadastroCidade);
            cadastroCidade.setVisible(true);
        cadastroCidade.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroCidade.repopularCombos();
    cadastroCidade.botaoLimpar.doClick();
    cadastroCidade.show();

}//GEN-LAST:event_cidadeMenuItemActionPerformed

private void gerarChequesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerarChequesMenuItemActionPerformed

    if (geradorCheque == null) {

        try {
            geradorCheque = new GeradorCheque();
            geradorCheque.setTitle("Manutenção de Cheques");
            desktopPane.add(geradorCheque);
            geradorCheque.setVisible(true);
        geradorCheque.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    geradorCheque.repopularCombos();
    geradorCheque.botaoLimpar.doClick();
    geradorCheque.show();


}//GEN-LAST:event_gerarChequesMenuItemActionPerformed

private void compensarChequeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compensarChequeMenuItemActionPerformed

    if (compensadorCheque == null) {

        try {
            compensadorCheque = new CompensadorCheque();
            compensadorCheque.setTitle("Compensação de Cheques");
            desktopPane.add(compensadorCheque);
            compensadorCheque.setVisible(true);
        compensadorCheque.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    compensadorCheque.repopularCombos();
    compensadorCheque.botaoLimpar.doClick();
    compensadorCheque.show();

}//GEN-LAST:event_compensarChequeMenuItemActionPerformed

private void transferenciaEntreContasMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferenciaEntreContasMenuItemActionPerformed

    if (transferenciaEntreContas == null) {

        try {
            transferenciaEntreContas = new TransferenciaEntreContas();
            transferenciaEntreContas.setTitle("Transferência de Fundos");
            desktopPane.add(transferenciaEntreContas);
            transferenciaEntreContas.setVisible(true);
            transferenciaEntreContas.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    transferenciaEntreContas.repopularCombos();
    transferenciaEntreContas.botaoLimpar.doClick();
    transferenciaEntreContas.show();

}//GEN-LAST:event_transferenciaEntreContasMenuItemActionPerformed

private void cancelarBaixaTituloPagarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBaixaTituloPagarMenuItemActionPerformed

    if (excluirTituloPagarBaixa == null) {

        try {
            excluirTituloPagarBaixa = new ExcluirTituloPagarBaixa();
            excluirTituloPagarBaixa.setTitle("Excluir Pagamentos de Títulos a Pagar");
            desktopPane.add(excluirTituloPagarBaixa);
            excluirTituloPagarBaixa.setVisible(true);
        excluirTituloPagarBaixa.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    excluirTituloPagarBaixa.repopularCombos();
    excluirTituloPagarBaixa.botaoLimpar.doClick();
    excluirTituloPagarBaixa.show();

}//GEN-LAST:event_cancelarBaixaTituloPagarMenuItemActionPerformed

private void organizacaoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_organizacaoMenuItemActionPerformed

    if (cadastroOrganizacao == null) {

        try {
            cadastroOrganizacao = new CadastroOrganizacao();
            cadastroOrganizacao.setTitle("Organização");
            desktopPane.add(cadastroOrganizacao);
            cadastroOrganizacao.setVisible(true);
        cadastroOrganizacao.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroOrganizacao.repopularCombos();
    cadastroOrganizacao.botaoLimpar.doClick();
    cadastroOrganizacao.show();

}//GEN-LAST:event_organizacaoMenuItemActionPerformed

private void usuarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioMenuItemActionPerformed

    if (cadastroUsuario == null) {

        try {
            cadastroUsuario = new CadastroUsuario();
            cadastroUsuario.setTitle("Usuário");
            desktopPane.add(cadastroUsuario);
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroUsuario.repopularCombos();
    cadastroUsuario.botaoLimpar.doClick();
    cadastroUsuario.show();

}//GEN-LAST:event_usuarioMenuItemActionPerformed

private void grupoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grupoMenuItemActionPerformed

    if (cadastroGrupo == null) {

        try {
            cadastroGrupo = new CadastroGrupo();
            cadastroGrupo.setTitle("Grupos");
            desktopPane.add(cadastroGrupo);
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroGrupo.repopularCombos();
    cadastroGrupo.botaoLimpar.doClick();
    cadastroGrupo.show();
}//GEN-LAST:event_grupoMenuItemActionPerformed

private void contaBancariaLancamentoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contaBancariaLancamentoMenuItemActionPerformed

    if (contaBancariaLancamento == null) {

        try {
            contaBancariaLancamento = new ContaBancariaLancamento();
            contaBancariaLancamento.setTitle("Lançamentos Bancários");
            desktopPane.add(contaBancariaLancamento);
            contaBancariaLancamento.setVisible(true);
            contaBancariaLancamento.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    contaBancariaLancamento.repopularCombos();
    contaBancariaLancamento.botaoLimparCredito.doClick();
    contaBancariaLancamento.show();

}//GEN-LAST:event_contaBancariaLancamentoMenuItemActionPerformed

private void particionadorTituloPagarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_particionadorTituloPagarMenuItemActionPerformed

    if (particionadorTituloPagar == null) {

        try {
            particionadorTituloPagar = new ParticionadorTituloPagar();
            particionadorTituloPagar.setTitle("Particionar Títulos");
            desktopPane.add(particionadorTituloPagar);
            particionadorTituloPagar.setVisible(true);
        particionadorTituloPagar.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    particionadorTituloPagar.repopularCombos();
    particionadorTituloPagar.botaoLimpar.doClick();
    particionadorTituloPagar.show();

}//GEN-LAST:event_particionadorTituloPagarMenuItemActionPerformed

private void lancamentoTesourariaMenuItem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lancamentoTesourariaMenuItem

    if (tesouraiaLancamento == null) {

        try {
            tesouraiaLancamento = new TesouraiaLancamento();
            tesouraiaLancamento.setTitle("Tesouraria - Lançamentos");
            desktopPane.add(tesouraiaLancamento);
             tesouraiaLancamento.setVisible(true);
        tesouraiaLancamento.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    tesouraiaLancamento.repopularCombos();
    tesouraiaLancamento.botaoLimparCredito.doClick();
    tesouraiaLancamento.show();

}//GEN-LAST:event_lancamentoTesourariaMenuItem

private void emitirReciboContaPagarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emitirReciboContaPagarMenuItemActionPerformed

    if (reciboContaPagar == null) {

        try {
            reciboContaPagar = new ReciboContaPagar();
            reciboContaPagar.setTitle("Recibo Contas a Pagar");
            desktopPane.add(reciboContaPagar);
            reciboContaPagar.setVisible(true);
        reciboContaPagar.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    reciboContaPagar.repopularCombos();
    reciboContaPagar.botaoLimpar.doClick();
    reciboContaPagar.show();
}//GEN-LAST:event_emitirReciboContaPagarMenuItemActionPerformed

private void emitirReciboTesourariaDebitoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emitirReciboTesourariaDebitoMenuItemActionPerformed

    if (reciboTesourariaDespesa == null) {

        try {
            reciboTesourariaDespesa = new ReciboTesourariaDespesa();
            reciboTesourariaDespesa.setTitle("Emitir Recibo Débitos");
            desktopPane.add(reciboTesourariaDespesa);
            reciboTesourariaDespesa.setVisible(true);
        reciboTesourariaDespesa.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    reciboTesourariaDespesa.repopularCombos();
    reciboTesourariaDespesa.botaoLimpar.doClick();
    reciboTesourariaDespesa.show();

}//GEN-LAST:event_emitirReciboTesourariaDebitoMenuItemActionPerformed

private void emitirReciboTesourariaCreditoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emitirReciboTesourariaCreditoMenuItemActionPerformed

    if (reciboTesourariaReceita == null) {

        try {
            reciboTesourariaReceita = new ReciboTesourariaReceita();
            reciboTesourariaReceita.setTitle("Emitir Recibo Créditos");
            desktopPane.add(reciboTesourariaReceita);
            reciboTesourariaReceita.setVisible(true);
        reciboTesourariaReceita.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    reciboTesourariaReceita.repopularCombos();
    reciboTesourariaReceita.botaoLimpar.doClick();
    reciboTesourariaReceita.show();

}//GEN-LAST:event_emitirReciboTesourariaCreditoMenuItemActionPerformed

private void emitirReciboCopiaChequeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emitirReciboCopiaChequeMenuItemActionPerformed

    if (reciboCopiaCheque == null) {

        try {
            reciboCopiaCheque = new ReciboCopiaCheque();
            reciboCopiaCheque.setTitle("Emitir Cópia de Cheques");
            desktopPane.add(reciboCopiaCheque);
             reciboCopiaCheque.setVisible(true);
        reciboCopiaCheque.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    reciboCopiaCheque.repopularCombos();
    reciboCopiaCheque.botaoLimpar.doClick();
    reciboCopiaCheque.show();

}//GEN-LAST:event_emitirReciboCopiaChequeMenuItemActionPerformed

private void relatorioDmsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioDmsMenuItemActionPerformed

    if (relatorioDMS == null) {

        try {
            relatorioDMS = new RelatorioDMS();
            relatorioDMS.setTitle("Declaração Mensal de Serviços");
            desktopPane.add(relatorioDMS);
            relatorioDMS.setVisible(true);
        relatorioDMS.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    relatorioDMS.repopularCombos();
    relatorioDMS.botaoLimpar.doClick();
    relatorioDMS.show();


}//GEN-LAST:event_relatorioDmsMenuItemActionPerformed

private void relatorioMovimentoDiarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioMovimentoDiarioMenuItemActionPerformed

    if (relatorioMovimentoDiario == null) {

        try {
            relatorioMovimentoDiario = new RelatorioMovimentoDiario();
            relatorioMovimentoDiario.setTitle("Relatório Movimento Diário");
            desktopPane.add(relatorioMovimentoDiario);
            relatorioMovimentoDiario.setVisible(true);
        relatorioMovimentoDiario.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    relatorioMovimentoDiario.repopularCombos();
    relatorioMovimentoDiario.botaoLimpar.doClick();
    relatorioMovimentoDiario.show();

}//GEN-LAST:event_relatorioMovimentoDiarioMenuItemActionPerformed

private void impressaoChequeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impressaoChequeMenuItemActionPerformed

    if (impressaoCheque == null) {

        try {
            impressaoCheque = new ImpressaoCheque();
            impressaoCheque.setTitle("Imprimir Cheque");
            desktopPane.add(impressaoCheque);
            impressaoCheque.setVisible(true);
        impressaoCheque.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    impressaoCheque.repopularCombos();
    impressaoCheque.botaoLimpar.doClick();
    impressaoCheque.show();

}//GEN-LAST:event_impressaoChequeMenuItemActionPerformed

private void layoutChequeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_layoutChequeMenuItemActionPerformed

    if (layoutCheque == null) {

        try {
            layoutCheque = new LayoutCheque();
            layoutCheque.setTitle("Configurar Layout do Cheque");
            desktopPane.add(layoutCheque);
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    layoutCheque.repopularCombos();
    layoutCheque.botaoLimpar.doClick();
    layoutCheque.show();

}//GEN-LAST:event_layoutChequeMenuItemActionPerformed

private void particionadorTituloReceberMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_particionadorTituloReceberMenuItemActionPerformed

    if (particionadorTituloReceber == null) {

        try {
            particionadorTituloReceber = new ParticionadorTituloReceber();
            particionadorTituloReceber.setTitle("Particionador de Título a Receber");
            desktopPane.add(particionadorTituloReceber);
             particionadorTituloReceber.setVisible(true);
        particionadorTituloReceber.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    particionadorTituloReceber.repopularCombos();
    particionadorTituloReceber.botaoLimpar.doClick();
    particionadorTituloReceber.show();


}//GEN-LAST:event_particionadorTituloReceberMenuItemActionPerformed

private void extratoTesourariaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_extratoTesourariaMenuItemActionPerformed

    if (extratoTesouraria == null) {

        try {
            extratoTesouraria = new ExtratoTesouraria();
            extratoTesouraria.setTitle("Extrato Tesouraria");
            desktopPane.add(extratoTesouraria);
            extratoTesouraria.setVisible(true);
        extratoTesouraria.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    extratoTesouraria.repopularCombos();
    extratoTesouraria.botaoLimpar.doClick();
    extratoTesouraria.show();

}//GEN-LAST:event_extratoTesourariaMenuItemActionPerformed

private void tipoNotaFiscalMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoNotaFiscalMenuItemActionPerformed

    if (cadastroTipoNotaFiscal == null) {

        try {
            cadastroTipoNotaFiscal = new CadastroTipoNotaFiscal();
            cadastroTipoNotaFiscal.setTitle("Tipo Nota Fiscal");
            desktopPane.add(cadastroTipoNotaFiscal);
            cadastroTipoNotaFiscal.setVisible(true);
        cadastroTipoNotaFiscal.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroTipoNotaFiscal.repopularCombos();
    cadastroTipoNotaFiscal.botaoLimpar.doClick();
    cadastroTipoNotaFiscal.show();

}//GEN-LAST:event_tipoNotaFiscalMenuItemActionPerformed

private void exportMegaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportMegaMenuItemActionPerformed

    if (Controller.getOrganizacao().getSistemaContabil() != null && !Controller.getOrganizacao().getSistemaContabil().isEmpty()) {
        if (exportacaoMegaContabil == null) {

            try {
                exportacaoMegaContabil = new ExportacaoMegaContabil();
                exportacaoMegaContabil.setTitle("Exportação Mega Contábil");
                desktopPane.add(exportacaoMegaContabil);
                exportacaoMegaContabil.setVisible(true);
        exportacaoMegaContabil.setPositionCenter();
            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            }

        }
    } else {

        JOptionPane.showMessageDialog(null, "Função não permitida!");

    }

    exportacaoMegaContabil.repopularCombos();
    exportacaoMegaContabil.botaoLimpar.doClick();
    exportacaoMegaContabil.show();

}//GEN-LAST:event_exportMegaMenuItemActionPerformed

private void emitirReciboContaReceberMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emitirReciboContaReceberMenuItemActionPerformed
    if (reciboContaReceber == null) {

        try {
            reciboContaReceber = new ReciboContaReceber();
            reciboContaReceber.setTitle("Recibo Contas a Receber");
            desktopPane.add(reciboContaReceber);
            reciboContaReceber.setVisible(true);
        reciboContaReceber.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    reciboContaReceber.repopularCombos();
    reciboContaReceber.botaoLimpar.doClick();
    reciboContaReceber.show();

}//GEN-LAST:event_emitirReciboContaReceberMenuItemActionPerformed

private void cancelaBaixaTituloReceberMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelaBaixaTituloReceberMenuItemActionPerformed

    if (excluirTituloReceberBaixa == null) {

        try {
            excluirTituloReceberBaixa = new ExcluirTituloReceberBaixa();
            excluirTituloReceberBaixa.setTitle("Excluir Recebimento de Títulos");
            desktopPane.add(excluirTituloReceberBaixa);
            excluirTituloReceberBaixa.setVisible(true);
        excluirTituloReceberBaixa.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    excluirTituloReceberBaixa.repopularCombos();
    excluirTituloReceberBaixa.botaoLimpar.doClick();
    excluirTituloReceberBaixa.show();


}//GEN-LAST:event_cancelaBaixaTituloReceberMenuItemActionPerformed

private void compensarMultiChequeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compensarMultiChequeMenuItemActionPerformed

    if (compensadorMultiplosCheques == null) {

        try {
            compensadorMultiplosCheques = new CompensadorMultiplosCheques();
            compensadorMultiplosCheques.setTitle("Compensação de Vários Cheques");
            desktopPane.add(compensadorMultiplosCheques);
            compensadorMultiplosCheques.setVisible(true);
        compensadorMultiplosCheques.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    compensadorMultiplosCheques.repopularCombos();
    compensadorMultiplosCheques.botaoLimpar.doClick();
    compensadorMultiplosCheques.show();

}//GEN-LAST:event_compensarMultiChequeMenuItemActionPerformed

private void manutencaoBancoDadosMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manutencaoBancoDadosMenuActionPerformed

    try {
//       
        manutencaoBancoDados = new ManutencaoBancoDados();
        manutencaoBancoDados.setTitle("Manutencao Banco Dados");
        desktopPane.add(manutencaoBancoDados);
        manutencaoBancoDados.setVisible(true);
        manutencaoBancoDados.setPositionCenter();
    } catch (final SystemException ex) {

        final File file = PrintScreen.capture();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                tratamentoExcecao(ex, file);

            }
        });

    }

    manutencaoBancoDados.repopularCombos();
    manutencaoBancoDados.show();
}//GEN-LAST:event_manutencaoBancoDadosMenuActionPerformed

private void telaAvisosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telaAvisosMenuItemActionPerformed

    if (telaAvisosTitulos == null) {

        try {
            telaAvisosTitulos = new TelaAvisosTitulos();
            //telaAvisosTitulos.setTitle("Compensação de Vários Cheques");
            desktopPane.add(telaAvisosTitulos);
            telaAvisosTitulos.setVisible(true);
        telaAvisosTitulos.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    telaAvisosTitulos.repopularCombos();
    telaAvisosTitulos.show();
}//GEN-LAST:event_telaAvisosMenuItemActionPerformed

private void relatorioExportacaoMegaContabilMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioExportacaoMegaContabilMenuItemActionPerformed

    if (relatorioExportacaoMegaContabil == null) {

        try {
            relatorioExportacaoMegaContabil = new RelatorioExportacaoMegaContabil();
            desktopPane.add(relatorioExportacaoMegaContabil);
            relatorioExportacaoMegaContabil.setVisible(true);
        relatorioExportacaoMegaContabil.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    relatorioExportacaoMegaContabil.repopularCombos();
    relatorioExportacaoMegaContabil.botaoLimpar.doClick();
    relatorioExportacaoMegaContabil.show();

}//GEN-LAST:event_relatorioExportacaoMegaContabilMenuItemActionPerformed

private void removExportMegaMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removExportMegaMenuItemActionPerformed
    if (excluirExportacao == null) {

        try {
            excluirExportacao = new ExcluirExportacaoMegaContabil();
            desktopPane.add(excluirExportacao);
            excluirExportacao.setVisible(true);
        excluirExportacao.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    excluirExportacao.repopularCombos();
    excluirExportacao.botaoLimpar.doClick();
    excluirExportacao.show();
}//GEN-LAST:event_removExportMegaMenuItemActionPerformed

private void TelaChequesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelaChequesMenuItemActionPerformed
    if (telaChequesTesouraria == null) {

        try {
            telaChequesTesouraria = new TelaChequesTesouraria();
            desktopPane.add(telaChequesTesouraria);
            telaChequesTesouraria.setVisible(true);
        telaChequesTesouraria.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    telaChequesTesouraria.repopularCombos();
    telaChequesTesouraria.botaoLimparConsulta.doClick();
    telaChequesTesouraria.show();
}//GEN-LAST:event_TelaChequesMenuItemActionPerformed

private void ListagemChequesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListagemChequesMenuItemActionPerformed

    if (listagemChequesTesouraria == null) {

        try {
            listagemChequesTesouraria = new ListagemChequesTesouraria();
            desktopPane.add(listagemChequesTesouraria);
            listagemChequesTesouraria.setVisible(true);
        listagemChequesTesouraria.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    listagemChequesTesouraria.repopularCombos();
    listagemChequesTesouraria.botaoLimpar.doClick();
    listagemChequesTesouraria.show();

}//GEN-LAST:event_ListagemChequesMenuItemActionPerformed

private void transfereBancoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transfereBancoMenuItemActionPerformed
    if (transfereTesourariaBanco == null) {

        try {
            transfereTesourariaBanco = new TransfereTesourariaBanco();
            desktopPane.add(transfereTesourariaBanco);
            transfereTesourariaBanco.setVisible(true);
        transfereTesourariaBanco.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    transfereTesourariaBanco.repopularCombos();
    transfereTesourariaBanco.botaoLimpar.doClick();
    transfereTesourariaBanco.show();
}//GEN-LAST:event_transfereBancoMenuItemActionPerformed

private void baixaTituloLoteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baixaTituloLoteMenuItemActionPerformed
    if (telaTituloPagar == null) {

        try {
            telaTituloPagar = new TelaTituloPagar();
            desktopPane.add(telaTituloPagar);
            telaTituloPagar.setVisible(true);
            telaTituloPagar.setPositionCenter();

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    telaTituloPagar.repopularCombos();
    telaTituloPagar.botaoLimpar.doClick();
    telaTituloPagar.show();
}//GEN-LAST:event_baixaTituloLoteMenuItemActionPerformed

private void saldoBancarioMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saldoBancarioMenuItemActionPerformed

    if (contaBancariaSaldo != null) {
        contaBancariaSaldo = null;
    }
    try {
        contaBancariaSaldo = new ContaBancariaSaldo();
        desktopPane.add(contaBancariaSaldo);
        contaBancariaSaldo.setVisible(true);
        contaBancariaSaldo.setPositionCenter();
    } catch (final SystemException ex) {

        final File file = PrintScreen.capture();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                tratamentoExcecao(ex, file);

            }
        });

    }

    contaBancariaSaldo.repopularCombos();
    contaBancariaSaldo.botaoLimpar.doClick();
    contaBancariaSaldo.show();
}//GEN-LAST:event_saldoBancarioMenuItemActionPerformed

private void configuracoesMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracoesMenuItemActionPerformed

    if (cadastroConfiguracoes == null) {

        try {
            cadastroConfiguracoes = new CadastroConfiguracoes();
            desktopPane.add(cadastroConfiguracoes);
            cadastroConfiguracoes.setVisible(true);
            cadastroConfiguracoes.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroConfiguracoes.repopularCombos();
    cadastroConfiguracoes.show();

}//GEN-LAST:event_configuracoesMenuItemActionPerformed

private void emailMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailMenuItemActionPerformed

    if (telaEnvioEmail == null) {

        try {
            telaEnvioEmail = new TelaEnvioEmail();
            desktopPane.add(telaEnvioEmail);
            telaEnvioEmail.setVisible(true);
            telaEnvioEmail.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    telaEnvioEmail.repopularCombos();
    telaEnvioEmail.botaoLimpar.doClick();
    telaEnvioEmail.show();

}//GEN-LAST:event_emailMenuItemActionPerformed

private void reciboTransferenciaMenuItemrelatorioExtratoBancarioMenuItem(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reciboTransferenciaMenuItemrelatorioExtratoBancarioMenuItem

    if (reciboTransferenciaBancaria == null) {

        try {
            reciboTransferenciaBancaria = new ReciboTransferenciaBancaria();
            desktopPane.add(reciboTransferenciaBancaria);
            reciboTransferenciaBancaria.setVisible(true);
            reciboTransferenciaBancaria.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    reciboTransferenciaBancaria.repopularCombos();
    reciboTransferenciaBancaria.botaoLimpar.doClick();
    reciboTransferenciaBancaria.show();

}//GEN-LAST:event_reciboTransferenciaMenuItemrelatorioExtratoBancarioMenuItem

private void cancelamentoLoteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelamentoLoteMenuItemActionPerformed

    if (excluirLotePagamento == null) {

        try {
            excluirLotePagamento = new ExcluirLotePagamento();
            desktopPane.add(excluirLotePagamento);
            excluirLotePagamento.setVisible(true);
            excluirLotePagamento.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    excluirLotePagamento.repopularCombos();
    excluirLotePagamento.botaoLimpar.doClick();
    excluirLotePagamento.show();

}//GEN-LAST:event_cancelamentoLoteMenuItemActionPerformed

private void parametroslMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_parametroslMenuItemActionPerformed

    if (parametros == null) {

        try {
            parametros = new TelaParametros();
            desktopPane.add(parametros);
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    parametros.repopularCombos();
    parametros.show();

}//GEN-LAST:event_parametroslMenuItemActionPerformed

private void jMenuItemRLoteDepositoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRLoteDepositoActionPerformed

    if (relatorioLoteDeposito == null) {

        try {
            relatorioLoteDeposito = new RelatorioLoteDeposito();
            desktopPane.add(relatorioLoteDeposito);
            relatorioLoteDeposito.setVisible(true);
            relatorioLoteDeposito.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    relatorioLoteDeposito.repopularCombos();
    relatorioLoteDeposito.botaoLimpar.doClick();
    relatorioLoteDeposito.show();

}//GEN-LAST:event_jMenuItemRLoteDepositoActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    if (relatorioLoteExportacao == null) {

        try {
            relatorioLoteExportacao = new RelatorioLoteExportacao();
            desktopPane.add(relatorioLoteExportacao);
            relatorioLoteExportacao.setVisible(true);
            relatorioLoteExportacao.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    relatorioLoteExportacao.repopularCombos();
    relatorioLoteExportacao.botaoLimpar.doClick();
    relatorioLoteExportacao.show();
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void relatorioLotePagamentoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_relatorioLotePagamentoMenuItemActionPerformed
    if (relatorioLotePagamento == null) {

        try {
            relatorioLotePagamento = new RelatorioLotePagamento();
            desktopPane.add(relatorioLotePagamento);
            relatorioLotePagamento.setVisible(true);
            relatorioLotePagamento.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    relatorioLotePagamento.repopularCombos();
    relatorioLotePagamento.botaoLimpar.doClick();
    relatorioLotePagamento.show();


}//GEN-LAST:event_relatorioLotePagamentoMenuItemActionPerformed

private void jMenuItemTransfereBancoTesourariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemTransfereBancoTesourariaActionPerformed
    if (transfereBancoTesouraria == null) {

        try {
            transfereBancoTesouraria = new TransfereBancoTesouraria();
            desktopPane.add(transfereBancoTesouraria);
            transfereBancoTesouraria.setVisible(true);
            transfereBancoTesouraria.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }
    transfereBancoTesouraria.repopularCombos();
    transfereBancoTesouraria.botaoLimpar.doClick();
    transfereBancoTesouraria.show();
}//GEN-LAST:event_jMenuItemTransfereBancoTesourariaActionPerformed

private void impressaoMultiplosChequeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impressaoMultiplosChequeMenuItemActionPerformed

    if (impressaoMultiplosCheque == null) {

        try {
            impressaoMultiplosCheque = new ImpressaoMultiplosCheque();
            impressaoMultiplosCheque.setTitle("Imprimir Mult Cheque");
            desktopPane.add(impressaoMultiplosCheque);
            impressaoMultiplosCheque.setVisible(true);
            impressaoMultiplosCheque.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    impressaoMultiplosCheque.repopularCombos();
    impressaoMultiplosCheque.botaoLimpar.doClick();
    impressaoMultiplosCheque.show();

}//GEN-LAST:event_impressaoMultiplosChequeMenuItemActionPerformed

private void contaContabilMenuItemhistoricoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contaContabilMenuItemhistoricoMenuItemActionPerformed

    if (cadastroContaContabil == null) {

        try {
            cadastroContaContabil = new CadastroContaContabil();
            cadastroContaContabil.setTitle("Conta Contabil");
            desktopPane.add(cadastroContaContabil);
            cadastroContaContabil.setVisible(true);
            cadastroContaContabil.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroContaContabil.repopularCombos();
    cadastroContaContabil.botaoLimpar.doClick();
    cadastroContaContabil.show();

}//GEN-LAST:event_contaContabilMenuItemhistoricoMenuItemActionPerformed

private void impressaoMultiplosRecibosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_impressaoMultiplosRecibosMenuItemActionPerformed

    if (impressaoMultiplosRecibos == null) {

        try {
            impressaoMultiplosRecibos = new ImpressaoMultiplosRecibos();
            desktopPane.add(impressaoMultiplosRecibos);
            impressaoMultiplosRecibos.setVisible(true);
            impressaoMultiplosRecibos.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    impressaoMultiplosRecibos.repopularCombos();
    impressaoMultiplosRecibos.botaoLimpar.doClick();
    impressaoMultiplosRecibos.show();

}//GEN-LAST:event_impressaoMultiplosRecibosMenuItemActionPerformed

private void sincronizeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sincronizeMenuItemActionPerformed

    if (Controller.getOrganizacao().getSistemaContabil() != null && !Controller.getOrganizacao().getSistemaContabil().isEmpty()) {

        //  if (Controller.getNomeEstacao().equalsIgnoreCase(Controller.getNameServidorBD())) {
        if (true) {

            // if (Controller.getIpServidorBD().equalsIgnoreCase(Controller.getIpEstacao())) {
            if (true) {

                if (telaSincronizarMegaContabil == null) {

                    try {
                        telaSincronizarMegaContabil = new TelaSincronizarMegaContabil();
                        desktopPane.add(telaSincronizarMegaContabil);
                        telaSincronizarMegaContabil.setVisible(true);
                        telaSincronizarMegaContabil.setPositionCenter();
                    } catch (final SystemException ex) {

                        final File file = PrintScreen.capture();

                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {

                                tratamentoExcecao(ex, file);

                            }
                        });

                    }

                }

            } else {
                JOptionPane.showMessageDialog(null, "Essa função só pode ser executada no SERVIDOR\n" + "Incoerencia com o endereo IP.");
                return;
            }

        } else {
            JOptionPane.showMessageDialog(null, "Essa rotina só pode ser executada no SERVIDOR\n" + "Incoerencia com o nome do computador");
            return;
        }

    } else {

        JOptionPane.showMessageDialog(null, "Função não permitida!");
        return;

    }

    telaSincronizarMegaContabil.repopularCombos();
    telaSincronizarMegaContabil.show();

}//GEN-LAST:event_sincronizeMenuItemActionPerformed

private void feriadosMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feriadosMenuItemActionPerformed

    if (telaFeriados == null) {

        try {
            telaFeriados = new TelaFeriados();
            desktopPane.add(telaFeriados);
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    telaFeriados.repopularCombos();
    telaFeriados.show();

}//GEN-LAST:event_feriadosMenuItemActionPerformed

private void cartaoCreditoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cartaoCreditoMenuItemActionPerformed

    if (cadastroCartaoCredito == null) {

        try {
            cadastroCartaoCredito = new CadastroCartaoCredito();
            desktopPane.add(cadastroCartaoCredito);
            cadastroCartaoCredito.setVisible(true);
            cadastroCartaoCredito.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    cadastroCartaoCredito.repopularCombos();
    cadastroCartaoCredito.show();

}//GEN-LAST:event_cartaoCreditoMenuItemActionPerformed

private void telaCartaoCreditoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telaCartaoCreditoMenuItemActionPerformed
    if (telaCartaoCredito == null) {

        try {
            telaCartaoCredito = new TelaCartaoCredito();
            desktopPane.add(telaCartaoCredito);
            telaCartaoCredito.setVisible(true);
            telaCartaoCredito.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    telaCartaoCredito.repopularCombos();
    telaCartaoCredito.show();


}//GEN-LAST:event_telaCartaoCreditoMenuItemActionPerformed

private void calculadorajMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculadorajMenuItemActionPerformed

    try {

        Runtime.getRuntime().exec("calc.exe");
    } catch (IOException ex) {
        ex.printStackTrace();
    }

}//GEN-LAST:event_calculadorajMenuItemActionPerformed

private void tecladoVirtualjMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tecladoVirtualjMenuItemActionPerformed

    try {

        Runtime.getRuntime().exec("osk.exe");
    } catch (IOException ex) {
        ex.printStackTrace();
    }

}//GEN-LAST:event_tecladoVirtualjMenuItemActionPerformed

private void emitirChequeAvulsoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emitirChequeAvulsoMenuItemActionPerformed

    if (impressaoChequeAvulso == null) {

        try {
            impressaoChequeAvulso = new ImpressaoChequeAvulso();
            impressaoChequeAvulso.setTitle("Imprimir Cheque Avulso");
            desktopPane.add(impressaoChequeAvulso);
            impressaoChequeAvulso.setVisible(true);
            impressaoChequeAvulso.setPositionCenter();
        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

        }

    }

    impressaoChequeAvulso.repopularCombos(Tela.TELA_PRINCIPAL, null);
    impressaoChequeAvulso.botaoLimpar.doClick();
    impressaoChequeAvulso.show();


}//GEN-LAST:event_emitirChequeAvulsoMenuItemActionPerformed

private void backupjMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupjMenuItemActionPerformed

    if (telaBackup == null) {

        try {
            telaBackup = new TelaBackup();
            telaBackup.setTitle("Backup do BD");
            desktopPane.add(telaBackup);
        } catch (final Exception ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(new SystemException(ex), file);

                }
            });

        }

    }

    telaBackup.repopularCombos();
    telaBackup.show();

}//GEN-LAST:event_backupjMenuItemActionPerformed

private void jrMenuItemRelacaoSacadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrMenuItemRelacaoSacadosActionPerformed

    if (relacaoSacados == null) {

        try {
            relacaoSacados = new RelacaoSacados();
            relacaoSacados.setTitle("Listagem Sacados");
            desktopPane.add(relacaoSacados);
            relacaoSacados.setVisible(true);
            relacaoSacados.setPositionCenter();
        } catch (final Exception ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(new SystemException(ex), file);

                }
            });

        }

    }

    relacaoSacados.repopularCombos();
    relacaoSacados.show();


}//GEN-LAST:event_jrMenuItemRelacaoSacadosActionPerformed

private void jrMenuItemRelacaoCedentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrMenuItemRelacaoCedentesActionPerformed

    if (relacaoCedentes == null) {

        try {
            relacaoCedentes = new RelacaoCedentes();
            relacaoCedentes.setTitle("Listagem Cedentes");
            desktopPane.add(relacaoCedentes);
            relacaoCedentes.setVisible(true);
            relacaoCedentes.setPositionCenter();
        } catch (final Exception ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(new SystemException(ex), file);

                }
            });

        }

    }

    relacaoCedentes.repopularCombos();
    relacaoCedentes.show();


}//GEN-LAST:event_jrMenuItemRelacaoCedentesActionPerformed

private void jMIverificarDadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMIverificarDadosActionPerformed
    //new ValidaLicencaFinance().genuineFinance();
    exibeMensagemAviso("Verificado", "Verificação");


}//GEN-LAST:event_jMIverificarDadosActionPerformed

    private void baixaTituloReceberMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baixaTituloReceberMenuItemActionPerformed
        if (telaTituloReceber == null) {

            try {
                telaTituloReceber = new TelaTituloReceber();
                desktopPane.add(telaTituloReceber);
                telaTituloReceber.setVisible(true);
                telaTituloReceber.setPositionCenter();
            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            }

        }
        telaTituloReceber.repopularCombos();
        telaTituloReceber.botaoLimpar.doClick();
        telaTituloReceber.show();        // TODO add your handling code here:
    }//GEN-LAST:event_baixaTituloReceberMenuItemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ListagemChequesMenuItem;
    private javax.swing.JMenuItem TelaChequesMenuItem;
    private javax.swing.JMenuItem backupjMenuItem;
    private javax.swing.JMenuItem bairroMenuItem;
    private javax.swing.JMenuItem baixaTituloLoteMenuItem;
    private javax.swing.JMenuItem baixaTituloReceberMenuItem;
    private javax.swing.JMenuItem bancoMenuItem1;
    private javax.swing.JMenuBar barraMenuPrincipal;
    private javax.swing.JMenuItem calculadorajMenuItem;
    private javax.swing.JMenuItem cancelaBaixaTituloReceberMenuItem;
    private javax.swing.JMenuItem cancelamentoLoteMenuItem;
    private javax.swing.JMenuItem cancelarBaixaTituloPagarMenuItem;
    private javax.swing.JMenuItem cartaoCreditoMenuItem;
    private javax.swing.JMenuItem cedenteMenuItem;
    private javax.swing.JMenuItem centroCustoMenuItem1;
    private javax.swing.JMenuItem cidadeMenuItem;
    private javax.swing.JMenuItem compensarChequeMenuItem;
    private javax.swing.JMenuItem compensarMultiChequeMenuItem;
    private javax.swing.JMenu conciliacaoMenu;
    private javax.swing.JMenuItem configuracoesMenuItem;
    private javax.swing.JMenuItem contaBancariaLancamentoMenuItem;
    private javax.swing.JMenuItem contaBancariaMenuItem;
    private javax.swing.JMenuItem contaContabilMenuItem;
    private javax.swing.JMenu contaPagarMenu;
    private javax.swing.JMenu contaReceberMenu;
    public static javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenuItem emailMenuItem;
    private javax.swing.JMenuItem emitirChequeAvulsoMenuItem;
    private javax.swing.JMenuItem emitirReciboContaPagarMenuItem;
    private javax.swing.JMenuItem emitirReciboContaReceberMenuItem;
    private javax.swing.JMenuItem emitirReciboCopiaChequeMenuItem;
    private javax.swing.JMenuItem emitirReciboTesourariaCreditoMenuItem;
    private javax.swing.JMenuItem emitirReciboTesourariaDebitoMenuItem;
    private javax.swing.JMenu exportMegaContabilMenu;
    private javax.swing.JMenuItem exportMegaMenuItem;
    private javax.swing.JMenuItem extratoBancarioMenuItem;
    private javax.swing.JMenuItem extratoTesourariaMenuItem;
    private javax.swing.JMenuItem feriadosMenuItem;
    private javax.swing.JMenuItem formaPagamentoMenuItem1;
    private javax.swing.JMenuItem funcionarioMenuItem;
    private javax.swing.JMenuItem gerarChequesMenuItem;
    private javax.swing.JMenuItem grupoMenuItem;
    private javax.swing.JMenuItem historicoMenuItem1;
    private javax.swing.JMenuItem impressaoChequeMenuItem;
    private javax.swing.JMenuItem impressaoMultiplosChequeMenuItem;
    private javax.swing.JMenuItem impressaoMultiplosRecibosMenuItem;
    private javax.swing.JMenuItem jMIverificarDados;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItemRLoteDeposito;
    private javax.swing.JMenuItem jMenuItemTransfereBancoTesouraria;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator26;
    private javax.swing.JSeparator jSeparator27;
    private javax.swing.JSeparator jSeparator28;
    private javax.swing.JSeparator jSeparator29;
    private javax.swing.JSeparator jSeparator30;
    private javax.swing.JSeparator jSeparator31;
    private javax.swing.JSeparator jSeparator32;
    private javax.swing.JSeparator jSeparator33;
    private javax.swing.JSeparator jSeparator34;
    private javax.swing.JSeparator jSeparator35;
    private javax.swing.JSeparator jSeparator36;
    private javax.swing.JSeparator jSeparator37;
    private javax.swing.JSeparator jSeparator38;
    private javax.swing.JSeparator jSeparator39;
    private javax.swing.JSeparator jSeparator40;
    private javax.swing.JSeparator jSeparator41;
    private javax.swing.JSeparator jSeparator42;
    private javax.swing.JSeparator jSeparator43;
    private javax.swing.JSeparator jSeparator44;
    private javax.swing.JSeparator jSeparator45;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JMenuItem jrMenuItemRelacaoCedentes;
    private javax.swing.JMenuItem jrMenuItemRelacaoSacados;
    private javax.swing.JLabel labelTPVersao;
    private javax.swing.JMenuItem lancamentoTituloPagarMenuItem;
    private javax.swing.JMenuItem lancamentoTituloReceberMenuItem;
    private javax.swing.JMenuItem layoutChequeMenuItem;
    private javax.swing.JMenuItem localPagamentoMenuItem1;
    private javax.swing.JMenuItem manutencaoBancoDadosMenu;
    private javax.swing.JMenuItem organizacaoMenuItem;
    private javax.swing.JMenu parametrosMenu;
    private javax.swing.JMenuItem parametroslMenuItem;
    private javax.swing.JMenuItem particionadorTituloPagarMenuItem;
    private javax.swing.JMenuItem particionadorTituloReceberMenuItem;
    private javax.swing.JMenuItem reCPMenuItem;
    private javax.swing.JMenuItem reciboTransferenciaMenuItem;
    private javax.swing.JMenuItem relCRMenuItem;
    private javax.swing.JMenuItem relFluxoMenuItem;
    private javax.swing.JMenuItem relatorioDmsMenuItem;
    private javax.swing.JMenuItem relatorioExportacaoMegaContabilMenuItem;
    private javax.swing.JMenuItem relatorioLotePagamentoMenuItem;
    private javax.swing.JMenu relatorioMenu;
    private javax.swing.JMenuItem relatorioMovimentoDiarioMenuItem;
    private javax.swing.JMenuItem removExportMegaMenuItem;
    private javax.swing.JMenuItem sacadoMenuItem;
    private javax.swing.JMenuItem sairMenuItem1;
    private javax.swing.JMenuItem saldoBancarioMenuItem;
    private javax.swing.JMenu segurancaMenu;
    private javax.swing.JMenuItem sincronizeMenuItem;
    private javax.swing.JMenuItem sobreMenuItem1;
    private javax.swing.JMenuItem tecladoVirtualjMenuItem;
    private javax.swing.JMenuItem telaAvisosMenuItem;
    private javax.swing.JMenuItem telaCartaoCreditoMenuItem;
    private javax.swing.JMenuItem tesourariaLancamentosMenuItem;
    private javax.swing.JMenu tesourariaMenu;
    private javax.swing.JMenuItem tipoAcrescimoMenuItem;
    private javax.swing.JMenuItem tipoCedenteMenuItem1;
    private javax.swing.JMenuItem tipoCobrancaMenuItem1;
    private javax.swing.JMenuItem tipoDeducaoMenuItem;
    private javax.swing.JMenuItem tipoNotaFiscalMenuItem;
    private javax.swing.JMenuItem tipoOperacaoBancariaMenuItem;
    private javax.swing.JMenuItem tipoSacadoMenuItem1;
    private javax.swing.JMenuItem tipoStatusMenuItem1;
    private javax.swing.JMenuItem tituloPagarMenuItem;
    private javax.swing.JMenuItem tituloReceberMenuItem;
    private javax.swing.JMenuItem transfereBancoMenuItem;
    private javax.swing.JMenuItem transferenciaEntreContasMenuItem;
    private javax.swing.JMenuItem usuarioMenuItem;
    private javax.swing.JMenu utilMenu;
    // End of variables declaration//GEN-END:variables
    private CadastroBairro cadastroBairro = null;
    private CadastroCidade cadastroCidade = null;
    private CadastroFuncionario cadastroFuncionario = null;
    private CadastroTipoCobranca cadastroTipoCobranca = null;
    private CadastroTipoSacado cadastroTipoSacado = null;
    private CadastroTipoCedente cadastroTipoCedente = null;
    private CadastroBanco cadastroBanco = null;
    private CadastroContaBancaria cadastroContaBancaria = null;
    private CadastroTipoStatus cadastroTipoStatus = null;
    private CadastroTipoDeducao cadastroTipoDeducao = null;
    private CadastroTipoAcrescimo cadastroTipoAcrescimo = null;
    private CadastroFormaPagamento cadastroFormaPagamento = null;
    private CadastroLocalPagamento cadastroLocalPagamento = null;
    private CadastroCentroCusto cadastroCentroCusto = null;
    private CadastroHistorico cadastroHistorico = null;
    private CadastroSacado cadastroSacado = null;
    private CadastroCedente cadastroCedente = null;
    private CadastroTituloPagar cadastroTituloPagar = null;
    private CadastroTituloReceber cadastroTituloReceber = null;
    private CadastroTituloPagarBaixa cadastroTituloPagarBaixa = null;
    private CadastroTituloReceberBaixa cadastroTituloReceberBaixa = null;
    private CadastroTipoOperacaoBancaria cadastroTipoOperacaoBancaria = null;
    private GeradorCheque geradorCheque = null;
    private CompensadorCheque compensadorCheque = null;
    private TransferenciaEntreContas transferenciaEntreContas = null;
    private TesouraiaLancamento tesouraiaLancamento = null;
    private ExcluirTituloPagarBaixa excluirTituloPagarBaixa = null;
    private ExcluirTituloReceberBaixa excluirTituloReceberBaixa = null;
    private CadastroUsuario cadastroUsuario = null;
    private CadastroGrupo cadastroGrupo = null;
    private CadastroOrganizacao cadastroOrganizacao = null;
    private ContaBancariaLancamento contaBancariaLancamento = null;
    private ParticionadorTituloPagar particionadorTituloPagar = null;
    private AboutFinance aboutFinance = null;
    private ReciboContaPagar reciboContaPagar = null;
    private ReciboContaReceber reciboContaReceber = null;
    private ReciboTesourariaDespesa reciboTesourariaDespesa = null;
    private ReciboTesourariaReceita reciboTesourariaReceita = null;
    private ReciboCopiaCheque reciboCopiaCheque = null;
    private LayoutCheque layoutCheque = null;
    private ParticionadorTituloReceber particionadorTituloReceber = null;
    private ExtratoTesouraria extratoTesouraria = null;
    private CadastroTipoNotaFiscal cadastroTipoNotaFiscal = null;
    private ExportacaoMegaContabil exportacaoMegaContabil = null;
    private CompensadorMultiplosCheques compensadorMultiplosCheques = null;
    private ManutencaoBancoDados manutencaoBancoDados = null;
    private TelaAvisosTitulos telaAvisosTitulos = null;
    private ExcluirExportacaoMegaContabil excluirExportacao = null;
    private TelaChequesTesouraria telaChequesTesouraria = null;
    private ListagemChequesTesouraria listagemChequesTesouraria = null;
    private TransfereTesourariaBanco transfereTesourariaBanco = null;
    private TelaTituloPagar telaTituloPagar = null;
    private TelaTituloReceber telaTituloReceber = null;
    private ContaBancariaSaldo contaBancariaSaldo = null;
    private CadastroConfiguracoes cadastroConfiguracoes = null;
    private TelaEnvioEmail telaEnvioEmail = null;
    private ReciboTransferenciaBancaria reciboTransferenciaBancaria = null;
    private ExcluirLotePagamento excluirLotePagamento = null;
    private TelaParametros parametros = null;
    private RelatorioFluxoCaixa relatorioFluxoCaixa = null;
    private RelatorioContasPagar relatorioContasPagar = null;
    private RelatorioContasReceber relatorioContasReceber = null;
    private RelatorioExtratoBancario relatorioExtratoBancario = null;
    private RelatorioDMS relatorioDMS = null;
    private RelatorioMovimentoDiario relatorioMovimentoDiario = null;
    private RelatorioExportacaoMegaContabil relatorioExportacaoMegaContabil = null;
    private RelatorioLoteExportacao relatorioLoteExportacao = null;
    private RelatorioLotePagamento relatorioLotePagamento = null;
    private RelatorioLoteDeposito relatorioLoteDeposito = null;
    private TransfereBancoTesouraria transfereBancoTesouraria = null;
    private ImpressaoCheque impressaoCheque = null;
    private ImpressaoChequeAvulso impressaoChequeAvulso = null;
    private ImpressaoMultiplosCheque impressaoMultiplosCheque = null;
    private CadastroContaContabil cadastroContaContabil = null;
    private ImpressaoMultiplosRecibos impressaoMultiplosRecibos = null;
    private TelaSincronizarMegaContabil telaSincronizarMegaContabil = null;
    private TelaFeriados telaFeriados = null;
    private CadastroCartaoCredito cadastroCartaoCredito = null;
    private TelaCartaoCredito telaCartaoCredito = null;
    private TelaBackup telaBackup = null;
    private RelacaoSacados relacaoSacados = null;
    private RelacaoCedentes relacaoCedentes = null;

}
