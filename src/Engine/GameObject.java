package Engine;

/**
 * Abstract GameObject that exsists in scenes.
 */
public abstract class GameObject {
    float x;
    float y;
    float size;
    Scene scene;
    
    /**
     * Create new object.
     * @param x x-position
     * @param y y-postion
     * @param size size
     */
    GameObject(float x, float y, float size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public abstract void onLoad();

    public abstract void onDestroy();

    public abstract void update();
}

