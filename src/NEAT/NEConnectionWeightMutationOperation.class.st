Class {
	#name : #NEConnectionWeightMutationOperation,
	#superclass : #NEAbstractNonStructuralMutationOperation,
	#category : #NEAT
}

{ #category : #'instance creation' }
NEConnectionWeightMutationOperation >> on: anIndividual [
	| c |
	c := (anIndividual connections atRandom: random).
	c weight: (random next * 0.5 - 0.25) + c weight
	"(anIndividual connections atRandom: random) weight: (random next * 4 - 2)"
]
