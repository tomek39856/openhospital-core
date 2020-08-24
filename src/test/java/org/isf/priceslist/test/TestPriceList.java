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
package org.isf.priceslist.test;


import static org.junit.Assert.assertEquals;

import org.isf.priceslist.model.PriceList;
import org.isf.utils.exception.OHException;

public class TestPriceList 
{	
    private static String listCode = "Code";
    private static String listName = "TestName";
    private static String listDescription = "TestDescription";
    private static String listCurrency = "Curr";
				
		
	public PriceList setup(
			boolean usingSet) throws OHException 
	{
		PriceList priceList;
	
				
		if (usingSet)
		{
			priceList = new PriceList();
			_setParameters(priceList);
		}
		else
		{
			// Create PriceList with all parameters 
			priceList = new PriceList(0, listCode, listName, listDescription, listCurrency);
		}
				    	
		return priceList;
	}
	
	public void _setParameters(
			PriceList priceList) 
	{		
		priceList.setCode(listCode);
		priceList.setCurrency(listCurrency);
		priceList.setDescription(listDescription);
		priceList.setName(listName);
		
		return;
	}
	
	public void check(
			PriceList priceList) 
	{		
		assertEquals(listCode, priceList.getCode());
		assertEquals(listCurrency, priceList.getCurrency());
		assertEquals(listDescription, priceList.getDescription());
		assertEquals(listName, priceList.getName());
		
		return;
	}
}
