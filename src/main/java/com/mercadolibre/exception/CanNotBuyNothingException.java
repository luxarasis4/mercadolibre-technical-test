
package com.mercadolibre.exception;

public class CanNotBuyNothingException extends RuntimeException {
    private static final long serialVersionUID = 4753934399648260122L;

    public CanNotBuyNothingException() {
        super();
    }

    public CanNotBuyNothingException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
        super(arg0, arg1, arg2, arg3);
    }

    public CanNotBuyNothingException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public CanNotBuyNothingException(String arg0) {
        super(arg0);
    }

    public CanNotBuyNothingException(Throwable arg0) {
        super(arg0);
    }
}
