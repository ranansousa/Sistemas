/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TituloPagarBaixaDeducaoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TituloPagarBaixaDeducaoTextFilterator implements TextFilterator<TituloPagarBaixaDeducaoModel> {

    public void getFilterStrings(List<String> baseList, TituloPagarBaixaDeducaoModel element) {
        final String descricao = (String) element.getTipoDeducao().toString();
        baseList.add(descricao);
    }
}
