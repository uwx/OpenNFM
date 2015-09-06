// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Record.java

import java.awt.Color;

public class Record
{

    public Record(Medium medium)
    {
        caught = 0;
        hcaught = false;
        prepit = true;
        ocar = new ContO[8];
        cntf = 50;
        car = new ContO[6][8];
        squash = new int[6][8];
        fix = new int[8];
        dest = new int[8];
        x = new int[300][8];
        y = new int[300][8];
        z = new int[300][8];
        xy = new int[300][8];
        zy = new int[300][8];
        xz = new int[300][8];
        wxz = new int[300][8];
        wzy = new int[300][8];
        ns = new int[8][20];
        sspark = new int[8][20][30];
        sx = new int[8][20][30];
        sy = new int[8][20][30];
        sz = new int[8][20][30];
        smag = new float[8][20][30];
        scx = new int[8][20][30];
        scz = new int[8][20][30];
        nr = new int[8];
        rspark = new int[8][200];
        sprk = new int[8][200];
        srx = new int[8][200];
        sry = new int[8][200];
        srz = new int[8][200];
        rcx = new float[8][200];
        rcy = new float[8][200];
        rcz = new float[8][200];
        nry = new int[8][4];
        ry = new int[8][4][7];
        magy = new int[8][4][7];
        mtouch = new boolean[8][7];
        nrx = new int[8][4];
        rx = new int[8][4][7];
        magx = new int[8][4][7];
        nrz = new int[8][4];
        rz = new int[8][4][7];
        magz = new int[8][4][7];
        checkpoint = new int[300];
        lastcheck = new boolean[300];
        wasted = 0;
        whenwasted = 0;
        powered = 0;
        closefinish = 0;
        starcar = new ContO[8];
        hx = new int[300][8];
        hy = new int[300][8];
        hz = new int[300][8];
        hxy = new int[300][8];
        hzy = new int[300][8];
        hxz = new int[300][8];
        hwxz = new int[300][8];
        hwzy = new int[300][8];
        hsspark = new int[8][20][30];
        hsx = new int[8][20][30];
        hsy = new int[8][20][30];
        hsz = new int[8][20][30];
        hsmag = new float[8][20][30];
        hscx = new int[8][20][30];
        hscz = new int[8][20][30];
        hrspark = new int[8][200];
        hsprk = new int[8][200];
        hsrx = new int[8][200];
        hsry = new int[8][200];
        hsrz = new int[8][200];
        hrcx = new float[8][200];
        hrcy = new float[8][200];
        hrcz = new float[8][200];
        hry = new int[8][4][7];
        hmagy = new int[8][4][7];
        hrx = new int[8][4][7];
        hmagx = new int[8][4][7];
        hrz = new int[8][4][7];
        hmagz = new int[8][4][7];
        hmtouch = new boolean[8][7];
        hcheckpoint = new int[300];
        hlastcheck = new boolean[300];
        cntdest = new int[8];
        lastfr = 0;
        m = medium;
        caught = 0;
        cotchinow(0);
    }

    public void reset(ContO contos[])
    {
        caught = 0;
        hcaught = false;
        wasted = 0;
        whenwasted = 0;
        closefinish = 0;
        powered = 0;
        for(int i = 0; i < 8; i++)
        {
            if(prepit)
                starcar[i] = new ContO(contos[i], 0, 0, 0, 0);
            fix[i] = -1;
            dest[i] = -1;
            cntdest[i] = 0;
        }

        for(int i = 0; i < 6; i++)
        {
            for(int i_0 = 0; i_0 < 8; i_0++)
            {
                car[i][i_0] = new ContO(contos[i_0], 0, 0, 0, 0);
                squash[i][i_0] = 0;
            }

        }

        for(int i = 0; i < 8; i++)
        {
            nr[i] = 0;
            for(int i_1 = 0; i_1 < 200; i_1++)
                rspark[i][i_1] = -1;

            for(int i_2 = 0; i_2 < 20; i_2++)
            {
                ns[i][i_2] = 0;
                for(int i_3 = 0; i_3 < 30; i_3++)
                    sspark[i][i_2][i_3] = -1;

            }

            for(int i_4 = 0; i_4 < 4; i_4++)
            {
                nry[i][i_4] = 0;
                nrx[i][i_4] = 0;
                nrz[i][i_4] = 0;
                for(int i_5 = 0; i_5 < 7; i_5++)
                {
                    ry[i][i_4][i_5] = -1;
                    rx[i][i_4][i_5] = -1;
                    rz[i][i_4][i_5] = -1;
                }

            }

        }

        prepit = false;
    }

