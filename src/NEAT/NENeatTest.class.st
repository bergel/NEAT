Class {
	#name : #NENeatTest,
	#superclass : #TestCase,
	#category : #NEAT
}

{ #category : #tests }
NENeatTest >> testBasic [

	| neat diff |
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
	
	neat result evaluate: #(0 0).
	
	self halt. 
]

{ #category : #tests }
NENeatTest >> testBasic2 [

	| neat diff result |
	neat := NEAT new.
	neat fitness: [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 0) #(1 0 0) #(1 1 1)) do: [ :tuple |
			diff := (ind evaluate: (tuple first: 2)) first - tuple last.
			score := score + (diff * diff) ]. 
		(score / -4) asFloat ].
	
"	neat resetConfig.
	neat for: NEConnectionWeightMutationOperation prob: 0.99.
"
	neat numberOfGenerations: 100.
	neat run.
	"self halt. "
	
	result := [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 0) #(1 0 0) #(1 1 1)) do: [ :tuple |
			((ind evaluate: (tuple first: 2)) first - tuple last) abs <= 0.2 ifTrue: [ score := score + 1 ] ]. 
		score ] value: neat result.
	self assert: result equals: 4.
	
"	neat result evaluate: #(0 1)."
	
	
]

{ #category : #tests }
NENeatTest >> testBasic3XOR [

	| neat diff |
	neat := NEAT new.
	neat fitness: [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 1) #(1 0 1) #(1 1 0)) do: [ :tuple |
			diff := (ind evaluate: (tuple first: 2)) first - tuple last.
			score := score + (diff * diff) ]. 
		(score / -4) asFloat ].
	
"	neat resetConfig.
	neat for: NEConnectionWeightMutationOperation prob: 0.99.
"
	neat numberOfGenerations: 100.
	neat run.
	self halt. 
	
	[ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 0) #(1 0 0) #(1 1 1)) do: [ :tuple |
			((ind evaluate: (tuple first: 2)) first - tuple last) abs <= 0.1 ifTrue: [ score := score + 1 ] ]. 
		score ] value: neat result.
	
	neat result evaluate: #(0 0).
	
	
]

{ #category : #tests }
NENeatTest >> testBasic4BinaryToInteger [

	| neat res |
	neat := NEAT new.
	neat numberOfOutputs: 4.
	neat fitness: [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 1) #(1 0 2) #(1 1 3)) do: [ :tuple |
			res := ind evaluate: (tuple first: 2).
			((res indexOf: res max) - 1 - tuple last) = 0 ifTrue: [ score := score + 1 ]
			].
		score ].
	
"	neat resetConfig.
	neat for: NEConnectionWeightMutationOperation prob: 0.99.
"
	neat numberOfGenerations: 100.
	neat run.
	self halt. 
	
	[ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 1) #(1 0 2) #(1 1 3)) do: [ :tuple |
			res := ind evaluate: (tuple first: 2).
			((res indexOf: res max) - 1 - tuple last) = 0 ifTrue: [ score := score + 1 ]
			].
		score ] value: neat result.
"	
	| ttt |	
	ttt := neat result evaluate: #(1 0).
	(ttt indexOf: ttt max) - 1
	
	"
]

{ #category : #tests }
NENeatTest >> testBasic5BinaryToIntegerLarger [

	| neat res |
	neat := NEAT new.
	neat numberOfInputs: 3.
	neat numberOfOutputs: 8.
	neat fitness: [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0 0) #(0 0 1 1) #(0 1 0 2) #(0 1 1 3) #(1 0 0 4) #(1 0 1 5) #(1 1 0 6) #(1 1 1 7)) do: [ :tuple |
			res := ind evaluate: tuple allButLast.
			((res indexOf: res max) - 1 - tuple last) = 0 ifTrue: [ score := score + 1 ]
			].
		score ].
	
"	neat resetConfig.
	neat for: NEConnectionWeightMutationOperation prob: 0.99.
"
	neat numberOfGenerations: 60.
	neat run.
	self halt. 
	
	[ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 1) #(1 0 2) #(1 1 3)) do: [ :tuple |
			res := ind evaluate: (tuple first: 2).
			((res indexOf: res max) - 1 - tuple last) = 0 ifTrue: [ score := score + 1 ]
			].
		score ] value: neat result.
"	
	| ttt |	
	ttt := neat result evaluate: #(1 0).
	(ttt indexOf: ttt max) - 1
	
	"
]

{ #category : #tests }
NENeatTest >> testBasicXOR [

	| neat diff |
	neat := NEAT new.
	neat fitness: [ :ind | 
		| score |
		score := 0.
		#(#(0 0 0) #(0 1 1) #(1 0 1) #(1 1 0)) do: [ :tuple |
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
	
	self halt. 
]
