/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.BairroModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class BairroTextFilterator implements TextFilterator<BairroModel> {

    public void getFilterStrings(List<String> baseList, BairroModel element) {
        final String bairro = element.getDescricao();
        baseList.add(bairro);
    }
}