    public void cotchinow(int i)
    {
        if(caught >= 300)
        {
            wasted = i;
            for(int i_6 = 0; i_6 < 8; i_6++)
            {
                starcar[i_6] = new ContO(car[0][i_6], 0, 0, 0, 0);
                hsquash[i_6] = squash[0][i_6];
                hfix[i_6] = fix[i_6];
                hdest[i_6] = dest[i_6];
            }

            for(int i_7 = 0; i_7 < 300; i_7++)
            {
                for(int i_8 = 0; i_8 < 8; i_8++)
                {
                    hx[i_7][i_8] = x[i_7][i_8];
                    hy[i_7][i_8] = y[i_7][i_8];
                    hz[i_7][i_8] = z[i_7][i_8];
                    hxy[i_7][i_8] = xy[i_7][i_8];
                    hzy[i_7][i_8] = zy[i_7][i_8];
                    hxz[i_7][i_8] = xz[i_7][i_8];
                    hwxz[i_7][i_8] = wxz[i_7][i_8];
                    hwzy[i_7][i_8] = wzy[i_7][i_8];
                }

                hcheckpoint[i_7] = checkpoint[i_7];
                hlastcheck[i_7] = lastcheck[i_7];
            }

            for(int i_9 = 0; i_9 < 8; i_9++)
            {
                for(int i_10 = 0; i_10 < 20; i_10++)
                {
                    for(int i_11 = 0; i_11 < 30; i_11++)
                    {
                        hsspark[i_9][i_10][i_11] = sspark[i_9][i_10][i_11];
                        hsx[i_9][i_10][i_11] = sx[i_9][i_10][i_11];
                        hsy[i_9][i_10][i_11] = sy[i_9][i_10][i_11];
                        hsz[i_9][i_10][i_11] = sz[i_9][i_10][i_11];
                        hsmag[i_9][i_10][i_11] = smag[i_9][i_10][i_11];
                        hscx[i_9][i_10][i_11] = scx[i_9][i_10][i_11];
                        hscz[i_9][i_10][i_11] = scz[i_9][i_10][i_11];
                    }

                }

                for(int i_12 = 0; i_12 < 200; i_12++)
                {
                    hrspark[i_9][i_12] = rspark[i_9][i_12];
                    hsprk[i_9][i_12] = sprk[i_9][i_12];
                    hsrx[i_9][i_12] = srx[i_9][i_12];
                    hsry[i_9][i_12] = sry[i_9][i_12];
                    hsrz[i_9][i_12] = srz[i_9][i_12];
                    hrcx[i_9][i_12] = rcx[i_9][i_12];
                    hrcy[i_9][i_12] = rcy[i_9][i_12];
                    hrcz[i_9][i_12] = rcz[i_9][i_12];
                }

            }

            for(int i_13 = 0; i_13 < 8; i_13++)
            {
                for(int i_14 = 0; i_14 < 4; i_14++)
                {
                    for(int i_15 = 0; i_15 < 7; i_15++)
                    {
                        hry[i_13][i_14][i_15] = ry[i_13][i_14][i_15];
                        hmagy[i_13][i_14][i_15] = magy[i_13][i_14][i_15];
                        hrx[i_13][i_14][i_15] = rx[i_13][i_14][i_15];
                        hmagx[i_13][i_14][i_15] = magx[i_13][i_14][i_15];
                        hrz[i_13][i_14][i_15] = rz[i_13][i_14][i_15];
                        hmagz[i_13][i_14][i_15] = magz[i_13][i_14][i_15];
                    }

                }

            }

            for(int i_16 = 0; i_16 < 8; i_16++)
            {
                for(int i_17 = 0; i_17 < 7; i_17++)
                    hmtouch[i_16][i_17] = mtouch[i_16][i_17];

            }

            hcaught = true;
        }
    }

