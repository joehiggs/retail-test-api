package com.example.retailtestapi.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class OrderItemRequest {

    @NotEmpty
    private String itemName;

    // not zero or negative?
    private int quantity;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
