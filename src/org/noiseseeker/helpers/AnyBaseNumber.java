package org.noiseseeker.helpers;

import java.util.Arrays;

/**
 * Created by cdk on 04.04.2015.
 */
public class AnyBaseNumber
{
    /**
     *
     * @param values
     */
    public static void NextValue(int numberOfUnits, int base, Integer[] values)
    {
        int cellMinValue = 0;
        int cellMaxValue = base;
        int cellIndex = numberOfUnits - 1;

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
    public static boolean IsMaxValue(int numberOfUnits, int base, Integer[] values)
    {
        boolean isMax = true;
        int cells = numberOfUnits;
        int cellMaxValue = base;

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
    public static void Print(int numberOfCells, Integer[] values)
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
    public static String GetAsString(int numberOfCells, Integer[] values)
    {
        int cells = numberOfCells;

        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<cells; i++)
        {
            stringBuilder.append(" " + values[i]);
        }

        return stringBuilder.toString();
    }


    /**
     * This is a really slow and badly implemented method. Can be optimized beyond recognition.
     * @param fromValues Values to start count from.
     * @param toValues Values to count to.
     * @return The distance between values.
     */
    public static long Distance(int numberOfUnits, int base, Integer[] fromValues, Integer[] toValues) {

        long distance = 0;

        Integer[] tmpFromValues = new Integer[numberOfUnits];
        Integer[] tmpToValues = new Integer[numberOfUnits];

        System.arraycopy(fromValues, 0, tmpFromValues, 0, numberOfUnits);
        System.arraycopy(toValues, 0, tmpToValues, 0, numberOfUnits);

        if (Arrays.equals(tmpFromValues, tmpToValues))
        {
            return 0;
        }

        do
        {
            AnyBaseNumber.NextValue(numberOfUnits, base, tmpFromValues);
            distance ++;
        }
        while(!Arrays.equals(tmpFromValues, tmpToValues));

        long absoluteDistance = Math.abs(distance);

        return absoluteDistance;
    }
}
