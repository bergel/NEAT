Class {
	#name : #NEIndividualTest,
	#superclass : #TestCase,
	#category : #NEAT
}

{ #category : #tests }
NEIndividualTest >> testExamplePaper [

	| ind |
	ind := NEIndividual new.
	ind addInputNode; addInputNode; addInputNode; addOutputNode; addHiddenNode.
	ind addConnection: (NEConnection new in: 1; out: 4; weight: 0.7; makeEnabled; innovationNumber: 1).
	ind addConnection: (NEConnection new in: 2; out: 4; weight: -0.5; makeDisabled; innovationNumber: 2).
	ind addConnection: (NEConnection new in: 3; out: 4; weight: 0.5; makeEnabled; innovationNumber: 3).
	ind addConnection: (NEConnection new in: 2; out: 5; weight: 0.2; makeEnabled; innovationNumber: 4).
	ind addConnection: (NEConnection new in: 5; out: 4; weight: 0.4; makeEnabled; innovationNumber: 5).
	ind addConnection: (NEConnection new in: 1; out: 5; weight: 0.6; makeEnabled; innovationNumber: 6).
	ind addConnection: (NEConnection new in: 4; out: 5; weight: 0.6; makeEnabled; innovationNumber: 11).

	self assert: ind numberOfInputs equals: 3.
	self assert: ind numberOfOutputs equals: 1.
]
