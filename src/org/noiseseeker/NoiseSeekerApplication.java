package org.noiseseeker;

import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.File;

/**
 * Created by cdk on 04.04.2015.
 */
public class NoiseSeekerApplication
{
    public NoiseSeekerApplication()
    {

    }

    public void run()
    {
        // Current leader has fitness 147.0 and  0 0 0 0 5 173 254 191
        // Current leader has fitness 384.0 and  0 0 0 0 5 190 175 207
        // Current leader has fitness 432.0 and  0 0 0 0 5 250 255 255
        // Current leader has fitness 440.0 and  0 0 0 0 5 254 255 255
        // The winner with max fitness of 440.0 is  0 0 0 0 3 250 255 255
        // The winner with max fitness of 47.0 is  0 0 0 0 3 250 255 255

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

        int numberOfCells = applicationProperties.getInt("Cells");
        int maxCellSize = applicationProperties.getInt("MaxCellSize");


        NoiseHelper noiseHelper = new NoiseHelper(numberOfCells, maxCellSize);
        FitnessCalculator fitnessCalculator = new LinesCountAndLengthFitnessCalculator();
        Integer[] values = new Integer[numberOfCells];

        String[] startNumber = applicationProperties.getStringArray("StartNumber");

        for(int startNumberIterator = 0; startNumberIterator < numberOfCells; startNumberIterator++)
        {
            values[startNumberIterator] = Integer.parseInt(startNumber[startNumberIterator]);
        }

        String currentMaxFitnessBuffer = "";
        Integer[] currentMaxFitnessValues = new Integer[]{};
        double currentMaxFitnessValue = 0;

        // Looping on a scalar value makes little sense here. If we are to scale up the buffer (e.g. 16 x 16) the
        // number of possible combinations is higher than that of long's MAX_VALUE (2^63-1).
        for(;;)
        {
            noiseHelper.intArrayNext(values);
            noiseHelper.setupCellsFromArray(values);
            Integer[][] buffer = noiseHelper.getCurrentBuffer();

            fitnessCalculator.setBuffer(noiseHelper.getNumberOfCells(), noiseHelper.getNumberOfCells(), buffer);
            fitnessCalculator.scanBuffer();

            double fitnessValue = fitnessCalculator.calculateFitnessScore();

            if ( fitnessValue > currentMaxFitnessValue)
            {
                currentMaxFitnessValue = fitnessValue;
                currentMaxFitnessBuffer = noiseHelper.intArrayGetString(values);
                System.out.println("Current leader has fitness " + currentMaxFitnessValue + " and " + currentMaxFitnessBuffer);

                String pngFilename = String.format("pngs/test-%1s.png", fitnessValue);
                noiseHelper.writeBlackAndWhiteImageFileFromBuffer(20, 20, buffer, pngFilename);
            }
        }

    }
}
