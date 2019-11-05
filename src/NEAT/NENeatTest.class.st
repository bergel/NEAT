Class {
	#name : #NENeatTest,
	#superclass : #TestCase,
	#category : #NEAT
}

{ #category : #tests }
NENeatTest >> testBasic [

	| neat |
	neat := NEAT new.
	neat fitness: [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 0) #(1 0 0) #(1 1 1)) do: [ :tuple |
			((ind evaluate: (tuple first: 2)) first - tuple last) abs <= 0.3 ifTrue: [ score := score + 1 ] ]. 
		score ].
	neat run.
	neat logs first.
	self halt.
]
