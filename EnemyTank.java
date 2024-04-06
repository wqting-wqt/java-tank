
package tank;

import com.sun.javafx.scene.traversal.Direction;
import java.util.Random;
import java.awt.*;

//创建敌人坦克类
public class EnemyTank extends Tank {
    public EnemyTank(Image leftImg, Image rightImg, Image upImg, Image DownImg, int x, int y, int width, int height, GamePanel gp) {
        super(leftImg, rightImg, upImg, DownImg, x, y, width, height, gp);
        //image = upImg;
        //direction= Direction.LEFT;//设置移动的方向
        new DirectionCtrl().start();
    }

    //创建移动方法move()11后1
    void move() {
        if(direction==Direction.UP)
        {
            upMove();
        }
        else if(direction==Direction.DOWN)
        {
            downMove();
        }
        else if(direction==Direction.LEFT)
        {
            leftMove();
        }
        else if(direction==Direction.RIGHT)
        {
            rightMove();
        }
    }


    //让敌人随机移动创建一个随机移动的方法randomDirection（）11后.3
    void randomDirection()
    {
        Random ran=new Random();
        int n=ran.nextInt(4);
        if(n==0)
        {
            direction=Direction.UP;
        }
        else if(n==1)
        {
            direction=Direction.DOWN;
        }
        else if(n==2)
        {
            direction=Direction.LEFT;
        }
        else if (n==3){
            direction=Direction.RIGHT;
        }
    }

    //创建线程对象
    class DirectionCtrl extends Thread//11后.4
    {
         @Override
         public void run()
         {
             while(true)
                 {
                 randomDirection();
                 try {
                     Thread.sleep(2000);
                     } catch (InterruptedException e) {
                     // TODO Auto-generated catch block
                     e.printStackTrace();
                    }
                }
            }
        }

    //1.10-1
    @Override
    public void paintSelf(Graphics g) {
        //fire(3);//12
        fire(1);//13
        move();
        g.drawImage(image, x, y, width, height, null);
    }

    @Override
    public Rectangle getRect() {
        // TODO Auto-generated method stub
        return new Rectangle(x, y, width, height);
    }

}


