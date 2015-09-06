// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Trackers.java


public class Trackers
{

    public Trackers()
    {
        x = new int[6700];
        y = new int[6700];
        z = new int[6700];
        xy = new int[6700];
        zy = new int[6700];
        skd = new int[6700];
        dam = new int[6700];
        notwall = new boolean[6700];
        decor = new boolean[6700];
        c = new int[6700][3];
        radx = new int[6700];
        radz = new int[6700];
        rady = new int[6700];
        nt = 0;
        sx = 0;
        sz = 0;
        ncx = 0;
        ncz = 0;
        sect = (int[][][])null;
    }

    public void devidetrackers(int i, int j, int k, int l)
    {
        sect = (int[][][])null;
        sx = i;
        sz = k;
        ncx = j / 3000;
        if(ncx <= 0)
            ncx = 1;
        ncz = l / 3000;
        if(ncz <= 0)
            ncz = 1;
        sect = new int[ncx][ncz][];
        for(int i1 = 0; i1 < ncx; i1++)
        {
            for(int k1 = 0; k1 < ncz; k1++)
            {
                int l1 = sx + i1 * 3000 + 1500;
                int i2 = sz + k1 * 3000 + 1500;
                int ai[] = new int[6700];
                int j2 = 0;
                for(int k2 = 0; k2 < nt; k2++)
                {
                    int j3 = py(l1, x[k2], i2, z[k2]);
                    if(j3 < 0x134fd90 && j3 > 0 && dam[k2] != 167)
                    {
                        ai[j2] = k2;
                        j2++;
                    }
                }

                if(i1 == 0 || k1 == 0 || i1 == ncx - 1 || k1 == ncz - 1)
                {
                    for(int l2 = 0; l2 < nt; l2++)
                        if(dam[l2] == 167)
                        {
                            ai[j2] = l2;
                            j2++;
                        }

                }
                if(j2 == 0)
                {
                    ai[j2] = 0;
                    j2++;
                }
                sect[i1][k1] = new int[j2];
                for(int i3 = 0; i3 < j2; i3++)
                    sect[i1][k1][i3] = ai[i3];

            }

        }

        for(int j1 = 0; j1 < nt; j1++)
            if(dam[j1] == 167)
                dam[j1] = 1;

        ncx--;
        ncz--;
    }

    public int py(int i, int j, int k, int l)
    {
        return (i - j) * (i - j) + (k - l) * (k - l);
    }

    int x[];
    int y[];
    int z[];
    int xy[];
    int zy[];
    int skd[];
    int dam[];
    boolean notwall[];
    boolean decor[];
    int c[][];
    int radx[];
    int radz[];
    int rady[];
    int nt;
    int sx;
    int sz;
    int ncx;
    int ncz;
    int sect[][][];
}
