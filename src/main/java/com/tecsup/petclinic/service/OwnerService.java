package com.tecsup.petclinic.service;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

public interface OwnerService{
	
	Owner create(Owner owner);
	/**
	 * 
	 * @param id
	 * @return
	 **/
	Owner update(Owner owner);
	
	void delete(long id) throws OwnerNotFoundException;
	
	
	Owner findById(long id) throws OwnerNotFoundException;
	
	Iterable<Owner> findAll();
	void delete(Long id) throws OwnerNotFoundException;
}
