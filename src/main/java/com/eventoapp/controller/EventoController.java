package com.eventoapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.model.Convidado;
import com.eventoapp.model.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository rep;

	@Autowired
	private ConvidadoRepository repConv;

	
	@RequestMapping(value="/cadastrarEvento",method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@PostMapping("/cadastrarEvento")	
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

	@GetMapping("/{codigo}")
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		 Evento evento =  rep.findByCodigo(codigo) ;
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");		
		mv.addObject("evento", evento);

		List<Convidado> convidados =  repConv.findByEvento(evento);
		mv.addObject("convidados", convidados);


		return mv;
	}

	@PostMapping("/{codigo}")
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, Convidado convidado) {
		Evento evento =  rep.findByCodigo(codigo) ;
		convidado.setEvento(evento);
		repConv.save(convidado);
		
		return "redirect:/{codigo}";
	}
				

}
