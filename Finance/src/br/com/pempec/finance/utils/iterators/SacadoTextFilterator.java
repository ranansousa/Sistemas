/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.SacadoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class SacadoTextFilterator implements TextFilterator<SacadoModel> {

    public void getFilterStrings(List<String> baseList, SacadoModel element) {
        final String nome = element.getNome();
        baseList.add(nome);


    }
}
