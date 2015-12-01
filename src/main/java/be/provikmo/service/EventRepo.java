/**
 * (c) 2015 ADMB. All rights reserved.
 */
package be.provikmo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import be.provikmo.model.Event;

/**
 * @author infglef
 *
 */
@Service
public interface EventRepo extends JpaRepository<Event, Long> {

}