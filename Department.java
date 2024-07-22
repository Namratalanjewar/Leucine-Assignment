@Entity
@Table(name = "Department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "department")
    private List<StudentProfile> studentProfiles;

    @OneToMany(mappedBy = "department")
    private List<FacultyProfile> facultyProfiles;

    @OneToMany(mappedBy = "department")
    private List<AdministratorProfile> administratorProfiles;

    @OneToMany(mappedBy = "department")
    private List<Course> courses;

    // Getters and setters
}
