public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
    StudentProfile findByUser(User user);
}
