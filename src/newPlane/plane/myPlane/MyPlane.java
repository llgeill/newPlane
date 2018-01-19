package newPlane.plane.myPlane;



import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import newPlane.util.Direction;
import newPlane.PlaneClient;
import newPlane.bullet.Bullet;
import newPlane.bullet.myPlane.*;
import newPlane.plane.SuperPlane;
import newPlane.util.PathUtil;

public class MyPlane extends SuperPlane {
    public Boolean bL=false,bR=false,bU=false,bD=false;
    public int initSuperBullet=planeClient.myPlaneInitSuperBullet;
    public int initLifeLength=planeClient.myPlaneLifeLength;
    public int superBullet=initSuperBullet;
    public int currentFireSort=0;
    public int allFireSort=4;



    public MyPlane(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.isGood=true;
        this.score=0;
        this.lifeLength=initLifeLength;
        this.PLANE_SPEED=14;
        this.thisPlaneImage=new Image("image/MyPlane.png");
    }

    @Override
    public void checkisLive() {
        if(!this.isLive||this.lifeLength<=0){
            planeClient.timeline.stop();
            planeClient.bossTimeLine.stop();
            planeClient.mediaPlayer.stop();
            new MediaPlayer(new Media(PathUtil.getUri("music/game_over.mp3"))).play();
            planeClient.alertDialog();
        }
    }

    @Override
    public void fire() {

        switch (currentFireSort){
            case 0:
                myBullet= new MyPlaneBulletOne(moveX+20,moveY,planeClient);
                planeClient.bulletList.add(myBullet);
                break;
            case 1:
                myBullet= new MyPlaneBulletTwo(moveX,moveY,planeClient);
                planeClient.bulletList.add(myBullet);
                break;
            case 2:
                myBullet= new MyPlaneBulletThree(moveX-30,moveY,planeClient);
                planeClient.bulletList.add(myBullet);
                break;
            case 3:
                myBullet= new MyPlaneBulletFour(moveX-25,moveY,planeClient);
                planeClient.bulletList.add(myBullet);
                break;
        }
            new MediaPlayer(new Media(PathUtil.getUri("music/bullet.mp3"))).play();





    }
    @Override
    //画出飞机
    public void draw(){

        move();
        this.planeClient.warGraphics.drawImage(thisPlaneImage,moveX,moveY,planWidth,planHeight);
    }
    public void superFire(){
        if(superBullet>=1){
            Bullet bullet=new MyPlaneSuperBullet(moveX-planeClient.myPlaneSuperBulletWeight/2+this.planWidth/2,moveY-planeClient.myPlaneSuperBulletHeight/2+this.planHeight/2,planeClient);
            planeClient.bulletList.add(bullet);
            superBullet--;
        }
    }
    //画出飞机的生命
    public void drawLife(){
        planeClient.myPlaneLifeCanvas.getGraphicsContext2D().clearRect(0,0,planeClient.LIFE_WEIGHT,planeClient.LIFE_HEIGHT);
        planeClient.myPlaneLifeCanvas.getGraphicsContext2D().setStroke(Color.RED);
        planeClient.myPlaneLifeCanvas.getGraphicsContext2D().strokeRect(0,0,planeClient.LIFE_WEIGHT,planeClient.LIFE_HEIGHT);
        planeClient.myPlaneLifeCanvas.getGraphicsContext2D().setFill(Color.RED);
        planeClient.myPlaneLifeCanvas.getGraphicsContext2D().fillRect(0,0,this.lifeLength*planeClient.LIFE_WEIGHT/this.initLifeLength, planeClient.LIFE_HEIGHT);
    }
    //确认当前飞机移动方向
    void locateDirection(){
        if(bL&&!bU&&!bR&&!bD)direction= Direction.LEFT;
        if(bL&&bU&&!bR&&!bD)direction=Direction.UL;
        if(!bL&&bU&&!bR&&!bD)direction=Direction.UP;
        if(!bL&&bU&&bR&&!bD)direction=Direction.RU;
        if(!bL&&!bU&&bR&&!bD)direction=Direction.RIGHT;
        if(!bL&&!bU&&bR&&bD)direction=Direction.RD;
        if(!bL&&!bU&&!bR&&bD)direction=Direction.DOWN;
        if(bL&&!bU&&!bR&&bD)direction=Direction.DL;
        if(!bL&&!bU&&!bR&&!bD)direction=Direction.STOP;
    }
    public void keyPressed(KeyEvent e) {
        KeyCode key=e.getCode();
        if(key==KeyCode.LEFT){
            bL=true;
        }else if(key==KeyCode.RIGHT){
            bR=true;
        }else if(key==KeyCode.UP){
            bU=true;
        }else if(key==KeyCode.DOWN){
            bD=true;
        }
        locateDirection();
    }
    public void keyRelease(KeyEvent e){
        KeyCode key=e.getCode();
        if(key==KeyCode.LEFT){
            bL=false;
        }else if(key==KeyCode.RIGHT){
            bR=false;
        }else if(key==KeyCode.UP){
            bU=false;
        }else if(key==KeyCode.DOWN){
            bD=false;
        }else if(key==KeyCode.Q){
            fire();
        }else if(key==KeyCode.W){
            superFire();
        }
        locateDirection();
    }
}
