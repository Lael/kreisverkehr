package graphics;

import java.awt.image.BufferedImage;

public class Canvas {
    private BufferedImage frame;
    private int width;
    private int height;

    public Canvas(BufferedImage frame) {
        this.frame = frame;
        this.width = frame.getWidth();
        this.height = frame.getHeight();
    }

    public void setPixel(int x, int y, Color color) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            Color oldColor = Color.unpack(frame.getRGB(x, y));
            frame.setRGB(x, y, oldColor.blend(color).pack());
        }
    }

    public void plotDisk(double x, double y, double r, Color color) {
        int lox = (int) Math.floor(x - r);
        int hix = (int) Math.ceil(x + r);
        int loy = (int) Math.floor(y - r);
        int hiy = (int) Math.ceil(y + r);

        for (int i = lox; i <= hix; i++) {
            if (i < 0 || i >= width) continue;
            for (int j = loy; j <= hiy; j++) {
                if (j < 0 || j >= height) continue;
                double dist = Math.pow(x - i, 2) + Math.pow(y - j, 2);
                double d = dist - r * r;
                if (d <= 1) setPixel(i, j, color);
                else if (d <= 0) setPixel(i, j, new Color(color).setA(d));
            }
        }
    }
}
