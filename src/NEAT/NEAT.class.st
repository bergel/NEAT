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
		'random',
		'log',
		'speciation',
		'numberOfGenerations'
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

{ #category : #'as yet unclassified' }
NEAT >> crossoverBetween: ind1 and: ind2 [
	^ ind1 fitness > ind2 fitness
		ifTrue: [ NECrossoverOperation new performOn: ind1 and: ind2 ]
		ifFalse: [ NECrossoverOperation new performOn: ind2 and: ind1 ]
]

{ #category : #initialization }
NEAT >> defaultConfig [
	self resetConfig.
	self for: NEConnectionWeightMutationOperation prob: 0.2.
	self for: NEAddConnectionMutationOperation prob: 0.2.
	self for: NEAddNodeMutationOperation prob: 0.01
]

{ #category : #initialization }
NEAT >> doLog [

	log := NELog new.
	log generation: logs size.
	log speciation: speciation.
	log minFitness: (population collect: #fitness) min.
	log maxFitness: (population collect: #fitness) max.
	log averageFitness: ((population collect: #fitness) average asFloat round: 3).
	log bestIndividual: self result.
	logs add: log.
]

{ #category : #'as yet unclassified' }
NEAT >> doMutate: newInd [
	config
		do: [ :cAssoc | 
			random next <= cAssoc value
				ifTrue: [ cAssoc key random: random; performOn: newInd ] ]
]

{ #category : #initialization }
NEAT >> doSpeciation [
	speciation := NESpeciation new.
	speciation process: population.
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
	config add: aClass new -> prob
]

{ #category : #ui }
NEAT >> gtInspectorNbOfSpeciesIn: composite [
	<gtInspectorPresentationOrder: -10>
	composite roassal2
		title: '#Species';
		initializeView: [ 	self visualizeNumberOfSpecies ]
]

{ #category : #ui }
NEAT >> gtInspectorViewIn: composite [
	<gtInspectorPresentationOrder: -10>
	composite roassal2
		title: 'Fitness';
		initializeView: [ 	self visualizeFitness ]
]

{ #category : #initialization }
NEAT >> initialize [
	super initialize.
	self defaultConfig.

	"We have two inputs and one bias"
	numberOfInputs := 2.
	numberOfOutputs := 1.
	
	populationSize := 100.
	random := Random seed: 42.
	
	logs := OrderedCollection new.
	numberOfGenerations := 10
]

{ #category : #initialization }
NEAT >> logs [
	^ logs
]

{ #category : #accessing }
NEAT >> numberOfGenerations [
	^ numberOfGenerations
]

{ #category : #accessing }
NEAT >> numberOfGenerations: anObject [
	numberOfGenerations := anObject
]

{ #category : #accessing }
NEAT >> numberOfInputs [
	^ numberOfInputs
]

{ #category : #accessing }
NEAT >> numberOfInputs: anObject [
	numberOfInputs := anObject
]

{ #category : #accessing }
NEAT >> numberOfOutputs [
	^ numberOfOutputs
]

{ #category : #accessing }
NEAT >> numberOfOutputs: anObject [
	numberOfOutputs := anObject
]

{ #category : #accessing }
NEAT >> populationSize [
	^ populationSize
]

{ #category : #accessing }
NEAT >> populationSize: anObject [
	populationSize := anObject
]

{ #category : #initialization }
NEAT >> resetConfig [
	config := OrderedCollection new
]

{ #category : #accessing }
NEAT >> result [
	| winner |
	winner := population first.
	population do: [ :i | winner fitness < i fitness ifTrue: [ winner := i ] ].
	^ winner
]

{ #category : #initialization }
NEAT >> run [
	self buildInitialPopuplation.
	self doSpeciation.
	self computeFitnesses.
	self doLog.
	
	self runFor: numberOfGenerations

	

]

{ #category : #initialization }
NEAT >> runFor: nbGen [

	'Running the NEAT algorithm'
	displayProgressFrom: 0 to: nbGen
	during: [:bar |
		1 to: nbGen do: [:x | 
			bar value: x.
			self runOneGeneration. ] ].
]

{ #category : #'as yet unclassified' }
NEAT >> runOneGeneration [
	| newPopulation ind1 ind2 newInd |
	self doSpeciation.
	newPopulation := OrderedCollection new.
	populationSize
		timesRepeat: [ 
			
			"The following lines use the crossover"
			random next <= 0.2
				ifTrue: [
					ind1 := self selectIndividual.
					ind2 := self selectIndividual: ind1 species individuals.
					newInd := self crossoverBetween: ind1 and: ind2 ]
				ifFalse: [
					newInd := self selectIndividual copy ].
				
			"newInd := self selectIndividual copy."
			self doMutate: newInd.
			newPopulation add: newInd.
			newInd computeFitness: fitness ].
	population := newPopulation.
	self doLog
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

{ #category : #ui }
NEAT >> visualizeFitness [

	| g d |
	g := RTGrapher new.
	
	"Min fitness"
	d := RTData new.
	d noDot; connectColor: Color red.
	d points: self logs.
	d y: #minFitness.
	g add: d.
	
	"Max fitness"
	d := RTData new.
	d noDot; connectColor: Color blue.
	d points: self logs.
	d y: #maxFitness.
	g add: d.
	
	"Average fitness"
	d := RTData new.
	d noDot; connectColor: Color green.
	d points: self logs.
	d y: #averageFitness.
	g add: d.
	
	^ g

]

{ #category : #ui }
NEAT >> visualizeNumberOfSpecies [
	| g d |
	g := RTGrapher new.
	d := RTData new.
	d points: self logs.
	d y: #numberOfSpecies.
	g add: d.
	^ g
]
