package me.legrange.scanner.escl;

import org.simpleframework.xml.Element;

public class Platen {

    @Element(name = "PlatenInputCaps")
    private InputCaps platenInputCaps;


    public InputCaps getPlatenInputCaps() {
        return platenInputCaps;
    }




}
