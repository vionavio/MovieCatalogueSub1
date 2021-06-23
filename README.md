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
    Memastikan ImageView untuk img_poster tampil sesuai yang diharapkan
    Memastikan ImageView untuk backdrop_path tampil sesuai yang diharapkan
    Memastikan TextView untuk item_title tampil sesuai yang diharapkan
    Memastikan TextView untuk original title tampil sesuai yang diharapkan
    Memastikan TextView untuk detail_rate tampil sesuai yang diharapkan
    Memastikan TextView untuk description tampil sesuai yang diharapkan
    Memastikan TextView untuk popularity tampil sesuai yang diharapkan
    
    Memastikan TextView untuk sum_rate tampil sesuai yang diharapkan
    Memastikan TextView untuk date_release tampil sesuai yang diharapkan
    Memastikan Menu collapse action bar dapat di klik
    
5. Menampilkan data favorite movie
    Memberi tindakan klik pada tab favorites
    Membaca jumlah items pada tv_movie_count 
    Memberi tindakan klik pada tab movies
    Memastikan RecyclerView rv_movie dalam keadaan tampil sesuai yang diharapkan
    Memberi tindakan klik pada item movie pertama pada rv_movie
    Memberi tindakan klik pada tombol favorite pada action_favorites
    Memberi tindakan klik tombol kembali
    Memberi tindakan klik pada tab favorites
    Memastikan TextView pada tv_movie_count sesuai yang diharapkan "(number) items"
    Memberi tindakan klik pada RecyclerView index 0 pada fragment favorites
    Memastikan TextView untuk title sesuai yang diharapkan
    Memberi tindakan klik pada index 0 film tersebut
    
    Memastikan ImageView untuk img_poster tampil sesuai yang diharapkan
    Memastikan ImageView untuk backdrop_path tampil sesuai yang diharapkan
    Memastikan TextView untuk item_title tampil sesuai yang diharapkan
    Memastikan TextView untuk original title tampil sesuai yang diharapkan      Memastikan TextView untuk detail_rate tampil sesuai yang diharapkan
    Memastikan TextView untuk backdrop_path tampil sesuai yang diharapkan
    Memastikan TextView untuk description tampil sesuai yang diharapkan
    Memastikan TextView untuk popularity tampil sesuai yang diharapkan
        
    Memastikan TextView untuk sum_rate tampil sesuai yang diharapkan
    Memastikan TextView untuk date_release tampil sesuai yang diharapkan
    Memastikan Menu collapse action bar dapat di klik
    
    Memberi tindakan klik pada tombol favorite untuk action_favorite
    Memberi tindakan klik tombol kembali
    Memberi tindakan klik tombol kembali
    Memastikan TextView pada tv_movie_count sesuai yang diharapkan "(number) items"

6. Menampilkan data tv show
    Memastikan tv show dalam keadaan tampil
    Gulir tv show ke posisi data terakhir
    Memastikan TextView untuk title tampil sesuai yang diharapkan
    
7. Menampilkan data detail tv show
    Tindakan klik pada data pertama di rv_tv_show
    Memastikan ImageView untuk img_poster  tampil
    Memastikan ImageView pada tv_backdrop_path tampil
    Memastikan TextView untuk tv_show_title padatitle/name tampil sesuai yang diharapkan
    Memastikan TextView untuk tv_original_name pada original name tampil sesuai yang diharapkan
    Memastikan TextView untuk tv_desc pada description tampil sesuai yang diharapkan
    Memastikan TextView untuk tv_air_date pada first air date tampil sesuai yang diharapkan
    Memastikan TextView untuk tv_language pada language tampil sesuai yang diharapkan
    Memastikan Menu collapse action bar dapat di klik
    
8. Menampilkan data favorite movie
    Memberi tindakan klik pada tab favorites
    Membaca jumlah items pada tv_tv_show_count 
    Memberi tindakan klik pada tab tv shows
    Memastikan RecyclerView rv_tv_show dalam keadaan tampil sesuai yang diharapkan
    Memberi tindakan klik pada item tv show pertama pada rv_tv_show
    Memberi tindakan klik pada tombol favorite pada action_favorites
    Memberi tindakan klik tombol kembali
    Memberi tindakan klik pada tab favorites
    Memastikan TextView pada tv_tv_show_count sesuai yang diharapkan "(number) items"
    Memberi tindakan klik pada RecyclerView index 0 pada fragment favorites
    Memastikan TextView untuk title sesuai yang diharapkan
    Memberi tindakan klik pada index 0 tv show tersebut
    
    Memastikan ImageView untuk img_poster  tampil
    Memastikan ImageView pada tv_backdrop_path tampil
    Memastikan TextView untuk tv_show_title padatitle/name tampil sesuai yang diharapkan
    Memastikan TextView untuk tv_original_name pada original name tampil sesuai yang diharapkan
    Memastikan TextView untuk tv_desc pada description tampil sesuai yang diharapkan
    Memastikan TextView untuk tv_air_date pada first air date tampil sesuai yang diharapkan
    Memastikan TextView untuk tv_language pada language tampil sesuai yang diharapkan     Memastikan Menu collapse action bar dapat di klik
    
    Memberi tindakan klik pada tombol favorite untuk action_favorite
    Memberi tindakan klik tombol kembali
    Memberi tindakan klik tombol kembali
    Memastikan TextView pada tv_tv_show_count sesuai yang diharapkan "(number) items"
