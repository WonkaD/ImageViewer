package control;

import application.TransferableImage;
import java.awt.datatransfer.Clipboard;
import javafx.scene.image.Image;
import javafx.scene.input.ClipboardContent;
import ui.ImageDisplay;

public class CopyImageCommand implements Command {

    private final ImageDisplay imageDisplay;
    private final Clipboard clipboard;

    public CopyImageCommand(ImageDisplay imageDisplay, Clipboard clipboard) {
        this.imageDisplay = imageDisplay;
        this.clipboard = clipboard;
    }

    @Override
    public void execute() {
        TransferableImage image = new TransferableImage(imageDisplay.currentImage().bitMap());
        clipboard.setContents(image, null);
        
    }

}
