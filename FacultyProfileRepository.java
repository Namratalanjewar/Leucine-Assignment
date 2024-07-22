public interface FacultyProfileRepository extends JpaRepository<FacultyProfile, Long> {
    FacultyProfile findByUser(User user);
}
