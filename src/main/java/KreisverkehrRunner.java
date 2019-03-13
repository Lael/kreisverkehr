import animation.Animation;
import animation.InterpolationFunction;
import drawable.Disk;
import drawable.Line;
import graphics.Color;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KreisverkehrRunner {

    public void run(String configFilePath, String outputFilePath) {
        System.out.println("config: " + configFilePath);
        System.out.println("output: " + outputFilePath);

        Config config = Config.parseFromFilePath(configFilePath);

        List<Animation> animations = new ArrayList<>();
        InterpolationFunction lineInterpolation = (time) -> new Line(0, 0, config.getWidth(), time * config.getHeight(), 10, new graphics.Color(1, 1, 1));
        animations.add(new Animation(lineInterpolation));
        InterpolationFunction diskInterpolation = (time) -> {
            double t = time / config.getTotalLength();
            return new Disk(config.getWidth() / 2.0, config.getHeight() * t, 100 * Math.sin(t * Math.PI), new Color(1, 1, 1));
        };
        animations.add(new Animation(diskInterpolation));
        Scene scene = new Scene(config.getWidth(), config.getHeight(), animations);

        // get number of frames
        int numFrames = (int) Math.ceil(config.getFramesPerSecond() * config.getTotalLength());
        double frameTime = 1.0 / config.getFramesPerSecond();
        int numZeros = (int) (Math.log(numFrames + 1) / Math.log(10)) + 1;

        File pngsDir = new File(".pngs");

        delete(pngsDir);

        if (!pngsDir.mkdirs()) {
            System.err.println("Failed to create .pngs/");
            System.exit(1);
        }

        for (int i = 0; i < numFrames; i++) {
            BufferedImage image = scene.getFrame(frameTime * i);
            String filename = String.format(".pngs/frame%0" + numZeros + "d.png", i + 1);
            File frameFile = new File(filename);
            try {
                ImageIO.write(image, "png", frameFile);
                System.out.println("writing " + filename);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            delete(new File(outputFilePath));
            String command = String.format("ffmpeg -framerate %f -i .pngs/frame%%0%dd.png -vcodec libx264 -crf 1  -pix_fmt yuv420p %s",
                    config.getFramesPerSecond(),
                    numZeros,
                    outputFilePath);
            executeCommand(command);
            delete(pngsDir);
            File outputFile = new File(outputFilePath);
            Desktop.getDesktop().open(outputFile);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void executeCommand(String command) throws IOException, InterruptedException {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(command);
        Process proc = runtime.exec(command);
        proc.waitFor();
    }

    private static void delete(File file) {
        File[] files = file.listFiles();
        if (file.exists() && files != null) {
            for (File f : files) {
                delete(f);
            }
        }
        file.delete();
    }
}
