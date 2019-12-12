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
	#category : #'NEAT-Core'
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
NEConnection >> innovationNumber: anInteger [
	innovationNumber := anInteger
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

{ #category : #printing }
NEConnection >> printOn: str [
	super printOn: str.
	str nextPut: $<.
	in ifNotNil: [ str nextPutAll: in asString ].
	str nextPut: $,.
	out ifNotNil: [ str nextPutAll: out asString ].
	str nextPut: $,.
	innovationNumber ifNotNil: [ str nextPutAll: innovationNumber asString ].
	enabled ifFalse: [ 
		str nextPut: $,.
		str nextPutAll: 'disabled' ]. 
	str nextPut: $>.
]

{ #category : #accessing }
NEConnection >> weight [
	^ weight
]

{ #category : #accessing }
NEConnection >> weight: aNumber [
	weight := aNumber
]
