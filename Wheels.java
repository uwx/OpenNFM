// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Wheels.java


public class Wheels
{

    public Wheels()
    {
        ground = 0;
        mast = 0;
        sparkat = 0;
        size = 2.0F;
        depth = 3F;
        sparkat = 0;
        ground = 0;
    }

    public void setrims(int i, int i_0, int i_1, int i_2, int i_3)
    {
        rc[0] = i;
        rc[1] = i_0;
        rc[2] = i_1;
        size = (float)i_2 / 10F;
        if(size < 0.0F)
            size = 0.0F;
        depth = (float)i_3 / 10F;
        if(depth / size > 41F)
            depth = size * 41F;
        if(depth / size < -25F)
            depth = -(size * 25F);
    }

    public void make(Medium medium, Trackers trackers, Plane planes[], int i, int i_4, int i_5, int i_6, 
            int i_7, int i_8, int i_9, int i_10)
    {
        int is[] = new int[20];
        int is_11[] = new int[20];
        int is_12[] = new int[20];
        int is_13[] = {
            45, 45, 45
        };
        int i_14 = 0;
        float f = (float)i_8 / 10F;
        float f_15 = (float)i_9 / 10F;
        if(i_7 == 11)
            i_14 = (int)((float)i_4 + 4F * f);
        sparkat = (int)(f_15 * 24F);
        ground = (int)((float)i_5 + 13F * f_15);
        int i_16 = -1;
        if(i_4 < 0)
            i_16 = 1;
        for(int i_17 = 0; i_17 < 20; i_17++)
            is[i_17] = (int)((float)i_4 - 4F * f);

        is_11[0] = (int)((float)i_5 - 9.1923F * f_15);
        is_12[0] = (int)((float)i_6 + 9.1923F * f_15);
        is_11[1] = (int)((float)i_5 - 12.557F * f_15);
        is_12[1] = (int)((float)i_6 + 3.3646F * f_15);
        is_11[2] = (int)((float)i_5 - 12.557F * f_15);
        is_12[2] = (int)((float)i_6 - 3.3646F * f_15);
        is_11[3] = (int)((float)i_5 - 9.1923F * f_15);
        is_12[3] = (int)((float)i_6 - 9.1923F * f_15);
        is_11[4] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[4] = (int)((float)i_6 - 12.557F * f_15);
        is_11[5] = (int)((float)i_5 + 3.3646F * f_15);
        is_12[5] = (int)((float)i_6 - 12.557F * f_15);
        is_11[6] = (int)((float)i_5 + 9.1923F * f_15);
        is_12[6] = (int)((float)i_6 - 9.1923F * f_15);
        is_11[7] = (int)((float)i_5 + 12.557F * f_15);
        is_12[7] = (int)((float)i_6 - 3.3646F * f_15);
        is_11[8] = (int)((float)i_5 + 12.557F * f_15);
        is_12[8] = (int)((float)i_6 + 3.3646F * f_15);
        is_11[9] = (int)((float)i_5 + 9.1923F * f_15);
        is_12[9] = (int)((float)i_6 + 9.1923F * f_15);
        is_11[10] = (int)((float)i_5 + 3.3646F * f_15);
        is_12[10] = (int)((float)i_6 + 12.557F * f_15);
        is_11[11] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[11] = (int)((float)i_6 + 12.557F * f_15);
        is_11[12] = i_5;
        is_12[12] = (int)((float)i_6 + 10F * size);
        is_11[13] = (int)((double)i_5 + 8.6600000000000001D * (double)size);
        is_12[13] = (int)((float)i_6 + 5F * size);
        is_11[14] = (int)((double)i_5 + 8.6600000000000001D * (double)size);
        is_12[14] = (int)((float)i_6 - 5F * size);
        is_11[15] = i_5;
        is_12[15] = (int)((float)i_6 - 10F * size);
        is_11[16] = (int)((double)i_5 - 8.6600000000000001D * (double)size);
        is_12[16] = (int)((float)i_6 - 5F * size);
        is_11[17] = (int)((double)i_5 - 8.6600000000000001D * (double)size);
        is_12[17] = (int)((float)i_6 + 5F * size);
        is_11[18] = i_5;
        is_12[18] = (int)((float)i_6 + 10F * size);
        is_11[19] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[19] = (int)((float)i_6 + 12.557F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 20, is_13, 0, i_10, 0, i_14, i_5, i_6, 7, 0, false, 0, false);
        planes[i].master = 1;
        i++;
        is[2] = (int)((float)i_4 - depth * f);
        is_11[2] = i_5;
        is_12[2] = i_6;
        int i_18 = (int)((float)i_10 - (depth / size) * 4F);
        if(i_18 < -16)
            i_18 = -16;
        is_11[0] = i_5;
        is_12[0] = (int)((float)i_6 + 10F * size);
        is_11[1] = (int)((double)i_5 + 8.6600000000000001D * (double)size);
        is_12[1] = (int)((float)i_6 + 5F * size);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 3, rc, 0, i_18, 0, i_14, i_5, i_6, 7, 0, false, 0, false);
        if(depth / size < 7F)
            planes[i].master = 2;
        i++;
        is_11[0] = (int)((double)i_5 + 8.6600000000000001D * (double)size);
        is_12[0] = (int)((float)i_6 + 5F * size);
        is_11[1] = (int)((double)i_5 + 8.6600000000000001D * (double)size);
        is_12[1] = (int)((float)i_6 - 5F * size);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 3, rc, 0, i_18, 0, i_14, i_5, i_6, 7, 0, false, 0, false);
        if(depth / size < 7F)
            planes[i].master = 2;
        i++;
        is_11[0] = (int)((double)i_5 + 8.6600000000000001D * (double)size);
        is_12[0] = (int)((float)i_6 - 5F * size);
        is_11[1] = i_5;
        is_12[1] = (int)((float)i_6 - 10F * size);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 3, rc, 0, i_18, 0, i_14, i_5, i_6, 7, 0, false, 0, false);
        if(depth / size < 7F)
            planes[i].master = 2;
        i++;
        is_11[0] = i_5;
        is_12[0] = (int)((float)i_6 - 10F * size);
        is_11[1] = (int)((double)i_5 - 8.6600000000000001D * (double)size);
        is_12[1] = (int)((float)i_6 - 5F * size);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 3, rc, 0, i_18, 0, i_14, i_5, i_6, 7, 0, false, 0, false);
        if(depth / size < 7F)
            planes[i].master = 2;
        i++;
        is_11[0] = (int)((double)i_5 - 8.6600000000000001D * (double)size);
        is_12[0] = (int)((float)i_6 - 5F * size);
        is_11[1] = (int)((double)i_5 - 8.6600000000000001D * (double)size);
        is_12[1] = (int)((float)i_6 + 5F * size);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 3, rc, 0, i_18, 0, i_14, i_5, i_6, 7, 0, false, 0, false);
        if(depth / size < 7F)
            planes[i].master = 2;
        i++;
        is_11[0] = (int)((double)i_5 - 8.6600000000000001D * (double)size);
        is_12[0] = (int)((float)i_6 + 5F * size);
        is_11[1] = i_5;
        is_12[1] = (int)((float)i_6 + 10F * size);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 3, rc, 0, i_18, 0, i_14, i_5, i_6, 7, 0, false, 0, false);
        if(depth / size < 7F)
            planes[i].master = 2;
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 - 12.557F * f_15);
        is_12[0] = (int)((float)i_6 + 3.3646F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 - 12.557F * f_15);
        is_12[1] = (int)((float)i_6 - 3.3646F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 - 12.557F * f_15);
        is_12[2] = (int)((float)i_6 - 3.3646F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 - 12.557F * f_15);
        is_12[3] = (int)((float)i_6 + 3.3646F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, -1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 - 9.1923F * f_15);
        is_12[0] = (int)((float)i_6 - 9.1923F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 - 12.557F * f_15);
        is_12[1] = (int)((float)i_6 - 3.3646F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 - 12.557F * f_15);
        is_12[2] = (int)((float)i_6 - 3.3646F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 - 9.1923F * f_15);
        is_12[3] = (int)((float)i_6 - 9.1923F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, 1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 - 9.1923F * f_15);
        is_12[0] = (int)((float)i_6 - 9.1923F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[1] = (int)((float)i_6 - 12.557F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[2] = (int)((float)i_6 - 12.557F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 - 9.1923F * f_15);
        is_12[3] = (int)((float)i_6 - 9.1923F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, 1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[0] = (int)((float)i_6 - 12.557F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 + 3.3646F * f_15);
        is_12[1] = (int)((float)i_6 - 12.557F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 + 3.3646F * f_15);
        is_12[2] = (int)((float)i_6 - 12.557F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[3] = (int)((float)i_6 - 12.557F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, -1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 + 9.1923F * f_15);
        is_12[0] = (int)((float)i_6 - 9.1923F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 + 3.3646F * f_15);
        is_12[1] = (int)((float)i_6 - 12.557F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 + 3.3646F * f_15);
        is_12[2] = (int)((float)i_6 - 12.557F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 + 9.1923F * f_15);
        is_12[3] = (int)((float)i_6 - 9.1923F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, 1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 + 9.1923F * f_15);
        is_12[0] = (int)((float)i_6 - 9.1923F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 + 12.557F * f_15);
        is_12[1] = (int)((float)i_6 - 3.3646F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 + 12.557F * f_15);
        is_12[2] = (int)((float)i_6 - 3.3646F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 + 9.1923F * f_15);
        is_12[3] = (int)((float)i_6 - 9.1923F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, 1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 + 12.557F * f_15);
        is_12[0] = (int)((float)i_6 - 3.3646F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 + 12.557F * f_15);
        is_12[1] = (int)((float)i_6 + 3.3646F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 + 12.557F * f_15);
        is_12[2] = (int)((float)i_6 + 3.3646F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 + 12.557F * f_15);
        is_12[3] = (int)((float)i_6 - 3.3646F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, -1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 + 9.1923F * f_15);
        is_12[0] = (int)((float)i_6 + 9.1923F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 + 12.557F * f_15);
        is_12[1] = (int)((float)i_6 + 3.3646F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 + 12.557F * f_15);
        is_12[2] = (int)((float)i_6 + 3.3646F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 + 9.1923F * f_15);
        is_12[3] = (int)((float)i_6 + 9.1923F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, 1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 + 9.1923F * f_15);
        is_12[0] = (int)((float)i_6 + 9.1923F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 + 3.3646F * f_15);
        is_12[1] = (int)((float)i_6 + 12.557F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 + 3.3646F * f_15);
        is_12[2] = (int)((float)i_6 + 12.557F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 + 9.1923F * f_15);
        is_12[3] = (int)((float)i_6 + 9.1923F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, 1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 + 3.3646F * f_15);
        is_12[0] = (int)((float)i_6 + 12.557F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[1] = (int)((float)i_6 + 12.557F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[2] = (int)((float)i_6 + 12.557F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 + 3.3646F * f_15);
        is_12[3] = (int)((float)i_6 + 12.557F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, -1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 - 9.1923F * f_15);
        is_12[0] = (int)((float)i_6 + 9.1923F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[1] = (int)((float)i_6 + 12.557F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 - 3.3646F * f_15);
        is_12[2] = (int)((float)i_6 + 12.557F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 - 9.1923F * f_15);
        is_12[3] = (int)((float)i_6 + 9.1923F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, 1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
        is[0] = (int)((float)i_4 - 4F * f);
        is_11[0] = (int)((float)i_5 - 9.1923F * f_15);
        is_12[0] = (int)((float)i_6 + 9.1923F * f_15);
        is[1] = (int)((float)i_4 - 4F * f);
        is_11[1] = (int)((float)i_5 - 12.557F * f_15);
        is_12[1] = (int)((float)i_6 + 3.3646F * f_15);
        is[2] = (int)((float)i_4 + 4F * f);
        is_11[2] = (int)((float)i_5 - 12.557F * f_15);
        is_12[2] = (int)((float)i_6 + 3.3646F * f_15);
        is[3] = (int)((float)i_4 + 4F * f);
        is_11[3] = (int)((float)i_5 - 9.1923F * f_15);
        is_12[3] = (int)((float)i_6 + 9.1923F * f_15);
        planes[i] = new Plane(medium, trackers, is, is_12, is_11, 4, is_13, 0, i_10, 1 * i_16, i_14, i_5, i_6, 7, 0, false, 0, true);
        i++;
    }

    int ground;
    int mast;
    int sparkat;
    int rc[] = {
        120, 120, 120
    };
    float size;
    float depth;
}
