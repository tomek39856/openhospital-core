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


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.isf.admission.model.Admission;
import org.isf.patient.model.Patient;
import org.springframework.transaction.annotation.Transactional;

import static org.isf.utils.time.TimeTools.getBeginningOfDay;
import static org.isf.utils.time.TimeTools.getBeginningOfNextDay;


@Transactional
public class AdmissionIoOperationRepositoryImpl implements AdmissionIoOperationRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;


	@Override
	public List<PatientAdmission> findPatientAndAdmissionId(final String searchTerms) {
		String[] terms = getTermsToSearch(searchTerms);
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
		Root<Admission> admissionRoot = query.from(Admission.class);
		Join<Admission, Patient> patient = admissionRoot.join("patient");
		query.multiselect(patient.get("code"), admissionRoot.get("id"));
		List<Predicate> where = new ArrayList<Predicate>();
		where.add(admissionNotDeletedPredicate(cb, admissionRoot));
		if (terms != null) {
			where.addAll(termsPredicates(terms, cb, patient));
		}
		where.add(patientNotDeletedPredicate(cb, patient));
		query.where(cb.and(where.toArray(new Predicate[where.size()])));
		query.orderBy(cb.desc(patient.get("code")));

		return mapToPatientAdmission(this.entityManager.createQuery(query).getResultList());
	}


	@SuppressWarnings("unchecked")	
	@Override
	public List<Admission> findAllBySearch(String literal) {
		return this.entityManager.
				createQuery(buildSearchQuery(literal)).
					getResultList();
	}	

	
	private CriteriaQuery<Admission> buildSearchQuery(String searchTerms) {
		String[] terms = getTermsToSearch(searchTerms);
		return createQuerySearchingForPatientContainingGivenWordsInHisProperties(terms);
	}

	@Override
	public List<PatientAdmission> findPatientAdmissionsBySearchAndDateRanges(final String searchTerms,
																			 final GregorianCalendar[] admissionRange,
																			 final GregorianCalendar[] dischargeRange) {
		String[] terms = getTermsToSearch(searchTerms);

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object[]> query = cb.createQuery(Object[].class);
		Root<Admission> admissionRoot = query.from(Admission.class);
		List<Predicate> where = new ArrayList<Predicate>();
		where.add(admissionNotDeletedPredicate(cb, admissionRoot));
		Join<Admission, Patient> patient = admissionRoot.join("patient");
		query.multiselect(patient.get("code"), admissionRoot.get("id"));
		if (terms != null) {
			where.addAll(termsPredicates(terms, cb, patient));
		}
		where.add(patientNotDeletedPredicate(cb, patient));
		where.addAll(dateRangePredicates(cb, admissionRoot, admissionRange, dischargeRange));
		query.where(cb.and(where.toArray(new Predicate[where.size()])));
		query.orderBy(cb.desc(patient.get("code")));

		return mapToPatientAdmission(this.entityManager.createQuery(query).getResultList());
	}


	private List<PatientAdmission> mapToPatientAdmission(List<Object[]> resultList) {
		final List<PatientAdmission> result = new ArrayList<PatientAdmission>(resultList.size());
		for (final Object[] arrays : resultList) {
			result.add(new PatientAdmission((Integer) arrays[0], (Integer) arrays[1]));
		}
		return result;

	}





    private String[] getTermsToSearch(String searchTerms) {
    	String[] terms = null;

    	if (searchTerms != null && !searchTerms.isEmpty()) {
			searchTerms = searchTerms.trim().toLowerCase();
			terms = searchTerms.split(" ");
		}

    	return terms;
	}


    
    private CriteriaQuery<Admission> createQuerySearchingForPatientContainingGivenWordsInHisProperties(String[] terms) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Admission> query = cb.createQuery(Admission.class);
		Root<Admission> admissionRoot = query.from(Admission.class);
		List<Predicate> where = new ArrayList<Predicate>();

		query.select(admissionRoot);
		where.add(admissionNotDeletedPredicate(cb, admissionRoot));
		Join<Admission, Patient> patient = admissionRoot.join("patient");
		if (terms != null) {
			where.addAll(termsPredicates(terms, cb, patient));
		}
		where.add(patientNotDeletedPredicate(cb, patient));
		query.where(cb.and(where.toArray(new Predicate[where.size()])));
		query.orderBy(cb.desc(patient.get("code")));

    	return query;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admission> findAllBySearchAndDateRanges(String searchTerms, GregorianCalendar[] admissionRange,
														GregorianCalendar[] dischargeRange) {
		return this.entityManager.
				createQuery(createQuerySearchingForPatientContainingGivenWordsInHisProperties(getTermsToSearch(searchTerms), admissionRange, dischargeRange)).
					getResultList();
	}

	private CriteriaQuery<Admission> createQuerySearchingForPatientContainingGivenWordsInHisProperties(String[] terms,
																									   GregorianCalendar[] admissionRange,
																									   GregorianCalendar[] dischargeRange) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Admission> query = cb.createQuery(Admission.class);
		Root<Admission> admissionRoot = query.from(Admission.class);
		List<Predicate> where = new ArrayList<Predicate>();

		query.select(admissionRoot);
		where.add(admissionNotDeletedPredicate(cb, admissionRoot));
		Join<Admission, Patient> patient = admissionRoot.join("patient");
		if (terms != null) {
			where.addAll(termsPredicates(terms, cb, patient));
		}
		where.add(patientNotDeletedPredicate(cb, patient));
		where.addAll(dateRangePredicates(cb, admissionRoot, admissionRange, dischargeRange));
		query.where(cb.and(where.toArray(new Predicate[where.size()])));
		query.orderBy(cb.desc(patient.get("code")));

		return query;
	}

	private List<Predicate> dateRangePredicates(CriteriaBuilder cb,
										 Root<Admission> admissionRoot,
										 GregorianCalendar[] admissionRange,
										 GregorianCalendar[] dischargeRange) {
		List<Predicate> predicates = new ArrayList<Predicate>();

		if(admissionRange != null) {
			if (admissionRange.length == 2 && admissionRange[0] != null && admissionRange[1] != null) {
				predicates.add(
					cb.and(
						cb.greaterThanOrEqualTo(admissionRoot.<Date>get("admDate"), getBeginningOfDay(admissionRange[0]).getTime()),
						cb.lessThan(admissionRoot.<Date>get("admDate"),  getBeginningOfNextDay(admissionRange[1]).getTime()))
					);
			}
		}

		if (dischargeRange != null) {
			if (dischargeRange.length == 2 && dischargeRange[0] != null && dischargeRange[1] != null) {
				predicates.add(
					cb.and(
						cb.greaterThanOrEqualTo(admissionRoot.<Date>get("disDate"), getBeginningOfDay(dischargeRange[0]).getTime()),
						cb.lessThan(admissionRoot.<Date>get("disDate"), getBeginningOfNextDay(dischargeRange[1]).getTime())
					));
			}
		}
		return predicates;
	}

	private Predicate admissionNotDeletedPredicate(CriteriaBuilder cb, Root<Admission> admissionRoot) {
		return cb.or(
			cb.equal(admissionRoot.get("deleted"), "N"),
			cb.isNull(admissionRoot.get("deleted"))
		);
	}

	private Predicate patientNotDeletedPredicate(CriteriaBuilder cb, Join<Admission, Patient> patient) {
		return cb.or(
			cb.equal(patient.get("deleted"), "N"),
			cb.isNull(patient.get("deleted"))
		);
	}

	private List<Predicate> termsPredicates(String[] terms, CriteriaBuilder cb, Join<Admission, Patient> patient) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		for(String term : terms) {
			predicates.add(wordExistsInOneOfPatientFields(term, cb, patient));
		}
		return predicates;
	}


	private Predicate wordExistsInOneOfPatientFields(String word, CriteriaBuilder cb, Join<Admission, Patient> patientRoot) {
		return cb.or(
			cb.like(cb.lower(patientRoot.get("code").as(String.class)), like(word)),
			cb.like(cb.lower(patientRoot.get("secondName").as(String.class)), like(word)),
			cb.like(cb.lower(patientRoot.get("firstName").as(String.class)), like(word)),
			cb.like(cb.lower(patientRoot.get("note").as(String.class)), like(word)),
			cb.like(cb.lower(patientRoot.get("taxCode").as(String.class)), like(word))
		);
	}

	private String like(String word) {
		return "%" + word + "%";
	}
}
