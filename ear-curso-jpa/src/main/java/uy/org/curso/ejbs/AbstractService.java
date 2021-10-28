package uy.org.curso.ejbs;

import java.io.Serializable;

import javax.persistence.EntityManager;

/**
 * Servicio abstracto del cual heredan el resto de los servicios
 * La idea es que aqui se implementen los metodos que con comunes a todos los servicios
 * @author juan
 *
 * @param <E> Entity con la cual va a trabajar el servicio
 * @param <PK> Tipo de la PK de la entity
 */
public abstract class AbstractService<E extends Serializable, PK extends Serializable> {

	private final transient Class<E> entityClass;

	public AbstractService(final Class<E> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Hook operation a ser implementada en las clases derivadas
	 * @return EntityManager
	 */
	protected abstract EntityManager getEntityManager();

	
	/**
	 * Realiza el persist de una entity
	 * @param entity Entity a persistir
	 */
	public void create(final E entity) {
		final EntityManager entityManager = getEntityManager();
		entityManager.persist(entity);
	}

	/**
	 * Busca una entity por la clave
	 * @param id clave de la entity
	 * @return Entity con dicha clave
	 */
	public final E find(final PK id) {
		return getEntityManager().find(entityClass, id);
	}

	/**
	 * Realiza el merge de una entity
	 * @param entity Entity a mergear
	 * @return Entity mergeada
	 */
	public E update(final E entity) {
		final EntityManager entityManager = getEntityManager();
		return entityManager.merge(entity);
	}
	
	/**
	 * Elimina una entity
	 * @param entity Entity a eliminar
	 */
	public void delete(final E entity) {
		final EntityManager entityManager = getEntityManager();
		entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
	}
}
