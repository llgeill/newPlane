package newPlane.bullet.myPlane;

import javafx.scene.image.Image;
import newPlane.PlaneClient;

public class MyPlaneBulletFour extends MyPlaneBullet {
    public MyPlaneBulletFour(int x, int y, PlaneClient planeClient) {
        super(x, y, planeClient);
        this.thisBulletImage=new Image("image/bb5.png");
        this.BULLET_HEIGHT = 60;
        this.BULLET_WEIGHT = 110;
        this.BULLET_FIRE=planeClient.myPlaneBulletFire+80;
    }
}
