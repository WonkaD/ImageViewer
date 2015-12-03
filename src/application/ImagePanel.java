package application;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import model.Image;
import ui.ImageDisplay;

public class ImagePanel extends JPanel implements ImageDisplay {

    private Image image;
    private BufferedImage bitmap;

    @Override
    public void show(Image image) {
        this.image = image;
        this.bitmap = (BufferedImage) image.bitMap();
        this.repaint();
    }

    @Override
    public Image currentImage() {
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        double relation = bitmap.getWidth() / (double) bitmap.getHeight();
        int width = this.getWidth();
        int height = this.getHeight();
        int x=0;
        int y=0;
        if (height>bitmap.getHeight() && width>bitmap.getWidth()){
            x=(int) (width - bitmap.getWidth()) / 2;
            y=(int) (height - bitmap.getHeight()) / 2;
            width = bitmap.getWidth();
            height = bitmap.getHeight();
        } else if (height / (double) bitmap.getHeight() > width / (double) bitmap.getWidth()) {
            y = (int) (height - width / relation) / 2;
            height = (int)(width/relation);
        } else {
            x = (int) (width - height * relation) / 2;
            width = (int) (height*relation);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(bitmap, x, y, width, height, ImagePanel.this);
    }
}
