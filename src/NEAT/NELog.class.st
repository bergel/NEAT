Class {
	#name : #NELog,
	#superclass : #Object,
	#instVars : [
		'generation',
		'speciation',
		'minFitness',
		'maxFitness',
		'averageFitness'
	],
	#category : #NEAT
}

{ #category : #accessing }
NELog >> averageFitness [
	^ averageFitness
]

{ #category : #accessing }
NELog >> averageFitness: anObject [
	averageFitness := anObject
]

{ #category : #accessing }
NELog >> generation [
	^ generation
]

{ #category : #accessing }
NELog >> generation: anObject [
	generation := anObject
]

{ #category : #accessing }
NELog >> maxFitness [
	^ maxFitness
]

{ #category : #accessing }
NELog >> maxFitness: anObject [
	maxFitness := anObject
]

{ #category : #accessing }
NELog >> minFitness [
	^ minFitness
]

{ #category : #accessing }
NELog >> minFitness: anObject [
	minFitness := anObject
]

{ #category : #'as yet unclassified' }
NELog >> numberOfSpecies [
	^ speciation numberOfSpecies
]

{ #category : #printing }
NELog >> printOn: str [
	super printOn: str.
	str nextPut: $<.
	generation ifNotNil: [ str nextPutAll: generation asString ].
	str nextPut: $>.
]

{ #category : #accessing }
NELog >> speciation [
	^ speciation
]

{ #category : #accessing }
NELog >> speciation: anObject [
	speciation := anObject
]
