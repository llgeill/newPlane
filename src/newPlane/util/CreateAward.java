package newPlane.util;

import newPlane.PlaneClient;
import newPlane.plane.award.AddLifeLength;
import newPlane.plane.award.AddMyPlaneFire;
import newPlane.plane.award.AddSuperBullet;
import newPlane.plane.enemy.EnemyPlane;

import java.util.Random;

public class CreateAward {
    public PlaneClient planeClient;

    public CreateAward(PlaneClient planeClient) {
        this.planeClient = planeClient;
    }

    public EnemyPlane createAward(){
        EnemyPlane enemyPlane=null;
        int rds=new Random().nextInt(8)+1;
        switch (new Random().nextInt(20)+1){
            case 10:
                enemyPlane=new AddLifeLength(planeClient,rds*80,-80*rds);
                break;
            case 15:
                enemyPlane=new AddSuperBullet(planeClient,rds*80,-80*rds);
                break;
            case 1:
                enemyPlane=new AddMyPlaneFire(planeClient,rds*80,-80*rds);
                break;

        }
        return enemyPlane;
    }
}
