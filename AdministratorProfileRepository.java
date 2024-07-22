public interface AdministratorProfileRepository extends JpaRepository<AdministratorProfile, Long> {
    AdministratorProfile findByUser(User user);
}