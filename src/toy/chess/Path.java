package toy.chess;

import java.util.ArrayList;

public class Path {

  private ArrayList<Position> positions = new ArrayList<Position>(8);

  public int size() {
    return positions.size();
  }

  public void add(Position p) {
    positions.add(p);
  }


}
