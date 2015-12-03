package application;

import control.Command;
import control.CopyImageCommand;
import control.NextImageCommand;
import control.OpenImageCommand;
import control.PrevImageCommand;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Image;
import ui.ImageDisplay;

public class Application extends JFrame {

    private final Map<String, Command> commands = new HashMap<>();
    private ImageDisplay imageDisplay;
    private JFileChooser fileChooser;
    private static String IMAGE_FOLDER="#";

    public void setImageFolder(String imageFolder) {
        IMAGE_FOLDER = imageFolder;
        System.out.println(imageFolder);
        imageDisplay.show(imageIn(imageFolder));
    }

    public static void main(String[] args) throws IOException {
        if (args.length==1)IMAGE_FOLDER = args[0];
        new Application().setVisible(true);
    }

    public Application() throws IOException {
        createCommands();
        if (IMAGE_FOLDER.equals("#")) createFileChooser();
        deployUI();
    }

    private void deployUI() throws IOException {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(new Dimension(500, 500));
        this.setLocationRelativeTo(null);
        this.getContentPane().add(menuBar(), BorderLayout.NORTH);
        this.getContentPane().add(imagePanel(imageIn(IMAGE_FOLDER)));
        this.getContentPane().add(toolbar(), BorderLayout.SOUTH);
        this.setFocusable(true);
        this.addKeyListener(new KeyboardManager());

    }

    private void createFileChooser() {
        fileChooser = new JFileChooser(IMAGE_FOLDER);
        FileNameExtensionFilter JPG = new FileNameExtensionFilter("Imagenes", "jpg","png","gif","bmp");
        fileChooser.setFileFilter(JPG);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            setImageFolder(fileChooser.getSelectedFile().toString());
        } else {
            System.exit(ABORT);
        }
    }

    private void createCommands() {
        commands.put("next", new NextImageCommand(this.imageDisplay));
        commands.put("prev", new PrevImageCommand(this.imageDisplay));
        commands.put("copy", new CopyImageCommand(this.imageDisplay, getToolkit().getSystemClipboard()));
        commands.put("open", new OpenImageCommand(fileChooser));
    }
    
    private ImagePanel imagePanel(Image image) throws IOException {
        ImagePanel panel = new ImagePanel();
        this.imageDisplay = panel;
        panel.show(image);
        panel.addMouseListener(new MouseShift());
        return panel;
    }

    private Image imageIn(String path) {
        return new FileImageReader(path).read();
    }

    private JPanel toolbar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(prevButton());
        panel.add(nextButton());
        return panel;
    }

    private JButton prevButton() {
        JButton button = new JButton("<");
        button.addActionListener(doCommand("prev"));
        return button;
    }

    private JButton nextButton() {
        JButton button = new JButton(">");
        button.addActionListener(doCommand("next"));
        return button;
    }

    private ActionListener doCommand(String operation) {
        return (ActionEvent e) -> {
            System.out.println("caca");
            commands.get(operation).execute();
        };
    }

    private MouseListener doShift() {
        return new MouseShift();

    }

    private KeyListener pressKey() {
        return new KeyboardManager();

    }

    private JMenuBar menuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu files = new JMenu("Archivos");
        JMenu edit = new JMenu("Editar");
        JMenu help = new JMenu("Ayuda");
        
        JMenuItem open = new JMenuItem("Abrir");
        open.addActionListener( doCommand("open"));
        files.add(open);
        
        JMenuItem delet = new JMenuItem("Eliminar");
        delet.addActionListener( doCommand("delet"));
        files.add(delet);
        
        JMenuItem copy = new JMenuItem("Copiar");
        copy.addActionListener( doCommand("copy"));
        files.add(copy);
        
        edit.add(new JMenuItem("Rotar a la derecha"));
        edit.add(new JMenuItem("Rotar a la izquierda"));
        
        menuBar.add(files);
        menuBar.add(edit);
        menuBar.add(help);
        
        return menuBar;
    }

}
