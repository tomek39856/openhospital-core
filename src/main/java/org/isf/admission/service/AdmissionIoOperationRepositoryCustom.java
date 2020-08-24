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
package org.isf.admission.service;


import org.isf.admission.model.Admission;
import org.isf.patient.model.Patient;

import java.util.GregorianCalendar;
import java.util.List;


public interface AdmissionIoOperationRepositoryCustom {

	/**
	 * @deprecated unless you need all the columns from patient and admission, use {@link #findPatientAndAdmissionId(String)} instead
	 * @return
	 */
	@Deprecated
	List<Admission> findAllBySearch(String searchTerms);

	List<PatientAdmission> findPatientAndAdmissionId(String searchTerms);

	/**
	 * @deprecated unless you need all the columns from patient and admission, use {@link #findPatientAdmissionsBySearchAndDateRanges(String, GregorianCalendar[], GregorianCalendar[])} instead
	 * @return
	 */
	@Deprecated
	List<Admission> findAllBySearchAndDateRanges(String searchTerms, GregorianCalendar[] admissionRange,
												GregorianCalendar[] dischargeRange);

	List<PatientAdmission> findPatientAdmissionsBySearchAndDateRanges(String searchTerms,
																	  GregorianCalendar[] admissionRange,
																	  GregorianCalendar[] dischargeRange);


	class PatientAdmission {
		/**
		 * @see Patient#getCode()
		 */
		private final Integer patientId;

		/**
		 * @see Admission#getId()
		 */
		private final Integer admissionId;

		public PatientAdmission(final Integer patientId,
								final Integer admissionId) {
			this.patientId = patientId;
			this.admissionId = admissionId;
		}

		public Integer getPatientId() {
			return patientId;
		}

		public Integer getAdmissionId() {
			return admissionId;
		}
	}
}
