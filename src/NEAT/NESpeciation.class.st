Class {
	#name : #NESpeciation,
	#superclass : #Object,
	#instVars : [
		'threshold',
		'groups'
	],
	#category : #NEAT
}

{ #category : #accessing }
NESpeciation >> groups [
	^ groups
]

{ #category : #adding }
NESpeciation >> initialize [
	super initialize.
	threshold := 5.
]

{ #category : #adding }
NESpeciation >> processSetOfInnovationsNumbers: someInnovationNumbers [

	"This method takes as argument a collection of collection of innovation numbers"
	| g |
	
	g := someInnovationNumbers groupedBy: [ :seq | (seq last: (threshold min: seq size - 1)) first ].
	groups := g values.
	
]

{ #category : #accessing }
NESpeciation >> threshold [
	^ threshold
]

{ #category : #accessing }
NESpeciation >> threshold: anInteger [
	threshold := anInteger
]
