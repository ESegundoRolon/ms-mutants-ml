package org.github.erolon.controllers;

import javax.validation.Valid;

import org.github.erolon.dto.DNASequenceRequest;
import org.github.erolon.model.DNASequence;
import org.github.erolon.service.IDNAValidateService;
import org.github.erolon.service.IMutantValidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {
	
	@Autowired
	IMutantValidateService mutantService;
	
	@Autowired
	IDNAValidateService mutantValidationService;
	

	@RequestMapping(value = "mutant", method = RequestMethod.POST)
	public Object mutantDetector(@RequestBody @Valid DNASequenceRequest request, BindingResult result) throws BindException {
		return mutantService.verifyMutation(new DNASequence(request));
	}
}
