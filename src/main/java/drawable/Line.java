package drawable;

import graphics.Canvas;
import graphics.Color;

public class Line extends DrawableObject {

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double thickness;
    private Color color;

    public Line(double x1, double y1, double x2, double y2, double thickness, Color color) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.thickness = thickness;
        this.color = color;
    }

    @Override
    public void draw(Canvas canvas) {
        int dxi = Math.max(1, Math.abs((int) Math.round(x2 - x1)));
        int dyi = Math.max(1, Math.abs((int) Math.round(y2 - y1)));
        if (x1 == x2) {
            if (y1 == y2) {
                canvas.plotDisk(x1, y1, thickness, color);
            }
            for (int i = 0; i <= dyi; i++) {
                canvas.plotDisk(x1, y1 + i * (y2 - y1) / dyi, thickness, color);
            }
        }
        int di = Math.max(dxi, dyi);
        for (int i = 0; i < di; i++) {
            canvas.plotDisk(x1 + i * (x2 - x1) / di, y1 + i * (y2 - y1) / di, thickness, color);
        }
    }
}
