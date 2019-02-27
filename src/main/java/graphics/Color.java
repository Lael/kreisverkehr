package graphics;

public class Color {
    public static int packd(double r, double g, double b, double a) {
        return packi(
                (int) Math.round(r * 255),
                (int) Math.round(g * 255),
                (int) Math.round(b * 255),
                (int) Math.round(a * 255));
    }

    public static int packd(double r, double g, double b) {
        return packd(r, g, b, 1);
    }

    public static int packi(int r, int g, int b, int a) {
        assert(r >= 0 && r <= 255);
        assert(g >= 0 && g <= 255);
        assert(b >= 0 && b <= 255);
        assert(a >= 0 && a <= 255);
        return a << 24 | r << 16 | g << 8 | b;
    }

    public static int packi(int r, int g, int b) {
        return packi(r, g, b, 255);
    }
}
