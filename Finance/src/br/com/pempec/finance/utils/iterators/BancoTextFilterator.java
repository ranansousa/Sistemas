/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.BancoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class BancoTextFilterator implements TextFilterator<BancoModel> {

    public void getFilterStrings(List<String> baseList, BancoModel element) {
        final String nome_banco = element.getNomeBanco();
        baseList.add(nome_banco);
    }
}
