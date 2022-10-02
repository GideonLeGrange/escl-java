package me.legrange.escl.discovery;

import java.util.List;

public final class ScannerRecord {

    private final String uuid;
    private   String version;
    private   String adminUrl;
    private  String representation;
    private  String resource;
    private  String makeModel;
    private  String note;
    private  List<String> mimeTypes;
    private  List<String> colorSpace;
    private  List<String> inputSource;
    private String duplex;

     ScannerRecord(String uuid) {
        this.uuid = uuid;
    }

    private String getUuid() {
        return uuid;
    }

    private String getVersion() {
        return version;
    }

    void setVersion(String version) {
        this.version = version;
    }

    private String getAdminUrl() {
        return adminUrl;
    }

    void setAdminUrl(String adminUrl) {
        this.adminUrl = adminUrl;
    }

    private String getRepresentation() {
        return representation;
    }

    void setRepresentation(String representation) {
        this.representation = representation;
    }

    private String getResource() {
        return resource;
    }

    void setResource(String resource) {
        this.resource = resource;
    }

    private String getMakeModel() {
        return makeModel;
    }

    void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    private String getNote() {
        return note;
    }

    void setNote(String note) {
        this.note = note;
    }

    private List<String> getMimeTypes() {
        return mimeTypes;
    }

    void setMimeTypes(List<String> mimeTypes) {
        this.mimeTypes = mimeTypes;
    }

    private List<String> getColorSpace() {
        return colorSpace;
    }

    void setColorSpace(List<String> colorSpace) {
        this.colorSpace = colorSpace;
    }

    private List<String> getInputSource() {
        return inputSource;
    }

    void setInputSource(List<String> inputSource) {
        this.inputSource = inputSource;
    }

    private String getDuplex() {
        return duplex;
    }

    void setDuplex(String duplex) {
        this.duplex = duplex;
    }

    @Override
    public String toString() {
        return "ScannerRecord{" +
                "uuid='" + uuid + '\'' +
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
