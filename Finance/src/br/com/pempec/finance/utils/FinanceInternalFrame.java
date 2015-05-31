package br.com.pempec.finance.utils;

import br.com.pempec.componentes.JIntegerField;
import br.com.pempec.finance.businessObjects.BackupBO;
import br.com.pempec.finance.businessObjects.OrganizacaoBO;
import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.BackupModel;
import br.com.pempec.finance.models.FinanceIdModel;
import br.com.pempec.finance.models.FinancePkModel;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.OrganizacaoModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.view.TelaErro;
import br.com.pempec.finance.view.TelaPrincipal;
import ca.odell.glazedlists.EventList;
import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/**
 *
 * @author PEMPEC
 */
public class FinanceInternalFrame extends javax.swing.JInternalFrame {

    protected OrganizacaoModel organizacaoModel = Controller.getOrganizacao();
    private static BackupBO backupBO = new BackupBO();

    public MovimentoDiarioModel registroMovimento(String acao, String codigo, String descricao, Double valor, String status) {

        if (Controller.getVersao()) {

            Controller.setQtdMovimentosHoje(0);

            // Controller.checarLicenca(); 19/05/2012
            Integer labelVersion = Integer.parseInt(PempecUtil.somenteNumero(ResourcePropertiesLocator.getMessageAbout("labelVersion")));

            Integer versaoSistemaBD = Integer.parseInt(PempecUtil.somenteNumero(Controller.getOrganizacao().getVersao()));

            if (labelVersion == null || versaoSistemaBD == null) {

                labelVersion = 1;
                versaoSistemaBD = 0;

            }

            if (labelVersion > versaoSistemaBD) {

                try {
                    organizacaoModel.setVersao(ResourcePropertiesLocator.getMessageAbout("labelVersion"));

                    organizacaoModel.setMovimentoDiarioModel(registroMovimento("VERSAO", PempecUtil.somenteNumero(ResourcePropertiesLocator.getMessageAbout("labelVersion")), "Estacao " + Controller.getNomeEstacao() + " - usuario " + Controller.getUsuarioLogado().getNome() + " atualizou a versao do sistema no bd.", Double.parseDouble(labelVersion.toString()), "Atualizada"));

                    organizacaoModel.getMovimentoDiarioModel().setObservacao(" V. Antiga > " + versaoSistemaBD);

                    new OrganizacaoBO().alterar(organizacaoModel);

                    //  19/05/2012  //  Controller.sendMailInterno("Versão Atualizada - " + labelVersion, " Versão atualizada na organizacao " + organizacaoModel.getFantasia() + "\r\nem " + PempecParse.dateToString(Controller.getDataServidorBD()) + "." + "\r\n Versão atual  " + ResourcePropertiesLocator.getMessageAbout("labelVersion") + "\r\n Estacao " + Controller.getNomeEstacao() + " - Usuario " + Controller.getUsuarioLogado().getNome() + "\r\n " + Controller.getSerialOrganizacao(), null);
                } catch (ApplicationException ex) {

                    ex.printStackTrace();

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

            try {

                exibeMensagemAviso("Seu sistema precisa ser atualizado.\nEntre em contato com o suporte.\n Para baixar a nova versao acesse o site\nhttp://www.pempec.com.br/finance\nATENÇÃO :  Voce deve baixar a versao FinanceUpdate.", "Versão");
                Controller.getOrganizacao().setVersao(ResourcePropertiesLocator.getMessageAbout("labelVersion"));

                Controller.sendMailInterno("AVISO  ", "O usuario " + Controller.getUsuarioLogado().getNome() + " da organizacao " + organizacaoModel.getFantasia() + "\r\nem " + PempecParse.dateToString(Controller.getDataServidorBD()) + "\r\n foi avisado que sua cópia está desatualizada. " + "\r\n Estacao " + Controller.getNomeEstacao() + "\r\n " + Controller.getSerialOrganizacao(), null);

            } catch (ApplicationException ex) {

                ex.printStackTrace();

            } catch (final SystemException ex) {

                final File file = PrintScreen.capture();

                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {

                        tratamentoExcecao(ex, file);

                    }
                });

            }

        }

//        if (!(organizacaoModel.getCodinome().equalsIgnoreCase("lobo") || organizacaoModel.getCodinome().equalsIgnoreCase("administrador") 
//                || organizacaoModel.getCodinome().equalsIgnoreCase("mangalarga") || organizacaoModel.getCodinome().equalsIgnoreCase("toca"))) {
//
//            if (!new ValidaLicencaFinance().validar(organizacaoModel)) {
//
//                exibeMensagemAviso("Ocorreu um erro grave no sistema!\nO acesso ao banco de dados está temporariamente bloqueado.\nEntre em contato com o suporte: (71) 8876-6064\n E-MAIL : suporte@pempec.com.br", "Validator");
//                Controller.setOrganizacao(null);
//            }
//        }
        Collection<BackupModel> collBackups = new ArrayList<BackupModel>();

        long qtdUltimoBackup = 0;
        long qtd = Controller.getQtdMovimentosHoje();
        int aux2 = Integer.MAX_VALUE;

        try {

            collBackups = backupBO.obterTodosPorData(organizacaoModel, Controller.getDataServidorBD());

            for (BackupModel bckModel : collBackups) {

                if (bckModel.getQtdRegistros() > qtdUltimoBackup) {

                    qtdUltimoBackup = bckModel.getQtdRegistros();

                }

            }

            //backup
            String aux = ResourcePropertiesLocator.getMessage("backup");
            aux2 = Integer.parseInt(PempecUtil.somenteNumero(aux));

            if (aux2 == 0) {

                aux2 = Integer.MAX_VALUE;
            }

            if (qtd > (qtdUltimoBackup + aux2)) {

                exibeMensagemAviso("    ATENÇÃO:\nNão esqueça de efetuar backup do sistema.\nNão nos responsabilizamos por eventuais perdas de dados.\nCódigo da Mensagem : " + qtd, "Backup");

            }

        } catch (final NumberFormatException nex) {

            exibeMensagemAviso("    ATENÇÃO:\nNão esqueça de efetuar backup do sistema.\nOcorreu um erro nas propriedades, entre em contato com o suporte.", "Backup");

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(nex, file);

                }
            });

            System.exit(0);

        } catch (final SystemException ex) {

            final File file = PrintScreen.capture();

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {

                    tratamentoExcecao(ex, file);

                }
            });

            System.exit(0);

        } catch (ApplicationException ex) {

            ex.printStackTrace();
        }

        MovimentoDiarioModel movimentoDiarioModel = new MovimentoDiarioModel();

        movimentoDiarioModel.setPk(new PKModel());

        movimentoDiarioModel.getPk().setOrganizacao(Controller.getOrganizacao());

        movimentoDiarioModel.getPk().setId(PempecKeyGenerator.generateKey());

        movimentoDiarioModel.setDataRegistro(PempecParse.dateToDate(Controller.getDataServidorBD()));

        movimentoDiarioModel.setAcao(acao);

        if (codigo != null && !codigo.isEmpty()) {

            movimentoDiarioModel.setCodigo(codigo);

        } else {

            movimentoDiarioModel.setCodigo(PempecKeyGenerator.generateKeyLong().toString());
        }

        movimentoDiarioModel.setObjeto(this.getClass().getSimpleName());

        movimentoDiarioModel.setDescricaoObjeto(descricao);

        movimentoDiarioModel.setValorOperacao(valor);

        movimentoDiarioModel.setStatusFinalObjeto(status);

        movimentoDiarioModel.setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

        return movimentoDiarioModel;

    }

    /**
     * Metodo utilizado para bloquear ou desbloquear todos os componentes
     * existentes na ABA, exceto JPanel e JLabel.
     *
     * @param aba JPanel.
     * @param enable - true para desbloquear e false para bloquear.
     */
    public void protegeAba(JPanel aba, Boolean enable) {

        Component[] components = aba.getComponents();

        for (Component component : components) {

            if (!(component instanceof JLabel || component instanceof JPanel)) {

                if (component instanceof JScrollPane) {
                    ((JScrollPane) component).getViewport().getView().setEnabled(enable);
                } else {
                    component.setEnabled(enable);
                }

            }

        }

    }

    @SuppressWarnings("static-access")
    public void exibeMensagemAviso(String mensagem, String titulo) {

//        PempecJOptionPane pempecJOptionPane = new PempecJOptionPane();
//
//
//        pempecJOptionPane.setLocation(750, 50);
//        pempecJOptionPane.setBackground(Color.red);
//        pempecJOptionPane.setToolTipText("Pempec");
//
        //pempecJOptionPane.showInternalMessageDialog(this, mensagem, (titulo == null ? "Aviso" : titulo), JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showInternalMessageDialog(this, mensagem, (titulo == null ? "Aviso" : titulo), JOptionPane.INFORMATION_MESSAGE);
    }

    public Boolean exibeMensagemConfirmacao(String mensagem, String titulo) {
        return JOptionPane.showInternalConfirmDialog(this, mensagem, (titulo == null ? "Aviso" : titulo), JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }

    public String getInputMessage(String mensagem, String valorDefault) {
        return JOptionPane.showInputDialog(this, mensagem, valorDefault);
    }

    /**
     * Metodo utilizado para bloquear ou desbloquear todos os componentes
     * existentes na ABA, exceto JPanel e JLabel. Exceto o parametro.
     *
     * @param aba JPanel.
     * @param except
     * @param enable - true para desbloquear e false para bloquear.
     */
    public void protegeAbaExcept(JPanel aba, Boolean enable, Component except) {

        Component[] components = aba.getComponents();

        for (Component component : components) {

            if (!(component instanceof JLabel || component instanceof JPanel) && !component.equals(except)) {

                if (component instanceof JScrollPane) {
                    ((JScrollPane) component).getViewport().getView().setEnabled(enable);
                } else {
                    component.setEnabled(enable);
                }

            }

        }

    }

    /**
     * Criar tela de Erro com botão de enviar erro ao administrador.
     *
     * @param exception
     */
    public synchronized void tratamentoExcecao(ApplicationException exception) {
        JOptionPane.showInternalMessageDialog(this, exception.getMessage());
    }

    public synchronized void tratamentoExcecao(IOException exception) {
        JOptionPane.showInternalMessageDialog(this, exception.getMessage());
    }

    /**
     * Criar tela de Erro com botão de enviar erro ao administrador.
     *
     * @param exception
     */
    public synchronized void tratamentoExcecao(SystemException exception, File file) {

        Throwable raiz = exception;

        exception.printStackTrace();

        //Pegando Exception que causou o erro.
        while (raiz.getCause() != null) {
            raiz = raiz.getCause();
        }

        TelaErro telaErro = new TelaErro(file);
        TelaPrincipal.desktopPane.add(telaErro);
        telaErro.labelDataHora.setText(PempecParse.dateHourSecondToString(new Date()));
        telaErro.labelMensagem.setText(raiz.getMessage());
        telaErro.labelTela.setText(getTitle());

        StringBuilder builder = new StringBuilder();

        for (StackTraceElement stack : raiz.getStackTrace()) {
            builder.append(stack.toString());
            builder.append("\n");
        }

        telaErro.jTObservacao.setText(builder.toString());

        telaErro.setFont(new Font("Arial", Font.PLAIN, 8));

        telaErro.show();

    }

    public synchronized void tratamentoExcecao(NumberFormatException exception, File file) {

        Throwable raiz = exception;

        exception.printStackTrace();

        //Pegando Exception que causou o erro.
        while (raiz.getCause() != null) {
            raiz = raiz.getCause();
        }

        TelaErro telaErro = new TelaErro(file);
        TelaPrincipal.desktopPane.add(telaErro);
        telaErro.labelDataHora.setText(PempecParse.dateHourSecondToString(new Date()));
        telaErro.labelMensagem.setText(raiz.getMessage());
        telaErro.labelTela.setText(getTitle());

        StringBuilder builder = new StringBuilder();

        for (StackTraceElement stack : raiz.getStackTrace()) {
            builder.append(stack.toString());
            builder.append("\n");
        }

        telaErro.jTObservacao.setText(builder.toString());

        telaErro.setFont(new Font("Arial", Font.PLAIN, 8));

        telaErro.show();

    }

    public Boolean validaPreenchimentoCombo(JComboBox combo, boolean autoComplete) {

        if (!(combo.getSelectedItem() instanceof FinancePkModel || combo.getSelectedItem() instanceof FinanceIdModel)) {
            return false;
        }

        if (autoComplete) {
            if (combo.getSelectedItem() != null) {
                return true;
            }
        } else {
            if (combo.getSelectedIndex() != 0) {
                return true;
            }
        }

        return false;

    }

    public Boolean existsInCombo(JComboBox combo, FinancePkModel object, boolean autoComplete) {

        if (object == null || object.getPk() == null || object.getPk().getId() == null || object.getPk().getId().isEmpty()) {
            return false;
        }

        int cont;

        if (autoComplete) {
            cont = 0;
        } else {
            cont = 1;
        }

        for (int i = cont; i < combo.getItemCount(); i++) {
            if (object.getPk().getId().equalsIgnoreCase(((FinancePkModel) combo.getItemAt(i)).getPk().getId())) {
                return true;
            }
        }

        return false;

    }

    public void selecionaCombo(JComboBox combo, FinancePkModel object, boolean autoComplete) {

        if (object == null || object.getPk() == null || object.getPk().getId() == null || object.getPk().getId().isEmpty()) {
            return;
        }

        int cont;

        if (autoComplete) {
            cont = 0;
        } else {
            cont = 1;
        }

        for (int i = cont; i < combo.getItemCount(); i++) {
            if (object.getPk().getId().equalsIgnoreCase(((FinancePkModel) combo.getItemAt(i)).getPk().getId())) {
                combo.setSelectedIndex(i);
                break;
            }
        }

    }

    public Boolean existsInCombo(JComboBox combo, FinanceIdModel object, boolean autoComplete) {

        if (object == null || object.getId() == null || object.getId().isEmpty()) {
            return false;
        }

        int cont;

        if (autoComplete) {
            cont = 0;
        } else {
            cont = 1;
        }

        for (int i = cont; i < combo.getItemCount(); i++) {
            if (object.getId().equalsIgnoreCase(((FinanceIdModel) combo.getItemAt(i)).getId())) {
                return true;
            }
        }

        return false;

    }

    public void selecionaCombo(JComboBox combo, FinanceIdModel object, boolean autoComplete) {

        if (object == null || object.getId() == null || object.getId().isEmpty()) {
            return;
        }

        int cont;

        if (autoComplete) {
            cont = 0;
        } else {
            cont = 1;
        }

        for (int i = cont; i < combo.getItemCount(); i++) {
            if (object.getId().equalsIgnoreCase(((FinanceIdModel) combo.getItemAt(i)).getId())) {
                combo.setSelectedIndex(i);
                break;
            }
        }

    }

    public void selecionaCombo(JComboBox combo, String object, boolean autoComplete) {

        if (object == null || object.isEmpty()) {
            return;
        }

        int cont;

        if (autoComplete) {
            cont = 0;
        } else {
            cont = 1;
        }

        for (int i = cont; i < combo.getItemCount(); i++) {
            if (object.equalsIgnoreCase(((String) combo.getItemAt(i)))) {
                combo.setSelectedIndex(i);
                break;
            }
        }

    }

    public void removeItemComboByName(EventList list, String name, boolean autoComplete) {

        int cont;

        if (autoComplete) {
            cont = 0;
        } else {
            cont = 1;
        }

        for (int i = cont; i < list.size(); i++) {
            if (name.equalsIgnoreCase(list.get(i).toString())) {
                list.remove(list.get(i));
                return;
            }
        }

    }

    public void limparCampos(JPanel... panels) {

        for (JPanel panel : panels) {

            Component[] components = panel.getComponents();

            for (Component component : components) {

                if (component instanceof JIntegerField) {
                    ((JIntegerField) component).setText("0");
                } else {
                    if (component instanceof JFormattedTextField) {
                        ((JFormattedTextField) component).setValue(null);
                        ((JFormattedTextField) component).setText("");
                    } else {
                        if (component instanceof JTextField) {
                            ((JTextField) component).setText("");
                        } else {
                            if (component instanceof JComboBox) {
                                //Combo auto-complete é Editavel.
                                JComboBox aux = (JComboBox) component;
                                if (aux.isEditable()) {
                                    aux.setSelectedItem(null);
                                } else {
                                    aux.setSelectedIndex(0);
                                }
                            }
                        }
                    }
                }
            }

        }

    }

    public void limparCamposExcept(Component except, JPanel... panels) {

        for (JPanel panel : panels) {

            Component[] components = panel.getComponents();

            for (Component component : components) {

                if (component.equals(except)) {
                    continue;
                }

                if (component instanceof JIntegerField) {
                    ((JIntegerField) component).setText("0");
                } else {
                    if (component instanceof JFormattedTextField) {
                        ((JFormattedTextField) component).setValue(null);
                        ((JFormattedTextField) component).setText("");
                    } else {
                        if (component instanceof JTextField) {
                            ((JTextField) component).setText("");
                        } else {
                            if (component instanceof JComboBox) {
                                //Combo auto-complete é Editavel.
                                JComboBox aux = (JComboBox) component;
                                if (aux.isEditable()) {
                                    aux.setSelectedItem(null);
                                } else {
                                    aux.setSelectedIndex(0);
                                }
                            }
                        }
                    }
                }
            }

        }

    }

    public void setPositionCenter() {
        Dimension d = this.getDesktopPane().getSize();
        this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);

    }

}
