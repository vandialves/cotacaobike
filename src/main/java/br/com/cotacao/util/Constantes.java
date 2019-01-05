package br.com.cotacao.util;

/***
 * Constantes do projeto para testes, em uma possivel operacao real essa classe deve ser
 * substituida por uma entidade que busca esses valores em algum banco de dados
 * @author vandialvesdeliraneto
 *
 */
public class Constantes {
	
	//############Status e Retornos############
	public static final String _0100 = "0100";
	public static final String OPERACAO_REALIZADA_COM_SUCESSO = "Operacao realizada com sucesso";
	public static final String _0199 = "0199";
	public static final String ERRO_AO_PROCESSAR_OPERACAO = "Erro ao processar cotacao";
	
	
	//############ Parametros ############
	public static final String CONFIGURACOES_TABELA = "configuracoes_cotacao_seguros";
	public static final String CONFIGURACOES_CHAVE = "chaveconfig";
	public static final String CONFIGURACOES_TIPO ="bike";
	
	public static final String FUSOHORARIO = "America/Sao_Paulo";
	public static final String KINESIS_STREAM = "cotacaoseguros";
	public static final String REGIAO_AWS = "us-east-1";
}
