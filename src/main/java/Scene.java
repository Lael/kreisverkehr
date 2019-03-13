import animation.Animation;
import graphics.Canvas;
import graphics.Color;

import java.awt.image.BufferedImage;
import java.util.List;

import static java.awt.image.BufferedImage.TYPE_INT_ARGB;

public class Scene {
    private int width;
    private int height;
    private List<Animation> animations;

    public Scene(int width, int height, List<Animation> animations) {
        this.width = width;
        this.height = height;
        this.animations = animations;
    }

    private void fillFrame(BufferedImage frame, double time) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                frame.setRGB(x, y, Color.packd(0.2, 0.2, 0.2));
            }
        }
        Canvas canvas = new Canvas(frame);
        for (Animation animation : animations) animation.draw(canvas, time);
    }

    BufferedImage getFrame(double dt) {
        BufferedImage frame = new BufferedImage(width, height, TYPE_INT_ARGB);
        fillFrame(frame, dt);
        return frame;
    }
}
