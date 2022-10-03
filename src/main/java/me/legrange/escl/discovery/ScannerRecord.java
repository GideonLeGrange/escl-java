package me.legrange.escl.discovery;

import java.util.List;

public final class ScannerRecord {

    private final String uuid;
    private final List<String> addresses;
    private final int port;
    private String version;
    private String adminUrl;
    private String representation;
    private String resource;
    private String makeModel;
    private String note;
    private List<String> mimeTypes;
    private List<String> colorSpace;
    private List<String> inputSource;
    private String duplex;

     ScannerRecord(String uuid, List<String> addresses, int port) {
        this.uuid = uuid;
        this.addresses = addresses;
        this.port = port;
    }

    public String getUuid() {
        return uuid;
    }

    public String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }

    public String getAdminUrl() {
        return adminUrl;
    }

    void setAdminUrl(String adminUrl) {
        this.adminUrl = adminUrl;
    }

    public String getRepresentation() {
        return representation;
    }

    void setRepresentation(String representation) {
        this.representation = representation;
    }

    public String getResource() {
        return resource;
    }

    void setResource(String resource) {
        this.resource = resource;
    }

    public String getMakeModel() {
        return makeModel;
    }

    void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public String getNote() {
        return note;
    }

    void setNote(String note) {
        this.note = note;
    }

    public List<String> getMimeTypes() {
        return mimeTypes;
    }

    void setMimeTypes(List<String> mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    public List<String> getColorSpace() {
        return colorSpace;
    }

    void setColorSpace(List<String> colorSpace) {
        this.colorSpace = colorSpace;
    }

    public List<String> getInputSource() {
        return inputSource;
    }

    public void setInputSource(List<String> inputSource) {
        this.inputSource = inputSource;
    }

    String getDuplex() {
        return duplex;
    }

    void setDuplex(String duplex) {
        this.duplex = duplex;
    }

    @Override
    public String toString() {
        return "ScannerRecord{" +
                "uuid='" + uuid + '\'' +
                ", addresses=" + addresses +
                ", port=" + port +
                ", version='" + version + '\'' +
                ", adminUrl='" + adminUrl + '\'' +
                ", representation='" + representation + '\'' +
                ", resource='" + resource + '\'' +
                ", makeModel='" + makeModel + '\'' +
                ", note='" + note + '\'' +
                ", mimeTypes=" + mimeTypes +
                ", colorSpace=" + colorSpace +
                ", inputSource=" + inputSource +
                ", duplex='" + duplex + '\'' +
                '}';
    }
}
