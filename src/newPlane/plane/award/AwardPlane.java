package newPlane.plane.award;

import newPlane.PlaneClient;
import newPlane.plane.enemy.EnemyPlane;

public class AwardPlane extends EnemyPlane {
    public AwardPlane(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.isGood=false;
        this.planWidth=90;
        this.isFunction=true;
    }

    @Override
    public void fire() {}
}
