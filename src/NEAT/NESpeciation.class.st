Class {
	#name : #NESpeciation,
	#superclass : #Object,
	#instVars : [
		'threshold',
		'groups'
	],
	#category : #'NEAT-Core'
}

{ #category : #accessing }
NESpeciation >> groups [
	^ groups
]

{ #category : #ui }
NESpeciation >> gtInspectorViewIn: composite [
	<gtInspectorPresentationOrder: -10>
	composite roassal2
		title: 'View';
		initializeView: [ 	self visualize ]
]

{ #category : #adding }
NESpeciation >> initialize [
	super initialize.
	threshold := 3.
]

{ #category : #'as yet unclassified' }
NESpeciation >> numberOfSpecies [
	^ groups size
]

{ #category : #accessing }
NESpeciation >> population [
	^ self groups flatCollect: #individuals
]

{ #category : #adding }
NESpeciation >> process: someIndividuals [

	"This method takes as argument a collection of collection of innovation numbers"
	| g |
	
	g := someIndividuals groupedBy: [ :individual | 
		| seq |
		seq := individual innovationNumberSequence.
		seq size < 2 
			ifTrue: [ seq first ]
			ifFalse: [ (seq last: (threshold min: seq size - 1)) first ] ]. 

	groups := g associations collect: [ :assoc | NESpecies new individuals: assoc value; id: assoc key ].
	groups do: [ :aSpecies |
		aSpecies individuals do: [ :i | i species: aSpecies ] ]
]

{ #category : #adding }
NESpeciation >> processOLD: someIndividuals [
	"DOES NOT REALLY work. "

	"This method takes as argument a collection of collection of innovation numbers"
	| g |
	
	g := someIndividuals groupedBy: [ :individual | 
		| seq |
		seq := individual innovationNumberSequence.
		(seq last: (threshold min: seq size - 1)) first ].

	groups := g associations collect: [ :assoc | NESpecies new individuals: assoc value; id: assoc key ]	
	
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

{ #category : #ui }
NESpeciation >> visualize [
	| b |
	b := RTMondrian new.
	b shape box size: [ :s | s individuals size ].
	b nodes: (self groups reverseSortedAs: #maxFitness).
	b layout grid.
	b normalizer normalizeColor: #maxFitness.
	^ b
]
