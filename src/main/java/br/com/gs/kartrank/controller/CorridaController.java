package br.com.gs.kartrank.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.gs.kartrank.model.Resultado;
import br.com.gs.kartrank.service.CorridaService;

@Controller
@RequestMapping("/")
public class CorridaController {
    
    private CorridaService corridaService;
    
    public CorridaController(CorridaService corridaService) {
	this.corridaService = corridaService;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String resultado(Model model) {
          List<Resultado> resultados = corridaService.gerarResultado();
          if (!resultados.isEmpty()) {
                model.addAttribute("resultados", resultados);
                model.addAttribute("melhorVoltaCorrida", corridaService.melhorVoltaDaCorrida());
          }
          return "resultado";
    }

}
