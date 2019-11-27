Class {
	#name : #NEConnectionWeightMutationOperation,
	#superclass : #NEAbstractNonStructuralMutationOperation,
	#category : #NEAT
}

{ #category : #'instance creation' }
NEConnectionWeightMutationOperation >> on: anIndividual [
	| c |
	anIndividual connections ifEmpty: [ ^ self ].
	c := (anIndividual connections atRandom: random).
	c weight: self randomWeight + c weight
	
]
