package me.legrange.escl;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class Adf {

    @Element(name = "AdfSimplexInputCaps", required = false)
    private InputCaps adfSimplexInputCaps;
    @Element(name = "AdfDuplexInputCaps", required = false)
    private InputCaps adfDuplexInputCaps;

    @Override
    public String toString() {
        return "Adf{" +
                "adfSimplexInputCaps=" + adfSimplexInputCaps +
                '}';
    }
}
