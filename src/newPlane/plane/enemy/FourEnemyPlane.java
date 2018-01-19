package newPlane.plane.enemy;

import javafx.scene.image.Image;
import newPlane.PlaneClient;
import newPlane.bullet.Bullet;
import newPlane.bullet.enemy.OneEnemyBullet;

import java.util.Random;

public class FourEnemyPlane extends EnemyPlane{
    public FourEnemyPlane(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.planHeight=100;
        this.planWidth=100;
        this.PLANE_SPEED=15;
        this.score=120;
        this.thisPlaneImage=new Image("image/a4-3.png");
    }

    @Override
    public void authoicMoveAndFire() {
        //智能开火
        if(new Random().nextInt(10)==5)this.fire();
    }
    @Override
    //如果重写fire，那么子弹速度无效
    public void fire() {
        Bullet bullet= new OneEnemyBullet(moveX+20,moveY+30,planeClient);
        bullet.BULLET_SPEED=25;
        Bullet bullet1= new OneEnemyBullet(moveX+70,moveY+30,planeClient);
        bullet1.BULLET_SPEED=25;
        planeClient.bulletList.add(bullet);
        planeClient.bulletList.add(bullet1);
    }
}
