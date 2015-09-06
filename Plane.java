// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Plane.java

import java.awt.*;

public class Plane
{

    public Plane(Medium medium, Trackers trackers, int is[], int is_0[], int is_1[], int i, int is_2[], 
            int i_3, int i_4, int i_5, int i_6, int i_7, int i_8, int i_9, 
            int i_10, boolean bool, int i_11, boolean bool_12)
    {
        c = new int[3];
        oc = new int[3];
        hsb = new float[3];
        glass = 0;
        gr = 0;
        fs = 0;
        disline = 7;
        road = false;
        solo = false;
        light = 0;
        master = 0;
        wx = 0;
        wz = 0;
        wy = 0;
        deltaf = 1.0F;
        projf = 1.0F;
        av = 0;
        bfase = 0;
        nocol = false;
        chip = 0;
        ctmag = 0.0F;
        cxz = 0;
        cxy = 0;
        czy = 0;
        cox = new int[3];
        coz = new int[3];
        coy = new int[3];
        dx = 0;
        dy = 0;
        dz = 0;
        vx = 0;
        vy = 0;
        vz = 0;
        embos = 0;
        typ = 0;
        pa = 0;
        pb = 0;
        flx = 0;
        colnum = 0;
        m = medium;
        t = trackers;
        n = i;
        ox = new int[n];
        oz = new int[n];
        oy = new int[n];
        for(int i_13 = 0; i_13 < n; i_13++)
        {
            ox[i_13] = is[i_13];
            oy[i_13] = is_1[i_13];
            oz[i_13] = is_0[i_13];
        }

        for(int i_14 = 0; i_14 < 3; i_14++)
            oc[i_14] = is_2[i_14];

        if(i_4 == -15)
        {
            if(is_2[0] == 211)
            {
                int i_15 = (int)(Math.random() * 40D - 20D);
                int i_16 = (int)(Math.random() * 40D - 20D);
                for(int i_17 = 0; i_17 < n; i_17++)
                {
                    ox[i_17] += i_15;
                    oz[i_17] += i_16;
                }

            }
            int i_18 = (int)(185D + Math.random() * 20D);
            is_2[0] = (217 + i_18) / 2;
            if(is_2[0] == 211)
                is_2[0] = 210;
            is_2[1] = (189 + i_18) / 2;
            is_2[2] = (132 + i_18) / 2;
            for(int i_19 = 0; i_19 < n; i_19++)
            {
                if(Math.random() > Math.random())
                    ox[i_19] += (int)(8D * Math.random() - 4D);
                if(Math.random() > Math.random())
                    oy[i_19] += (int)(8D * Math.random() - 4D);
                if(Math.random() > Math.random())
                    oz[i_19] += (int)(8D * Math.random() - 4D);
            }

        }
        if(is_2[0] == is_2[1] && is_2[1] == is_2[2])
            nocol = true;
        if(i_3 == 0)
        {
            for(int i_20 = 0; i_20 < 3; i_20++)
            {
                c[i_20] = (int)((float)is_2[i_20] + (float)is_2[i_20] * ((float)m.snap[i_20] / 100F));
                if(c[i_20] > 255)
                    c[i_20] = 255;
                if(c[i_20] < 0)
                    c[i_20] = 0;
            }

        }
        if(i_3 == 1)
        {
            for(int i_21 = 0; i_21 < 3; i_21++)
                c[i_21] = (m.csky[i_21] * m.fade[0] * 2 + m.cfade[i_21] * 3000) / (m.fade[0] * 2 + 3000);

        }
        if(i_3 == 2)
        {
            for(int i_22 = 0; i_22 < 3; i_22++)
                c[i_22] = (int)((float)m.crgrnd[i_22] * 0.925F);

        }
        if(i_3 == 3)
        {
            for(int i_23 = 0; i_23 < 3; i_23++)
                c[i_23] = is_2[i_23];

        }
        disline = i_9;
        bfase = i_10;
        glass = i_3;
        Color.RGBtoHSB(c[0], c[1], c[2], hsb);
        if(i_3 == 3 && m.trk != 2)
        {
            hsb[1] += 0.05F;
            if(hsb[1] > 1.0F)
                hsb[1] = 1.0F;
        }
        if(!nocol && glass != 1)
        {
            if(bfase > 20 && (double)hsb[1] > 0.25D)
                hsb[1] = 0.25F;
            if(bfase > 25 && (double)hsb[2] > 0.69999999999999996D)
                hsb[2] = 0.7F;
            if(bfase > 30 && (double)hsb[1] > 0.14999999999999999D)
                hsb[1] = 0.15F;
            if(bfase > 35 && (double)hsb[2] > 0.59999999999999998D)
                hsb[2] = 0.6F;
            if(bfase > 40)
                hsb[0] = 0.075F;
            if(bfase > 50 && (double)hsb[2] > 0.5D)
                hsb[2] = 0.5F;
            if(bfase > 60)
                hsb[0] = 0.05F;
        }
        road = bool;
        light = i_11;
        solo = bool_12;
        gr = i_4;
        fs = i_5;
        wx = i_6;
        wy = i_7;
        wz = i_8;
        deltafntyp();
    }

    public void deltafntyp()
    {
        int i = Math.abs(ox[2] - ox[1]);
        int i_24 = Math.abs(oy[2] - oy[1]);
        int i_25 = Math.abs(oz[2] - oz[1]);
        if(i_24 <= i && i_24 <= i_25)
            typ = 2;
        if(i <= i_24 && i <= i_25)
            typ = 1;
        if(i_25 <= i && i_25 <= i_24)
            typ = 3;
        deltaf = 1.0F;
        for(int i_26 = 0; i_26 < 3; i_26++)
        {
            for(int i_27 = 0; i_27 < 3; i_27++)
                if(i_27 != i_26)
                    deltaf *= (float)(Math.sqrt((ox[i_27] - ox[i_26]) * (ox[i_27] - ox[i_26]) + (oy[i_27] - oy[i_26]) * (oy[i_27] - oy[i_26]) + (oz[i_27] - oz[i_26]) * (oz[i_27] - oz[i_26])) / 100D);

        }

        deltaf = deltaf / 3F;
    }

