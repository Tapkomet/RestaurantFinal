package ua.training.model.entity;

import java.sql.Timestamp;

public class Check {
    private int id;
    private Timestamp createTime;
    private User client;
    private User admin;
    private long totalPrice;
    private boolean confirmed;

    public Check() {
    }

    public Check(int id, Timestamp createTime, User client, long totalPrice, boolean confirmed) {
        this.id = id;
        this.createTime = createTime;
        this.client = client;
        this.totalPrice = totalPrice;
        this.confirmed = confirmed;
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

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
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
        if (this.createTime == null ? other.createTime != null : !this.createTime.equals(other.createTime)) return false;
        if (this.client == null ? other.client != null : !this.client.equals(other.client)) return false;
        if (this.totalPrice != other.totalPrice) return false;
        return this.confirmed == other.confirmed;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + this.id;
        result = result * PRIME + (this.createTime == null ? 0 : this.createTime.hashCode());
        result = result * PRIME + (this.client == null ? 0 : this.client.hashCode());
        result = result * PRIME + (int) (this.totalPrice ^ (this.totalPrice >>> 32));
        result = result * PRIME + (confirmed ? 1 : 0);
        return result;
    }

    public static class Builder {

        private int id;
        private Timestamp createTime;
        private User client;
        private long totalPrice;
        private boolean confirmed;

        public Builder(User client) {
            this.client = client;
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder totalPrice(long totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder withConfirmed(boolean confirmed) {
            this.confirmed = confirmed;
            return this;
        }

        public Check build() {
            Check check = new Check();
            check.id = this.id;
            check.createTime = this.createTime;
            check.client = this.client;
            check.totalPrice = this.totalPrice;
            check.confirmed = this.confirmed;
            return check;
        }
    }
}
