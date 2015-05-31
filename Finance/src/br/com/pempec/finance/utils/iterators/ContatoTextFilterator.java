/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.ContatoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class ContatoTextFilterator implements TextFilterator<ContatoModel> {

    public void getFilterStrings(List<String> baseList, ContatoModel element) {
        final String email = element.getEmail();
        baseList.add(email);
    }
}
