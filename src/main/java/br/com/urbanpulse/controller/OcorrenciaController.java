package br.com.urbanpulse.controller;

import br.com.urbanpulse.model.dto.OcorrenciaDTO;
import br.com.urbanpulse.model.entity.Ocorrencia;
import br.com.urbanpulse.model.entity.Usuario;
import br.com.urbanpulse.model.repository.OcorrenciaRepository;
import br.com.urbanpulse.model.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
public class OcorrenciaController {

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("formulario-ocorrencia");
        mv.addObject("usuarios", usuarioRepository.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView cadastrar(@Valid OcorrenciaDTO ocorrenciaDTO,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/ocorrencias");
        }
        Usuario usuario = usuarioRepository.findByCodigo(ocorrenciaDTO.usuarioCodigo());

        if (usuario != null) {
            usuario.setPontosEngajamento(usuario.getPontosEngajamento() + 10);
            usuarioRepository.save(usuario);
        }
        Ocorrencia ocorrencia = new Ocorrencia(ocorrenciaDTO, usuario);
        ocorrenciaRepository.save(ocorrencia);
        ModelAndView mv = new ModelAndView("redirect:/ocorrencias");
        return mv;
    }

    @GetMapping
    public ModelAndView consultar() {
        List<Ocorrencia> ocorrencias = ocorrenciaRepository.findAll();
        ModelAndView mv = new ModelAndView("ocorrencias");
        mv.addObject("ocorrencias", ocorrencias);
        return mv;
    }

    @GetMapping("/editar/{codigo}")
    public ModelAndView consultarPorCodigo(@PathVariable Long codigo) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("formulario-ocorrencia");
        mv.addObject("ocorrencia", ocorrencia);
        mv.addObject("usuarios", usuarioRepository.findAll());
        mv.addObject("acao", "/ocorrencias/atualizar/" + codigo);
        return mv;
    }

    @GetMapping("/excluir/{codigo}")
    public ModelAndView excluir(@PathVariable Long codigo) {
        ocorrenciaRepository.deleteById(codigo);
        return new ModelAndView("redirect:/ocorrencias");
    }

    @PostMapping("/atualizar/{codigo}")
    public ModelAndView atualizar(@PathVariable Long codigo,
                                  @Valid OcorrenciaDTO ocorrenciaDTO,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/ocorrencias");
        }
        Usuario usuario = usuarioRepository.findByCodigo(ocorrenciaDTO.usuarioCodigo());
        Ocorrencia ocorrencia = new Ocorrencia(ocorrenciaDTO, usuario);
        ocorrencia.setCodigo(codigo);
        ocorrenciaRepository.save(ocorrencia);
        ModelAndView mv = new ModelAndView("redirect:/ocorrencias");
        return mv;
    }
}