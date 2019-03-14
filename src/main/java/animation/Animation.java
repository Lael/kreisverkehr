package animation;

import drawable.DrawableObject;
import graphics.Canvas;


public class Animation {

    InterpolationFunction interpolationFunction;
    double startTime;
    double duration;

    public Animation(InterpolationFunction interpolationFunction, double startTime, double duration) {
        this.interpolationFunction = interpolationFunction;
        this.startTime = startTime;
        this.duration = duration;
    }

    public void draw(Canvas canvas, double time) {
        if (time < startTime || time > startTime + duration) return;
        double t = (time - startTime) / duration;
        DrawableObject drawableObject = interpolationFunction.interpolate(t);
        drawableObject.draw(canvas);
    }
}
