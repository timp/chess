# Chess move validator

This exercise was set as an interview test, with a time limit of four hours.
I decided to embrace being [nerd sniped](https://xkcd.com/356/) and 
see what it would take develop a solution, without researching other solutions.
It is a fair sized toy problem, suitable for a college term assignment and 
has taken me two weeks elapsed time, probably around 40 hours.

The exercise was taken without reference to how the problem 
has previously been addressed. The development methodology was 
often Test Driven, using IntelliJ's coverage tools to ensure tests 
achieved 100% line coverage at an explicit review step for each tranche 
of functionality.

## System Capabilities

The system reads a file of moves and applies them to a correctly setup board.
The system will only allow valid moves for pieces, it ensures a move path is free of other pieces for Queen, Bishop, Rook and Pawn
The system will recognise all valid chess moves:
 - En Passant capture
 - Castling
 - Queening
 - Determine whether a move puts opposing King in Check
 - Determine whether a check is checkmate
 - Determine if a move would place a player's own King in Check and disallow it

The system does not implement the threefold repetition rule as that requires an appeal which cannot be coded in the move language. 

The system has a simple mechanism for checking move validity: 
all possible moves for a piece in that position on an empty board 
are generated and then illegal ones removed.

Check checking then consists of inspecting whether any legal possible move of any 
piece would land on a King's square.

To check for check mate all possible moves are evaluated, 
though importantly not checked for check mate, and if none 
are legal then the player is in check mate.  

## Conclusion
The initial, given, interface implementation leaked its internal representation 
and modified its parameters: 

```java
/**
 * @param from zero based array coordinates for square moved from
 * @param to zero based array coordinates for square moved to
 * @return true whilst there are moves in the file
 **/
public interface UserInput {
    boolean nextMove(int[] from, int[] to) throws UserInputException;
}
```

The internal representation of the board as an array has some small 
advantages by piggybacking on array semantics however in retrospect 
I feel that this was a mistake and that an object model based upon 
English Descriptive Notation would result in cleaner code and a more 
natural fit with how one thinks of Chess. 
