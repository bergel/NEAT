Class {
	#name : #NEAbstractMutationOperation,
	#superclass : #Object,
	#instVars : [
		'random'
	],
	#category : #NEAT
}

{ #category : #accessing }
NEAbstractMutationOperation >> nextInt: anInteger [
	^ random nextInt: anInteger
]

{ #category : #'instance creation' }
NEAbstractMutationOperation >> on: anIndividual [
	self subclassResponsibility 
]

{ #category : #accessing }
NEAbstractMutationOperation >> random [
	^ random
]

{ #category : #accessing }
NEAbstractMutationOperation >> random: anObject [
	random := anObject
]
