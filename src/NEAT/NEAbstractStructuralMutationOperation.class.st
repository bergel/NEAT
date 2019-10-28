Class {
	#name : #NEAbstractStructuralMutationOperation,
	#superclass : #NEAbstractMutationOperation,
	#classVars : [
		'InnovationNumber'
	],
	#category : #NEAT
}

{ #category : #initialization }
NEAbstractStructuralMutationOperation class >> reset [ 

	InnovationNumber := 1
]

{ #category : #'instance creation' }
NEAbstractStructuralMutationOperation >> performOn: anIndividual [
	InnovationNumber isNil ifTrue: [ self class reset ].
	InnovationNumber := InnovationNumber + 1.
	super performOn: anIndividual.
]
