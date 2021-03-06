import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TilePanel extends JPanel{

    BufferedImage bufferedImage;

    /**
     * Üres Tile megjelenítéséért felelős.
     * @param tileView a tile nézete
     */
    public TilePanel(ElementView tileView) {

        String tileViewImage = tileView.getImage();

        File file = new File(System.getProperty("user.dir") + "/img/" + tileViewImage);
        try {
            this.bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        setMinimumSize(new Dimension(60,60));
    }
    /**
     * Foglalt Tile megjelenítéséért felelős.
     * @param tileView a tile nézete
     */
    public TilePanel(ElementView tileView, ElementView visitorView){

        String tileViewImage = tileView.getImage();
        String visitorViewImage = visitorView.getImage();

        File file = new File(System.getProperty("user.dir") + "/img/" + tileViewImage);
        try {
            this.bufferedImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + visitorViewImage),JLabel.CENTER);
        this.add(label);
        setMinimumSize(new Dimension(60,60));
    }

    /**
     *A komponens kirajzolása
     */
    @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bufferedImage != null) {
                g.drawImage(bufferedImage, 0, 0, this);
            }
        }

    /**
     * A megjelenítendő kép méreteit adja meg.
     * @return a megfeleő dimenzióval tér vissza
     */
    @Override
        public Dimension getPreferredSize() {
            if (bufferedImage != null) {
                int width = bufferedImage.getWidth();
                int height = bufferedImage.getHeight();
                return new Dimension(width , height );
            }
            return super.getPreferredSize();
        }
    }

