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
package org.isf.distype.test;


import static org.junit.Assert.assertEquals;

import org.isf.distype.model.DiseaseType;
import org.isf.utils.exception.OHException;

public class TestDiseaseType 
{	
    private String code = "ZZ";
    private String description = "TestDescription";
    
			
	public DiseaseType setup(
			boolean usingSet) throws OHException 
	{
		DiseaseType diseaseType;
	
				
		if (usingSet)
		{
			diseaseType = new DiseaseType();
			_setParameters(diseaseType);
		}
		else
		{
			// Create DiseaseType with all parameters 
			diseaseType = new DiseaseType(code, description);
		}
				    	
		return diseaseType;
	}
	
	public void _setParameters(
			DiseaseType diseaseType) 
	{	
		diseaseType.setCode(code);
		diseaseType.setDescription(description);
		
		return;
	}
	
	public void check(
			DiseaseType diseaseType) 
	{		
    	assertEquals(code, diseaseType.getCode());
    	assertEquals(description, diseaseType.getDescription());
		
		return;
	}
}
