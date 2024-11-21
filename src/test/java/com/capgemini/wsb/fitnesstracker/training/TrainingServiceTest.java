@SpringBootTest
public class TrainingServiceIntegrationTest {

    @Autowired
    private TrainingService trainingService;

    @Test
    public void shouldFetchTrainingsForUser() {
        List<Training> trainings = trainingService.getTrainingsByUser("user1");
        assertNotNull(trainings);
    }
}
