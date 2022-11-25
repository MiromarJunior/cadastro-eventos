package com.eventoapp.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String form(@Valid Evento ev ,BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos !");
			return "redirect:/cadastrarEvento";
		}
		rep.save(ev);
		attributes.addFlashAttribute("mensagem", "Evento adicionado com sucesso !");
		return "redirect:/cadastrarEvento";
		
	}
	
	@GetMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		List<Evento> eventos = rep.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}


	@RequestMapping("/deletarEvento")
	public String deletarEvento(long codigo){
		Evento evento = rep.findByCodigo(codigo);
		rep.delete(evento);
		return "redirect:/eventos";
	}

	@RequestMapping("/deletarConvidado")
	public String deletarConvidado(String rg){
		Convidado convidado = repConv.findByRg(rg);		
		repConv.delete(convidado);
		Evento evento = convidado.getEvento();
		String codigo = ""+evento.getCodigo() ;

		return "redirect:/"+codigo;
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
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, @Valid Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()){
			attributes.addFlashAttribute("mensagem", "Verifique os campos !");
			return "redirect:/{codigo}";
		}
		Evento evento =  rep.findByCodigo(codigo) ;
		convidado.setEvento(evento);
		repConv.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso !");
		return "redirect:/{codigo}";
	}
				

}
