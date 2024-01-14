package by.sep.dto;

public class DepositDto extends ProductDto {
    private double depositRate;
    private double minSum;

    public DepositDto() {
    }

    public DepositDto(String id, String name, String description,
                      int durationInMonth, double depositRate, double minSum) {
        super(id, name, description, durationInMonth);
        this.depositRate = depositRate;
        this.minSum = minSum;
    }

    public double getDepositRate() {
        return depositRate;
    }

    public void setDepositRate(double depositRate) {
        this.depositRate = depositRate;
    }

    public double getMinSum() {
        return minSum;
    }

    public void setMinSum(double minSum) {
        this.minSum = minSum;
    }
}
