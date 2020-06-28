package com.ufps.edu.controlador;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ufps.edu.model.dao.IAccesoVisitanteDao;
import com.ufps.edu.model.dao.IVisitanteDao;
import com.ufps.edu.model.entidades.AccesoVisitante;
import com.ufps.edu.model.entidades.Visitante;

@Controller()
@RequestMapping("/home")
public class RegistroVisitanteController {

	@Autowired
	private IVisitanteDao visitanteDao;
	
	@Autowired
	private IAccesoVisitanteDao accesoVisitanteDao;
	
	@GetMapping("validarVisitantes")
	public String redireccionarValidarVisitante(@RequestParam(required = false) String documento, Model model) {
		if(documento==null||documento.isEmpty()) {
			model.addAttribute("error", "Digite su cedula");
			return "validarVisitante";
		}else {
			Optional<Visitante> opt=visitanteDao.findByDocumento(documento);
			if(!opt.isPresent()) {
				return "datosVisitante";
			}else {
				Visitante visitante=opt.get();
				model.addAttribute("documento", visitante.getDocumento());
				model.addAttribute("nombre", visitante.getNombre());
				model.addAttribute("fechaNacimiento", visitante.getFechaNacimiento());
				model.addAttribute("telefono", visitante.getTelefono());
				model.addAttribute("eps", visitante.getEps());
				model.addAttribute("genero", visitante.getGenero());
				return "datosVisitante";
			}
		}
		
	}
	
	@GetMapping("registroVisitantes")
	public String redireccionarRegistrarVisitante() {
		return "datosVisitante";
	}
	
	@PostMapping("registroVisitantes")
	public String registrarVisitante(@RequestParam String documento,@RequestParam String nombre,
			@RequestParam String fechaNacimiento,@RequestParam String telefono, @RequestParam String eps, 
			@RequestParam String genero,@RequestParam String temperatura, Model model) {
		
		if(documento==null||documento.isEmpty()||nombre==null||nombre.isEmpty()||
				fechaNacimiento==null||fechaNacimiento.isEmpty()||telefono==null||telefono.isEmpty()||
						eps==null||eps.isEmpty()||genero==null||genero.isEmpty()
				||temperatura==null||temperatura.isEmpty()) {
			model.addAttribute("error", "Llene todos los datos del formulario");
			return "datosVisitante";
		}
		else {
			Optional<Visitante> opt=visitanteDao.findByDocumento(documento);
			if(!opt.isPresent()) {
				Visitante visitante=new Visitante();
				visitante.setDocumento(documento);
				visitante.setEps(Long.parseLong(eps));
				visitante.setFechaNacimiento(LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				visitante.setGenero(genero);
				visitante.setTelefono(telefono);
				visitante.setNombre(nombre);
				Visitante vis=visitanteDao.save(visitante);
				
				AccesoVisitante accesoVisitante=new AccesoVisitante();
				accesoVisitante.setFecha(new Date());
				accesoVisitante.setTemperatura(Long.parseLong(temperatura));
				accesoVisitante.setVisitante(vis.getId());
				accesoVisitanteDao.save(accesoVisitante);
			}else {
				Visitante visitante=opt.get();
				AccesoVisitante accesoVisitante=new AccesoVisitante();
				accesoVisitante.setFecha(new Date());
				accesoVisitante.setTemperatura(Long.parseLong(temperatura));
				accesoVisitante.setVisitante(visitante.getId());
				accesoVisitanteDao.save(accesoVisitante);
				
			}
			model.addAttribute("success", "Registro exitoso");
			return "home";
		}
	}
	
}
