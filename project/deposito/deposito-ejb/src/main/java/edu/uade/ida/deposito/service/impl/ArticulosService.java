package edu.uade.ida.deposito.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.uade.ida.deposito.dto.ArticuloDTO;
import edu.uade.ida.deposito.dto.CreateArticuloRequestDTO;
import edu.uade.ida.deposito.dto.ModificacionStockRequestDTO;
import edu.uade.ida.deposito.dto.NotificacionNuevoArticuloDTO;
import edu.uade.ida.deposito.dto.SearchArticulosDTO;
import edu.uade.ida.deposito.model.Articulo;
import edu.uade.ida.deposito.model.TipoDeArticulo;
import edu.uade.ida.deposito.repository.ArticuloRepository;
import edu.uade.ida.deposito.service.ArticulosServiceLocal;
import edu.uade.ida.deposito.service.integration.DespachoServiceLocal;
import edu.uade.ida.deposito.service.integration.LogisticaMonitoreoServiceLocal;
import edu.uade.ida.deposito.service.integration.PortalServiceLocal;
import edu.uade.ida.deposito.util.DTOUtil;
import edu.uade.ida.deposito.util.NivelAudit;

@Stateless
public class ArticulosService implements ArticulosServiceLocal {
	
    @Inject
    private Logger log;
	
	@Inject
	private EntityManager em;
	
	@Inject 
	private ArticuloRepository articuloRepository;
	
	@Inject
	private LogisticaMonitoreoServiceLocal lms;
	
	@Inject
	private DespachoServiceLocal ds;
	
	@Inject
	private PortalServiceLocal ps;
	
	@Override
	public ArticuloDTO crearArticulo(CreateArticuloRequestDTO dto) {
		ArticuloDTO nuevoArticuloDTO = null;
        try {
        	Articulo articulo = new Articulo(dto.getCodArticulo(), dto.getNombre(), dto.getDescripcion(), dto.getMarca(),
										 	 dto.getPrecio(), dto.getFoto(), dto.getOrigen(), TipoDeArticulo.parse(dto.getTipo()),
										 	 dto.getStock(), dto.getDatosExtra());
        	em.persist(articulo);
        	nuevoArticuloDTO = articulo.getDTO();
        	// Registrar en Logística y Monitoreo
        	lms.enviarAudit(NivelAudit.INFO, "Registrado nuevo artículo, código de artículo: " + articulo.getCodArticulo());        	
        	// Generar notificación para módulos interesados
        	NotificacionNuevoArticuloDTO notificacionNuevoArticulo = new NotificacionNuevoArticuloDTO("GO1",
					articulo.getCodArticulo(), articulo.getNombre(), articulo.getDescripcion(), articulo.getMarca(),
					articulo.getPrecio(), articulo.getFoto(), articulo.getOrigen(), articulo.getTipo().toString(),
					articulo.getDatosExtra());
        	// notificar a Despacho/s y Portal/es
        	ds.noticarNuevoArticulo(notificacionNuevoArticulo);
        	ps.noticarNuevoArticulo(notificacionNuevoArticulo);        	
        	
        } catch(Exception ex) {
        	this.lms.enviarAudit(NivelAudit.ERROR, "Registrado error al crear nuevo artículo por " + "GO1");
        	log.info("Error al crear artículo: " + ex.getMessage());
        }
        return nuevoArticuloDTO;
	}
	
	@Override
	public void modificarStockDeArticulos(List<ModificacionStockRequestDTO> modificaciones) {
		for (ModificacionStockRequestDTO modificacion: modificaciones) {
			Articulo articulo = articuloRepository.get(modificacion.getIdArticulo());
			try {
				modificarStockDeArticulo(articulo, modificacion.getNuevoStock());
				log.info("Se modificó stock de artículo con éxito: " + "codArticulo: " + articulo.getCodArticulo() + ", nuevoStock: " + articulo.getStock());
			} catch (Exception e) {
				log.warning("Error al modificar stock de artículo: " + "codArticulo: " + articulo.getCodArticulo() + " " + e.getMessage());	
			}
		}
	}
	
	private Articulo modificarStockDeArticulo(Articulo articulo, Integer nuevoStock) throws Exception {
		if (nuevoStock < 0) throw new Exception("Stock incorrecto");
		articulo.setStock(nuevoStock);
		return em.merge(articulo);
	}

	@Override
	public List<ArticuloDTO> getArticulosPorIds(List<Long> ids) {
		List<Articulo> articulos = articuloRepository.getPorIds(ids);
		return DTOUtil.getDTOs(articulos, ArticuloDTO.class);
	}

	@Override
	public List<ArticuloDTO> buscarArticulos(SearchArticulosDTO searchArticulosDTO) {
		List<Articulo> articulos = articuloRepository.findAll(); // TODO Search (criteria / hql)
		return DTOUtil.getDTOs(articulos, ArticuloDTO.class);
	}

	@Override
	public int createArticulosDefault() {
		CreateArticuloRequestDTO[] articulosDefault = new CreateArticuloRequestDTO[]{
				new CreateArticuloRequestDTO("1351953", "Aire Acondicionado Split WBC 12B-13B 2645 F/C", "4 modos de operación diferentes: Frío, deshumidificación, ventilación y calor", "Whirlpool", new BigDecimal("4633"), "url", "Argentina", TipoDeArticulo.Electro.toString(), 20, null),
				new CreateArticuloRequestDTO("3122", "Musculosa Pale", "Musculosa Basement con Breteles", "Basement", new BigDecimal("79"), "url", "Brasil", TipoDeArticulo.Moda.toString(), 30, null),
				new CreateArticuloRequestDTO("1857363", "Mesa para TV 21\" wengue", "Mesa para TV", "Mica", new BigDecimal("409"), "url", "Argentina", TipoDeArticulo.Mueble.toString(), 40, null),
				new CreateArticuloRequestDTO("1858018", "Coche Paraguas Gris", "Coche Paraguas", "Love", new BigDecimal("529"), "url", "China", TipoDeArticulo.Niños.toString(), 50, null)
		};
		log.info("Crear articulos default");
		for (int i = 0; i < articulosDefault.length; i++) {
			crearArticulo(articulosDefault[i]);
		}
		log.info("Creados " + articulosDefault.length + " articulos");
		return articulosDefault.length;
	}

}
