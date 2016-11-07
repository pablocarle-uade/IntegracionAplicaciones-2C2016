package edu.uade.ida.deposito.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.NotificacionNuevoArticuloDTO;
import edu.uade.ida.deposito.dto.SearchArticulosDTO;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.TipoDeArticulo;
import edu.uade.ida.deposito.repository.ArticuloRepository;
import edu.uade.ida.deposito.service.ArticulosServiceLocal;
import edu.uade.ida.deposito.service.integration.DespachoServiceLocal;
import edu.uade.ida.deposito.service.integration.LogisticaMonitoreoServiceLocal;
import edu.uade.ida.deposito.service.integration.NivelAudit;
import edu.uade.ida.deposito.service.integration.PortalServiceLocal;
import edu.uade.ida.deposito.util.DTOUtil;

@Stateless
public class ArticulosService implements ArticulosServiceLocal {
	
    @Inject
    private Logger log;
	
	@Inject
	private EntityManager entityManager;
	
	@Inject 
	private ArticuloRepository articuloRepository;
	
	@Inject
	private LogisticaMonitoreoServiceLocal logisticaYMonitoreoService;
	
	@Inject
	private DespachoServiceLocal despachoService;
	
	@Inject
	private PortalServiceLocal portalService;
	
	@Override
	public ArticuloDTO crearArticulo(ArticuloDTO dto) {
		log.info("Se ha solicitado crear artículo: " + dto.getCodArticulo());
        try {
        	Articulo articulo = new Articulo(dto.getCodArticulo(), dto.getNombre(), dto.getDescripcion(), dto.getMarca(),
										 	 dto.getPrecio(), dto.getFoto(), dto.getOrigen(), TipoDeArticulo.parse(dto.getTipo()),
										 	 dto.getStock(), dto.getDatosExtra());
        	entityManager.persist(articulo);
        	dto.setId(articulo.getId());
        	// sync/async configurable
        	// this.logisticaYMonitoreoService.enviarAudit(NivelAudit.INFO, "Registrado nuevo artículo por " + "GO1" + ", código de artículo: " + articulo.getCodArticulo());        	
        	// notificar nuevo art a despacho y portal (los dos async)
        	this.despachoService.noticarNuevoArticulo(new NotificacionNuevoArticuloDTO());
        	this.portalService.noticarNuevoArticulo(new NotificacionNuevoArticuloDTO());
        } catch(Exception ex) {
        	this.logisticaYMonitoreoService.enviarAudit(NivelAudit.ERROR, "Registrado error al crear nuevo artículo por " + "GO1");
        	log.info("Error al crear artículo: " + ex.getMessage());
        }
        return dto;
	}
	
	@Override
	public List<ArticuloDTO> buscarArticulos(SearchArticulosDTO searchArticulosDTO) {
		List<Articulo> articulos = articuloRepository.findAll(); // TODO Search (criteria / hql)
		return DTOUtil.getDTOs(articulos, ArticuloDTO.class);
	}

	@Override
	public int createArticulosDefault() {
		ArticuloDTO[] articulosDefault = new ArticuloDTO[]{
				new ArticuloDTO(-1, "1351953", "Aire Acondicionado Split WBC 12B-13B 2645 F/C", "4 modos de operación diferentes: Frío, deshumidificación, ventilación y calor", "Whirlpool", new BigDecimal("4633"), "url", "Argentina", TipoDeArticulo.Electro.toString(), 20, null),
				new ArticuloDTO(-1, "3122", "Musculosa Pale", "Musculosa Basement con Breteles", "Basement", new BigDecimal("79"), "url", "Brasil", TipoDeArticulo.Moda.toString(), 30, null),
				new ArticuloDTO(-1, "1857363", "Mesa para TV 21\" wengue", "Mesa para TV", "Mica", new BigDecimal("409"), "url", "Argentina", TipoDeArticulo.Mueble.toString(), 40, null),
				new ArticuloDTO(-1, "1858018", "Coche Paraguas Gris", "Coche Paraguas", "Love", new BigDecimal("529"), "url", "China", TipoDeArticulo.Niños.toString(), 50, null)
		};
		log.info("Crear articulos default");
		for (int i = 0; i < articulosDefault.length; i++) {
			crearArticulo(articulosDefault[i]);
		}
		log.info("Creados " + articulosDefault.length + " articulos");
		return articulosDefault.length;
	}

}
