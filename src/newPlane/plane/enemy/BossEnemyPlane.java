package newPlane.plane.enemy;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import newPlane.util.Direction;
import newPlane.PlaneClient;
import newPlane.bullet.Bullet;
import newPlane.bullet.enemy.EnemyBullBullet;

import java.util.Random;

public class BossEnemyPlane extends EnemyPlane {
    public int initLifeLength;

    public BossEnemyPlane(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.score=3000;
        this.planHeight=350;
        this.planWidth=200;
        this.lifeLength=3000;
        this.thisPlaneImage=new Image("image/boss.png");
        this.initLifeLength=lifeLength;
    }


    @Override
    public void draw() {
        super.draw();
        planeClient.warGraphics.clearRect(moveX,moveY,200,10);
        planeClient.warGraphics.setStroke(Color.RED);
        planeClient.warGraphics.strokeRect(moveX,moveY,200,10);
        planeClient.warGraphics.setFill(Color.RED);
        planeClient.warGraphics.fillRect(moveX,moveY,this.lifeLength*200/this.initLifeLength, 10);
    }

    @Override
    public void fire() {
        Direction[] directions=Direction.values();
        for(int i=0;i<directions.length;i++){
            Bullet bullet= new EnemyBullBullet(moveX+10,moveY+10,planeClient);
            bullet.direction=directions[i];
            if(!directions[i].equals(Direction.STOP)) planeClient.bulletList.add(bullet);
        }
    }

    @Override
    public void authoicMoveAndFire() {
        //智能移动
        count++;
        if(count==12){
            Direction[] dirs=Direction.values();
            Random random=new Random();
            Direction dir=dirs[random.nextInt(2)+4];
            this.direction=dir;
            count=0;
        }
        //智能开火
        if(new Random().nextInt(20)==10)this.fire();
    }

    @Override
    public void checkisLive(){
        //boss死掉后重新new出新的初始化数量的飞机
        if(!this.isGood&&this.lifeLength<=0)this.isLive=false;
        if(!this.isLive){
            planeClient.isPass=true;
            for(int i=0;i<planeClient.initMaxEnemyPlaneCount;i++) planeClient.enemyPlaneList.add(planeClient.createEnemyPlane.createEnemyPlane());
            planeClient.enemyPlaneList.remove(this);
        }
    }
}
