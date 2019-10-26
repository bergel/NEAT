Class {
	#name : #NEConnectionWeightMutationOperation,
	#superclass : #NEAbstractNonStructuralMutationOperation,
	#category : #NEAT
}

{ #category : #'instance creation' }
NEConnectionWeightMutationOperation >> on: anIndividual [
	(anIndividual connections atRandom: random) weight: (random next * 4 - 2)
]
