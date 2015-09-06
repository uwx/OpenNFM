// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Medium.java

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Medium
{

    public Medium()
    {
        focus_point = 400;
        ground = 250;
        skyline = -300;
        fogd = 7;
        mgen = (int)(Math.random() * 100000D);
        loadnew = false;
        lightson = false;
        darksky = false;
        lightn = -1;
        lilo = 217;
        lton = false;
        noelec = 0;
        trk = 0;
        crs = false;
        cx = 400;
        cy = 225;
        cz = 50;
        xz = 0;
        zy = 0;
        x = 0;
        y = 0;
        z = 0;
        iw = 0;
        ih = 0;
        w = 800;
        h = 450;
        nsp = 0;
        spx = new int[7];
        spz = new int[7];
        sprad = new int[7];
        td = false;
        bcxz = 0;
        bt = false;
        vxz = 180;
        adv = 500;
        vert = false;
        tcos = new float[360];
        tsin = new float[360];
        lastmaf = 0;
        checkpoint = -1;
        lastcheck = false;
        elecr = 0.0F;
        cpflik = false;
        nochekflk = false;
        cntrn = 0;
        trn = 0;
        hit = 45000;
        ptr = 0;
        ptcnt = -10;
        nrnd = 0;
        trx = 0L;
        trz = 0L;
        atrx = 0L;
        atrz = 0L;
        fallen = 0;
        fo = 1.0F;
        gofo = (float)(0.33000001311302185D + Math.random() * 1.3400000000000001D);
        fvect = 200;
        ogpx = (int[][])null;
        ogpz = (int[][])null;
        pvr = (float[][])null;
        cgpx = null;
        cgpz = null;
        pmx = null;
        pcv = null;
        sgpx = 0;
        sgpz = 0;
        nrw = 0;
        ncl = 0;
        noc = 0;
        clx = null;
        clz = null;
        cmx = null;
        clax = (int[][][])null;
        clay = (int[][][])null;
        claz = (int[][][])null;
        clc = (int[][][][])null;
        nmt = 0;
        mrd = null;
        nmv = null;
        mtx = (int[][])null;
        mty = (int[][])null;
        mtz = (int[][])null;
        mtc = (int[][][])null;
        nst = 0;
        stx = null;
        stz = null;
        stc = (int[][][])null;
        bst = null;
        twn = null;
        resdown = 0;
        rescnt = 5;
        madfade = 0;
        fadset = false;
        for(int i = 0; i < 360; i++)
            tcos[i] = (float)Math.cos((double)i * 0.017453292519943295D);

        for(int i = 0; i < 360; i++)
            tsin[i] = (float)Math.sin((double)i * 0.017453292519943295D);

    }

    public float random()
    {
        if(cntrn == 0)
        {
            for(int i = 0; i < 3; i++)
            {
                rand[i] = (int)(10D * Math.random());
                if(Math.random() > Math.random())
                    diup[i] = false;
                else
                    diup[i] = true;
            }

            cntrn = 20;
        } else
        {
            cntrn--;
        }
        for(int i = 0; i < 3; i++)
        {
            if(diup[i])
            {
                rand[i]++;
                if(rand[i] == 10)
                    rand[i] = 0;
                continue;
            }
            rand[i]--;
            if(rand[i] == -1)
                rand[i] = 9;
        }

        trn++;
        if(trn == 3)
            trn = 0;
        return (float)rand[trn] / 10F;
    }

    public void watch(ContO conto, int i)
    {
        if(td)
        {
            y = (int)((float)(conto.y - 300) - 1100F * random());
            x = conto.x + (int)((float)((conto.x + 400) - conto.x) * cos(i) - (float)((conto.z + 5000) - conto.z) * sin(i));
            z = conto.z + (int)((float)((conto.x + 400) - conto.x) * sin(i) + (float)((conto.z + 5000) - conto.z) * cos(i));
            td = false;
        }
        int i_0 = 0;
        if(conto.x - x - cx > 0)
            i_0 = 180;
        int i_1 = -(int)((double)(90 + i_0) + Math.atan((double)(conto.z - z) / (double)(conto.x - x - cx)) / 0.017453292519943295D);
        i_0 = 0;
        if(conto.y - y - cy < 0)
            i_0 = -180;
        int i_2 = (int)Math.sqrt((conto.z - z) * (conto.z - z) + (conto.x - x - cx) * (conto.x - x - cx));
        int i_3 = (int)((double)(90 + i_0) - Math.atan((double)i_2 / (double)(conto.y - y - cy)) / 0.017453292519943295D);
        for(; i_1 < 0; i_1 += 360);
        for(; i_1 > 360; i_1 -= 360);
        xz = i_1;
        zy += (i_3 - zy) / 5;
        if((int)Math.sqrt((conto.z - z) * (conto.z - z) + (conto.x - x - cx) * (conto.x - x - cx) + (conto.y - y - cy) * (conto.y - y - cy)) > 6000)
            td = true;
    }

    public void aroundtrack(CheckPoints checkpoints)
    {
        y = -hit;
        x = cx + (int)trx + (int)(17000F * cos(vxz));
        z = (int)trz + (int)(17000F * sin(vxz));
        if(hit > 5000)
        {
            if(hit == 45000)
            {
                fo = 1.0F;
                zy = 67;
                atrx = ((long)checkpoints.x[0] - trx) / 116L;
                atrz = ((long)checkpoints.z[0] - trz) / 116L;
                focus_point = 400;
            }
            hit -= fallen;
            fallen += 7;
            trx += atrx;
            trz += atrz;
            if(hit < 17600)
                zy -= 2;
            if(fallen > 500)
                fallen = 500;
            if(hit <= 5000)
            {
                hit = 5000;
                fallen = 0;
            }
            vxz += 3;
        } else
        {
            focus_point = (int)(400F * fo);
            if((double)Math.abs(fo - gofo) > 0.0050000000000000001D)
            {
                if(fo < gofo)
                    fo += 0.005F;
                else
                    fo -= 0.005F;
            } else
            {
                gofo = (float)(0.34999999403953552D + Math.random() * 1.3D);
            }
            vxz++;
            trx -= (trx - (long)checkpoints.x[ptr]) / 10L;
            trz -= (trz - (long)checkpoints.z[ptr]) / 10L;
            if(ptcnt == 7)
            {
                ptr++;
                if(ptr == checkpoints.n)
                {
                    ptr = 0;
                    nrnd++;
                }
                ptcnt = 0;
            } else
            {
                ptcnt++;
            }
        }
        if(vxz > 360)
            vxz -= 360;
        xz = -vxz - 90;
        int i;
        if(-y - cy < 0)
            i = -180;
        i = (int)Math.sqrt(((trz - (long)z) + (long)cz) * ((trz - (long)z) + (long)cz) + (trx - (long)x - (long)cx) * (trx - (long)x - (long)cx));
        if(cpflik)
            cpflik = false;
        else
            cpflik = true;
    }

    public void around(ContO conto, boolean bool)
    {
        if(!bool)
        {
            if(!vert)
                adv += 2;
            else
                adv -= 2;
            if(adv > 900)
                vert = true;
            if(adv < -500)
                vert = false;
        } else
        {
            adv -= 14;
            if(adv < 617)
                adv = 617;
        }
        int i = 500 + adv;
        if(bool && i < 1300)
            i = 1300;
        if(i < 1000)
            i = 1000;
        y = conto.y - adv;
        if(y > 10)
            vert = false;
        x = conto.x + (int)((float)(conto.x - i - conto.x) * cos(vxz));
        z = conto.z + (int)((float)(conto.x - i - conto.x) * sin(vxz));
        if(!bool)
            vxz += 2;
        else
            vxz += 4;
        int i_4 = 0;
        int i_5 = y;
        if(i_5 > 0)
            i_5 = 0;
        if(conto.y - i_5 - cy < 0)
            i_4 = -180;
        int i_6 = (int)Math.sqrt(((conto.z - z) + cz) * ((conto.z - z) + cz) + (conto.x - x - cx) * (conto.x - x - cx));
        int i_7 = (int)((double)(90 + i_4) - Math.atan((double)i_6 / (double)(conto.y - i_5 - cy)) / 0.017453292519943295D);
        xz = -vxz + 90;
        if(bool)
            i_7 -= 15;
        zy += (i_7 - zy) / 10;
    }

    public void getaround(ContO conto)
    {
        if(!vert)
            adv += 2;
        else
            adv -= 2;
        if(adv > 1700)
            vert = true;
        if(adv < -500)
            vert = false;
        if(conto.y - adv > 10)
            vert = false;
        int i = 500 + adv;
        if(i < 1000)
            i = 1000;
        int i_8 = conto.y - adv;
        int i_9 = conto.x + (int)((float)(conto.x - i - conto.x) * cos(vxz));
        int i_10 = conto.z + (int)((float)(conto.x - i - conto.x) * sin(vxz));
        int i_11 = 0;
        if(Math.abs(i_8 - y) > fvect)
        {
            if(y < i_8)
                y += fvect;
            else
                y -= fvect;
        } else
        {
            y = i_8;
            i_11++;
        }
        if(Math.abs(i_9 - x) > fvect)
        {
            if(x < i_9)
                x += fvect;
            else
                x -= fvect;
        } else
        {
            x = i_9;
            i_11++;
        }
        if(Math.abs(i_10 - z) > fvect)
        {
            if(z < i_10)
                z += fvect;
            else
                z -= fvect;
        } else
        {
            z = i_10;
            i_11++;
        }
        if(i_11 == 3)
            fvect = 200;
        else
            fvect += 2;
        for(vxz += 2; vxz > 360; vxz -= 360);
        int i_12 = -vxz + 90;
        int i_13 = 0;
        if(conto.x - x - cx > 0)
            i_13 = 180;
        int i_14 = -(int)((double)(90 + i_13) + Math.atan((double)(conto.z - z) / (double)(conto.x - x - cx)) / 0.017453292519943295D);
        int i_15 = y;
        i_13 = 0;
        if(i_15 > 0)
            i_15 = 0;
        if(conto.y - i_15 - cy < 0)
            i_13 = -180;
        int i_16 = (int)Math.sqrt(((conto.z - z) + cz) * ((conto.z - z) + cz) + (conto.x - x - cx) * (conto.x - x - cx));
        int i_17 = 25;
        if(i_16 != 0)
            i_17 = (int)((double)(90 + i_13) - Math.atan((double)i_16 / (double)(conto.y - i_15 - cy)) / 0.017453292519943295D);
        for(; i_12 < 0; i_12 += 360);
        for(; i_12 > 360; i_12 -= 360);
        for(; i_14 < 0; i_14 += 360);
        for(; i_14 > 360; i_14 -= 360);
        if((Math.abs(i_12 - i_14) < 30 || Math.abs(i_12 - i_14) > 330) && i_11 == 3)
        {
            if(Math.abs(i_12 - xz) > 7 && Math.abs(i_12 - xz) < 353)
            {
                if(Math.abs(i_12 - xz) > 180)
                {
                    if(xz > i_12)
                        xz += 7;
                    else
                        xz -= 7;
                } else
                if(xz < i_12)
                    xz += 7;
                else
                    xz -= 7;
            } else
            {
                xz = i_12;
            }
        } else
        if(Math.abs(i_14 - xz) > 6 && Math.abs(i_14 - xz) < 354)
        {
            if(Math.abs(i_14 - xz) > 180)
            {
                if(xz > i_14)
                    xz += 3;
                else
                    xz -= 3;
            } else
            if(xz < i_14)
                xz += 3;
            else
                xz -= 3;
        } else
        {
            xz = i_14;
        }
        zy += (i_17 - zy) / 10;
    }

    public void transaround(ContO conto, ContO conto_18, int i)
    {
        int i_19 = (conto.x * (20 - i) + conto_18.x * i) / 20;
        int i_20 = (conto.y * (20 - i) + conto_18.y * i) / 20;
        int i_21 = (conto.z * (20 - i) + conto_18.z * i) / 20;
        if(!vert)
            adv += 2;
        else
            adv -= 2;
        if(adv > 900)
            vert = true;
        if(adv < -500)
            vert = false;
        int i_22 = 500 + adv;
        if(i_22 < 1000)
            i_22 = 1000;
        y = i_20 - adv;
        if(y > 10)
            vert = false;
        x = i_19 + (int)((float)(i_19 - i_22 - i_19) * cos(vxz));
        z = i_21 + (int)((float)(i_19 - i_22 - i_19) * sin(vxz));
        vxz += 2;
        int i_23 = 0;
        int i_24 = y;
        if(i_24 > 0)
            i_24 = 0;
        if(i_20 - i_24 - cy < 0)
            i_23 = -180;
        int i_25 = (int)Math.sqrt(((i_21 - z) + cz) * ((i_21 - z) + cz) + (i_19 - x - cx) * (i_19 - x - cx));
        int i_26 = (int)((double)(90 + i_23) - Math.atan((double)i_25 / (double)(i_20 - i_24 - cy)) / 0.017453292519943295D);
        xz = -vxz + 90;
        zy += (i_26 - zy) / 10;
    }

    public void follow(ContO conto, int i, int i_27)
    {
        zy = 10;
        int i_28 = 2 + Math.abs(bcxz) / 4;
        if(i_28 > 20)
            i_28 = 20;
        if(i_27 != 0)
        {
            if(i_27 == 1)
            {
                if(bcxz < 180)
                    bcxz += i_28;
                if(bcxz > 180)
                    bcxz = 180;
            }
            if(i_27 == -1)
            {
                if(bcxz > -180)
                    bcxz -= i_28;
                if(bcxz < -180)
                    bcxz = -180;
            }
        } else
        if(Math.abs(bcxz) > i_28)
        {
            if(bcxz > 0)
                bcxz -= i_28;
            else
                bcxz += i_28;
        } else
        if(bcxz != 0)
            bcxz = 0;
        i += bcxz;
        xz = -i;
        x = (conto.x - cx) + (int)((float)(-(conto.z - 800 - conto.z)) * sin(i));
        z = (conto.z - cz) + (int)((float)(conto.z - 800 - conto.z) * cos(i));
        y = conto.y - 250 - cy;
    }

    public void getfollow(ContO conto, int i, int i_29)
    {
        zy = 10;
        int i_30 = 2 + Math.abs(bcxz) / 4;
        if(i_30 > 20)
            i_30 = 20;
        if(i_29 != 0)
        {
            if(i_29 == 1)
            {
                if(bcxz < 180)
                    bcxz += i_30;
                if(bcxz > 180)
                    bcxz = 180;
            }
            if(i_29 == -1)
            {
                if(bcxz > -180)
                    bcxz -= i_30;
                if(bcxz < -180)
                    bcxz = -180;
            }
        } else
        if(Math.abs(bcxz) > i_30)
        {
            if(bcxz > 0)
                bcxz -= i_30;
            else
                bcxz += i_30;
        } else
        if(bcxz != 0)
            bcxz = 0;
        i += bcxz;
        xz = -i;
        int i_31 = (conto.x - cx) + (int)((float)(-(conto.z - 800 - conto.z)) * sin(i));
        int i_32 = (conto.z - cz) + (int)((float)(conto.z - 800 - conto.z) * cos(i));
        int i_33 = conto.y - 250 - cy;
        int i_34 = 0;
        if(Math.abs(i_33 - y) > fvect)
        {
            if(y < i_33)
                y += fvect;
            else
                y -= fvect;
        } else
        {
            y = i_33;
            i_34++;
        }
        if(Math.abs(i_31 - x) > fvect)
        {
            if(x < i_31)
                x += fvect;
            else
                x -= fvect;
        } else
        {
            x = i_31;
            i_34++;
        }
        if(Math.abs(i_32 - z) > fvect)
        {
            if(z < i_32)
                z += fvect;
            else
                z -= fvect;
        } else
        {
            z = i_32;
            i_34++;
        }
        if(i_34 == 3)
            fvect = 200;
        else
            fvect += 2;
    }

    public void newpolys(int i, int i_35, int i_36, int i_37, Trackers trackers, int i_38)
    {
        Random random = new Random((i_38 + cgrnd[0] + cgrnd[1] + cgrnd[2]) * 1671);
        nrw = i_35 / 1200 + 9;
        ncl = i_37 / 1200 + 9;
        sgpx = i - 4800;
        sgpz = i_36 - 4800;
        ogpx = (int[][])null;
        ogpz = (int[][])null;
        pvr = (float[][])null;
        cgpx = null;
        cgpz = null;
        pmx = null;
        pcv = null;
        ogpx = new int[nrw * ncl][8];
        ogpz = new int[nrw * ncl][8];
        pvr = new float[nrw * ncl][8];
        cgpx = new int[nrw * ncl];
        cgpz = new int[nrw * ncl];
        pmx = new int[nrw * ncl];
        pcv = new float[nrw * ncl];
        int i_39 = 0;
        int i_40 = 0;
        for(int i_41 = 0; i_41 < nrw * ncl; i_41++)
        {
            cgpx[i_41] = sgpx + i_39 * 1200 + (int)(random.nextDouble() * 1000D - 500D);
            cgpz[i_41] = sgpz + i_40 * 1200 + (int)(random.nextDouble() * 1000D - 500D);
            for(int i_42 = 0; i_42 < trackers.nt; i_42++)
            {
                if(trackers.zy[i_42] != 0 || trackers.xy[i_42] != 0)
                    continue;
                if(trackers.radx[i_42] < trackers.radz[i_42] && Math.abs(cgpz[i_41] - trackers.z[i_42]) < trackers.radz[i_42])
                    for(; Math.abs(cgpx[i_41] - trackers.x[i_42]) < trackers.radx[i_42]; cgpx[i_41] += random.nextDouble() * (double)trackers.radx[i_42] * 2D - (double)trackers.radx[i_42]);
                if(trackers.radz[i_42] >= trackers.radx[i_42] || Math.abs(cgpx[i_41] - trackers.x[i_42]) >= trackers.radx[i_42])
                    continue;
                for(; Math.abs(cgpz[i_41] - trackers.z[i_42]) < trackers.radz[i_42]; cgpz[i_41] += random.nextDouble() * (double)trackers.radz[i_42] * 2D - (double)trackers.radz[i_42]);
            }

            if(++i_39 == nrw)
            {
                i_39 = 0;
                i_40++;
            }
        }

        for(int i_43 = 0; i_43 < nrw * ncl; i_43++)
        {
            float f = (float)(0.29999999999999999D + 1.6000000000000001D * random.nextDouble());
            ogpx[i_43][0] = 0;
            ogpz[i_43][0] = (int)((100D + random.nextDouble() * 760D) * (double)f);
            ogpx[i_43][1] = (int)((100D + random.nextDouble() * 760D) * 0.70709999999999995D * (double)f);
            ogpz[i_43][1] = ogpx[i_43][1];
            ogpx[i_43][2] = (int)((100D + random.nextDouble() * 760D) * (double)f);
            ogpz[i_43][2] = 0;
            ogpx[i_43][3] = (int)((100D + random.nextDouble() * 760D) * 0.70709999999999995D * (double)f);
            ogpz[i_43][3] = -ogpx[i_43][3];
            ogpx[i_43][4] = 0;
            ogpz[i_43][4] = -(int)((100D + random.nextDouble() * 760D) * (double)f);
            ogpx[i_43][5] = -(int)((100D + random.nextDouble() * 760D) * 0.70709999999999995D * (double)f);
            ogpz[i_43][5] = ogpx[i_43][5];
            ogpx[i_43][6] = -(int)((100D + random.nextDouble() * 760D) * (double)f);
            ogpz[i_43][6] = 0;
            ogpx[i_43][7] = -(int)((100D + random.nextDouble() * 760D) * 0.70709999999999995D * (double)f);
            ogpz[i_43][7] = -ogpx[i_43][7];
            for(int i_44 = 0; i_44 < 8; i_44++)
            {
                int i_45 = i_44 - 1;
                if(i_45 == -1)
                    i_45 = 7;
                int i_46 = i_44 + 1;
                if(i_46 == 8)
                    i_46 = 0;
                ogpx[i_43][i_44] = ((ogpx[i_43][i_45] + ogpx[i_43][i_46]) / 2 + ogpx[i_43][i_44]) / 2;
                ogpz[i_43][i_44] = ((ogpz[i_43][i_45] + ogpz[i_43][i_46]) / 2 + ogpz[i_43][i_44]) / 2;
                pvr[i_43][i_44] = (float)(1.1000000000000001D + random.nextDouble() * 0.80000000000000004D);
                int i_47 = (int)Math.sqrt((int)((float)(ogpx[i_43][i_44] * ogpx[i_43][i_44]) * pvr[i_43][i_44] * pvr[i_43][i_44] + (float)(ogpz[i_43][i_44] * ogpz[i_43][i_44]) * pvr[i_43][i_44] * pvr[i_43][i_44]));
                if(i_47 > pmx[i_43])
                    pmx[i_43] = i_47;
            }

            pcv[i_43] = (float)(0.96999999999999997D + random.nextDouble() * 0.029999999999999999D);
            if(pcv[i_43] > 1.0F)
                pcv[i_43] = 1.0F;
            if(random.nextDouble() > random.nextDouble())
                pcv[i_43] = 1.0F;
        }

    }

    public void groundpolys(Graphics2D graphics2d)
    {
        int i = (x - sgpx) / 1200 - 12;
        if(i < 0)
            i = 0;
        int i_48 = i + 25;
        if(i_48 > nrw)
            i_48 = nrw;
        if(i_48 < i)
            i_48 = i;
        int i_49 = (z - sgpz) / 1200 - 12;
        if(i_49 < 0)
            i_49 = 0;
        int i_50 = i_49 + 25;
        if(i_50 > ncl)
            i_50 = ncl;
        if(i_50 < i_49)
            i_50 = i_49;
        int is[][] = new int[i_48 - i][i_50 - i_49];
        for(int i_51 = i; i_51 < i_48; i_51++)
        {
            for(int i_52 = i_49; i_52 < i_50; i_52++)
            {
                is[i_51 - i][i_52 - i_49] = 0;
                int i_53 = i_51 + i_52 * nrw;
                if(resdown >= 2 && i_53 % 2 != 0)
                    continue;
                int i_54 = cx + (int)((float)(cgpx[i_53] - x - cx) * cos(xz) - (float)(cgpz[i_53] - z - cz) * sin(xz));
                int i_55 = cz + (int)((float)(cgpx[i_53] - x - cx) * sin(xz) + (float)(cgpz[i_53] - z - cz) * cos(xz));
                int i_56 = cz + (int)((float)(250 - y - cy) * sin(zy) + (float)(i_55 - cz) * cos(zy));
                if(xs(i_54 + pmx[i_53], i_56) <= 0 || xs(i_54 - pmx[i_53], i_56) >= w || i_56 <= -pmx[i_53] || i_56 >= fade[2])
                    continue;
                is[i_51 - i][i_52 - i_49] = i_56;
                int is_57[] = new int[8];
                int is_58[] = new int[8];
                int is_59[] = new int[8];
                for(int i_60 = 0; i_60 < 8; i_60++)
                {
                    is_57[i_60] = (int)(((float)ogpx[i_53][i_60] * pvr[i_53][i_60] + (float)cgpx[i_53]) - (float)x);
                    is_58[i_60] = (int)(((float)ogpz[i_53][i_60] * pvr[i_53][i_60] + (float)cgpz[i_53]) - (float)z);
                    is_59[i_60] = ground;
                }

                rot(is_57, is_58, cx, cz, xz, 8);
                rot(is_59, is_58, cy, cz, zy, 8);
                int is_61[] = new int[8];
                int is_62[] = new int[8];
                int i_63 = 0;
                int i_64 = 0;
                int i_65 = 0;
                int i_66 = 0;
                boolean bool = true;
                for(int i_67 = 0; i_67 < 8; i_67++)
                {
                    is_61[i_67] = xs(is_57[i_67], is_58[i_67]);
                    is_62[i_67] = ys(is_59[i_67], is_58[i_67]);
                    if(is_62[i_67] < 0 || is_58[i_67] < 10)
                        i_63++;
                    if(is_62[i_67] > h || is_58[i_67] < 10)
                        i_64++;
                    if(is_61[i_67] < 0 || is_58[i_67] < 10)
                        i_65++;
                    if(is_61[i_67] > w || is_58[i_67] < 10)
                        i_66++;
                }

                if(i_65 == 8 || i_63 == 8 || i_64 == 8 || i_66 == 8)
                    bool = false;
                if(!bool)
                    continue;
                int i_68 = (int)(((float)cpol[0] * pcv[i_53] + (float)cgrnd[0]) / 2.0F);
                int i_69 = (int)(((float)cpol[1] * pcv[i_53] + (float)cgrnd[1]) / 2.0F);
                int i_70 = (int)(((float)cpol[2] * pcv[i_53] + (float)cgrnd[2]) / 2.0F);
                if(i_56 - pmx[i_53] > fade[0])
                {
                    i_68 = (i_68 * 7 + cfade[0]) / 8;
                    i_69 = (i_69 * 7 + cfade[1]) / 8;
                    i_70 = (i_70 * 7 + cfade[2]) / 8;
                }
                if(i_56 - pmx[i_53] > fade[1])
                {
                    i_68 = (i_68 * 7 + cfade[0]) / 8;
                    i_69 = (i_69 * 7 + cfade[1]) / 8;
                    i_70 = (i_70 * 7 + cfade[2]) / 8;
                }
                graphics2d.setColor(new Color(i_68, i_69, i_70));
                graphics2d.fillPolygon(is_61, is_62, 8);
            }

        }

        for(int i_71 = i; i_71 < i_48; i_71++)
        {
            for(int i_72 = i_49; i_72 < i_50; i_72++)
            {
                if(is[i_71 - i][i_72 - i_49] == 0)
                    continue;
                int i_73 = i_71 + i_72 * nrw;
                int is_74[] = new int[8];
                int is_75[] = new int[8];
                int is_76[] = new int[8];
                for(int i_77 = 0; i_77 < 8; i_77++)
                {
                    is_74[i_77] = (ogpx[i_73][i_77] + cgpx[i_73]) - x;
                    is_75[i_77] = (ogpz[i_73][i_77] + cgpz[i_73]) - z;
                    is_76[i_77] = ground;
                }

                rot(is_74, is_75, cx, cz, xz, 8);
                rot(is_76, is_75, cy, cz, zy, 8);
                int is_78[] = new int[8];
                int is_79[] = new int[8];
                int i_80 = 0;
                int i_81 = 0;
                int i_82 = 0;
                int i_83 = 0;
                boolean bool = true;
                for(int i_84 = 0; i_84 < 8; i_84++)
                {
                    is_78[i_84] = xs(is_74[i_84], is_75[i_84]);
                    is_79[i_84] = ys(is_76[i_84], is_75[i_84]);
                    if(is_79[i_84] < 0 || is_75[i_84] < 10)
                        i_80++;
                    if(is_79[i_84] > h || is_75[i_84] < 10)
                        i_81++;
                    if(is_78[i_84] < 0 || is_75[i_84] < 10)
                        i_82++;
                    if(is_78[i_84] > w || is_75[i_84] < 10)
                        i_83++;
                }

                if(i_82 == 8 || i_80 == 8 || i_81 == 8 || i_83 == 8)
                    bool = false;
                if(!bool)
                    continue;
                int i_85 = (int)((float)cpol[0] * pcv[i_73]);
                int i_86 = (int)((float)cpol[1] * pcv[i_73]);
                int i_87 = (int)((float)cpol[2] * pcv[i_73]);
                if(is[i_71 - i][i_72 - i_49] - pmx[i_73] > fade[0])
                {
                    i_85 = (i_85 * 7 + cfade[0]) / 8;
                    i_86 = (i_86 * 7 + cfade[1]) / 8;
                    i_87 = (i_87 * 7 + cfade[2]) / 8;
                }
                if(is[i_71 - i][i_72 - i_49] - pmx[i_73] > fade[1])
                {
                    i_85 = (i_85 * 7 + cfade[0]) / 8;
                    i_86 = (i_86 * 7 + cfade[1]) / 8;
                    i_87 = (i_87 * 7 + cfade[2]) / 8;
                }
                graphics2d.setColor(new Color(i_85, i_86, i_87));
                graphics2d.fillPolygon(is_78, is_79, 8);
            }

        }

    }

    public void newclouds(int i, int i_88, int i_89, int i_90)
    {
        clx = null;
        clz = null;
        cmx = null;
        clax = (int[][][])null;
        clay = (int[][][])null;
        claz = (int[][][])null;
        clc = (int[][][][])null;
        i = i / 20 - 10000;
        i_88 = i_88 / 20 + 10000;
        i_89 = i_89 / 20 - 10000;
        i_90 = i_90 / 20 + 10000;
        noc = ((i_88 - i) * (i_90 - i_89)) / 0xfe502b;
        clx = new int[noc];
        clz = new int[noc];
        cmx = new int[noc];
        clax = new int[noc][3][12];
        clay = new int[noc][3][12];
        claz = new int[noc][3][12];
        clc = new int[noc][2][6][3];
        for(int i_91 = 0; i_91 < noc; i_91++)
        {
            clx[i_91] = (int)((double)i + (double)(i_88 - i) * Math.random());
            clz[i_91] = (int)((double)i_89 + (double)(i_90 - i_89) * Math.random());
            float f = (float)(0.25D + Math.random() * 1.25D);
            float f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][0] = (int)((double)f_92 * 0.3826D);
            claz[i_91][0][0] = (int)((double)f_92 * 0.92379999999999995D);
            clay[i_91][0][0] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][1] = (int)((double)f_92 * 0.70709999999999995D);
            claz[i_91][0][1] = (int)((double)f_92 * 0.70709999999999995D);
            clay[i_91][0][1] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][2] = (int)((double)f_92 * 0.92379999999999995D);
            claz[i_91][0][2] = (int)((double)f_92 * 0.3826D);
            clay[i_91][0][2] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][3] = (int)((double)f_92 * 0.92379999999999995D);
            claz[i_91][0][3] = -(int)((double)f_92 * 0.3826D);
            clay[i_91][0][3] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][4] = (int)((double)f_92 * 0.70709999999999995D);
            claz[i_91][0][4] = -(int)((double)f_92 * 0.70709999999999995D);
            clay[i_91][0][4] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][5] = (int)((double)f_92 * 0.3826D);
            claz[i_91][0][5] = -(int)((double)f_92 * 0.92379999999999995D);
            clay[i_91][0][5] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][6] = -(int)((double)f_92 * 0.3826D);
            claz[i_91][0][6] = -(int)((double)f_92 * 0.92379999999999995D);
            clay[i_91][0][6] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][7] = -(int)((double)f_92 * 0.70709999999999995D);
            claz[i_91][0][7] = -(int)((double)f_92 * 0.70709999999999995D);
            clay[i_91][0][7] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][8] = -(int)((double)f_92 * 0.92379999999999995D);
            claz[i_91][0][8] = -(int)((double)f_92 * 0.3826D);
            clay[i_91][0][8] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][9] = -(int)((double)f_92 * 0.92379999999999995D);
            claz[i_91][0][9] = (int)((double)f_92 * 0.3826D);
            clay[i_91][0][9] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][10] = -(int)((double)f_92 * 0.70709999999999995D);
            claz[i_91][0][10] = (int)((double)f_92 * 0.70709999999999995D);
            clay[i_91][0][10] = (int)((25D - Math.random() * 50D) * (double)f);
            f_92 = (float)((200D + Math.random() * 700D) * (double)f);
            clax[i_91][0][11] = -(int)((double)f_92 * 0.3826D);
            claz[i_91][0][11] = (int)((double)f_92 * 0.92379999999999995D);
            clay[i_91][0][11] = (int)((25D - Math.random() * 50D) * (double)f);
            for(int i_93 = 0; i_93 < 12; i_93++)
            {
                int i_94 = i_93 - 1;
                if(i_94 == -1)
                    i_94 = 11;
                int i_95 = i_93 + 1;
                if(i_95 == 12)
                    i_95 = 0;
                clax[i_91][0][i_93] = ((clax[i_91][0][i_94] + clax[i_91][0][i_95]) / 2 + clax[i_91][0][i_93]) / 2;
                clay[i_91][0][i_93] = ((clay[i_91][0][i_94] + clay[i_91][0][i_95]) / 2 + clay[i_91][0][i_93]) / 2;
                claz[i_91][0][i_93] = ((claz[i_91][0][i_94] + claz[i_91][0][i_95]) / 2 + claz[i_91][0][i_93]) / 2;
            }

            for(int i_96 = 0; i_96 < 12; i_96++)
            {
                f_92 = (float)(1.2D + 0.59999999999999998D * Math.random());
                clax[i_91][1][i_96] = (int)((float)clax[i_91][0][i_96] * f_92);
                claz[i_91][1][i_96] = (int)((float)claz[i_91][0][i_96] * f_92);
                clay[i_91][1][i_96] = (int)((double)clay[i_91][0][i_96] - 100D * Math.random());
                f_92 = (float)(1.1000000000000001D + 0.29999999999999999D * Math.random());
                clax[i_91][2][i_96] = (int)((float)clax[i_91][1][i_96] * f_92);
                claz[i_91][2][i_96] = (int)((float)claz[i_91][1][i_96] * f_92);
                clay[i_91][2][i_96] = (int)((double)clay[i_91][1][i_96] - 240D * Math.random());
            }

            cmx[i_91] = 0;
            for(int i_97 = 0; i_97 < 12; i_97++)
            {
                int i_98 = i_97 - 1;
                if(i_98 == -1)
                    i_98 = 11;
                int i_99 = i_97 + 1;
                if(i_99 == 12)
                    i_99 = 0;
                clay[i_91][1][i_97] = ((clay[i_91][1][i_98] + clay[i_91][1][i_99]) / 2 + clay[i_91][1][i_97]) / 2;
                clay[i_91][2][i_97] = ((clay[i_91][2][i_98] + clay[i_91][2][i_99]) / 2 + clay[i_91][2][i_97]) / 2;
                int i_100 = (int)Math.sqrt(clax[i_91][2][i_97] * clax[i_91][2][i_97] + claz[i_91][2][i_97] * claz[i_91][2][i_97]);
                if(i_100 > cmx[i_91])
                    cmx[i_91] = i_100;
            }

            for(int i_101 = 0; i_101 < 6; i_101++)
            {
                double d = Math.random();
                double d_102 = Math.random();
                for(int i_103 = 0; i_103 < 3; i_103++)
                {
                    f_92 = (float)clds[i_103] * 1.05F - (float)clds[i_103];
                    clc[i_91][0][i_101][i_103] = (int)((double)clds[i_103] + (double)f_92 * d);
                    if(clc[i_91][0][i_101][i_103] > 255)
                        clc[i_91][0][i_101][i_103] = 255;
                    if(clc[i_91][0][i_101][i_103] < 0)
                        clc[i_91][0][i_101][i_103] = 0;
                    clc[i_91][1][i_101][i_103] = (int)((double)((float)clds[i_103] * 1.05F) + (double)f_92 * d_102);
                    if(clc[i_91][1][i_101][i_103] > 255)
                        clc[i_91][1][i_101][i_103] = 255;
                    if(clc[i_91][1][i_101][i_103] < 0)
                        clc[i_91][1][i_101][i_103] = 0;
                }

            }

        }

    }

    public void drawclouds(Graphics2D graphics2d)
    {
        for(int i = 0; i < noc; i++)
        {
            int i_104 = cx + (int)((float)(clx[i] - x / 20 - cx) * cos(xz) - (float)(clz[i] - z / 20 - cz) * sin(xz));
            int i_105 = cz + (int)((float)(clx[i] - x / 20 - cx) * sin(xz) + (float)(clz[i] - z / 20 - cz) * cos(xz));
            int i_106 = cz + (int)((float)(cldd[4] - y / 20 - cy) * sin(zy) + (float)(i_105 - cz) * cos(zy));
            int i_107 = xs(i_104 + cmx[i], i_106);
            int i_108 = xs(i_104 - cmx[i], i_106);
            if(i_107 <= 0 || i_108 >= w || i_106 <= -cmx[i] || i_107 - i_108 <= 20)
                continue;
            int is[][] = new int[3][12];
            int is_109[][] = new int[3][12];
            int is_110[][] = new int[3][12];
            int is_111[] = new int[12];
            int is_112[] = new int[12];
            boolean bool = false;
            boolean bool_113 = false;
            boolean bool_114 = false;
            boolean bool_115 = false;
            boolean bool_116 = true;
            boolean bool_117 = false;
            boolean bool_118 = false;
            boolean bool_119 = false;
            for(int i_120 = 0; i_120 < 3; i_120++)
            {
                for(int i_121 = 0; i_121 < 12; i_121++)
                {
                    is[i_120][i_121] = (clax[i][i_120][i_121] + clx[i]) - x / 20;
                    is_110[i_120][i_121] = (claz[i][i_120][i_121] + clz[i]) - z / 20;
                    is_109[i_120][i_121] = (clay[i][i_120][i_121] + cldd[4]) - y / 20;
                }

                rot(is[i_120], is_110[i_120], cx, cz, xz, 12);
                rot(is_109[i_120], is_110[i_120], cy, cz, zy, 12);
            }

            for(int i_122 = 0; i_122 < 12; i_122 += 2)
            {
                int i_123 = 0;
                int i_124 = 0;
                int i_125 = 0;
                int i_126 = 0;
                bool_116 = true;
                int i_127 = 0;
                int i_128 = 0;
                int i_129 = 0;
                for(int i_130 = 0; i_130 < 6; i_130++)
                {
                    int i_131 = 0;
                    int i_132 = 1;
                    if(i_130 == 0)
                        i_131 = i_122;
                    if(i_130 == 1)
                    {
                        i_131 = i_122 + 1;
                        if(i_131 >= 12)
                            i_131 -= 12;
                    }
                    if(i_130 == 2)
                    {
                        i_131 = i_122 + 2;
                        if(i_131 >= 12)
                            i_131 -= 12;
                    }
                    if(i_130 == 3)
                    {
                        i_131 = i_122 + 2;
                        if(i_131 >= 12)
                            i_131 -= 12;
                        i_132 = 2;
                    }
                    if(i_130 == 4)
                    {
                        i_131 = i_122 + 1;
                        if(i_131 >= 12)
                            i_131 -= 12;
                        i_132 = 2;
                    }
                    if(i_130 == 5)
                    {
                        i_131 = i_122;
                        i_132 = 2;
                    }
                    is_111[i_130] = xs(is[i_132][i_131], is_110[i_132][i_131]);
                    is_112[i_130] = ys(is_109[i_132][i_131], is_110[i_132][i_131]);
                    i_128 += is[i_132][i_131];
                    i_127 += is_109[i_132][i_131];
                    i_129 += is_110[i_132][i_131];
                    if(is_112[i_130] < 0 || is_110[0][i_130] < 10)
                        i_123++;
                    if(is_112[i_130] > h || is_110[0][i_130] < 10)
                        i_124++;
                    if(is_111[i_130] < 0 || is_110[0][i_130] < 10)
                        i_125++;
                    if(is_111[i_130] > w || is_110[0][i_130] < 10)
                        i_126++;
                }

                if(i_125 == 6 || i_123 == 6 || i_124 == 6 || i_126 == 6)
                    bool_116 = false;
                if(!bool_116)
                    continue;
                i_128 /= 6;
                i_127 /= 6;
                i_129 /= 6;
                int i_133 = (int)Math.sqrt((cy - i_127) * (cy - i_127) + (cx - i_128) * (cx - i_128) + i_129 * i_129);
                if(i_133 >= fade[7])
                    continue;
                int i_134 = clc[i][1][i_122 / 2][0];
                int i_135 = clc[i][1][i_122 / 2][1];
                int i_136 = clc[i][1][i_122 / 2][2];
                for(int i_137 = 0; i_137 < 16; i_137++)
                    if(i_133 > fade[i_137])
                    {
                        i_134 = (i_134 * fogd + cfade[0]) / (fogd + 1);
                        i_135 = (i_135 * fogd + cfade[1]) / (fogd + 1);
                        i_136 = (i_136 * fogd + cfade[2]) / (fogd + 1);
                    }

                graphics2d.setColor(new Color(i_134, i_135, i_136));
                graphics2d.fillPolygon(is_111, is_112, 6);
            }

            for(int i_138 = 0; i_138 < 12; i_138 += 2)
            {
                int i_139 = 0;
                int i_140 = 0;
                int i_141 = 0;
                int i_142 = 0;
                bool_116 = true;
                int i_143 = 0;
                int i_144 = 0;
                int i_145 = 0;
                for(int i_146 = 0; i_146 < 6; i_146++)
                {
                    int i_147 = 0;
                    int i_148 = 0;
                    if(i_146 == 0)
                        i_147 = i_138;
                    if(i_146 == 1)
                    {
                        i_147 = i_138 + 1;
                        if(i_147 >= 12)
                            i_147 -= 12;
                    }
                    if(i_146 == 2)
                    {
                        i_147 = i_138 + 2;
                        if(i_147 >= 12)
                            i_147 -= 12;
                    }
                    if(i_146 == 3)
                    {
                        i_147 = i_138 + 2;
                        if(i_147 >= 12)
                            i_147 -= 12;
                        i_148 = 1;
                    }
                    if(i_146 == 4)
                    {
                        i_147 = i_138 + 1;
                        if(i_147 >= 12)
                            i_147 -= 12;
                        i_148 = 1;
                    }
                    if(i_146 == 5)
                    {
                        i_147 = i_138;
                        i_148 = 1;
                    }
                    is_111[i_146] = xs(is[i_148][i_147], is_110[i_148][i_147]);
                    is_112[i_146] = ys(is_109[i_148][i_147], is_110[i_148][i_147]);
                    i_144 += is[i_148][i_147];
                    i_143 += is_109[i_148][i_147];
                    i_145 += is_110[i_148][i_147];
                    if(is_112[i_146] < 0 || is_110[0][i_146] < 10)
                        i_139++;
                    if(is_112[i_146] > h || is_110[0][i_146] < 10)
                        i_140++;
                    if(is_111[i_146] < 0 || is_110[0][i_146] < 10)
                        i_141++;
                    if(is_111[i_146] > w || is_110[0][i_146] < 10)
                        i_142++;
                }

                if(i_141 == 6 || i_139 == 6 || i_140 == 6 || i_142 == 6)
                    bool_116 = false;
                if(!bool_116)
                    continue;
                i_144 /= 6;
                i_143 /= 6;
                i_145 /= 6;
                int i_149 = (int)Math.sqrt((cy - i_143) * (cy - i_143) + (cx - i_144) * (cx - i_144) + i_145 * i_145);
                if(i_149 >= fade[7])
                    continue;
                int i_150 = clc[i][0][i_138 / 2][0];
                int i_151 = clc[i][0][i_138 / 2][1];
                int i_152 = clc[i][0][i_138 / 2][2];
                for(int i_153 = 0; i_153 < 16; i_153++)
                    if(i_149 > fade[i_153])
                    {
                        i_150 = (i_150 * fogd + cfade[0]) / (fogd + 1);
                        i_151 = (i_151 * fogd + cfade[1]) / (fogd + 1);
                        i_152 = (i_152 * fogd + cfade[2]) / (fogd + 1);
                    }

                graphics2d.setColor(new Color(i_150, i_151, i_152));
                graphics2d.fillPolygon(is_111, is_112, 6);
            }

            int i_154 = 0;
            int i_155 = 0;
            int i_156 = 0;
            int i_157 = 0;
            bool_116 = true;
            int i_158 = 0;
            int i_159 = 0;
            int i_160 = 0;
            for(int i_161 = 0; i_161 < 12; i_161++)
            {
                is_111[i_161] = xs(is[0][i_161], is_110[0][i_161]);
                is_112[i_161] = ys(is_109[0][i_161], is_110[0][i_161]);
                i_159 += is[0][i_161];
                i_158 += is_109[0][i_161];
                i_160 += is_110[0][i_161];
                if(is_112[i_161] < 0 || is_110[0][i_161] < 10)
                    i_154++;
                if(is_112[i_161] > h || is_110[0][i_161] < 10)
                    i_155++;
                if(is_111[i_161] < 0 || is_110[0][i_161] < 10)
                    i_156++;
                if(is_111[i_161] > w || is_110[0][i_161] < 10)
                    i_157++;
            }

            if(i_156 == 12 || i_154 == 12 || i_155 == 12 || i_157 == 12)
                bool_116 = false;
            if(!bool_116)
                continue;
            i_159 /= 12;
            i_158 /= 12;
            i_160 /= 12;
            int i_162 = (int)Math.sqrt((cy - i_158) * (cy - i_158) + (cx - i_159) * (cx - i_159) + i_160 * i_160);
            if(i_162 >= fade[7])
                continue;
            int i_163 = clds[0];
            int i_164 = clds[1];
            int i_165 = clds[2];
            for(int i_166 = 0; i_166 < 16; i_166++)
                if(i_162 > fade[i_166])
                {
                    i_163 = (i_163 * fogd + cfade[0]) / (fogd + 1);
                    i_164 = (i_164 * fogd + cfade[1]) / (fogd + 1);
                    i_165 = (i_165 * fogd + cfade[2]) / (fogd + 1);
                }

            graphics2d.setColor(new Color(i_163, i_164, i_165));
            graphics2d.fillPolygon(is_111, is_112, 12);
        }

    }

    public void newmountains(int i, int i_167, int i_168, int i_169)
    {
        Random random = new Random(mgen);
        nmt = (int)(20D + 10D * random.nextDouble());
        int i_170 = (i + i_167) / 60;
        int i_171 = (i_168 + i_169) / 60;
        int i_172 = Math.max(i_167 - i, i_169 - i_168) / 60;
        mrd = null;
        nmv = null;
        mtx = (int[][])null;
        mty = (int[][])null;
        mtz = (int[][])null;
        mtc = (int[][][])null;
        mrd = new int[nmt];
        nmv = new int[nmt];
        mtx = new int[nmt][];
        mty = new int[nmt][];
        mtz = new int[nmt][];
        mtc = new int[nmt][][];
        int is[] = new int[nmt];
        int is_173[] = new int[nmt];
        for(int i_174 = 0; i_174 < nmt; i_174++)
        {
            int i_175 = 85;
            float f = 0.5F;
            float f_176 = 0.5F;
            is[i_174] = (int)(10000D + random.nextDouble() * 10000D);
            int i_177 = (int)(random.nextDouble() * 360D);
            if(random.nextDouble() > random.nextDouble())
            {
                f = (float)(0.20000000000000001D + random.nextDouble() * 0.34999999999999998D);
                f_176 = (float)(0.20000000000000001D + random.nextDouble() * 0.34999999999999998D);
                nmv[i_174] = (int)((double)f * (24D + 16D * random.nextDouble()));
                i_175 = (int)(85D + 10D * random.nextDouble());
            } else
            {
                f = (float)(0.29999999999999999D + random.nextDouble() * 1.1000000000000001D);
                f_176 = (float)(0.20000000000000001D + random.nextDouble() * 0.34999999999999998D);
                nmv[i_174] = (int)((double)f * (12D + 8D * random.nextDouble()));
                i_175 = (int)(104D - 10D * random.nextDouble());
            }
            mtx[i_174] = new int[nmv[i_174] * 2];
            mty[i_174] = new int[nmv[i_174] * 2];
            mtz[i_174] = new int[nmv[i_174] * 2];
            mtc[i_174] = new int[nmv[i_174]][3];
            for(int i_178 = 0; i_178 < nmv[i_174]; i_178++)
            {
                mtx[i_174][i_178] = (int)((((double)(i_178 * 500) + (random.nextDouble() * 800D - 400D)) - (double)(250 * (nmv[i_174] - 1))) * (double)f);
                mtx[i_174][i_178 + nmv[i_174]] = (int)((((double)(i_178 * 500) + (random.nextDouble() * 800D - 400D)) - (double)(250 * (nmv[i_174] - 1))) * (double)f);
                mtx[i_174][nmv[i_174]] = (int)((double)mtx[i_174][0] - (100D + random.nextDouble() * 600D) * (double)f);
                mtx[i_174][nmv[i_174] * 2 - 1] = (int)((double)mtx[i_174][nmv[i_174] - 1] + (100D + random.nextDouble() * 600D) * (double)f);
                if(i_178 == 0 || i_178 == nmv[i_174] - 1)
                    mty[i_174][i_178] = (int)((-400D - 1200D * random.nextDouble()) * (double)f_176 + (double)ground);
                if(i_178 == 1 || i_178 == nmv[i_174] - 2)
                    mty[i_174][i_178] = (int)((-1000D - 1450D * random.nextDouble()) * (double)f_176 + (double)ground);
                if(i_178 > 1 && i_178 < nmv[i_174] - 2)
                    mty[i_174][i_178] = (int)((-1600D - 1700D * random.nextDouble()) * (double)f_176 + (double)ground);
                mty[i_174][i_178 + nmv[i_174]] = ground - 70;
                mtz[i_174][i_178] = i_171 + i_172 + is[i_174];
                mtz[i_174][i_178 + nmv[i_174]] = i_171 + i_172 + is[i_174];
                float f_179 = (float)(0.5D + random.nextDouble() * 0.5D);
                mtc[i_174][i_178][0] = (int)(170F * f_179 + 170F * f_179 * ((float)snap[0] / 100F));
                if(mtc[i_174][i_178][0] > 255)
                    mtc[i_174][i_178][0] = 255;
                if(mtc[i_174][i_178][0] < 0)
                    mtc[i_174][i_178][0] = 0;
                mtc[i_174][i_178][1] = (int)((float)i_175 * f_179 + 85F * f_179 * ((float)snap[1] / 100F));
                if(mtc[i_174][i_178][1] > 255)
                    mtc[i_174][i_178][1] = 255;
                if(mtc[i_174][i_178][1] < 1)
                    mtc[i_174][i_178][1] = 0;
                mtc[i_174][i_178][2] = 0;
            }

            for(int i_180 = 1; i_180 < nmv[i_174] - 1; i_180++)
            {
                int i_181 = i_180 - 1;
                int i_182 = i_180 + 1;
                mty[i_174][i_180] = ((mty[i_174][i_181] + mty[i_174][i_182]) / 2 + mty[i_174][i_180]) / 2;
            }

            rot(mtx[i_174], mtz[i_174], i_170, i_171, i_177, nmv[i_174] * 2);
            is_173[i_174] = 0;
        }

        for(int i_183 = 0; i_183 < nmt; i_183++)
        {
            for(int i_184 = i_183 + 1; i_184 < nmt; i_184++)
                if(is[i_183] < is[i_184])
                    is_173[i_183]++;
                else
                    is_173[i_184]++;

            mrd[is_173[i_183]] = i_183;
        }

    }

    public void drawmountains(Graphics2D graphics2d)
    {
        for(int i = 0; i < nmt; i++)
        {
            int i_185 = mrd[i];
            int i_186 = cx + (int)((float)(mtx[i_185][0] - x / 30 - cx) * cos(xz) - (float)(mtz[i_185][0] - z / 30 - cz) * sin(xz));
            int i_187 = cz + (int)((float)(mtx[i_185][0] - x / 30 - cx) * sin(xz) + (float)(mtz[i_185][0] - z / 30 - cz) * cos(xz));
            int i_188 = cz + (int)((float)(mty[i_185][0] - y / 30 - cy) * sin(zy) + (float)(i_187 - cz) * cos(zy));
            int i_189 = cx + (int)((float)(mtx[i_185][nmv[i_185] - 1] - x / 30 - cx) * cos(xz) - (float)(mtz[i_185][nmv[i_185] - 1] - z / 30 - cz) * sin(xz));
            int i_190 = cz + (int)((float)(mtx[i_185][nmv[i_185] - 1] - x / 30 - cx) * sin(xz) + (float)(mtz[i_185][nmv[i_185] - 1] - z / 30 - cz) * cos(xz));
            int i_191 = cz + (int)((float)(mty[i_185][nmv[i_185] - 1] - y / 30 - cy) * sin(zy) + (float)(i_190 - cz) * cos(zy));
            if(xs(i_189, i_191) <= 0 || xs(i_186, i_188) >= w)
                continue;
            int is[] = new int[nmv[i_185] * 2];
            int is_192[] = new int[nmv[i_185] * 2];
            int is_193[] = new int[nmv[i_185] * 2];
            for(int i_194 = 0; i_194 < nmv[i_185] * 2; i_194++)
            {
                is[i_194] = mtx[i_185][i_194] - x / 30;
                is_192[i_194] = mty[i_185][i_194] - y / 30;
                is_193[i_194] = mtz[i_185][i_194] - z / 30;
            }

            int i_195 = (int)Math.sqrt(is[nmv[i_185] / 4] * is[nmv[i_185] / 4] + is_193[nmv[i_185] / 4] * is_193[nmv[i_185] / 4]);
            rot(is, is_193, cx, cz, xz, nmv[i_185] * 2);
            rot(is_192, is_193, cy, cz, zy, nmv[i_185] * 2);
            int is_196[] = new int[4];
            int is_197[] = new int[4];
            boolean bool = false;
            boolean bool_198 = false;
            boolean bool_199 = false;
            boolean bool_200 = false;
            boolean bool_201 = true;
            for(int i_202 = 0; i_202 < nmv[i_185] - 1; i_202++)
            {
                int i_203 = 0;
                int i_204 = 0;
                int i_205 = 0;
                int i_206 = 0;
                bool_201 = true;
                for(int i_207 = 0; i_207 < 4; i_207++)
                {
                    int i_208 = i_207 + i_202;
                    if(i_207 == 2)
                        i_208 = i_202 + nmv[i_185] + 1;
                    if(i_207 == 3)
                        i_208 = i_202 + nmv[i_185];
                    is_196[i_207] = xs(is[i_208], is_193[i_208]);
                    is_197[i_207] = ys(is_192[i_208], is_193[i_208]);
                    if(is_197[i_207] < 0 || is_193[i_208] < 10)
                        i_203++;
                    if(is_197[i_207] > h || is_193[i_208] < 10)
                        i_204++;
                    if(is_196[i_207] < 0 || is_193[i_208] < 10)
                        i_205++;
                    if(is_196[i_207] > w || is_193[i_208] < 10)
                        i_206++;
                }

                if(i_205 == 4 || i_203 == 4 || i_204 == 4 || i_206 == 4)
                    bool_201 = false;
                if(!bool_201)
                    continue;
                float f = ((float)i_195 / 2500F + (8000F - (float)fade[0]) / 1000F) - 2.0F - ((float)Math.abs(y) - 250F) / 5000F;
                if(f <= 0.0F || f >= 10F)
                    continue;
                if((double)f < 3.5D)
                    f = 3.5F;
                int i_209 = (int)(((float)(mtc[i_185][i_202][0] + cgrnd[0]) + (float)csky[0] * f + (float)cfade[0] * f) / (2.0F + f * 2.0F));
                int i_210 = (int)(((float)(mtc[i_185][i_202][1] + cgrnd[1]) + (float)csky[1] * f + (float)cfade[1] * f) / (2.0F + f * 2.0F));
                int i_211 = (int)(((float)(mtc[i_185][i_202][2] + cgrnd[2]) + (float)csky[2] * f + (float)cfade[2] * f) / (2.0F + f * 2.0F));
                graphics2d.setColor(new Color(i_209, i_210, i_211));
                graphics2d.fillPolygon(is_196, is_197, 4);
            }

        }

    }

    public void newstars()
    {
        stx = null;
        stz = null;
        stc = (int[][][])null;
        bst = null;
        twn = null;
        nst = 0;
        if(lightson)
        {
            Random random = new Random((long)(Math.random() * 100000D));
            nst = 40;
            stx = new int[nst];
            stz = new int[nst];
            stc = new int[nst][2][3];
            bst = new boolean[nst];
            twn = new int[nst];
            for(int i = 0; i < nst; i++)
            {
                stx[i] = (int)(2000D * random.nextDouble() - 1000D);
                stz[i] = (int)(2000D * random.nextDouble() - 1000D);
                int i_212 = (int)(3D * random.nextDouble());
                if(i_212 >= 3)
                    i_212 = 0;
                if(i_212 <= -1)
                    i_212 = 2;
                int i_213 = i_212 + 1;
                if(random.nextDouble() > random.nextDouble())
                    i_213 = i_212 - 1;
                if(i_213 == 3)
                    i_213 = 0;
                if(i_213 == -1)
                    i_213 = 2;
                for(int i_214 = 0; i_214 < 3; i_214++)
                {
                    stc[i][0][i_214] = 200;
                    if(i_212 == i_214)
                        stc[i][0][i_214] += (int)(55D * random.nextDouble());
                    if(i_213 == i_214)
                        stc[i][0][i_214] += 55;
                    stc[i][0][i_214] = (stc[i][0][i_214] * 2 + csky[i_214]) / 3;
                    stc[i][1][i_214] = (stc[i][0][i_214] + csky[i_214]) / 2;
                }

                twn[i] = (int)(4D * random.nextDouble());
                if(random.nextDouble() > 0.80000000000000004D)
                    bst[i] = true;
                else
                    bst[i] = false;
            }

        }
    }

    public void drawstars(Graphics2D graphics2d)
    {
        for(int i = 0; i < nst; i++)
        {
            int i_215 = cx + (int)((float)stx[i] * cos(xz) - (float)stz[i] * sin(xz));
            int i_216 = cz + (int)((float)stx[i] * sin(xz) + (float)stz[i] * cos(xz));
            int i_217 = cy + (int)(-200F * cos(zy) - (float)i_216 * sin(zy));
            int i_218 = cz + (int)(-200F * sin(zy) + (float)i_216 * cos(zy));
            i_215 = xs(i_215, i_218);
            i_217 = ys(i_217, i_218);
            if(i_215 - 1 <= iw || i_215 + 3 >= w || i_217 - 1 <= ih || i_217 + 3 >= h)
                continue;
            if(twn[i] == 0)
            {
                int i_219 = (int)(3D * Math.random());
                if(i_219 >= 3)
                    i_219 = 0;
                if(i_219 <= -1)
                    i_219 = 2;
                int i_220 = i_219 + 1;
                if(Math.random() > Math.random())
                    i_220 = i_219 - 1;
                if(i_220 == 3)
                    i_220 = 0;
                if(i_220 == -1)
                    i_220 = 2;
                for(int i_221 = 0; i_221 < 3; i_221++)
                {
                    stc[i][0][i_221] = 200;
                    if(i_219 == i_221)
                        stc[i][0][i_221] += (int)(55D * Math.random());
                    if(i_220 == i_221)
                        stc[i][0][i_221] += 55;
                    stc[i][0][i_221] = (stc[i][0][i_221] * 2 + csky[i_221]) / 3;
                    stc[i][1][i_221] = (stc[i][0][i_221] + csky[i_221]) / 2;
                }

                twn[i] = 3;
            } else
            {
                twn[i]--;
            }
            int i_222 = 0;
            if(bst[i])
                i_222 = 1;
            graphics2d.setColor(new Color(stc[i][1][0], stc[i][1][1], stc[i][1][2]));
            graphics2d.fillRect(i_215 - 1, i_217, 3 + i_222, 1 + i_222);
            graphics2d.fillRect(i_215, i_217 - 1, 1 + i_222, 3 + i_222);
            graphics2d.setColor(new Color(stc[i][0][0], stc[i][0][1], stc[i][0][2]));
            graphics2d.fillRect(i_215, i_217, 1 + i_222, 1 + i_222);
        }

    }

    public void d(Graphics2D graphics2d)
    {
        nsp = 0;
        if(zy > 90)
            zy = 90;
        if(zy < -90)
            zy = -90;
        if(xz > 360)
            xz -= 360;
        if(xz < 0)
            xz += 360;
        if(y > 0)
            y = 0;
        ground = 250 - y;
        int is[] = new int[4];
        int is_223[] = new int[4];
        int i = cgrnd[0];
        int i_224 = cgrnd[1];
        int i_225 = cgrnd[2];
        int i_226 = crgrnd[0];
        int i_227 = crgrnd[1];
        int i_228 = crgrnd[2];
        int i_229 = h;
        for(int i_230 = 0; i_230 < 16; i_230++)
        {
            int i_231 = fade[i_230];
            int i_232 = ground;
            if(zy != 0)
            {
                i_232 = cy + (int)((float)(ground - cy) * cos(zy) - (float)(fade[i_230] - cz) * sin(zy));
                i_231 = cz + (int)((float)(ground - cy) * sin(zy) + (float)(fade[i_230] - cz) * cos(zy));
            }
            is[0] = iw;
            is_223[0] = ys(i_232, i_231);
            if(is_223[0] < ih)
                is_223[0] = ih;
            if(is_223[0] > h)
                is_223[0] = h;
            is[1] = iw;
            is_223[1] = i_229;
            is[2] = w;
            is_223[2] = i_229;
            is[3] = w;
            is_223[3] = is_223[0];
            i_229 = is_223[0];
            if(i_230 > 0)
            {
                i_226 = (i_226 * 7 + cfade[0]) / 8;
                i_227 = (i_227 * 7 + cfade[1]) / 8;
                i_228 = (i_228 * 7 + cfade[2]) / 8;
                if(i_230 < 3)
                {
                    i = (i * 7 + cfade[0]) / 8;
                    i_224 = (i_224 * 7 + cfade[1]) / 8;
                    i_225 = (i_225 * 7 + cfade[2]) / 8;
                } else
                {
                    i = i_226;
                    i_224 = i_227;
                    i_225 = i_228;
                }
            }
            if(is_223[0] < h && is_223[1] > ih)
            {
                graphics2d.setColor(new Color(i, i_224, i_225));
                graphics2d.fillPolygon(is, is_223, 4);
            }
        }

        if(lightn != -1 && lton)
        {
            if(lightn < 16)
            {
                if(lilo > lightn + 217)
                    lilo -= 3;
                else
                    lightn = (int)(16F + 16F * random());
            } else
            if(lilo < lightn + 217)
                lilo += 7;
            else
                lightn = (int)(16F * random());
            csky[0] = (int)((float)lilo + (float)lilo * ((float)snap[0] / 100F));
            if(csky[0] > 255)
                csky[0] = 255;
            if(csky[0] < 0)
                csky[0] = 0;
            csky[1] = (int)((float)lilo + (float)lilo * ((float)snap[1] / 100F));
            if(csky[1] > 255)
                csky[1] = 255;
            if(csky[1] < 0)
                csky[1] = 0;
            csky[2] = (int)((float)lilo + (float)lilo * ((float)snap[2] / 100F));
            if(csky[2] > 255)
                csky[2] = 255;
            if(csky[2] < 0)
                csky[2] = 0;
        }
        i = csky[0];
        i_224 = csky[1];
        i_225 = csky[2];
        int i_233 = i;
        int i_234 = i_224;
        int i_235 = i_225;
        int i_236 = cy + (int)((float)(skyline - 700 - cy) * cos(zy) - (float)(7000 - cz) * sin(zy));
        int i_237 = cz + (int)((float)(skyline - 700 - cy) * sin(zy) + (float)(7000 - cz) * cos(zy));
        i_236 = ys(i_236, i_237);
        int i_238 = ih;
        for(int i_239 = 0; i_239 < 16; i_239++)
        {
            int i_240 = fade[i_239];
            int i_241 = skyline;
            if(zy != 0)
            {
                i_241 = cy + (int)((float)(skyline - cy) * cos(zy) - (float)(fade[i_239] - cz) * sin(zy));
                i_240 = cz + (int)((float)(skyline - cy) * sin(zy) + (float)(fade[i_239] - cz) * cos(zy));
            }
            is[0] = iw;
            is_223[0] = ys(i_241, i_240);
            if(is_223[0] > h)
                is_223[0] = h;
            if(is_223[0] < ih)
                is_223[0] = ih;
            is[1] = iw;
            is_223[1] = i_238;
            is[2] = w;
            is_223[2] = i_238;
            is[3] = w;
            is_223[3] = is_223[0];
            i_238 = is_223[0];
            if(i_239 > 0)
            {
                i = (i * 7 + cfade[0]) / 8;
                i_224 = (i_224 * 7 + cfade[1]) / 8;
                i_225 = (i_225 * 7 + cfade[2]) / 8;
            }
            if(is_223[1] < i_236)
            {
                i_233 = i;
                i_234 = i_224;
                i_235 = i_225;
            }
            if(is_223[0] > ih && is_223[1] < h)
            {
                graphics2d.setColor(new Color(i, i_224, i_225));
                graphics2d.fillPolygon(is, is_223, 4);
            }
        }

        is[0] = iw;
        is_223[0] = i_238;
        is[1] = iw;
        is_223[1] = i_229;
        is[2] = w;
        is_223[2] = i_229;
        is[3] = w;
        is_223[3] = i_238;
        if(is_223[0] < h && is_223[1] > ih)
        {
            float f = ((float)Math.abs(y) - 250F) / (float)(fade[0] * 2);
            if(f < 0.0F)
                f = 0.0F;
            if(f > 1.0F)
                f = 1.0F;
            i = (int)(((float)i * (1.0F - f) + (float)i_226 * (1.0F + f)) / 2.0F);
            i_224 = (int)(((float)i_224 * (1.0F - f) + (float)i_227 * (1.0F + f)) / 2.0F);
            i_225 = (int)(((float)i_225 * (1.0F - f) + (float)i_228 * (1.0F + f)) / 2.0F);
            graphics2d.setColor(new Color(i, i_224, i_225));
            graphics2d.fillPolygon(is, is_223, 4);
        }
        if(resdown != 2)
        {
            for(int i_242 = 1; i_242 < 20; i_242++)
            {
                int i_243 = 7000;
                int i_244 = skyline - 700 - i_242 * 70;
                if(zy != 0 && i_242 != 19)
                {
                    i_244 = cy + (int)((float)(skyline - 700 - i_242 * 70 - cy) * cos(zy) - (float)(7000 - cz) * sin(zy));
                    i_243 = cz + (int)((float)(skyline - 700 - i_242 * 70 - cy) * sin(zy) + (float)(7000 - cz) * cos(zy));
                }
                is[0] = iw;
                if(i_242 != 19)
                {
                    is_223[0] = ys(i_244, i_243);
                    if(is_223[0] > h)
                        is_223[0] = h;
                    if(is_223[0] < ih)
                        is_223[0] = ih;
                } else
                {
                    is_223[0] = ih;
                }
                is[1] = iw;
                is_223[1] = i_236;
                is[2] = w;
                is_223[2] = i_236;
                is[3] = w;
                is_223[3] = is_223[0];
                i_236 = is_223[0];
                i_233 = (int)((double)i_233 * 0.99099999999999999D);
                i_234 = (int)((double)i_234 * 0.99099999999999999D);
                i_235 = (int)((double)i_235 * 0.998D);
                if(is_223[1] > ih && is_223[0] < h)
                {
                    graphics2d.setColor(new Color(i_233, i_234, i_235));
                    graphics2d.fillPolygon(is, is_223, 4);
                }
            }
            drawstars(graphics2d);
            drawmountains(graphics2d);
            drawclouds(graphics2d);
        }
        groundpolys(graphics2d);
        if(noelec != 0)
            noelec--;
        if(cpflik)
        {
            cpflik = false;
        } else
        {
            cpflik = true;
            elecr = random() * 15F - 6F;
        }
    }

    public void addsp(int i, int i_245, int i_246)
    {
        if(nsp != 7)
        {
            spx[nsp] = i;
            spz[nsp] = i_245;
            sprad[nsp] = i_246;
            nsp++;
        }
    }

    public void setsnap(int i, int i_247, int i_248)
    {
        snap[0] = i;
        snap[1] = i_247;
        snap[2] = i_248;
    }

    public void setsky(int i, int i_249, int i_250)
    {
        osky[0] = i;
        osky[1] = i_249;
        osky[2] = i_250;
        for(int i_251 = 0; i_251 < 3; i_251++)
        {
            clds[i_251] = (osky[i_251] * cldd[3] + cldd[i_251]) / (cldd[3] + 1);
            clds[i_251] = (int)((float)clds[i_251] + (float)clds[i_251] * ((float)snap[i_251] / 100F));
            if(clds[i_251] > 255)
                clds[i_251] = 255;
            if(clds[i_251] < 0)
                clds[i_251] = 0;
        }

        csky[0] = (int)((float)i + (float)i * ((float)snap[0] / 100F));
        if(csky[0] > 255)
            csky[0] = 255;
        if(csky[0] < 0)
            csky[0] = 0;
        csky[1] = (int)((float)i_249 + (float)i_249 * ((float)snap[1] / 100F));
        if(csky[1] > 255)
            csky[1] = 255;
        if(csky[1] < 0)
            csky[1] = 0;
        csky[2] = (int)((float)i_250 + (float)i_250 * ((float)snap[2] / 100F));
        if(csky[2] > 255)
            csky[2] = 255;
        if(csky[2] < 0)
            csky[2] = 0;
        float fs[] = new float[3];
        Color.RGBtoHSB(csky[0], csky[1], csky[2], fs);
        if((double)fs[2] < 0.59999999999999998D)
            darksky = true;
        else
            darksky = false;
    }

    public void setcloads(int i, int i_252, int i_253, int i_254, int i_255)
    {
        if(i_254 < 0)
            i_254 = 0;
        if(i_254 > 10)
            i_254 = 10;
        if(i_255 < -1500)
            i_255 = -1500;
        if(i_255 > -500)
            i_255 = -500;
        cldd[0] = i;
        cldd[1] = i_252;
        cldd[2] = i_253;
        cldd[3] = i_254;
        cldd[4] = i_255;
        for(int i_256 = 0; i_256 < 3; i_256++)
        {
            clds[i_256] = (osky[i_256] * cldd[3] + cldd[i_256]) / (cldd[3] + 1);
            clds[i_256] = (int)((float)clds[i_256] + (float)clds[i_256] * ((float)snap[i_256] / 100F));
            if(clds[i_256] > 255)
                clds[i_256] = 255;
            if(clds[i_256] < 0)
                clds[i_256] = 0;
        }

    }

    public void setgrnd(int i, int i_257, int i_258)
    {
        ogrnd[0] = i;
        ogrnd[1] = i_257;
        ogrnd[2] = i_258;
        for(int i_259 = 0; i_259 < 3; i_259++)
        {
            cpol[i_259] = (ogrnd[i_259] * texture[3] + texture[i_259]) / (1 + texture[3]);
            cpol[i_259] = (int)((float)cpol[i_259] + (float)cpol[i_259] * ((float)snap[i_259] / 100F));
            if(cpol[i_259] > 255)
                cpol[i_259] = 255;
            if(cpol[i_259] < 0)
                cpol[i_259] = 0;
        }

        cgrnd[0] = (int)((float)i + (float)i * ((float)snap[0] / 100F));
        if(cgrnd[0] > 255)
            cgrnd[0] = 255;
        if(cgrnd[0] < 0)
            cgrnd[0] = 0;
        cgrnd[1] = (int)((float)i_257 + (float)i_257 * ((float)snap[1] / 100F));
        if(cgrnd[1] > 255)
            cgrnd[1] = 255;
        if(cgrnd[1] < 0)
            cgrnd[1] = 0;
        cgrnd[2] = (int)((float)i_258 + (float)i_258 * ((float)snap[2] / 100F));
        if(cgrnd[2] > 255)
            cgrnd[2] = 255;
        if(cgrnd[2] < 0)
            cgrnd[2] = 0;
        for(int i_260 = 0; i_260 < 3; i_260++)
            crgrnd[i_260] = (int)(((double)cpol[i_260] * 0.98999999999999999D + (double)cgrnd[i_260]) / 2D);

    }

    public void setexture(int i, int i_261, int i_262, int i_263)
    {
        if(i_263 < 20)
            i_263 = 20;
        if(i_263 > 60)
            i_263 = 60;
        texture[0] = i;
        texture[1] = i_261;
        texture[2] = i_262;
        texture[3] = i_263;
        i = (ogrnd[0] * i_263 + i) / (1 + i_263);
        i_261 = (ogrnd[1] * i_263 + i_261) / (1 + i_263);
        i_262 = (ogrnd[2] * i_263 + i_262) / (1 + i_263);
        cpol[0] = (int)((float)i + (float)i * ((float)snap[0] / 100F));
        if(cpol[0] > 255)
            cpol[0] = 255;
        if(cpol[0] < 0)
            cpol[0] = 0;
        cpol[1] = (int)((float)i_261 + (float)i_261 * ((float)snap[1] / 100F));
        if(cpol[1] > 255)
            cpol[1] = 255;
        if(cpol[1] < 0)
            cpol[1] = 0;
        cpol[2] = (int)((float)i_262 + (float)i_262 * ((float)snap[2] / 100F));
        if(cpol[2] > 255)
            cpol[2] = 255;
        if(cpol[2] < 0)
            cpol[2] = 0;
        for(int i_264 = 0; i_264 < 3; i_264++)
            crgrnd[i_264] = (int)(((double)cpol[i_264] * 0.98999999999999999D + (double)cgrnd[i_264]) / 2D);

    }

    public void setpolys(int i, int i_265, int i_266)
    {
        cpol[0] = (int)((float)i + (float)i * ((float)snap[0] / 100F));
        if(cpol[0] > 255)
            cpol[0] = 255;
        if(cpol[0] < 0)
            cpol[0] = 0;
        cpol[1] = (int)((float)i_265 + (float)i_265 * ((float)snap[1] / 100F));
        if(cpol[1] > 255)
            cpol[1] = 255;
        if(cpol[1] < 0)
            cpol[1] = 0;
        cpol[2] = (int)((float)i_266 + (float)i_266 * ((float)snap[2] / 100F));
        if(cpol[2] > 255)
            cpol[2] = 255;
        if(cpol[2] < 0)
            cpol[2] = 0;
        for(int i_267 = 0; i_267 < 3; i_267++)
            crgrnd[i_267] = (int)(((double)cpol[i_267] * 0.98999999999999999D + (double)cgrnd[i_267]) / 2D);

    }

    public void setfade(int i, int i_268, int i_269)
    {
        cfade[0] = (int)((float)i + (float)i * ((float)snap[0] / 100F));
        if(cfade[0] > 255)
            cfade[0] = 255;
        if(cfade[0] < 0)
            cfade[0] = 0;
        cfade[1] = (int)((float)i_268 + (float)i_268 * ((float)snap[1] / 100F));
        if(cfade[1] > 255)
            cfade[1] = 255;
        if(cfade[1] < 0)
            cfade[1] = 0;
        cfade[2] = (int)((float)i_269 + (float)i_269 * ((float)snap[2] / 100F));
        if(cfade[2] > 255)
            cfade[2] = 255;
        if(cfade[2] < 0)
            cfade[2] = 0;
    }

    public void fadfrom(int i)
    {
        if(i > 8000)
            i = 8000;
        if(!fadset)
        {
            ofade = i;
            fadset = true;
        }
        i = 8000;
        for(int i_270 = 1; i_270 < 17; i_270++)
            fade[i_270 - 1] = (i / 2) * (i_270 + 1);

    }

    public void adjstfade(float f, float f_271, int i, GameSparker gamesparker)
    {
        fade[0] = ofade;
        fadfrom(fade[0]);
    }

    public int xs(int i, int i_272)
    {
        if(i_272 < cz)
            i_272 = cz;
        return ((i_272 - focus_point) * (cx - i)) / i_272 + i;
    }

    public int ys(int i, int i_273)
    {
        if(i_273 < 10)
            i_273 = 10;
        return ((i_273 - focus_point) * (cy - i)) / i_273 + i;
    }

    public float cos(int i)
    {
        for(; i >= 360; i -= 360);
        for(; i < 0; i += 360);
        return tcos[i];
    }

    public float sin(int i)
    {
        for(; i >= 360; i -= 360);
        for(; i < 0; i += 360);
        return tsin[i];
    }

    public void rot(int is[], int is_274[], int i, int i_275, int i_276, int i_277)
    {
        if(i_276 != 0)
        {
            for(int i_278 = 0; i_278 < i_277; i_278++)
            {
                int i_279 = is[i_278];
                int i_280 = is_274[i_278];
                is[i_278] = i + (int)((float)(i_279 - i) * cos(i_276) - (float)(i_280 - i_275) * sin(i_276));
                is_274[i_278] = i_275 + (int)((float)(i_279 - i) * sin(i_276) + (float)(i_280 - i_275) * cos(i_276));
            }

        }
    }

    int focus_point;
    int ground;
    int skyline;
    int fade[] = {
        3000, 4500, 6000, 7500, 9000, 10500, 12000, 13500, 15000, 16500, 
        18000, 19500, 21000, 22500, 24000, 25500
    };
    int cldd[] = {
        210, 210, 210, 1, -1000
    };
    int clds[] = {
        210, 210, 210
    };
    int osky[] = {
        170, 220, 255
    };
    int csky[] = {
        170, 220, 255
    };
    int ogrnd[] = {
        205, 200, 200
    };
    int cgrnd[] = {
        205, 200, 200
    };
    int texture[] = {
        0, 0, 0, 50
    };
    int cpol[] = {
        215, 210, 210
    };
    int crgrnd[] = {
        205, 200, 200
    };
    int cfade[] = {
        255, 220, 220
    };
    int snap[] = {
        0, 0, 0
    };
    int fogd;
    int mgen;
    boolean loadnew;
    boolean lightson;
    boolean darksky;
    int lightn;
    int lilo;
    boolean lton;
    int noelec;
    int trk;
    boolean crs;
    int cx;
    int cy;
    int cz;
    int xz;
    int zy;
    int x;
    int y;
    int z;
    int iw;
    int ih;
    int w;
    int h;
    int nsp;
    int spx[];
    int spz[];
    int sprad[];
    boolean td;
    int bcxz;
    boolean bt;
    int vxz;
    int adv;
    boolean vert;
    float tcos[];
    float tsin[];
    int lastmaf;
    int checkpoint;
    boolean lastcheck;
    float elecr;
    boolean cpflik;
    boolean nochekflk;
    int cntrn;
    boolean diup[] = {
        false, false, false
    };
    int rand[] = {
        0, 0, 0
    };
    int trn;
    int hit;
    int ptr;
    int ptcnt;
    int nrnd;
    long trx;
    long trz;
    long atrx;
    long atrz;
    int fallen;
    float fo;
    float gofo;
    int fvect;
    int ogpx[][];
    int ogpz[][];
    float pvr[][];
    int cgpx[];
    int cgpz[];
    int pmx[];
    float pcv[];
    int sgpx;
    int sgpz;
    int nrw;
    int ncl;
    int noc;
    int clx[];
    int clz[];
    int cmx[];
    int clax[][][];
    int clay[][][];
    int claz[][][];
    int clc[][][][];
    int nmt;
    int mrd[];
    int nmv[];
    int mtx[][];
    int mty[][];
    int mtz[][];
    int mtc[][][];
    int nst;
    int stx[];
    int stz[];
    int stc[][][];
    boolean bst[];
    int twn[];
    int resdown;
    int rescnt;
    int ofade;
    int madfade;
    boolean fadset;
}
