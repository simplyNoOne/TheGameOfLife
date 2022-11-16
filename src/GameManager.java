import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;

public class GameManager {
    private boolean paused;

    private Cell[][] grid;

    public GameManager(){
        paused = true;
        grid = new Cell[50][50];
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j< grid[i].length; j++)
                grid[i][j] = new Cell();
        }
    }

    int getGridSize(){return grid.length;}
    void setGamePaused(boolean paused){this.paused = paused;}
    boolean getGamePaused(){return paused;}

    public void randomize(){
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j< grid[i].length; j++)
                grid[i][j].initRandom();
        }
    }

    public void clear(){
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j< grid[i].length; j++)
                grid[i][j].reset();
        }
    }

    public void addButtons(JPanel window){
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j< grid[i].length; j++)
                grid[i][j].addButton(window);
        }
    }

    public void iteration(){
        calculate();
        update();
    }

    public void setCellNeighbors(){
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j< grid[i].length; j++){
                ArrayList<Cell>neighbors = new ArrayList<>();
                if(j != 0)
                    neighbors.add(grid[i][j-1]);
                if(j != grid[i].length - 1)
                    neighbors.add(grid[i][j+1]);
                if( i != 0){
                    neighbors.add(grid[i-1][j]);
                    if(j != 0)
                        neighbors.add(grid[i-1][j-1]);
                    if(j != grid[i].length - 1)
                        neighbors.add(grid[i-1][j+1]);
                }
                if( i != grid.length - 1){
                    neighbors.add(grid[i+1][j]);
                    if(j != 0)
                        neighbors.add(grid[i+1][j-1]);
                    if(j != grid[i+1].length -1 )
                        neighbors.add(grid[i+1][j+1]);
                }
                grid[i][j].setNeighbors(neighbors);
            }
        }
    }

    private void calculate(){
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j< grid[i].length; j++){
                grid[i][j].calculateSelf();
            }
        }
    }

    private void update(){
        for(int i = 0; i < grid.length; i++){
            for (int j = 0; j< grid[i].length; j++){
               grid[i][j].updateSelf();

            }
        }
    }
}
