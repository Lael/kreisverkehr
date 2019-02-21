import animation.Animation;

import java.awt.image.BufferedImage;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class Scene {
    private Config config;
    private List<Animation> animations;


    public Scene(Config config) {
        this.config = config;
    }

    BufferedImage getFrame(double dt) {
        return new BufferedImage(config.getWidth(), config.getWidth(), TYPE_INT_ARGB);
    }
}
