import java.util.ArrayList;

public interface iCouponDiscount {
	void DisplayCoupons(ArrayList<Discount> couponDiscount);
	Discount CalculateCouponDiscount(ArrayList<Discount> couponDiscount);
}
