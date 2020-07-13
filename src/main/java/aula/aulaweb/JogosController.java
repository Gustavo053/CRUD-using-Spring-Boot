package aula.aulaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class JogosController {

    private JogosService jogosService;

    @Autowired
    public void setJogosService(JogosService jogosService) {
        this.jogosService = jogosService;
    }

    @RequestMapping("/")
    public String getHome(Model model) {
        List<Jogos> jogos = jogosService.findAll();
        model.addAttribute("jogosList", jogos);

        return "index";
    }

    @RequestMapping("/adicionar")
    public String adicionar(Model model) {
        Jogos jogo = new Jogos();
        model.addAttribute("jogo", jogo);

        return "adicionar";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String cadastrar(@ModelAttribute Jogos jogo){
        jogosService.add(jogo);

        return "redirect:/";
    }

    @RequestMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable(name = "id") Long id) {
        Jogos jogo = jogosService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("editar");
        modelAndView.addObject("jogo", jogo);

        return modelAndView;
    }

    @RequestMapping("/deletar/{id}")
    public String delete(@PathVariable(name = "id") Long id) {
        jogosService.delete(id);

        return "redirect:/";
    }

}
