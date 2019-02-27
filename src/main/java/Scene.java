import animation.Animation;
import graphics.Color;

import java.awt.image.BufferedImage;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class Scene {
    private Config config;
    private List<Animation> animations;


    public Scene(Config config) {
        this.config = config;
    }

    private void fillFrame(BufferedImage frame) {
        for (int x = 0; x < config.getWidth(); x++) {
            for (int y = 0; y < config.getHeight(); y++) {
                frame.setRGB(x, y, Color.packd(0.5f, 1, 1));
            }
        }
    }

    BufferedImage getFrame(double dt) {
        BufferedImage frame = new BufferedImage(config.getWidth(), config.getHeight(), TYPE_INT_ARGB);
        fillFrame(frame);
        return frame;
    }
}
