Class {
	#name : #NEAT,
	#superclass : #Object,
	#instVars : [
		'config',
		'populationSize',
		'population',
		'numberOfInputs',
		'numberOfOutputs',
		'logs',
		'fitness',
		'random'
	],
	#category : #NEAT
}

{ #category : #initialization }
NEAT >> buildInitialPopuplation [
	population := OrderedCollection new.
	populationSize timesRepeat: [ 
		| i |
		i := NEIndividual new.
		i random: random.
		numberOfInputs timesRepeat: [ i addInputNode ].
		numberOfOutputs timesRepeat: [ i addOutputNode ].
		population add: i
		]
]

{ #category : #initialization }
NEAT >> computeFitnesses [
	population do: [ :i | i computeFitness: fitness ]
]

{ #category : #initialization }
NEAT >> defaultConfig [
	config := Dictionary new.
	self for: NEConnectionWeightMutationOperation prob: 0.3.
	self for: NEAddConnectionMutationOperation prob: 0.2.
	self for: NEAddNodeMutationOperation prob: 0.2
]

{ #category : #accessing }
NEAT >> fitness [
	^ fitness
]

{ #category : #accessing }
NEAT >> fitness: anObject [
	fitness := anObject
]

{ #category : #initialization }
NEAT >> for: aClass prob: prob [
	config at: aClass new put: prob
]

{ #category : #initialization }
NEAT >> initialize [
	super initialize.
	self defaultConfig.

	numberOfInputs := 2.
	numberOfOutputs := 1.
	
	populationSize := 100.
	random := Random seed: 42.
	
	logs := OrderedCollection new
]

{ #category : #initialization }
NEAT >> logs [
	^ logs
]

{ #category : #initialization }
NEAT >> run [
	| sp log |
	self buildInitialPopuplation.
	sp := NESpeciation new.
	sp process: population.
	self computeFitnesses.
	log := NELog new.
	log generation: 0.
	log speciation: sp.
	log minFitness: (population collect: #fitness) min.
	log maxFitness: (population collect: #fitness) max.
	logs add: log.
	self runOneGeneration
]

{ #category : #'as yet unclassified' }
NEAT >> runOneGeneration [
	| newPopulation indCopy sp ind ind1 ind2 ind1Copy ind2Copy newInd |
	newPopulation := OrderedCollection new.
	
	populationSize timesRepeat: [
		ind1 := self selectIndividual.
		ind2 := self selectIndividual: ind species individuals.
		ind1Copy := ind1 copy.
		ind2Copy := ind2 copy.
		
		"We do the crossover"
		ind1 fitness > ind2 fitness 
			ifTrue: [ newInd := NECrossoverOperation new performOn: ind1 and: ind2 ]
			ifFalse: [ newInd := NECrossoverOperation new performOn: ind2 and: ind1 ].
		 
		 
		"We do mutations"
		config associationsDo: [ :cAssoc |
			random next <= cAssoc value ifTrue: [ cAssoc key performOn: newInd ] ].
		newPopulation add: newInd.
		newInd computeFitness: fitness.
	].
	
	sp := NESpeciation new.
	sp process: newPopulation.

	self halt.
	population := newPopulation
]

{ #category : #'as yet unclassified' }
NEAT >> selectIndividual [
	^ self selectIndividual: population
]

{ #category : #'as yet unclassified' }
NEAT >> selectIndividual: someIndividuals [
	| i k winner |
	k := 4.
	winner := someIndividuals atRandom: random.
	k timesRepeat: [ 
		"Maybe have a method NEIndividual>>sibblings"
		i := winner species individuals atRandom: random.
		winner fitness < i fitness ifTrue: [ winner := i ]
	].
	^ winner
]
