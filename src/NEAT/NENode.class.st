Class {
	#name : #NENode,
	#superclass : #Object,
	#instVars : [
		'id',
		'kind',
		'connectionsCache'
	],
	#category : #NEAT
}

{ #category : #'as yet unclassified' }
NENode >> connect: anotherNode weight: aWeight [
	connectionsCache add: aWeight -> anotherNode
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
	connectionsCache := OrderedCollection new 
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
