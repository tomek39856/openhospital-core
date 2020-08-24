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
package org.isf.menu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/*------------------------------------------
 * User - model for the user entity
 * -----------------------------------------
 * modification history
 * ? - ? - first version 
 * 07/05/2016 - Antonio - ported to JPA
 * 
 *------------------------------------------*/
@Entity
@Table(name="GROUPMENU")
public class GroupMenu 
{
	@Id 
	@Column(name="GM_ID")		
	private Integer code;
	
	@Column(name="GM_UG_ID_A")
	private String userGroup;
	
	@Column(name="GM_MNI_ID_A")
	private String menuItem;

	@Column(name="GM_ACTIVE")
	private char active;	
	
	@Transient
	private volatile int hashCode = 0;
			
	
	public GroupMenu(){
	}
	
	public GroupMenu(Integer code, String userGroup, String menuItem, char active)
	{
		this.code = code;
		this.userGroup = userGroup;
		this.menuItem = menuItem;
		this.active = active;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	public String getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(String menuItem) {
		this.menuItem = menuItem;
	}
	public char getActive() {
		return active;
	}
	public void setActive(char active) {
		this.active = active;
	}
	
	public String toString(){
		return code.toString();		
	}
	
	@Override
	public boolean equals(Object anObject) {
        return (anObject == null) || !(anObject instanceof GroupMenu) ? false
                : (getCode().equals(((GroupMenu) anObject).getCode())
                  && getUserGroup().equalsIgnoreCase(((GroupMenu) anObject).getUserGroup()) 
                  && getMenuItem().equals(((GroupMenu) anObject).getMenuItem())
                  && getActive() == ((GroupMenu) anObject).getActive());
    }

	@Override
	public int hashCode() {
	    if (this.hashCode == 0) {
	        final int m = 23;
	        int c = 133;
	        
	        c = m * c + code.hashCode();
	        
	        this.hashCode = c;
	    }
	  
	    return this.hashCode;
	}	
	
}//class GroupMenu
