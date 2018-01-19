package newPlane.bullet.enemy;


import javafx.scene.image.Image;

import newPlane.PlaneClient;
import newPlane.bullet.Bullet;


public class EnemyPlaneBullet extends Bullet {
    public EnemyPlaneBullet(int x, int y , PlaneClient planeClient) {
        super(x, y, planeClient);
        this.thisBulletImage=new Image("image/feidan.png");
        this.BULLET_HEIGHT=40;
        this.BULLET_WEIGHT=15;
        this.isGood=false;
    }

}
