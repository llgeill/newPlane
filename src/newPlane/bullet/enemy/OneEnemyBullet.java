package newPlane.bullet.enemy;

import javafx.scene.image.Image;
import newPlane.PlaneClient;

public class OneEnemyBullet extends EnemyPlaneBullet {
    public OneEnemyBullet(int x, int y , PlaneClient planeClient) {
        super(x, y, planeClient);
        this.thisBulletImage=new Image("image/jiguang4.png");
    }

}
