package com.twash.notificationservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.twash.notificationservice.model.Addon;
import com.twash.notificationservice.model.Packages;
import com.twash.notificationservice.service.PackageAndAddonDaoImpl;

import io.swagger.v3.oas.annotations.Operation;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class PackageAndAddonController {
	@Autowired
	private PackageAndAddonDaoImpl packagedao;
	
	
	 //To Insert package Into DataBase
	@Operation(summary = "Adds a new package")
	@PostMapping("/package")
	public ResponseEntity<String> addPackage(  @RequestBody Packages pack) {
		try {
			packagedao.addPackages(pack);
			return ResponseEntity.ok("Package added suceesfully with Id:"+pack.getId());
			}
			catch (Exception e) {
				 return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
			}
	}
	
	//To get all the packages
	@Operation(summary="To get all the list of packages")
	@GetMapping("/packages")
	public ResponseEntity<?> getAllPackages(){
		List<Packages> allpack=packagedao.getAllPackages();
		return ResponseEntity.ok(allpack);
	}
	
	
	 //To Get All the Packages from id
	@Operation(summary = "Get all  packages from database using id")
	@GetMapping("/packages/find/{id}")
	public ResponseEntity<?> findPackageById (@PathVariable("id") String id) {
		try {
			Optional<Packages> pack=packagedao.getPackagesbyId(id);
			
			return  ResponseEntity.ok(pack);
		}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//To update the package using id
	@Operation(summary = "Update the package using id")
	@GetMapping("/packages/update/{id}")
	public ResponseEntity<?> updatePackageById(@PathVariable("id") String id, Packages pack){
		try {
			packagedao.updatePackageById(id, pack);	
			return ResponseEntity.ok("Package Updated Sucessfully");
			}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//To delete the package
	@Operation(summary = "To Delete the package using id")
	@GetMapping("/packages/delete/{id}")
	public ResponseEntity<?> deletePackageById(@PathVariable("id") String id){
		try {
			packagedao.deletePackageById(id);	
			return ResponseEntity.ok("Package Deleted Sucessfully");
			}
		catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	 //To Insert Addon Into DataBase
		@Operation(summary = "Adds a new addon")
		@PostMapping("/addon")
		public ResponseEntity<String> addAddon( @Validated  @RequestBody Addon addon) {
			try {
				packagedao.addAddon(addon);
				return ResponseEntity.ok("Addon added suceesfully with Id:"+addon.getId());
				}
				catch (Exception e) {
					 return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
				}
		}
		
		//To get all the Addons
		@GetMapping("/addon")
		@Operation(summary="To get all the list of Addons")
		public ResponseEntity<?> getAllAddons(){
			List<Addon> alladdon=packagedao.getAllAddons();
			return ResponseEntity.ok(alladdon);
		}
		
		
		 //To Get  the Addon from id
		
		@Operation(summary = "Get all  addon from database using id")
		@GetMapping("/addon/find/{id}")
		public ResponseEntity<?> findaddonById (@PathVariable("id") String id) {
			try {
				Optional<Addon> addon=packagedao.getAddonbyId(id);
				
				return  ResponseEntity.ok(addon);
			}
			catch(Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
		}
		
		//To update the addon using id
		@Operation(summary = "Update the addon using id")
		@GetMapping("/addon/update/{id}")
		public ResponseEntity<?> updateAddonById(@PathVariable("id") String id, Addon addon){
			try {
				packagedao.updateAddonById(id, addon);	
				return ResponseEntity.ok("Addon Updated Sucessfully");
				}
			catch(Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		
		// To delete the addon using id 
		@Operation(summary = "To Delete the addon using id")
		@GetMapping("/addon/delete/{id}")
		public ResponseEntity<?> deleteAddonById(@PathVariable("id") String id){
			try {
				packagedao.deleteAddonById(id);	
				return ResponseEntity.ok("Addon Deleted Sucessfully");
				}
			catch(Exception e) {
				return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
}
