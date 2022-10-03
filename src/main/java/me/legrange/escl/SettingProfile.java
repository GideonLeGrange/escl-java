package me.legrange.escl;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class SettingProfile {

    @ElementList(name = "ColorModes", type = String.class, entry = "ColorMode")
    private List<String> colorModes;
    @ElementList(name = "ContentTypes", type = String.class, entry = "ContentType", required = false)
    private List<String> contentTypes;
    @ElementList(name = "DocumentFormats", type = String.class, entry = "DocumentFormat")
    private List<String> documentFormats;
    @Element(name = "SupportedResolutions", type = DiscreteResolutions.class)
    private DiscreteResolutions supportedResolutions;
    @ElementList(name = "ColorSpaces", type = String.class, entry = "ColorSpace")
    private List<String> colorSpaces;
    @ElementList(name = "CcdChannels", type = String.class, entry = "CcdChannel")
    private List<String> ccdChannels;

    @Override
    public String toString() {
        return "SettingProfile{" +
                "colorModes=" + colorModes +
                ", contentTypes=" + contentTypes +
                ", documentFormats=" + documentFormats +
                ", supportedResolutions=" + supportedResolutions +
                ", colorSpaces=" + colorSpaces +
                ", ccdChannels=" + ccdChannels +
                '}';
    }
}
