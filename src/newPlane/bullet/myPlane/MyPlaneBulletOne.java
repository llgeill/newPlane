package newPlane.bullet.myPlane;

import javafx.scene.image.Image;
import newPlane.util.Direction;
import newPlane.PlaneClient;
import newPlane.bullet.Bullet;


public class MyPlaneBulletOne extends MyPlaneBullet {

    public MyPlaneBulletOne(int x, int y , PlaneClient planeClient) {
        super(x, y, planeClient);
        this.thisBulletImage=new Image("image/MyBulletOne.png");
        this.BULLET_HEIGHT = 40;
        this.BULLET_WEIGHT = 40;
    }


}