package org.noiseseeker.fitnessfunctions;

import org.noiseseeker.BitBufferFitnessCalculator;
import org.noiseseeker.analyzers.LinesAnalyzer;

import java.util.Enumeration;

/**
 * Created by cdk on 03.04.2015.
 */
public class LinesCountAndLengthBitBufferFitness extends BitBufferFitnessCalculator
{
    /**
     * This fitness functions rewards buffers with few long lines. In addition, it rewards buffers where only
     * 40-60% of the buffers is marked.
     * @return
     */
    @Override
    public double calculateFitnessScore(int width, int height, Integer[][] bitBuffer)
    {
        LinesAnalyzer analyzer = new LinesAnalyzer();
        analyzer.setBitBuffer(width, height, bitBuffer);
        analyzer.analyze();

        int numberOfLines = analyzer.getDetectedLines().size();

        int totalLength = 0;
        int fitnessScore = 0;

        Enumeration<Long> enumKey = analyzer.getDetectedLines().keys();

        while(enumKey.hasMoreElements())
        {
            Long key = enumKey.nextElement();
            int value = analyzer.getDetectedLines().get(key);
            totalLength += value;
        }

        long searchSpace = width * height;
        long markedBits = analyzer.getNumberOfBits();

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
