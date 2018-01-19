package newPlane.plane.enemy;

import newPlane.util.Direction;
import newPlane.explore.Explore;
import newPlane.PlaneClient;
import newPlane.bullet.Bullet;
import newPlane.bullet.enemy.EnemyPlaneBullet;
import newPlane.plane.SuperPlane;


import java.util.Random;

public class EnemyPlane extends SuperPlane {
    public int count=0;


    public EnemyPlane(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.isGood=false;
        this.direction=Direction.DOWN;
        this.lifeLength=80;
    }

    @Override
    public void fire() {
        Bullet bullet= new EnemyPlaneBullet(moveX+10,moveY+10,planeClient);
        bullet.BULLET_SPEED=bulletSpeed;
        planeClient.bulletList.add(bullet);
    }


    @Override
    public void move() {
        authoicMoveAndFire();
        super.move();
    }
    //自动移动实际上为随机改变方向
    public void authoicMoveAndFire(){
        //智能移动，每数到12就换一次方向，继续移动
        moveDown();
        count++;
        if(count==12){
            Direction[] dirs=Direction.values();
            Random random=new Random();
            Direction dir=dirs[random.nextInt(2)];
            this.direction=dir;
            count=0;
        }
        //智能开火
        if(new Random().nextInt(50)==10)this.fire();
    };
    public void isCollision(){
        if(this.getRectangle().intersects(planeClient.myPlane.getRectangle())){
            if(this.isFunction){
                this.isLive=false;
                this.getOneEffect();
                return;
            }
            if(!this.isBeAttack){
                isBeAttack=true;
                this.lifeLength-=planeClient.collsionForEnemyPlaneFire;
                planeClient.exploreList.add(new Explore(moveX,moveY,planeClient));
                planeClient.myPlane.lifeLength-=planeClient.collsionForMyPlaneFire;
            }
        }
    }
    //检查生命并且判断是否需要随机生成奖励
    public void checkisLive(){
        if(!this.isGood&&this.moveY>=planeClient.WORLD_WEIGHT||this.moveX>=planeClient.WORLD_HEIGHT)this.isLive=false;
        //判断是否需要增加新的敌机
        if(!this.isGood&&this.lifeLength<=0)this.isLive=false;
        if(!this.isLive){
            planeClient.enemyPlaneList.remove(this);
            if(planeClient.enemyPlaneList.size()<planeClient.maxEnemyPlaneCount)
                planeClient.enemyPlaneList.add(planeClient.createEnemyPlane.createEnemyPlane());
            if(!this.isFunction){
                //随机生成附加生命，在工具类里面随机
                EnemyPlane award=planeClient.createAward.createAward();
                if(award!=null) planeClient.enemyPlaneList.add(award);
            }
        }
    }
    public void getOneEffect(){};
    public boolean checkDown(){
       return true;
    }
    public boolean checkDL(){
        return true;
    }
    public boolean checkRD(){
        return true;
    }

}