    public void rec(ContO conto, int i, int i_18, int i_19, int i_20, int i_21)
    {
        if(i == i_21)
            caught++;
        if(cntf == 50)
        {
            for(int i_22 = 0; i_22 < 5; i_22++)
            {
                car[i_22][i] = new ContO(car[i_22 + 1][i], 0, 0, 0, 0);
                squash[i_22][i] = squash[i_22 + 1][i];
            }

            car[5][i] = new ContO(conto, 0, 0, 0, 0);
            squash[5][i] = i_18;
            cntf = 0;
        } else
        {
            cntf++;
        }
        fix[i]--;
        if(i_20 != 0)
            dest[i]--;
        if(dest[i] == 230)
            if(i == i_21)
            {
                cotchinow(i_21);
                whenwasted = 229;
            } else
            if(i_19 != 0)
            {
                cotchinow(i);
                whenwasted = 165 + i_19;
            }
        for(int i_23 = 0; i_23 < 299; i_23++)
        {
            x[i_23][i] = x[i_23 + 1][i];
            y[i_23][i] = y[i_23 + 1][i];
            z[i_23][i] = z[i_23 + 1][i];
            zy[i_23][i] = zy[i_23 + 1][i];
            xy[i_23][i] = xy[i_23 + 1][i];
            xz[i_23][i] = xz[i_23 + 1][i];
            wxz[i_23][i] = wxz[i_23 + 1][i];
            wzy[i_23][i] = wzy[i_23 + 1][i];
        }

        x[299][i] = conto.x;
        y[299][i] = conto.y;
        z[299][i] = conto.z;
        xy[299][i] = conto.xy;
        zy[299][i] = conto.zy;
        xz[299][i] = conto.xz;
        wxz[299][i] = conto.wxz;
        wzy[299][i] = conto.wzy;
        if(i == i_21)
        {
            for(int i_24 = 0; i_24 < 299; i_24++)
            {
                checkpoint[i_24] = checkpoint[i_24 + 1];
                lastcheck[i_24] = lastcheck[i_24 + 1];
            }

            checkpoint[299] = conto.m.checkpoint;
            lastcheck[299] = conto.m.lastcheck;
        }
        for(int i_25 = 0; i_25 < 20; i_25++)
        {
            if(conto.stg[i_25] == 1)
            {
                sspark[i][i_25][ns[i][i_25]] = 300;
                sx[i][i_25][ns[i][i_25]] = conto.sx[i_25];
                sy[i][i_25][ns[i][i_25]] = conto.sy[i_25];
                sz[i][i_25][ns[i][i_25]] = conto.sz[i_25];
                smag[i][i_25][ns[i][i_25]] = conto.osmag[i_25];
                scx[i][i_25][ns[i][i_25]] = conto.scx[i_25];
                scz[i][i_25][ns[i][i_25]] = conto.scz[i_25];
                ns[i][i_25]++;
                if(ns[i][i_25] == 30)
                    ns[i][i_25] = 0;
            }
            for(int i_26 = 0; i_26 < 30; i_26++)
                sspark[i][i_25][i_26]--;

        }

        if(conto.sprk != 0)
        {
            rspark[i][nr[i]] = 300;
            sprk[i][nr[i]] = conto.sprk;
            srx[i][nr[i]] = conto.srx;
            sry[i][nr[i]] = conto.sry;
            srz[i][nr[i]] = conto.srz;
            rcx[i][nr[i]] = conto.rcx;
            rcy[i][nr[i]] = conto.rcy;
            rcz[i][nr[i]] = conto.rcz;
            nr[i]++;
            if(nr[i] == 200)
                nr[i] = 0;
        }
        for(int i_27 = 0; i_27 < 200; i_27++)
            rspark[i][i_27]--;

        for(int i_28 = 0; i_28 < 4; i_28++)
        {
            for(int i_29 = 0; i_29 < 7; i_29++)
            {
                ry[i][i_28][i_29]--;
                rx[i][i_28][i_29]--;
                rz[i][i_28][i_29]--;
            }

        }

    }

