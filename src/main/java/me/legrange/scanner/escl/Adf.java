package me.legrange.scanner.escl;

import org.simpleframework.xml.Element;

public final class Adf {

    @Element(name = "AdfSimplexInputCaps", required = false)
    private InputCaps adfSimplexInputCaps;
    @Element(name = "AdfDuplexInputCaps", required = false)
    private InputCaps adfDuplexInputCaps;

    public InputCaps getAdfSimplexInputCaps() {
        return adfSimplexInputCaps;
    }

    public InputCaps getAdfDuplexInputCaps() {
        return adfDuplexInputCaps;
    }
}
