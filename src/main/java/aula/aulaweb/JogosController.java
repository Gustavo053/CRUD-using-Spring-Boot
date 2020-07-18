package aula.aulaweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.*;

@Controller
public class JogosController {

    private JogosService jogosService;

    @Autowired
    public void setJogosService(JogosService jogosService) {
        this.jogosService = jogosService;
    }

    @RequestMapping("/")
    public String getHome(Model model, HttpServletRequest request, HttpServletResponse response) {
        List<Jogos> jogos = jogosService.findAll();

        Date date = new Date();
        Integer day = date.getDate();
        String parserDay = String.valueOf(day);
        Integer month = date.getMonth() + 1;
        String parserMonth = String.valueOf(month);
        Integer hours = date.getHours();
        String parserHours = String.valueOf(hours);
        Integer minute = date.getMinutes();
        String parserMinute = String.valueOf(minute);

        String valueCookie = "(data|hora):"+ parserDay + "." + parserMonth + "|" + parserHours + ":" + parserMinute;

        Cookie activeUser = new Cookie("activeUser", valueCookie);
        response.addCookie(activeUser);

        model.addAttribute("jogosList", jogos);
        model.addAttribute("cookie", valueCookie);

        return "index";
    }

    @RequestMapping("/adicionar")
    public String adicionar(Model model) {
        Jogos jogo = new Jogos();
        model.addAttribute("jogo", jogo);

        return "adicionar";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String cadastrar(@ModelAttribute(name = "jogo") @Valid Jogos jogo, Errors errors, RedirectAttributes ra, HttpServletRequest request){
        if (request.getParameter("view").equals("adicionar")) {
            if (errors.hasErrors()) {
                return "adicionar";
            } else {
                jogosService.add(jogo);
                ra.addFlashAttribute("create", true);

                return "redirect:/";
            }
        } else {
            if (errors.hasErrors()) {
                return "editar";
            } else {
                jogosService.add(jogo);
                ra.addFlashAttribute("edit", true);

                return "redirect:/";
            }
        }
    }

    @RequestMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable(name = "id") Long id) {
        Jogos jogo = jogosService.findOne(id);
        ModelAndView modelAndView = new ModelAndView("editar");
        modelAndView.addObject("jogo", jogo);

        return modelAndView;
    }

    @RequestMapping("/deletar/{id}")
    public String delete(@PathVariable(name = "id") Long id, RedirectAttributes ra) {
        jogosService.delete(id);
        ra.addFlashAttribute("delete", true);

        return "redirect:/";
    }

}
