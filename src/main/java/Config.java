import java.io.File;

public class Config {
    private int width;
    private int height;

    private double totalLength;
    private double framesPerSecond;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getTotalLength() {
        return totalLength;
    }

    public double getFramesPerSecond() {
        return framesPerSecond;
    }

    public static Config parseFromFile(File configFile) {
        return new Config();
    }
}
