Class {
	#name : #NENode,
	#superclass : #Object,
	#instVars : [
		'id',
		'kind',
		'connectionsCache',
		'value'
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

{ #category : #'public access' }
NENode >> evaluate: aValue [
	"If we are an output node, then we sum the value"
	self isOutput ifTrue: [ value := value + aValue. ^ self ].
	
	"else, we simply pass it" 
	connectionsCache do: [ :assoc |
		assoc value evaluate: assoc key * aValue ]
]

{ #category : #accessing }
NENode >> id [
	^ id
]

{ #category : #accessing }
NENode >> id: anInteger [
	id := anInteger
]

{ #category : #testing }
NENode >> isInput [
	^ kind == #input
]

{ #category : #testing }
NENode >> isOutput [
	^ kind == #output
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

{ #category : #evaluating }
NENode >> value [
	^ value
]
