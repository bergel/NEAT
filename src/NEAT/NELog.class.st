Class {
	#name : #NELog,
	#superclass : #Object,
	#instVars : [
		'generation',
		'speciation',
		'minFitness',
		'maxFitness'
	],
	#category : #NEAT
}

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

{ #category : #accessing }
NELog >> speciation [
	^ speciation
]

{ #category : #accessing }
NELog >> speciation: anObject [
	speciation := anObject
]
