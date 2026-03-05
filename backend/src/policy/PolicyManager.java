package policy;

public class PolicyManager {
	private static PolicyManager instancePolicyManager = new PolicyManager(); // Singleton instance
	private CancellationPolicy cancellationPolicy;
	private RefundPolicy refundPolicy;
	private PricingStrategy pricingStrategy;
	
	// private constructor
	private PolicyManager() {
  		pricingStrategy = new StandardPricingPolicy();
  		cancellationPolicy = new FreeCancellationPolicy();
  		refundPolicy = new NoRefundPolicy();
	}	 
	public static PolicyManager getInstance() {
        return instancePolicyManager;
    }
	public CancellationPolicy getCancellationPolicy() {
		return this.cancellationPolicy;
	}
	public void setCancellationPolicy(CancellationPolicy cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}
	
	public PricingStrategy getPricingStrategy() {
		return this.pricingStrategy;
	}
	public void setPricingStrategy(PricingStrategy pricingStrategy) {
		this.pricingStrategy = pricingStrategy;
	}
	public RefundPolicy getRefundPolicy() {
		return this.refundPolicy;
	}
	public void setRefundPolicy(RefundPolicy refundPolicy) {
		this.refundPolicy = refundPolicy;
	}
}
