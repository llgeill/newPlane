package newPlane.bullet;

import javafx.scene.image.Image;
import newPlane.*;
import newPlane.explore.Explore;
import newPlane.plane.enemy.EnemyPlane;
import newPlane.plane.SuperPlane;
import newPlane.util.Direction;

import java.awt.Rectangle;
import java.util.List;

public  class  Bullet {
    public int moveX=0;
    public int moveY=0;
    public int BULLET_HEIGHT=40;
    public int BULLET_WEIGHT=10;
    public int BULLET_SPEED=10;
    public int BULLET_FIRE=10;
    public Direction direction=Direction.DOWN;
    public Boolean isGood=false;
    public Boolean isLive=true;
    public Image thisBulletImage=new Image("image/zidan1.png");
    public PlaneClient planeClient;



    public Bullet(int x, int y, PlaneClient planeClient){
        this.moveY=y;
        this.moveX=x;
        this.planeClient=planeClient;
    }
    public void draw(){
        move();
        this.planeClient.warGraphics.drawImage(thisBulletImage,moveX,moveY,BULLET_WEIGHT,BULLET_HEIGHT);
    }
    public void move(){
        switch (direction){
            case DOWN:
                moveDown();
                break;
            case UP:
                moveUP();
                break;
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case DL:
                moveDown();
                moveLeft();
                break;
            case RU:
                moveRight();
                moveUP();
                break;
            case RD:
                moveRight();
                moveDown();
                break;
            case UL:
                moveUP();
                moveLeft();
                break;
        }
    }
    public void checkIsLive(){
        if(this.moveX>=planeClient.WORLD_WEIGHT||this.moveX<=0||this.moveY>=planeClient.WORLD_HEIGHT||this.moveY<=0)this.isLive=false;
        if(!this.isLive) planeClient.bulletList.remove(this);
    }
    public void hitPlane(SuperPlane plane) {
        if (plane.isGood != this.isGood && !plane.isFunction && plane.getRectangle().intersects(this.getRectangle())) {
            this.isLive=false;
            plane.lifeLength -= BULLET_FIRE;
            //如果受到攻击将会降低子弹威力
            if(plane.isGood&&planeClient.myPlane.currentFireSort>0)planeClient.myPlane.currentFireSort--;
            if (plane.lifeLength <= 0) {
                plane.isLive = false;
                planeClient.exploreList.add(new Explore(moveX, moveY, planeClient));
                if(!plane.isGood)planeClient.scroeText.setText("分数 : " + String.valueOf(planeClient.score += plane.score));
            }
        }
    }
    public void hitPlanes(List<EnemyPlane> planeList){

        for(int i=0;i<planeList.size();i++){
            hitPlane(planeList.get(i));
        }
    }
    public Rectangle getRectangle(){
        return new Rectangle(moveX,moveY,BULLET_WEIGHT,BULLET_HEIGHT);
    }
    public void moveDown(){
        this.moveY+=BULLET_SPEED;
    }
    public void moveUP(){
        this.moveY-=BULLET_SPEED;
    }
    public void moveLeft(){
        this.moveX-=BULLET_SPEED;
    }
    public void moveRight(){
        this.moveX+=BULLET_SPEED;
    }

}
