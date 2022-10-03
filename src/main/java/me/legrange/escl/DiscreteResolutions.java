package me.legrange.escl;

import org.simpleframework.xml.ElementList;

import java.util.List;

public class DiscreteResolutions {

    @ElementList(name = "DiscreteResolutions", type = DiscreteResolution.class, entry = "DiscreteResolution", required = false)
    private List<DiscreteResolution> discreteResolutions;

    public List<DiscreteResolution> getDiscreteResolutions() {
        return discreteResolutions;
    }

    @Override
    public String toString() {
        return "DiscreteResolutions{" +
                "discreteResolutions=" + discreteResolutions +
                '}';
    }
}
