package org.noiseseeker;


import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Various helper functions
 */
public class NoiseHelper
{
    private Integer rows[];
    private int maxCellValue;
    private int numberOfCells;

    public NoiseHelper(int numberOfCells, int maxCellValue)
    {
        this.numberOfCells = numberOfCells;
        this.maxCellValue = maxCellValue;

        this.rows = new Integer[this.numberOfCells];

        for (int iterator = 0; iterator < this.numberOfCells; iterator++)
        {
            this.rows[iterator] = 0;
        }
    }

    /**
     *
     * @return
     */
    public int getNumberOfCells()
    {
        return this.numberOfCells;
    }

    /**
     *
     * @return
     */
    public int getMaxCellValue()
    {
        return this.maxCellValue;
    }

    /**
     *
     * @param values
     */
    public void setupCellsFromArray(Integer[] values)
    {
        this.rows = values;
    }


    /**
     *
     * @return
     */
    public Integer[][] getCurrentBuffer()
    {
        Integer[][] arrayTemp = new Integer[this.numberOfCells][this.numberOfCells];

        for (int y = 0; y < numberOfCells; y++)
            for (int x = 0; x < numberOfCells; x++)
            {
                arrayTemp[x][y] = getBitFromCells(y, x);
            }

        return arrayTemp;

    }

    /**
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    private int getBitFromCells(int rowIndex, int columnIndex)
    {
        int number = this.rows[rowIndex];

        // Hack code to get a full-sized bit string (size of numberOfCells)
        String s = Integer.toBinaryString(number);

        if ( s.length() != numberOfCells)
        {
            int delta = numberOfCells - s.length();
            String x = "";
            for(int m=0; m<delta; m++)
            {
                x += "0";
            }
            s = x + s;
        }
        return Integer.parseInt("" + s.charAt(columnIndex));
    }

    /**
     *
     * @param buffer
     */
    public void printBuffer(Integer[][] buffer)
    {
        for (int y = 0; y < numberOfCells; y++)
            for (int x = 0; x < numberOfCells; x++)
            {
                System.out.print(buffer[x][y] + "");

                if ( x == numberOfCells - 1)
                    System.out.println("");
            }

    }

    /**
     *
     * @param values
     */
    public void intArrayNext(Integer[] values)
    {
        int cellMinValue = 0;
        int cellMaxValue = maxCellValue + 1;
        int cellIndex = numberOfCells - 1;

        values[cellIndex] = values[cellIndex] + 1;

        for(int j=cellIndex; j>=0; j--)
        {
            if ( values[j] >= cellMaxValue )
            {
                values[j] = cellMinValue;
                values[j-1] ++;
            }
        }
    }

    /**
     *
     * @param values
     * @return
     */
    public boolean intArrayIsMax(Integer[] values)
    {
        boolean isMax = true;
        int cells = numberOfCells;
        int cellMaxValue = maxCellValue;

        for(int i=0; i<cells; i++)
            if ( values[i] >= cellMaxValue)
                isMax = false;

        return isMax;
    }

    /**
     *
     * @param values
     * @return
     */
    public void intArrayPrint(Integer[] values)
    {
        int cells = numberOfCells;

        for(int i=0; i<cells; i++)
        {
            System.out.print(" " + values[i]);
        }
    }

    /**
     *
     * @param values
     * @return
     */
    public String intArrayGetString(Integer[] values)
    {
        int cells = numberOfCells;
        int cellMaxValue = maxCellValue;

        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<cells; i++)
        {
            stringBuilder.append(" " + values[i]);
        }

        return stringBuilder.toString();
    }


    /**
     *
     * @param noiseValues
     * @param filename
     */
    public void writeBlackAndWhiteImageFileFromNoiseValues(Integer[] noiseValues, String filename)
    {
        throw new NotImplementedException();
    }

    /**
     *
     * @param buffer
     * @param filename
     */
    public void writeBlackAndWhiteImageFileFromBuffer(int xScale, int yScale, Integer[][] buffer, String filename)
    {
        BufferedImage bufferedImage = new BufferedImage(this.getNumberOfCells()*xScale, this.getNumberOfCells()*yScale, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();

        for (int y = 0; y < numberOfCells; y++)
            for (int x = 0; x < numberOfCells; x++)
            {
                if ( getBitFromCells(y, x) == 1 )
                {
                    graphics.fillRect(x * xScale, y * yScale, xScale, yScale);
                }
            }

        try
        {
            File file = new File(filename);
            ImageIO.write(bufferedImage, "PNG", file);
        }
        catch(IOException ioException)
        {

        }


    }

}
