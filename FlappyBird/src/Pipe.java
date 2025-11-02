import java.awt.*;

public class Pipe {

        private int posX;
        private  int posY;
        private int width;
        private int height;
        private Image image;
        private  int velocityX;
        boolean isUpper;
        boolean passed;

        public  Pipe(int posX, int posY, int width, int height, Image image, boolean isUpper) {
            this.posX = posX;
            this.posY = posY;
            this.width = width;
            this.height = height;
            this.image = image;
            this.velocityX = 0;
            this.passed = false;
            this.isUpper = isUpper; //menambahkan isUpper untuk menandai pipa (atas/bawah) yang akan dibutuhkan untuk menghitung skor
        }

    // Getter dan Setter untuk posisi
    public int getPosX() {
        return posX;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    // Getter dan Setter untuk velocityX
    public int getVelocityX() {
        return velocityX;
    }
    public void setVelocityX(int velocityX) {
        this.velocityX = velocityX;
    }

    public Image getImage() {
        return image;
    }

    public boolean isPassed() {
        return passed;
    }
    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public boolean isUpper() {
        return isUpper;
    }
}

