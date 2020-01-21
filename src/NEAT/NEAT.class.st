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
		'numberOfGenerations',
		'shouldUseElitism',
		'speciationThreshold',
		'individualClass'
	],
	#category : #'NEAT-Core'
}

{ #category : #initialization }
NEAT >> buildInitialPopulation [
	population := OrderedCollection new.
	populationSize timesRepeat: [ 
		| i |
		i := individualClass new.
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

{ #category : #'as yet unclassified' }
NEAT >> crossoverRate [
	| t |
	t := config detect: [ :assoc | assoc key isKindOf: NECrossoverOperation ] ifNone: [ ^ 0 ].
	^ t value
]

{ #category : #'as yet unclassified' }
NEAT >> currentGeneration [
	^ self logs size
	
]

{ #category : #initialization }
NEAT >> defaultConfig [
	self resetConfig.
	self for: NEConnectionWeightMutationOperation prob: 0.2.
	self for: NEAddConnectionMutationOperation prob: 0.2.
	self for: NEAddNodeMutationOperation prob: 0.01.
	self for: NECrossoverOperation prob: 0.2
]

{ #category : #initialization }
NEAT >> doLog [
	self assert: [ speciation notNil ].
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
			(cAssoc key isKindOf: NEAbstractMutationOperation) ifTrue: [
				random next <= cAssoc value
					ifTrue: [ cAssoc key random: random; performOn: newInd ] ] ]
]

{ #category : #initialization }
NEAT >> doNotUseElitism [
	shouldUseElitism := false
]

{ #category : #initialization }
NEAT >> doSpeciation [
	speciation := NESpeciation new.
	speciation threshold: speciationThreshold.
	speciation process: population.
]

{ #category : #initialization }
NEAT >> doUseElitism [
	shouldUseElitism := true
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
NEAT >> for: aClass [
	config do: [ :assoc | (assoc key isKindOf: aClass) ifTrue: [ ^ assoc value ] ].
	^ 0
]

{ #category : #initialization }
NEAT >> for: aClass prob: prob [
	config do: [ :assoc | (assoc key isKindOf: aClass) ifTrue: [ assoc value: prob. ^ self ] ].
	
	config add: aClass new -> prob
]

{ #category : #ui }
NEAT >> gtInspectorFitnessIn: composite [
	<gtInspectorPresentationOrder: -100>
	composite roassal2
		title: 'Fitness';
		initializeView: [ 	self visualizeFitness ]
]

{ #category : #'as yet unclassified' }
NEAT >> gtInspectorGenerationsIn: composite [
	<gtInspectorPresentationOrder: -10>
	composite roassal2
		title: 'Generations';
		initializeView: [ 	self visualizeGenerations ]
]

{ #category : #ui }
NEAT >> gtInspectorNbOfSpeciesIn: composite [
	<gtInspectorPresentationOrder: -10>
	composite roassal2
		title: '#Species';
		initializeView: [ 	self visualizeNumberOfSpecies ]
]

{ #category : #'as yet unclassified' }
NEAT >> gtInspectorSpeciesEvolutionIn: composite [
	<gtInspectorPresentationOrder: -10>
	composite roassal2
		title: 'Evolution';
		initializeView: [ 	self visualizeSpeciesEvolution ]
]

{ #category : #ui }
NEAT >> gtInspectorViewIn: composite [
	<gtInspectorPresentationOrder: -10>
	composite roassal2
		title: 'Result history';
		initializeView: [ 	self visualizeResultHistory ]
]

{ #category : #initialization }
NEAT >> individualClass: aClass [
	"Set the class to be used to create an individual.
	The default value is NEIndividual, but it could be a subclass of it."
	individualClass := aClass
]

{ #category : #initialization }
NEAT >> initialize [
	super initialize.
	self defaultConfig.

	"We have two inputs and one bias"
	numberOfInputs := 2.
	numberOfOutputs := 1.
	
	populationSize := 150.
	random := Random seed: 42.
	
	logs := OrderedCollection new.
	numberOfGenerations := 10.
	
	self individualClass: NEIndividual.
	
	self speciationThreshold: 3.
	self doUseElitism
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

{ #category : #'as yet unclassified' }
NEAT >> releaseHistory [

	self logs allButLast do: #release
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
	self buildInitialPopulation.
	self doSpeciation.
	self computeFitnesses.
	self doLog.
	
	self runFor: numberOfGenerations.
	self doSpeciation.
	

]

{ #category : #initialization }
NEAT >> runFor: nbGen [

	'Running the NEAT algorithm'
	displayProgressFrom: 1 to: nbGen
	during: [:bar |
		1 to: nbGen do: [:x | 
			bar value: x.
			self runOneGeneration. ] ].
]

{ #category : #'as yet unclassified' }
NEAT >> runOneGeneration [
	| newPopulation ind1 ind2 newInd numberOfIndividualToCreate |
	self doSpeciation.
	newPopulation := OrderedCollection new.
	numberOfIndividualToCreate := (shouldUseElitism and: [ self currentGeneration > 1 ]) 
												ifTrue: [ newPopulation add: self logs last bestIndividual copy. populationSize - 1 ] 
												ifFalse: [ populationSize ].
	numberOfIndividualToCreate
		timesRepeat: [ 
			"The following lines use the crossover"
			random next <= self crossoverRate
				ifTrue: [
					ind1 := self selectIndividual.
					ind2 := self selectIndividual: ind1 species individuals.
					newInd := self crossoverBetween: ind1 and: ind2.
					newInd addParent: ind1; addParent: ind2.
					ind1 addChildren: newInd.
					ind2 addChildren: newInd ]
				ifFalse: [
					| i |
					i := self selectIndividual.
					newInd := i copy.
					newInd addParent: i.
					i addChildren: newInd ].

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

{ #category : #accessing }
NEAT >> speciationThreshold [
	^ speciationThreshold
]

{ #category : #accessing }
NEAT >> speciationThreshold: anInteger [
	"Per default, it is 3"
	speciationThreshold := anInteger
]

{ #category : #ui }
NEAT >> visualizeFitness [
	"Visualizing the max, min, and average fitness for each generation"
	| g d |
	g := RTGrapher new.
	
	"Min fitness"
	d := RTData new.
	d label: 'Minimum fitness'.
	d noDot; connectColor: Color red.
	d points: logs.
	d y: #minFitness.
	g add: d.
	
	"Max fitness"
	d := RTData new.
	d label: 'Maximum fitness'.
	d noDot; connectColor: Color blue.
	d points: logs.
	d y: #maxFitness.
	g add: d.
	
	"Average fitness"
	d := RTData new.
	d label: 'Average fitness'.
	d noDot; connectColor: Color green.
	d points: logs.
	d y: #averageFitness.
	g add: d.
	
	g axisX noDecimal; title: 'Generation'.
	g axisY title: 'Fitness'.
	g legend addText: 'Fitness evolution'.
	^ g
]

{ #category : #'as yet unclassified' }
NEAT >> visualizeGenerations [
	| b allIndividuals t |
	b := RTMondrian new.
	allIndividuals := OrderedCollection new.
	b
		nodes: (self logs allButFirst collect: #speciation)
		forEach: [ :spe | 
			b shape box size: #numberOfChildren.
			t := (spe population reverseSortedAs: #numberOfChildren)
				select: [ :ii | ii numberOfChildren >= 3 ].
			allIndividuals addAll: t.
			b nodes: t ].
	b layout verticalLine.
	b shape line color: Color blue trans.
	b edges
		objects: allIndividuals;
		connectToAll: #children.
	b normalizer
		objects: allIndividuals;
		normalizeColor: #fitness
			using:
			{Color red.
			Color gray}
			using: #squared.
	^ b
]

{ #category : #ui }
NEAT >> visualizeNumberOfSpecies [
	| g d |
	g := RTGrapher new.
	d := RTData new.
	d points: self logs.
	d y: #numberOfSpecies.
	g add: d.
	g axisX title: 'Generation'; noDecimal.
	g axisY title: '# species'.
	^ g
]

{ #category : #ui }
NEAT >> visualizeResultHistory [
	| b |
	b := RTMondrian new.
	b shape box size: #numberOfIndividuals.
	b interaction popupText: #averageFitness.
	b nodes: self result species withAllParentSpecies asSet.
	b edges connectToAll: #parentSpecies.
	b layout tree.
	b normalizer normalizeColor: #averageFitness.
	^ b
]

{ #category : #'as yet unclassified' }
NEAT >> visualizeSpeciesEvolution [
	"Species evolution"

	| allSpecies allSpeciations b |
	allSpecies := OrderedCollection new.
	allSpeciations := self logs collect: #speciation.
	b := RTMondrian new.
	b
		nodes: allSpeciations
		forEach: [ :sp | 
			allSpecies addAll: sp groups.
			b shape box size: #numberOfIndividuals.
			b nodes: sp groups ].
	b layout verticalLine alignCenter.
	b shape line
		color: Color blue trans;
		withShorterVerticalAttachPoint.
	b edges
		objects: allSpecies;
		connectToAll: #childrenSpecies.
	b normalizer
		objects: allSpecies;
		normalizeColor: #maxFitness.
	^ b
]
