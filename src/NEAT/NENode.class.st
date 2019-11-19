Class {
	#name : #NENode,
	#superclass : #Object,
	#instVars : [
		'id',
		'kind',
		'connectionsCache',
		'innovationNumber',
		'numberOfInputs',
		'zValue',
		'numberOfReceivedValues',
		'result'
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
"	self isInput ifFalse: [
		self assert: [ aValue >= 0 ].
		self assert: [ aValue <= 1 ] ]."

	"We just received a value. We add it to zValue."
	zValue := zValue + aValue.

	"We say we received a new value"
	numberOfReceivedValues := numberOfReceivedValues + 1.

	self haltIf: [ numberOfReceivedValues > numberOfInputs ].


	"If we received the number of zValues, then we can compute the sigmoid, and keep it."
	numberOfReceivedValues = numberOfInputs ifFalse: [ ^ self ].
	result := self isInput ifTrue: [ zValue ] ifFalse: [ self sigmoid: zValue ].
	
	"We go here only if not output"
	connectionsCache do: [ :assoc | assoc value evaluate: result * assoc key ]



	
]

{ #category : #accessing }
NENode >> id [
	^ id
]

{ #category : #accessing }
NENode >> id: anInteger [
	id := anInteger
]

{ #category : #'as yet unclassified' }
NENode >> increaseNumberOfInputs [
	numberOfInputs := numberOfInputs + 1
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

{ #category : #accessing }
NENode >> numberOfInputs [
	"Return the number of inputs the nodes accepts"
	^ numberOfInputs
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
	zValue := 0.
	numberOfInputs := 0.
	numberOfReceivedValues := 0.
	result := 0.
	self isInput 
		ifTrue: [ numberOfInputs := 1 ]
		
]

{ #category : #'public access' }
NENode >> result [
	^ result
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
	^ zValue
]
