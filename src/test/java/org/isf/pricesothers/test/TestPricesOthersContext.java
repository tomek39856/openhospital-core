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
package org.isf.pricesothers.test;


import java.util.List;

import org.isf.pricesothers.model.PricesOthers;
import org.isf.utils.db.DbJpaUtil;
import org.isf.utils.exception.OHException;

public class TestPricesOthersContext 
{		
	private static List<PricesOthers> savedPricesOthers;
		
		
	@SuppressWarnings("unchecked")
	public void saveAll(
			DbJpaUtil jpa) throws OHException 
    {	
		jpa.beginTransaction();			
		jpa.createQuery("SELECT * FROM PRICESOTHERS", PricesOthers.class, false);
		savedPricesOthers = (List<PricesOthers>)jpa.getList();
		jpa.commitTransaction();
        		
        return;
    }
	
	public List<PricesOthers> getAllSaved() throws OHException 
    {	        		
        return savedPricesOthers;
    }
	    
    @SuppressWarnings("unchecked")
    public void deleteNews(
    		DbJpaUtil jpa) throws OHException 
    {
		jpa.beginTransaction();			
		jpa.createQuery("SELECT * FROM PRICESOTHERS", PricesOthers.class, false);
		List<PricesOthers> pricesOthers = (List<PricesOthers>)jpa.getList();
		for (PricesOthers price: pricesOthers)
		{    		
			int index = savedPricesOthers.indexOf(price);
			
			
			if (index == -1)
			{				
				jpa.remove(price);
			}
	  }        
		jpa.commitTransaction();
		        
        return;
    } 
}
