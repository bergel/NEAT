Class {
	#name : #NEAddConnectionMutationOperation,
	#superclass : #NEAbstractStructuralMutationOperation,
	#category : #'NEAT-Core'
}

{ #category : #'instance creation' }
NEAddConnectionMutationOperation >> findMissingConnectionIn: anIndividual nbTry: nbTry [
	| node1 node2 |
	nbTry = 0 ifTrue: [ ^ nil ].
	node1 := (anIndividual nodes reject: #isOutput) atRandom: random.
	node2 := (anIndividual nodes reject: #isInput) atRandom: random.
	(anIndividual connections anySatisfy: [ :c |
		(c in = node1 id and: [ c out = node2 id ]) ]) ifTrue: [ 
			^ self findMissingConnectionIn: anIndividual nbTry: (nbTry - 1) ]. 

	(anIndividual connections anySatisfy: [ :c |
		(c in = node2 id and: [ c out = node1 id ]) ]) ifTrue: [ 
			^ self findMissingConnectionIn: anIndividual nbTry: (nbTry - 1) ]. 
		
	(self is: node1 accessibleFrom: node2 in: anIndividual)
		ifTrue: [ ^ self findMissingConnectionIn: anIndividual nbTry: (nbTry - 1) ].
		
	node2 isInput ifTrue: [ ^ self findMissingConnectionIn: anIndividual nbTry: (nbTry - 1) ].
	node1 isOutput ifTrue: [ ^ self findMissingConnectionIn: anIndividual nbTry: (nbTry - 1) ].
	"{ FROM . TO }"
	^ { node1 id . node2 id }
]

{ #category : #'instance creation' }
NEAddConnectionMutationOperation >> is: node1 accessibleFrom: node2 in: anIndividual [
	anIndividual buildNetwork.
	^ self privateIs: node1 accessibleFrom: node2
]

{ #category : #'instance creation' }
NEAddConnectionMutationOperation >> on: anIndividual [
	| array newWeight c |
	array := self findMissingConnectionIn: anIndividual nbTry: 5.

	"We did not find a solution, so we merely exit. There is not much we can do"
	array ifNil: [ ^ self ].
	newWeight := self randomWeight.
	c := NEConnection new
				in: array first;
				out: array second;
				weight: newWeight;
				makeEnabled;
				innovationNumber: InnovationNumber.
	anIndividual addConnection: c.
	anIndividual trace: { self class name . c . newWeight }
]

{ #category : #'instance creation' }
NEAddConnectionMutationOperation >> privateIs: node1 accessibleFrom: node2 [
	node1 == node2 ifTrue: [ ^ true ].
	node2 connectedNodes do: [ :n |
		node1 == n ifTrue: [ ^ true ].
		(self privateIs: node1 accessibleFrom: n) ifTrue: [ ^ true ] ].
	^ false
]
