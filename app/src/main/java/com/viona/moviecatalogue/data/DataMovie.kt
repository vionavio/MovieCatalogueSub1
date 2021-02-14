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
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F1.%20Soul%202020.jpg?alt=media&token=5b30ee71-6db2-494b-8b8f-b7364458b4c6",
                "89%",
                1447,
                "1h 40min",
                "54.000"

            )
        )

        movies.add(
            MovieEntity(
                "m2",
                "Tenet",
                2020,
                "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of international espionage on a mission that will unfold in something beyond real time.",
                "Christopher Nolan",
                "Christopher Nolan",
                "John David Washington, Robert Pattinson, Elizabeth Debicki",
                7.5,
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F2.%20Tenet%202020.jpg?alt=media&token=cd15f1e5-9984-4db0-ac8f-5a81ddccf643",
                "80%",
                4344,
                "2h 30min",
                "74.000"
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
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F3.%20Shadow%20In%20the%20Cloud%202019.jpg?alt=media&token=fe04ede3-646e-40d3-80fc-373d31b277a4",
                "66%",
                500,
                "1h 23min",
                "50.000"
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
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F4.%20Avenger%202019.jpg?alt=media&token=87709de4-95d6-47ec-8bf6-fff45883015f",
                "94%",
                9020,
                "3h 1min",
                "127.000"
            )
        )

        movies.add(
            MovieEntity(
                "m5",
                "Mulan",
                2020,
                "A young Chinese maiden disguises herself as a male warrior in order to save her father.",
                " Pete Docter, Kemp Powers",
                "Niki Caro",
                "Yifei Liu, Donnie Yen, Li Gong",
                5.6,
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F5.%20Mulan%202020.jpg?alt=media&token=00167f4c-ce7a-45b7-be99-26a2e8c14cf1",
                "66%",
                2797,
                "1h 55min",
                "47.000"
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
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F6.%20Pieces%20of%20a%20Woman%202020.jpg?alt=media&token=fd428a3d-0b76-4d2b-9834-48c6bd721e91",
                "66%",
                263,
                "2h 6min",
                "75.000"
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
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F7.%20The%20Shawshank%20Redemption%201994.jpg?alt=media&token=73668134-713d-47ae-9279-3bab704e130b",
                "80%",
                8724,
                "2h 22min",
                "85.000"
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
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F8.%20Parasite%202019.jpg?alt=media&token=653ebc7e-29ed-4fd3-905b-e70a73b25156",
                "96%",
                2986,
                "2h 12min",
                "90.000"
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
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F9.%20Sound%20of%20Metal%202019.jpg?alt=media&token=6da60187-2622-4d66-b5db-9301f52886df",
                "81%",
                221,
                "2h",
                "70.000"
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
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F10.%20The%20Lion%20King%202019.jpg?alt=media&token=9e74d946-8175-4dce-82ad-2bfeab7c1075",
                "55%",
                2988,
                "1h 58min",
                "80.000"
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
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F11.%20Locked%20Down%202021.jpg?alt=media&token=c00e2ec2-6974-4db2-9837-b8df2a3a0391",
                "54%",
                134,
                "1h 58min",
                "40.000"
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
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/Movie%2F12.%20Interstellar%202014.jpg?alt=media&token=9868d2a2-3262-449a-8f28-c64cfebf38d4",
                "74%",
                4370,
                "2h 49min",
                "56.000"
            )
        )

        return movies
    }
}