package br.com.loja.java.icts.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * Created by alan.nascimento on 30/10/2019
 */
@FacesConverter(value = "zerosEsquerdaConverter")
public class ConverterZerosEsquerda implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

        if (s != null) {
            return s;
        }

        return null;

    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return zeroEsquerda((String) o);
    }

    public String zeroEsquerda(String s) {

        while (s.length() < 8) {
            s = "0" + s;
        }

        return s;

    }

}
