package com.ufps.edu.controlador;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.ufps.edu.model.dao.IBasicoDao;
import com.ufps.edu.model.dao.IRegistroDao;
import com.ufps.edu.model.entidades.Basico;
import com.ufps.edu.model.entidades.Registro;

@Controller()
@RequestMapping("/home")
public class ControladorIngresoQr {
	
	@Autowired
	private IBasicoDao basicoDao;
	
	@Autowired
	private IRegistroDao registroDao;
	
	@GetMapping("qr")
	public String redireccionarQr() {
		return "qr";
	}
	
	@PostMapping("validarQr")
	public String validarQr(@RequestParam String documento, @RequestParam String temperatura
			, Model model) {
		//System.out.println(documento);
		Optional<Basico> basico=basicoDao.findByDocumento(documento);
		if(!basico.isPresent()) {
			model.addAttribute("error", "Esta cedula no pertenece a nadie de la universidad");
			return "qr";
		}else {
			Registro registro=new Registro();
			registro.setTemperatura(Double.parseDouble(temperatura));
			registro.setPersona(basico.get().getId());
			registroDao.save(registro);
			model.addAttribute("success", "Validación exitosa");
			return "home";
		}
	}
}
