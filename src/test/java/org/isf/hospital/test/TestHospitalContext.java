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
package org.isf.hospital.test;


import java.util.List;

import org.isf.hospital.model.Hospital;
import org.isf.utils.db.DbJpaUtil;
import org.isf.utils.exception.OHException;

public class TestHospitalContext 
{		
	private static List<Hospital> savedHospital;
		
		
	@SuppressWarnings("unchecked")
	public void saveAll(
			DbJpaUtil jpa) throws OHException 
    {	
		jpa.beginTransaction();			
		jpa.createQuery("SELECT * FROM HOSPITAL", Hospital.class, false);
		savedHospital = (List<Hospital>)jpa.getList();
		jpa.commitTransaction();
        		
        return;
    }
	
	public List<Hospital> getAllSaved() throws OHException 
    {	        		
        return savedHospital;
    }
	    
    @SuppressWarnings("unchecked")
    public void deleteNews(
    		DbJpaUtil jpa) throws OHException 
    {
		jpa.beginTransaction();			
		jpa.createQuery("SELECT * FROM HOSPITAL", Hospital.class, false);
		List<Hospital> Hospitals = (List<Hospital>)jpa.getList();
		for (Hospital hospital: Hospitals) 
		{    		
			int index = savedHospital.indexOf(hospital);
			
			
			if (index == -1)
			{				
				jpa.remove(hospital);
			}
	    }        
		jpa.commitTransaction();
		        
        return;
    } 
}
