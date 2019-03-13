package animation;

import drawable.DrawableObject;

@FunctionalInterface
public interface InterpolationFunction {
    DrawableObject interpolate(double time);
}