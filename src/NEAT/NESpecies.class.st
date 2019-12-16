Class {
	#name : #NESpecies,
	#superclass : #Object,
	#instVars : [
		'individuals',
		'id'
	],
	#category : #'NEAT-Core'
}

{ #category : #accessing }
NESpecies >> averageFitness [
	^ (self individuals collect: #fitness) average
]

{ #category : #'as yet unclassified' }
NESpecies >> childrenSpecies [
	| results |
	results := Set new.
	self individuals do: [ :i | results addAll: (i children collect: #species) ].
	^ results
]

{ #category : #ui }
NESpecies >> gtInspectorViewIn: composite [
	<gtInspectorPresentationOrder: -10>
	composite roassal2
		title: 'View';
		initializeView: [ 	self visualize ]
]

{ #category : #accessing }
NESpecies >> id [
	^ id
]

{ #category : #accessing }
NESpecies >> id: anObject [
	id := anObject
]

{ #category : #accessing }
NESpecies >> individuals [
	^ individuals
]

{ #category : #accessing }
NESpecies >> individuals: anObject [
	individuals := anObject
]

{ #category : #accessing }
NESpecies >> maxFitness [
	^ (self individuals collect: #fitness) max
]

{ #category : #'as yet unclassified' }
NESpecies >> numberOfIndividuals [
	^ individuals size
]

{ #category : #'as yet unclassified' }
NESpecies >> parentSpecies [
	| results |
	results := Set new.
	self individuals do: [ :i | results addAll: (i parents collect: #species) ].
	^ results asSet asArray
]

{ #category : #printing }
NESpecies >> printOn: str [
	super printOn: str.
	str nextPut: $<.
	id ifNotNil: [ str nextPutAll: id asString ].
	str nextPut: $,.
	individuals ifNotNil: [ str nextPutAll: individuals size asString ].
	str nextPut: $>.
]

{ #category : #ui }
NESpecies >> visualize [
	| b |
	b := RTMondrian new.
	b nodes: individuals.
	b layout grid.
	^ b
]

{ #category : #'as yet unclassified' }
NESpecies >> withAllParentSpecies [
	| result |
	result := OrderedCollection new.
	self withAllParentSpecies: result.
	^ result 
]

{ #category : #'as yet unclassified' }
NESpecies >> withAllParentSpecies: result [
	result add: self.
	self parentSpecies do: [ :s | s withAllParentSpecies: result ] 
]
