package newPlane.plane.award;

import javafx.scene.image.Image;
import newPlane.PlaneClient;

public class AddMyPlaneFire extends AwardPlane{
    public AddMyPlaneFire(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.thisPlaneImage=new Image("image/trans.png");
    }

    @Override
    public void getOneEffect(){
        if(planeClient.myPlane.currentFireSort<planeClient.myPlane.allFireSort-1)
        planeClient.myPlane.currentFireSort+=1;
    }

}
