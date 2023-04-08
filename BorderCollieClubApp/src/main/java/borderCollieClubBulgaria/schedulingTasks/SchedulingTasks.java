package borderCollieClubBulgaria.schedulingTasks;

import borderCollieClubBulgaria.service.DogService;
import borderCollieClubBulgaria.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulingTasks {

    private final UserService userService;

    private final DogService dogService;

    private final Logger LOGGER = LoggerFactory.getLogger(SchedulingTasks.class);

    public SchedulingTasks(UserService userService, DogService dogService) {
        this.userService = userService;
        this.dogService = dogService;
    }

    @Scheduled(cron = "1 1 1 1 1 *")
    public void increaseAge() {

        this.userService.increaseAgeOfUsers();
        this.dogService.increaseAgeOfDogs();

        LOGGER.info("Age are increased by scheduler!");

    }

    @Scheduled(cron = "*/60 *  * * * *")
    public void doJob() {

        LOGGER.info("I am a schedule task and I am doing very important work!!!");

    }
}
