package br.com.urbanpulse.controller;

import br.com.urbanpulse.model.dto.AcompanhamentoDTO;
import br.com.urbanpulse.model.entity.Acompanhamento;
import br.com.urbanpulse.model.entity.Ocorrencia;
import br.com.urbanpulse.model.repository.AcompanhamentoRepository;
import br.com.urbanpulse.model.repository.OcorrenciaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/acompanhamentos")
public class AcompanhamentoController {

    @Autowired
    private AcompanhamentoRepository acompanhamentoRepository;

    @Autowired
    private OcorrenciaRepository ocorrenciaRepository;

    @GetMapping("/novo")
    public ModelAndView novo() {
        ModelAndView mv = new ModelAndView("formulario-acompanhamento");
        mv.addObject("ocorrencias", ocorrenciaRepository.findAll());
        return mv;
    }

    @PostMapping
    public ModelAndView cadastrar(@Valid AcompanhamentoDTO acompanhamentoDTO,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/acompanhamentos");
        }
        Ocorrencia ocorrencia = ocorrenciaRepository.findByCodigo(acompanhamentoDTO.ocorrenciaCodigo());

        if (ocorrencia != null) {
            if (acompanhamentoDTO.etapa().equalsIgnoreCase("Concluído") || acompanhamentoDTO.etapa().equalsIgnoreCase("Resolvido")) {
                ocorrencia.setStatus("CONCLUÍDA");
            } else {
                ocorrencia.setStatus("EM ANDAMENTO");
            }
            ocorrenciaRepository.save(ocorrencia);
        }
        Acompanhamento acompanhamento = new Acompanhamento(acompanhamentoDTO, ocorrencia);
        acompanhamentoRepository.save(acompanhamento);
        ModelAndView mv = new ModelAndView("redirect:/acompanhamentos");
        return mv;
    }

    @GetMapping
    public ModelAndView consultar() {
        List<Acompanhamento> acompanhamentos = acompanhamentoRepository.findAll();
        ModelAndView mv = new ModelAndView("acompanhamentos");
        mv.addObject("acompanhamentos", acompanhamentos);
        return mv;
    }

    @GetMapping("/editar/{codigo}")
    public ModelAndView consultarPorCodigo(@PathVariable Long codigo) {
        Acompanhamento acompanhamento = acompanhamentoRepository.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("formulario-acompanhamento");
        mv.addObject("acompanhamento", acompanhamento);
        mv.addObject("ocorrencias", ocorrenciaRepository.findAll());
        mv.addObject("acao", "/acompanhamentos/atualizar/" + codigo);
        return mv;
    }

    @GetMapping("/excluir/{codigo}")
    public ModelAndView excluir(@PathVariable Long codigo) {
        acompanhamentoRepository.deleteById(codigo);
        return new ModelAndView("redirect:/acompanhamentos");
    }

    @PostMapping("/atualizar/{codigo}")
    public ModelAndView atualizar(@PathVariable Long codigo,
                                  @Valid AcompanhamentoDTO acompanhamentoDTO,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return new ModelAndView("redirect:/acompanhamentos");
        }
        Ocorrencia ocorrencia = ocorrenciaRepository.findByCodigo(acompanhamentoDTO.ocorrenciaCodigo());
        if (ocorrencia != null) {
            if (acompanhamentoDTO.etapa().equalsIgnoreCase("Concluído") || acompanhamentoDTO.etapa().equalsIgnoreCase("Resolvido")) {
                ocorrencia.setStatus("CONCLUÍDA");
            } else {
                ocorrencia.setStatus("EM ANDAMENTO");
            }
            ocorrenciaRepository.save(ocorrencia);
        }
        Acompanhamento acompanhamento = new Acompanhamento(acompanhamentoDTO, ocorrencia);
        acompanhamento.setCodigo(codigo);
        acompanhamentoRepository.save(acompanhamento);
        ModelAndView mv = new ModelAndView("redirect:/acompanhamentos");
        return mv;
    }
}