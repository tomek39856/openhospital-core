/*-
 * #%L
 * OpenHospital
 * $Id:$
 * $HeadURL:$
 * %%
 * Copyright (C) 2006 - 2020 Informatici Senza Frontiere
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
package org.isf.accounting.test;


import static org.junit.Assert.assertEquals;

import org.isf.accounting.model.Bill;
import org.isf.accounting.model.BillItems;
import org.isf.utils.exception.OHException;

public class TestBillItems 
{	
	private static boolean isPrice = false;
	private static String priceID = "TestPId";
	private static String itemDescription = "TestItemDescription";
	private static double itemAmount = 10.10;
	private static int itemQuantity = 20;

			
	public BillItems setup(
			Bill  bill,
			boolean usingSet) throws OHException 
	{
		BillItems billItem;
	
				
		if (usingSet)
		{
			billItem = new BillItems();
			_setParameters(billItem, bill);
		}
		else
		{
			// Create BillItem with all parameters 
			billItem = new BillItems(0, bill, isPrice, priceID, itemDescription, itemAmount, itemQuantity);
		}
				    	
		return billItem;
	}
	
	public void _setParameters(
			BillItems billItem,
			Bill bill) 
	{		
		billItem.setBill(bill);
		billItem.setItemAmount(itemAmount);
		billItem.setItemDescription(itemDescription);
		billItem.setItemQuantity(itemQuantity);
		billItem.setPrice(isPrice);
		billItem.setPriceID(priceID);
		
		return;
	}
	
	public void check(
			BillItems billItem) 
	{		
		assertEquals(itemAmount, billItem.getItemAmount(), 0.1);
		assertEquals(itemDescription, billItem.getItemDescription());
		assertEquals(itemQuantity, billItem.getItemQuantity());
		assertEquals(isPrice, billItem.isPrice());
		assertEquals(priceID, billItem.getPriceID());
				
		return;
	}
}
