package me.legrange.scanner.escl;

import org.simpleframework.xml.Element;

public class DiscreteResolution {

    @Element(name = "XResolution", required = false)
    private int xResolution;
    @Element(name = "YResolution", required = false)
    private int yResolution;

    public int getxResolution() {
        return xResolution;
    }

    public int getyResolution() {
        return yResolution;
    }

}
