package org.noiseseeker;

import java.util.Enumeration;

/**
 * Created by cdk on 03.04.2015.
 */
public class LinesFitnessCalculator extends FitnessCalculator
{
    /**
     * This fitness functions rewards buffers with few long lines. In addition, it rewards buffers where only
     * 40-60% of the buffers is marked.
     * @return
     */
    @Override
    public double calculateFitnessScore()
    {
        int numberOfLines = this.registeredLines.size();

        int totalLength = 0;
        int fitnessScore = 0;

        Enumeration<Long> enumKey = this.registeredLines.keys();

        while(enumKey.hasMoreElements())
        {
            Long key = enumKey.nextElement();
            int value = this.registeredLines.get(key);
            totalLength += value;
        }

        long searchSpace = this.dimensionX * this.dimensionY;
        long markedBits = getMarkedBits();

        fitnessScore = totalLength - numberOfLines;

        // Try to keep ratio between 40% - 60% of marked bits. Reward buffers in this zone.
        double percentageMarkedBits = (markedBits / searchSpace) * 100;

        if ( percentageMarkedBits >= 40.0 && percentageMarkedBits <= 60.0)
        {
            fitnessScore += fitnessScore * percentageMarkedBits;
        }

        return fitnessScore;
    }
}
