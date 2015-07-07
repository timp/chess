# Chess move validator

This exercise was set as an interview test, with a time limit of four hours.
It has taken considerably more. 

The tests achieve 100% line coverage.

## Done

The system reads a file of moves and applies them to a correctly setup board.
The system will only allow valid moves for pieces, it ensures a move path is free of other pieces for Queen, Bishop, Rook and Pawn
The system will recognise:
 - En Passant capture
 - Castling
 - Queening

The system does not implement the threefold repetition rule as that requires an appeal which cannot be coded in the move language. 


## To Do 
 - Determine if a move would place a player's own King in Check
 - Determine whether a move puts opposing King in Check
 - Determine Checkmate
 