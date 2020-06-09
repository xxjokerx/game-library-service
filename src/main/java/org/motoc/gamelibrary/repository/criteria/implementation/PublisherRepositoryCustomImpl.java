package org.motoc.gamelibrary.repository.criteria.implementation;

import org.motoc.gamelibrary.model.Game;
import org.motoc.gamelibrary.model.Publisher;
import org.motoc.gamelibrary.repository.criteria.PublisherRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

/**
 * It's the category custom repository implementation, made to create / use javax persistence objects, criteria, queryDSL (if needed)
 */
@Repository
public class PublisherRepositoryCustomImpl implements PublisherRepositoryCustom {

    private static final Logger logger = LoggerFactory.getLogger(PublisherRepositoryCustomImpl.class);

    private final EntityManager entityManager;

    @Autowired
    public PublisherRepositoryCustomImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Removes a publisher
     */
    @Override
    public void remove(Long id) {
        Publisher publisher = entityManager.find(Publisher.class, id);
        publisher.removeContact(publisher.getContact());

        for (Game game : publisher.getGames()) {
            game.removePublisher(publisher);
        }
        entityManager.remove(publisher);
    }
}
