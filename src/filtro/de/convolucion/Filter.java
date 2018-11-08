package filtro.de.convolucion;

public class Filter {

    // Atributes
    private int[][] filter;
    private double divider;

    // Constructor
    public Filter() {

        this.filter = new int[3][3];
        initFilter();

        this.divider = 1;

    }

    // Private Methods
    private void initFilter() {

        // [a] [b] [c]
        // [d] [e] [f]
        // [g] [h] [i]
        this.filter[0][0] = -1; // a
        this.filter[0][1] = -1; // b
        this.filter[0][2] = -1; // c
        this.filter[1][0] = -1; // d
        this.filter[1][1] = 8; // e
        this.filter[1][2] = -1; // f
        this.filter[2][0] = -1; // g
        this.filter[2][1] = -1; // h
        this.filter[2][2] = -1; // i

    }

    // Public Methods
    public byte applyFilter(int[][] pixels) {

        // Variable en la que se guardara el resultado
        int resultColor = 0;

        // Sumar todos los valores con su correspondiente peso
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels.length; col++) {
                resultColor += (pixels[row][col] * this.filter[row][col]);
            }
        }

        // Dividir la suma entre el divisor
        resultColor = (int)(((double) resultColor) / this.divider);

        if (resultColor < 0) {
            resultColor = 0;
        }
        
        if (resultColor > 255) {
            resultColor = 255;
        }

        // Devolver el valor obtenido
        return (byte) resultColor;

    }

}
