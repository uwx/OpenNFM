// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContO.java

import java.awt.*;
import java.io.*;
import java.util.Random;

public class ContO
{

    public ContO(byte is[], Medium medium, Trackers trackers)
    {
        npl = 0;
        x = 0;
        y = 0;
        z = 0;
        xz = 0;
        xy = 0;
        zy = 0;
        wxz = 0;
        wzy = 0;
        dist = 0;
        maxR = 0;
        disp = 0;
        disline = 14;
        shadow = false;
        noline = false;
        decor = false;
        grounded = 1.0F;
        grat = 0;
        keyx = new int[4];
        keyz = new int[4];
        sprkat = 0;
        tnt = 0;
        ust = 0;
        srx = 0;
        sry = 0;
        srz = 0;
        rcx = 0.0F;
        rcy = 0.0F;
        rcz = 0.0F;
        sprk = 0;
        elec = false;
        roted = false;
        edl = new int[4];
        edr = new int[4];
        fix = false;
        fcnt = 0;
        checkpoint = 0;
        colok = 0;
        errd = false;
        err = "";
        roofat = 0;
        wh = 0;
        m = medium;
        t = trackers;
        p = new Plane[286];
        int is_0[] = new int[286];
        for(int i = 0; i < 286; i++)
            is_0[i] = 0;

        if(m.loadnew)
        {
            for(int i = 0; i < 4; i++)
                keyz[i] = 0;

            shadow = true;
        }
        String string = "";
        boolean bool = false;
        boolean bool_1 = false;
        int i = 0;
        float f = 1.0F;
        float f_2 = 1.0F;
        float fs[] = {
            1.0F, 1.0F, 1.0F
        };
        int is_3[] = new int[100];
        int is_4[] = new int[100];
        int is_5[] = new int[100];
        int is_6[] = {
            0, 0, 0
        };
        boolean bool_7 = false;
        Wheels wheels = new Wheels();
        boolean bool_8 = false;
        int i_9 = 0;
        int i_10 = 1;
        int i_11 = 0;
        int i_12 = 0;
        int i_13 = 0;
        int i_14 = 0;
        boolean bool_15 = false;
        boolean bool_16 = false;
        try
        {
            DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(is));
            do
            {
                String string_17;
                if((string_17 = datainputstream.readLine()) == null)
                    break;
                string = (new StringBuilder()).append("").append(string_17.trim()).toString();
                if(npl < 210)
                {
                    if(string.startsWith("<p>"))
                    {
                        bool = true;
                        i = 0;
                        i_10 = 0;
                        i_11 = 0;
                        i_13 = 0;
                        is_0[npl] = 1;
                        if(!bool_16)
                            bool_15 = false;
                    }
                    if(bool)
                    {
                        if(string.startsWith("gr("))
                            i_10 = getvalue("gr", string, 0);
                        if(string.startsWith("fs("))
                        {
                            i_11 = getvalue("fs", string, 0);
                            is_0[npl] = 2;
                        }
                        if(string.startsWith("c("))
                        {
                            i_14 = 0;
                            is_6[0] = getvalue("c", string, 0);
                            is_6[1] = getvalue("c", string, 1);
                            is_6[2] = getvalue("c", string, 2);
                        }
                        if(string.startsWith("glass"))
                            i_14 = 1;
                        if(string.startsWith("gshadow"))
                            i_14 = 2;
                        if(string.startsWith("lightF"))
                            i_13 = 1;
                        if(string.startsWith("light"))
                            i_13 = 1;
                        if(string.startsWith("lightB"))
                            i_13 = 2;
                        if(string.startsWith("noOutline"))
                            bool_15 = true;
                        if(string.startsWith("p("))
                        {
                            is_3[i] = (int)((float)getvalue("p", string, 0) * f * f_2 * fs[0]);
                            is_4[i] = (int)((float)getvalue("p", string, 1) * f * fs[1]);
                            is_5[i] = (int)((float)getvalue("p", string, 2) * f * fs[2]);
                            int i_18 = (int)Math.sqrt(is_3[i] * is_3[i] + is_4[i] * is_4[i] + is_5[i] * is_5[i]);
                            if(i_18 > maxR)
                                maxR = i_18;
                            i++;
                        }
                    }
                    if(string.startsWith("</p>"))
                    {
                        p[npl] = new Plane(m, t, is_3, is_5, is_4, i, is_6, i_14, i_10, i_11, 0, 0, 0, disline, 0, bool_7, i_13, bool_15);
                        if(is_6[0] == fcol[0] && is_6[1] == fcol[1] && is_6[2] == fcol[2] && i_14 == 0)
                            p[npl].colnum = 1;
                        if(is_6[0] == scol[0] && is_6[1] == scol[1] && is_6[2] == scol[2] && i_14 == 0)
                            p[npl].colnum = 2;
                        npl++;
                        bool = false;
                    }
                }
                if(string.startsWith("rims("))
                    wheels.setrims(getvalue("rims", string, 0), getvalue("rims", string, 1), getvalue("rims", string, 2), getvalue("rims", string, 3), getvalue("rims", string, 4));
                if(string.startsWith("w(") && i_9 < 4)
                {
                    keyx[i_9] = (int)((float)getvalue("w", string, 0) * f * fs[0]);
                    keyz[i_9] = (int)((float)getvalue("w", string, 2) * f * fs[2]);
                    wheels.make(m, t, p, npl, (int)((float)getvalue("w", string, 0) * f * f_2 * fs[0]), (int)((float)getvalue("w", string, 1) * f * fs[1]), (int)((float)getvalue("w", string, 2) * f * fs[2]), getvalue("w", string, 3), (int)((float)getvalue("w", string, 4) * f * f_2), (int)((float)getvalue("w", string, 5) * f), i_12);
                    npl += 19;
                    if(m.loadnew)
                    {
                        wh += (int)((float)getvalue("w", string, 5) * f);
                        if(wheels.ground > 140)
                        {
                            String string_19 = "FRONT";
                            if(keyz[i_9] < 0)
                                string_19 = "BACK";
                            err = (new StringBuilder()).append("Wheels Error:\n").append(string_19).append(" Wheels floor is too far below the center of Y Axis of the car!    \n\nPlease decrease the Y value of the ").append(string_19).append(" Wheels or decrease its height.     \n \n").toString();
                            errd = true;
                            keyz[i_9] = 0;
                            keyx[i_9] = 0;
                        }
                        if(wheels.ground < -100)
                        {
                            String string_20 = "FRONT";
                            if(keyz[i_9] < 0)
                                string_20 = "BACK";
                            err = (new StringBuilder()).append("Wheels Error:\n").append(string_20).append(" Wheels floor is too far above the center of Y Axis of the car!    \n\nPlease increase the Y value of the ").append(string_20).append(" Wheels or increase its height.     \n \n").toString();
                            errd = true;
                            keyz[i_9] = 0;
                            keyx[i_9] = 0;
                        }
                        if(Math.abs(keyx[i_9]) > 400)
                        {
                            String string_21 = "FRONT";
                            if(keyz[i_9] < 0)
                                string_21 = "BACK";
                            err = (new StringBuilder()).append("Wheels Error:\n").append(string_21).append(" Wheels are too far apart!    \n\nPlease decrease the \261X value of the ").append(string_21).append(" Wheels.     \n \n").toString();
                            errd = true;
                            keyz[i_9] = 0;
                            keyx[i_9] = 0;
                        }
                        if(Math.abs(keyz[i_9]) > 700)
                        {
                            if(keyz[i_9] < 0)
                                err = "Wheels Error:\nBACK Wheels are too far backwards from the center of the Z Axis!    \n\nPlease increase the -Z value of the BACK Wheels.     \n \n";
                            else
                                err = "Wheels Error:\nFRONT Wheels are too far forwards from the center of the Z Axis!    \n\nPlease decrease the +Z value of the FRONT Wheels.     \n \n";
                            errd = true;
                            keyz[i_9] = 0;
                            keyx[i_9] = 0;
                        }
                        if((int)((float)getvalue("w", string, 4) * f * f_2) > 300)
                        {
                            String string_22 = "FRONT";
                            if(keyz[i_9] < 0)
                                string_22 = "BACK";
                            err = (new StringBuilder()).append("Wheels Error:\nWidth of the ").append(string_22).append(" Wheels is too large!    \n\nPlease decrease the width of the ").append(string_22).append(" Wheels.     \n \n").toString();
                            errd = true;
                            keyz[i_9] = 0;
                            keyx[i_9] = 0;
                        }
                    }
                    i_9++;
                }
                if(string.startsWith("tracks"))
                {
                    int i_23 = getvalue("tracks", string, 0);
                    txy = new int[i_23];
                    tzy = new int[i_23];
                    tc = new int[i_23][3];
                    tradx = new int[i_23];
                    tradz = new int[i_23];
                    trady = new int[i_23];
                    tx = new int[i_23];
                    ty = new int[i_23];
                    tz = new int[i_23];
                    skd = new int[i_23];
                    dam = new int[i_23];
                    notwall = new boolean[i_23];
                    bool_8 = true;
                }
                if(bool_8)
                {
                    if(string.startsWith("<track>"))
                    {
                        bool_1 = true;
                        notwall[tnt] = false;
                        dam[tnt] = 1;
                        skd[tnt] = 0;
                        ty[tnt] = 0;
                        tx[tnt] = 0;
                        tz[tnt] = 0;
                        txy[tnt] = 0;
                        tzy[tnt] = 0;
                        trady[tnt] = 0;
                        tradx[tnt] = 0;
                        tradz[tnt] = 0;
                        tc[tnt][0] = 0;
                        tc[tnt][1] = 0;
                        tc[tnt][2] = 0;
                    }
                    if(bool_1)
                    {
                        if(string.startsWith("c"))
                        {
                            tc[tnt][0] = getvalue("c", string, 0);
                            tc[tnt][1] = getvalue("c", string, 1);
                            tc[tnt][2] = getvalue("c", string, 2);
                        }
                        if(string.startsWith("xy"))
                            txy[tnt] = getvalue("xy", string, 0);
                        if(string.startsWith("zy"))
                            tzy[tnt] = getvalue("zy", string, 0);
                        if(string.startsWith("radx"))
                            tradx[tnt] = (int)((float)getvalue("radx", string, 0) * f);
                        if(string.startsWith("rady"))
                            trady[tnt] = (int)((float)getvalue("rady", string, 0) * f);
                        if(string.startsWith("radz"))
                            tradz[tnt] = (int)((float)getvalue("radz", string, 0) * f);
                        if(string.startsWith("ty"))
                            ty[tnt] = (int)((float)getvalue("ty", string, 0) * f);
                        if(string.startsWith("tx"))
                            tx[tnt] = (int)((float)getvalue("tx", string, 0) * f);
                        if(string.startsWith("tz"))
                            tz[tnt] = (int)((float)getvalue("tz", string, 0) * f);
                        if(string.startsWith("skid"))
                            skd[tnt] = getvalue("skid", string, 0);
                        if(string.startsWith("dam"))
                            dam[tnt] = 3;
                        if(string.startsWith("notwall"))
                            notwall[tnt] = true;
                    }
                    if(string.startsWith("</track>"))
                    {
                        bool_1 = false;
                        tnt++;
                    }
                }
                if(string.startsWith("disp("))
                    disp = getvalue("disp", string, 0);
                if(string.startsWith("disline("))
                    disline = getvalue("disline", string, 0) * 2;
                if(string.startsWith("shadow"))
                    shadow = true;
                if(string.startsWith("stonecold"))
                    noline = true;
                if(string.startsWith("newstone"))
                {
                    noline = true;
                    bool_15 = true;
                    bool_16 = true;
                }
                if(string.startsWith("decorative"))
                    decor = true;
                if(string.startsWith("road"))
                    bool_7 = true;
                if(string.startsWith("notroad"))
                    bool_7 = false;
                if(string.startsWith("grounded("))
                    grounded = (float)getvalue("grounded", string, 0) / 100F;
                if(string.startsWith("div("))
                    f = (float)getvalue("div", string, 0) / 10F;
                if(string.startsWith("idiv("))
                    f = (float)getvalue("idiv", string, 0) / 100F;
                if(string.startsWith("iwid("))
                    f_2 = (float)getvalue("iwid", string, 0) / 100F;
                if(string.startsWith("ScaleX("))
                    fs[0] = (float)getvalue("ScaleX", string, 0) / 100F;
                if(string.startsWith("ScaleY("))
                    fs[1] = (float)getvalue("ScaleY", string, 0) / 100F;
                if(string.startsWith("ScaleZ("))
                    fs[2] = (float)getvalue("ScaleZ", string, 0) / 100F;
                if(string.startsWith("gwgr("))
                {
                    i_12 = getvalue("gwgr", string, 0);
                    if(m.loadnew)
                    {
                        if(i_12 > 40)
                            i_12 = 40;
                        if(i_12 < 0 && i_12 >= -15)
                            i_12 = -16;
                        if(i_12 < -40)
                            i_12 = -40;
                    }
                }
                if(string.startsWith("1stColor("))
                {
                    fcol[0] = getvalue("1stColor", string, 0);
                    fcol[1] = getvalue("1stColor", string, 1);
                    fcol[2] = getvalue("1stColor", string, 2);
                    colok++;
                }
                if(string.startsWith("2ndColor("))
                {
                    scol[0] = getvalue("2ndColor", string, 0);
                    scol[1] = getvalue("2ndColor", string, 1);
                    scol[2] = getvalue("2ndColor", string, 2);
                    colok++;
                }
            } while(true);
            datainputstream.close();
        }
        catch(Exception exception)
        {
            if(!errd)
            {
                err = (new StringBuilder()).append("Error While Loading 3D Model\n\nLine:     ").append(string).append("\n\nError Detail:\n").append(exception).append("           \n \n").toString();
                System.out.println(err);
                errd = true;
            }
        }
        grat = wheels.ground;
        sprkat = wheels.sparkat;
        if(shadow)
        {
            stg = new int[20];
            rtg = new int[100];
            for(int i_24 = 0; i_24 < 20; i_24++)
                stg[i_24] = 0;

            for(int i_25 = 0; i_25 < 100; i_25++)
                rtg[i_25] = 0;

        }
        if(m.loadnew)
        {
            if(i_9 != 0)
                wh = wh / i_9;
            boolean bool_26 = false;
            for(int i_27 = 0; i_27 < npl; i_27++)
            {
                int i_28 = 0;
                int i_29 = p[i_27].ox[0];
                int i_30 = p[i_27].ox[0];
                int i_31 = p[i_27].oy[0];
                int i_32 = p[i_27].oy[0];
                int i_33 = p[i_27].oz[0];
                int i_34 = p[i_27].oz[0];
                for(int i_35 = 0; i_35 < p[i_27].n; i_35++)
                {
                    if(p[i_27].ox[i_35] > i_29)
                        i_29 = p[i_27].ox[i_35];
                    if(p[i_27].ox[i_35] < i_30)
                        i_30 = p[i_27].ox[i_35];
                    if(p[i_27].oy[i_35] > i_31)
                        i_31 = p[i_27].oy[i_35];
                    if(p[i_27].oy[i_35] < i_32)
                        i_32 = p[i_27].oy[i_35];
                    if(p[i_27].oz[i_35] > i_33)
                        i_33 = p[i_27].oz[i_35];
                    if(p[i_27].oz[i_35] < i_34)
                        i_34 = p[i_27].oz[i_35];
                }

                if(Math.abs(i_29 - i_30) <= Math.abs(i_31 - i_32) && Math.abs(i_29 - i_30) <= Math.abs(i_33 - i_34))
                    i_28 = 1;
                if(Math.abs(i_31 - i_32) <= Math.abs(i_29 - i_30) && Math.abs(i_31 - i_32) <= Math.abs(i_33 - i_34))
                    i_28 = 2;
                if(Math.abs(i_33 - i_34) <= Math.abs(i_29 - i_30) && Math.abs(i_33 - i_34) <= Math.abs(i_31 - i_32))
                    i_28 = 3;
                if(i_28 == 2 && (!bool_26 || (i_31 + i_32) / 2 < roofat))
                {
                    roofat = (i_31 + i_32) / 2;
                    bool_26 = true;
                }
                if(is_0[i_27] != 1)
                    continue;
                int i_36 = 1000;
                int i_37 = 0;
                for(int i_38 = 0; i_38 < p[i_27].n; i_38++)
                {
                    int i_39 = i_38 + 1;
                    if(i_39 >= p[i_27].n)
                        i_39 -= p[i_27].n;
                    int i_40 = i_38 + 2;
                    if(i_40 >= p[i_27].n)
                        i_40 -= p[i_27].n;
                    if(i_28 == 1)
                    {
                        int i_41 = Math.abs((int)(Math.atan((double)(p[i_27].oz[i_38] - p[i_27].oz[i_39]) / (double)(p[i_27].oy[i_38] - p[i_27].oy[i_39])) / 0.017453292519943295D));
                        int i_42 = Math.abs((int)(Math.atan((double)(p[i_27].oz[i_40] - p[i_27].oz[i_39]) / (double)(p[i_27].oy[i_40] - p[i_27].oy[i_39])) / 0.017453292519943295D));
                        if(i_41 > 45)
                            i_41 = 90 - i_41;
                        else
                            i_42 = 90 - i_42;
                        if(i_41 + i_42 < i_36)
                        {
                            i_36 = i_41 + i_42;
                            i_37 = i_38;
                        }
                    }
                    if(i_28 == 2)
                    {
                        int i_43 = Math.abs((int)(Math.atan((double)(p[i_27].oz[i_38] - p[i_27].oz[i_39]) / (double)(p[i_27].ox[i_38] - p[i_27].ox[i_39])) / 0.017453292519943295D));
                        int i_44 = Math.abs((int)(Math.atan((double)(p[i_27].oz[i_40] - p[i_27].oz[i_39]) / (double)(p[i_27].ox[i_40] - p[i_27].ox[i_39])) / 0.017453292519943295D));
                        if(i_43 > 45)
                            i_43 = 90 - i_43;
                        else
                            i_44 = 90 - i_44;
                        if(i_43 + i_44 < i_36)
                        {
                            i_36 = i_43 + i_44;
                            i_37 = i_38;
                        }
                    }
                    if(i_28 != 3)
                        continue;
                    int i_45 = Math.abs((int)(Math.atan((double)(p[i_27].oy[i_38] - p[i_27].oy[i_39]) / (double)(p[i_27].ox[i_38] - p[i_27].ox[i_39])) / 0.017453292519943295D));
                    int i_46 = Math.abs((int)(Math.atan((double)(p[i_27].oy[i_40] - p[i_27].oy[i_39]) / (double)(p[i_27].ox[i_40] - p[i_27].ox[i_39])) / 0.017453292519943295D));
                    if(i_45 > 45)
                        i_45 = 90 - i_45;
                    else
                        i_46 = 90 - i_46;
                    if(i_45 + i_46 < i_36)
                    {
                        i_36 = i_45 + i_46;
                        i_37 = i_38;
                    }
                }

                if(i_37 != 0)
                {
                    int is_47[] = new int[p[i_27].n];
                    int is_48[] = new int[p[i_27].n];
                    int is_49[] = new int[p[i_27].n];
                    for(int i_50 = 0; i_50 < p[i_27].n; i_50++)
                    {
                        is_47[i_50] = p[i_27].ox[i_50];
                        is_48[i_50] = p[i_27].oy[i_50];
                        is_49[i_50] = p[i_27].oz[i_50];
                    }

                    for(int i_51 = 0; i_51 < p[i_27].n; i_51++)
                    {
                        int i_52 = i_51 + i_37;
                        if(i_52 >= p[i_27].n)
                            i_52 -= p[i_27].n;
                        p[i_27].ox[i_51] = is_47[i_52];
                        p[i_27].oy[i_51] = is_48[i_52];
                        p[i_27].oz[i_51] = is_49[i_52];
                    }

                }
                if(i_28 == 1)
                    if(Math.abs(p[i_27].oz[0] - p[i_27].oz[1]) > Math.abs(p[i_27].oy[0] - p[i_27].oy[1]))
                    {
                        if(p[i_27].oz[0] > p[i_27].oz[1])
                        {
                            if(p[i_27].oy[1] > p[i_27].oy[2])
                                p[i_27].fs = 1;
                            else
                                p[i_27].fs = -1;
                        } else
                        if(p[i_27].oy[1] > p[i_27].oy[2])
                            p[i_27].fs = -1;
                        else
                            p[i_27].fs = 1;
                    } else
                    if(p[i_27].oy[0] > p[i_27].oy[1])
                    {
                        if(p[i_27].oz[1] > p[i_27].oz[2])
                            p[i_27].fs = -1;
                        else
                            p[i_27].fs = 1;
                    } else
                    if(p[i_27].oz[1] > p[i_27].oz[2])
                        p[i_27].fs = 1;
                    else
                        p[i_27].fs = -1;
                if(i_28 == 2)
                    if(Math.abs(p[i_27].oz[0] - p[i_27].oz[1]) > Math.abs(p[i_27].ox[0] - p[i_27].ox[1]))
                    {
                        if(p[i_27].oz[0] > p[i_27].oz[1])
                        {
                            if(p[i_27].ox[1] > p[i_27].ox[2])
                                p[i_27].fs = -1;
                            else
                                p[i_27].fs = 1;
                        } else
                        if(p[i_27].ox[1] > p[i_27].ox[2])
                            p[i_27].fs = 1;
                        else
                            p[i_27].fs = -1;
                    } else
                    if(p[i_27].ox[0] > p[i_27].ox[1])
                    {
                        if(p[i_27].oz[1] > p[i_27].oz[2])
                            p[i_27].fs = 1;
                        else
                            p[i_27].fs = -1;
                    } else
                    if(p[i_27].oz[1] > p[i_27].oz[2])
                        p[i_27].fs = -1;
                    else
                        p[i_27].fs = 1;
                if(i_28 == 3)
                    if(Math.abs(p[i_27].oy[0] - p[i_27].oy[1]) > Math.abs(p[i_27].ox[0] - p[i_27].ox[1]))
                    {
                        if(p[i_27].oy[0] > p[i_27].oy[1])
                        {
                            if(p[i_27].ox[1] > p[i_27].ox[2])
                                p[i_27].fs = 1;
                            else
                                p[i_27].fs = -1;
                        } else
                        if(p[i_27].ox[1] > p[i_27].ox[2])
                            p[i_27].fs = -1;
                        else
                            p[i_27].fs = 1;
                    } else
                    if(p[i_27].ox[0] > p[i_27].ox[1])
                    {
                        if(p[i_27].oy[1] > p[i_27].oy[2])
                            p[i_27].fs = -1;
                        else
                            p[i_27].fs = 1;
                    } else
                    if(p[i_27].oy[1] > p[i_27].oy[2])
                        p[i_27].fs = 1;
                    else
                        p[i_27].fs = -1;
                boolean bool_53 = false;
                boolean bool_54 = false;
                int i_55 = 0;
                do
                {
                    if(i_55 >= npl)
                        break;
                    if(i_55 != i_27 && is_0[i_55] != 0)
                    {
                        boolean bool_56 = false;
                        int i_57 = p[i_55].ox[0];
                        int i_58 = p[i_55].ox[0];
                        int i_59 = p[i_55].oy[0];
                        int i_60 = p[i_55].oy[0];
                        int i_61 = p[i_55].oz[0];
                        int i_62 = p[i_55].oz[0];
                        for(int i_63 = 0; i_63 < p[i_55].n; i_63++)
                        {
                            if(p[i_55].ox[i_63] > i_57)
                                i_57 = p[i_55].ox[i_63];
                            if(p[i_55].ox[i_63] < i_58)
                                i_58 = p[i_55].ox[i_63];
                            if(p[i_55].oy[i_63] > i_59)
                                i_59 = p[i_55].oy[i_63];
                            if(p[i_55].oy[i_63] < i_60)
                                i_60 = p[i_55].oy[i_63];
                            if(p[i_55].oz[i_63] > i_61)
                                i_61 = p[i_55].oz[i_63];
                            if(p[i_55].oz[i_63] < i_62)
                                i_62 = p[i_55].oz[i_63];
                        }

                        int i_64 = (i_57 + i_58) / 2;
                        int i_65 = (i_59 + i_60) / 2;
                        int i_66 = (i_61 + i_62) / 2;
                        int i_67 = (i_29 + i_30) / 2;
                        int i_68 = (i_31 + i_32) / 2;
                        int i_69 = (i_33 + i_34) / 2;
                        if(i_28 == 1 && (i_65 <= i_31 && i_65 >= i_32 && i_66 <= i_33 && i_66 >= i_34 || i_68 <= i_59 && i_68 >= i_60 && i_69 <= i_61 && i_69 >= i_62))
                        {
                            if(i_57 < i_30)
                                bool_53 = true;
                            if(i_58 > i_29)
                                bool_54 = true;
                        }
                        if(i_28 == 2 && (i_64 <= i_29 && i_64 >= i_30 && i_66 <= i_33 && i_66 >= i_34 || i_67 <= i_57 && i_67 >= i_58 && i_69 <= i_61 && i_69 >= i_62))
                        {
                            if(i_59 < i_32)
                                bool_53 = true;
                            if(i_60 > i_31)
                                bool_54 = true;
                        }
                        if(i_28 == 3 && (i_64 <= i_29 && i_64 >= i_30 && i_65 <= i_31 && i_65 >= i_32 || i_67 <= i_57 && i_67 >= i_58 && i_68 <= i_59 && i_68 >= i_60))
                        {
                            if(i_61 < i_34)
                                bool_53 = true;
                            if(i_62 > i_33)
                                bool_54 = true;
                        }
                    }
                    if(bool_53 && bool_54)
                        break;
                    i_55++;
                } while(true);
                boolean bool_70 = false;
                if(bool_53 && !bool_54)
                    bool_70 = true;
                if(bool_54 && !bool_53)
                {
                    p[i_27].fs *= -1;
                    bool_70 = true;
                }
                if(bool_53 && bool_54)
                {
                    p[i_27].fs = 0;
                    p[i_27].gr = 40;
                    bool_70 = true;
                }
                if(!bool_70)
                {
                    int i_71 = 0;
                    int i_72 = 0;
                    if(i_28 == 1)
                    {
                        i_71 = (i_29 + i_30) / 2;
                        i_72 = i_71;
                    }
                    if(i_28 == 2)
                    {
                        i_71 = (i_31 + i_32) / 2;
                        i_72 = i_71;
                    }
                    if(i_28 == 3)
                    {
                        i_71 = (i_33 + i_34) / 2;
                        i_72 = i_71;
                    }
                    for(int i_73 = 0; i_73 < npl; i_73++)
                    {
                        if(i_73 == i_27)
                            continue;
                        boolean bool_74 = false;
                        boolean bools[] = new boolean[p[i_73].n];
                        for(int i_75 = 0; i_75 < p[i_73].n; i_75++)
                        {
                            bools[i_75] = false;
                            for(int i_76 = 0; i_76 < p[i_27].n; i_76++)
                                if(p[i_27].ox[i_76] == p[i_73].ox[i_75] && p[i_27].oy[i_76] == p[i_73].oy[i_75] && p[i_27].oz[i_76] == p[i_73].oz[i_75])
                                {
                                    bools[i_75] = true;
                                    bool_74 = true;
                                }

                        }

                        if(!bool_74)
                            continue;
                        for(int i_77 = 0; i_77 < p[i_73].n; i_77++)
                        {
                            if(bools[i_77])
                                continue;
                            if(i_28 == 1)
                            {
                                if(p[i_73].ox[i_77] > i_71)
                                    i_71 = p[i_73].ox[i_77];
                                if(p[i_73].ox[i_77] < i_72)
                                    i_72 = p[i_73].ox[i_77];
                            }
                            if(i_28 == 2)
                            {
                                if(p[i_73].oy[i_77] > i_71)
                                    i_71 = p[i_73].oy[i_77];
                                if(p[i_73].oy[i_77] < i_72)
                                    i_72 = p[i_73].oy[i_77];
                            }
                            if(i_28 != 3)
                                continue;
                            if(p[i_73].oz[i_77] > i_71)
                                i_71 = p[i_73].oz[i_77];
                            if(p[i_73].oz[i_77] < i_72)
                                i_72 = p[i_73].oz[i_77];
                        }

                    }

                    if(i_28 == 1)
                        if((i_71 + i_72) / 2 > (i_29 + i_30) / 2)
                            p[i_27].fs *= -1;
                        else
                        if((i_71 + i_72) / 2 == (i_29 + i_30) / 2 && (i_29 + i_30) / 2 < 0)
                            p[i_27].fs *= -1;
                    if(i_28 == 2)
                        if((i_71 + i_72) / 2 > (i_31 + i_32) / 2)
                            p[i_27].fs *= -1;
                        else
                        if((i_71 + i_72) / 2 == (i_31 + i_32) / 2 && (i_31 + i_32) / 2 < 0)
                            p[i_27].fs *= -1;
                    if(i_28 == 3)
                        if((i_71 + i_72) / 2 > (i_33 + i_34) / 2)
                            p[i_27].fs *= -1;
                        else
                        if((i_71 + i_72) / 2 == (i_33 + i_34) / 2 && (i_33 + i_34) / 2 < 0)
                            p[i_27].fs *= -1;
                }
                p[i_27].deltafntyp();
            }

        }
    }

    public ContO(ContO conto_78, int i, int i_79, int i_80, int i_81)
    {
        npl = 0;
        x = 0;
        y = 0;
        z = 0;
        xz = 0;
        xy = 0;
        zy = 0;
        wxz = 0;
        wzy = 0;
        dist = 0;
        maxR = 0;
        disp = 0;
        disline = 14;
        shadow = false;
        noline = false;
        decor = false;
        grounded = 1.0F;
        grat = 0;
        keyx = new int[4];
        keyz = new int[4];
        sprkat = 0;
        tnt = 0;
        ust = 0;
        srx = 0;
        sry = 0;
        srz = 0;
        rcx = 0.0F;
        rcy = 0.0F;
        rcz = 0.0F;
        sprk = 0;
        elec = false;
        roted = false;
        edl = new int[4];
        edr = new int[4];
        fix = false;
        fcnt = 0;
        checkpoint = 0;
        colok = 0;
        errd = false;
        err = "";
        roofat = 0;
        wh = 0;
        m = conto_78.m;
        t = conto_78.t;
        npl = conto_78.npl;
        maxR = conto_78.maxR;
        disp = conto_78.disp;
        disline = conto_78.disline;
        noline = conto_78.noline;
        shadow = conto_78.shadow;
        grounded = conto_78.grounded;
        decor = conto_78.decor;
        if(m.loadnew && (i_81 == 90 || i_81 == -90))
            grounded += 10000F;
        grat = conto_78.grat;
        sprkat = conto_78.sprkat;
        p = new Plane[conto_78.npl];
        for(int i_82 = 0; i_82 < npl; i_82++)
        {
            if(conto_78.p[i_82].master == 1)
                conto_78.p[i_82].n = 20;
            p[i_82] = new Plane(m, t, conto_78.p[i_82].ox, conto_78.p[i_82].oz, conto_78.p[i_82].oy, conto_78.p[i_82].n, conto_78.p[i_82].oc, conto_78.p[i_82].glass, conto_78.p[i_82].gr, conto_78.p[i_82].fs, conto_78.p[i_82].wx, conto_78.p[i_82].wy, conto_78.p[i_82].wz, conto_78.disline, conto_78.p[i_82].bfase, conto_78.p[i_82].road, conto_78.p[i_82].light, conto_78.p[i_82].solo);
        }

        x = i;
        y = i_79;
        z = i_80;
        xz = 0;
        xy = 0;
        zy = 0;
        for(int i_83 = 0; i_83 < npl; i_83++)
        {
            p[i_83].colnum = conto_78.p[i_83].colnum;
            p[i_83].master = conto_78.p[i_83].master;
            p[i_83].rot(p[i_83].ox, p[i_83].oz, 0, 0, i_81, p[i_83].n);
            p[i_83].loadprojf();
        }

        if(conto_78.tnt != 0)
        {
            for(int i_84 = 0; i_84 < conto_78.tnt; i_84++)
            {
                t.xy[t.nt] = (int)((float)conto_78.txy[i_84] * m.cos(i_81) - (float)conto_78.tzy[i_84] * m.sin(i_81));
                t.zy[t.nt] = (int)((float)conto_78.tzy[i_84] * m.cos(i_81) + (float)conto_78.txy[i_84] * m.sin(i_81));
                for(int i_85 = 0; i_85 < 3; i_85++)
                {
                    t.c[t.nt][i_85] = (int)((float)conto_78.tc[i_84][i_85] + (float)conto_78.tc[i_84][i_85] * ((float)m.snap[i_85] / 100F));
                    if(t.c[t.nt][i_85] > 255)
                        t.c[t.nt][i_85] = 255;
                    if(t.c[t.nt][i_85] < 0)
                        t.c[t.nt][i_85] = 0;
                }

                t.x[t.nt] = (int)(((float)x + (float)conto_78.tx[i_84] * m.cos(i_81)) - (float)conto_78.tz[i_84] * m.sin(i_81));
                t.z[t.nt] = (int)((float)z + (float)conto_78.tz[i_84] * m.cos(i_81) + (float)conto_78.tx[i_84] * m.sin(i_81));
                t.y[t.nt] = y + conto_78.ty[i_84];
                t.skd[t.nt] = conto_78.skd[i_84];
                t.dam[t.nt] = conto_78.dam[i_84];
                t.notwall[t.nt] = conto_78.notwall[i_84];
                if(decor)
                    t.decor[t.nt] = true;
                else
                    t.decor[t.nt] = false;
                int i_86 = Math.abs(i_81);
                if(i_86 == 180)
                    i_86 = 0;
                t.radx[t.nt] = (int)Math.abs((float)conto_78.tradx[i_84] * m.cos(i_86) + (float)conto_78.tradz[i_84] * m.sin(i_86));
                t.radz[t.nt] = (int)Math.abs((float)conto_78.tradx[i_84] * m.sin(i_86) + (float)conto_78.tradz[i_84] * m.cos(i_86));
                t.rady[t.nt] = conto_78.trady[i_84];
                t.nt++;
            }

        }
        for(int i_87 = 0; i_87 < 4; i_87++)
        {
            keyx[i_87] = conto_78.keyx[i_87];
            keyz[i_87] = conto_78.keyz[i_87];
        }

        if(shadow)
        {
            stg = new int[20];
            sx = new int[20];
            sy = new int[20];
            sz = new int[20];
            scx = new int[20];
            scz = new int[20];
            osmag = new float[20];
            sav = new int[20];
            smag = new float[20][8];
            srgb = new int[20][3];
            sbln = new float[20];
            ust = 0;
            for(int i_88 = 0; i_88 < 20; i_88++)
                stg[i_88] = 0;

            rtg = new int[100];
            rbef = new boolean[100];
            rx = new int[100];
            ry = new int[100];
            rz = new int[100];
            vrx = new float[100];
            vry = new float[100];
            vrz = new float[100];
            for(int i_89 = 0; i_89 < 100; i_89++)
                rtg[i_89] = 0;

        }
    }

    public ContO(int i, int i_90, int i_91, Medium medium, Trackers trackers, int i_92, int i_93, 
            int i_94)
    {
        npl = 0;
        x = 0;
        y = 0;
        z = 0;
        xz = 0;
        xy = 0;
        zy = 0;
        wxz = 0;
        wzy = 0;
        dist = 0;
        maxR = 0;
        disp = 0;
        disline = 14;
        shadow = false;
        noline = false;
        decor = false;
        grounded = 1.0F;
        grat = 0;
        keyx = new int[4];
        keyz = new int[4];
        sprkat = 0;
        tnt = 0;
        ust = 0;
        srx = 0;
        sry = 0;
        srz = 0;
        rcx = 0.0F;
        rcy = 0.0F;
        rcz = 0.0F;
        sprk = 0;
        elec = false;
        roted = false;
        edl = new int[4];
        edr = new int[4];
        fix = false;
        fcnt = 0;
        checkpoint = 0;
        colok = 0;
        errd = false;
        err = "";
        roofat = 0;
        wh = 0;
        m = medium;
        t = trackers;
        x = i_92;
        z = i_93;
        y = i_94;
        xz = 0;
        xy = 0;
        zy = 0;
        grat = 0;
        sprkat = 0;
        disline = 4;
        noline = true;
        shadow = false;
        grounded = 115F;
        decor = true;
        npl = 5;
        p = new Plane[5];
        Random random = new Random(i);
        int is[] = new int[8];
        int is_95[] = new int[8];
        int is_96[] = new int[8];
        int is_97[] = new int[8];
        int is_98[] = new int[8];
        float f = i_90;
        float f_99 = i_91;
        if(f_99 < 2.0F)
            f_99 = 2.0F;
        if(f_99 > 6F)
            f_99 = 6F;
        if(f < 2.0F)
            f = 2.0F;
        if(f > 6F)
            f = 6F;
        f /= 1.5F;
        f_99 /= 1.5F;
        f_99 *= 1.0F + (f - 2.0F) * 0.1786F;
        float f_100 = (float)(50D + 100D * random.nextDouble());
        is[0] = -(int)(f_100 * f * 0.7071F);
        is_95[0] = (int)(f_100 * f * 0.7071F);
        f_100 = (float)(50D + 100D * random.nextDouble());
        is[1] = 0;
        is_95[1] = (int)(f_100 * f);
        f_100 = (float)(50D + 100D * random.nextDouble());
        is[2] = (int)((double)(f_100 * f) * 0.70709999999999995D);
        is_95[2] = (int)((double)(f_100 * f) * 0.70709999999999995D);
        f_100 = (float)(50D + 100D * random.nextDouble());
        is[3] = (int)(f_100 * f);
        is_95[3] = 0;
        f_100 = (float)(50D + 100D * random.nextDouble());
        is[4] = (int)((double)(f_100 * f) * 0.70709999999999995D);
        is_95[4] = -(int)((double)(f_100 * f) * 0.70709999999999995D);
        f_100 = (float)(50D + 100D * random.nextDouble());
        is[5] = 0;
        is_95[5] = -(int)(f_100 * f);
        f_100 = (float)(50D + 100D * random.nextDouble());
        is[6] = -(int)((double)(f_100 * f) * 0.70709999999999995D);
        is_95[6] = -(int)((double)(f_100 * f) * 0.70709999999999995D);
        f_100 = (float)(50D + 100D * random.nextDouble());
        is[7] = -(int)(f_100 * f);
        is_95[7] = 0;
        for(int i_101 = 0; i_101 < 8; i_101++)
        {
            is_96[i_101] = (int)((double)is[i_101] * (0.20000000000000001D + 0.40000000000000002D * random.nextDouble()));
            is_97[i_101] = (int)((double)is_95[i_101] * (0.20000000000000001D + 0.40000000000000002D * random.nextDouble()));
            is_98[i_101] = -(int)((10D + 15D * random.nextDouble()) * (double)f_99);
        }

        maxR = 0;
        for(int i_102 = 0; i_102 < 8; i_102++)
        {
            int i_103 = i_102 - 1;
            if(i_103 == -1)
                i_103 = 7;
            int i_104 = i_102 + 1;
            if(i_104 == 8)
                i_104 = 0;
            is[i_102] = ((is[i_103] + is[i_104]) / 2 + is[i_102]) / 2;
            is_95[i_102] = ((is_95[i_103] + is_95[i_104]) / 2 + is_95[i_102]) / 2;
            is_96[i_102] = ((is_96[i_103] + is_96[i_104]) / 2 + is_96[i_102]) / 2;
            is_97[i_102] = ((is_97[i_103] + is_97[i_104]) / 2 + is_97[i_102]) / 2;
            is_98[i_102] = ((is_98[i_103] + is_98[i_104]) / 2 + is_98[i_102]) / 2;
            int i_105 = (int)Math.sqrt(is[i_102] * is[i_102] + is_95[i_102] * is_95[i_102]);
            if(i_105 > maxR)
                maxR = i_105;
            i_105 = (int)Math.sqrt(is_96[i_102] * is_96[i_102] + is_98[i_102] * is_98[i_102] + is_97[i_102] * is_97[i_102]);
            if(i_105 > maxR)
                maxR = i_105;
        }

        disp = maxR / 17;
        int is_106[] = new int[3];
        float f_107 = -1F;
        float f_108 = (f / f_99 - 0.33F) / 33.4F;
        if((double)f_108 < 0.0050000000000000001D)
            f_108 = 0.0F;
        if((double)f_108 > 0.057000000000000002D)
            f_108 = 0.057F;
        for(int i_109 = 0; i_109 < 4; i_109++)
        {
            int i_110 = i_109 * 2;
            int i_111 = i_110 + 2;
            if(i_111 == 8)
                i_111 = 0;
            int is_112[] = new int[6];
            int is_113[] = new int[6];
            int is_114[] = new int[6];
            is_112[0] = is[i_110];
            is_112[1] = is[i_110 + 1];
            is_112[2] = is[i_111];
            is_112[5] = is_96[i_110];
            is_112[4] = is_96[i_110 + 1];
            is_112[3] = is_96[i_111];
            is_114[0] = is_95[i_110];
            is_114[1] = is_95[i_110 + 1];
            is_114[2] = is_95[i_111];
            is_114[5] = is_97[i_110];
            is_114[4] = is_97[i_110 + 1];
            is_114[3] = is_97[i_111];
            is_113[0] = 0;
            is_113[1] = 0;
            is_113[2] = 0;
            is_113[5] = is_98[i_110];
            is_113[4] = is_98[i_110 + 1];
            is_113[3] = is_98[i_111];
            for(f_100 = (float)((0.17000000000000001D - (double)f_108) * random.nextDouble()); (double)Math.abs(f_107 - f_100) < 0.029999999999999999D - (double)(f_108 * 0.176F); f_100 = (float)((0.17000000000000001D - (double)f_108) * random.nextDouble()));
            f_107 = f_100;
            for(int i_115 = 0; i_115 < 3; i_115++)
                if(m.trk == 2)
                    is_106[i_115] = (int)(390F / ((2.2F + f_100) - f_108));
                else
                    is_106[i_115] = (int)((float)(m.cpol[i_115] + m.cgrnd[i_115]) / ((2.2F + f_100) - f_108));

            p[i_109] = new Plane(m, t, is_112, is_114, is_113, 6, is_106, 3, -8, 0, 0, 0, 0, disline, 0, true, 0, false);
        }

        f_100 = (float)(0.02D * random.nextDouble());
        for(int i_116 = 0; i_116 < 3; i_116++)
            if(m.trk == 2)
                is_106[i_116] = (int)(390F / (2.15F + f_100));
            else
                is_106[i_116] = (int)((float)(m.cpol[i_116] + m.cgrnd[i_116]) / (2.15F + f_100));

        p[4] = new Plane(m, t, is_96, is_97, is_98, 8, is_106, 3, -8, 0, 0, 0, 0, disline, 0, true, 0, false);
        int is_117[] = new int[2];
        int is_118[] = new int[2];
        for(int i_119 = 0; i_119 < 4; i_119++)
        {
            int i_120 = i_119 * 2 + 1;
            t.y[t.nt] = is_98[i_120] / 2;
            t.rady[t.nt] = Math.abs(is_98[i_120] / 2);
            if(i_119 == 0 || i_119 == 2)
            {
                t.z[t.nt] = (is_95[i_120] + is_97[i_120]) / 2;
                t.radz[t.nt] = Math.abs(t.z[t.nt] - is_95[i_120]);
                i_120 = i_119 * 2 + 2;
                if(i_120 == 8)
                    i_120 = 0;
                t.x[t.nt] = (is[i_119 * 2] + is[i_120]) / 2;
                t.radx[t.nt] = Math.abs(t.x[t.nt] - is[i_119 * 2]);
            } else
            {
                t.x[t.nt] = (is[i_120] + is_96[i_120]) / 2;
                t.radx[t.nt] = Math.abs(t.x[t.nt] - is[i_120]);
                i_120 = i_119 * 2 + 2;
                if(i_120 == 8)
                    i_120 = 0;
                t.z[t.nt] = (is_95[i_119 * 2] + is_95[i_120]) / 2;
                t.radz[t.nt] = Math.abs(t.z[t.nt] - is_95[i_119 * 2]);
            }
            if(i_119 == 0)
            {
                is_118[0] = t.z[t.nt] - t.radz[t.nt];
                t.zy[t.nt] = (int)(Math.atan((double)t.rady[t.nt] / (double)t.radz[t.nt]) / 0.017453292519943295D);
                if(t.zy[t.nt] > 40)
                    t.zy[t.nt] = 40;
                t.xy[t.nt] = 0;
            }
            if(i_119 == 1)
            {
                is_117[0] = t.x[t.nt] - t.radx[t.nt];
                t.xy[t.nt] = (int)(Math.atan((double)t.rady[t.nt] / (double)t.radx[t.nt]) / 0.017453292519943295D);
                if(t.xy[t.nt] > 40)
                    t.xy[t.nt] = 40;
                t.zy[t.nt] = 0;
            }
            if(i_119 == 2)
            {
                is_118[1] = t.z[t.nt] + t.radz[t.nt];
                t.zy[t.nt] = -(int)(Math.atan((double)t.rady[t.nt] / (double)t.radz[t.nt]) / 0.017453292519943295D);
                if(t.zy[t.nt] < -40)
                    t.zy[t.nt] = -40;
                t.xy[t.nt] = 0;
            }
            if(i_119 == 3)
            {
                is_117[1] = t.x[t.nt] + t.radx[t.nt];
                t.xy[t.nt] = -(int)(Math.atan((double)t.rady[t.nt] / (double)t.radx[t.nt]) / 0.017453292519943295D);
                if(t.xy[t.nt] < -40)
                    t.xy[t.nt] = -40;
                t.zy[t.nt] = 0;
            }
            t.x[t.nt] += x;
            t.z[t.nt] += z;
            t.y[t.nt] += y;
            for(int i_121 = 0; i_121 < 3; i_121++)
                t.c[t.nt][i_121] = p[i_119].oc[i_121];

            t.skd[t.nt] = 2;
            t.dam[t.nt] = 1;
            t.notwall[t.nt] = false;
            t.decor[t.nt] = true;
            t.rady[t.nt] += 10;
            t.nt++;
        }

        t.y[t.nt] = 0;
        for(int i_122 = 0; i_122 < 8; i_122++)
            t.y[t.nt] += is_98[i_122];

        t.y[t.nt] = t.y[t.nt] / 8;
        t.y[t.nt] += y;
        t.rady[t.nt] = 200;
        t.radx[t.nt] = is_117[0] - is_117[1];
        t.radz[t.nt] = is_118[0] - is_118[1];
        t.x[t.nt] = (is_117[0] + is_117[1]) / 2 + x;
        t.z[t.nt] = (is_118[0] + is_118[1]) / 2 + z;
        t.zy[t.nt] = 0;
        t.xy[t.nt] = 0;
        for(int i_123 = 0; i_123 < 3; i_123++)
            t.c[t.nt][i_123] = p[4].oc[i_123];

        t.skd[t.nt] = 4;
        t.dam[t.nt] = 1;
        t.notwall[t.nt] = false;
        t.decor[t.nt] = true;
        t.nt++;
    }

    public void d(Graphics2D graphics2d)
    {
        if(dist != 0)
            dist = 0;
        int i = m.cx + (int)((float)(x - m.x - m.cx) * m.cos(m.xz) - (float)(z - m.z - m.cz) * m.sin(m.xz));
        int i_124 = m.cz + (int)((float)(x - m.x - m.cx) * m.sin(m.xz) + (float)(z - m.z - m.cz) * m.cos(m.xz));
        int i_125 = m.cz + (int)((float)(y - m.y - m.cy) * m.sin(m.zy) + (float)(i_124 - m.cz) * m.cos(m.zy));
        int i_126 = xs(i + maxR, i_125) - xs(i - maxR, i_125);
        if(xs(i + maxR * 2, i_125) > m.iw && xs(i - maxR * 2, i_125) < m.w && i_125 > -maxR && (i_125 < m.fade[disline] + maxR || m.trk != 0) && (i_126 > disp || m.trk != 0) && (!decor || m.resdown != 2 && m.trk != 1))
        {
            if(shadow)
                if(!m.crs)
                {
                    if(i_125 < 2000)
                    {
                        boolean bool = false;
                        if(t.ncx != 0 || t.ncz != 0)
                        {
                            int i_127 = (x - t.sx) / 3000;
                            if(i_127 > t.ncx)
                                i_127 = t.ncx;
                            if(i_127 < 0)
                                i_127 = 0;
                            int i_128 = (z - t.sz) / 3000;
                            if(i_128 > t.ncz)
                                i_128 = t.ncz;
                            if(i_128 < 0)
                                i_128 = 0;
                            int i_129 = t.sect[i_127][i_128].length - 1;
                            do
                            {
                                if(i_129 < 0)
                                    break;
                                int i_130 = t.sect[i_127][i_128][i_129];
                                if(Math.abs(t.zy[i_130]) != 90 && Math.abs(t.xy[i_130]) != 90 && Math.abs(x - t.x[i_130]) < t.radx[i_130] + maxR && Math.abs(z - t.z[i_130]) < t.radz[i_130] + maxR && (!t.decor[i_130] || m.resdown != 2))
                                {
                                    bool = true;
                                    break;
                                }
                                i_129--;
                            } while(true);
                        }
                        if(bool)
                        {
                            for(int i_131 = 0; i_131 < npl; i_131++)
                                p[i_131].s(graphics2d, x - m.x, y - m.y, z - m.z, xz, xy, zy, 0);

                        } else
                        {
                            int i_132 = m.cy + (int)((float)(m.ground - m.cy) * m.cos(m.zy) - (float)(i_124 - m.cz) * m.sin(m.zy));
                            int i_133 = m.cz + (int)((float)(m.ground - m.cy) * m.sin(m.zy) + (float)(i_124 - m.cz) * m.cos(m.zy));
                            if(ys(i_132 + maxR, i_133) > 0 && ys(i_132 - maxR, i_133) < m.h)
                            {
                                for(int i_134 = 0; i_134 < npl; i_134++)
                                    p[i_134].s(graphics2d, x - m.x, y - m.y, z - m.z, xz, xy, zy, 1);

                            }
                        }
                        m.addsp(x - m.x, z - m.z, (int)((double)maxR * 0.80000000000000004D));
                    } else
                    {
                        lowshadow(graphics2d, i_125);
                    }
                } else
                {
                    for(int i_135 = 0; i_135 < npl; i_135++)
                        p[i_135].s(graphics2d, x - m.x, y - m.y, z - m.z, xz, xy, zy, 2);

                }
            int i_136 = m.cy + (int)((float)(y - m.y - m.cy) * m.cos(m.zy) - (float)(i_124 - m.cz) * m.sin(m.zy));
            if(ys(i_136 + maxR, i_125) > m.ih && ys(i_136 - maxR, i_125) < m.h)
            {
                if(elec && m.noelec == 0)
                    electrify(graphics2d);
                if(fix)
                    fixit(graphics2d);
                if(checkpoint != 0 && checkpoint - 1 == m.checkpoint)
                    i_126 = -1;
                if(shadow)
                {
                    dist = (int)Math.sqrt(((m.x + m.cx) - x) * ((m.x + m.cx) - x) + (m.z - z) * (m.z - z) + ((m.y + m.cy) - y) * ((m.y + m.cy) - y));
                    for(int i_137 = 0; i_137 < 20; i_137++)
                        if(stg[i_137] != 0)
                            pdust(i_137, graphics2d, true);

                    dsprk(graphics2d, true);
                }
                int is[] = new int[npl];
                int is_138[] = new int[npl];
                for(int i_139 = 0; i_139 < npl; i_139++)
                    is[i_139] = 0;

                for(int i_140 = 0; i_140 < npl; i_140++)
                {
                    for(int i_141 = i_140 + 1; i_141 < npl; i_141++)
                    {
                        if(p[i_140].av != p[i_141].av)
                        {
                            if(p[i_140].av < p[i_141].av)
                                is[i_140]++;
                            else
                                is[i_141]++;
                            continue;
                        }
                        if(i_140 > i_141)
                            is[i_140]++;
                        else
                            is[i_141]++;
                    }

                    is_138[is[i_140]] = i_140;
                }

                for(int i_142 = 0; i_142 < npl; i_142++)
                    p[is_138[i_142]].d(graphics2d, x - m.x, y - m.y, z - m.z, xz, xy, zy, wxz, wzy, noline, i_126);

                if(shadow)
                {
                    for(int i_143 = 0; i_143 < 20; i_143++)
                        if(stg[i_143] != 0)
                            pdust(i_143, graphics2d, false);

                    dsprk(graphics2d, false);
                }
                dist = (int)(Math.sqrt((int)Math.sqrt(((m.x + m.cx) - x) * ((m.x + m.cx) - x) + (m.z - z) * (m.z - z) + ((m.y + m.cy) - y) * ((m.y + m.cy) - y))) * (double)grounded);
            }
        }
        if(shadow && dist == 0)
        {
            for(int i_144 = 0; i_144 < 20; i_144++)
                if(stg[i_144] != 0)
                    stg[i_144] = 0;

            for(int i_145 = 0; i_145 < 100; i_145++)
                if(rtg[i_145] != 0)
                    rtg[i_145] = 0;

            if(sprk != 0)
                sprk = 0;
        }
    }

    public void lowshadow(Graphics2D graphics2d, int i)
    {
        int is[] = new int[4];
        int is_146[] = new int[4];
        int is_147[] = new int[4];
        int i_148 = 1;
        int i_149;
        for(i_149 = Math.abs(zy); i_149 > 270; i_149 -= 360);
        i_149 = Math.abs(i_149);
        if(i_149 > 90)
            i_148 = -1;
        is[0] = (int)(((double)keyx[0] * 1.2D + (double)x) - (double)m.x);
        is_147[0] = (int)(((double)((keyz[0] + 30) * i_148) * 1.2D + (double)z) - (double)m.z);
        is[1] = (int)(((double)keyx[1] * 1.2D + (double)x) - (double)m.x);
        is_147[1] = (int)(((double)((keyz[1] + 30) * i_148) * 1.2D + (double)z) - (double)m.z);
        is[2] = (int)(((double)keyx[3] * 1.2D + (double)x) - (double)m.x);
        is_147[2] = (int)(((double)((keyz[3] - 30) * i_148) * 1.2D + (double)z) - (double)m.z);
        is[3] = (int)(((double)keyx[2] * 1.2D + (double)x) - (double)m.x);
        is_147[3] = (int)(((double)((keyz[2] - 30) * i_148) * 1.2D + (double)z) - (double)m.z);
        rot(is, is_147, x - m.x, z - m.z, xz, 4);
        int i_150 = (int)((double)(float)m.crgrnd[0] / 1.5D);
        int i_151 = (int)((double)(float)m.crgrnd[1] / 1.5D);
        int i_152 = (int)((double)(float)m.crgrnd[2] / 1.5D);
        for(int i_153 = 0; i_153 < 4; i_153++)
            is_146[i_153] = m.ground;

        if(t.ncx != 0 || t.ncz != 0)
        {
            int i_154 = (x - t.sx) / 3000;
            if(i_154 > t.ncx)
                i_154 = t.ncx;
            if(i_154 < 0)
                i_154 = 0;
            int i_155 = (z - t.sz) / 3000;
            if(i_155 > t.ncz)
                i_155 = t.ncz;
            if(i_155 < 0)
                i_155 = 0;
            int i_156 = t.sect[i_154][i_155].length - 1;
            do
            {
                if(i_156 < 0)
                    break;
                int i_157 = t.sect[i_154][i_155][i_156];
                int i_158 = 0;
                for(int i_159 = 0; i_159 < 4; i_159++)
                    if(Math.abs(t.zy[i_157]) != 90 && Math.abs(t.xy[i_157]) != 90 && t.rady[i_157] != 801 && Math.abs(is[i_159] - (t.x[i_157] - m.x)) < t.radx[i_157] && Math.abs(is_147[i_159] - (t.z[i_157] - m.z)) < t.radz[i_157] && (!t.decor[i_157] || m.resdown != 2))
                        i_158++;

                if(i_158 > 2)
                {
                    for(int i_160 = 0; i_160 < 4; i_160++)
                    {
                        is_146[i_160] = t.y[i_157] - m.y;
                        if(t.zy[i_157] != 0)
                            is_146[i_160] += ((float)(is_147[i_160] - (t.z[i_157] - m.z - t.radz[i_157])) * m.sin(t.zy[i_157])) / m.sin(90 - t.zy[i_157]) - ((float)t.radz[i_157] * m.sin(t.zy[i_157])) / m.sin(90 - t.zy[i_157]);
                        if(t.xy[i_157] != 0)
                            is_146[i_160] += ((float)(is[i_160] - (t.x[i_157] - m.x - t.radx[i_157])) * m.sin(t.xy[i_157])) / m.sin(90 - t.xy[i_157]) - ((float)t.radx[i_157] * m.sin(t.xy[i_157])) / m.sin(90 - t.xy[i_157]);
                    }

                    i_150 = (int)((double)(float)t.c[i_157][0] / 1.5D);
                    i_151 = (int)((double)(float)t.c[i_157][1] / 1.5D);
                    i_152 = (int)((double)(float)t.c[i_157][2] / 1.5D);
                    break;
                }
                i_156--;
            } while(true);
        }
        rot(is, is_147, m.cx, m.cz, m.xz, 4);
        rot(is_146, is_147, m.cy, m.cz, m.zy, 4);
        boolean bool = true;
        int i_161 = 0;
        int i_162 = 0;
        int i_163 = 0;
        int i_164 = 0;
        for(int i_165 = 0; i_165 < 4; i_165++)
        {
            is[i_165] = xs(is[i_165], is_147[i_165]);
            is_146[i_165] = ys(is_146[i_165], is_147[i_165]);
            if(is_146[i_165] < m.ih || is_147[i_165] < 10)
                i_161++;
            if(is_146[i_165] > m.h || is_147[i_165] < 10)
                i_162++;
            if(is[i_165] < m.iw || is_147[i_165] < 10)
                i_163++;
            if(is[i_165] > m.w || is_147[i_165] < 10)
                i_164++;
        }

        if(i_163 == 4 || i_161 == 4 || i_162 == 4 || i_164 == 4)
            bool = false;
        if(bool)
        {
            for(int i_166 = 0; i_166 < 16; i_166++)
                if(i > m.fade[i_166])
                {
                    i_150 = (i_150 * m.fogd + m.cfade[0]) / (m.fogd + 1);
                    i_151 = (i_151 * m.fogd + m.cfade[1]) / (m.fogd + 1);
                    i_152 = (i_152 * m.fogd + m.cfade[2]) / (m.fogd + 1);
                }

            graphics2d.setColor(new Color(i_150, i_151, i_152));
            graphics2d.fillPolygon(is, is_146, 4);
        }
    }

    public void fixit(Graphics2D graphics2d)
    {
        if(fcnt == 1)
        {
            for(int i = 0; i < npl; i++)
            {
                p[i].hsb[0] = 0.57F;
                p[i].hsb[2] = 0.8F;
                p[i].hsb[1] = 0.8F;
                Color color = Color.getHSBColor(p[i].hsb[0], p[i].hsb[1], p[i].hsb[2]);
                int i_167 = (int)((float)color.getRed() + (float)color.getRed() * ((float)m.snap[0] / 100F));
                if(i_167 > 255)
                    i_167 = 255;
                if(i_167 < 0)
                    i_167 = 0;
                int i_168 = (int)((float)color.getGreen() + (float)color.getGreen() * ((float)m.snap[1] / 100F));
                if(i_168 > 255)
                    i_168 = 255;
                if(i_168 < 0)
                    i_168 = 0;
                int i_169 = (int)((float)color.getBlue() + (float)color.getBlue() * ((float)m.snap[2] / 100F));
                if(i_169 > 255)
                    i_169 = 255;
                if(i_169 < 0)
                    i_169 = 0;
                Color.RGBtoHSB(i_167, i_168, i_169, p[i].hsb);
                p[i].flx = 1;
            }

        }
        if(fcnt == 2)
        {
            for(int i = 0; i < npl; i++)
                p[i].flx = 1;

        }
        if(fcnt == 4)
        {
            for(int i = 0; i < npl; i++)
                p[i].flx = 3;

        }
        if((fcnt == 1 || fcnt > 2) && fcnt != 9)
        {
            int is[] = new int[8];
            int is_170[] = new int[8];
            int is_171[] = new int[4];
            int i;
            for(i = 0; i < 4; i++)
            {
                is[i] = (keyx[i] + x) - m.x;
                is_170[i] = (grat + y) - m.y;
                is_171[i] = (keyz[i] + z) - m.z;
            }

            rot(is, is_170, x - m.x, y - m.y, xy, 4);
            rot(is_170, is_171, y - m.y, z - m.y, zy, 4);
            rot(is, is_171, x - m.x, z - m.z, xz, 4);
            rot(is, is_171, m.cx, m.cz, m.xz, 4);
            rot(is_170, is_171, m.cy, m.cz, m.zy, 4);
            i = 0;
            int i_172 = 0;
            int i_173 = 0;
            for(int i_174 = 0; i_174 < 4; i_174++)
            {
                for(int i_175 = 0; i_175 < 4; i_175++)
                {
                    if(Math.abs(is[i_174] - is[i_175]) > i)
                        i = Math.abs(is[i_174] - is[i_175]);
                    if(Math.abs(is_170[i_174] - is_170[i_175]) > i_172)
                        i_172 = Math.abs(is_170[i_174] - is_170[i_175]);
                    if(py(is[i_174], is[i_175], is_170[i_174], is_170[i_175]) > i_173)
                        i_173 = py(is[i_174], is[i_175], is_170[i_174], is_170[i_175]);
                }

            }

            i_173 = (int)(Math.sqrt(i_173) / 1.5D);
            if(i < i_173)
                i = i_173;
            if(i_172 < i_173)
                i_172 = i_173;
            int i_176 = m.cx + (int)((float)(x - m.x - m.cx) * m.cos(m.xz) - (float)(z - m.z - m.cz) * m.sin(m.xz));
            int i_177 = m.cz + (int)((float)(x - m.x - m.cx) * m.sin(m.xz) + (float)(z - m.z - m.cz) * m.cos(m.xz));
            int i_178 = m.cy + (int)((float)(y - m.y - m.cy) * m.cos(m.zy) - (float)(i_177 - m.cz) * m.sin(m.zy));
            i_177 = m.cz + (int)((float)(y - m.y - m.cy) * m.sin(m.zy) + (float)(i_177 - m.cz) * m.cos(m.zy));
            is[0] = xs((int)((double)i_176 - (double)i / 0.80000000000000004D - (double)m.random() * ((double)i / 2.3999999999999999D)), i_177);
            is_170[0] = ys((int)((double)i_178 - (double)i_172 / 1.9199999999999999D - (double)m.random() * ((double)i_172 / 5.6699999999999999D)), i_177);
            is[1] = xs((int)((double)i_176 - (double)i / 0.80000000000000004D - (double)m.random() * ((double)i / 2.3999999999999999D)), i_177);
            is_170[1] = ys((int)((double)i_178 + (double)i_172 / 1.9199999999999999D + (double)m.random() * ((double)i_172 / 5.6699999999999999D)), i_177);
            is[2] = xs((int)((double)i_176 - (double)i / 1.9199999999999999D - (double)m.random() * ((double)i / 5.6699999999999999D)), i_177);
            is_170[2] = ys((int)((double)i_178 + (double)i_172 / 0.80000000000000004D + (double)m.random() * ((double)i_172 / 2.3999999999999999D)), i_177);
            is[3] = xs((int)((double)i_176 + (double)i / 1.9199999999999999D + (double)m.random() * ((double)i / 5.6699999999999999D)), i_177);
            is_170[3] = ys((int)((double)i_178 + (double)i_172 / 0.80000000000000004D + (double)m.random() * ((double)i_172 / 2.3999999999999999D)), i_177);
            is[4] = xs((int)((double)i_176 + (double)i / 0.80000000000000004D + (double)m.random() * ((double)i / 2.3999999999999999D)), i_177);
            is_170[4] = ys((int)((double)i_178 + (double)i_172 / 1.9199999999999999D + (double)m.random() * ((double)i_172 / 5.6699999999999999D)), i_177);
            is[5] = xs((int)((double)i_176 + (double)i / 0.80000000000000004D + (double)m.random() * ((double)i / 2.3999999999999999D)), i_177);
            is_170[5] = ys((int)((double)i_178 - (double)i_172 / 1.9199999999999999D - (double)m.random() * ((double)i_172 / 5.6699999999999999D)), i_177);
            is[6] = xs((int)((double)i_176 + (double)i / 1.9199999999999999D + (double)m.random() * ((double)i / 5.6699999999999999D)), i_177);
            is_170[6] = ys((int)((double)i_178 - (double)i_172 / 0.80000000000000004D - (double)m.random() * ((double)i_172 / 2.3999999999999999D)), i_177);
            is[7] = xs((int)((double)i_176 - (double)i / 1.9199999999999999D - (double)m.random() * ((double)i / 5.6699999999999999D)), i_177);
            is_170[7] = ys((int)((double)i_178 - (double)i_172 / 0.80000000000000004D - (double)m.random() * ((double)i_172 / 2.3999999999999999D)), i_177);
            if(fcnt == 3)
                rot(is, is_170, xs(i_176, i_177), ys(i_178, i_177), 22, 8);
            if(fcnt == 4)
                rot(is, is_170, xs(i_176, i_177), ys(i_178, i_177), 22, 8);
            if(fcnt == 5)
                rot(is, is_170, xs(i_176, i_177), ys(i_178, i_177), 0, 8);
            if(fcnt == 6)
                rot(is, is_170, xs(i_176, i_177), ys(i_178, i_177), -22, 8);
            if(fcnt == 7)
                rot(is, is_170, xs(i_176, i_177), ys(i_178, i_177), -22, 8);
            int i_179 = (int)(191F + 191F * ((float)m.snap[0] / 350F));
            if(i_179 > 255)
                i_179 = 255;
            if(i_179 < 0)
                i_179 = 0;
            int i_180 = (int)(232F + 232F * ((float)m.snap[1] / 350F));
            if(i_180 > 255)
                i_180 = 255;
            if(i_180 < 0)
                i_180 = 0;
            int i_181 = (int)(255F + 255F * ((float)m.snap[2] / 350F));
            if(i_181 > 255)
                i_181 = 255;
            if(i_181 < 0)
                i_181 = 0;
            graphics2d.setColor(new Color(i_179, i_180, i_181));
            graphics2d.fillPolygon(is, is_170, 8);
            is[0] = xs((int)((float)(i_176 - i) - m.random() * (float)(i / 4)), i_177);
            is_170[0] = ys((int)((double)i_178 - (double)i_172 / 2.3999999999999999D - (double)m.random() * ((double)i_172 / 9.5999999999999996D)), i_177);
            is[1] = xs((int)((float)(i_176 - i) - m.random() * (float)(i / 4)), i_177);
            is_170[1] = ys((int)((double)i_178 + (double)i_172 / 2.3999999999999999D + (double)m.random() * ((double)i_172 / 9.5999999999999996D)), i_177);
            is[2] = xs((int)((double)i_176 - (double)i / 2.3999999999999999D - (double)m.random() * ((double)i / 9.5999999999999996D)), i_177);
            is_170[2] = ys((int)((float)(i_178 + i_172) + m.random() * (float)(i_172 / 4)), i_177);
            is[3] = xs((int)((double)i_176 + (double)i / 2.3999999999999999D + (double)m.random() * ((double)i / 9.5999999999999996D)), i_177);
            is_170[3] = ys((int)((float)(i_178 + i_172) + m.random() * (float)(i_172 / 4)), i_177);
            is[4] = xs((int)((float)(i_176 + i) + m.random() * (float)(i / 4)), i_177);
            is_170[4] = ys((int)((double)i_178 + (double)i_172 / 2.3999999999999999D + (double)m.random() * ((double)i_172 / 9.5999999999999996D)), i_177);
            is[5] = xs((int)((float)(i_176 + i) + m.random() * (float)(i / 4)), i_177);
            is_170[5] = ys((int)((double)i_178 - (double)i_172 / 2.3999999999999999D - (double)m.random() * ((double)i_172 / 9.5999999999999996D)), i_177);
            is[6] = xs((int)((double)i_176 + (double)i / 2.3999999999999999D + (double)m.random() * ((double)i / 9.5999999999999996D)), i_177);
            is_170[6] = ys((int)((float)(i_178 - i_172) - m.random() * (float)(i_172 / 4)), i_177);
            is[7] = xs((int)((double)i_176 - (double)i / 2.3999999999999999D - (double)m.random() * ((double)i / 9.5999999999999996D)), i_177);
            is_170[7] = ys((int)((float)(i_178 - i_172) - m.random() * (float)(i_172 / 4)), i_177);
            i_179 = (int)(213F + 213F * ((float)m.snap[0] / 350F));
            if(i_179 > 255)
                i_179 = 255;
            if(i_179 < 0)
                i_179 = 0;
            i_180 = (int)(239F + 239F * ((float)m.snap[1] / 350F));
            if(i_180 > 255)
                i_180 = 255;
            if(i_180 < 0)
                i_180 = 0;
            i_181 = (int)(255F + 255F * ((float)m.snap[2] / 350F));
            if(i_181 > 255)
                i_181 = 255;
            if(i_181 < 0)
                i_181 = 0;
            graphics2d.setColor(new Color(i_179, i_180, i_181));
            graphics2d.fillPolygon(is, is_170, 8);
        }
        if(fcnt > 7)
        {
            fcnt = 0;
            fix = false;
        } else
        {
            fcnt++;
        }
    }

    public void electrify(Graphics2D graphics2d)
    {
        for(int i = 0; i < 4; i++)
        {
            if(elc[i] == 0)
            {
                edl[i] = (int)(380F - m.random() * 760F);
                edr[i] = (int)(380F - m.random() * 760F);
                elc[i] = 1;
            }
            int i_182 = (int)((float)edl[i] + (190F - m.random() * 380F));
            int i_183 = (int)((float)edr[i] + (190F - m.random() * 380F));
            int i_184 = (int)(m.random() * 126F);
            int i_185 = (int)(m.random() * 126F);
            int is[] = new int[8];
            int is_186[] = new int[8];
            int is_187[] = new int[8];
            for(int i_188 = 0; i_188 < 8; i_188++)
                is_187[i_188] = z - m.z;

            is[0] = x - m.x - 504;
            is_186[0] = y - m.y - edl[i] - 5 - (int)(m.random() * 5F);
            is[1] = (x - m.x - 252) + i_185;
            is_186[1] = y - m.y - i_182 - 5 - (int)(m.random() * 5F);
            is[2] = ((x - m.x) + 252) - i_184;
            is_186[2] = y - m.y - i_183 - 5 - (int)(m.random() * 5F);
            is[3] = (x - m.x) + 504;
            is_186[3] = y - m.y - edr[i] - 5 - (int)(m.random() * 5F);
            is[4] = (x - m.x) + 504;
            is_186[4] = (y - m.y - edr[i]) + 5 + (int)(m.random() * 5F);
            is[5] = ((x - m.x) + 252) - i_184;
            is_186[5] = (y - m.y - i_183) + 5 + (int)(m.random() * 5F);
            is[6] = (x - m.x - 252) + i_185;
            is_186[6] = (y - m.y - i_182) + 5 + (int)(m.random() * 5F);
            is[7] = x - m.x - 504;
            is_186[7] = (y - m.y - edl[i]) + 5 + (int)(m.random() * 5F);
            if(roted)
                rot(is, is_187, x - m.x, z - m.z, 90, 8);
            rot(is, is_187, m.cx, m.cz, m.xz, 8);
            rot(is_186, is_187, m.cy, m.cz, m.zy, 8);
            boolean bool = true;
            int i_189 = 0;
            int i_190 = 0;
            int i_191 = 0;
            int i_192 = 0;
            int is_193[] = new int[8];
            int is_194[] = new int[8];
            for(int i_195 = 0; i_195 < 8; i_195++)
            {
                is_193[i_195] = xs(is[i_195], is_187[i_195]);
                is_194[i_195] = ys(is_186[i_195], is_187[i_195]);
                if(is_194[i_195] < m.ih || is_187[i_195] < 10)
                    i_189++;
                if(is_194[i_195] > m.h || is_187[i_195] < 10)
                    i_190++;
                if(is_193[i_195] < m.iw || is_187[i_195] < 10)
                    i_191++;
                if(is_193[i_195] > m.w || is_187[i_195] < 10)
                    i_192++;
            }

            if(i_191 == 8 || i_189 == 8 || i_190 == 8 || i_192 == 8)
                bool = false;
            if(bool)
            {
                int i_196 = (int)(160F + 160F * ((float)m.snap[0] / 500F));
                if(i_196 > 255)
                    i_196 = 255;
                if(i_196 < 0)
                    i_196 = 0;
                int i_197 = (int)(238F + 238F * ((float)m.snap[1] / 500F));
                if(i_197 > 255)
                    i_197 = 255;
                if(i_197 < 0)
                    i_197 = 0;
                int i_198 = (int)(255F + 255F * ((float)m.snap[2] / 500F));
                if(i_198 > 255)
                    i_198 = 255;
                if(i_198 < 0)
                    i_198 = 0;
                i_196 = (i_196 * 2 + 214 * (elc[i] - 1)) / (elc[i] + 1);
                i_197 = (i_197 * 2 + 236 * (elc[i] - 1)) / (elc[i] + 1);
                if(m.trk == 1)
                {
                    i_196 = 255;
                    i_197 = 128;
                    i_198 = 0;
                }
                graphics2d.setColor(new Color(i_196, i_197, i_198));
                graphics2d.fillPolygon(is_193, is_194, 8);
                if(is_187[0] < 4000)
                {
                    i_196 = (int)(150F + 150F * ((float)m.snap[0] / 500F));
                    if(i_196 > 255)
                        i_196 = 255;
                    if(i_196 < 0)
                        i_196 = 0;
                    i_197 = (int)(227F + 227F * ((float)m.snap[1] / 500F));
                    if(i_197 > 255)
                        i_197 = 255;
                    if(i_197 < 0)
                        i_197 = 0;
                    i_198 = (int)(255F + 255F * ((float)m.snap[2] / 500F));
                    if(i_198 > 255)
                        i_198 = 255;
                    if(i_198 < 0)
                        i_198 = 0;
                    graphics2d.setColor(new Color(i_196, i_197, i_198));
                    graphics2d.drawPolygon(is_193, is_194, 8);
                }
            }
            if((float)elc[i] > m.random() * 60F)
                elc[i] = 0;
            else
                elc[i]++;
        }

        if(!roted || xz != 0)
        {
            xy += 11;
            if(xy > 360)
                xy -= 360;
        } else
        {
            zy += 11;
            if(zy > 360)
                zy -= 360;
        }
    }

    public void dust(int i, float f, float f_199, float f_200, int i_201, int i_202, float f_203, 
            int i_204, boolean bool)
    {
        boolean bool_205 = false;
        if(i_204 > 5 && (i == 0 || i == 2))
            bool_205 = true;
        if(i_204 < -5 && (i == 1 || i == 3))
            bool_205 = true;
        float f_206 = (float)((Math.sqrt(i_201 * i_201 + i_202 * i_202) - 40D) / 160D);
        if(f_206 > 1.0F)
            f_206 = 1.0F;
        if((double)f_206 > 0.20000000000000001D && !bool_205)
        {
            ust++;
            if(ust == 20)
                ust = 0;
            if(!bool)
            {
                float f_207 = m.random();
                sx[ust] = (int)((f + (float)x * f_207) / (1.0F + f_207));
                sz[ust] = (int)((f_200 + (float)z * f_207) / (1.0F + f_207));
                sy[ust] = (int)((f_199 + (float)y * f_207) / (1.0F + f_207));
            } else
            {
                sx[ust] = (int)((f + (float)(x + i_201)) / 2.0F);
                sz[ust] = (int)((f_200 + (float)(z + i_202)) / 2.0F);
                sy[ust] = (int)f_199;
            }
            if(sy[i] > 250)
                sy[i] = 250;
            osmag[ust] = f_203 * f_206;
            scx[ust] = i_201;
            scz[ust] = i_202;
            stg[ust] = 1;
        }
    }

    public void pdust(int i, Graphics2D graphics2d, boolean bool)
    {
        if(bool)
            sav[i] = (int)Math.sqrt(((m.x + m.cx) - sx[i]) * ((m.x + m.cx) - sx[i]) + ((m.y + m.cy) - sy[i]) * ((m.y + m.cy) - sy[i]) + (m.z - sz[i]) * (m.z - sz[i]));
        if(bool && sav[i] > dist || !bool && sav[i] <= dist)
        {
            if(stg[i] == 1)
            {
                sbln[i] = 0.6F;
                boolean bool_208 = false;
                int is[] = new int[3];
                for(int i_209 = 0; i_209 < 3; i_209++)
                {
                    is[i_209] = (int)(255F + 255F * ((float)m.snap[i_209] / 100F));
                    if(is[i_209] > 255)
                        is[i_209] = 255;
                    if(is[i_209] < 0)
                        is[i_209] = 0;
                }

                int i_210 = (x - t.sx) / 3000;
                if(i_210 > t.ncx)
                    i_210 = t.ncx;
                if(i_210 < 0)
                    i_210 = 0;
                int i_211 = (z - t.sz) / 3000;
                if(i_211 > t.ncz)
                    i_211 = t.ncz;
                if(i_211 < 0)
                    i_211 = 0;
                for(int i_212 = 0; i_212 < t.sect[i_210][i_211].length; i_212++)
                {
                    int i_213 = t.sect[i_210][i_211][i_212];
                    if(Math.abs(t.zy[i_213]) == 90 || Math.abs(t.xy[i_213]) == 90 || Math.abs(sx[i] - t.x[i_213]) >= t.radx[i_213] || Math.abs(sz[i] - t.z[i_213]) >= t.radz[i_213])
                        continue;
                    if(t.skd[i_213] == 0)
                        sbln[i] = 0.2F;
                    if(t.skd[i_213] == 1)
                        sbln[i] = 0.4F;
                    if(t.skd[i_213] == 2)
                        sbln[i] = 0.45F;
                    for(int i_214 = 0; i_214 < 3; i_214++)
                        srgb[i][i_214] = (t.c[i_213][i_214] + is[i_214]) / 2;

                    bool_208 = true;
                }

                if(!bool_208)
                {
                    for(int i_215 = 0; i_215 < 3; i_215++)
                        srgb[i][i_215] = (m.crgrnd[i_215] + is[i_215]) / 2;

                }
                float f = (float)(0.10000000000000001D + (double)m.random());
                if(f > 1.0F)
                    f = 1.0F;
                scx[i] = (int)((float)scx[i] * f);
                scz[i] = (int)((float)scx[i] * f);
                for(int i_216 = 0; i_216 < 8; i_216++)
                    smag[i][i_216] = osmag[i] * m.random() * 50F;

                for(int i_217 = 0; i_217 < 8; i_217++)
                {
                    int i_218 = i_217 - 1;
                    if(i_218 == -1)
                        i_218 = 7;
                    int i_219 = i_217 + 1;
                    if(i_219 == 8)
                        i_219 = 0;
                    smag[i][i_217] = ((smag[i][i_218] + smag[i][i_219]) / 2.0F + smag[i][i_217]) / 2.0F;
                }

                smag[i][6] = smag[i][7];
            }
            int i_220 = m.cx + (int)((float)(sx[i] - m.x - m.cx) * m.cos(m.xz) - (float)(sz[i] - m.z - m.cz) * m.sin(m.xz));
            int i_221 = m.cz + (int)((float)(sx[i] - m.x - m.cx) * m.sin(m.xz) + (float)(sz[i] - m.z - m.cz) * m.cos(m.xz));
            int i_222 = m.cy + (int)(((float)(sy[i] - m.y - m.cy) - smag[i][7]) * m.cos(m.zy) - (float)(i_221 - m.cz) * m.sin(m.zy));
            i_221 = m.cz + (int)(((float)(sy[i] - m.y - m.cy) - smag[i][7]) * m.sin(m.zy) + (float)(i_221 - m.cz) * m.cos(m.zy));
            sx[i] += scx[i] / (stg[i] + 1);
            sz[i] += scz[i] / (stg[i] + 1);
            int is[] = new int[8];
            int is_223[] = new int[8];
            is[0] = xs((int)((float)i_220 + smag[i][0] * 0.9238F * 1.5F), i_221);
            is_223[0] = ys((int)((float)i_222 + smag[i][0] * 0.3826F * 1.5F), i_221);
            is[1] = xs((int)((float)i_220 + smag[i][1] * 0.9238F * 1.5F), i_221);
            is_223[1] = ys((int)((float)i_222 - smag[i][1] * 0.3826F * 1.5F), i_221);
            is[2] = xs((int)((float)i_220 + smag[i][2] * 0.3826F), i_221);
            is_223[2] = ys((int)((float)i_222 - smag[i][2] * 0.9238F), i_221);
            is[3] = xs((int)((float)i_220 - smag[i][3] * 0.3826F), i_221);
            is_223[3] = ys((int)((float)i_222 - smag[i][3] * 0.9238F), i_221);
            is[4] = xs((int)((float)i_220 - smag[i][4] * 0.9238F * 1.5F), i_221);
            is_223[4] = ys((int)((float)i_222 - smag[i][4] * 0.3826F * 1.5F), i_221);
            is[5] = xs((int)((float)i_220 - smag[i][5] * 0.9238F * 1.5F), i_221);
            is_223[5] = ys((int)((float)i_222 + smag[i][5] * 0.3826F * 1.5F), i_221);
            is[6] = xs((int)((float)i_220 - smag[i][6] * 0.3826F * 1.7F), i_221);
            is_223[6] = ys((int)((float)i_222 + smag[i][6] * 0.9238F), i_221);
            is[7] = xs((int)((float)i_220 + smag[i][7] * 0.3826F * 1.7F), i_221);
            is_223[7] = ys((int)((float)i_222 + smag[i][7] * 0.9238F), i_221);
            for(int i_224 = 0; i_224 < 7; i_224++)
                smag[i][i_224] += 5F + m.random() * 15F;

            smag[i][7] = smag[i][6];
            boolean bool_225 = true;
            int i_226 = 0;
            int i_227 = 0;
            int i_228 = 0;
            int i_229 = 0;
            for(int i_230 = 0; i_230 < 8; i_230++)
            {
                if(is_223[i_230] < m.ih || i_221 < 10)
                    i_226++;
                if(is_223[i_230] > m.h || i_221 < 10)
                    i_227++;
                if(is[i_230] < m.iw || i_221 < 10)
                    i_228++;
                if(is[i_230] > m.w || i_221 < 10)
                    i_229++;
            }

            if(i_228 == 4 || i_226 == 4 || i_227 == 4 || i_229 == 4)
                bool_225 = false;
            if(bool_225)
            {
                int i_231 = srgb[i][0];
                int i_232 = srgb[i][1];
                int i_233 = srgb[i][2];
                for(int i_234 = 0; i_234 < 16; i_234++)
                    if(sav[i] > m.fade[i_234])
                    {
                        i_231 = (i_231 * m.fogd + m.cfade[0]) / (m.fogd + 1);
                        i_232 = (i_232 * m.fogd + m.cfade[1]) / (m.fogd + 1);
                        i_233 = (i_233 * m.fogd + m.cfade[2]) / (m.fogd + 1);
                    }

                graphics2d.setColor(new Color(i_231, i_232, i_233));
                float f = sbln[i] - (float)stg[i] * (sbln[i] / 8F);
                graphics2d.setComposite(AlphaComposite.getInstance(3, f));
                graphics2d.fillPolygon(is, is_223, 8);
                graphics2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
            }
            if(stg[i] == 7)
                stg[i] = 0;
            else
                stg[i]++;
        }
    }

    public void sprk(float f, float f_235, float f_236, float f_237, float f_238, float f_239, int i)
    {
        if(i != 1)
        {
            srx = (int)(f - (float)sprkat * m.sin(xz));
            sry = (int)(f_235 - (float)sprkat * m.cos(zy) * m.cos(xy));
            srz = (int)(f_236 + (float)sprkat * m.cos(xz));
            sprk = 1;
        } else
        {
            sprk++;
            if(sprk == 4)
            {
                srx = (int)((float)x + f_237);
                sry = (int)f_235;
                srz = (int)((float)z + f_239);
                sprk = 5;
            } else
            {
                srx = (int)f;
                sry = (int)f_235;
                srz = (int)f_236;
            }
        }
        if(i == 2)
            sprk = 6;
        rcx = f_237;
        rcy = f_238;
        rcz = f_239;
    }

    public void dsprk(Graphics2D graphics2d, boolean bool)
    {
        if(bool && sprk != 0)
        {
            int i = (int)(Math.sqrt(rcx * rcx + rcy * rcy + rcz * rcz) / 10D);
            if(i > 5)
            {
                boolean bool_240 = false;
                if((double)dist < Math.sqrt(((m.x + m.cx) - srx) * ((m.x + m.cx) - srx) + ((m.y + m.cy) - sry) * ((m.y + m.cy) - sry) + (m.z - srz) * (m.z - srz)))
                    bool_240 = true;
                if(i > 33)
                    i = 33;
                int i_241 = 0;
                int i_242 = 0;
                do
                {
                    if(i_242 >= 100)
                        break;
                    if(rtg[i_242] == 0)
                    {
                        rtg[i_242] = 1;
                        rbef[i_242] = bool_240;
                        i_241++;
                    }
                    if(i_241 == i)
                        break;
                    i_242++;
                } while(true);
            }
        }
        for(int i = 0; i < 100; i++)
        {
            if(rtg[i] == 0 || (!rbef[i] || !bool) && (rbef[i] || bool))
                continue;
            if(rtg[i] == 1)
            {
                if(sprk < 5)
                {
                    rx[i] = (srx + 3) - (int)((double)m.random() * 6.7000000000000002D);
                    ry[i] = (sry + 3) - (int)((double)m.random() * 6.7000000000000002D);
                    rz[i] = (srz + 3) - (int)((double)m.random() * 6.7000000000000002D);
                } else
                {
                    rx[i] = (srx + 10) - (int)(m.random() * 20F);
                    ry[i] = sry - (int)(m.random() * 4F);
                    rz[i] = (srz + 10) - (int)(m.random() * 20F);
                }
                int i_243 = (int)Math.sqrt(rcx * rcx + rcy * rcy + rcz * rcz);
                float f = 0.2F + 0.4F * m.random();
                float f_244 = m.random() * m.random() * m.random();
                float f_245 = 1.0F;
                if(m.random() > m.random())
                {
                    if(m.random() > m.random())
                        f_245 *= -1F;
                    vrx[i] = -((rcx + (float)i_243 * (1.0F - rcx / (float)i_243) * f_244 * f_245) * f);
                }
                if(m.random() > m.random())
                {
                    if(m.random() > m.random())
                        f_245 *= -1F;
                    if(sprk == 5)
                        f_245 = 1.0F;
                    vry[i] = -((rcy + (float)i_243 * (1.0F - rcy / (float)i_243) * f_244 * f_245) * f);
                }
                if(m.random() > m.random())
                {
                    if(m.random() > m.random())
                        f_245 *= -1F;
                    vrz[i] = -((rcz + (float)i_243 * (1.0F - rcz / (float)i_243) * f_244 * f_245) * f);
                }
            }
            rx[i] += vrx[i];
            ry[i] += vry[i];
            rz[i] += vrz[i];
            int i_246 = m.cx + (int)((float)(rx[i] - m.x - m.cx) * m.cos(m.xz) - (float)(rz[i] - m.z - m.cz) * m.sin(m.xz));
            int i_247 = m.cz + (int)((float)(rx[i] - m.x - m.cx) * m.sin(m.xz) + (float)(rz[i] - m.z - m.cz) * m.cos(m.xz));
            int i_248 = m.cy + (int)((float)(ry[i] - m.y - m.cy) * m.cos(m.zy) - (float)(i_247 - m.cz) * m.sin(m.zy));
            i_247 = m.cz + (int)((float)(ry[i] - m.y - m.cy) * m.sin(m.zy) + (float)(i_247 - m.cz) * m.cos(m.zy));
            int i_249 = m.cx + (int)(((float)(rx[i] - m.x - m.cx) + vrx[i]) * m.cos(m.xz) - ((float)(rz[i] - m.z - m.cz) + vrz[i]) * m.sin(m.xz));
            int i_250 = m.cz + (int)(((float)(rx[i] - m.x - m.cx) + vrx[i]) * m.sin(m.xz) + ((float)(rz[i] - m.z - m.cz) + vrz[i]) * m.cos(m.xz));
            int i_251 = m.cy + (int)(((float)(ry[i] - m.y - m.cy) + vry[i]) * m.cos(m.zy) - (float)(i_250 - m.cz) * m.sin(m.zy));
            i_250 = m.cz + (int)(((float)(ry[i] - m.y - m.cy) + vry[i]) * m.sin(m.zy) + (float)(i_250 - m.cz) * m.cos(m.zy));
            int i_252 = xs(i_246, i_247);
            int i_253 = ys(i_248, i_247);
            int i_254 = xs(i_249, i_250);
            int i_255 = ys(i_251, i_250);
            if(i_252 < m.iw && i_254 < m.iw)
                rtg[i] = 0;
            if(i_252 > m.w && i_254 > m.w)
                rtg[i] = 0;
            if(i_253 < m.ih && i_255 < m.ih)
                rtg[i] = 0;
            if(i_253 > m.h && i_255 > m.h)
                rtg[i] = 0;
            if(ry[i] > 250)
                rtg[i] = 0;
            if(rtg[i] == 0)
                continue;
            int i_256 = 255;
            int i_257 = 197 - 30 * rtg[i];
            int i_258 = 0;
            for(int i_259 = 0; i_259 < 16; i_259++)
                if(i_247 > m.fade[i_259])
                {
                    i_256 = (i_256 * m.fogd + m.cfade[0]) / (m.fogd + 1);
                    i_257 = (i_257 * m.fogd + m.cfade[1]) / (m.fogd + 1);
                    i_258 = (i_258 * m.fogd + m.cfade[2]) / (m.fogd + 1);
                }

            graphics2d.setColor(new Color(i_256, i_257, i_258));
            graphics2d.drawLine(i_252, i_253, i_254, i_255);
            vrx[i] = vrx[i] * 0.8F;
            vry[i] = vry[i] * 0.8F;
            vrz[i] = vrz[i] * 0.8F;
            if(rtg[i] == 3)
                rtg[i] = 0;
            else
                rtg[i]++;
        }

        if(sprk != 0)
            sprk = 0;
    }

    public int xs(int i, int i_260)
    {
        if(i_260 < 50)
            i_260 = 50;
        return ((i_260 - m.focus_point) * (m.cx - i)) / i_260 + i;
    }

    public int ys(int i, int i_261)
    {
        if(i_261 < 50)
            i_261 = 50;
        return ((i_261 - m.focus_point) * (m.cy - i)) / i_261 + i;
    }

    public int getvalue(String string, String string_262, int i)
    {
        int i_263 = 0;
        String string_264 = "";
        for(int i_265 = string.length() + 1; i_265 < string_262.length(); i_265++)
        {
            String string_266 = (new StringBuilder()).append("").append(string_262.charAt(i_265)).toString();
            if(string_266.equals(",") || string_266.equals(")"))
            {
                i_263++;
                i_265++;
            }
            if(i_263 == i)
                string_264 = (new StringBuilder()).append(string_264).append(string_262.charAt(i_265)).toString();
        }

        return Float.valueOf(string_264).intValue();
    }

    public int getpy(int i, int i_267, int i_268)
    {
        return ((i - x) / 10) * ((i - x) / 10) + ((i_267 - y) / 10) * ((i_267 - y) / 10) + ((i_268 - z) / 10) * ((i_268 - z) / 10);
    }

    public int py(int i, int i_269, int i_270, int i_271)
    {
        return (i - i_269) * (i - i_269) + (i_270 - i_271) * (i_270 - i_271);
    }

    public void rot(int is[], int is_272[], int i, int i_273, int i_274, int i_275)
    {
        if(i_274 != 0)
        {
            for(int i_276 = 0; i_276 < i_275; i_276++)
            {
                int i_277 = is[i_276];
                int i_278 = is_272[i_276];
                is[i_276] = i + (int)((float)(i_277 - i) * m.cos(i_274) - (float)(i_278 - i_273) * m.sin(i_274));
                is_272[i_276] = i_273 + (int)((float)(i_277 - i) * m.sin(i_274) + (float)(i_278 - i_273) * m.cos(i_274));
            }

        }
    }

    Medium m;
    Trackers t;
    Plane p[];
    int npl;
    int x;
    int y;
    int z;
    int xz;
    int xy;
    int zy;
    int wxz;
    int wzy;
    int dist;
    int maxR;
    int disp;
    int disline;
    boolean shadow;
    boolean noline;
    boolean decor;
    float grounded;
    int grat;
    int keyx[];
    int keyz[];
    int sprkat;
    int txy[];
    int tzy[];
    int tc[][];
    int tradx[];
    int tradz[];
    int trady[];
    int tx[];
    int ty[];
    int tz[];
    int skd[];
    int dam[];
    boolean notwall[];
    int tnt;
    int stg[];
    int sx[];
    int sy[];
    int sz[];
    int scx[];
    int scz[];
    float osmag[];
    int sav[];
    float smag[][];
    int srgb[][];
    float sbln[];
    int ust;
    int srx;
    int sry;
    int srz;
    float rcx;
    float rcy;
    float rcz;
    int sprk;
    int rtg[];
    boolean rbef[];
    int rx[];
    int ry[];
    int rz[];
    float vrx[];
    float vry[];
    float vrz[];
    boolean elec;
    boolean roted;
    int edl[];
    int edr[];
    int elc[] = {
        0, 0, 0, 0
    };
    boolean fix;
    int fcnt;
    int checkpoint;
    int fcol[] = {
        0, 0, 0
    };
    int scol[] = {
        0, 0, 0
    };
    int colok;
    boolean errd;
    String err;
    int roofat;
    int wh;
}
