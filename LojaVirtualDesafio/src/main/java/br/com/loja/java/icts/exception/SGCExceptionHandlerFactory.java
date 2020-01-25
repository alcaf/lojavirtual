package br.com.loja.java.icts.exception;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class SGCExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory factory;

    public SGCExceptionHandlerFactory(ExceptionHandlerFactory factory) {
        this.factory = factory;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {
        return new SGCExceptionHandler(factory.getExceptionHandler());
    }
}
