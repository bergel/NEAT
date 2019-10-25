Class {
	#name : #NEIndividual,
	#superclass : #Object,
	#instVars : [
		'nodes',
		'connections',
		'in'
	],
	#category : #NEAT
}

{ #category : #adding }
NEIndividual >> addConnection: aConnection [
	connections add: aConnection 
]

{ #category : #adding }
NEIndividual >> addHiddenNode [
	self addNode: (NENode new setAsHidden)
]

{ #category : #adding }
NEIndividual >> addInputNode [
	self addNode: (NENode new setAsInput)
]

{ #category : #adding }
NEIndividual >> addNode: aNode [
	nodes add: aNode
]

{ #category : #adding }
NEIndividual >> addOutputNode [
	self addNode: (NENode new setAsOutput)
]

{ #category : #'as yet unclassified' }
NEIndividual >> buildNetwork [
	nodes do: #resetConnections.
	connections do: [ :c |
		c isEnabled ifTrue: [
			(nodes at: c in) connect: (nodes at: c out) weight: c weight ].
	]
]

{ #category : #initialization }
NEIndividual >> initialize [
	super initialize.
	nodes := OrderedCollection new.
	connections := OrderedCollection new
]

{ #category : #'as yet unclassified' }
NEIndividual >> numberOfInputs [
	^ (nodes select: #isInput) size
]

{ #category : #'as yet unclassified' }
NEIndividual >> numberOfOutputs [
	^ (nodes select: #isOutput) size
]
