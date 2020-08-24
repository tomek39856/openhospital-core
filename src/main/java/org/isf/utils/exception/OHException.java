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
package org.isf.utils.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OHException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Logger logger = LoggerFactory.getLogger(OHException.class);
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public OHException(String message, Throwable cause) {
		super(message, cause);
		if (logger.isErrorEnabled()) {
			logger.error(">> EXCEPTION: {}", sanitize(message));
			logger.error(">> {}", cause);
		}
	}
	
	/**
	 * 
	 * @param message
	 */
	public OHException(String message) {
		super(message);
		if (logger.isErrorEnabled()) {
			logger.error(">> EXCEPTION: {}", sanitize(message));
		}
	}
	
	/**
	 * Sanitize the given {@link String} value. 
	 * 
	 * @param value the value to sanitize.
	 * @return the sanitized value or <code>null</code> if the passed value is <code>null</code>.
	 */
	protected String sanitize(String value)
	{
		if (value == null) return null;
		return value.trim().replaceAll("'", "''");
	}
}
