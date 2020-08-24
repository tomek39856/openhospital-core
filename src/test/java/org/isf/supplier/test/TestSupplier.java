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
package org.isf.supplier.test;

import static org.junit.Assert.assertEquals;

import org.isf.supplier.model.Supplier;
import org.isf.utils.exception.OHException;

public class TestSupplier 
{	 
	private Integer supId = null;
	private String supName = "TestName";
	private String supAddress = "TestAddress";
	private String supTaxcode = "TestTax";
	private String supPhone = "TestPhone";
	private String supFax = "TestFax";
	private String supEmail = "TestEmail";
	private String supNote = "TestNote";
	private Character supDeleted = 'N';    
			
	public Supplier setup(
			boolean usingSet) throws OHException 
	{
		Supplier supplier;
	
				
		if (usingSet)
		{
			supplier = new Supplier();
			_setParameters(supplier);
		}
		else
		{
			// Create Supplier with all parameters 
			supplier = new Supplier(supId, supName, supAddress, supTaxcode, supPhone, supFax, supEmail, supNote, supDeleted);
		}
				    	
		return supplier;
	}
	
	public void _setParameters(
			Supplier supplier) 
	{	
		supplier.setSupAddress(supAddress);
		supplier.setSupDeleted(supDeleted);
		supplier.setSupEmail(supEmail);
		supplier.setSupFax(supFax);
		supplier.setSupName(supName);
		supplier.setSupNote(supNote);
		supplier.setSupPhone(supPhone);
		supplier.setSupTaxcode(supTaxcode);
		
		return;
	}
	
	public void check(
			Supplier supplier) 
	{		
    	assertEquals(supAddress, supplier.getSupAddress());
    	assertEquals(supDeleted, supplier.getSupDeleted());
    	assertEquals(supEmail, supplier.getSupEmail());
    	assertEquals(supFax, supplier.getSupFax());
    	assertEquals(supName, supplier.getSupName());
    	assertEquals(supNote, supplier.getSupNote());
    	assertEquals(supPhone, supplier.getSupPhone());
    	assertEquals(supTaxcode, supplier.getSupTaxcode());
		
		return;
	}
}
