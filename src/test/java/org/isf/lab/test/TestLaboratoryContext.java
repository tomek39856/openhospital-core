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
package org.isf.lab.test;


import java.util.List;

import org.isf.lab.model.Laboratory;
import org.isf.utils.db.DbJpaUtil;
import org.isf.utils.exception.OHException;

public class TestLaboratoryContext 
{		
	private static List<Laboratory> savedLaboratory;
		
		
	@SuppressWarnings("unchecked")
	public void saveAll(
			DbJpaUtil jpa) throws OHException 
    {	
		jpa.beginTransaction();			
		jpa.createQuery("SELECT * FROM LABORATORY", Laboratory.class, false);
		savedLaboratory = (List<Laboratory>)jpa.getList();
		jpa.commitTransaction();
        		
        return;
    }
	
	public List<Laboratory> getAllSaved() throws OHException 
    {	        		
        return savedLaboratory;
    }
	    
    @SuppressWarnings("unchecked")
    public void deleteNews(
    		DbJpaUtil jpa) throws OHException 
    {
		jpa.beginTransaction();			
		jpa.createQuery("SELECT * FROM LABORATORY", Laboratory.class, false);
		List<Laboratory> Laboratories = (List<Laboratory>)jpa.getList();
		for (Laboratory laboratory: Laboratories) 
		{    		
			int index = savedLaboratory.indexOf(laboratory);
			
			if (index == -1)
			{	
				jpa.remove(laboratory);
			}
	    }        
		jpa.commitTransaction();
		        
        return;
    } 
}
