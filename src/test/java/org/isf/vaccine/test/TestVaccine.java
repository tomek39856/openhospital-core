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
package org.isf.vaccine.test;


import static org.junit.Assert.assertEquals;

import org.isf.utils.exception.OHException;
import org.isf.vaccine.model.Vaccine;
import org.isf.vactype.model.VaccineType;

public class TestVaccine 
{	
    private String code = "Z";
    private String description = "TestDescription";
    
			
	public Vaccine setup(
			VaccineType vaccineType,
			boolean usingSet) throws OHException 
	{
		Vaccine vaccine;
	
				
		if (usingSet)
		{
			vaccine = new Vaccine();
			_setParameters(vaccineType, vaccine);
		}
		else
		{
			// Create Vaccine with all parameters 
			vaccine = new Vaccine(code, description, vaccineType);
		}
				    	
		return vaccine;
	}
	
	public void _setParameters(
			VaccineType vaccineType,
			Vaccine vaccine) 
	{	
		vaccine.setCode(code);
		vaccine.setDescription(description);
		vaccine.setVaccineType(vaccineType);
		
		return;
	}
	
	public void check(
			Vaccine vaccine) 
	{		
    	assertEquals(code, vaccine.getCode());
    	assertEquals(description, vaccine.getDescription());
		
		return;
	}
}
