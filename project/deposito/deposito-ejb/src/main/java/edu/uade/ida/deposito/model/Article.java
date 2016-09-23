package edu.uade.ida.deposito.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Articulo
 * 
 * @author pcarle
 *
 */
@Entity
public class Article {
	
	@Id
	private String sku;
	private String description;
	private String uom;
	private ArticleType type;
	private int purchaseStock;
	private int availableStock;
	
	
}
