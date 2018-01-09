package com.poc.dynamicform.service;

import com.poc.dynamicform.web.form.HelloWordForm;

public interface HelloWorldService {
	
	HelloWordForm loadForm(final Long id);
}