
//<<<<<<< HEAD
import java.applet.Applet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
//import org.apache.commons.io.FileUtils;

import netscape.javascript.JSObject;

public class GameSparker extends Applet implements Runnable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1689987003531876702L;

	@Override
	public boolean keyDown(final Event event, final int i) {
		if (!exwist) {
			if (i == 1004)
				u[0].up = true;
			if (i == 1005)
				u[0].down = true;
			if (i == 1007)
				u[0].right = true;
			if (i == 1006)
				u[0].left = true;
			if (i == 32)
				u[0].handb = true;
			if (i == 120 || i == 88)
				u[0].lookback = -1;
			if (i == 122 || i == 90)
				u[0].lookback = 1;
			if (i == 10 || i == 80 || i == 112 || i == 27)
				u[0].enter = true;
			if (i == 77 || i == 109)
				if (u[0].mutem)
					u[0].mutem = false;
				else
					u[0].mutem = true;
			if (i == 78 || i == 110)
				if (u[0].mutes)
					u[0].mutes = false;
				else
					u[0].mutes = true;
			if (i == 97 || i == 65)
				if (u[0].arrace)
					u[0].arrace = false;
				else
					u[0].arrace = true;
			if (i == 118 || i == 86) {
				view++;
				if (view == 5)
					view = 0;
			}
		}
		return false;
	}

	@Override
	public void stop() {
		if (exwist && gamer != null) {
			System.gc();
			gamer.stop();
			gamer = null;
		}
		exwist = true;
	}

	@Override
	public boolean lostFocus(final Event event, final Object obj) {
		if (!exwist && !lostfcs) {
			lostfcs = true;
			mouses = 0;
			u[0].falseo();
			setCursor(new Cursor(0));
		}
		return false;
	}

	@Override
	public boolean gotFocus(final Event event, final Object obj) {
		if (!exwist && lostfcs)
			lostfcs = false;
		return false;
	}

	public String getstring(final String s, final String s1, final int i) {
		int k = 0;
		String s3 = "";
		for (int j = s.length() + 1; j < s1.length(); j++) {
			final String s2 = "" + s1.charAt(j);
			if (s2.equals(",") || s2.equals(")")) {
				k++;
				j++;
			}
			if (k == i)
				s3 += s1.charAt(j);
		}

		return s3;
	}

	public int getint(final String s, final String s1, final int i) {
		int k = 0;
		String s3 = "";
		for (int j = s.length() + 1; j < s1.length(); j++) {
			final String s2 = "" + s1.charAt(j);
			if (s2.equals(",") || s2.equals(")")) {
				k++;
				j++;
			}
			if (k == i)
				s3 += s1.charAt(j);
		}

		return Integer.valueOf(s3).intValue();
	}

	/*
	 * public int readcookie(String string) { int i = -1; try { JSObject
	 * jsobject = JSObject.getWindow(this); jsobject.eval("scook=GetCookie('" +
	 * string + "');"); i =
	 * Integer.valueOf(String.valueOf(jsobject.getMember("scook"))).intValue();
	 * }catch (NoClassDefFoundError localException) { System.out.println(
	 * "Not running in web browser"); return 51; }catch (Exception
	 * localException) { localException.printStackTrace(); return 51; } return
	 * i; }
	 */

	public int readcookie(final String string) {
		int i = -1;
		try {
			// if in browser and plugin.jar present
			final JSObject jsobject = JSObject.getWindow(this);
			jsobject.eval("scook=GetCookie('" + string + "');");
			i = Integer.valueOf(String.valueOf(jsobject.getMember("scook"))).intValue();
		} catch (NoClassDefFoundError | netscape.javascript.JSException localException) {
			// if not in browser or no plugin.jar
			System.out.println("Not running in web browser (" + string + ")");
			try {
				final BufferedReader saveFile = new BufferedReader(new FileReader(string + ".dat"));

				final String saveLine = saveFile.readLine();
				saveFile.close();
				return Integer.parseInt(saveLine);
			} catch (final IOException ioexception) {
				// if not in browser or no plugin.jar
				// and the saved game doesn't exist
				System.out.println(ioexception.toString());
				System.out.println(string + ".dat probably doesn't exist");
				return -1;
			}
		} catch (final Exception localException) {
			// if in browser and the saved game doesn't exist
			System.out.println("No cookie found (" + string + ")");
			localException.printStackTrace();
			return -1;
		}
		System.out.println("Successfully loaded cookie " + string);
		return i; // will this kill my code? find out at 11
	}

	@Override
	public void paint(final Graphics g) {
		g.drawImage(offImage, 0, 0, this);
	}

	public GameSparker() {
		u = new Control[51];
		mouses = 0;
		xm = 0;
		ym = 0;
		lostfcs = false;
		exwist = true;
		nob = 0;
		notb = 0;
		view = 0;
	}

	public void loadbase(final ContO aconto[], final Medium medium, final Trackers trackers,
			final xtGraphics xtgraphics, final String as[]) {
		xtgraphics.dnload += 6;
		try {
			final URL url = new URL(getCodeBase(), "data/models.radq");
			final DataInputStream datainputstream = new DataInputStream(url.openStream());
			final ZipInputStream zipinputstream = new ZipInputStream(datainputstream);
			ZipEntry zipentry = zipinputstream.getNextEntry();
			for (; zipentry != null; zipentry = zipinputstream.getNextEntry()) {
				int i = 0;
				int j = 0;
				do
					if (zipentry.getName().startsWith(as[j]))
						i = j;
				while (++j < as.length);
				j = (int) zipentry.getSize();
				final byte abyte0[] = new byte[j];
				int k = 0;
				int l;
				for (; j > 0; j -= l) {
					l = zipinputstream.read(abyte0, k, j);
					k += l;
				}

				aconto[i] = new ContO(abyte0, medium, trackers);
				xtgraphics.dnload++;
			}

			datainputstream.close();
			zipinputstream.close();
		} catch (final Exception exception) {
			System.out.println("Error Reading Models: " + exception);
		}
		System.gc();
	}

	@Override
	public void update(final Graphics g) {
		paint(g);
	}

	/*
	 * Deprecated int since the applet is now running on a frame
	 * 
	 * 1: LD sound 2: HD sound
	 */
	public int sunytyp() {
		try {
			final String s = System.getProperty("java.version");
			final String s1 = "" + getAppletContext();
			if (!s1.startsWith("com.ms."))
				return !s.startsWith("1.3") && !s.startsWith("1.4") ? 2 : 1;
			else
				return 1;
		} catch (final NullPointerException ex) {
			// 1: LD sound
			// 2: HD sound
			System.out.println("Running on a frame");
			return 2;
		}
	}

	@Override
	public boolean keyUp(final Event event, final int i) {
		if (!exwist) {
			if (i == 1004)
				u[0].up = false;
			if (i == 1005)
				u[0].down = false;
			if (i == 1007)
				u[0].right = false;
			if (i == 1006)
				u[0].left = false;
			if (i == 32)
				u[0].handb = false;
			if (i == 120 || i == 88 || i == 122 || i == 90)
				u[0].lookback = 0;
		}
		return false;
	}

	@Override
	public void start() {
		if (gamer == null)
			gamer = new Thread(this);
		gamer.start();
	}

	@Override
	public boolean mouseDown(final Event event, final int i, final int j) {
		if (!exwist && mouses == 0) {
			xm = i;
			ym = j;
			mouses = 1;
		}
		return false;
	}

	public void loadstage(final ContO aconto[], final ContO aconto1[], final Medium medium, final Trackers trackers,
			final CheckPoints checkpoints, final xtGraphics xtgraphics, final Madness amadness[], final Record record) {
		trackers.nt = 0;
		nob = xtgraphics.nplayers;
		notb = 0;
		checkpoints.n = 0;
		checkpoints.nsp = 0;
		checkpoints.fn = 0;
		checkpoints.haltall = false;
		checkpoints.wasted = 0;
		checkpoints.catchfin = 0;
		medium.lightson = false;
		medium.ground = 250;
		view = 0;
		int i = 0;
		int j = 100;
		int k = 0;
		int l = 100;
		//////////////
		final int i98_ = 100;
		final int i99_ = 0;
		final int i100_ = 100;
		if (!trackers.tracksReady)
			TracksSetup(trackers);
		String s1 = "";
		try {
			final URL url = new URL(getCodeBase(), "tracks/" + checkpoints.stage + ".txt");
			final DataInputStream datainputstream = new DataInputStream(url.openStream());
			String s;
			while ((s = datainputstream.readLine()) != null) {
				s1 = "" + s.trim();
				if (s1.startsWith("mountains"))
					medium.mgen = getint("mountains", s1, 0);
				////////////////
				if (s1.startsWith("snap"))
					medium.setsnap(getint("snap", s1, 0), getint("snap", s1, 1), getint("snap", s1, 2));
				if (s1.startsWith("sky")) {
					medium.setsky(getint("sky", s1, 0), getint("sky", s1, 1), getint("sky", s1, 2));
					xtgraphics.snap(checkpoints.stage);
				}
				if (s1.startsWith("ground"))
					medium.setgrnd(getint("ground", s1, 0), getint("ground", s1, 1), getint("ground", s1, 2));
				if (s1.startsWith("polys"))
					medium.setpolys(getint("polys", s1, 0), getint("polys", s1, 1), getint("polys", s1, 2));
				if (s1.startsWith("fog"))
					medium.setfade(getint("fog", s1, 0), getint("fog", s1, 1), getint("fog", s1, 2));
				if (s1.startsWith("density"))
					medium.fogd = getint("density", s1, 0);
				if (s1.startsWith("texture"))
					medium.setexture(getint("texture", s1, 0), getint("texture", s1, 1), getint("texture", s1, 2),
							getint("texture", s1, 3));
				if (s1.startsWith("clouds"))
					medium.setcloads(getint("clouds", s1, 0), getint("clouds", s1, 1), getint("clouds", s1, 2),
							getint("clouds", s1, 3), getint("clouds", s1, 4));
				if (s1.startsWith("fadefrom")) {
					medium.fadfrom(getint("fadefrom", s1, 0));
					medium.origfade = medium.fade[0];
				}
				if (s1.startsWith("lightson"))
					medium.lightson = true;
				if (s1.startsWith("set")) {
					int k1 = getint("set", s1, 0);
					k1 += xtgraphics.nCars - 10; // MAY NEED TO BE - 10
					aconto[nob] = new ContO(aconto1[k1], getint("set", s1, 1), medium.ground - aconto1[k1].grat,
							getint("set", s1, 2), getint("set", s1, 3));
					if (s1.indexOf(")p") != -1) {
						checkpoints.x[checkpoints.n] = getint("chk", s1, 1);
						checkpoints.z[checkpoints.n] = getint("chk", s1, 2);
						checkpoints.y[checkpoints.n] = 0;
						checkpoints.typ[checkpoints.n] = 0;
						if (s1.indexOf(")pt") != -1)
							checkpoints.typ[checkpoints.n] = -1;
						if (s1.indexOf(")pr") != -1)
							checkpoints.typ[checkpoints.n] = -2;
						if (s1.indexOf(")po") != -1)
							checkpoints.typ[checkpoints.n] = -3;
						if (s1.indexOf(")ph") != -1)
							checkpoints.typ[checkpoints.n] = -4;
						if (s1.indexOf("out") != -1)
							System.out.println("out: " + checkpoints.n);
						checkpoints.n++;
						notb = nob + 1;
					}
					nob++;
				}
				if (s1.startsWith("fltset")) {
					int i2 = getint("fltset", s1, 0);
					i2 += xtgraphics.nCars - 10; // MAY NEED TO BE - 10
					aconto[nob] = new ContO(aconto1[i2], getint("fltset", s1, 1), getint("fltset", s1, 3),
							getint("set", s1, 2), getint("set", s1, 4));
					if (s1.indexOf(")p") != -1) {
						getint("fltset", s1, 4);
						checkpoints.x[checkpoints.n] = getint("fltset", s1, 1);
						checkpoints.z[checkpoints.n] = getint("fltset", s1, 2);
						checkpoints.y[checkpoints.n] = getint("fltset", s1, 3);
						checkpoints.typ[checkpoints.n] = 0;
						if (s1.indexOf(")pt") != -1)
							checkpoints.typ[checkpoints.n] = -1;
						if (s1.indexOf(")pr") != -1)
							checkpoints.typ[checkpoints.n] = -2;
						if (s1.indexOf(")po") != -1)
							checkpoints.typ[checkpoints.n] = -3;
						if (s1.indexOf(")ph") != -1)
							checkpoints.typ[checkpoints.n] = -4;
						checkpoints.n++;
						notb = nob + 1;
					}
					nob++;
				}
				if (s1.startsWith("chk")) {
					int l1 = getint("chk", s1, 0);
					l1 += xtgraphics.nCars - 10; // MAY NEED TO BE - 10
					aconto[nob] = new ContO(aconto1[l1], getint("chk", s1, 1), medium.ground - aconto1[l1].grat,
							getint("chk", s1, 2), getint("chk", s1, 3));
					checkpoints.x[checkpoints.n] = getint("chk", s1, 1);
					checkpoints.z[checkpoints.n] = getint("chk", s1, 2);
					checkpoints.y[checkpoints.n] = medium.ground - aconto1[l1].grat;
					if (getint("chk", s1, 3) == 0)
						checkpoints.typ[checkpoints.n] = 1;
					else
						checkpoints.typ[checkpoints.n] = 2;
					checkpoints.pcs = checkpoints.n;
					checkpoints.n++;
					aconto[nob].checkpoint = checkpoints.nsp + 1;
					checkpoints.nsp++;
					nob++;
					notb = nob;
				}
				if (s1.startsWith("fltchk")) {
					int l1 = getint("fltchk", s1, 0);
					l1 += xtgraphics.nCars - 10; // MAY NEED TO BE - 10
					aconto[nob] = new ContO(aconto1[l1], getint("fltchk", s1, 1), getint("fltchk", s1, 3),
							getint("fltchk", s1, 2), getint("fltchk", s1, 4));
					checkpoints.x[checkpoints.n] = getint("fltchk", s1, 1);
					checkpoints.z[checkpoints.n] = getint("fltchk", s1, 2);
					checkpoints.y[checkpoints.n] = getint("fltchk", s1, 3);
					if (getint("fltchk", s1, 4) == 0)
						checkpoints.typ[checkpoints.n] = 1;
					else
						checkpoints.typ[checkpoints.n] = 2;
					checkpoints.pcs = checkpoints.n;
					checkpoints.n++;
					aconto[nob].checkpoint = checkpoints.nsp + 1;
					checkpoints.nsp++;
					nob++;
					notb = nob;
				}
				if (s1.startsWith("fix")) {
					int i2 = getint("fix", s1, 0);
					i2 += xtgraphics.nCars - 10; // MAY NEED TO BE - 10
					aconto[nob] = new ContO(aconto1[i2], getint("fix", s1, 1), getint("fix", s1, 3),
							getint("fix", s1, 2), getint("fix", s1, 4));
					checkpoints.fx[checkpoints.fn] = getint("fix", s1, 1);
					checkpoints.fz[checkpoints.fn] = getint("fix", s1, 2);
					checkpoints.fy[checkpoints.fn] = getint("fix", s1, 3);
					aconto[nob].elec = true;
					if (getint("fix", s1, 4) != 0) {
						checkpoints.roted[checkpoints.fn] = true;
						aconto[nob].roted = true;
					} else
						checkpoints.roted[checkpoints.fn] = false;
					if (s1.indexOf(")s") != -1)
						checkpoints.special[checkpoints.fn] = true;
					else
						checkpoints.special[checkpoints.fn] = false;
					checkpoints.fn++;
					nob++;
					notb = nob;
				}
				if (s1.startsWith("nlaps"))
					checkpoints.nlaps = getint("nlaps", s1, 0);
				if (s1.startsWith("name"))
					checkpoints.name = getstring("name", s1, 0).replace('|', ',');
				if (s1.startsWith("maxr")) {
					final int j2 = getint("maxr", s1, 0);
					final int j3 = getint("maxr", s1, 1);
					i = j3;
					final int j4 = getint("maxr", s1, 2);
					for (int j5 = 0; j5 < j2; j5++) {
						aconto[nob] = new ContO(aconto1[45 + (xtgraphics.nCars - 16)], j3,
								medium.ground - aconto1[45 + (xtgraphics.nCars - 16)].grat, j5 * 4800 + j4, 0);
						nob++;
					}

					trackers.y[trackers.nt] = -5000;
					trackers.rady[trackers.nt] = 7100;
					trackers.x[trackers.nt] = j3 + 500;
					trackers.radx[trackers.nt] = 600;
					trackers.z[trackers.nt] = ((j2 * 4800) / 2 + j4) - 2400;
					trackers.radz[trackers.nt] = (j2 * 4800) / 2;
					trackers.xy[trackers.nt] = 90;
					trackers.zy[trackers.nt] = 0;
					trackers.dam[trackers.nt] = 1;
					trackers.nt++;
				}
				if (s1.startsWith("maxl")) {
					final int k2 = getint("maxl", s1, 0);
					final int k3 = getint("maxl", s1, 1);
					j = k3;
					final int k4 = getint("maxl", s1, 2);
					for (int k5 = 0; k5 < k2; k5++) {
						aconto[nob] = new ContO(aconto1[45 + (xtgraphics.nCars - 16)], k3,
								medium.ground - aconto1[45 + (xtgraphics.nCars - 16)].grat, k5 * 4800 + k4, 0);
						nob++;
					}

					trackers.y[trackers.nt] = -5000;
					trackers.rady[trackers.nt] = 7100;
					trackers.x[trackers.nt] = k3 - 500;
					trackers.radx[trackers.nt] = 600;
					trackers.z[trackers.nt] = ((k2 * 4800) / 2 + k4) - 2400;
					trackers.radz[trackers.nt] = (k2 * 4800) / 2;
					trackers.xy[trackers.nt] = -90;
					trackers.zy[trackers.nt] = 0;
					trackers.dam[trackers.nt] = 1;
					trackers.nt++;
				}

				if (s1.startsWith("maxt")) {
					final int l2 = getint("maxt", s1, 0);
					final int l3 = getint("maxt", s1, 1);
					k = l3;
					final int l4 = getint("maxt", s1, 2);
					for (int l5 = 0; l5 < l2; l5++) {
						aconto[nob] = new ContO(aconto1[45 + (xtgraphics.nCars - 16)], l5 * 4800 + l4,
								medium.ground - aconto1[45 + (xtgraphics.nCars - 16)].grat, l3, 90);
						nob++;
					}

					trackers.y[trackers.nt] = -5000;
					trackers.rady[trackers.nt] = 7100;
					trackers.z[trackers.nt] = l3 + 500;
					trackers.radz[trackers.nt] = 600;
					trackers.x[trackers.nt] = ((l2 * 4800) / 2 + l4) - 2400;
					trackers.radx[trackers.nt] = (l2 * 4800) / 2;
					trackers.zy[trackers.nt] = 90;
					trackers.xy[trackers.nt] = 0;
					trackers.dam[trackers.nt] = 1;
					trackers.nt++;
				}
				if (s1.startsWith("maxb")) {
					final int i3 = getint("maxb", s1, 0);
					final int i4 = getint("maxb", s1, 1);
					l = i4;
					final int i5 = getint("maxb", s1, 2);
					for (int i6 = 0; i6 < i3; i6++) {
						aconto[nob] = new ContO(aconto1[45 + (xtgraphics.nCars - 16)], i6 * 4800 + i5,
								medium.ground - aconto1[45 + (xtgraphics.nCars - 16)].grat, i4, 90);
						nob++;
					}

					trackers.y[trackers.nt] = -5000;
					trackers.rady[trackers.nt] = 7100;
					trackers.z[trackers.nt] = i4 - 500;
					trackers.radz[trackers.nt] = 600;
					trackers.x[trackers.nt] = ((i3 * 4800) / 2 + i5) - 2400;
					trackers.radx[trackers.nt] = (i3 * 4800) / 2;
					trackers.zy[trackers.nt] = -90;
					trackers.xy[trackers.nt] = 0;
					trackers.dam[trackers.nt] = 1;
					trackers.nt++;
				}
			}
			datainputstream.close();
			medium.newpolys(j, i - j, l, k - l, trackers, notb);
			medium.newmountains(i98_, i, i100_, i99_);
			medium.newclouds(j, i, l, k);
			medium.newstars();
		} catch (final Exception exception) {
			xtgraphics.fase = 3;
			System.out.println("Error in stage " + checkpoints.stage);
			System.out.println("" + exception);
			System.out.println("At line: " + s1);
		}
		if (checkpoints.stage == 16)
			medium.lightn = 0;
		else
			medium.lightn = -1;
		if (checkpoints.stage == 1)
			medium.nochekflk = false;
		else
			medium.nochekflk = true;
		if (xtgraphics.fase == 2) {
			medium.trx = 0L;
			medium.trz = 0L;
			if (trackers.nt >= 4) {
				int i1 = 4;
				do {
					medium.trx += trackers.x[trackers.nt - i1];
					medium.trz += trackers.z[trackers.nt - i1];
				} while (--i1 > 0);
			}
			medium.trx = medium.trx / 4L;
			medium.trz = medium.trz / 4L;
			medium.ptr = 0;
			medium.ptcnt = -10;
			medium.hit = 45000;
			medium.fallen = 0;
			medium.nrnd = 0;
			medium.trk = true;
			xtgraphics.fase = 1;
			mouses = 0;
		}
		int j1 = 0;
		do
			u[j1].reset(checkpoints, xtgraphics.sc[j1]);
		while (++j1 < xtgraphics.nplayers);
		xtgraphics.resetstat(checkpoints.stage);
		j1 = 0;
		int prevx = 0;
		int prevz = 0;
		do {
			// STARTING LOCATIONS
			// aconto[j1] = new ContO(aconto1[xtgraphics.sc[j1]],
			// xtgraphics.xstart[j1], 250 - aconto1[xtgraphics.sc[j1]].grat,
			// xtgraphics.zstart[j1], 0);
			for (int x = 0; x < trackers.nt; x++) {
				if (trackers.x[x] > prevx)
					prevx = trackers.x[x];
				if (trackers.z[x] > prevz)
					prevz = trackers.z[x];
			}
			final int copx = (int) ((Math.random() * prevx) - (Math.random() * (prevx * 2)));
			final int copz = (int) ((Math.random() * prevz) - (Math.random() * (prevz * 2)));
			System.out.println("" + j1);
			System.out.println("" + xtgraphics.sc[j1]);
			System.out.println("" + aconto1[xtgraphics.sc[j1]].grat);
			if (j1 >= xtgraphics.nplayers - xtgraphics.ncops - 1)
				aconto[j1] = new ContO(aconto1[xtgraphics.sc[j1]], copx, 250 - aconto1[xtgraphics.sc[j1]].grat, copz,
						0);
			else if (j1 % 3 == 0)
				aconto[j1] = new ContO(aconto1[xtgraphics.sc[j1]], 0, 250 - aconto1[xtgraphics.sc[j1]].grat,
						-760 + ((j1 / 3) * 760), 0);
			else if (j1 % 3 == 1)
				aconto[j1] = new ContO(aconto1[xtgraphics.sc[j1]], -350, 250 - aconto1[xtgraphics.sc[j1]].grat,
						-380 + (j1 / 3 * 760), 0);
			else if (j1 % 3 == 2)
				aconto[j1] = new ContO(aconto1[xtgraphics.sc[j1]], 350, 250 - aconto1[xtgraphics.sc[j1]].grat,
						-380 + (j1 / 3 * 760), 0);
			amadness[j1].reseto(xtgraphics.sc[j1], aconto[j1], checkpoints);
		} while (++j1 < xtgraphics.nplayers);

		record.reset(aconto);

		System.gc();
	}

	public void TracksSetup(final Trackers trackers) {

	}

	@Override
	public void run() {
		rd.setColor(new Color(0, 0, 0));
		rd.fillRect(0, 0, 670, 400);
		repaint();
		final Trackers trackers = new Trackers();
		TracksSetup(trackers);
		final Medium medium = new Medium();
		int i = 5;
		int j = 530;
		final int k = sunytyp();
		if (k != 0)
			i = 15;
		if (k != 2)
			j = 500;
		final CheckPoints checkpoints = new CheckPoints();
		final xtGraphics xtgraphics = new xtGraphics(medium, rd, this, settings);
		xtgraphics.makeFont();
		xtgraphics.loaddata(k);
		final Record record = new Record(medium);
		final String as[] = { "TunedTornadoShark", "formula7", "canyenaro", "TunedLVC", "TunedNimi", "TunedMaxRevenge",
				"TunedOxide", "koolkat", "drifter", "policecops", "mustang", "king", "TunedM8", "masheen", "radicalone",
				"TunedMonstaa", // 16

				// added cars
				"car17", "car18", "car19", "car20", "car21", "car22", "car23", "car24", "car25", "car26", "car27",
				"car28", "car29", "car30", "car31", "car32", "car33", "car34", "car35", "car36", "car37", "car38",
				"car39", "car40", "car41", "car42", "car43", "car44", "car45", "car46", "car47", "car48", "car49",
				"car50", /* "car51", */

				"road", "froad", "twister2", "twister1", // 20
				"turn", "offroad", "bumproad", "offturn", "nroad", "nturn", "roblend", "noblend", "rnblend", "roadend", // 30
				"offroadend", "hpground", "ramp30", "cramp35", "dramp15", "dhilo15", "slide10", "takeoff", "sramp22",
				"offbump", // 40
				"offramp", "sofframp", "halfpipe", "spikes", "rail", "thewall", "checkpoint", "fixpoint",
				"offcheckpoint", "sideoff", // 50
				"bsideoff", "uprise", "riseroad", "sroad", "soffroad", "tree", "speedramp", "mountain", "trap",
				"tunnel", "newoffroad", // 61

				// new parts
				"tree9", "tree10", "grass", "lamppost", "sun", "cloud", "tree11", "postalbox", "tree12", "stopsign",
				"concretebarrier", "dumpstertrashcan", "tree13", "pavedroad", "pavedturn", "pavedend", "burj_al_arab",
				"feather_reed", "pyramid_of_giza", "rock", "rock2", "rock3", "rainbowroad", "questionmarkblock",
				"rainbowstraight", "rainbowturn", "rainbowend", "onepartmaze", // 27
				"car51", 
				"car52", 
				"car53", 
				"car54", 
				"car55", 
				"car56", 
				"car57", 
				"car58", 
				"car59", 
				"car60", 
				"car61", 
				"car62", 
				"car63", 
				"car64", 
				"car65", 
				"car66", 
				"car67", 
				"car68", 
				"car69", 
				"car70", 
				"car71", 
				"car72", 
				"car73", 
				"car74", 
				"car75", 
				"car76", 
				"car77", 
				"car78", 
				"car79", 
				"car80", 
				"car81", 
				"car82", 
				"car83", 
				"car84", 
				"car85", 
				"car86", 
				"car87", 
				"car88", 
				"car89", 
				"car90", 
				"car91", 
				"car92", 
				"car93", 
				"car94", 
				"car95", 
				"car96", 
				"car97", 
				"car98", 
				"car99", 
				"car100", 
				"car101", 
				"car102", 
				"car103", 
				"car104", 
				"car105", 
				"car106", 
				"car107", 
				"car108", 
				"car109", 
				"car110", 
				"car111", 
				"car112", 
				"car113", 
				"car114", 
				"car115", 
				"car116", 
				"car117", 
				"car118", 
				"car119", 
				"car120", 
				"car121", 
				"car122", 
				"car123", 
				"car124", 
				"car125", 
				"car126", 
				"car127", 
				"car128", 
				"car129", 
				"car130", 
				"car131", 
				"car132", 
				"car133", 
				"car134", 
				"car135", 
				"car136", 
				"car137", 
				"car138", 
				"car139", 
				"car140", 
				"car141", 
				"car142", 
				"car143", 
				"car144", 
				"car145", 
				"car146", 
				"car147", 
				"car148", 
				"car149", 
				"car150", 
				"car151", 
				"car152", 
				"car153", 
				"car154", 
				"car155", 
				"car156", 
				"car157", 
				"car158", 
				"car159", 
				"car160", 
				"car161", 
				"car162", 
				"car163", 
				"car164", 
				"car165", 
				"car166", 
				"car167", 
				"car168", 
				"car169", 
				"car170", 
				"car171", 
				"car172", 
				"car173", 
				"car174", 
				"car175", 
				"car176", 
				"car177", 
				"car178", 
				"car179", 
				"car180", 
				"car181", 
				"car182", 
				"car183", 
				"car184", 
				"car185", 
				"car186", 
				"car187", 
				"car188", 
				"car189", 
				"car190", 
				"car191", 
				"car192", 
				"car193", 
				"car194", 
				"car195", 
				"car196", 
				"car197", 
				"car198", 
				"car199", 
				"car200", 
				"car201", 
				"car202", 
				"car203", 
				"car204", 
				"car205", 
				"car206", 
				"car207", 
				"car208", 
				"car209", 
				"car210", 
				"car211", 
				"car212", 
				"car213", 
				"car214", 
				"car215", 
				"car216", 
				"car217", 
				"car218", 
				"car219", 
				"car220", 
				"car221", 
				"car222", 
				"car223", 
				"car224", 
				"car225", 
				"car226", 
				"car227", 
				"car228", 
				"car229", 
				"car230", 
				"car231", 
				"car232", 
				"car233", 
				"car234", 
				"car235", 
				"car236", 
				"car237", 
				"car238", 
				"car239", 
				"car240", 
				"car241", 
				"car242", 
				"car243", 
				"car244", 
				"car245", 
				"car246", 
				"car247", 
				"car248", 
				"car249", 
				"car250", 
				"car251", 
				"car252", 
				"car253", 
				"car254", 
				"car255", 
				"car256", 
				"car257", 
				"car258", 
				"car259", 
				"car260", 
				"car261", 
				"car262", 
				"car263", 
				"car264", 
				"car265", 
				"car266", 
				"car267", 
				"car268", 
				"car269", 
				"car270", 
				"car271", 
				"car272", 
				"car273", 
				"car274", 
				"car275", 
				"car276", 
				"car277", 
				"car278", 
				"car279", 
				"car280", 
				"car281", 
				"car282", 
				"car283", 
				"car284", 
				"car285", 
				"car286", 
				"car287", 
				"car288", 
				"car289", 
				"car290", 
				"car291", 
				"car292", 
				"car293", 
				"car294", 
				"car295", 
				"car296", 
				"car297", 
				"car298", 
				"car299", 
				"car300", 
				"car301", 
				"car302", 
				"car303", 
				"car304", 
				"car305", 
				"car306", 
				"car307", 
				"car308", 
				"car309", 
				"car310", 
				"car311", 
				"car312", 
				"car313", 
				"car314", 
				"car315", 
				"car316", 
				"car317", 
				"car318", 
				"car319", 
				"car320", 
				"car321", 
				"car322", 
				"car323", 
				"car324", 
				"car325", 
				"car326", 
				"car327", 
				"car328", 
				"car329", 
				"car330", 
				"car331", 
				"car332", 
				"car333", 
				"car334", 
				"car335", 
				"car336", 
				"car337", 
				"car338", 
				"car339", 
				"car340", 
				"car341", 
				"car342", 
				"car343", 
				"car344", 
				"car345", 
				"car346", 
				"car347", 
				"car348", 
				"car349", 
				"car350", 
				"car351", 
				"car352", 
				"car353", 
				"car354", 
				"car355", 
				"car356", 
				"car357", 
				"car358", 
				"car359", 
				"car360", 
				"car361", 
				"car362", 
				"car363", 
				"car364", 
				"car365", 
				"car366", 
				"car367", 
				"car368", 
				"car369", 
				"car370", 
				"car371", 
				"car372", 
				"car373", 
				"car374", 
				"car375", 
				"car376", 
				"car377", 
				"car378", 
				"car379", 
				"car380", 
				"car381", 
				"car382", 
				"car383", 
				"car384", 
				"car385", 
				"car386", 
				"car387", 
				"car388", 
				"car389", 
				"car390", 
				"car391", 
				"car392", 
				"car393", 
				"car394", 
				"car395", 
				"car396", 
				"car397", 
				"car398", 
				"car399", 
				"car400", 
				"car401", 
				"car402", 
				"car403", 
				"car404", 
				"car405", 
				"car406", 
				"car407", 
				"car408", 
				"car409", 
				"car410", 
				"car411", 
				"car412", 
				"car413", 
				"car414", 
				"car415", 
				"car416", 
				"car417", 
				"car418", 
				"car419", 
				"car420", 
				"car421", 
				"car422", 
				"car423", 
				"car424", 
				"car425", 
				"car426", 
				"car427", 
				"car428", 
				"car429", 
				"car430", 
				"car431", 
				"car432", 
				"car433", 
				"car434", 
				"car435", 
				"car436", 
				"car437", 
				"car438", 
				"car439", 
				"car440", 
				"car441", 
				"car442", 
				"car443", 
				"car444", 
				"car445", 
				"car446", 
				"car447", 
				"car448", 
				"car449", 
				"car450", 
				"car451", 
				"car452", 
				"car453", 
				"car454", 
				"car455", 
				"car456", 
				"car457", 
				"car458", 
				"car459", 
				"car460", 
				"car461", 
				"car462", 
				"car463", 
				"car464", 
				"car465", 
				"car466", 
				"car467", 
				"car468", 
				"car469", 
				"car470", 
				"car471", 
				"car472", 
				"car473", 
				"car474", 
				"car475", 
				"car476", 
				"car477", 
				"car478", 
				"car479", 
				"car480", 
				"car481", 
				"car482", 
				"car483", 
				"car484", 
				"car485", 
				"car486", 
				"car487", 
				"car488", 
				"car489", 
				"car490", 
				"car491", 
				"car492", 
				"car493", 
				"car494", 
				"car495", 
				"car496", 
				"car497", 
				"car498", 
				"car499", 
				"car500", 
				"car501", 
				"car502", 
				"car503", 
				"car504", 
				"car505", 
				"car506", 
				"car507", 
				"car508", 
				"car509", 
				"car510", 
				"car511", 
				"car512", 
				"car513", 
				"car514", 
				"car515", 
				"car516", 
				"car517", 
				"car518", 
				"car519", 
				"car520", 
				"car521", 
				"car522", 
				"car523", 
				"car524", 
				"car525", 
				"car526", 
				"car527", 
				"car528", 
				"car529", 
				"car530", 
				"car531", 
				"car532", 
				"car533", 
				"car534", 
				"car535", 
				"car536", 
				"car537", 
				"car538", 
				"car539", 
				"car540", 
				"car541", 
				"car542", 
				"car543", 
				"car544", 
				"car545", 
				"car546", 
				"car547", 
				"car548", 
				"car549", 
				"car550", 
				"car551", 
				"car552", 
				"car553", 
				"car554", 
				"car555", 
				"car556", 
				"car557", 
				"car558", 
				"car559", 
				"car560", 
				"car561", 
				"car562", 
				"car563", 
				"car564", 
				"car565", 
				"car566", 
				"car567", 
				"car568", 
				"car569", 
				"car570", 
				"car571", 
				"car572", 
				"car573", 
				"car574", 
				"car575", 
				"car576", 
				"car577", 
				"car578", 
				"car579", 
				"car580", 
				"car581", 
				"car582", 
				"car583", 
				"car584", 
				"car585", 
				"car586", 
				"car587", 
				"car588", 
				"car589", 
				"car590", 
				"car591", 
				"car592", 
				"car593", 
				"car594", 
				"car595", 
				"car596", 
				"car597", 
				"car598", 
				"car599", 
				"car600", 
				"car601", 
				"car602", 
				"car603", 
				"car604", 
				"car605", 
				"car606", 
				"car607", 
				"car608", 
				"car609", 
				"car610", 
				"car611", 
				"car612", 
				"car613", 
				"car614", 
				"car615", 
				"car616", 
				"car617", 
				"car618", 
				"car619", 
				"car620", 
				"car621", 
				"car622", 
				"car623", 
				"car624", 
				"car625", 
				"car626", 
				"car627", 
				"car628", 
				"car629", 
				"car630", 
				"car631", 
				"car632", 
				"car633", 
				"car634", 
				"car635", 
				"car636", 
				"car637", 
				"car638", 
				"car639", 
				"car640", 
				"car641", 
				"car642", 
				"car643", 
				"car644", 
				"car645", 
				"car646", 
				"car647", 
				"car648", 
				"car649", 
				"car650", 
				"car651", 
				"car652", 
				"car653", 
				"car654", 
				"car655", 
				"car656", 
				"car657", 
				"car658", 
				"car659", 
				"car660", 
				"car661", 
				"car662", 
				"car663", 
				"car664", 
				"car665", 
				"car666", 
				"car667", 
				"car668", 
				"car669", 
				"car670", 
				"car671", 
				"car672", 
				"car673", 
				"car674", 
				"car675", 
				"car676", 
				"car677", 
				"car678", 
				"car679", 
				"car680", 
				"car681", 
				"car682", 
				"car683", 
				"car684", 
				"car685", 
				"car686", 
				"car687", 
				"car688", 
				"car689", 
				"car690", 
				"car691", 
				"car692", 
				"car693", 
				"car694", 
				"car695", 
				"car696", 
				"car697", 
				"car698", 
				"car699", 
				"car700", 
				"car701", 
				"car702", 
				"car703", 
				"car704", 
				"car705", 
				"car706", 
				"car707", 
				"car708", 
				"car709", 
				"car710", 
				"car711", 
				"car712", 
				"car713", 
				"car714", 
				"car715", 
				"car716", 
				"car717", 
				"car718", 
				"car719", 
				"car720", 
				"car721", 
				"car722", 
				"car723", 
				"car724", 
				"car725", 
				"car726", 
				"car727", 
				"car728", 
				"car729", 
				"car730", 
				"car731", 
				"car732", 
				"car733", 
				"car734", 
				"car735", 
				"car736", 
				"car737", 
				"car738", 
				"car739", 
				"car740", 
				"car741", 
				"car742", 
				"car743", 
				"car744", 
				"car745", 
				"car746", 
				"car747", 
				"car748", 
				"car749", 
				"car750", 
				"car751", 
				"car752", 
				"car753", 
				"car754", 
				"car755", 
				"car756", 
				"car757", 
				"car758", 
				"car759", 
				"car760", 
				"car761", 
				"car762", 
				"car763", 
				"car764", 
				"car765", 
				"car766", 
				"car767", 
				"car768", 
				"car769", 
				"car770", 
				"car771", 
				"car772", 
				"car773", 
				"car774", 
				"car775", 
				"car776", 
				"car777", 
				"car778", 
				"car779", 
				"car780", 
				"car781", 
				"car782", 
				"car783", 
				"car784", 
				"car785", 
				"car786", 
				"car787", 
				"car788", 
				"car789", 
				"car790", 
				"car791", 
				"car792", 
				"car793", 
				"car794", 
				"car795", 
				"car796", 
				"car797", 
				"car798", 
				"car799", 
				"car800", 
				"car801", 
				"car802", 
				"car803", 
				"car804", 
				"car805", 
				"car806", 
				"car807", 
				"car808", 
				"car809", 
				"car810", 
				"car811", 
				"car812", 
				"car813", 
				"car814", 
				"car815", 
				"car816", 
				"car817", 
				"car818", 
				"car819", 
				"car820", 
				"car821", 
				"car822", 
				"car823", 
				"car824", 
				"car825", 
				"car826", 
				"car827", 
				"car828", 
				"car829", 
				"car830", 
				"car831", 
				"car832", 
				"car833", 
				"car834", 
				"car835", 
				"car836", 
				"car837", 
				"car838", 
				"car839", 
				"car840", 
				"car841", 
				"car842", 
				"car843", 
				"car844", 
				"car845", 
				"car846", 
				"car847", 
				"car848", 
				"car849", 
				"car850", 
				"car851", 
				"car852", 
				"car853", 
				"car854", 
				"car855", 
				"car856", 
				"car857", 
				"car858", 
				"car859", 
				"car860", 
				"car861", 
				"car862", 
				"car863", 
				"car864", 
				"car865", 
				"car866", 
				"car867", 
				"car868", 
				"car869", 
				"car870", 
				"car871", 
				"car872", 
				"car873", 
				"car874", 
				"car875", 
				"car876", 
				"car877", 
				"car878", 
				"car879", 
				"car880", 
				"car881", 
				"car882", 
				"car883", 
				"car884", 
				"car885", 
				"car886", 
				"car887", 
				"car888", 
				"car889", 
				"car890", 
				"car891", 
				"car892", 
				"car893", 
				"car894", 
				"car895", 
				"car896", 
				"car897", 
				"car898", 
				"car899", 
				"car900", 
				"car901", 
				"car902", 
				"car903", 
				"car904", 
				"car905", 
				"car906", 
				"car907", 
				"car908", 
				"car909", 
				"car910", 
				"car911", 
				"car912", 
				"car913", 
				"car914", 
				"car915", 
				"car916", 
				"car917", 
				"car918", 
				"car919", 
				"car920", 
				"car921", 
				"car922", 
				"car923", 
				"car924", 
				"car925", 
				"car926", 
				"car927", 
				"car928", 
				"car929", 
				"car930", 
				"car931", 
				"car932", 
				"car933", 
				"car934", 
				"car935", 
				"car936", 
				"car937", 
				"car938", 
				"car939", 
				"car940", 
				"car941", 
				"car942", 
				"car943", 
				"car944", 
				"car945", 
				"car946", 
				"car947", 
				"car948", 
				"car949", 
				"car950", 
				"car951", 
				"car952", 
				"car953", 
				"car954", 
				"car955", 
				"car956", 
				"car957", 
				"car958", 
				"car959", 
				"car960", 
				"car961", 
				"car962", 
				"car963", 
				"car964", 
				"car965", 
				"car966", 
				"car967", 
				"car968", 
				"car969", 
				"car970", 
				"car971", 
				"car972", 
				"car973", 
				"car974", 
				"car975", 
				"car976", 
				"car977", 
				"car978", 
		};
		final ContO aconto[] = new ContO[as.length];
		loadbase(aconto, medium, trackers, xtgraphics, as);
		final ContO aconto1[] = new ContO[10000];
		final Madness amadness[] = new Madness[51];
		int l = 0;
		do {
			amadness[l] = new Madness(medium, record, xtgraphics, l);
			u[l] = new Control(medium);
		} while (++l < xtgraphics.nplayers);
		l = 0;
		float f = 35F;
		int i1 = 80;
		l = readcookie("unlocked");
		if (l >= 1 && l <= xtgraphics.nTracks) {
			final int l2 = readcookie("userstage");
			xtgraphics.unlocked = l;
			if (xtgraphics.unlocked != xtgraphics.nTracks)
				checkpoints.stage = xtgraphics.unlocked;
			else if (l2 >= 1 && l2 <= xtgraphics.nTracks)
				checkpoints.stage = l2;
			else
				checkpoints.stage = (int) (Math.random() * xtgraphics.nTracks) + 1;
			xtgraphics.opselect = 0;
		}
		l = readcookie("usercar");
		if (l >= 0 && l <= xtgraphics.nCars - 1)
			xtgraphics.sc[0] = l;
		l = readcookie("gameprfact");
		if (l != -1) {
			f = readcookie("gameprfact");
			i1 = 1;
		}
		boolean flag = false;
		xtgraphics.stoploading();
		System.gc();
		final Date date = new Date();
		long l3 = date.getTime();
		float f1 = 30F;
		boolean flag1 = false;
		int j1 = 0;
		int k1 = 0;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		boolean flag2 = false;
		exwist = false;

		final int TICKS_PER_SECOND = 60;
		final int SKIP_TICKS = 1000 / TICKS_PER_SECOND;
		final int MAX_FRAMESKIP = 5;

		double next_game_tick = System.currentTimeMillis();
		int loops;

		while (true) {
			loops = 0;
			while (System.currentTimeMillis() > next_game_tick && loops < MAX_FRAMESKIP) {
				Date date1 = new Date();
				final long l4 = date1.getTime();
				if (xtgraphics.fase == 111) {
					if (mouses == 1)
						i2 = 800;
					if (i2 < 800) {
						xtgraphics.clicknow();
						i2++;
					} else {
						i2 = 0;
						xtgraphics.fase = 9;
						mouses = 0;
						lostfcs = false;
					}
				}
				if (xtgraphics.fase == 9)
					if (i2 < xtgraphics.nplayers) {
						xtgraphics.rad(i2, 1);
						catchlink(0);
						if (mouses == 2)
							mouses = 0;
						if (mouses == 1)
							mouses = 2;
						i2++;
					} else {
						i2 = 0;
						xtgraphics.fase = 10;
						mouses = 0;
						u[0].falseo();
					}
				if (xtgraphics.fase == -9)
					if (i2 < 2) {
						rd.setColor(new Color(0, 0, 0));
						rd.fillRect(0, 0, 670, 400);
						i2++;
					} else {
						xtgraphics.inishcarselect();
						i2 = 0;
						xtgraphics.fase = 7;
						mouses = 0;
					}
				if (xtgraphics.fase == 8) {
					xtgraphics.credits(u[0]);
					if (xtgraphics.flipo == 102)
						rd.drawImage(xtgraphics.credsnap(offImage), 0, 0, null);
					xtgraphics.ctachm(xm, ym, mouses, u[0]);
					if (xtgraphics.flipo <= 100)
						catchlink(0);
					if (mouses == 2)
						mouses = 0;
					if (mouses == 1)
						mouses = 2;
				}
				if (xtgraphics.fase == 10) {
					xtgraphics.maini(u[0]);
					xtgraphics.ctachm(xm, ym, mouses, u[0]);
					if (mouses == 2)
						mouses = 0;
					if (mouses == 1)
						mouses = 2;
				}
				if (xtgraphics.fase == 11) {
					xtgraphics.inst(u[0]);
					xtgraphics.ctachm(xm, ym, mouses, u[0]);
					if (mouses == 2)
						mouses = 0;
					if (mouses == 1)
						mouses = 2;
				}
				if (xtgraphics.fase == -5) {
					xtgraphics.finish(checkpoints, aconto, u[0]);
					if (flag) {
						if (checkpoints.stage == xtgraphics.unlocked && xtgraphics.winner
								&& xtgraphics.unlocked != xtgraphics.nTracks)
							savecookie("unlocked", "" + (xtgraphics.unlocked + 1));
						savecookie("gameprfact", "" + (int) f);
						savecookie("usercar", "" + xtgraphics.sc[0]);
						flag = true;
					}
					xtgraphics.ctachm(xm, ym, mouses, u[0]);
					if (checkpoints.stage == xtgraphics.nTracks && xtgraphics.winner)
						catchlink(1);
					if (mouses == 2)
						mouses = 0;
					if (mouses == 1)
						mouses = 2;
				}
				if (xtgraphics.fase == 7) {
					xtgraphics.carselect(u[0], aconto, amadness[0]);
					xtgraphics.ctachm(xm, ym, mouses, u[0]);
					if (mouses == 2)
						mouses = 0;
					if (mouses == 1)
						mouses = 2;
				}
				if (xtgraphics.fase == 6) {
					xtgraphics.musicomp(checkpoints.stage, u[0]);
					xtgraphics.ctachm(xm, ym, mouses, u[0]);
					if (mouses == 2)
						mouses = 0;
					if (mouses == 1)
						mouses = 2;
				}
				if (xtgraphics.fase == 5) {
					xtgraphics.loadmusic(checkpoints.stage, i1);
					if (!flag) {
						savecookie("usercar", "" + xtgraphics.sc[0]);
						flag = true;
					}
					savecookie("userstage", "" + checkpoints.stage);
				}
				if (xtgraphics.fase == 4) {
					xtgraphics.cantgo(u[0]);
					xtgraphics.ctachm(xm, ym, mouses, u[0]);
					if (mouses == 2)
						mouses = 0;
					if (mouses == 1)
						mouses = 2;
				}
				if (xtgraphics.fase == 3) {
					xtgraphics.loadingfailed(checkpoints.stage, u[0]);
					xtgraphics.ctachm(xm, ym, mouses, u[0]);
					if (mouses == 2)
						mouses = 0;
					if (mouses == 1)
						mouses = 2;
				}
				if (xtgraphics.fase == 2) {
					xtgraphics.loadingstage(checkpoints.stage);
					loadstage(aconto1, aconto, medium, trackers, checkpoints, xtgraphics, amadness, record);
					u[0].falseo();
				}
				if (xtgraphics.fase == 1) {
					xtgraphics.trackbg(false);
					rd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
					medium.aroundtrack(checkpoints);
					int i3 = 0;
					final int ai[] = new int[10000];
					for (int k5 = 51; k5 < notb; k5++)
						if (aconto1[k5].dist != 0) {
							ai[i3] = k5;
							i3++;
						} else
							aconto1[k5].d(rd);

					final int ai5[] = new int[i3];
					for (int j7 = 0; j7 < i3; j7++)
						ai5[j7] = 0;

					for (int k7 = 0; k7 < i3; k7++)
						for (int i11 = k7 + 1; i11 < i3; i11++)
							if (aconto1[ai[k7]].dist != aconto1[ai[i11]].dist) {
								if (aconto1[ai[k7]].dist < aconto1[ai[i11]].dist)
									ai5[k7]++;
								else
									ai5[i11]++;
							} else if (i11 > k7)
								ai5[k7]++;
							else
								ai5[i11]++;

					for (int l7 = 0; l7 < i3; l7++)
						for (int j11 = 0; j11 < i3; j11++)
							if (ai5[j11] == l7)
								aconto1[ai[j11]].d(rd);

					xtgraphics.ctachm(xm, ym, mouses, u[0]);
					if (mouses == 2)
						mouses = 0;
					if (mouses == 1)
						mouses = 2;
					rd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
					xtgraphics.stageselect(checkpoints, u[0]);
				}
				if (xtgraphics.fase == 176) {
					medium.d(rd);
					int j3 = 0;
					final int ai1[] = new int[10000];
					for (int i6 = 0; i6 < nob; i6++)
						// if(aconto1[i6] != null)
						if (aconto1[i6].dist != 0) {
							ai1[j3] = i6;
							j3++;
						} else
							aconto1[i6].d(rd);

					final int ai6[] = new int[j3];
					for (int i8 = 0; i8 < j3; i8++)
						ai6[i8] = 0;

					for (int j8 = 0; j8 < j3; j8++)
						for (int k11 = j8 + 1; k11 < j3; k11++)
							if (aconto1[ai1[j8]].dist != aconto1[ai1[k11]].dist) {
								if (aconto1[ai1[j8]].dist < aconto1[ai1[k11]].dist)
									ai6[j8]++;
								else
									ai6[k11]++;
							} else if (k11 > j8)
								ai6[j8]++;
							else
								ai6[k11]++;

					for (int k8 = 0; k8 < j3; k8++)
						for (int l11 = 0; l11 < j3; l11++)
							if (ai6[l11] == k8)
								aconto1[ai1[l11]].d(rd);

					medium.follow(aconto1[0], 0, 0);
					xtgraphics.hipnoload(checkpoints.stage, false);
					if (i1 != 0)
						i1--;
					else {
						u[0].enter = false;
						u[0].handb = false;
						if (xtgraphics.loadedt[checkpoints.stage - 1])
							if (xtgraphics.isMidi[checkpoints.stage - 1])
								xtgraphics.mtracks[checkpoints.stage - 1].play();
							else
								xtgraphics.stracks[checkpoints.stage - 1].play();
						setCursor(new Cursor(0));
						xtgraphics.fase = 6;
					}
				}
				if (xtgraphics.fase == 0) {
					int k3 = 0;
					do
						if (amadness[k3].newcar) {
							final int j5 = aconto1[k3].xz;
							final int j6 = aconto1[k3].xy;
							final int l8 = aconto1[k3].zy;
							aconto1[k3] = new ContO(aconto[amadness[k3].cn], aconto1[k3].x, aconto1[k3].y,
									aconto1[k3].z, 0);
							aconto1[k3].xz = j5;
							aconto1[k3].xy = j6;
							aconto1[k3].zy = l8;
							amadness[k3].newcar = false;
						}
					while (++k3 < xtgraphics.nplayers);
					medium.d(rd);
					k3 = 0;
					final int ai2[] = new int[10000];
					for (int k6 = 0; k6 < nob; k6++)
						if (aconto1[k6] != null)
							if (aconto1[k6].dist != 0) {
								ai2[k3] = k6;
								k3++;
							} else
								aconto1[k6].d(rd);

					final int ai7[] = new int[k3];
					final int ai10[] = new int[k3];
					for (int i12 = 0; i12 < k3; i12++)
						ai7[i12] = 0;

					for (int j12 = 0; j12 < k3; j12++) {
						for (int i14 = j12 + 1; i14 < k3; i14++)
							if (aconto1[ai2[j12]].dist != aconto1[ai2[i14]].dist) {
								if (aconto1[ai2[j12]].dist < aconto1[ai2[i14]].dist)
									ai7[j12]++;
								else
									ai7[i14]++;
							} else if (i14 > j12)
								ai7[j12]++;
							else
								ai7[i14]++;

						ai10[ai7[j12]] = j12;
					}

					for (int k12 = 0; k12 < k3; k12++)
						aconto1[ai2[ai10[k12]]].d(rd);

					if (xtgraphics.starcnt == 0) {
						int l12 = 0;
						do {
							int j14 = 0;
							do
								if (j14 != l12)
									amadness[l12].colide(aconto1[l12], amadness[j14], aconto1[j14]);
							while (++j14 < xtgraphics.nplayers);
						} while (++l12 < xtgraphics.nplayers);
						l12 = 0;
						do
							amadness[l12].drive(u[l12], aconto1[l12], trackers, checkpoints);
						while (++l12 < xtgraphics.nplayers);
						l12 = 0;
						do
							record.rec(aconto1[l12], l12, amadness[l12].squash, amadness[l12].lastcolido,
									amadness[l12].cntdest);
						while (++l12 < xtgraphics.nplayers);
						checkpoints.checkstat(amadness, aconto1, record, xtgraphics.nplayers, xtgraphics.ncops);
						l12 = 1;
						do
							u[l12].preform(amadness[l12], aconto1[l12], checkpoints, trackers, xtgraphics.nplayers);
						while (++l12 < xtgraphics.nplayers);
					} else {
						if (xtgraphics.starcnt == 130) {
							medium.adv = 1900;
							medium.zy = 40;
							medium.vxz = 70;
							rd.setColor(new Color(255, 255, 255));
							rd.fillRect(0, 0, 670, 400);
						}
						if (xtgraphics.starcnt != 0)
							xtgraphics.starcnt--;
					}
					if (xtgraphics.starcnt < 38) {
						if (view == 0) {
							medium.follow(aconto1[0], amadness[0].cxz, u[0].lookback);
							xtgraphics.stat(amadness, checkpoints, u[0], aconto1, true);
							xtgraphics.stat2(amadness, checkpoints, u[0], true);
						}
						if (view == 1) {
							medium.around(aconto1[0], false);
							xtgraphics.stat(amadness, checkpoints, u[0], aconto1, false);
							xtgraphics.stat2(amadness, checkpoints, u[0], false);
						}
						if (view == 2) {
							medium.watch(aconto1[0], amadness[0].mxz);
							xtgraphics.stat(amadness, checkpoints, u[0], aconto1, false);
							xtgraphics.stat2(amadness, checkpoints, u[0], false);
						}
						if (view == 3) {
							medium.eagle(aconto1[0], aconto1[0].xz);
							xtgraphics.stat(amadness, checkpoints, u[0], aconto1, false);
							xtgraphics.stat2(amadness, checkpoints, u[0], false);
						}
						if (view == 4) {
							medium.gopro(amadness[0], aconto1[0], aconto1[0].xz);
							xtgraphics.stat(amadness, checkpoints, u[0], aconto1, false);
							xtgraphics.stat2(amadness, checkpoints, u[0], false);
						}
						if (mouses == 1) {
							u[0].enter = true;
							mouses = 0;
						}
						if (xtgraphics.starcnt == 36) {
							repaint();
							xtgraphics.blendude(offImage);
						}
					} else {
						medium.around(aconto1[3], true);
						if (u[0].enter || u[0].handb) {
							xtgraphics.starcnt = 38;
							u[0].enter = false;
							u[0].handb = false;
						}
						if (xtgraphics.starcnt == 38) {
							mouses = 0;
							medium.vert = false;
							medium.adv = 900;
							medium.vxz = 180;
							checkpoints.checkstat(amadness, aconto1, record, xtgraphics.nplayers, xtgraphics.ncops);
							medium.follow(aconto1[0], amadness[0].cxz, 0);
							xtgraphics.stat(amadness, checkpoints, u[0], aconto1, true);
							xtgraphics.stat2(amadness, checkpoints, u[0], true);
							rd.setColor(new Color(255, 255, 255));
							rd.fillRect(0, 0, 670, 400);
						}
					}
				}
				if (xtgraphics.fase == -1) {
					if (k1 == 0) {
						int i4 = 0;
						do {
							record.ocar[i4] = new ContO(aconto1[i4], 0, 0, 0, 0);
							aconto1[i4] = new ContO(record.car[0][i4], 0, 0, 0, 0);
						} while (++i4 < xtgraphics.nplayers);
					}
					medium.d(rd);
					int j4 = 0;
					final int ai3[] = new int[100];
					for (int l6 = 0; l6 < nob; l6++)
						if (aconto1[l6].dist != 0) {
							ai3[j4] = l6;
							j4++;
						} else
							aconto1[l6].d(rd);

					final int ai8[] = new int[j4];
					for (int i9 = 0; i9 < j4; i9++)
						ai8[i9] = 0;

					for (int j9 = 0; j9 < j4; j9++)
						for (int i13 = j9 + 1; i13 < j4; i13++)
							if (aconto1[ai3[j9]].dist != aconto1[ai3[i13]].dist) {
								if (aconto1[ai3[j9]].dist < aconto1[ai3[i13]].dist)
									ai8[j9]++;
								else
									ai8[i13]++;
							} else if (i13 > j9)
								ai8[j9]++;
							else
								ai8[i13]++;

					for (int k9 = 0; k9 < j4; k9++)
						for (int j13 = 0; j13 < j4; j13++)
							if (ai8[j13] == k9)
								aconto1[ai3[j13]].d(rd);

					if (u[0].enter || u[0].handb || mouses == 1) {
						k1 = 299;
						u[0].enter = false;
						u[0].handb = false;
						mouses = 0;
					}
					int l9 = 0;
					do {
						if (record.fix[l9] == k1)
							if (aconto1[l9].dist == 0)
								aconto1[l9].fcnt = 8;
							else
								aconto1[l9].fix = true;
						if (aconto1[l9].fcnt == 7 || aconto1[l9].fcnt == 8) {
							aconto1[l9] = new ContO(aconto[amadness[l9].cn], 0, 0, 0, 0);
							record.cntdest[l9] = 0;
						}
						if (k1 == 299)
							aconto1[l9] = new ContO(record.ocar[l9], 0, 0, 0, 0);
						record.play(aconto1[l9], amadness[l9], l9, k1);
					} while (++l9 < xtgraphics.nplayers);
					if (++k1 == 300) {
						k1 = 0;
						xtgraphics.fase = -6;
					} else
						xtgraphics.replyn();
					medium.around(aconto1[0], false);
				}
				if (xtgraphics.fase == -2) {
					if (record.hcaught && record.wasted == 0 && record.whenwasted != 229 && checkpoints.stage <= 2
							&& xtgraphics.looped != 0)
						record.hcaught = false;
					if (record.hcaught) {
						if (medium.random() > 0.45000000000000001D)
							medium.vert = false;
						else
							medium.vert = true;
						medium.adv = (int) (900F * medium.random());
						medium.vxz = (int) (360F * medium.random());
						k1 = 0;
						xtgraphics.fase = -3;
						i2 = 0;
						j2 = 0;
					} else {
						k1 = -2;
						xtgraphics.fase = -4;
					}
				}
				if (xtgraphics.fase == -3) {
					if (k1 == 0) {
						if (record.wasted == 0) {
							if (record.whenwasted == 229) {
								k2 = 67;
								medium.vxz += 90;
							} else {
								k2 = (int) (medium.random() * 4F);
								if (k2 == 1 || k2 == 3)
									k2 = 69;
								if (k2 == 2 || k2 == 4)
									k2 = 30;
							}
						} else if (record.closefinish != 0 && j2 != 0)
							medium.vxz += 90;
						int k4 = 0;
						do
							aconto1[k4] = new ContO(record.starcar[k4], 0, 0, 0, 0);
						while (++k4 < xtgraphics.nplayers);
					}
					medium.d(rd);
					int i5 = 0;
					final int ai4[] = new int[100];
					for (int i7 = 0; i7 < nob; i7++)
						if (aconto1[i7].dist != 0) {
							ai4[i5] = i7;
							i5++;
						} else
							aconto1[i7].d(rd);

					final int ai9[] = new int[i5];
					for (int i10 = 0; i10 < i5; i10++)
						ai9[i10] = 0;

					for (int j10 = 0; j10 < i5; j10++)
						for (int k13 = j10 + 1; k13 < i5; k13++)
							if (aconto1[ai4[j10]].dist != aconto1[ai4[k13]].dist) {
								if (aconto1[ai4[j10]].dist < aconto1[ai4[k13]].dist)
									ai9[j10]++;
								else
									ai9[k13]++;
							} else if (k13 > j10)
								ai9[j10]++;
							else
								ai9[k13]++;

					for (int k10 = 0; k10 < i5; k10++)
						for (int l13 = 0; l13 < i5; l13++)
							if (ai9[l13] == k10)
								aconto1[ai4[l13]].d(rd);

					int l10 = 0;
					do {
						if (record.hfix[l10] == k1)
							if (aconto1[l10].dist == 0)
								aconto1[l10].fcnt = 8;
							else
								aconto1[l10].fix = true;
						if (aconto1[l10].fcnt == 7 || aconto1[l10].fcnt == 8) {
							aconto1[l10] = new ContO(aconto[amadness[l10].cn], 0, 0, 0, 0);
							record.cntdest[l10] = 0;
						}
						record.playh(aconto1[l10], amadness[l10], l10, k1);
					} while (++l10 < xtgraphics.nplayers);
					if (j2 == 2 && k1 == 299)
						u[0].enter = true;
					if (u[0].enter || u[0].handb) {
						xtgraphics.fase = -4;
						u[0].enter = false;
						u[0].handb = false;
						k1 = -7;
					} else {
						xtgraphics.levelhigh(record.wasted, record.whenwasted, record.closefinish, k1,
								checkpoints.stage);
						if (k1 == 0 || k1 == 1 || k1 == 2) {
							rd.setColor(new Color(0, 0, 0));
							rd.fillRect(0, 0, 670, 400);
						}
						if (record.wasted != 0) {
							if (record.closefinish == 0) {
								if (i2 == 9 || i2 == 11) {
									rd.setColor(new Color(255, 255, 255));
									rd.fillRect(0, 0, 670, 400);
								}
								if (i2 == 0)
									medium.around(aconto1[0], false);
								if (i2 > 0 && i2 < 20)
									medium.transaround(aconto1[0], aconto1[record.wasted], i2);
								if (i2 == 20)
									medium.around(aconto1[record.wasted], false);
								if (k1 > record.whenwasted && i2 != 20)
									i2++;
								if ((i2 == 0 || i2 == 20) && ++k1 == 300) {
									k1 = 0;
									i2 = 0;
									j2++;
								}
							} else if (record.closefinish == 1) {
								if (i2 == 0)
									medium.around(aconto1[0], false);
								if (i2 > 0 && i2 < 20)
									medium.transaround(aconto1[0], aconto1[record.wasted], i2);
								if (i2 == 20)
									medium.around(aconto1[record.wasted], false);
								if (i2 > 20 && i2 < 40)
									medium.transaround(aconto1[record.wasted], aconto1[0], i2 - 20);
								if (i2 == 40)
									medium.around(aconto1[0], false);
								if (i2 > 40 && i2 < 60)
									medium.transaround(aconto1[0], aconto1[record.wasted], i2 - 40);
								if (i2 == 60)
									medium.around(aconto1[record.wasted], false);
								if (k1 > 160 && i2 < 20)
									i2++;
								if (k1 > 230 && i2 < 40)
									i2++;
								if (k1 > 280 && i2 < 60)
									i2++;
								if ((i2 == 0 || i2 == 20 || i2 == 40 || i2 == 60) && ++k1 == 300) {
									k1 = 0;
									i2 = 0;
									j2++;
								}
							} else {
								if (i2 == 0)
									medium.around(aconto1[0], false);
								if (i2 > 0 && i2 < 20)
									medium.transaround(aconto1[0], aconto1[record.wasted], i2);
								if (i2 == 20)
									medium.around(aconto1[record.wasted], false);
								if (i2 > 20 && i2 < 40)
									medium.transaround(aconto1[record.wasted], aconto1[0], i2 - 20);
								if (i2 == 40)
									medium.around(aconto1[0], false);
								if (i2 > 40 && i2 < 60)
									medium.transaround(aconto1[0], aconto1[record.wasted], i2 - 40);
								if (i2 == 60)
									medium.around(aconto1[record.wasted], false);
								if (i2 > 60 && i2 < 80)
									medium.transaround(aconto1[record.wasted], aconto1[0], i2 - 60);
								if (i2 == 80)
									medium.around(aconto1[0], false);
								if (k1 > 90 && i2 < 20)
									i2++;
								if (k1 > 160 && i2 < 40)
									i2++;
								if (k1 > 230 && i2 < 60)
									i2++;
								if (k1 > 280 && i2 < 80)
									i2++;
								if ((i2 == 0 || i2 == 20 || i2 == 40 || i2 == 60 || i2 == 80) && ++k1 == 300) {
									k1 = 0;
									i2 = 0;
									j2++;
								}
							}
						} else {
							if (k2 == 67 && (i2 == 3 || i2 == 31 || i2 == 66)) {
								rd.setColor(new Color(255, 255, 255));
								rd.fillRect(0, 0, 670, 400);
							}
							if (k2 == 69 && (i2 == 3 || i2 == 5 || i2 == 31 || i2 == 33 || i2 == 66 || i2 == 68)) {
								rd.setColor(new Color(255, 255, 255));
								rd.fillRect(0, 0, 670, 400);
							}
							if (k2 == 30 && i2 >= 1 && i2 < 30)
								if (i2 % (int) (2.0F + medium.random() * 3F) == 0 && !flag2) {
									rd.setColor(new Color(255, 255, 255));
									rd.fillRect(0, 0, 670, 400);
									flag2 = true;
								} else
									flag2 = false;
							if (k1 > record.whenwasted && i2 != k2)
								i2++;
							medium.around(aconto1[0], false);
							if ((i2 == 0 || i2 == k2) && ++k1 == 300) {
								k1 = 0;
								i2 = 0;
								j2++;
							}
						}
					}
				}
				if (xtgraphics.fase == -4) {
					if (k1 <= 0) {
						rd.drawImage(xtgraphics.mdness, 224, 30, null);
						rd.drawImage(xtgraphics.dude[0], 70, 10, null);
					}
					if (k1 >= 0)
						xtgraphics.fleximage(offImage, k1, checkpoints.stage);
					k1++;
					if (checkpoints.stage == xtgraphics.nTracks && k1 == 10)
						xtgraphics.fase = -5;
					if (k1 == 12)
						xtgraphics.fase = -5;
				}
				if (xtgraphics.fase == -6) {
					repaint();
					xtgraphics.pauseimage(offImage);
					xtgraphics.fase = -7;
					mouses = 0;
				}
				if (xtgraphics.fase == -7) {
					xtgraphics.pausedgame(checkpoints.stage, u[0], record);
					if (k1 != 0)
						k1 = 0;
					xtgraphics.ctachm(xm, ym, mouses, u[0]);
					if (mouses == 2)
						mouses = 0;
					if (mouses == 1)
						mouses = 2;
				}
				if (xtgraphics.fase == -8) {
					xtgraphics.cantreply();
					if (++k1 == 150 || u[0].enter || u[0].handb || mouses == 1) {
						xtgraphics.fase = -7;
						mouses = 0;
						u[0].enter = false;
						u[0].handb = false;
					}
				}
				if (lostfcs && xtgraphics.fase != 176 && xtgraphics.fase != 111) {
					if (xtgraphics.fase == 0)
						u[0].enter = true;
					else
						xtgraphics.nofocus();
					if (mouses == 1 || mouses == 2)
						lostfcs = false;
				}
				repaint();
				xtgraphics.playsounds(amadness[0], u[0], checkpoints.stage);
				date1 = new Date();
				final long l5 = date1.getTime();
				if (xtgraphics.fase == 0 || xtgraphics.fase == -1 || xtgraphics.fase == -3) {
					if (!flag1) {
						f1 = f;
						flag1 = true;
						j1 = 0;
					}
					if (j1 == 10) {
						if (l5 - l3 < j)
							f1 = (float) (f1 + 0.5D);
						else {
							f1 = (float) (f1 - 0.5D);
							if (f1 < 5F)
								f1 = 5F;
						}
						if (xtgraphics.starcnt == 0)
							medium.adjstfade(f1);
						l3 = l5;
						j1 = 0;
					} else
						j1++;
				} else {
					if (flag1) {
						f = f1;
						flag1 = false;
						j1 = 0;
					}
					if (i1 == 0 || xtgraphics.fase != 176) {
						if (j1 == 10) {
							if (l5 - l3 < 400L)
								f1 = (float) (f1 + 3.5D);
							else {
								f1 = (float) (f1 - 3.5D);
								if (f1 < 5F)
									f1 = 5F;
							}
							l3 = l5;
							j1 = 0;
						} else
							j1++;
					} else {
						if (i1 == 79) {
							f1 = f;
							l3 = l5;
							j1 = 0;
						}
						if (j1 == 10) {
							if (l5 - l3 < j)
								f1 += 5F;
							else {
								f1 -= 5F;
								if (f1 < 5F)
									f1 = 5F;
							}
							l3 = l5;
							j1 = 0;
						} else
							j1++;
						if (i1 == 1)
							f = f1;
					}
				}
				if (exwist) {
					rd.dispose();
					xtgraphics.stopallnow();
					System.gc();
					gamer.stop();
					gamer = null;
				}
				long l2 = Math.round(f1) - (l5 - l4);
				if (l2 < i)
					l2 = i;
				try {
					Thread.sleep(l2);
				} catch (final InterruptedException _ex) {
				}
				next_game_tick += SKIP_TICKS;
				loops++;
			}
			memory = (int) ((((float) Runtime.getRuntime().totalMemory() - (float) Runtime.getRuntime().freeMemory())
					/ Runtime.getRuntime().maxMemory()) * 100);
			if (memory != memprev)
				System.out
						.println("[MEMORY] " + memory + "% memory used of " + (int) (Runtime.getRuntime().totalMemory()
								/ 1048576.0F /* bytes to MB */) + "mb");
			memprev = memory;
		}

	}

	@Override
	public void init() {
		offImage = createImage(670, 400);
		if (offImage != null) {
			sg = offImage.getGraphics();
			rd = ((Graphics2D) sg);
			rd.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			rd.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			rd.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			rd.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
			rd.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION,
					RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
			rd.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			rd.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_DISABLE);
			rd.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
			rd.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
			rd.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
		}
	}

	/*
	 * public void savecookie(String string, String string_107_) { try {
	 * JSObject jsobject = JSObject.getWindow(this); jsobject.eval("SetCookie('"
	 * + string + "','" + string_107_ + "');"); } catch (Exception exception) {
	 * exception.printStackTrace(); } catch (NoClassDefFoundError
	 * localException) { System.out.println("Not running in web browser"); } }
	 */

	public void savecookie(final String filename, final String num) {
		try {
			final JSObject jsobject = JSObject.getWindow(this);
			jsobject.eval("SetCookie('" + filename + "','" + num + "');");
		} catch (netscape.javascript.JSException | NoClassDefFoundError localException) {
			System.out.println("Not running in web browser (" + filename + ")");
			try {
				final FileWriter saveFile = new FileWriter(filename + ".dat");

				// Write the data to the file.
				saveFile.write(num);
				saveFile.write("\n");

				// All done, close the FileWriter.
				saveFile.close();

				System.out.println("Successfully saved game (" + filename + ")");
			} catch (final IOException fileNoAccess) {
				System.out.println("Could not access file " + filename);
				fileNoAccess.printStackTrace();
			}
		}
	}

	public void catchlink(final int i) {
		if (!lostfcs) {
			if (i == 0)
				if (xm > 0 && xm < 670 && ym > 110 && ym < 169 || xm > 210 && xm < 460 && ym > 240 && ym < 259) {
					setCursor(new Cursor(12));
					if (mouses == 2)
						try {
							final URL url = new URL("javascript:radicalplay();");
							getAppletContext().showDocument(url);
						} catch (final Exception _ex) {
						}
				} else
					setCursor(new Cursor(0));
			if (i == 1)
				if (xm > 0 && xm < 670 && ym > 205 && ym < 267) {
					setCursor(new Cursor(12));
					if (mouses == 2)
						try {
							final URL url1 = new URL("javascript:radicalplay();");
							getAppletContext().showDocument(url1);
						} catch (final Exception _ex) {
						}
				} else
					setCursor(new Cursor(0));
		}
	}

	@Override
	public boolean mouseMove(final Event event, final int i, final int j) {
		if (!exwist && !lostfcs) {
			xm = i;
			ym = j;
		}
		return false;
	}

	CheckPoints cp;
	Graphics2D rd;
	Graphics sg;
	Settings settings = new Settings(this);
	Image offImage;
	Thread gamer;
	Control u[];
	int mouses;
	int xm;
	int ym;
	boolean lostfcs;
	boolean exwist;
	int nob;
	int notb;
	int view;
	int memory = 0;
	int memprev = 0;
}
// >>>>>>> nfm2desktop
