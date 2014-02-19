package eu.solidcraft.readability

import groovy.transform.TypeChecked

@TypeChecked
interface Outcome {
}

@TypeChecked
class PositiveOutcome implements Outcome {
}

@TypeChecked
interface  NegativeOutcome extends Outcome {
}

@TypeChecked
class NegativeOutcome1 implements NegativeOutcome {
}

@TypeChecked
class NegativeOutcome2 implements NegativeOutcome {
}

@TypeChecked
class NegativeOutcome3 implements NegativeOutcome {
}