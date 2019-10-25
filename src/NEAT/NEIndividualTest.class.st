Class {
	#name : #NEIndividualTest,
	#superclass : #TestCase,
	#category : #NEAT
}

{ #category : #tests }
NEIndividualTest >> testExamplePaper [

	| ind network |
	ind := NEIndividual new.
	ind addInputNode; addInputNode; addInputNode; addOutputNode; addHiddenNode.
	network := ind buildNetwork
]
