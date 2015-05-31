/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TituloPagarBaixaChequeModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TituloPagarBaixaBancoTextFilterator implements TextFilterator<TituloPagarBaixaChequeModel> {

    public void getFilterStrings(List<String> baseList, TituloPagarBaixaChequeModel element) {
        final String conta = element.getContaBancariaCheque().getNumeroCheque();
        baseList.add(conta);
    }
}
