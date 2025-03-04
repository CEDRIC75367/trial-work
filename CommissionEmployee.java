public class CommissionEmployee {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private double grossSales;
    private double commissionRate;


    public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber,
                              double grossSales, double commissionRate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        setGrossSales(grossSales);
        setCommissionRate(commissionRate);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public double getGrossSales() {
        return grossSales;
    }

    public void setGrossSales(double grossSales) {
        if (grossSales >= 0.0) {
            this.grossSales = grossSales;
        } else {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        if (commissionRate > 0.0 && commissionRate <= 1.0) {
            this.commissionRate = commissionRate;
        } else {
            throw new IllegalArgumentException("Commission rate must be > 0.0 and <= 1.0");
        }
    }

    // Method to calculate earnings
    public double earnings() {
        return grossSales * commissionRate;
    }


    @Override
    public String toString() {
        return String.format("Commission Employee: %s %s%nSocial Security Number: %s%nGross Sales: %.2f%n" +
                        "Commission Rate: %.2f",
                firstName, lastName, socialSecurityNumber, grossSales, commissionRate);
    }


    public static void main(String[] args) {
        try {

            CommissionEmployee employee = new CommissionEmployee(
                    "Cedric", "Raichand", "953-81-2694", 10000.0, 0.1);


            System.out.println(employee);


            employee.setGrossSales(15000.0);
            employee.setCommissionRate(0.15);


            System.out.println("\nUpdated Employee Details:");
            System.out.println(employee);


            System.out.printf("\nEmployee Earnings: %.2f%n", employee.earnings());


            System.out.println("\nTesting Invalid Values:");
            employee.setGrossSales(-5000.0);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
        }
    }
}