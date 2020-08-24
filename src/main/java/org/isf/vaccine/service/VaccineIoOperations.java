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
package org.isf.vaccine.service;

import java.util.ArrayList;

import org.isf.utils.db.TranslateOHServiceException;
import org.isf.utils.exception.OHServiceException;
import org.isf.vaccine.model.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class offers the io operations for recovering and managing
 * vaccine records from the database
 *
 * @author Eva
 * 
 * modification history
 * 20/10/2011 - Cla - insert vaccinetype managment
 *
 */

@Service
@Transactional(rollbackFor=OHServiceException.class)
@TranslateOHServiceException
public class VaccineIoOperations {

	@Autowired
	private VaccineIoOperationRepository repository;
	
	/**
	 * returns the list of {@link Vaccine}s based on vaccine type code
	 *
	 * @param vaccineTypeCode - the type code. If <code>null</code> returns all {@link Vaccine}s in the DB
	 * @return the list of {@link Vaccine}s
	 * @throws OHServiceException 
	 */
	public ArrayList<Vaccine> getVaccine(String vaccineTypeCode) throws OHServiceException {
		return new ArrayList<Vaccine>(
			vaccineTypeCode != null ?
				repository.findByVaccineType_CodeOrderByDescriptionAsc(vaccineTypeCode) :
				repository.findAllByOrderByDescriptionAsc()
		);
	}

	/**
	 * inserts a new {@link Vaccine} in the DB
	 *
	 * @param vaccine - the item to insert
	 * @return <code>true</code> if the item has been inserted, <code>false</code> otherwise
	 * @throws OHServiceException 
	 */
	public boolean newVaccine(Vaccine vaccine) throws OHServiceException {
		return repository.save(vaccine) != null;
	}
	
	/**
	 * updates a {@link Vaccine} in the DB
	 *
	 * @param vaccine - the item to update
	 * @return <code>true</code> if the item has been updated, <code>false</code> otherwise
	 * @throws OHServiceException 
	 */
	public boolean updateVaccine(Vaccine vaccine) throws OHServiceException {
		return repository.save(vaccine) != null;
	}

	/**
	 * deletes a {@link Vaccine} in the DB
	 *
	 * @param vaccine - the item to delete
	 * @return <code>true</code> if the item has been deleted, <code>false</code> otherwise
	 * @throws OHServiceException 
	 */
	public boolean deleteVaccine(Vaccine vaccine) throws OHServiceException {
		repository.delete(vaccine);
		return true;
	}

	/**
	 * checks if the code is already in use
	 *
	 * @param code - the vaccine code
	 * @return <code>true</code> if the code is already in use, <code>false</code> otherwise
	 * @throws OHServiceException 
	 */
	public boolean isCodePresent(String code) throws OHServiceException {
		return repository.exists(code);
	}
}


