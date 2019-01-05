package br.com.cotacao.dao;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehose;
import com.amazonaws.services.kinesisfirehose.AmazonKinesisFirehoseClientBuilder;

import br.com.cotacao.util.Constantes;

/***
 * Classe que gerencia o objeto cliente do Kinesis
 * @author vandialvesdeliraneto
 *
 */
public class KinesisClientFactory {

	private KinesisClientFactory() {
	}

	private static class Holder {
		private static final AmazonKinesisFirehoseClientBuilder builder = AmazonKinesisFirehoseClientBuilder
				.standard()
				.withRegion(Constantes.REGIAO_AWS)
				.withClientConfiguration(new ClientConfiguration()
						.withGzip(true)
						.withMaxConnections(200));
	}

	public static AmazonKinesisFirehose getClient() {
		return Holder.builder.build();
	}

}