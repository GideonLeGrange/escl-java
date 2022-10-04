package me.legrange.scanner.escl;

import org.simpleframework.xml.Element;

import java.util.List;

public class Jobs {

    @Element(name = "JobInfo")
    private List<JobInfo> jobs;

    @Override
    public String toString() {
        return "Jobs{" +
                "jobs=" + jobs +
                '}';
    }
}
