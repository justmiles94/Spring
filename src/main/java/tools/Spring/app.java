package tools.Spring;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;

//@EnableJms
@SpringBootApplication
    @ComponentScan(basePackages = { "tools.Spring.Controllers", "tools.Spring.messaging", "tools.Spring.soap" })
    public class App {
	
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	//@Autowired
	//JmsTemplate jmsTemplate;
	
	@Autowired
	    Environment env;

	Logger log = Logger.getLogger(App.class);

	@Value("${USER}")
	    String prop;

	//@Value("${environments.dev.url}")
	//String yaml;

	@Value("${spring.application.name:}")
	    String cmd;

	@Value("${app.name}")
	    String appname;
	
	@Value("${spring.info.build.location:}")
	    String buildLocation;

	@Autowired
	    JdbcTemplate jdbc;
	
	//@Autowired
	//QuoteClient quoteClient;

	// @Autowired
	// MailSender sender;
	
	@Autowired
	    private static ApplicationContext ap;
	
	public static void main(String[] args) {
	    ap = SpringApplication.run(App.class, args);
	}

	@Bean
	    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	    return args -> {
		log.info(prop);
		// openWebConnection();
		print(cmd, appname, env.getProperty("app.name"), env.getProperty("user.home"), buildLocation);
		//jmsTemplate.des
		//jmsTemplate.convertAndSend(new Email("justmiles94@gmail.com", ""));
		//http://www.webservice.com/globalweather.asmx?wsdl
		//String ticker = "MSFT";
		//
		//if (args.length > 0) {
		//ticker = args[0];
		//}
		//GetQuoteResponse response = quoteClient.getQuote(ticker);
		//System.err.println(response.getGetQuoteResult());
		
		
		// MimeMessageHelper message = sender.newMessage();
		// message.setFrom("justmiles94@gmail.com");
		// message.setTo("justmiles94@gmail.com");
		// message.setSubject("SUBJECT - TEST");
		// message.setText("hello world");
		// sender.sendMail(message);
		// resultSetConnection();
		// List<String> names = (List<String>) jdbc.queryForList("select
		// FirstName from USERS", String.class);
		// names.forEach(System.out::println);
	    };
	}
	
	public status void printBeans(){
	    String[] beanNames = ctx.getBeanDefinitionNames();
	    Arrays.sort(beanNames);
	    beanNames.foreach(log::info);
	}

	@Scheduled(fixedRate = 5000)
	    public void reportCurrentTime() {
	    log.info("The time is now {" + dateFormat.format(new Date()) + "}");
	}

	//    @Scheduled(fixedRate = 60000)
	//    public void reportCurrentTim() {
	//        log.info("The time is now {" + dateFormat.format(new Date()) + " }");
	//        int returnCode = 0;
	//        try{
	//        SpringApplication.exit(ap, () -> returnCode);
	//        }catch(IllegalArgumentException e){
	//        print("Null context still acts appropiately on shutdown", e);
	//        }
	//        print(returnCode);
	//    }
	
	public void print(Object... objects){
	    Arrays.asList(objects).forEach(log::info);
	}
    }
