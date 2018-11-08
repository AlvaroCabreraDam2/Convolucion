package filtro.de.convolucion;

import java.awt.GridBagLayout;
import javax.swing.JFrame;

public class MainActivity {

    // Atributes
    private Viewer viewer;
    private Filter filter;
    private JFrame window;

    // Constructor
    public MainActivity() {
        this.viewer = new Viewer();
        this.filter = new Filter();
        this.newWindow();
        this.applyFilter();
        this.viewer.setDataToImage();
        this.viewer.paintImage(this.viewer.getGraphics());
    }

    // Private Methods
    private void newWindow() {
        this.window = new JFrame("Filtro de convolucion");
        this.window.setSize(this.viewer.getImageWidth() + 40, this.viewer.getImageHeight() + 40);
        this.window.setLocationRelativeTo(null);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.getContentPane().setLayout(new GridBagLayout());
        this.window.getContentPane().add(this.viewer);
        this.window.setVisible(true);
    }

    private void applyFilter() {
        for (int x = 1; x < this.viewer.getImageWidth() - 1; x++) {
            for (int y = 1; y < this.viewer.getImageHeight() - 1; y++) {
                
                byte[] resultColors = new byte[3];
                
                for (int inc = 0; inc < 3; inc++) {
                    
                    int[][] pixels = new int[3][3];
                    // Crear matriz con los 9 valores de cada pixel
                    for (int x2 = 0, posRelativeY = -1; x2 < 3; x2++, posRelativeY++) {
                        for (int y2 = 0, posRelativeX = -1; y2 < 3; y2++, posRelativeX++) {
                            pixels[x2][y2] = Byte.toUnsignedInt(this.viewer.getPixelBGR(x + posRelativeX, y + posRelativeY)[inc]);
                        }
                    }
                    
                    resultColors[inc] = this.filter.applyFilter(pixels);
                }
                
                this.viewer.setPixelBGR(x, y, resultColors);
            }
        }
    }

    // Public Methods
    // Main
    public static void main(String[] args) {

        MainActivity main = new MainActivity();

    }

}
