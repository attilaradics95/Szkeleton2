import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileView extends ElementView {
    public TileView(){
        image = "tile.jpg";
    }

    public JPanel draw( ){
        /*JLabel label = new JLabel("",new ImageIcon(System.getProperty("user.dir") + "/img/" + image),JLabel.CENTER);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(60,60));
        panel.add(label);
        return panel;*/
        return new TilePanel(this);
    }

    public JPanel draw(ElementView visitorView){
        return new TilePanel(this, visitorView);
    }
}
