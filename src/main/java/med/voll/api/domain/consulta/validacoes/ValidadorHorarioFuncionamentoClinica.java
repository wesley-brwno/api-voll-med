package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsultas{
    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.data();

        boolean domigo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean antesDaAberturaDaClina = dataConsulta.getHour() < 7;
        boolean depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
        if (domigo || antesDaAberturaDaClina || depoisDoEncerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clinica");
        }
    }
}