    public void play(ContO conto, Madness mad, int i, int i_30)
    {
        conto.x = x[i_30][i];
        conto.y = y[i_30][i];
        conto.z = z[i_30][i];
        conto.zy = zy[i_30][i];
        conto.xy = xy[i_30][i];
        conto.xz = xz[i_30][i];
        conto.wxz = wxz[i_30][i];
        conto.wzy = wzy[i_30][i];
        if(i == 0)
        {
            conto.m.checkpoint = checkpoint[i_30];
            conto.m.lastcheck = lastcheck[i_30];
        }
        if(i_30 == 0)
            cntdest[i] = 0;
        if(dest[i] == i_30)
            cntdest[i] = 7;
        if(i_30 == 0 && dest[i] < -1)
        {
            for(int i_31 = 0; i_31 < conto.npl; i_31++)
                if(conto.p[i_31].wz == 0 || conto.p[i_31].gr == -17 || conto.p[i_31].gr == -16)
                    conto.p[i_31].embos = 13;

        }
        if(cntdest[i] != 0)
        {
            for(int i_32 = 0; i_32 < conto.npl; i_32++)
                if(conto.p[i_32].wz == 0 || conto.p[i_32].gr == -17 || conto.p[i_32].gr == -16)
                    conto.p[i_32].embos = 1;

            cntdest[i]--;
        }
        for(int i_33 = 0; i_33 < 20; i_33++)
        {
            for(int i_34 = 0; i_34 < 30; i_34++)
                if(sspark[i][i_33][i_34] == i_30)
                {
                    conto.stg[i_33] = 1;
                    conto.sx[i_33] = sx[i][i_33][i_34];
                    conto.sy[i_33] = sy[i][i_33][i_34];
                    conto.sz[i_33] = sz[i][i_33][i_34];
                    conto.osmag[i_33] = smag[i][i_33][i_34];
                    conto.scx[i_33] = scx[i][i_33][i_34];
                    conto.scz[i_33] = scz[i][i_33][i_34];
                }

        }

        for(int i_35 = 0; i_35 < 200; i_35++)
            if(rspark[i][i_35] == i_30)
            {
                conto.sprk = sprk[i][i_35];
                conto.srx = srx[i][i_35];
                conto.sry = sry[i][i_35];
                conto.srz = srz[i][i_35];
                conto.rcx = rcx[i][i_35];
                conto.rcy = rcy[i][i_35];
                conto.rcz = rcz[i][i_35];
            }

        for(int i_36 = 0; i_36 < 4; i_36++)
        {
            for(int i_37 = 0; i_37 < 7; i_37++)
            {
                if(ry[i][i_36][i_37] == i_30)
                    regy(i_36, magy[i][i_36][i_37], mtouch[i][i_37], conto, mad);
                if(rx[i][i_36][i_37] == i_30)
                    regx(i_36, magx[i][i_36][i_37], conto, mad);
                if(rz[i][i_36][i_37] == i_30)
                    regz(i_36, magz[i][i_36][i_37], conto, mad);
            }

        }

    }

    public void playh(ContO conto, Madness mad, int i, int i_38, int i_39)
    {
        conto.x = hx[i_38][i];
        conto.y = hy[i_38][i];
        conto.z = hz[i_38][i];
        conto.zy = hzy[i_38][i];
        conto.xy = hxy[i_38][i];
        conto.xz = hxz[i_38][i];
        conto.wxz = hwxz[i_38][i];
        conto.wzy = hwzy[i_38][i];
        if(i == i_39)
        {
            conto.m.checkpoint = hcheckpoint[i_38];
            conto.m.lastcheck = hlastcheck[i_38];
        }
        if(i_38 == 0)
            cntdest[i] = 0;
        if(hdest[i] == i_38)
            cntdest[i] = 7;
        if(i_38 == 0 && hdest[i] < -1)
        {
            for(int i_40 = 0; i_40 < conto.npl; i_40++)
                if(conto.p[i_40].wz == 0 || conto.p[i_40].gr == -17 || conto.p[i_40].gr == -16)
                    conto.p[i_40].embos = 13;

        }
        if(cntdest[i] != 0)
        {
            for(int i_41 = 0; i_41 < conto.npl; i_41++)
                if(conto.p[i_41].wz == 0 || conto.p[i_41].gr == -17 || conto.p[i_41].gr == -16)
                    conto.p[i_41].embos = 1;

            cntdest[i]--;
        }
        for(int i_42 = 0; i_42 < 20; i_42++)
        {
            for(int i_43 = 0; i_43 < 30; i_43++)
                if(hsspark[i][i_42][i_43] == i_38)
                {
                    conto.stg[i_42] = 1;
                    conto.sx[i_42] = hsx[i][i_42][i_43];
                    conto.sy[i_42] = hsy[i][i_42][i_43];
                    conto.sz[i_42] = hsz[i][i_42][i_43];
                    conto.osmag[i_42] = hsmag[i][i_42][i_43];
                    conto.scx[i_42] = hscx[i][i_42][i_43];
                    conto.scz[i_42] = hscz[i][i_42][i_43];
                }

        }

        for(int i_44 = 0; i_44 < 200; i_44++)
            if(hrspark[i][i_44] == i_38)
            {
                conto.sprk = hsprk[i][i_44];
                conto.srx = hsrx[i][i_44];
                conto.sry = hsry[i][i_44];
                conto.srz = hsrz[i][i_44];
                conto.rcx = hrcx[i][i_44];
                conto.rcy = hrcy[i][i_44];
                conto.rcz = hrcz[i][i_44];
            }

        for(int i_45 = 0; i_45 < 4; i_45++)
        {
            for(int i_46 = 0; i_46 < 7; i_46++)
            {
                if(hry[i][i_45][i_46] == i_38 && lastfr != i_38)
                    regy(i_45, hmagy[i][i_45][i_46], hmtouch[i][i_46], conto, mad);
                if(hrx[i][i_45][i_46] == i_38)
                    if(lastfr != i_38)
                        regx(i_45, hmagx[i][i_45][i_46], conto, mad);
                    else
                        chipx(i_45, hmagx[i][i_45][i_46], conto, mad);
                if(hrz[i][i_45][i_46] != i_38)
                    continue;
                if(lastfr != i_38)
                    regz(i_45, hmagz[i][i_45][i_46], conto, mad);
                else
                    chipz(i_45, hmagz[i][i_45][i_46], conto, mad);
            }

        }

        lastfr = i_38;
    }

