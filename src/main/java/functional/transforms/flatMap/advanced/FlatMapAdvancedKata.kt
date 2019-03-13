// Copyright © FunctionalHub.com 2018. All rights reserved.

package functional.transforms.flatMap.advanced

import functional.transforms.BoxArt
import functional.transforms.MovieList

fun getAllMoviesInformation(list: List<MovieList>): List<Map<String, Any?>> =
        list.flatMap { it.movies }
                .map { mapOf("id" to it.id, "title" to it.title, "boxArt" to it.boxArts.boxArt150()) }

private fun List<BoxArt>.boxArt150() = firstOrNull { it.width == 150 }