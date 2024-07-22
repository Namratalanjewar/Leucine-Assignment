@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    @Autowired
    private AdministratorProfileRepository administratorProfileRepository;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();

        User existingUser = userRepository.findByUsername(user.getUsername());

        if (existingUser == null || !existingUser.getPassword().equals(user.getPassword())) {
            response.put("error", "Invalid username or password");
            return ResponseEntity.badRequest().body(response);
        }

        if (existingUser.getRole().equals(Role.STUDENT)) {
            StudentProfile studentProfile = studentProfileRepository.findByUser(existingUser);
            response.put("role", "student");
            response.put("name", studentProfile.getUser().getName());
            response.put("department", studentProfile.getDepartment().getName());
            response.put("year", studentProfile.getYear());
        } else if (existingUser.getRole().equals(Role.FACULTY_MEMBER)) {
            FacultyProfile facultyProfile = facultyProfileRepository.findByUser(existingUser);
            response.put("role", "faculty_member");
            response.put("name", facultyProfile.getUser().getName());
            response.put("department", facultyProfile.getDepartment().getName());
            response.put("office_hours", facultyProfile.getOffice_hours());
        } else if (existingUser.getRole().equals(Role.ADMINISTRATOR)) {
            AdministratorProfile administratorProfile = administratorProfileRepository.findByUser(existingUser);
            response.put("role", "administrator");
            response.put("name", administratorProfile.getUser().getName());
            response.put("department", administratorProfile.getDepartment().getName());
        }

        return ResponseEntity.ok(response);
    }
}