// Copyright © FunctionalHub.com 2018. All rights reserved.

package functional.transforms.zip

import functional.transforms.Cast
import functional.transforms.Movie

fun getFirstActorMap(movies: List<Movie>, casts: List<Cast>): Map<String, String?> =
        movies.zip(casts)
                .map { (movie, cast) ->
                    movie.title to cast.people.first().name.substringBefore(" ")
                }.toMap()