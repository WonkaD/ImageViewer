package application;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TransferableImage implements Transferable{
    private final Image image;

    public TransferableImage(Object image) {
        BufferedImage bitmap = (BufferedImage) image;
        this.image = bitmap.getScaledInstance(bitmap.getWidth(),bitmap.getHeight(), BufferedImage.SCALE_DEFAULT);
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.imageFlavor};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor df) {
        return df == DataFlavor.imageFlavor;
    }

    @Override
    public Object getTransferData(DataFlavor df) throws UnsupportedFlavorException, IOException {
        if (isDataFlavorSupported(df)){
            return image;
        }else {
            throw new UnsupportedFlavorException(df);
        }
    }
    
}
