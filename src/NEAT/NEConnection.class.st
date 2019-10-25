Class {
	#name : #NEConnection,
	#superclass : #Object,
	#instVars : [
		'in',
		'out',
		'weight',
		'enabled',
		'innovationNumber'
	],
	#category : #NEAT
}

{ #category : #accessing }
NEConnection >> in [
	^ in
]

{ #category : #evaluating }
NEConnection >> in: aValue [
	in := aValue
]

{ #category : #accessing }
NEConnection >> innovationNumber [
	^ innovationNumber
]

{ #category : #accessing }
NEConnection >> innovationNumber: anObject [
	innovationNumber := anObject
]

{ #category : #evaluating }
NEConnection >> isEnabled [
	^ enabled 
]

{ #category : #evaluating }
NEConnection >> makeDisabled [
	enabled := false
]

{ #category : #evaluating }
NEConnection >> makeEnabled [
	enabled := true
]

{ #category : #accessing }
NEConnection >> out [
	^ out
]

{ #category : #accessing }
NEConnection >> out: anObject [
	out := anObject
]

{ #category : #accessing }
NEConnection >> weight [
	^ weight
]

{ #category : #accessing }
NEConnection >> weight: anObject [
	weight := anObject
]
