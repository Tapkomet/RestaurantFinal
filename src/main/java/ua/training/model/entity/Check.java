package ua.training.model.entity;

import java.sql.Timestamp;
import java.util.List;

public class Check {
    private int id;
    private Timestamp createTime;
    private User client;
    private User admin;
    private long totalPrice;
    private boolean confirmed;
    private boolean check;
    private boolean paid;
    private long tip;
    private List<Item> items;

    public Check() {
    }

    public Check(int id, Timestamp createTime, User client, long totalPrice, boolean confirmed) {
        this.id = id;
        this.createTime = createTime;
        this.client = client;
        this.totalPrice = totalPrice;
        this.confirmed = confirmed;
    }

    public Check(int id, Timestamp createTime, User client, long totalPrice, boolean confirmed,
                 boolean check, boolean paid, long tip) {
        this.id = id;
        this.createTime = createTime;
        this.client = client;
        this.totalPrice = totalPrice;
        this.confirmed = confirmed;
        this.check = check;
        this.paid = paid;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean getPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public long getTip() {
        return tip;
    }

    public void setTip(long tip) {
        this.tip = tip;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Check{" +
                "id=" + id +
                ", createTime='" + createTime +
                ", client=" + client.getEmail() +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;
        final Check other = (Check) o;
        if (this.id != other.id) return false;
        if (this.createTime == null ? other.createTime != null : !this.createTime.equals(other.createTime))
            return false;
        if (this.client == null ? other.client != null : !this.client.equals(other.client)) return false;
        if (this.admin == null ? other.admin != null : !this.admin.equals(other.admin)) return false;
        if (this.totalPrice != other.totalPrice) return false;
        if (this.check != other.check) return false;
        if (this.paid != other.paid) return false;
        if (this.tip != other.tip) return false;
        if (this.items == null ? other.items != null : !this.items.equals(other.items)) return false;
        return this.confirmed == other.confirmed;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + this.id;
        result = result * PRIME + (this.createTime == null ? 0 : this.createTime.hashCode());
        result = result * PRIME + (this.client == null ? 0 : this.client.hashCode());
        result = result * PRIME + (this.admin == null ? 0 : this.admin.hashCode());
        result = result * PRIME + (int) (this.totalPrice ^ (this.totalPrice >>> 32));
        result = result * PRIME + (int) (this.tip ^ (this.tip >>> 32));
        result = result * PRIME + (confirmed ? 1 : 0);
        result = result * PRIME + (check ? 1 : 0);
        result = result * PRIME + (paid ? 1 : 0);
        result = result * PRIME + (this.items == null ? 0 : this.items.hashCode());
        return result;
    }

    public static class Builder {

        private int id;
        private Timestamp createTime;
        private User client;
        private User admin;
        private long totalPrice;
        private boolean confirmed;
        private boolean check;
        private boolean paid;
        private long tip;
        private List<Item> items;

        public Builder(User admin) {
            this.client = client;
        }

        public Builder toAdmin(User admin) {
            this.admin = admin;
            return this;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder totalPrice(long totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder isConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
            return this;
        }

        public Builder isCheck(boolean check) {
            this.check = check;
            return this;
        }

        public Builder isPaid(boolean paid) {
            this.paid = paid;
            return this;
        }
        
        public Builder tip(long tip) {
            this.tip = tip;
            return this;
        }

        public Builder withItems(List<Item> items) {
            this.items = items;
            return this;
        }

        public Check build() {
            Check check = new Check();
            check.id = this.id;
            check.createTime = this.createTime;
            check.client = this.client;
            check.admin = this.admin;
            check.totalPrice = this.totalPrice;
            check.confirmed = this.confirmed;
            check.check = this.check;
            check.paid = this.paid;
            check.tip = this.tip;
            check.items = this.items;
            return check;
        }
    }
}
