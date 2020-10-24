package ventana;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import sun.awt.image.FileImageSource;

public class MostrarImagen extends JFrame {

    
    BufferedImage imagenPrincipal, imagenPrincipalRotada, imagenPrincipalRecortada;
    int width, height;
    Image imgRecorted, imgRotated;
    JPanel p;
    JLabel lbl;
    ImageIcon im;
    JButton btnRotate;

    public MostrarImagen() {
        this.pack();
        this.setBounds(0, 0, 600, 600);
        this.setTitle("Dividir imagen");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        p = new JPanel();
        lbl = new JLabel();
        btnRotate = new JButton("Set Image");

        btnRotate.setBounds(20, 400, 150, 40);
        btnRotate.addActionListener((ActionEvent ae) -> {
            clickeado();
        });

        p.setBounds(200, 100, 200, 200);
        p.setBackground(Color.yellow);
        p.setLayout(null);

        lbl.setBounds(0, 0, 200, 200);
        lbl.setVisible(true);

        p.add(lbl);
        this.add(btnRotate);
        this.add(p);
    }

    public void clickeado() {
        cargarImagen();
    }

    public Image recortarImagen(BufferedImage imagenPrincipal) {
        imgRecorted = createImage(new FilteredImageSource(imagenPrincipal.getSource(), new CropImageFilter(250, 0, 200, 200)));
        return imgRecorted; //<-Esta es la imagen que enviamos al lbl
    }

    public void cargarImagen() {
        try {
            imagenPrincipal = ImageIO.read(new File("src/img/Compromiso.png"));
            imgRecorted = recortarImagen(imagenPrincipal);

            im = new ImageIcon(imgRecorted);
            lbl.setIcon(im);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
