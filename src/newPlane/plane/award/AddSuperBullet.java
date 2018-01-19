package newPlane.plane.award;

import javafx.scene.image.Image;
import newPlane.PlaneClient;

public class AddSuperBullet extends AwardPlane {

    public AddSuperBullet(PlaneClient planeClient, int moveX, int moveY) {
        super(planeClient, moveX, moveY);
        this.thisPlaneImage=new Image("image/bom.png");
    }

    @Override
    public void getOneEffect(){
        planeClient.myPlane.superBullet+=1;
    }


}
