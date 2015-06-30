package toy.chess;
/*
 * @author timp
 * @since 2015/06/29
 */
public class InvalidChessCoordinate extends RuntimeException  {
    public InvalidChessCoordinate(int coord) {
        super("Invalid coordinate: " + coord);
    }
}
