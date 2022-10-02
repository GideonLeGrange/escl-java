package me.legrange.escl;

import org.simpleframework.xml.Element;

public class Platen {

    @Element(name = "PlatenInputCaps")
    private  PlatenInputCaps platenInputCaps;

    @Override
    public String toString() {
        return "Platen{" +
                "platenInputCaps=" + platenInputCaps +
                '}';
    }
}
