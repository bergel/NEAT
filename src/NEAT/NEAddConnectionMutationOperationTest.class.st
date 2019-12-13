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

	| ind op r c |
	ind := NEIndividual new.
	ind seed: 42.
	ind addInputNode; addInputNode; addInputNode.
	ind addOutputNode.
	self assert: ind numberOfConnections equals: 0.
	
	op := NEAddConnectionMutationOperation new.
	op random: ind random.
	r := op findMissingConnectionIn: ind nbTry: 2.
	self assert: r equals: #(1 5).

	op performOn: ind.
	"We get another connection"
	self assert: ind numberOfConnections equals: 1.
	self assert: ind connections size equals: 1.
	c := ind connections first.
	self assert: c in equals: 3.
	self assert: c out equals: 5.
]
