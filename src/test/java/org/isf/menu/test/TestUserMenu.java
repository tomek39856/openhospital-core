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

import org.isf.menu.model.UserMenuItem;
import org.isf.utils.exception.OHException;

public class TestUserMenu 
{	
    private String code = "Z";
	private String buttonLabel = "TestButtonLabel";
	private String altLabel = "TestAltLabel";
	private String tooltip = "TestToolTip";
	private char shortcut = 'Y';
	private String mySubmenu = "TestMySubmenu";
	private String myClass = "TestMyClass";
	private boolean isASubMenu = true;
	private int position = 11;
    
			
	public UserMenuItem setup(
			boolean usingSet) throws OHException 
	{
		UserMenuItem userMenuItem;
	
				
		if (usingSet)
		{
			userMenuItem = new UserMenuItem();
			_setParameters(userMenuItem);
		}
		else
		{
			// Create UserMenuItem with all parameters 
			userMenuItem = new UserMenuItem(code, buttonLabel, altLabel, tooltip, shortcut, mySubmenu, myClass, isASubMenu, position, true);
		}
				    	
		return userMenuItem;
	}
	
	public void _setParameters(
			UserMenuItem userMenuItem) 
	{	
		userMenuItem.setCode(code);
		userMenuItem.setAltLabel(altLabel);
		userMenuItem.setButtonLabel(buttonLabel);
		userMenuItem.setActive(true);
		userMenuItem.setASubMenu(isASubMenu);
		userMenuItem.setMyClass(myClass);
		userMenuItem.setMySubmenu(mySubmenu);
		userMenuItem.setPosition(position);
		userMenuItem.setShortcut(shortcut);
		userMenuItem.setTooltip(tooltip);
		
		return;
	}
	
	public void check(
			UserMenuItem userMenuItem) 
	{		
    	assertEquals(code, userMenuItem.getCode());
    	assertEquals(altLabel, userMenuItem.getAltLabel());
    	assertEquals(buttonLabel, userMenuItem.getButtonLabel());
    	assertEquals(isASubMenu, userMenuItem.isASubMenu());
    	assertEquals(myClass, userMenuItem.getMyClass());
    	assertEquals(mySubmenu, userMenuItem.getMySubmenu());
    	assertEquals(position, userMenuItem.getPosition());
    	assertEquals(shortcut, userMenuItem.getShortcut());
    	assertEquals(tooltip, userMenuItem.getTooltip());
		
		return;
	}
}
