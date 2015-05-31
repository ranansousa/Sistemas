/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.EstadoModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class EstadoTextFilterator implements TextFilterator<EstadoModel> {

    public void getFilterStrings(List<String> baseList, EstadoModel element) {
        final String descricao = element.getDescricao();
        baseList.add(descricao);
    }
}
