Class {
	#name : #NENeatTest,
	#superclass : #TestCase,
	#category : #NEAT
}

{ #category : #tests }
NENeatTest >> testBasic [

	| neat diff precision |
	neat := NEAT new.
	neat fitness: [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 0) #(1 0 0) #(1 1 1)) do: [ :tuple |
			diff := (ind evaluate: (tuple first: 2)) first - tuple last.
			score := score + (diff * diff) ]. 
		(score / -4) asFloat ].
	
"	neat fitness: [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 0) #(1 0 0) #(1 1 1)) do: [ :tuple |
			((ind evaluate: (tuple first: 2)) first - tuple last) abs <= 0.1 ifTrue: [ score := score + 1 ] ]. 
		score ]."
	neat numberOfGenerations: 50.
	neat run.
	
	[ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 0) #(1 0 0) #(1 1 1)) do: [ :tuple |
			((ind evaluate: (tuple first: 2)) first - tuple last) abs <= 0.1 ifTrue: [ score := score + 1 ] ]. 
		score ] value: neat result.
	
	precision := 0.2.
	self assert: ((neat result evaluate: #(0 0)) first - 0) < precision.
	self assert: ((neat result evaluate: #(0 1)) first - 0) < precision.
	self assert: ((neat result evaluate: #(1 0)) first - 0) < precision.
	self assert: ((neat result evaluate: #(1 1)) first - 1) < precision.
	
	
	 
]

{ #category : #tests }
NENeatTest >> testBasic3XOR [

	| neat diff r |
	neat := NEAT new.
	neat fitness: [ :ind | 
		| loss |
		loss := 0.
		#(#(0 0 0) #(0 1 1) #(1 0 1) #(1 1 0)) do: [ :tuple |
			diff := (ind evaluate: (tuple first: 2)) first - tuple last.
			loss := loss + (diff * diff) ]. 
		(loss / -4) asFloat ].
	
"	neat resetConfig.
	neat for: NEConnectionWeightMutationOperation prob: 0.99.
"
	neat numberOfGenerations: 100.
	neat run.
	
	r := 
	[ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 0) #(1 0 0) #(1 1 1)) do: [ :tuple |
			((ind predict: (tuple first: 2)) = tuple last) ifTrue: [ score := score + 1 ] ]. 
		score ] value: neat result.
	
	self assert: r equals: 3.
	neat result predict: #(0 1).
	
	
]

{ #category : #tests }
NENeatTest >> testBasic4BinaryToInteger [

	| neat res r |
	neat := NEAT new.
	neat numberOfOutputs: 4.
	neat fitness: [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 1) #(1 0 2) #(1 1 3)) do: [ :tuple |
			((ind predict: (tuple first: 2)) = tuple last) ifTrue: [ score := score + 1 ]
			].
		score ].
	
"	neat resetConfig.
	neat for: NEConnectionWeightMutationOperation prob: 0.99.
"
	neat numberOfGenerations: 100.
	neat run. 
	
	r :=
	[ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 1) #(1 0 2) #(1 1 3)) do: [ :tuple |
			((ind predict: (tuple first: 2)) = tuple last) ifTrue: [ score := score + 1 ]
			].
		score ] value: neat result.
	self assert: r equals: 4
]

{ #category : #tests }
NENeatTest >> testBasic5BinaryToIntegerLarger [

	| neat |
	neat := NEAT new.
	neat numberOfInputs: 3.
	neat numberOfOutputs: 8.
	neat fitness: [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0 0) #(0 0 1 1) #(0 1 0 2) #(0 1 1 3) #(1 0 0 4) #(1 0 1 5) #(1 1 0 6) #(1 1 1 7)) do: [ :tuple |
			((ind predict: tuple allButLast) = tuple last) ifTrue: [ score := score + 1 ] ].
		score ].
	
"	neat resetConfig.
	neat for: NEConnectionWeightMutationOperation prob: 0.99.
"
	neat numberOfGenerations: 60.
	neat run.
	
	self assert: neat logs last maxFitness equals: 6
]
