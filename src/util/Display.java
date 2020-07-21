package util;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

/**
 * A display that encapsulates a JFrame to draw objects on
 */
public class Display {
    private JFrame frame;
    private String title;
    private int width, height;
    private Canvas canvas;

    public Display(String title, int width, int height){
        this.title = title;
        this.width = width;
        this.height = height;
        createDisplay();
    }

    private void createDisplay(){
        frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width, height));
        canvas.setMaximumSize(new Dimension(width, height));
        canvas.setMinimumSize(new Dimension(width, height));
        //canvas.setFocusable(false);
        
        frame.add(canvas);
        frame.pack();
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public JFrame getFrame(){
        return frame;
    }

}