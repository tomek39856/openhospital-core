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
package org.isf.medtype.test;


import static org.junit.Assert.assertEquals;

import org.isf.medtype.model.MedicalType;
import org.isf.utils.exception.OHException;

public class TestMedicalType 
{	
    private String code = "Z";
    private String description = "TestDescription";
    
			
	public MedicalType setup(
			boolean usingSet) throws OHException 
	{
		MedicalType medicalType;
	
				
		if (usingSet)
		{
			medicalType = new MedicalType();
			_setParameters(medicalType);
		}
		else
		{
			// Create MedicalType with all parameters 
			medicalType = new MedicalType(code, description);
		}
				    	
		return medicalType;
	}
	
	public void _setParameters(
			MedicalType medicalType) 
	{	
		medicalType.setCode(code);
		medicalType.setDescription(description);
		
		return;
	}
	
	public void check(
			MedicalType medicalType) 
	{		
    	assertEquals(code, medicalType.getCode());
    	assertEquals(description, medicalType.getDescription());
		
		return;
	}
}
