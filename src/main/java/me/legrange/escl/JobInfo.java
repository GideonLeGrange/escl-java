package me.legrange.escl;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class JobInfo {

    @Element(name = "JobUri")
    private String jobUri;
    @Element(name = "JobUuid")
    private String uuid;
    @Element(name = "Age")
    private int age;
    @Element(name = "ImagesCompleted")
    private int imagesCompleted;
    @Element(name = "ImagesToTransfer")
    private int imagesToTransfer;
    @Element(name = "JobState")
    private String state;
    @ElementList(name = "JobStateReasons", required = false, type = String.class, entry="JobStateReason")
    private List<String> reason;

    public String getJobUri() {
        return jobUri;
    }

    public String getUuid() {
        int idx = uuid.lastIndexOf(":");
        if (idx > 0) {
            return uuid.substring(idx + 1);
        }
        return uuid;
    }

    public int getAge() {
        return age;
    }

    public int getImagesCompleted() {
        return imagesCompleted;
    }

    public int getImagesToTransfer() {
        return imagesToTransfer;
    }

    public String getState() {
        return state;
    }

    public List<String> getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "JobInfo{" +
                "jobUri='" + jobUri + '\'' +
                ", uuid='" + uuid + '\'' +
                ", age=" + age +
                ", imagesCompleted=" + imagesCompleted +
                ", imagesToTransfer=" + imagesToTransfer +
                ", state='" + state + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
