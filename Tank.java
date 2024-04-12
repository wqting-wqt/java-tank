package tank;

import com.sun.javafx.scene.traversal.Direction;

import java.awt.*;

public abstract class Tank extends GameObject {
    //移动速度
    public int speed = 3;
    public Image leftImg;
    public Image rightImg;
    public Image upImg;
    public Image DownImg;
    public Direction direction;

    public Tank(Image leftImg, Image rightImg, Image upImg, Image DownImg, int x, int y, int width, int height, GamePanel gp) {
        this.leftImg = leftImg;
        this.rightImg = rightImg;
        this.upImg = upImg;
        this.DownImg = DownImg;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gp = gp;
    }

    public abstract void paintSelf(Graphics g);

    public abstract Rectangle getRect();

    //1.12-2
    class FireCd extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            canFire = true;
        }
    }

    //开火函数的变量
     public boolean canFire=true;
    //开火1.12-2

    public void fire(int type) {
        int bulletX = x;
        int bulletY = y;
        if (canFire) {
            bulletX = x;
            bulletY = y;
            if (direction == Direction.UP)
            {
                bulletX = x + width / 2 - 8;
            }
            else if (direction == Direction.DOWN)
            {
                bulletX = x + width / 2 - 8;
                bulletY = y + height;
            }
            else if (direction == Direction.LEFT)
            {
                bulletY = y + height / 2 - 8;
            }
            else if (direction == Direction.RIGHT)
            {
                bulletX = x + width;
                bulletY = y + height / 2 - 8;
            }
            canFire = false;
            new FireCd().start();
            //玩家坦克开火
            if (type == 0) {
                Bullet bullet = new Bullet(
                        Toolkit.getDefaultToolkit().createImage("D:develop/JetBrains/homework/src/images/playerbullet.png")
                        , direction, bulletX, bulletY, 16, 16, gp);
                gp.playerBulletList.add(bullet);
            }
            else
            { //敌人开火
                EnemyBullet bullet = new EnemyBullet(
                        Toolkit.getDefaultToolkit().createImage("D:/develop/JetBrains/homework/src/images/enemybullet.png"),
                        direction, bulletX, bulletY, 16, 16, gp);
                gp.enemyBulletList.add(bullet);

            }
        }
    }
       //往上
        public void upMove() {
            //修改坦克方向
            direction = Direction.UP;
            //将往下移动图片赋值
            image = upImg;
            y = y - speed;
            if (y < (height / 2)) {
                y = height / 2;
            }
        }
        //往下
        public void downMove()
        {
            //修改坦克方向
            direction = Direction.DOWN;
            //将往上移动图片赋值
            image = DownImg;
            y = y + speed;
            if (y > gp.hight - height) {
                y = gp.hight - height;
            }
        }
        //往左
        public void leftMove ()
        {
            //修改坦克方向
            direction = Direction.LEFT;
            //将往上移动图片赋值
            image = leftImg;
            x = x - speed;
            if (x < 0) {
                x = 0;
            }
        }
        //往右
        public void rightMove ()
        {
            //修改坦克方向
            direction = Direction.RIGHT;
            //将往上移动图片赋值
            image = rightImg;
            x = x + speed;
            if (x > gp.width - width) {
                x = gp.width - width;
            }
        }

    }


