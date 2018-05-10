import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class GamePanel extends JPanel implements MouseListener {

    //region Konstruktor
    public GamePanel(ATile[][] tiles, Visitor[][] visitors){

        //feliratkozunk a JPanel egér eseményfigyelőjére
        addMouseListener(this);

        int numberOfRows = tiles.length;
        int numberOfColumns = tiles[0].length;


        //region Ablak felépítése

        this.setLayout(new GridLayout(numberOfRows,numberOfColumns,0,-5));
        for(int row = 0; row < numberOfRows; row++){
            for(int col = 0; col< numberOfColumns; col++){
                ElementView tileView = tiles[row][col].getView();
                if(tiles[row][col].getVisitor() == null)
                this.add(tileView.draw());
                else{
                    ElementView visitorView = tiles[row][col].getVisitor().getView();
                    this.add(tileView.draw(visitorView));

                }
            }
        }


        //endregion

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MouseHandler mouseHandler = new MouseHandler();
        mouseHandler.Click(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    //endregion
}