    public void recy(int i, float f, boolean bool, int i_47)
    {
        ry[i_47][i][nry[i_47][i]] = 300;
        magy[i_47][i][nry[i_47][i]] = (int)f;
        mtouch[i_47][nry[i_47][i]] = bool;
        nry[i_47][i]++;
        if(nry[i_47][i] == 7)
            nry[i_47][i] = 0;
    }

    public void recx(int i, float f, int i_48)
    {
        rx[i_48][i][nry[i_48][i]] = 300;
        magx[i_48][i][nry[i_48][i]] = (int)f;
        nrx[i_48][i]++;
        if(nrx[i_48][i] == 7)
            nrx[i_48][i] = 0;
    }

    public void recz(int i, float f, int i_49)
    {
        rz[i_49][i][nry[i_49][i]] = 300;
        magz[i_49][i][nry[i_49][i]] = (int)f;
        nrz[i_49][i]++;
        if(nrz[i_49][i] == 7)
            nrz[i_49][i] = 0;
    }

    public void regy(int i, float f, boolean bool, ContO conto, Madness mad)
    {
        if(f > 100F)
        {
            f -= 100F;
            int i_50 = 0;
            int i_51 = 0;
            int i_52 = conto.zy;
            int i_53 = conto.xy;
            for(; i_52 < 360; i_52 += 360);
            for(; i_52 > 360; i_52 -= 360);
            if(i_52 < 210 && i_52 > 150)
                i_50 = -1;
            if(i_52 > 330 || i_52 < 30)
                i_50 = 1;
            for(; i_53 < 360; i_53 += 360);
            for(; i_53 > 360; i_53 -= 360);
            if(i_53 < 210 && i_53 > 150)
                i_51 = -1;
            if(i_53 > 330 || i_53 < 30)
                i_51 = 1;
            if(i_51 * i_50 == 0 || bool)
            {
                for(int i_54 = 0; i_54 < conto.npl; i_54++)
                {
                    float f_55 = 0.0F;
                    for(int i_56 = 0; i_56 < conto.p[i_54].n; i_56++)
                        //if(conto.p[i_54].wz == 0 && py(conto.keyx[i], conto.p[i_54].ox[i_56], conto.keyz[i], conto.p[i_54].oz[i_56]) < mad.cn.clrad[mad.cn])
                        //{
                        //    f_55 = (f / 20F) * m.random();
                        //    conto.p[i_54].oz[i_56] += f_55 * m.sin(i_52);
                        //    conto.p[i_54].ox[i_56] -= f_55 * m.sin(i_53);
                        //}

                    if(f_55 == 0.0F)
                        continue;
                    if(Math.abs(f_55) >= 1.0F)
                    {
                        conto.p[i_54].chip = 1;
                        conto.p[i_54].ctmag = f_55;
                    }
                    if(!conto.p[i_54].nocol && conto.p[i_54].glass != 1)
                    {
                        if(conto.p[i_54].bfase > 20 && (double)conto.p[i_54].hsb[1] > 0.20000000000000001D)
                            conto.p[i_54].hsb[1] = 0.2F;
                        if(conto.p[i_54].bfase > 30)
                        {
                            if((double)conto.p[i_54].hsb[2] < 0.5D)
                                conto.p[i_54].hsb[2] = 0.5F;
                            if((double)conto.p[i_54].hsb[1] > 0.10000000000000001D)
                                conto.p[i_54].hsb[1] = 0.1F;
                        }
                        if(conto.p[i_54].bfase > 40)
                            conto.p[i_54].hsb[1] = 0.05F;
                        if(conto.p[i_54].bfase > 50)
                        {
                            if((double)conto.p[i_54].hsb[2] > 0.80000000000000004D)
                                conto.p[i_54].hsb[2] = 0.8F;
                            conto.p[i_54].hsb[0] = 0.075F;
                            conto.p[i_54].hsb[1] = 0.05F;
                        }
                        if(conto.p[i_54].bfase > 60)
                            conto.p[i_54].hsb[0] = 0.05F;
                        conto.p[i_54].bfase += f_55;
                        new Color(conto.p[i_54].c[0], conto.p[i_54].c[1], conto.p[i_54].c[2]);
                        Color color = Color.getHSBColor(conto.p[i_54].hsb[0], conto.p[i_54].hsb[1], conto.p[i_54].hsb[2]);
                        conto.p[i_54].c[0] = color.getRed();
                        conto.p[i_54].c[1] = color.getGreen();
                        conto.p[i_54].c[2] = color.getBlue();
                    }
                    if(conto.p[i_54].glass == 1)
                        conto.p[i_54].gr += Math.abs((double)f_55 * 1.5D);
                }

            }
            if(i_51 * i_50 == -1)
            {
                int i_57 = 0;
                int i_58 = 1;
                for(int i_59 = 0; i_59 < conto.npl; i_59++)
                {
                    float f_60 = 0.0F;
                    for(int i_61 = 0; i_61 < conto.p[i_59].n; i_61++)
                    {
                        if(conto.p[i_59].wz != 0)
                            continue;
                        f_60 = (f / 15F) * m.random();
                        //if((Math.abs(conto.p[i_59].oy[i_61] - mad.cn.flipy[mad.cn] - squash[0][mad.im]) < mad.cn.msquash[mad.cn] * 3 || conto.p[i_59].oy[i_61] < mad.cn.flipy[mad.cn] + squash[0][mad.im]) && squash[0][mad.im] < mad.cn.msquash[mad.cn])
                        //{
                        //    conto.p[i_59].oy[i_61] += f_60;
                        //    i_57 = (int)((float)i_57 + f_60);
                        //    i_58++;
                        //}
                    }

                    if(conto.p[i_59].glass == 1)
                        conto.p[i_59].gr += 5;
                    else
                    if(f_60 != 0.0F)
                        conto.p[i_59].bfase += f_60;
                    if(Math.abs(f_60) >= 1.0F)
                    {
                        conto.p[i_59].chip = 1;
                        conto.p[i_59].ctmag = f_60;
                    }
                }

                squash[0][mad.im] += i_57 / i_58;
            }
        }
    }

