package me.legrange.scanner.escl;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class ScanSettings {

    @Element(name = "Version")
    private String version;
    @Element(name = "Intent")
    private String intent;
    @ElementList(name = "ScanRegions")
    private List<ScanRegion> scanRegions;
    @Element(name = "InputSource")
    private String inputSource;
    @Element(name = "ColorMode")
    private String colorMode;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getIntent() {
        return intent;
    }

    public void setIntent(String intent) {
        this.intent = intent;
    }

    public List<ScanRegion> getScanRegions() {
        return scanRegions;
    }

    public void setScanRegions(List<ScanRegion> scanRegions) {
        this.scanRegions = scanRegions;
    }

    public String getInputSource() {
        return inputSource;
    }

    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }

    public String getColorMode() {
        return colorMode;
    }

    public void setColorMode(String colorMode) {
        this.colorMode = colorMode;
    }
/*
    <scan:ScanSettings
xmlns:scan="http://schemas.hp.com/imaging/escl/2011/05/03
" xmlns:pwg="http://www.pwg.org/schemas/2010/12/sm">
  <pwg:Version>2.6</pwg:Version>
  <scan:Intent>Photo</scan:Intent>
  <pwg:ScanRegions>
   <pwg:ScanRegion>
     <pwg:Height>1200</pwg:Height>
  <pwg:ContentRegionUnits>escl:ThreeHundredthsOfInches</p
wg:ContentRegionUnits>
     <pwg:Width>1800</pwg:Width>
     <pwg:XOffset>0</pwg:XOffset>
     <pwg:YOffset>0</pwg:YOffset>
   </pwg:ScanRegion>
  </pwg:ScanRegions>
  <pwg:InputSource>Platen</pwg:InputSource>
  <scan:ColorMode>Grayscale8</scan:ColorMode>
</scan:ScanSettings>
     */
}
