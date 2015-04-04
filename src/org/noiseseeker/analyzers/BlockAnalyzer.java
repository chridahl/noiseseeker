package org.noiseseeker.analyzers;

import java.util.Hashtable;

/**
 * Should analyze a bitbuffer and find a) the number of blocks (four or more grouped bits) and their [w,h] size.
 * Created by cdk on 04.04.2015.
 */
public class BlockAnalyzer
{
        protected int width;
        protected int height;
        protected Integer[][] bitBuffer;
        protected Integer[][] visitedPoints;

        public BlockAnalyzer()
        {
        }

}
