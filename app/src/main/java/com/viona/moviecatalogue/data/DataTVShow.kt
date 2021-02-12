package com.viona.moviecatalogue.data

import com.viona.moviecatalogue.models.TVShowEntity

object DataTVShow {

    fun generateDummyTVShow(): List<TVShowEntity> {

        val tvShow = ArrayList<TVShowEntity>()

        tvShow.add(
            TVShowEntity(
                "s1",
                "Planet Earth II",
                2016,
                "David Attenborough",
                "Wildlife documentary series with David Attenborough, beginning with a look at the remote islands which offer sanctuary to some of the planet's rarest creatures, to the beauty of cities, which are home to humans, and animals",
                "Documentary",
                1,
                9.5,
                "http://bit.ly/3sIYuou"
            )
        )

        tvShow.add(
            TVShowEntity(
                "s2",
                "Planet Earth",
                2006,
                "David Attenborough, Sigourney Weaver, Thomas Anguti Johnston",
                "Emmy Award-winning, 11 episodes, five years in the making, the most expensive nature documentary series ever commissioned by the BBC, and the first to be filmed in high definition.",
                "Documentary",
                13,
                9.4,
                "http://bit.ly/3c3ZBJJ"
            )
        )

        tvShow.add(
            TVShowEntity(
                "s3",
                "Band of Brothers",
                2001,
                "Scott Grimes, Damian Lewis, Ron Livingston",
                "The story of Easy Company of the U.S. Army 101st Airborne Division, and their mission in World War II Europe, from Operation Overlord, through V-J Day.",
                "Action, Drama, History",
                8,
                9.4,
                "http://bit.ly/2LWf6IU"
            )
        )

        tvShow.add(
            TVShowEntity(
                "s4",
                "Breaking Bad",
                2008,
                "Bryan Cranston, Aaron Paul, Anna Gunn",
                "A high school chemistry teacher diagnosed with inoperable lung cancer turns to manufacturing and selling methamphetamine in order to secure his family's future.",
                "Crime, Drama, Thriller",
                83,
                9.5,
                "http://bit.ly/3c5Ns74"
            )
        )

        tvShow.add(
            TVShowEntity(
                "s5",
                "Chernobyl",
                2019,
                " Jessie Buckley, Jared Harris, Stellan Skarsgård",
                "In April 1986, an explosion at the Chernobyl nuclear power plant in the Union of Soviet Socialist Republics becomes one of the world's worst man-made catastrophes.",
                "Drama, History, Thriller",
                28,
                9.4,
                "http://bit.ly/397qR82"
            )
        )

        tvShow.add(
            TVShowEntity(
                "s6",
                "Blue Planet II",
                2018,
                "David Attenborough, Peter Drost, Roger Horrocks",
                "David Attenborough returns to the world's oceans in this sequel to the acclaimed documentary filming rare and unusual creatures of the deep, as well as documenting the problems our oceans face.",
                "Documentary",
                2,
                9.3,
                "http://bit.ly/399ahVg"
            )
        )
        tvShow.add(
            TVShowEntity(
                "s7",
                "The Wire",
                2002,
                "Dominic West, Lance Reddick, Sonja Sohn",
                "The Baltimore drug scene, as seen through the eyes of drug dealers and law enforcement.",
                "Crime, Drama, Thriller",
                9,
                9.3,
                "http://bit.ly/3sQTK0g"
            )
        )
        tvShow.add(
            TVShowEntity(
                "s8",
                "Our Planet",
                2019,
                "David Attenborough",
                "Documentary series focusing on the breadth of the diversity of habitats around the world, from the remote Arctic wilderness and mysterious deep oceans to the vast landscapes of Africa and diverse jungles of South America.",
                "Documentary",
                7,
                9.3,
                "http://bit.ly/364Pvo4"
            )
        )
        tvShow.add(
            TVShowEntity(
                "s9",
                "Cosmos",
                2014,
                "Neil deGrasse Tyson, Stoney Emshwiller, Piotr Michael ",
                "An exploration of our discovery of the laws of nature and coordinates in space and time.",
                "Documentary",
                16,
                9.3,
                "http://bit.ly/3qLsHle"
            )
        )
        tvShow.add(
            TVShowEntity(
                "s10",
                "Game of Thrones",
                2011,
                "Emilia Clarke, Peter Dinklage, Kit Harington",
                "Nine noble families fight for control over the lands of Westeros, while an ancient enemy returns after being dormant for millennia.",
                " Action, Adventure, Drama",
                304,
                9.3,
                "http://bit.ly/3qOrOIj"
            )
        )
        tvShow.add(
            TVShowEntity(
                "s11",
                "Avatar: The Last Airbender",
                2005,
                " Zach Tyler, Mae Whitman, Jack De Sena",
                "In a war-torn world of elemental magic, a young boy reawakens to undertake a dangerous mystic quest to fulfill his destiny as the Avatar, and bring peace to the world.",
                "Animation, Action, Adventure",
                19,
                9.2,
                "http://bit.ly/3c0qWwh"
            )
        )
        tvShow.add(
            TVShowEntity(
                "s12",
                "Rick and Morty",
                2013,
                "Justin Roiland, Chris Parnell, Spencer Grammer",
                "An animated series that follows the exploits of a super scientist and his not-so-bright grandson.",
                "Animation, Adventure, Comedy",
                20,
                9.2,
                "http://bit.ly/364CWJn"
            )
        )

        return tvShow
    }
}