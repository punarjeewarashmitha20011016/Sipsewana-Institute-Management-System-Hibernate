package dto;

public class RegistrationFeeDTO {
    private double fee;

    public RegistrationFeeDTO() {
    }

    public RegistrationFeeDTO(double fee) {
        this.fee = fee;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "RegistrationFeeDTO{" +
                "fee=" + fee +
                '}';
    }
}
