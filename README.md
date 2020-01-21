# Neuroevolution and NEAT

[Neuroevolution](https://en.wikipedia.org/wiki/Neuroevolution) is a fantastic area that belongs to artificial intelligence. Neuroevolution is about _evolving_ neural networks to solve a particular problem. Neuroevolution differs from classical deep learning in the way a satisfactory model is obtained. Classical learning techniques for Deep Learning (e.g., backpropagation) are about _learning_, while Neuroevolution is about _evolving_ a model. 

NEAT is maybe the most popular neuroevolution algorithm. A description of NEAT may be found in the paper [Evolving Neural Networks through Augmenting Topologies](http://nn.cs.utexas.edu/downloads/papers/stanley.ec02.pdf).

# NEAT4Pharo

This repository provides an implementation of NEAT for the [Pharo programming language](http://pharo.org). Many implementations of NEAT exist in a wide range of programming languages. The advantages of NEAT4Pharo is to have a relativaly small amount of source code (< 1000 LOC), and it offers interactive visualization to give a better understanding of how the evolution was carried out. 

# Loading NEAT4Pharo

Execute the following script to load the [Roassal2 visualization engine](https://github.com/ObjectProfile/Roassal2) and NEAT4Pharo:

```Smalltalk
Metacello new
    baseline: 'Roassal2';
    repository: 'github://ObjectProfile/Roassal2/src';
    load.
Gofer new
    url: 'github://bergel/NEAT/src';
    package: 'NEAT';
    load.
```

# Example

One of the introductory example in neural network, is to build a neural network that expresses the XOR logical gate. We can do so using NEAT. Consider the following script:

```Smalltalk
neat := NEAT new.
neat fitness: [ :ind | 
	| score |
	score := 0.
	#(#(0 0 0) #(0 1 0) #(1 0 1) #(1 1 0)) do: [ :tuple |
		diff := (ind evaluate: (tuple first: 2)) first - tuple last.
		score := score + (diff * diff) ]. 
	(score / -4) asFloat ].
neat run
```

The script configure the NEAT algorithm to handles individual (i.e., neural networks) having two inputs and one output. The XOR logical gates takes two arguments and return one value. So, a neural network with 2 inputs and 1 output is sufficent to express the XOR.

Evaluating the script gives the following output:
![alt text](https://raw.githubusercontent.com/bergel/NEAT/branch/images/XOR-example.png)


# Wanna to chat about it? 

Join the [Pharo discord server](http://pharo.org/community) and join the `#ia` channel. You are also very welcome to post issues to this GitHub repository.



