Class {
	#name : #NEIndividual,
	#superclass : #Object,
	#instVars : [
		'nodes',
		'connections'
	],
	#category : #NEAT
}

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
	| n |
	n := NENetwork new.
	^ n
]

{ #category : #initialization }
NEIndividual >> initialize [
	super initialize.
	nodes := OrderedCollection new.
	connections := OrderedCollection new
]
