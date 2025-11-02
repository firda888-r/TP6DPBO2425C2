import javax.swing .*; //digunakan untuk komponen GUI seperti timer/image icon
import java.awt .*;  //digunakan untuk menggambar dan membuat grafik pada layar (image/graphics)
import java.awt.event.ActionListener; //
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener; //KeyListener supaya bisa merespon aksi yang dilakukan user (setiap menekan spasi untuk terbang)
import java.lang.reflect.Array;
import java.sql.Time;
import java.util.ArrayList; //digunakan untuk  menyimpan objek pipe yang akan ditampilkan

//logic, otak utama dari game flappybird
//implementasi actrionliFstener supaya dapat merespon/mendengar kejadian (saat timer berjalan dan saat ada aksi misal tombol ditekan)
public class Logic implements ActionListener, KeyListener {
    //Atribut utama
    int frameWidth = 360;
    int frameHeight = 640;
    int playerStartPosX = frameWidth / 2; //lebar dan tinggi ukuran layar game
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;

    //tambahkan atribut posisi dan ukuran pipa
    int pipeStartPosX = frameWidth;
    int PipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    int score = 0; //untuk score
    private JLabel scoreLabel;
    View view;
    Image birdimage;
    Player player;
    Image lowerPipeImage;
    Image upperPipeImage;
    ArrayList<Pipe>pipes;

    //menambahkan atribut time
    Timer gameLoop;
    Timer pipesCooldown;
    float gravity = 0.2f;
    int pipesVelocityX = -2;
    boolean gameOver = false;

    public Logic() {
        birdimage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdimage);

        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        pipes = new ArrayList<Pipe>();

        pipesCooldown = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.println("Pipa");
                placePipes();
            }
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    //setter view
    public void setView(View view) { this.view = view;}

    public Player getPlayer() { return player;}

    public  ArrayList<Pipe>getPipes(){return pipes;}

    public void placePipes() {
        int randomPosY = (int) (PipeStartPosY - pipeHeight / 4 - Math.random() * (pipeHeight / 2));
        int openingSpace = frameHeight / 4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage, true);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight,
                lowerPipeImage, false);
        pipes.add (lowerPipe);
    }

    public void move() {
        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY((int)(player.getPosY() + player.getVelocityY()));
        player.setPosY(Math.max(player.getPosY(), 0));

        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipesVelocityX);
        }
    }

    public void checkCollision() {
        Rectangle birdRect = new Rectangle(player.getPosX(), player.getPosY(), playerWidth, playerHeight);

        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipeWidth, pipeHeight);

            if (birdRect.intersects(pipeRect)) {
                gameOver = true;
                gameLoop.stop(); // hentikan permainan
                pipesCooldown.stop();
                System.out.println("Game Over: Menabrak pipa!");
            }
            // kalau burung melewati pipa
            if (!pipe.isUpper() && pipe.getPosX() + pipe.getWidth() < player.getPosX() && !pipe.isPassed()) {
                score++;
                pipe.setPassed(true);
            }

            if (player.getPosY() > frameHeight - player.getHeight()) {
                gameOver = true;
            }
        }

        // Cek jika jatuh ke bawah layar
        if (player.getPosY() + playerHeight >= frameHeight) {
            gameOver = true;
            gameLoop.stop();
            pipesCooldown.stop();
            System.out.println("Game Over: Jatuh ke bawah!");
        }
    }

    public void restartGame() {
        //restart score
        score = 0;
        if (view != null) {
            view.updateScore(score); //mengupdate skore supaya mudai dari 0
        }

        // atur ulang status
        gameOver = false;

        // reset posisi burung ke tengah layar
        player.setPosX(frameWidth / 2);
        player.setPosY(frameHeight / 2);
        player.setVelocityY(0);

        pipes.clear(); // hapus semua pipa lama
        pipesCooldown.start(); // mulai ulang timer pipa & game loop
        gameLoop.start();

        System.out.println("Game dimulai ulang!");
    }

    @Override
    public  void actionPerformed(ActionEvent e) {
        if (!gameOver) {
            move();
            checkCollision();

            if (view != null) {
                view.updateScore(score);
                view.repaint();
            }
        }
    }

    @Override
    public  void keyTyped(KeyEvent e) {}
    public  void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-4); //ini untuk menyesuaikan ketinggian burung saat terbang (semakin besar angkanya semakin tinggi)
        }

        //jika menekan event R maka gameover
        if(e.getKeyCode() == KeyEvent.VK_R) {
            if(gameOver) {
                restartGame();
            }
        }
    }
    public  void keyReleased(KeyEvent e) {}
}