// Copyright © FunctionalHub.com 2018. All rights reserved.

package functional.transforms.filtermap

import functional.transforms.Movie

fun getFiveRatingMoviesIds(movies: List<Movie>): List<Int> = movies.filter{it.rating == 5.0}.map { it.id }
