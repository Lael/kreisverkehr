package graphics;

public class Color {
    private double r;
    private double g;
    private double b;
    private double a;

    public Color(double r, double g, double b, double a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    public Color(double r, double g, double b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 1;
    }

    public Color(Color other) {
        this.r = other.r;
        this.g = other.g;
        this.b = other.b;
        this.a = other.a;
    }

    public double getR() {
        return r;
    }

    public double getG() {
        return g;
    }

    public double getB() {
        return b;
    }

    public double getA() {
        return a;
    }

    public Color setR(double r) {
        this.r = r;
        return this;
    }

    public Color setG(double g) {
        this.g = g;
        return this;
    }

    public Color setB(double b) {
        this.b = b;
        return this;
    }

    public Color setA(double a) {
        this.a = a;
        return this;
    }

    public void scaleRGB(double s) {
        this.r *= s;
        this.g *= s;
        this.b *= s;
    }

    public Color blend(Color top) {
        double alpha = top.a;
        double oneMinusAlpha = 1 - top.a;
        return new Color(
                this.r * oneMinusAlpha + top.r * alpha,
                this.g * oneMinusAlpha + top.g * alpha,
                this.b * oneMinusAlpha + top.b * alpha,
                1);
    }

    public int pack() {
        return Color.packd(r, g, b, a);
    }

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

    public static Color unpack(int rgb) {
        int r = rgb & 0x00ff0000;
        int g = rgb & 0x0000ff00;
        int b = rgb & 0x000000ff;
        int a = rgb & 0xff000000;
        return new Color(r / 255.0, g / 255.0, b / 255.0, a / 255.0);
    }

}
