Class {
	#name : #NESpeciesTest,
	#superclass : #TestCase,
	#category : #'NEAT-Tests'
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

{ #category : #tests }
NESpeciesTest >> testBasicOnIndividuals [

	| r individuals i sp i2 |
	r := Random seed: 42.
	individuals := OrderedCollection new.
	
	10 timesRepeat: [ 
		i := NEIndividual new.
		i random: r.
		i addInputNode; addInputNode; addInputNode.
		i addOutputNode.
		individuals add: i.
		
		"For each individual we create 10 clones"
		10 timesRepeat: [ 
			i2 := i copy.
			NEAddNodeMutationOperation new performOn: i2.
			NEAddConnectionMutationOperation new performOn: i2.
			NEAddConnectionMutationOperation new performOn: i2.
			individuals add: i2
		].
	].

	sp := NESpeciation new.
	sp threshold: 4.
	sp process: individuals.
	self assert: sp numberOfSpecies equals: 8.
"	self assert: (sp groups allSatisfy: [ :s | s numberOfIndividuals = 10 ])"

]

{ #category : #tests }
NESpeciesTest >> testBasicOnIndividuals02 [

	| r individuals i sp i2 |
	r := Random seed: 42.
	individuals := OrderedCollection new.
	
	10 timesRepeat: [ 
		i := NEIndividual new.
		i random: r.
		i addInputNode; addInputNode; addInputNode.
		i addOutputNode.
		individuals add: i.
		
		"For each individual we create 20 clones"
		20 timesRepeat: [ 
			i2 := i copy.
			NEAddNodeMutationOperation new performOn: i2.
			"NEAddNodeMutationOperation new performOn: i2."
			r next > 0.5 ifTrue: [ NEAddConnectionMutationOperation new performOn: i2 ].
			
			"NEAddConnectionMutationOperation new performOn: i2."
			individuals add: i2
		].
	].

	sp := NESpeciation new.
	sp process: individuals.
	self assert: sp numberOfSpecies equals: 29.
	"self assert: (sp groups allSatisfy: [ :s | s numberOfIndividuals = 20 ])"

]
