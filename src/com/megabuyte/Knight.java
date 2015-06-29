package com.megabuyte;

import static com.megabuyte.Owner.BLACK;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Knight extends Piece {

  public Knight(Owner o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Knight";
  }
  
  @Override
  public String getAbbreviation() {
    return getOwner() == BLACK ? "K" : "k";
  }

  public void validate(int[] from, int[] to) {
  }

}
