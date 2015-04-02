package org.noiseseeker;


/**
 * Various helper functions
 */
public class NoiseHelper
{
    private Integer rows[];
    int MAX_CELL_VALUE;
    int BIT_SIZE;

    public NoiseHelper(int bitSize, int maxCellValue)
    {
        this.BIT_SIZE = bitSize;
        this.MAX_CELL_VALUE = maxCellValue;

        this.rows = new Integer[BIT_SIZE];
        for (int iterator = 0; iterator < BIT_SIZE; iterator++) {
            this.rows[iterator] = 0;
        }
    }

    /**
     *
     * @return
     */
    public int getBitSize()
    {
        return this.BIT_SIZE;
    }

    /**
     *
     * @return
     */
    public int getMaxCellValue()
    {
        return this.MAX_CELL_VALUE;
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
    public Integer[][] getCurrentBuffer() {
        Integer[][] arrayTemp = new Integer[16][16];

        for (int y = 0; y < BIT_SIZE; y++)
            for (int x = 0; x < BIT_SIZE; x++) {
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

        // Hack code to get a full-sized bit string (size of BIT_SIZE)
        String s = Integer.toBinaryString(number);

        if ( s.length() != BIT_SIZE)
        {
            int delta = BIT_SIZE - s.length();
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
        for (int y = 0; y < BIT_SIZE; y++)
            for (int x = 0; x < BIT_SIZE; x++) {
                System.out.print(buffer[x][y] + "");
                if ( x == BIT_SIZE - 1)
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
        int cellMaxValue = MAX_CELL_VALUE + 1;
        int cellIndex = BIT_SIZE - 1;

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
        int cells = BIT_SIZE;
        int cellMaxValue = MAX_CELL_VALUE;

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
        int cells = BIT_SIZE;
        int cellMaxValue = MAX_CELL_VALUE;

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
        int cells = BIT_SIZE;
        int cellMaxValue = MAX_CELL_VALUE;

        StringBuilder stringBuilder = new StringBuilder();

        for(int i=0; i<cells; i++)
        {
            stringBuilder.append(" " + values[i]);
        }

        return stringBuilder.toString();
    }
}
