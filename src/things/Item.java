package things;

public class Item {
    protected int size = 0;
    protected String name;

    public Item(String name, int size){
        this.name = name;
        this.size = size;
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }
}
