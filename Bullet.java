package tank;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;
//创建子弹类继承继承父类（游戏物体类）
public class Bullet extends GameObject{
    //定义子弹速度
    public int speed=7;

    //public GamePanel gp;
    //方向移动枚举类
    public Direction direction;
    public Bullet(Image image,Direction direction,int x,int y,int width,int height,GamePanel gp)
    {
        this.image=image;
        this.direction=direction;
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.gp=gp;
    }


    //修改子弹坐标
    public void move()
    {
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
    }



    @Override
    public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }

    //碰撞检测1.13-2

    public void hitEnemy() {
        //获取敌人的集合
        for (EnemyTank enemy : gp.enemyTankList) {
            //如果自身的矩形和其他矩形相交
            //判断是否和敌人碰撞
            if (getRect().intersects(enemy.getRect())) {
                //消灭敌人 中集合中消失
                gp.enemyTankList.remove(enemy);
                //移除子弹
                gp.playerBulletList.remove(this);
                //gp.killCount++;
                break;
            }
        }
    }
    //碰撞检测1.13-2
    public void hitEnemyBullet()
    {
        for(EnemyBullet enemyBullet:gp.enemyBulletList)
        {
            if(getRect().intersects(enemyBullet.getRect()))
            {
                gp.enemyBulletList.remove(enemyBullet);
                gp.playerBulletList.remove(this);
                break;
            }
        }
    }


    @Override
    public void paintSelf(Graphics g) {
        move();
        hitEnemyBullet();//13.4
        hitEnemy();//13.5
        g.drawImage(image, x, y, width,height, null);
    }

}
