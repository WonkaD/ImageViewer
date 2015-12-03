package ui;

import model.Image;

public interface ImageDisplay {
    Image currentImage();
    void show(Image image);

}
