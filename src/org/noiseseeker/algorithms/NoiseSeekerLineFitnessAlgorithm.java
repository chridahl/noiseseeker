package org.noiseseeker.algorithms;

import org.apache.commons.configuration.AbstractFileConfiguration;
import org.noiseseeker.fitnesscalculators.BitBufferFitnessCalculator;
import org.noiseseeker.helpers.AnyBaseNumber;
import org.noiseseeker.helpers.NoiseBuffer;
import org.noiseseeker.helpers.NumberToMedia;
import org.noiseseeker.interfaces.INoiseSeekerExperiment;

public class NoiseSeekerLineFitnessAlgorithm implements INoiseSeekerExperiment
{
    private AbstractFileConfiguration applicationProperties;
    private BitBufferFitnessCalculator bitBufferLinesFitnessCalculator;

    public NoiseSeekerLineFitnessAlgorithm(AbstractFileConfiguration applicationProperties,
                                           BitBufferFitnessCalculator bitBufferFitnessCalculator)
    {
        this.applicationProperties = applicationProperties;
        this.bitBufferLinesFitnessCalculator = bitBufferFitnessCalculator;
    }

    public void run()
    {
        int numberOfUnits = this.applicationProperties.getInt("NumberOfUnits");
        int base = this.applicationProperties.getInt("Base");
        int pngWidth = this.applicationProperties.getInt("PNGWidth");
        int pngHeight = this.applicationProperties.getInt("PNGHeight");
        String[] startNumber = this.applicationProperties.getStringArray("StartNumber");

        NoiseBuffer noiseBuffer = new NoiseBuffer(numberOfUnits, base);
        Integer[] values = new Integer[numberOfUnits];
        Integer[] currentMaxFitnessNumber = new Integer[numberOfUnits];
        double currentMaxFitnessValue = 0;
        String currentMaxFitnessBuffer;

        for(int startNumberIterator = 0; startNumberIterator < numberOfUnits; startNumberIterator++)
        {
            values[startNumberIterator] = Integer.parseInt(startNumber[startNumberIterator]);
        }

        // Looping on a scalar value makes little sense here. If we are to scale up the bitBuffer (e.g. 16 x 16) the
        // number of possible combinations is higher than that of long's MAX_VALUE (2^63-1).
        for(;;)
        {
            AnyBaseNumber.NextValue(numberOfUnits, base, values);
            noiseBuffer.setupCellsFromArray(values);
            Integer[][] buffer = noiseBuffer.getBitBuffer();

            double fitnessValue = bitBufferLinesFitnessCalculator.calculateFitnessScore(noiseBuffer.getNumberOfUnits(), noiseBuffer.getNumberOfUnits(), buffer);

            if ( fitnessValue > currentMaxFitnessValue)
            {
                currentMaxFitnessValue = fitnessValue;
                currentMaxFitnessBuffer = AnyBaseNumber.GetAsString(numberOfUnits, values);
                System.out.println("Current leader has fitness " + currentMaxFitnessValue + " and " + currentMaxFitnessBuffer);

                String pngFilename = String.format("pngs/test-%1s.png", fitnessValue);
                NumberToMedia.CreatePNG(noiseBuffer, numberOfUnits, pngWidth, pngHeight, pngFilename);

            }
        }

    }
}

