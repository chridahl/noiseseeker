package org.noiseseeker.algorithms;

import org.apache.commons.configuration.AbstractFileConfiguration;
import org.noiseseeker.analyzers.anynumber.Base256Size8BlockAnalyzer;
import org.noiseseeker.analyzers.anynumber.IAnyNumberAnalyzer;
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


    public void run() throws Exception
    {
        int numberOfUnits = this.applicationProperties.getInt("NumberOfUnits");
        int base = this.applicationProperties.getInt("Base");
        int pngWidth = this.applicationProperties.getInt("PNGWidth");
        int pngHeight = this.applicationProperties.getInt("PNGHeight");

        double currentMaxFitnessValue = 0;
        String currentMaxFitnessBuffer;
        String[] startNumber = this.applicationProperties.getStringArray("StartNumber");

        if ( numberOfUnits != 8 && base != 256)
            throw new Exception("SeekLinesButSkipCertainValues will only work with base 256 and 8 as number of units.");

        AnyBaseNumberBitBuffer anyBaseNumberBitBuffer = new AnyBaseNumberBitBuffer(numberOfUnits, base);
        IAnyNumberAnalyzer blockFilter = new Base256Size8BlockAnalyzer();

        Integer[] values = new Integer[numberOfUnits];
        Integer[] currentMaxFitnessNumber = new Integer[numberOfUnits];

        for(int startNumberIterator = 0; startNumberIterator < numberOfUnits; startNumberIterator++)
            values[startNumberIterator] = Integer.parseInt(startNumber[startNumberIterator]);

        long filenameIdIterator = 0L;

        for(;;)
        {
            AnyBaseNumber.NextValue(numberOfUnits, base, values);

            if ( blockFilter.UseNumber(values) && Math.random() > 0.5)
            {
                anyBaseNumberBitBuffer.setupCellsFromArray(values);
                Integer[][] buffer = anyBaseNumberBitBuffer.getBitBuffer();

                double fitnessValue = bitBufferLinesFitnessCalculator.calculateFitnessScore(anyBaseNumberBitBuffer.getNumberOfUnits(), anyBaseNumberBitBuffer.getNumberOfUnits(), buffer);

                if ( fitnessValue > 17)
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

