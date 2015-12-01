/**
 * (c) 2015 ADMB. All rights reserved.
 */
package be.provikmo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import be.provikmo.model.Translation;

/**
 * @author infglef
 *
 */
@Service
public interface TranslationRepo extends JpaRepository<Translation, Long> {

	List<Translation> findByKeyAndLocale(String key, String locale);

}
