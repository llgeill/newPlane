package newPlane.plane;

import javafx.scene.image.Image;
import newPlane.util.Direction;
import newPlane.PlaneClient;
import newPlane.bullet.Bullet;

import java.awt.*;


public abstract class SuperPlane {
    public PlaneClient planeClient;
    public int moveX;
    public int moveY;
    public int lifeLength;
    public Image thisPlaneImage;
    public int PLANE_SPEED=5;
    public int planWidth=60;
    public int planHeight=60;
    public int bulletSpeed=10;
    public int score=100;
    public Direction direction=Direction.STOP;
    public Boolean isGood=false;
    public Boolean isLive=true;
    public boolean isFunction=false;
    public boolean isBeAttack=false;
    public Bullet myBullet;


    public SuperPlane(PlaneClient planeClient, int moveX, int moveY) {
        this.planeClient = planeClient;
        this.moveX = moveX;
        this.moveY = moveY;
    }



    public void draw(){
        //飞机的移动
        move();
        this.planeClient.warGraphics.drawImage(thisPlaneImage,moveX,moveY,planWidth,planHeight);
    }
    public void checkisLive(){
        if(!this.isGood&&this.moveY>=planeClient.WORLD_WEIGHT||this.moveX>=planeClient.WORLD_HEIGHT)this.isLive=false;
    }
    public abstract void fire();
    public void moveDown(){
        this.moveY+=PLANE_SPEED;
    }
    public void moveUP(){
        this.moveY-=PLANE_SPEED;
    }
    public void moveLeft(){
        this.moveX-=PLANE_SPEED;
    }
    public void moveRight(){
        this.moveX+=PLANE_SPEED;
    }
    public void move(){
        switch (direction){
            case RIGHT:
                if(checkRight()) moveRight();
                break;
            case LEFT:
                if(checkLeft()) moveLeft();
                break;
            case UP:
                if(checkUp()) moveUP();
                break;
            case DOWN:
                if(checkDown())moveDown();
                break;
            case DL:
                if(checkDL()){
                    moveDown();
                    moveLeft();
                }
                break;
            case RD:
                if(checkRD()){
                    moveRight();
                    moveDown();
                }
                break;
            case RU:
                if(checkRU()){
                    moveRight();
                    moveUP();
                }
                break;
            case UL:
                if(checkUL()){
                    moveUP();
                    moveLeft();
                }
                break;
            case STOP:
                break;
        }
    }
    public boolean checkRight(){
        if(moveX<=planeClient.WORLD_WEIGHT-70)return true;
        return false;
    }
    public boolean checkLeft(){
        if(moveX>=10)return true;
        return false;
    }
    public boolean checkUp(){
        if(moveY>=10)return true;
        return false;
    }
    public boolean checkDown(){
        if(moveY<=planeClient.WORLD_HEIGHT-110)return true;
        return false;
    }
    public boolean checkDL(){
        if(checkDown()&checkLeft())return true;
        return false;
    }
    public boolean checkRD(){
        if(checkRight()&&checkDown())return true;
        return false;
    }
    public boolean checkRU(){
        if(checkRight()&&checkUp())return true;
        return false;
    }
    public boolean checkUL(){
        if(checkUp()&&checkLeft())return true;
        return false;
    }
    public Rectangle getRectangle(){
        return new Rectangle(moveX,moveY,planWidth,planHeight);
    }



}
