Class {
	#name : #NEAddNodeMutationOperation,
	#superclass : #NEAbstractStructuralMutationOperation,
	#category : #NEAT
}

{ #category : #'instance creation' }
NEAddNodeMutationOperation >> on: anIndividual [
	
	| relevantConnections c |
	relevantConnections := anIndividual connections select: #isEnabled.
	relevantConnections ifEmpty: [ ^self ].
	
	c := relevantConnections atRandom: random.
	self assert: [ c isEnabled ].
	c makeDisabled.
	(anIndividual addHiddenNode) innovationNumber: InnovationNumber.
	anIndividual addConnection: 
		(NEConnection new in: c in; out: anIndividual numberOfNodes; weight: 1; makeEnabled; innovationNumber: InnovationNumber).
	anIndividual addConnection: 
		(NEConnection new in: anIndividual numberOfNodes; out: c out; weight: c weight; makeEnabled; innovationNumber: InnovationNumber).
]
