import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

public class ImageAddLogo {
  static final File dir = new File("F:\\needsLogo");
  static final String[] EXTENSIONS = new String[] { "png", "jpg", "jpeg" };
  static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
    // @Override
    public boolean accept(final File dir, final String name) {
      for (final String ext : EXTENSIONS) {
        if (name.endsWith("." + ext)) {
          return (true);
        }
      }
      return (false);
    }
  };

  public static void main(String[] args) {

    if (dir.isDirectory()) { // make sure it's a directory
      for (final File f : dir.listFiles(IMAGE_FILTER)) {
        BufferedImage img = null;

        try {
          BufferedImage NRlogo = ImageIO.read(new File("E:\\logoSingleColor.png"));
          img = ImageIO.read(f);
          int width = img.getWidth();
          int height = img.getHeight();
          double logoLength = (80.0 / 1440) * Math.min(width, height);
          System.out.println(logoLength);

          // you probably want something more involved here
          // to display in your UI
          System.out.println("image: " + f.getName());
          System.out.println(" width : " + width);
          System.out.println(" height: " + height);
          System.out.println(" size  : " + f.length());

          Image tmp = NRlogo.getScaledInstance((int)logoLength, (int)logoLength, Image.SCALE_SMOOTH);
          BufferedImage Rlogo = new BufferedImage((int)logoLength, (int)logoLength, BufferedImage.TYPE_INT_ARGB);
          Graphics2D g2d = Rlogo.createGraphics();
          g2d.drawImage(tmp, 0, 0, null);
          g2d.dispose();

          Graphics2D g = img.createGraphics();
          g.drawImage(Rlogo, (int)(width - logoLength * (1.25)), (int)(height - logoLength * (1.25)), null);
          g.dispose();

          File outputfile = new File("F:\\ShotsLogoed\\" + f.getName());
          ImageIO.write(img, "png", outputfile);

          System.out.println("Logo added.");
        } catch (final IOException e) {
          // handle errors here
        }
      }
      System.out.println("Mission complete.");
    }
  }
}
