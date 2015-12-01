/**
 * (c) 2015 ADMB. All rights reserved.
 */
package be.provikmo.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import be.provikmo.model.Event;
import be.provikmo.model.Translation;
import be.provikmo.service.EventRepo;
import be.provikmo.service.TranslationRepo;

/**
 * @author infglef
 *
 */
@Component
public class DummyDataCLR implements CommandLineRunner {

	@Autowired
	private EventRepo repo;

	@Autowired
	private TranslationRepo transRepo;

	/** {@inheritDoc} */
	@Override
	public void run(String... args) throws Exception {

		Event eventEen = new Event("een event", 2, 4, 15);
		Event eventTwee = new Event("een event", 7, 8, 15);
		Event eventDrie = new Event("een event", 6, 15, 15);
		eventDrie.setColor("orange");
		repo.save(eventEen);
		repo.save(eventTwee);
		repo.save(eventDrie);

		Translation budgetParrent = new Translation();
		budgetParrent.setKey("BUDGET_KEY");
		budgetParrent.setLocale("nl_BE");
		budgetParrent.setValue("PreventieBudget");

		Translation budgetOpmaak = new Translation();
		budgetOpmaak.setKey("BUDGET_SIM_KEY");
		budgetOpmaak.setLocale("nl_BE");
		budgetOpmaak.setValue("Opmaak");

		Translation budgetBeheer = new Translation();
		budgetBeheer.setKey("BUDGET_BEHEER_KEY");
		budgetBeheer.setLocale("nl_BE");
		budgetBeheer.setValue("Beheer");

		Translation budgetCalculator = new Translation();
		budgetCalculator.setKey("BUDGET_CALCULATOR_KEY");
		budgetCalculator.setLocale("nl_BE");
		budgetCalculator.setValue("Calculator");

		Translation budgetFaturatie = new Translation();
		budgetFaturatie.setKey("BUDGET_FACTURATIE_KEY");
		budgetFaturatie.setLocale("nl_BE");
		budgetFaturatie.setValue("Facturatie");

		Translation main = new Translation();
		main.setKey("RB_KEY");
		main.setLocale("nl_BE");
		main.setValue("Home");

		Translation testHome = new Translation();
		testHome.setKey("RB_HOME_KEY");
		testHome.setLocale("nl_BE");
		testHome.setValue("Test home");

		transRepo.save(budgetParrent);
		transRepo.save(budgetOpmaak);
		transRepo.save(budgetBeheer);
		transRepo.save(budgetCalculator);
		transRepo.save(budgetFaturatie);
		transRepo.save(main);
		transRepo.save(testHome);

	}

}
