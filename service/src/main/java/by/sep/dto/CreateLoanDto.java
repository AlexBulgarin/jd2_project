package by.sep.dto;

public class CreateLoanDto extends CreateProductDto{
    private double loanRate;
    private double maxSum;

    public CreateLoanDto() {
    }

    public CreateLoanDto(String id, String name, String description,
                         int durationInMonth, double loanRate, double maxSum) {
        super(id, name, description, durationInMonth);
        this.loanRate = loanRate;
        this.maxSum = maxSum;
    }

    public double getLoanRate() {
        return loanRate;
    }

    public void setLoanRate(double loanRate) {
        this.loanRate = loanRate;
    }

    public double getMaxSum() {
        return maxSum;
    }

    public void setMaxSum(double maxSum) {
        this.maxSum = maxSum;
    }
}
