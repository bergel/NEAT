Class {
	#name : #NEIndividual,
	#superclass : #Object,
	#instVars : [
		'nodes',
		'connections',
		'in',
		'value'
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
	| newWeight |
	self addNode: (NENode new setAsInput).

	self outputNodes do: [ :on |
		newWeight := Random new next * 4 - 2.
		self addConnection: (NEConnection new in: self numberOfNodes; out: on id; weight: newWeight; makeEnabled; innovationNumber: 1)
	]
]

{ #category : #adding }
NEIndividual >> addNode: aNode [
	nodes add: aNode.
	aNode id: nodes size
]

{ #category : #adding }
NEIndividual >> addOutputNode [
	| newWeight |
	self addNode: (NENode new setAsOutput).
	
	self inputNodes do: [ :ino |
		newWeight := Random new next * 4 - 2.
		self addConnection: (NEConnection new in: ino id; out: self numberOfNodes; weight: newWeight; makeEnabled; innovationNumber: 1)
	]
]

{ #category : #'as yet unclassified' }
NEIndividual >> buildNetwork [
	nodes do: #resetConnections.
	connections do: [ :c |
		c isEnabled ifTrue: [
			(nodes at: c in) connect: (nodes at: c out) weight: c weight ].
	]
]

{ #category : #'public access' }
NEIndividual >> evaluate: anArray [
	self assert: [ anArray size = self numberOfInputs ] description: 'Wrong number of arguments'.
	
	self buildNetwork.
	self inputNodes with: anArray do: [ :n :aValue | n evaluate: aValue ].
	^ self outputNodes collect: #value as: Array
]

{ #category : #initialization }
NEIndividual >> initialize [
	super initialize.
	nodes := OrderedCollection new.
	connections := OrderedCollection new
]

{ #category : #'as yet unclassified' }
NEIndividual >> inputNodes [
	^ nodes select: #isInput
]

{ #category : #testing }
NEIndividual >> isNode: node1Id connectedTo: node2Id [
	^ connections anySatisfy: [ :c | c in = node1Id and: [ c out = node2Id ] ]
]

{ #category : #accessing }
NEIndividual >> nodes [
	^ nodes
]

{ #category : #'as yet unclassified' }
NEIndividual >> numberOfConnections [
	^ connections size
]

{ #category : #'as yet unclassified' }
NEIndividual >> numberOfInputs [
	^ self inputNodes size
]

{ #category : #'as yet unclassified' }
NEIndividual >> numberOfNodes [
	^ nodes size
]

{ #category : #'as yet unclassified' }
NEIndividual >> numberOfOutputs [
	^ self outputNodes size
]

{ #category : #'as yet unclassified' }
NEIndividual >> outputNodes [
	^ nodes select: #isOutput
]

{ #category : #'as yet unclassified' }
NEIndividual >> visualize [
	| b |
	self buildNetwork.
	b := RTMondrian new.
	b shape label text: #id.
	b nodes: self nodes.
	b edges connectToAll: #connectedNodes.
	b layout tree.
	^ b
]
