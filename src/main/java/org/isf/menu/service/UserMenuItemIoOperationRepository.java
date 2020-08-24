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
package org.isf.menu.service;

import org.isf.menu.model.UserMenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMenuItemIoOperationRepository extends JpaRepository<UserMenuItem, String> {

    @Query(value = "select menuItem.code, menuItem.buttonLabel, menuItem.altLabel, menuItem.tooltip, menuItem.shortcut, " +
			"menuItem.mySubmenu, menuItem.myClass, menuItem.isASubMenu, menuItem.position, groupMenu.active " +
			"from UserMenuItem menuItem, GroupMenu groupMenu, UserGroup  userGroup, User user " +
			"where (user.userName=:id) " +
			"and (user.userGroupName=userGroup.code) " +
			"and (userGroup.code=groupMenu.userGroup) " +
			"and (menuItem.code=groupMenu.menuItem) " +
			"order by menuItem.position")
    List<Object[]> findAllWhereId(@Param("id") String id);

    @Query(value = "select menuItem.code, menuItem.buttonLabel, menuItem.altLabel, menuItem.tooltip, menuItem.shortcut, " +
			"menuItem.mySubmenu, menuItem.myClass, menuItem.isASubMenu, menuItem.position, groupMenu.active " +
			"from UserMenuItem menuItem, GroupMenu groupMenu, UserGroup  userGroup, User user " +
			"where userGroup.code=:groupId " +
			"and (user.userGroupName=userGroup.code) " +
			"and (userGroup.code=groupMenu.userGroup) " +
			"and (menuItem.code=groupMenu.menuItem) " +
			"order by menuItem.position")
    List<Object[]> findAllWhereGroupId(@Param("groupId") String groupId);
    
}
