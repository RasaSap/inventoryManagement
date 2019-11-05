package inventoryManagement;

import java.util.Date;

public class Item {
    private String itemName;
    private long code;
    private int quantity;
    private Date expirationDate;

    public Item(String itemName, long code, int quantity, Date expirationDate) {
        this.itemName = itemName;
        this.code = code;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", code='" + code + '\'' +
                ", quantity='" + quantity + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
