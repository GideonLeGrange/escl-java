package me.legrange.escl;

import org.simpleframework.xml.Element;

public class DiscreteResolution {

    @Element(name = "XResolution")
    private int xResolution;
    @Element(name = "YResolution")
    private int yResolution;

    @Override
    public String toString() {
        return "DiscreteResolution{" +
                "xResolution=" + xResolution +
                ", yResolution=" + yResolution +
                '}';
    }
}
