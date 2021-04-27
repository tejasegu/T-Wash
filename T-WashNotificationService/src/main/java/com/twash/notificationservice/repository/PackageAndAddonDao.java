package com.twash.notificationservice.repository;

import java.util.List;
import java.util.Optional;

import com.twash.notificationservice.model.Addon;
import com.twash.notificationservice.model.Packages;

public interface PackageAndAddonDao {

	//Package Methods
	public String addPackages(Packages pack);
	public List<Packages> getAllPackages();
	public Optional<Packages> getPackagesbyId(String id);
	public void  updatePackageById(String id, Packages pack);
    public String deletePackageById (String id);
    
    //Addon Methods
    public String addAddon(Addon addon);
	public List<Addon> getAllAddons();
	public Optional<Addon> getAddonbyId(String id);
	public String  updateAddonById(String id, Addon add);
    public String deleteAddonById (String id);
    
    
    
}
