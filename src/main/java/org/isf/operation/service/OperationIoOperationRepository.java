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
package org.isf.operation.service;

import org.isf.operation.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface OperationIoOperationRepository extends JpaRepository<Operation, String> {
    List<Operation> findByOrderByDescriptionDesc();
	List<Operation> findAllByDescriptionContainsOrderByDescriptionDesc(String type);
    Operation findOneByDescriptionAndType_Code(String description, String type);
    Operation findByCode(String code);
	@Query(value = "SELECT * FROM OPERATION JOIN OPERATIONTYPE ON OPE_OCL_ID_A = OCL_ID_A WHERE OPE_FOR LIKE 1 OR  OPE_FOR LIKE 3  ORDER BY OPE_DESC", nativeQuery= true)
	ArrayList<Operation> findAllWithoutDescriptionOpd();
	@Query(value = "SELECT * FROM OPERATION JOIN OPERATIONTYPE ON OPE_OCL_ID_A = OCL_ID_A WHERE OPE_FOR LIKE 1 OR  OPE_FOR LIKE 2  ORDER BY OPE_DESC", nativeQuery= true)
	ArrayList<Operation> findAllWithoutDescriptionAdm();
}