    public void regx(int i, float f, ContO conto, Madness mad)
    {
        if(Math.abs(f) > 100F)
        {
            if(f > 100F)
                f -= 100F;
            if(f < -100F)
                f += 100F;
            for(int i_62 = 0; i_62 < conto.npl; i_62++)
            {
                float f_63 = 0.0F;
                for(int i_64 = 0; i_64 < conto.p[i_62].n; i_64++)
                    /*if(conto.p[i_62].wz == 0 && py(conto.keyx[i], conto.p[i_62].ox[i_64], conto.keyz[i], conto.p[i_62].oz[i_64]) < mad.cn.clrad[mad.cn])
                    {
                        f_63 = (f / 20F) * m.random();
                        conto.p[i_62].oz[i_64] -= f_63 * m.sin(conto.xz) * m.cos(conto.zy);
                        conto.p[i_62].ox[i_64] += f_63 * m.cos(conto.xz) * m.cos(conto.xy);
                    }*/

                if(f_63 == 0.0F)
                    continue;
                if(Math.abs(f_63) >= 1.0F)
                {
                    conto.p[i_62].chip = 1;
                    conto.p[i_62].ctmag = f_63;
                }
                if(!conto.p[i_62].nocol && conto.p[i_62].glass != 1)
                {
                    if(conto.p[i_62].bfase > 20 && (double)conto.p[i_62].hsb[1] > 0.20000000000000001D)
                        conto.p[i_62].hsb[1] = 0.2F;
                    if(conto.p[i_62].bfase > 30)
                    {
                        if((double)conto.p[i_62].hsb[2] < 0.5D)
                            conto.p[i_62].hsb[2] = 0.5F;
                        if((double)conto.p[i_62].hsb[1] > 0.10000000000000001D)
                            conto.p[i_62].hsb[1] = 0.1F;
                    }
                    if(conto.p[i_62].bfase > 40)
                        conto.p[i_62].hsb[1] = 0.05F;
                    if(conto.p[i_62].bfase > 50)
                    {
                        if((double)conto.p[i_62].hsb[2] > 0.80000000000000004D)
                            conto.p[i_62].hsb[2] = 0.8F;
                        conto.p[i_62].hsb[0] = 0.075F;
                        conto.p[i_62].hsb[1] = 0.05F;
                    }
                    if(conto.p[i_62].bfase > 60)
                        conto.p[i_62].hsb[0] = 0.05F;
                    conto.p[i_62].bfase += Math.abs(f_63);
                    new Color(conto.p[i_62].c[0], conto.p[i_62].c[1], conto.p[i_62].c[2]);
                    Color color = Color.getHSBColor(conto.p[i_62].hsb[0], conto.p[i_62].hsb[1], conto.p[i_62].hsb[2]);
                    conto.p[i_62].c[0] = color.getRed();
                    conto.p[i_62].c[1] = color.getGreen();
                    conto.p[i_62].c[2] = color.getBlue();
                }
                if(conto.p[i_62].glass == 1)
                    conto.p[i_62].gr += Math.abs((double)f_63 * 1.5D);
            }

        }
    }

