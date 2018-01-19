package newPlane.bullet.myPlane;

import javafx.scene.image.Image;
import newPlane.util.Direction;
import newPlane.PlaneClient;
import newPlane.bullet.Bullet;

public class MyPlaneBulletThree extends MyPlaneBullet {
    public MyPlaneBulletThree(int x, int y, PlaneClient planeClient) {
        super(x, y, planeClient);
        this.thisBulletImage=new Image("image/bb3.png");
        this.BULLET_HEIGHT = 60;
        this.BULLET_WEIGHT = 100;
        this.BULLET_FIRE=planeClient.myPlaneBulletFire+50;
    }
}
