package CustomerComposition;

public class App {
    public static void main(String[] args) {

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setStreet1("123 North Ave");
        shippingAddress.setStreet2("123 house");
        shippingAddress.setCity("Decatur");
        shippingAddress.setState("GA");
        shippingAddress.setZip(30033);

        BillingAddress billingAddress = new BillingAddress();


        billingAddress.setStreet1("123 South Ave");
        billingAddress.setStreet2("45 Apartment");
        billingAddress.setCity("Atlanta");
        billingAddress.setState("GA");
        billingAddress.setZip(30033);

        Customer customer = new Customer("Jung", "Yoon", "juhuyoon@gmail.com", "4047199824", true, shippingAddress, billingAddress);

        System.out.println("Billing Address is: " + customer.getBillingAddress().toString());
        System.out.println("Shipping Address is: " + customer.getShippingAddress().toString());
        System.out.println(customer.getFirstName());
        System.out.println(customer.getLastname());
        System.out.println(customer.getEmail());
        System.out.println(customer.getPhoneNumber());
        System.out.println(customer.isRewardsMember());
    }
}
