package ventana;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MostrarImagen extends JFrame {

    BufferedImage imagenPrincipal, imagenPrincipalRotada, imagenPrincipalRecortada;
    int width, height;
    Image imgRecorted = null, imgRotated;
    JPanel p;
    JLabel lbl;
    ImageIcon im;
    JButton btnAddImage, btnRotateImage;
    int grados = 0;

    public MostrarImagen() {
        ///Inicializa el JFrame
        this.pack();
        this.setBounds(0, 0, 600, 600);
        this.setTitle("Dividir imagen");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Inicializa objetos a usar
        p = new JPanel();
        btnAddImage = new JButton("Cargar imagen");

        btnAddImage.setBounds(20, 400, 150, 40);
        btnAddImage.addActionListener((ActionEvent ae) -> {
            agregaImagen();
            rotateImage();
        });

        btnRotateImage = new JButton("Rotar Imagen");

        btnRotateImage.setBounds(400, 400, 150, 40);
        btnRotateImage.addActionListener((ActionEvent ae) -> {
            rotateImage();
        });

        p.setBounds(50, 50, 400, 400);
        p.setLayout(null);

        //Agrega objetos al frame
        this.add(btnAddImage);
        this.add(btnRotateImage);
        this.add(p);

    }

    public void agregaImagen() {
        cargarImagen();
    }

    public void rotateImage() {
        rotar(p.getGraphics());
    }

    public Image recortarImagen(BufferedImage imagenPrincipal) {
        imgRecorted = createImage(new FilteredImageSource(imagenPrincipal.getSource(), new CropImageFilter(250, 150, 200, 200)));
        return imgRecorted; //<-Esta es la imagen que enviamos al lbl
    }

    public void rotar(Graphics g) {
        if (imgRecorted != null) {
            if (grados < 360) {
                grados += 90;
            } else {
                grados = 90;
            }
            BufferedImage bufimRecorted = imgRecortedToBuffer(imgRecorted);
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(p.getSize().width / 2, p.getSize().height / 2);
            g2d.rotate(Math.toRadians(grados));
            g2d.drawImage(bufimRecorted, -bufimRecorted.getWidth() / 2, -bufimRecorted.getHeight() / 2, null);
        } else {
            JOptionPane.showMessageDialog(this, "Usted no ha cargado la imagen");
        }
    }

    public BufferedImage imgRecortedToBuffer(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bima = new BufferedImage(200, 200, imagenPrincipal.getType());
        Graphics2D bGr = bima.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bima;
    }

    public void cargarImagen() {
        try {
            imagenPrincipal = ImageIO.read(new File("src/img/Compromiso.png"));
            imgRecorted = recortarImagen(imagenPrincipal);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
