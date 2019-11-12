package com.tecsup.petclinic.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tecsup.petclinic.domain.Owner;
import com.tecsup.petclinic.exception.OwnerNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= Replace.NONE)
public class OwnerServiceTest {
		
		private static final Logger logger=LoggerFactory.getLogger(OwnerServiceTest.class);
		
		@Autowired
		private OwnerService ownerService;
		
		@Test
		public void testFindOwnerById() {
			long id=1;
			String first_name="George";
			Owner owner=null;
			
			try {
				owner= ownerService.findById(id);
			}catch(OwnerNotFoundException e1) {
				fail(e1.getMessage());
			}
			logger.info(""+owner);
			
			assertEquals(first_name, owner.getFirst_name());
		}
		
		@Test
		public void testCreateOwner() {
			String first_name="Luis Angel";
			String last_name="Gastelu Condori";
			String address="216 Edelmira del Pando";
			String city="Lima";
			String telephone="950277468";

			Owner owner=new Owner(first_name,last_name, address, city, telephone);
			owner= ownerService.create(owner);
			logger.info(""+owner);
			
			assertEquals(first_name,owner.getFirst_name());
			assertEquals(last_name,owner.getLast_name());
			assertEquals(address,owner.getAddress());
			assertEquals(city,owner.getCity());
			assertEquals(telephone,owner.getTelephone());
		}


		
		@Test
		public void testDelete() {
			String first_name="Luis Angel";
			String last_name="Gastelu Condori";
			String address="216 Edelmira del Pando";
			String city="Lima";
			String telephone="950277468";
			
			Owner owner=new Owner(first_name, last_name, address, city, telephone);
			owner= ownerService.create(owner);
			logger.info(""+owner);
			
			try {
				ownerService.delete(owner.getId());
			}catch(OwnerNotFoundException e1) {
				fail(e1.getMessage());
			}
			try {
				ownerService.findById(owner.getId());
				assertTrue(true);
			}catch(OwnerNotFoundException e2) {
				assertTrue(false);
			}
		}
		
		@Test
		public void testUpdateOwner() {
			String first_name="Luis Angel";
			String last_name="Gastelu Condori";
			String address="216 Edelmira del Pando";
			String city="Lima";
			String telephone="950277468";
			long id=1;
			
			String up_first_name="Elver";
			String up_last_name="Teja";
			String up_address="Callao City";
			String up_city="La molina";
			String up_telephone="948725613";
			
			Owner owner=new Owner(first_name, last_name, address, city, telephone);
			
			logger.info(">"+ owner);
			Owner leerOwner= ownerService.create(owner);
			logger.info(">>"+leerOwner);
			
			id=leerOwner.getId();
			
			leerOwner.setFirst_name(up_first_name);
			leerOwner.setLast_name(up_last_name);
			leerOwner.setAddress(up_address);
			leerOwner.setCity(up_city);
			leerOwner.setTelephone(up_telephone);
			
			Owner upgradeOwner= ownerService.update(leerOwner);
			logger.info(">>>>"+upgradeOwner);
			
			assertThat(id).isNotNull();
			assertEquals(id,upgradeOwner.getId());
			assertEquals(up_first_name,upgradeOwner.getFirst_name());
			assertEquals(up_last_name,upgradeOwner.getLast_name());
			assertEquals(up_address,upgradeOwner.getAddress());
			assertEquals(up_city,upgradeOwner.getCity());
			assertEquals(up_telephone,upgradeOwner.getTelephone());
		}
		
}
