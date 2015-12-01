/**
 * (c) 2015 ADMB. All rights reserved.
 */
package be.provikmo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import be.ikmo.core.common.util.LocaleUtil;
import be.ikmo.core.ui.knowledgecenter.KnowledgeCenter;
import be.provikmo.commons.exceptions.ServiceException;
import be.provikmo.model.Translation;
import be.provikmo.service.TranslationRepo;

/**
 * @author infglef
 *
 */
@Controller
public class TranslationController {

	@Autowired
	private TranslationRepo transRepo;

	@Autowired
	private KnowledgeCenter knowledgeCenter;

	@RequestMapping("/translation")
	public ResponseEntity<Translation> getTranslations(@Param("lang") String lang, @Param("key") String key)
		throws ServiceException {
		Translation translation = new Translation();
		translation.setKey(key);
		translation.setLocale(lang);
		translation.setValue(knowledgeCenter.translateValue(key, LocaleUtil.getLocaleForTaalcode(lang)));
		if (translation.getValue().contains("KEY")) {
			translation.setValue(transRepo.findByKeyAndLocale(key, lang).get(0).getValue());
		}
		return new ResponseEntity<Translation>(translation, HttpStatus.OK);
	}

}
