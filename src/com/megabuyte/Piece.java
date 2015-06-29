package com.megabuyte;

/**
 * @author timp
 * @since 2015/06/29
 */
public abstract class  Piece {
  Owner owner = null;

  public Piece(Owner owner) {
    this.owner = owner;
  }

  public Owner getOwner() {
    return this.owner;
  }

  public abstract String getName();

  /** Black pieces are uppercase, white lowercase */
  public abstract String getAbbreviation();

  public abstract void validate(int[] from, int[] to);

}

