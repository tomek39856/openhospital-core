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
package org.isf.vactype.test;


import static org.junit.Assert.assertEquals;

import org.isf.utils.exception.OHException;
import org.isf.vactype.model.VaccineType;

public class TestVaccineType 
{	
    private String code = "Z";
    private String description = "TestDescription";
    
			
	public VaccineType setup(
			boolean usingSet) throws OHException 
	{
		VaccineType vaccineType;
	
				
		if (usingSet)
		{
			vaccineType = new VaccineType();
			_setParameters(vaccineType);
		}
		else
		{
			// Create VaccineType with all parameters 
			vaccineType = new VaccineType(code, description);
		}
				    	
		return vaccineType;
	}
	
	public void _setParameters(
			VaccineType vaccineType) 
	{	
		vaccineType.setCode(code);
		vaccineType.setDescription(description);
		
		return;
	}
	
	public void check(
			VaccineType vaccineType) 
	{		
    	assertEquals(code, vaccineType.getCode());
    	assertEquals(description, vaccineType.getDescription());
		
		return;
	}
}
