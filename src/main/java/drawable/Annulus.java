package drawable;

import graphics.Canvas;
import graphics.Color;

public class Annulus extends DrawableObject {
    private double x;
    private double y;
    private double innerRadius;
    private double outerRadius;
    private Color color;

    public Annulus(double x, double y, double innerRadius, double outerRadius, Color color) {
        this.x = x;
        this.y = y;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
        this.color = color;
    }


    @Override
    public void draw(Canvas canvas) {
        int lox = (int) Math.floor(x - outerRadius);
        int hix = (int) Math.ceil(x + outerRadius);
        int loy = (int) Math.floor(y - outerRadius);
        int hiy = (int) Math.ceil(y + outerRadius);

        double midRadius = (innerRadius + outerRadius) / 2;
        double halfThickness = outerRadius - midRadius;

        for (int i = lox; i <= hix; i++) {
            for (int j = loy; j <= hiy; j++) {
                double dist = Math.pow(x - i, 2) + Math.pow(y - j, 2);
                double d = Math.abs(dist - midRadius);
                if (d < halfThickness - 1) canvas.setPixel(i, j, color);
                else if (d < halfThickness) canvas.setPixel(i, j, new Color(color).setA(halfThickness - d));
            }
        }
    }
}
