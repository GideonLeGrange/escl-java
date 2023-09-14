package me.legrange.scanner.escl;

import org.simpleframework.xml.Element;

public final class ScanBufferInfo {

    @Element(name="ScanSettings", required = true)
    private ScanSettings scanSettings;

    @Element(name="ImageWidth", required = true)
    private int imageWidth;
    @Element(name="ImageHeight", required = true)
    private int imageHeight;
    @Element(name="BytesPerLine", required = true)
    private int bytesPerLine;

    @Override
    public String toString() {
        return "ScanBufferInfo{" +
                "scanSettings=" + scanSettings +
                ", imageWidth=" + imageWidth +
                ", imageHeight=" + imageHeight +
                ", bytesPerLine=" + bytesPerLine +
                '}';
    }
}
