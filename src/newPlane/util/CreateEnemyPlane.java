package newPlane.util;

import newPlane.PlaneClient;
import newPlane.plane.enemy.*;

import java.util.Random;

public class CreateEnemyPlane {
    public PlaneClient planeClient;

    public CreateEnemyPlane(PlaneClient planeClient) {
        this.planeClient = planeClient;
    }

    public EnemyPlane createEnemyPlane(){
        EnemyPlane enemyPlane=null;
        Random rd=new Random();
        int rds=rd.nextInt(8)+1;
        switch (new Random().nextInt(12)+1){
            case 1:
                enemyPlane=new OneEnemyPlane(planeClient,rds*80,-50*rds);
                break;
            case 5:
                enemyPlane=new ThreeEnemyPlane(planeClient,rds*80,-50*rds);
                break;
            case 10:
                enemyPlane=new FourEnemyPlane(planeClient,rds*80,-50*rds);
                break;
            default:
                enemyPlane=new TwoEnemyPlane(planeClient,rds*80,-50*rds);
                break;
        }
        return enemyPlane;
    }
}
