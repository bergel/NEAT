Class {
	#name : #NEATExample,
	#superclass : #Object,
	#category : #'NEAT-Examples'
}

{ #category : #'as yet unclassified' }
NEATExample >> irisData [
"The execution of this script initializes the variable irisData.
This variable is used in the subsequent scripts of this chapter"
| irisCSV lines tLines irisData neat res |
irisCSV := (ZnEasy get: 'https://agileartificialintelligence.github.io/Datasets/iris.csv') contents.
lines := irisCSV lines. 
lines := lines allButFirst.
tLines := lines collect: [ :l | 
        | ss |
        ss := l substrings: ','.
        (ss allButLast collect: [ :w | w asNumber ]), (Array with: ss last) ].

irisData := tLines collect: [ :row | 
        | l |
        row last = 'setosa' ifTrue: [ l := #( 0 ) ].
        row last = 'versicolor' ifTrue: [ l := #( 1 ) ].
        row last = 'virginica' ifTrue: [ l := #( 2 ) ].
        row allButLast, l ].
    
irisData.


	neat := NEAT new.
	neat numberOfInputs: 4.
	neat numberOfOutputs: 3.
	neat fitness: [ :ind | 
		| score |
		score := 0.
		irisData do: [ :tuple |
			res := ind evaluate: tuple allButLast.
			((res indexOf: res max) - 1 - tuple last) = 0 ifTrue: [ score := score + 1 ].
			].
		score / irisData size ].
	neat numberOfGenerations: 10.
	neat run.
	
neat result evaluate: #(6.7 3.1 5.6 2.4)
]
