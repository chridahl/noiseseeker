package org.noiseseeker;

import java.util.Enumeration;
import java.util.Hashtable;

public class FitnessCalculator
{
    private Integer[][] buffer;
    private Integer[][] visitedPoints;
    public Hashtable<Long, Integer> registeredLines;
    private int dimensionX;
    private int dimensionY;

    public FitnessCalculator()
    {
        registeredLines = new Hashtable();
    }

    /**
     *
     * @param dimensionX
     * @param dimensionY
     * @param rayBuffer
     */
    public void setBuffer(int dimensionX, int dimensionY, Integer[][] rayBuffer)
    {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.buffer = rayBuffer;
        this.visitedPoints = new Integer[dimensionX][dimensionY];
    }

    /**
     *
     */
    public void scanBuffer()
    {
        long id = 0;
        for (int y = 0; y < this.dimensionX; y++)
            for (int x = 0; x < this.dimensionX; x++)
            {
                if (this.buffer[x][y] == 1)
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

        registeredLines.put(id, length);

        if (getBufferValue(x, y, 1, 0) == 1 && !this.hasVisitedPoint(x + 1, y))
            return scanLine(x + 1, y, id, length);

        if (getBufferValue(x, y, 0, 1) == 1 && !this.hasVisitedPoint(x, y + 1))
            return scanLine(x, y + 1, id, length);

        if (getBufferValue(x, y, -1, 0) == 1 && !this.hasVisitedPoint(x - 1, y))
            return scanLine(x - 1, y, id, length);

        if (getBufferValue(x, y, 1, 1) == 1 && !this.hasVisitedPoint(x + 1, y + 1))
            return scanLine(x + 1, y + 1, id, length);

        if (getBufferValue(x, y, 0, -1) == 1 && !this.hasVisitedPoint(x, y - 1))
            return scanLine(x, y - 1, id, length);

        if (getBufferValue(x, y, -1, -1) == 1 && !this.hasVisitedPoint(x - 1, y - 1))
            return scanLine(x - 1, y - 1, id, length);

        if (getBufferValue(x, y, 1, -1) == 1 && !this.hasVisitedPoint(x + 1, y - 1))
            return scanLine(x + 1, y - 1, id, length);

        if (getBufferValue(x, y, -1, 1) == 1 && !this.hasVisitedPoint(x - 1, y + 1))
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
    private int getBufferValue(int x, int y, int xOffset, int yOffset)
    {

        if ((y + yOffset) >= this.dimensionY)
            return 0;

        if ((y + yOffset) < 0)
            return 0;

        if ((x + xOffset) >= this.dimensionX)
            return 0;

        if ((x + xOffset) < 0)
            return 0;

        return this.buffer[x + xOffset][y + yOffset];

    }


    /**
     *
      * @return
     */
    public long getMarkedBits()
    {
        long counter = 0;

        for (int y = 0; y < this.dimensionX; y++)
            for (int x = 0; x < this.dimensionX; x++)
            {
                if (this.buffer[x][y] == 1)
                {
                    counter ++;
                }
            }

        return counter;
    }


    /**
     *
     * @return
     */
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

        // Try to keep ratio between 40% - 60% of marked bits. Reward buffers in this zone.

        fitnessScore = totalLength - numberOfLines;

        double percentageMarkedBits = (markedBits / searchSpace) * 100;
        if ( percentageMarkedBits >= 40.0 && percentageMarkedBits <= 60.0)
        {
            fitnessScore += fitnessScore * percentageMarkedBits;
        }

        return fitnessScore;
    }

}
