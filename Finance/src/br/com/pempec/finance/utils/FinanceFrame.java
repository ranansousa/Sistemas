/**
 *
 */
package br.com.pempec.finance.utils;

import br.com.pempec.finance.exceptions.ApplicationException;
import br.com.pempec.finance.exceptions.SystemException;
import br.com.pempec.finance.models.MovimentoDiarioModel;
import br.com.pempec.finance.models.PKModel;
import br.com.pempec.finance.view.TelaErro;
import br.com.pempec.finance.view.TelaPrincipal;
import java.io.File;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 * @author PEMPEC
 */
public class FinanceFrame extends javax.swing.JFrame {

    public MovimentoDiarioModel registroMovimento(String acao, String codigo, String descricao, Double valor, String status) {
        MovimentoDiarioModel movimentoDiarioModel = new MovimentoDiarioModel();
        movimentoDiarioModel.setPk(new PKModel());
        movimentoDiarioModel.getPk().setOrganizacao(Controller.getOrganizacao());
        movimentoDiarioModel.getPk().setId(PempecKeyGenerator.generateKey());
        movimentoDiarioModel.setDataRegistro(Controller.getDataServidorBD());
        movimentoDiarioModel.setAcao(acao);
        movimentoDiarioModel.setObjeto(this.getClass().getSimpleName());
        movimentoDiarioModel.setDescricaoObjeto(descricao);
        movimentoDiarioModel.setValorOperacao(valor);
        movimentoDiarioModel.setStatusFinalObjeto(status);

        if (codigo != null && !codigo.isEmpty()) {
            movimentoDiarioModel.setCodigo(codigo);
        } else {
            movimentoDiarioModel.setCodigo(PempecKeyGenerator.generateKeyLong().toString());
        }

        movimentoDiarioModel.setNumeroMovimento(PempecKeyGenerator.generateNumeroMovimentoDiario());

        return movimentoDiarioModel;

    }

    public void exibeMensagemAviso(String mensagem, String titulo) {
        JOptionPane.showMessageDialog(this, mensagem, (titulo == null ? "Aviso" : titulo), JOptionPane.INFORMATION_MESSAGE);
    }

    public Boolean exibeMensagemConfirmacao(String mensagem, String titulo) {
        return JOptionPane.showConfirmDialog(this, mensagem, (titulo == null ? "Aviso" : titulo), JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * Criar tela de Erro com botão de enviar erro ao administrador.
     *
     * @param exception
     */
    public synchronized void tratamentoExcecao(ApplicationException exception) {

        JOptionPane.showMessageDialog(this, exception.getMessage());

    }

    /**
     * Criar tela de Erro com botão de enviar erro ao administrador.
     *
     * @param exception
     */
    public synchronized void tratamentoExcecao(SystemException exception, File file) {

        Throwable raiz = exception;

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
        telaErro.show();

    }
}
