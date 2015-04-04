package org.noiseseeker.tests;

import junit.framework.TestCase;
import org.junit.Test;
import org.noiseseeker.helpers.AnyBaseNumber;

/**
 * Created by cdk on 04.04.2015.
 */
public class AnyBaseNumberTest extends TestCase {

    @Test
    public void testDistanceSmallDecimalNumbers() throws Exception {

        long number = AnyBaseNumber.Distance(2, 9, new Integer[]{0, 6}, new Integer[]{0, 8});
        assertEquals(2L, number);

    }

    @Test
    public void testDistanceLargerDecimalNumbers() throws Exception {

        long number = AnyBaseNumber.Distance(4, 9, new Integer[]{0, 0, 0, 0}, new Integer[]{1, 0, 0, 0});
        assertEquals(1000L, number);
    }

    @Test
    public void testDistanceLargerBase255Numbers() throws Exception {

        long number = AnyBaseNumber.Distance(4, 255, new Integer[]{0, 0, 0, 0}, new Integer[]{0, 255, 0, 0});
        assertEquals(16711680L, number);
    }

      // Takes too long
//    public void testDistanceLargerBase255Numbers2() throws Exception {
//
//        long number = AnyBaseNumber.Distance(4, 255, new Integer[]{0, 0, 0, 0}, new Integer[]{255, 255, 255, 0});
//        assertEquals(4294967040L, number);
//    }

}