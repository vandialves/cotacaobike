package br.com.cotacao.dao;

import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;

import br.com.cotacao.util.Constantes;

/***
 * Classe de acesso ao DynamoDB
 * @author vandialvesdeliraneto
 *
 */
public class DynamoDAO {
	private volatile AmazonDynamoDB DynamoClient;
	private volatile DynamoDB dynamoDB;

	public DynamoDAO() {
		super();
		this.DynamoClient = AmazonDynamoDBClientBuilder
				.standard()
				.withRegion(Constantes.REGIAO_AWS)
				.build();
		this.dynamoDB  = new DynamoDB(DynamoClient);
	}

	/***
	 * Essa funcao sera utilizada em caso de possivel cenario de mudanca constante dos valores 
	 * ranges das seguradoras. Inclusao e remocao de seguradoras, etc...
	 * @param chave
	 * @return
	 * @throws Exception
	 */
	public Map<String,Object> getConfigs(String chave) throws Exception {
		Table table = this.dynamoDB.getTable(Constantes.CONFIGURACOES_TABELA);
		GetItemSpec spec = new GetItemSpec()
				.withPrimaryKey(Constantes.CONFIGURACOES_CHAVE, chave)
				.withConsistentRead(true);
		Item item = table.getItem(spec);
		if(null == item) {
			throw new Exception();
		}
		return item.asMap();
	}

}
