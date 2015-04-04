package org.noiseseeker.helpers;


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
    public int getBitFromCells(int rowIndex, int columnIndex)
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






}
