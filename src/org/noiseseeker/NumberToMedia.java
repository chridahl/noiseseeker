package org.noiseseeker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NumberToMedia
{
    public static void CreatePNG(NoiseHelper noiseHelper, int numberOfCells, int pngWidth, int pngHeight, Integer[][] buffer, String pngFilename)
    {
        BufferedImage bufferedImage = new BufferedImage(numberOfCells*pngWidth,numberOfCells*pngHeight, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = bufferedImage.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, numberOfCells*pngWidth, numberOfCells*pngHeight);
        graphics.setColor(Color.black);

        for (int y = 0; y < numberOfCells; y++)
            for (int x = 0; x < numberOfCells; x++)
            {
                if ( noiseHelper.getBitFromCells(y, x) == 1 )
                {
                    graphics.fillRect(x * pngWidth, y * pngHeight, pngWidth, pngHeight);
                }
            }

        try
        {
            File file = new File(pngFilename);
            ImageIO.write(bufferedImage, "PNG", file);
        }
        catch(IOException ioException)
        {
            // We are not gonna handle this exception.
        }

    }
}
