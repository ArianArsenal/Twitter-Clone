package Common.models.PhotoModels;

import java.io.IOException;
import java.io.File;

public class Header extends Photo {

    public Header(String filePath) throws IOException {
        super(filePath);
    }
    //Checks the picture size and aspect ratio for header
    public boolean isValid() {
        int maxWidth = 1500;
        int maxHeight = 1500;
        int maxSize = 2 * 1024 * 1024; // 2 MB in bytes
    
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
