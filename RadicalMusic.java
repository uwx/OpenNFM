public interface RadicalMusic {
    public void load(int gain, int skiprate, int tempo, boolean sunny, boolean macn);
    public void play();
    public void resume();
    public void stop();
    public void unload();
    public void unloadAll();
}