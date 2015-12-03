package control;

import application.Application;
import javax.swing.JFileChooser;

public class OpenImageCommand implements Command {

    private final JFileChooser fileChooser;
    private final Application application;

    public OpenImageCommand(JFileChooser frame, Application application) {
        this.fileChooser = frame;
        this.application = application;
    }

    @Override
    public void execute() {
        if (fileChooser.showOpenDialog(fileChooser));

    }

}
