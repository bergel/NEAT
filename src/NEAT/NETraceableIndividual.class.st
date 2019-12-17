Class {
	#name : #NETraceableIndividual,
	#superclass : #NEIndividual,
	#instVars : [
		'trace'
	],
	#category : #'NEAT-Core'
}

{ #category : #'as yet unclassified' }
NETraceableIndividual >> computeFitness: oneArgBlock [
	super computeFitness: oneArgBlock.
	self trace: { #fitness . fitness }
]

{ #category : #copying }
NETraceableIndividual >> copy [
	| result |
	result := super copy.
	result setTrace: trace copy.
	^ result
]

{ #category : #initialization }
NETraceableIndividual >> initialize [
	super initialize.
	self setTrace: OrderedCollection new
]

{ #category : #copying }
NETraceableIndividual >> setTrace: aTrace [
	trace := aTrace
]

{ #category : #copying }
NETraceableIndividual >> trace: aTupple [
	trace add: aTupple
]
