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

import org.isf.menu.model.User;
import org.isf.menu.model.UserGroup;
import org.isf.utils.exception.OHException;

public class TestUser 
{	
    private String name = "TestName";
    private String description = "TestDescription";	
	private String passwd = "TestPaswd";
	private String desc = "TestDesc";
    
			
	public User setup(
			UserGroup userGroupName,
			boolean usingSet) throws OHException 
	{
		User user;
	
				
		if (usingSet)
		{
			user = new User();
			_setParameters(user, userGroupName);
		}
		else
		{
			// Create User with all parameters 
			user = new User(name, userGroupName, passwd, desc);
		}
				    	
		return user;
	}
	
	public void _setParameters(
			User user,
			UserGroup userGroupName) 
	{	
		user.setUserName(name);
		user.setDesc(desc);
		user.setUserGroupName(userGroupName);
		user.setPasswd(passwd);
		
		return;
	}
	
	public void check(
			User user) 
	{		
    	assertEquals(name, user.getUserName());
    	assertEquals(desc, user.getDesc());
    	assertEquals(passwd, user.getPasswd());
		
		return;
	}
}
