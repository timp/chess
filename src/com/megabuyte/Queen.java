package com.megabuyte;

import static com.megabuyte.Owner.BLACK;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Queen extends Piece {

  public Queen(Owner o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Queen";
  }

  @Override
  public String getAbbreviation() {
    return getOwner() == BLACK ? "Q" : "q";
  }

  @Override
  public void validate(Position from, Position to) {

  }


}
