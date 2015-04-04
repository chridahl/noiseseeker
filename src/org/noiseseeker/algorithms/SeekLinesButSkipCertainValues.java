package org.noiseseeker.algorithms;

import org.apache.commons.configuration.AbstractFileConfiguration;
import org.noiseseeker.fitnesscalculators.BitBufferFitnessCalculator;
import org.noiseseeker.helpers.AnyBaseNumber;
import org.noiseseeker.helpers.AnyBaseNumberBitBuffer;
import org.noiseseeker.helpers.NumberToMedia;
import org.noiseseeker.interfaces.INoiseSeekerExperiment;

public class SeekLinesButSkipCertainValues implements INoiseSeekerExperiment
{
    private AbstractFileConfiguration applicationProperties;
    private BitBufferFitnessCalculator bitBufferLinesFitnessCalculator;

    public SeekLinesButSkipCertainValues(AbstractFileConfiguration applicationProperties,
                                           BitBufferFitnessCalculator bitBufferFitnessCalculator)
    {
        this.applicationProperties = applicationProperties;
        this.bitBufferLinesFitnessCalculator = bitBufferFitnessCalculator;
    }


    /**
     * Skip values that contribute to long bit rows (1111 1111 etc.)
     * @param values
     * @return
     */
    public boolean shouldSkipValue(Integer[] values)
    {
        for(int value : values)
            if ( value == 255
                        || value == 254
                        || value == 252
                        || value == 248
                        || value == 240
                        || value == 127
                        || value == 63
                        || value == 31
                        || value == 15
                        || value == 7)
                return true;

        return false;
    }


    public void run() throws Exception
    {
        int numberOfUnits = this.applicationProperties.getInt("NumberOfUnits");
        int base = this.applicationProperties.getInt("Base");
        int pngWidth = this.applicationProperties.getInt("PNGWidth");
        int pngHeight = this.applicationProperties.getInt("PNGHeight");
        String[] startNumber = this.applicationProperties.getStringArray("StartNumber");

        if ( numberOfUnits != 8 && base != 256)
            throw new Exception("SeekLinesButSkipCertainValues will only work with base 256 and 8 as number of units.");

        AnyBaseNumberBitBuffer anyBaseNumberBitBuffer = new AnyBaseNumberBitBuffer(numberOfUnits, base);
        Integer[] values = new Integer[numberOfUnits];
        Integer[] currentMaxFitnessNumber = new Integer[numberOfUnits];
        double currentMaxFitnessValue = 0;
        String currentMaxFitnessBuffer;

        for(int startNumberIterator = 0; startNumberIterator < numberOfUnits; startNumberIterator++)
            values[startNumberIterator] = Integer.parseInt(startNumber[startNumberIterator]);

        long filenameIdIterator = 0L;

        for(;;)
        {
            AnyBaseNumber.NextValue(numberOfUnits, base, values);

            if ( !shouldSkipValue(values))
            {
                anyBaseNumberBitBuffer.setupCellsFromArray(values);
                Integer[][] buffer = anyBaseNumberBitBuffer.getBitBuffer();

                double fitnessValue = bitBufferLinesFitnessCalculator.calculateFitnessScore(anyBaseNumberBitBuffer.getNumberOfUnits(), anyBaseNumberBitBuffer.getNumberOfUnits(), buffer);

                if ( fitnessValue > 20)
                {
                    currentMaxFitnessValue = fitnessValue;
                    currentMaxFitnessBuffer = AnyBaseNumber.GetAsString(numberOfUnits, values);
                    System.out.println("Current leader has fitness " + currentMaxFitnessValue + " and " + currentMaxFitnessBuffer);

                    String pngFilename = String.format("pngs/test-%1s.png", filenameIdIterator++);
                    NumberToMedia.CreatePNG(anyBaseNumberBitBuffer, numberOfUnits, pngWidth, pngHeight, pngFilename);
                }

            }
        }

    }
}

