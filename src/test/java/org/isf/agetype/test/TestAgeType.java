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
package org.isf.agetype.test;


import static org.junit.Assert.assertEquals;

import org.isf.agetype.model.AgeType;
import org.isf.utils.exception.OHException;

public class TestAgeType 
{	
    private String code = "d8";
    private String description = "TestDescription";
    private int from = 1;
    private int to = 100;
    
			
	public AgeType setup(
			boolean usingSet) throws OHException 
	{
		AgeType ageType;
	
				
		if (usingSet)
		{
			ageType = new AgeType();
			_setParameters(ageType);
		}
		else
		{
			// Create AgeType with all parameters 
			ageType = new AgeType(code, from, to, description);
		}
				    	
		return ageType;
	}
	
	public void _setParameters(
			AgeType ageType) 
	{	
		ageType.setCode(code);
		ageType.setFrom(from);
		ageType.setTo(to);
		ageType.setDescription(description);
		
		return;
	}
	
	public void check(
			AgeType ageType) 
	{		
    	assertEquals(code, ageType.getCode());
    	assertEquals(from, ageType.getFrom());
    	assertEquals(to, ageType.getTo());
    	assertEquals(description, ageType.getDescription());
		
		return;
	}
}
