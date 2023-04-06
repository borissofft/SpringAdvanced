package bg.softuni.kidscare;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class KidsCareApplication {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        System.out.println("start");
        context =  SpringApplication.run(KidsCareApplication.class, args);
    }

//    @Scheduled(cron = "0 * * * * MON-FRI")
    @Scheduled(cron = "0 30 3 * * ?")
    public static void restart() {
        System.out.println("restart");
        ApplicationArguments args = context.getBean(ApplicationArguments.class);

        Thread thread = new Thread(() -> {
            context.close();
            context = SpringApplication.run(KidsCareApplication.class, args.getSourceArgs());
        });

        thread.setDaemon(false);
        thread.start();
    }

}