package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.UsuarioModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class UsuarioTextFilterator implements TextFilterator<UsuarioModel> {

    public void getFilterStrings(List<String> baseList, UsuarioModel element) {
        final String nome = element.getNome();
        baseList.add(nome);
    }
}