    public void regz(int i, float f, ContO conto, Madness mad)
    {
        if(Math.abs(f) > 100F)
        {
            if(f > 100F)
                f -= 100F;
            if(f < -100F)
                f += 100F;
            for(int i_65 = 0; i_65 < conto.npl; i_65++)
            {
                float f_66 = 0.0F;
                for(int i_67 = 0; i_67 < conto.p[i_65].n; i_67++)
                    /*if(conto.p[i_65].wz == 0 && py(conto.keyx[i], conto.p[i_65].ox[i_67], conto.keyz[i], conto.p[i_65].oz[i_67]) < mad.cn.clrad[mad.cn])
                    {
                        f_66 = (f / 20F) * m.random();
                        conto.p[i_65].oz[i_67] += f_66 * m.cos(conto.xz) * m.cos(conto.zy);
                        conto.p[i_65].ox[i_67] += f_66 * m.sin(conto.xz) * m.cos(conto.xy);
                    }*/

                if(f_66 == 0.0F)
                    continue;
                if(Math.abs(f_66) >= 1.0F)
                {
                    conto.p[i_65].chip = 1;
                    conto.p[i_65].ctmag = f_66;
                }
                if(!conto.p[i_65].nocol && conto.p[i_65].glass != 1)
                {
                    if(conto.p[i_65].bfase > 20 && (double)conto.p[i_65].hsb[1] > 0.20000000000000001D)
                        conto.p[i_65].hsb[1] = 0.2F;
                    if(conto.p[i_65].bfase > 30)
                    {
                        if((double)conto.p[i_65].hsb[2] < 0.5D)
                            conto.p[i_65].hsb[2] = 0.5F;
                        if((double)conto.p[i_65].hsb[1] > 0.10000000000000001D)
                            conto.p[i_65].hsb[1] = 0.1F;
                    }
                    if(conto.p[i_65].bfase > 40)
                        conto.p[i_65].hsb[1] = 0.05F;
                    if(conto.p[i_65].bfase > 50)
                    {
                        if((double)conto.p[i_65].hsb[2] > 0.80000000000000004D)
                            conto.p[i_65].hsb[2] = 0.8F;
                        conto.p[i_65].hsb[0] = 0.075F;
                        conto.p[i_65].hsb[1] = 0.05F;
                    }
                    if(conto.p[i_65].bfase > 60)
                        conto.p[i_65].hsb[0] = 0.05F;
                    conto.p[i_65].bfase += Math.abs(f_66);
                    new Color(conto.p[i_65].c[0], conto.p[i_65].c[1], conto.p[i_65].c[2]);
                    Color color = Color.getHSBColor(conto.p[i_65].hsb[0], conto.p[i_65].hsb[1], conto.p[i_65].hsb[2]);
                    conto.p[i_65].c[0] = color.getRed();
                    conto.p[i_65].c[1] = color.getGreen();
                    conto.p[i_65].c[2] = color.getBlue();
                }
                if(conto.p[i_65].glass == 1)
                    conto.p[i_65].gr += Math.abs((double)f_66 * 1.5D);
            }

        }
    }

