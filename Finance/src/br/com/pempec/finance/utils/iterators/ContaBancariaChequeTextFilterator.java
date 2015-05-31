package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.ContaBancariaChequeModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class ContaBancariaChequeTextFilterator implements TextFilterator<ContaBancariaChequeModel> {

    public void getFilterStrings(List<String> baseList, ContaBancariaChequeModel element) {
        final String numero = element.getNumeroCheque();
        baseList.add(numero);
    }
}
