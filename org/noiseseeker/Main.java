package org.noiseseeker;

public class Main
{
    public static void main(String[] args)
    {
        NoiseHelper noiseHelper = new NoiseHelper(8, 255);
        FitnessCalculator fitnessCalculator = new FitnessCalculator();
        Integer[] values = new Integer[]{0,0,0,0,5,173,254,191};
        // Current leader has fitness 147.0 and  0 0 0 0 5 173 254 191
        // Current leader has fitness 384.0 and  0 0 0 0 5 190 175 207
        // Current leader has fitness 432.0 and  0 0 0 0 5 250 255 255
        // Current leader has fitness 440.0 and  0 0 0 0 5 254 255 255

        String currentMaxFitnessBuffer = "";
        Integer[] currentMaxFitnessValues = new Integer[]{};
        double currentMaxFitnessValue = 0;

        for(long i = 0; i<99999999L; i++)
        {
            noiseHelper.intArrayNext(values);
            noiseHelper.setupCellsFromArray(values);
            Integer[][] buffer = noiseHelper.getCurrentBuffer();

            fitnessCalculator.SetBuffer(noiseHelper.getNumberOfCells(), noiseHelper.getNumberOfCells(), buffer);
            fitnessCalculator.ScanBuffer();

            double fitnessValue = fitnessCalculator.CalculateFitnessScore();

            if ( fitnessValue > currentMaxFitnessValue)
            {
                currentMaxFitnessValue = fitnessValue;
                currentMaxFitnessBuffer = noiseHelper.intArrayGetString(values);
                System.out.println("Current leader has fitness " + currentMaxFitnessValue + " and " + currentMaxFitnessBuffer);
            }
        }

        System.out.println("The winner with max fitness of " + currentMaxFitnessValue + " is " + currentMaxFitnessBuffer);
    }
}

