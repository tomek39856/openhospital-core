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
package org.isf.lab.manager;

import java.util.ArrayList;

import org.isf.lab.model.Laboratory;
import org.isf.lab.model.LaboratoryRow;
import org.isf.lab.service.LabIoOperations;
import org.isf.utils.exception.OHServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LabRowManager {

	private final Logger logger = LoggerFactory.getLogger(LabRowManager.class);
	
	@Autowired
	private LabIoOperations ioOperations;
	
	/**
	 * Return a list of results ({@link LaboratoryRow}s) for passed lab entry.
	 * @param code - the {@link Laboratory} record ID.
	 * @return the list of {@link LaboratoryRow}s. It could be <code>empty</code>
	 * @throws OHServiceException 
	 */
	public ArrayList<LaboratoryRow> getLabRowByLabId(Integer code) throws OHServiceException{
		return ioOperations.getLabRow(code);
	}
}
