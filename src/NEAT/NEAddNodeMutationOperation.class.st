Class {
	#name : #NEAddNodeMutationOperation,
	#superclass : #NEAbstractStructuralMutationOperation,
	#category : #'NEAT-Core'
}

{ #category : #'instance creation' }
NEAddNodeMutationOperation >> on: anIndividual [
	
	| relevantConnections c connection1 connection2 |
	relevantConnections := anIndividual connections select: #isEnabled.
	relevantConnections ifEmpty: [ ^self ].
	
	c := relevantConnections atRandom: anIndividual random.
	self assert: [ c isEnabled ].
	c makeDisabled.
	(anIndividual addHiddenNode) innovationNumber: InnovationNumber.
	connection1 := NEConnection new in: c in; out: anIndividual numberOfNodesWithBias; weight: 1; makeEnabled; innovationNumber: InnovationNumber.
	anIndividual addConnection: connection1.
	connection2 := NEConnection new in: anIndividual numberOfNodesWithBias; out: c out; weight: c weight; makeEnabled; innovationNumber: InnovationNumber.
	anIndividual addConnection: connection2.
		
	anIndividual trace: { self class name . c . connection1 . connection2 }
]
