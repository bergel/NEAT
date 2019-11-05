Class {
	#name : #NENode,
	#superclass : #Object,
	#instVars : [
		'id',
		'kind',
		'connectionsCache',
		'value',
		'innovationNumber'
	],
	#category : #NEAT
}

{ #category : #'as yet unclassified' }
NENode >> connect: anotherNode weight: aWeight [
	connectionsCache add: aWeight -> anotherNode
]

{ #category : #'as yet unclassified' }
NENode >> connectedNodes [
	^ connectionsCache collect: #value as: Array
]

{ #category : #copying }
NENode >> copy [
	| newNode |
	newNode := NENode new.
	newNode id: self id.
	newNode kind: self kind.
	newNode innovationNumber: self innovationNumber.
	^ newNode

]

{ #category : #'public access' }
NENode >> evaluate: aValue [
	self assert: [ aValue >= 0 ].
	self assert: [ aValue <= 1 ].

	"If we are an output node, then we sum the value"
	self isOutput ifTrue: [ value := value + aValue. ^ self ].
	
	"else, we simply pass it" 
	connectionsCache do: [ :assoc |
		assoc value evaluate: (self sigmoid: assoc key * aValue) ]
]

{ #category : #accessing }
NENode >> id [
	^ id
]

{ #category : #accessing }
NENode >> id: anInteger [
	id := anInteger
]

{ #category : #accessing }
NENode >> innovationNumber [
	^ innovationNumber
]

{ #category : #accessing }
NENode >> innovationNumber: anInteger [
	innovationNumber := anInteger
]

{ #category : #testing }
NENode >> isBias [
	^ self isInput and: [ id = 1 ]
]

{ #category : #testing }
NENode >> isInput [
	^ kind == #input
]

{ #category : #testing }
NENode >> isOutput [
	^ kind == #output
]

{ #category : #accessing }
NENode >> kind [
	^ kind
]

{ #category : #accessing }
NENode >> kind: aKind [
	kind := aKind
]

{ #category : #printing }
NENode >> printOn: str [ 
	super printOn: str.
	str nextPut: $<.
	id ifNotNil: [ str nextPutAll: id asString ].
	str nextPut: $,.
	innovationNumber ifNotNil: [ str nextPutAll: innovationNumber asString ].
	str nextPut: $,.
	kind ifNotNil: [ str nextPutAll: kind asString ].
	str nextPut: $>.
]

{ #category : #initialization }
NENode >> resetConnections [
	connectionsCache := OrderedCollection new.
	value := 0.
]

{ #category : #initialization }
NENode >> setAsHidden [
	kind := #hidden
]

{ #category : #initialization }
NENode >> setAsInput [
	kind := #input
]

{ #category : #initialization }
NENode >> setAsOutput [
	kind := #output
]

{ #category : #'public access' }
NENode >> sigmoid: z [
	^ 1 / (1 + z negated exp)
]

{ #category : #evaluating }
NENode >> value [
	^ value
]
