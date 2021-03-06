// Copyright © FunctionalHub.com 2018. All rights reserved.

package functional.transforms.map

import functional.transforms.Movie

fun getMovies(movies: List<Movie>): Map<Int, String> =
        movies.map { movie ->
            movie.id to movie.title
        }.toMap()