    public void loadprojf()
    {
        projf = 1.0F;
        for(int i = 0; i < 3; i++)
        {
            for(int i_28 = 0; i_28 < 3; i_28++)
                if(i_28 != i)
                    projf *= (float)(Math.sqrt((ox[i] - ox[i_28]) * (ox[i] - ox[i_28]) + (oz[i] - oz[i_28]) * (oz[i] - oz[i_28])) / 100D);

        }

        projf = projf / 3F;
    }

    public void d(Graphics2D graphics2d, int i, int i_29, int i_30, int i_31, int i_32, int i_33, 
            int i_34, int i_35, boolean bool, int i_36)
    {
        if(master == 1)
            if(av > 1500 && !m.crs)
                n = 12;
            else
                n = 20;
        int is[] = new int[n];
        int is_37[] = new int[n];
        int is_38[] = new int[n];
        if(embos == 0)
        {
            for(int i_39 = 0; i_39 < n; i_39++)
            {
                is[i_39] = ox[i_39] + i;
                is_38[i_39] = oy[i_39] + i_29;
                is_37[i_39] = oz[i_39] + i_30;
            }

            if((gr == -11 || gr == -12 || gr == -13) && m.lastmaf == 1)
            {
                for(int i_40 = 0; i_40 < n; i_40++)
                {
                    is[i_40] = -ox[i_40] + i;
                    is_38[i_40] = oy[i_40] + i_29;
                    is_37[i_40] = -oz[i_40] + i_30;
                }

            }
        } else
        {
            if(embos <= 11 && (double)m.random() > 0.5D && glass != 1)
            {
                for(int i_41 = 0; i_41 < n; i_41++)
                {
                    is[i_41] = (int)((float)(ox[i_41] + i) + (15F - m.random() * 30F));
                    is_38[i_41] = (int)((float)(oy[i_41] + i_29) + (15F - m.random() * 30F));
                    is_37[i_41] = (int)((float)(oz[i_41] + i_30) + (15F - m.random() * 30F));
                }

                rot(is, is_38, i, i_29, i_32, n);
                rot(is_38, is_37, i_29, i_30, i_33, n);
                rot(is, is_37, i, i_30, i_31, n);
                rot(is, is_37, m.cx, m.cz, m.xz, n);
                rot(is_38, is_37, m.cy, m.cz, m.zy, n);
                int is_42[] = new int[n];
                int is_43[] = new int[n];
                for(int i_44 = 0; i_44 < n; i_44++)
                {
                    is_42[i_44] = xs(is[i_44], is_37[i_44]);
                    is_43[i_44] = ys(is_38[i_44], is_37[i_44]);
                }

                graphics2d.setColor(new Color(230, 230, 230));
                graphics2d.fillPolygon(is_42, is_43, n);
            }
            float f = 1.0F;
            if(embos <= 4)
                f = 1.0F + m.random() / 5F;
            if(embos > 4 && embos <= 7)
                f = 1.0F + m.random() / 4F;
            if(embos > 7 && embos <= 9)
            {
                f = 1.0F + m.random() / 3F;
                if((double)hsb[2] > 0.69999999999999996D)
                    hsb[2] = 0.7F;
            }
            if(embos > 9 && embos <= 10)
            {
                f = 1.0F + m.random() / 2.0F;
                if((double)hsb[2] > 0.59999999999999998D)
                    hsb[2] = 0.6F;
            }
            if(embos > 10 && embos <= 12)
            {
                f = 1.0F + m.random() / 1.0F;
                if((double)hsb[2] > 0.5D)
                    hsb[2] = 0.5F;
            }
            if(embos == 12)
            {
                chip = 1;
                ctmag = 2.0F;
                bfase = -7;
            }
            if(embos == 13)
            {
                hsb[1] = 0.2F;
                hsb[2] = 0.4F;
            }
            if(embos == 16)
            {
                pa = (int)(m.random() * (float)n);
                for(pb = (int)(m.random() * (float)n); pa == pb; pb = (int)(m.random() * (float)n));
            }
            if(embos >= 16)
            {
                int i_45 = 1;
                int i_46 = 1;
                int i_47;
                for(i_47 = Math.abs(i_33); i_47 > 270; i_47 -= 360);
                i_47 = Math.abs(i_47);
                if(i_47 > 90)
                    i_45 = -1;
                int i_48;
                for(i_48 = Math.abs(i_32); i_48 > 270; i_48 -= 360);
                i_48 = Math.abs(i_48);
                if(i_48 > 90)
                    i_46 = -1;
                int is_49[] = new int[3];
                int is_50[] = new int[3];
                is[0] = ox[pa] + i;
                is_38[0] = oy[pa] + i_29;
                is_37[0] = oz[pa] + i_30;
                is[1] = ox[pb] + i;
                is_38[1] = oy[pb] + i_29;
                for(is_37[1] = oz[pb] + i_30; Math.abs(is[0] - is[1]) > 100;)
                    if(is[1] > is[0])
                        is[1] -= 30;
                    else
                        is[1] += 30;

                while(Math.abs(is_37[0] - is_37[1]) > 100) 
                    if(is_37[1] > is_37[0])
                        is_37[1] -= 30;
                    else
                        is_37[1] += 30;
                int i_51 = (int)((double)(Math.abs(is[0] - is[1]) / 3) * (0.5D - (double)m.random()));
                int i_52 = (int)((double)(Math.abs(is_37[0] - is_37[1]) / 3) * (0.5D - (double)m.random()));
                is[2] = (is[0] + is[1]) / 2 + i_51;
                is_37[2] = (is_37[0] + is_37[1]) / 2 + i_52;
                int i_53 = (int)(((double)(Math.abs(is[0] - is[1]) + Math.abs(is_37[0] - is_37[1])) / 1.5D) * ((double)(m.random() / 2.0F) + 0.5D));
                is_38[2] = (is_38[0] + is_38[1]) / 2 - i_45 * i_46 * i_53;
                rot(is, is_38, i, i_29, i_32, 3);
                rot(is_38, is_37, i_29, i_30, i_33, 3);
                rot(is, is_37, i, i_30, i_31, 3);
                rot(is, is_37, m.cx, m.cz, m.xz, 3);
                rot(is_38, is_37, m.cy, m.cz, m.zy, 3);
                for(int i_54 = 0; i_54 < 3; i_54++)
                {
                    is_49[i_54] = xs(is[i_54], is_37[i_54]);
                    is_50[i_54] = ys(is_38[i_54], is_37[i_54]);
                }

                int i_55 = (int)(255F + 255F * ((float)m.snap[0] / 400F));
                if(i_55 > 255)
                    i_55 = 255;
                if(i_55 < 0)
                    i_55 = 0;
                int i_56 = (int)(169F + 169F * ((float)m.snap[1] / 300F));
                if(i_56 > 255)
                    i_56 = 255;
                if(i_56 < 0)
                    i_56 = 0;
                int i_57 = (int)(89F + 89F * ((float)m.snap[2] / 200F));
                if(i_57 > 255)
                    i_57 = 255;
                if(i_57 < 0)
                    i_57 = 0;
                graphics2d.setColor(new Color(i_55, i_56, i_57));
                graphics2d.fillPolygon(is_49, is_50, 3);
                is[0] = ox[pa] + i;
                is_38[0] = oy[pa] + i_29;
                is_37[0] = oz[pa] + i_30;
                is[1] = ox[pb] + i;
                is_38[1] = oy[pb] + i_29;
                for(is_37[1] = oz[pb] + i_30; Math.abs(is[0] - is[1]) > 100;)
                    if(is[1] > is[0])
                        is[1] -= 30;
                    else
                        is[1] += 30;

                while(Math.abs(is_37[0] - is_37[1]) > 100) 
                    if(is_37[1] > is_37[0])
                        is_37[1] -= 30;
                    else
                        is_37[1] += 30;
                is[2] = (is[0] + is[1]) / 2 + i_51;
                is_37[2] = (is_37[0] + is_37[1]) / 2 + i_52;
                i_53 = (int)((double)i_53 * 0.80000000000000004D);
                is_38[2] = (is_38[0] + is_38[1]) / 2 - i_45 * i_46 * i_53;
                rot(is, is_38, i, i_29, i_32, 3);
                rot(is_38, is_37, i_29, i_30, i_33, 3);
                rot(is, is_37, i, i_30, i_31, 3);
                rot(is, is_37, m.cx, m.cz, m.xz, 3);
                rot(is_38, is_37, m.cy, m.cz, m.zy, 3);
                for(int i_58 = 0; i_58 < 3; i_58++)
                {
                    is_49[i_58] = xs(is[i_58], is_37[i_58]);
                    is_50[i_58] = ys(is_38[i_58], is_37[i_58]);
                }

                i_55 = (int)(255F + 255F * ((float)m.snap[0] / 400F));
                if(i_55 > 255)
                    i_55 = 255;
                if(i_55 < 0)
                    i_55 = 0;
                i_56 = (int)(207F + 207F * ((float)m.snap[1] / 300F));
                if(i_56 > 255)
                    i_56 = 255;
                if(i_56 < 0)
                    i_56 = 0;
                i_57 = (int)(136F + 136F * ((float)m.snap[2] / 200F));
                if(i_57 > 255)
                    i_57 = 255;
                if(i_57 < 0)
                    i_57 = 0;
                graphics2d.setColor(new Color(i_55, i_56, i_57));
                graphics2d.fillPolygon(is_49, is_50, 3);
            }
            for(int i_59 = 0; i_59 < n; i_59++)
            {
                if(typ == 1)
                    is[i_59] = (int)((float)ox[i_59] * f + (float)i);
                else
                    is[i_59] = ox[i_59] + i;
                if(typ == 2)
                    is_38[i_59] = (int)((float)oy[i_59] * f + (float)i_29);
                else
                    is_38[i_59] = oy[i_59] + i_29;
                if(typ == 3)
                    is_37[i_59] = (int)((float)oz[i_59] * f + (float)i_30);
                else
                    is_37[i_59] = oz[i_59] + i_30;
            }

            if(embos != 70)
                embos++;
            else
                embos = 16;
        }
        if(wz != 0)
            rot(is_38, is_37, wy + i_29, wz + i_30, i_35, n);
        if(wx != 0)
            rot(is, is_37, wx + i, wz + i_30, i_34, n);
        if(chip == 1 && ((double)m.random() > 0.59999999999999998D || bfase == 0))
        {
            chip = 0;
            if(bfase == 0 && nocol)
                bfase = 1;
        }
        if(chip != 0)
        {
            if(chip == 1)
            {
                cxz = i_31;
                cxy = i_32;
                czy = i_33;
                int i_60 = (int)(m.random() * (float)n);
                cox[0] = ox[i_60];
                coz[0] = oz[i_60];
                coy[0] = oy[i_60];
                if(ctmag > 3F)
                    ctmag = 3F;
                if(ctmag < -3F)
                    ctmag = -3F;
                cox[1] = (int)((float)cox[0] + ctmag * (10F - m.random() * 20F));
                cox[2] = (int)((float)cox[0] + ctmag * (10F - m.random() * 20F));
                coy[1] = (int)((float)coy[0] + ctmag * (10F - m.random() * 20F));
                coy[2] = (int)((float)coy[0] + ctmag * (10F - m.random() * 20F));
                coz[1] = (int)((float)coz[0] + ctmag * (10F - m.random() * 20F));
                coz[2] = (int)((float)coz[0] + ctmag * (10F - m.random() * 20F));
                dx = 0;
                dy = 0;
                dz = 0;
                if(bfase != -7)
                {
                    vx = (int)(ctmag * (30F - m.random() * 60F));
                    vz = (int)(ctmag * (30F - m.random() * 60F));
                    vy = (int)(ctmag * (30F - m.random() * 60F));
                } else
                {
                    vx = (int)(ctmag * (10F - m.random() * 20F));
                    vz = (int)(ctmag * (10F - m.random() * 20F));
                    vy = (int)(ctmag * (10F - m.random() * 20F));
                }
                chip = 2;
            }
            int is_61[] = new int[3];
            int is_62[] = new int[3];
            int is_63[] = new int[3];
            for(int i_64 = 0; i_64 < 3; i_64++)
            {
                is_61[i_64] = cox[i_64] + i;
                is_63[i_64] = coy[i_64] + i_29;
                is_62[i_64] = coz[i_64] + i_30;
            }

            rot(is_61, is_63, i, i_29, cxy, 3);
            rot(is_63, is_62, i_29, i_30, czy, 3);
            rot(is_61, is_62, i, i_30, cxz, 3);
            for(int i_65 = 0; i_65 < 3; i_65++)
            {
                is_61[i_65] += dx;
                is_63[i_65] += dy;
                is_62[i_65] += dz;
            }

            dx += vx;
            dz += vz;
            dy += vy;
            vy += 7;
            if(is_63[0] > m.ground)
                chip = 19;
            rot(is_61, is_62, m.cx, m.cz, m.xz, 3);
            rot(is_63, is_62, m.cy, m.cz, m.zy, 3);
            int is_66[] = new int[3];
            int is_67[] = new int[3];
            for(int i_68 = 0; i_68 < 3; i_68++)
            {
                is_66[i_68] = xs(is_61[i_68], is_62[i_68]);
                is_67[i_68] = ys(is_63[i_68], is_62[i_68]);
            }

            int i_69 = (int)(m.random() * 3F);
            if(bfase != -7)
            {
                if(i_69 == 0)
                    graphics2d.setColor((new Color(c[0], c[1], c[2])).darker());
                if(i_69 == 1)
                    graphics2d.setColor(new Color(c[0], c[1], c[2]));
                if(i_69 == 2)
                    graphics2d.setColor((new Color(c[0], c[1], c[2])).brighter());
            } else
            {
                graphics2d.setColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]));
            }
            graphics2d.fillPolygon(is_66, is_67, 3);
            chip++;
            if(chip == 20)
                chip = 0;
        }
        rot(is, is_38, i, i_29, i_32, n);
        rot(is_38, is_37, i_29, i_30, i_33, n);
        rot(is, is_37, i, i_30, i_31, n);
        if((i_32 != 0 || i_33 != 0 || i_31 != 0) && m.trk != 2)
        {
            projf = 1.0F;
            for(int i_70 = 0; i_70 < 3; i_70++)
            {
                for(int i_71 = 0; i_71 < 3; i_71++)
                    if(i_71 != i_70)
                        projf *= (float)(Math.sqrt((is[i_70] - is[i_71]) * (is[i_70] - is[i_71]) + (is_37[i_70] - is_37[i_71]) * (is_37[i_70] - is_37[i_71])) / 100D);

            }

            projf = projf / 3F;
        }
        rot(is, is_37, m.cx, m.cz, m.xz, n);
        boolean bool_72 = false;
        int is_73[] = new int[n];
        int is_74[] = new int[n];
        int i_75 = 500;
        for(int i_76 = 0; i_76 < n; i_76++)
        {
            is_73[i_76] = xs(is[i_76], is_37[i_76]);
            is_74[i_76] = ys(is_38[i_76], is_37[i_76]);
        }

        int i_77 = 0;
        int i_78 = 1;
        for(int i_79 = 0; i_79 < n; i_79++)
        {
            for(int i_80 = i_79; i_80 < n; i_80++)
                if(i_79 != i_80 && Math.abs(is_73[i_79] - is_73[i_80]) - Math.abs(is_74[i_79] - is_74[i_80]) < i_75)
                {
                    i_78 = i_79;
                    i_77 = i_80;
                    i_75 = Math.abs(is_73[i_79] - is_73[i_80]) - Math.abs(is_74[i_79] - is_74[i_80]);
                }

        }

        if(is_74[i_77] < is_74[i_78])
        {
            int i_81 = i_77;
            i_77 = i_78;
            i_78 = i_81;
        }
        if(spy(is[i_77], is_37[i_77]) > spy(is[i_78], is_37[i_78]))
        {
            bool_72 = true;
            int i_82 = 0;
            for(int i_83 = 0; i_83 < n; i_83++)
            {
                if(is_37[i_83] < 50 && is_38[i_83] > m.cy)
                {
                    bool_72 = false;
                    continue;
                }
                if(is_38[i_83] == is_38[0])
                    i_82++;
            }

            if(i_82 == n && is_38[0] > m.cy)
                bool_72 = false;
        }
        rot(is_38, is_37, m.cy, m.cz, m.zy, n);
        boolean bool_84 = true;
        int is_85[] = new int[n];
        int is_86[] = new int[n];
        int i_87 = 0;
        int i_88 = 0;
        int i_89 = 0;
        int i_90 = 0;
        int i_91 = 0;
        for(int i_92 = 0; i_92 < n; i_92++)
        {
            is_85[i_92] = xs(is[i_92], is_37[i_92]);
            is_86[i_92] = ys(is_38[i_92], is_37[i_92]);
            if(is_86[i_92] < m.ih || is_37[i_92] < 10)
                i_87++;
            if(is_86[i_92] > m.h || is_37[i_92] < 10)
                i_88++;
            if(is_85[i_92] < m.iw || is_37[i_92] < 10)
                i_89++;
            if(is_85[i_92] > m.w || is_37[i_92] < 10)
                i_90++;
            if(is_37[i_92] < 10)
                i_91++;
        }

        if(i_89 == n || i_87 == n || i_88 == n || i_90 == n)
            bool_84 = false;
        if(m.trk == 1 && (i_89 != 0 || i_87 != 0 || i_88 != 0 || i_90 != 0))
            bool_84 = false;
        if(m.trk == 3 && i_91 != 0)
            bool_84 = false;
        if(i_91 != 0)
            bool = true;
        if(bool_84 && i_36 != -1)
        {
            int i_93 = 0;
            int i_94 = 0;
            for(int i_95 = 0; i_95 < n; i_95++)
            {
                for(int i_96 = i_95; i_96 < n; i_96++)
                {
                    if(i_95 == i_96)
                        continue;
                    if(Math.abs(is_85[i_95] - is_85[i_96]) > i_93)
                        i_93 = Math.abs(is_85[i_95] - is_85[i_96]);
                    if(Math.abs(is_86[i_95] - is_86[i_96]) > i_94)
                        i_94 = Math.abs(is_86[i_95] - is_86[i_96]);
                }

            }

            if(i_93 == 0 || i_94 == 0)
                bool_84 = false;
            else
            if(i_93 < 3 && i_94 < 3 && (i_36 / i_93 > 15 && i_36 / i_94 > 15 || bool) && (!m.lightson || light == 0))
                bool_84 = false;
        }
        if(bool_84)
        {
            int i_97 = 1;
            int i_98 = gr;
            if(i_98 < 0 && i_98 >= -15)
                i_98 = 0;
            if(gr == -11)
                i_98 = -90;
            if(gr == -12)
                i_98 = -75;
            if(gr == -14 || gr == -15)
                i_98 = -50;
            if(glass == 2)
                i_98 = 200;
            if(this.fs != 0)
            {
                boolean bool_99 = true;
                boolean bool_100 = true;
                int i_101;
                int i_102;
                if(Math.abs(is_86[0] - is_86[1]) > Math.abs(is_86[2] - is_86[1]))
                {
                    i_101 = 0;
                    i_102 = 2;
                } else
                {
                    i_101 = 2;
                    i_102 = 0;
                    i_97 *= -1;
                }
                if(is_86[1] > is_86[i_101])
                    i_97 *= -1;
                if(is_85[1] > is_85[i_102])
                    i_97 *= -1;
                if(this.fs != 22)
                {
                    i_97 *= this.fs;
                    if(i_97 == -1)
                    {
                        i_98 += 40;
                        i_97 = -111;
                    }
                }
            }
            if(m.lightson && light == 2)
                i_98 -= 40;
            int i_103 = is_38[0];
            int i_104 = is_38[0];
            int i_105 = is[0];
            int i_106 = is[0];
            int i_107 = is_37[0];
            int i_108 = is_37[0];
            for(int i_109 = 0; i_109 < n; i_109++)
            {
                if(is_38[i_109] > i_103)
                    i_103 = is_38[i_109];
                if(is_38[i_109] < i_104)
                    i_104 = is_38[i_109];
                if(is[i_109] > i_105)
                    i_105 = is[i_109];
                if(is[i_109] < i_106)
                    i_106 = is[i_109];
                if(is_37[i_109] > i_107)
                    i_107 = is_37[i_109];
                if(is_37[i_109] < i_108)
                    i_108 = is_37[i_109];
            }

            int i_110 = (i_103 + i_104) / 2;
            int i_111 = (i_105 + i_106) / 2;
            int i_112 = (i_107 + i_108) / 2;
            av = (int)Math.sqrt((m.cy - i_110) * (m.cy - i_110) + (m.cx - i_111) * (m.cx - i_111) + i_112 * i_112 + i_98 * i_98 * i_98);
            if(m.trk == 0 && (av > m.fade[disline] || av == 0))
                bool_84 = false;
            if(i_97 == -111 && av > 4500 && !road)
                bool_84 = false;
            if(i_97 == -111 && av > 1500)
                bool = true;
            if(av > 3000 && m.adv <= 900)
                bool = true;
            if(this.fs == 22 && av < 11200)
                m.lastmaf = i_97;
            if(gr == -13 && (!m.lastcheck || i_36 != -1))
                bool_84 = false;
            if(master == 2 && av > 1500 && !m.crs)
                bool_84 = false;
            if((gr == -14 || gr == -15 || gr == -12) && (av > 11000 || bool_72 || i_97 == -111 || m.resdown == 2) && m.trk != 2 && m.trk != 3)
                bool_84 = false;
            if(gr == -11 && av > 11000 && m.trk != 2 && m.trk != 3)
                bool_84 = false;
            if(glass == 2 && (m.trk != 0 || av > 6700))
                bool_84 = false;
            if(flx != 0 && (double)m.random() > 0.29999999999999999D && flx != 77)
                bool_84 = false;
        }
        if(bool_84)
        {
            float f = (float)((double)(projf / deltaf) + 0.29999999999999999D);
            if(bool && !solo)
            {
                boolean bool_113 = false;
                if(f > 1.0F)
                {
                    if((double)f >= 1.27D)
                        bool_113 = true;
                    f = 1.0F;
                }
                if(bool_113)
                    f = (float)((double)f * 0.89000000000000001D);
                else
                    f = (float)((double)f * 0.85999999999999999D);
                if((double)f < 0.37D)
                    f = 0.37F;
                if(gr == -9)
                    f = 0.7F;
                if(gr == -4)
                    f = 0.74F;
                if(gr != -7 && m.trk == 0 && bool_72)
                    f = 0.32F;
                if(gr == -8 || gr == -14 || gr == -15)
                    f = 1.0F;
                if(gr == -11 || gr == -12)
                {
                    f = 0.6F;
                    if(i_36 == -1)
                        if(m.cpflik || m.nochekflk && !m.lastcheck)
                            f = 1.0F;
                        else
                            f = 0.76F;
                }
                if(gr == -13 && i_36 == -1)
                    if(m.cpflik)
                        f = 0.0F;
                    else
                        f = 0.76F;
                if(gr == -6)
                    f = 0.62F;
                if(gr == -5)
                    f = 0.55F;
            } else
            {
                if(f > 1.0F)
                    f = 1.0F;
                if((double)f < 0.59999999999999998D || bool_72)
                    f = 0.6F;
            }
            Color color = Color.getHSBColor(hsb[0], hsb[1], hsb[2] * f);
            if(m.trk == 1)
            {
                float fs[] = new float[3];
                Color.RGBtoHSB(oc[0], oc[1], oc[2], fs);
                fs[0] = 0.15F;
                fs[1] = 0.3F;
                color = Color.getHSBColor(fs[0], fs[1], fs[2] * f + 0.0F);
            }
            if(m.trk == 3)
            {
                float fs[] = new float[3];
                Color.RGBtoHSB(oc[0], oc[1], oc[2], fs);
                fs[0] = 0.6F;
                fs[1] = 0.14F;
                color = Color.getHSBColor(fs[0], fs[1], fs[2] * f + 0.0F);
            }
            int i_114 = color.getRed();
            int i_115 = color.getGreen();
            int i_116 = color.getBlue();
            if(m.lightson && (light != 0 || (gr == -11 || gr == -12) && i_36 == -1))
            {
                i_114 = oc[0];
                if(i_114 > 255)
                    i_114 = 255;
                if(i_114 < 0)
                    i_114 = 0;
                i_115 = oc[1];
                if(i_115 > 255)
                    i_115 = 255;
                if(i_115 < 0)
                    i_115 = 0;
                i_116 = oc[2];
                if(i_116 > 255)
                    i_116 = 255;
                if(i_116 < 0)
                    i_116 = 0;
            }
            if(m.trk == 0)
            {
                for(int i_117 = 0; i_117 < 16; i_117++)
                    if(av > m.fade[i_117])
                    {
                        i_114 = (i_114 * m.fogd + m.cfade[0]) / (m.fogd + 1);
                        i_115 = (i_115 * m.fogd + m.cfade[1]) / (m.fogd + 1);
                        i_116 = (i_116 * m.fogd + m.cfade[2]) / (m.fogd + 1);
                    }

            }
            graphics2d.setColor(new Color(i_114, i_115, i_116, glass != 1 || m.resdown != 0 ? 255 : 175));
            graphics2d.fillPolygon(is_85, is_86, n);
            if(m.trk != 0 && gr == -10)
                bool = false;
            if(!bool)
            {
                if(flx == 0)
                {
                    if(!solo)
                    {
                        i_114 = 0;
                        i_115 = 0;
                        i_116 = 0;
                        if(m.lightson && light != 0)
                        {
                            i_114 = oc[0] / 2;
                            if(i_114 > 255)
                                i_114 = 255;
                            if(i_114 < 0)
                                i_114 = 0;
                            i_115 = oc[1] / 2;
                            if(i_115 > 255)
                                i_115 = 255;
                            if(i_115 < 0)
                                i_115 = 0;
                            i_116 = oc[2] / 2;
                            if(i_116 > 255)
                                i_116 = 255;
                            if(i_116 < 0)
                                i_116 = 0;
                        }
                        graphics2d.setColor(new Color(i_114, i_115, i_116));
                        graphics2d.drawPolygon(is_85, is_86, n);
                    }
                } else
                {
                    if(flx == 2)
                    {
                        graphics2d.setColor(new Color(0, 0, 0));
                        graphics2d.drawPolygon(is_85, is_86, n);
                    }
                    if(flx == 1)
                    {
                        i_114 = 0;
                        i_115 = (int)(223F + 223F * ((float)m.snap[1] / 100F));
                        if(i_115 > 255)
                            i_115 = 255;
                        if(i_115 < 0)
                            i_115 = 0;
                        i_116 = (int)(255F + 255F * ((float)m.snap[2] / 100F));
                        if(i_116 > 255)
                            i_116 = 255;
                        if(i_116 < 0)
                            i_116 = 0;
                        graphics2d.setColor(new Color(i_114, i_115, i_116));
                        graphics2d.drawPolygon(is_85, is_86, n);
                        flx = 2;
                    }
                    if(flx == 3)
                    {
                        i_114 = 0;
                        i_115 = (int)(255F + 255F * ((float)m.snap[1] / 100F));
                        if(i_115 > 255)
                            i_115 = 255;
                        if(i_115 < 0)
                            i_115 = 0;
                        i_116 = (int)(223F + 223F * ((float)m.snap[2] / 100F));
                        if(i_116 > 255)
                            i_116 = 255;
                        if(i_116 < 0)
                            i_116 = 0;
                        graphics2d.setColor(new Color(i_114, i_115, i_116));
                        graphics2d.drawPolygon(is_85, is_86, n);
                        flx = 2;
                    }
                    if(flx == 77)
                    {
                        graphics2d.setColor(new Color(16, 198, 255));
                        graphics2d.drawPolygon(is_85, is_86, n);
                        flx = 0;
                    }
                }
            } else
            if(road && av <= 3000 && m.trk == 0 && m.fade[0] > 4000)
            {
                if((i_114 -= 10) < 0)
                    i_114 = 0;
                if((i_115 -= 10) < 0)
                    i_115 = 0;
                if((i_116 -= 10) < 0)
                    i_116 = 0;
                graphics2d.setColor(new Color(i_114, i_115, i_116));
                graphics2d.drawPolygon(is_85, is_86, n);
            }
            if(gr == -10)
                if(m.trk == 0)
                {
                    i_114 = c[0];
                    i_115 = c[1];
                    i_116 = c[2];
                    if(i_36 == -1 && m.cpflik)
                    {
                        i_114 = (int)((double)i_114 * 1.6000000000000001D);
                        if(i_114 > 255)
                            i_114 = 255;
                        i_115 = (int)((double)i_115 * 1.6000000000000001D);
                        if(i_115 > 255)
                            i_115 = 255;
                        i_116 = (int)((double)i_116 * 1.6000000000000001D);
                        if(i_116 > 255)
                            i_116 = 255;
                    }
                    for(int i_118 = 0; i_118 < 16; i_118++)
                        if(av > m.fade[i_118])
                        {
                            i_114 = (i_114 * m.fogd + m.cfade[0]) / (m.fogd + 1);
                            i_115 = (i_115 * m.fogd + m.cfade[1]) / (m.fogd + 1);
                            i_116 = (i_116 * m.fogd + m.cfade[2]) / (m.fogd + 1);
                        }

                    graphics2d.setColor(new Color(i_114, i_115, i_116));
                    graphics2d.drawPolygon(is_85, is_86, n);
                } else
                if(m.cpflik && m.hit == 5000)
                {
                    i_115 = (int)(Math.random() * 115D);
                    i_114 = i_115 * 2 - 54;
                    if(i_114 < 0)
                        i_114 = 0;
                    if(i_114 > 255)
                        i_114 = 255;
                    i_116 = 202 + i_115 * 2;
                    if(i_116 < 0)
                        i_116 = 0;
                    if(i_116 > 255)
                        i_116 = 255;
                    if((i_115 += 101) < 0)
                        i_115 = 0;
                    if(i_115 > 255)
                        i_115 = 255;
                    graphics2d.setColor(new Color(i_114, i_115, i_116));
                    graphics2d.drawPolygon(is_85, is_86, n);
                }
            if(gr == -18 && m.trk == 0)
            {
                i_114 = c[0];
                i_115 = c[1];
                i_116 = c[2];
                if(m.cpflik && m.elecr >= 0.0F)
                {
                    i_114 = (int)(25.5F * m.elecr);
                    if(i_114 > 255)
                        i_114 = 255;
                    i_115 = (int)(128F + 12.8F * m.elecr);
                    if(i_115 > 255)
                        i_115 = 255;
                    i_116 = 255;
                }
                for(int i_119 = 0; i_119 < 16; i_119++)
                    if(av > m.fade[i_119])
                    {
                        i_114 = (i_114 * m.fogd + m.cfade[0]) / (m.fogd + 1);
                        i_115 = (i_115 * m.fogd + m.cfade[1]) / (m.fogd + 1);
                        i_116 = (i_116 * m.fogd + m.cfade[2]) / (m.fogd + 1);
                    }

                graphics2d.setColor(new Color(i_114, i_115, i_116));
                graphics2d.drawPolygon(is_85, is_86, n);
            }
        }
    }

    public void s(Graphics2D graphics2d, int i, int i_120, int i_121, int i_122, int i_123, int i_124, 
            int i_125)
    {
        int is[] = new int[n];
        int is_126[] = new int[n];
        int is_127[] = new int[n];
        for(int i_128 = 0; i_128 < n; i_128++)
        {
            is[i_128] = ox[i_128] + i;
            is_127[i_128] = oy[i_128] + i_120;
            is_126[i_128] = oz[i_128] + i_121;
        }

        rot(is, is_127, i, i_120, i_123, n);
        rot(is_127, is_126, i_120, i_121, i_124, n);
        rot(is, is_126, i, i_121, i_122, n);
        int i_129 = (int)((double)(float)m.crgrnd[0] / 1.5D);
        int i_130 = (int)((double)(float)m.crgrnd[1] / 1.5D);
        int i_131 = (int)((double)(float)m.crgrnd[2] / 1.5D);
        for(int i_132 = 0; i_132 < n; i_132++)
            is_127[i_132] = m.ground;

        if(i_125 == 0)
        {
            int i_133 = 0;
            int i_134 = 0;
            int i_135 = 0;
            int i_136 = 0;
            for(int i_137 = 0; i_137 < n; i_137++)
            {
                int i_138 = 0;
                int i_139 = 0;
                int i_140 = 0;
                int i_141 = 0;
                for(int i_142 = 0; i_142 < n; i_142++)
                {
                    if(is[i_137] >= is[i_142])
                        i_138++;
                    if(is[i_137] <= is[i_142])
                        i_139++;
                    if(is_126[i_137] >= is_126[i_142])
                        i_140++;
                    if(is_126[i_137] <= is_126[i_142])
                        i_141++;
                }

                if(i_138 == n)
                    i_133 = is[i_137];
                if(i_139 == n)
                    i_134 = is[i_137];
                if(i_140 == n)
                    i_135 = is_126[i_137];
                if(i_141 == n)
                    i_136 = is_126[i_137];
            }

            int i_143 = (i_133 + i_134) / 2;
            int i_144 = (i_135 + i_136) / 2;
            int i_145 = ((i_143 - t.sx) + m.x) / 3000;
            if(i_145 > t.ncx)
                i_145 = t.ncx;
            if(i_145 < 0)
                i_145 = 0;
            int i_146 = ((i_144 - t.sz) + m.z) / 3000;
            if(i_146 > t.ncz)
                i_146 = t.ncz;
            if(i_146 < 0)
                i_146 = 0;
            int i_147 = t.sect[i_145][i_146].length - 1;
            do
            {
                if(i_147 < 0)
                    break;
                int i_148 = t.sect[i_145][i_146][i_147];
                int i_149 = 0;
                if(Math.abs(t.zy[i_148]) != 90 && Math.abs(t.xy[i_148]) != 90 && t.rady[i_148] != 801 && Math.abs(i_143 - (t.x[i_148] - m.x)) < t.radx[i_148] && Math.abs(i_144 - (t.z[i_148] - m.z)) < t.radz[i_148] && (!t.decor[i_148] || m.resdown != 2))
                    i_149++;
                if(i_149 != 0)
                {
                    for(int i_150 = 0; i_150 < n; i_150++)
                    {
                        is_127[i_150] = t.y[i_148] - m.y;
                        if(t.zy[i_148] != 0)
                            is_127[i_150] += ((float)(is_126[i_150] - (t.z[i_148] - m.z - t.radz[i_148])) * m.sin(t.zy[i_148])) / m.sin(90 - t.zy[i_148]) - ((float)t.radz[i_148] * m.sin(t.zy[i_148])) / m.sin(90 - t.zy[i_148]);
                        if(t.xy[i_148] != 0)
                            is_127[i_150] += ((float)(is[i_150] - (t.x[i_148] - m.x - t.radx[i_148])) * m.sin(t.xy[i_148])) / m.sin(90 - t.xy[i_148]) - ((float)t.radx[i_148] * m.sin(t.xy[i_148])) / m.sin(90 - t.xy[i_148]);
                    }

                    i_129 = (int)((double)(float)t.c[i_148][0] / 1.5D);
                    i_130 = (int)((double)(float)t.c[i_148][1] / 1.5D);
                    i_131 = (int)((double)(float)t.c[i_148][2] / 1.5D);
                    break;
                }
                i_147--;
            } while(true);
        }
        boolean bool = true;
        int is_151[] = new int[n];
        int is_152[] = new int[n];
        if(i_125 == 2)
        {
            i_129 = 87;
            i_130 = 85;
            i_131 = 57;
        } else
        {
            for(int i_153 = 0; i_153 < m.nsp; i_153++)
            {
                for(int i_154 = 0; i_154 < n; i_154++)
                    if(Math.abs(is[i_154] - m.spx[i_153]) < m.sprad[i_153] && Math.abs(is_126[i_154] - m.spz[i_153]) < m.sprad[i_153])
                        bool = false;

            }

        }
        if(bool)
        {
            rot(is, is_126, m.cx, m.cz, m.xz, n);
            rot(is_127, is_126, m.cy, m.cz, m.zy, n);
            int i_155 = 0;
            int i_156 = 0;
            int i_157 = 0;
            int i_158 = 0;
            for(int i_159 = 0; i_159 < n; i_159++)
            {
                is_151[i_159] = xs(is[i_159], is_126[i_159]);
                is_152[i_159] = ys(is_127[i_159], is_126[i_159]);
                if(is_152[i_159] < m.ih || is_126[i_159] < 10)
                    i_155++;
                if(is_152[i_159] > m.h || is_126[i_159] < 10)
                    i_156++;
                if(is_151[i_159] < m.iw || is_126[i_159] < 10)
                    i_157++;
                if(is_151[i_159] > m.w || is_126[i_159] < 10)
                    i_158++;
            }

            if(i_157 == n || i_155 == n || i_156 == n || i_158 == n)
                bool = false;
        }
        if(bool)
        {
            for(int i_160 = 0; i_160 < 16; i_160++)
                if(av > m.fade[i_160])
                {
                    i_129 = (i_129 * m.fogd + m.cfade[0]) / (m.fogd + 1);
                    i_130 = (i_130 * m.fogd + m.cfade[1]) / (m.fogd + 1);
                    i_131 = (i_131 * m.fogd + m.cfade[2]) / (m.fogd + 1);
                }

            graphics2d.setColor(new Color(i_129, i_130, i_131));
            graphics2d.fillPolygon(is_151, is_152, n);
        }
    }

    public int xs(int i, int i_161)
    {
        if(i_161 < m.cz)
            i_161 = m.cz;
        return ((i_161 - m.focus_point) * (m.cx - i)) / i_161 + i;
    }

    public int ys(int i, int i_162)
    {
        if(i_162 < m.cz)
            i_162 = m.cz;
        return ((i_162 - m.focus_point) * (m.cy - i)) / i_162 + i;
    }

    public void rot(int is[], int is_163[], int i, int i_164, int i_165, int i_166)
    {
        if(i_165 != 0)
        {
            for(int i_167 = 0; i_167 < i_166; i_167++)
            {
                int i_168 = is[i_167];
                int i_169 = is_163[i_167];
                is[i_167] = i + (int)((float)(i_168 - i) * m.cos(i_165) - (float)(i_169 - i_164) * m.sin(i_165));
                is_163[i_167] = i_164 + (int)((float)(i_168 - i) * m.sin(i_165) + (float)(i_169 - i_164) * m.cos(i_165));
            }

        }
    }

    public int spy(int i, int i_170)
    {
        return (int)Math.sqrt((i - m.cx) * (i - m.cx) + i_170 * i_170);
    }

    Medium m;
    Trackers t;
    int ox[];
    int oy[];
    int oz[];
    int n;
    int c[];
    int oc[];
    float hsb[];
    int glass;
    int gr;
    int fs;
    int disline;
    boolean road;
    boolean solo;
    int light;
    int master;
    int wx;
    int wz;
    int wy;
    float deltaf;
    float projf;
    int av;
    int bfase;
    boolean nocol;
    int chip;
    float ctmag;
    int cxz;
    int cxy;
    int czy;
    int cox[];
    int coz[];
    int coy[];
    int dx;
    int dy;
    int dz;
    int vx;
    int vy;
    int vz;
    int embos;
    int typ;
    int pa;
    int pb;
    int flx;
    int colnum;
}
