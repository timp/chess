package com.megabuyte;

import static com.megabuyte.Owner.BLACK;

/**
 * @author timp
 * @since 2015/06/29
 */
public class King extends Piece {

  public King(Owner o) {
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
  public void validate(int[] from, int[] to) {
    
  }


}
