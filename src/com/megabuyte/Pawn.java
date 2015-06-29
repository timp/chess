package com.megabuyte;

import static com.megabuyte.Owner.BLACK;
/**
 * @author timp
 * @since 2015/06/29
 */
public class Pawn extends Piece {

  public Pawn(Owner o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Pawn";
  }

  @Override
  public String getAbbreviation() {
    return getOwner() == BLACK ? "P" : "p";
  }

  @Override
  public void validate(Position from, Position to) {

  }


}
