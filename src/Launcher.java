import game.Game;

/**
* Welcome to --Insert name of game here--! This game was
* created by 
* @author Grace Geng
* @author Ricky Khaing
* @author William Gao
*/
public class Launcher {
    /**
     * You know what this does
     * @param args  Command line args
     */
    
    public static void main(String[] args){
        Game game = new Game("Insert Name of Game Here", 900,595);
        game.start();
        // URL location = Launcher.class.getClass().getProtectionDomain().getCodeSource().getLocation();
        // System.out.println(location.getPath());
    }
}