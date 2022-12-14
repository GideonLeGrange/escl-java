package me.legrange.scanner.escl;
import org.simpleframework.xml.Element;

public class ScannerCapabilities {

    @Element(name = "UUID", required = false)
    private String uuid;
    @Element(name = "Version")
    private String version;
    @Element(name = "MakeAndModel", required = false)
    private String makeAndModel;
    @Element(name = "SerialNumber", required = false)
    private String serialNumber;
    @Element(name = "AdminURI", required = false)
    private String adminUri;
    @Element(name = "IconURI", required = false)
    private String iconUri;

    @Element(name="Platen", required = false)
    private Platen platen;
    @Element(name = "Adf", required = false)
    private Adf adf;

    public String getUuid() {
        return uuid;
    }

    public String getVersion() {
        return version;
    }

    public String getMakeAndModel() {
        return makeAndModel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getAdminUri() {
        return adminUri;
    }

    public String getIconUri() {
        return iconUri;
    }

    public Platen getPlaten() {
        return platen;
    }

    public Adf getAdf() {
        return adf;
    }

    @Override
    public String toString() {
        return "ScannerCapabilities{" +
                "uuid='" + uuid + '\'' +
                ", version='" + version + '\'' +
                ", makeAndModel='" + makeAndModel + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", adminUri='" + adminUri + '\'' +
                ", iconUri='" + iconUri + '\'' +
                ", platen=" + platen +
                ", adf=" + adf +
                '}';
    }
}
