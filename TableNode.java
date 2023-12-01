public class TableNode {
    private int capacity;
    TableNode left;
    TableNode right;


    public TableNode(int capacity){
        this.capacity =capacity;
        this.left=null;
        this.left=null;
    }


    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

     public TableNode getLeft() {
        return left;
    }

    public void setLeft(TableNode left) {
        this.left = left;
    }

    public TableNode getRight() {
        return right;
    }

    public void setRight(TableNode right) {
        this.right = right;
    }




    
}