package org.noiseseeker.fitnesscalculators;

import java.util.Hashtable;

public abstract class BitBufferFitnessCalculator
{
    public BitBufferFitnessCalculator()
    {
    }

    /**
     *
     * @return
     */
    public abstract double calculateFitnessScore(int width, int height, Integer[][] bitBuffer);

}
