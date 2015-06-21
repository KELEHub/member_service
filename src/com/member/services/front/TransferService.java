package com.member.services.front;

import com.member.entity.Information;
import com.member.entity.SystemParameter;
import com.member.form.front.Transform;

public interface TransferService {

	public void transferManager(Information from,Information to,Transform form,SystemParameter parameter);
}
