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
package org.isf.disease.test;


import static org.junit.Assert.assertEquals;

import org.isf.disease.model.Disease;
import org.isf.distype.model.DiseaseType;
import org.isf.utils.exception.OHException;

public class TestDisease 
{	
    private String code = "999";
    private String description = "TestDescription";
    
			
	public Disease setup(
			DiseaseType diseaseType,
			boolean usingSet) throws OHException 
	{
		Disease disease;
	
				
		if (usingSet)
		{
			disease = new Disease();
			_setParameters(disease, diseaseType);
		}
		else
		{
			// Create Disease with all parameters 
			disease = new Disease(code, description, diseaseType);
		}
				    	
		return disease;
	}
	
	public void _setParameters(
			Disease disease,
			DiseaseType diseaseType) 
	{	
		disease.setCode(code);
		disease.setDescription(description);
		disease.setType(diseaseType);
		
		return;
	}
	
	public void check(
			Disease disease) 
	{		
    	assertEquals(code, disease.getCode());
    	assertEquals(description, disease.getDescription());
		
		return;
	}
}
