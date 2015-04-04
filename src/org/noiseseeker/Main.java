package org.noiseseeker;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;

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
        catch(Exception exception)
        {
            System.out.println("Could not open and/or read application.properties");
            System.exit(1);
        }

        FitnessCalculator lineFitnessCalculator = new LinesCountAndLengthFitnessCalculator();
        NoiseSeekerExperimentRunner experimentOneRunner = new NoiseSeekerExperimentRunner(applicationProperties, lineFitnessCalculator);
        experimentOneRunner.run();
    }
}

