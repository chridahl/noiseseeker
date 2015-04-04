package org.noiseseeker.analyzers;

import java.util.Hashtable;

/**
 * Should analyze a bitbuffer and find a) the number of blocks (four or more grouped bits) and their [w,h] size.
 * Created by cdk on 04.04.2015.
 */
public class BlocksAnalyzer
{
        protected int width;
        protected int height;
        protected Integer[][] bitBuffer;
        protected Integer[][] visitedPoints;
        public Hashtable<Long, Integer> detectedBlocks;

        public BlocksAnalyzer()
        {
                detectedBlocks = new Hashtable();
        }

        /**
         *
         * @param width
         * @param height
         * @param bitBuffer
         */
        public void setBitBuffer(int width, int height, Integer[][] bitBuffer)
        {
                this.width = width;
                this.height = height;
                this.bitBuffer = bitBuffer;
                this.visitedPoints = new Integer[width][height];

        }

        /**
         *
         * @return
         */
        public Hashtable<Long, Integer> getDetectedBlocks() {

                return this.detectedBlocks;
        }

        /**
         *
         */
        public void analyze()
        {
                long id = 0;
                for (int y = 0; y < this.width; y++)
                        for (int x = 0; x < this.width; x++)
                        {
                                if (this.bitBuffer[x][y] == 1)
                                {
                                        if (!hasVisitedPoint(x, y))
                                                seekBlock(x, y, id++);
                                }
                        }
        }

        /**
         *
         * @param x
         * @param y
         * @return
         */
        private boolean hasVisitedPoint(int x, int y)
        {
                try
                {
                        if (this.visitedPoints[x][y] == 1)
                                return true;
                        else
                                return false;
                }
                catch (Exception e)
                {
                        return false;
                }
        }

        /**
         *
         * @param x
         * @param y
         */
        private void setVisitedPoint(int x, int y)
        {
                this.visitedPoints[x][y] = 1;
        }

        /**
         *
         * @param x
         * @param y
         * @param id
         * @return
         */
        private void seekBlock(int x, int y, long id)
        {
                this.setVisitedPoint(x, y);

                int size = 0;

                if (getBitBufferValue(x, y, 1, 0) == 1 && !this.hasVisitedPoint(x + 1, y))
                if (getBitBufferValue(x, y, 0, 1) == 1 && !this.hasVisitedPoint(x, y + 1))
                if (getBitBufferValue(x, y, 1, 1) == 1 && !this.hasVisitedPoint(x + 1, y + 1))
                {
                        this.setVisitedPoint(x + 1, y);
                        this.setVisitedPoint(x    , y + 1);
                        this.setVisitedPoint(x + 1, y + 1);
                        size = 4;
                }

                if ( size > 0 )
                        this.detectedBlocks.put(id, size);
        }

        /**
         *
         * @param x
         * @param y
         * @param xOffset
         * @param yOffset
         * @return
         */
        private int getBitBufferValue(int x, int y, int xOffset, int yOffset)
        {

                if ((y + yOffset) >= this.height)
                        return 0;

                if ((y + yOffset) < 0)
                        return 0;

                if ((x + xOffset) >= this.width)
                        return 0;

                if ((x + xOffset) < 0)
                        return 0;

                return this.bitBuffer[x + xOffset][y + yOffset];

        }


        /**
         *
         * @return
         */
        public long getNumberOfBits()
        {
                long counter = 0;

                for (int y = 0; y < this.width; y++)
                        for (int x = 0; x < this.width; x++)
                        {
                                if (this.bitBuffer[x][y] == 1)
                                {
                                        counter ++;
                                }
                        }

                return counter;
        }


}
