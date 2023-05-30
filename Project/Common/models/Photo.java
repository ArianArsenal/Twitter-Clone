package Common.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Photo {
    private BufferedImage image;

    public Photo(String filePath) throws IOException {
        File file = new File(filePath);
        image = ImageIO.read(file);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
    public static void main(String[] args) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(null, null, false, null);
        bufferedImage.get
    }
    

}