/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TesourariaCreditoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TesourariaCreditoTextFilterator implements TextFilterator<TesourariaCreditoModel> {

    public void getFilterStrings(List<String> baseList, TesourariaCreditoModel element) {
        final String numero = element.getNumeroDocumento();
        baseList.add(numero);
    }
}
