import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;


public class GameWindow extends JFrame implements ActionListener {

    GameManager manager;
    JSplitPane divider;

    JPanel menu;
    JPanel grid;

    JButton startBtn;
    JButton stopBtn;
    JButton randomBtn;
    JButton clearBtn;


    ArrayList<JButton> buttons;

    public GameWindow(GameManager m){
        manager = m;
        this.setTitle("THE GAME OF LIFE");

        divider = new JSplitPane();
        menu = new JPanel();
        grid = new JPanel();
        startBtn = new JButton("PLAY");
        stopBtn = new JButton("PAUSE");
        randomBtn = new JButton("RANDOMIZE");
        clearBtn = new JButton("CLEAR");

        buttons = new ArrayList<>();
        buttons.add(startBtn);
        buttons.add(stopBtn);
        buttons.add(randomBtn);
        buttons.add(clearBtn);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000,1000);
        setResizable(false);
        getContentPane().setLayout(new GridLayout());
        getContentPane().add(divider);

        divider.setOrientation(JSplitPane.VERTICAL_SPLIT);  // we want it to split the window verticaly
        divider.setDividerLocation(50);                    // the initial position of the divider is 200 (our window is 400 pixels high)
        divider.setTopComponent(menu);                  // at the top we want our "topPanel"
        divider.setBottomComponent(grid);

        menu.setLayout(new GridLayout());
        for(JButton b : buttons){
            menu.add(b);
            b.addActionListener(this);
            b.setBackground(Color.white);
            b.setForeground(Color.black);
            b.setFocusable(false);

        }
        grid.setLayout(new GridLayout(manager.getGridSize(), manager.getGridSize()));

    }

    public JPanel getGrid(){return grid;}

    public void buttonsAdded(){

        setVisible(true);
    }
    public void update(){
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(JButton b : buttons){
            b.setBackground(Color.white);
            b.setForeground(Color.black);
        }
        if(e.getSource() instanceof JButton) {
            ((JButton) e.getSource()).setBackground(Color.black);
            ((JButton) e.getSource()).setForeground(Color.white);
        }

        if (e.getSource() == startBtn) {
            manager.setGamePaused(false);
        } else if (e.getSource() == stopBtn) {
            manager.setGamePaused(true);
        } else if (e.getSource() == randomBtn) {
            manager.setGamePaused(true);
            manager.randomize();
            repaint();
        }else if (e.getSource() == clearBtn) {
            manager.setGamePaused(true);
            manager.clear();
            repaint();
        }
    }
}
