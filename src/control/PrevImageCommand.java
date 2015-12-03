package control;

import model.Image;
import ui.ImageDisplay;

public class PrevImageCommand implements Command {

    private final ImageDisplay imageDisplay;

    public PrevImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.show(prevImage());
    }

    public Image currentImage() {
        return imageDisplay.currentImage();
    }

    private Image prevImage() {
        return currentImage().prev();
    }

}
