package eu.solidcraft.staticcompilation

import groovy.transform.CompileStatic
import org.joda.time.DateTime
import spock.lang.Specification

class ShowMeTheSpeedSpec extends Specification {

    //See: http://melix.github.io/talks/s2gx-typechecking/slides.html#slide-53

    def "why do we need static compilation"() {
        expect:
            staticVsDynamic(40) > 0
    }

    int fibDynamic(int i) {
        i < 2 ? 1 : fibDynamic(i - 2) + fibDynamic(i - 1)
    }

    @CompileStatic
    int fibStatic(int i) {
        i < 2 ? 1 : fibStatic(i - 2) + fibStatic(i - 1)
    }

    long staticVsDynamic(int i) {
        long dynamicLength = measure(i, "dynamic") { fibDynamic(i) }
        long staticLength = measure(i, "static") { fibStatic(i) }
        return dynamicLength - staticLength
    }

    private long measure(int i, String name, Closure closure) {
        long start = milis()
        closure.call(i)
        long length = calculateTheEnd(start)
        println("$name took: $length")
        return length
    }

    private long calculateTheEnd(long start) {
        return milis() - start
    }

    private long milis() {
        return DateTime.now().millis
    }


}
