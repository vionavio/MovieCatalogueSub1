Keterangan test ada di file README


# Unit Testing View Model Movie Catalogue

1. Movie View Model Test
    Memanipulasi data ketika pemanggilan data movie di kelas movie repository
    memastikan metode kelas repository terpanggil
    
    Memastikan data movie tidak kosong
    Memastikan jumlah data movie sesuai yang diharapkan

2. TV Show View Model Test
    Memanipulasi data ketika pemanggilan data tv Show di kelas tv Show repository
    memastikan metode kelas repository terpanggil
    
    Memastikan data tv show tidak kosong
    Memastikan jumlah data tv Show sesuai yang diharapkan

3. Detail Movie View Model Test
    Memanipulasi data ketika pemanggilan data movie di kelas repository
    memastikan metode kelas repository terpanggil
    
    Memastikan detail data movie tidak kosong
    Memastikan detail data movie (id, title, original title, overview, poster path, backdrop path,
    original language, vote count, popularity, vote average) sesuai yang diharapkan

4. Detail TV Show View Model Test
    Memanipulasi data ketika pemanggilan data tv show di kelas repository
    memastikan metode kelas repository terpanggil
    
    Memastikan detail data tv show tidak kosong
    Memastikan detail data tv show (id, name, overview, poster path, backdrop path,
    original language, vote count, popularity, vote average, first air date) sesuai yang diharapkan

# Instrumental Testing Movie Catalogue

1. Test Tab
   a. Klik tab movies
      Memastikan fragment_movie tampil
   b. Klik tab tv show
      Memastikan fragment_tv_show tampil
   c. Klik tab favorite
      Memastikan fragment_favorite tampil
      
2. Menggukanan  Button kmbali
    - Klik tab favorites
    - Memastikan fragment_favorite dalam keadaan tampil
    - Klik tombol kembali (up button)
    - Memastikan teks tv shows dalam keadaan tampil
    - Memastikan fragment_tv_shows** dalam keadaan tampil
    - Klik tombol kembali 
    - Memastikan teks movies dalam keadaan tampil
    - Memastikan fragment_movie dalam keadaan tampil

3. Menampilkan data movie
    Memastikan rv_movie dalam keadaan tampil
    Gulir rv_movie ke posisi data terakhir
    Memastikan TextView untuk title tampil sesuai yang diharapkan

4. Menampilkan data detail movie
    Memberi tindakan klik pada data pertama di rv_movie
    Memastikan ImageView poster tampil
    Memastikan TextView untuk img_poster tampil sesuai yang diharapkan
    Memastikan TextView untuk backdrop_path tampil sesuai yang diharapkan
    Memastikan TextView untuk item_title tampil sesuai yang diharapkan
    Memastikan TextView untuk original title tampil sesuai yang diharapkan
    Memastikan TextView untuk detail_rate tampil sesuai yang diharapkan
    Memastikan TextView untuk description tampil sesuai yang diharapkan
    Memastikan TextView untuk popularity tampil sesuai yang diharapkan
    
    Memastikan TextView untuk sum_rate tampil sesuai yang diharapkan
    Memastikan TextView untuk date_release tampil sesuai yang diharapkan
    Memastikan Menu share dapat di klik

5. Menampilkan data tv show
    Memastikan tv show dalam keadaan tampil
    Gulir tv show ke posisi data terakhir
    Memastikan TextView untuk title tampil sesuai yang diharapkan
    
6. Menampilkan data detail tv show
    Tindakan klik pada data pertama di rv_tv_show
    Memastikan ImageView pada poster tampil
    Memastikan TextView untuk title/name tampil sesuai yang diharapkan
    Memastikan TextView untuk original name tampil sesuai yang diharapkan
    Memastikan TextView untuk rating tampil sesuai yang diharapkan
    Memastikan TextView untuk episode tampil sesuai yang diharapkan
    Memastikan TextView untuk season tampil sesuai yang diharapkan
    Memastikan TextView untuk spoken languages tampil sesuai yang diharapkan
    Memastikan TextView untuk genres tampil sesuai yang diharapkan
    Memastikan TextView untuk description tampil sesuai yang diharapkan
    Memastikan TextView untuk status tampil sesuai yang diharapkan
    Memastikan TextView untuk first air date tampil sesuai yang diharapkan
    Memastikan TextView untuk popularity tampil sesuai yang diharapkan
    Memastikan Menu share dapat di klik