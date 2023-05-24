package hra;

public class Items {
    protected int[] itemPosition = new int[2];
    protected String name;

    public Items() {
        this.itemPosition[0] =0 ;
        this.itemPosition[1] =0 ;
        this.name = "";
    }

    public int[] getItemPosition() {
        return itemPosition;
    }

    public void setItemPosition(int[] itemPosition) {
        this.itemPosition = itemPosition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
