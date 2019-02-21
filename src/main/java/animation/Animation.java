package animation;

import drawable.DrawableObject;

import java.util.List;

public abstract class Animation {
    public abstract List<DrawableObject> interpolateFrame(double time);
}
