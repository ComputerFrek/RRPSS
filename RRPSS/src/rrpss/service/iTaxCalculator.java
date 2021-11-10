package rrpss.service;
import java.util.ArrayList;

import rrpss.entity.Order;
import rrpss.entity.Tax;

public interface iTaxCalculator{
	double CalculateTax(Order order, ArrayList<Tax> taxList);
}
