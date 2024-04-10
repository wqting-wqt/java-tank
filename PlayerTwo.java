package tank;


import java.awt.*;

public class PlayerTwo extends Tank{

    public PlayerTwo(Image leftImg, Image rightImg, Image upImg, Image DownImg, int x, int y, int width, int height, GamePanel gp) {
        super(leftImg, rightImg, upImg, DownImg, x, y, width, height, gp);
    }

    //绘制
    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(image,x,y,width,height,null);
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(x,y,width,height);
    }
}