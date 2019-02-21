import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class KreisverkehrRunner {

    public void run(String configFilePath, String outputFilePath) {
        System.out.println("config: " + configFilePath);
        System.out.println("output: " + outputFilePath);

        File configFile = new File(configFilePath);
        // do stuff

        Config config = Config.parseFromFile(configFile);
        Scene scene = new Scene(config);

        // get number of frames
        int numFrames = (int) Math.ceil(config.getFramesPerSecond() * config.getTotalLength());
        double frameTime = 1.0 / config.getFramesPerSecond();
        int numZeros = (int) (Math.log(numFrames + 1) / Math.log(10)) - 1;

        File pngsDir = new File(".pngs");

        if (!pngsDir.mkdirs()) {
            System.err.println("Failed to create .pngs/");
            System.exit(1);
        }

        for (int i = 0; i < numFrames; i++) {
            BufferedImage image = scene.getFrame(frameTime * i);
            String filename = String.format(".pngs/frame%0" + numZeros + "d", i + 1);
            File frameFile = new File(filename);
            try {
                ImageIO.write(image, "png", frameFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Runtime runtime = Runtime.getRuntime();
            Process ffmpegProcess = runtime.exec(
                    String.format("ffmpeg -framerate %f -pattern_type glob -i '.pngs/*.png' -c:v ffv1 %s",
                            config.getFramesPerSecond(),
                            outputFilePath));
            ffmpegProcess.waitFor();
            File outputFile = new File(outputFilePath);
            Desktop.getDesktop().open(outputFile);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
