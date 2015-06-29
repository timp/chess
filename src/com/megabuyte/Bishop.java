package com.megabuyte;

import static com.megabuyte.Owner.BLACK;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Bishop extends Piece {

  public Bishop(Owner o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Bishop";
  }


  @Override
  public String getAbbreviation() {
    return getOwner() == BLACK ? "B" : "b";
  }

  @Override
  public void validate(Position from, Position to) {

  }


}
