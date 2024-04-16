public class Node {
    int row;
    int col;
    int g;
    int f;

    public Node(int row,int col,int g,int h){
        this.row = row;
        this.col = col;
        this.g = g;
        this.f = g + h;
    }
}
