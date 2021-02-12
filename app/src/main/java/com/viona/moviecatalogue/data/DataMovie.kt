package com.viona.moviecatalogue.data

import com.viona.moviecatalogue.models.MovieEntity

object DataMovie {

    fun generateDummyMovie(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "m1",
                "Soul",
                2020,
                "A musician who has lost his passion for music is transported out of his body and must find his way back with the help of an infant soul learning about herself.",
                " Pete Docter, Kemp Powers",
                " Pete Docter",
                "Jamie Foxx, Tina Fey, Graham Norton",
                8.1,
                "https://www.imdb.com/title/tt2948372/mediaviewer/rm4113422337/"
            )
        )

        movies.add(
            MovieEntity(
                "m2",
                "Tenet",
                2020,
                "A musician who has lost his passion for music is transported out of his body and must find his way back with the help of an infant soul learning about herself.",
                "Christopher Nolan",
                "Christopher Nolan",
                "John David Washington, Robert Pattinson, Elizabeth Debicki",
                7.5,
                "http://bit.ly/3qud2q4"
            )
        )

        movies.add(
            MovieEntity(
                "m3",
                "Shadow in the Cloud",
                2020,
                "A female WWII pilot traveling with top secret documents on a B-17 Flying Fortress encounters an evil presence on board the flight.",
                "Roseanne Liang",
                "Max Landis, Roseanne Liang",
                "Chloë Grace Moretz, Nick Robinson, Beulah Koale",
                4.7,
                "http://bit.ly/3nPY243"
            )
        )

        movies.add(
            MovieEntity(
                "m4",
                "Avengers: Endgame",
                2019,
                "After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.",
                "Anthony Russo, Joe Russo",
                "Christopher Markus",
                " Robert Downey Jr., Chris Evans, Mark Ruffalo",
                8.4,
                "http://bit.ly/2XLGBHx"
            )
        )

        movies.add(
            MovieEntity(
                "m5",
                "Mulan",
                2020,
                "A young Chinese maiden disguises herself as a male warrior in order to save her father.",  " Pete Docter, Kemp Powers",
                "Niki Caro",
                "Yifei Liu, Donnie Yen, Li Gong",
                5.6,
                "http://bit.ly/38WoiWB"
            )
        )

        movies.add(
            MovieEntity(
                "m6",
                "Pieces of a Woman",
                2020,
                "When a young mother's home birth ends in unfathomable tragedy, she begins a year-long odyssey of mourning that fractures relationships with loved ones in this deeply personal story of a woman learning to live alongside her loss.",
                "Kornél Mundruczó",
                "Kata Wéber",
                "Vanessa Kirby, Shia LaBeouf, Ellen Burstyn",
                7.1,
                "http://bit.ly/2LY5Rrg"
            )
        )

        movies.add(
            MovieEntity(
                "m7",
                "The Shawshank Redemption",
                1994,
                "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                "Frank Darabont",
                "Stephen King",
                "Tim Robbins, Morgan Freeman, Bob Gunton",
                9.3,
                "http://bit.ly/2LH08WX"
            )
        )

        movies.add(
            MovieEntity(
                "m8",
                "Parasite",
                2019,
                "Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.",
                "Bong Joon Ho",
                "Bong Joon Ho",
                "Song Kang-Ho, Lee Sun-kyun, Cho Yeo-jeong",
                8.6,
                "http://bit.ly/38Rhx8g"
            )
        )

        movies.add(
            MovieEntity(
                "m9",
                "Sound of Metal",
                2019,
                "A heavy-metal drummer's life is thrown into freefall when he begins to lose his hearing.",
                "Darius Marder",
                "Darius Marder, Abraham Marder",
                "Riz Ahmed, Olivia Cooke, Paul Raci",
                7.8,
                "http://bit.ly/2XNuKIP"
            )
        )

        movies.add(
            MovieEntity(
                "m10",
                "The Lion King",
                2019,
                "After the murder of his father, a young lion prince flees his kingdom only to learn the true meaning of responsibility and bravery.",
                "Jon Favreau",
                "Jeff Nathanson",
                "Donald Glover, Beyoncé, Seth Rogen",
                6.9,
                "http://bit.ly/3nRYjDN"
            )
        )

        movies.add(
            MovieEntity(
                "m11",
                "Locked Down",
                2021,
                "A couple attempts a high-risk, high-stakes jewelry heist at a department store.",
                " Doug Liman",
                "Steven Knight",
                "Chiwetel Ejiofor, Anne Hathaway, Sonic",
                4.9,
                "http://bit.ly/38PDJiX"
            )
        )

        movies.add(
            MovieEntity(
                "m12",
                "Interstellar",
                2014,
                "A team of explorers travel through a wormhole in space in an attempt to ensure humanity's survival.",
                "Christopher Nolan",
                "Jonathan Nolan, Christopher Nolan",
                "Matthew McConaughey, Anne Hathaway, Jessica Chastain",
                8.6,
                "http://bit.ly/3ilKM66"
            )
        )

        return movies
    }
}