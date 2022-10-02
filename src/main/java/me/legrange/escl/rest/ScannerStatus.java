package me.legrange.escl.rest;

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
    @ElementList(name = "Jobs", type = JobInfo.class, entry = "JobInfo")
    private List<JobInfo> jobs;


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