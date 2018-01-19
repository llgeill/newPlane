package newPlane.plane.enemy;

import javafx.scene.image.Image;
import newPlane.PlaneClient;

public class TwoEnemyPlane extends EnemyPlane{
    public TwoEnemyPlane(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.lifeLength=50;
        this.PLANE_SPEED=5;
        this.bulletSpeed=12;
        this.thisPlaneImage=new Image("image/otherPlane.png");
        this.score=30;
    }

}
