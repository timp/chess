# Java Programming Toy

## Introduction

This exercise is intended to test design and programming ability in Java.

As far as is possible, it intends to replicate the normal working environment.
As such, it is not designed to be a test of memory.
Manuals etc can - and should -  be referred to.
Recruitment is a two-way process: this exercise is also intended to allow
candidates to sample the working environment.

The tasks consists of a number of steps.
Each step should be completed before the next one is attempted.
It is not expected that in the time allocated that all steps will be completed.

There are no rigid assessment criteria.
We will be looking at a number of factors including overall design,
code readability, maintainability, amount of work completed etc.

The person administrating your test will be at hand to answer any questions.
Please do not hesitate to contact this person if you get stuck at any stage
or if any part of  these instructions are unclear.

## Problem Definition

You have been asked to write a computer program to allow two human players
to play chess.
If you are not familiar with the rules of chess please ask your test
administrator!

An implementation of the following interface which gives the next move
of each player is provided :-

```java
public interface UserInput {
/**
	*/

	public boolean nextMove(int[]from, int[]to) throws UserInputException;

}
```

An implementation of this interface, called `UserInputFile`,
which reads moves from a text file whose name is passed in the constructor
is provided.  Some sample text files are also provided.
Your test administrator will advice you where these are on the test machine.

###Step 1.

Create a program which creates an instance of `UserInputFile` and
repeatedly calls the `nextMove()` method and outputs the desired moves
onto standard out.  Sample output could be :-

  	Player 1: move from 4,1 to 4,3
  	Player 2: move from 4,6 to 4,4

###Step 2.

Model and create a board object containing pieces.
Initialise the board with the default Chess starting position.

###Step 3.

Update the state of the board according to the moves returned by `nextMove()`.

###Step 4.

Modify the program to test the validity of each move
(at this stage do not worry about the special case of a player being
in "check").

###Step 5.

Modify the program to output (in simple ASCII form) the board after each move.  It is suggested that player 1 is represented by upper-case characters and player 2 by lower-case characters.

###Step 6.

Modify the program to test for and take appropriate action on
"check" and "checkmate".


