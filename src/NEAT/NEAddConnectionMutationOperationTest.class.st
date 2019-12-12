"
A NEAddConnectionMutationOperationTest is a test class for testing the behavior of NEAddConnectionMutationOperation
"
Class {
	#name : #NEAddConnectionMutationOperationTest,
	#superclass : #TestCase,
	#category : #'NEAT-Tests'
}

{ #category : #tests }
NEAddConnectionMutationOperationTest >> testAdding [ 

	| ind |
	ind := NEIndividual new.
	ind seed: 42.
	ind addInputNode; addInputNode; addInputNode.
	ind addOutputNode.
	self assert: ind numberOfConnections equals: 0.
	
	NEAddConnectionMutationOperation new performOn: ind.
	self assert: ind numberOfConnections equals: 1
]
