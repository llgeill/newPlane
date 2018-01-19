package newPlane.bullet.myPlane;

import javafx.scene.image.Image;
import newPlane.PlaneClient;
import newPlane.bullet.Bullet;
import newPlane.util.Direction;

public class MyPlaneBullet extends Bullet {
    public MyPlaneBullet(int x, int y , PlaneClient planeClient) {
        super(x, y, planeClient);
        this.direction= Direction.UP;
        this.BULLET_FIRE=planeClient.myPlaneBulletFire;
        this.isGood=true;
    }
}
