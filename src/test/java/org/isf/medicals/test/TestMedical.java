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
package org.isf.medicals.test;

import static org.junit.Assert.assertEquals;

import org.isf.medicals.model.Medical;
import org.isf.medtype.model.MedicalType;
import org.isf.utils.exception.OHException;

public class TestMedical 
{	 
	private Integer code = null;
	private String prod_code = "TP1";
	private String description = "TestDescription";
	private double initialqty = 10.10;
	private Integer pcsperpck = 11;
	private double inqty = 20.20;
	private double outqty = 30.30;
	private double minqty = 40.40;
    
			
	public Medical setup(
			MedicalType medicalType,
			boolean usingSet) throws OHException 
	{
		Medical medical;
	
				
		if (usingSet)
		{
			medical = new Medical();
			_setParameters(medical, medicalType);
		}
		else
		{
			// Create Medical with all parameters 
			medical = new Medical(code, medicalType, prod_code, description, initialqty, pcsperpck, minqty, inqty, outqty);
		}
				    	
		return medical;
	}
	
	public void _setParameters(
			Medical medical,
			MedicalType medicalType) 
	{	
		medical.setDescription(description);
		medical.setInitialqty(initialqty);
		medical.setInqty(inqty);
		medical.setMinqty(minqty);
		medical.setOutqty(outqty);
		medical.setPcsperpck(pcsperpck);
		medical.setProd_code(prod_code);
		medical.setType(medicalType);
		
		return;
	}
	
	public void check(
			Medical medical) 
	{		
    	assertEquals(description, medical.getDescription());
    	assertEquals(initialqty, medical.getInitialqty(), 0.1);
    	assertEquals(inqty, medical.getInqty(), 0.1);
    	assertEquals(minqty, medical.getMinqty(), 0.1);
    	assertEquals(outqty, medical.getOutqty(), 0.1);
    	assertEquals(pcsperpck, medical.getPcsperpck());
    	assertEquals(prod_code, medical.getProd_code());
		
		return;
	}
}
