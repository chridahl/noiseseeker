package org.noiseseeker.tests;

import junit.framework.TestCase;
import org.junit.Test;
import org.noiseseeker.NoiseNumber;

/**
 * Created by cdk on 04.04.2015.
 */
public class NoiseNumberTest extends TestCase {

    public void testIntArrayNext() throws Exception {

    }

    public void testIntArrayIsMax() throws Exception {

    }

    public void testIntArrayPrint() throws Exception {

    }

    @Test
    public void testIntArrayGetString() throws Exception {

    }

    public void testIntArrayDistanceSmallDecimalNumbers() throws Exception {

        long number = NoiseNumber.intArrayDistance(2, 9, new Integer[]{0, 6}, new Integer[]{0,8});
        assertEquals(2L, number);

    }

    @Test
    public void testIntArrayDistanceLargerDecimalNumbers() throws Exception {

        long number = NoiseNumber.intArrayDistance(4, 9, new Integer[]{0, 0, 0, 0}, new Integer[]{1, 0, 0, 0});
        assertEquals(1000L, number);
    }

    @Test
    public void testIntArrayDistanceLargerBase255Numbers() throws Exception {

        long number = NoiseNumber.intArrayDistance(4, 255, new Integer[]{0, 0, 0, 0}, new Integer[]{0, 255, 0, 0});
        assertEquals(16711680L, number);
    }

    @Test
    public void testIntArrayDistanceLargerBase255Numbers2() throws Exception {

        long number = NoiseNumber.intArrayDistance(4, 255, new Integer[]{0, 0, 0, 0}, new Integer[]{255, 255, 255, 0});
        assertEquals(4294967040L, number);
    }

}