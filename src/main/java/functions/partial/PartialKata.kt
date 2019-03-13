// Copyright © FunctionalHub.com 2018. All rights reserved.

package functions.partial

data class Element(val type: String, val position: String, val id: Int)

var partial: (Int) -> Element = { Element("Blog", "Fixed", it) }