    public void chipx(int i, float f, ContO conto, Madness mad)
    {
        if(Math.abs(f) > 100F)
        {
            if(f > 100F)
                f -= 100F;
            if(f < -100F)
                f += 100F;
            for(int i_68 = 0; i_68 < conto.npl; i_68++)
            {
                float f_69 = 0.0F;
                for(int i_70 = 0; i_70 < conto.p[i_68].n; i_70++)
                    //if(conto.p[i_68].wz == 0 && py(conto.keyx[i], conto.p[i_68].ox[i_70], conto.keyz[i], conto.p[i_68].oz[i_70]) < mad.cn.clrad[mad.cn])
                    //    f_69 = (f / 20F) * m.random();

                if(f_69 != 0.0F && Math.abs(f_69) >= 1.0F)
                {
                    conto.p[i_68].chip = 1;
                    conto.p[i_68].ctmag = f_69;
                }
            }

        }
    }

    public void chipz(int i, float f, ContO conto, Madness mad)
    {
        if(Math.abs(f) > 100F)
        {
            if(f > 100F)
                f -= 100F;
            if(f < -100F)
                f += 100F;
            for(int i_71 = 0; i_71 < conto.npl; i_71++)
            {
                float f_72 = 0.0F;
                for(int i_73 = 0; i_73 < conto.p[i_71].n; i_73++)
                    //if(conto.p[i_71].wz == 0 && py(conto.keyx[i], conto.p[i_71].ox[i_73], conto.keyz[i], conto.p[i_71].oz[i_73]) < mad.cn.clrad[mad.cn])
                    //    f_72 = (f / 20F) * m.random();

                if(f_72 != 0.0F && Math.abs(f_72) >= 1.0F)
                {
                    conto.p[i_71].chip = 1;
                    conto.p[i_71].ctmag = f_72;
                }
            }

        }
    }

    public int py(int i, int i_74, int i_75, int i_76)
    {
        return (i - i_74) * (i - i_74) + (i_75 - i_76) * (i_75 - i_76);
    }

    Medium m;
    int caught;
    boolean hcaught;
    boolean prepit;
    ContO ocar[];
    int cntf;
    ContO car[][];
    int squash[][];
    int fix[];
    int dest[];
    int x[][];
    int y[][];
    int z[][];
    int xy[][];
    int zy[][];
    int xz[][];
    int wxz[][];
    int wzy[][];
    int ns[][];
    int sspark[][][];
    int sx[][][];
    int sy[][][];
    int sz[][][];
    float smag[][][];
    int scx[][][];
    int scz[][][];
    int nr[];
    int rspark[][];
    int sprk[][];
    int srx[][];
    int sry[][];
    int srz[][];
    float rcx[][];
    float rcy[][];
    float rcz[][];
    int nry[][];
    int ry[][][];
    int magy[][][];
    boolean mtouch[][];
    int nrx[][];
    int rx[][][];
    int magx[][][];
    int nrz[][];
    int rz[][][];
    int magz[][][];
    int checkpoint[];
    boolean lastcheck[];
    int wasted;
    int whenwasted;
    int powered;
    int closefinish;
    ContO starcar[];
    int hsquash[] = {
        0, 0, 0, 0, 0, 0, 0, 0
    };
    int hfix[] = {
        -1, -1, -1, -1, -1, -1, -1, -1
    };
    int hdest[] = {
        -1, -1, -1, -1, -1, -1, -1, -1
    };
    int hx[][];
    int hy[][];
    int hz[][];
    int hxy[][];
    int hzy[][];
    int hxz[][];
    int hwxz[][];
    int hwzy[][];
    int hsspark[][][];
    int hsx[][][];
    int hsy[][][];
    int hsz[][][];
    float hsmag[][][];
    int hscx[][][];
    int hscz[][][];
    int hrspark[][];
    int hsprk[][];
    int hsrx[][];
    int hsry[][];
    int hsrz[][];
    float hrcx[][];
    float hrcy[][];
    float hrcz[][];
    int hry[][][];
    int hmagy[][][];
    int hrx[][][];
    int hmagx[][][];
    int hrz[][][];
    int hmagz[][][];
    boolean hmtouch[][];
    int hcheckpoint[];
    boolean hlastcheck[];
    int cntdest[];
    int lastfr;
}
