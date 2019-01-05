package br.com.cotacao.executor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;
import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.cotacao.dao.DynamoDAO;
import br.com.cotacao.exception.CotacaoServiceException;
import br.com.cotacao.model.Pedido;
import br.com.cotacao.model.Proposta;
import br.com.cotacao.util.Constantes;
import br.com.cotacao.util.Utilitarios;


@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableAsync
public class Seguradora {

	public static volatile DynamoDAO dynamoDAO = new DynamoDAO();
	private volatile Logger log = LoggerFactory.getLogger(this.getClass());
	private final SimpleDateFormat padraoTimestamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS");
	Gson jsonAux = new Gson();
	
	@Autowired
	KinesisAsync filaKinesis;


	public static void main(String[] args) {
		TimeZone tz = TimeZone.getTimeZone(Constantes.FUSOHORARIO);
		TimeZone.setDefault(tz);
		SpringApplication.run(Seguradora.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(200);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(400);
		executor.setThreadNamePrefix("AsyncThreadID-");
		executor.initialize();
		return executor;
	}

	@RequestMapping(value = { "/check","/test" }, method = RequestMethod.GET)
	String healthCheck() {
		return "{ \"mensagem\" : \"Health Check de Exemplo para o Load Balancer\" }";
	}

	@RequestMapping(value = {"/cotar"}, method = RequestMethod.POST)
	Proposta cotacaoSeguradoras(@RequestBody HashMap<String, Object> requestSpring) {
		String startTime = this.padraoTimestamp.format(new Date());
		Proposta response = null;
		Pedido inputs = null;
		try {
			inputs = Utilitarios.validarInputs(requestSpring);
			//TODO dinamicamente pegar os ranges de valores das seguradoras, exemplo: Map<String, Object> configuracoes = dynamoDAO.getConfigs(Constantes.CONFIGURACOES_TIPO);

			double premioAlfa = Cotacao.cotarBike_ALFA(inputs.getPrice());
			double premioBeta = Cotacao.cotarBike_BETA(inputs.getPrice());
			double premioSigma = Cotacao.cotarBike_SIGMA(inputs.getPrice());
			
			String endTime = this.padraoTimestamp.format(new Date());
			response = Utilitarios.montarResposta(startTime,endTime,premioAlfa, premioBeta, premioSigma);
			filaKinesis.sendLog(inputs, response);
			this.log.info("Processado com Sucesso:"+ '\t' + jsonAux.toJson(response));
		} 
		catch (Exception e)
		{
			Proposta responsecatch = null;
			if(e instanceof CotacaoServiceException) {
				responsecatch = Proposta.getResponseError(e);
			}else {
				responsecatch = Proposta.getResponseError();
			}
			responsecatch.setDtInicio(startTime);
			responsecatch.setDtFim(this.padraoTimestamp.format(new Date()));

			filaKinesis.sendLog(inputs, responsecatch);
			this.log.error(jsonAux.toJson(responsecatch));
			return responsecatch;
		}

		return response;
	}






}