package com.member.services.back;

import java.util.List;

import com.member.entity.Tickling;

public interface TicklingManagerService {

	List<Tickling> getTicklingByState(Integer state);

	void setTickling(Tickling tickling);

}
