import javax.swing.JFrame;

// Min, Max, Close
class GameFrame extends JFrame {

    GameFrame(){
        setSize(GameConstants.GWIDTH, GameConstants.GHEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // cross button will exit the program from memory
        setResizable(false);   // resolution of frame shouldn't be changed
        setTitle("Game-2024");
        
        setLocationRelativeTo(null);
        add(new Board());
        setVisible(true);
    }
    public static void main(String[] args) {
        new GameFrame(); 
    }
}