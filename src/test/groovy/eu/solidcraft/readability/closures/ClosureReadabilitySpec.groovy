package eu.solidcraft.readability.closures

import groovy.util.logging.Slf4j
import spock.lang.Specification

@Slf4j
class ClosureReadabilitySpec extends Specification {

    def "try catch looks terrible"() {
        expect:
            myBusinessMethod()
    }

    boolean myBusinessMethod() {
        try {
            step1()
        } catch (Exception e) {
            closeAllResources()
            log.error(e.message, e)
        }

        try {
            step2()
        } catch (Exception e) {
            closeAllResources()
            log.error(e.message, e)
        }

        try {
            step3()
        } catch (Exception e) {
            closeAllResources()
            log.error(e.message, e)
        }

        return true
    }


    def "but we can do it better"() {
        expect:
            myBusinessMethod2()
    }

    boolean myBusinessMethod2() {
        log { step1() }
        log { step2() }
        log { step3() }
        return true
    }

    void log(Closure closure) {
        try {
            closure()
        } catch (Exception e) {
            closeAllResources()
            log.error(e.message, e)
        }
    }

    void closeAllResources() {
    }

    void step3() {
        throw new RuntimeException("oh noes, errors in step3")
    }

    void step2() {
        throw new RuntimeException("oh noes, errors in step2")
    }

    void step1() {
        throw new RuntimeException("oh noes, errors in step1")
    }
}
