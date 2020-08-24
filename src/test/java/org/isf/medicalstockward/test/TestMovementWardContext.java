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
package org.isf.medicalstockward.test;


import java.util.List;

import org.isf.medicalstockward.model.MovementWard;
import org.isf.utils.db.DbJpaUtil;
import org.isf.utils.exception.OHException;

public class TestMovementWardContext 
{		
	private static List<MovementWard> savedMovementWard;
		
		
	@SuppressWarnings("unchecked")
	public void saveAll(
			DbJpaUtil jpa) throws OHException 
    {	
		jpa.beginTransaction();			
		jpa.createQuery("select movWard from MovementWard movWard", MovementWard.class, true);
		savedMovementWard = (List<MovementWard>)jpa.getList();
		jpa.commitTransaction();
        		
        return;
    }
	
	public List<MovementWard> getAllSaved() throws OHException 
    {	        		
        return savedMovementWard;
    }
	    
    @SuppressWarnings("unchecked")
    public void deleteNews(
    		DbJpaUtil jpa) throws OHException 
    {
		jpa.beginTransaction();			
		jpa.createQuery("select movWard from MovementWard movWard", MovementWard.class, true);
		List<MovementWard> MovementWards = (List<MovementWard>)jpa.getList();
		for (MovementWard movementWard: MovementWards) 
		{    		
			int index = savedMovementWard.indexOf(movementWard);
			
			
			if (index == -1)
			{				
				jpa.remove(movementWard);
			}
	    }        
		jpa.commitTransaction();
		        
        return;
    } 
}
