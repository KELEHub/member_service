package com.member.services.back;

import java.util.List;

import com.member.entity.Tickling;
import com.member.form.back.TickForm;

public interface TicklingManagerService {

	List<Tickling> getTicklingByState(Integer state);

	void setTickling(Tickling tickling);

	void deleteTickling(TickForm form);

	void updateTickling(TickForm form);

	List<Tickling> getTicklingById(Integer id);
	
	public List<Tickling> getTicklingByNumberState(Integer state,String usernumber);

}
