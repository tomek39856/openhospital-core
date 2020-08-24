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
package org.isf.menu.test;


import static org.junit.Assert.assertEquals;

import org.isf.menu.model.UserGroup;
import org.isf.utils.exception.OHException;

public class TestUserGroup 
{	
    private String code = "Z";
    private String description = "TestDescription";
    
			
	public UserGroup setup(
			boolean usingSet) throws OHException 
	{
		UserGroup userGroup;
	
				
		if (usingSet)
		{
			userGroup = new UserGroup();
			_setParameters(userGroup);
		}
		else
		{
			// Create UserGroup with all parameters 
			userGroup = new UserGroup(code, description);
		}
				    	
		return userGroup;
	}
	
	public void _setParameters(
			UserGroup userGroup) 
	{	
		userGroup.setCode(code);
		userGroup.setDesc(description);
		
		return;
	}
	
	public void check(
			UserGroup userGroup) 
	{		
    	assertEquals(code, userGroup.getCode());
    	assertEquals(description, userGroup.getDesc());
		
		return;
	}
}
