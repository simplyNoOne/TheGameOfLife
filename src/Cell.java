import javax.swing.JPanel;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Cell implements ActionListener {

    private Button button;
    private boolean alive;
    private boolean nextState;
    private Cell[] neighbors;

    public Cell(){
        alive = false;
        nextState = false;
    }

    public boolean getState(){return alive;}

    public void setNeighbors(ArrayList<Cell> neighbors){
        this.neighbors = new Cell[neighbors.size()];
        this.neighbors = neighbors.toArray(this.neighbors);
    }

    public void initRandom(){
        nextState = ((int)(Math.random()*2)%2 == 0);
        updateSelf();
    }

    public void reset(){
        nextState = false;
        updateSelf();
    }

    public void calculateSelf(){
        int counter = 0;
        for(Cell n : neighbors){
            if(n.getState())
                counter++;
        }
        if((counter == 2 && alive) || counter == 3)
            nextState = true;
        else
            nextState = false;
    }

    public void updateSelf(){
        alive = nextState;
        if(alive)
            button.setBackground(Color.BLACK);
        else
            button.setBackground(Color.WHITE);
    }

    private void changeState(){
        nextState = !alive;
        updateSelf();
    }

    public void addButton(JPanel grid){
        button = new Button();
        grid.add(button);
        button.addActionListener(this);
    }

        @Override
        public void actionPerformed(ActionEvent e) {
            changeState();
        }
}
