
public class Trackers
{

    String sequ[] = {
        "Access Denied !", "This game will not run under this http:/ loaction:", "Please contact radicalplay.com for details."
    };
    int x[];
    int y[];
    int z[];
    int xy[];
    int zy[];
    int skd[];
    int dam[];
    boolean notwall[];
    boolean tracksReady;
    int c[][];
    int radx[];
    int radz[];
    int rady[];
    int nt;

    public Trackers()
    {
        x = new int[100000];
        y = new int[100000];
        z = new int[100000];
        xy = new int[100000];
        zy = new int[1000000];
        skd = new int[100000];
        dam = new int[100000];
        notwall = new boolean[100000];
        tracksReady = false;
        c = new int[100000][3];
        radx = new int[100000];
        radz = new int[100000];
        rady = new int[100000];
        nt = 0;
    }
}
