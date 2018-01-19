package newPlane.explore;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import newPlane.PlaneClient;

public class Explore {
    public int EXPLORE_HEIGHT=230;
    public int EXPLORE_WEIGHT=230;
    public int moveX;
    public int moveY;
    public boolean live=true;
    public PlaneClient planeClient;
    public int count=0;
    static Image[] bomImage=new Image[15];
    static {
        for(int i=0;i<bomImage.length;i++){
            int j=i+1;
            bomImage[i]=new Image("image/boom"+j+".png");
        }
    }
    public Explore(int moveX, int moveY, PlaneClient planeClient){
        this.moveX=moveX;
        this.moveY=moveY;
        this.planeClient=planeClient;
    }

    public void draw(){
        if(!live){
            planeClient.exploreList.remove(this);
            return;
        }
        if(count==bomImage.length){
            live=false;
            count=0;
            return;
        }
        this.planeClient.warGraphics.drawImage(bomImage[count],moveX,moveY,EXPLORE_WEIGHT,EXPLORE_HEIGHT);
        count++;
    }

}
