package ventana;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MostrarImagen extends JFrame {
    BufferedImage imagenPrincipal, bufimRecorted;
    Image imgRecorted;
    JPanel p;
    JButton btnAddImage, btnRotateImage;
    int grados = 0;

    public MostrarImagen() {
        ///Inicializa el JFrame
        this.setBounds(0, 0, 600, 600);
        this.setTitle("Dividir imagen");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Inicializa objetos a usa
        p = new JPanel();
        btnAddImage = new JButton("Cargar imagen");

        btnAddImage.setBounds(20, 500, 150, 40);
        btnAddImage.addActionListener((ActionEvent ae) -> {
            cargarImagen();
        });

        btnRotateImage = new JButton("Rotar Imagen");

        btnRotateImage.setBounds(400, 500, 150, 40);
        btnRotateImage.addActionListener((ActionEvent ae) -> {
            rotar(p.getGraphics());
        });

        p.setBounds(this.getWidth() / 4, this.getHeight() / 4, 250, 250);
        p.setLayout(null);
        p.setBackground(Color.BLACK);

        //Agrega objetos al frame
        this.add(btnAddImage);
        this.add(btnRotateImage);
        this.add(p);

    }

    public void cargarImagen() {
        try {
            imagenPrincipal = ImageIO.read(new File("src/img/Compromiso.png"));
            imgRecorted = recortarImagen(imagenPrincipal);
            dibujarBuffered(0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dibujarBuffered(int grados) {
        if (bufimRecorted != null) {
            Graphics2D g2d = (Graphics2D) p.getGraphics();
            g2d.translate(p.getSize().width / 2, p.getSize().height / 2);
            g2d.rotate(Math.toRadians(grados));
            g2d.drawImage(bufimRecorted, -bufimRecorted.getWidth() / 2, -bufimRecorted.getHeight() / 2, null);
        } else {
            JOptionPane.showMessageDialog(this, "Buffered no inicializado...");
        }
    }

    public Image recortarImagen(BufferedImage imagenPrincipal) {
        imgRecorted = createImage(new FilteredImageSource(imagenPrincipal.getSource(), new CropImageFilter(250, 150, 200, 200)));
        bufimRecorted = imgRecortedToBuffer(imgRecorted);
        return imgRecorted;
    }

    public void rotar(Graphics g) {
        if (imgRecorted != null) {
            if (grados < 360) {
                grados += 90;
            } else {
                grados = 90;
            }
            dibujarBuffered(grados);
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

}
