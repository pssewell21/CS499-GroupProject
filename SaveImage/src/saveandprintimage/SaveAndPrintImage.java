package saveandprintimage;
 
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class SaveAndPrintImage {
  public static void main(String[] argv) throws Exception {
    int width = 100;
    int height = 100;

    BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    Graphics2D g2d = bufferedImage.createGraphics();

    g2d.setColor(Color.white);
    g2d.fillRect(0, 0, width, height);
    g2d.setColor(Color.black);
    g2d.fillOval(0, 0, width, height);

    g2d.dispose();
    RenderedImage rendImage = bufferedImage;

    File file = new File("newimage.jpeg");
    ImageIO.write(rendImage, "jpeg", file);
  }

}
