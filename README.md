# Custumer Service

### Requisitos

1. JDK 8
1. Maven 3

### Rodando

1. Clone o projeto: `https://github.com/leonardohenrique/tokio-test.git`
1. Entre na pasta `tokio-test` e execute: `mvn spring-boot:run`
1. Acesse: `http://localhost:8080/customers`


###[Observação]

Esta usando spring security 
para logar ao iniciar o projeto e gerado uma senha sempre que reiniciar o servidor 

Usuario padrão do spring = user
Exemplo: Using generated security password: e3192d37-60b4-43a0-a92b-aba4953ff68c

coloquei senha fixa 
#spring.security.user.name=user
#spring.security.user.password=customer
para manter agilidade no desenvolvimento desabilitei essa spring security 


#####################################
Para ver a paginação 
http://localhost:8080/customers?page=2

ou quantidade desejada
http://localhost:8080/customers?page=2&size=5


http://localhost:8080/swagger-ui.html#/
utilizado swagger-ui para simular as requisições

como ultizar o swagger no aquivo pom.xml coloca a depencia
<!-- Swagger -->
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>2.7.0</version>
	<scope>compile</scope>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>2.7.0</version>
	<scope>compile</scope>
</dependency>

depois implementar SwaggerConfig.class na mesma pasta da main 
no SwaggerConfig coloca uma anotação @Configuration oque indica que ele e uma arquivo de configuração do spring

apiInfo do SwaggerConfig informações importates para colocar 

.contact(new Contact(name"", url"", email""))
		.title("Customer")
		.description("Documentação API dos Clientes")
		.license("Apache Licence Version 2.0")
		.licenseUrl("https://apache.org")
		.version("1.0")


No desenvolvimento inicialmente o id da tabela Customer
estava AUTO onde muitas vezes cria seguencia que não queremos,

mudei para IDENTITY cria a chave primaria com a propia notação de "auto_increment"

foi utilizado tambem site developer.mozilla.org
para ver os codigos de status de reposta HTTP



