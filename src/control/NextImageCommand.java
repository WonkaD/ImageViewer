package control;

import model.Image;
import ui.ImageDisplay;

public class NextImageCommand implements Command {

    private final ImageDisplay imageDisplay;

    public NextImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.show(nextImage());
    }

    public Image currentImage() {
        return imageDisplay.currentImage();
    }

    private Image nextImage() {
        return currentImage().next();
    }

}
