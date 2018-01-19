package newPlane.bullet.enemy;

import javafx.scene.image.Image;
import newPlane.PlaneClient;

public class EnemyBullBullet extends EnemyPlaneBullet {
    public EnemyBullBullet(int x, int y, PlaneClient planeClient) {
        super(x, y, planeClient);
        this.thisBulletImage=new Image("image/wb-01.png");
        this.BULLET_HEIGHT=60;
        this.BULLET_WEIGHT=90;
    }
}
