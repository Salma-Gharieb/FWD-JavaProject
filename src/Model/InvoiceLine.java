/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author hd STORE
 */
public class InvoiceLine {
    
    private String itemName;
    private double price;
    private int count;
    private double itemTotal;
    private InvoiceHeader invoice;

    public InvoiceLine(String itemName, double price, int count, InvoiceHeader invoice) {
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        this.invoice = invoice;
        this.setItemTotal(this.count * this.price);
    }

    public InvoiceHeader getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceHeader invoice) {
        this.invoice = invoice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }
    
    
}
