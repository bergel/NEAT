Class {
	#name : #NECrossoverOperation,
	#superclass : #Object,
	#instVars : [
		'random'
	],
	#category : #'NEAT-Core'
}

{ #category : #'as yet unclassified' }
NECrossoverOperation >> NOTWORKINGperformOn: i1 and: i2 [
	"THIS VERSION PRODUCES SOME CYCLES"
	"Return a child that is the result of doing a crossover between i1 and i2"

	"ASSUME that the fitness of i1 is higher than the one of i2"

	| newConnections indexI1 indexI2 shouldIterate newNodes tmpColl nodesFromI2 nodesFromI1 missingNodeIds |
	self assert: [ i1 fitness >= i2 fitness ].
	newNodes := OrderedCollection new.
	newNodes addAll: (i1 nodes collect: #copy).
	newConnections := OrderedCollection new.
	indexI1 := 1.
	indexI2 := 1.
	shouldIterate := true.
	[ shouldIterate ]
		whileTrue: [ | c1 c2 |
			indexI1 := indexI1 min: i1 numberOfConnections.
			indexI2 := indexI2 min: i2 numberOfConnections.
			c1 := i1 connections at: indexI1.
			c2 := i2 connections at: indexI2.
			c1 innovationNumber = c2 innovationNumber
				ifTrue: [ newConnections
						add:
							(i1 random next > 0.5
								ifTrue: [ c1 ]
								ifFalse: [ c2 ]) copy.
					indexI1 := indexI1 + 1.
					indexI2 := indexI2 + 1 ]
				ifFalse: [ shouldIterate := false ].
			(indexI1 >= i1 numberOfConnections
				and: [ indexI2 >= i2 numberOfConnections ])
				ifTrue: [ shouldIterate := false ] ].
	tmpColl := OrderedCollection new.
	tmpColl
		addAll: ((i1 connections allButFirst: indexI1 - 1) collect: #copy).
	tmpColl
		addAll: ((i2 connections allButFirst: indexI2 - 1) collect: #copy).
	tmpColl := tmpColl
		sortedAs: [ :a :b | a innovationNumber < b innovationNumber ].
	newConnections := newConnections , tmpColl.

	"We should also copy the nodes that are missing from the individual"
	nodesFromI2 := tmpColl
		flatCollect: [ :c | 
			{c in.
			c out} ]
		as: Set.
	nodesFromI1 := i1 nodes collect: #id.
	missingNodeIds := nodesFromI2 copyWithoutAll: nodesFromI1.
	missingNodeIds ifNotNil: [ newNodes addAll: (i2 nodesFromIds: missingNodeIds). ].
	
	
	^ NEIndividual new
		nodes: newNodes;
		connections: newConnections;
		random: i1 random;
		sanityCheck
]

{ #category : #'as yet unclassified' }
NECrossoverOperation >> OLDperformOn: i1 and: i2 [
	"Return a child that is the result of doing a crossover between i1 and i2"
	"ASSUME that the fitness of i1 is higher than the one of i2"
	| newConnections indexI1 indexI2 shouldIterate |
	
self halt.
	newConnections := OrderedCollection new.
	indexI1 := 1.
	indexI2 := 1.
	shouldIterate := true.
	[ shouldIterate ] whileTrue: [ 
		| c1 c2 |
		indexI1 := indexI1 min: i1 numberOfConnections.
		indexI2 := indexI2 min: i2 numberOfConnections.
		c1 := i1 connections at: indexI1.
		c2 := i2 connections at: indexI2.
		c1 innovationNumber = c2 innovationNumber
			ifTrue: [ 
				newConnections add: (i1 random next > 0.5 ifTrue: [ c1 ] ifFalse: [ c2 ]).
				indexI1 := indexI1 + 1.
				indexI2 := indexI2 + 1. ]
			ifFalse: [ 
				c1 innovationNumber > c2 innovationNumber 
					ifTrue: [ 
						newConnections add: c2.
						indexI2 := indexI2 + 1.
						indexI2 >= i2 numberOfConnections ifTrue: [ indexI1 := indexI1 + 1 ].
					]
					ifFalse: [ 
						"We skip from the unfit individual"
						newConnections add: c1.
						indexI1 := indexI1 + 1.
						indexI1 >= i1 numberOfConnections ifTrue: [ indexI2 := indexI2 + 1 ].
					]
			].

		(indexI1 >= i1 numberOfConnections and: [ indexI2 >= i2 numberOfConnections ])
			ifTrue: [ shouldIterate := false ]
	].

self halt.
]

{ #category : #'as yet unclassified' }
NECrossoverOperation >> performOn: i1 and: i2 [
	"Return a child that is the result of doing a crossover between i1 and i2"
	"ASSUME that the fitness of i1 is higher than the one of i2"
	| newConnections indexI1 indexI2 shouldIterate newNodes |
	newNodes := OrderedCollection new.
	newNodes addAll: (i1 nodes collect: #copy).

	newConnections := OrderedCollection new.
	indexI1 := 1.
	indexI2 := 1.
	shouldIterate := true.
	(i1 connections notEmpty and: [ i2 connections notEmpty ]) ifFalse: [ ^ NEIndividual new nodes: newNodes; connections: newConnections; random: i1 random ].
	[ shouldIterate ] whileTrue: [ 
		| c1 c2 |
		indexI1 := indexI1 min: i1 numberOfConnections.
		indexI2 := indexI2 min: i2 numberOfConnections.
		c1 := i1 connections at: indexI1.
		c2 := i2 connections at: indexI2.
		c1 innovationNumber = c2 innovationNumber
			ifTrue: [ 
				newConnections add: (i1 random next > 0.5 ifTrue: [ c1 ] ifFalse: [ c2 ]) copy.
				indexI1 := indexI1 + 1.
				indexI2 := indexI2 + 1. ]
			ifFalse: [ shouldIterate := false ].

		(indexI1 >= i1 numberOfConnections and: [ indexI2 >= i2 numberOfConnections ])
			ifTrue: [ shouldIterate := false ]
	].
	newConnections addAll: (i1 connections allButFirst: indexI1 - 1).

	^ NEIndividual new nodes: newNodes; connections: newConnections; random: i1 random
]
