package com.poc.dynamicform.service.impl;

import org.springframework.stereotype.Service;

import com.poc.dynamicform.service.HelloWorldService;
import com.poc.dynamicform.web.form.HelloWordForm;

@Service
public class HelloWorldServiceImpl implements HelloWorldService {
	
	@Override
	public HelloWordForm loadForm(final Long id) {
		return null;
	}

}
