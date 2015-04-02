package org.noiseseeker;

public class Main
{
    public static void main(String[] args)
    {
        // Current leader has fitness 147.0 and  0 0 0 0 5 173 254 191
        // Current leader has fitness 384.0 and  0 0 0 0 5 190 175 207
        // Current leader has fitness 432.0 and  0 0 0 0 5 250 255 255
        // Current leader has fitness 440.0 and  0 0 0 0 5 254 255 255
        // The winner with max fitness of 440.0 is  0 0 0 0 3 250 255 255
        // The winner with max fitness of 47.0 is  0 0 0 0 3 250 255 255

        NoiseHelper noiseHelper = new NoiseHelper(8, 255);
        FitnessCalculator fitnessCalculator = new FitnessCalculator();

        Integer[] values = new Integer[]{0,0,0,0,0,0,0,0};
        String currentMaxFitnessBuffer = "";
        Integer[] currentMaxFitnessValues = new Integer[]{};
        double currentMaxFitnessValue = 0;

        for(long i = 0; i<99999999L; i++)
        {
            noiseHelper.intArrayNext(values);
            noiseHelper.setupCellsFromArray(values);
            Integer[][] buffer = noiseHelper.getCurrentBuffer();

            fitnessCalculator.setBuffer(noiseHelper.getNumberOfCells(), noiseHelper.getNumberOfCells(), buffer);
            fitnessCalculator.scanBuffer();

            double fitnessValue = fitnessCalculator.calculateFitnessScore();

            if ( fitnessValue > currentMaxFitnessValue)
            {
                currentMaxFitnessValue = fitnessValue;
                currentMaxFitnessBuffer = noiseHelper.intArrayGetString(values);
                System.out.println("Current leader has fitness " + currentMaxFitnessValue + " and " + currentMaxFitnessBuffer);

                String pngFilename = String.format("pngs/test-%1s.png", i);
                noiseHelper.writeBlackAndWhiteImageFileFromBuffer(20, 20, buffer, pngFilename);
            }
        }

        System.out.println("The winner with max fitness of " + currentMaxFitnessValue + " is " + currentMaxFitnessBuffer);
    }
}

