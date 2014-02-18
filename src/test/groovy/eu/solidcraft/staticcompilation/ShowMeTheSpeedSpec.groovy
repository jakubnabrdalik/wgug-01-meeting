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
        long dynamicLength = getDynamicTime(i)
        long staticLength = getStaticTime(i)
        return dynamicLength - staticLength
    }

    private long getStaticTime(int i) {
        long staticStart = milis()
        fibStatic(i)
        long staticLength = calculateTheEnd(staticStart)
        println("static took: $staticLength")
        return staticLength
    }

    private long getDynamicTime(int i) {
        long dynamicStart = milis()
        fibDynamic(i)
        long dynamicLength = calculateTheEnd(dynamicStart)
        println("dynamic took: $dynamicLength")
        return dynamicLength
    }

    private long calculateTheEnd(long dynamicStart) {
        long dynamicEnd = milis()
        long dynamicLength = dynamicEnd - dynamicStart
        return dynamicLength
    }

    private long milis() {
        return DateTime.now().millis
    }


}
