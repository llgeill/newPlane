package newPlane.plane.award;

import javafx.scene.image.Image;
import newPlane.PlaneClient;

public class AddLifeLength extends AwardPlane{


    public AddLifeLength(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.thisPlaneImage=new Image("image/life1.png");
    }


    @Override
    public void getOneEffect(){
        planeClient.myPlane.lifeLength=planeClient.myPlane.initLifeLength;
    }

    @Override
    public void fire() {}
}
