package plantenApp.java.model.data;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class FotoData {
    private ArrayList<Image> images;

    public void Bind(ImageView[] imageviews) {
        for (int i = 0; i < imageviews.length; i++) {
            imageviews[i].setImage(images.get(i));
        }
    }

    public ArrayList<Image> getImages() {
        return images;
    }

    public void setImages(ArrayList<Image> images) {
        this.images = images;
    }
}
