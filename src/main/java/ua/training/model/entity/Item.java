package ua.training.model.entity;

import lombok.Data;

import java.util.Comparator;

@Data
public class Item {
    private int id;
    private String name;
    private long price;
    private int number;
    private Check check;
    private String category;

    public static Comparator<Item> ItemIdComparator = (s1, s2) -> {
        int id1 = s1.getId();
        int id2 = s2.getId();
        return id1 - id2;
    };

    public static Comparator<Item> ItemNameComparator = (s1, s2) -> {
        String name1 = s1.getName().toUpperCase();
        String name2 = s2.getName().toUpperCase();
        return name1.compareTo(name2);
    };

    public static Comparator<Item> ItemPriceComparator = (s1, s2) -> {
        long price1 = s1.getPrice();
        long price2 = s2.getPrice();
        return Long.compare(price1, price2);
    };

    public static Comparator<Item> ItemCategoryComparator = (s1, s2) -> {
        String category1 = s1.getCategory().toUpperCase();
        String category2 = s2.getCategory().toUpperCase();
        return category1.compareTo(category2);
    };

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name +
                ", price=" + price +
                ", number=" + number +
                ", category=" + category +
                '}';
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + this.id;
        result = result * PRIME + (this.name == null ? 0 : this.name.hashCode());
        result = result * PRIME + this.number;
        result = result * PRIME + (int) (this.price ^ (this.price >>> 32));
        result = result * PRIME + (this.category == null ? 0 : this.category.hashCode());
        return result;
    }


    public static class Builder {

        private int id;
        private String name;
        private long price;
        private int number;
        private Check check;
        private String category;

        public Builder(int id) {
            this.id = id;
        }

        public Builder(String name) {
            this.name = name;
        }

        public Builder itemName(String name) {
            this.name = name;
            return this;
        }

        public Builder price(long price) {
            this.price = price;
            return this;
        }

        public Builder number(int number) {
            this.number = number;
            return this;
        }

        public Builder fromCheck(Check check) {
            this.check = check;
            return this;
        }

        public Builder category(String category) {
            this.category = category;
            return this;
        }


        public Item build() {
            Item item = new Item();
            item.id = this.id;
            item.name = this.name;
            item.price = this.price;
            item.number = this.number;
            item.category = this.category;
            return item;
        }
    }

}
