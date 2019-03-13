package drawable;

import graphics.Canvas;
import graphics.Color;

public class Disk extends DrawableObject {
    private double x;
    private double y;
    private double r;
    private Color color;

    public Disk(double x, double y, double r, Color color) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
    }

    public void draw(Canvas canvas) {
        canvas.plotDisk(x, y, r, color);
    }
}
