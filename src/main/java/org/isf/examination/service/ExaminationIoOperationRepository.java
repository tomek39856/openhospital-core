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
package org.isf.examination.service;

import java.util.List;

import org.isf.examination.model.PatientExamination;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExaminationIoOperationRepository extends JpaRepository<PatientExamination, Integer> {
		@Query(value = "select p from PatientExamination p where p.patient.code = :patientCode")
		List<PatientExamination> findByPatient_CodeOrderByPexDateDesc(@Param("patientCode") int patientCode);
		@Query(value = "select p from PatientExamination p where p.patient.code = :patientCode")
		Page<PatientExamination> findByPatient_CodeOrderByPexDateDesc(@Param("patientCode") int patientCode, Pageable pageable);
}
