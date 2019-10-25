Class {
	#name : #NENode,
	#superclass : #Object,
	#instVars : [
		'id',
		'kind'
	],
	#category : #NEAT
}

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
