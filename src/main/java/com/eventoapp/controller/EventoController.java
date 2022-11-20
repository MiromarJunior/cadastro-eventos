package com.eventoapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.model.Evento;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository rep;
	
	@RequestMapping(value="/cadastrarEvento",method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/cadastrarEvento",method = RequestMethod.POST)
	public String form(Evento ev) {
		rep.save(ev);
		return "redirect:/eventos";
		
	}
	
	@GetMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		List<Evento> eventos = rep.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
				

}
