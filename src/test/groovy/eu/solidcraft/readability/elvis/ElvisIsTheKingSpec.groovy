package eu.solidcraft.readability.elvis

import eu.solidcraft.readability.Outcome

class ElvisIsTheKingSpec {



    Outcome "complex business logic that has to stop"() {
        return step1() ?:
               step2() ?:
               step3()
    }


}
