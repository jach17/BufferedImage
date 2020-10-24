package ventana;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    BufferedImage imagenPrincipal;
    int width, height;
    Image imgRecorted;
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
        this.setBackground(Color.yellow);
        
        p = new JPanel();
        lbl = new JLabel();
        btnRotate = new JButton("Click to rotate");
        
        
        cargarImagen();
        

        btnRotate.setBounds(20, 400, 150, 40);
        btnRotate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clickeado();
            }
        });
        
        lbl.setIcon(im);
        lbl.setBounds(0, 0, 200, 200);
        lbl.setVisible(true);

        p.setBounds(200, 100, 200, 200);
        p.setBackground(Color.yellow);
        p.setLayout(null);
        
        
        p.add(lbl);
        this.add(btnRotate);
        this.add(p);
    }
    
    public void clickeado(){
        System.out.println("You clicked me!");
    }
//
//    @Override
//    public void paint(Graphics g) {
//        super.paint(g); //To change body of generated methods, choose Tools | Templates.
//        g.drawImage(imgRecorted, 10, 10, null);
//
//    }

    public void cargarImagen() {
        try {
            imagenPrincipal = ImageIO.read(new File("src/img/Compromiso.png"));
            width = imagenPrincipal.getWidth();
            height = imagenPrincipal.getHeight();
            imgRecorted = createImage(new FilteredImageSource(imagenPrincipal.getSource(), new CropImageFilter(250, 0, 200, 200)));
            im = new ImageIcon(imgRecorted);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
