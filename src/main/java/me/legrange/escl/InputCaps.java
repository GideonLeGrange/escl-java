package me.legrange.escl;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class InputCaps {

    @Element(name = "MinWidth")
    private int minWidth;
    @Element(name = "MaxWidth")
    private int maxWidth;
    @Element(name = "MinHeight")
    private int minHeight;
    @Element(name = "MaxHeight")
    private int maxHeight;
    @Element(name = "MaxScanRegions")
    private int maxScanRegions;
    @ElementList(name = "SettingProfiles", type = SettingProfile.class)
    private List<SettingProfile> settingProfiles;

    @Override
    public String toString() {
        return "PlatenInputCaps{" +
                "minWidth=" + minWidth +
                ", maxWidth=" + maxWidth +
                ", minHeight=" + minHeight +
                ", maxHeight=" + maxHeight +
                ", maxRegions=" + maxScanRegions +
                ", settingProfiles=" + settingProfiles +
                '}';
    }
}
