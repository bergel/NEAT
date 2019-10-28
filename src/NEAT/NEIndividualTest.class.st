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
	self assert: ind numberOfConnections equals: 3.
	
	self assert: (ind nodes allSatisfy: [ :n | n innovationNumber = 1 ]).
	self assert: (ind connections allSatisfy: [ :n | n innovationNumber = 1 ]).
	
	NEAddNodeMutationOperation new performOn: ind.
	self assert: ind numberOfNodes equals: 5.
	self assert: ind numberOfConnections equals: 5.
	self assert: ((ind nodes allButLast: 1) allSatisfy: [ :n | n innovationNumber = 1 ]).
	self assert: (ind nodes last innovationNumber = 2).
	self assert: ((ind connections allButLast: 3) allSatisfy: [ :n | n innovationNumber = 1 ]).
	self assert: ((ind connections last: 2) allSatisfy: [ :n | n innovationNumber = 2 ]).

	NEAddNodeMutationOperation new performOn: ind.
	self assert: ind numberOfNodes equals: 6.
	self assert: ind numberOfConnections equals: 7.
	self assert: (ind nodes last innovationNumber = 3).
	self assert: ((ind connections last: 2) allSatisfy: [ :n | n innovationNumber = 3 ]).

	NEAddNodeMutationOperation new performOn: ind.
	self assert: ind numberOfNodes equals: 7.
	self assert: ind numberOfConnections equals: 9.
	self assert: (ind nodes last innovationNumber = 4).
	self assert: ((ind connections last: 2) allSatisfy: [ :n | n innovationNumber = 4 ]).

]

{ #category : #tests }
NEIndividualTest >> testBasic [
	| ind |
	ind := NEIndividual new.
	self assert: ind numberOfNodes equals: 0.
	self assert: ind numberOfInputs equals: 0.
	self assert: ind numberOfOutputs equals: 0.
	self assert: ind numberOfConnections equals: 0.
]

{ #category : #tests }
NEIndividualTest >> testExamplePaper [

	| ind |
	ind := NEIndividual new.
	ind random: (Random seed: 42).
	ind addInputNode; addInputNode; addInputNode; addOutputNode; addHiddenNode.
	ind addConnection: (NEConnection new in: 2; out: 5; weight: 0.2; makeEnabled; innovationNumber: 4).
	ind addConnection: (NEConnection new in: 5; out: 4; weight: 0.4; makeEnabled; innovationNumber: 5).
	ind addConnection: (NEConnection new in: 1; out: 5; weight: 0.6; makeEnabled; innovationNumber: 6).

	self assert: ind numberOfInputs equals: 3.
	self assert: ind numberOfOutputs equals: 1.
	self assert: ind numberOfNodes equals: 5.
	
	self assert: (ind evaluate: #(0.1 0.2 0.3)) equals: #(0.14230940324361877).
	self assert: (ind evaluate: #(3 2 1)) equals: #(-3.97766456502381).

	self assert: ind nodes first connectedNodes equals: { ind nodes fourth . ind nodes fifth }.
	self assert: ind nodes second connectedNodes equals: { ind nodes fourth . ind nodes fifth }.
	self assert: ind nodes third connectedNodes equals: { ind nodes fourth }.
	self assert: ind nodes fourth connectedNodes isEmpty.
	self assert: ind nodes fifth connectedNodes equals: { ind nodes fourth }.
	
	self assert: (ind isNode: 1 connectedTo: 4).
	self assert: (ind isNode: 1 connectedTo: 5).	
	self assert: (ind isNode: 3 connectedTo: 4).
	self deny: (ind isNode: 3 connectedTo: 5).
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
	self assert: ind numberOfConnections equals: 2.
	self assert: ind numberOfOutputs equals: 1.
]

{ #category : #tests }
NEIndividualTest >> testInitialConnections2 [

	| ind |
	ind := NEIndividual new.
	ind seed: 42.
	ind addOutputNode.
	self assert: ind numberOfConnections equals: 0.
	
	ind addInputNode.
	self assert: ind numberOfConnections equals: 1.
	ind addInputNode.
	self assert: ind numberOfConnections equals: 2.
	self assert: ind numberOfInputs equals: 2.
	self assert: ind numberOfOutputs equals: 1.
	
]

{ #category : #tests }
NEIndividualTest >> testSimpleIndividual [

	| ind |
	ind := NEIndividual new.
	ind seed: 42.
	ind addInputNode; addInputNode; addInputNode.
	ind addOutputNode.
	
	self assert: ind numberOfConnections equals: 3.
]
