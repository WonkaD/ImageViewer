package application;

import java.io.File;
import java.io.FileFilter;

public class ImageFileFilter implements FileFilter{
    private static final String[] IMAGE_EXTENSIONS = {".jpg", ".png", ".gif"};

    @Override
    public boolean accept(File pathname) {
        for (String extension : IMAGE_EXTENSIONS) {
                if (pathname.getName().endsWith(extension)) {
                    return true;
                }
            }
            return false;
    }
    
}
