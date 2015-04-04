package org.noiseseeker;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.noiseseeker.algorithms.DistanceBetweenLettersAlgorithm;
import org.noiseseeker.algorithms.NoiseSeekerLineFitnessAlgorithm;
import org.noiseseeker.algorithms.SeekLinesButSkipCertainValues;
import org.noiseseeker.fitnessfunctions.LinesAndBlocksBitBufferFitness;
import org.noiseseeker.fitnessfunctions.LinesCountAndLengthBitBufferFitness;
import org.noiseseeker.interfaces.INoiseSeekerExperiment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {

        File configurationFile = new File("application.properties");
        PropertiesConfiguration applicationProperties = new PropertiesConfiguration();

        try
        {
            applicationProperties.load(configurationFile);
        }
        catch (Exception exception)
        {
            System.out.println("Could not open and/or read application.properties");
            System.exit(1);
        }

        // Setup and run all experiments

        List<INoiseSeekerExperiment> experiments = new ArrayList<INoiseSeekerExperiment>();
        //experiments.add(new NoiseSeekerLineFitnessAlgorithm(applicationProperties, new LinesCountAndLengthBitBufferFitness()));
        experiments.add(new SeekLinesButSkipCertainValues(applicationProperties, new LinesAndBlocksBitBufferFitness()));

        try
        {
            for (INoiseSeekerExperiment experiment : experiments)
            {
                experiment.run();
            }
        }
        catch(Exception exception)
        {
            System.out.println("Experiment failed with " + exception.toString());
            System.exit(1);
        }

    }
}

