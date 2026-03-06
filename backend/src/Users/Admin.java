package Users;

import policy.CancellationPolicy;
import policy.PolicyManager;
import policy.PricingStrategy;
import policy.RefundPolicy;

public class Admin extends User {

    private static int idCounter = 1;

    public Admin(String name, String email, String phone) {
        super(name, email, phone, "Admin");
        this.setRole("Admin");
        this.userId = "AD-" + idCounter++;
    }

    // =========================================================
    // Consultant approval
    // =========================================================
    public boolean reviewConsultantRequest(Consultant consultant, boolean approve) {

        if (consultant == null) return false;

        if (approve) {
            consultant.approve();
            System.out.println("Consultant approved: " + consultant.getName());
        } else {
            consultant.reject();
            System.out.println("Consultant rejected: " + consultant.getName());
        }

        return approve;
    }

    // =========================================================
    // Define system policies
    // =========================================================
    public void defineSystemPolicies(PricingStrategy pricing,
                                     CancellationPolicy cancellation,
                                     RefundPolicy refund) {

        PolicyManager manager = PolicyManager.getInstance();

        manager.setPricingStrategy(pricing);
        manager.setCancellationPolicy(cancellation);
        manager.setRefundPolicy(refund);

        System.out.println("System policies updated by admin.");
    }
}