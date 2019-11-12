package com.tecsup.petclinic.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.domain.OwnerRepository;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

@Service
public class OwnerServiceImpl implements OwnerService{
	
private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);
	
	@Autowired
	OwnerRepository ownerRepository;
	
	
	/**
	 * 
	 * @param pet
	 * @return
	 */	
	@Override
	public Owner findById(long id) throws OwnerNotFoundException{

		Optional<Owner> owner = ownerRepository.findById(id);
		
		if ( !owner.isPresent())
			throw new OwnerNotFoundException("Record not found...!");
			
		return owner.get();
	}



	@Override
	public Iterable<Owner> findAll() {
		// TODO Auto-generated method stub
		return ownerRepository.findAll();
	}



	@Override
	public Owner create(Owner owner) {
		// TODO Auto-generated method stub
		return ownerRepository.save(owner);
	}



	@Override
	public Owner update(Owner owner) {
		// TODO Auto-generated method stub
		return ownerRepository.save(owner);
	}



	@Override
	public void delete(Long id) throws OwnerNotFoundException {
		// TODO Auto-generated method stub
		Owner owner = findById(id);
		ownerRepository.delete(owner);
	}



	@Override
	public void delete(long id) throws OwnerNotFoundException {
		// TODO Auto-generated method stub
		
	}
}