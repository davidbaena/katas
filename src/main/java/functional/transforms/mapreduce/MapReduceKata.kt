// Copyright © FunctionalHub.com 2018. All rights reserved.

package functional.transforms.mapreduce

import functional.transforms.BoxArt
import functional.transforms.Movie

fun getUrlOfLargestBoxArt(list: List<Movie>): String = list.flatMap { it.boxArts }.biggestBoxArt().url

private fun List<BoxArt>.biggestBoxArt(): BoxArt =
        reduce { acc, boxArt ->
            if (boxArea(boxArt) > boxArea(acc)) boxArt
            else acc
        }


private fun boxArea(boxArt: BoxArt) =
        boxArt.width * boxArt.height