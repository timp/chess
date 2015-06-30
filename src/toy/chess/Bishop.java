package toy.chess;

/**
 * @author timp
 * @since 2015/06/29
 */
public class Bishop extends Piece {

  public Bishop(Player o) {
    super(o);
  }

  @Override
  public String getName() {
    return "Bishop";
  }


  @Override
  public String getAbbreviation() {
    return getOwner() == Player.BLACK ? "B" : "b";
  }

  @Override
  public void validate(Position from, Position to) {

  }


}
