package edu.uade.ida.deposito.service;

import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.TipoDeArticulo;

@Stateless
public class ArticulosService implements ArticulosServiceLocal, ArticulosServiceRemote {
	
    @Inject
    private Logger log;
	
	@Inject
	private EntityManager em;
	
	// TODO: Change response type to CrearArticuloResponseDTO, handle errors
	@Override
	public ArticuloDTO crearArticulo(ArticuloDTO dto) {
		log.info("Se ha solicitado crear artículo: " + dto.getCodArticulo());
        
        final Articulo articulo = new Articulo();
        articulo.setCodArticulo(dto.getCodArticulo());
        articulo.setDescripcion(dto.getDescripcion());
        articulo.setMarca(dto.getMarca());
        articulo.setOrigen(dto.getOrigen());
        articulo.setPrecio(dto.getPrecio());
        articulo.setTipo(TipoDeArticulo.valueOf(dto.getTipo()));
        articulo.setUrlImagen(dto.getUrlImagen());
        articulo.setDatosExtra(dto.getDatosExtra());
        em.persist(articulo);
        dto.setId(articulo.getId());

        return dto;
	}


}
