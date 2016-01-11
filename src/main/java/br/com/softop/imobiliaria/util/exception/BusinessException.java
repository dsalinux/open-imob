package br.com.softop.imobiliaria.util.exception;

/**
 *
 * @author Danilo Souza Almeida
 */
public class BusinessException extends Exception {

    public BusinessException() {
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
