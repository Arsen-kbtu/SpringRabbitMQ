package org.springframework.amqp.tutorials.tut2.models;

public class Apartment {
    private String address;
    private int price;
    private int size;

    public Apartment(String address, int price, int size) {
        this.address = address;
        this.price = price;
        this.size = size;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "address='" + address + '\'' +
                ", price=" + price +
                ", size=" + size +
                '}';
    }
}
