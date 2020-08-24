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
package org.isf.hospital.manager;

import org.isf.hospital.model.Hospital;
import org.isf.hospital.service.HospitalIoOperations;
import org.isf.utils.exception.OHServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class that provides gui separation from database operations and gives some
 * useful logic manipulations of the dynamic data (memory)
 * 
 * @author bob
 * 
 */
@Component
public class HospitalBrowsingManager {
	
	private final Logger logger = LoggerFactory.getLogger(HospitalBrowsingManager.class);
	
	@Autowired
	private HospitalIoOperations ioOperations;

	/**
	 * Reads from database hospital information
	 * 
	 * @return {@link Hospital} object
	 * @throws OHServiceException 
	 */
	public Hospital getHospital() throws OHServiceException {
		return ioOperations.getHospital();
	}
	/**
	 * Reads from database currency cod
	 * @return currency cod
	 * @throws OHServiceException 
	 */
	public String getHospitalCurrencyCod() throws OHServiceException {
		return ioOperations.getHospitalCurrencyCod();
	}

	/**
	 * Updates hospital information
	 * 
	 * @return <code>true</code> if the hospital informations have been updated, <code>false</code> otherwise
	 * @throws OHServiceException 
	 */
	public boolean updateHospital(Hospital hospital) throws OHServiceException {
		return ioOperations.updateHospital(hospital);
    }	
}
	
