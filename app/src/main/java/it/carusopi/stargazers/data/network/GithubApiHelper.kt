package it.carusopi.stargazers.data.network

import okhttp3.Headers

/**
 * Created by carusopi on 31/10/2017.
 */

private val META_NEXT = "next"
private val META_LAST = "last"
private val META_PREV = "prev"
private val META_FIRST = "first"
private val HEADER_NEXT = "X-Next"
private val HEADER_LAST = "X-Last"
private val META_REL = "rel"
private val HEADERS_LINK = "LINK"
private val DELIM_LINKS = ","
private val DELIM_LINK_PARAM = ";"

class PageLinks(headers: Headers) {

    var first: String? = null
    var last: String? = null
    var next: String? = null
    var prev: String? = null

    init {
        var linkHeader: String? = headers.get(HEADERS_LINK)

        if (linkHeader != null) {
            val links = linkHeader.split(DELIM_LINKS.toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            for (link in links) {
                val segments = link.split(DELIM_LINK_PARAM.toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
                if (segments.size < 2)
                    continue

                var linkPart = segments[0].trim({ it <= ' ' })
                if (!linkPart.startsWith("<") || !linkPart.endsWith(">"))
                    continue
                linkPart = linkPart.substring(1, linkPart.length - 1)

                for (i in 1 until segments.size) {
                    val rel = segments[i].trim({ it <= ' ' }).split("=".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray() //$NON-NLS-1$
                    if (rel.size < 2 || !META_REL.equals(rel[0]))
                        continue

                    var relValue = rel[1]
                    if (relValue.startsWith("\"") && relValue.endsWith("\""))
                        relValue = relValue.substring(1, relValue.length - 1)

                    if (META_FIRST.equals(relValue))
                        first = linkPart
                    else if (META_LAST.equals(relValue))
                        last = linkPart
                    else if (META_NEXT.equals(relValue))
                        next = linkPart
                    else if (META_PREV.equals(relValue))
                        prev = linkPart
                }
            }
        } else {
            next = headers.get(HEADER_NEXT)
            last = headers.get(HEADER_LAST)
        }
    }


}
