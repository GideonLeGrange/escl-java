package me.legrange.escl;

import org.simpleframework.xml.Element;

public class ScanRegion {

    @Element(name = "Width")
    private int width;
    @Element(name = "Height")
    private int height;
    @Element(name = "XOffset")
    private int xOffset;
    @Element(name = "YOffset")
    private int yOffset;


    public ScanRegion(int width, int height, int xOffset, int yOffset) {
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    @Override
    public String toString() {
        return "ScanRegion{" +
                "width=" + width +
                ", height=" + height +
                ", xOffset=" + xOffset +
                ", yOffset=" + yOffset +
                '}';
    }
}
