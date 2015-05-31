/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.FormaPagamentoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class FormaPagamentoTextFilterator implements TextFilterator<FormaPagamentoModel> {

    public void getFilterStrings(List<String> baseList, FormaPagamentoModel element) {
        final String descricao = element.getDescricao();
        baseList.add(descricao);
    }
}
