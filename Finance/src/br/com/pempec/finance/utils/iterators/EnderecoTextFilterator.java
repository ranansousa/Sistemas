/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.EnderecoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class EnderecoTextFilterator implements TextFilterator<EnderecoModel> {

    public void getFilterStrings(List<String> baseList, EnderecoModel element) {
        final String log = element.getLogradouro();
        baseList.add(log);
    }
}
