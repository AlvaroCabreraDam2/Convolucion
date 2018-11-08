package filtro.de.convolucion;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Viewer extends Canvas {

    // Atributes
    private BufferedImage image;
    private BufferedImage imageToEdit;
    private byte[] data;

    // Constructor
    public Viewer() {
        
        this.loadImage();
        loadData();

    }

    // Private Methods
    public boolean loadImage() {
        try {
            this.image = ImageIO.read(new File("src/images/image.jpg"));
            this.imageToEdit = ImageIO.read(new File("src/images/image.jpg"));
            return true;
        } catch (IOException e) {
            System.out.println("No se ha podido encontrar la imagen");
            return false;
        }
    }
    
    private void loadData() {
        this.data = ((DataBufferByte) this.image.getData().getDataBuffer()).getData();
    }
    
    private int[] getPixelPosition(int x, int y) {
        int[] pixelPosition = new int[3];
        
        for (int inc = 0; inc < pixelPosition.length; inc++) {
            pixelPosition[inc] = ((y * this.image.getWidth() + x) * 3) + inc;  
        }
        
        return pixelPosition;
    }

    // Public Methods
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(image.getWidth(), image.getHeight());
    }
    
    public void paintImage(Graphics g) {
        super.paint(g);
        if (this.imageToEdit != null) {
            g.drawImage(this.imageToEdit, 0, 0, this);
        }
    }

    public int getImageWidth() {
        return this.image.getWidth();
    }

    public int getImageHeight() {
        return this.image.getHeight();
    }
    
    public void setPixelBGR (int x, int y, byte[] colors) {     
        for (int inc = 0; inc < colors.length; inc++) {
            this.data[this.getPixelPosition(x, y)[inc]] = colors[inc];
        }
    }
    
    public byte[] getPixelBGR(int x, int y) {
        byte[] pixelBGR = new byte[3];
        for (int inc = 0; inc < pixelBGR.length; inc++) {
            pixelBGR[inc] = data[this.getPixelPosition(x, y)[inc]];  
        }
        return pixelBGR;
    }
    
    public void setDataToImage() {
        this.imageToEdit.setData(Raster.createRaster(this.image.getSampleModel(), new DataBufferByte(data, data.length), new Point()));
    }
    
}
