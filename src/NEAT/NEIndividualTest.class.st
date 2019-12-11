Class {
	#name : #NEIndividualTest,
	#superclass : #TestCase,
	#category : #NEAT
}

{ #category : #'tests mutation operators' }
NEIndividualTest >> setUp [
	super setUp.
	NEAbstractStructuralMutationOperation reset
]

{ #category : #'tests mutation operators' }
NEIndividualTest >> testAddNodeMutation [

	| ind |
	ind := NEIndividual new.
	ind seed: 42.
	ind addInputNode; addInputNode; addInputNode.
	ind addOutputNode.
	self assert: ind numberOfNodes equals: 4.
	self assert: ind numberOfConnections equals: 4.
	
	self assert: (ind nodes allSatisfy: [ :n | n innovationNumber = 1 ]).
	self assert: (ind connections allSatisfy: [ :n | n innovationNumber = 1 ]).
	
	NEAddNodeMutationOperation new performOn: ind.
	self assert: ind numberOfNodes equals: 5.
	self assert: ind numberOfConnections equals: 6.
	self assert: ((ind nodes allButLast: 1) allSatisfy: [ :n | n innovationNumber = 1 ]).
	self assert: (ind nodes last innovationNumber = 2).
	self assert: ((ind connections allButLast: 3) allSatisfy: [ :n | n innovationNumber = 1 ]).
	self assert: ((ind connections last: 2) allSatisfy: [ :n | n innovationNumber = 2 ]).

	NEAddNodeMutationOperation new performOn: ind.
	self assert: ind numberOfNodes equals: 6.
	self assert: ind numberOfConnections equals: 8.
	self assert: (ind nodes last innovationNumber = 3).
	self assert: ((ind connections last: 2) allSatisfy: [ :n | n innovationNumber = 3 ]).

	NEAddNodeMutationOperation new performOn: ind.
	self assert: ind numberOfNodes equals: 7.
	self assert: ind numberOfConnections equals: 10.
	self assert: (ind nodes last innovationNumber = 4).
	self assert: ((ind connections last: 2) allSatisfy: [ :n | n innovationNumber = 4 ]).

]

{ #category : #tests }
NEIndividualTest >> testBasic [
	| ind |
	ind := NEIndividual new.
	self assert: ind numberOfNodesWithBias equals: 1.
	self assert: ind numberOfNodes equals: 0.
	self assert: ind numberOfInputs equals: 0.
	self assert: ind numberOfOutputs equals: 0.
	self assert: ind numberOfConnections equals: 0.
]

{ #category : #tests }
NEIndividualTest >> testCopy [

	| ind ind2 |
	ind := NEIndividual new.
	ind seed: 42.
	ind addInputNode; addInputNode; addInputNode.
	ind addOutputNode.

	ind2 := ind copy.
	self deny: ind equals: ind2.
	self deny: ind nodes equals: ind2 nodes.
	self deny: ind connections equals: ind2 connections.
	self assert: ind random equals: ind2 random.
	
	ind nodes with: ind2 nodes do: [ :n1 :n2 | self deny: n1 equals: n2 ].
	ind connections with: ind2 connections do: [ :c1 :c2 | self deny: c1 equals: c2 ].
]

{ #category : #'tests crossover' }
NEIndividualTest >> testCrossOver01 [

	| i1 i2 |
	i1 := NEIndividual new.
	i1 seed: 10.
	i1 addInputNode; addInputNode; addInputNode.
	i1 addOutputNode.
	
	i2 := NEIndividual new.
	i2 seed: 42.
	i2 addInputNode; addInputNode; addInputNode.
	i2 addOutputNode.
	
	NEAddNodeMutationOperation new performOn: i1.
	NEAddConnectionMutationOperation new performOn: i1.
	NEAddConnectionMutationOperation new performOn: i1.
	NEAddNodeMutationOperation new performOn: i2.
	NEAddNodeMutationOperation new performOn: i2.
	
	NECrossoverOperation new performOn: i1 and: i2.
]

{ #category : #tests }
NEIndividualTest >> testExamplePaper [

	| ind |
	ind := NEIndividual new.
	ind random: (Random seed: 42).
	ind addInputNode; addInputNode; addInputNode; addOutputNode; addHiddenNode.
	ind addConnection: (NEConnection new in: 2; out: 6; weight: 0.2; makeEnabled; innovationNumber: 4).
	ind addConnection: (NEConnection new in: 6; out: 4; weight: 0.4; makeEnabled; innovationNumber: 5).
	ind addConnection: (NEConnection new in: 1; out: 6; weight: 0.6; makeEnabled; innovationNumber: 6).

	self assert: ind numberOfInputs equals: 3.
	self assert: ind numberOfOutputs equals: 1.
	self assert: ind numberOfNodes equals: 5.
	
	self assert: (ind evaluate: #(0.1 0.2 0.3)) equals: #(0.0886048582358332).
	self assert: (ind evaluate: #(3 2 1)) equals: #(0.2576768427369574).

"	self assert: ind nodes first connectedNodes equals: { ind nodes fourth . ind nodes fifth }.
	self assert: ind nodes second connectedNodes equals: { ind nodes fourth . ind nodes fifth }.
	self assert: ind nodes third connectedNodes equals: { ind nodes fourth }.
	self assert: ind nodes fourth connectedNodes isEmpty.
	self assert: ind nodes fifth connectedNodes equals: { ind nodes fourth }.
	
	self assert: (ind isNode: 1 connectedTo: 4).
	self assert: (ind isNode: 1 connectedTo: 5).	
	self assert: (ind isNode: 3 connectedTo: 4).
	self deny: (ind isNode: 3 connectedTo: 5)."
]

{ #category : #tests }
NEIndividualTest >> testFitnessAndMutations [

	| ind |
	ind := NEIndividual new.
	ind addInputNode; addOutputNode.
	ind random: (Random seed: 42).
	self should: [ ind fitness ] raise: AssertionFailure.
	ind computeFitness: [ :i | 23 ].
	self assert: ind fitness equals: 23.
	
	NEAddConnectionMutationOperation new performOn: ind.
	self should: [ ind fitness ] raise: AssertionFailure.
]

{ #category : #tests }
NEIndividualTest >> testInitialConnections [

	| ind |
	ind := NEIndividual new.
	ind seed: 42.
	self assert: ind numberOfConnections equals: 0.
	ind addInputNode.
	self assert: ind numberOfConnections equals: 0.
	ind addInputNode.
	self assert: ind numberOfConnections equals: 0.
	self assert: ind numberOfInputs equals: 2.
	self assert: ind numberOfOutputs equals: 0.
	
	ind addOutputNode.
	self assert: ind numberOfInputs equals: 2.
	self assert: ind numberOfConnections equals: 3.
	self assert: ind numberOfOutputs equals: 1.
]

{ #category : #tests }
NEIndividualTest >> testInitialConnections2 [

	| ind |
	ind := NEIndividual new.
	ind seed: 42.
	ind addOutputNode.
	self assert: ind numberOfConnections equals: 1.
	
	ind addInputNode.
	self assert: ind numberOfConnections equals: 2.
	ind addInputNode.
	self assert: ind numberOfConnections equals: 3.
	self assert: ind numberOfInputs equals: 2.
	self assert: ind numberOfOutputs equals: 1.
	
]

{ #category : #'tests mutation operators' }
NEIndividualTest >> testNumberOfInputs [

	| ind outputNode |
	ind := NEIndividual new.
	ind seed: 42.
	ind addInputNode; addInputNode; addInputNode.
	ind addOutputNode.

	outputNode := ind outputNodes first.
	self assert: outputNode numberOfInputs equals: 0.
	
	ind buildNetwork.
	self assert: outputNode numberOfInputs equals: 4
]

{ #category : #tests }
NEIndividualTest >> testSimpleIndividual [

	| ind |
	ind := NEIndividual new.
	ind seed: 42.
	ind addInputNode; addInputNode; addInputNode.
	ind addOutputNode.
	
	self assert: ind numberOfConnections equals: 4.
	self assert: ind innovationNumberSequence equals: #(1 1 1 1)
]
