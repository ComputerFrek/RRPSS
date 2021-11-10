package rrpss.service;
import rrpss.entity.Order;

public interface iUpdateOrderController {
	void AddOrderItem(Order order);
	void RemoveOrderItem(Order order);
}
