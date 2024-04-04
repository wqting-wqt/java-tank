
package tank;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;

//创建敌人子弹类EnemyBullet继承Bullet
public class EnemyBullet extends Bullet {
    public EnemyBullet(Image image, Direction direction, int x, int y, int width, int height, GamePanel gp) {
        super(image, direction, x, y, width, height, gp);
    }

   public void hitPlayer(){//子弹类碰撞检测14.2
        //敌人子弹类检测碰撞
        if(getRect().intersects(gp.player1.getRect())){
            gp.gameOver();
        }
    }



//子弹类碰撞检测14.2
@Override
public void paintSelf(Graphics g) {
    move();
    hitPlayer();
    g.drawImage(image, x, y, width, height, null);
}

@Override
public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

public void move() {
    //当前方向往上
    if(direction==Direction.UP)
    {
        y=y-speed;
    }//当前方向往下
    else if(direction==Direction.DOWN)
    {
        y=y+speed;
    }//当前方向往左
    else if(direction==Direction.LEFT)
    {
        x=x-speed;
    }//当前方向往右
    else if(direction==Direction.RIGHT)
    {
        x=x+speed;
    }
      if (x < 0) {
          gp.enemyBulletList.remove(this);
      }
      if (x > gp.width) {
          gp.enemyBulletList.remove(this);
      }
      if (y > gp.hight) {
          gp.enemyBulletList.remove(this);
      }
      if (y < 0) {
          gp.enemyBulletList.remove(this);
      }
    }
}



