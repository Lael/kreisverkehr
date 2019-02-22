import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Config {
    private int width;
    private int height;

    private double totalLength;
    private double framesPerSecond;

    private Config(int width, int height, double totalLength, double framesPerSecond) {
        this.width = width;
        this.height = height;
        this.totalLength = totalLength;
        this.framesPerSecond = framesPerSecond;
    }

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

    public static Config parseFromFilePath(String configFilePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(configFilePath));
            Map<String, String> values = new HashMap<>();
            for (String line : lines) {
                String[] parts = line.split(": ");
                if (parts.length != 2) {
                    System.err.println("Malformed config line: " + line);
                } else {
                    values.put(parts[0], parts[1]);
                }
            }
            return new Config(
                    Integer.parseInt(values.get("width")),
                    Integer.parseInt(values.get("height")),
                    Double.parseDouble(values.get("length")),
                    Double.parseDouble(values.get("fps")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Config(800, 600, 3.0, 60);
    }
}
