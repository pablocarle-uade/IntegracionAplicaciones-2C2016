package edu.uade.ida.deposito.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Tipos de articulo
 * 
 * @author pcarle
 *
 */
@Entity
public class ArticleType {

	@Id
	private int articleTypeId;
	
}
