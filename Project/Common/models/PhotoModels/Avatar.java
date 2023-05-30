package Common.models.PhotoModels;

import java.io.IOException;
import java.io.File;

public class Avatar extends Photo {

    public Avatar(String filePath) throws IOException {
        super(filePath);
    }
    //Checks the picture size and aspect ratio for Avatar
    public boolean isValid() {
        int maxWidth = 400;
        int maxHeight = 400;
        int maxSize = 1024 * 1024; // 1 MB in bytes
    
        int width = super.getImage().getWidth();
        int height = super.getImage().getHeight();
        long fileSize = new File(super.getFilePath()).length();
    
        if (width == maxWidth && height == maxHeight && fileSize <= maxSize) {
            return true;
        } else {
            return false;
        }
    }
    
}
