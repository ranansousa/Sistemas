/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.ContaBancariaChequeModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class ChequeTextFilterator implements TextFilterator<ContaBancariaChequeModel> {

    public void getFilterStrings(List<String> baseList, ContaBancariaChequeModel element) {
        final String descricao = element.getNumeroCheque();
        baseList.add(descricao);
    }
}
