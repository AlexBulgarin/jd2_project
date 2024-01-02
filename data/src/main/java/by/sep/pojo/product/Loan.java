package by.sep.pojo.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "t_product_loan")
@PrimaryKeyJoinColumn(name = "fk_product_id")
public class Loan extends Product {
    @Column(name = "loan_rate", nullable = false)
    private Double loanRate;
    @Column(name = "max_sum", nullable = false)
    private Double maxSum;

    public Loan() {
    }

    public Loan(String id, String name, String description,
                Integer durationInMonth, Double loanRate, Double maxSum) {
        super(id, name, description, durationInMonth);
        this.loanRate = loanRate;
        this.maxSum = maxSum;
    }

    public Double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(Double loanRate) {
        this.loanRate = loanRate;
    }

    public Double getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(Double maxSum) {
        this.maxSum = maxSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Loan loan = (Loan) o;

        if (!Objects.equals(loanRate, loan.loanRate)) return false;
        return Objects.equals(maxSum, loan.maxSum);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (loanRate != null ? loanRate.hashCode() : 0);
        result = 31 * result + (maxSum != null ? maxSum.hashCode() : 0);
        return result;
    }
}
