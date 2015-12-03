package control;

import javax.swing.JFileChooser;

public class OpenImageCommand implements Command {

    private final JFileChooser fileChooser;

    public OpenImageCommand(JFileChooser frame) {
        this.fileChooser = frame;
    }

    @Override
    public void execute() {
        fileChooser.showOpenDialog(fileChooser);

    }

}
