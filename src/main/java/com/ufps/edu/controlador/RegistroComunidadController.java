package com.ufps.edu.controlador;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.ufps.edu.model.dao.IBasicoDao;
import com.ufps.edu.model.dao.IComorbilidadDao;
import com.ufps.edu.model.dao.IRegistroDao;
import com.ufps.edu.model.entidades.Basico;
import com.ufps.edu.model.entidades.Comorbilidad;
import com.ufps.edu.model.entidades.Registro;
import com.ufps.edu.negocio.JsonResultado;

@Controller()
@RequestMapping("/home")
public class RegistroComunidadController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private IBasicoDao basicoDao;
	
	@Autowired
	private IComorbilidadDao comorbilidadDao;
	
	@Autowired
	private IRegistroDao registroDao;

	@GetMapping("validarComunidad")
	public String redireccionarValidarVisitante(@RequestParam(required = false) String documento, Model model) {
		if(documento==null||documento.isEmpty()) {
			model.addAttribute("error", "Digite su cedula");
			return "validarComunidad";
		}else {
			String url = "http://siaweb.ufps.edu.co/prueba.php?documento=";
			String str = restTemplate.getForObject(url+documento, String.class);
			//System.out.println("esta es la direccion con documento"+str);
			JsonResultado response = new Gson().fromJson(str, JsonResultado.class);
			//JsonResultado response = restTemplate.getForObject(url+documento, JsonResultado.class);
			//System.out.println("esta es la direccion con documento"+response.getNombre());
			if(response.getError()) {
				model.addAttribute("error", "La cedula no existe en el sistema");
				return "validarComunidad";
			}else {
				Optional<Basico> opt=basicoDao.findByDocumento(documento);
				model.addAttribute("documento", documento);
				model.addAttribute("nombre", response.getNombre());
				model.addAttribute("tipoPersona", response.getTipo());
				if(opt.isPresent()) {
					Basico basico=opt.get();
				if(basico.getEps()!=null) {
					model.addAttribute("eps", basico.getEps());
				}if(basico.getGenero()!=null) {
					model.addAttribute("genero", basico.getGenero());
				}if(basico.getEmbarazo()!=null) {
					model.addAttribute("embarazo", basico.getEmbarazo());
				}if(basico.getModalidad()!=null) {
					model.addAttribute("modalidad", basico.getModalidad());
				}if(basico.getFechaNacimiento()!=null) {
					model.addAttribute("fechaNacimiento", basico.getFechaNacimiento());
				}if(basico.getModalidad()!=null) {
					model.addAttribute("telefono", basico.getTelefono());
				}
				
				return "datosPersonales";
				
				}else {
					return "datosPersonales";
				}
			}
		}
		
	}
	
	@PostMapping("registrarDatosPersonales")
	public String registrarDatosPersonales(@RequestParam String documento, @RequestParam String nombre,
			@RequestParam String fechaNacimiento, @RequestParam String telefono,
			@RequestParam String tipoPersona, @RequestParam String eps, @RequestParam String genero,
			@RequestParam(required = false, defaultValue = "false") String embarazo, @RequestParam String modalidad, Model model) throws ParseException {
		Optional<Basico> opt=basicoDao.findByDocumento(documento);
		System.out.println(embarazo);
		if(opt.isPresent()) {
			Basico basico=opt.get();
			model.addAttribute("documento", basico.getDocumento());
			model.addAttribute("mas60", basico.getMas60());
			model.addAttribute("menos15", basico.getMenos15());
			model.addAttribute("salud", basico.getSalud());
			model.addAttribute("contactoNombre",basico.getContactoNombre());
			model.addAttribute("contactoTelefono",basico.getContactoNombre());
			
			basico.setNombre(nombre);
			basico.setTelefono(telefono);
			basico.setTipo(Long.parseLong(tipoPersona));
			basico.setEps(Long.parseLong(eps));
			basico.setGenero(genero);
			if(!fechaNacimiento.isEmpty()) {
			basico.setFechaNacimiento(LocalDate.parse(fechaNacimiento, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			}
			basico.setModalidad(Long.parseLong(modalidad));
			if(embarazo.equalsIgnoreCase("on")) {
				basico.setEmbarazo(true);
			}else {
				basico.setEmbarazo(false);
			}
			basico.setDocumento(documento);
			basicoDao.save(basico);
			
			return "personas";
		}else {
		Basico entity=new Basico();
		entity.setNombre(nombre);
		entity.setTelefono(telefono);
		entity.setTipo(Long.parseLong(tipoPersona));
		entity.setEps(Long.parseLong(eps));
		entity.setGenero(genero);
		entity.setModalidad(Long.parseLong(modalidad));
		if(embarazo.equalsIgnoreCase("on")) {
			entity.setEmbarazo(true);
		}
		entity.setDocumento(documento);
		basicoDao.save(entity);
		model.addAttribute("documento", documento);
		return "personas";
		}
		
	}

	@PostMapping("registrarPersonas")
	public String registrarPersonas(@RequestParam(required = false, defaultValue = "false") String mas60, @RequestParam(required = false, defaultValue = "false") String menos15,
			@RequestParam(required = false, defaultValue = "false") String salud, @RequestParam String contactoNombre,
			@RequestParam String contactoTelefono, @RequestParam String documento, Model model) {
		Optional<Basico> opt=basicoDao.findByDocumento(documento);
		if(opt.isPresent()) {
			Basico entity=opt.get();
			if(mas60.equalsIgnoreCase("on")) {
				entity.setMas60(true);
			}else {
				entity.setMas60(false);
			}
			if(menos15.equalsIgnoreCase("on")) {
				entity.setMenos15(true);
			}else {
				entity.setMenos15(false);
			}
			if(salud.equalsIgnoreCase("on")) {
				entity.setSalud(true);
			}else {
				entity.setSalud(false);
			}
			entity.setContactoNombre(contactoNombre);
			entity.setContactoTelefono(contactoTelefono);
			basicoDao.save(entity);
			
			Optional<Comorbilidad> Comorbilidad=comorbilidadDao.findById(entity.getId());
			if(Comorbilidad.isPresent()) {
			model.addAttribute("diabetes", Comorbilidad.get().getDiabetes());
			model.addAttribute("cardio", Comorbilidad.get().getCardio());
			model.addAttribute("cerebro", Comorbilidad.get().getCerebro());
			model.addAttribute("vih", Comorbilidad.get().getVih());
			model.addAttribute("cancer", Comorbilidad.get().getCancer());
			model.addAttribute("corticoides", Comorbilidad.get().getCorticoides());
			model.addAttribute("epoc", Comorbilidad.get().getEpoc());
			model.addAttribute("nutricion", Comorbilidad.get().getNutricion());
			model.addAttribute("fumador", Comorbilidad.get().getFumador());
			}
			model.addAttribute("documento", documento);
			return "cormobilidades";
		}else {
			return "personas";
		}
	}
	
	@PostMapping("registrarComorbilidades")
	public String registrarComorbilidad(@RequestParam(required = false, defaultValue = "false") String 	diabetes,
			@RequestParam(required = false, defaultValue = "false") String cardio,@RequestParam(required = false, defaultValue = "false") String cerebro,
			@RequestParam(required = false, defaultValue = "false") String vih,@RequestParam(required = false, defaultValue = "false") String 	cancer,
			@RequestParam(required = false, defaultValue = "false") String corticoides,@RequestParam(required = false, defaultValue = "false") String epoc,
			@RequestParam(required = false, defaultValue = "false") String nutricion,@RequestParam(required = false, defaultValue = "false") String fumador,
			@RequestParam String documento, Model model) {
		
		Comorbilidad entity=new Comorbilidad();
		Optional<Basico> opt=basicoDao.findByDocumento(documento);
		entity.setId(opt.get().getId());
		if(diabetes.equalsIgnoreCase("on")) {
			entity.setDiabetes(true);
		}else {
			entity.setDiabetes(false);
		}
		if(cardio.equalsIgnoreCase("on")) {
			entity.setCardio(true);
		}else {
			entity.setCardio(false);
		}
		if(cerebro.equalsIgnoreCase("on")) {
			entity.setCerebro(true);
		}else {
			entity.setCerebro(false);
		}
		if(cancer.equalsIgnoreCase("on")) {
			entity.setCancer(true);
		}else {
			entity.setCancer(false);
		}
		if(corticoides.equalsIgnoreCase("on")) {
			entity.setCorticoides(true);
		}else {
			entity.setCorticoides(false);
		}
		if(epoc.equalsIgnoreCase("on")) {
			entity.setEpoc(true);
		}else {
			entity.setEpoc(false);
		}
		if(nutricion.equalsIgnoreCase("on")) {
			entity.setNutricion(true);
		}else {
			entity.setNutricion(false);
		}
		if(fumador.equalsIgnoreCase("on")) {
			entity.setFumador(true);
		}else {
			entity.setFumador(false);
		}
		if(vih.equalsIgnoreCase("on")) {
			entity.setVih(true);
		}else {
			entity.setVih(false);
		}
		comorbilidadDao.save(entity);
		model.addAttribute("documento", documento);
		
		
		return "registro";
	}
	
	
	@PostMapping("registrarComunidad")
	public String registrarComunidad(@RequestParam String documento, 
			@RequestParam(required = false, defaultValue = "false") String tos, @RequestParam(required = false, defaultValue = "false") String malestar,
			@RequestParam(required = false, defaultValue = "false") String fatiga, @RequestParam(required = false, defaultValue = "false") String nasal,
			@RequestParam(required = false, defaultValue = "false") String garganta, @RequestParam(required = false, defaultValue = "false") String dificultad
			,@RequestParam String temperatura, Model model) {
		Optional<Basico> opt=basicoDao.findByDocumento(documento);
		Registro entity=new Registro();
		entity.setPersona(opt.get().getId());
		entity.setTemperatura(Double.parseDouble(temperatura));
		if(tos.equalsIgnoreCase("on")) {
			entity.setTos(true);
		}else {
			entity.setTos(false);
		}
		if(malestar.equalsIgnoreCase("on")) {
			entity.setMalestar(true);
		}else {
			entity.setMalestar(false);
		}
		if(fatiga.equalsIgnoreCase("on")) {
			entity.setFatiga(true);
		}else {
			entity.setFatiga(false);
		}
		if(nasal.equalsIgnoreCase("on")) {
			entity.setNasal(true);
		}else {
			entity.setNasal(false);
		}
		if(garganta.equalsIgnoreCase("on")) {
			entity.setGarganta(true);
		}else {
			entity.setGarganta(false);
		}
		if(dificultad.equalsIgnoreCase("on")) {
			entity.setDificultad(true);
		}else {
			entity.setDificultad(false);
		}
		registroDao.save(entity);
		model.addAttribute("success", "Registro Exitoso de "+opt.get().getNombre());
		
			return "home";
		}
}
