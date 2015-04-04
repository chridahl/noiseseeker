package org.noiseseeker.analyzers.anynumber;

/**
 * Created by cdk on 04.04.2015.
 */
public class Base256Size8BlockAnalyzer implements IAnyNumberAnalyzer
{
    /**
     * Skip values that contribute to long bit rows (1111 1111 etc.)
     * @param values
     * @return
     */
    public boolean UseNumber(Integer[] values)
    {
        for(int value : values)
            if ( value == 255
                    || value == 254
                    || value == 252
                    || value == 248
                    || value == 240
                    || value == 127
                    || value == 63
                    || value == 31
                    || value == 15
                    || value == 7)
                return false;

        return true;
    }

}
