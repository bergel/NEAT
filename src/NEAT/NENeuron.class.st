Class {
	#name : #NENeuron,
	#superclass : #Object,
	#instVars : [
		'connections'
	],
	#category : #NEAT
}

{ #category : #adding }
NENeuron >> add: aNode weight: weight [
	connections add: aNode -> weight
]

{ #category : #adding }
NENeuron >> initialize [ 
	super initialize.
	connections := OrderedCollection new
]
