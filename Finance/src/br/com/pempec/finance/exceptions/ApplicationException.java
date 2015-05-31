/**
 *
 */
package br.com.pempec.finance.exceptions;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Equipe Pempec
 *
 */
public class ApplicationException extends Exception {

    private Collection<String> params = new ArrayList<String>();

    public ApplicationException(Exception exception) {
        super(exception);
    }

    public ApplicationException(String exception) {
        super(exception);
    }

    public ApplicationException(String exception, Collection<String> parms) {
        super(exception);
        this.params = params;
    }

    /**
     *
     * @return params
     */
    public Collection<String> getParams() {
        return params;
    }

    /**
     *
     * @param params
     */
    public void setParams(Collection<String> params) {
        this.params = params;
    }
}
