Class {
	#name : #NESpecies,
	#superclass : #Object,
	#instVars : [
		'individuals',
		'id'
	],
	#category : #NEAT
}

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

{ #category : #'as yet unclassified' }
NESpecies >> numberOfIndividuals [
	^ individuals size
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
