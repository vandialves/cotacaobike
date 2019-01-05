package br.com.cotacao.executor;

import java.nio.ByteBuffer;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.model.PutRecordRequest;
import com.amazonaws.services.kinesisfirehose.model.PutRecordResult;
import com.amazonaws.services.kinesisfirehose.model.Record;
import com.google.gson.Gson;

import br.com.cotacao.dao.KinesisClientFactory;
import br.com.cotacao.model.Pedido;
import br.com.cotacao.model.Proposta;
import br.com.cotacao.util.Constantes;

/***
 * Classe que envia os dados do pedido e da proposta para a fila do Kinesis
 * O envio assincrono eh feito por um pool de threads gerenciadas pelo Spring 
 * @author vandialvesdeliraneto
 *
 */

@Service
public class KinesisAsync{

	public KinesisAsync() {
	}

	@Async
	public CompletableFuture<String> sendLog(Pedido pedido, Proposta proposta) {
		try {
			Logger log = LoggerFactory.getLogger(this.getClass());
			Gson aux = new Gson();

			String jsonPedido = aux.toJson(pedido);
			String jsonProposta = aux.toJson(proposta);
			String sendlogs = "Pedido-" + jsonPedido + "Proposta-" + jsonProposta; //exemplo para manter simplicidade

			AmazonKinesisFirehose kinesisClient = KinesisClientFactory.getClient();
			PutRecordRequest putRecord = new PutRecordRequest();
			putRecord.setDeliveryStreamName(Constantes.KINESIS_STREAM);
			Record record = new Record();
			record.setData(ByteBuffer.wrap(sendlogs.getBytes()));
			putRecord.setRecord(record);
			PutRecordResult result = kinesisClient.putRecord(putRecord);

			if(result != null) {
				String recordId = "";
				recordId = result.getRecordId();
				if(recordId.isEmpty()) {
					log.error("Erro ao enviar o Log para a fila.");
					//TODO Criar mecanismo de recuperacao de falha e/ou tentar novamente
				}
				else {
					log.info("SUCESSO-LOG: " + Thread.currentThread().getName());
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return CompletableFuture.completedFuture("Ok");
	}

}