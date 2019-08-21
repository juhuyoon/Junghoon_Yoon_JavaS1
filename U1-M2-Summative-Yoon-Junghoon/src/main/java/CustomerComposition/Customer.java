package CustomerComposition;

public class Customer {
    private String firstName;
    private String lastname;
    private String email;
    //Phone number should be a string
    private String phoneNumber;
    private boolean rewardsMember;

    private ShippingAddress shippingAddress;
    private BillingAddress billingAddress;

    public Customer(String firstName, String lastname, String email, String phoneNumber, boolean rewardsMember, ShippingAddress shippingAddress, BillingAddress billingAddress) {
        this.firstName = firstName;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.rewardsMember = rewardsMember;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isRewardsMember() {
        return rewardsMember;
    }

    public void setRewardsMember(boolean rewardsMember) {
        this.rewardsMember = rewardsMember;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }
}
