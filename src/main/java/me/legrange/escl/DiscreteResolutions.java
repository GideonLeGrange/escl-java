package me.legrange.escl;

import org.simpleframework.xml.ElementList;

import java.util.List;

public class DiscreteResolutions {

    @ElementList(name = "DiscreteResolutions", type = DiscreteResolution.class, entry = "DiscreteResolution")
    private List<DiscreteResolution> resolutions;

    @Override
    public String toString() {
        return "DiscreteResolutions{" +
                "resolutions=" + resolutions +
                '}';
    }
}
