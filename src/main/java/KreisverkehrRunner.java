import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class KreisverkehrRunner {

    public void run(String configFilePath, String outputFilePath) {
        System.out.println("config: " + configFilePath);
        System.out.println("output: " + outputFilePath);

        Config config = Config.parseFromFilePath(configFilePath);

        // change to change the result
        Scene scene = new Scene(config);

        // get number of frames
        int numFrames = (int) Math.ceil(config.getFramesPerSecond() * config.getTotalLength());
        double frameTime = 1.0 / config.getFramesPerSecond();
        int numZeros = (int) (Math.log(numFrames + 1) / Math.log(10));

        File pngsDir = new File(".pngs");

        deleteDir(pngsDir);

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
            Runtime runtime = Runtime.getRuntime();
            String command = String.format("ffmpeg -framerate %f -pattern_type glob -i '/Users/laelcosta/Desktop/kreisverkehr/.pngs/*.png' -c:v ffv1 %s",
                    config.getFramesPerSecond(),
                    outputFilePath);
            System.out.println(command);
            Process ffmpegProcess = runtime.exec(command);
            ffmpegProcess.waitFor();
            deleteDir(pngsDir);
            File outputFile = new File(outputFilePath);
            Desktop.getDesktop().open(outputFile);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void deleteDir(File dir) {
        File[] files = dir.listFiles();
        if (dir.exists() && files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDir(file);
                } else {
                    file.delete();
                }
            }
            dir.delete();
        }
    }

    private static void deleteFile(File file) {
        if (!file.delete()) {
            System.err.println("Failed to delete file: " + file.getName());
        }
    }
}
