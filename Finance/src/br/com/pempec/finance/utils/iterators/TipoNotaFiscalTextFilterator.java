package br.com.pempec.finance.utils.iterators;

import br.com.pempec.finance.models.TipoNotaFiscalModel;
import ca.odell.glazedlists.TextFilterator;
import java.util.List;

/**
 *
 * @author PEMPEC
 */
public class TipoNotaFiscalTextFilterator implements TextFilterator<TipoNotaFiscalModel> {

    public void getFilterStrings(List<String> baseList, TipoNotaFiscalModel element) {
        final String descricao = element.getDescricao();
        baseList.add(descricao);
    }
}
