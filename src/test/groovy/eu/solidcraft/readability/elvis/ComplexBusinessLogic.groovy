package eu.solidcraft.readability.elvis
import eu.solidcraft.readability.*
import groovy.transform.PackageScope
import groovy.transform.TypeChecked

@TypeChecked
@PackageScope
class ComplexBusinessLogic {
    /*
        Implement complex business logic
        step1:
        step2:
        step3:
        if any of the step fails, do something and do not continue
     */


    Outcome complexProcess() {
        try {
            step1()
        } catch (Exception e) {
            return new NegativeOutcome1()
        }

        Outcome outcomeOfStep2 = step2()
        if( outcomeOfStep2 instanceof NegativeOutcome) {
            return outcomeOfStep2
        }

        try {
            step3()
        } catch (Exception e) {
            return new NegativeOutcome3()
        }

        return new PositiveOutcome()
    }

    private void step3() {
    }

    private Outcome step2() {
    }

    private void step1() {
    }

}
