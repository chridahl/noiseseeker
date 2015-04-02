package org.noiseseeker;

import java.util.Enumeration;
import java.util.Hashtable;

public class FitnessCalculator
{
    private Integer[][] rayBuffer;
    private Integer[][] visitedPoints;
    public Hashtable<Long, Integer> registeredLines;
    private int dimensionX;
    private int dimensionY;

    public FitnessCalculator()
    {
        registeredLines = new Hashtable();
    }

    public void SetBuffer(int dimensionX, int dimensionY, Integer[][] rayBuffer)
    {
        this.dimensionX = dimensionX;
        this.dimensionY = dimensionY;
        this.rayBuffer = rayBuffer;
        this.visitedPoints = new Integer[dimensionX][dimensionY];
    }

    /**
     *
     */
    public void ScanBuffer()
    {
        long id = 0;
        for (int yy = 0; yy < this.dimensionX; yy++)
            for (int xx = 0; xx < this.dimensionX; xx++) {
                if (this.rayBuffer[xx][yy] == 1) {
                    if (!hasVisitedPoint(xx, yy))
                        ScanLine(xx, yy, id++, 0);
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
    private int ScanLine(int x, int y, long id, int length)
    {
        if (this.hasVisitedPoint(x, y))
        {
            return length;
        }

        this.setVisitedPoint(x, y);

        length = length + 1;

        registeredLines.put(id, length);

        if (getBufferValue(x, y, 1, 0) == 1 && !this.hasVisitedPoint(x + 1, y))
            return ScanLine(x + 1, y, id, length);

        if (getBufferValue(x, y, 0, 1) == 1 && !this.hasVisitedPoint(x, y + 1))
            return ScanLine(x, y + 1, id, length);

        if (getBufferValue(x, y, -1, 0) == 1 && !this.hasVisitedPoint(x - 1, y))
            return ScanLine(x - 1, y, id, length);

        if (getBufferValue(x, y, 1, 1) == 1 && !this.hasVisitedPoint(x + 1, y + 1))
            return ScanLine(x + 1, y + 1, id, length);

        if (getBufferValue(x, y, 0, -1) == 1 && !this.hasVisitedPoint(x, y - 1))
            return ScanLine(x, y - 1, id, length);

        if (getBufferValue(x, y, -1, -1) == 1 && !this.hasVisitedPoint(x - 1, y - 1))
            return ScanLine(x - 1, y - 1, id, length);

        if (getBufferValue(x, y, 1, -1) == 1 && !this.hasVisitedPoint(x + 1, y - 1))
            return ScanLine(x + 1, y - 1, id, length);

        if (getBufferValue(x, y, -1, 1) == 1 && !this.hasVisitedPoint(x - 1, y + 1))
            return ScanLine(x - 1, y + 1, id, length);

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

        return this.rayBuffer[x + xOffset][y + yOffset];

    }

    /**
     *
     * @return
     */
    public double CalculateFitnessScore()
    {
        int numberOfLines = this.registeredLines.size();

        int totalLength = 0;
        int fitnessScore = 0;

        Enumeration<Long> enumKey = this.registeredLines.keys();

        while(enumKey.hasMoreElements())
        {
            Long key = enumKey.nextElement();
            int val = this.registeredLines.get(key);
            totalLength += val;
        }

        fitnessScore = numberOfLines * totalLength;

        return fitnessScore;
    }

}
