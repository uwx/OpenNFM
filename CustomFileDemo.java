// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CustomFileDemo.java

import java.io.*;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomFileDemo
{
	private static JFrame frame;
	private static final JTextField textField = new JTextField();
	private static final JPanel panel = new JPanel();
	private static final JLabel lblNewLabel = new JLabel("Enter username...");
	private static final JButton btnNewButton = new JButton("Steal cars");
	private static final JButton btnNewButton2 = new JButton("Steal tracks");
	private static final JPanel panel_1 = new JPanel();
	private static final JPanel panel_2 = new JPanel();

    public CustomFileDemo()
    {
    }
    
    private static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("NFM Stealer");
        frame.setBounds(100, 100, 246, 281);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Display the window.
        frame.getContentPane().add(panel_2, BorderLayout.NORTH);
        panel_2.add(lblNewLabel);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        textField.setToolTipText("Enter the username of the player to steal cars and tracks");
        textField.setColumns(10);
        panel.add(textField);
        frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                String s = textField.getText();
                lblNewLabel.setText("Downloading cars from " + s);
                frame.pack();
                frame.setSize(frame.getWidth(), 128);
                new Thread(new Runnable() { public void run() {
	                String args1[];
	                try
	                {
	                    URL url = new URL((new StringBuilder()).append("http://multiplayer.needformadness.com/cars/lists/").append(s).append(".txt?reqlo=").append((int)(Math.random() * 1000D)).append("").toString());
	                    DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(url.openStream()));
	                    String s1 = datainputstream.readLine().trim();
	                    s1 = s1.substring(s1.indexOf("(") + 1, s1.length() - 1);
	                    args1 = s1.split(",");
	                    datainputstream.close();
	                }
	                catch(Exception exception)
	                {
	                    System.out.println((new StringBuilder()).append(s).append("\n").toString());
	                    exception.printStackTrace();
	                    args1 = null;
	                }
	                System.out.println("---- LIST OF CARS ----");
	                for (int i2 = 0; i2 < args1.length; i2++) System.out.println(args1[i2]);
	                System.out.println("----------------------");
	                int k = args1.length;
	                for(int l = 0; l < k;)
	                {
	                	lblNewLabel.setText("Downloading car " + args1[l]);
	                	frame.pack();
	                    frame.setSize(frame.getWidth(), 128);
	                    String s2 = args1[l];
	                    try
	                    {
	                        String s3 = (new StringBuilder()).append("http://multiplayer.needformadness.com/cars/").append(s2).append(".radq?reqlo=").append((int)(Math.random() * 1000D)).toString();
	                        s3 = s3.replace(' ', '_');
	                        URL url1 = new URL(s3);
	                        int i1 = url1.openConnection().getContentLength();
	                        DataInputStream datainputstream1 = new DataInputStream(new BufferedInputStream(url1.openStream()));
	                        byte abyte0[] = new byte[i1];
	                        datainputstream1.readFully(abyte0);
	                        ZipInputStream zipinputstream;
	                        if(abyte0[0] == 80 && abyte0[1] == 75 && abyte0[2] == 3)
	                        {
	                            zipinputstream = new ZipInputStream(new ByteArrayInputStream(abyte0));
	                        } else
	                        {
	                            byte abyte1[] = new byte[i1 - 40];
	                            for(int j1 = 0; j1 < i1 - 40; j1++)
	                            {
	                                byte byte0 = 20;
	                                if(j1 >= 500)
	                                    byte0 = 40;
	                                abyte1[j1] = abyte0[j1 + byte0];
	                            }
	
	                            zipinputstream = new ZipInputStream(new ByteArrayInputStream(abyte1));
	                        }
	                        ZipEntry zipentry = zipinputstream.getNextEntry();
	                        if(zipentry == null)
	                            continue;
	                        int k1 = Integer.valueOf(zipentry.getName()).intValue();
	                        byte abyte2[] = new byte[k1];
	                        int l1 = 0;
	                        int i2;
	                        for(; k1 > 0; k1 -= i2)
	                        {
	                            i2 = zipinputstream.read(abyte2, l1, k1);
	                            l1 += i2;
	                        }
	
	                        String s4 = new String(abyte2);
	                        s4 = (new StringBuilder()).append(s4).append("\n").toString();
	                        String s5 = "";
	                        int j2 = 0;
	                        for(int k2 = s4.indexOf("\n", 0); k2 != -1 && j2 < s4.length();)
	                        {
	                            String s6 = s4.substring(j2, k2);
	                            s6 = s6.trim();
	                            j2 = k2 + 1;
	                            k2 = s4.indexOf("\n", j2);
	                            if(!s6.startsWith("carmaker(") && !s6.startsWith("publish("))
	                            {
	                                s5 = (new StringBuilder()).append(s5).append("").append(s6).append("\n").toString();
	                            } else
	                            {
	                                s5 = s5.trim();
	                                s5 = (new StringBuilder()).append(s5).append("\n").toString();
	                            }
	                        }
	
	                        s5 = s5.trim();
	                        s5 = (new StringBuilder()).append(s5).append("\n\n").toString();
	                        File file = new File((new StringBuilder()).append("C:\\NFM Cars\\").append(s).toString());
	                        if(!file.exists())
	                            file.mkdirs();
	                        file = new File((new StringBuilder()).append("C:\\NFM Cars\\").append(s).append("\\").append(s2).append(".rad").toString());
	                        BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
	                        bufferedwriter.write(s5);
	                        bufferedwriter.close();
	                        bufferedwriter = null;
	                        zipinputstream.close();
	                        l++;
	                    }
	                    catch(Exception exception1)
	                    {
	                        System.out.println((new StringBuilder()).append(s2).append("\n").toString());
	                        exception1.printStackTrace();
	                        l++;
	                    }
	                    System.gc();
	                }
	                lblNewLabel.setText("Finished... Enter another username...");
	                try {
						this.wait(1000L);
		                lblNewLabel.setText("Enter username...");
					} catch (InterruptedException | java.lang.IllegalMonitorStateException e) {
						lblNewLabel.setText("Enter username...");
					}
                }}).start();
        	}
        });
        panel_1.add(btnNewButton);
        btnNewButton2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String s = textField.getText();
                lblNewLabel.setText("Downloading tracks from " + s);
                frame.pack();
                frame.setSize(frame.getWidth(), 128);
                new Thread(new Runnable() { public void run() {
                    String args1[];
                    try
                    {
                        URL url = new URL((new StringBuilder()).append("http://multiplayer.needformadness.com/tracks/lists/").append(s).append(".txt?reqlo=").append((int)(Math.random() * 1000D)).append("").toString());
                        DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(url.openStream()));
                        String s1 = datainputstream.readLine().trim();
                        s1 = s1.substring(s1.indexOf("(") + 1, s1.length() - 1);
                        args1 = s1.split(",");
                        datainputstream.close();
                    }
                    catch(Exception exception)
                    {
                        System.out.println((new StringBuilder()).append(s).append("\n").toString());
                        exception.printStackTrace();
                        args1 = null;
                    }
                    System.out.println("---- LIST OF TRACKS ----");
                    for (int i2 = 0; i2 < args1.length; i2++) System.out.println(args1[i2]);
                    System.out.println("----------------------");
                    int k = args1.length;
                    for(int l = 0; l < k;)
                    {
                    	lblNewLabel.setText("Downloading track " + args1[l]);
                    	frame.pack();
                        frame.setSize(frame.getWidth(), 128);
                        String s2 = args1[l];
                        try
                        {
                            String s3 = (new StringBuilder()).append("http://multiplayer.needformadness.com/tracks/").append(s2).append(".radq?reqlo=").append((int)(Math.random() * 1000D)).toString();
                            s3 = s3.replace(' ', '_');
                            URL url1 = new URL(s3);
                            int i1 = url1.openConnection().getContentLength();
                            DataInputStream datainputstream1 = new DataInputStream(new BufferedInputStream(url1.openStream()));
                            byte abyte0[] = new byte[i1];
                            datainputstream1.readFully(abyte0);
                            ZipInputStream zipinputstream;
                            if(abyte0[0] == 80 && abyte0[1] == 75 && abyte0[2] == 3)
                            {
                                zipinputstream = new ZipInputStream(new ByteArrayInputStream(abyte0));
                            } else
                            {
                                byte abyte1[] = new byte[i1 - 40];
                                for(int j1 = 0; j1 < i1 - 40; j1++)
                                {
                                    byte byte0 = 20;
                                    if(j1 >= 500)
                                        byte0 = 40;
                                    abyte1[j1] = abyte0[j1 + byte0];
                                }

                                zipinputstream = new ZipInputStream(new ByteArrayInputStream(abyte1));
                            }
                            ZipEntry zipentry = zipinputstream.getNextEntry();
                            if(zipentry == null)
                                continue;
                            int k1 = Integer.valueOf(zipentry.getName()).intValue();
                            byte abyte2[] = new byte[k1];
                            int l1 = 0;
                            int i2;
                            for(; k1 > 0; k1 -= i2)
                            {
                                i2 = zipinputstream.read(abyte2, l1, k1);
                                l1 += i2;
                            }

                            String s4 = new String(abyte2);
                            s4 = (new StringBuilder()).append(s4).append("\n").toString();
                            String s5 = "";
                            int j2 = 0;
                            for(int k2 = s4.indexOf("\n", 0); k2 != -1 && j2 < s4.length();)
                            {
                                String s6 = s4.substring(j2, k2);
                                s6 = s6.trim();
                                j2 = k2 + 1;
                                k2 = s4.indexOf("\n", j2);
                                if(s6.startsWith("soundtrack("))
                                {
                                	String s23 = getstr("soundtrack", s6, 0);
                                	lblNewLabel.setText("Downloading sound track " + s23);
                                	frame.pack();
                                    frame.setSize(frame.getWidth(), 128);
                                	int l3 = 0;
                                	try
                                    {
                                        String s8 = (new StringBuilder()).append("http://multiplayer.needformadness.com/tracks/music/").append(s23).append(".zip").toString();
                                        s8 = s8.replace(' ', '_');
                                        URL url3 = new URL(s8);
                                        int j15 = url3.openConnection().getContentLength();
                                        File file6 = new File("C:\\NFM Tracks\\music\\");
                                        if(!file6.exists())
                                            file6.mkdirs();
                                        file6 = new File((new StringBuilder()).append("").append("C:\\NFM Tracks\\music\\").append(s23).append(".zip").toString());
                                        if(file6.exists())
                                            if(file6.length() == (long)j15)
                                                l3 = 1;
                                            else {
                                            	lblNewLabel.setText("Sound track " + s23 + " already exists!");
                                            	frame.pack();
                                                frame.setSize(frame.getWidth(), 128);
                                            }
                                        if(l3 == 0)
                                        {
                                            DataInputStream datainputstream3 = new DataInputStream(url3.openStream());
                                            byte abyte1[] = new byte[j15];
                                            datainputstream3.readFully(abyte1);
                                            datainputstream3.close();
                                            FileOutputStream fileoutputstream1 = new FileOutputStream(file6);
                                            fileoutputstream1.write(abyte1);
                                            fileoutputstream1.close();
                                            fileoutputstream1 = null;
                                        }
                                    }
                                    catch(Exception exception13) {
                                    	lblNewLabel.setText("Could not download sound track " + s23);
                                    	frame.pack();
                                        frame.setSize(frame.getWidth(), 128);
                                    	exception13.printStackTrace();
                                    }
                                }
                                if(!s6.startsWith("trackmaker(") && !s6.startsWith("publish("))
                                {
                                    s5 = (new StringBuilder()).append(s5).append("").append(s6).append("\n").toString();
                                } else
                                {
                                    s5 = s5.trim();
                                    s5 = (new StringBuilder()).append(s5).append("\n").toString();
                                }
                            }

                            s5 = s5.trim();
                            s5 = (new StringBuilder()).append(s5).append("\n\n").toString();
                            File file = new File((new StringBuilder()).append("C:\\NFM Tracks\\").append(s).toString());
                            if(!file.exists())
                                file.mkdirs();
                            file = new File((new StringBuilder()).append("C:\\NFM Tracks\\").append(s).append("\\").append(s2).append(".txt").toString());
                            BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
                            bufferedwriter.write(s5);
                            bufferedwriter.close();
                            bufferedwriter = null;
                            zipinputstream.close();
                            l++;
                        }
                        catch(Exception exception1)
                        {
                            System.out.println((new StringBuilder()).append(s2).append("\n").toString());
                            exception1.printStackTrace();
                            l++;
                        }
                        System.gc();
                    }
                	lblNewLabel.setText("Finished... Enter another username...");
	                try {
						this.wait(1000L);
		                lblNewLabel.setText("Enter username...");
					} catch (InterruptedException | java.lang.IllegalMonitorStateException e) {
						lblNewLabel.setText("Enter username...");
					}
	            }}).start();
        	}
        });
        
        panel_1.add(btnNewButton2);
        frame.pack();
        frame.setSize(frame.getWidth(), 128);
        frame.setResizable(false);
        frame.setMinimumSize(frame.getSize());
        frame.setVisible(true);
    }

    public static void main(String args[])
        throws IOException
    {
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    	
    	/*
        String args3[] = {
            "AudiR8King"
        };
        dltracks(args3);
        */
    }
    
    public static void dlcars(String[] args3) {
        int i = args3.length;
    	for(int j = 0; j < i; j++)
        {
        	System.out.println("Downloading cars from " + args3[j]);
            String s = args3[j];
            String args1[];
            try
            {
                URL url = new URL((new StringBuilder()).append("http://multiplayer.needformadness.com/cars/lists/").append(s).append(".txt?reqlo=").append((int)(Math.random() * 1000D)).append("").toString());
                DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(url.openStream()));
                String s1 = datainputstream.readLine().trim();
                s1 = s1.substring(s1.indexOf("(") + 1, s1.length() - 1);
                args1 = s1.split(",");
                datainputstream.close();
            }
            catch(Exception exception)
            {
                System.out.println((new StringBuilder()).append(s).append("\n").toString());
                exception.printStackTrace();
                continue;
            }
            String args4[] = args1;
            System.out.println("---- LIST OF CARS ----");
            for (int i2 = 0; i2 < args1.length; i2++) System.out.println(args1[i2]);
            System.out.println("----------------------");
            int k = args4.length;
            for(int l = 0; l < k;)
            {
            	System.out.println("Downloading car " + args4[l]);
                String s2 = args4[l];
                try
                {
                    String s3 = (new StringBuilder()).append("http://multiplayer.needformadness.com/cars/").append(s2).append(".radq?reqlo=").append((int)(Math.random() * 1000D)).toString();
                    s3 = s3.replace(' ', '_');
                    URL url1 = new URL(s3);
                    int i1 = url1.openConnection().getContentLength();
                    DataInputStream datainputstream1 = new DataInputStream(new BufferedInputStream(url1.openStream()));
                    byte abyte0[] = new byte[i1];
                    datainputstream1.readFully(abyte0);
                    ZipInputStream zipinputstream;
                    if(abyte0[0] == 80 && abyte0[1] == 75 && abyte0[2] == 3)
                    {
                        zipinputstream = new ZipInputStream(new ByteArrayInputStream(abyte0));
                    } else
                    {
                        byte abyte1[] = new byte[i1 - 40];
                        for(int j1 = 0; j1 < i1 - 40; j1++)
                        {
                            byte byte0 = 20;
                            if(j1 >= 500)
                                byte0 = 40;
                            abyte1[j1] = abyte0[j1 + byte0];
                        }

                        zipinputstream = new ZipInputStream(new ByteArrayInputStream(abyte1));
                    }
                    ZipEntry zipentry = zipinputstream.getNextEntry();
                    if(zipentry == null)
                        continue;
                    int k1 = Integer.valueOf(zipentry.getName()).intValue();
                    byte abyte2[] = new byte[k1];
                    int l1 = 0;
                    int i2;
                    for(; k1 > 0; k1 -= i2)
                    {
                        i2 = zipinputstream.read(abyte2, l1, k1);
                        l1 += i2;
                    }

                    String s4 = new String(abyte2);
                    s4 = (new StringBuilder()).append(s4).append("\n").toString();
                    String s5 = "";
                    int j2 = 0;
                    for(int k2 = s4.indexOf("\n", 0); k2 != -1 && j2 < s4.length();)
                    {
                        String s6 = s4.substring(j2, k2);
                        s6 = s6.trim();
                        j2 = k2 + 1;
                        k2 = s4.indexOf("\n", j2);
                        if(!s6.startsWith("carmaker(") && !s6.startsWith("publish("))
                        {
                            s5 = (new StringBuilder()).append(s5).append("").append(s6).append("\n").toString();
                        } else
                        {
                            s5 = s5.trim();
                            s5 = (new StringBuilder()).append(s5).append("\n").toString();
                        }
                    }

                    s5 = s5.trim();
                    s5 = (new StringBuilder()).append(s5).append("\n\n").toString();
                    File file = new File((new StringBuilder()).append("C:\\NFM Cars\\").append(s).toString());
                    if(!file.exists())
                        file.mkdirs();
                    file = new File((new StringBuilder()).append("C:\\NFM Cars\\").append(s).append("\\").append(s2).append(".rad").toString());
                    BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
                    bufferedwriter.write(s5);
                    bufferedwriter.close();
                    bufferedwriter = null;
                    zipinputstream.close();
                    l++;
                }
                catch(Exception exception1)
                {
                    System.out.println((new StringBuilder()).append(s2).append("\n").toString());
                    exception1.printStackTrace();
                    l++;
                }
            }

            System.gc();
        }
    }
    
    public static void dltracks(String[] args3) {
        // dl tracks
        int i = args3.length;
        for(int j = 0; j < i; j++)
        {
        	System.out.println("Downloading tracks from " + args3[j]);
            String s = args3[j];
            String args1[];
            try
            {
                URL url = new URL((new StringBuilder()).append("http://multiplayer.needformadness.com/tracks/lists/").append(s).append(".txt?reqlo=").append((int)(Math.random() * 1000D)).append("").toString());
                DataInputStream datainputstream = new DataInputStream(new BufferedInputStream(url.openStream()));
                String s1 = datainputstream.readLine().trim();
                s1 = s1.substring(s1.indexOf("(") + 1, s1.length() - 1);
                args1 = s1.split(",");
                datainputstream.close();
            }
            catch(Exception exception)
            {
                System.out.println((new StringBuilder()).append(s).append("\n").toString());
                exception.printStackTrace();
                continue;
            }
            String args4[] = args1;
            System.out.println("---- LIST OF TRACKS ----");
            for (int i2 = 0; i2 < args1.length; i2++) System.out.println(args1[i2]);
            System.out.println("----------------------");
            int k = args4.length;
            for(int l = 0; l < k;)
            {
            	System.out.println("Downloading track " + args4[l]);
                String s2 = args4[l];
                try
                {
                    String s3 = (new StringBuilder()).append("http://multiplayer.needformadness.com/tracks/").append(s2).append(".radq?reqlo=").append((int)(Math.random() * 1000D)).toString();
                    s3 = s3.replace(' ', '_');
                    URL url1 = new URL(s3);
                    int i1 = url1.openConnection().getContentLength();
                    DataInputStream datainputstream1 = new DataInputStream(new BufferedInputStream(url1.openStream()));
                    byte abyte0[] = new byte[i1];
                    datainputstream1.readFully(abyte0);
                    ZipInputStream zipinputstream;
                    if(abyte0[0] == 80 && abyte0[1] == 75 && abyte0[2] == 3)
                    {
                        zipinputstream = new ZipInputStream(new ByteArrayInputStream(abyte0));
                    } else
                    {
                        byte abyte1[] = new byte[i1 - 40];
                        for(int j1 = 0; j1 < i1 - 40; j1++)
                        {
                            byte byte0 = 20;
                            if(j1 >= 500)
                                byte0 = 40;
                            abyte1[j1] = abyte0[j1 + byte0];
                        }

                        zipinputstream = new ZipInputStream(new ByteArrayInputStream(abyte1));
                    }
                    ZipEntry zipentry = zipinputstream.getNextEntry();
                    if(zipentry == null)
                        continue;
                    int k1 = Integer.valueOf(zipentry.getName()).intValue();
                    byte abyte2[] = new byte[k1];
                    int l1 = 0;
                    int i2;
                    for(; k1 > 0; k1 -= i2)
                    {
                        i2 = zipinputstream.read(abyte2, l1, k1);
                        l1 += i2;
                    }

                    String s4 = new String(abyte2);
                    s4 = (new StringBuilder()).append(s4).append("\n").toString();
                    String s5 = "";
                    int j2 = 0;
                    for(int k2 = s4.indexOf("\n", 0); k2 != -1 && j2 < s4.length();)
                    {
                        String s6 = s4.substring(j2, k2);
                        s6 = s6.trim();
                        j2 = k2 + 1;
                        k2 = s4.indexOf("\n", j2);
                        if(s6.startsWith("soundtrack("))
                        {
                        	dlmusic(getstr("soundtrack", s6, 0));
                        }
                        if(!s6.startsWith("trackmaker(") && !s6.startsWith("publish("))
                        {
                            s5 = (new StringBuilder()).append(s5).append("").append(s6).append("\n").toString();
                        } else
                        {
                            s5 = s5.trim();
                            s5 = (new StringBuilder()).append(s5).append("\n").toString();
                        }
                    }

                    s5 = s5.trim();
                    s5 = (new StringBuilder()).append(s5).append("\n\n").toString();
                    File file = new File((new StringBuilder()).append("C:\\NFM Tracks\\").append(s).toString());
                    if(!file.exists())
                        file.mkdirs();
                    file = new File((new StringBuilder()).append("C:\\NFM Tracks\\").append(s).append("\\").append(s2).append(".txt").toString());
                    BufferedWriter bufferedwriter = new BufferedWriter(new FileWriter(file));
                    bufferedwriter.write(s5);
                    bufferedwriter.close();
                    bufferedwriter = null;
                    zipinputstream.close();
                    l++;
                }
                catch(Exception exception1)
                {
                    System.out.println((new StringBuilder()).append(s2).append("\n").toString());
                    exception1.printStackTrace();
                    l++;
                }
            }
            System.gc();
        }
    }
    
    static int getint(String s, String s1, int i)
    {
        int k = 0;
        String s3 = "";
        for(int j = s.length() + 1; j < s1.length(); j++)
        {
            String s2 = (new StringBuilder()).append("").append(s1.charAt(j)).toString();
            if(s2.equals(",") || s2.equals(")"))
            {
                k++;
                j++;
            }
            if(k == i)
                s3 = (new StringBuilder()).append(s3).append(s1.charAt(j)).toString();
        }

        return Integer.valueOf(s3).intValue();
    }
    
    static String getstr(String s, String s1, int i)
    {
        int k = 0;
        String s3 = "";
        for(int j = s.length() + 1; j < s1.length(); j++)
        {
            String s2 = (new StringBuilder()).append("").append(s1.charAt(j)).toString();
            if(s2.equals(",") || s2.equals(")"))
            {
                k++;
                j++;
            }
            if(k == i)
                s3 = (new StringBuilder()).append(s3).append(s1.charAt(j)).toString();
        }

        return s3;
    }
    
    static void dlmusic(String s23)
    {
    	System.out.println("Downloading sound track " + s23);
    	int l3 = 0;
    	try
        {
            String s8 = (new StringBuilder()).append("http://multiplayer.needformadness.com/tracks/music/").append(s23).append(".zip").toString();
            s8 = s8.replace(' ', '_');
            URL url3 = new URL(s8);
            int j15 = url3.openConnection().getContentLength();
            File file6 = new File("C:\\NFM Tracks\\music\\");
            if(!file6.exists())
                file6.mkdirs();
            file6 = new File((new StringBuilder()).append("").append("C:\\NFM Tracks\\music\\").append(s23).append(".zip").toString());
            if(file6.exists())
                if(file6.length() == (long)j15)
                    l3 = 1;
                else
                	System.out.println("Sound track " + s23 + " already exists!");
            if(l3 == 0)
            {
                DataInputStream datainputstream3 = new DataInputStream(url3.openStream());
                byte abyte1[] = new byte[j15];
                datainputstream3.readFully(abyte1);
                datainputstream3.close();
                FileOutputStream fileoutputstream1 = new FileOutputStream(file6);
                fileoutputstream1.write(abyte1);
                fileoutputstream1.close();
                fileoutputstream1 = null;
            }
        }
        catch(Exception exception13) {System.out.println("Could not download sound track " + s23); exception13.printStackTrace();}
    }
	public JFrame getFrame() {
		return frame;
	}
}
