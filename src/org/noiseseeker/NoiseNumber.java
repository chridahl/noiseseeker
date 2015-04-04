package org.noiseseeker;

import java.util.Arrays;

/**
 * Created by cdk on 04.04.2015.
 */
public class NoiseNumber
{
    /**
     *
     * @param values
     */
    public static void intArrayNext(int numberOfCells, int maxCellValue, Integer[] values)
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
                if ( j-1 > 0) {
                    values[j - 1]++;
                } else {
                    values[0]++;
                }
            }
        }
    }

    /**
     *
     * @param values
     * @return
     */
    public static boolean intArrayIsMax(int numberOfCells, int maxCellValue, Integer[] values)
    {
        boolean isMax = true;
        int cells = numberOfCells;
        int cellMaxValue = maxCellValue;

        for(int i=0; i<cells; i++)
            if ( values[i] <= cellMaxValue)
                isMax = false;

        return isMax;
    }

    /**
     *
     * @param values
     * @return
     */
    public static void intArrayPrint(int numberOfCells, Integer[] values)
    {
        int cells = numberOfCells;

        for(int i=0; i<cells; i++)
        {
            System.out.print(" " + values[i]);
        }

        System.out.println();
    }

    /**
     *
     * @param values
     * @return
     */
    public static String intArrayGetString(int numberOfCells, int maxCellValue, Integer[] values)
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
     * @param fromValues Values to start count from.
     * @param toValues Values to count to.
     * @return The distance between values.
     */
    public static long intArrayDistance(int numberOfCells, int maxCellValue, Integer[] fromValues, Integer[] toValues) {

        long distance = 0;

        Integer[] tmpFromValues = new Integer[numberOfCells];
        Integer[] tmpToValues = new Integer[numberOfCells];

        System.arraycopy(fromValues, 0, tmpFromValues, 0, numberOfCells);
        System.arraycopy(toValues, 0, tmpToValues, 0, numberOfCells);

        if (Arrays.equals(tmpFromValues, tmpToValues))
        {
            return 0;
        }

        do
        {
            NoiseNumber.intArrayNext(numberOfCells, maxCellValue, tmpFromValues);
            distance ++;
        }
        while(!Arrays.equals(tmpFromValues, tmpToValues));

        long absoluteDistance = Math.abs(distance);

//        if (absoluteDistance == Long.MAX_VALUE)
//            throw new Exception("Distance too great for type long");

        return absoluteDistance;
    }
}
