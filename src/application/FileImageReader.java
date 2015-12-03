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
        System.out.println("new");
        System.out.println(folder.toString());
        System.out.println(folder.isDirectory());
        System.out.println(folder.getParentFile());
        if (folder.isDirectory()) {
            this.files = folder.listFiles(new ImageFileFilter());
        } else if (folder.isFile()){
            this.files = folder.getParentFile().listFiles(new ImageFileFilter());
        }
        if (files.length == 0) {
            this.files = new File[]{new File("C:\\Users\\WonkaD\\Documents\\NetBeansProjects\\ImageViewer\\src\\NoDirectory\\error.jpg")};
        }
        System.out.println(files.length);
        System.out.println(files[0].toString());
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
                return image((index == 0) ? files.length - 1 : index - 1);
            }

            @Override
            public Image next() {
                return image((index < files.length - 1) ? index + 1 : 0);
            }
        };
    }

}
