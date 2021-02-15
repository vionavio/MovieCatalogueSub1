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
                "50.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F1.%20Planet%20Earth%20II.jpg?alt=media&token=166641f3-3611-4ff7-a324-f9df287e4cfa"
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
                "250.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F2.%20Planet%20Earth.jpg?alt=media&token=79c38917-892e-4269-90a0-c9df8b28453a"
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
                "200.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F3.%20Band%20of%20Brothers.jpg?alt=media&token=7cb1e938-f180-44d3-961e-2dbeec64e4fb"
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
                "500.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F4.%20Breaking%20Bad.jpg?alt=media&token=c11603f3-f120-4b55-979c-4b89a106024f"
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
                "150.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F5.%20Chernobyl.jpg?alt=media&token=8ffc8b16-54a9-4c26-8cde-571ba5fc3554"
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
                "70.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F6.%20Blue%20Planet%20II.jpg?alt=media&token=d74e986c-0ae5-4122-9db8-2e78f02b21f8"
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
                "120.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F7.%20The%20Wire.jpg?alt=media&token=e209c646-698c-4e5c-9b55-9dac63f7d8dc"
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
                "100.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F8.%20Our%20Planet.jpg?alt=media&token=d7b4623c-590b-4dea-8e17-aa59cd35d1d2"
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
                "140.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F9.%20Cosmos.jpg?alt=media&token=95330db5-6303-44fc-aa86-541e7da12640"
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
                "700.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F10.%20Game%20of%20Thrones.jpg?alt=media&token=0f1191f5-ff9a-4de1-acef-6c5132f9834b"
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
                "130.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F11.%20Avatar%20The%20Last%20Airbender.jpg?alt=media&token=bfa0da86-6849-4615-850f-351cb0aa4163"
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
                "90.000",
                "https://firebasestorage.googleapis.com/v0/b/moviecatalogue-43cde.appspot.com/o/TV%20Show%2F12.%20Rick%20and%20Morty.jpg?alt=media&token=4eda7529-d756-41b7-824b-b9a2e6ca5447"
            )
        )
        return tvShow
    }
}