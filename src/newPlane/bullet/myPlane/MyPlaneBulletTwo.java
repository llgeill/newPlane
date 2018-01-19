package newPlane.bullet.myPlane;

import javafx.scene.image.Image;
import newPlane.util.Direction;
import newPlane.PlaneClient;
import newPlane.bullet.Bullet;

public class MyPlaneBulletTwo extends MyPlaneBullet {
    public MyPlaneBulletTwo(int x, int y, PlaneClient planeClient) {
        super(x, y, planeClient);
        this.thisBulletImage=new Image("image/MyBullet2.png");
        this.BULLET_HEIGHT = 50;
        this.BULLET_WEIGHT = 50;
        this.BULLET_FIRE=planeClient.myPlaneBulletFire+20;
    }
}
