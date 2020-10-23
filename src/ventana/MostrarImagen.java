package ventana;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MostrarImagen extends JFrame {

    BufferedImage imagenPrincipal;
    int width, height;
    Image imgRecorted;
    JPanel p;

    public MostrarImagen() {
        this.pack();
        this.setBounds(0, 0, 600, 600);
        this.setTitle("Dividir imagen");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cargarImagen();
        p = new JPanel();
        p.setBounds(0, 0, imagenPrincipal.getWidth(), imagenPrincipal.getHeight());

        this.add(p);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(imgRecorted, 0, 0, null);

    }

    public void cargarImagen() {
        try {
            imagenPrincipal = ImageIO.read(new File("src/img/Compromiso.png"));
            width = imagenPrincipal.getWidth();
            height = imagenPrincipal.getHeight();
            imgRecorted = createImage(new FilteredImageSource(imagenPrincipal.getSource(), new CropImageFilter((width / 4) * 2, 0, width / 4, height / 2)));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}

