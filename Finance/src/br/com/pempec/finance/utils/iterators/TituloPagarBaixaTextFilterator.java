/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TituloPagarBaixaModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TituloPagarBaixaTextFilterator implements TextFilterator<TituloPagarBaixaModel> {

    public void getFilterStrings(List<String> baseList, TituloPagarBaixaModel element) {
        final String numero = element.getTitulo().getNumeroDocumento();
        baseList.add(numero);
    }
}
