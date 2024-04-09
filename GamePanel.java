package tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

//JFrame是一个顶层的框架类，好比一个窗户的框子。
//也是一个容器类。这个框子可以嵌入几个玻璃窗
//创建界面类
public class GamePanel extends JFrame {

    //定义玩家子弹集合
    public ArrayList<Bullet> playerBulletList=new ArrayList<>();

    //创建敌人子弹集合12.3
    public ArrayList<EnemyBullet> enemyBulletList = new ArrayList<EnemyBullet>();

    // 定义绘制坦克方法与集合
    public ArrayList<EnemyTank> enemyTankList=new ArrayList<EnemyTank>();

    //获取坦克图片
    private static Image selectImg=Toolkit.getDefaultToolkit().createImage("D:/develop/JetBrains/homework/src/images/tank_r.gif");

    //定义缓存图片
    private Image buffimg;

    //定义敌人数量
    private int maxEnemyCount=10;

    //定义游戏界面的大小
    public int width=800;
    public int hight=600;

    private static int selectImgY = 200;
    //0:表示开始界面  1：代表单人模式 2：双人模式  3：游戏结束
    private int gameState = 0;

    public PlayerOne player1=new PlayerOne(
            Toolkit.getDefaultToolkit().createImage("D:/develop/JetBrains/homework/src/images/tank_l.gif"),
            Toolkit.getDefaultToolkit().createImage("D:/develop/JetBrains/homework/src/images/tank_r.gif"),
            Toolkit.getDefaultToolkit().createImage("D:/develop/JetBrains/homework/src/images/tank_u.gif"),
            Toolkit.getDefaultToolkit().createImage("D:/develop/JetBrains/homework/src/images/tank_d.gif"),
            200, 500, 64, 64, this);
  //  private Direction direction;
  //创建敌人的方法10.3
  private void createEnemy() {
      //当集合中创建敌人的数量大于10，停止创建
      //如果在开始界面 或者敌人的数据大于最大数量就停止生成敌人
      if (gameState == 0 || enemyTankList.size()>=maxEnemyCount){
          return;
      }
      else {
          Random random = new Random();
          EnemyTank enemy = new EnemyTank(
                  Toolkit.getDefaultToolkit().createImage("D:/develop/JetBrains/homework/src/images/enemy_1_l.gif"),
                  Toolkit.getDefaultToolkit().createImage("D:/develop/JetBrains/homework/src/images/enemy_1_r.gif"),
                  Toolkit.getDefaultToolkit().createImage("/develop/JetBrains/homework/src/images/enemy_1_u.gif"),
                  Toolkit.getDefaultToolkit().createImage("D:/develop/JetBrains/homework/src/images/enemy_1_d.gif"),
                  100, 32, 64, 64, this);
          enemyTankList.add(enemy);
      }
  }

    //敌人内部类11.2
    class EnemyCtrl extends Thread {
        @Override
        public void run()
        {
            while(true)
            {
                createEnemy();
                try {
                    //每次创建完敌人后休眠
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void initUI()//定义初始化UI函数
    {
        setTitle("坦克大战");//设置窗口标题
        setSize(width, hight);//设置窗口大小
        setResizable(false);//设置用户不能拖动改变窗口大小
        setDefaultCloseOperation(3);//直接关闭应用程序
        setVisible(true);//设置窗口为可见
        //添加键盘监听事件
        addKeyListener(new KeyPressAction());

        //创建敌人线程11.3
        new EnemyCtrl().start();

        //一直重新绘画界面
        while (true){
            repaint();
            try {
                Thread.sleep(25);

            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    // private static Image selectImg = Toolkit,getDefaultToolkit().createImage("images/tank_r.gif");
    //重写方法 设置游戏背景
    @Override
    public void paint(Graphics g) {
        if(buffimg == null){
            buffimg = createImage(width,hight);
        }
        Graphics imgPoing = buffimg.getGraphics();
        //设置游戏背景颜色和大小
        imgPoing.setColor(Color.black);
        imgPoing.fillRect(0,0,width,hight);
        if(gameState == 0){
            imgPoing.setColor(Color.red);
            //设置字体大小
            //new Font(String 字体，int 风格，int 字号);
            //Font.PLAIN(普通)  Font.BOLD(加粗)  Font.ITALIC(斜体)
            //Font.BOLD+ Font.ITALIC(粗斜体)
            imgPoing.setFont(new Font("楷体",Font.BOLD,40));
            //绘制文字
            imgPoing.drawString("请选择模式",300,150);
            imgPoing.setColor(Color.yellow);
            imgPoing.setFont(new Font("楷体",Font.BOLD,35));
            //绘制文字
            imgPoing.drawString("单人模式",350,250);
            imgPoing.drawString("双人模式",350,350);
            //绘制图片
            imgPoing.drawImage(selectImg, 250, selectImgY,64,64, this);
            }
            else if(gameState == 1){//绘制单人模式
            imgPoing.setColor(Color.yellow);
            imgPoing.setFont(new Font("楷体",Font.BOLD,40));

            //绘制文字
            imgPoing.drawString("单人模式",300,150);

            //绘制玩家子弹-遍历集合
            for (int i=0;i<playerBulletList.size();i++){
                playerBulletList.get(i).paintSelf(imgPoing);
            }

            //调用创建敌人方法1.10-2.2
             createEnemy();
             for (EnemyTank e:enemyTankList) {
                 e.paintSelf(imgPoing);
                 }

             //绘制敌人子弹12.4
             for (int i=0;i<enemyBulletList.size();i++){
                 //绘制子弹
                 enemyBulletList.get(i).paintSelf(imgPoing);
             }

            //绘制玩家坦克
            player1.paintSelf(imgPoing);

        }else if (gameState==2){
            imgPoing.setColor(Color.yellow);
            imgPoing.setFont(new Font("楷体",Font.BOLD,35));
            //绘制文字
            imgPoing.drawString("双人模式",350,350);
        }
        else if(gameState==3)
             {
             imgPoing.setColor(Color.yellow);
             imgPoing.setFont(new Font("楷体",Font.BOLD,35));
             imgPoing.drawString("敌机触碰到玩家，游戏失败!", 140, 250);
             //imgPoing.drawString("杀敌总数:"+killCount, 350, 350);
             }

        //绘制缓存图片
        g.drawImage(buffimg,0,0,width,hight,null);
    }


    //内部类
    class KeyPressAction extends KeyAdapter{

        //键盘松开事件
        @Override
        public void keyReleased(KeyEvent e) {

            if(gameState==1)
            {
                player1.keyReleased(e);
            }
            else if(gameState==2)
            {
                player1.keyReleased(e);
            }

        }

        //当用户点击键盘时，被调用
        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyChar());
            //用户在开始界面
            if(gameState == 0){
                //用户按下w
                if(e.getKeyCode() == KeyEvent.VK_W){
                    selectImgY = 200;
                }
                //用户按下 s
                if(e.getKeyCode() == KeyEvent.VK_S){
                    selectImgY = 300;
                }
                //用户按下 回车
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    //判断是那种游戏状态
                    if(selectImgY==200){
                        gameState=1;
                    }else if(selectImgY == 300){
                        gameState=2;
                    }
                }
            }else if (gameState ==1){
                //如果玩家选择了单人模式，将按下的键发送给玩家自身的事件方法
                player1.keyPressed(e);
            }

        }
    }

    //游戏结束14.1

    public void gameOver()
    {
        gameState=3;
        enemyBulletList.clear();
        playerBulletList.clear();
        enemyTankList.clear();
    }

}

