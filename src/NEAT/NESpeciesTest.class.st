Class {
	#name : #NESpeciesTest,
	#superclass : #TestCase,
	#category : #NEAT
}

{ #category : #tests }
NESpeciesTest >> testBasic [

	| in1 in2 in3 in4 in5 in6 in7 sp |
	in1 := #(1 2 3 5 8 10 15 20).
	in2 := #(1 2 3 5 8 10 15 20 24).
	in3 := #(1 2 4).
	in4 := #(1 2 4 7).
	in5 := #(1 2 4 35).
	in6 := #(1 2 4 7 36).
	in7 := #(1 40).
	
	sp := NESpeciation new.
	sp processSetOfInnovationsNumbers: { in1 . in2 . in3 . in4 . in5 . in6 . in7 }.
	
		
]
