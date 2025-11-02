import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/*File Utama Flappy Bird*/
public class App extends JPanel {

    private Image backgroundImage;

    public App() {
        // Load background menu
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();

        // Gunakan layout agar tombol di tengah
        setLayout(new GridBagLayout());

        // Buat tombol
        JButton startButton = new JButton("Play Game");
        JButton exitButton = new JButton("Exit");

        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        exitButton.setFont(new Font("Arial", Font.BOLD, 20));

        // Styling tombol (opsional)
        startButton.setBackground(Color.ORANGE);
        exitButton.setBackground(Color.LIGHT_GRAY);

        // Atur posisi tombol
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridy = 0;
        add(startButton, gbc);
        gbc.gridy = 1;
        add(exitButton, gbc);

        // Aksi tombol Start
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Tutup menu dan buka game
                JFrame menuFrame = (JFrame) SwingUtilities.getWindowAncestor(App.this);
                menuFrame.dispose();

                Logic logic = new Logic();
                View view = new View(logic);
                logic.setView(view);

                JFrame gameFrame = new JFrame("Flappy Bird Game");
                gameFrame.add(view);
                gameFrame.pack();
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.setLocationRelativeTo(null);
                gameFrame.setResizable(false);
                gameFrame.setVisible(true);
            }
        });
        // Aksi tombol Exit
        exitButton.addActionListener(e -> System.exit(0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Gambar background menu
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public static void main(String[] args) {
        // Frame utama menu
        JFrame frame = new JFrame("Menu Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.add(new App());
        frame.setVisible(true);
    }
}
