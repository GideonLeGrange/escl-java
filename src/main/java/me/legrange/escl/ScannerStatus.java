package me.legrange.escl;

import me.legrange.escl.JobInfo;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.List;

public class ScannerStatus {

    @Element(name = "Version")
    private String version;
    @Element(name = "State")
    private String state;
    @Element(name = "AdfState", required = false)
    private String adfState;
    @ElementList(name = "Jobs", type = JobInfo.class, entry = "JobInfo", required = false)
    private List<JobInfo> jobs;

    public String getVersion() {
        return version;
    }

    public String getState() {
        return state;
    }

    public String getAdfState() {
        return adfState;
    }

    public List<JobInfo> getJobs() {
        return jobs;
    }

    @Override
    public String toString() {
        return "ScannerStatus{" +
                "version='" + version + '\'' +
                ", state='" + state + '\'' +
                ", adfState='" + adfState + '\'' +
                ", jobs=" + jobs +
                '}';
    }
}
