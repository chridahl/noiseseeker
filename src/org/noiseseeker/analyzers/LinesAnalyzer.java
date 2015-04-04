package org.noiseseeker.analyzers;

import org.noiseseeker.helpers.NoiseBuffer;

import java.util.Hashtable;

/**
 * Created by cdk on 04.04.2015.
 */
public class LinesAnalyzer
{
    protected int width;
    protected int height;
    protected Integer[][] bitBuffer;
    protected Integer[][] visitedPoints;
    public Hashtable<Long, Integer> detectedLines;

    public LinesAnalyzer()
    {
        detectedLines = new Hashtable();
    }

    /**
     *
     * @param width
     * @param height
     * @param bitBuffer
     */
    public void setBitBuffer(int width, int height, Integer[][] bitBuffer)
    {
        this.width = width;
        this.height = height;
        this.bitBuffer = bitBuffer;
        this.visitedPoints = new Integer[width][height];

    }

    /**
     *
     * @return
     */
    public Hashtable<Long, Integer> getDetectedLines() {

        return this.detectedLines;
    }

    /**
     *
     */
    public void analyze()
    {
        long id = 0;
        for (int y = 0; y < this.width; y++)
            for (int x = 0; x < this.width; x++)
            {
                if (this.bitBuffer[x][y] == 1)
                {
                    if (!hasVisitedPoint(x, y))
                        scanLine(x, y, id++, 0);
                }
            }
    }

    /**
     *
     * @param x
     * @param y
     * @return
     */
    private boolean hasVisitedPoint(int x, int y)
    {
        try
        {
            if (this.visitedPoints[x][y] == 1)
                return true;
            else
                return false;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    /**
     *
     * @param x
     * @param y
     */
    private void setVisitedPoint(int x, int y)
    {
        this.visitedPoints[x][y] = 1;
    }

    /**
     *
     * @param x
     * @param y
     * @param id
     * @param length
     * @return
     */
    private int scanLine(int x, int y, long id, int length)
    {
        if (this.hasVisitedPoint(x, y))
        {
            return length;
        }

        this.setVisitedPoint(x, y);

        length = length + 1;

        this.detectedLines.put(id, length);

        if (getBitBufferValue(x, y, 1, 0) == 1 && !this.hasVisitedPoint(x + 1, y))
            return scanLine(x + 1, y, id, length);

        if (getBitBufferValue(x, y, 0, 1) == 1 && !this.hasVisitedPoint(x, y + 1))
            return scanLine(x, y + 1, id, length);

        if (getBitBufferValue(x, y, -1, 0) == 1 && !this.hasVisitedPoint(x - 1, y))
            return scanLine(x - 1, y, id, length);

        if (getBitBufferValue(x, y, 1, 1) == 1 && !this.hasVisitedPoint(x + 1, y + 1))
            return scanLine(x + 1, y + 1, id, length);

        if (getBitBufferValue(x, y, 0, -1) == 1 && !this.hasVisitedPoint(x, y - 1))
            return scanLine(x, y - 1, id, length);

        if (getBitBufferValue(x, y, -1, -1) == 1 && !this.hasVisitedPoint(x - 1, y - 1))
            return scanLine(x - 1, y - 1, id, length);

        if (getBitBufferValue(x, y, 1, -1) == 1 && !this.hasVisitedPoint(x + 1, y - 1))
            return scanLine(x + 1, y - 1, id, length);

        if (getBitBufferValue(x, y, -1, 1) == 1 && !this.hasVisitedPoint(x - 1, y + 1))
            return scanLine(x - 1, y + 1, id, length);

        return length;
    }

    /**
     *
     * @param x
     * @param y
     * @param xOffset
     * @param yOffset
     * @return
     */
    private int getBitBufferValue(int x, int y, int xOffset, int yOffset)
    {

        if ((y + yOffset) >= this.height)
            return 0;

        if ((y + yOffset) < 0)
            return 0;

        if ((x + xOffset) >= this.width)
            return 0;

        if ((x + xOffset) < 0)
            return 0;

        return this.bitBuffer[x + xOffset][y + yOffset];

    }


    /**
     *
     * @return
     */
    public long getNumberOfBits()
    {
        long counter = 0;

        for (int y = 0; y < this.width; y++)
            for (int x = 0; x < this.width; x++)
            {
                if (this.bitBuffer[x][y] == 1)
                {
                    counter ++;
                }
            }

        return counter;
    }
}
