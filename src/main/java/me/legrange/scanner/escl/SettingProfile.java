package me.legrange.scanner.escl;

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
    @Element(name = "SupportedResolutions")
    private DiscreteResolutions supportedResolutions;
    @ElementList(name = "ColorSpaces", type = String.class, entry = "ColorSpace")
    private List<String> colorSpaces;
    @ElementList(name = "CcdChannels", type = String.class, entry = "CcdChannel")
    private List<String> ccdChannels;

    public List<String> getColorModes() {
        return colorModes;
    }

    public List<String> getContentTypes() {
        return contentTypes;
    }

    public List<String> getDocumentFormats() {
        return documentFormats;
    }

    public DiscreteResolutions getSupportedResolutions() {
        return supportedResolutions;
    }

    public List<String> getColorSpaces() {
        return colorSpaces;
    }

    public List<String> getCcdChannels() {
        return ccdChannels;
    }
}
