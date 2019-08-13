package com.jk.service;

import com.jk.model.NavBean;

import java.util.List;

public interface NavService {

	List<NavBean> findNavList();

	void savePower(NavBean n);

	void delPowerNode(Integer powerId);

	NavBean findPowerInfo(Integer powerId);

	void updatePower(NavBean n);


}
