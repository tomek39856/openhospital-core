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
package org.isf.patient.service;

import java.util.ArrayList;
import java.util.List;

import org.isf.patient.model.Patient;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PatientIoOperationRepository extends JpaRepository<Patient, Integer>, PatientIoOperationRepositoryCustom {
    
    List<Patient> findByDeletedOrDeletedIsNull(String deletionStatus);

    List<Patient> findAllByDeletedIsNullOrDeletedEqualsOrderByName(String patDeleted, Pageable pageable);
    
    @Query("select p from Patient p where p.name = :name and (p.deleted = :deletedStatus or p.deleted is null) order by p.secondName, p.firstName")
	List<Patient> findByNameAndDeletedOrderByName(@Param("name") String name, @Param("deletedStatus") String deletedStatus);

	@Query("select p from Patient p where p.code = :id and (p.deleted = :deletedStatus or p.deleted is null)")
	List<Patient> findAllWhereIdAndDeleted(@Param("id") Integer id, @Param("deletedStatus") String deletedStatus);
    
    @Modifying
    @Transactional
    @Query(value = "update Patient p set p.deleted = 'Y' where p.code = :id")
    int updateDeleted(@Param("id") Integer id);
            
    List<Patient> findByNameAndDeleted(String name, String deletedStatus);

    @Query(value = "select max(p.code) from Patient p")
    Integer findMaxCode();

	@Query(value = "SELECT * FROM PATIENT "
		+"LEFT JOIN (SELECT PEX_PAT_ID, PEX_HEIGHT AS PAT_HEIGHT, PEX_WEIGHT AS PAT_WEIGHT FROM PATIENTEXAMINATION GROUP BY PEX_PAT_ID ORDER BY PEX_DATE DESC) "
		+"AS HW ON PAT_ID = HW.PEX_PAT_ID WHERE (PAT_DELETED='N' or PAT_DELETED is null) "
		+"AND (PAT_AFFILIATED_PERSON < 1 OR PAT_AFFILIATED_PERSON is null) "
		+"AND (PAT_IS_HEAD_AFFILIATION = 1) "
		+"ORDER BY PAT_ID DESC",
		nativeQuery= true)
	ArrayList<Patient> getPatientsHeadWithHeightAndWeight();
}
