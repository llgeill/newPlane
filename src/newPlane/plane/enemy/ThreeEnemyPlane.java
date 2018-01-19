package newPlane.plane.enemy;

import javafx.scene.image.Image;
import newPlane.PlaneClient;

import java.util.Random;

public class ThreeEnemyPlane extends EnemyPlane{
    public ThreeEnemyPlane(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.planHeight=100;
        this.planWidth=100;
        this.PLANE_SPEED=20;
        this.score=60;
        this.thisPlaneImage=new Image("image/threeEnemy.png");
    }

    @Override
    public void fire() {
    }

    @Override
    public void authoicMoveAndFire() {

    }
}
