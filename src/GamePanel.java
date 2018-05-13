import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

public class GamePanel extends JPanel implements KeyListener, MouseListener {

    //region Attribútumok
    Controller controller;
    Game game;
    MouseHandler mouseHandler;
    KeyHandler keyHandler;
    //endregion

    //region Konstruktor
    public GamePanel(){
        mouseHandler = new MouseHandler();
        keyHandler = new KeyHandler();
        game = Game.getInstance();
        this.setFocusable(true);
        this.requestFocusInWindow(true);

        //feliratkozunk a JPanel egér és billentyű eseményfigyelőjére
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        drawAll();

    }
    //endregion

    //region A pálya kirajzolását végző függvény
    public void drawAll(){
        ATile[][] tiles = game.getMap();

        int numberOfRows = tiles.length;
        int numberOfColumns = tiles[0].length;

        this.setLayout(new GridLayout(numberOfRows,numberOfColumns,0,0));
        for(int row = 0; row < numberOfRows; row++){
            for(int col = 0; col< numberOfColumns; col++){
                ElementView tileView = tiles[row][col].getView();
                //Ha üres a mező akkor csak a mezőt rajzoljuk ki
                if(tiles[row][col].getVisitor() == null)
                    this.add(tileView.draw());
                    //Ha nem üres, akkor azt is kirajzoljuk, ami rajta van
                else{
                    ElementView visitorView = tiles[row][col].getVisitor().getView();
                    this.add(tileView.draw(visitorView));

                }
            }
        }
    }
    //endregion

    //region Egér események kezelése
    @Override
    public void mouseClicked(MouseEvent e) {
        mouseHandler.Click(e);
        this.removeAll();
        this.updateUI();
        drawAll();
        repaint();
        revalidate();
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

    //region Billentyű események kezelése
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed lefutott");
        keyHandler.Control(e);
        this.removeAll();
        this.updateUI();
        drawAll();
        repaint();
        revalidate();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //endregion
}
