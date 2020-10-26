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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MostrarImagen extends JFrame {

    BufferedImage imagenPrincipal, imagenPrincipalRotada, imagenPrincipalRecortada;
    int width, height;
    Image imgRecorted = null, imgRotated;
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

        p.setBounds(100,100, 200,200);
        p.setLayout(null);

        lbl.setBounds(0, 0, 200, 200);
        lbl.setVisible(true);

        p.add(lbl);
        this.add(btnRotate);
        this.add(p);
        
    }

    public void clickeado() {
        cargarImagen();
        pintar(p.getGraphics());
    }

    public Image recortarImagen(BufferedImage imagenPrincipal) {
        imgRecorted = createImage(new FilteredImageSource(imagenPrincipal.getSource(), new CropImageFilter(250, 0, 200, 200)));
        return imgRecorted; //<-Esta es la imagen que enviamos al lbl
    }

    int grados = 0;
    
    public void pintar(Graphics g) {

        if (imgRecorted != null) {
            if(grados<360){
                grados +=90;
            }else{
                grados=90;
            }
            BufferedImage bufimRecorted = imgRecortedToBuffer(imgRecorted);
            Graphics2D g2d = (Graphics2D) g;
            
            g2d.translate(p.getSize().width/2, p.getSize().height/2);
            g2d.rotate(Math.toRadians(grados));
            g2d.drawImage(bufimRecorted, -bufimRecorted.getWidth() / 2, -bufimRecorted.getHeight() / 2, null);

        } else {
            System.out.println("imgRecorted not init");
        }
    }

    public BufferedImage imgRecortedToBuffer(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        // Create a buffered image with transparency
        BufferedImage bimage = new BufferedImage(200, 200, imagenPrincipal.getType());

        // Draw the image on to the buffered image
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();

        // Return the buffered image
        return bimage;
    }

    public void cargarImagen() {
        try {
            imagenPrincipal = ImageIO.read(new File("src/img/Compromiso.png"));
            imgRecorted = recortarImagen(imagenPrincipal);
            
//
//            im = new ImageIcon(imgRecorted);
//            lbl.setIcon(im);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
