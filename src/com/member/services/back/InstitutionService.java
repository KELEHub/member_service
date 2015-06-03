package com.member.services.back;

import com.member.entity.Institution;

public interface InstitutionService {
	
    public Institution getInstitutionInfo();
	
	public boolean setInstitution(Institution institution);

}
