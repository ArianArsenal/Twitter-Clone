package Common.models;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Photo {
    private BufferedImage image;
    private String filePath;

    public Photo(String filePath) throws IOException {
        this.filePath = filePath;
        File file = new File(filePath);
        image = ImageIO.read(file);
    }
    //Saves the photo if nessessory 
    public void save(String filePath, String format) throws IOException {
        File file = new File(filePath);
        ImageIO.write(image, format, file);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    

}