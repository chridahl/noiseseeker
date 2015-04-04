package org.noiseseeker;

import org.apache.commons.configuration.AbstractFileConfiguration;

public class NoiseSeekerLineFitnessAlgorithm implements INoiseSeekerExperiment
{
    private AbstractFileConfiguration applicationProperties;
    private FitnessCalculator fitnessCalculator;

    public NoiseSeekerLineFitnessAlgorithm(AbstractFileConfiguration applicationProperties,
                                           FitnessCalculator fitnessCalculator)
    {
        this.applicationProperties = applicationProperties;
        this.fitnessCalculator = fitnessCalculator;
    }

    public void run()
    {
        int numberOfCells = this.applicationProperties.getInt("Cells");
        int maxCellSize = this.applicationProperties.getInt("MaxCellSize");
        int pngWidth = this.applicationProperties.getInt("PNGWidth");
        int pngHeight = this.applicationProperties.getInt("PNGHeight");
        String[] startNumber = this.applicationProperties.getStringArray("StartNumber");

        NoiseHelper noiseHelper = new NoiseHelper(numberOfCells, maxCellSize);

        Integer[] values = new Integer[numberOfCells];
        Integer[] currentMaxFitnessNumber = new Integer[numberOfCells];
        double currentMaxFitnessValue = 0;
        String currentMaxFitnessBuffer;

        for(int startNumberIterator = 0; startNumberIterator < numberOfCells; startNumberIterator++)
        {
            values[startNumberIterator] = Integer.parseInt(startNumber[startNumberIterator]);
        }

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
                NumberToMedia.CreatePNG(noiseHelper, numberOfCells, pngWidth, pngHeight, pngFilename);

            }
        }

    }
}

