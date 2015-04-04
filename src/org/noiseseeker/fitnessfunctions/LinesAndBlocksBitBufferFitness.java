package org.noiseseeker.fitnessfunctions;

import org.noiseseeker.analyzers.BlocksAnalyzer;
import org.noiseseeker.analyzers.LinesAnalyzer;
import org.noiseseeker.fitnesscalculators.BitBufferFitnessCalculator;

import java.util.Enumeration;

/**
 * Created by cdk on 04.04.2015.
 */
public class LinesAndBlocksBitBufferFitness extends BitBufferFitnessCalculator
{
    /**
     * This fitness functions rewards buffers with few long lines. In addition, it rewards buffers where only
     * 40-60% of the buffers is marked.
     * @return
     */
    @Override
    public double calculateFitnessScore(int width, int height, Integer[][] bitBuffer)
    {
        LinesAnalyzer linesAnalyzer = new LinesAnalyzer();
        BlocksAnalyzer blocksAnalyser = new BlocksAnalyzer();

        linesAnalyzer.setBitBuffer(width, height, bitBuffer);
        blocksAnalyser.setBitBuffer(width, height, bitBuffer);

        linesAnalyzer.analyze();
        blocksAnalyser.analyze();

        int numberOfLines = linesAnalyzer.getDetectedLines().size();
        int totalLength = 0;
        int fitnessScore = 0;

        Enumeration<Long> enumLinesKey = linesAnalyzer.getDetectedLines().keys();

        while(enumLinesKey.hasMoreElements())
        {
            Long key = enumLinesKey.nextElement();
            int value = linesAnalyzer.getDetectedLines().get(key);
            totalLength += value;
        }


        int numberOfBlocks = blocksAnalyser.getDetectedBlocks().size();

        long searchSpace = width * height;

        fitnessScore = totalLength - numberOfLines - (numberOfBlocks * 4);


        return fitnessScore;
    }
}
