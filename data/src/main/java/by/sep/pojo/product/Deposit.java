package by.sep.pojo.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "t_product_deposit")
@PrimaryKeyJoinColumn(name = "fk_product_id")
public class Deposit extends Product {
    @Column(name = "deposit_rate")
    private Double depositRate;
    @Column(name = "min_sum")
    private Double minSum;

    public Deposit() {
    }

    public Deposit(String id, String name, String description,
                   Integer durationInMonth, Double depositRate, Double minSum) {
        super(id, name, description, durationInMonth);
        this.depositRate = depositRate;
        this.minSum = minSum;
    }

    public Double getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(Double depositRate) {
        this.depositRate = depositRate;
    }

    public Double getMinSum() {
        return minSum;
    }

    public void setMinSum(Double minSum) {
        this.minSum = minSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Deposit deposit = (Deposit) o;

        if (!Objects.equals(depositRate, deposit.depositRate)) return false;
        return Objects.equals(minSum, deposit.minSum);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (depositRate != null ? depositRate.hashCode() : 0);
        result = 31 * result + (minSum != null ? minSum.hashCode() : 0);
        return result;
    }
}
