package newPlane.bullet.myPlane;

import javafx.scene.image.Image;
import newPlane.util.Direction;
import newPlane.PlaneClient;
import newPlane.explore.Explore;
import newPlane.plane.SuperPlane;

public class MyPlaneSuperBullet extends MyPlaneBullet {
    public Image shield=new Image("image/shield.png");
    public Image shield02=new Image("image/shield02.png");
    public boolean flag=true;

    public MyPlaneSuperBullet(int x, int y , PlaneClient planeClient) {
        super(x, y, planeClient);
        this.BULLET_HEIGHT=planeClient.myPlaneSuperBulletWeight;
        this.BULLET_WEIGHT=planeClient.myPlaneSuperBulletHeight;
        this.BULLET_FIRE=planeClient.myPlaneSuperBulletFire;

    }

    @Override
    public void draw() {
        move();
        if(flag){
            this.planeClient.warGraphics.drawImage(shield02, moveX, moveY, BULLET_WEIGHT, BULLET_HEIGHT);
            flag=false;
        }else{
            this.planeClient.warGraphics.drawImage(shield, moveX, moveY, BULLET_WEIGHT, BULLET_HEIGHT);
            flag=true;
        }

    }

    //只允许攻击一次
    public void hitPlane(SuperPlane plane) {
        if (plane.isGood != this.isGood && !plane.isBeAttack&& !plane.isFunction && plane.getRectangle().intersects(this.getRectangle())) {
            this.isLive=false;
            plane.isBeAttack=true;
            plane.lifeLength -= BULLET_FIRE;
            //如果受到攻击将会降低子弹威力
            if(plane.isGood&&planeClient.myPlane.currentFireSort>0)planeClient.myPlane.currentFireSort--;
            if (plane.lifeLength <= 0) {
                plane.isLive = false;
                planeClient.exploreList.add(new Explore(plane.moveX, plane.moveY, planeClient));
                if(!plane.isGood)planeClient.scroeText.setText("分数 : " + String.valueOf(planeClient.score += plane.score));
            }
        }
    }

    @Override
    //可以消灭敌人子弹，与其他子弹有所区别。
    public void checkIsLive(){
        if(this.moveY<=-110){
            this.isLive=false;
            planeClient.bulletList.remove(this);
        }

        for(int i=0;i<planeClient.bulletList.size();i++){
            if(planeClient.bulletList.get(i).getRectangle().intersects(this.getRectangle())){
                if(!planeClient.bulletList.get(i).isGood) planeClient.bulletList.get(i).isLive=false;
            }
        }
    }

}
