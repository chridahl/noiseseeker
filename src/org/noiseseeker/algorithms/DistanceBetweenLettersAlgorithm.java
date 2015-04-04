package org.noiseseeker.algorithms;

import org.apache.commons.configuration.AbstractFileConfiguration;
import org.noiseseeker.fitnesscalculators.BitBufferFitnessCalculator;
import org.noiseseeker.helpers.AnyBaseNumberBitBuffer;
import org.noiseseeker.interfaces.INoiseSeekerExperiment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdk on 04.04.2015.
 */
public class DistanceBetweenLettersAlgorithm implements INoiseSeekerExperiment
{
    private AbstractFileConfiguration applicationProperties;
    private BitBufferFitnessCalculator bitBufferLinesFitnessCalculator;

    public DistanceBetweenLettersAlgorithm(AbstractFileConfiguration applicationProperties,
                                           BitBufferFitnessCalculator bitBufferFitnessCalculator)
    {
        this.applicationProperties = applicationProperties;
        this.bitBufferLinesFitnessCalculator = bitBufferFitnessCalculator;

    }

    public void run() {

        Integer[][] a = AnyBaseNumberBitBuffer.BitBufferFromString(8, "01111110" + "01000010" + "01000010" + "01111110" + "01000010" + "01000010" + "01000010" + "01000010");
        Integer[][] c = AnyBaseNumberBitBuffer.BitBufferFromString(8, "01111110" + "01000000" + "01000000" + "01000000" + "01000000" + "01000000" + "01000000" + "01111110");
        Integer[][] e = AnyBaseNumberBitBuffer.BitBufferFromString(8, "01111110" + "01000000" + "01000000" + "01111100" + "01000000" + "01000000" + "01000000" + "01111110");
        Integer[][] s = AnyBaseNumberBitBuffer.BitBufferFromString(8, "01111110" + "01000000" + "01000000" + "01111110" + "00000010" + "00000010" + "00000010" + "01111110");
        Integer[][] o = AnyBaseNumberBitBuffer.BitBufferFromString(8,
                "11111111" +
                        "10000001" +
                        "10000001" +
                        "10000001" +
                        "10000001" +
                        "10000001" +
                        "10000001" +
                        "11111111"
        );
        Integer[][] r1 = AnyBaseNumberBitBuffer.BitBufferFromString(8,
                "10000111" +
                        "11111111" +
                        "11111111" +
                        "11111111" +
                        "00100000" +
                        "00000100" +
                        "00100000" +
                        "10000000"
        );

        List<Double> fitnessValues = new ArrayList<Double>();
        fitnessValues.add(bitBufferLinesFitnessCalculator.calculateFitnessScore(8, 8, a));
        fitnessValues.add(bitBufferLinesFitnessCalculator.calculateFitnessScore(8, 8, c));
        fitnessValues.add(bitBufferLinesFitnessCalculator.calculateFitnessScore(8, 8, e));
        fitnessValues.add(bitBufferLinesFitnessCalculator.calculateFitnessScore(8, 8, s));
        fitnessValues.add(bitBufferLinesFitnessCalculator.calculateFitnessScore(8, 8, o));
        fitnessValues.add(bitBufferLinesFitnessCalculator.calculateFitnessScore(8, 8, r1));


        for (double fitnessValue : fitnessValues)
        {
            System.out.println("Fitness value " + fitnessValue);
        }




    }
}

/*
 "00000000" +
 "00000000" +
 "00000000" +
 "00000000" +
 "00000000" +
 "00000000" +
 "00000000" +
 "00000000"
 */
