package eu.solidcraft.readability.elvis

import eu.solidcraft.readability.NegativeOutcome
import eu.solidcraft.readability.Outcome
import eu.solidcraft.readability.PositiveOutcome
import groovy.transform.PackageScope
import groovy.transform.TypeChecked

@TypeChecked
@PackageScope
class ComplexBusinessLogicBetter {

    Outcome complexProcess() {
        return step1() ?:
               step2() ?:
               step3() ?:
               operationSuccesfull()
    }

    PositiveOutcome operationSuccesfull() {
        return new PositiveOutcome()
    }

    private NegativeOutcome step3() {
        //returns nothing or NegativeOutcome
    }

    private NegativeOutcome step2() {
        //return nothing or NegativeOutcome
    }

    private NegativeOutcome step1() {
        //return nothing or NegativeOutcome
    }

}
