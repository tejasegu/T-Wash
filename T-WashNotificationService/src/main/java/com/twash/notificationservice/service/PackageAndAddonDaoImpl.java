package com.twash.notificationservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twash.notificationservice.model.Addon;
import com.twash.notificationservice.model.Packages;
import com.twash.notificationservice.repository.AddonRepository;
import com.twash.notificationservice.repository.PackageAndAddonDao;
import com.twash.notificationservice.repository.PackageRepository;

@Service
public class PackageAndAddonDaoImpl implements PackageAndAddonDao{
	
	@Autowired
	private PackageRepository packagerepo;

	@Override
	public String addPackages(Packages pack) {
		packagerepo.save(pack);
		return "Package Added Scucessfully"  ;
	}

	@Override
	public List<Packages> getAllPackages() {
		
		return packagerepo.findAll();
	}

	@Override
	public Optional<Packages> getPackagesbyId(String id) {
		
		return packagerepo.findById(id);
	}

	@Override
	public void updatePackageById(String id, Packages pack ) {
              Optional<Packages> packag=packagerepo.findById(id);
              Packages packagetoupdate=packag.get();
              packagetoupdate.setHback(pack.getHback());
              packagetoupdate.setPackagename(pack.getPackagename());
              packagetoupdate.setSedan(pack.getSedan());
              packagetoupdate.setSuv(pack.getSuv());
              packagerepo.save(packagetoupdate);
		
	}

	@Override
	public String deletePackageById(String id) {
		packagerepo.deleteById(id);
		return "Deleted Successfully";
	}

	
	
	@Autowired
	private AddonRepository addonrepo;
	
	@Override
	public String addAddon(Addon addon) {
		addonrepo.save(addon);
		return "Addon Added Succesfully";
	}

	@Override
	public List<Addon> getAllAddons() {
		return addonrepo.findAll();
	}

	@Override
	public Optional<Addon> getAddonbyId(String id) {

		return addonrepo.findById(id);
	}

	@Override
	public String updateAddonById(String id, Addon add) {
		Optional<Addon> addon=addonrepo.findById(id);
		Addon addontoupdate=addon.get();
		addontoupdate.setAddonname(add.getAddonname());
		addontoupdate.setPrice(add.getPrice());
		addonrepo.save(addontoupdate);
				return "Addon Updated Successfully";
	}

	@Override
	public String deleteAddonById(String id) {
		addonrepo.deleteById(id);
		return "Deleted Successfully";
	}

}
