package tank;
import java.awt.*;

public abstract class GameObject {

    public GamePanel gp;
    public Image image;
    public int x;
    public int y;
    public int width;
    public int height;

    public abstract void paintSelf(Graphics g);
    public abstract Rectangle getRect();

}

