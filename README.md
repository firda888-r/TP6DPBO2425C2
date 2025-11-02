**Janji**

Saya Firda Ridzki Utami dengan NIM 2401626 mengerjakan TP6 dalam Praktikum mata kuliah DPBO untuk keberkahannya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aaminn

Secara keseluruhan, alur program berjalan mulai dari tampilan menu utama, lalu masuk ke permainan utama saat pemain menekan tombol “Start Game”. Saat permainan berlangsung, burung akan bergerak karena pengaruh gravitasi dan dapat dikendalikan dengan tombol spasi untuk terbang. Skor pemain akan bertambah setiap kali berhasil melewati pipa, dan permainan akan berhenti jika burung menabrak pipa atau jatuh ke bawah. Setelah Game Over, pemain dapat menekan tombol “R” untuk memulai kembali permainan dari awal.

Program Flappy Bird ini terdiri dari beberapa komponen utama yang saling berkaitan. pada awal permainan, program akan menampilkan tampilan menu utama yang dibuat menggunakan JFrame dan JPanel pada file App.java. Menu ini memiliki dua tombol, yaitu tombol Start Game untuk memulai permainan dan Exit untuk keluar dari program. Ketika tombol Start Game ditekan, menu akan ditutup dan permainan utama akan dimulai dengan memanggil kelas Logic dan View, lalu ditampilkan pada jendela baru berukuran 360x640 piksel.

Bagian inti pada program ini terdapat di dalam kelas App.java yang berfungsi sebagai otak utama fame FlappyBird ini. Didalamnya terdapat sistem time yang menggunakan ActionListener, fitur Game over yang diimplementasikan pada metode checkColliosion yang berfungsi mengecek apakah bird (player) menabrak pipa atau terjatuh yang menandakan gameover. Setelah permainan berhenti, pemain dapat memulai ulang permainan dengan menekan tombol R, yang diatur pada metode keyPressed(). Ketika tombol tersebut ditekan dan status game sedang gameOver, maka metode restartGame() dijalankan untuk mengatur ulang posisi burung, menghapus pipa lama, mengatur skor kembali ke nol, dan menjalankan ulang timer.

Tampilan visual permainan diatur pada kelas View.java. Kelas ini menggunakan JPanel untuk menggambar elemen seperti burung dan pipa menggunakan metode paintComponent(). Selain itu, View.java juga menambahkan sebuah JLabel di pojok kiri atas untuk menampilkan skor dengan teks “Score: 0”.

Dokumentasi saat program game FlappyBird dijalankan

![play](https://github.com/user-attachments/assets/c8d7b05c-595d-4b77-aa43-3758b6d377f5)

**Game over**

![game over](https://github.com/user-attachments/assets/63678d00-d2b7-4a86-928c-7cd3a9e5e9ca)


**Menu**

![menu](https://github.com/user-attachments/assets/93965c8c-0268-403e-9e4f-9434f0802497)
