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

import org.isf.menu.model.GroupMenu;
import org.isf.utils.exception.OHException;

public class TestGroupMenu 
{	
    private Integer code = 999;
    private String userGroup = "TestDescription";
    private String menuItem = "TestDescription";
    private char active = 'Y';
    
			
	public GroupMenu setup(
			boolean usingSet) throws OHException 
	{
		GroupMenu groupMenu;
	
				
		if (usingSet)
		{
			groupMenu = new GroupMenu();
			_setParameters(groupMenu);
		}
		else
		{
			// Create GroupMenu with all parameters 
			groupMenu = new GroupMenu(code, userGroup, menuItem, active);
		}
				    	
		return groupMenu;
	}
	
	public void _setParameters(
			GroupMenu groupMenu) 
	{	
		groupMenu.setCode(code);
		groupMenu.setUserGroup(userGroup);
		groupMenu.setMenuItem(menuItem);
		groupMenu.setActive(active);
		
		return;
	}
	
	public void check(
			GroupMenu groupMenu) 
	{		
    	assertEquals(code, groupMenu.getCode());
    	assertEquals(userGroup, groupMenu.getUserGroup());
    	assertEquals(menuItem, groupMenu.getMenuItem());
    	assertEquals(active, groupMenu.getActive());
		
		return;
	}
}
