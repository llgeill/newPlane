package newPlane.plane.enemy;

import javafx.scene.image.Image;

import newPlane.PlaneClient;



public class OneEnemyPlane extends EnemyPlane {
    public OneEnemyPlane(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.planHeight=80;
        this.planWidth=80;
        this.PLANE_SPEED=10;
        this.thisPlaneImage=new Image("image/oneEnemy.png");
        this.bulletSpeed=12;
        this.score=230;
    }

}
