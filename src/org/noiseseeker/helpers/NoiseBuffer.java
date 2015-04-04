package org.noiseseeker.helpers;


public class NoiseBuffer
{
    private Integer rows[];
    private int base;
    private int numberOfUnits;

    public NoiseBuffer(int numberOfUnits, int base)
    {
        this.numberOfUnits = numberOfUnits;
        this.base = base;

        this.rows = new Integer[this.numberOfUnits];

        for (int iterator = 0; iterator < this.numberOfUnits; iterator++)
        {
            this.rows[iterator] = 0;
        }
    }

    /**
     *
     * @return
     */
    public int getNumberOfUnits()
    {
        return this.numberOfUnits;
    }

    /**
     *
     * @return
     */
    public int getBase()
    {
        return this.base;
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
    public Integer[][] getBitBuffer()
    {
        Integer[][] arrayTemp = new Integer[this.numberOfUnits][this.numberOfUnits];

        for (int y = 0; y < numberOfUnits; y++)
            for (int x = 0; x < numberOfUnits; x++)
            {
                arrayTemp[x][y] = getBitAtRowAndCloumn(y, x);
            }

        return arrayTemp;

    }

    /**
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    public int getBitAtRowAndCloumn(int rowIndex, int columnIndex)
    {
        int number = this.rows[rowIndex];

        // Hack code to get a full-sized bit string (size of numberOfUnits)
        String s = Integer.toBinaryString(number);

        if ( s.length() != numberOfUnits)
        {
            int delta = numberOfUnits - s.length();
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
    public static void Print(int numberOfUnits, Integer[][] buffer)
    {
        for (int y = 0; y < numberOfUnits; y++)
            for (int x = 0; x < numberOfUnits; x++)
            {
                System.out.print(buffer[x][y] + "");

                if ( x == numberOfUnits - 1)
                    System.out.println("");
            }

    }
}
