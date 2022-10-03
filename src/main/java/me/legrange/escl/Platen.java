package me.legrange.escl;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class Platen {

    @Element(name = "PlatenInputCaps")
    private InputCaps platenInputCaps;


    public InputCaps getPlatenInputCaps() {
        return platenInputCaps;
    }




}
