// Copyright © FunctionalHub.com 2018. All rights reserved.

package functional.transforms.reduce

import functional.transforms.Movie

fun getLargestRating(list: List<Movie>): Double = list.reduce { acc, movie ->
    if (acc.rating > movie.rating) acc
    else movie
}.rating
