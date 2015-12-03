package application;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.Image;
import persistence.ImageReader;

public class FileImageReader implements ImageReader {

    private File[] files;

    public FileImageReader(String path) {
        this(new File(path));
    }

    public FileImageReader(File folder) {
        this.files = folder.listFiles(new ImageFileFilter());
    }

    @Override
    public Image read() {
        return image(0);
    }

    private Image image(int index) {
        return new Image() {
            

            @Override
            public Object bitMap() {
                try {
                    return ImageIO.read(files[index]);
                } catch (IOException ex) {
                    return null;
                }          
            }

            @Override
            public Image prev() {
                return image( (index == 0) ? files.length - 1 : index - 1);
            }

            @Override
            public Image next() {
                return image( (index < files.length-1) ? index + 1 : 0) ;
            }
        };
    }

}
