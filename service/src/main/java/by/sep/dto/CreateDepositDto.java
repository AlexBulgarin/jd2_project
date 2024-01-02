package by.sep.dto;

public class CreateDepositDto extends CreateProductDto{
    private double depositRate;
    private double minSum;

    public CreateDepositDto() {
    }

    public CreateDepositDto(String id, String name, String description,
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
