package me.legrange.escl;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;

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
    @ElementList(name = "SettingProfiles", type = SettingProfile.class, entry = "SettingProfile")
    private List<SettingProfile> settingProfiles;

//    @ElementList(name = "SettingsProfiles", type = SettingProfile.class, entry = "SettingProfile", inline = false)
//    private List<SettingProfile> settingsProfiles;


    public int getMinWidth() {
        return minWidth;
    }

    public int getMaxWidth() {
        return maxWidth;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMaxScanRegions() {
        return maxScanRegions;
    }


    public List<SettingProfile> getSettingProfiles() {
        return settingProfiles;
    }
}
