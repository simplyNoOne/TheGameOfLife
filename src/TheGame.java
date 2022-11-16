
public class TheGame {


    public static void main(String [] args){
        GameManager manager = new GameManager();
        GameWindow window = new GameWindow(manager);
        manager.setCellNeighbors();
        manager.addButtons(window.getGrid());
        window.buttonsAdded();

        while(true){
            if(!manager.getGamePaused()) {
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    System.out.println("Something went wrong");
                }
                manager.iteration();
                window.update();
            }
            else
                System.out.print(".");

        }
    }
}
