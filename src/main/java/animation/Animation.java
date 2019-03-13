package animation;

import drawable.DrawableObject;
import graphics.Canvas;


public class Animation {

    InterpolationFunction interpolationFunction;

    public Animation(InterpolationFunction interpolationFunction) {
        this.interpolationFunction = interpolationFunction;
    }

    public void draw(Canvas canvas, double time) {
        DrawableObject drawableObject = interpolationFunction.interpolate(time);
        drawableObject.draw(canvas);
    }
}
