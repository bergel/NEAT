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

{ #category : #'instance creation' }
NEAbstractMutationOperation >> performOn: anIndividual [
	self assert: [ anIndividual random notNil ] description: 'Individual does not have a random number'.
	random := anIndividual random.
	self on: anIndividual 
]

{ #category : #accessing }
NEAbstractMutationOperation >> random [
	^ random
]

{ #category : #accessing }
NEAbstractMutationOperation >> random: anObject [
	random := anObject
]
