/**
 *
 */
package br.com.pempec.finance.exceptions;

/**
 * @author Luis Alexandre
 *
 */
public class SystemException extends Exception {

    public SystemException(Exception exception) {
        super(exception);
    }

    public SystemException(String exception) {
        super(exception);
    }
}
