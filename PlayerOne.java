package tank;
import com.sun.javafx.scene.traversal.Direction;
import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerOne extends Tank{

    protected boolean isLeft,isRight,isUp,isDown;

    //有参构造器，当类被创建时调用
    public PlayerOne(Image leftImg, Image rightImg, Image upImg, Image DownImg, int x, int y, int width, int height, GamePanel gp) {
        super(leftImg, rightImg, upImg, DownImg, x, y, width, height, gp);
        //设置朝上图片显示
        image=upImg;
        //设置当前初始化方向
        direction= Direction.UP;
    }

    //绘制自身
    @Override
    public void paintSelf(Graphics g) {
        move();
        g.drawImage(image,x,y,width,height,null);
    }

    @Override
    public Rectangle getRect() {
        return new Rectangle(x,y,width,height);
    }



    //键盘点击事件
    public void keyPressed(KeyEvent e){
        //往上移动
        if(e.getKeyCode() == KeyEvent.VK_W){
            //调用父类写好的移动方法
            isUp=true;

        }else if (e.getKeyCode() == KeyEvent.VK_S){//往下移动
            isDown=true;
        }else if(e.getKeyCode() == KeyEvent.VK_A){//往左移动

            isLeft=true;

        }else if(e.getKeyCode() == KeyEvent.VK_D){//往右移动
            isRight=true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_J){//子弹发射
            /*创建子弹对象*/
            fire();
            Bullet bullet=new Bullet(
                    Toolkit.getDefaultToolkit().createImage("D:/develop/JetBrains/homework/src/images/playerbullet.png"),//子弹图片
                    direction,//朝向
                    x,y,//x,y 坐标
                    16,16,gp);
            gp.playerBulletList.add(bullet);
        }
    }
    //键盘松开事件
    public void keyReleased(KeyEvent e) {
        //往上移动
        if(e.getKeyCode() == KeyEvent.VK_W){
            isUp=false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S){//往下移动
            isDown=false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_A){//往左移动
            isLeft=false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D){//往右移动
            isRight=false;
        }

    }

    public void fire(){
        int bulletX= x;
        int bulletY = y;
        //判断坦克朝向
        if(direction == Direction.UP){
            //坦克当x前坐标+坦克宽度/2-8子弹宽度的一半
            bulletX =x+width/2-8;
        }else if(direction==Direction.DOWN)
        {
            bulletX=x+width/2-8;
            bulletY=y+height;
        }
        else if(direction==Direction.LEFT)
        {
            bulletY=y+height/2-8;
        }
        else if(direction==Direction.RIGHT)
        {
            bulletX=x+width;
            bulletY=y+height/2-8;
        }
        /**创建子弹对象*/
        Bullet bullet=new Bullet(
                Toolkit.getDefaultToolkit()
                        .createImage("D:/develop/JetBrains/homework/src/images/playerbullet.png"),      //子弹图片
                direction,//朝向
                bulletX,bulletY,//x,y 坐标
                16,16,//子弹大小
                gp);
        gp.playerBulletList.add(bullet);
    }

    //真正坦克移动方法
    void move()
    {
        if(isUp)
        {
            upMove();
        }
        else if(isDown)
        {
            downMove();
        }
        else if(isLeft)
        {
            leftMove();
        }
        else if(isRight)
        {
            rightMove();
        }
    }
}

