package org.noiseseeker;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.noiseseeker.fitnessfunctions.LinesCountAndLengthBitBufferFitness;
import org.noiseseeker.interfaces.INoiseSeekerExperiment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args) {

        File configurationFile = new File("application.properties");
        PropertiesConfiguration applicationProperties = new PropertiesConfiguration();

        try {
            applicationProperties.load(configurationFile);
        } catch (Exception exception) {
            System.out.println("Could not open and/or read application.properties");
            System.exit(1);
        }

        List<INoiseSeekerExperiment> experiments = new ArrayList<INoiseSeekerExperiment>();
        experiments.add(new NoiseSeekerLineFitnessAlgorithm(applicationProperties, new LinesCountAndLengthBitBufferFitness()));

        for (INoiseSeekerExperiment experiment : experiments)
        {
            experiment.run();
        }

    }
}

