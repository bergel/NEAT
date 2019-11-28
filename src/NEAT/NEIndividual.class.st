Class {
	#name : #NEIndividual,
	#superclass : #Object,
	#instVars : [
		'nodes',
		'connections',
		'random',
		'fitness',
		'species'
	],
	#category : #NEAT
}

{ #category : #adding }
NEIndividual >> addConnection: aConnection [
	self assert: [ connections noneSatisfy: [ :c | c in = aConnection in and: [ c out =  aConnection out ] ] ] description: 'Cannot have duplicated connections'.
	connections ifNotEmpty: [ 
		self assert: [ aConnection innovationNumber >= connections last innovationNumber ] description: 'Something wrong here'. 
	].
	connections add: aConnection.
	self resetNetwork.
]

{ #category : #adding }
NEIndividual >> addHiddenNode [
	| node |
	node := NENode new setAsHidden.
	self addNode: node.
	^ node
]

{ #category : #adding }
NEIndividual >> addInputNode [
	| newWeight |
	self addNode: (NENode new setAsInput; innovationNumber: 1).

"	self outputNodes do: [ :on |
		newWeight := random next * 4 - 2.
		self addConnection: (NEConnection new in: self numberOfNodesWithBias; out: on id; weight: newWeight; makeEnabled; innovationNumber: 1)
	]"
]

{ #category : #adding }
NEIndividual >> addNode: aNode [
	self resetNetwork.
	self addRawNode: aNode.
	aNode id: nodes size
]

{ #category : #adding }
NEIndividual >> addOutputNode [
	| newWeight |
	self addNode: (NENode new setAsOutput; innovationNumber: 1).
	
"	self inputNodes do: [ :ino |
		newWeight := random next * 4 - 2.
		self addConnection: (NEConnection new in: ino id; out: self numberOfNodesWithBias; weight: newWeight; makeEnabled; innovationNumber: 1)
	]"
]

{ #category : #adding }
NEIndividual >> addRawNode: aNode [
	nodes add: aNode
]

{ #category : #'as yet unclassified' }
NEIndividual >> buildNetwork [
	self resetNetwork.
	connections
		do: [ :c | 
			c isEnabled
				ifTrue: [ 
					(nodes at: c in) connect: (nodes at: c out) weight: c weight.
					(nodes at: c out) increaseNumberOfInputs ] ]
]

{ #category : #'as yet unclassified' }
NEIndividual >> computeFitness: oneArgBlock [
	"self assert: [ fitness isNil ] description: 'Already compute fitness'."
	fitness ifNotNil: [ ^ self ]. "Already computed"
	fitness := oneArgBlock value: self.
]

{ #category : #accessing }
NEIndividual >> connections [
	^ connections 
]

{ #category : #accessing }
NEIndividual >> connections: someConnections [
	connections := someConnections 
]

{ #category : #copying }
NEIndividual >> copy [
	| newInd |
	newInd := NEIndividual new.
	newInd random: self random.
	newInd nodes: (self nodes collect: #copy).
	newInd connections: (self connections collect: #copy).
	newInd fitness: fitness.
	^ newInd
]

{ #category : #'public access' }
NEIndividual >> evaluate: anArray [
	self assert: [ anArray size = self numberOfInputs ] description: 'Wrong number of arguments'.
	
	self buildNetwork.
	
	"We provide 1 to the first node, which is considered as the bias node"
	self inputNodes with: #(1), anArray do: [ :n :aValue | n evaluate: aValue ].
	^ self outputNodes collect: [ :n | n result ] as: Array
]

{ #category : #accessing }
NEIndividual >> fitness [
	self assert: [ fitness notNil ] description: 'Need to compute fitness first'.
	^ fitness
]

{ #category : #'as yet unclassified' }
NEIndividual >> fitness: aFitnessValue [
	"Private method"
	fitness := aFitnessValue
]

{ #category : #ui }
NEIndividual >> gtInspectorListIn: composite [
	<gtInspectorPresentationOrder: -1>
	composite list
		title: 'Innov';
		display: #innovationNumberSequence
]

{ #category : #ui }
NEIndividual >> gtInspectorViewIn: composite [
	<gtInspectorPresentationOrder: -10>
	composite roassal2
		title: 'View';
		initializeView: [ 	self visualize ]
]

{ #category : #initialization }
NEIndividual >> initialize [
	super initialize.
	nodes := OrderedCollection new.
	connections := OrderedCollection new.
	"self seed: 42."
	
	"This is the bias node"
	self addInputNode
]

{ #category : #accessing }
NEIndividual >> innovationNumberSequence [
	self connections ifEmpty: [ ^ #(0) ].
	^ self connections collect: #innovationNumber as: Array
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

{ #category : #accessing }
NEIndividual >> nodes: someNodes [
	nodes := someNodes 
]

{ #category : #'as yet unclassified' }
NEIndividual >> numberOfConnections [
	^ connections size
]

{ #category : #'as yet unclassified' }
NEIndividual >> numberOfInputs [
	"We substracte one for the bias"
	^ self inputNodes size - 1
]

{ #category : #'as yet unclassified' }
NEIndividual >> numberOfNodes [
	"Exclude the bias node"
	^ nodes size - 1
]

{ #category : #'as yet unclassified' }
NEIndividual >> numberOfNodesWithBias [
	^ self nodes size
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
NEIndividual >> predict: inputs [
	"Make a prediction. This method assumes that the number of outputs is the same as the number of different values the network can output"
	"The index of a collection begins at 1 in Pharo"
	| outputs |
	outputs := self evaluate: inputs.
	^ (outputs indexOf: (outputs max)) - 1
]

{ #category : #printing }
NEIndividual >> printOn: str [
	super printOn: str.
	str nextPut: $<.
	fitness ifNotNil: [ str nextPutAll: fitness asString ].
	str nextPut: $>.
]

{ #category : #accessing }
NEIndividual >> random [
	^ random
	
]

{ #category : #accessing }
NEIndividual >> random: aRandomNumberGenerator [
	random := aRandomNumberGenerator
]

{ #category : #'as yet unclassified' }
NEIndividual >> resetNetwork [
	nodes do: #resetConnections
]

{ #category : #'instance creation' }
NEIndividual >> seed: aSeed [
	self random: (Random seed: aSeed)
]

{ #category : #accessing }
NEIndividual >> species [
	^ species
]

{ #category : #accessing }
NEIndividual >> species: anObject [
	species := anObject
]

{ #category : #ui }
NEIndividual >> visualize [
	| b |
	self buildNetwork.
	b := RTMondrian new.
	b shape label text: #id;
		color: Color gray;
		if: #isInput color: Color blue;
		if: #isOutput color: Color red;
		if: #isBias color: Color yellow.
	b nodes: self nodes.
	b edges connectToAll: #connectedNodes.
	b layout tree.
	^ b
]
