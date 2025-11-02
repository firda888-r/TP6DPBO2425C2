import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class View extends JPanel{
    int width = 360;
    int height = 640;
    int score;

    private  Logic logic; //tambahkan atribut ini
    private JLabel scoreLabel; //deklarasi jpanel papan score

    //construktor
    public View(Logic logic) {
        this.logic = logic;// memasukan instance ke atribut
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.cyan);
        setFocusable(true);
        addKeyListener(logic);
        setLayout(null); //set bounds

        //score board
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(10, 10, 150, 30);
        add(scoreLabel);
    }
    //methode untuk mengupdate jumlah skor
    public void updateScore(int score) {
        scoreLabel.setText("Score: " + score);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
        if (logic.gameOver) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("GAME OVER", 80, 300);
        }
    }

    public  void draw (Graphics g) {
        Player player = logic.getPlayer();
        if (player != null) {
            g.drawImage(player.getImage(), player.getPosX(), player.getPosY(),
                    player.getWidth(), player.getHeight(), null);
        }

        ArrayList<Pipe> pipes = logic.getPipes();
        if(pipes != null) {
            for (int i = 0; i < pipes.size(); i++) {
                Pipe pipe = pipes.get(i);
                g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(),
                        pipe.getWidth(), pipe.getHeight(), null);
            }
        }

    }

}

